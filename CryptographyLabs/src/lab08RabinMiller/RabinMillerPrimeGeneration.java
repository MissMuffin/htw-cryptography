package lab08RabinMiller;

import java.math.BigInteger;
import java.util.Random;

public class RabinMillerPrimeGeneration {	
	
	private static boolean generatePrime(BigInteger n, BigInteger security) {
		
		BigInteger zero = BigInteger.ZERO;
		BigInteger one =  BigInteger.ONE;
		BigInteger two = BigInteger.valueOf(2);
		BigInteger three = BigInteger.valueOf(3);
		
		if(n.compareTo(three) < 0) {
			System.out.println("input needs to be grater or equal to 3");
			return false;
		} else {
			if(n.mod(two).equals(one)) {
				
				BigInteger s = 	n.subtract(one);		
				BigInteger t = new BigInteger("0");
				
				while(s.mod(two).equals(zero)) {
					s = s.divide(two);
					t = t.add(one);
				}
				
				BigInteger k = new BigInteger("0");
				while(k.compareTo(security) < 0) {	//is this k < security?
					BigInteger a;
					do {
						a = new BigInteger(n.bitLength(), new Random());
					} while (a.compareTo(n) >= 0 || a.compareTo(two) < 0); //http://stackoverflow.com/questions/2290057/how-to-generate-a-random-biginteger-value-in-java
					
					BigInteger v = a.modPow(s, n);
					if (v.equals(one)) {
//						System.out.println("v passes = a test");
					} else {
						BigInteger i = new BigInteger("1");
						while (!v.equals(n.subtract(one))) {
							if (i.equals(t.subtract(one))) {
								return false;
							} else {
								v = v.modPow(two, n);
								i = i.add(one);
							}
						}
					}
					k = k.add(two);
				}
				
			} else {
				System.out.println("input is multiple of 2, not good");
				return false;
			}
		}		
		return true;
	}
	
	public static void main(String[] args) {
		BigInteger g = BigInteger.valueOf(0);
		BigInteger limit = BigInteger.valueOf(1000000);
		while(!g.equals(limit)) {
			
			if(generatePrime(g, BigInteger.valueOf(5))) {
				System.out.println(g.toString() + " TRUE");			
			}
			g = g.add(BigInteger.ONE);
		}
		System.out.println("DONE");
	}
}
