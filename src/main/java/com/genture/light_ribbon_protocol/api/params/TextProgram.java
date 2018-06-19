package com.genture.light_ribbon_protocol.api.params;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/6/12.
 */
public class TextProgram {

	private List<Text> texts;

	public byte[] getBytes(){

		byte[] program_bytes = new byte[]{};

		for(int i=0; i<texts.size(); i++) {
			Text text = texts.get(i);
			int pos = program_bytes.length;
			program_bytes = Arrays.copyOf(program_bytes, program_bytes.length + text.getBytes().length);
			System.arraycopy(text.getBytes(), 0, program_bytes, pos, text.getBytes().length);
		}

		return program_bytes;
	}

	public List<Text> getTexts() {
		return texts;
	}

	public void setTexts(List<Text> texts) {
		this.texts = texts;
	}
}
