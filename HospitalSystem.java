import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;

import java.util.ArrayList;
import java.util.List;

public class HospitalSystem {

    public static void main(String[] args)
            throws InterruptedException, ExecutionException {

        // Create the patient repository
        PatientRepository repository = new PatientRepository();

        // Create the medicine inventory
        MedicineInventory inventory = new MedicineInventory();

        // Create a laboratory request queue
        BlockingQueue<LabRequest> labQueue =
                new ArrayBlockingQueue<>(20);

        // Allow only two technicians to use equipment at a time
        Semaphore labEquipment = new Semaphore(2, true);

        // Create a pool of 20 worker threads
        ExecutorService executor =
                Executors.newFixedThreadPool(20);

        // Keep track of doctors, nurses and pharmacists
        List<Future<?>> workerTasks = new ArrayList<>();

        // Keep track of laboratory technicians
        List<Future<?>> technicianTasks = new ArrayList<>();


        // Create and submit five doctors
        for (int i = 0; i < 5; i++) {

            Doctor doctor = new Doctor(
                    "Doctor " + (i + 1),
                    repository,
                    1001 + i,
                    "Diagnosis " + (i + 1)
            );

            workerTasks.add(executor.submit(doctor));
        }


        // Create and submit ten nurses
        for (int i = 0; i < 10; i++) {

            Nurse nurse = new Nurse(
                    "Nurse " + (i + 1),
                    repository,
                    labQueue,
                    1001 + i,
                    "Temperature: 37°C, Blood Pressure: 120/80",
                    "Blood Test"
            );

            workerTasks.add(executor.submit(nurse));
        }


        // Create and submit three laboratory technicians
        for (int i = 0; i < 3; i++) {

            LaboratoryTechnician technician =
                    new LaboratoryTechnician(
                            "Technician " + (i + 1),
                            repository,
                            labQueue,
                            labEquipment
                    );

            technicianTasks.add(executor.submit(technician));
        }


        // Create and submit two pharmacists
        for (int i = 0; i < 2; i++) {

            Pharmacist pharmacist = new Pharmacist(
                    "Pharmacist " + (i + 1),
                    repository,
                    inventory,
                    1001 + i,
                    5
            );

            workerTasks.add(executor.submit(pharmacist));
        }


        // Wait for doctors, nurses and pharmacists to finish
        for (Future<?> task : workerTasks) {
            task.get();
        }


        // Send stop messages to the three laboratory technicians
        for (int i = 0; i < 3; i++) {

            labQueue.put(new LabRequest(-1, "STOP"));
        }


        // Wait for all laboratory technicians to stop
        for (Future<?> task : technicianTasks) {
            task.get();
        }


        // Shut down the executor service
        executor.shutdown();

        System.out.println("Hospital operations completed.");
    }
}