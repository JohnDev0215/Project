package w;

import n0.AbstractC1861K;
import n0.C1870U;
import n0.C1891t;
import o0.C2008d;
import x.AbstractC2631e;
import x.C2650p;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: w.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2584o extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C2584o f23120n = new C2584o(1, 0);
    public static final C2584o o = new C2584o(1, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final C2584o f23121p = new C2584o(1, 2);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final C2584o f23122q = new C2584o(1, 3);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final C2584o f23123r = new C2584o(1, 4);

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final C2584o f23124s = new C2584o(1, 5);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final C2584o f23125t = new C2584o(1, 6);

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final C2584o f23126u = new C2584o(1, 7);

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final C2584o f23127v = new C2584o(1, 8);

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final C2584o f23128w = new C2584o(1, 9);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f23129m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C2584o(int i7, int i10) {
        super(i7);
        this.f23129m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f23129m) {
            case 0:
                Boolean bool = (Boolean) obj;
                bool.booleanValue();
                return bool;
            case 1:
                Boolean bool2 = (Boolean) obj;
                bool2.booleanValue();
                return bool2;
            case 2:
                long jA = C1891t.a(((C1891t) obj).f19453a, C2008d.f20195t);
                return new x.r(C1891t.d(jA), C1891t.h(jA), C1891t.g(jA), C1891t.e(jA));
            case 3:
                return obj;
            case 4:
                long j3 = ((C1870U) obj).f19423a;
                return new C2650p(C1870U.b(j3), C1870U.c(j3));
            case 5:
                C2650p c2650p = (C2650p) obj;
                return new C1870U(AbstractC1861K.j(c2650p.f23598a, c2650p.f23599b));
            case 6:
                return AbstractC2631e.p(0.0f, 7, null);
            case 7:
                ((Number) obj).intValue();
                return 0;
            case 8:
                ((Number) obj).intValue();
                return 0;
            default:
                return AbstractC2565A.c;
        }
    }
}
