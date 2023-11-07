package com.example.freestyle_training.kurochi;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.freestyle_training.Taniyama.Account;
import com.example.freestyle_training.Taniyama.TagList;
import com.example.freestyle_training.Taniyama.UrlInfomation;

import org.springframework.ui.Model;

@Controller
public class kurochicontroller {
    UrlInfomation urlInfo = new UrlInfomation();
    TagList taglist = new TagList();
    Map<String, String> selectMap = new LinkedHashMap<String, String>();
    tagInput taginput = new tagInput();

    @RequestMapping(path = "/addTag")
    public String tagopen(@ModelAttribute Account account, Model model) throws IOException {

        urlInfo.addTag("朝礼");
        urlInfo.addTag("夕礼");
        urlInfo.addTag("ギャザー");

        taglist.addTagNameList("朝礼");
        taglist.addTagNameList("夕礼");
        taglist.addTagNameList("ギャザー");

        selectMap.put("key_A", "選択肢Aは、これですよ");
        selectMap.put("key_B", "選択肢Ｂは、これですよ");

        model.addAttribute("tagdata", "");
        model.addAttribute("Account", account);
        model.addAttribute("UrlInfomation", urlInfo);
        model.addAttribute("TagList", taglist);
        model.addAttribute("tagInput", taginput);

        model.addAttribute("selectItems",selectMap);    
        return "kurochi/addTag";
    }

    @RequestMapping(path = "/inputTag") 
    public String tagopen2(@ModelAttribute Account account, Model model, UrlInfomation urlInfo,Map<String, String> selectMap,tagInput taginput)throws IOException{
        //passにtagを一時的に入れる
        urlInfo.addTag(account.getPassward());
        selectMap.put("key_A", "選択肢Aは、これですよ");
        selectMap.put("key_B", "選択肢Ｂは、これですよ");

        model.addAttribute("tagdata", "");
        model.addAttribute("Account", account);
        model.addAttribute("UrlInfomation", urlInfo);
        model.addAttribute("tagInput", taginput);

        model.addAttribute("selectItems",selectMap); 


        return "kurochi/addTag";
    }

    @RequestMapping(path = "/tagselect")
    public String selectTag(@ModelAttribute Account account, Model model, UrlInfomation urlInfo,Map<String, String> selectMap)throws IOException{
        model.addAttribute("tagdata", "");
        model.addAttribute("Account", account);
        model.addAttribute("UrlInfomation", urlInfo);
        model.addAttribute("TagList", taglist);
        model.addAttribute("tagInput", new tagInput());

        model.addAttribute("selectItems",selectMap);
        return "kurochi/addTag";
    }

}
