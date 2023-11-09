package com.example.freestyle_training.Yamakawa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.ArrayList;

import com.example.freestyle_training.Taniyama.Account;
import com.example.freestyle_training.Taniyama.Memo;
import com.example.freestyle_training.Taniyama.UrlInfomation;
import com.example.freestyle_training.kurochi.tagcheck;

public class URLManager {
    // #region シングルトン

    static private URLManager instance;

    static public URLManager getInstance() {
        if (instance == null)
            instance = new URLManager();

        return instance;
    }

    private URLManager() {
    }

    public void getUrlList(Account account) {
        try {
            File file = new File("src\\main\\db\\master\\url\\urlInfomation.txt");

            if (!file.exists()) {
                System.out.print("ファイルが開けません");
                return;
            }

            // .txt読み込み
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String data;
            // １行ずつ読み込み
            while ((data = bufferedReader.readLine()) != null) {
                String[] urlDataSprits = data.split("_");
                UrlInfomation urlInfo = new UrlInfomation();
                urlInfo.setName(urlDataSprits[0]);
                urlInfo.setUrl(urlDataSprits[1]);
                for (int i = 2; i < urlDataSprits.length; i++) {
                    urlInfo.addTag(urlDataSprits[i]);
                }
                account.addUrlList(urlInfo);
            }

            bufferedReader.close();

        } catch (IOException e) {

        }
    }

    public void getUrlListSortTag(Account account) {
        getUrlList(account);
        String sortTag = account.getTagname();
        if (sortTag.equals("") || sortTag.equals("---") || sortTag.equals("master"))
            return;

        List<UrlInfomation> urlList = new ArrayList<UrlInfomation>();
        int urlLength = account.getUrlList().size();
        for (int i = 0; i < urlLength; i++) {
            if (account.getUrlList().get(i).CheckTag(sortTag)) {
                urlList.add(account.getUrlList().get(i));
            }
        }

        account.setUrlList(urlList);
    }

    public void getTag(Account account) {

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

    public tagcheck addTag(Account account) {
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

    public void addUrl(Account account, UrlInfomation urlInfo) {
        getUrlList(account);
        String basePass = "src\\main\\db\\" + account.getName() + "\\url\\urlInfomation.txt";
        try {
            File file = new File(basePass);
            FileWriter fw = new FileWriter(file);
            int urlLength = account.getUrlList().size();
            for (int i = 0; i < urlLength; i++) {
                UrlInfomation accountUrlInfo = account.getUrlList().get(i);
                String line = accountUrlInfo.getName() + "_" + accountUrlInfo.getUrl() + accountUrlInfo.getTagName();
                fw.write(line);
            }
            String line = urlInfo.getName() + "_" + urlInfo.getUrl() + urlInfo.getTagName();
            fw.write(line);

            fw.close();

        } catch (IOException e) {

        }
    }

    public void urlSettingChange(Account account, String name, String url, int row) {
        try {
            File file = new File("src\\main\\db\\" + account.getName() + "\\url\\urlInfomation.txt");

            if (!file.exists()) {
                System.out.print("ファイルが開けません");
                return;
            }

            // .txt読み込み
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String data;
            StringBuilder fileContent = new StringBuilder();
            int currentLine = 1;
            while ((data = bufferedReader.readLine()) != null) {
                if (currentLine == (row * 2) + 1) {
                    fileContent.append(name).append(System.lineSeparator());
                    System.out.println(name);
                } else if (currentLine == (row * 2) + 2) {
                    fileContent.append(url).append(System.lineSeparator());
                    System.out.println(url);
                } else {
                    fileContent.append(data).append(System.lineSeparator());
                }
                currentLine++;
            }

            bufferedReader.close();

            BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("C:\\freestyle_training\\src\\main\\db\\master\\url\\urlInfomation.txt"),
                    "UTF-8"));
            System.out.println(fileContent.toString());
            fileWriter.write(fileContent.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void urlDelete(Account account, String deleteName) {
        int urlLength = account.getUrlList().size();
        for (int i = 0; i < urlLength; i++) {
            if (account.getUrlList().get(i).getName().equals(deleteName)) {
                account.getUrlList().remove(i);
                break;
            }
        }

        String basePass = "src\\main\\db\\" + account.getName() + "\\url\\urlInfomation.txt";
        try {
            File file = new File(basePass);
            FileWriter fw = new FileWriter(file);
            urlLength = account.getUrlList().size();
            for (int i = 0; i < urlLength; i++) {
                UrlInfomation urlInfo = account.getUrlList().get(i);
                String line = urlInfo.getName() + "_" + urlInfo.getUrl() + urlInfo.getTagName();
                fw.write(line);
            }

            fw.close();

        } catch (IOException e) {

        }

    }

}
