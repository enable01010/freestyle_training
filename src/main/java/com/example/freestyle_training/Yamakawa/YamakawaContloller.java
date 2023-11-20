package com.example.freestyle_training.Yamakawa;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.freestyle_training.Taniyama.Account;
import com.example.freestyle_training.Taniyama.UrlInfomation;

import org.springframework.ui.Model;

import java.util.List;
import java.util.ArrayList;

@Controller
public class YamakawaContloller {
    @RequestMapping(path = "/urlInfomation")
    public static String urlSummaryPageRequest(Account account, Model model) throws IOException {

        // Urlの取得
        URLManager.getInstance().getUrlList(account);

        // タグの取得
        URLManager.getInstance().getTag(account);
        Map<String, String> selecttagMap = new LinkedHashMap<String, String>();
        for (int i = 0; i < account.getTagList().size(); i++) {
            selecttagMap.put(Integer.valueOf(i).toString(), account.getTagList().get(i));
        }

        // HTMLへ送信する情報の取りまとめ
        model.addAttribute("Account", account);
        model.addAttribute("tagdata", "");
        model.addAttribute("selectItems", selecttagMap);

        return "Yamakawa/URLInfomation";
    }

    @RequestMapping(path = "/urlRevision")
    public String urlRevisionPageRequest(@ModelAttribute Account account, Model model) throws IOException {

        URLManager.getInstance().getUrlList(account);

        model.addAttribute("Account", account);

        return "Yamakawa/URLRevision";
    }

    @RequestMapping(path = "/urlChange")
    public String urlSettingPageRequest(Model model, int i, String NAME, String URL, String AccountName,
            String AccountPassward)
            throws IOException {

        System.out.println(i);

        // タグ情報の取得
        Account account = new Account();
        account.setName(AccountName);
        account.setPassward(AccountPassward);
        URLManager.getInstance().getTag(account);

        List<String> nowSelectedTag = new ArrayList<String>();
        URLManager.getInstance().getTagOnly(account, i, nowSelectedTag);

        UrlInfomation info = new UrlInfomation();
        info.setTag(nowSelectedTag);

        model.addAttribute("Account", account);
        model.addAttribute("AccountNAME", AccountName);
        model.addAttribute("AccountPASSWARD", AccountPassward);
        model.addAttribute("i", i);
        model.addAttribute("URL", URL);
        model.addAttribute("NAME", NAME);
        model.addAttribute("errorMessage", "");
        model.addAttribute("UrlInfomation", info);
        model.addAttribute("selectedTag", nowSelectedTag);

        return "Yamakawa/URLChange";

    }

    @RequestMapping(path = "/urlPage")
    public String urlSettingChangeRequest(String AccountName, String AccountPassward, Model model, int i, String NAME,
            String URL, @ModelAttribute UrlInfomation info)
            throws IOException {

        if (NAME.length() == 0 || URL.length() == 0) {
            model.addAttribute("AccountNAME", AccountName);
            model.addAttribute("AccountPASSWARD", AccountPassward);
            model.addAttribute("i", i);
            model.addAttribute("URL", URL);
            model.addAttribute("NAME", NAME);
            model.addAttribute("errorMessage", "正しい入力がされていません");
            return "Yamakawa/URLChange";
        }

        Account account = new Account();

        account.setName(AccountName);
        account.setPassward(AccountPassward);
        URLManager.getInstance().urlSettingChange(account, NAME, URL, i, info.getTag());

        return urlSummaryPageRequest(account, model);
    }
}
