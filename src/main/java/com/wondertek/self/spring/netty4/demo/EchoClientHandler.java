package com.wondertek.self.spring.netty4.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 *
 * Created by wd on 2019/1/8.
 */
// @Sharable用于标记EchoClientHandler，可以在channel中分享使用
@ChannelHandler.Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf>{

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //一旦建立连接，将会发送消息
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks".getBytes()));
    }

    /**
     * 捕获异常
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //打印堆栈
        cause.printStackTrace();
        //关闭channel
        ctx.close();

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        //记录收到的消息
        System.out.println("Client received: " + msg.toString(CharsetUtil.UTF_8));

    }
}
