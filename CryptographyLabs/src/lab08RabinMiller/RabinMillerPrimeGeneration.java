package lab08RabinMiller;

import java.math.BigInteger;
import java.util.Random;

public class RabinMillerPrimeGeneration {	
	
	private static boolean RabinMiller(BigInteger n, BigInteger security) {
		
		BigInteger zero = BigInteger.ZERO;
		BigInteger one =  BigInteger.ONE;
		BigInteger two = BigInteger.valueOf(2);
		BigInteger three = BigInteger.valueOf(3);
		
		if(n.compareTo(three) < 0) {
//			System.out.println("input needs to be greater or equal to 3");
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
					if (!v.equals(one)) {
						
						BigInteger i = new BigInteger("0");
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
//				System.out.println("input is multiple of 2, not good");
				return false;
			}
		}		
		return true;
	}
	
	private static void findPositiveFalse(int bitlength, int certainty) {
		BigInteger n;
		boolean found = false;
		while(!found) {
			n  = new BigInteger(bitlength, new Random());
			if(RabinMiller(n, BigInteger.valueOf(certainty)) && !n.isProbablePrime(10)) {
				System.out.println("false positive number: " + n);
				found = true;
			}
		}
	}
	
	private static void findPrime(int bitLength, int certainty) {
		BigInteger n;
		boolean found = false;
		while(!found) {
			n  = new BigInteger(bitLength, new Random());
			if(RabinMiller(n, BigInteger.valueOf(certainty))) {
				System.out.println("probable prime: " + n);
				System.out.println("BigInt prime: " + n.isProbablePrime(10));
				found = true;
			}
		}
	}
	
	private static void findPrimes(long limit, int certainty) {
		BigInteger g = BigInteger.valueOf(0);
		BigInteger limitBigInt = BigInteger.valueOf(limit);
		int count = 0;
		int countBigInt = 0;
		
		while(!g.equals(limit)) {
			if(RabinMiller(g, BigInteger.valueOf(certainty))) {
//				System.out.println(g.toString() + " TRUE");	
				count++;
			}
			if (g.isProbablePrime(certainty)) {
				countBigInt++;
			}
			
			g = g.add(BigInteger.ONE);
		}
		System.out.println("DONE");
		System.out.println("number of primes us : " + count); //should be 78498
		System.out.println("number of primes BigInt : " + countBigInt);
	}
	
	public static void main(String[] args) {
//		RabinMillerPrimeGeneration.findPrimes(1000000l, 10);
		
//		RabinMillerPrimeGeneration.findPositiveFalse(32, 1);
		
		RabinMillerPrimeGeneration.findPrime(512, 5);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
