package day01;

public class Counter {
    protected long count = 0;

    public void add(int value) {
        this.count = this.count + value;
    }

    public static void main(String[] args) throws Exception{
        Counter counter = new Counter();
        Thread t1 = new Thread(() -> {
            counter.add(1);
        });
        Thread t2 = new Thread(() -> {
            counter.add(3);
        });
        t1.start();
        t2.start();
        Thread.sleep(2000);
        System.out.println(counter.count);
    }
}
