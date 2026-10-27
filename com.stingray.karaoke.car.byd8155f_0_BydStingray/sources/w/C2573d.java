package w;

import x.AbstractC2631e;
import x.L0;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: w.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2573d extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C2573d f23096n = new C2573d(2, 0);
    public static final C2573d o = new C2573d(2, 1);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f23097m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C2573d(int i7, int i10) {
        super(i7);
        this.f23097m = i10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f23097m) {
            case 0:
                long j3 = ((a1.j) obj).f11037a;
                long j10 = ((a1.j) obj2).f11037a;
                Object obj3 = L0.f23437a;
                return AbstractC2631e.p(400.0f, 1, new a1.j(H7.H.c(1, 1)));
            default:
                w wVar = (w) obj2;
                return Boolean.valueOf(((w) obj) == wVar && wVar == w.f23146n);
        }
    }
}
