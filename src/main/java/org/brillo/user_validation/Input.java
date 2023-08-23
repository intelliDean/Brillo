package org.brillo.user_validation;

import java.util.Scanner;

public class Input {
    public static void main(String[] args) {
        System.out.println(returnInput());
    }

    public static UserInput returnInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Date of Birth (DD/MM/YYYY): ");
        String dobString = scanner.nextLine();

        return new UserInput(username, email, password, dobString);
    }

    public record UserInput(
            String username,
            String email,
            String password,
            String dobString
    ) {}
}



