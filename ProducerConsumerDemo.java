import java.lang.Thread; // Import the Thread class from the Java language package.

public class ProducerConsumerDemo {

    public static void main(String[] args) {
        // Create a buffer with a capacity of 100 items.
        Buffer b = new Buffer(100);

        // Instantiate producer and consumer with the same buffer.
        Producer p = new Producer(b);
        Consumer c = new Consumer(b);

        // Set names for the threads for easier identification.
        p.setName("Producer");
        c.setName("Consumer");

        // Start the producer and consumer threads.
        p.start();
        c.start();

        // Main thread sleeps for 10 seconds to allow production and consumption.
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            // Handle any exceptions during sleep (e.g., InterruptedException).
            e.printStackTrace();
        }

        // Indicate that the main thread has finished its execution.
        System.out.println("MAIN FIN");

        // Stop both producer and consumer threads.
        p.stop();
        c.stop();
    }
}

// Buffer class to manage the shared resource (the buffer).
class Buffer {
    int capacity; // Maximum capacity of the buffer.
    int noOfItems = 0; // Current number of items in the buffer.

    // Constructor to initialize the buffer's capacity.
    Buffer(int c) {
        capacity = c;
    }

    // Synchronized method for producing items.
    synchronized void Produce(int n) {
        System.out.println("Current items: " + noOfItems);

        // Check if there is enough space to produce n items.
        if (noOfItems + n > capacity) {
            try {
                // If buffer is full, wait until there's space.
                wait();
            } catch (Exception e) {
                // Handle any exceptions during wait.
                e.printStackTrace();
            }
        } else {
            // Produce n items and update the buffer.
            System.out.println("Producer produced " + n + " items");
            noOfItems += n;

            // Notify all waiting threads that the buffer state has changed.
            notifyAll();
            System.out.println("Current items: " + noOfItems + "\n\n");
        }
    }

    // Synchronized method for consuming items.
    synchronized void consume(int n) {
        System.out.println("Current items: " + noOfItems);
        System.out.println("Requested: " + n);

        // Wait until there are enough items to consume.
        while (n > noOfItems) {
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting ....");
                wait(); // Wait if not enough items are available.
            } catch (Exception e) {
                // Handle any exceptions during wait.
                e.printStackTrace();
            }
        }

        // Consume n items if there are enough in the buffer.
        if (n <= noOfItems) {
            System.out.println("Thread " + Thread.currentThread().getName() + " consumed " + n + " items\n\n");
            noOfItems -= n;

            // Notify all waiting threads that the buffer state has changed.
            notifyAll();
        }
    }
}

// Producer class extending Thread to produce items.
class Producer extends Thread {
    Buffer b; // Reference to the shared buffer.

    // Constructor to set the buffer for the producer.
    Producer(Buffer obj) {
        b = obj;
    }

    // Run method for producing items in a loop.
    @Override
    public void run() {
        while (true) {
            // Produce a random number of items between 0 and 100.
            b.Produce((int) (Math.random() * 100));
            try {
                // Sleep for a second between productions.
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Handle interruptions during sleep.
                e.printStackTrace();
            }
        }
    }
}

// Consumer class extending Thread to consume items.
class Consumer extends Thread {
    Buffer b; // Reference to the shared buffer.

    // Constructor to set the buffer for the consumer.
    Consumer(Buffer obj) {
        b = obj;
    }

    // Run method for consuming items in a loop.
    @Override
    public void run() {
        while (true) {
            // Consume a random number of items between 0 and 100.
            b.consume((int) (Math.random() * 100));
            try {
                // Sleep for a second between consumptions.
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Handle interruptions during sleep.
                e.printStackTrace();
            }
        }
    }
}
