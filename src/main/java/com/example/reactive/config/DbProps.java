package com.example.reactive.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "db")
public class DbProps {

    private String host;
    private Integer port;
    private String name;
    private String user;
    private String pass;

}
