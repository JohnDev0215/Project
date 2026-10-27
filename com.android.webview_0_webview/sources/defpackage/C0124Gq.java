package defpackage;

/* JADX INFO: renamed from: Gq, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0124Gq {
    public final String a;
    public final String b;
    public final String c;
    public final boolean d;

    public C0124Gq(String str, String str2, String str3, boolean z) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = z;
    }

    public static C0124Gq a(String str, String str2) {
        return new C0124Gq(str, str2, null, true);
    }

    public static C0124Gq b(String str, String str2) {
        return new C0124Gq(str, str2, null, false);
    }

    public static C0124Gq c(String str, String str2, String str3) {
        return new C0124Gq(str, str2, str3, false);
    }
}
