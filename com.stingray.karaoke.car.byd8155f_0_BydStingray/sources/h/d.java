package H;

import A7.m;
import g0.n;

/* JADX INFO: loaded from: classes.dex */
public final class d extends n {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public c f2750y;

    @Override // g0.n
    public final void A0() {
        c cVar = this.f2750y;
        if (cVar != null) {
            m.d(cVar, "null cannot be cast to non-null type androidx.compose.foundation.relocation.BringIntoViewRequesterImpl");
            cVar.f2749a.n(this);
        }
    }

    @Override // g0.n
    public final boolean w0() {
        return false;
    }

    @Override // g0.n
    public final void z0() {
        c cVar = this.f2750y;
        if (cVar != null) {
            cVar.f2749a.n(this);
        }
        if (cVar != null) {
            cVar.f2749a.b(this);
        }
        this.f2750y = cVar;
    }
}
