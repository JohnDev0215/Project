package K7;

import Q7.InterfaceC0650c;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: K7.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0393q extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3830m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f3831n;
    public final /* synthetic */ Object o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0393q(int i7, int i10, Object obj) {
        super(0);
        this.f3830m = i10;
        this.o = obj;
        this.f3831n = i7;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f3830m) {
            case 0:
                Object obj = ((InterfaceC0650c) this.o).D0().get(this.f3831n);
                A7.m.e(obj, "descriptor.valueParameters[i]");
                return (Q7.J) obj;
            default:
                return Integer.valueOf(((N0.H) ((E5.z) this.o).f1842e).f(this.f3831n));
        }
    }
}
