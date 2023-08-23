package org.brillo.user_validation;

public class ValidationResult {
     private boolean valid = true;
    private StringBuilder message = new StringBuilder();

    public void addValidationFailure(String reason) {
        valid = false;
        message.append(reason);
    }

    public boolean isValid() {
        return valid;
    }

    public String getMessage() {
        return message.toString();
    }
}
