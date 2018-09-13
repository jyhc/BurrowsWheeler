import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RedBlackBST;

public class BurrowsWheeler {
    // apply Burrows-Wheeler transform, reading from standard input and writing to standard output
    public static void transform() {
        String s = BinaryStdIn.readString();
        CircularSuffixArray csa = new CircularSuffixArray(s);
        StringBuilder builder = new StringBuilder(csa.length());
        char temp;
        for(int i = 0; i < csa.length(); i++) {
            if(csa.index(i) == 0) BinaryStdOut.write(i);
            if(csa.index(i)-1 < 0) temp = s.charAt(s.length()-1);
            else temp = s.charAt(csa.index(i)-1);
            builder.append(temp);
        }
        BinaryStdOut.write(builder.toString());
        BinaryStdOut.flush();
        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler inverse transform, reading from standard input and writing to standard output
    public static void inverseTransform() {
        int first = BinaryStdIn.readInt();
        String s = BinaryStdIn.readString();
//        BinaryStdOut.write(s);
//        BinaryStdOut.write(first);
        
        String firstCol = keyIndexCount(s);
        int[] next = new int[s.length()];
        RedBlackBST<Character, Queue<Integer>> t = new RedBlackBST<>();
        for(int i = 0; i < s.length(); i++) {
            if(!t.contains(s.charAt(i))) {
                Queue<Integer> q = new Queue<>();
                q.enqueue(i);
                t.put(s.charAt(i), q);
            } else {
                Queue<Integer> q = t.get(s.charAt(i));
                q.enqueue(i);
            }
        }
        
        for(int i = 0; i < s.length(); i++) {
            Queue<Integer> val = t.get(firstCol.charAt(i));
            next[i] = val.dequeue();
        }
        
        StringBuilder original = new StringBuilder();
        int temp = first;
        while(original.length() < s.length()) {
            original.append(firstCol.charAt(temp));
            temp = next[temp];
        }
        BinaryStdOut.write(original.toString());
        
        BinaryStdOut.flush();
        BinaryStdOut.close();
    }
    
    private static String keyIndexCount(String s) {
        int R = 256; //ascii extended is 256
        int N = s.length();
        int[] count = new int[R+1];
        StringBuilder aux = new StringBuilder(s);//more efficient as data structure is mutable
        for (int i = 0; i < N; i++) count[s.charAt(i)+1]++;
        for (int r = 0; r < R; r++) count[r+1] += count[r];
        for (int i = 0; i < N; i++) aux.setCharAt(count[s.charAt(i)]++, s.charAt(i));
        return aux.toString();
    }

    // if args[0] is '-', apply Burrows-Wheeler transform
    // if args[0] is '+', apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if(args[0].contentEquals("-")) {
            BurrowsWheeler.transform();
        }
        if(args[0].contentEquals("+")){
            BurrowsWheeler.inverseTransform();
        }
    }
}