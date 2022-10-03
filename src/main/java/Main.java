import db.AdminService;
import menu.Menu;
import org.macnss.Admin;
import org.macnss.Agent;

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
                }break;
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
                    System.out.println("Agent login.....");
                    maCnss.setRole("agent");
                    Agent agent = maCnss.agentLogin();
                    if (agent == null) {
                        return;
                    }
                    maCnss.setPerson(agent);
                    choice = '0';
                }break;
                case '3':{
                    System.out.println("Patient login.....");
                }break;
                default:{
                    System.out.println("It seems like you are tired ;)");
                }
            }
        }while (choice != '0');

        if (maCnss.getRole().equals("admin")) {
            adminBoard();
        } else if (maCnss.getRole().equals("agent")) {
            
        } else if (true) {
            System.out.println("another board");
        }

    }

    public static void adminBoard(){
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
                    System.out.println("adding agent...");
                }break;
                default:{
                    System.out.println("It seems like you are tired ;)");
                }
            }
        }while (choice != '0');

    }

}

