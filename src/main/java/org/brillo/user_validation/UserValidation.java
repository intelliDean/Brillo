package org.brillo.user_validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class UserValidation {
    public static void main(String[] args) {
       Input.UserInput input = Input.returnInput();
       String username = input.username();
        ValidationResult result = validateUser(
                username, input.email(), input.password(), input.dobString()
        );

        if (result.isValid()) {
            System.out.println("All validations passed!");
        } else {
            System.out.printf("""
                    Validation failed:
                    %s
                    """, result.getMessage());
        }
    }


    public static ValidationResult validateUser(String username, String email, String password, String dobString) {
        ValidationResult result = new ValidationResult();

        if (username.isEmpty()) {
            result.addValidationFailure("Empty username: Username cannot be empty\n");
        }
        if (username.length() < 4) {
            result.addValidationFailure("Short username: Username must be, at least, 4 characters long\n");
        }
        if (email.isEmpty()) {
            result.addValidationFailure("Empty email: Email address cannot be empty\n");
        }
        if (invalidEmail(email)) {
            result.addValidationFailure("Invalid email: Please enter a valid email address\n");
        }
        if (password.isEmpty()) {
            result.addValidationFailure("Empty password: Passwords cannot be empty\n");
        }
        if (invalidPassword(password)) {
            result.addValidationFailure("Invalid password: Password must contain at least 1 uppercase, " +
                    "1 lowercase, 1 special character, 1 digit, and 8 characters without whitespace\n");
        }
        if (dobString.isEmpty()) {
            result.addValidationFailure("Empty date of birth: Date of birth cannot be empty\n");
        }
        if (invalidDateOfBirth(dobString)) {
            result.addValidationFailure("Under age: Age must be, at least, 16 years old\n");
        }
        return result;
    }

   public static boolean invalidEmail(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return !Pattern.compile(regex).matcher(email).matches();
    }

    public static boolean invalidPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return !Pattern.compile(regex).matcher(password).matches();
    }

    public static boolean invalidDateOfBirth(String dobString) {
        try {
            LocalDate dateOfBirth = LocalDate.parse(
                    dobString, DateTimeFormatter.ofPattern("dd/MM/yyyy")
            );
            LocalDate minDOB = LocalDate.now().minusYears(16);
            return !dateOfBirth.isBefore(minDOB);
        } catch (Exception e) {
            return true;
        }
    }
}



