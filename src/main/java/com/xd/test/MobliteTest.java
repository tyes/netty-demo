package com.xd.test;

import com.google.protobuf.InvalidProtocolBufferException;
import com.xd.protobuf.Mobile;

public class MobliteTest {

	public static void main(String[] args) {
		Mobile.MobilePhone.Builder mobileBuilder = Mobile.MobilePhone.newBuilder();
		Mobile.Hardware.Builder hardwareBuilder = Mobile.Hardware.newBuilder();
		hardwareBuilder.setRam(12).setRom(22).setSize(120);
		mobileBuilder.setHardware(hardwareBuilder).setBrand("三星").addSoftware("壹").addSoftware("贰").addSoftware("叁");

		byte[] byteString = mobileBuilder.build().toByteArray();

		try {
			Mobile.MobilePhone mobilePhone = Mobile.MobilePhone.parseFrom(byteString);
			System.out.println(mobilePhone);
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
