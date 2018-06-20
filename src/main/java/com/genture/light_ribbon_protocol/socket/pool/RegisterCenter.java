package com.genture.light_ribbon_protocol.socket.pool;

import com.genture.light_ribbon_protocol.socket.server.Server;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2018/6/11.
 */
public class RegisterCenter {

	private static Map<String, Boolean> registerMap = new HashMap<>();
	private static Selector selector;

	static {
		try {
			selector = Selector.open();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 向注册中心注册该host
	 * @param server
	 */
	public static void register(Server server){
		Boolean registered = isRegistered(server.getHost());
		if(registered == false){
			Set<SocketChannel> channels = ChannelPool.initiateHostPool(server);
			Iterator<SocketChannel> it = channels.iterator();
			while(it.hasNext()){
				SocketChannel channel = it.next();
				try {
					channel.register(selector, SelectionKey.OP_WRITE, server.getHost());
					channel.register(selector, SelectionKey.OP_READ, server.getHost());
				} catch (ClosedChannelException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 判断host是否在注册中心注册
	 * @param host
	 * @return
	 */
	public static boolean isRegistered(String host){
		boolean isRegistered = registerMap.containsKey(host);
		return isRegistered;
	}

	public static Selector getSelector() {
		return selector;
	}

	public static void setSelector(Selector selector) {
		RegisterCenter.selector = selector;
	}
}
