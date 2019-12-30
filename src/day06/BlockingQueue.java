package day06;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 阻塞队列
 * 当队列为空时 取数据会被阻塞 直到不为空
 * 当队列满时 放数据会被阻塞 直到不满
 */
public class BlockingQueue<T> {
    private LinkedList<T> list=new LinkedList<T>();
    private int limit=10;
    public BlockingQueue(int limit){//限制队列的大小
        this.limit=limit;
    }

    public  synchronized  void  add(T t){
        System.out.println(Thread.currentThread().getName());
        while(list.size()>=limit){
            try {

                System.out.println(Thread.currentThread().getName()+" 放数据阻塞中");
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        list.add(t);
        notifyAll();
    }

    public  synchronized  T  take(){
        while(list.size()==0){
            try {
                System.out.println("取数据 阻塞中");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notifyAll();
        return   list.removeFirst();
    }


    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue=new BlockingQueue<Integer>(10);
        for (int i=0;i<20;i++){
            final int t=i;
            new Thread(()->{
                blockingQueue.add(t);
            }).start();
       }
        for (int i=0;i<10;i++){
            new Thread(()->{
                blockingQueue.take();
            }).start();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(blockingQueue);

    }
}
