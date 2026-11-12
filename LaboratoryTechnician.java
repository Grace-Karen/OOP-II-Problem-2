import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class LaboratoryTechnician implements Runnable {

    // Attributes
    private String technicianName;
    private PatientRepository repository;
    private BlockingQueue<LabRequest> labQueue;
    private Semaphore labEquipment;

    // Constructor
    public LaboratoryTechnician(String technicianName,
                                PatientRepository repository,
                                BlockingQueue<LabRequest> labQueue,
                                Semaphore labEquipment) {

        this.technicianName = technicianName;
        this.repository = repository;
        this.labQueue = labQueue;
        this.labEquipment = labEquipment;
    }

    // Task performed by the technician
    @Override
    public void run() {

        try {

            // Keep processing laboratory requests
            while (!Thread.currentThread().isInterrupted()) {

                

                try {

                    // Retrieve a request from the queue
                    LabRequest request = labQueue.take();

                    // Check whether this is a stop request
if (request.getPatientId() == -1) {
    break;
}
// Wait for an available laboratory machine
                labEquipment.acquire();
                
                    // Find the patient
                    Patient patient =
                        repository.findPatient(request.getPatientId());

                    if (patient != null) {

                        // Simulated laboratory result
                        String result =
                            request.getTestName() + " completed";

                        // Store the result in the patient's record
                        patient.updateTestResults(result);

                        System.out.println(technicianName +
                            " processed " + request.getTestName() +
                            " for patient " + request.getPatientId());
                    }

                    // Simulate using the laboratory machine
                    Thread.sleep(1000);

                } finally {

                    // Release the machine for another technician
                    labEquipment.release();
                }
            }

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            System.out.println(technicianName +
                " has stopped working.");
        }
    }
}