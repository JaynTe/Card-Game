package de.cardgame.utils;

public class UserInfo {
	
	private int userid = 0;
	private String username = "";
	private String email = "";
	private String secretkey = "";
	
	private int level = 0;
	private int xp = 0;
	
	private int money = 0;
	private int premmoney = 0;
	
	public UserInfo() {}
	
	public UserInfo(int userid, String secretkey) {
		this.userid = userid;
		this.secretkey = secretkey;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserid() {
		return userid;
	}
	
	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getSecretkey() {
		return secretkey;
	}

	public void setSecretkey(String secretkey) {
		this.secretkey = secretkey;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getPremmoney() {
		return premmoney;
	}

	public void setPremmoney(int premmoney) {
		this.premmoney = premmoney;
	}
	
}