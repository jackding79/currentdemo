package day04;

/**
 * java 中实现锁
 */
public class Lock {
    private boolean islocked=false;
    public synchronized  void lock() throws InterruptedException{
        while(islocked) {//防止假唤醒
            System.out.println(Thread.currentThread().getName()+" :开始等待");
            wait();      //第一个线程执行lock 将islocked设置为true 接下来的线程会处于等待 直到上一个线程将它唤醒
        }
        islocked=true;
    }

    public synchronized  void unlock(){
            System.out.println(Thread.currentThread().getName()+" :开始唤醒上一个等待线程");
            notify();//唤醒等待的线程
            islocked=false;
    }
}
