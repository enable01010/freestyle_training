package com.example.freestyle_training.haru;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.freestyle_training.Taniyama.Account;
import com.example.freestyle_training.Taniyama.UrlInfomation;
import com.example.freestyle_training.Yamakawa.URLManager;

import org.springframework.ui.Model;

@Controller
public class haruContloller {
    @RequestMapping(path = "/addurl")
    public String urlAddPageRequest(@ModelAttribute Account account, Model model) throws IOException {
        UrlInfomation urlInfo = new UrlInfomation();
        ErrorMessage errorLog = new ErrorMessage();

        URLManager.getInstance().getTag(account);

        model.addAttribute("Account", account);
        model.addAttribute("UrlInfomation", urlInfo);
        model.addAttribute("ErrorMessage", errorLog);
        return "Hirata/addurl";
    }

    @RequestMapping(path = "/submit")
    public String urlAddRequest(@ModelAttribute Account account, Model model, @ModelAttribute UrlInfomation urlInfo,
            @ModelAttribute ErrorMessage errorLog)
            throws IOException {

        if (urlInfo.getNameUrl().equals("") || urlInfo.getUrl().equals("")) {
            errorLog.setErrorLog("名前またはURLの入力が不十分です");
            urlInfo.setNameUrl(null);
            urlInfo.setUrl(null);
            model.addAttribute("Account", account);
            model.addAttribute("UrlInfomation", urlInfo);
            model.addAttribute("ErrorMessage", errorLog);
            return "Hirata/addurl";
        }

        URLManager.getInstance().addUrl(account, urlInfo);

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
    public String urlSettingDeleteRequest(@ModelAttribute Account account, Model model,
            @ModelAttribute UrlDelete urlDelete)
            throws IOException {
        urlDelete.delete();

        model.addAttribute("Account", account);
        return "Taniyama/DebugStart";
    }
}
