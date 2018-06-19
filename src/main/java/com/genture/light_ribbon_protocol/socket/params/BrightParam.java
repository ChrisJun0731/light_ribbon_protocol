package com.genture.light_ribbon_protocol.socket.params;

/**
 * Created by Administrator on 2018/6/14.
 */
public class BrightParam {

	byte mode;
	byte level;

	public byte getMode() {
		return mode;
	}

	public void setMode(byte mode) {
		this.mode = mode;
	}

	public byte getLevel() {
		return level;
	}

	public void setLevel(byte level) {
		this.level = level;
	}

	public byte[] getBytes(){
		return new byte[]{mode, level};
	}
}
