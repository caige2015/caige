package song;

public class MaoPao {

    public static void main(String[] args) {
        int[] array = {1,3,4,5,2,7,9,8,34,45,23,12,6};
        int[] orderList = getListOfOrder(array);
        for(int i=0; i < orderList.length; i++) {
            System.out.print(orderList[i] + " ");
        }
    }
    
    public static int[] getListOfOrder(int[] list){
        int temp = 0;
        for(int i=0; i < list.length-1; i++) {
            for(int j=i+1; j < list.length; j++) {
                if(list[i] > list[j]){
                    temp = list[i];
                    list[i] = list[j];
                    list[j] = temp;
                }
            }
        }
        return list;
    }
}
