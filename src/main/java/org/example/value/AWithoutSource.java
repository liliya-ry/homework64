package org.example.value;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
public class AWithoutSource {
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
