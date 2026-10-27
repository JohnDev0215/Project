package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class PK {
    public final boolean a;
    public final int b;
    public final int c;
    public final String d;
    public final boolean e;
    public final String f;

    public PK(boolean z, int i, int i2, String str, boolean z2, String str2) {
        this.a = z;
        this.b = i;
        this.c = i2;
        this.d = str == null ? "" : str;
        this.e = z2;
        this.f = str2 == null ? "" : str2;
    }

    public int a() {
        if (!this.a) {
            return 1;
        }
        if (this.b != 0) {
            return 0;
        }
        switch (this.c) {
        }
        return 0;
    }

    public int b() {
        if (this.a) {
            return TK.a(this.b, this.c);
        }
        return 6;
    }
}
