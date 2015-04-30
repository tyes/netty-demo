package com.xd.netty.echo.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author xiaodong
 *
 * @date 2015年4月30日 下午5:18:56
 */
public class EchoClientHandler extends ChannelHandlerAdapter {

	private int counter;
	private final String ECHO_REQ = "Hi, Hiro. Welcome to Netty.$_";

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for (int i = 0; i < 10; i++) {
			ByteBuf echo = Unpooled.copiedBuffer(ECHO_REQ.getBytes());
			ctx.writeAndFlush(echo);
		}

	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("This is " + ++counter + " times receive server : [ " + msg + " ]");
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

}
