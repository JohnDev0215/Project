package com.byd.filemanager.k;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* JADX INFO: compiled from: ShellUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3634a = System.getProperty("line.separator");

    public static void a(String str) {
        b.b("exeShell exec = " + str);
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
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static void b() {
        b.b("sync start");
        a("sync");
        b.b("sync end");
    }
}
