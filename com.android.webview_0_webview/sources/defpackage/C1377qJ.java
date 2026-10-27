package defpackage;

import java.util.concurrent.Executor;

/* JADX INFO: renamed from: qJ, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1377qJ implements InterfaceC1101lJ {
    public boolean s;
    public final /* synthetic */ C1431rJ t;

    public C1377qJ(C1431rJ c1431rJ) {
        this.t = c1431rJ;
    }

    @Override // defpackage.InterfaceC1101lJ, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.t.s.close();
    }

    public void finalize() throws Throwable {
        C1431rJ c1431rJ;
        Executor executor;
        if (!this.s && (executor = (c1431rJ = this.t).w) != null) {
            executor.execute(new RunnableC1267oJ(c1431rJ));
        }
        super.finalize();
    }

    @Override // defpackage.InterfaceC1101lJ
    public boolean q(C0989jJ c0989jJ) {
        this.s = true;
        return this.t.s.q(c0989jJ);
    }
}
