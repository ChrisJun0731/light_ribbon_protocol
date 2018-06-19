package com.genture.light_ribbon_protocol.api.params;

import com.genture.light_ribbon_protocol.api.params.enums.font.*;
import com.genture.light_ribbon_protocol.socket.frame.utils.FrameUtil;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * Created by Administrator on 2018/6/12.
 */
public class Text {

	private int id;
	private int content_len;
	private FontSize fontSize;
	private FontColor fontColor;
	private FontDisplay fontDisplay;
	private FontSpeed fontSpeed;
	private int stayTime;
	private FontAlign fontAlign;
	private String content;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getContent_len() {
		return content_len;
	}

	public void setContent_len(int content_len) {
		this.content_len = content_len;
	}

	public FontSize getFontSize() {
		return fontSize;
	}

	public void setFontSize(FontSize fontSize) {
		this.fontSize = fontSize;
	}

	public FontColor getFontColor() {
		return fontColor;
	}

	public void setFontColor(FontColor fontColor) {
		this.fontColor = fontColor;
	}

	public FontDisplay getFontDisplay() {
		return fontDisplay;
	}

	public void setFontDisplay(FontDisplay fontDisplay) {
		this.fontDisplay = fontDisplay;
	}

	public FontSpeed getFontSpeed() {
		return fontSpeed;
	}

	public void setFontSpeed(FontSpeed fontSpeed) {
		this.fontSpeed = fontSpeed;
	}

	public int getStayTime() {
		return stayTime;
	}

	public void setStayTime(int stayTime) {
		this.stayTime = stayTime;
	}

	public FontAlign getFontAlign() {
		return fontAlign;
	}

	public void setFontAlign(FontAlign fontAlign) {
		this.fontAlign = fontAlign;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
		try {
			setContent_len(content.getBytes("UTF-8").length);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public byte[] getBytes(){

		try {
			byte[] id = new byte[]{(byte)getId()};
			byte[] content_len = FrameUtil.convertByteAndInt(getContent_len());
			byte[] size = new byte[]{fontSize.getFontSize()};
			byte[] color = new byte[]{fontColor.getValue()};
			byte[] displayType = new byte[]{fontDisplay.getMode()};
			byte[] speed = new byte[]{fontSpeed.getSpeed()};
			byte[] stayTime = FrameUtil.convertByteAndInt(getStayTime());
			byte[] align = new byte[]{fontAlign.getAlign()};
			byte[] content = getContent().getBytes("UTF-8");

			int len = id.length + content_len.length + size.length + color.length + displayType.length + speed.length+
					stayTime.length + align.length + content.length;
			byte[] text_bytes = Arrays.copyOf(id, len);
			System.arraycopy(content_len, 0, text_bytes, id.length, content_len.length);
			System.arraycopy(size, 0, text_bytes, id.length + content_len.length, size.length);
			System.arraycopy(color, 0, text_bytes, id.length + content_len.length + size.length,
					color.length);
			System.arraycopy(displayType, 0, text_bytes, id.length + content_len.length + size.length +
					color.length, displayType.length);
			System.arraycopy(speed, 0, text_bytes, id.length + content_len.length + size.length +
					color.length + displayType.length, speed.length);
			System.arraycopy(stayTime, 0, text_bytes, id.length + content_len.length + size.length +
					color.length + displayType.length + speed.length, stayTime.length);
			System.arraycopy(align, 0, text_bytes, id.length + content_len.length + size.length +
					color.length + displayType.length + speed.length + stayTime.length, align.length);
			System.arraycopy(content, 0, text_bytes, id.length + content_len.length + size.length +
					color.length + displayType.length + speed.length + stayTime.length + align.length, content.length);

			return text_bytes;

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return null;
	}
}