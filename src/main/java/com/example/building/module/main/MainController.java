package com.example.building.module.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    private static final String MAIN_TEMPLATE = "/module/main";

    @RequestMapping({ "/main", "/" })
    public String index() {
        return MAIN_TEMPLATE;
    }
}
