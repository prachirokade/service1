package com.example.service;

import com.example.model.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author prokade
 */
@Service
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger LOG = Logger.getLogger(HelloService.class.getName());


    public String getHello() {
        LOG.log(Level.INFO, "calling rest api: http://localhost:8082/hello");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        return restTemplate.exchange("http://helloservice2-env.eba-dici3izg.us-east-2.elasticbeanstalk.com/hello",
                HttpMethod.GET, entity, String.class).getBody();
    }

    public String getConcatenatedName(Name name) {
        LOG.log(Level.INFO, "calling rest api: http://localhost:8083/concatenate");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<Name> entity = new HttpEntity<Name>(name, headers);

        return restTemplate.exchange("http://loggingservice-env.eba-nbmpywmx.us-east-2.elasticbeanstalk.com/concatenate",
                HttpMethod.POST, entity, String.class).getBody();
    }
}
