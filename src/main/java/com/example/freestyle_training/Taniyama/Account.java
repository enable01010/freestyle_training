package com.example.freestyle_training.Taniyama;

import java.util.List;
import java.util.ArrayList;

public class Account {
    private String name;
    private String passward;
    private List<UrlInfomation> urlList;
    private List<Memo> memoList;

    public Account() {
        urlList = new ArrayList<UrlInfomation>();
        memoList = new ArrayList<Memo>();
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
        return urlList;
    }

    public void addUrlList(UrlInfomation add) {
        urlList.add(add);
    }

    public List<Memo> getMemoList() {
        return memoList;
    }

    public void addMemoList(Memo add) {
        memoList.add(add);
    }
    // #endregion
}