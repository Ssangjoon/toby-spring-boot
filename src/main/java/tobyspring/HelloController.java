package tobyspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(String name) {

        return "hello" + name;
    }
}