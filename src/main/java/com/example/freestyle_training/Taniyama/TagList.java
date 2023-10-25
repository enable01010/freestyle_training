package com.example.freestyle_training.Taniyama;

import java.util.List;
import java.util.ArrayList;

public class TagList {
    private List<String> tagNameList;

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
