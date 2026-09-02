package me.sukibei;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/hi")
    public String hi() {
        return "Hello World";
    }


        @GetMapping("/test")
        public String test() {
            return "Hello World";
        }
}
