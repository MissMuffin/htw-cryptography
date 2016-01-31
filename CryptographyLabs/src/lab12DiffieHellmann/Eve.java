package lab12DiffieHellmann;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class Eve extends CryptoPlayer {

	public Eve() {
		
	}
	
	SocketAddress addr;
	Proxy proxy;
	InetSocketAddress dest;
	
	ServerSocket serverSocket;
	Socket bobSocket;
	Socket aliceSocket;
	PrintWriter bobOut;
	BufferedReader bobIn;
	PrintWriter aliceOut;
	BufferedReader aliceIn;
	
	void run() {
		try{
			
//			addr = new InetSocketAddress("localhost", 2004);
//			proxy = new Proxy(Proxy.Type.SOCKS, addr);
//			socket = new Socket(proxy);
//			dest = new InetSocketAddress("localhost", 2005);
//			socket.connect(dest);
			
			//communicate with bob
//			serverSocket = new ServerSocket(2014, 10);
//			
//			System.out.println("eve waiting for connection");
//			bobSocket = serverSocket.accept();
//			System.out.println("Connection received from " + bobSocket.getInetAddress().getHostName());
//			
//			bobOut = new PrintWriter(bobSocket.getOutputStream(), true);
//			bobIn = new BufferedReader(new InputStreamReader(bobSocket.getInputStream()));
			
			//communicate with alice
			aliceSocket = new Socket("localhost", 2004);
			aliceOut = new PrintWriter(aliceSocket.getOutputStream(), true);
			aliceIn = new BufferedReader(new InputStreamReader(aliceSocket.getInputStream()));
			
			do{
				aliceOut.println("i'm eve");
			} while (!message.equals("bye"));
			
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			try {
				if(in != null)in.close();
				if(out != null) out.close();
				socket.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}
	
	public void sendMessage(String msg) {
		out.println(msg);
		System.out.println("server>" + msg);
	}
	
	public static void main(String[] args) {
		Eve eve = new Eve();
		eve.run();
	}
}
