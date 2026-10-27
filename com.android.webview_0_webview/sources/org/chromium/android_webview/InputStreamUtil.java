package org.chromium.android_webview;

import android.util.Log;
import defpackage.Z7;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class InputStreamUtil {
    public static String a(String str) {
        return Z7.d("Got exception when calling ", str, "() on an InputStream returned from shouldInterceptRequest. This will cause the related request to fail.");
    }

    public static int available(InputStream inputStream) {
        try {
            return Math.max(-1, inputStream.available());
        } catch (IOException e) {
            Log.e("InputStreamUtil", a("available"), e);
            return -2;
        }
    }

    public static void close(InputStream inputStream) {
        try {
            inputStream.close();
        } catch (IOException e) {
            Log.e("InputStreamUtil", a("close"), e);
        }
    }

    public static int read(InputStream inputStream, byte[] bArr, int i, int i2) {
        try {
            return Math.max(-1, inputStream.read(bArr, i, i2));
        } catch (IOException e) {
            Log.e("InputStreamUtil", a("read"), e);
            return -2;
        }
    }

    public static long skip(InputStream inputStream, long j) {
        try {
            return Math.max(-1L, inputStream.skip(j));
        } catch (IOException e) {
            Log.e("InputStreamUtil", a("skip"), e);
            return -2L;
        }
    }
}
