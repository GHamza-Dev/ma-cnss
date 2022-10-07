import mailService.SendingEmail;
import menu.Menu;
import org.macnss.*;
import service.*;
import dialog.Prompt;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MaCnss {
    private String role;
    private Person person;

    private ArrayList<Dossier> listDossier;

    public void setListDossier(ArrayList<Dossier> listDossier) {
        this.listDossier = listDossier;
    }

    public MaCnss() {
        this.listDossier = new ArrayList<>();
    }

    public MaCnss(String role, Person person) {
        this.role = role;
        this.person = person;
    }

    public void displayPatientDossiers(){
        ArrayList<Dossier> dossiers = new ArrayList<>();
        if (this.person == null) {
            System.out.println("Ops something went wrong please try again");
            return;
        }
        dossiers = DossierService.selectDossiers(this.person.getId());
        for (Dossier dossier: dossiers) {
            System.out.println(dossier);
        }
    }
    public void displayDossiers(){
        ArrayList<Dossier> dossiers = DossierService.selectDossiers("waiting");

        if (dossiers == null) {
            System.out.println("There are no dossiers to display!");
            return;
        }

        for (Dossier dossier: dossiers) {
            System.out.println(dossier);
        }
    }
    public void validateDossiers(){
        ArrayList<Dossier> dossiers = DossierService.selectDossiers("waiting");

        if (dossiers == null) {
            System.out.println("There are no dossiers to display!");
            return;
        }

        for (Dossier dossier: dossiers) {
            System.out.println(dossier);
        }

        int dId = Prompt.promptForDossierId();

        Dossier selectedDossier = dossiers.stream()
                .filter(dossier1 -> dossier1.getId() == dId)
                .findAny()
                .orElse(null);

        if (selectedDossier == null) {
            System.out.println("Please enter a valid dossier id!!!");
            return;
        }

        System.out.println(selectedDossier);

        HashMap<String,String> decision = new HashMap<>();
        decision.put("a","accepted");
        decision.put("b","rejected");

        System.out.println("Click [a] to confirm repayment");
        System.out.println("Click [b] to reject repayment");
        System.out.print("Answer: ");

        String answer = new Scanner(System.in).nextLine();
        boolean ok = DossierService.setDossierStatus(dId,decision.get(answer));

        if (ok) {
            System.out.println("[SUCCESS] : Dossier status changed successfully");
            SendingEmail.send(selectedDossier.getPatient().getEmail(),"State of cnss request","Your repayment request has been "+ decision.get(answer));
            return;
        }

        System.out.println("[FAILED] : Ops something went wrong while changing dossier status");
    }

    public void addDossier() {
        Menu menu = new Menu("ADD DOSSIER");
        char choice = '+';
        menu.addChoice("add client");
        menu.addChoice("add doctor"); // doctor
        menu.addChoice("add medication");
        menu.addChoice("add analysis");
        menu.addChoice("add radio");
        menu.addChoice("display dossier");
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
                    int pat = Prompt.promptForPatientSearch();
                    Patient patientResult = PatientService.searchPatient(pat);
                    if (patientResult == null) {
                        System.out.println("Patient not found!");
                    }else {
                        dossier.setPatient(patientResult);
                    }
                }
                break;
                case '2': {
                    System.out.println("[add doctor...]");
                    String speciality = Prompt.promptForSpecialitySearch();
                    Speciality specialityResult = SpecialityService.searchSpeciality(speciality);
                    if (specialityResult == null) {
                        System.out.println("Speciality not found!");
                    }else {
                        dossier.setSpeciality(specialityResult);
                    }
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
                    String name = Prompt.promptForAnalysisName();
                    Analysis analysis = AnalysisService.searchAnalysis(name);
                    if (analysis == null) {
                        System.out.println("Analysis not found!");
                    }else {
                        float payedAmt = Prompt.promptForPayedAmount();
                        analysis.setPayedAmount(payedAmt);
                        dossier.addAnalysis(analysis);
                    }
                }break;
                case '5': {
                    System.out.println("[add radio...]");
                    String name = Prompt.promptForRadioName();
                    Radio radio = RadioService.searchRadio(name);
                    if (radio == null) {
                        System.out.println("Radio not found!");
                    }else {
                        float payedAmt = Prompt.promptForPayedAmount();
                        radio.setPayedAmount(payedAmt);
                        dossier.addRadio(radio);
                    }
                }break;
                case '6': {
                    System.out.println("[DISPLAY DOSSIER...]");
                    System.out.println(dossier);
                }break;
                case '7': {
                    boolean ok = true;
                    System.out.println("[CONFIRMATION...]");
                    if (dossier.getPatient() == null) {
                        System.out.println("[ERROR] : You have not entered a patient yet!");
                        ok = false;
                    }
                    if (dossier.getSpeciality() == null) {
                        System.out.println("[ERROR] : You have not entered a speciality yet!");
                        ok = false;
                    }
                    if (ok) {
                        float repayment = dossier.calculateRepayment();
                        dossier.setRepayment(repayment);
                        DossierService.insertDossier(dossier);
                    }
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
                int code = MaCnss.generateCode();
                LocalTime time = LocalTime.now();
                SendingEmail.send(credentials.get("email"),"Confirmation code","Your confirmation code is:"+ code);
                System.out.print("Enter confirmation code: ");
                Scanner scanner = new Scanner(System.in);
                int confirmationCode = scanner.nextInt();
                if (confirmationCode == code && LocalTime.now().compareTo(time.plusMinutes(5)) < 1){
                    return agent;
                }else {
                    System.out.println("Confirmation code invalid");
                }
            }else {
                System.out.println("Username or password does not match!");
            }


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

    public static int generateCode() {
        int b = (int)(Math.random()*(9999-0+1)+0);
        return b;
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
