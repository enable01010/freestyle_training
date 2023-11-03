package com.example.freestyle_training.Yamakawa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String data;
            //int num = 0;
            boolean changeSetValue = true;            
            List<UrlInfomation> urlData = new ArrayList<UrlInfomation>();            
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

}
