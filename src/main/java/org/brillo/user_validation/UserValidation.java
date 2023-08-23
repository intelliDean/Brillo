package org.brillo.user_validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserValidation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Date of Birth (YYYY-MM-DD): ");
        String dobString = scanner.nextLine();

        ValidationResult result = validateUser(username, email, password, dobString);

        if (result.isValid()) {
            System.out.println("All validations passed!");
            String jwtToken = generateJWT(username);
            System.out.println("Generated JWT: " + jwtToken);
            String verificationStatus = verifyJWT(jwtToken) ? "verification pass" : "verification fails";
            System.out.println("Token verification: " + verificationStatus);
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
        if (!isValidEmail(email)) {
            result.addValidationFailure("Invalid email: Please enter a valid email address\n");
        }
        if (password.isEmpty()) {
            result.addValidationFailure("Empty password: Passwords cannot be empty\n");
        }
        if (!isValidPassword(password)) {
            result.addValidationFailure("Invalid password: Password must contain at least 1 uppercase, " +
                    "1 lowercase, 1 special character, 1 digit, and 8 characters without whitespace\n");
        }
        if (dobString.isEmpty()) {
            result.addValidationFailure("Empty date of birth: Date of birth cannot be empty\n");
        }
        if (!isValidDOB(dobString)) {
            result.addValidationFailure("Under age: Age must be, at least, 16 years old\n");
        }
        return result;
    }

   public static boolean isValidEmail(String email) {
        // Email validation regex
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.compile(regex).matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        // Password validation regex: At least 1 uppercase, 1 special character, 1 digit, 8 characters
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return Pattern.compile(regex).matcher(password).matches();
    }

    public static boolean isValidDOB(String dobString) {
        try {
            LocalDate dob = LocalDate.parse(dobString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate minDOB = LocalDate.now().minusYears(16);
            return dob.isBefore(minDOB);
        } catch (Exception e) {
            return false;
        }
    }

    public static String generateJWT(String username) {
        // Logic to generate JWT goes here
        // For simplicity, return a mock JWT
        return "mock-jwt-token";
    }

    public static boolean verifyJWT(String token) {
        // Logic to verify JWT goes here
        // For simplicity, consider any non-empty token as valid
        return !token.isEmpty();
    }
}



