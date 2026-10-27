package E0;

import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class c0 extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f1619m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ d0 f1620n;
    public final /* synthetic */ g0.n o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C0154d f1621p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ long f1622q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ C0168s f1623r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ boolean f1624s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ boolean f1625t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ float f1626u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ c0(d0 d0Var, g0.n nVar, C0154d c0154d, long j3, C0168s c0168s, boolean z6, boolean z10, float f10, int i7) {
        super(0);
        this.f1619m = i7;
        this.f1620n = d0Var;
        this.o = nVar;
        this.f1621p = c0154d;
        this.f1622q = j3;
        this.f1623r = c0168s;
        this.f1624s = z6;
        this.f1625t = z10;
        this.f1626u = f10;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f1619m) {
            case 0:
                g0.n nVarE = AbstractC0156f.e(this.o, this.f1621p.a());
                boolean z6 = this.f1625t;
                d0 d0Var = this.f1620n;
                C0154d c0154d = this.f1621p;
                long j3 = this.f1622q;
                C0168s c0168s = this.f1623r;
                boolean z10 = this.f1624s;
                if (nVarE == null) {
                    d0Var.V0(c0154d, j3, c0168s, z10, z6);
                } else {
                    d0Var.getClass();
                    float f10 = this.f1626u;
                    c0168s.l(nVarE, f10, z6, new c0(d0Var, nVarE, c0154d, j3, c0168s, z10, z6, f10, 0));
                }
                break;
            default:
                this.f1620n.g1(AbstractC0156f.e(this.o, this.f1621p.a()), this.f1621p, this.f1622q, this.f1623r, this.f1624s, this.f1625t, this.f1626u);
                break;
        }
        return m7.q.f19267a;
    }
}
