package org.macnss;

public class Radio  extends Document{
    private float percentage;

    public Radio(int id, String name, float percentage) {
        super(id, name);
        this.percentage = percentage;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}
