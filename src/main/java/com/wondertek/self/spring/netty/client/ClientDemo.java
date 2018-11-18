package com.wondertek.self.spring.netty.client;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientDemo {

    public static void main(String[] args) {

        ClientBootstrap bootstrap = new ClientBootstrap();

        //创建线程池
        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();

        bootstrap.setFactory(new NioClientSocketChannelFactory(boss, worker));

        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("decode", new StringDecoder());
                pipeline.addLast("encode", new StringEncoder());
                pipeline.addLast("clientHandler", new ClientHandler());
                return pipeline;
            }
        });
        ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress(1010));
        Channel channel = channelFuture.getChannel();
        System.out.println("客户端启动成功");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入：");
            channel.write(scanner.next());
        }
    }
}
