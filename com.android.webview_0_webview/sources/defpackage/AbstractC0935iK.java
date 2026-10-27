package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.mojo.system.impl.CoreImpl;

/* JADX INFO: renamed from: iK, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0935iK implements OJ {
    public int s;
    public CoreImpl t;

    public AbstractC0935iK(CoreImpl coreImpl, int i) {
        this.t = coreImpl;
        this.s = i;
    }

    @Override // defpackage.OJ
    public InterfaceC0544bK H() {
        return new C1102lK(this);
    }

    @Override // defpackage.OJ
    public boolean a() {
        return this.s != 0;
    }

    @Override // defpackage.OJ, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        int i = this.s;
        if (i != 0) {
            this.s = 0;
            CoreImpl coreImpl = this.t;
            Objects.requireNonNull(coreImpl);
            int iMLQe1QEw = N.MLQe1QEw(coreImpl, i);
            if (iMLQe1QEw != 0) {
                throw new WJ(iMLQe1QEw);
            }
        }
    }

    public final void finalize() throws Throwable {
        if (a()) {
            Ts.f("HandleImpl", "Handle was not closed.", new Object[0]);
            CoreImpl coreImpl = this.t;
            int i = this.s;
            Objects.requireNonNull(coreImpl);
            N.MLQe1QEw(coreImpl, i);
        }
        super.finalize();
    }

    @Override // defpackage.OJ
    public int t() {
        int i = this.s;
        this.s = 0;
        return i;
    }

    @Override // defpackage.OJ
    public LJ z() {
        return this.t;
    }

    public AbstractC0935iK(AbstractC0935iK abstractC0935iK) {
        this.t = abstractC0935iK.t;
        int i = abstractC0935iK.s;
        abstractC0935iK.s = 0;
        this.s = i;
    }
}
