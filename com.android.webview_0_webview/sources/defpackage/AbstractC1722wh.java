package defpackage;

import android.net.Uri;

/* JADX INFO: renamed from: wh, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1722wh {
    public static final C0971j1 a = new C0971j1();

    public static synchronized Uri a(String str) {
        Uri uri;
        C0971j1 c0971j1 = a;
        synchronized (AbstractC1722wh.class) {
            uri = (Uri) c0971j1.get(str);
            if (uri == null) {
                String strValueOf = String.valueOf(Uri.encode(str));
                uri = Uri.parse(strValueOf.length() != 0 ? "content://com.google.android.gms.phenotype/".concat(strValueOf) : new String("content://com.google.android.gms.phenotype/"));
                c0971j1.put(str, uri);
            }
        }
        return uri;
    }
}
