package A;

import B2.C0074n;
import E0.C0155e;
import F0.AbstractC0191a;
import K.InterfaceC0355n0;
import S8.AbstractC0727y;
import S8.C0712i;
import S8.InterfaceC0710g;
import T.C0735d;
import T.C0761q;
import T.C0767t0;
import T.EnumC0756n0;
import android.graphics.RectF;
import d0.C1053b;
import e0.AbstractC1155B;
import e0.C1183w;
import e0.C1184x;
import h8.AbstractC1387d;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import n0.AbstractC1861K;
import n0.C1863M;
import n0.InterfaceC1889r;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import q0.C2164c;
import u.C2453A;
import x3.AbstractC2673a;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class G0 extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f24m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f25n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ G0(Object obj, int i7) {
        super(2);
        this.f24m = i7;
        this.f25n = obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v45, types: [A7.o, z7.n] */
    /* JADX WARN: Type inference failed for: r1v37, types: [A7.o, z7.k] */
    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        boolean zF;
        int i7;
        int i10;
        d0.k kVar;
        Collection collectionN0;
        long j3 = 255;
        long j10 = -9187201950435737472L;
        char c = 7;
        InterfaceC0710g interfaceC0710gT = null;
        int i11 = 0;
        switch (this.f24m) {
            case 0:
                float fFloatValue = ((Number) obj).floatValue();
                float fFloatValue2 = ((Number) obj2).floatValue();
                J0 j02 = (J0) this.f25n;
                AbstractC0727y.w(j02.v0(), null, null, new F0(j02, fFloatValue, fFloatValue2, null), 3);
                return Boolean.TRUE;
            case 1:
                return new a1.h(AbstractC2673a.d(0, ((g0.f) this.f25n).a(0, (int) (((a1.j) obj).f11037a & 4294967295L))));
            case 2:
                return new a1.h(((g0.c) this.f25n).a(0L, ((a1.j) obj).f11037a, (a1.k) obj2));
            case 3:
                return new a1.h(AbstractC2673a.d(((g0.e) this.f25n).a(0, (int) (((a1.j) obj).f11037a >> 32), (a1.k) obj2), 0));
            case 4:
                InterfaceC1889r interfaceC1889r = (InterfaceC1889r) obj;
                C2164c c2164c = (C2164c) obj2;
                E0.d0 d0Var = (E0.d0) this.f25n;
                if (d0Var.f1648w.W()) {
                    E0.k0 snapshotObserver = E0.I.a(d0Var.f1648w).getSnapshotObserver();
                    C1863M c1863m = E0.d0.f1628Q;
                    snapshotObserver.a(d0Var, C0155e.f1653p, new C0024l(d0Var, interfaceC1889r, c2164c, 4));
                    d0Var.f1645N = false;
                } else {
                    d0Var.f1645N = true;
                }
                return m7.q.f19267a;
            case 5:
                C0761q c0761q = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
                    c0761q.K();
                } else {
                    ((AbstractC0191a) this.f25n).a(0, c0761q);
                }
                return m7.q.f19267a;
            case 6:
                ((InterfaceC0355n0) this.f25n).e(((m0.c) obj2).f18880a);
                return m7.q.f19267a;
            case 7:
                m0.d dVarG = AbstractC1861K.G((RectF) obj);
                m0.d dVarG2 = AbstractC1861K.G((RectF) obj2);
                switch (((C0074n) this.f25n).l) {
                    case 8:
                        zF = dVarG.f(dVarG2);
                        break;
                    default:
                        long jA = dVarG.a();
                        dVarG2.getClass();
                        zF = m0.c.d(jA) >= dVarG2.f18882a && m0.c.d(jA) < dVarG2.c && m0.c.e(jA) >= dVarG2.f18883b && m0.c.e(jA) < dVarG2.d;
                        break;
                }
                return Boolean.valueOf(zF);
            case 8:
                Set set = (Set) obj;
                C0767t0 c0767t0 = (C0767t0) this.f25n;
                synchronized (c0767t0.f8352b) {
                    try {
                        if (((EnumC0756n0) c0767t0.f8364r.getValue()).compareTo(EnumC0756n0.f8278p) >= 0) {
                            C2453A c2453a = c0767t0.f8355g;
                            if (set instanceof V.f) {
                                C2453A c2453a2 = ((V.f) set).l;
                                Object[] objArr = c2453a2.f22580b;
                                long[] jArr = c2453a2.f22579a;
                                int length = jArr.length - 2;
                                if (length >= 0) {
                                    int i12 = 0;
                                    while (true) {
                                        long j11 = jArr[i12];
                                        if ((((~j11) << c) & j11 & j10) != j10) {
                                            int i13 = 8 - ((~(i12 - length)) >>> 31);
                                            for (int i14 = 0; i14 < i13; i14++) {
                                                if ((j11 & 255) < 128) {
                                                    Object obj3 = objArr[(i12 << 3) + i14];
                                                    if (!(obj3 instanceof AbstractC1155B) || ((AbstractC1155B) obj3).g(1)) {
                                                        c2453a.a(obj3);
                                                    }
                                                }
                                                j11 >>= 8;
                                            }
                                            i7 = 1;
                                            if (i13 == 8) {
                                            }
                                        } else {
                                            i7 = 1;
                                        }
                                        if (i12 != length) {
                                            i12 += i7;
                                            j10 = -9187201950435737472L;
                                            c = 7;
                                        }
                                    }
                                }
                            } else {
                                for (Object obj4 : set) {
                                    if (!(obj4 instanceof AbstractC1155B) || ((AbstractC1155B) obj4).g(1)) {
                                        c2453a.a(obj4);
                                    }
                                }
                            }
                            interfaceC0710gT = c0767t0.t();
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                    break;
                }
                if (interfaceC0710gT != null) {
                    ((C0712i) interfaceC0710gT).resumeWith(m7.q.f19267a);
                }
                return m7.q.f19267a;
            case 9:
                Set set2 = (Set) obj;
                if (set2 instanceof V.f) {
                    C2453A c2453a3 = ((V.f) set2).l;
                    Object[] objArr2 = c2453a3.f22580b;
                    long[] jArr2 = c2453a3.f22579a;
                    int length2 = jArr2.length - 2;
                    if (length2 >= 0) {
                        int i15 = 0;
                        while (true) {
                            long j12 = jArr2[i15];
                            if ((((~j12) << 7) & j12 & (-9187201950435737472L)) != -9187201950435737472L) {
                                int i16 = 8 - ((~(i15 - length2)) >>> 31);
                                int i17 = 0;
                                while (i17 < i16) {
                                    if ((j12 & j3) < 128) {
                                        Object obj5 = objArr2[(i15 << 3) + i17];
                                        if (!(obj5 instanceof AbstractC1155B) || ((AbstractC1155B) obj5).g(4)) {
                                        }
                                    }
                                    j12 >>= 8;
                                    i17++;
                                    j3 = 255;
                                }
                                i10 = 1;
                                if (i16 == 8) {
                                }
                            } else {
                                i10 = 1;
                            }
                            if (i15 != length2) {
                                i15 += i10;
                                j3 = 255;
                            }
                        }
                    }
                } else {
                    Set set3 = set2;
                    if (!(set3 instanceof Collection) || !set3.isEmpty()) {
                        for (Object obj6 : set3) {
                            if (!(obj6 instanceof AbstractC1155B) || ((AbstractC1155B) obj6).g(4)) {
                                ((U8.l) this.f25n).p(set2);
                            }
                        }
                    }
                }
                return m7.q.f19267a;
            case 10:
                C1053b c1053b = (C1053b) obj;
                List list = (List) ((A7.o) this.f25n).invoke(c1053b, obj2);
                int size = list.size();
                while (i11 < size) {
                    Object obj7 = list.get(i11);
                    if (obj7 != null && (kVar = c1053b.f13255m) != null && !kVar.b(obj7)) {
                        throw new IllegalArgumentException("item can't be saved");
                    }
                    i11++;
                }
                if (list.isEmpty()) {
                    return null;
                }
                return new ArrayList(list);
            case 11:
                Collection collection = (Set) obj;
                while (true) {
                    C1184x c1184x = (C1184x) this.f25n;
                    AtomicReference atomicReference = c1184x.f13747b;
                    Object obj8 = atomicReference.get();
                    if (obj8 == null) {
                        collectionN0 = collection;
                    } else if (obj8 instanceof Set) {
                        collectionN0 = AbstractC1999n.D(obj8, collection);
                    } else {
                        if (!(obj8 instanceof List)) {
                            C0735d.z("Unexpected notification");
                            throw null;
                        }
                        collectionN0 = AbstractC1998m.n0((Collection) obj8, AbstractC1387d.j(collection));
                    }
                    while (!atomicReference.compareAndSet(obj8, collectionN0)) {
                        if (atomicReference.get() != obj8) {
                        }
                        break;
                    }
                    if (C1184x.a(c1184x)) {
                        c1184x.f13746a.b(new C1183w(c1184x, i11));
                    }
                    return m7.q.f19267a;
                }
                break;
            case 12:
                g0.o oVar = (g0.o) obj;
                g0.o oVarC = (g0.m) obj2;
                if (oVarC instanceof g0.j) {
                    InterfaceC2786o interfaceC2786o = ((g0.j) oVarC).c;
                    A7.E.e(3, interfaceC2786o);
                    g0.l lVar = g0.l.f14684a;
                    C0761q c0761q2 = (C0761q) this.f25n;
                    oVarC = g0.a.c(c0761q2, (g0.o) interfaceC2786o.g(lVar, c0761q2, 0));
                }
                return oVar.k(oVarC);
            default:
                w.w wVar = (w.w) obj;
                w.w wVar2 = (w.w) obj2;
                w.w wVar3 = w.w.f23146n;
                return Boolean.valueOf(wVar == wVar3 && wVar2 == wVar3 && !((w.G) this.f25n).f23069a.d);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public G0(InterfaceC2785n interfaceC2785n) {
        super(2);
        this.f24m = 10;
        this.f25n = (A7.o) interfaceC2785n;
    }
}
