package org.macnss;

import service.AnalysisService;

import javax.swing.plaf.PanelUI;
import java.util.ArrayList;

public class Dossier {
    private int id;
    private float repayment;
    private String status;

    private Patient patient;
    private Speciality speciality;
    private ArrayList<Medication> medications;
    private ArrayList<Radio> radios;
    private ArrayList<Analysis> analyses;

    public Dossier(){
        this.medications = new ArrayList<>();
        this.radios = new ArrayList<>();
        this.analyses = new ArrayList<>();
    }

    public Dossier(int id, float repayment, String status, Patient patient, Speciality speciality, ArrayList<Medication> medications, ArrayList<Radio> radios, ArrayList<Analysis> analyses) {
        this.id = id;
        this.repayment = repayment;
        this.status = status;
        this.patient = patient;
        this.speciality = speciality;
        this.medications = medications;
        this.radios = radios;
        this.analyses = analyses;
    }

    public float calculateRepayment(){
        float repayment = 0;
        repayment+=this.speciality.getRepayment();
        repayment+=calculateAnalysisRepayment();
        repayment+=calculateRadioRepayment();

        if (this.speciality.isMedicationRefundable() == 1) {
            float medRepayment = calculateMedicationRepayment();
            repayment+=medRepayment;
        }
        return repayment;
    }

    public float calculateMedicationRepayment(){
        float repayment = 0;
        for (Medication medication: this.medications) {
            repayment+=medication.getRepayment();
        }
        return repayment;
    }

    public float calculateRadioRepayment(){
        float repayment = 0;
        for (Radio radio: this.radios) {
            repayment+=(radio.getPercentage()*radio.getPayedAmount())/100;
        }
        return repayment;
    }
    public float calculateAnalysisRepayment(){
        float repayment = 0;
        for (Analysis analysis: this.analyses) {
            repayment+=(analysis.getPercentage()*analysis.getPayedAmount())/100;
        }
        return repayment;
    }

    public ArrayList<Integer> getMedicationIds(){
        int size = this.medications.size();
        if (size == 0) {
            return null;
        }
        ArrayList<Integer> ids = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ids.add(this.medications.get(i).getId());
        }
        return ids;
    }
    public ArrayList<Integer> getRadioIds(){
        int size = this.radios.size();
        if (size == 0) {
            return null;
        }
        ArrayList<Integer> ids = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ids.add(this.radios.get(i).getId());
        }
        return ids;
    }

    public ArrayList<Integer> getAnalysisIds(){
        int size = this.analyses.size();
        if (size == 0) {
            return null;
        }
        ArrayList<Integer> ids = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ids.add(this.analyses.get(i).getId());
        }
        return ids;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getRepayment() {
        return repayment;
    }

    public void setRepayment(float repayment) {
        this.repayment = repayment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public ArrayList<Medication> getMedications() {
        return medications;
    }

    public void setMedications(ArrayList<Medication> medications) {
        this.medications = medications;
    }

    public void addMedication(Medication medication){
        this.medications.add(medication);
    }
    public void addAnalysis(Analysis analysis){
        this.analyses.add(analysis);
    }

    public void addRadio(Radio radio){
        this.radios.add(radio);
    }

    public ArrayList<Radio> getRadios() {
        return radios;
    }

    public void setRadios(ArrayList<Radio> radios) {
        this.radios = radios;
    }

    public ArrayList<Analysis> getAnalyses() {
        return analyses;
    }

    public void setAnalyses(ArrayList<Analysis> analyses) {
        this.analyses = analyses;
    }

    @Override
    public String toString() {
        String output = "***** Dossier N°: "+this.id+" *****\n";
        output+= "Status: "+this.status+"\n";

        if (this.status.equals("accepted")) {
            output+=" - Repayment: "+this.repayment+"\n";
        }

        output+= patient+"\n";

        if (this.speciality != null){
            output+="-------------------\n";
            output+= speciality+"\n";
        }

        if (this.medications.size() > 0) {
            output+="-------------------\n";
            for (Medication medication: this.medications) {
                output+=medication+"\n";
            }
        }

        if (this.radios.size() > 0) {
            output+="-------------------\n";
            for (Radio radio: this.radios) {
                output+=radio;
            }
        }

        if (this.analyses.size() > 0) {
            output+="-------------------\n";
            for (Analysis analysis: this.analyses) {
                output+=analysis;
            }
        }
        return output;
    }
}
