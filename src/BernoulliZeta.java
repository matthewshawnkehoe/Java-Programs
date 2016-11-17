/**************************************************************************
**
**    Bernoulli numbers to calculate of Zeta(2N)
**
**************************************************************************
**    Matthew Kehoe
**    09/15/2015
**
**    The Bernoulli numbers have multiple interesting relationships with the
**    Zeta function. One of these relationships, originally found by Euler,
**    states that Zeta(2n) = -1^(n+1) * B_2n * (2*pi)^2n / 2 * (2n)!. The 
**    program is designed to calculate even values for the Zeta function.
**************************************************************************/


import java.math.BigInteger;
import org.apache.commons.math3.fraction.BigFraction;

public class BernoulliZeta {
    
    // Main method
    public static void main(String[] args) {        
        System.out.println("The value of Zeta(2n) in the form \u03A0^2 /"
                + " fraction.");
        System.out.println();
        printZeta2N();
        
        // Uncomment to print out Zeta(-n)
        /* System.out.println("The value of Zeta(-N) in BigFraction form.");
         System.out.println();
         printZetaNegN();*/
    }
    
    /**
	 * Prints the values of Zeta(2n) in the format of 
         * Numerator = BigInteger * pi^2n
         * Denominator = BigInteger
    */
    public static void printZeta2N(){
        String pi = "\u03A0";
        
        for (int i = 1; i < 1000; i++) {     
            
            if( evenZeta(i).getNumerator().doubleValue() > 1)
                System.out.println("Zeta of " + 2*i + " = " + 
                        evenZeta(i).getNumerator() + " " + pi + "^" + 2*i + 
                        " / " + evenZeta(i).getDenominator());
            else
                System.out.println("Zeta of " + 2*i + " = " + pi + 
                        "^" + 2*i + " / " + evenZeta(i).getDenominator());
        } 
    }
    
    /**
	 * Prints the values of Zeta(-n) in the format of 
         * Numerator = BigFraction Bern_n+1
         * Denominator = BigInteger n+1
    */
    public static void printZetaNegN(){
        for (int i = 1; i < 1000; i++) {
            System.out.println("Zeta of " + -i + " = " 
                + oddZeta(i));
        } 
    }
    
    
    /**
	 * BigFraction representation of the Zeta value for Zeta(2N). 
         * @param n the int value for Zeta(2n)
         * Numerator = (-1)^n+1 * B_2n * (2pi)^2n
         * Denominator = 2*(2n)!
	 * @return the value of Zeta(2n)
    */
    public static BigFraction evenZeta(int n){
        
        BigInteger negOne = BigInteger.valueOf(-1);
        BigInteger two = BigInteger.valueOf(2);
        BigInteger j = BigInteger.valueOf(n);
        
        // Sign
        BigInteger sign = negOne.pow(n+1);
        
        // Numerator
        BigFraction bernNum = bernoulli(2*n);
        BigFraction num = bernNum.multiply(two.pow(2*n)).multiply(sign);
        
        // Denominator
        BigInteger twoN = two.multiply(BigInteger.valueOf(n));
        BigInteger den = two.multiply(factorial(twoN));
        
        BigFraction zeta2N = num.divide(den);
        return zeta2N;    
    }
    
    /**
	 * BigFraction representation of the Zeta value for Zeta(-n). 
         * @param n the int value for Zeta(-n)
         * - B_n+1/(n+1)
	 * @return the value of Zeta(-n)
    */
    public static BigFraction oddZeta(int n){
        
        BigInteger negOne = BigInteger.valueOf(-1);
        BigFraction bernNum = bernoulli(n+1);
        BigInteger j = BigInteger.valueOf(n+1);
        return bernNum.divide(j).multiply(negOne);
    }

    /**
     * The factorial function returning a BigInteger
     * @param n the BigInteger to pass through for the factorial(n)
     * @return the factorial of n
    */ 
    public static BigInteger factorial(BigInteger n) {
        BigInteger result = BigInteger.ONE;

        while (!n.equals(BigInteger.ZERO)) {
            result = result.multiply(n);
            n = n.subtract(BigInteger.ONE);
        }

        return result;
    }

    /* Generates the Bernoulli number, B_n, by a double sum.
     * @param n The index of the Bernoulli number.
     * @return The Bernoulli number at n.
     */
    private static BigFraction bernoulli(int n) {
        BigFraction result = BigFraction.ZERO;
        for (int k = 0; k <= n; k++) {
            BigFraction jSum = BigFraction.ZERO;
            BigInteger bInt = BigInteger.ONE;
            for (int j = 0; j <= k; j++) {
                BigInteger jPowN = (new BigInteger("" + j))
                        .pow(n);
                if (j % 2 == 0) {
                    jSum = jSum.add(bInt.multiply(jPowN));
                } 
                else {
                    jSum = jSum.subtract(bInt.multiply(jPowN));
                }

                /* updates binomial(k,j) recursively
                 */
                bInt = bInt.multiply(new BigInteger("" + (k - j))).
                        divide(new BigInteger("" + (j + 1)));
            }
            result = result.add(jSum.divide(new BigInteger("" + (k + 1)))
            );
        }
        return result;
    }
}
