package Q;

import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q.w0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0538w0 extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C0538w0 f6063n = new C0538w0(2, 0);
    public static final C0538w0 o = new C0538w0(2, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final C0538w0 f6064p = new C0538w0(2, 2);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final C0538w0 f6065q = new C0538w0(2, 3);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final C0538w0 f6066r = new C0538w0(2, 4);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f6067m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0538w0(int i7, int i10) {
        super(i7);
        this.f6067m = i10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f6067m) {
            case 0:
                return (EnumC0542y0) ((C0540x0) obj2).f6069b.f7329f.getValue();
            case 1:
                return Integer.valueOf(((C0.J) obj).c(((Number) obj2).intValue()));
            case 2:
                return Integer.valueOf(((C0.J) obj).V(((Number) obj2).intValue()));
            case 3:
                return Integer.valueOf(((C0.J) obj).a0(((Number) obj2).intValue()));
            default:
                return Integer.valueOf(((C0.J) obj).P(((Number) obj2).intValue()));
        }
    }
}
