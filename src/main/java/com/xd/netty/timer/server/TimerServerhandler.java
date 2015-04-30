package com.xd.netty.timer.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimerServerhandler extends ChannelHandlerAdapter {

	private int counter;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// ByteBuf byteBuf = (ByteBuf) msg;
		// byte[] req = new byte[byteBuf.readableBytes()];
		// byteBuf.readBytes(req);
		// String body = new String(req, "UTF-8").substring(0, req.length -
		// System.getProperty("line.separator").length());

		String body = (String) msg;
		System.out.println("The time server receive order : " + body + "; the counter is " + ++counter);
		String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new java.util.Date(System.currentTimeMillis())
				.toString() : "BAD QUERY";
		currentTime = currentTime + System.getProperty("line.separator");
		ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
		ctx.writeAndFlush(resp);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}

}
