package D;

import A.C0022k;
import A.EnumC0017h0;
import C0.InterfaceC0097d;
import K.E0;
import K.G0;
import K.O0;
import e0.InterfaceC1154A;
import java.io.Serializable;
import l0.AbstractC1713d;
import l0.C1727r;
import u.C2481u;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class Q extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f1044m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f1045n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f1046p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f1047q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Q(int i7, int i10, Object obj, Object obj2, Object obj3) {
        super(1);
        this.f1044m = i10;
        this.o = obj;
        this.f1046p = obj2;
        this.f1047q = obj3;
        this.f1045n = i7;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f1044m) {
            case 0:
                C0.U u4 = (C0.U) obj;
                C0.V[] vArr = (C0.V[]) this.o;
                int length = vArr.length;
                int i7 = 0;
                int i10 = 0;
                while (i7 < length) {
                    C0.V v10 = vArr[i7];
                    int i11 = i10 + 1;
                    A7.m.c(v10);
                    Object objI = v10.i();
                    O o = objI instanceof O ? (O) objI : null;
                    S s6 = (S) this.f1046p;
                    s6.getClass();
                    AbstractC0120b abstractC0120b = o != null ? o.c : null;
                    int i12 = this.f1045n;
                    C0.U.d(u4, v10, ((int[]) this.f1047q)[i10], abstractC0120b != null ? abstractC0120b.c(i12 - v10.f871m, a1.k.l) : s6.f1049b.a(0, i12 - v10.f871m));
                    i7++;
                    i10 = i11;
                }
                return m7.q.f19267a;
            case 1:
                C0.U u10 = (C0.U) obj;
                K.S s10 = (K.S) this.f1046p;
                int i13 = s10.f3199b;
                G0 g02 = (G0) s10.d.a();
                N0.H h10 = g02 != null ? g02.f3161a : null;
                C0.M m10 = (C0.M) this.o;
                boolean z6 = m10.getLayoutDirection() == a1.k.f11038m;
                C0.V v11 = (C0.V) this.f1047q;
                m0.d dVarJ = K.X.j(m10, i13, s10.c, h10, z6, v11.l);
                EnumC0017h0 enumC0017h0 = EnumC0017h0.f181m;
                int i14 = v11.l;
                E0 e02 = s10.f3198a;
                e02.a(enumC0017h0, dVarJ, this.f1045n, i14);
                C0.U.f(u10, v11, Math.round(-e02.f3142a.i()), 0);
                return m7.q.f19267a;
            case 2:
                C0.U u11 = (C0.U) obj;
                O0 o02 = (O0) this.f1046p;
                int i15 = o02.f3193b;
                G0 g03 = (G0) o02.d.a();
                N0.H h11 = g03 != null ? g03.f3161a : null;
                C0.V v12 = (C0.V) this.f1047q;
                m0.d dVarJ2 = K.X.j((C0.M) this.o, i15, o02.c, h11, false, v12.l);
                EnumC0017h0 enumC0017h02 = EnumC0017h0.l;
                int i16 = v12.f871m;
                E0 e03 = o02.f3192a;
                e03.a(enumC0017h02, dVarJ2, this.f1045n, i16);
                C0.U.f(u11, v12, 0, Math.round(-e03.f3142a.i()));
                return m7.q.f19267a;
            case 3:
                if (obj == ((T.D) this.o)) {
                    throw new IllegalStateException("A derived state calculation cannot read itself");
                }
                if (obj instanceof InterfaceC1154A) {
                    int i17 = ((b0.c) this.f1046p).f12193a - this.f1045n;
                    C2481u c2481u = (C2481u) this.f1047q;
                    int iC = c2481u.c(obj);
                    c2481u.f(Math.min(i17, iC >= 0 ? c2481u.c[iC] : Integer.MAX_VALUE), obj);
                }
                return m7.q.f19267a;
            case 4:
                InterfaceC0097d interfaceC0097d = (InterfaceC0097d) obj;
                boolean zH = AbstractC1713d.H((C1727r) this.o, (C1727r) this.f1046p, this.f1045n, (C0022k) this.f1047q);
                Boolean boolValueOf = Boolean.valueOf(zH);
                if (zH || !interfaceC0097d.a()) {
                    return boolValueOf;
                }
                return null;
            default:
                InterfaceC0097d interfaceC0097d2 = (InterfaceC0097d) obj;
                boolean zG = AbstractC1713d.G(this.f1045n, (C0022k) this.f1047q, (C1727r) this.o, (m0.d) this.f1046p);
                Boolean boolValueOf2 = Boolean.valueOf(zG);
                if (zG || !interfaceC0097d2.a()) {
                    return boolValueOf2;
                }
                return null;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Q(Object obj, Object obj2, int i7, Serializable serializable, int i10) {
        super(1);
        this.f1044m = i10;
        this.o = obj;
        this.f1046p = obj2;
        this.f1045n = i7;
        this.f1047q = serializable;
    }
}
