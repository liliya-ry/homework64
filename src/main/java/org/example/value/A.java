package org.example.value;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;

@Component
@PropertySource("classpath:prop.properties")
public class A {
    @Value("some string")
    String strValue;
    @Value("${db.name}")
    String dbName;
    @Value("${http.port:80}")
    int httpPort;
    @Value("${user:lili}")
    String username;
    @Value("${username:defaultUser}")
    String usernameFromSystem;
}
