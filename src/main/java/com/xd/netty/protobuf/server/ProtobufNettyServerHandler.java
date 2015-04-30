package com.xd.netty.protobuf.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import com.xd.netty.protobuf.AddressBookProtos;
import com.xd.netty.protobuf.AddressBookProtos.AddressBook;

public class ProtobufNettyServerHandler extends SimpleChannelInboundHandler<AddressBookProtos.AddressBook> {

//	@Override
//	protected void channelRead0(ChannelHandlerContext ctx, AddressBookProtos.AddressBook msg) throws Exception {
//		System.out.println("服务器接收到的数据是： " + msg.toString());
//		AddressBookProtos.Person person = msg.getPerson(0);
//		//把电话薄中的第一个人返回给客户端
//		ctx.writeAndFlush(person);
//	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("RamoteAddress : " + ctx.channel().remoteAddress() + " active");
	//	ctx.writeAndFlush("Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n");
		super.channelActive(ctx);
	}

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, AddressBook msg) throws Exception {
		System.out.println("服务器接收到的数据是： " + msg.toString());
		AddressBookProtos.Person person = msg.getPerson(0);
		//把电话薄中的第一个人返回给客户端
		ctx.writeAndFlush(person);
		
	}
}
