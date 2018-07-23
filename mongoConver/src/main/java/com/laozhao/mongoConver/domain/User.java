package com.laozhao.mongoConver.domain;

import java.util.Date;

public class User {
	@Override
	public String toString() {
		return "User [name=" + name + ", time=" + time + ", getName()=" + getName() + ", getTime()=" + getTime()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	private String name = "zy";
	private Date time = new Date();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
