import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BurrowsWheeler {
    // apply Burrows-Wheeler transform, reading from standard input and writing to standard output
    public static void transform() {
        String s = StdIn.readString();
        CircularSuffixArray csa = new CircularSuffixArray(s);
        StringBuilder builder = new StringBuilder(csa.length());
        char temp;
        for(int i = 0; i < csa.length(); i++) {
            if(csa.index(i) == 0) StdOut.print(i+"\n");
            if(csa.index(i)-1 < 0) temp = s.charAt(s.length()-1);
            else temp = s.charAt(csa.index(i)-1);
            builder.append(temp);
        }
        StdOut.print(builder.toString());
    }

    // apply Burrows-Wheeler inverse transform, reading from standard input and writing to standard output
    public static void inverseTransform() {
        
    }

    // if args[0] is '-', apply Burrows-Wheeler transform
    // if args[0] is '+', apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if(args[0].contentEquals("-")) {
            BurrowsWheeler.transform();
        }
    }
}