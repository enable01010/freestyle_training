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
import com.example.freestyle_training.Taniyama.UrlInfomation;

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
            File file = new File("C:\\freestyle_training\\src\\main\\db\\master\\url\\urlInfomation.txt");

            if (!file.exists()) {
                System.out.print("ファイルが開けません");
                return;
            }

            // .txt読み込み
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String data;
            boolean changeSetValue = true;
            List<UrlInfomation> urlData = new ArrayList<UrlInfomation>();
            // １行ずつ読み込み
            while ((data = bufferedReader.readLine()) != null) {
                if (changeSetValue) {
                    UrlInfomation urlInfo = new UrlInfomation();
                    urlInfo.setName(data);
                    urlData.add(urlInfo);
                } else {
                    urlData.get(urlData.size() - 1).setUrl(data);
                    account.setUrlList(urlData);
                }

                changeSetValue = !changeSetValue;
            }

            bufferedReader.close();

        } catch (IOException e) {

        }

    }

    public void urlSettingChange(Account account, String name, String url, int row) {
        try {
           File file = new File("C:\\freestyle_training\\src\\main\\db\\master\\url\\urlInfomation.txt");

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
                if (currentLine == (row*2)+1) {
                    fileContent.append(name).append(System.lineSeparator());
                    System.out.println(name);                    
                } else if (currentLine == (row*2)+2) {                    
                    fileContent.append(url).append(System.lineSeparator());                    
                    System.out.println(url);
                }
                else{
                    fileContent.append(data).append(System.lineSeparator());
                }
                currentLine++;
            }
            

            bufferedReader.close();

            BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\freestyle_training\\src\\main\\db\\master\\url\\urlInfomation.txt"), "UTF-8"));
            System.out.println(fileContent.toString());
            fileWriter.write(fileContent.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
