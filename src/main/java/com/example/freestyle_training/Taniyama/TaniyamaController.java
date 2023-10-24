package com.example.freestyle_training.Taniyama;

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
public class TaniyamaController {
    @RequestMapping(path = "/DebugStart")
    public String sample(Model model) throws IOException {
        return "Taniyama/DebugStart";
    }
}
