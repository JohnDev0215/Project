package G;

import A.EnumC0017h0;
import E0.p0;

/* JADX INFO: loaded from: classes.dex */
public final class b0 extends g0.n implements p0 {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public EnumC0017h0 f2542A;
    public boolean B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public boolean f2543C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public L0.h f2544D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final Z f2545E = new Z(this, 0);

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public Z f2546F;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public H7.r f2547y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public X f2548z;

    public b0(H7.r rVar, X x8, EnumC0017h0 enumC0017h0, boolean z6, boolean z10) {
        this.f2547y = rVar;
        this.f2548z = x8;
        this.f2542A = enumC0017h0;
        this.B = z6;
        this.f2543C = z10;
        H0();
    }

    @Override // E0.p0
    public final void H(L0.j jVar) {
        H7.w[] wVarArr = L0.t.f3964a;
        L0.u uVar = L0.r.l;
        H7.w[] wVarArr2 = L0.t.f3964a;
        H7.w wVar = wVarArr2[6];
        uVar.a(jVar, Boolean.TRUE);
        jVar.l(L0.r.f3938E, this.f2545E);
        if (this.f2542A == EnumC0017h0.l) {
            L0.h hVar = this.f2544D;
            if (hVar == null) {
                A7.m.j("scrollAxisRange");
                throw null;
            }
            L0.u uVar2 = L0.r.f3952p;
            H7.w wVar2 = wVarArr2[11];
            uVar2.a(jVar, hVar);
        } else {
            L0.h hVar2 = this.f2544D;
            if (hVar2 == null) {
                A7.m.j("scrollAxisRange");
                throw null;
            }
            L0.u uVar3 = L0.r.o;
            H7.w wVar3 = wVarArr2[10];
            uVar3.a(jVar, hVar2);
        }
        Z z6 = this.f2546F;
        if (z6 != null) {
            jVar.l(L0.i.f3890f, new L0.a(null, z6));
        }
        jVar.l(L0.i.f3886A, new L0.a(null, new A.F(new Y(this, 0), 24)));
        L0.b bVarC = this.f2548z.c();
        L0.u uVar4 = L0.r.f3944f;
        H7.w wVar4 = wVarArr2[20];
        uVar4.a(jVar, bVarC);
    }

    public final void H0() {
        this.f2544D = new L0.h(new Y(this, 1), new Y(this, 2), this.f2543C);
        this.f2546F = this.B ? new Z(this, 1) : null;
    }

    @Override // g0.n
    public final boolean w0() {
        return false;
    }
}
