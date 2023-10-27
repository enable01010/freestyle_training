package com.example.freestyle_training.Taniyama;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class MemoManager {
    // #region シングルトン

    static private MemoManager instance;

    static public MemoManager getInstance() {
        if (instance == null)
            instance = new MemoManager();

        return instance;
    }

    private MemoManager() {
    }

    // #endregion

    public void memoPageDataDownLoad(Account account) {

        try {
            File file = new File("src\\main\\db\\master\\memo");
            File[] fileArray = file.listFiles();
            for (int i = 0; i < fileArray.length; i++) {
                String fileName = fileArray[i].getName();
                BufferedReader br = new BufferedReader(new FileReader(fileArray[i]));
                StringBuilder builder = new StringBuilder();

                // テキストファイルから全文読み取る
                String content;
                while ((content = br.readLine()) != null) {
                    builder.append(content);
                }

                // 保存処理
                Memo memoData = new Memo();
                memoData.setDate(fileName);
                memoData.setText(builder.toString());
                account.addMemoList(memoData);

                br.close();
            }
        } catch (IOException e) {

        }
    }
}
