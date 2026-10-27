package N5;

import S8.AbstractC0727y;
import android.net.Uri;
import q7.C2195i;

/* JADX INFO: loaded from: classes.dex */
public final class e0 implements k2.h {
    public final k2.q l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final c0 f4688m;

    public e0(k2.q qVar, c0 c0Var) {
        this.l = qVar;
        this.f4688m = c0Var;
    }

    @Override // k2.h
    public final void b(k2.E e10) {
        A7.m.f(e10, "transferListener");
        this.l.b(e10);
    }

    @Override // k2.h
    public final void close() {
        this.l.close();
    }

    @Override // k2.h
    public final long g(k2.m mVar) throws Throwable {
        A7.m.f(mVar, "dataSpec");
        String queryParameter = mVar.f17675a.getQueryParameter("mediaId");
        A7.z zVar = new A7.z();
        zVar.l = mVar;
        if (queryParameter != null) {
            AbstractC0727y.A(C2195i.l, new d0(this, queryParameter, zVar, mVar, null));
        }
        return this.l.g((k2.m) zVar.l);
    }

    @Override // k2.h
    public final Uri j() {
        return this.l.j();
    }

    @Override // e2.InterfaceC1201i
    public final int n(byte[] bArr, int i7, int i10) {
        A7.m.f(bArr, "buffer");
        return this.l.n(bArr, i7, i10);
    }
}
