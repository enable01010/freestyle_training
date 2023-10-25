package com.example.freestyle_training.Taniyama;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
                        if (passward.equals(account.getPassward())) {
                            return AccountCheckResult.success;
                        } else {
                            return AccountCheckResult.passwardDiff;
                        }

                    }

                }

                return AccountCheckResult.accountNon;
            }

        } catch (IOException e) {

        }

        return AccountCheckResult.other;
    }
}