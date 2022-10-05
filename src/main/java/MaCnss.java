import menu.Menu;
import org.macnss.*;
import service.AdminService;
import service.AgentService;
import service.MedicationService;
import service.PatientService;
import dialog.Prompt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static service.PatientService.getHistory;

public class MaCnss {
    private String role;
    private Person person;

    private Dossier listDossier;

    public MaCnss() {

    }

    public MaCnss(String role, Person person) {
        this.role = role;
        this.person = person;
    }

    public void addDossier() {
        Menu menu = new Menu("ADD DOSSIER");
        char choice = '+';
        menu.addChoice("add client");
        menu.addChoice("add doctor"); // doctor
        menu.addChoice("add medication");
        menu.addChoice("add analysis");
        menu.addChoice("add radio");
        menu.addChoice("confirm registration");

        Dossier dossier = new Dossier();

        do {
            choice = menu.promptChoice(new Scanner(System.in));
            switch (choice) {
                case '0': {
                    System.out.println("GOOD BYE!");
                    return;
                }
                case '1': {
                    System.out.println("[Add client...]");
                }
                break;
                case '2': {
                    System.out.println("[add doctor...]");
                }break;
                case '3': {
                    System.out.println("[add medication...]");
                    String med = Prompt.promptForMedicationSearch();
                    Medication medicationResult = MedicationService.searchMedication(med);
                    if (medicationResult == null) {
                        System.out.println("Medication not found!");
                    }else {
                        dossier.addMedication(medicationResult);
                    }
                }break;
                case '4': {
                    System.out.println("[add analysis...]");
                }break;
                case '5': {
                    System.out.println("[add radio...]");
                }break;
                case '6': {
                    System.out.println("[CONFIRM...]");
                }break;
                case '7': {
                    System.out.println("[DISPLAY DOSSIER...]");
                    System.out.println(dossier);
                }break;
                default: {
                    System.out.println("It seems like you are tired ;)");
                }
            }
        } while (choice != '0');

    }
    public void addAgent() {
        HashMap<String, String> agent = Prompt.promptForPerson();
        boolean added = AgentService.insertAgent(agent.get("username"), agent.get("email"), agent.get("password"));
        if (added) {
            System.out.println("Agent added successfully!");
            return;
        }

        System.out.println("Ops something went wrong while adding agent!");
    }

    public void addPatient() {
        HashMap<String, String> p = Prompt.promptForPerson();
        long mat = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        Patient patient = new Patient(p.get("username"), p.get("email"), p.get("password"), mat);

        boolean added = PatientService.insertPatient(patient);

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

    public void patientCheckHistory() {
        ArrayList<Dossier> dossier = getHistory(this.person.getId());
        if (dossier != null) {
            System.out.println(dossier);
        }


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
