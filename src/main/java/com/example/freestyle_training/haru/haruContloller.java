package com.example.freestyle_training.haru;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.freestyle_training.Taniyama.Account;
import com.example.freestyle_training.Taniyama.UrlInfomation;
import com.example.freestyle_training.haru.UrlDelete;

import org.springframework.context.ApplicationContext;

import jakarta.servlet.ServletException;

import org.springframework.ui.Model;

@Controller
public class haruContloller {
    @RequestMapping(path = "/addurl")
    public String addrulPageRequest(@ModelAttribute Account account, Model model) throws IOException {
        UrlInfomation urlInfo = new UrlInfomation();
        ErrorMessage errorLog = new ErrorMessage();

        urlInfo.addTag("朝礼");
        urlInfo.addTag("夕礼");
        urlInfo.addTag("ギャザー");

        model.addAttribute("Account", account);
        model.addAttribute("UrlInfomation", urlInfo);
        model.addAttribute("ErrorMessage", errorLog);
        return "Hirata/addurl";
    }

    @RequestMapping(path = "/submit")
    public String submitData(@ModelAttribute Account account, Model model, @ModelAttribute UrlInfomation urlInfo,
            @ModelAttribute ErrorMessage errorLog)
            throws IOException {
        urlInfo.addTag("朝礼");
        urlInfo.addTag("夕礼");
        urlInfo.addTag("ギャザー");

        try {
            FileWriter fw = new FileWriter("src\\main\\db\\UrlData.txt", true);
            PrintWriter pw = new PrintWriter(new BufferedWriter(fw));

            if (urlInfo.getName().equals("") || urlInfo.getUrl().equals("")) {
                pw.close();
                errorLog.setErrorLog("名前またはURLの入力が不十分です");
                urlInfo.setName(null);
                urlInfo.setUrl(null);
                model.addAttribute("Account", account);
                model.addAttribute("UrlInfomation", urlInfo);
                model.addAttribute("ErrorMessage", errorLog);
                return "Hirata/addurl";
            }
            pw.write(urlInfo.getName());
            pw.write(urlInfo.getUrl());

            for (int i = 0; i < urlInfo.getCheckTag().size(); i++) {
                pw.write(urlInfo.getCheckTag().get(i));
            }
            pw.write(" \r\n");
            urlInfo.clearCheckTag();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("Account", account);
        return "Taniyama/DebugStart";
    }

    @RequestMapping(path = "/deleteurl")
    public String deleterulPageRequest(@ModelAttribute Account account, Model model) throws IOException {
        UrlInfomation urlInfo = new UrlInfomation();
        UrlDelete urldelete = new UrlDelete();

        model.addAttribute("Account", account);
        model.addAttribute("UrlInfomation", urlInfo);
        model.addAttribute("UrlDelete", urldelete);
        return "Hirata/urldelete";
    }

    @RequestMapping(path = "/delete")
    public String deleteurl(@ModelAttribute Account account, Model model, @ModelAttribute UrlDelete urlDelete)
            throws IOException {
        urlDelete.delete();

        model.addAttribute("Account", account);
        return "Taniyama/DebugStart";
    }
}
