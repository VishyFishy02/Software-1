import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * A program that takes four personal numbers from a user as well as a
 * mathematical constant and approximates the constant using the deJager formula
 *
 * @author VishalKumar
 *
 */
public final class ABCDGuesser2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser2() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        double num = 0;
        // ask user for input
        out.print("Enter a positive real number: ");
        String input = in.nextLine();

        // verify that input is positive and real
        while (!(FormatChecker.canParseDouble(input))) {
            out.print("Please enter a positive real number: ");
            input = in.nextLine();
        }
        while (Double.parseDouble(input) <= 0) {
            out.print("Please enter a POSITIVE real number: ");
            input = in.nextLine();
        }

        //convert input to double and return value
        num = Double.parseDouble(input);
        return num;

    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        double num = 0;
        // ask user for input
        out.print("Enter a positive real number not equal to 1.0: ");
        String input = in.nextLine();

        // verify that input is positive and real and not equal to 1.0
        while (!(FormatChecker.canParseDouble(input))) {
            out.print("Please enter a positive real number not equal to 1.0: ");
            input = in.nextLine();
        }
        while (Double.parseDouble(input) <= 0
                || Math.abs(Double.parseDouble(input) - 1) < .0001) {
            out.print("Please enter a POSITIVE real number NOT equal to 1.0: ");
            input = in.nextLine();
        }

        //convert input to double and return value
        num = Double.parseDouble(input);
        return num;

    }

    private static void printFinalResults(double w, double x, double y,
            double z, double aVal, double bVal, double cVal, double dVal,
            double error, SimpleWriter out, SimpleReader in) {
        out.println("\nThe exponent of " + w + " is " + aVal);
        out.println("The exponent of " + x + " is " + bVal);
        out.println("The exponent of " + y + " is " + cVal);
        out.println("The exponent of " + z + " is " + dVal);
        out.println("Using the charming theory the approximate value is: "
                + (Math.pow(w, aVal)) * (Math.pow(x, bVal))
                        * (Math.pow(y, cVal)) * (Math.pow(z, dVal)));
        out.print("The error is: ");
        out.print(error, 2, false);
        out.print("%");
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

        // get constant from the user
        out.println(
                "Enter a mathmatical constant that you want to approximate");
        double constant = getPositiveDouble(in, out);

        // get four numbers from the user
        out.println("\nEnter four numbers");
        double w = getPositiveDoubleNotOne(in, out);
        double x = getPositiveDoubleNotOne(in, out);
        double y = getPositiveDoubleNotOne(in, out);
        double z = getPositiveDoubleNotOne(in, out);

        // array of 17 charming theory numbers
        double[] charmNums = { -5, -4, -3, -2, -1, -1.0 / 2, -1.0 / 3, -1.0 / 4,
                0, 1.0 / 4, 1.0 / 3, 1.0 / 2, 1, 2, 3, 4, 5 };
        int length = charmNums.length;

        // calculate the the difference between initial approximate and constant
        double difference = Math.abs(((w * charmNums[0]) * (x * charmNums[0])
                * (y * charmNums[0]) * (z * charmNums[0]) - constant));

        // initialize exponent indexes, exponent values of charming theory
        double aVal = 0, bVal = 0, cVal = 0, dVal = 0;

        // loop until end of the charmNums array is reached
        for (int d = 0; d < length; d++) {
            for (int c = 0; c < length; c++) {
                for (int b = 0; b < length; b++) {
                    for (int a = 0; a < length; a++) {

                        // calculate how far off new difference is from the constant
                        double currentDiff = Math
                                .abs((Math.pow(w, charmNums[a]))
                                        * (Math.pow(x, charmNums[b]))
                                        * (Math.pow(y, charmNums[c]))
                                        * (Math.pow(z, charmNums[d]))
                                        - constant);

                        // assign new values if current diff is closer than difference
                        if (currentDiff < difference) {
                            difference = currentDiff;
                            aVal = charmNums[a];
                            bVal = charmNums[b];
                            cVal = charmNums[c];
                            dVal = charmNums[d];
                        }
                    }
                }
            }
        }

        // calculate error and print final results
        double error = (difference / constant) * 100;
        printFinalResults(w, x, y, z, aVal, bVal, cVal, dVal, error, out, in);

        // close input and output streams
        in.close();
        out.close();
    }

}
