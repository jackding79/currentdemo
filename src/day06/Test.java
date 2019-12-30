package day06;

import java.util.concurrent.CopyOnWriteArrayList;

public class Test {
    volatile int i=0;
    public  synchronized  void increment(){
        try {
            System.out.println(Thread.currentThread().getName()+" 我要开始阻塞");
            wait();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        i++;
    }

    public static void main(String[] args) {
        Test test=new Test();
        for (int i=0;i<10;i++){
            new Thread(()->{
                test.increment();
            }).start();
        }
    }
}
