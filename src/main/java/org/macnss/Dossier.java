package org.macnss;

public class Dossier {
    private int id;
    private int patientId;
    private String status;
    private int repayment;

    public Dossier(int id, int patientId) {
        this.id = id;
        this.patientId = patientId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRepayment() {
        return repayment;
    }

    public void setRepayment(int repayment) {
        this.repayment = repayment;
    }

    @Override
    public String toString() {
        return "Dossier{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", status='" + status + '\'' +
                ", repayment=" + repayment +
                '}';
    }
}
