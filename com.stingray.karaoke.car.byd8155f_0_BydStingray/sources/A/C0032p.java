package A;

import K.C0331b0;
import S8.InterfaceC0725w;
import T.C0738e0;
import android.os.Bundle;
import androidx.lifecycle.C0973k;
import io.sentry.android.navigation.SentryNavigationListener;
import n3.C1930E;
import n3.C1945j;
import x.AbstractC2631e;
import x.C2620I;
import x.C2629d;
import x.C2645l;
import x.C2648n;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: A.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0032p extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f244m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f245n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f246p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f247q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0032p(A7.w wVar, N0 n02, A7.w wVar2, r rVar) {
        super(1);
        this.f244m = 0;
        this.f245n = wVar;
        this.f246p = n02;
        this.o = wVar2;
        this.f247q = rVar;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        boolean z6;
        switch (this.f244m) {
            case 0:
                C2645l c2645l = (C2645l) obj;
                float fFloatValue = ((Number) c2645l.f23554e.getValue()).floatValue();
                A7.w wVar = (A7.w) this.f245n;
                float f10 = fFloatValue - wVar.l;
                float fA = ((N0) this.f246p).a(f10);
                wVar.l = ((Number) c2645l.f23554e.getValue()).floatValue();
                ((A7.w) this.o).l = ((Number) c2645l.f23552a.f23395b.b(c2645l.f23555f)).floatValue();
                if (Math.abs(f10 - fA) > 0.5f) {
                    c2645l.a();
                }
                ((r) this.f247q).getClass();
                break;
            case 1:
                l3.P p10 = new l3.P((G.G) this.o, (C0.f0) this.f246p, (G.j0) this.f247q, 4);
                G.V v10 = (G.V) this.f245n;
                v10.c = p10;
                break;
            case 2:
                C0331b0 c0331b0 = (C0331b0) this.f245n;
                if (c0331b0.b()) {
                    A7.z zVar = new A7.z();
                    C0022k c0022k = new C0022k(c0331b0.d, c0331b0.f3235t, zVar, 6);
                    T0.z zVar2 = (T0.z) this.o;
                    T0.t tVar = zVar2.f8483a;
                    tVar.h((T0.y) this.f246p, (T0.m) this.f247q, c0022k, c0331b0.f3236u);
                    T0.E e10 = new T0.E(zVar2, tVar);
                    zVar2.f8484b.set(e10);
                    zVar.l = e10;
                    c0331b0.f3222e = e10;
                }
                break;
            case 3:
                A7.m.f((T.G) obj, "$this$DisposableEffect");
                C0973k c0973k = new C0973k((C1930E) this.f245n, new SentryNavigationListener(((Boolean) ((T.X) this.f246p).getValue()).booleanValue(), ((Boolean) ((T.X) this.f247q).getValue()).booleanValue()));
                A9.b bVar = (A9.b) this.o;
                bVar.M0(c0973k);
                break;
            case 4:
                C1945j c1945j = (C1945j) obj;
                A7.m.f(c1945j, "it");
                ((A7.v) this.f245n).l = true;
                ((C1930E) this.o).a((n3.y) this.f246p, (Bundle) this.f247q, c1945j, n7.u.l);
                break;
            case 5:
                C2645l c2645l2 = (C2645l) obj;
                C2629d c2629d = (C2629d) this.f245n;
                AbstractC2631e.r(c2645l2, c2629d.c);
                C0738e0 c0738e0 = c2645l2.f23554e;
                Object objA = C2629d.a(c2629d, c0738e0.getValue());
                boolean zA = A7.m.a(objA, c0738e0.getValue());
                InterfaceC2782k interfaceC2782k = (InterfaceC2782k) this.f246p;
                if (!zA) {
                    c2629d.c.f23569m.setValue(objA);
                    ((C2648n) this.o).f23569m.setValue(objA);
                    if (interfaceC2782k != null) {
                        interfaceC2782k.b(c2629d);
                    }
                    c2645l2.a();
                    ((A7.v) this.f247q).l = true;
                } else if (interfaceC2782k != null) {
                    interfaceC2782k.b(c2629d);
                }
                break;
            default:
                long jLongValue = ((Number) obj).longValue();
                T.Q0 q02 = (T.Q0) ((T.X) this.o).getValue();
                long jLongValue2 = q02 != null ? ((Number) q02.getValue()).longValue() : jLongValue;
                x.L l = (x.L) this.f246p;
                long j3 = l.c;
                V.d dVar = l.f23435a;
                InterfaceC0725w interfaceC0725w = (InterfaceC0725w) this.f247q;
                int i7 = 0;
                A7.w wVar2 = (A7.w) this.f245n;
                if (j3 == Long.MIN_VALUE || wVar2.l != AbstractC2631e.l(interfaceC0725w.getCoroutineContext())) {
                    l.c = jLongValue;
                    int i10 = dVar.f9279n;
                    if (i10 > 0) {
                        Object[] objArr = dVar.l;
                        int i11 = 0;
                        do {
                            ((C2620I) objArr[i11]).f23414r = true;
                            i11++;
                        } while (i11 < i10);
                    }
                    wVar2.l = AbstractC2631e.l(interfaceC0725w.getCoroutineContext());
                }
                float f11 = wVar2.l;
                if (f11 == 0.0f) {
                    int i12 = dVar.f9279n;
                    if (i12 > 0) {
                        Object[] objArr2 = dVar.l;
                        do {
                            C2620I c2620i = (C2620I) objArr2[i7];
                            c2620i.o.setValue(c2620i.f23412p.c);
                            c2620i.f23414r = true;
                            i7++;
                        } while (i7 < i12);
                    }
                } else {
                    long j10 = (long) ((jLongValue2 - l.c) / f11);
                    int i13 = dVar.f9279n;
                    if (i13 > 0) {
                        Object[] objArr3 = dVar.l;
                        z6 = true;
                        int i14 = 0;
                        do {
                            C2620I c2620i2 = (C2620I) objArr3[i14];
                            if (!c2620i2.f23413q) {
                                c2620i2.f23416t.f23436b.setValue(Boolean.FALSE);
                                if (c2620i2.f23414r) {
                                    c2620i2.f23414r = false;
                                    c2620i2.f23415s = j10;
                                }
                                long j11 = j10 - c2620i2.f23415s;
                                c2620i2.o.setValue(c2620i2.f23412p.b(j11));
                                c2620i2.f23413q = c2620i2.f23412p.g(j11);
                            }
                            if (!c2620i2.f23413q) {
                                z6 = false;
                            }
                            i14++;
                        } while (i14 < i13);
                    } else {
                        z6 = true;
                    }
                    l.d.setValue(Boolean.valueOf(!z6));
                }
                break;
        }
        return m7.q.f19267a;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0032p(T.X x8, x.L l, A7.w wVar, InterfaceC0725w interfaceC0725w) {
        super(1);
        this.f244m = 6;
        this.o = x8;
        this.f246p = l;
        this.f245n = wVar;
        this.f247q = interfaceC0725w;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0032p(Object obj, Object obj2, Object obj3, Object obj4, int i7) {
        super(1);
        this.f244m = i7;
        this.f245n = obj;
        this.o = obj2;
        this.f246p = obj3;
        this.f247q = obj4;
    }
}
