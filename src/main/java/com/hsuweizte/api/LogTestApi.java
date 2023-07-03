package com.hsuweizte.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestApi {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/log")
    public String log() {
        String name = "hsuweizte";
        String email = "hsuweizte@gmail.com";
        int i = 4 / 0;
        logger.info("--------log------------");
        return "logtest";
    }
}
