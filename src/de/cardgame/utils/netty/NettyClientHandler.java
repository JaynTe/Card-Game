package de.cardgame.utils.netty;

import org.json.JSONObject;

import de.cardgame.Main;
import de.cardgame.utils.Logger;
import de.cardgame.utils.Logger.LogType;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyClientHandler extends SimpleChannelInboundHandler<String> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		Logger.log(msg, LogType.DEBUG);

		JSONObject jb = new JSONObject(msg);
		Events event = Events.valueOf(jb.getString("event"));

		switch (event) {
		case SERVER_VERIFIEDEVENT:
				if(jb.getBoolean("verified")) {
					Logger.log("Verification was successful!", LogType.INFO);
				}else {
					Logger.log("Verification was NOT successful!", LogType.ERROR);
					Logger.log("Retrying in 5sec", LogType.INFO);
				}
			break;

		case SERVER_USERINFO:
				Logger.log("Userinfo updating", LogType.INFO);
				//UPDATE USERINFO
				Main.getUser().setUsername(jb.getString("username"));
				Main.getUser().setEmail(jb.getString("email"));
				Main.getUser().setLevel(jb.getInt("level"));
				Main.getUser().setXp(jb.getInt("xp"));
				Main.getUser().setMoney(jb.getInt("money"));
				Main.getUser().setPremmoney(jb.getInt("premmoney"));
			break;

		default:
			break;
		}

	}

}
