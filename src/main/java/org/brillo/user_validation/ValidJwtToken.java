package org.brillo.user_validation;

import static org.brillo.user_validation.UserValidation.validateUser;

public class ValidJwtToken {
    public static void main(String[] args) {
       Input.UserInput input = Input.returnInput();

        String jwtToken = generateJwtToken(
                input.username(), input.email(), input.password(), input.dobString()
        );
        String valid = isJwtTokenValid(jwtToken);
        System.out.println(valid);
    }
    public static String generateJwtToken(String username, String email, String password, String dobString) {
        ValidationResult result = validateUser(username, email, password, dobString);
        if (result.isValid()) {
            return JwtGenerator.generateToken( email, 3600);
        }
        return null;
    }
    public static String isJwtTokenValid(String token) {

        if (token != null) {
            if (JwtGenerator.isValid(token)) {
                return "Verification Pass!";
            }
        }
        return "Verification Fails!";
    }
}
