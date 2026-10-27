package x;

import T.C0734c0;
import T.C0738e0;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class v0 extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ x0 f23647m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ float f23648n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v0(x0 x0Var, float f10) {
        super(1);
        this.f23647m = x0Var;
        this.f23648n = f10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        long jLongValue = ((Number) obj).longValue();
        x0 x0Var = this.f23647m;
        if (!x0Var.g()) {
            C0734c0 c0734c0 = x0Var.f23665g;
            if (c0734c0.i() == Long.MIN_VALUE) {
                c0734c0.j(jLongValue);
                ((C0738e0) x0Var.f23661a.f476m).setValue(Boolean.TRUE);
            }
            long jI = jLongValue - c0734c0.i();
            float f10 = this.f23648n;
            if (f10 != 0.0f) {
                jI = C7.a.Y(jI / ((double) f10));
            }
            x0Var.o(jI);
            x0Var.h(jI, f10 == 0.0f);
        }
        return m7.q.f19267a;
    }
}
