package org.macnss;

public class Speciality extends Document{
    private int repayment;
    private boolean medicationRefundable;

    public Speciality(int id, String name, int repayment, boolean medicationRefundable) {
        super(id, name);
        this.repayment = repayment;
        this.medicationRefundable = medicationRefundable;
    }

    public int getRepayment() {
        return repayment;
    }

    public void setRepayment(int repayment) {
        this.repayment = repayment;
    }

    public boolean isMedicationRefundable() {
        return medicationRefundable;
    }

    public void setMedicationRefundable(boolean medicationRefundable) {
        this.medicationRefundable = medicationRefundable;
    }
}
