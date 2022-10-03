import db.AdminService;
import db.AgentService;
import db.PatientService;
import dialog.Prompt;
import org.macnss.Admin;
import org.macnss.Agent;
import org.macnss.Patient;
import org.macnss.Person;

import java.util.HashMap;
import java.util.Scanner;

public class MaCnss {
    private String role;
    private Person person;

    public MaCnss() {

    }

    public MaCnss(String role, Person person) {
        this.role = role;
        this.person = person;
    }

    public void addAgent(){
        HashMap<String,String> agent = Prompt.promptForPerson();
        boolean added = AgentService.insertAgent(agent.get("username"),agent.get("email"),agent.get("password"));
        if (added) {
            System.out.println("Agent added successfully!");
            return;
        }

        System.out.println("Ops something went wrong while adding agent!");
    }

    public void addPatient(){
        HashMap<String,String> p = Prompt.promptForPerson();
        long mat = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        Patient patient = new Patient(p.get("username"),p.get("email"),p.get("password"),mat);

        boolean added = PatientService.insertAgent(patient);

        if (added) {
            System.out.println("Patient added successfully!");
            return;
        }

        System.out.println("Ops something went wrong while adding patient!");
    }

    public Admin adminLogin() {
        int attempts = 0;

        while (true) {

            if (attempts > 6) {
                System.out.println("You reached the...");
                return null;
            }

            HashMap<String, String> credentials = Prompt.promptForCredentials();
            Admin admin = AdminService.login(credentials.get("email"), credentials.get("password"));

            if (admin != null) {
                return admin;
            }

            System.out.println("Username or password does not match!");

            attempts++;
        }

    }

    public Agent agentLogin() {
        int attempts = 0;

        while (true) {

            if (attempts > 6) {
                System.out.println("You reached the...");
                return null;
            }

            HashMap<String, String> credentials = Prompt.promptForCredentials();
            Agent agent = AgentService.login(credentials.get("email"), credentials.get("password"));

            if (agent != null) {
                return agent;
            }

            System.out.println("Username or password does not match!");

            attempts++;
        }

    }

    public Patient patientLogin() {
        int attempts = 0;

        while (true) {

            if (attempts > 6) {
                System.out.println("You reached the...");
                return null;
            }

            HashMap<String, String> credentials = Prompt.promptForCredentialsPatient();
            Patient patient = PatientService.login(Integer.valueOf(credentials.get("mat")), credentials.get("password"));

            if (patient != null) {
                return patient;
            }

            System.out.println("Username or password does not match!");

            attempts++;
        }

    }

    public void patientCheckHistory(){
        System.out.println(this.person);

    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getRole() {
        return role;
    }

    public Person getPerson() {
        return person;
    }
}
