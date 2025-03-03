package com.tet_store;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class indexController {
    @GetMapping("")
    public String index() {
        return "This is an API for the Tet Store flutter project";
    }

    @GetMapping("/about")
    public String about() {
        return "This app has been developed by Abzedizo Tetman";
    }
}
