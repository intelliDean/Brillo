package org.brillo.user_validation;

public class ValidationResult {
     private boolean valid = true;
    private final StringBuilder message = new StringBuilder();

    public void addValidationFailure(String reason) {
        valid = false;
        message.append(reason);
    }

    public boolean isValid() {
        if (valid) {
            message.append(true);
        }
        return valid;
    }

    public String getMessage() {
        return message.toString();
    }
}
