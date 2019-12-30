package day02;

/**
 * 丢失信号
 * 为了避免信号丢失， 用一个变量来保存是否被通知过。在notify前，设置自己已经被通知过。在wait后，设置自己没有被通知过，需要等待通知。
 */
public class MyWaitNotify {
    MonitorObject monitorObject = new MonitorObject();
    boolean wasSignalled = false;

    public void dowait() {
        synchronized (monitorObject) {
            if (!wasSignalled) {
                try {
                    System.out.println("开始等待");
                    monitorObject.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            wasSignalled = false;
        }
        System.out.println("等待结束");
    }

    public void donotify() {
        synchronized (monitorObject) {
            wasSignalled = true;
            System.out.println("开始唤醒");
            monitorObject.notify();
        }
    }

    public static void main(String[] args) {
        MyWaitNotify myWaitNotify = new MyWaitNotify();
        new Thread(() -> {
            myWaitNotify.dowait();
        }).start();
        new Thread(() -> {
            myWaitNotify.donotify();
        }).start();
    }
}
