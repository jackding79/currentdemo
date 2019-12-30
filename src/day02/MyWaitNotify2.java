package day02;

/**
 * 假唤醒 由于莫名其妙的原因，线程有可能在没有调用过notify()和notifyAll()的情况下醒来。这就是所谓的假唤醒（spurious wakeups）
 *
 * 为了防止假唤醒 将wait放在while循序中 当被唤醒后 还需要进行一次while循环信号量判断 使线程继续等待
 */
public class MyWaitNotify2 {
       MonitorObject monitorObject = new MonitorObject();
    boolean wasSignalled = false;

    public void dowait() {
        synchronized (monitorObject) {
            while (!wasSignalled) {
                try {
                    monitorObject.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            wasSignalled = false;
        }
    }

    public void doNotify() {

        synchronized (monitorObject) {

            wasSignalled = true;

            monitorObject.notify();

        }

    }

}
