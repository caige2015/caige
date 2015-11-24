package song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//硬币算法，有25,10,1三种硬币，输入n，求最少的硬币数
public class Coin {

    public static int getNums(int n){
   
        List<Integer> list = new ArrayList<Integer>();
        
        for(int i=0;i<1000;i++){
            for(int j=0; j < 1000; j++) {
                for(int z=0; z < 1000; z++) {
                    if(25*i+10*j+z==n){
                        list.add(i+j+z);
                    }
                }
            }
        }
        
        Collections.sort(list);
        return list.get(0);
    }
    
    public static void main(String[] args) {
       int a = getNums(34);
       System.out.println("--->" + a);
    }
}
