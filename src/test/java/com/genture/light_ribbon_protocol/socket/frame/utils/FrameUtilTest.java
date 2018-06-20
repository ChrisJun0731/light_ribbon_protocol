package com.genture.light_ribbon_protocol.socket.frame.utils;

import com.genture.light_ribbon_protocol.socket.frame.Frame;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2018/6/19.
 */
public class FrameUtilTest {

	private Frame frame;

	@Before
	public void init(){
		frame = new Frame();
		frame.setControl(new byte[]{(byte) 0x43});
		frame.setData(new byte[]{(byte)0x00, (byte)0x55});
		frame.setValid(FrameUtil.computeValid(frame));
	}

	@Test
	public void getBytes() throws Exception {
		byte[] frame_bytes = FrameUtil.getBytes(frame);
	}

	@Test
	public void isValid() throws Exception {
		boolean isValid = FrameUtil.isValid(frame);
		System.out.println(isValid);
	}

	@Test
	public void computeValid() throws Exception {
		byte valid = FrameUtil.computeValid(frame)[0];
		System.out.println(valid);
	}


	@Test
	public void parseFrame() throws Exception {
		Frame frame1 = FrameUtil.parseFrame(FrameUtil.getBytes(frame));
	}

	@Test
	public void convertByteAndInt() throws Exception {
		byte[] b = FrameUtil.convertByteAndInt(268);
		System.out.println(b[0] + " " + b[1]);
	}

	@Test
	public void convertByteAndInt1() throws Exception {
		int num = FrameUtil.convertByteAndInt(new byte[]{(byte) 0x01, (byte) 0x03});
		System.out.println(num);
	}

}