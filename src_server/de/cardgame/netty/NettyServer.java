package de.cardgame.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

	private final int port;

	public NettyServer(int port) {
		this.port = port;
	}

	public void run() throws InterruptedException {
		final EventLoopGroup bGroup = new NioEventLoopGroup();
		final EventLoopGroup wGroup = new NioEventLoopGroup();

		try {
			final ServerBootstrap bootstrap = new ServerBootstrap().group(bGroup, wGroup)
					.channel(NioServerSocketChannel.class).childHandler(new NettyServerInitializer());

			bootstrap.bind(port).sync().channel().closeFuture().sync();
		} finally {
			bGroup.shutdownGracefully();
			wGroup.shutdownGracefully();
		}
	}

}
