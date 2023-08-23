package org.brillo.user_validation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class ValidJwtTokenTest {

    @Test
    void validateUserInput() {
        String username = "Dean8ix";
        String email = "dean@gmail.com";
        String password = "Pass12Word!";
        String dateOfBirth = "12/09/1990";

        String jwtToken = ValidJwtToken.generateJwtToken(username, email, password, dateOfBirth);
        assertThat(jwtToken).isNotNull().isInstanceOf(String.class);
    }

    @Test
    void invalidInputCannotGenerateJwt() {
        String username = "de";
        String email = "dean@gmail.com";
        String password = "Pass12Word!";
        String dateOfBirth = "12/09/1990";

        String jwtToken = ValidJwtToken.generateJwtToken(username, email, password, dateOfBirth);
        assertThat(jwtToken).isNull();
    }

    @Test
    void validateJwtTokenGenerated() {
        String username = "Dean8ix";
        String email = "dean@gmail.com";
        String password = "Pass12Word!";
        String dateOfBirth = "12/09/1990";

        String jwtToken = ValidJwtToken.generateJwtToken(username, email, password, dateOfBirth);
        String response = ValidJwtToken.isJwtTokenValid(jwtToken);
        assertThat(response)
                .isNotNull()
                .isInstanceOf(String.class)
                .isEqualTo("Verification Pass!");
    }
    @Test
    void ifJwtTokenIsNotValidItWillNotBeValidated() {
        String username = "Dean8ix";
        String email = "dean@gmail.com";
        String password = "Pass12Word!";
        String dateOfBirth = "12/09/1990";

        String jwtToken = ValidJwtToken.generateJwtToken(username, email, password, dateOfBirth);
        String alteredToken = jwtToken + "alt12";
        String response = ValidJwtToken.isJwtTokenValid(alteredToken);
        assertThat(response)
                .isNotNull()
                .isInstanceOf(String.class)
                .isEqualTo("Verification Fails!");
    }
}