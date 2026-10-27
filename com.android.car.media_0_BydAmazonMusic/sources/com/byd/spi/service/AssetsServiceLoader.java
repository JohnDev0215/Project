package com.byd.spi.service;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import spi.c;
import spi.i6;
import spi.kd;

/* JADX INFO: loaded from: classes2.dex */
public class AssetsServiceLoader {
    private static final String TAG = "-AssetsServiceLoader";

    public static class Holder {
        private static final AssetsServiceLoader INSTANCE = new AssetsServiceLoader();

        private Holder() {
        }
    }

    public static AssetsServiceLoader with() {
        return Holder.INSTANCE;
    }

    public <T> T loadService(Context context, @NonNull Class<T> cls) {
        InputStream inputStreamOpen;
        String line;
        try {
            try {
                inputStreamOpen = context.getAssets().open(cls.getName());
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamOpen));
                    line = null;
                    do {
                        try {
                            line = bufferedReader.readLine();
                        } catch (Throwable unused) {
                            if (inputStreamOpen != null) {
                            }
                            return (T) kd.b().a(context, Class.forName(line));
                        }
                    } while (TextUtils.isEmpty(line));
                    bufferedReader.close();
                    inputStreamOpen.close();
                } catch (Throwable unused2) {
                    line = null;
                }
            } catch (Throwable unused3) {
                inputStreamOpen = null;
                line = null;
            }
            try {
                inputStreamOpen.close();
            } catch (Throwable unused4) {
            }
            return (T) kd.b().a(context, Class.forName(line));
        } catch (Throwable th) {
            StringBuilder sbK = c.k("spi load service from config error:");
            sbK.append(cls.getName());
            i6.f("foundation.spi-AssetsServiceLoader", sbK.toString(), th);
            return null;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:0|2|(2:23|3)|(8:4|(1:29)(1:7)|25|10|11|(7:14|27|15|32|(3:30|17|35)(1:34)|33|12)|31|21)|6|25|10|11|(1:12)|31|21|(1:(0))) */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <T> java.util.List<T> loadServices(android.content.Context r6, @androidx.annotation.NonNull java.lang.Class<T> r7) {
        /*
            r5 = this;
            java.lang.String r5 = r7.getName()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            android.content.res.AssetManager r2 = r6.getAssets()     // Catch: java.lang.Throwable -> L31
            java.io.InputStream r1 = r2.open(r5)     // Catch: java.lang.Throwable -> L31
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L31
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L31
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L31
            r5.<init>(r2)     // Catch: java.lang.Throwable -> L31
        L1c:
            java.lang.String r2 = r5.readLine()     // Catch: java.lang.Throwable -> L31
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Throwable -> L31
            if (r3 == 0) goto L2d
            r5.close()     // Catch: java.lang.Throwable -> L31
            r1.close()     // Catch: java.lang.Throwable -> L31
            goto L33
        L2d:
            r0.add(r2)     // Catch: java.lang.Throwable -> L31
            goto L1c
        L31:
            if (r1 == 0) goto L36
        L33:
            r1.close()     // Catch: java.lang.Throwable -> L36
        L36:
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            spi.kd r1 = spi.kd.b()
            java.util.Iterator r0 = r0.iterator()
        L43:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L75
            java.lang.Object r2 = r0.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch: java.lang.Throwable -> L5d
            java.lang.Object r2 = r1.a(r6, r2)     // Catch: java.lang.Throwable -> L5d
            if (r2 == 0) goto L43
            r5.add(r2)     // Catch: java.lang.Throwable -> L5d
            goto L43
        L5d:
            r2 = move-exception
            java.lang.String r3 = "spi load service from config error:"
            java.lang.StringBuilder r3 = spi.c.k(r3)
            java.lang.String r4 = r7.getName()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "foundation.spi-AssetsServiceLoader"
            spi.i6.f(r4, r3, r2)
            goto L43
        L75:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.spi.service.AssetsServiceLoader.loadServices(android.content.Context, java.lang.Class):java.util.List");
    }

    private AssetsServiceLoader() {
    }
}
