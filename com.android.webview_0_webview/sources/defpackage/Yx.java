package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class Yx {
    public final int a;
    public final String b;

    public Yx(int i, String str) {
        this.a = i;
        this.b = str;
    }

    public static Yx a(String str) {
        return new Yx(-1, str);
    }

    public boolean b() {
        return this.a == 0;
    }
}
