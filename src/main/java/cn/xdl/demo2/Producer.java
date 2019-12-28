package cn.xdl.demo2;

public class Producer implements Runnable{
    private Storage s;

    public Producer(Storage s) {
        this.s = s;
    }

    @Override
    public void run() {
        for(int i=1;i<=20;i++){
            Object obj = new Object();
            s.produce(obj);
            System.out.println("生产者线程"+Thread.currentThread().getName()+"生产的商品是："+obj);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
