package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HtmlController {

    Logger logger = LoggerFactory.getLogger(HtmlController.class);
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        logger.info("===============进入登录页========================");
        return "login/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        logger.info("===============进入注册页========================");
        return "login/register";
    }
}
