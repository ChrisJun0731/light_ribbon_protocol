package com.genture.light_ribbon_protocol.socket.pool;

import com.genture.light_ribbon_protocol.socket.server.Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Administrator on 2018/6/11.
 */
public class ChannelPool {

	private static final int initialPoolSize = 1;
	private static Set<SelectionKey> selectionKeys = null;

	public static SocketChannel getChannel(Server server, String option){

		boolean isRegistered = RegisterCenter.isRegistered(server.getHost());
		if(!isRegistered){
			RegisterCenter.register(server);
		}
		try {
			while(RegisterCenter.getSelector().select()!=0){

				selectionKeys = RegisterCenter.getSelector().selectedKeys();
				SocketChannel socketChannel = null;
				for(SelectionKey key: selectionKeys){
					if(key.attachment().equals(server.getHost()) && key.isWritable() && option.equals("write")){
						socketChannel = (SocketChannel)key.channel();
						return socketChannel;
					}
					if (key.attachment().equals(server.getHost()) && key.isReadable() && option.equals("read")) {
						socketChannel = (SocketChannel)key.channel();
						return socketChannel;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 初始化某个host下的连接池
	 * @param server
	 */
	public static Set<SocketChannel> initiateHostPool(Server server){

		Set<SocketChannel> channelSet = new HashSet<>();

		for(int i=0; i<initialPoolSize; i++){
			try {
				SocketChannel channel = SocketChannel.open(new InetSocketAddress(server.getHost(), server.getPort()));
				channel.configureBlocking(false);
				channelSet.add(channel);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return channelSet;
	}

	/**
	 * 清除已处理事件
	 */
	public static void clearDoneEvents(){
		selectionKeys.clear();
	}
}
