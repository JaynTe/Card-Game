package de.cardgame;

import java.util.ArrayList;
import java.util.HashMap;

import de.cardgame.netty.NettyServer;
import de.cardgame.utils.Logger;
import de.cardgame.utils.User;
import de.cardgame.utils.Logger.LogType;
import io.netty.channel.Channel;

public class Main {
	
	private final static HashMap<Channel, User> verifiedUser = new HashMap<>();
	private final  static ArrayList<Channel> notverifiedUser = new ArrayList<>();

	
	private final static String SERVER_NAME = "CARDSERVER";
	private final static double SERVER_VERSION = 0.1;
	
	public static void main(String[] args) {
		Logger.log("Staring " + SERVER_NAME + " v" + SERVER_VERSION, LogType.INFO);
		
		NettyServer ns = new NettyServer(8000);
		try {
			ns.run();
		} catch (InterruptedException e) {
			Logger.log("Exception in Mainclass: ", LogType.FATAL);
			e.printStackTrace();
		}
	}

	public static HashMap<Channel, User> getVerifieduser() {
		return verifiedUser;
	}

	public static ArrayList<Channel> getNotverifieduser() {
		return notverifiedUser;
	}

	public static String getServerName() {
		return SERVER_NAME;
	}

	public static double getServerVersion() {
		return SERVER_VERSION;
	}

}
