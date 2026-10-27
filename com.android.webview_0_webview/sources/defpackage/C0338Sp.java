package defpackage;

import android.util.Log;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/* JADX INFO: renamed from: Sp, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0338Sp {
    public String a = Z7.c("android-webview-video-poster:default_video_poster/", String.valueOf(new Random().nextLong()));
    public AbstractC0511ao b;

    public C0338Sp(AbstractC0511ao abstractC0511ao) {
        this.b = abstractC0511ao;
    }

    public static void a(OutputStream outputStream) {
        try {
            outputStream.close();
        } catch (IOException e) {
            Log.e("DefaultVideoPosterRequestHandler", null, e);
        }
    }
}
