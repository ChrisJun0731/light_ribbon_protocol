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
	private static final int initialPoolSize = 5;

	public static SocketChannel getChannel(Server server, String option){
		boolean isRegistered = RegisterCenter.isRegistered(server.getHost());
		SocketChannel socketChannel = null;
		if(!isRegistered){
			RegisterCenter.register(server);
		}
		try {
			while(RegisterCenter.getSelector().select()!=0){
				Set<SelectionKey> keys = RegisterCenter.getSelector().selectedKeys();
				Iterator<SelectionKey> it = keys.iterator();
				while(it.hasNext()){
					SelectionKey key = it.next();
//					boolean writeable = key.isWritable();
//					boolean readable = key.isReadable();
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
}
