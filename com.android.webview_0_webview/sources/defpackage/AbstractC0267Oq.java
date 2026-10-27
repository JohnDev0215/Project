package defpackage;

import java.io.File;

/* JADX INFO: renamed from: Oq, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0267Oq {
    public static File a() {
        File fileB = b();
        if (fileB.mkdir() || fileB.isDirectory()) {
            return fileB;
        }
        return null;
    }

    public static File b() {
        return new File(Is.a.getCacheDir(), "WebView_Crashes");
    }
}
