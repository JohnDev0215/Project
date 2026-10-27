package K;

import A.EnumC0017h0;
import B2.C0074n;
import T.C0753m;
import T.C0761q;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: K.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0363v extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final /* synthetic */ boolean f3407A;
    public final /* synthetic */ InterfaceC2782k B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final /* synthetic */ T0.s f3408C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final /* synthetic */ a1.b f3409D;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0331b0 f3410m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ N0.L f3411n;
    public final /* synthetic */ int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f3412p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ E0 f3413q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ T0.y f3414r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ C0074n f3415s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ g0.o f3416t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ g0.o f3417u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ g0.o f3418v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ g0.o f3419w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final /* synthetic */ H.c f3420x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final /* synthetic */ O.K f3421y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final /* synthetic */ boolean f3422z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0363v(C0331b0 c0331b0, N0.L l, int i7, int i10, E0 e02, T0.y yVar, C0074n c0074n, g0.o oVar, g0.o oVar2, g0.o oVar3, g0.o oVar4, H.c cVar, O.K k10, boolean z6, boolean z10, InterfaceC2782k interfaceC2782k, T0.s sVar, a1.b bVar) {
        super(2);
        this.f3410m = c0331b0;
        this.f3411n = l;
        this.o = i7;
        this.f3412p = i10;
        this.f3413q = e02;
        this.f3414r = yVar;
        this.f3415s = c0074n;
        this.f3416t = oVar;
        this.f3417u = oVar2;
        this.f3418v = oVar3;
        this.f3419w = oVar4;
        this.f3420x = cVar;
        this.f3421y = k10;
        this.f3422z = z6;
        this.f3407A = z10;
        this.B = interfaceC2782k;
        this.f3408C = sVar;
        this.f3409D = bVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        N0.L l;
        int iE;
        g0.o o02;
        C0761q c0761q = (C0761q) obj;
        if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            g0.l lVar = g0.l.f14684a;
            C0331b0 c0331b0 = this.f3410m;
            g0.o oVarG = androidx.compose.foundation.layout.c.g(lVar, ((a1.e) c0331b0.f3224g.getValue()).l, 0.0f, 2);
            int i7 = this.o;
            int i10 = this.f3412p;
            N0.L l10 = this.f3411n;
            g0.o oVarB = g0.a.b(oVarG, new Q(i7, i10, l10));
            boolean zH = c0761q.h(c0331b0);
            Object objG = c0761q.G();
            if (zH || objG == C0753m.f8270a) {
                objG = new A.I0(c0331b0, 25);
                c0761q.a0(objG);
            }
            InterfaceC2772a interfaceC2772a = (InterfaceC2772a) objG;
            E0 e02 = this.f3413q;
            EnumC0017h0 enumC0017h0 = (EnumC0017h0) e02.f3144e.getValue();
            T0.y yVar = this.f3414r;
            int i11 = N0.K.c;
            long j3 = yVar.f8482b;
            int i12 = (int) (j3 >> 32);
            long j10 = e02.d;
            if (i12 != ((int) (j10 >> 32))) {
                iE = i12;
                l = l10;
            } else {
                l = l10;
                int i13 = (int) (j3 & 4294967295L);
                iE = i13 != ((int) (4294967295L & j10)) ? i13 : N0.K.e(j3);
            }
            e02.d = j3;
            T0.F fO = X.o(this.f3415s, yVar.f8481a);
            int iOrdinal = enumC0017h0.ordinal();
            if (iOrdinal == 0) {
                o02 = new O0(e02, iE, fO, interfaceC2772a);
            } else {
                if (iOrdinal != 1) {
                    throw new A9.k();
                }
                o02 = new S(e02, iE, fO, interfaceC2772a);
            }
            l9.d.s(androidx.compose.foundation.relocation.a.a(g0.a.b(h6.q.n(oVarB).k(o02).k(this.f3416t).k(this.f3417u), new A.E(l, 2)).k(this.f3418v).k(this.f3419w), this.f3420x), b0.g.b(-363167407, new C0362u(this.f3421y, c0331b0, this.f3422z, this.f3407A, this.B, yVar, this.f3408C, this.f3409D, this.f3412p), c0761q), c0761q, 48);
        }
        return m7.q.f19267a;
    }
}
