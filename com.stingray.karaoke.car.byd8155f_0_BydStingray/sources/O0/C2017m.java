package o0;

import x3.AbstractC2673a;

/* JADX INFO: renamed from: o0.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C2017m implements InterfaceC2013i {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C2021q f20209m;

    public /* synthetic */ C2017m(C2021q c2021q, int i7) {
        this.l = i7;
        this.f20209m = c2021q;
    }

    @Override // o0.InterfaceC2013i
    public final double c(double d) {
        switch (this.l) {
            case 0:
                return AbstractC2673a.t(this.f20209m.f20221k.c(d), r8.f20215e, r8.f20216f);
            default:
                return this.f20209m.f20223n.c(AbstractC2673a.t(d, r8.f20215e, r8.f20216f));
        }
    }
}
