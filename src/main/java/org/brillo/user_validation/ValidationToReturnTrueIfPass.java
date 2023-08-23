package org.brillo.user_validation;

import java.util.Scanner;

import static org.brillo.user_validation.UserValidation.validateUser;

public class ValidationToReturnTrueIfPass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Date of Birth (DD/MM/YYYY): ");
        String dobString = scanner.nextLine();

        ValidationResult result = validateUser(username, email, password, dobString);

        if (result.isValid()) {
            System.out.println(result.getMessage());
        } else {
            System.out.println(result.getMessage());
        }
    }
}