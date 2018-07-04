package com.genture.light_ribbon_protocol.socket.client;

import com.genture.light_ribbon_protocol.api.params.LightProgram;
import com.genture.light_ribbon_protocol.api.params.TextProgram;
import com.genture.light_ribbon_protocol.api.params.Brightness;
import com.genture.light_ribbon_protocol.socket.frame.Frame;
import com.genture.light_ribbon_protocol.socket.frame.utils.BrightParamUtil;
import com.genture.light_ribbon_protocol.socket.frame.utils.FrameUtil;
import com.genture.light_ribbon_protocol.socket.frame.utils.LightProgramUtil;
import com.genture.light_ribbon_protocol.socket.frame.utils.TextProgramUtil;
import com.genture.light_ribbon_protocol.socket.params.BrightParam;
import com.genture.light_ribbon_protocol.socket.server.Server;

/**
 * Created by Administrator on 2018/6/12.
 */
public class LightRibbonClient {

	/**
	 * 发送文本节目
	 * @param textProgram
	 * @param server
	 */
	public static void sendTextProgram(TextProgram textProgram, Server server){
		Frame sendFrame = new Frame();

		sendFrame.setControl(new byte[]{(byte)0x44});
		sendFrame.setData(textProgram.getBytes());
		sendFrame.setValid(FrameUtil.computeValid(sendFrame));

		TCPClient.sendFrame(server, sendFrame);

		Frame recFrame = FrameUtil.parseFrame(TCPClient.receiveFrame(server));
		if(recFrame.getControl()[0] == (byte)0x44){
			if(recFrame.getData()[0] == (byte)0x55){
				System.out.println("文字节目发送成功！");
			}
		}else{
			System.out.println("接收帧错误！");
		}
	}

	/**
	 * 接受文本节目
	 * @param server
	 * @return
	 */
	public static TextProgram queryTextProgram(Server server){

		Frame sendFrame = new Frame();
		sendFrame.setControl(new byte[]{(byte)0x48});
		sendFrame.setData(null);
		sendFrame.setValid(FrameUtil.computeValid(sendFrame));
		TCPClient.sendFrame(server, sendFrame);

		Frame recFrame = FrameUtil.parseFrame(TCPClient.receiveFrame(server));
		if(recFrame.getControl()[0]==(byte)0x48){
			if(recFrame.getValid()[0] == FrameUtil.computeValid(recFrame)[0]){

				TextProgram program = TextProgramUtil.parseData(recFrame);
				return program;
			}else{
				System.out.println("帧校验码错误！");
			}
		}else{
			System.out.println("接收帧错误！");
		}

		return null;
	}

	/**
	 * 设置光带颜色
	 * @param lightProgram
	 * @param server
	 */
	public static void sendLightProgram(LightProgram lightProgram, Server server){

		Frame sendFrame = new Frame();

		sendFrame.setControl(new byte[]{(byte)0x59});
		sendFrame.setData(lightProgram.getBytes());
		sendFrame.setValid(FrameUtil.computeValid(sendFrame));

		TCPClient.sendFrame(server, sendFrame);

		Frame recFrame = FrameUtil.parseFrame(TCPClient.receiveFrame(server));
		if(recFrame.getControl()[0] == (byte)0x59){
			if(recFrame.getData()[0] == (byte)0x55){
				System.out.println("光带颜色设置成功！");
			}else{
				System.out.println("光带颜色设置失败！");
			}
		}else{
			System.out.println("接收帧错误！");
		}

	}

	/**
	 * 获取光带颜色
	 * @param server
	 * @return
	 */
	public static LightProgram queryLightProgram(Server server){

		Frame sendFrame = new Frame();
		sendFrame.setControl(new byte[]{(byte)0x79});
		sendFrame.setData(null);
		sendFrame.setValid(FrameUtil.computeValid(sendFrame));
		TCPClient.sendFrame(server, sendFrame);

		Frame recFrame = FrameUtil.parseFrame(TCPClient.receiveFrame(server));
		if(recFrame.getControl()[0]==(byte)0x79){
			if (recFrame.getValid()[0] == FrameUtil.computeValid(recFrame)[0]) {
				LightProgram program = LightProgramUtil.parseData(recFrame);
				return program;
			}
		}else{
			System.out.println("接收帧错误！");
		}

		return null;
	}

	/**
	 * 查询设备是否在线
	 * @param server
	 * @return
	 */
	public static boolean isOnline(Server server){

		Frame sendFrame = new Frame();
		sendFrame.setControl(new byte[]{(byte) 0x60});
		sendFrame.setData(null);
		sendFrame.setValid(FrameUtil.computeValid(sendFrame));
		TCPClient.sendFrame(server, sendFrame);
		Frame recFrame = FrameUtil.parseFrame(TCPClient.receiveFrame(server));
		if(recFrame.getControl()[0] == (byte)0x60){
			if (recFrame.getValid()[0] == FrameUtil.computeValid(recFrame)[0]) {
				return true;
			} else{
				System.out.println("校验码错误！");
			}
		}

		return false;
	}

	/**
	 * 设置光带亮度
	 * @param brightness
	 * @param server
	 */
	public static void setBrightness(Brightness brightness, Server server){
		BrightParam param = new BrightParam();
		param.setMode((byte)brightness.getMode());
		param.setLevel((byte) brightness.getLevel());

		Frame sendFrame = new Frame();
		sendFrame.setControl(new byte[]{(byte) 0x4c});
		sendFrame.setData(param.getBytes());
		sendFrame.setValid(FrameUtil.computeValid(sendFrame));
		TCPClient.sendFrame(server, sendFrame);

		Frame recFrame = FrameUtil.parseFrame(TCPClient.receiveFrame(server));
		if(recFrame.getControl()[0] == (byte)0x4c){
			if (recFrame.getValid()[0] == FrameUtil.computeValid(recFrame)[0]) {
				System.out.println("亮度设置成功！");
			}else{
				System.out.println("校验码错误！");
			}
		}else{
			System.out.println("接收帧错误！");
		}
	}

	/**
	 * 查询亮度参数
	 * @param server
	 * @return
	 */
	public static Brightness queryBrightness(Server server){

		Frame sendFrame = new Frame();
		sendFrame.setControl(new byte[]{(byte)0x6c});
		sendFrame.setData(null);
		sendFrame.setValid(FrameUtil.computeValid(sendFrame));
		TCPClient.sendFrame(server, sendFrame);

		Frame recFrame = FrameUtil.parseFrame(TCPClient.receiveFrame(server));
		if(recFrame.getControl()[0] == (byte)0x6c){
			if (recFrame.getValid()[0] == FrameUtil.computeValid(recFrame)[0]) {
				return BrightParamUtil.parseData(recFrame);
			}else{
				System.out.println("校验码错误！");
			}
		}else{
			System.out.println("接受帧错误！");
		}

		return null;
	}
}
