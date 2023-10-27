package com.example.freestyle_training.Taniyama;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;

@Controller
public class TaniyamaController {
    @RequestMapping(path = "/DebugStart")
    public String sample(Model model) throws IOException {
        Account account = new Account();
        account.setName("master");
        account.setPassward("master");

        model.addAttribute("Account", account);
        return "Taniyama/DebugStart";
    }

    @RequestMapping(path = "/loginPage")
    public String loginPageRequest(Model model) throws IOException {

        model.addAttribute("Account", new Account());

        return "Taniyama/LoginPage";
    }

    @RequestMapping(path = "/login")
    public String Login(@ModelAttribute Account account, Model model) throws IOException {

        // アカウントの名前とパスワードのチェック
        AccountCheckResult result = LoginManager.getInstance().accountCheck(account);

        // 結果を元に処理
        if (result == AccountCheckResult.success) {
            return "Taniyama/DebugStart";
        } else {

            String errorLog = "";
            switch (result) {
                case accountNon:
                    errorLog = "アカウントがありません";
                    break;
                case passwardDiff:
                    errorLog = "パスワードが間違っています";
                    break;
                case inputNon:
                    errorLog = "入力が不足しています";
                    break;
                default:
                    errorLog = "原因不明のエラーです";
                    break;
            }

            model.addAttribute("Account", account);
            model.addAttribute("errorLog", errorLog);
            return "Taniyama/LoginPage";
        }

    }

    @RequestMapping(path = "/memoPage")
    public String memoPageRequest(@ModelAttribute Account account, Model model) throws IOException {

        MemoManager.getInstance().memoPageDataDownLoad(account);

        model.addAttribute("Account", account);

        return "Taniyama/MemoPage";
    }

    @RequestMapping(path = "/memoSave")
    public void memoSaveRequest(@ModelAttribute Account account, Model model) throws IOException {

        System.out.println(account.getName());
    }
}
