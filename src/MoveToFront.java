import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        String s = BinaryStdIn.readString();
        int[] ascii = new int[256];//extended ascii
        for(int i = 0; i < 256; i++) {
            ascii[i] = i;
        }
        for(int i = 0; i < s.length(); i++) {
            BinaryStdOut.write((char) ascii[s.charAt(i)]);
            
            for(int j = 0; j < 256; j++) {
                int count = 0;
                if(ascii[j] < ascii[s.charAt(i)]) {
                    ascii[j] += 1;
                    count++;
                }
                if(count == ascii[s.charAt(i)]) break;
            }
            ascii[s.charAt(i)] = 0;//set to first position
        }
        BinaryStdOut.flush();
        BinaryStdOut.close();
    }
    
    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        String s = BinaryStdIn.readString();
        int[] ascii = new int[256];//extended ascii
        for(int i = 0; i < 256; i++) {
            ascii[i] = i;
        }
        for(int i = 0; i < s.length(); i++) {
            BinaryStdOut.write((char) ascii[s.charAt(i)]);
            int temp = ascii[s.charAt(i)];
            for(int j = s.charAt(i); j != 0; j--) {                
                ascii[j] = ascii[j-1];
            }
            ascii[0] = temp;//set to first position
        }
        BinaryStdOut.flush();
        BinaryStdOut.close();
    }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args) {
        if(args[0].contentEquals("-")) {
            MoveToFront.encode();
        }
        if(args[0].contentEquals("+")){
            MoveToFront.decode();
        }
    }
}