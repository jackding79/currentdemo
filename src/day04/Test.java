package day04;

public class Test {
    Lock lock=new Lock();
    private int count=0;
    public void test() throws Exception{
        lock.lock();
        Thread.sleep(2000);
        System.out.println(count++);
        lock.unlock();
    }

    public void Outer() throws Exception{//调用outer会使线程处于无线等待状态  这种写法不支持重入
        lock.lock();
        Inner();
        lock.unlock();
    }
    public void Inner() throws Exception{
        lock.lock();
        System.out.println("Inner");
        lock.unlock();
    }

    public static void main(String[] args) {
        Test test=new Test();
       /* new Thread(()->{
            try {
                test.test();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();
        new Thread(()->{
            try {
                test.test();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();
*/
        new Thread(()->{
            try {
                test.Outer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
