package lab11EllipticCurves;

import java.util.ArrayList;
import java.util.HashSet;

import org.bouncycastle.crypto.agreement.kdf.ConcatenationKDFGenerator;

import lab03ExtendedEuclidian.ExtendedEuclidean;


public class EllipticCalcHelper {
        
    private ArrayList<Integer> quadrate;
    private ArrayList<Integer> inverses;
    private ArrayList<Point> points;
    
    public EllipticCalcHelper(){
        points = new ArrayList<Point>();
        quadrate = new ArrayList<Integer>();
        inverses = new ArrayList<>();
        initQuadrate();
        initInverses();
    }
    
//    quadrate{0,1,4,9,16,25,13,3,18,12,8,6};
    private void initQuadrate() {
        quadrate.add(0);
        quadrate.add(1);
        quadrate.add(4);
        quadrate.add(9);
        quadrate.add(16);
        quadrate.add(2);
        quadrate.add(13);
        quadrate.add(3);
        quadrate.add(18);
        quadrate.add(12);
        quadrate.add(8);
        quadrate.add(6);        
    }  
    
    private void initInverses() {
    	inverses.add(0);	//0
    	inverses.add(1);	//1
    	inverses.add(12);	//2
    	inverses.add(8);	//3
    	inverses.add(6);	//4
    	inverses.add(14);	//5
    	inverses.add(4);	//6
    	inverses.add(10);	//7
    	inverses.add(3);	//8
    	inverses.add(18);	//9
    	inverses.add(7);	//10
    	inverses.add(21);	//11
    	inverses.add(2);	//12
    	inverses.add(16);	//13
    	inverses.add(5);	//14
    	inverses.add(20);	//15
    	inverses.add(13);	//16
    	inverses.add(19);	//17
    	inverses.add(9);	//18
    	inverses.add(17);	//19
    	inverses.add(15);	//20
    	inverses.add(11);	//21
    	inverses.add(22);	//22
    }
    
    public void getAllPoints(){
        int res;
        for (int i = 0; i<23; i++){
            res = (i*i*i+3*i+22)%23;
            if (quadrate.contains(res)){
                points.add(new Point(i,quadrate.indexOf(res)));
                points.add(new Point(i,23-quadrate.indexOf(res)));
            }
        }
    }
    
    public Point calcP(Point p1, Point p2) {
    	Point result = new Point(0, 0);
    	int inverse = (p2.getX() - p1.getX()) % 23;
    	if(inverse<0) inverse+= 23;
    	int lambda = ((p2.getY() - p1.getY()) * inverses.get(inverse)) % 23;
    	if(lambda<0) lambda+=23;
    	int xtemp = (lambda * lambda - p1.getX() - p2.getX()) % 23;
    	result.setX(xtemp < 0 ? xtemp+23 : xtemp);
    	int ytemp = ((-1 * lambda * (result.getX() - p1.getX())) - p1.getY()) % 23;
    	result.setY(ytemp < 0 ? ytemp+23 : ytemp);
    	if (result.getY() < 0) {
    		result.setY(result.getY() + 23);
		}
    	System.out.println("lambda: " + lambda);
    	result.printPoint();
    	return result;
    }
    
    public Point calcP(Point p) {
    	Point result = new Point(0, 0);
    	int inverse = (2 * p.getY()) % 23;
    	if(inverse<0) inverse+= 23;
    	int lambda = (3 * p.getX() * p.getX() + 3) * inverses.get(inverse) % 23;
    	if(lambda<0) lambda+=23;
    	int xtemp = (lambda * lambda - (2 * p.getX())) % 23;
    	result.setX(xtemp < 0 ? xtemp+23 : xtemp);
    	int ytemp = ((-1 * lambda * (result.getX() - p.getX())) - p.getY()) % 23;
    	result.setY(ytemp < 0 ? ytemp+23 : ytemp);
    	if (result.getY() < 0) {
    		result.setY(result.getY() + 23);
		}
    	System.out.println("lambda: " + lambda);
    	result.printPoint();
    	return result;
    }
    
    public static void main(String[] args) {
        EllipticCalcHelper e = new EllipticCalcHelper();
//        e.getAllPoints();
//        for (Point p : e.points){
//            p.printPoint();
//        }
        
        Point input = new Point(6, 16);
        System.out.println("k: 2");
        Point k2 = e.calcP(input);
//        Point k3 = e.calcP(k2, input);
//        Point k6 = e.calcP(k3);
//        Point k9 = e.calcP(k6, k3);
//        Point k11 = e.calcP(k9, k2);
//        Point k22 = e.calcP(k11);
//        Point k33 = e.calcP(k11, k22);
        
        for (int i = 3; i < 34; i++) {
        	System.out.println("k: " + i);
			k2 = e.calcP(k2, input);
		}
    }
}

