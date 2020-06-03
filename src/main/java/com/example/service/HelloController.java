package com.example.service;


import com.example.model.Name;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.logging.Level;

@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    //   private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final java.util.logging.Logger LOG =
            java.util.logging.Logger.getLogger(HelloController.class.getName());

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    @ApiOperation(value = "API to check health of service")
    public String health() {
        return "UP";
    }


    @RequestMapping(value = "/name", method = RequestMethod.POST)
    @ApiOperation(value = "API return concatenated response from input name")
    public ResponseEntity<String> helloName(@Valid @NotNull @RequestBody Name name) {

        String hello = helloService.getHello();
        String concatenatedName = helloService.getConcatenatedName(name);
        LOG.log(Level.INFO, "executed successfully");
        return new ResponseEntity<>(hello + " " + concatenatedName, HttpStatus.OK);
    }


}
