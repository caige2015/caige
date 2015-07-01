package song;


public class Bisearch {

    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7,8};
        int local = getLocaltion(array, 5);
        if(local == -1){
            System.out.println("没找到！");
        }else {
            System.out.println("在第" + local + "个位置！");
        }
    }
    
    public static int getLocaltion(int[] list, int num){
        int low = 0;
        int high = list.length-1;
        int middle = (low + high)/2;
        while(low <= high){
            middle = (low + high)/2;
            if(list[middle] == num){
                return middle;
            }else if(list[middle] > num){
                high = middle - 1;
            }else if(list[middle] < num){
                low = middle + 1;
            }
        }
        return -1;
    }
}
