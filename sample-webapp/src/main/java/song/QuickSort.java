package song;


public class QuickSort {

    public static void quick(int[] list, int low, int high){
        int middle;
        if(low < high){
            middle = getMiddle(list, low, high);
            quick(list, 0, middle-1);
            quick(list, middle+1, high);
        }
    }
    
    
    public static int getMiddle(int[] list, int low, int high){
        int temp = list[low];
        while(low < high){
            while(low<high && list[high] >= temp){
                high--;
            }
            list[low] = list[high];
            while(low<high && list[low] <= temp){
                low++;
            }
            list[high] = list[low];
        }
        list[low] = temp;
        return low;
    }
    
    public static void main(String[] args) {
        int[] array = {1,45,2,43,8,9,42,12,32};
        quick(array, 0, array.length-1);
        for(int i=0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
