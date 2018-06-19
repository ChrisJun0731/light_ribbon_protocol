package com.genture.light_ribbon_protocol.socket.frame.utils;

import com.genture.light_ribbon_protocol.api.params.LightProgram;
import com.genture.light_ribbon_protocol.socket.frame.Frame;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2018/6/19.
 */
public class LightProgramUtilTest {

	private Frame frame;

	@Before
	public void init(){
		frame = new Frame();
		frame.setControl(new byte[]{(byte) 0x59});
		frame.setData(new byte[]{02, 01, 01, 02, 02});
		frame.setValid(FrameUtil.computeValid(frame));
	}

	@Test
	public void parseData() throws Exception {
		LightProgram program = LightProgramUtil.parseData(frame);


	}

}