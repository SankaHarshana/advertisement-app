package com.example.advertisementapp.common;

import com.example.advertisementapp.manager.JWTManager;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;

@Service
public class JWTGeneratorService {

    private final JWTManager jwtManager;

    @Autowired
    public JWTGeneratorService(JWTManager jwtManager) {
        this.jwtManager = jwtManager;
    }


    public String getNewToken(String email, Long userId){
        OffsetDateTime utc = OffsetDateTime.now(ZoneOffset.UTC);
        Date issueDate = Date.from(utc.toInstant());
        Date expireDate = new Date(issueDate.getTime() + 2 * 3600*1000);

        String jwtToken = Jwts.builder().setSubject(email)
                .claim("email", email)
                .claim("user_id",userId)
                .setIssuedAt(issueDate).setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, jwtManager.getSecretKey()).compact();

        return jwtToken;
    }

    public String getRefreshToken(String email, Long userId){
        OffsetDateTime utc = OffsetDateTime.now(ZoneOffset.UTC);
        Date issueDate = Date.from(utc.toInstant());

        Calendar expireDate = Calendar.getInstance();
        expireDate.setTime(issueDate);
        expireDate.add(Calendar.MONTH,6);

        String jwtToken = Jwts.builder()
                .setSubject(email)
                .claim("email", email)
                .claim("user_id", userId)
                .setIssuedAt(issueDate).setExpiration(expireDate.getTime())
                .signWith(SignatureAlgorithm.HS256, jwtManager.getSecretKey()).compact();
        return jwtToken;
    }
}
