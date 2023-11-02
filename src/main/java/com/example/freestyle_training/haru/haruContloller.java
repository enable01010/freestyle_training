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
    public String addrulPageRequest(@ModelAttribute Account account, Model model) throws IOException {
        UrlInfomation urlInfo = new UrlInfomation();

        urlInfo.addTag("朝礼");
        urlInfo.addTag("夕礼");
        urlInfo.addTag("ギャザー");

        model.addAttribute("Account", account);
        model.addAttribute("UrlInfomation", urlInfo);
        model.addAttribute("SelectTag", new SelectTag());
        return "Hirata/addurl";
    }

    @RequestMapping(path = "/submit")
    public void submitData(@ModelAttribute Account account, Model model, @ModelAttribute UrlInfomation urlInfo)
            throws IOException {
        System.out.println(urlInfo.getName());
        System.out.println(urlInfo.getUrl());
        for (int i = 0; i < urlInfo.getTag().size(); i++) {
            System.out.println(urlInfo.getTag().get(i));
        }

    }

}
