package com.ts.appservice.engineeringmode.e;

import android.content.res.AssetManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class b {
    public static String a(AssetManager assetManager, String str) {
        InputStream inputStreamOpen;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        String string = null;
        try {
            inputStreamOpen = assetManager.open(str);
            try {
                inputStreamReader = new InputStreamReader(inputStreamOpen);
                try {
                    bufferedReader = new BufferedReader(inputStreamReader);
                } finally {
                }
            } finally {
            }
        } catch (IOException e) {
            a.d("ReadJsonUtil", "initAllPingId: ", e);
        }
        try {
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                stringBuffer.append(line);
            }
            string = stringBuffer.toString();
            a.a("ReadJsonUtil", "getJson : " + string);
            bufferedReader.close();
            inputStreamReader.close();
            if (inputStreamOpen != null) {
                inputStreamOpen.close();
            }
            return string;
        } finally {
        }
    }
}
