public class ThreadPriority implements Runnable {
    public static void main(String[] args) {

        // System.out.println(Thread.currentThread().getName() + " has priority " + Thread.currentThread().getPriority());
        Thread t1 = new Thread(new ThreadPriority(), "child");
        // System.out.println("Thread" + t1.getName() + " has Priority " + t1.getPriority());
        t1.setPriority(1);
        t1.start();
        for (int i = 0; i < 1000; i++) {
            System.out.println("Thread main"+i);
        }
    }

    public void run() {

        for (int i = 0; i < 1000; i++) {
            System.out.println("child "+i);
        }

    }
}
