package day05;

public class Test {
    Lock lock = new ReentrantLock();

    public void outer() {
        try {
            lock.lock();
        } catch (InterruptedException e) {// crtl+alt+t
            e.printStackTrace();
        }
        inner();
        lock.unlock();
    }

    public void inner() {
        try {
            lock.lock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
    }

    public static void main(String[] args) {
        Test test=new Test();
        new Thread(()->{
            test.outer();
        }).start();
        new Thread(()->{
            test.outer();
        }).start();
    }
}
