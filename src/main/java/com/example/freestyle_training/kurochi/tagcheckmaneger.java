package com.example.freestyle_training.kurochi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.example.freestyle_training.Taniyama.Account;
import com.example.freestyle_training.Taniyama.AccountCheckResult;
import com.example.freestyle_training.Taniyama.UrlInfomation;

import java.util.List;
import java.util.ArrayList;

public class tagcheckmaneger {
    static private tagcheckmaneger instance;

    static public tagcheckmaneger getInstance() {
        if (instance == null)
            instance = new tagcheckmaneger();

        return instance;
    }

    private tagcheckmaneger() {
    }
    // #endregion

    public tagcheck tagCheckresult(Account account, UrlInfomation urlInfo) {
        int i;
        if (account.getTagname().equals("")) {
            account.getTagname();
            return tagcheck.notag;
        }

        for (i = 0; i < urlInfo.getTag().size(); i++) {
            if (account.getTagname().equals(urlInfo.getTag().get(i))) {
                return tagcheck.nulltag;
            }
        }
        
        return tagcheck.other;
    }

}
