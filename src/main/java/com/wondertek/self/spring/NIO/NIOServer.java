package com.wondertek.self.spring.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOServer {

    // 通道管理器
    private Selector selector;

    /**
     * 绑定端口号
     */
    public void initServer(int port) throws IOException {
        //设置一个serverSocet通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //设置通道为非阻塞
        serverSocketChannel.configureBlocking(false);
        //将该通道对应的serverSocket绑定到port端口
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        //获得一个通道管理器
        this.selector = Selector.open();
        // 将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件,注册该事件后，
        // 当该事件到达时，selector.select()会返回，如果该事件没到达selector.select()会一直阻塞。
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    /**
     * 采用轮询的方式监听select上是否有需要处理的事件
     */
    public void listen() throws IOException {
        System.out.println("服务端启动成功");
        while (true) {
            //当注册事件到达的时候返回，否则一直阻塞
            selector.select();
            Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                handle(selectionKey);
            }
        }
    }

    /**
     * 处理请求
     * @param selectionKey
     */
    private void handle(SelectionKey selectionKey) throws IOException {
        //客户端请求连接事件
        if (selectionKey.isAcceptable()) {
            handleAccept(selectionKey);
        } else if (selectionKey.isReadable()) {
            //获得了可读事件
            handleRead(selectionKey);
        }
    }

    /**
     * 处理连接事件
     * @param selectionKey
     * @throws IOException
     */
    private void handleAccept(SelectionKey selectionKey) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
        //获得和客户端连接的通道
        SocketChannel socketChannel = serverSocketChannel.accept();

        socketChannel.configureBlocking(false);
        //此处可以添加向客户端发送的信息
        System.out.println("新的客户端连接");
        //和客户端连接成功之后，将通道设置读的权限，可以接受客户端传来的信息
        socketChannel.register(this.selector, SelectionKey.OP_READ);
    }

    /**
     * 处理读的请求
     * @param selectionKey
     */
    private void handleRead(SelectionKey selectionKey) throws IOException {
        //服务器可读取信息，得到事件发生的channel
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        //创建读取的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int read = socketChannel.read(buffer);
        if (read > 0) {
            byte[] bytes = buffer.array();
            String msg = new String(bytes).trim();
            System.out.println("服务端收到消息：" + msg);

            //回写数据
            ByteBuffer outBuffer = ByteBuffer.wrap("好的".getBytes());
            socketChannel.write(outBuffer);
        }
    }

    /**
     * 启动服务端测试
     *
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        NIOServer server = new NIOServer();
        server.initServer(8000);
        server.listen();
    }
}
