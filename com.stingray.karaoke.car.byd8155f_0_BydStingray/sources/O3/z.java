package o3;

import T.C0735d;
import T.C0761q;
import n3.C1927B;
import n3.C1930E;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class z extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f20517m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C1930E f20518n;
    public final /* synthetic */ C1927B o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ g0.o f20519p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ g0.c f20520q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f20521r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f20522s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f20523t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f20524u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f20525v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ int f20526w;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ z(C1930E c1930e, C1927B c1927b, g0.o oVar, g0.c cVar, InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2, InterfaceC2782k interfaceC2782k3, InterfaceC2782k interfaceC2782k4, InterfaceC2782k interfaceC2782k5, int i7, int i10) {
        super(2);
        this.f20517m = i10;
        this.f20518n = c1930e;
        this.o = c1927b;
        this.f20519p = oVar;
        this.f20520q = cVar;
        this.f20521r = interfaceC2782k;
        this.f20522s = interfaceC2782k2;
        this.f20523t = interfaceC2782k3;
        this.f20524u = interfaceC2782k4;
        this.f20525v = interfaceC2782k5;
        this.f20526w = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f20517m) {
            case 0:
                ((Number) obj2).intValue();
                int iZ = C0735d.Z(this.f20526w | 1);
                C1930E c1930e = this.f20518n;
                InterfaceC2782k interfaceC2782k = this.f20522s;
                InterfaceC2782k interfaceC2782k2 = this.f20523t;
                h6.q.c(c1930e, this.o, this.f20519p, this.f20520q, this.f20521r, interfaceC2782k, interfaceC2782k2, this.f20524u, this.f20525v, (C0761q) obj, iZ);
                break;
            case 1:
                ((Number) obj2).intValue();
                int iZ2 = C0735d.Z(this.f20526w | 1);
                C1930E c1930e2 = this.f20518n;
                InterfaceC2782k interfaceC2782k3 = this.f20522s;
                InterfaceC2782k interfaceC2782k4 = this.f20523t;
                h6.q.c(c1930e2, this.o, this.f20519p, this.f20520q, this.f20521r, interfaceC2782k3, interfaceC2782k4, this.f20524u, this.f20525v, (C0761q) obj, iZ2);
                break;
            default:
                ((Number) obj2).intValue();
                int iZ3 = C0735d.Z(this.f20526w | 1);
                C1930E c1930e3 = this.f20518n;
                InterfaceC2782k interfaceC2782k5 = this.f20522s;
                InterfaceC2782k interfaceC2782k6 = this.f20523t;
                h6.q.c(c1930e3, this.o, this.f20519p, this.f20520q, this.f20521r, interfaceC2782k5, interfaceC2782k6, this.f20524u, this.f20525v, (C0761q) obj, iZ3);
                break;
        }
        return m7.q.f19267a;
    }
}
