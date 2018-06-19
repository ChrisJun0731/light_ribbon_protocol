package com.genture.light_ribbon_protocol.api.params.enums.font;

/**
 * Created by Administrator on 2018/6/14.
 */
public enum FontDisplay {

	STATIC((byte)0x00),SCROLL_LEFT((byte)0x09),SCROLL_RIGHR((byte)0x0a),
	SCROLL_UP((byte)0x0b),SCROLL_DOWN((byte)0x0c);

	private byte mode;

	FontDisplay(byte mode){
		this.mode = mode;
	}

	public byte getMode() {
		return mode;
	}

	public void setMode(byte mode) {
		this.mode = mode;
	}
}
