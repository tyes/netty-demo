package com.xd.netty.subscribe.client;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

import com.xd.netty.subscribe.SubscribeReqProto;

public class SubscribeClientHandler extends ChannelHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for(int i=0; i< 10; i++){
			ctx.write(req(i));
		}
		ctx.flush();
	}

	private SubscribeReqProto.SubscribeReq req(int i) {
		SubscribeReqProto.SubscribeReq.Builder req = SubscribeReqProto.SubscribeReq.newBuilder();
		req.setUsername("xiaodong");
		req.setSubReqId(i);
		req.setProductName("netty protobuf test!");
		List<String> adddress = new ArrayList<String>();
		adddress.add("1111");
		adddress.add("2222");
		adddress.add("3333");
		req.addAllAddress(adddress);
		return req.build();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("receive server response : [ " + msg + " ]" );
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
	
	
}
