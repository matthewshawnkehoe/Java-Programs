/**************************************************************************
**
**    Dirichlet series for the Zeta function           
**
**************************************************************************
**    Matthew Kehoe
**    09/20/2015
**
**    This program computes the value for Zeta(z) using the Dirichlet series 
**    summation. The Dirichlet series summation is valid for Re(s) > 0 and
**    quickly approaches convergence. The BigDecimal class and methods are 
**    referenced through the Apfloat library for Java.
**************************************************************************/

import org.apfloat.ApcomplexMath;
import org.apfloat.Apcomplex;
import org.apfloat.Apfloat;
import java.util.*;

public class DirichletZeta {
    final private static long PRECISION = 500;
    final private static Apfloat ONE = new Apfloat("1", PRECISION);
      
    public static void main(String[] args) {
        DirichletMain();
    }

    // Main method
    public static void DirichletMain() {
        float re = 0, im = 0;
        double start, stop, totalTime;
        Scanner scan = new Scanner(System.in);
        System.out.println("Calculation of the Riemann Zeta "
                + "Function in the form Zeta(s) = a + ib.");
        System.out.println();
        System.out.print("Enter the value of [a] inside the Riemann Zeta "
                + "Function: ");
        try {
            re = scan.nextFloat();
        } catch (Exception e) {
            System.out.println("Please enter a valid number for a.");
        }
        System.out.print("Enter the value of [b] inside the Riemann Zeta "
                + "Function: ");
        try {
            im = scan.nextFloat();
        } catch (Exception e) {
            System.out.println("Please enter a valid number for b.");
        }
        start = System.currentTimeMillis();
        Apfloat real = new Apfloat(re, PRECISION);
        Apfloat ima = new Apfloat(im, PRECISION);
        Apcomplex s = new Apcomplex(real,ima);
        System.out.println("The value for Zeta(s) is " + 
                String.format("%.100s", calculateDirichletZeta(s)));
        stop = System.currentTimeMillis();
        totalTime = (double) (stop - start) / 1000.0;
        System.out.println("Total time taken is " + totalTime + " seconds.");
    }
    
    /**
     * The calculation of the Dirichlet series summation for Zeta(s)
     * Calculation = 1/(s-1) * series summation.
     * Stop the summation when the convergence is reached through the
     * desired Apfloat EPSILON.
     * @param s the user defined value for re(s) and ima(s)
     * @return the calculated value of Zeta(s)
    */  
    private static Apcomplex calculateDirichletZeta(Apcomplex s) {
        Apcomplex sum = Apcomplex.ZERO;
        Apcomplex cons = (ONE.divide(s.subtract(ONE)));
        Apfloat EPSILON = new Apfloat("1e-6", PRECISION);
        int stop_condition = 1;
        int n = 1;
        while (stop_condition > 0)
        {
            Apcomplex nextIter = zetaSum(n, s);
            stop_condition = ApcomplexMath.abs(nextIter).compareTo(EPSILON);
            sum = sum.add(nextIter);
            {
                System.out.println("Value at interation " + n + " : " 
                        + String.format("%.100s", cons.multiply(sum)));
            }
            n++;
        }
        return cons.multiply(sum);
    }
    
    /**
     * The Dirichlet series summation formula for Zeta(s)
     * Summation = n/(n+1)^s - (n-s)/n^s
     * @param this_n the current index for the summation
     * @param s the user defined value for re(s) and ima(s)
     * @return the series summation
    */   
    public static Apcomplex zetaSum(long this_n, Apcomplex s) {
         Apfloat n = new Apfloat(this_n, PRECISION);
         Apfloat nPlusOne = n.add(ONE);
         
         Apcomplex firstTerm = n.divide(ApcomplexMath.pow(nPlusOne, s));
         Apcomplex secondTerm = n.subtract(s).divide(ApcomplexMath.pow
                                (n, s));
         return firstTerm.subtract(secondTerm);
    }
}
