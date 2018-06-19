package com.genture.light_ribbon_protocol.socket.frame.utils;

import com.genture.light_ribbon_protocol.api.params.Brightness;
import com.genture.light_ribbon_protocol.socket.frame.Frame;
import com.genture.light_ribbon_protocol.socket.params.BrightParam;

/**
 * Created by Administrator on 2018/6/14.
 */
public class BrightParamUtil {

	/**
	 * 解析亮度数据域，返回Brightness对象
	 * @param frame
	 * @return
	 */
	public static Brightness parseData(Frame frame){
		byte[] data = frame.getData();
		BrightParam param = new BrightParam();
		param.setMode(data[0]);
		param.setLevel(data[1]);
		return convertBright(param);
	}

	/**
	 * 亮度转换
	 * @param param
	 * @return
	 */
	private static Brightness convertBright(BrightParam param){
		Brightness brightness = new Brightness();
		brightness.setMode(param.getMode());
		brightness.setLevel(param.getLevel());

		return brightness;
	}
}
