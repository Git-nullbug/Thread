package cn.xdl.demo2;

public class StorageTest {
    public static void main(String[] args) {

        Storage s = new Storage();
        Thread p1 = new Thread(new Producer(s));
        Thread p2 = new Thread(new Producer(s));

        Thread c1 = new Thread(new Consumer(s));
        Thread c2 = new Thread(new Consumer(s));
        p1.start();
        p2.start();
       // c1.start();
        c2.start();


    }
}
