package com.example.freestyle_training.Yamakawa;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.freestyle_training.Taniyama.Account;

import org.springframework.ui.Model;

@Controller
public class YamakawaContloller {
    @RequestMapping(path = "/urlInfomation")
    public String urlSummaryPageRequest(Account account, Model model) throws IOException {

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

        model.addAttribute("AccountNAME", AccountName);
        model.addAttribute("AccountPASSWARD", AccountPassward);
        model.addAttribute("i", i);
        model.addAttribute("URL", URL);
        model.addAttribute("NAME", NAME);
        model.addAttribute("errorMessage", "");
        return "Yamakawa/URLChange";

    }

    @RequestMapping(path = "/urlPage")
    public String urlSettingChangeRequest(String AccountName, String AccountPassward, Model model, int i, String NAME,
            String URL)
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
        URLManager.getInstance().urlSettingChange(account, NAME, URL, i);
        URLManager.getInstance().getUrlList(account);
        model.addAttribute("Account", account);

        return "Yamakawa/URLInfomation";
    }
}
