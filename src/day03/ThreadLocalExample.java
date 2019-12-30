package day03;

/**
 * threadlocal
 */
public class ThreadLocalExample {
    public static class MyRunnable implements Runnable{
        private ThreadLocal<Integer> threadLocal=new ThreadLocal<>();
        @Override
        public void run() {
            threadLocal.set((int)(Math.random()*1000));
            try{
                Thread.sleep(2000);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(threadLocal.get());
        }
    }

    public static void main(String[] args) throws  Exception{
        MyRunnable myRunnable=new MyRunnable();
        Thread t1=new Thread(myRunnable);
        Thread t2=new Thread(myRunnable);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
