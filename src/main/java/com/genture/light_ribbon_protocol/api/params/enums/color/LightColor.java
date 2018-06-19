package com.genture.light_ribbon_protocol.api.params.enums.color;

/**
 * Created by Administrator on 2018/6/14.
 */

public enum LightColor {

	RED((byte)0x01),YELLOW((byte)0x02),GREEN((byte)0x03);

	private byte color;

	LightColor(byte color){
		this.color = color;
	}

	public byte getColor() {
		return color;
	}

	public void setColor(byte color) {
		this.color = color;
	}
}

