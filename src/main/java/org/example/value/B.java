package org.example.value;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;

@Component
@PropertySource("classpath:prop.properties")
public class B {
    String city;
    String user;

    @Autowired
    public B(@Value("${city}") String city) {
        this.city = city;
    }

    @Autowired
    public void setUser(@Value("${user}") String user) {
        this.user = user;
    }
}
