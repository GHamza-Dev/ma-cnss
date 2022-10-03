package menu;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private String title;
    private ArrayList<String> choices;
    private int nbrOfChoices;

    public Menu() {
        this.title = "MENU";
        this.choices = new ArrayList<>();
        this.nbrOfChoices = 1;
    }

    public Menu(String title) {
        this.title = title;
        this.choices = new ArrayList<>();
        this.nbrOfChoices = 1;
    }

    public void addChoice(String choice){
        String msg = "Enter "+nbrOfChoices+": to ";
        this.choices.add(msg+choice+".");
        nbrOfChoices++;
    }

    public void printMenu(){
        System.out.println("***** ---- "+this.title+" ---- *****");
        System.out.println("Enter 0: to exit the application.");
        for (int i = 0; i < choices.size(); i++) {
            System.out.println(choices.get(i));
        }
        System.out.println("***** ---- ---- ---- *****");
    }

    public char promptChoice(Scanner scanner) {
        printMenu();
        System.out.print("\nYour choice: ");
        return scanner.next().charAt(0);
    }
}
