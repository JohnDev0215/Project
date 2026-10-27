package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Vy {
    public final C1406qu a;
    public boolean b;
    public long c;
    public boolean d;
    public int e;

    public Vy(C1406qu c1406qu, boolean z, long j, boolean z2, int i) {
        this.a = c1406qu;
        this.b = z;
        this.c = j;
        this.d = z2;
        this.e = i;
    }

    public boolean a() {
        return this.e == 0 && !(this.b && ((this.c > 0L ? 1 : (this.c == 0L ? 0 : -1)) == 0 || this.d));
    }
}
