package com.genture.light_ribbon_protocol.socket.pool;

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
	private static Set<SocketChannel> channles;
	private static final int initialPoolSize = 5;
	private static int port = 5000;

	public static SocketChannel getChannel(String host, String option){
		boolean isRegistered = RegisterCenter.isRegistered(host);
		SocketChannel socketChannel = null;
		if(!isRegistered){
			RegisterCenter.register(host);
		}
		try {
			while(RegisterCenter.getSelector().select()!=0){
				Set<SelectionKey> keys = RegisterCenter.getSelector().selectedKeys();
				Iterator<SelectionKey> it = keys.iterator();
				while(it.hasNext()){
					SelectionKey key = it.next();
					if(key.attachment().equals(host) && key.isWritable() && option.equals("write")){
						socketChannel = (SocketChannel)key.channel();
						return socketChannel;
					}
					if (key.attachment().equals(host) && key.isReadable() && option.equals("read")) {
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
	 * @param host
	 */
	public static Set<SocketChannel> initiateHostPool(String host){

		Set<SocketChannel> channelSet = new HashSet<SocketChannel>();

		for(int i=0; i<initialPoolSize; i++){
			try {
				SocketChannel channel = SocketChannel.open(new InetSocketAddress(host, port));
				channelSet.add(channel);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return channelSet;
	}
}
