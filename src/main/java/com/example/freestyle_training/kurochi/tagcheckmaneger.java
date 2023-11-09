package com.example.freestyle_training.kurochi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.example.freestyle_training.Taniyama.Account;
import com.example.freestyle_training.Taniyama.AccountCheckResult;
import com.example.freestyle_training.Taniyama.UrlInfomation;

import java.util.List;
import java.util.ArrayList;

public class tagcheckmaneger {
    static private tagcheckmaneger instance;

    static public tagcheckmaneger getInstance() {
        if (instance == null)
            instance = new tagcheckmaneger();

        return instance;
    }

    private tagcheckmaneger() {
    }
    // #endregion

    public tagcheck tagCheckresult(Account account, UrlInfomation urlInfo) {
        int i;
        if (account.getTagname().equals("")) {
            account.getTagname();
            return tagcheck.notag;
        }

        if (!(account.getTagname().equals(""))) {
            return tagcheck.success;
        }

        for (i = 0; i < urlInfo.getCheckTag().size(); i++) {
            if (account.getTagname().equals(urlInfo.getCheckTag().get(i))) {
                return tagcheck.nulltag;
            }
        }
        return tagcheck.other;
    }

    public void openTag(Account account) {
        try {
            File file = new File("src\\main\\db\\" + account.getName() + "\\tagdata\\tag.txt");

            if (file.exists()) {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);

                String content;
                while ((content = br.readLine()) != null) {
                    account.setTagList(content);
                }
                br.close();
            }

        } catch (IOException e) {

        }
    }

    public tagcheck saveTag(Account account) {
        if (account.getTagname().equals("")) {
            account.getTagname();
            return tagcheck.notag;
        }

        List<String> nowTagList = new ArrayList<String>();
        try {
            // 読み込み
            File file = new File("src\\main\\db\\" + account.getName() + "\\tagdata\\tag.txt");
            if (file.exists()) {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);

                String content;
                while ((content = br.readLine()) != null) {
                    nowTagList.add(content);
                }
                br.close();
            }

            // 比較
            boolean isSame = false;
            for (int i = 0; i < nowTagList.size(); i++) {
                if (nowTagList.get(i).equals(account.getTagname())) {
                    isSame = true;
                    break;
                }
            }

            // 処理
            if (isSame) {
                return tagcheck.nulltag;
            } else {
                nowTagList.add(account.getTagname());
                file = new File("src\\main\\db\\" + account.getName() + "\\tagdata\\tag.txt");
                FileWriter fw = new FileWriter(file);
                for (int i = 0; i < nowTagList.size(); i++) {
                    fw.write(nowTagList.get(i) + "\n");
                }

                fw.close();
                return tagcheck.success;
            }

        } catch (IOException e) {

        }

        return tagcheck.other;
    }
}
