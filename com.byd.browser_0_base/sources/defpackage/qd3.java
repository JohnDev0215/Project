package defpackage;

import defpackage.l61;

/* JADX INFO: compiled from: TopToolbarOverlayMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class qd3 implements l61.a {
    public final /* synthetic */ int e;
    public final /* synthetic */ td3 f;

    public qd3(td3 td3Var, int i) {
        this.f = td3Var;
        this.e = i;
    }

    @Override // l61.a
    public final void g(int i, boolean z) {
        boolean z2 = (i & this.e) > 0;
        td3 td3Var = this.f;
        td3Var.n = z2;
        td3Var.d();
    }
}
