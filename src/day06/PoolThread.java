package day06;

import java.util.concurrent.BlockingQueue;

/**
 * 线程池
 */
public class PoolThread extends Thread{
    private BlockingQueue<Runnable> taskQueue=null;
    private boolean isStopped=false;
    public PoolThread(BlockingQueue<Runnable> queue){
        taskQueue=queue;
    }
    public void run(){
        while(!isStopped){
            try {
                Runnable runnable=taskQueue.take();
                runnable.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized  void toStop(){
        isStopped=true;
        this.interrupt();         //打断线程池 queue调用
    }

    public  synchronized boolean isStopped(){
        return isStopped;
    }
}
