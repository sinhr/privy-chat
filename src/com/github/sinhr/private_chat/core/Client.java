package com.github.sinhr.private_chat.core;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client implements Runnable {
	
	DataInputStream din;
	DataOutputStream dout;
	
	private JTextField tf;
	private JTextArea ta;
	JPanel ui = new JPanel(new GridLayout());
		
	public Client(String host, int port)	{
		
		setLayout(new BorderLayout());
			ui.add("North", tf);
			ui.add("Center", ta);
		
			
		tf.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e)	{
				processMessage(e.getActionCommand());
			}
		});
				
		try	{
			
			Socket socket = new Socket(host, port);
			System.out.println("Connected to "+socket);
			
			din = new DataInputStream(socket.getInputStream());
			dout = new DataOutputStream(socket.getOutputStream());
			
			new Thread(this).start();
		
		} catch ( IOException ie)	{	
			System.out.println(ie);
		}
	}
	
	private void setLayout(BorderLayout borderLayout) {
		// TODO Auto-generated method stub
		
	}

	public void processMessage(String message) {
		
		try	{
			dout.writeUTF(message);
			tf.setText("");
			
		}	catch( IOException ie)	{
			System.out.println(ie);
		}
	}

	@Override
	public void run() {
		
		try	{
			while(true)	{
				String message = din.readUTF();
				ta.append(message+"\n");
			}
		}	catch ( IOException ie)	{
			System.out.println(ie);
		}
		
	}

}

