package lab04GettingReadyDiffieHellmann;

public class EffectiveCalculationProgram {
    //schreiben sie ein programm zur moglichst effektiven berechnung von x^y mod n
    //wobei x, y, n vom typ long sind
    

    private long x, y, n = 0;
    private long temp = 0;
    
    public EffectiveCalculationProgram(long x, long y, long n) {
        this.x = x;
        this.y = y;
        this.n = n;
        this.temp = x%n;
    }
    
    public long calculate() {    
        long result = 1;
        long powerOfTwo = 1;
        
        while (powerOfTwo <= y) {
            if ((y & powerOfTwo) == powerOfTwo) {
                result = (result * temp)%n;
            }
            temp = ((temp * temp)%n);
            powerOfTwo = powerOfTwo << 1;    //bitwise shift for quicker calculations instead of multiplication with 2            
        }
        return result%n;
    }
    
    
    public static void main(String[] args) {
        EffectiveCalculationProgram test = new EffectiveCalculationProgram(11, 234, 43);
        System.out.println("" + test.x + " ^ " + test.y + " mod " + test.n + " =");
        System.out.println(test.calculate());
    }
}

