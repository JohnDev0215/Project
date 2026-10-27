package K6;

import T.C0735d;
import T.C0761q;
import g0.o;
import g1.AbstractC1274a;
import m7.q;
import x3.AbstractC2673a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ o f3664m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f3665n;

    public /* synthetic */ a(o oVar, int i7, int i10) {
        this.l = i10;
        this.f3664m = oVar;
        this.f3665n = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        int i7 = this.l;
        C0761q c0761q = (C0761q) obj;
        ((Integer) obj2).getClass();
        switch (i7) {
            case 0:
                l9.l.a(this.f3664m, c0761q, C0735d.Z(this.f3665n | 1));
                break;
            case 1:
                AbstractC1274a.i(this.f3664m, c0761q, C0735d.Z(this.f3665n | 1));
                break;
            default:
                AbstractC2673a.c(this.f3664m, c0761q, C0735d.Z(this.f3665n | 1));
                break;
        }
        return q.f19267a;
    }
}
