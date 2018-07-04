package com.genture.light_ribbon_protocol.socket.client;

import com.genture.light_ribbon_protocol.socket.frame.Frame;
import com.genture.light_ribbon_protocol.socket.frame.utils.FrameUtil;
import com.genture.light_ribbon_protocol.socket.pool.ChannelPool;
import com.genture.light_ribbon_protocol.socket.pool.RegisterCenter;
import com.genture.light_ribbon_protocol.socket.server.Server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
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
		SocketChannel channel = ChannelPool.getChannel(server, "write");
		byte[] frame_bytes = FrameUtil.getBytes(frame);
		ByteBuffer buffer = ByteBuffer.allocate(frame_bytes.length);
		buffer.put(frame_bytes);
		try {
			buffer.flip();
			channel.write(buffer);

			//通道发完消息，向通道注册读事件
			channel.register(RegisterCenter.getSelector(), SelectionKey.OP_READ, server.getHost());
			ChannelPool.clearDoneEvents();
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

		SocketChannel socketChannel = ChannelPool.getChannel(server, "read");
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		try {
			while(socketChannel.read(buffer) != 0){
				buffer.flip();
				int limit = buffer.limit();
				int size = finalFrame.length;
				finalFrame = Arrays.copyOf(finalFrame, size+limit);
				byte[] frame = new byte[limit];
				buffer.get(frame);
				System.arraycopy(frame,0, finalFrame, size, limit);
				buffer.clear();
			}
			//通道读完消息，向通道注册读事件
			socketChannel.register(RegisterCenter.getSelector(), SelectionKey.OP_WRITE, server.getHost());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return finalFrame;
	}
}
