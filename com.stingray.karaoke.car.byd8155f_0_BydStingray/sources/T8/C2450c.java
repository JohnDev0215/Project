package t8;

import F8.AbstractC0287w;
import Q7.InterfaceC0671y;

/* JADX INFO: renamed from: t8.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2450c extends g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f22496b = 1;

    public /* synthetic */ C2450c(Object obj) {
        super(obj);
    }

    @Override // t8.g
    public final AbstractC0287w a(InterfaceC0671y interfaceC0671y) {
        switch (this.f22496b) {
            case 0:
                A7.m.f(interfaceC0671y, "module");
                N7.i iVarL = interfaceC0671y.l();
                iVarL.getClass();
                return iVarL.r(N7.k.BOOLEAN);
            case 1:
                A7.m.f(interfaceC0671y, "module");
                N7.i iVarL2 = interfaceC0671y.l();
                iVarL2.getClass();
                return iVarL2.r(N7.k.DOUBLE);
            default:
                A7.m.f(interfaceC0671y, "module");
                N7.i iVarL3 = interfaceC0671y.l();
                iVarL3.getClass();
                return iVarL3.r(N7.k.FLOAT);
        }
    }

    @Override // t8.g
    public String toString() {
        switch (this.f22496b) {
            case 1:
                return ((Number) this.f22499a).doubleValue() + ".toDouble()";
            case 2:
                return ((Number) this.f22499a).floatValue() + ".toFloat()";
            default:
                return super.toString();
        }
    }

    public C2450c(double d) {
        super(Double.valueOf(d));
    }

    public C2450c(float f10) {
        super(Float.valueOf(f10));
    }
}
