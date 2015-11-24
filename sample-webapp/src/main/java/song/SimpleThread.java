package song;


public class SimpleThread extends Thread{

    private int countDown = 5;
    private int threadNumber;
    private static int threadCount = 0;
    
    public SimpleThread(){
        threadNumber = ++threadCount;
        System.out.println("Making " + threadNumber);
    }
    
    @Override
    public void run() {
        System.out.println("thread:"+threadNumber+" ("+countDown+")");
        while(true){
            if(--countDown == 0) return;
        }
    }
    
    public static void main(String[] args) {
        for(int i=0; i < 5; i++) {
            new SimpleThread().start();
            System.out.println("开始");
        }
    }
}
