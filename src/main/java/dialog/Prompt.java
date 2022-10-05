package dialog;

import java.util.HashMap;
import java.util.Scanner;

public class Prompt {

    private static final Scanner scanner = new Scanner(System.in);

    public static String promptForMedicationSearch(){
        System.out.print("Enter medication name or bar code: ");
        return scanner.nextLine();
    }

    public static String promptForAnalysisName(){
        System.out.print("Enter analysis name: ");
        return scanner.nextLine();
    }
    public static HashMap<String ,String> promptForCredentials(){
        HashMap<String,String> credentials = new HashMap<>();

        System.out.print("Enter your email: ");
        credentials.put("email",scanner.nextLine());
        System.out.print("Enter your password: ");
        credentials.put("password",scanner.nextLine());

        return credentials;
    }

    public static HashMap<String ,String> promptForCredentialsPatient(){
        HashMap<String,String> credentials = new HashMap<>();

        System.out.print("Enter your registration number: ");
        credentials.put("mat",String.valueOf(scanner.nextInt()));
        System.out.print("Enter your password: ");
        credentials.put("password",scanner.next());

        return credentials;
    }

    public static HashMap<String,String> promptForPerson(){
        HashMap<String,String> person = new HashMap<>();

        System.out.print("Username: ");
        person.put("username",scanner.nextLine());
        System.out.print("Email: ");
        person.put("email",scanner.nextLine());
        System.out.print("Password: ");
        person.put("password",scanner.nextLine());

        return person;
    }
}
