package lab12DiffieHellmann;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Alice extends CryptoPlayer {

	public Alice() {

	}

	ServerSocket serverSocket;

	void run() {
		// http://stackoverflow.com/questions/1776457/java-client-server-application-with-sockets
		try {
			// 1. creating a server socket
			serverSocket = new ServerSocket(2004, 10);

			// 2. Wait for connection
			System.out.println("Waiting for connection");
			socket = serverSocket.accept();
			System.out.println("Connection received from " + socket.getInetAddress().getHostName());

			// 3. get Input and Output streams
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			sendMessage("Connection successful");

			// 4. The two parts communicate via the input and output streams
			do {
				//find big prime p
				p = new BigInteger(10, 128, new Random());
				//find smaller prime g
				g = new BigInteger(9, 128, new Random());
				//communicate all numbers to bob
				sendMessage(p.toString());
				sendMessage("done p");
				while(!message.equals("received p")){
					message = in.readLine();
					System.out.println("Bob>" + message);
				}
				sendMessage(g.toString());
				sendMessage("done g");
				while(!message.equals("received g")){
					message = in.readLine();
					System.out.println("Bob>" + message);
				}
				//chose random privKey smaller than p
				privKey = new BigInteger(8, new Random());
				//calc g^privkey mod p
				pubKey = g.modPow(privKey, p);
				//send to bob
				sendMessage(pubKey.toString());
				sendMessage("done pubKey");
				//wait for bob's g^bobPrivKey mod p
				while(!message.equals("done")){
					message = in.readLine();
					System.out.println("Bob>" + message);
					if(!message.equals("done")) {
						try {
							pubKeyPartner = new BigInteger(message);
						} catch (Exception e) {}
					}
				}
				//calc (g^privkey)^bobPrivKey mod p
				sharedKey = pubKeyPartner.modPow(pubKey, p);
				//done
				System.out.println("p: "+p);
				System.out.println("g: "+g);
//				System.out.println("alice privkey:"+privKey);
				System.out.println("alice pubkey: "+pubKey);
				System.out.println("bob pubkey: "+pubKeyPartner);
				System.out.println("alice shared: "+sharedKey.toString());
				
				message = in.readLine();
				if (message.equals("bye"))
					sendMessage("bye");
				
			} while (!message.equals("bye"));
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			// 4: Closing connection
			try {
				in.close();
				out.close();
				serverSocket.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}

	public void sendMessage(String msg) {
		out.println(msg);
		System.out.println("server>" + msg);
	}

	public static void main(String args[]) {
		Alice server = new Alice();
		while (!server.message.equals("bye")) {
			server.run();
		}
	}
}
