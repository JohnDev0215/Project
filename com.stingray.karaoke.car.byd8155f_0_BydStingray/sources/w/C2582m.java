package w;

import x.x0;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: w.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2582m extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f23116m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ x0 f23117n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C2582m(x0 x0Var, int i7) {
        super(0);
        this.f23116m = i7;
        this.f23117n = x0Var;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f23116m) {
            case 0:
                x0 x0Var = this.f23117n;
                Object objP0 = x0Var.f23661a.P0();
                w wVar = w.f23146n;
                return Boolean.valueOf(objP0 == wVar && x0Var.d.getValue() == wVar);
            default:
                return Long.valueOf(this.f23117n.b());
        }
    }
}
