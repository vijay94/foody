package com.vijay.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ApplicationProperties {
    @Autowired
    private Environment env;


    public String getJwtTokenName() {
        return env.getProperty("jwt.tokenname");
    }

    public String getJwtBearer() {
        return env.getProperty("jwt.bearer");
    }

    public byte[] getJwtSecret() {
        return env.getProperty("jwt.secret").getBytes();
    }

    public long getJwtExpirationTime() {

        return Long.parseLong(env.getProperty("jwt.expirytime"));
    }
}
