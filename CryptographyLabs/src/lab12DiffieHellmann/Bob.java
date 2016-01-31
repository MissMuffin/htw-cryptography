package lab12DiffieHellmann;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class Bob extends CryptoPlayer {

	public Bob() {}

	void run() {
		// http://stackoverflow.com/questions/1776457/java-client-server-application-with-sockets
		try {
			// 1. creating a socket to connect to the server
			socket = new Socket("localhost", 2014);
			System.out.println("Connected to localhost in port 2014");
			
			// 2. get Input and Output streams
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			// 3: Communicating with the server
			do {
				
				//receive p from alice
				while(!message.equals("done p")) {
					message = in.readLine();
					System.out.println("Alice> "+message);
					if(!message.equals("done p")) {
						try{
							p = new BigInteger(message);
						}catch(Exception e) {}
					}
				}
				sendMessage("received p");
				//receive g form alice
				while(!message.equals("done g")) {
					message = in.readLine();
					System.out.println("Alice> "+message);
					if(!message.equals("done g")) {
						try {
							g = new BigInteger(message);
						} catch (Exception e){}
					}
				}			
				sendMessage("received g");
				//receive alice's pubkey
				while(!message.equals("done pubKey")) {
					message = in.readLine();
					System.out.println("Alice> "+message);
					if(!message.equals("done pubKey")) {
						try {
							pubKeyPartner = new BigInteger(message);
						} catch (Exception e){}
					}
				}
				//chose random privKey smaller than p
				privKey = new BigInteger(8, new Random());	
				//calc g^privkey mod p = pubkey
				pubKey = g.modPow(privKey, p);
				//send pubkey to alice
				sendMessage(pubKey.toString());
				sendMessage("done");
				//calc (g^privkey)^bobPrivKey mod p
				sharedKey = pubKeyPartner.modPow(pubKey, p);
				//done
				System.out.println("p: "+p);
				System.out.println("g: "+g);
				System.out.println("alice pubkey: "+pubKeyPartner);
//				System.out.println("bob privkey:"+privKey);
				System.out.println("bob pubkey: "+pubKey);
				System.out.println("bob shared: " + sharedKey.toString());
				message = "bye";
				sendMessage(message);
				
			} while (!message.equals("bye"));
		} catch (UnknownHostException unknownHost) {
			System.err.println("You are trying to connect to an unknown host!");
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			// 4: Closing connection
			try {
				in.close();
				out.close();
				socket.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}

	public void sendMessage(String msg) {
		out.println(msg);
		System.out.println("Bob>" + msg);
	}

	public static void main(String args[]) {
		Bob client = new Bob();
		client.run();
	}
}
