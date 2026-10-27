package w;

import G.C0314y;
import x.C2629d;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: w.C, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2567C extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f23051m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f23052n;
    public final /* synthetic */ long o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C2567C(int i7, long j3, Object obj) {
        super(1);
        this.f23051m = i7;
        this.f23052n = obj;
        this.o = j3;
    }

    /* JADX WARN: Type inference failed for: r13v32, types: [A7.o, z7.k] */
    /* JADX WARN: Type inference failed for: r13v35, types: [A7.o, z7.k] */
    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        InterfaceC2782k interfaceC2782k;
        InterfaceC2782k interfaceC2782k2;
        int iOrdinal;
        long jC = 0;
        long j3 = this.o;
        Object obj2 = this.f23052n;
        switch (this.f23051m) {
            case 0:
                C2569E c2569e = (C2569E) obj2;
                c2569e.getClass();
                int iOrdinal2 = ((w) obj).ordinal();
                if (iOrdinal2 == 0) {
                    t tVar = c2569e.f23056C.f23067a.c;
                    if (tVar != null && (interfaceC2782k = tVar.f23140b) != null) {
                        j3 = ((a1.j) interfaceC2782k.b(new a1.j(j3))).f11037a;
                    }
                } else if (iOrdinal2 != 1) {
                    if (iOrdinal2 != 2) {
                        throw new A9.k();
                    }
                    t tVar2 = c2569e.f23057D.f23069a.c;
                    if (tVar2 != null && (interfaceC2782k2 = tVar2.f23140b) != null) {
                        j3 = ((a1.j) interfaceC2782k2.b(new a1.j(j3))).f11037a;
                    }
                }
                return new a1.j(j3);
            case 1:
                w wVar = (w) obj;
                C2569E c2569e2 = (C2569E) obj2;
                if (c2569e2.f23061H != null && c2569e2.H0() != null && !A7.m.a(c2569e2.f23061H, c2569e2.H0()) && (iOrdinal = wVar.ordinal()) != 0 && iOrdinal != 1) {
                    if (iOrdinal != 2) {
                        throw new A9.k();
                    }
                    t tVar3 = c2569e2.f23057D.f23069a.c;
                    if (tVar3 != null) {
                        long j10 = this.o;
                        long j11 = ((a1.j) tVar3.f23140b.b(new a1.j(j10))).f11037a;
                        g0.c cVarH0 = c2569e2.H0();
                        A7.m.c(cVarH0);
                        a1.k kVar = a1.k.l;
                        long jA = cVarH0.a(j10, j11, kVar);
                        g0.c cVar = c2569e2.f23061H;
                        A7.m.c(cVar);
                        jC = a1.h.c(jA, cVar.a(j10, j11, kVar));
                    }
                }
                return new a1.h(jC);
            case 2:
                w wVar2 = (w) obj;
                C2569E c2569e3 = (C2569E) obj2;
                N n10 = c2569e3.f23056C.f23067a.f23084b;
                long j12 = n10 != null ? ((a1.h) n10.f23080a.b(new a1.j(j3))).f11033a : 0L;
                N n11 = c2569e3.f23057D.f23069a.f23084b;
                long j13 = n11 != null ? ((a1.h) n11.f23080a.b(new a1.j(j3))).f11033a : 0L;
                int iOrdinal3 = wVar2.ordinal();
                if (iOrdinal3 == 0) {
                    jC = j12;
                } else if (iOrdinal3 != 1) {
                    if (iOrdinal3 != 2) {
                        throw new A9.k();
                    }
                    jC = j13;
                }
                return new a1.h(jC);
            default:
                long jC2 = a1.h.c(((a1.h) ((C2629d) obj).d()).f11033a, j3);
                int i7 = C0314y.f2618r;
                C0314y c0314y = (C0314y) obj2;
                c0314y.e(jC2);
                c0314y.c.a();
                return m7.q.f19267a;
        }
    }
}
