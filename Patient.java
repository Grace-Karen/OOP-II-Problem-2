public class Patient {

    // Patient attributes
    private int patientId;
    private String name;
    private String testResults;
    private int age;
    private String diagnosis;
    private String vitalSigns;

    // Constructor
    public Patient(int patientId, String name, int age) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
    }

    // Getter: returns the patient's registration number
    public int getPatientId() {
        return patientId;
    }

    // Method to update diagnosis
    public synchronized void updateDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    // Method to update vital signs
    public synchronized void updateVitalSigns(String vitalSigns) {
        this.vitalSigns = vitalSigns;
    }

    // Updates the patient's laboratory results
    public synchronized void updateTestResults(String testResults) {
        this.testResults = testResults;
    }

    // Method to display patient information
    public synchronized void displayDetails() {
        System.out.println("Patient ID: " + patientId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Diagnosis: " + diagnosis);
        System.out.println("Vital Signs: " + vitalSigns);
        System.out.println("Test Results: " + testResults);
    }
}