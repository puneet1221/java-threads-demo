/**
 * TestDemo
 */
public class TestDemo {

    public static void main(String[] args) {
        Threadt11 t1 = new Threadt11();
        Threadt2 t2 = new Threadt2();
        t1.start();
        t2.start();
        try {
            Thread.sleep(2000);
            System.out.println(t1.getState());
            t1.join();
            t2.join();

            System.out.println("main is going to take rest");

        } catch (Exception e) {

        }
        System.out.println("Main finished");
    }
}

class Threadt11 extends Thread {

    @Override
    public void run() {
        try {

            Thread.sleep(10000);
        } catch (Exception e) {
            // TODO: handle exception
        }

        for (int i = 0; i < 1000; i++) {
            System.out.println("Thread1=>" + i);
        }
    }
}

/**
 * */

class Threadt2 extends Thread {

    @Override
    public void run() {
        Thread.yield();
        for (int i = 0; i < 1000; i++) {

            System.out.println("Thread 2=>" + i);

        }
    }

}