package com.example.freestyle_training.Taniyama;

import java.util.List;
import java.util.ArrayList;

public class UrlInfomation {
    private String url;
    private String name;
    private List<String> tag;

    public UrlInfomation() {
        tag = new ArrayList<String>();
    }

    // #region ゲッターセッター
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> nametag) {
        tag = nametag;
    }

    public void addTag(String add) {
        tag.add(add);
    }

    public int countTag() {
        return tag.size();
    }

    // #endregion
}
