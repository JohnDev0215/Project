package defpackage;

/* JADX INFO: renamed from: fJ, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0766fJ implements InterfaceC1157mJ {
    public final LJ s;
    public final InterfaceC0822gJ t;

    public AbstractC0766fJ(LJ lj, InterfaceC0822gJ interfaceC0822gJ) {
        this.s = lj;
        this.t = interfaceC0822gJ;
    }

    @Override // defpackage.InterfaceC1101lJ, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.t.close();
    }
}
