package com.byd.cleardata.i;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f2717a = System.getProperty("line.separator");

    public static void a(String str) {
        c.b("exeShell exec = " + str);
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(str).getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            char[] cArr = new char[1024];
            while (true) {
                int i = bufferedReader.read(cArr);
                if (i == -1) {
                    bufferedReader.close();
                    return;
                }
                stringBuffer.append(cArr, 0, i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void b() {
        c.b("sync start");
        a("sync");
        c.b("sync end");
    }
}
