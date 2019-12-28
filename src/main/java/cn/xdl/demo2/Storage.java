package cn.xdl.demo2;

import java.util.ArrayList;
import java.util.List;

public class Storage {

    private final static int MAX_SIZE = 10;
    private List<Object> list = new ArrayList<>();

    public void produce(Object obj){
        synchronized (this){
            while(list.size() >= MAX_SIZE){
                try {
                    System.out.println("生成者"+Thread.currentThread().getName()+"让出线程，进入等待状态");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(obj);
            System.out.println("生成者"+Thread.currentThread().getName()+"生产一个产品，现库存："+list.size());
            notifyAll();
        }
    }

    public Object consume(){
        synchronized(this){
            while(list.size() <= 0){
                try {
                    System.out.println("消费者"+Thread.currentThread().getName()+"让出线程，进入等待状态");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            notifyAll();
            System.out.println("消费者"+Thread.currentThread().getName()+"消费一个产品，现库存："+list.size());
            return list.remove(list.size() - 1);
        }
    }









}
