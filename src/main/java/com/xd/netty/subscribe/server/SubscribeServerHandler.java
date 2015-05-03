package com.xd.netty.subscribe.server;

import com.xd.netty.subscribe.SubscribeReqProto;
import com.xd.netty.subscribe.SubscribeRespProto;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 
 * @author xiaodong
 *
 * @date 2015年5月3日 下午2:42:29
 */
public class SubscribeServerHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq) msg;
		if ("xiaodong".equalsIgnoreCase(req.getUsername())) {
			System.out.println("Service accept the client subscribe req : [ " + req.toString() + " ]");
			ctx.writeAndFlush(resp(req.getSubReqId()));
		}
	}

	private SubscribeRespProto.SubscribeResp resp(int subReqId) {
		SubscribeRespProto.SubscribeResp.Builder resp = SubscribeRespProto.SubscribeResp.newBuilder();
		resp.setSubReqId(subReqId);
		resp.setRespCode(0);
		resp.setDesc("netty protobuf test!");
		return resp.build();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
