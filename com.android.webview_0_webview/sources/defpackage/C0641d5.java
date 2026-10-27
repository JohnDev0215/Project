package defpackage;

/* JADX INFO: renamed from: d5, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0641d5 {
    public long a = 0;
    public C0641d5 b;

    public int a(int i) {
        C0641d5 c0641d5 = this.b;
        if (c0641d5 == null) {
            return i >= 64 ? Long.bitCount(this.a) : Long.bitCount(this.a & ((1 << i) - 1));
        }
        if (i < 64) {
            return Long.bitCount(this.a & ((1 << i) - 1));
        }
        return Long.bitCount(this.a) + c0641d5.a(i - 64);
    }

    public final void b() {
        if (this.b == null) {
            this.b = new C0641d5();
        }
    }

    public boolean c(int i) {
        if (i < 64) {
            return (this.a & (1 << i)) != 0;
        }
        b();
        return this.b.c(i - 64);
    }

    public void d() {
        this.a = 0L;
        C0641d5 c0641d5 = this.b;
        if (c0641d5 != null) {
            c0641d5.d();
        }
    }

    public String toString() {
        if (this.b == null) {
            return Long.toBinaryString(this.a);
        }
        return this.b.toString() + "xx" + Long.toBinaryString(this.a);
    }
}
