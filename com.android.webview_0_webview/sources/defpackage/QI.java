package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class QI {
    public int a;
    public long b;
    public long c = 0;
    public final long d;
    public final long e;

    public QI(long j, int i) {
        this.d = j;
        this.e = i;
    }

    public void a(long j, long j2) {
        if (j % 8 != 0) {
            throw new TI("Incorrect starting alignment: " + j + ".");
        }
        if (j < this.b) {
            throw new TI("Trying to access memory out of order.");
        }
        if (j2 < j) {
            throw new TI("Incorrect memory range.");
        }
        if (j2 > this.d) {
            throw new TI("Trying to access out of range memory.");
        }
        this.b = (-8) & ((j2 + 8) - 1);
    }
}
