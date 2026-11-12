public class Pharmacist implements Runnable {

    // Attributes
    private String pharmacistName;
    private PatientRepository repository;
    private MedicineInventory inventory;
    private int patientId;
    private int doses;

    // Constructor
    public Pharmacist(String pharmacistName,
                      PatientRepository repository,
                      MedicineInventory inventory,
                      int patientId, int doses) {

        this.pharmacistName = pharmacistName;
        this.repository = repository;
        this.inventory = inventory;
        this.patientId = patientId;
        this.doses = doses;
    }

    // Task performed by the pharmacist
    @Override
public void run() {

    // Find the patient
    Patient patient = repository.findPatient(patientId);

    if (patient != null) {

        System.out.println(pharmacistName +
            " is dispensing medicine to patient " + patientId);

        // Request medicine from the inventory
        boolean successful = inventory.dispenseMedicine(doses);

        if (successful) {
            System.out.println("Medicine successfully dispensed to patient "
                + patientId);
        } else {
            System.out.println("Medicine could not be dispensed to patient "
                + patientId);
        }

    } else {

        System.out.println("Patient not found: " + patientId);
    }
}
}