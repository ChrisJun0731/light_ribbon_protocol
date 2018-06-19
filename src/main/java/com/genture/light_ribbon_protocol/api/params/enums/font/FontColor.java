package com.genture.light_ribbon_protocol.api.params.enums.font;

/**
 * Created by Administrator on 2018/6/14.
 */
public enum FontColor {

	RED((byte)0x01),GREEN((byte)0x02),YELLOW((byte)0x03),
	BLUE((byte)0x04),PINK((byte)0x05),CYAN((byte)0x06),
	WHITE((byte)0x07);

	private byte color;

	FontColor(byte color){
		this.color = color;
	}

	public byte getValue() {
		return color;
	}

	public void setValue(byte color) {
		this.color = color;
	}
}
