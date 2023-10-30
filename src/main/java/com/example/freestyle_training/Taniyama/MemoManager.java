package com.example.freestyle_training.Taniyama;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

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

    public void memoOpen(Account account) {

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
                account.setMemoList(memoData);

                br.close();
            }
        } catch (IOException e) {

        }
    }

    public void memoSave(Account account) {
        String basePass = "src\\main\\db\\" + account.getName() + "\\memo\\";
        int length = account.getMemoList().size();
        try {
            for (int i = 0; i < length; i++) {
                Memo memo = account.getMemoList().get(i);
                String pass = basePass + memo.getDate();
                File file = new File(pass);
                FileWriter fw = new FileWriter(file);
                fw.write(memo.getText());
                fw.close();
            }
        } catch (IOException e) {

        }
    }

    public void memoAdd(Account account) {
        String basePass = "src\\main\\db\\" + account.getName() + "\\memo\\";
        GregorianCalendar gcalendar = new GregorianCalendar();
        String fileName = gcalendar.get(Calendar.YEAR) + "_" + (gcalendar.get(Calendar.MONTH) + 1) + "_"
                + gcalendar.get(Calendar.DATE) + ".txt";
        String pass = basePass + fileName;

        try {
            File file = new File(pass);
            FileWriter fw = new FileWriter(file);
            fw.close();
            // 保存処理
            Memo memoData = new Memo();
            memoData.setDate(fileName);
            memoData.setText("");
            account.setMemoList(memoData);
        } catch (IOException e) {

        }
    }
}
