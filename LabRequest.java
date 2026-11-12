public class LabRequest {

    // Attributes
    private int patientId;
    private String testName;

    // Constructor
    public LabRequest(int patientId, String testName) {
        this.patientId = patientId;
        this.testName = testName;
    }

    // Getter for patient ID
    public int getPatientId() {
        return patientId;
    }

    // Getter for test name
    public String getTestName() {
        return testName;
    }
}