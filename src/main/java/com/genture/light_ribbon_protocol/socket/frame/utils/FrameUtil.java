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

		int header_len = Frame.header.length;
		int control_len = frame.getControl().length;
		int data_len = frame.getData_len().length;
		int dataLen = frame.getData()==null?0:frame.getData().length;
		int valid_len = frame.getValid().length;
		int tail_len = Frame.tail.length;
		int total_len = header_len + control_len + data_len + dataLen + valid_len + tail_len;

		byte[] frame_bytes = Arrays.copyOf(Frame.header, total_len);

		System.arraycopy(frame.getControl(), 0, frame_bytes, header_len, control_len);
		System.arraycopy(frame.getData_len(), 0, frame_bytes,header_len+control_len, data_len);
		if(frame.getData() != null){
			System.arraycopy(frame.getData(), 0, frame_bytes, header_len+control_len + data_len,
					dataLen);
		}
		System.arraycopy(frame.getValid(), 0, frame_bytes, header_len+control_len + data_len + dataLen,
				valid_len);
		System.arraycopy(Frame.tail, 0, frame_bytes, header_len+control_len + data_len + dataLen + valid_len,
				tail_len);

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

		int valid = 0;

		for(int i=0; i<Frame.header.length; i++){
			valid += Frame.header[i]<0?Frame.header[i]+256:Frame.header[i];
		}
		for(int i=0; i<frame.getControl().length; i++){
			valid += frame.getControl()[i]<0?frame.getControl()[i]+256:frame.getControl()[i];
		}
		for(int i=0; i<frame.getData_len().length; i++){
			valid += frame.getData_len()[i]<0?frame.getData_len()[i]+256:frame.getData_len()[i];
		}
		if(frame.getData()!=null){
			for(int i=0; i<frame.getData().length; i++){
				valid += frame.getData()[i]<0?frame.getData()[i]+256:frame.getData()[i];
			}
		}

		String hex = Integer.toHexString(valid);
		String val = hex.substring(hex.length()-2);

		int result = Integer.valueOf(val, 16);

		return new byte[]{(byte)result};
	}

	/**
	 * 解析帧
	 * @param frame_bytes
	 * @return
	 */
	public static Frame parseFrame(byte[] frame_bytes){
		Frame frame = new Frame();

		int header_len = frame.getHeader().length;
		int control_len = frame.getControl().length;
		int data_len = frame.getData_len().length;
		int dataLen;
		int valid_len = frame.getValid().length;
		int tail_len = frame.getTail().length;

		frame.setHeader(Arrays.copyOf(frame_bytes, header_len));
		frame.setControl(Arrays.copyOfRange(frame_bytes, header_len, header_len + control_len));
		frame.setData_len(Arrays.copyOfRange(frame_bytes, header_len + control_len,
				header_len + control_len + data_len));
		dataLen = FrameUtil.convertByteAndInt(frame.getData_len());
		frame.setData(Arrays.copyOfRange(frame_bytes, header_len + control_len + data_len,
				header_len + control_len + data_len + dataLen));
		frame.setValid(Arrays.copyOfRange(frame_bytes, header_len + control_len + data_len + dataLen,
				header_len + control_len + data_len + dataLen + valid_len));
		frame.setTail(Arrays.copyOfRange(frame_bytes, header_len + control_len + data_len + dataLen + valid_len,
				header_len + control_len + data_len + dataLen + valid_len + tail_len));

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

		return storage[0]<0?storage[0]+256:storage[0]*256 + storage[1]<0?storage[1]+256:storage[1];
	}
}
