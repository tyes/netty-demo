package com.xd.netty.protobuf.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import com.xd.netty.protobuf.AddressBookProtos;
import com.xd.netty.protobuf.AddressBookProtos.Person;

public class ProtobufNettyClientHandler extends SimpleChannelInboundHandler<AddressBookProtos.Person> {

//	@Override
//	protected void channelRead0(ChannelHandlerContext ctx, AddressBookProtos.Person msg) throws Exception {
//		//打印接收到的信息
//		System.out.println(msg.toString());
//	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		
		System.out.println("Client active ");
		
		AddressBookProtos.AddressBook.Builder addressBookBuilder = AddressBookProtos.AddressBook.newBuilder();
		 
        AddressBookProtos.Person.PhoneNumber.Builder phoneNumberBuilder = AddressBookProtos.
                Person.PhoneNumber.newBuilder();
 
        AddressBookProtos.Person.Builder personBuilder = AddressBookProtos.Person.newBuilder();
        personBuilder.setEmail("744858873@qq.com").setId(123456789).setName("hellolyx");
        personBuilder.addPhone(phoneNumberBuilder.setNumber("15840330465").setType(AddressBookProtos.Person.PhoneType.HOME).build());
        personBuilder.addPhone(phoneNumberBuilder.setNumber("15840330466").setType(AddressBookProtos.Person.PhoneType.WORK).build());
        personBuilder.addPhone(phoneNumberBuilder.setNumber("15840330467").setType(AddressBookProtos.Person.PhoneType.MOBILE).build());
        personBuilder.addPhone(phoneNumberBuilder.setNumber("15840330468").setType(AddressBookProtos.Person.PhoneType.MOBILE).build());
        personBuilder.addPhone(phoneNumberBuilder.setNumber("15840330469").setType(AddressBookProtos.Person.PhoneType.MOBILE).build());
        personBuilder.setPhone(0, phoneNumberBuilder.setNumber("110").setType(AddressBookProtos.Person.PhoneType.MOBILE).build());
 
        //向电话薄里添加一个联系人
        addressBookBuilder.addPerson(personBuilder.build());
 
        personBuilder.setEmail("78655676@qq.com").setId(123456789).setName("hellodog");
        personBuilder.addPhone(phoneNumberBuilder.setNumber("15840330465").setType(AddressBookProtos.Person.PhoneType.HOME).build());
        personBuilder.addPhone(phoneNumberBuilder.setNumber("15840330466").setType(AddressBookProtos.Person.PhoneType.WORK).build());
        personBuilder.addPhone(phoneNumberBuilder.setNumber("15840330467").setType(AddressBookProtos.Person.PhoneType.MOBILE).build());
        personBuilder.addPhone(phoneNumberBuilder.setNumber("15840330468").setType(AddressBookProtos.Person.PhoneType.MOBILE).build());
        personBuilder.addPhone(phoneNumberBuilder.setNumber("15840330469").setType(AddressBookProtos.Person.PhoneType.MOBILE).build());
        personBuilder.setPhone(0, phoneNumberBuilder.setNumber("119").setType(AddressBookProtos.Person.PhoneType.MOBILE).build());
 
        //再次向电话薄里添加一个联系人
        addressBookBuilder.addPerson(personBuilder.build());
 
        personBuilder.setEmail("78655676@qq.com").setId(123456789).setName("hellopig");
        personBuilder.addPhone(phoneNumberBuilder.setNumber("15840330465").setType(AddressBookProtos.Person.PhoneType.HOME).build());
        personBuilder.addPhone(phoneNumberBuilder.setNumber("15840330466").setType(AddressBookProtos.Person.PhoneType.WORK).build());
        personBuilder.addPhone(phoneNumberBuilder.setNumber("15840330467").setType(AddressBookProtos.Person.PhoneType.MOBILE).build());
        personBuilder.addPhone(phoneNumberBuilder.setNumber("15840330468").setType(AddressBookProtos.Person.PhoneType.MOBILE).build());
        personBuilder.addPhone(phoneNumberBuilder.setNumber("15840330469").setType(AddressBookProtos.Person.PhoneType.MOBILE).build());
        personBuilder.setPhone(0, phoneNumberBuilder.setNumber("124").setType(AddressBookProtos.Person.PhoneType.MOBILE).build());
 
        addressBookBuilder.addPerson(personBuilder.build());
        AddressBookProtos.AddressBook addressBook = addressBookBuilder.build();
        
        ctx.writeAndFlush(addressBook);
	}

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Person msg) throws Exception {
		//打印接收到的信息
				System.out.println(msg.toString());
		
	}

//	@Override
//	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//		super.exceptionCaught(ctx, cause);
//	}

	
	
}
