package defpackage;

/* JADX INFO: renamed from: q6, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1364q6 {
    public int a = 0;
    public int b;
    public int c;
    public int d;
    public int e;

    public boolean a() {
        int i = this.a;
        if ((i & 7) != 0 && (i & (b(this.d, this.b) << 0)) == 0) {
            return false;
        }
        int i2 = this.a;
        if ((i2 & 112) != 0 && (i2 & (b(this.d, this.c) << 4)) == 0) {
            return false;
        }
        int i3 = this.a;
        if ((i3 & 1792) != 0 && (i3 & (b(this.e, this.b) << 8)) == 0) {
            return false;
        }
        int i4 = this.a;
        return (i4 & 28672) == 0 || (i4 & (b(this.e, this.c) << 12)) != 0;
    }

    public int b(int i, int i2) {
        if (i > i2) {
            return 1;
        }
        return i == i2 ? 2 : 4;
    }
}
