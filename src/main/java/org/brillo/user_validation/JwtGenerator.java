package org.brillo.user_validation;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

public class JwtGenerator {
    private static final String jwtToken =
            "OIDCu9SZ9U5LGqF6mW12K LuSxO70GPfqw3W2fh4uciXEi17bxij7229eNsVXRZnpsOa7pMb8gGbfJdl8YmnZ5uuaA43RPRH1v8720TWYUwAST90hzdLdzVYh54WXm44";
    private static final Key key = new SecretKeySpec(
            jwtToken.getBytes(), SignatureAlgorithm.HS512.getJcaName()
    );


    public static String generateToken( String email, int expiration) {
        return Jwts.builder()
                .setIssuer("Brillo")
                .setSubject(email)
                .signWith(SignatureAlgorithm.HS512, key)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(expiration)))
                .compact();
    }

    public static Boolean isValid(String token) {
        try {
            final Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();
            final Date expiration = claims.getExpiration();
            return expiration != null &&
                    expiration.after(Date.from(Instant.now()));
        } catch (JwtException e) {
            return false;
        }
    }
}
