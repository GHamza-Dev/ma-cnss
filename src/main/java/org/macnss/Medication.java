package org.macnss;

public class Medication extends Document{
    private String barcode;
    private float repayment;

    public Medication(int id,String barcode, String name, float repayment) {
        super(id, name);
        this.barcode = barcode;
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
        String output = "Medication ID: "+this.id+"\n";
        output+="Bar code: "+this.barcode+"\n";
        output+="Expected repayment: "+this.repayment;
        return output;
    }
}
