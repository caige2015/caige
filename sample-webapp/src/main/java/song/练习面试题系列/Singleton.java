package song.练习面试题系列;

//单例模式大全，各种方法实现单例模式

/**
 * 单例模式
 * @author 猜个
 * 因为单例是静态的final变量，当类第一次加载到内存中的时候就初始化了，其thread-safe性由JVM来负责保证。
 * 值得注意的是这个实现方式不是lazy-loadedd的。
 */
class Singleton1 {

    private Singleton1(){}
    
    private static final Singleton1 singleton = new Singleton1();
    
    public static Singleton1 getInstance(){
        return singleton;
    }
}

/**
 * 单例模式
 * @author 猜个
 * 线程不安全方式
 */
class Singleton2 {

    private static Singleton2 singleton2 = null;
    
    private Singleton2(){}
    
    public static Singleton2 getInstance(){
        if(singleton2==null){
            singleton2 = new Singleton2();
        }
        return singleton2;
    }
}

/**
 * 线程安全的，但是开销很大，每次线程调用方法时都需要去比较是否同步。
 * @author 猜个
 *
 */
class Singleton3 {

    private static Singleton3 singleton3 = null;
    
    private Singleton3(){}
    
    public static synchronized Singleton3 getInstance(){
        if(singleton3==null){
            singleton3 = new Singleton3();
        }
        return singleton3;
    }
}

//关于延迟初始化(lazy loaded)
//“除非绝对必要，否则就不要延迟初始化”。延迟初始化是一把双刃剑，它降低了初始化类或者创建实例的开销，却增加了访问被延迟初始化的域的开销，考虑到延迟初始化的域最终需要初始化的开销以及域的访问开销，延迟初始化实际上降低了性能。
/**
 * DCL (double checked locking 实现法)
 * 双检查法
 * @author 猜个
 *我们可以看到有两次对instance是否为null的判断：
 *如果第一次判断不为空，则直接返回实例就可以了；
 *如果instance为空，则进入同步代码块再进行null值判断，再选择是否实例化。
 *第一个null判断可以减少系统的开销。在实际项目中做过多线程开发的都应该知道DCL。
 */
class Singleton4{
    private volatile Singleton4 singleton4;
    
    private Singleton4(){}
    
    public Singleton4 getInstance(){
        if(singleton4==null){
            synchronized(Singleton4.class) {
                if(singleton4==null){
                    singleton4 = new Singleton4();
                }
            }
        }
        return singleton4;
    }
}

/**
 * @author 猜个
 * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
 * 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载
 * 当getInstance方法第一次被调用的时候，
 * 它第一次读取SingletonHolder.instance，
 * 导致SingletonHolder类得到初始化；而这个类在装载并被初始化的时候，会初始化它的静态域，
 * 从而创建Singleton的实例，由于是静态的域，因此只会被虚拟机在装载类的时候初始化一次，
 * 并由虚拟机来保证它的线程安全性。这个模式的优势在于，getInstance方法并没有被同步，
 * 并且只是执行一个域的访问，因此延迟初始化并没有增加任何访问成本
 */
class Singleton5{
    
    private static class SingletonHolder{
       private static Singleton5 singleton5 = new Singleton5();
    }
    
    private Singleton5(){}
    
    public static Singleton5 getInstance(){
        return SingletonHolder.singleton5;
    }
}


//你可以通过Singleton.INSTANCE来访问该单示例变量。默认枚举实例的创建是线程安全的，
//但是在枚举中的其他任何方法由程序员自己负责。如果你正在使用实例方法，
//那么你需要确保线程安全（如果它影响到其他对象的状态的话）。传统单例存在的另外一个问题是一旦你实现了序列化接口，
//那么它们不再保持单例了，但是枚举单例，JVM对序列化有保证。枚举实现单例的好处：有序列化和线程安全的保证，代码简单。
/**
 * 枚举方式单例
 * 借助枚举的方式新建出实例且为final类型的
 * since java1.5
 * @author 猜个
 *
 */

class Singleton6{
    public static void main(String[] args) {
        System.out.println("d---");
    }
}

enum Singleton6Helper{
   
    singleton6;
    
    private Singleton6Helper(){}
    
    public Singleton6 build(){
        final Singleton6 singleton6 = new Singleton6();
        return singleton6;
    }
}
















