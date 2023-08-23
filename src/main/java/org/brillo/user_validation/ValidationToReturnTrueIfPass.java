package org.brillo.user_validation;

import static org.brillo.user_validation.UserValidation.validateUser;

public class ValidationToReturnTrueIfPass {
    public static void main(String[] args) {
       Input.UserInput input = Input.returnInput();
        ValidationResult result = validateUser(
                input.username(), input.email(), input.password(), input.dobString()
        );
        if (result.isValid()) {
            System.out.println(result.getMessage());
        } else {
            System.out.println(result.getMessage());
        }
    }
}