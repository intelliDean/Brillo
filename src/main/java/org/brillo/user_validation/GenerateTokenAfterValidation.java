package org.brillo.user_validation;

import java.util.Map;
import java.util.Scanner;

import static org.brillo.user_validation.UserValidation.validateUser;

public class GenerateTokenAfterValidation {

    public static void main(String[] args) {
        System.out.println(
                generateJwtAfterValidation()
        );
    }

    private static String generateJwtAfterValidation() {
         Scanner scanner = new Scanner(System.in);

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Date of Birth (DD/MM/YYY): ");
        String dobString = scanner.nextLine();

        ValidationResult result = validateUser(username, email, password, dobString);

        if (result.isValid()) {
            return JwtGenerator.generateToken( email, 3600);
        }
        return result.getMessage();
    }
}
