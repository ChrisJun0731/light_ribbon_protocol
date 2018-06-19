package com.genture.light_ribbon_protocol.api.params.enums.font;

/**
 * Created by Administrator on 2018/6/12.
 */
public enum FontSize {

	Font16((byte)0x01),Font24((byte)0x02),Font32((byte)0x03),Font48((byte)0x04);

	private byte fontSize;

	FontSize(byte fontSize){
		this.fontSize = fontSize;
	}

	public byte getFontSize() {
		return fontSize;
	}

	public void setFontSize(byte fontSize) {
		this.fontSize = fontSize;
	}
}
