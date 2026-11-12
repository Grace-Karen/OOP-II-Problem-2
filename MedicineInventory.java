import java.util.concurrent.locks.ReentrantLock;

public class MedicineInventory {

    // Initial medicine stock
    private int availableDoses = 50;

    // Lock for controlling access to the inventory
    private ReentrantLock lock = new ReentrantLock();

    // Method for dispensing medicine
    public boolean dispenseMedicine(int doses) {

        lock.lock();

        try {

            if (availableDoses >= doses) {

                availableDoses -= doses;

                System.out.println(doses +
                    " doses dispensed successfully.");

                System.out.println("Remaining stock: "
                    + availableDoses);

                return true;

            } else {

                System.out.println("Insufficient medicine stock!");

                return false;
            }

        } finally {

            lock.unlock();
        }
    }
}