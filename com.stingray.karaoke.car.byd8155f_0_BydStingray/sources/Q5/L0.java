package Q5;

import T.C0735d;
import T.C0761q;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class L0 implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ g0.o f6431m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f6432n;
    public final /* synthetic */ int o;

    public /* synthetic */ L0(g0.o oVar, InterfaceC2772a interfaceC2772a, int i7, int i10) {
        this.l = i10;
        this.f6431m = oVar;
        this.f6432n = interfaceC2772a;
        this.o = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        int i7 = this.l;
        C0761q c0761q = (C0761q) obj;
        ((Integer) obj2).getClass();
        switch (i7) {
            case 0:
                w0.c.l(this.f6431m, this.f6432n, c0761q, C0735d.Z(this.o | 1));
                break;
            case 1:
                w0.c.m(this.f6431m, this.f6432n, c0761q, C0735d.Z(this.o | 1));
                break;
            case 2:
                w0.c.p(this.f6431m, this.f6432n, c0761q, C0735d.Z(this.o | 1));
                break;
            default:
                w0.c.n(this.f6431m, this.f6432n, c0761q, C0735d.Z(this.o | 1));
                break;
        }
        return m7.q.f19267a;
    }
}
