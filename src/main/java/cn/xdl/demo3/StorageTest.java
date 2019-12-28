package cn.xdl.demo3;

public class StorageTest {

    public static void main(String[] args) {
        Storage s = new Storage();
        Thread s1 = new Thread(new Producer(s));
        Thread s2 = new Thread(new Producer(s));

        Thread x1 = new Thread(new Consumer(s));
        Thread x2 = new Thread(new Consumer(s));

        s1.start();
        s2.start();

        x1.start();
        x2.start();


    }



}
