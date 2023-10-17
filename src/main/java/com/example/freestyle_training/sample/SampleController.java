package com.example.freestyle_training.sample;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.ApplicationContext;

import jakarta.servlet.ServletException;

import org.springframework.ui.Model;

@Controller
public class SampleController {

    @Autowired
    private ApplicationContext context;

    @RequestMapping(path = "/sample")
    public String sample(Model model) throws ServletException, IOException {

        model.addAttribute("message", "Hello Spring Boot 3.0.");
        model.addAttribute("SampleInput", new SampleInput());

        return "sample/sample";
    }

    @RequestMapping(path = "/input")
    public String input(@ModelAttribute SampleInput input, Model model) {

        model.addAttribute("name", input.getName());
        model.addAttribute("pass", input.getPass());

        return "sample/output";
    }

    @RequestMapping(path = "/end")
    public void end(Model model) {
        SpringApplication.exit(context, () -> 0);
    }

}
