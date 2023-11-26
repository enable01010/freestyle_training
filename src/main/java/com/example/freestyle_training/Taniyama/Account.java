package com.example.freestyle_training.Taniyama;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;

public class Account {
    private String name;
    private String passward;
    public String tagname;
    @Valid
    private List<UrlInfomation> urlList;
    @Valid
    private List<Memo> memoList;
    private List<String> tagList;

    public Account() {
        urlList = new ArrayList<UrlInfomation>();
        memoList = new ArrayList<Memo>();
    }

    public void memoSort() {
        if (memoList == null)
            return;

        // 日付けを年・月・日に分割
        int memoSize = memoList.size();
        String[][] days = new String[memoSize][3];
        for (int i = 0; i < memoSize; i++) {
            days[i] = memoList.get(i).getDate().split("_");
            days[i][2] = days[i][2].replace(".txt", "");
        }

        // 文字列→数字変換
        int[][] daysInt = new int[memoSize][4];
        for (int i = 0; i < memoSize; i++) {
            for (int j = 0; j < 3; j++) {
                daysInt[i][j] = Integer.parseInt(days[i][j]);
            }
            daysInt[i][3] = i;
        }

        // ソート年
        for (int i = 0; i < memoSize; i++) {
            int maxValue = daysInt[i][0];
            int nowNumber = i;
            for (int j = i + 1; j < memoSize; j++) {
                if (maxValue < daysInt[j][0]) {
                    nowNumber = j;
                    maxValue = daysInt[j][0];
                }
            }

            int[] temp = daysInt[i];
            daysInt[i] = daysInt[nowNumber];
            daysInt[nowNumber] = temp;
        }

        // ソート月
        for (int i = 0; i < memoSize; i++) {
            int maxValue = daysInt[i][1];
            int nowNumber = i;

            for (int j = i + 1; j < memoSize; j++) {
                if (daysInt[i][0] != daysInt[j][0])
                    break;

                if (maxValue < daysInt[j][1]) {
                    nowNumber = j;
                    maxValue = daysInt[j][1];
                }
            }

            int[] temp = daysInt[i];
            daysInt[i] = daysInt[nowNumber];
            daysInt[nowNumber] = temp;
        }

        // ソート日
        for (int i = 0; i < memoSize; i++) {
            int maxValue = daysInt[i][2];
            int nowNumber = i;

            for (int j = i + 1; j < memoSize; j++) {
                if (daysInt[i][0] != daysInt[j][0] || daysInt[i][1] != daysInt[j][1])
                    break;

                if (maxValue < daysInt[j][2]) {
                    nowNumber = j;
                    maxValue = daysInt[j][2];
                }
            }

            int[] temp = daysInt[i];
            daysInt[i] = daysInt[nowNumber];
            daysInt[nowNumber] = temp;

        }

        // 入れ直し
        List<Memo> tempMemos = new ArrayList<Memo>();
        for (int i = 0; i < memoSize; i++) {
            tempMemos.add(memoList.get(daysInt[i][3]));
        }
        memoList = tempMemos;
    }

    // #region ゲッターセッター
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassward() {
        return passward;
    }

    public void setPassward(String passward) {
        this.passward = passward;
    }

    public List<UrlInfomation> getUrlList() {
        if (urlList == null) {
            urlList = new ArrayList<UrlInfomation>();
        }
        return urlList;
    }

    public void setUrlList(List<UrlInfomation> urlList) {
        this.urlList = urlList;
    }

    public void deleteUrlList() {
        if (urlList == null)
            return;

        urlList.clear();
    }

    public void addUrlList(UrlInfomation add) {
        urlList.add(add);
    }

    public List<Memo> getMemoList() {
        return memoList;
    }

    public void setMemoList(List<Memo> memoList) {
        this.memoList = memoList;
    }

    public void setMemoList(Memo[] memoList) {
        this.memoList = new ArrayList<Memo>();
        for (int i = 0; i < memoList.length; i++) {
            this.memoList.add(memoList[i]);
        }
    }

    public void setMemoList(Memo add) {
        if (memoList == null) {
            memoList = new ArrayList<Memo>();
        }

        memoList.add(add);
    }

    public void setMemoList(String memo) {

    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String value) {
        tagname = value;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(String add) {
        if (tagList == null) {
            tagList = new ArrayList<String>();
        }

        tagList.add(add);
    }

    public void setTagList(List<String> tag) {
        tagList = tag;
    }
    // #endregion
}
