/**
 * ThreadLifeCycle
 */
public class ThreadLifeCycle {

    public static void main(String[] args) {
        MyThread t2 = new MyThread();
        t2.setName("child");
        System.out.println(t2.getName() + "is in" + t2.getState());
        t2.start();
        System.out.println(t2.getName() + " is in " + t2.getState());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
        System.out.println(t2.getName() + " is in " + t2.getState());

    }

}

class MyThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " is running");

        }

    }

}