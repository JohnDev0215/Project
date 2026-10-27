package Z7;

import Q7.InterfaceC0650c;
import Q7.InterfaceC0666t;
import T7.K;
import T7.Q;
import n7.AbstractC1998m;
import v8.AbstractC2564d;
import x3.AbstractC2673a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: Z7.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0902e extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C0902e f10934n = new C0902e(1, 0);
    public static final C0902e o = new C0902e(1, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final C0902e f10935p = new C0902e(1, 2);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final C0902e f10936q = new C0902e(1, 3);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final C0902e f10937r = new C0902e(1, 4);

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final C0902e f10938s = new C0902e(1, 5);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final C0902e f10939t = new C0902e(1, 6);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f10940m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0902e(int i7, int i10) {
        super(i7);
        this.f10940m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        InterfaceC0650c interfaceC0650cB;
        String strD;
        boolean z6 = false;
        switch (this.f10940m) {
            case 0:
                InterfaceC0650c interfaceC0650c = (InterfaceC0650c) obj;
                A7.m.f(interfaceC0650c, "it");
                int i7 = C0903f.l;
                return Boolean.valueOf(AbstractC1998m.T(F.f10916f, h6.f.d(interfaceC0650c)));
            case 1:
                InterfaceC0650c interfaceC0650c2 = (InterfaceC0650c) obj;
                A7.m.f(interfaceC0650c2, "it");
                if (interfaceC0650c2 instanceof InterfaceC0666t) {
                    int i10 = C0903f.l;
                    if (AbstractC1998m.T(F.f10916f, h6.f.d(interfaceC0650c2))) {
                        z6 = true;
                    }
                }
                return Boolean.valueOf(z6);
            case 2:
                InterfaceC0650c interfaceC0650c3 = (InterfaceC0650c) obj;
                A7.m.f(interfaceC0650c3, "it");
                return Boolean.valueOf(AbstractC2673a.M(interfaceC0650c3));
            case 3:
                return ((Q) obj).b();
            case 4:
                InterfaceC0650c interfaceC0650c4 = (InterfaceC0650c) obj;
                A7.m.f(interfaceC0650c4, "it");
                return Boolean.valueOf(AbstractC2673a.M(AbstractC2564d.k(interfaceC0650c4)));
            case 5:
                InterfaceC0650c interfaceC0650c5 = (InterfaceC0650c) obj;
                A7.m.f(interfaceC0650c5, "it");
                int i11 = AbstractC0901d.l;
                K k10 = (K) interfaceC0650c5;
                if (N7.i.y(k10) && AbstractC2564d.b(k10, new S0.p(k10, 7)) != null) {
                    z6 = true;
                }
                return Boolean.valueOf(z6);
            default:
                InterfaceC0650c interfaceC0650c6 = (InterfaceC0650c) obj;
                A7.m.f(interfaceC0650c6, "it");
                if (N7.i.y(interfaceC0650c6)) {
                    int i12 = C0903f.l;
                    if (F.f10915e.contains(interfaceC0650c6.getName()) && (interfaceC0650cB = AbstractC2564d.b(interfaceC0650c6, o)) != null && (strD = h6.f.d(interfaceC0650cB)) != null) {
                        if (!F.f10914b.contains(strD)) {
                        }
                        z6 = true;
                    }
                }
                return Boolean.valueOf(z6);
        }
    }
}
