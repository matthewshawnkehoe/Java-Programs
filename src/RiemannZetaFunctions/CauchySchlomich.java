/**************************************************************************
**
**    Euler-Riemann Zeta Function           
**
**************************************************************************
**    Matthew Kehoe
**    06/20/2015
**
**    This program computes the value for Zeta(s) using the standard form
**    of Zeta(s), the Riemann functional equation, and the Cauchy-Schlomilch
**    transformation. A recursive method named riemannFuncForm has been created
**    to handle computations of Zeta(s). Simpson's method is used to 
**    approximate the definite integral calculated by the Cauchy-Schlomilch
**    transformation.
**************************************************************************/

import java.util.*;

public class CauchySchlomich {

    // Main method
    public static void main(String[] args) {
        ZetaMain();       
    }
    
    /**
	 * Calculates Zeta(s) for s in real.
    */
    public static void ZetaMain() {
        double s = 0;
        double start, stop, totalTime;
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the value of s inside the Riemann Zeta " + 
                "Function: ");
        try {
                s = scan.nextDouble();
        }
        catch (Exception e) {
           System.out.println("Please enter a valid number for s.");
        }
        start = System.currentTimeMillis();
        System.out.println("Value for the Zeta Function = " 
                    + riemannFuncForm(s));
        stop = System.currentTimeMillis();
        totalTime = (double) (stop-start) / 1000.0;
        System.out.println("Total time taken is " + totalTime + " seconds.");
    
    }
    
    /**
	 * The standard summation of an infinite series for the Zeta function.
         * <br> Stop the summation when the relative error is less than 2*10^-7
	 * @param s - the value of s.
	 * @return the value of Zeta(s) through an infinite series 
         * approximation.
    */
    public static double standardZeta(double s) {
        int n = 1;
        double currentSum = 0;
        double relativeError = 1;
        double error = 2.0 * 10E-7;
        double remainder;
              
        while (relativeError > error) {
            currentSum = Math.pow(n, -s) + currentSum;
            remainder = 1 / ((s-1)* Math.pow(n, (s-1)));
            relativeError =  remainder / currentSum;
            n++;
        }
        System.out.println("The number of terms summed was " + n + ".");
        return currentSum;
    }
    
    /**
	 * Approximation of the Gamma function by the Lanczos Approximation.
	 * @param s - the value of s.
	 * @return the approximate value of Gamma(s).
    */
    public static double lancGamma(double s){
                    double[] p = {0.99999999999980993, 676.5203681218851, 
                        -1259.1392167224028, 771.32342877765313, 
                        -176.61502916214059, 12.507343278686905,
                        -0.13857109526572012, 9.9843695780195716e-6, 
                        1.5056327351493116e-7};
                    
                    int g = 7;
                    
                    // Implements Euler's Reflection Formula.
                    if(s < 0.5) return Math.PI / (Math.sin(Math.PI * s)
                            *lancGamma(1-s));

                    s -= 1;
                    double a = p[0];
                    double t = s + g + 0.5;
                    for(int i = 1; i < p.length; i++){
                            a += p[i] / (s+i);
                    }

                    return Math.sqrt(2*Math.PI)*Math.pow(t, s+0.5)
                            *Math.exp(-t)*a;
            }
    
    /**
	 * Approximation of the Gamma function by the Stirling Approximation. 
	 * @param s - the value of s.
	 * @return the approximate value of Gamma(s).
    */
    public static double strlGamma(double s){
		return Math.sqrt(2 * Math.PI/s) *
                        Math.pow((s/Math.E), s);
	}
    
    /**
	 * Riemann's Functional Equation - Directly calculates the value of 
           Zeta(s).
         
         1. The first if statement handles when s < 0 and s is a multiple
            of 2k. These are trivial zeroes where Zeta(s) = 0.
         
         2. The second if statement handles when s < -170. This 
            implementation of Zeta(s) does not handle values below Zeta(-170).
            The specific cause is due to the double data type and the Stirling
            Approximation for Gamma(s).
         
         3. The third if statement handles when s < -141. The value of Zeta(s)
            is calculated by recursion through the functional equation and
            the Stirling Approximation for Gamma(s).
            
         4. The fourth if statement handles when s <= -1. The value of Zeta(s)
            is calculated by recursion through the functional equation and
            the Lanczos Approximation for Gamma(s).
    
         5. The fifth if statement handles the values of -1 < s < 0. Recursion 
            is used alongside an approximation through Simpson's method.
         
         6. The sixth if statement handles the values of 0 <= s < 2. Simpson's 
            method is used to numerically compute an approximation of the 
            definite integral.
    
         7. The last if statement directly calculates Zeta(s) through the 
            standard form where s > 1.
            
	 * @param s - the value of s.
	 * @return the value of Zeta(s)
    */
    public static double riemannFuncForm(double s) {
        double term = Math.pow(2, s)*Math.pow(Math.PI, s-1)*
            (Math.sin((Math.PI*s)/2));
    
        if(s <= -2 && s % 2 == 0)
            return 0;
        else if(s < -170) {
            System.out.println("This implementation of Zeta(s) does not " +
                    "support values where s < -170.");
            return term*standardZeta(1-s)*strlGamma(1-s);
        }
        else if(s <= -141)
            return term*standardZeta(1-s)*strlGamma(1-s);
        else if(s <= -1)
            return term*standardZeta(1-s)*lancGamma(1-s);
        else if (s > -1 && s < 0)
            return term*getSimpsonSum(1-s)*lancGamma(1-s);
        else if (s >= 0 && s < 2)
            return getSimpsonSum(s);
        else 
            return standardZeta(s);
    }
    
    /**
	 * Returns the function referenced inside the right hand side of the 
         * Cauchy-Schlomilch transformation for Zeta(s).
	 * @param s - the value of s.
	 * @return the value of the function inside the integrand.
    */
    public static double function(double x, double s) {
        double sech = 1 / Math.cosh(x); // Hyperbolic cosecant
        double squared = Math.pow(sech, 2);
        return ((Math.pow(x, s)) * squared);
    }
    
    /**
	 * Simpson's rule for evaluating a definite integral. 
	 * @param a - the start of the integral approximation.
         * @param b - the end of the integral approximation.
         * @param s - the value of s.
         * @param n - initially the number of terms to sum, should be even.
	 * @return the integral approximation through Simpson's method.
    */
    public static double SimpsonsRule(double a, double b, double s, int n) {
        double simpson, dx, x, sum4x, sum2x;

        dx = (b-a) / n;
        sum4x = 0.0;
        sum2x = 0.0;
        
        // 4/3 terms
        for (int i = 1; i < n; i += 2) {
            x = a + i * dx;
            sum4x += function(x,s);
        }
        
        // 2/3 terms
        for (int i = 2; i < n-1; i += 2) {
            x = a + i * dx;
            sum2x += function(x,s);
        }
        
        // Compute the integral approximation.
        simpson = function(a,s) + function(a,b);
        simpson = (dx / 3)*(simpson + 4 * sum4x + 2 * sum2x);
        return simpson;
    }
    
    /**
	 * Handles the error inside Simpson's rule for for 
         * f(x) = t^s * sech(t)^2. The integration is done from 0 to 100.
         * Stop Simspson's Method when the relative error is less than 
         * 2 * 10^-7.
	 * @param a - the start of the integral approximation.
         * @param b - the end of the integral approximation.
         * @param s - the value of s.
         * @param n - initially the number of terms to sum, should be even.
	 * @return the integral approximation through Simpson's method.
    */
    public static double SimpsonError(double a, double b, double s, int n)
    {
        double futureVal;
        double absError = 1.0;
        double finalValueOfN;
        double numberOfIterations = 0.0;
        double currentVal = SimpsonsRule(a,b,s,n);

        while (absError / currentVal > 0.000001) {
            n = 2*n;
            futureVal = SimpsonsRule(a,b,s,n);
            absError = Math.abs(futureVal - currentVal) / 15;
            currentVal = futureVal;
        }
        
        // Find the number of iterations. N starts at 8 and doubles 
        // every iteration.
        finalValueOfN = n / 8;
        while (finalValueOfN % 2 == 0) {
            finalValueOfN = finalValueOfN / 2;
            numberOfIterations++;
        }
        System.out.println("The number of iterations is " 
                + numberOfIterations + ".");
        return currentVal;
    }

    /**
	 * Returns an approximate sum of Zeta(s) through Simpson's rule.
         * @param s - the value of s.
	 * @return the approximate sum by Simpson's rule.
    */
    public static double getSimpsonSum(double s) {
        double constant = Math.pow(2, (2*s)-1) / (((Math.pow(2, s)) -2)*
                (lancGamma(1+s)));
        double seriesApprox = Math.pow(1/Math.cosh(1.0), 2) + 2/3 + 100/189;
        System.out.println("Did Simpson's Method.");
        if (s > 0 && s < 1)
            return seriesApprox*SimpsonError(0, 100, s, 8);
        else
            return constant*SimpsonError(0, 100, s, 8);
    }
}
