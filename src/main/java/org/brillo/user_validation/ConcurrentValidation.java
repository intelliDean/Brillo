package org.brillo.user_validation;

import java.util.concurrent.CompletableFuture;

public class ConcurrentValidation {
    public static void main(String[] args) {
        Input.UserInput input = Input.returnInput();
        CompletableFuture<ValidationResult> usernameValidation =
                CompletableFuture.supplyAsync(() -> validateUsername(input.username()));
        CompletableFuture<ValidationResult> emailValidation =
                CompletableFuture.supplyAsync(() -> validateEmail(input.email()));
        CompletableFuture<ValidationResult> passwordValidation =
                CompletableFuture.supplyAsync(() -> validatePassword(input.password()));
        CompletableFuture<ValidationResult> dobValidation =
                CompletableFuture.supplyAsync(() -> validateDOB(input.dobString()));

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
        if (UserValidation.invalidDateOfBirth(dobString)) {
            result.addValidationFailure("Under age: Age must be, at least, 16 years old");
        }
        return result;
     }

     private static ValidationResult validatePassword(String password) {
        ValidationResult result = new ValidationResult();
          if (password.isEmpty()) {
            result.addValidationFailure("Empty password: Passwords cannot be empty");
        }
        if (UserValidation.invalidPassword(password)) {
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
        if (UserValidation.invalidEmail(email)) {
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
