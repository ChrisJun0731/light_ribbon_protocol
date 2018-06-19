package com.genture.light_ribbon_protocol.api;

import com.genture.light_ribbon_protocol.api.params.LightProgram;
import com.genture.light_ribbon_protocol.api.params.TextProgram;
import com.genture.light_ribbon_protocol.api.params.Brightness;
import com.genture.light_ribbon_protocol.socket.client.LightRibbonClient;
import com.genture.light_ribbon_protocol.socket.server.Server;

/**
 * Created by Administrator on 2018/6/12.
 */
public class LightRibbon {

	/**
	 * 发送文字节目
	 * @param textProgram
	 * @param server
	 */
	public void sendTextProgram(TextProgram textProgram, Server server){

		LightRibbonClient.sendTextProgram(textProgram, server);
	}

	/**
	 * 获取文字节目
	 * @param server
	 * @return
	 */
	public TextProgram getTextProgram(Server server){

		return LightRibbonClient.queryTextProgram(server);
	}

	/**
	 * 光带颜色设置
	 * @param lightProgram
	 * @param server
	 */
	public void sendLightProgram(LightProgram lightProgram, Server server){

		LightRibbonClient.sendLightProgram(lightProgram, server);
	}

	/**
	 * 光带颜色获取
	 * @param server
	 * @return
	 */
	public LightProgram getLightProgram(Server server){

		return LightRibbonClient.queryLightProgram(server);
	}

	/**
	 * 查询设备是否在线
	 * @param server
	 * @return
	 */
	public boolean isOnline(Server server){
		return LightRibbonClient.isOnline(server);
	}

	/**
	 * 设置光带亮度参数
	 * @param brightness
	 * @param server
	 */
	public void setBrightness(Brightness brightness, Server server){
		LightRibbonClient.setBrightness(brightness, server);
	}

	/**
	 * 查询光带亮度参数
	 * @param server
	 * @return
	 */
	public Brightness queryBrightness(Server server){

		return LightRibbonClient.queryBrightness(server);
	}
}
