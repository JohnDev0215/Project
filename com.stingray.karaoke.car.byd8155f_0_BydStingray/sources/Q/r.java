package Q;

import T.C0735d;
import T.C0761q;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class r extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ g0.o f6024m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ float f6025n;
    public final /* synthetic */ long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f6026p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f6027q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(g0.o oVar, float f10, long j3, int i7, int i10) {
        super(2);
        this.f6024m = oVar;
        this.f6025n = f10;
        this.o = j3;
        this.f6026p = i7;
        this.f6027q = i10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int iZ = C0735d.Z(this.f6026p | 1);
        g0.o oVar = this.f6024m;
        L.b(oVar, this.f6025n, this.o, (C0761q) obj, iZ, this.f6027q);
        return m7.q.f19267a;
    }
}
