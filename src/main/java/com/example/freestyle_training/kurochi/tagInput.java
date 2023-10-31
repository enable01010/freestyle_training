package com.example.freestyle_training.kurochi;

import java.io.Serializable;
import java.util.List;

public class tagInput implements Serializable  {
    public List<String> tagNameList;
    private String tag;

    // ゲッタ-セッター
    public String getTag() {
        return tag;
    }

    public void setTag(String value) {
        tag = value;
    }
    

}
