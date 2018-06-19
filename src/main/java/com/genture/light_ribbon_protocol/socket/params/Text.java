package com.genture.light_ribbon_protocol.socket.params;

/**
 * Created by Administrator on 2018/6/11.
 */
public class Text {

	private byte id;
	private byte[] length;
	private byte fontSize;
	private byte fontColor;
	private byte displayType;
	private byte speed;
	private byte[] stayTime;
	private byte align;
	private byte[] content;

	public byte getAlign() {
		return align;
	}

	public void setAlign(byte align) {
		this.align = align;
	}

	public byte getId() {
		return id;
	}

	public void setId(byte id) {
		this.id = id;
	}

	public byte getFontSize() {
		return fontSize;
	}

	public void setFontSize(byte fontSize) {
		this.fontSize = fontSize;
	}

	public byte getFontColor() {
		return fontColor;
	}

	public void setFontColor(byte fontColor) {
		this.fontColor = fontColor;
	}

	public byte getDisplayType() {
		return displayType;
	}

	public void setDisplayType(byte displayType) {
		this.displayType = displayType;
	}

	public byte getSpeed() {
		return speed;
	}

	public void setSpeed(byte speed) {
		this.speed = speed;
	}

	public byte[] getLength() {
		return length;
	}

	public void setLength(byte[] length) {
		this.length = length;
	}

	public byte[] getStayTime() {
		return stayTime;
	}

	public void setStayTime(byte[] stayTime) {
		this.stayTime = stayTime;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
}
