package com.hsuweizte.api;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
public class TestApi {
    @PostMapping("/test")
    public String test(@RequestBody Map<String, String> email) {
        Date expireDate =
                //設定30min過期
                new Date(System.currentTimeMillis() + 30 * 60 * 1000);
        String jwtToken = Jwts.builder()
                .setSubject(email.get("email")) //我以email當subject
                .setExpiration(expireDate)
                //Secret是自訂的私鑰，HS512是自選的演算法，可以任意改變
                .signWith(SignatureAlgorithm.HS512, "Secret")
                .compact();
        //直接將JWT傳回
        return jwtToken;
    }

    @GetMapping("/test")
    public String test() {
        return "你好";
    }
}
