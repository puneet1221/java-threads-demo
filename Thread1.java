class MyThread1 extends Thread {

    public static void main(String[] args) {
        MyThread1 t1 = new MyThread1();
        t1.start();
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " is running now");
        }

    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " IS RUNNING");

        }
    }

}