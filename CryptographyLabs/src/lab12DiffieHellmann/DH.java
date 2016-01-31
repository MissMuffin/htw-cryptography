package lab12DiffieHellmann;

import java.io.IOException;
import java.net.ServerSocket;

public class DH {

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
	
	public static void main(String[] args) {
		Alice alice = new Alice();
		Bob bob = new Bob();
		
		alice.run();
		bob.run();
	}
}
