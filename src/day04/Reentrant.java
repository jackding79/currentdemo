package day04;

/**
 * @synchronized 支持重入
 * 可重入:这意味着如果一个java线程进入了代码中的synchronized同步块，
 *      并因此获得了该同步块使用的同步对象对应的管程上的锁，那么这个线程可以进入由同一个管程对象所同步的另一个java代码块
 */
public class Reentrant {
    public  synchronized  void Outer(){
        Inner();
    }
    public  synchronized void Inner(){
        System.out.println("inner");
    }

    public static void main(String[] args) {
        Reentrant reentrant=new Reentrant();
        reentrant.Outer();
    }
}
