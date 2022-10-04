import menu.Menu;
import org.macnss.Admin;
import org.macnss.Agent;
import org.macnss.Patient;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        MaCnss maCnss = new MaCnss();

        Menu loginMenu = new Menu();
        char choice = '+';

        loginMenu.addChoice("login as admin");
        loginMenu.addChoice("login as agent");
        loginMenu.addChoice("login as patient");

        do {
            choice = loginMenu.promptChoice(scanner);
            switch (choice){
                case '0':{
                    System.out.println("GOOD BYE!");
                    return;
                }
                case '1':{
                    maCnss.setRole("admin");
                    Admin admin = maCnss.adminLogin();
                    if (admin == null) {
                        return;
                    }
                    maCnss.setPerson(admin);
                    choice = '0';
                }break;
                case '2':{
                    maCnss.setRole("agent");
                    Agent agent = maCnss.agentLogin();
                    if (agent == null) {
                        return;
                    }
                    maCnss.setPerson(agent);
                    choice = '0';
                }break;
                case '3':{
                    maCnss.setRole("patient");
                    Patient patient = maCnss.patientLogin();
                    if (patient == null) {
                        return;
                    }
                    maCnss.setPerson(patient);
                    choice = '0';
                }break;
                default:{
                    System.out.println("It seems like you are tired ;)");
                }
            }
        }while (choice != '0');

        if (maCnss.getRole().equals("admin")) {
            adminBoard(maCnss);
        } else if (maCnss.getRole().equals("agent")) {
            agentBoard(maCnss);
        }else if (maCnss.getRole().equals("patient")){
            patientBoard(maCnss);
        }else {
            System.out.println("No board found!");
        }

    }

    public static void agentBoard(MaCnss maCnss){
        char choice = '+';
        Menu agentMenu = new Menu("AGENT MENU");
        agentMenu.addChoice("add patient");

        do {
            choice = agentMenu.promptChoice(scanner);
            switch (choice){
                case '0':{
                    System.out.println("GOOD BYE!");
                }break;
                case '1':{
                    maCnss.addPatient();
                }break;
                default:{
                    System.out.println("It seems like you are tired ;)");
                }
            }
        }while (choice != '0');
    }

    public static void adminBoard(MaCnss maCnss){
        char choice = '+';
        Menu adminMenu = new Menu("ADMIN MENU");
        adminMenu.addChoice("create new agent");
        do {
            choice = adminMenu.promptChoice(scanner);
            switch (choice){
                case '0':{
                    System.out.println("GOOD BYE!");
                }break;
                case '1':{
                    maCnss.addAgent();
                }break;
                default:{
                    System.out.println("It seems like you are tired ;)");
                }
            }
        }while (choice != '0');

    }

    public static void patientBoard(MaCnss maCnss){
        char choice = '+';
        Menu patientMenu = new Menu("PATIENT MENU");
        patientMenu.addChoice("view history");
        do {
            choice = patientMenu.promptChoice(scanner);
            switch (choice){
                case '0':{
                    System.out.println("GOOD BYE!");
                }break;
                case '1':{
                    maCnss.patientCheckHistory();
                }break;
                default:{
                    System.out.println("It seems like you are tired ;)");
                }
            }
        }while (choice != '0');

    }

}

