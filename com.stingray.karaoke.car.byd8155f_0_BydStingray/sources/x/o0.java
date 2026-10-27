package x;

import e0.C1184x;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class o0 extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f23597m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final o0 f23585n = new o0(1, 0);
    public static final o0 o = new o0(1, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final o0 f23586p = new o0(1, 2);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final o0 f23587q = new o0(1, 3);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final o0 f23588r = new o0(1, 4);

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final o0 f23589s = new o0(1, 5);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final o0 f23590t = new o0(1, 6);

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final o0 f23591u = new o0(1, 7);

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final o0 f23592v = new o0(1, 8);

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final o0 f23593w = new o0(1, 9);

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final o0 f23594x = new o0(1, 10);

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final o0 f23595y = new o0(1, 11);

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final o0 f23596z = new o0(1, 12);

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public static final o0 f23578A = new o0(1, 13);
    public static final o0 B = new o0(1, 14);

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public static final o0 f23579C = new o0(1, 15);

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public static final o0 f23580D = new o0(1, 16);

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public static final o0 f23581E = new o0(1, 17);

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public static final o0 f23582F = new o0(1, 18);

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public static final o0 f23583G = new o0(1, 19);

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public static final o0 f23584H = new o0(1, 20);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ o0(int i7, int i10) {
        super(i7);
        this.f23597m = i10;
    }

    /* JADX WARN: Type inference failed for: r4v4, types: [java.lang.Object, m7.f] */
    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f23597m) {
            case 0:
                return m7.q.f19267a;
            case 1:
                ((InterfaceC2772a) obj).a();
                return m7.q.f19267a;
            case 2:
                C2634f0 c2634f0 = (C2634f0) obj;
                long j3 = c2634f0.f23522r;
                ((C1184x) B0.f23389a.getValue()).d(c2634f0, f23586p, c2634f0.f23523s);
                long j10 = c2634f0.f23522r;
                if (j3 != j10) {
                    U u4 = c2634f0.f23530z;
                    if (u4 != null) {
                        u4.f23458g = j10;
                        if (u4.f23455b == null) {
                            u4.f23459h = C7.a.Y((1.0d - ((double) u4.f23456e.a(0))) * c2634f0.f23522r);
                        }
                    } else if (j10 != 0) {
                        c2634f0.i1();
                    }
                }
                return m7.q.f19267a;
            case 3:
                long j11 = ((a1.f) obj).f11031a;
                return new C2650p(Float.intBitsToFloat((int) (j11 >> 32)), Float.intBitsToFloat((int) (j11 & 4294967295L)));
            case 4:
                C2650p c2650p = (C2650p) obj;
                return new a1.f((((long) Float.floatToRawIntBits(c2650p.f23599b)) & 4294967295L) | (((long) Float.floatToRawIntBits(c2650p.f23598a)) << 32));
            case 5:
                return new C2649o(((a1.e) obj).l);
            case 6:
                return new a1.e(((C2649o) obj).f23577a);
            case 7:
                return new C2649o(((Number) obj).floatValue());
            case 8:
                return Float.valueOf(((C2649o) obj).f23577a);
            case 9:
                long j12 = ((a1.h) obj).f11033a;
                return new C2650p((int) (j12 >> 32), (int) (j12 & 4294967295L));
            case 10:
                C2650p c2650p2 = (C2650p) obj;
                return new a1.h(AbstractC2673a.d(Math.round(c2650p2.f23598a), Math.round(c2650p2.f23599b)));
            case 11:
                long j13 = ((a1.j) obj).f11037a;
                return new C2650p((int) (j13 >> 32), (int) (j13 & 4294967295L));
            case 12:
                C2650p c2650p3 = (C2650p) obj;
                int iRound = Math.round(c2650p3.f23598a);
                if (iRound < 0) {
                    iRound = 0;
                }
                int iRound2 = Math.round(c2650p3.f23599b);
                return new a1.j(H7.H.c(iRound, iRound2 >= 0 ? iRound2 : 0));
            case 13:
                return new C2649o(((Number) obj).intValue());
            case 14:
                return Integer.valueOf((int) ((C2649o) obj).f23577a);
            case 15:
                long j14 = ((m0.c) obj).f18880a;
                return new C2650p(m0.c.d(j14), m0.c.e(j14));
            case 16:
                C2650p c2650p4 = (C2650p) obj;
                return new m0.c(h6.e.a(c2650p4.f23598a, c2650p4.f23599b));
            case 17:
                m0.d dVar = (m0.d) obj;
                return new r(dVar.f18882a, dVar.f18883b, dVar.c, dVar.d);
            case 18:
                r rVar = (r) obj;
                return new m0.d(rVar.f23611a, rVar.f23612b, rVar.c, rVar.d);
            case 19:
                long j15 = ((m0.f) obj).f18890a;
                return new C2650p(m0.f.d(j15), m0.f.b(j15));
            default:
                C2650p c2650p5 = (C2650p) obj;
                return new m0.f(h6.j.f(c2650p5.f23598a, c2650p5.f23599b));
        }
    }
}
