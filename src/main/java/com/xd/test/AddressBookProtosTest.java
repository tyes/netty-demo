package com.xd.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.DynamicMessage;
import com.xd.netty.protobuf.AddressBookProtos;

public class AddressBookProtosTest {

	public static void main(String[] args) {
		AddressBookProtos.AddressBook.Builder addressBooBuilder = AddressBookProtos.AddressBook.newBuilder();

		AddressBookProtos.Person.PhoneNumber.Builder phoneNumbBuilder = AddressBookProtos.Person.PhoneNumber
				.newBuilder();

		AddressBookProtos.Person.Builder personBuilder = AddressBookProtos.Person.newBuilder();

		personBuilder.setId(1).setName("halo").setEmail("halo@gmail.com");
		personBuilder.addPhone(phoneNumbBuilder.setNumber("18611154").setType(AddressBookProtos.Person.PhoneType.HOME)
				.build());

		personBuilder.addPhone(phoneNumbBuilder.setNumber("18611155").setType(AddressBookProtos.Person.PhoneType.WORK)
				.build());
		personBuilder.addPhone(phoneNumbBuilder.setNumber("18611156").setType(AddressBookProtos.Person.PhoneType.HOME)
				.build());
		personBuilder.addPhone(phoneNumbBuilder.setNumber("18611157")
				.setType(AddressBookProtos.Person.PhoneType.MOBILE).build());
		personBuilder.addPhone(phoneNumbBuilder.setNumber("18611158")
				.setType(AddressBookProtos.Person.PhoneType.MOBILE).build());
		personBuilder.setPhone(0, phoneNumbBuilder.setNumber("110").setType(AddressBookProtos.Person.PhoneType.MOBILE)
				.build());

		addressBooBuilder.addPerson(personBuilder.build());

		personBuilder.setId(2).setName("hiro").setEmail("hiro@gmail.com");
		personBuilder.addPhone(phoneNumbBuilder.setNumber("18611154").setType(AddressBookProtos.Person.PhoneType.HOME)
				.build());

		personBuilder.addPhone(phoneNumbBuilder.setNumber("18611155").setType(AddressBookProtos.Person.PhoneType.WORK)
				.build());
		personBuilder.addPhone(phoneNumbBuilder.setNumber("18611156").setType(AddressBookProtos.Person.PhoneType.HOME)
				.build());
		personBuilder.addPhone(phoneNumbBuilder.setNumber("18611157")
				.setType(AddressBookProtos.Person.PhoneType.MOBILE).build());
		personBuilder.addPhone(phoneNumbBuilder.setNumber("18611158")
				.setType(AddressBookProtos.Person.PhoneType.MOBILE).build());
		personBuilder.setPhone(0, phoneNumbBuilder.setNumber("220").setType(AddressBookProtos.Person.PhoneType.MOBILE)
				.build());

		addressBooBuilder.addPerson(personBuilder.build());

		personBuilder.setId(3).setName("hello").setEmail("hello@163.com");
		personBuilder.addPhone(phoneNumbBuilder.setNumber("18611154").setType(AddressBookProtos.Person.PhoneType.HOME)
				.build());

		personBuilder.addPhone(phoneNumbBuilder.setNumber("18611155").setType(AddressBookProtos.Person.PhoneType.WORK)
				.build());
		personBuilder.addPhone(phoneNumbBuilder.setNumber("18611156").setType(AddressBookProtos.Person.PhoneType.HOME)
				.build());
		personBuilder.addPhone(phoneNumbBuilder.setNumber("18611157")
				.setType(AddressBookProtos.Person.PhoneType.MOBILE).build());
		personBuilder.addPhone(phoneNumbBuilder.setNumber("18611158")
				.setType(AddressBookProtos.Person.PhoneType.MOBILE).build());
		personBuilder.setPhone(0, phoneNumbBuilder.setNumber("330").setType(AddressBookProtos.Person.PhoneType.MOBILE)
				.build());

		addressBooBuilder.addPerson(personBuilder.build());

		byte[] byteArray = addressBooBuilder.build().toByteArray();

		String path = "e:\\test.txt";
		
//		try {
//			FileOutputStream outputStream = new FileOutputStream(path);
//			outputStream.write(byteArray);
//			outputStream.flush();
//			outputStream.close();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		try {
			FileInputStream inputStream = new FileInputStream(path);
			byte[] message = new byte[inputStream.available()];
			inputStream.read(message);
			
			FileDescriptor fileDescriptor = AddressBookProtos.getDescriptor();
			Descriptors.Descriptor addressbBookDescriptor = fileDescriptor.findMessageTypeByName("AddressBook");
			DynamicMessage addressBook = DynamicMessage.parseFrom(addressbBookDescriptor, message);
			System.out.println(addressBook.toString());
			inputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
