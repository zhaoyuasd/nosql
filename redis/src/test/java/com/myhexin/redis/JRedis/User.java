package com.myhexin.redis.JRedis;

public class User {
  private String userName;
  private String passWord;
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPassWord() {
	return passWord;
}
public void setPassWord(String passWord) {
	this.passWord = passWord;
}
public User(String userName, String passWord) {
	super();
	this.userName = userName;
	this.passWord = passWord;
}
@Override
public String toString() {
	return "User [userName=" + userName + ", passWord=" + passWord + "]";
}

public User() {}
}
