package com.byd.foundation.utils;

import android.content.Context;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: classes.dex */
public class AssetsUtils {
    public static String readSingleLine(Context context, String str) {
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
            if (inputStreamOpen == null) {
                return line;
            }
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

    /* JADX WARN: Removed duplicated region for block: B:18:0x002a A[EXC_TOP_SPLITTER, PHI: r1
  0x002a: PHI (r1v2 java.io.InputStream) = (r1v1 java.io.InputStream), (r1v3 java.io.InputStream) binds: [B:12:0x0032, B:7:0x0028] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<java.lang.String> readLines(android.content.Context r3, java.lang.String r4) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            android.content.res.AssetManager r3 = r3.getAssets()     // Catch: java.lang.Throwable -> L32
            java.io.InputStream r1 = r3.open(r4)     // Catch: java.lang.Throwable -> L32
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L32
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L32
            r4.<init>(r1)     // Catch: java.lang.Throwable -> L32
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L32
        L18:
            java.lang.String r4 = r3.readLine()     // Catch: java.lang.Throwable -> L32
            boolean r2 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Throwable -> L32
            if (r2 == 0) goto L2e
            r3.close()     // Catch: java.lang.Throwable -> L32
            r1.close()     // Catch: java.lang.Throwable -> L32
            if (r1 == 0) goto L35
        L2a:
            r1.close()     // Catch: java.lang.Throwable -> L35
            goto L35
        L2e:
            r0.add(r4)     // Catch: java.lang.Throwable -> L32
            goto L18
        L32:
            if (r1 == 0) goto L35
            goto L2a
        L35:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.foundation.utils.AssetsUtils.readLines(android.content.Context, java.lang.String):java.util.List");
    }
}
