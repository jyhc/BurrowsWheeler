import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BurrowsWheeler {
    // apply Burrows-Wheeler transform, reading from standard input and writing to standard output
    public static void transform() {
        String s = StdIn.readString();
        CircularSuffixArray csa = new CircularSuffixArray(s);
        StringBuilder builder = new StringBuilder(csa.length());
        for(int i = 0; i < csa.length(); i++) {
            if(csa.index(i) == 0) StdOut.print(i);
            StdOut.print( + "\n");
        }
    }

    // apply Burrows-Wheeler inverse transform, reading from standard input and writing to standard output
    public static void inverseTransform() {
        
    }

    // if args[0] is '-', apply Burrows-Wheeler transform
    // if args[0] is '+', apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        
    }
}