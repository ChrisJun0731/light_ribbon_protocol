package com.genture.light_ribbon_protocol.api.params.enums.font;

/**
 * Created by Administrator on 2018/6/14.
 */
public enum FontSpeed {

	SPEED1((byte)0x00),SPEED2((byte)0x01),SPEED3((byte)0x02),
	SPEED4((byte)0x03),SPEED5((byte)0x04),SPEED6((byte)0x05),
	SPEED7((byte)0x06),SPEED8((byte)0x07),SPEED9((byte)0x08),
	SPEED10((byte)0x09);

	private byte speed;

	FontSpeed(byte speed){
		this.speed = speed;
	}

	public byte getSpeed() {
		return speed;
	}

	public void setSpeed(byte speed) {
		this.speed = speed;
	}
}
