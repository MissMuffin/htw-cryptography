package lab03ExtendedEuclidian;

public class ExtendedEuclidean {
	
	long j = 0;
	long k = 0;
	
	public ExtendedEuclidean(long j, long k){
		this.j = j;
		this.k = k;
	}
	
	public long[] calculatedEGCD(){
		long c = j;
		long d = k;
		long q = 0;
		long[] factors = {1,0,0,1};
		long tempc = 0;
		
		long temp1;
		long temp2;
		long temp3;
		long temp4;
		
		while (c>0){
			q = (long) Math.floor((1.0*d)/c);
			tempc = c;
			c = d-q*c;
			d = tempc;
			
			//factors
			temp1 = factors[2] - q*factors[0];
			temp2 = factors[3] - q*factors[1];
			temp3 = factors[0];
			temp4 = factors[1];
			
			factors[0] = temp1;
			factors[1] = temp2;
			factors[2] = temp3;
			factors[3] = temp4;
		}
		
		long[] returnArray={d, factors[2], factors[3]};
		return returnArray;
	}
	
	/*
	 * Given are a, b, p, and q with p and q different prime numbers, a and b any two integers. Write a small program that calulates the following values:
	 * Determine a solution z for qz = 1 mod p
	 * Compute y = (a - b)z mod p
	 * Determine x = yq + b
	 */
	public void exerciseThree(long h, long l) {		
		long[] egcdVals = calculatedEGCD(); //1:d 2:factor f 3:factor g
		
		long a = h;	//int
		long b = l;	//int
		long q = this.j;	//prime
		long p = this.k;	//prime		
		long z = egcdVals[1];	//factor 1 is the inverse of z in qz = 1 mod p -> q*factor1 + p*factor2 = 1
		
		long y = ((a-b) * z ) % p;
		
		long x = y * q + b;
		
		System.out.println("result z: " + z);
		System.out.println("result y: " + y);
		System.out.println("result x: " + x);
	}
	
	public void printGCD(long[] vals) {		
		System.out.println("GCD: " + vals[0]);
		System.out.println("Factors: " + vals[1] + " ," + vals[2]);		
		System.out.println("(" + vals[1] + " * " + this.j + ")" 
		+ " + ("+ vals[2] + " * " + this.k + ")" + " = " + vals[0]);
	}
	
	
	public static void main(String[] args) {
		ExtendedEuclidean e = new ExtendedEuclidean(899, 961);
		
		e.printGCD(e.calculatedEGCD());
//		e.exerciseThree(5, 10);
		
	}
	

//		public static void main(String[] args) {
//
//			int ni = 0;
//			int Mi = 60;
//			int mi = 7;
//			
//			while (!((Mi * ni) % mi == 1)){
//				ni++;
//			}
//			System.out.println(ni);
//		}	
}
