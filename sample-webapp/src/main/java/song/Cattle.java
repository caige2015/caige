package song;

/*有一头母牛，它每年年初生一头小母牛。每头小母牛从第四个年头开始，每年年初也生一头小母牛。
编写程序，输入一个正整数N，求在第N年的时候，共有多少头母牛？*/
public class Cattle {

    public static void main(String[] args){
        int a = getNums(4);
        System.out.println(a);
    }
    
    public static int getNums(int n){
        if(n==0){
            return 1;
        }else if(n==1){
            return 2;
        }else if(n==2) {
            return 3;
        }else if(n==3){
            return 4;
        }else {
            return getNums(n-3)+getNums(n-1);
        }
    }
}
