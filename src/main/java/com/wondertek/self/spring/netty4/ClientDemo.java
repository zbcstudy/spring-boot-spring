package com.wondertek.self.spring.netty4;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ClientDemo {

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();

        EventLoopGroup boss = new NioEventLoopGroup();

        try {
            bootstrap.group(boss);

            //设置socket
            bootstrap.channel(NioSocketChannel.class);

            //设置管道
            bootstrap.handler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel ch) throws Exception {
                    ch.pipeline().addLast(new StringEncoder());
                    ch.pipeline().addLast(new StringDecoder());
                    ch.pipeline().addLast(new Netty4Handler());
                }
            });

            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8888);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.println("请输入：");
                String msg = bufferedReader.readLine();
                channelFuture.channel().writeAndFlush(msg);
            }
        } catch (Exception e) {

        }finally {
            boss.shutdownGracefully();
        }
    }
}
