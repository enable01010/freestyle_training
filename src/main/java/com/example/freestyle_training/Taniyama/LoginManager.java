package com.example.freestyle_training.Taniyama;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginManager {
    // #region シングルトン
    static private LoginManager instance;

    static public LoginManager getInstance() {
        if (instance == null)
            instance = new LoginManager();

        return instance;
    }

    private LoginManager() {
    }
    // #endregion

    public AccountCheckResult accountCheck(Account account) {
        if (account.getName().equals("") || account.getPassward().equals(""))
            return AccountCheckResult.inputNon;

        try {
            File file = new File("src\\main\\db\\AccoutData.txt");

            if (file.exists()) {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);

                String content;
                while ((content = br.readLine()) != null) {
                    String[] spritContent = content.split("_");
                    String name = spritContent[0];
                    String passward = spritContent[1];

                    if (name.equals(account.getName())) {
                        br.close();
                        if (passward.equals(account.getPassward())) {
                            return AccountCheckResult.success;
                        } else {
                            return AccountCheckResult.passwardDiff;
                        }

                    }

                }
                br.close();
                return AccountCheckResult.accountNon;
            }

        } catch (IOException e) {

        }
        return AccountCheckResult.other;
    }

    public Account ChengeJsonDataToAccount(String accountData) {
        Account account = new Account();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(accountData);

            // Jsonデータの処理
            account.setName(json.get("name").textValue());
            account.setPassward(json.get("passward").textValue());
            JsonNode memoListJson = json.get("memoList");
            JsonNode current = memoListJson.get(0);
            for (int i = 0; (current = memoListJson.get(i)) != null; i++) {
                Memo memo = new Memo();
                memo.setDate(current.get("date").textValue());
                memo.setText(current.get("text").textValue());
                account.setMemoList(memo);
            }
        } catch (Exception e) {
        }

        return account;
    }
}