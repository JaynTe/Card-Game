package de.cardgame.netty;

import org.json.JSONObject;

import de.cardgame.Main;
import de.cardgame.utils.JsonUtil;
import de.cardgame.utils.Logger;
import de.cardgame.utils.WebRequest;
import de.cardgame.utils.Logger.LogType;
import de.cardgame.utils.User;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class NettyServerHandler extends SimpleChannelInboundHandler<String> {

	private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) {
		Channel incoming = ctx.channel();

		Logger.log("New Connection: " + incoming.remoteAddress(), LogType.INFO);
		channels.add(incoming);
		Main.getNotverifieduser().add(incoming);
		Logger.log("Adding '" + incoming.remoteAddress() + "' to not verified user.", LogType.INFO);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) {
		Channel incoming = ctx.channel();

		if (Main.getVerifieduser().containsKey(incoming)) {
			Logger.log("User '" + Main.getVerifieduser().get(incoming).getUsername() + "/#"
					+ Main.getVerifieduser().get(incoming).getUserid() + "' (" + incoming.remoteAddress()
					+ ") disconnected!", LogType.INFO);
			Main.getVerifieduser().remove(incoming);
		} else if (Main.getNotverifieduser().contains(incoming)) {
			Logger.log("Not verified user '" + incoming.remoteAddress() + "' disconnected!", LogType.INFO);
			Main.getNotverifieduser().remove(incoming);
		}

		channels.remove(incoming);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext arg0, String message) {
		Channel incoming = arg0.channel();

		if(!JsonUtil.isJSONValid(message)) {
			Logger.log("Message from '" + incoming.remoteAddress() + "' was not in a json format!", LogType.ERROR);
			incoming.disconnect();
			return;	
		}
		
		JSONObject jb = new JSONObject(message);
		Events event = Events.valueOf(jb.getString("event"));

		if (Main.getNotverifieduser().contains(incoming)) {
			if (event != Events.CLIENT_VERIFYEVENT) {
				incoming.disconnect();
				return;
			}
			if (WebRequest.isUserVerified(jb.getInt("userid"), jb.getString("secretkey"))) {
				Main.getVerifieduser().put(incoming, new User(jb.getInt("userid"), jb.getString("secretkey")));
				Logger.log("User is verified (#" + jb.getInt("userid") + ")", LogType.INFO);
				
				JSONObject jb2 = WebRequest.getUserinfo(jb.getInt("userid"), jb.getString("secretkey"));
				
				if(jb2.has("error")) {
					Logger.log(jb2.getString("errormsg"), LogType.ERROR);
					return;
				}
				
				Main.getVerifieduser().get(incoming).setUsername(jb2.getString("username"));
				Main.getVerifieduser().get(incoming).setEmail(jb2.getString("email"));
				Main.getVerifieduser().get(incoming).setLevel(jb2.getInt("level"));
				Main.getVerifieduser().get(incoming).setXp(jb2.getInt("xp"));
				Main.getVerifieduser().get(incoming).setMoney(jb2.getInt("money"));
				Main.getVerifieduser().get(incoming).setPremmoney(jb2.getInt("premmoney"));
				
				Logger.log("Sending SERVER_VERIFIEDEVENT and SERVER_USERINFO to (" + jb2.getString("username") + "/#" + jb.getInt("userid") + ")", LogType.INFO);
				incoming.writeAndFlush(new JSONObject().put("event", "SERVER_VERIFIEDEVENT").put("verified", true) + "\n");
				incoming.writeAndFlush(jb2.put("event", "SERVER_USERINFO") + "\n");
				
			} else {
				Logger.log("User was not verified.", LogType.INFO);
				incoming.writeAndFlush(new JSONObject().put("event", "SERVER_VERIFIEDEVENT").put("verified", false) + "\n");
				incoming.disconnect();
				return;
			}

		}

//		switch (event.getName()) {
//		case "CLIENT_VERIFYEVENT":
//			
//			break;
//
//		default:
//			break;
//		}

		Logger.log(message, LogType.DEBUG);
	}

}
