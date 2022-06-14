import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program with implementation of {@code NaturalNumber} secondary operation
 * {@code root} implemented as static method.
 *
 * @author VishalKumar
 *
 */
public final class NaturalNumberRoot {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private NaturalNumberRoot() {
    }

    /**
     * Updates {@code n} to the {@code r}-th root of its incoming value.
     *
     * @param n
     *            the number whose root to compute
     * @param r
     *            root
     * @updates n
     * @requires r >= 2
     * @ensures n ^ (r) <= #n < (n + 1) ^ (r)
     */
    public static void root(NaturalNumber n, int r) {
        assert n != null : "Violation of: n is  not null";
        assert r >= 2 : "Violation of: r >= 2";

        // declare NN constants
        NaturalNumber one = new NaturalNumber2(1);
        NaturalNumber two = new NaturalNumber2(2);
        NaturalNumber zero = new NaturalNumber2(0);

        // set hi to n + 1
        NaturalNumber hi = n.newInstance();
        hi.add(n);
        hi.add(one);

        // set low to 0
        NaturalNumber lo = n.newInstance();
        lo.add(zero);

        // make guess variables
        NaturalNumber guess = n.newInstance();
        guess.add(hi);
        guess.add(lo);
        guess.divide(two);
        // variable that will hold guess to the power of r
        NaturalNumber guessExp = guess.newInstance();

        // condition variable for while loop
        NaturalNumber condition = n.newInstance();
        condition.add(hi);
        condition.subtract(lo);

        // loop until hi is no longer greater than lo
        while (condition.compareTo(one) > 0) {
            // assign guessExp to the power of R
            guessExp.clear();
            guessExp.add(guess);
            guessExp.power(r);
            // if block checks to see if guess is equal to n
            if (n.compareTo(guessExp) >= 0) {
                lo.transferFrom(guess);
            } else {
                hi.transferFrom(guess);
            }
            // assign guess to (hi + lo) / 2
            guess.add(hi);
            guess.add(lo);
            guess.divide(two);
            // assign condition to hi - lo
            condition.clear();
            condition.add(hi);
            condition.subtract(lo);

        }

        // set n to guess
        n.transferFrom(guess);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        final String[] numbers = { "0", "1", "13", "1024", "189943527", "0",
                "1", "13", "4096", "189943527", "0", "1", "13", "1024",
                "189943527", "82", "82", "82", "82", "82", "9", "27", "81",
                "243", "143489073", "2147483647", "2147483648",
                "9223372036854775807", "9223372036854775808",
                "618970019642690137449562111",
                "162259276829213363391578010288127",
                "170141183460469231731687303715884105727" };
        final int[] roots = { 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 15, 15, 15, 15, 15,
                2, 3, 4, 5, 15, 2, 3, 4, 5, 15, 2, 2, 3, 3, 4, 5, 6 };
        final String[] results = { "0", "1", "3", "32", "13782", "0", "1", "2",
                "16", "574", "0", "1", "1", "1", "3", "9", "4", "3", "2", "1",
                "3", "3", "3", "3", "3", "46340", "46340", "2097151", "2097152",
                "4987896", "2767208", "2353973" };

        for (int i = 0; i < numbers.length; i++) {
            NaturalNumber n = new NaturalNumber2(numbers[i]);
            NaturalNumber r = new NaturalNumber2(results[i]);
            root(n, roots[i]);
            if (n.equals(r)) {
                out.println("Test " + (i + 1) + " passed: root(" + numbers[i]
                        + ", " + roots[i] + ") = " + results[i]);
            } else {
                out.println("*** Test " + (i + 1) + " failed: root("
                        + numbers[i] + ", " + roots[i] + ") expected <"
                        + results[i] + "> but was <" + n + ">");
            }
        }

        out.close();
    }

}