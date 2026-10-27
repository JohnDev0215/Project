package Q;

import T.C0735d;
import T.C0761q;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0494a extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f5783m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ b0.b f5784n;
    public final /* synthetic */ InterfaceC2772a o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ g0.o f5785p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f5786q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f5787r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ boolean f5788s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ C0543z f5789t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ D.L f5790u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ C.k f5791v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ int f5792w;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0494a(b0.b bVar, InterfaceC2772a interfaceC2772a, g0.o oVar, InterfaceC2785n interfaceC2785n, InterfaceC2785n interfaceC2785n2, boolean z6, C0543z c0543z, D.L l, C.k kVar, int i7, int i10) {
        super(2);
        this.f5783m = i10;
        this.f5784n = bVar;
        this.o = interfaceC2772a;
        this.f5785p = oVar;
        this.f5786q = interfaceC2785n;
        this.f5787r = interfaceC2785n2;
        this.f5788s = z6;
        this.f5789t = c0543z;
        this.f5790u = l;
        this.f5791v = kVar;
        this.f5792w = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f5783m) {
            case 0:
                ((Number) obj2).intValue();
                int iZ = C0735d.Z(this.f5792w | 1);
                b0.b bVar = this.f5784n;
                boolean z6 = this.f5788s;
                C0543z c0543z = this.f5789t;
                AbstractC0496b.a(bVar, this.o, this.f5785p, this.f5786q, this.f5787r, z6, c0543z, this.f5790u, this.f5791v, (C0761q) obj, iZ);
                break;
            default:
                ((Number) obj2).intValue();
                int iZ2 = C0735d.Z(this.f5792w | 1);
                b0.b bVar2 = this.f5784n;
                boolean z10 = this.f5788s;
                C0543z c0543z2 = this.f5789t;
                C.a(bVar2, this.o, this.f5785p, this.f5786q, this.f5787r, z10, c0543z2, this.f5790u, this.f5791v, (C0761q) obj, iZ2);
                break;
        }
        return m7.q.f19267a;
    }
}
