package com.wondertek.self.spring.netty4;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ServerDemo {

    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();


        //创建线程池
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            //绑定线程池
            serverBootstrap.group(boss, worker);

            //设置socket连接工厂
            serverBootstrap.channel(ServerSocketChannel.class);
            //设置channel
            serverBootstrap.childHandler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel ch) throws Exception {
                    ch.pipeline().addLast(new StringEncoder());
                    ch.pipeline().addLast(new StringDecoder());
                    ch.pipeline().addLast(new Netty4Handler());
                }
            });

            //serverSocketChannel的设置，链接缓冲池的大小
            serverBootstrap.option(ChannelOption.SO_BACKLOG, 2018);
            //socketChannel的设置,维持链接的活跃，清除死链接
            serverBootstrap.childOption(ChannelOption.TCP_NODELAY, true);
            //socketChannel的设置,关闭延迟发送
            serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture channelFuture = serverBootstrap.bind(8888);
            System.out.println("server start");

            //等待服务器端关闭
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //释放资源，关闭线程池
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
