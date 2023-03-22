/**************************************************************************
**
**    Abel-Plana Formula for the Zeta Function           
**
**************************************************************************
**    Axion004
**    08/16/2015
**
**    This program computes the value for Zeta(z) using a definite integral
**    approximation through the Abel-Plana formula. The Abel-Plana formula
**    can be shown to approximate the value for Zeta(s) through a definite
**    integral. The integral approximation is handled through the Composite 
**    Simpson's Rule known as Adaptive Quadrature.
**************************************************************************/

import java.util.*;
import java.math.*;


public class AbelPlana extends Complex {
    private static MathContext MC = new MathContext(512, 
            RoundingMode.HALF_EVEN);
    public static void main(String[] args) {
        AbelMain();
    }

    // Main method
    public static void AbelMain() {
        double re = 0, im = 0;
        double start, stop, totalTime;
        Scanner scan = new Scanner(System.in);
        System.out.println("Calculation of the Riemann Zeta " + 
                "Function in the form Zeta(s) = a + ib.");
        System.out.println();
        System.out.print("Enter the value of [a] inside the Riemann Zeta " + 
                "Function: ");
        try {
                re = scan.nextDouble();
        }
        catch (Exception e) {
           System.out.println("Please enter a valid number for a.");
        }
        System.out.print("Enter the value of [b] inside the Riemann Zeta " + 
                "Function: ");
        try {
                im = scan.nextDouble();
        }
        catch (Exception e) {
           System.out.println("Please enter a valid number for b.");
        }
        start = System.currentTimeMillis();
        Complex z = new Complex(new BigDecimal(re), new BigDecimal(im));
        System.out.println("The value for Zeta(s) is " + AbelPlana(z));
        stop = System.currentTimeMillis();
        totalTime = (double) (stop-start) / 1000.0;
        System.out.println("Total time taken is " + totalTime + " seconds.");
    }

    /**
     * The definite integral for Zeta(z) in the Abel-Plana formula.
         * <br> Numerator = Sin(z * arctan(t))
         * <br> Denominator = (1 + t^2)^(z/2) * (e^(2*pi*t) - 1)
     * @param t - the value of t passed into the integrand.
         * @param z - The complex value of z = a + i*b
     * @return the value of the complex function.
    */   
    public static Complex f(double t, Complex z) {
        Complex num = (z.multiply(Math.atan(t))).sin();
        Complex D1 = new Complex(1 + t*t).pow(z.divide(TWO));
        Complex D2 = new Complex(Math.pow(Math.E, 2.0*Math.PI*t) - 1.0);
        Complex den = D1.multiply(D2);
        return num.divide(den, MC);
    }

    /**
     * Adaptive quadrature - See http://www.mathworks.com/moler/quad.pdf
     * @param a - the lower bound of integration.
         * @param b - the upper bound of integration.
         * @param z - The complex value of z = a + i*b
     * @return the approximate numerical value of the integral.
    */  
    public static Complex adaptiveQuad(double a, double b, Complex z) {
        double EPSILON = 1E-13;
        double step = b - a;
        double c = (a + b) / 2.0;
        double d = (a + c) / 2.0;
        double e = (b + c) / 2.0;        

        Complex S1 = (f(a, z).add(f(c, z).multiply(FOUR)).add(f(b, z))).
                multiply(step / 6.0);
        Complex S2 = (f(a, z).add(f(d, z).multiply(FOUR)).add(f(c, z).multiply
                (TWO)).add(f(e, z).multiply(FOUR)).add(f(b, z))).multiply
                (step / 12.0);
        Complex result = (S2.subtract(S1)).divide(FIFTEEN, MC);

        if(S2.subtract(S1).mod() <= EPSILON)
            return S2.add(result);
        else
            return adaptiveQuad(a, c, z).add(adaptiveQuad(c, b, z));
    }

    /**
     * The definite integral for Zeta(z) in the Abel-Plana formula.
         * <br> value =  1/2 + 1/(z-1) + 2 * Integral
         * @param z - The complex value of z = a + i*b
     * @return the value of Zeta(z) through value and the
         * quadrature approximation.
    */   
    public static Complex AbelPlana(Complex z) {
        Complex C1 = ONEHALF.add(ONE.divide(z.subtract(ONE), MC));
        Complex C2  =  TWO.multiply(adaptiveQuad(1E-16, 100.0, z));
        if ( z.real().doubleValue() == 0 && z.imag().doubleValue() == 0)
            return new Complex(0.0, 0.0);
        else
            return C1.add(C2);
    }

}
