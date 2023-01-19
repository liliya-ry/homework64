package org.example.value;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.*;

import java.util.List;
import java.util.Map;

@Component
@PropertySource("classpath:prop.properties")
public class C {
    @Value("#{systemProperties['user.name']}")
    String username;
    @Value("#{systemProperties['unknown'] ?: 'default'}")
    String defaultStr;
//    @Value("#{A.httpPort}")
//    int httpPort;
    @Value("#{'${listOfValues}'.split(',')}")
    List<String> valuesList;
    @Value("#{${valuesMap}}")
    Map<String, Integer> valuesMap;
    @Value("#{${valuesMap}.key1}")
    Integer valuesMapKey1;
    @Value("#{${valuesMap}['unknownKey']}")
    Integer unknownMapKey;
}
