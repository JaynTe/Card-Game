package de.cardgame.netty;

public enum Events {
	
	CLIENT_VERIFYEVENT("CLIENT_VERIFYEVENT"), /* json: event:CLIENT_VERIFYEVENT, secretkey, userid example: {"event":"CLIENT_VERIFYEVENT", "userid":1, "secretkey":"w"}*/
	
	SERVER_VERIFIEDEVENT("SERVER_VERIFIEDEVENT"), /* json: event:SERVER_VERIFIEDEVENT, verified:true or false */
	SERVER_USERINFO("SERVER_USERINFO"); /* json: event:SERVER_USERINFO, username, xp, level, money, premmoney */
	
	private final String name; 
	
	private Events(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}
