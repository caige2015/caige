package song;

import java.util.Arrays;
import java.util.Random;

/**
 * 双色球算法
 * @author caige
 *
 */
public class Lettery {

    private static String[] redBalls = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14",  
        "15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33"};
    
    public static String[] getShuan() {  
        String[] doubleColorBall=new String[7];  //存放生成的号码
        boolean[] flag=new boolean[redBalls.length];  //new一个boolean数组，默认值全都为false，如果有被随机到的数就制成true，下次就不在产生了，避免重复
        //System.out.println(Arrays.toString(flag));  
        Random random=new Random();  
        for (int i = 0;i < doubleColorBall.length-1;i++) {  
            while (true) {  
                int index=random.nextInt(redBalls.length);  
                if(!flag[index]){  
                    doubleColorBall[i]=redBalls[index];  
                    flag[index]=true;  
                    break;  
                }  
            }  
        }  
        //先给最后一个元素赋个最大值，避免在排序的时候出现空指针  
        doubleColorBall[doubleColorBall.length-1]=redBalls[redBalls.length-1];  
        Arrays.sort(doubleColorBall);  //排序
        doubleColorBall[doubleColorBall.length-1]=redBalls[random.nextInt(16)];  //排序过后生成最后一个蓝球
        return doubleColorBall;  
    }  
    
    public static void main(String[] args) {
        String[] strs = getShuan();
        for(int i=0; i < strs.length; i++) {
            System.out.print(strs[i]+" ");
        }
    }
}
