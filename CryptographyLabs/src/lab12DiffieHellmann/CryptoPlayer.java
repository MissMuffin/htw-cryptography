package lab12DiffieHellmann;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.util.Random;

public class CryptoPlayer {
	
	/**
	 * g = public (prime) base, known to Alice, Bob, and Eve
	 * p = public (prime) modulus, known to Alice, Bob, and Eve
	 * a = Alice's private key, known only to Alice
	 * b = Bob's private key known only to Bob
	 * A = Alice's public key, known to Alice, Bob, and Eve. A = g^a mod p
	 * B = Bob's public key, known to Alice, Bob, and Eve. B = g^b mod p
	 * WIKIPEDIA
	 */

	/**
	 * Alice and Bob agree on a finite cyclic group G of order n and a generating element g in G. 
	 * (This is usually done long before the rest of the protocol; g is assumed to be known by all attackers.) 
	 * The group G is written multiplicatively.
	 * Alice picks a random natural number a, where 1 ≤ a < n, and sends g^a to Bob.
	 * Bob picks a random natural number b, which is also 1 ≤ b < n, and sends g^b to Alice.
	 * Alice computes (g^b)^a.
	 * Bob computes (g^a)^b.
	 * Both Alice and Bob are now in possession of the group element g^(ab), 
	 * which can serve as the shared secret key. The group G satisfies the requisite 
	 * condition for secure communication if there is not an efficient algorithm for 
	 * determining whether g^(ab) = g^c given g^a, ^gb, and g^c for some c [elemenOf] G.
	 * WIKIPEDIA
	 */
	
	public BigInteger pubKey;
	public BigInteger sharedKey;
	public BigInteger privKey;
	public BigInteger pubKeyPartner;
	public BigInteger p;
	public BigInteger g;
	public Socket socket;
	public PrintWriter out;
	public BufferedReader in;
	public String message = "";
	
	private static int LIMIT = 10;
	
	public CryptoPlayer() {	}

	public BigInteger generateP() {
		return new BigInteger(LIMIT, 128, new Random());
	}
	
	public BigInteger generateG() {
		return new BigInteger(LIMIT-1, 128, new Random());
	}
	
	public BigInteger generatePrivKey() {
		return new BigInteger(p.bitLength()-1, new Random());
	}
	
	public BigInteger calcSharedKey() {
		return pubKeyPartner.modPow(pubKey, p);
	}
	
	public BigInteger calcPubKey() {
		return g.modPow(privKey, p);
	}
	
	public void sendMessage(String msg) {}
}
