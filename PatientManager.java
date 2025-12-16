public class PatientManager {
    private User currentUser;
    private ArrayList<Patient> patients;
    private Patient viewedPatient;

    public PatientManager(User currentUser) {
        this.currentUser = currentUser;
        patients = loadPatients();

        sortPatientsById();

        if (currentUser instanceof Patient)
            viewedPatient = (Patient) currentUser;
    }

    public void viewMyProfile() {
        System.out.println(currentUser.displayProfile());
    }

    public void viewPatientById(int id) {
        if (currentUser instanceof Patient)
            throw new IllegalStateException("Patients cannot view other patients.");

        int index = binarySearch(id);
        if (index >= 0) {
            viewedPatient = patients.get(index);
            System.out.println(viewedPatient.displayProfile());
        } else {
            System.out.println("Patient not found.");
        }
    }

    public void editViewedPatient(String field, String newValue) {
        if (viewedPatient == null) return;

        switch (field.toLowerCase()) {
            case "password": viewedPatient.setPassword(newValue); break;
            case "name": viewedPatient.setName(newValue); break;
            case "email": viewedPatient.setEmail(newValue); break;
            case "notes": viewedPatient.setTreatmentNotes(newValue); break;
            default: System.out.println("Cannot edit that field."); return;
        }

        savePatientsToFile();
    }
}
