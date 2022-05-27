package de.cardgame.utils;

import de.cardgame.Main;
import de.cardgame.utils.Logger.LogType;

public class Helper {
	
	public static void shutdown() {
		Logger.log("Closing Netty Connection.", LogType.INFO);
		Main.getNc().getChannel().disconnect();
		Main.getNc().getGroup().shutdownGracefully();
		Logger.log("Shutdown!", LogType.INFO);
		System.exit(1337);
	}

}
