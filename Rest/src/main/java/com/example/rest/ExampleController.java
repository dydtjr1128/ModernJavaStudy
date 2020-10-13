package com.example.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author : dydtjr1128
 * @project : Study
 * @github : https://github.com/dydtjr1128
 * @since : 2020-08-14
 */

@RestController
@RequestMapping("/api/v1/auth")
public class ExampleController{

    ExampleController() {
        System.out.println("Create constructor");
    }

    /*@GetMapping("/{sub_url}")
    public RedirectView defaultFunc(@PathVariable String sub_url) {
        System.out.println("Get default url");
        return new RedirectView(sub_url);
    }*/

    @RequestMapping("/example")
    public void example() {
        System.out.println("Get example url");
    }
}
