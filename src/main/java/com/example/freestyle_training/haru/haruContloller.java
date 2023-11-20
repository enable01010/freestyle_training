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
    public static String urlAddPageRequest(@ModelAttribute Account account, Model model, ErrorMessage errorLog,
            String tagError)
            throws IOException {
        UrlInfomation urlInfo = new UrlInfomation();
        if (errorLog == null)
            errorLog = new ErrorMessage();

        if (tagError == null)
            tagError = "";

        URLManager.getInstance().getTag(account);
        urlInfo.setTag(account.getTagList());

        model.addAttribute("Account", account);
        model.addAttribute("UrlInfomation", urlInfo);
        model.addAttribute("ErrorMessage", errorLog);
        model.addAttribute("tagdata", "");
        model.addAttribute("errorLog", tagError);
        return "Hirata/addurl";
    }

    @RequestMapping(path = "/submit")
    public String urlAddRequest(@ModelAttribute Account account, Model model, @ModelAttribute UrlInfomation urlInfo)
            throws IOException {

        if (urlInfo.getNameUrl().equals("") || urlInfo.getUrl().equals("")) {
            ErrorMessage errorLog = new ErrorMessage();
            errorLog.setErrorLog("名前またはURLの入力が不十分です");
            return urlAddPageRequest(account, model, errorLog, null);
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
