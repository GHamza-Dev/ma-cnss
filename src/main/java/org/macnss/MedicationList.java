package org.macnss;

import java.util.ArrayList;

public class MedicationList {
    private ArrayList<Medication> medications;

    public MedicationList(){
        this.medications = new ArrayList<>();
    }

    public void addMedication(Medication medication){
        this.medications.add(medication);
    }

    public ArrayList<Medication> getMedications() {
        return medications;
    }

}
