package com.github.sinhr.private_chat.core;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/*
 *  Listens port and creates ServerThread for incoming connections
 */
public class Server {
	
	private HashMap<Socket,DataOutputStream> outputStreams;
	
	public Server(int port) throws IOException {
		listen(port);
	}

	public static void main(String[] args) throws IOException {
		int port = Integer.parseInt(args[0]);
		new Server(port);
	}

	private void listen(int port) throws IOException	{
		ServerSocket ss = new ServerSocket(port);
		System.out.println("Listening on "+ss);
		
		while(true)	{
			Socket s = ss.accept();
			System.out.println("Connection from "+s);
			
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			outputStreams.put(s,dout);
			
			new ServerThread(this,s);
		}
	}

	public synchronized void sendToAll(String message) {
		//TODO 

	}

	public void removeConnection(Socket socket) {
		try {
			socket.shutdownInput();
			socket.shutdownOutput();
			socket.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}