public class InterruptDemo {
    public static void main(String[] args) {
        TestThread t1=new TestThread();
        t1.start();
        System.out.println("calling interrupt contnuing its xectuing");
        t1.interrupt();
        System.out.println("Main finished");
    }
}

class TestThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(
                    "hello ");
        }

        //i am gonna lseep now
        try {
            System.out.println("slept");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("interrupted"+ e.getMessage());
           
        }
    }
}
