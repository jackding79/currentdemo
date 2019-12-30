package day06;

import java.util.concurrent.*;

/**
 * jdk 自带线程池
 * //当 线程数<coreSize 会继续为每一个任务创建新线程
 * //当 线程数>coreSize 会将任务放入队列中执行
 * //当 队列也满了 就会继续创建线程直到线程数>=maximumSize 此时再来新任务 就会执行拒绝策略
 */
public class ThreadPool {
    private static  ThreadPoolExecutor executor=new ThreadPoolExecutor(3,6,
            100, TimeUnit.SECONDS,new LinkedBlockingQueue<>(100),new RejectHandler());
    public static void main(String[] args) {
        for (int i=0;i<=9;i++){
            executor.submit(new Task());
            System.out.println("Active Thread ："+executor.getActiveCount()+
                    " .Task count"+executor.getTaskCount()+
                    " .Queue size"+executor.getQueue().size());
        }

    }
    private static class Task extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(1000*100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class RejectHandler implements   RejectedExecutionHandler{
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println(" My name is "+r+" 我溜了 ");
        }
    }
}
