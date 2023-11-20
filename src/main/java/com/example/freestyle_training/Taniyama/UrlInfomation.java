package com.example.freestyle_training.Taniyama;

import java.util.List;
import java.util.ArrayList;

public class UrlInfomation {
    private String url;
    private String nameUrl;
    private List<String> tag;

    public UrlInfomation() {
        tag = new ArrayList<String>();
    }

    public boolean CheckTag(String tagNeme) {

        int tagLength = tag.size();
        for (int i = 0; i < tagLength; i++) {
            if (tag.get(i).equals(tagNeme)) {
                return true;
            }
        }

        return false;
    }

    public String getTagName() {
        String tagNames = "";

        int tagLenght = tag.size();
        for (int i = 0; i < tagLenght; i++) {
            tagNames += tag.get(i) + "_";
        }

        return tagNames;
    }

    // #region ゲッターセッター
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNameUrl() {
        return nameUrl;
    }

    public void setNameUrl(String name) {
        this.nameUrl = name;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> nametag) {
        tag = nametag;
    }

    public void setTag(String add) {
        tag.add(add);
    }

    public void addTag(String add) {
        tag.add(add);
    }

    public int countTag() {
        return tag.size();
    }

    // #endregion
}
