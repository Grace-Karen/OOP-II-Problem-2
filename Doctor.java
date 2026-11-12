public class Doctor implements Runnable {

    // Attributes
    private PatientRepository repository;
    private int patientId;
    private String diagnosis;
    private String doctorName;

    // Constructor
    public Doctor(String doctorName, PatientRepository repository,
                  int patientId, String diagnosis) {

        this.doctorName = doctorName;
        this.repository = repository;
        this.patientId = patientId;
        this.diagnosis = diagnosis;
    }

    // Task performed by the doctor
    @Override
    public void run() {

        // Find the patient using their registration number
        Patient patient = repository.findPatient(patientId);

        if (patient != null) {

            // Update the patient's diagnosis
            patient.updateDiagnosis(diagnosis);

            System.out.println(doctorName +
                " updated the diagnosis for patient " + patientId);

        } else {

            System.out.println("Patient not found: " + patientId);
        }
    }

    // Retrieves a patient's laboratory results
    public void viewTestResults() {

        Patient patient = repository.findPatient(patientId);

        if (patient != null) {

            System.out.println("Laboratory results for patient "
                + patientId);

            patient.displayDetails();

        } else {

            System.out.println("Patient not found: " + patientId);
        }
    }
}