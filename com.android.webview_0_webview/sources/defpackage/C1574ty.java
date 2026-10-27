package defpackage;

import java.text.SimpleDateFormat;
import java.util.Locale;

/* JADX INFO: renamed from: ty, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1574ty {
    public String a;
    public String b;
    public long c;
    public boolean d;
    public byte[] e;

    @Deprecated
    public static long a(String str) {
        return new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US).parse(str).getTime();
    }

    public String toString() {
        return super.toString();
    }
}
