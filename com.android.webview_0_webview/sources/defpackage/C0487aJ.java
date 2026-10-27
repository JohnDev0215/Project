package defpackage;

/* JADX INFO: renamed from: aJ, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0487aJ implements InterfaceC0655dJ, LI {
    public final LJ s;
    public final InterfaceC1157mJ t;
    public LI u;
    public int v;

    public C0487aJ(LJ lj, InterfaceC1157mJ interfaceC1157mJ) {
        this.s = lj;
        this.t = interfaceC1157mJ;
    }

    @Override // defpackage.LI
    public void I(WJ wj) {
        LI li = this.u;
        if (li != null) {
            li.I(wj);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.t.close();
    }
}
