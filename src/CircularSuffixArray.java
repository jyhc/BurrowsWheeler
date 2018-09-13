import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class CircularSuffixArray {
    
    private final int length;
    private int[] index;
    
    public CircularSuffixArray(String s) {   // circular suffix array of s
        //don't need to make defensive copy as strings are immutable        
        if(s == null) throw new java.lang.IllegalArgumentException();
        length = s.length();
        index = new int[length];
        for(int i = 0; i < length ; i++) {
            index[i] = i;
        }
        StdRandom.shuffle(index);
        threeWaySort(s, index);
    }
    
    //three way sort code based on 3 way string sort from course adapted to sorting with index array
    private void threeWaySort(String a, int[] index) { 
        threeWaySort(index, a, 0, a.length() - 1, 0); 
    }
    
    private void threeWaySort(int[] index, String s, int lo, int hi, int d) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        int v = charAt(s, index[lo], d);
        int i = lo + 1;
        while (i <= gt)
        {
            int t = charAt(s, index[i], d);
            if (t < v) exch(index, lt++, i++);
            else if (t > v) exch(index, i, gt--);
            else i++;
        }
        threeWaySort(index, s, lo, lt-1, d);
        threeWaySort(index, s, lt, gt, d+1);
        threeWaySort(index, s, gt+1, hi, d);
    }
    
    private int charAt(String s, int start, int d) { 
        if (start + d >= s.length()) return s.charAt(start + d - s.length());
        return s.charAt(start + d);
    }
    
    private void exch(int[] index, int i, int j) {
        int temp = index[i];
        index[i] = index[j];
        index[j] = temp;
    }
           
    public int length() {    // length of s
        return length;
    }
           
    public int index(int i) {    // returns index of ith sorted suffix
        if(i < 0 && i > length - 1) throw new java.lang.IllegalArgumentException();
        return index[i];
    }
      
    public static void main(String[] args) { // unit testing (required)
        In in = new In("testing\\abra.txt");
        String s = in.readAll();
        CircularSuffixArray csa = new CircularSuffixArray(s);
        for(int i = 0; i < csa.length(); i++) {
            StdOut.print(csa.index(i)+"\n");
        }
    }
       
}