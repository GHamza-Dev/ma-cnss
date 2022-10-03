package dialog;

import java.util.HashMap;
import java.util.Scanner;

public class Dialog {

    private static final Scanner scanner = new Scanner(System.in);

    public static HashMap<String ,String> promptForCredentials(){
        HashMap<String,String> credentials = new HashMap<>();

        System.out.print("Enter your email: ");
        credentials.put("email",scanner.nextLine());
        System.out.print("Enter your password: ");
        credentials.put("password",scanner.nextLine());

        return credentials;
    }
}
