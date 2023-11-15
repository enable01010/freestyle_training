package com.example.freestyle_training.Taniyama;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.example.freestyle_training.Yamakawa.URLManager;

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
    public String loginRequest(@ModelAttribute Account account, Model model) throws IOException {

        // アカウントの名前とパスワードのチェック
        AccountCheckResult result = LoginManager.getInstance().accountCheck(account);

        // 結果を元に処理
        if (result == AccountCheckResult.success) {
            URLManager.getInstance().getUrlList(account);
            model.addAttribute("Account", account);
            return "Yamakawa/URLInfomation";
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

        MemoManager.getInstance().memoOpen(account);

        model.addAttribute("Account", account);

        return "Taniyama/MemoPage";
    }

    @RequestMapping(path = "/memoSave")
    public void memoSaveRequest(@RequestParam("account") String accountData, Model model) throws IOException {

        Account account = LoginManager.getInstance().ChengeJsonDataToAccount(accountData);

        // データの保存f
        MemoManager.getInstance().memoSave(account);
    }

    @RequestMapping(path = "/memoAddToday")
    public String memoAddTodayRequest(@RequestParam("account") String accountData, Model model) throws IOException {

        memoSaveRequest(accountData, model);
        Account account = LoginManager.getInstance().ChengeJsonDataToAccount(accountData);
        MemoManager.getInstance().memoAddToday(account);
        model.addAttribute("Account", account);
        return "Taniyama/MemoPage";
    }

}
