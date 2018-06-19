package com.genture.light_ribbon_protocol.socket.client;

import com.genture.light_ribbon_protocol.socket.frame.Frame;
import com.genture.light_ribbon_protocol.socket.frame.utils.FrameUtil;
import com.genture.light_ribbon_protocol.socket.pool.ChannelPool;
import com.genture.light_ribbon_protocol.socket.server.Server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Created by Administrator on 2018/6/7.
 */
public class TCPClient {

	/**
	 * 发送帧
	 * @param server
	 * @param frame
	 */
	public static void sendFrame(Server server, Frame frame){
		SocketChannel channel = ChannelPool.getChannel(server.getHost(), "write");
		byte[] frame_bytes = FrameUtil.getBytes(frame);
		ByteBuffer buffer = ByteBuffer.allocate(frame_bytes.length);
		buffer.put(frame_bytes);
		try {
			buffer.flip();
			channel.write(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 接受帧
	 * @return
	 */
	public static byte[] receiveFrame(Server server){

		byte[] finalFrame = new byte[]{};

		SocketChannel socketChannel = ChannelPool.getChannel(server.getHost(), "read");
		ByteBuffer buffer = ByteBuffer.allocate(512);
		try {
			int bytesRead = socketChannel.read(buffer);
			while(bytesRead != -1){
				buffer.flip();
				int limit = buffer.limit();
				int size = finalFrame.length + limit;
				finalFrame = Arrays.copyOf(finalFrame, size);
				byte[] frame = new byte[limit];
				buffer.get(frame);
				System.arraycopy(frame,0, finalFrame, finalFrame.length, limit);
				buffer.clear();
				bytesRead = socketChannel.read(buffer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return finalFrame;
	}
}
