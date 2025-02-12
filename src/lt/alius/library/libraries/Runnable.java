package lt.alius.library.libraries;

public class Runnable implements java.lang.Runnable {
    @Override
    public void run() {
        System.out.println("This code is running in a thread");
    }
}
