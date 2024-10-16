/**
 * DaemonDemo
 * 
 * 
 * Daemon Threads and JVM Exit: If the main thread finishes executing, the JVM
 * may exit even if daemon threads are still running. Daemon threads do not keep
 * the JVM alive. In the code above, the main thread may finish before the
 * daemon thread has a chance to execute its run method.
 */
public class DaemonDemo extends Thread {

    public static void main(String[] args) {
        // Check if the main thread is a daemon thread
        System.out.println("Is main thread a daemon? " + Thread.currentThread().isDaemon());

        // Create an instance of DaemonDemo
        DaemonDemo d = new DaemonDemo();

        // Set the thread as a daemon thread
        d.setDaemon(true);
        // Start the daemon thread
        d.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        // Print the name of the current thread and if it is a daemon thread
        System.out.println(Thread.currentThread().getName() + " is daemon: " + Thread.currentThread().isDaemon());
    }
}
