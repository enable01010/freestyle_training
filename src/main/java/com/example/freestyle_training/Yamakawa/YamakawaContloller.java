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
    public String sample(Model model) throws IOException {
        
        return "Yamakawa/URLInfomation";        
    }

    @RequestMapping(path = "/urlRevision")
    public String urlRevisionRageRequest(@ModelAttribute Account account, Model model) throws IOException {
        
        /*URLManager.getInstance().getUrlList()*/

        model.addAttribute("Account", account);

        return "Yamakawa/URLRevision";
    }

    @RequestMapping(path = "/urlPage")
    public String urlSettingPageRequest(@ModelAttribute Account account, Model model) throws IOException {
        
        /*URLManager.getInstance().getUrlSetting()*/
                
        model.addAttribute("Account", account);

        return "Yamakawa/URLPage";
    }

    @RequestMapping(path = "/urlChange")
    public String urlSettingChangeRequest(@ModelAttribute Account account, Model model) throws IOException {
        
        /*URLManager.getInstance().urlSettingChange()*/
                
        model.addAttribute("Account", account);

        return "Yamakawa/URLChange";
    }
}
