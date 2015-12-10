package lab06PrimeNumbers;

import java.math.BigInteger;
import java.util.Random;

public class PrimeNumbers {

	BigInteger big1;
	BigInteger big2;
	
	
	public PrimeNumbers() {
		big1 = new BigInteger(65, 128, new Random());
		big2 = new BigInteger(65, 128, new Random());
		System.out.println(big1 + "   " + big1.bitLength());
		System.out.println(big2 + "   " + big2.bitLength());
	}
	
	private BigInteger multiply(BigInteger big1, BigInteger big2) {
		return big1.multiply(big2);
	}
	
	public static void main(String[] args) {
		PrimeNumbers test = new PrimeNumbers();
		
		BigInteger multiBigInt = test.multiply(test.big1, test.big2);
		System.out.println(multiBigInt);
	}
}
