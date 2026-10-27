package y;

import x3.AbstractC2673a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class s0 extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ float f23997m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ G7.a f23998n;
    public final /* synthetic */ int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s0(float f10, G7.a aVar, int i7) {
        super(1);
        this.f23997m = f10;
        this.f23998n = aVar;
        this.o = i7;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        Float fValueOf = Float.valueOf(this.f23997m);
        G7.a aVar = this.f23998n;
        L0.f fVar = new L0.f(((Number) AbstractC2673a.x(fValueOf, aVar)).floatValue(), aVar, this.o);
        H7.w[] wVarArr = L0.t.f3964a;
        L0.u uVar = L0.r.c;
        H7.w wVar = L0.t.f3964a[1];
        uVar.a((L0.j) obj, fVar);
        return m7.q.f19267a;
    }
}
