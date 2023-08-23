package org.brillo.user_validation;

import java.util.Scanner;

import static org.brillo.user_validation.UserValidation.validateUser;

public class GenerateTokenAfterValidation {

    public static void main(String[] args) {
        System.out.println(
                generateJwtAfterValidation()
        );
    }
    private static String generateJwtAfterValidation() {
        Input.UserInput input = Input.returnInput();
        String email = input.email();
        ValidationResult result = validateUser(
                input.username(),email , input.password(), input.dobString()
        );

        if (result.isValid()) {
            return JwtGenerator.generateToken(email, 3600);
        }
        return result.getMessage();
    }
}
