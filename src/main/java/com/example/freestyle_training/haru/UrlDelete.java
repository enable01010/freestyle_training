package com.example.freestyle_training.haru;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UrlDelete {
    private String nameurl = null;

    public String getNameurl() {
        return nameurl;
    }

    public void setNameurl(String str) {
        nameurl = str;
    }

    public void delete() {

        // Fileクラスをインスタンス化
        File file = new File("src\\main\\db\\UrlData.txt");

        int strCount = getNameurl().length();

        String readText = fileRead(file, strCount, getNameurl());
        try {
            FileWriter fW = new FileWriter(file);
            fW.write(readText);
            fW.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    private static String fileRead(File file, int strcount, String str) {

        StringBuffer fileRead = new StringBuffer("");

        try {

            // FileReaderクラスをインスタンス化
            FileReader fr = new FileReader(file);

            // BufferedReaderクラスをインスタンス化
            BufferedReader br = new BufferedReader(fr);

            String textLine = null;

            while ((textLine = br.readLine()) != null) {
                if (textLine.equals("")) {
                    continue;
                }
                if (!(str.equals(textLine.substring(1, strcount + 1)))) {
                    fileRead.append(textLine + "\r\n");
                }
            }

            // ファイルを閉じる
            br.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return fileRead.toString();
    }
}
