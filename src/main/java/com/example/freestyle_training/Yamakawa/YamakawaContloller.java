package com.example.freestyle_training.Yamakawa;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.freestyle_training.Taniyama.Account;
import com.example.freestyle_training.Taniyama.MemoManager;

import org.springframework.context.ApplicationContext;

import jakarta.servlet.ServletException;

import org.springframework.ui.Model;

@Controller
public class YamakawaContloller {
    @RequestMapping(path = "/urlInfomation")
    public String urlSummaryPageRequest(Account account, Model model) throws IOException {

        URLManager.getInstance().getUrlList(account);

        model.addAttribute("Account", account);

        return "Yamakawa/URLInfomation";
    }

    @RequestMapping(path = "/urlRevision")
    public String urlRevisionRageRequest(@ModelAttribute Account account, Model model) throws IOException {

        URLManager.getInstance().getUrlList(account);

        model.addAttribute("Account", account);

        return "Yamakawa/URLRevision";
    }

    @RequestMapping(path = "/urlPage")
    public String urlSettingPageRequest(@ModelAttribute Account account, Model model, int i, String NAME, String URL)
            throws IOException {

        if (NAME.length() == 0 || URL.length() == 0) {
            model.addAttribute("Account", account);
            model.addAttribute("i", i);
            model.addAttribute("URL", URL);
            model.addAttribute("NAME", NAME);
            model.addAttribute("errorMessage", "正しい入力がされていません");
            return "Yamakawa/URLChange";
        }

        URLManager.getInstance().urlSettingChange(account, NAME, URL, i);
        URLManager.getInstance().getUrlList(account);
        model.addAttribute("Account", account);

        return "Yamakawa/URLInfomation";
    }

    @RequestMapping(path = "/urlChange")
    public String urlSettingChangeRequest(@ModelAttribute Account account, Model model, int i, String NAME, String URL)
            throws IOException {
        System.out.println(i);
        model.addAttribute("Account", account);
        model.addAttribute("i", i);
        model.addAttribute("URL", URL);
        model.addAttribute("NAME", NAME);
        model.addAttribute("errorMessage", "");
        return "Yamakawa/URLChange";
    }
}
