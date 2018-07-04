package com.genture.light_ribbon_protocol.api;

import com.genture.light_ribbon_protocol.api.params.*;
import com.genture.light_ribbon_protocol.api.params.enums.color.LightColor;
import com.genture.light_ribbon_protocol.api.params.enums.font.FontSpeed;
import com.genture.light_ribbon_protocol.socket.client.LightRibbonClient;
import com.genture.light_ribbon_protocol.socket.server.Server;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
		TextProgram program = new TextProgram();
		List<Text> texts = new ArrayList<>();
		Text text = new Text();
		text.setId(1);
		text.setContent("111");
		texts.add(text);
		program.setTexts(texts);
		lightRibbon.sendTextProgram(program, server);
	}

	@Test
	public void getTextProgram() throws Exception {
		TextProgram program = lightRibbon.getTextProgram(server);
		List<Text> texts = program.getTexts();
		for(Text text: texts){
			System.out.println(text.getId());
			System.out.println(text.getContent());
		}

	}

	@Test
	public void sendLightProgram() throws Exception {

		LightProgram program = new LightProgram();
		List<Light> lights = new ArrayList<>();
		Light light = new Light();
		light.setId(1);
		light.setLightColor(LightColor.GREEN);
		lights.add(light);
		program.setLights(lights);
		lightRibbon.sendLightProgram(program, server);
	}

	@Test
	public void getLightProgram() throws Exception {
		LightProgram program = lightRibbon.getLightProgram(server);
		List<Light> lights = program.getLights();
		for(Light light: lights){
			System.out.println(light.getId());
			System.out.println(light.getLightColor());
		}
	}

	@Test
	public void isOnline() throws Exception {
		boolean online = lightRibbon.isOnline(server);
		System.out.println(online);
	}

	@Test
	public void setBrightness() throws Exception {
		Brightness brightness = new Brightness();
		brightness.setMode(1);
		brightness.setLevel(10);
		lightRibbon.setBrightness(brightness, server);
	}

	@Test
	public void queryBrightness() throws Exception {
		Brightness brightness = lightRibbon.queryBrightness(server);
		if(brightness == null){
			System.out.println("object is null.");
		}
		System.out.println(brightness.getLevel());
		System.out.println(brightness.getMode());
	}

}