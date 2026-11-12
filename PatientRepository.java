public class PatientRepository {

    // Array that stores 100 patient objects
    private final Patient[] patients = new Patient[100];

    // Sample first names
    private String[] firstNames = {
        "Mary", "John", "Grace", "Peter", "Jane",
        "David", "Esther", "James", "Sarah", "Daniel"
    };

    // Sample surnames
    private String[] lastNames = {
        "Wanjiku", "Kamau", "Achieng", "Otieno", "Njeri",
        "Mwangi", "Omondi", "Wambui", "Kiptoo", "Mutua"
    };

    // Constructor: automatically loads 100 patients
    public PatientRepository() {
        loadPatients();
    }

    // Creates and stores the 100 patient records
    private void loadPatients() {

        for (int i = 0; i < 100; i++) {

            String name = firstNames[i % 10] + " "
                        + lastNames[i / 10];

            int patientId = 1001 + i;
            int age = 18 + (i % 63);

            patients[i] = new Patient(patientId, name, age);
        }
    }

    // Finds a patient using their hospital registration number
    public synchronized Patient findPatient(int patientId) {

        for (Patient patient : patients) {

            if (patient != null &&
                patientId == patient.getPatientId()) {

                return patient;
            }
        }

        return null;
    }

    // Displays all registered patients
    public synchronized void displayAllPatients() {

        for (Patient patient : patients) {

            patient.displayDetails();
            System.out.println("--------------------");
        }

        System.out.println("Total registered patients: " + patients.length);
    }
}