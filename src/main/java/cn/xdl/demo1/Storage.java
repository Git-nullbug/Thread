package cn.xdl.demo1;

import java.lang.reflect.Array;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Storage {
    //仓库现有商品个数
    private int count = 0;
    //使用阻塞队列做仓库
    private BlockingQueue<Object> bq = new ArrayBlockingQueue<Object>(10,true);

    private int x = 0;
    private int y = 0;

    public static void main(String[] args) {
        Storage s = new Storage();
        for(int i=0;i<3;i++){
            new Thread(s.new Producer()).start();
            new Thread(s.new Consumer()).start();
        }
    }

    class Producer implements Runnable{

        @Override
        public void run() {

            for(int i=1;i<=20;i++){
                try {
                    bq.put(new Object());
                    count++;
                    x++;
                    System.out.println("生产者线程："+Thread.currentThread().getName()+"生产一个商品，现仓库中有"+count+"个，共生产："+x+"次");
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
    }

    class Consumer implements Runnable{
        @Override
        public void run() {
            for(int i=1;i<=20;i++){
                try {
                    bq.take();
                    count--;
                    y++;
                    System.out.println("消费者线程："+Thread.currentThread().getName()+"消费一个商品，现仓库中有"+count+"个，共消费："+y+"次");
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
