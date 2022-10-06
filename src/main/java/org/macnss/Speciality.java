package org.macnss;

public class Speciality extends Document{
    private float repayment;
    private int medicationRefundable;

    public Speciality(int id, String name, float repayment, int medicationRefundable) {
        super(id, name);
        this.repayment = repayment;
        this.medicationRefundable = medicationRefundable;
    }

    public Speciality(String name, float repayment, int medicationRefundable) {
        super(name);
        this.repayment = repayment;
        this.medicationRefundable = medicationRefundable;
    }

    public float getRepayment() {
        return repayment;
    }

    public void setRepayment(float repayment) {
        this.repayment = repayment;
    }

    public int isMedicationRefundable() {
        return medicationRefundable;
    }

    public void setMedicationRefundable(int medicationRefundable) {
        this.medicationRefundable = medicationRefundable;
    }

    @Override
    public String toString() {
        String output ="Speciality ID: "+this.id+"\n";
        output+="Doctor speciality: "+this.name+"\n";
        output+="Expected repayment: "+this.repayment;
        return output;
    }
}
