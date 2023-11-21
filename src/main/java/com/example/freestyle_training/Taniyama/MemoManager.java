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

    public MemoOpenResult memoOpen(Account account) {

        // アカウント情報のチェック
        AccountCheckResult accountCheckResult = LoginManager.getInstance().accountCheck(account);
        switch (accountCheckResult) {
            case inputNon:
                return MemoOpenResult.inputNon;
            case accountNon:
                return MemoOpenResult.accountNon;
            case passwardDiff:
                return MemoOpenResult.passwardDiff;
            case other:
                return MemoOpenResult.other;
            default:
                break;
        }

        try {
            File file = new File("src\\main\\db\\" + account.getName() + "\\memo");
            if (file.exists() == false)
                return MemoOpenResult.other;

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
            return MemoOpenResult.other;
        }

        account.memoSort();
        return MemoOpenResult.success;
    }

    public MemoSaveResult memoSave(Account account) {
        // アカウント情報のチェック
        AccountCheckResult accountCheckResult = LoginManager.getInstance().accountCheck(account);
        switch (accountCheckResult) {
            case inputNon:
                return MemoSaveResult.inputNon;
            case accountNon:
                return MemoSaveResult.accountNon;
            case passwardDiff:
                return MemoSaveResult.passwardDiff;
            case other:
                return MemoSaveResult.other;
            default:
                break;
        }

        // メモリストの情報を精査
        if (account.getMemoList().size() == 0)
            return MemoSaveResult.memoNothing;

        int memoSize = account.getMemoList().size();
        for (int i = 0; i < memoSize; i++) {
            Memo memo = account.getMemoList().get(i);
            if (memo.getDate() == null || memo.getDate() == "")
                return MemoSaveResult.memoDataBreak;
        }

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
            return MemoSaveResult.other;
        }

        return MemoSaveResult.success;
    }

    public MemoAddTodayResult memoAddToday(Account account) {
        // アカウント情報のチェック
        AccountCheckResult accountCheckResult = LoginManager.getInstance().accountCheck(account);
        switch (accountCheckResult) {
            case inputNon:
                return MemoAddTodayResult.inputNon;
            case accountNon:
                return MemoAddTodayResult.accountNon;
            case passwardDiff:
                return MemoAddTodayResult.passwardDiff;
            case other:
                return MemoAddTodayResult.other;
            default:
                break;
        }

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
            return MemoAddTodayResult.other;
        }

        return MemoAddTodayResult.success;
    }
}
