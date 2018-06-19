package com.genture.light_ribbon_protocol.socket.pool;

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

	private static Map<String, Boolean> registerMap = new HashMap<String, Boolean>();
	private static Selector selector;

	static {
		try {
			selector = Selector.open();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void register(String host){
		Boolean registered = registerMap.get(host);
		if(registered == false){
			Set<SocketChannel> channels = ChannelPool.initiateHostPool(host);
			Iterator<SocketChannel> it = channels.iterator();
			while(it.hasNext()){
				SocketChannel channel = it.next();
				try {
					channel.register(selector, SelectionKey.OP_WRITE, host);
					channel.register(selector, SelectionKey.OP_READ, host);
				} catch (ClosedChannelException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static boolean isRegistered(String host){
		boolean isRegistered = registerMap.get(host);
		return isRegistered;
	}

	public static Selector getSelector() {
		return selector;
	}

	public static void setSelector(Selector selector) {
		RegisterCenter.selector = selector;
	}
}
