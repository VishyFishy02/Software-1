import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of Natural Numbers.
 *
 * @author Vishal Kumar
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";
        NaturalNumber value = new NaturalNumber2(0);
        NaturalNumber zero = new NaturalNumber2(0);

        // statement to get past the first root node
        if (exp.label().equals("expression")) {
            value.add(evaluate(exp.child(0)));
        }
        // base case
        else if (exp.hasAttribute("value")) {
            NaturalNumber num = new NaturalNumber2();
            num.setFromInt(Integer.parseInt(exp.attributeValue("value")));
            value.add(num);
        }

        // recursive if else block to evaluate math operations
        else if (exp.label().equals("plus")) {
            NaturalNumber sum = value.newInstance();
            sum.add(evaluate(exp.child(0)));
            sum.add(evaluate(exp.child(1)));
            value.add(sum);
        } else if (exp.label().equals("minus")) {
            NaturalNumber difference = value.newInstance();
            NaturalNumber child0 = evaluate(exp.child(0));
            NaturalNumber child1 = evaluate(exp.child(1));
            // verify that NN subtract method precondition is fulfilled
            if (child0.compareTo(child1) >= 0) {
                difference.add(child0);
                difference.subtract(child1);
                value.add(difference);
            } else {
                components.utilities.Reporter
                        .fatalErrorToConsole("Error: child0 < child1");
            }
        } else if (exp.label().equals("times")) {
            NaturalNumber product = value.newInstance();
            product.add(evaluate(exp.child(0)));
            product.multiply(evaluate(exp.child(1)));
            value.add(product);
        } else {
            NaturalNumber quotient = value.newInstance();
            NaturalNumber child0 = evaluate(exp.child(0));
            NaturalNumber child1 = evaluate(exp.child(1));
            // verify that NN divide method precondition is fulfilled
            if (child1.compareTo(zero) > 0) {
                quotient.add(child0);
                quotient.divide(child1);
                value.add(quotient);
            } else {
                components.utilities.Reporter
                        .fatalErrorToConsole("Error: divide by zero");
            }
        }

        // return final value
        return value;
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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}