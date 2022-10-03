import db.AdminService;
import dialog.Prompt;
import org.macnss.Admin;
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
