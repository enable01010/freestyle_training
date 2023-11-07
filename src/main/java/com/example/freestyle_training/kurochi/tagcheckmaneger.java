package com.example.freestyle_training.kurochi;

import java.io.IOException;

import com.example.freestyle_training.Taniyama.Account;
import com.example.freestyle_training.Taniyama.AccountCheckResult;
import com.example.freestyle_training.Taniyama.UrlInfomation;

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

    public tagcheck tagCheckresult(Account account,UrlInfomation urlInfo) {
        int i;
        if (account.getTagname().equals("") ){
            account.getTagname();
            return tagcheck.notag;
        }

        if (!(account.getTagname().equals("") )) {
            return tagcheck.success;
        }

        for(i=0; i<urlInfo.getCheckTag().size(); i++){
            if(account.getTagname().equals(urlInfo.getCheckTag().get(i))){
                return tagcheck.nulltag;
            }
        }
        return tagcheck.other;
    }
}
