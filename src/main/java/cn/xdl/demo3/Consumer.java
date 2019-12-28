package cn.xdl.demo3;

public class Consumer implements Runnable {
    private Storage s;

    public Consumer(Storage s) {
        this.s = s;
    }

    @Override
    public void run() {
        for(int i=1;i<=20;i++){
            Object consume = s.consume();
            System.out.println("消费者线程"+Thread.currentThread().getName()+"消费的商品是："+consume);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
