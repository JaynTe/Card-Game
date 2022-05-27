package de.cardgame.utils.netty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

import de.cardgame.utils.Logger;
import de.cardgame.utils.Logger.LogType;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
	
	private final List<JSONObject> requests = Collections.synchronizedList(new ArrayList<>());

	private final String host;
	private final int port;
	
	private Channel channel;
	private EventLoopGroup group;

	public NettyClient(String host, final int port) {
		this.host = host;
		this.port = port;
	}

	public void run() throws Exception {
		group = new NioEventLoopGroup();

		try {
			Bootstrap bootstrap = new Bootstrap().group(group).channel(NioSocketChannel.class)
					.handler(new NettyClientInitializer());

			channel = bootstrap.connect(host, port).sync().channel();

				while (true) {
					if(!requests.isEmpty())
						for(int i = 0; i < requests.size(); i++) {
							channel.writeAndFlush(requests.get(i).toString() + "\r\n");
							Logger.log("Send to Server:" , LogType.DEBUG);
							Logger.log(requests.get(i).toString(), LogType.DEBUG);
							requests.remove(i);
						}
				}
		} finally {
			group.shutdownGracefully();
		}
	}

	public EventLoopGroup getGroup() {
		return group;
	}

	public Channel getChannel() {
		return channel;
	}

	public List getRequests() {
		return requests;
	}
	
	

}
