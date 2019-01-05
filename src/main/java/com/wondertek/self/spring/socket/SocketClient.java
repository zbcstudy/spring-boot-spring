package com.wondertek.self.spring.socket;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @Author zbc
 * @Date 21:40-2019/1/5
 */
public class SocketClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        //超时时间
        socket.setSoTimeout(3000);
        socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(), 2000), 3000);
        System.out.println("已发起客户端连接,并进行后续操作");
        System.out.println("客户端信息：" + socket.getLocalAddress() + "P: " + socket.getLocalPort());
        System.out.println("服务端信息：" + socket.getInetAddress() + "P: " + socket.getPort());

        try {
            todo(socket);
        } catch (IOException e) {
            System.out.println("异常退出");
        }

        socket.close();
        System.out.println("客户端已退出");
    }

    private static void todo(Socket client) throws IOException{
        //构建键盘输入流
        InputStream inputStream = System.in;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        //得到socket输出流，并转换为打印流
        OutputStream outputStream = client.getOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        //得到socket输入流
        InputStream socketInputStream = client.getInputStream();
        BufferedReader socketBufferReader = new BufferedReader(new InputStreamReader(socketInputStream));

        boolean flag = true;
        do {
            //键盘读取一行
            String line = bufferedReader.readLine();
            //发送到服务器
            printStream.println(line);

            //从服务器读取一行
            String echo = socketBufferReader.readLine();
            if ("bye".equalsIgnoreCase(echo)) {
                flag = false;
            } else {
                System.out.println(echo);
            }
        } while (flag);

        socketBufferReader.close();
        printStream.close();

    }
}
