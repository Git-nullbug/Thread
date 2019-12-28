package cn.xdl.demo2;

public class Consumer implements Runnable {
    private Storage s;

    public Consumer(Storage s) {
        this.s = s;
    }

    @Override
    public void run() {
        while(true){
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
