package com.genture.light_ribbon_protocol.api.params.enums.font;

/**
 * Created by Administrator on 2018/6/14.
 */
public enum FontAlign {

	LEFT((byte)0x01),MIDDLE((byte)0x02),RIGHT((byte)0x03);

	private byte align;

	FontAlign(byte align){
		this.align = align;
	}

	public byte getAlign() {
		return align;
	}

	public void setAlign(byte align) {
		this.align = align;
	}
}
