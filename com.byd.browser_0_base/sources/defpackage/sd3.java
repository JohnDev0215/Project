package defpackage;

import defpackage.kj;

/* JADX INFO: compiled from: TopToolbarOverlayMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class sd3 implements kj.a {
    public final /* synthetic */ td3 e;

    public sd3(td3 td3Var) {
        this.e = td3Var;
    }

    @Override // kj.a
    public final void c(boolean z, int i, int i2, int i3) {
        td3 td3Var = this.e;
        td3Var.i.m(ud3.j, td3Var.f.a() + i);
        td3Var.b();
        td3Var.d();
    }

    @Override // kj.a
    public final void j(int i) {
        if (cc3.b()) {
            boolean z = i == 0;
            td3 td3Var = this.e;
            td3Var.l = z;
            td3Var.b();
        }
    }
}
