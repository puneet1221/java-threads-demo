public class SynchronizationDemo {
    public static void main(String[] args) {
        Data d = new Data();
        TestThread1 t1 = new TestThread1(d);
        TestThread1 t2 = new TestThread1(d);
        t1.start();
        t2.start();

        System.out.println("Main finished");
    }
}

class Data {

    synchronized void update() {
        System.out.println(Thread.currentThread().getName() + " entered into sychronized area");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            System.out.println("state of object being updated by " + Thread.currentThread().getName());

        }
        System.out.println(Thread.currentThread().getName() + " exited from syunchronized area");
    }

    void read() {
        System.out.println(Thread.currentThread().getName() + " enterd into reading area");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Reading is done by" + Thread.currentThread().getName());
        }
    }

}

class TestThread1 extends Thread {
    Data obj;

    TestThread1(Data d) {
        obj = d;
    }

    @Override
    public void run() {
        obj.read();
        obj.update();
    }
}
