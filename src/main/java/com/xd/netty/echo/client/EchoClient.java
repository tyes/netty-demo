package com.xd.netty.echo.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author xiaodong
 *
 * @date 2015年4月30日 下午5:14:08
 */
public class EchoClient {

	public void connect(String host, int port) {
		EventLoopGroup group = new NioEventLoopGroup();
		
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ChannelPipeline pipeline = ch.pipeline();
							ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
							pipeline.addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
							pipeline.addLast(new StringDecoder());
							pipeline.addLast(new EchoClientHandler());
						}
					});
			ChannelFuture future = b.connect(host, port).sync();
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String host = "127.0.0.1";
		int port = 8999;
		new EchoClient().connect(host, port);
	}

}
