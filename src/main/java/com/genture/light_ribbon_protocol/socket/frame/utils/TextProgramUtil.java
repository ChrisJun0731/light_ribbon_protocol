package com.genture.light_ribbon_protocol.socket.frame.utils;

import com.genture.light_ribbon_protocol.api.params.TextProgram;
import com.genture.light_ribbon_protocol.api.params.enums.font.*;
import com.genture.light_ribbon_protocol.socket.frame.Frame;
import com.genture.light_ribbon_protocol.socket.params.Text;
import com.genture.light_ribbon_protocol.socket.params.interfaces.TextSetting;

import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/6/12.
 */
public class TextProgramUtil {

	/**
	 * 解析数据域，获取TextProgram实例
	 * @param frame
	 * @return
	 */
	public static TextProgram parseData(Frame frame){

		TextProgram textProgram = new TextProgram();
		byte[] data = frame.getData();
		List<Text> texts = new ArrayList<>();

		int pos = 0;
		while(pos < data.length){
			Text text = new Text();
			text.setId(data[pos]);
			text.setLength(new byte[]{data[pos+1], data[pos+2]});
			int len = FrameUtil.convertByteAndInt(text.getLength());
			text.setFontSize(data[pos + 3]);
			text.setFontColor(data[pos + 4]);
			text.setDisplayType(data[pos + 5]);
			text.setSpeed(data[pos + 6]);
			text.setStayTime(new byte[]{data[pos + 7], data[pos + 8]});
			text.setAlign(data[9]);
			text.setContent(Arrays.copyOfRange(data, pos+10, pos+10+len));
			texts.add(text);

			pos = pos + 10 + len;
		}
		List<com.genture.light_ribbon_protocol.api.params.Text> real_texts = convertText(texts);
		textProgram.setTexts(real_texts);

		return textProgram;
	}

	/**
	 * 将基于字节属性的Text对象，转化为用户需要的Text对象
	 * @param texts
	 * @return
	 */
	private static List<com.genture.light_ribbon_protocol.api.params.Text> convertText(List<Text> texts){

		List<com.genture.light_ribbon_protocol.api.params.Text> real_texts = new ArrayList<>();

		for (Text text : texts) {
			com.genture.light_ribbon_protocol.api.params.Text real_text =
					new com.genture.light_ribbon_protocol.api.params.Text();
			real_text.setId(text.getId());
			real_text.setFontSize(setFontSize(text.getFontSize(), (fontSize)->{
				switch (fontSize) {
					case (byte)0x01:
						return FontSize.Font16;
					case (byte)0x02:
						return FontSize.Font24;
					case (byte)0x03:
						return FontSize.Font32;
					case (byte)0x04:
						return FontSize.Font48;
				}
				return FontSize.Font16;
			}));
			real_text.setFontColor(setTextColor(text.getFontColor(), ((color)-> {
				switch (color) {
					case (byte) 0x01:
						return FontColor.RED;
					case (byte) 0x02:
						return FontColor.GREEN;
					case (byte) 0x03:
						return FontColor.YELLOW;
					case (byte) 0x04:
						return FontColor.BLUE;
					case (byte) 0x05:
						return FontColor.PINK;
					case (byte) 0x06:
						return FontColor.CYAN;
					case (byte) 0x07:
						return FontColor.WHITE;
				}
				return FontColor.GREEN;
			})));
			real_text.setFontDisplay(setTextDisplay(text.getDisplayType(), (display)->{
				switch(display){
					case (byte)0x00:
						return FontDisplay.STATIC;
					case (byte)0x09:
						return FontDisplay.SCROLL_LEFT;
					case (byte)0x0a:
						return FontDisplay.SCROLL_RIGHR;
					case (byte)0x0b:
						return FontDisplay.SCROLL_UP;
					case (byte)0x0c:
						return FontDisplay.SCROLL_DOWN;
				}
				return FontDisplay.STATIC;
			}));
			real_text.setFontSpeed(setFontSpeed(text.getSpeed(), (speed)->{
				switch(speed){
					case (byte)0x00:
						return FontSpeed.SPEED1;
					case (byte)0x01:
						return FontSpeed.SPEED2;
					case (byte)0x02:
						return FontSpeed.SPEED3;
					case (byte)0x03:
						return FontSpeed.SPEED4;
					case (byte)0x04:
						return FontSpeed.SPEED5;
					case (byte)0x05:
						return FontSpeed.SPEED6;
					case (byte)0x06:
						return FontSpeed.SPEED7;
					case (byte)0x07:
						return FontSpeed.SPEED8;
					case (byte)0x08:
						return FontSpeed.SPEED9;
					case (byte)0x09:
						return FontSpeed.SPEED10;
				}
				return FontSpeed.SPEED1;
			}));
			real_text.setStayTime(FrameUtil.convertByteAndInt(text.getStayTime()));
			real_text.setFontAlign(setTextAlign(text.getAlign(), (align)->{
				switch(align){
					case (byte)0x01:
						return FontAlign.LEFT;
					case (byte)0x02:
						return FontAlign.MIDDLE;
					case (byte)0x03:
						return FontAlign.RIGHT;
				}
				return FontAlign.LEFT;
			}));
			try {
				real_text.setContent(new String(text.getContent(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			real_texts.add(real_text);
		}

		return real_texts;
	}

	private static FontColor setTextColor(byte color, TextSetting<FontColor> setting){
		return setting.set(color);
	}

	private static FontDisplay setTextDisplay(byte display, TextSetting<FontDisplay> setting){
		return setting.set(display);
	}

	private static FontAlign setTextAlign(byte align, TextSetting<FontAlign> setting){
		return setting.set(align);
	}

	private static FontSize setFontSize(byte fontSize, TextSetting<FontSize> setting){
		return setting.set(fontSize);
	}

	private static FontSpeed setFontSpeed(byte fontSpeed, TextSetting<FontSpeed> setting){
		return setting.set(fontSpeed);
	}
}
