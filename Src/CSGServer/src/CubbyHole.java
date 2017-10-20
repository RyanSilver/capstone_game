//package EYETeeCapstoneProject;

import java.io.*;

/**
 * Thread safe communication between threads 
 */
public class CubbyHole {

    private String contents;
    private boolean available = false;

    /**
     * used to get messages from other threads 
     * @return: message from other thread 
     */
    public synchronized String get() {
        while (available == false) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        available = false;
        notify();
        return contents;
    }

    /**
     *
     * @param value: string to "put" in the cubby hole. this will be read by the other thread 
     */
    public synchronized void put(String value) {
        while (available == true) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        contents = value;
        available = true;
        notify();
    }
}
