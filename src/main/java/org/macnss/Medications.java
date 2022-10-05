package org.macnss;

public class Medications extends Document{
    private float repayment;

    public Medications(int id, String name, float repayment) {
        super(id, name);
        this.repayment = repayment;
    }

    public float getRepayment() {
        return repayment;
    }

    public void setRepayment(float repayment) {
        this.repayment = repayment;
    }

    @Override
    public String toString() {
        return "Medications{" +
                "repayment=" + repayment +
                '}';
    }
}
