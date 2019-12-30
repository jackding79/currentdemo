package day03;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
    Lock lock=new ReentrantLock();
    public void dowait() throws InterruptedException{
        System.out.println(Thread.currentThread().getName());
        lock.lock();
        System.out.println("锁住了");
        Thread.sleep(2000);
        lock.unlock();
    }

    public static void main(String[] args) throws Exception {
        LockExample lockExample=new LockExample();
        new Thread(()->{
            try {
                lockExample.dowait();
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                lockExample.dowait();
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();
    }
}
