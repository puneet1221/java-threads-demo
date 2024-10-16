public class Thread2 implements Runnable {

    public static void main(String[] args) {

        Thread2 t2 = new Thread2(); // runnable object
        Thread t1 = new Thread(t2,"ChildThread"); // Thread class object
        t1.start();
        Thread.currentThread().setName("parent");

        for (int i = 0; i < 100; i++) {
            System.out.println("Thread " + Thread.currentThread().getName() + " is runnung");
        }

    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Thread " + Thread.currentThread().getName() + " is running");
        }

    }
}
