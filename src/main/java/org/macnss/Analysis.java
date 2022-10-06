package org.macnss;

public class Analysis extends Document{
    private float percentage;
    private float payedAmount;

    public Analysis(int id, String name, float percentage) {
        super(id, name);
        this.percentage = percentage;
    }

    public float getPayedAmount() {
        return payedAmount;
    }

    public void setPayedAmount(float payedAmount) {
        this.payedAmount = payedAmount;
    }

    public Analysis(int id, String name, float percentage, float payedAmount) {
        super(id, name);
        this.percentage = percentage;
        this.payedAmount = payedAmount;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        String output = "Analysis ID: "+this.id+"\n";
        output+="Analysis name: "+this.name+"\n";
        output+="Repayment percentage: "+this.percentage;
        return output;
    }
}
