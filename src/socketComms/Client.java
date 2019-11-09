package socketComms;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws IOException, InterruptedException{
		// TODO Auto-generated method stub
		ServerSocket server = new ServerSocket(5556);
		String fromClient;
		String toClient = "next";
//		int toClient = 1;
//		System.out.println("Waiting for connection");
//		Socket client = server.accept();
//		System.out.println("Connection received");
		System.out.println("Waiting for connection");
		Socket client = server.accept();
		System.out.println("Connection received");
		
		boolean run = true;
		while(run){
			
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
//			PrintWriter out = new PrintWriter(client.getOutputStream(), true);
			DataOutputStream dout = new DataOutputStream(client.getOutputStream());
			DataInputStream din = new DataInputStream(client.getInputStream());
			
			byte inp = din.readByte();
//			System.out.println(inp);
			switch(inp) {
				case 1:
					System.out.println("Left");
					break;
				case 2:
					System.out.println("Right");
					break;
				case 3:
					System.out.println("Forward");
					break;
				case 4:
					System.out.println("Backward");
					break;
				case 10:
					System.out.println("Exit");
					run = false;
					break;
				default:
					System.out.println("Invalid");
					break;
			}
//			fromClient = in.readLine();
//			System.out.println("Received: " + fromClient);
			
			dout.writeByte(toClient.length());
			dout.writeBytes(toClient);
			
//			out.println(toClient);
		}
		
		server.close();
		System.exit(0);
	}
}
