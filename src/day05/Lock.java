package day05;

/**
 * 定义一个锁的接口
 */
public interface Lock {
    public void lock() throws InterruptedException;
    public void unlock();
}
