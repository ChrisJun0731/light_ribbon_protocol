package com.genture.light_ribbon_protocol.api;

import com.genture.light_ribbon_protocol.socket.client.LightRibbonClient;
import com.genture.light_ribbon_protocol.socket.server.Server;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2018/6/20.
 */
public class LightRibbonTest {

	private Server server;
	private LightRibbon lightRibbon;

	@Before
	public void init(){
		server = new Server("192.168.40.66", 60001);
		lightRibbon = new LightRibbon();
	}

	@Test
	public void sendTextProgram() throws Exception {
	}

	@Test
	public void getTextProgram() throws Exception {
	}

	@Test
	public void sendLightProgram() throws Exception {
	}

	@Test
	public void getLightProgram() throws Exception {
	}

	@Test
	public void isOnline() throws Exception {
		boolean online = lightRibbon.isOnline(server);
		System.out.println(online);
	}

	@Test
	public void setBrightness() throws Exception {
	}

	@Test
	public void queryBrightness() throws Exception {
	}

}