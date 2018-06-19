package com.genture.light_ribbon_protocol.socket.frame.utils;

import com.genture.light_ribbon_protocol.socket.frame.Frame;

import java.util.Arrays;

/**
 * Created by Administrator on 2018/6/11.
 */
public class FrameUtil {

	/**
	 * 组装帧
	 * @param frame
	 * @return
	 */
	public static byte[] getBytes(Frame frame){

		int len = Frame.header.length + frame.getControl().length + frame.getData_len().length
				+ (frame.getData()==null?0:frame.getData().length) + frame.getValid().length + Frame.tail.length;
		byte[] frame_bytes = Arrays.copyOf(Frame.header, len);

		System.arraycopy(frame.getControl(), 0, frame_bytes, Frame.header.length, frame.getControl().length);
		System.arraycopy(frame.getData_len(), 0, frame_bytes,Frame.header.length+
				frame.getControl().length, frame.getData_len().length);
		if(frame.getData() != null){
			System.arraycopy(frame.getData(), 0, frame_bytes, Frame.header.length +
					frame.getControl().length + frame.getData_len().length, frame.getData().length);
		}
		System.arraycopy(frame.getValid(), 0, frame_bytes, Frame.header.length +
				frame.getControl().length + frame.getData_len().length + (frame.getData()==null?0:frame.getData().length),
				frame.getValid().length);
		System.arraycopy(Frame.tail, 0, frame_bytes, Frame.header.length +
				frame.getControl().length + frame.getData_len().length +(frame.getData()==null?0:frame.getData_len().length) +
				frame.getValid().length, Frame.tail.length);

		return frame_bytes;
	}

	/**
	 * 校验帧是否有效
	 * @param frame
	 * @return
	 */
	public static boolean isValid(Frame frame){

		byte[] valid = computeValid(frame);

		if(valid[0] == frame.getValid()[0]){
			return true;
		}

		return false;
	}

	public static byte[] computeValid(Frame frame){

		byte valid = 0;

		for(int i=0; i<Frame.header.length; i++){
			valid += Frame.header[i];
		}
		for(int i=0; i<frame.getControl().length; i++){
			valid += frame.getControl()[i];
		}
		for(int i=0; i<frame.getData_len().length; i++){
			valid += frame.getData_len()[i];
		}
		if(frame.getData()!=null){
			for(int i=0; i<frame.getData().length; i++){
				valid += frame.getData()[i];
			}
		}

		String val = String.valueOf(valid);
		val = val.substring(val.length() - 2);
		valid = Byte.parseByte(val);

		return new byte[]{valid};
	}

	/**
	 * 解析帧
	 * @param frame_bytes
	 * @return
	 */
	public static Frame parseFrame(byte[] frame_bytes){

		Frame frame = new Frame();

		frame.setHeader(Arrays.copyOf(frame_bytes, frame.getHeader().length));
		frame.setControl(Arrays.copyOfRange(frame_bytes, frame.getHeader().length, frame.getHeader().length +
				frame.getControl().length));
		frame.setData_len(Arrays.copyOfRange(frame_bytes, frame.getHeader().length + frame.getControl().length,
				frame.getHeader().length + frame.getControl().length + frame.getData_len().length));
		frame.setData(Arrays.copyOfRange(frame_bytes, frame.getHeader().length + frame.getControl().length +
				frame.getData_len().length, frame.getHeader().length + frame.getControl().length +
				frame.getData_len().length + (frame.getData()==null?0:frame.getData().length)));
		frame.setValid(Arrays.copyOfRange(frame_bytes, frame.getHeader().length + frame.getControl().length +
				frame.getData_len().length + frame.getData().length, frame.getHeader().length +
				frame.getControl().length + frame.getData_len().length + (frame.getData()==null?0:frame.getData().length) +
				frame.getValid().length));
		frame.setTail(Arrays.copyOfRange(frame_bytes, frame.getHeader().length + frame.getControl().length +
				frame.getData_len().length + (frame.getData()==null?0:frame.getData().length) + frame.getValid().length,
				frame.getHeader().length + frame.getControl().length + frame.getData_len().length +
						frame.getData().length + frame.getValid().length + frame.getTail().length));

		return frame;
	}

	/**
	 * 高低位存储装换
	 * @param num
	 * @return
	 */
	public static byte[] convertByteAndInt(int num){

		byte high = (byte)(num/256);
		byte low = (byte)(num%256);
		byte[] storage = new byte[]{high, low};

		return storage;
	}

	/**
	 * 高低位存储转换
	 * @param storage
	 * @return
	 */
	public static int convertByteAndInt(byte[] storage){

		return storage[0]*256 + storage[1];
	}
}
