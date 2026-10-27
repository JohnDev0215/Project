package com.byd.spi.service;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import spi.dh;
import spi.e;
import spi.pv;

/* JADX INFO: loaded from: classes.dex */
public class AssetsServiceLoader {
    private static final String TAG = "-AssetsServiceLoader";

    public static class Holder {
        private static final AssetsServiceLoader INSTANCE = new AssetsServiceLoader();

        private Holder() {
        }
    }

    private AssetsServiceLoader() {
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
                            return (T) pv.b().a(context, Class.forName(line));
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
            return (T) pv.b().a(context, Class.forName(line));
        } catch (Throwable th) {
            StringBuilder sbK = e.k("spi load service from config error:");
            sbK.append(cls.getName());
            dh.f("foundation.spi-AssetsServiceLoader", sbK.toString(), th);
            return null;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:0|2|(2:23|3)|(8:4|(1:29)(1:7)|25|10|11|(7:14|27|15|32|(3:30|17|35)(1:34)|33|12)|31|21)|6|25|10|11|(1:12)|31|21|(1:(0))) */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <T> java.util.List<T> loadServices(android.content.Context r7, @androidx.annotation.NonNull java.lang.Class<T> r8) {
        /*
            r6 = this;
            java.lang.String r0 = r8.getName()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
            android.content.res.AssetManager r3 = r7.getAssets()     // Catch: java.lang.Throwable -> L31
            java.io.InputStream r2 = r3.open(r0)     // Catch: java.lang.Throwable -> L31
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L31
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L31
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L31
            r0.<init>(r3)     // Catch: java.lang.Throwable -> L31
        L1c:
            java.lang.String r3 = r0.readLine()     // Catch: java.lang.Throwable -> L31
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Throwable -> L31
            if (r4 == 0) goto L2d
            r0.close()     // Catch: java.lang.Throwable -> L31
            r2.close()     // Catch: java.lang.Throwable -> L31
            goto L33
        L2d:
            r1.add(r3)     // Catch: java.lang.Throwable -> L31
            goto L1c
        L31:
            if (r2 == 0) goto L36
        L33:
            r2.close()     // Catch: java.lang.Throwable -> L36
        L36:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            spi.pv r2 = spi.pv.b()
            java.util.Iterator r1 = r1.iterator()
        L43:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L76
            java.lang.Object r3 = r1.next()
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch: java.lang.Throwable -> L5d
            java.lang.Object r3 = r2.a(r7, r3)     // Catch: java.lang.Throwable -> L5d
            if (r3 == 0) goto L43
            r0.add(r3)     // Catch: java.lang.Throwable -> L5d
            goto L43
        L5d:
            r3 = move-exception
            java.lang.String r4 = "spi load service from config error:"
            java.lang.StringBuilder r4 = spi.e.k(r4)
            java.lang.String r5 = r8.getName()
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            java.lang.String r5 = "foundation.spi-AssetsServiceLoader"
            spi.dh.f(r5, r4, r3)
            goto L43
        L76:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.spi.service.AssetsServiceLoader.loadServices(android.content.Context, java.lang.Class):java.util.List");
    }
}
