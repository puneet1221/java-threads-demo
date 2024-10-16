public class StaticBlockDemo {
    public static void main(String[] args) {

        DAO d = new DAO();
        RailReservation r = new RailReservation();

        TestThread22 t2 = new TestThread22(r, d);
        TestThread22 t3 = new TestThread22(r, d);
        TestThread22 t4 = new TestThread22(r, d);
        t2.start();
        t3.start();
        t4.start();
        System.out.println("Main finished");

    }

}

class RailReservation {
    DAO dao;

    RailReservation() {

    }

    RailReservation(DAO d) {
        dao = d;
    }

    void getSeatAvailability() {
        System.out.println("seat avaialbiotlityt chekded by" + Thread.currentThread().getName());
    }

    void bookTicket() {
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Performing mandatory formality" + i + " by " + Thread.currentThread().getName());
        }
        synchronized (dao) {
            System.out.println("\n\nA!!!!Alert database state being updated by !!!" + Thread.currentThread().getName()
                    + " no other thread is allowed to update the stae\n");
            dao.bookTicket();
            System.out.println("Ticket booked by " + Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName()
                    + "completed the state Updation Now other threads can update the stae!\n\n\n");

        }

    }

}

class DAO {
    int numberOfAvalSeats = 100;

    void bookTicket() {
        numberOfAvalSeats -= 1;
        for (int i = 0; i < 2; i++) {
            System.out.println("database updation is being peformed by " + Thread.currentThread().getName());
            System.out.println("Please other thread wait");
            try {
                Thread.sleep(2000);
            } catch (Exception e) {

            }
        }

    }
}

class TestThread22 extends Thread {
    RailReservation robj;

    TestThread22(RailReservation r, DAO d) {
        r.dao = d;
        robj = r;
    }

    @Override
    public void run() {

        robj.getSeatAvailability();
        robj.bookTicket();
    }

}