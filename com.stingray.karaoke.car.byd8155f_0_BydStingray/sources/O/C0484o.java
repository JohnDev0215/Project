package O;

import z7.InterfaceC2772a;

/* JADX INFO: renamed from: O.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0484o extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ E5.z f4979m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f4980n;
    public final /* synthetic */ int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ t4.e f4981p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f4982q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0484o(E5.z zVar, int i7, int i10, t4.e eVar, m7.f fVar) {
        super(0);
        this.f4979m = zVar;
        this.f4980n = i7;
        this.o = i10;
        this.f4981p = eVar;
        this.f4982q = fVar;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.Object, m7.f] */
    @Override // z7.InterfaceC2772a
    public final Object a() {
        int iIntValue = ((Number) this.f4982q.getValue()).intValue();
        t4.e eVar = this.f4981p;
        boolean z6 = eVar.f() == 1;
        E5.z zVar = this.f4979m;
        N0.H h10 = (N0.H) zVar.f1842e;
        int i7 = this.f4980n;
        long jL = h10.l(i7);
        int i10 = N0.K.c;
        int i11 = (int) (jL >> 32);
        int iF = h10.f(i11);
        int i12 = h10.f4349b.f4394f;
        if (iF != iIntValue) {
            i11 = iIntValue >= i12 ? h10.i(i12 - 1) : h10.i(iIntValue);
        }
        int iE = (int) (jL & 4294967295L);
        if (h10.f(iE) != iIntValue) {
            iE = iIntValue >= i12 ? h10.e(i12 - 1, false) : h10.e(iIntValue, false);
        }
        int i13 = this.o;
        if (i11 == i13) {
            return zVar.b(iE);
        }
        if (iE == i13) {
            return zVar.b(i11);
        }
        if (!(eVar.f22462m ^ z6) ? i7 >= i11 : i7 > iE) {
            i11 = iE;
        }
        return zVar.b(i11);
    }
}
