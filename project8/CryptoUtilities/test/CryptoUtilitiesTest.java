import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Put your name here
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */

    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_50_200() {
        NaturalNumber n = new NaturalNumber2(50);
        NaturalNumber nExpected = new NaturalNumber2(50);
        NaturalNumber m = new NaturalNumber2(200);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isEven
     */

    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    @Test
    public void testIsEven_extremeCase() {
        NaturalNumber n = new NaturalNumber2(
                "239472983230420394820394820923947298323");
        NaturalNumber nExpected = new NaturalNumber2(
                "239472983230420394820394820923947298323");
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    @Test
    public void testIsEven_normalCase() {
        NaturalNumber n = new NaturalNumber2("68");
        NaturalNumber nExpected = new NaturalNumber2("68");
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    /*
     * Tests of powerMod
     */

    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_extremeCase() {
        NaturalNumber n = new NaturalNumber2(100);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(67);
        NaturalNumber pExpected = new NaturalNumber2(67);
        NaturalNumber m = new NaturalNumber2(9);
        NaturalNumber mExpected = new NaturalNumber2(9);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testIsWitnessToCompositeness1() {
        NaturalNumber n = new NaturalNumber2(5);
        NaturalNumber nExpected = new NaturalNumber2(5);
        NaturalNumber w = new NaturalNumber2(3);
        NaturalNumber wExpected = new NaturalNumber2(3);
        boolean expectedAnswer = false;
        assertEquals(CryptoUtilities.isWitnessToCompositeness(w, n),
                expectedAnswer);
    }

    @Test
    public void testIsWitnessToCompositeness2() {
        NaturalNumber n = new NaturalNumber2(20);
        NaturalNumber w = new NaturalNumber2(4);
        boolean expectedAnswer = true;
        assertEquals(CryptoUtilities.isWitnessToCompositeness(w, n),
                expectedAnswer);
    }

    @Test
    public void testIsWitnessToCompositeness3() {
        NaturalNumber n = new NaturalNumber2(23);
        NaturalNumber w = new NaturalNumber2(15);
        boolean expectedAnswer = false;
        assertEquals(CryptoUtilities.isWitnessToCompositeness(w, n),
                expectedAnswer);
    }

    @Test
    public void testIsPrime2_1() {
        NaturalNumber n = new NaturalNumber2(100);
        boolean expectedAnswer = false;
        assertEquals(CryptoUtilities.isPrime2(n), expectedAnswer);
    }

    @Test
    public void testIsPrime2_2() {
        NaturalNumber n = new NaturalNumber2(5);
        boolean expectedAnswer = true;
        assertEquals(CryptoUtilities.isPrime2(n), expectedAnswer);
    }

    @Test
    public void testIsPrime2_3() {
        NaturalNumber n = new NaturalNumber2(26);
        boolean expectedAnswer = false;
        assertEquals(CryptoUtilities.isPrime2(n), expectedAnswer);
    }

    @Test
    public void testGenerateNextLikelyPrime_1() {
        NaturalNumber n = new NaturalNumber2(5);
        NaturalNumber expectedAnswer = new NaturalNumber2(7);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(n, expectedAnswer);
    }

    @Test
    public void testGenerateNextLikelyPrime_2() {
        NaturalNumber n = new NaturalNumber2(29);
        NaturalNumber expectedAnswer = new NaturalNumber2(31);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(n, expectedAnswer);
    }

    @Test
    public void testGenerateNextLikelyPrime_3() {
        NaturalNumber n = new NaturalNumber2(137);
        NaturalNumber expectedAnswer = new NaturalNumber2(139);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(n, expectedAnswer);
    }

}
