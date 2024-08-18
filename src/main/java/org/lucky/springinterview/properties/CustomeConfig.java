package org.lucky.springinterview.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.Map;

@ConfigurationProperties("custom.app")
@Component
@Getter
@Setter
public class CustomeConfig {

    private String prop1;
    private String prop2;
    private String prop3;
    private Map<String,ListProp> list;


    @Getter
    @Setter
    @ToString
    public static class ListProp{
        private String one1;
        private String one2;
        private String one3;

    }

}

