package song;


public class DiGui {

    public static void main(String[] args) {
       int a = JC(3);
       System.out.println(a);
    }
    
    public static int JC(int n){
        if(n==1){
            return 1;
        }else {
            return n*JC(n-1);
        }
    }
}
