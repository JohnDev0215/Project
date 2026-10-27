package com.byd.foundation.utils;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: classes2.dex */
public class AssetsUtils {
    /* JADX WARN: Can't wrap try/catch for region: R(9:0|2|(2:15|3)|(5:4|(1:19)(1:9)|17|7|13)|6|17|7|13|(1:(0))) */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<java.lang.String> readLines(android.content.Context r3, java.lang.String r4) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            android.content.res.AssetManager r3 = r3.getAssets()     // Catch: java.lang.Throwable -> L30
            java.io.InputStream r1 = r3.open(r4)     // Catch: java.lang.Throwable -> L30
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L30
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L30
            r4.<init>(r1)     // Catch: java.lang.Throwable -> L30
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L30
        L18:
            java.lang.String r4 = r3.readLine()     // Catch: java.lang.Throwable -> L30
            boolean r2 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Throwable -> L30
            if (r2 == 0) goto L2c
            r3.close()     // Catch: java.lang.Throwable -> L30
            r1.close()     // Catch: java.lang.Throwable -> L30
        L28:
            r1.close()     // Catch: java.lang.Throwable -> L33
            goto L33
        L2c:
            r0.add(r4)     // Catch: java.lang.Throwable -> L30
            goto L18
        L30:
            if (r1 == 0) goto L33
            goto L28
        L33:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.foundation.utils.AssetsUtils.readLines(android.content.Context, java.lang.String):java.util.List");
    }

    public static String readSingleLine(@NonNull Context context, String str) {
        String str2;
        InputStream inputStreamOpen;
        InputStream inputStream = null;
        String line = null;
        try {
            inputStreamOpen = context.getAssets().open(str);
        } catch (Throwable unused) {
            str2 = null;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamOpen));
            do {
                line = bufferedReader.readLine();
            } while (TextUtils.isEmpty(line));
            bufferedReader.close();
            inputStreamOpen.close();
            try {
                inputStreamOpen.close();
                return line;
            } catch (Throwable unused2) {
                return line;
            }
        } catch (Throwable unused3) {
            String str3 = line;
            inputStream = inputStreamOpen;
            str2 = str3;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable unused4) {
                }
            }
            return str2;
        }
    }
}
