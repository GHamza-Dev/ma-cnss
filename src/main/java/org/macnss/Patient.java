package org.macnss;

public class Patient extends Person{
    private long mat;

    public Patient(String username,String email, String password,long mat){
        super(username,email,password);
        this.mat = mat;
    }
    public Patient(int id,String username,String email, String password,long mat){
        super(id,username,email,password);
        this.mat = mat;
    }

    public long getMat() {
        return mat;
    }

    public void setMat(int mat) {
        this.mat = mat;
    }


    @Override
    public String toString() {
        String output = "Patient ID: "+this.id+"\n";
        output+="MAT: "+this.mat+"\n";
        output+="Full name: "+this.username;
        return output;
    }
}
