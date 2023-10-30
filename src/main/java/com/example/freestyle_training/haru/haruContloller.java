package com.example.freestyle_training.haru;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.freestyle_training.Taniyama.Account;
import com.example.freestyle_training.Taniyama.UrlInfomation;

import org.springframework.context.ApplicationContext;

import jakarta.servlet.ServletException;

import org.springframework.ui.Model;

@Controller
public class haruContloller {
    @RequestMapping(path = "/addurl")
    public String sample(@ModelAttribute Account account, Model model) throws IOException {
        UrlInfomation urlInfo = new UrlInfomation();
        model.addAttribute("Account", account);
        // urlInfo.addTag(urlInfo.tag_Str);
        return "Hirata/addurl";
    }

}
