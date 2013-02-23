package com.github.sinhr.private_chat.core;

public class Message {
	
	private static final long serialVersionUID = 1112122200L;
	static final int WHOISIN=0, MESSAGE=1, DISCONNECT=2;
	private int type;
	private String message;
	
	Message(int type, String message){
		this.type = type;
		this.message = message;
	}
	
	int getType(){
		return type;
	}
	
	String getMessage(){
		return message;
	}

}

