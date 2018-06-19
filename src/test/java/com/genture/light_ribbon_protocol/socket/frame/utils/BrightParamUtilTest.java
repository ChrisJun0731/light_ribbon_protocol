package com.genture.light_ribbon_protocol.socket.frame.utils;

import com.genture.light_ribbon_protocol.api.params.Brightness;
import com.genture.light_ribbon_protocol.socket.frame.Frame;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2018/6/19.
 */
public class BrightParamUtilTest {

	private Frame frame;

	@Before
	public void init(){
		frame = new Frame();
		frame.setControl(new byte[]{(byte)0x4c});
		frame.setData(new byte[]{(byte)01, (byte)31});
		frame.setValid(FrameUtil.computeValid(frame));
	}

	@Test
	public void parseData() throws Exception {
		Brightness brightness = BrightParamUtil.parseData(frame);
	}

}