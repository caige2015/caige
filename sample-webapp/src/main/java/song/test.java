package song;

import java.util.Arrays;

public class test {

    public static void main(String[] args) {
        int[] arra={67, 56, 324, 59, 3, 56, 6};
        Arrays.sort(arra);
        for(int n=0; n < arra.length; n++) {
            System.out.print(arra[n]+" ");
        }
    }
}