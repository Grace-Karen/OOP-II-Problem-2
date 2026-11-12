import java.util.concurrent.BlockingQueue;

public class Nurse implements Runnable {

    // Attributes
    private PatientRepository repository;
    private BlockingQueue<LabRequest> labQueue;
    private int patientId;
    private String vitalSigns;
    private String nurseName;
    private String testName;

    // Constructor
    public Nurse(String nurseName, PatientRepository repository,
                 BlockingQueue<LabRequest> labQueue,
                 int patientId, String vitalSigns, String testName) {

        this.nurseName = nurseName;
        this.repository = repository;
        this.labQueue = labQueue;
        this.patientId = patientId;
        this.vitalSigns = vitalSigns;
        this.testName = testName;
    }

    // Task performed by the nurse
    @Override
    public void run() {

        Patient patient = repository.findPatient(patientId);

        if (patient != null) {

            // Update vital signs
            patient.updateVitalSigns(vitalSigns);

            System.out.println(nurseName +
                " updated vital signs for patient " + patientId);

            try {

                // Send a laboratory request to the queue
                LabRequest request = new LabRequest(patientId, testName);

                labQueue.put(request);

                System.out.println(nurseName +
                    " submitted a " + testName +
                    " request for patient " + patientId);

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

            }

        } else {

            System.out.println("Patient not found: " + patientId);
        }
    }
}
