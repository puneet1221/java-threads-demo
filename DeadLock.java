// This class demonstrates a potential deadlock situation using threads.
public class DeadLock extends Thread {
    // Instances of classes A and B that will be involved in the deadlock.
    A a = new A();
    B b = new B();

    public static void main(String[] args) {
        // Create an instance of the DeadLock class.
        DeadLock d = new DeadLock();

        // Set the name of the current thread to "Thread 1".
        Thread.currentThread().setName("Thread 1");

        // Set the name of the DeadLock thread to "Thread 2".
        d.setName("Thread 2");

        // Start the DeadLock thread.
        d.start();

        // Start the method in the main thread that leads to the first locking attempt.
        d.startThread1();
    }

    // Method to start the locking process from the main thread.
    private void startThread1() {
        // Thread 1 tries to acquire a lock on object a and then calls method d1 on b.
        a.d1(b);
    }

    // The run method is executed when the DeadLock thread starts.
    @Override
    public void run() {
        // Thread 2 tries to acquire a lock on object b and then calls method d1 on a.
        b.d1(a);
    }
}

// Class A with synchronized methods that can lead to a deadlock.
class A {
    // Synchronized method that locks this instance and attempts to call B's method.
    synchronized void d1(B b) {
        try {
            // Simulate some work with a sleep.
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // Handle interruption during sleep.
            e.printStackTrace();
        }
        // Attempt to call B's last method, requiring a lock on B.
        b.last();
    }

    // Another synchronized method that can be called by B.
    synchronized void last() {
        // Print message indicating this method was called.
        System.out.println("A's last method");
    }
}

// Class B with synchronized methods that can lead to a deadlock.
class B {
    // Synchronized method that locks this instance and attempts to call A's method.
    synchronized void d1(A a) {
        try {
            // Simulate some work with a sleep.
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // Handle interruption during sleep.
            e.printStackTrace();
        }
        // Attempt to call A's last method, requiring a lock on A.
        a.last();
    }

    // Another synchronized method that can be called by A.
    synchronized void last() {
        // Print message indicating this method was called.
        System.out.println("B's last method");
    }
}
