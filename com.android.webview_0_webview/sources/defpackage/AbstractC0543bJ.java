package defpackage;

/* JADX INFO: renamed from: bJ, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0543bJ implements InterfaceC0710eJ {
    public final C0487aJ s;

    public AbstractC0543bJ(LJ lj, InterfaceC1157mJ interfaceC1157mJ) {
        this.s = new C0487aJ(lj, interfaceC1157mJ);
    }

    @Override // defpackage.LI
    public void I(WJ wj) {
        this.s.I(wj);
    }

    @Override // defpackage.InterfaceC0822gJ, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.s.t.close();
    }
}
