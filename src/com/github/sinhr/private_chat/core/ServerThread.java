package com.github.sinhr.private_chat.core;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread {

	private Server server;
	private Socket socket;

	public ServerThread(Server server, Socket socket) {
		this.server = server;
		this.socket = socket;

		start();
	}

	public void run() {

		try {
			DataInputStream din = new DataInputStream(socket.getInputStream());

			while (true) {
				String message = din.readUTF();

				System.out.println("Sending " + message);
				server.sendToAll(message);
			}
		} catch (EOFException ie) {
			
		} catch (IOException ie) {
			
			ie.printStackTrace();
		} finally {

			server.removeConnection(socket);
		}

	}
}
