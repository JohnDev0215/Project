package K;

import A.EnumC0017h0;
import T0.C0781g;
import java.util.List;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: K.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0340g extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C0340g f3254n = new C0340g(1, 0);
    public static final C0340g o = new C0340g(1, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final C0340g f3255p = new C0340g(1, 2);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final C0340g f3256q = new C0340g(1, 3);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final C0340g f3257r = new C0340g(1, 4);

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final C0340g f3258s = new C0340g(1, 5);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final C0340g f3259t = new C0340g(1, 6);

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final C0340g f3260u = new C0340g(1, 7);

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final C0340g f3261v = new C0340g(1, 8);

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final C0340g f3262w = new C0340g(1, 9);

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final C0340g f3263x = new C0340g(1, 10);

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final C0340g f3264y = new C0340g(1, 11);

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final C0340g f3265z = new C0340g(1, 12);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3266m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0340g(int i7, int i10) {
        super(i7);
        this.f3266m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        m7.q qVar = m7.q.f19267a;
        switch (this.f3266m) {
            case 0:
                return qVar;
            case 1:
                return qVar;
            case 2:
                return qVar;
            case 3:
                return qVar;
            case 4:
                x.N n10 = (x.N) obj;
                n10.f23440a = 1000;
                n10.a(Float.valueOf(1.0f), 0);
                n10.a(Float.valueOf(1.0f), 499);
                n10.a(Float.valueOf(0.0f), 500);
                n10.a(Float.valueOf(0.0f), 999);
                return qVar;
            case 5:
                O.H h10 = (O.H) obj;
                long j3 = h10.f4904f;
                int i7 = N0.K.c;
                return new C0781g(((int) (j3 & 4294967295L)) - X.s((int) (4294967295L & j3), h10.f4905g.l), 0);
            case 6:
                O.H h11 = (O.H) obj;
                String str = h11.f4905g.l;
                long j10 = h11.f4904f;
                int i10 = N0.K.c;
                int iP = X.p((int) (j10 & 4294967295L), str);
                if (iP != -1) {
                    return new C0781g(0, iP - ((int) (4294967295L & h11.f4904f)));
                }
                return null;
            case 7:
                O.H h12 = (O.H) obj;
                Integer numE = h12.e();
                if (numE == null) {
                    return null;
                }
                int iIntValue = numE.intValue();
                long j11 = h12.f4904f;
                int i11 = N0.K.c;
                return new C0781g(((int) (4294967295L & j11)) - iIntValue, 0);
            case 8:
                O.H h13 = (O.H) obj;
                Integer numD = h13.d();
                if (numD == null) {
                    return null;
                }
                int iIntValue2 = numD.intValue();
                long j12 = h13.f4904f;
                int i12 = N0.K.c;
                return new C0781g(0, iIntValue2 - ((int) (4294967295L & j12)));
            case 9:
                O.H h14 = (O.H) obj;
                Integer numC = h14.c();
                if (numC == null) {
                    return null;
                }
                int iIntValue3 = numC.intValue();
                long j13 = h14.f4904f;
                int i13 = N0.K.c;
                return new C0781g(((int) (4294967295L & j13)) - iIntValue3, 0);
            case 10:
                O.H h15 = (O.H) obj;
                Integer numB = h15.b();
                if (numB == null) {
                    return null;
                }
                int iIntValue4 = numB.intValue();
                long j14 = h15.f4904f;
                int i14 = N0.K.c;
                return new C0781g(0, iIntValue4 - ((int) (4294967295L & j14)));
            case 11:
                List list = (List) obj;
                Object obj2 = list.get(1);
                A7.m.d(obj2, "null cannot be cast to non-null type kotlin.Boolean");
                EnumC0017h0 enumC0017h0 = ((Boolean) obj2).booleanValue() ? EnumC0017h0.l : EnumC0017h0.f181m;
                Object obj3 = list.get(0);
                A7.m.d(obj3, "null cannot be cast to non-null type kotlin.Float");
                return new E0(enumC0017h0, ((Float) obj3).floatValue());
            default:
                H7.w[] wVarArr = L0.t.f3964a;
                ((L0.j) obj).l(L0.r.f3950m, qVar);
                return qVar;
        }
    }
}
