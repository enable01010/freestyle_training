package com.example.freestyle_training.kurochi;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.freestyle_training.Taniyama.Account;
import com.example.freestyle_training.Taniyama.TagList;
import com.example.freestyle_training.Taniyama.UrlInfomation;
import com.example.freestyle_training.Yamakawa.URLManager;

import org.springframework.ui.Model;

@Controller
public class kurochicontroller {
    UrlInfomation urlInfo = new UrlInfomation();
    TagList taglist = new TagList();
    Map<String, String> selectMap = new LinkedHashMap<String, String>();
    tagInput taginput = new tagInput();

    @RequestMapping(path = "/addTag")
    public String tagopen(@ModelAttribute Account account, Model model) throws IOException {
        String tagerrorLog = "";

        model.addAttribute("tagdata", "");
        model.addAttribute("Account", account);
        model.addAttribute("errorLog", tagerrorLog);
        return "kurochi/addTag";
    }

    @RequestMapping(path = "/inputTag")
    public String tagAddRequest(@ModelAttribute Account account, Model model, UrlInfomation urlInfo, tagInput taginput)
            throws IOException {
        String tagerrorLog = "";

        urlInfo.addTag(account.getTagname());

        // tagの例外処理
        tagcheck result = URLManager.getInstance().addTag(account);

        // 結果を元に処理
        if (result == tagcheck.success) {
            model.addAttribute("Account", account);
        } else {
            switch (result) {
                case notag:
                    tagerrorLog = "タグがありません";
                    break;
                case nulltag:
                    tagerrorLog = "同じタグがあります";
                    break;
                case overtag:
                    tagerrorLog = "10字以下のタグの名前を入力してください";
                    break;
                default:
                    tagerrorLog = "原因不明のエラーです";
                    break;
            }

            model.addAttribute("Account", account);
            model.addAttribute("errorLog", tagerrorLog);
        }

        urlInfo.addTag("朝礼");
        urlInfo.addTag("夕礼");
        urlInfo.addTag("ギャザー");

        model.addAttribute("tagdata", "");
        model.addAttribute("Account", account);
        model.addAttribute("UrlInfomation", urlInfo);
        model.addAttribute("tagInput", taginput);
        model.addAttribute("tagerrorLog", tagerrorLog);

        return "kurochi/addTag";
    }

    @RequestMapping(path = "/tagselect")
    public String tagRequest(@ModelAttribute Account account, Model model, UrlInfomation urlInfo) throws IOException {
        URLManager.getInstance().getTag(account);
        Map<String, String> selecttagMap = new LinkedHashMap<String, String>();
        for (int i = 0; i < account.getTagList().size(); i++) {
            selecttagMap.put(Integer.valueOf(i).toString(), account.getTagList().get(i));
        }

        model.addAttribute("tagdata", "");
        model.addAttribute("Account", account);
        model.addAttribute("UrlInfomation", urlInfo);
        model.addAttribute("selectItems", selecttagMap);
        return "kurochi/tagselect";
    }
}
