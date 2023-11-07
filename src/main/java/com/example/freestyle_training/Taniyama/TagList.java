package com.example.freestyle_training.Taniyama;

import java.util.List;
import java.util.ArrayList;

public class TagList {
    public List<String> tagNameList;

    private String tag;

    // ゲッタ-セッター
    public String getTag() {
        return tag;
    }

    public void setTag(String value) {
        tag = value;
    }
    
    public TagList() {
        tagNameList = new ArrayList<String>();
    }

    // #region ゲッターセッター
    public List<String> getTagNameList() {
        return tagNameList;
    }

    public void addTagNameList(String add) {
        tagNameList.add(add);
    }

    // #endregion
}
