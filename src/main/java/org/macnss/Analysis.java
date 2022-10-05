package org.macnss;

public class Analysis extends Document{
    private float percentage;

    public Analysis(int id, String name, float percentage) {
        super(id, name);
        this.percentage = percentage;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "Analysis{" +
                "percentage=" + percentage +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
