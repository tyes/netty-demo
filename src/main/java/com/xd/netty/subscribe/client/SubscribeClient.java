/**
 * 
 */
package com.xd.netty.subscribe.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

import com.xd.netty.subscribe.SubscribeRespProto;

/**
 * @author xiaodong
 *
 * @date 2015年5月3日 下午2:55:19
 */
public class SubscribeClient {

	private void connect(String host, int port) {
		EventLoopGroup group = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel sc) throws Exception {
						sc.pipeline().addLast(new ProtobufVarint32FrameDecoder());
						sc.pipeline().addLast(
								new ProtobufDecoder(SubscribeRespProto.SubscribeResp.getDefaultInstance()));
						sc.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
						sc.pipeline().addLast(new ProtobufEncoder());
						sc.pipeline().addLast(new SubscribeClientHandler());
					}
				});
		try {
			ChannelFuture future = b.connect(host, port).sync();
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String host = "127.0.0.1";
		int port = 8999;
		new SubscribeClient().connect(host, port);
		;

	}

}
