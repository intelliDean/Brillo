package org.brillo.user_validation;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class ConcurrentValidation {
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

        CompletableFuture<ValidationResult> usernameValidation =
                CompletableFuture.supplyAsync(() -> validateUsername(username));
        CompletableFuture<ValidationResult> emailValidation =
                CompletableFuture.supplyAsync(() -> validateEmail(email));
        CompletableFuture<ValidationResult> passwordValidation =
                CompletableFuture.supplyAsync(() -> validatePassword(password));
        CompletableFuture<ValidationResult> dobValidation =
                CompletableFuture.supplyAsync(() -> validateDOB(dobString));

        CompletableFuture<Void> allValidations = CompletableFuture.allOf(
                usernameValidation, emailValidation, passwordValidation, dobValidation);

        allValidations.thenRun(() -> {
            ValidationResult usernameResult = usernameValidation.join();
            ValidationResult emailResult = emailValidation.join();
            ValidationResult passwordResult = passwordValidation.join();
            ValidationResult dobResult = dobValidation.join();

            if (usernameResult.isValid() && emailResult.isValid() && passwordResult.isValid() && dobResult.isValid()) {
                System.out.println("All validations passed");
            } else {
                System.out.println("Validation errors:");
                System.out.println(usernameResult.getMessage());
                System.out.println(emailResult.getMessage());
                System.out.println(passwordResult.getMessage());
                System.out.println(dobResult.getMessage());
            }
        });
        allValidations.join();
    }

     private static ValidationResult validateDOB(String dobString) {
        ValidationResult result = new ValidationResult();
        if (dobString.isEmpty()) {
            result.addValidationFailure("Empty date of birth: Date of birth cannot be empty");
        }
        if (!UserValidation.isValidDOB(dobString)) {
            result.addValidationFailure("Under age: Age must be, at least, 16 years old");
        }
        return result;
     }

     private static ValidationResult validatePassword(String password) {
        ValidationResult result = new ValidationResult();
          if (password.isEmpty()) {
            result.addValidationFailure("Empty password: Passwords cannot be empty");
        }
        if (!UserValidation.isValidPassword(password)) {
            result.addValidationFailure("Invalid password: Password must contain at least 1 uppercase, " +
                    "1 lowercase, 1 special character, 1 digit, and 8 characters without whitespace");
        }
        return result;
     }

     private static ValidationResult validateEmail(String email) {
        ValidationResult result = new ValidationResult();
         if (email.isEmpty()) {
            result.addValidationFailure("Empty email: Email address cannot be empty");
        }
        if (!UserValidation.isValidEmail(email)) {
            result.addValidationFailure("Invalid email: Please enter a valid email address");
        }
        return result;
     }

     private static ValidationResult validateUsername(String username) {
        ValidationResult result = new ValidationResult();
        if (username.isEmpty()) {
            result.addValidationFailure("Empty username: Username cannot be empty");
        }
        if (username.length() < 4) {
            result.addValidationFailure("Short username: Username must be, at least, 4 characters long");
        }
        return result;
     }
}
