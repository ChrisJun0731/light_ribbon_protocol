package com.genture.light_ribbon_protocol.socket.frame;

import com.genture.light_ribbon_protocol.socket.frame.utils.FrameUtil;

/**
 * Created by Administrator on 2018/6/11.
 */
public class Frame {

	public static byte[] header = new byte[]{(byte)0xA0, (byte)0xB0, (byte)0xC0, (byte)0xD0};
	public static byte[] tail = new byte[]{(byte)0xE0};
	private byte[] control = new byte[1];
	private byte[] valid = new byte[1];
	private byte[] data_len = new byte[2];
	private byte[] data;

	public static byte[] getHeader() {
		return header;
	}

	public static void setHeader(byte[] header) {
		Frame.header = header;
	}

	public static byte[] getTail() {
		return tail;
	}

	public static void setTail(byte[] tail) {
		Frame.tail = tail;
	}

	public byte[] getValid() {
		return valid;
	}

	public void setValid(byte[] valid) {
		this.valid = valid;
	}

	public byte[] getControl() {
		return control;
	}

	public void setControl(byte[] control) {
		this.control = control;
	}

	public byte[] getData_len() {
		return data_len;
	}

	public void setData_len(byte[] data_len) {
		this.data_len = data_len;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		if(data == null){
			setData_len(new byte[]{(byte)0x00, (byte)0x00});
		}else{
			setData_len(FrameUtil.convertByteAndInt(data.length));
		}
		this.data = data;
	}
}
