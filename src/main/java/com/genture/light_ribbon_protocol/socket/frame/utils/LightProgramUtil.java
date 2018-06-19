package com.genture.light_ribbon_protocol.socket.frame.utils;

import com.genture.light_ribbon_protocol.api.params.Light;
import com.genture.light_ribbon_protocol.api.params.LightProgram;
import com.genture.light_ribbon_protocol.api.params.enums.color.LightColor;
import com.genture.light_ribbon_protocol.socket.frame.Frame;
import com.genture.light_ribbon_protocol.socket.params.LightBrand;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/13.
 */
public class LightProgramUtil {

	/**
	 * 解析光带数据域，返回LightProgram对象
	 * @param frame
	 * @return
	 */
	public static LightProgram parseData(Frame frame){

		LightProgram program = new LightProgram();
		byte[] data = frame.getData();
		int num = data[0];
		List<LightBrand> brands = new ArrayList<>();

		if(data.length != 1+2*num){
			System.out.println("返回数据域有误！");
		}
		for(int i=1; i<data.length; i=i+2){
			LightBrand brand = new LightBrand();
			brand.setId(data[i]);
			brand.setColor(data[i+1]);
			brands.add(brand);
		}

		program.setNum(num);
		program.setLights(convertLightBrand(brands));

		return program;

	}

	/**
	 * 转换光带对象
	 * @param brands
	 * @return
	 */
	private static List<Light> convertLightBrand(List<LightBrand> brands){

		List<Light> lights = new ArrayList<>();
		for(LightBrand brand: brands){
			Light light = new Light();
			light.setId(brand.getId());
			light.setLightColor(brand.getColor()==(byte)0x01?LightColor.RED:
					(brand.getColor()==(byte)0x02?LightColor.YELLOW:LightColor.GREEN));
			lights.add(light);
		}

		return lights;
	}
}
