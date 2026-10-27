package defpackage;

import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: JobSupport.kt */
/* JADX INFO: loaded from: classes.dex */
public final class in extends o31 implements hn {
    public final jn i;

    public in(r31 r31Var) {
        this.i = r31Var;
    }

    @Override // defpackage.hn
    public final boolean a(Throwable th) {
        r31 r31VarM = m();
        if (th instanceof CancellationException) {
            return true;
        }
        return r31VarM.e(th) && r31VarM.k();
    }

    @Override // defpackage.jr0
    public final /* bridge */ /* synthetic */ vi3 c(Throwable th) {
        l(th);
        return vi3.a;
    }

    @Override // defpackage.tv
    public final void l(Throwable th) {
        this.i.F(m());
    }
}
