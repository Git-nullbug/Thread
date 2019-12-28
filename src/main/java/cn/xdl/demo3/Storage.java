package cn.xdl.demo3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Storage {
    //定义常量为仓库最大容量（list）
    private final static int MAX_SIZE = 10;
    //定义仓库对象
    private List<Object> list = new ArrayList<>();
    //获取锁对象
    Lock lock = new ReentrantLock(true);
    //定义仓库满时线程让出的的变量
    Condition full = lock.newCondition();
    //定义仓库空时线程让出的变量
    Condition empty = lock.newCondition();

    private int x = 0;
    private int y = 0;

    public void produce(Object obj){
        //获得锁
        lock.lock();
        try{
            while(list.size() >= MAX_SIZE){
                try {
                    //当前线程让出时间片进入等待状态
                    System.out.println("生成者"+Thread.currentThread().getName()+"让出线程，进入等待状态");
                    full.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(obj);
            x++;
            System.out.println("生成者"+Thread.currentThread().getName()+"生产一个产品，现库存："+list.size()+" 一共生产："+x+"次");

            //唤醒等待线程
            empty.signal();

        }finally{
            //释放锁
            lock.unlock();
        }
    }

    public Object consume(){
        //获得锁
        lock.lock();
        try{
            while(list.size() <= 0){
                try {
                    //当前线程进入等待状态
                    System.out.println("消费者"+Thread.currentThread().getName()+"让出线程，进入等待状态");
                    empty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //唤醒等待线程
            full.signalAll();
            y++;
            System.out.println("消费者"+Thread.currentThread().getName()+"消费一个产品，现库存："+list.size()+" 一共消费："+y+"次");
            return  list.remove(0);
        }finally{
            //释放锁
            lock.unlock();
        }
    }










}
