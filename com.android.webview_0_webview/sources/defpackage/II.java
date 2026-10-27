package defpackage;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class II implements InterfaceC1212nJ {
    public final InterfaceC1212nJ s;
    public final Executor t;
    public final Exception u;
    public boolean v;

    public II(LJ lj, InterfaceC1212nJ interfaceC1212nJ) {
        this.s = interfaceC1212nJ;
        Executor xi = (Executor) YI.a.get();
        if (xi == null) {
            xi = new XI(lj);
            YI.a.set(xi);
        }
        this.t = xi;
        this.u = new Exception("AutocloseableRouter allocated at:");
    }

    @Override // defpackage.InterfaceC1101lJ, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.s.close();
        this.v = true;
    }

    @Override // defpackage.ZI
    public OJ e() {
        return (VJ) this.s.e();
    }

    public void finalize() throws Throwable {
        if (this.v) {
            super.finalize();
        } else {
            this.t.execute(new HI(this));
            throw new IllegalStateException("Warning: Router objects should be explicitly closed when no longer required otherwise you may leak handles.", this.u);
        }
    }

    @Override // defpackage.InterfaceC1157mJ
    public boolean p(C0989jJ c0989jJ, InterfaceC1101lJ interfaceC1101lJ) {
        return this.s.p(c0989jJ, interfaceC1101lJ);
    }

    @Override // defpackage.InterfaceC1101lJ
    public boolean q(C0989jJ c0989jJ) {
        return this.s.q(c0989jJ);
    }
}
