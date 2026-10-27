package E;

import G.T;
import G.V;
import G.W;
import G.h0;
import e0.AbstractC1167g;
import e0.AbstractC1179s;
import java.util.Collection;
import java.util.List;
import l0.AbstractC1713d;
import l0.C1727r;
import l3.P;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class x extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f1447m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f1448n;
    public final /* synthetic */ Object o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ x(int i7, int i10, Object obj) {
        super(1);
        this.f1447m = i10;
        this.o = obj;
        this.f1448n = i7;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f1447m) {
            case 0:
                T t10 = (T) obj;
                C0144a c0144a = ((D) this.o).f1302a;
                AbstractC1167g abstractC1167gC = AbstractC1179s.c();
                AbstractC1179s.f(abstractC1167gC, AbstractC1179s.d(abstractC1167gC), abstractC1167gC != null ? abstractC1167gC.f() : null);
                for (int i7 = 0; i7 < c0144a.f1326a; i7++) {
                    int i10 = this.f1448n + i7;
                    t10.getClass();
                    long j3 = W.f2527a;
                    V v10 = t10.f2524b;
                    P p10 = v10.c;
                    if (p10 != null) {
                        t10.f2523a.add(new h0(p10, i10, j3, v10.f2526b));
                    }
                }
                return m7.q.f19267a;
            case 1:
                T t11 = (T) obj;
                C0144a c0144a2 = ((F.y) this.o).f1973a;
                AbstractC1167g abstractC1167gC2 = AbstractC1179s.c();
                AbstractC1179s.f(abstractC1167gC2, AbstractC1179s.d(abstractC1167gC2), abstractC1167gC2 != null ? abstractC1167gC2.f() : null);
                for (int i11 = 0; i11 < c0144a2.f1326a; i11++) {
                    int i12 = this.f1448n + i11;
                    t11.getClass();
                    long j10 = W.f2527a;
                    V v11 = t11.f2524b;
                    P p11 = v11.c;
                    if (p11 != null) {
                        t11.f2523a.add(new h0(p11, i12, j10, v11.f2526b));
                    }
                }
                return m7.q.f19267a;
            case 2:
                return Boolean.valueOf(((List) obj).addAll(this.f1448n, (Collection) this.o));
            default:
                Boolean boolB = AbstractC1713d.B((C1727r) obj, this.f1448n);
                ((A7.z) this.o).l = boolB;
                return Boolean.valueOf(boolB != null ? boolB.booleanValue() : false);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x(int i7, Collection collection) {
        super(1);
        this.f1447m = 2;
        this.f1448n = i7;
        this.o = collection;
    }
}
