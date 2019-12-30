package day05;

/**
 * 自己实现一个简单的可重入锁
 */
public class ReentrantLock implements Lock{
    private boolean isLocked=false;
    Thread lockedby=null;
    int lockedcount=0;
    @Override
    public synchronized void lock() throws InterruptedException{
            Thread current=Thread.currentThread();
            while(isLocked&&current!=lockedby){//判断 如果是当前上锁的线程 就跳过wait() 使其能继续lock
                wait();
            }
            isLocked=true;
            lockedby=current;
            lockedcount++;

    }

    @Override
    public synchronized void unlock() {
                if(Thread.currentThread()==lockedby){ //是当前线程 上锁数量减一 直到为0时 下一个线程才可以被唤醒
                    lockedcount--;
                }
                if(lockedcount==0){
                    notify();
                    isLocked=false;
                }
    }
}
