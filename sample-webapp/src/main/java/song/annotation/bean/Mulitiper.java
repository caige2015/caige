package song.annotation.bean;

import song.annotation.common.ExtracInterface;

@ExtracInterface("IMultipier")
public class Mulitiper {

    public int multiply(int x,int y){
        int total = 0;
        for(int i = 0; i < x; i++){
            total = add(total, y);
        }
        return total;
    }
    private int add(int x, int y){return x + y;}
    
    public static void main(String[] args) {
        Mulitiper m = new Mulitiper();
        System.out.println("11*16= "+m.multiply(11, 16));
    }
}
