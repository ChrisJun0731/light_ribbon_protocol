package com.genture.light_ribbon_protocol.api.params;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/6/12.
 */
public class LightProgram {

	private int num;
	private List<Light> lights;

	public byte[] getBytes(){

		byte[] program_bytes = Arrays.copyOf(new byte[]{(byte)num}, 1);

		for(int i=0; i<lights.size(); i++) {
			int pos = program_bytes.length;
			program_bytes = Arrays.copyOf(program_bytes, program_bytes.length + lights.get(i).getBytes().length);
			System.arraycopy(lights.get(i), 0, program_bytes, pos, lights.get(i).getBytes().length);
		}

		return program_bytes;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public List<Light> getLights() {
		return lights;
	}

	public void setLights(List<Light> lights) {
		this.lights = lights;
	}
}
