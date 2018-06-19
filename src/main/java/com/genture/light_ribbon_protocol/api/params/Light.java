package com.genture.light_ribbon_protocol.api.params;

import com.genture.light_ribbon_protocol.api.params.enums.color.LightColor;

/**
 * Created by Administrator on 2018/6/12.
 */
public class Light {

	private int id;
	private LightColor lightColor;

	public byte[] getBytes(){
		return new byte[]{(byte) id, lightColor.getColor()};
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LightColor getLightColor() {
		return lightColor;
	}

	public void setLightColor(LightColor lightColor) {
		this.lightColor = lightColor;
	}
}

