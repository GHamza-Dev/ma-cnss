package org.macnss;

public class Radio extends Document{
    private float percentage;
    private float payedAmount;

    public Radio(int id, String name, float percentage) {
        super(id, name);
        this.percentage = percentage;
    }
    public Radio(int id, String name, float percentage,float payedAmount) {
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

    public float getPayedAmount() {
        return payedAmount;
    }

    public void setPayedAmount(float payedAmount) {
        this.payedAmount = payedAmount;
    }
}
