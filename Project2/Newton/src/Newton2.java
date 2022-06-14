import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * A program that computes the square root of a number using Newton Iteration
 *
 * @author VishalKumar
 *
 */
public final class Newton2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton2() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number (or 0) to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        double r = x;
        // exception for when user wants to calculate the root of 0
        if (x == 0.0) {
            return 0.0;
        } else {
            while (!(((r * r) - x) / x < (.001 * .001))) {
                r = ((r + (x / r)) / 2);
            }
        }
        return r;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        // boolean to store whether or not user wants to do another round
        boolean another = false;

        // compute if user would like to calculate another square root
        out.print("Would You like to calculate a square root? (y/n) ");
        char answer = in.nextLine().charAt(0);

        if (answer == 'y') {
            another = !another;
        }

        // loop until user no longer wants to calculate a square root
        while (another) {
            out.print("Enter a positive double (#.##): ");
            double num = in.nextDouble();
            double rootNum = sqrt(num);
            out.println("The square root of " + num + " is " + rootNum);

            out.print(
                    "Would You like to calculate another square root? (y/n): ");
            answer = in.nextLine().charAt(0);

            if (answer != 'y') {
                another = !another;
            }

        }
        out.println("Goodbye!");
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
