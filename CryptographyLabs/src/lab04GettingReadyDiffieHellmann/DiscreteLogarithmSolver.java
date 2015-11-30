package lab04GettingReadyDiffieHellmann;

public class DiscreteLogarithmSolver {

//  schreiben sie ein programm zur bestimmung des diskreten logarithmus
//  parameter seien eine primzahl p und zwei zahlen a, b aus {1, 2, ..., p-1}
//  ruckgabewert: die kleinste naturliche zahl x welche die kongruenz a^x=b mod p erfullt
//          ansonsten 0
  
  
  long p, a, b, temp = 0;
  long x = 1;
  long periodCounter = 0;
  
  public DiscreteLogarithmSolver(long a, long b, long p) {
      this.a = a;
      this.b = b;
      this.p = p;
      this.temp = a;
  }
  
  private long solve() {        
      
      while (temp != b) {
          
          //check if full period has been reached
          if (periodCounter == 2) return 0;
          
          temp = ((temp * a)%p);
          if (temp == 1) periodCounter++;
          x++;
      }        
      return x;
  }
  
  
  public static void main(String args[]) {        
      DiscreteLogarithmSolver test = new DiscreteLogarithmSolver(7, 10, 31);
      long result = test.solve();
      String answer = ""+test.a+" ^ x = "+test.b+" mod "+test.p+" ";
      answer += result == 0 ?
              "has no solution" : "is solvable : "+ "\n x = "+result+" is one solution "+ "\n using fermat’s theorem more solutions can be described with x = "+result+" + "+(test.p-1)+"t";
      System.out.println(answer);
  }
  
}

