package com.genture.light_ribbon_protocol.socket.server;

/**
 * Created by Administrator on 2018/6/11.
 */
public class Server {

	private String host;
	private static int port = 5000;

	public Server(String host, int port){
		this.host = host;
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
