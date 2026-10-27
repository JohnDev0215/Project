package u2;

import android.os.Handler;
import android.util.Pair;
import d5.C1077F;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.HashMap;
import java.util.IdentityHashMap;

/* JADX INFO: renamed from: u2.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2512o extends AbstractC2507j {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final d5.d0 f22883k;
    public final IdentityHashMap l = new IdentityHashMap();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Handler f22884m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f22885n;
    public e2.F o;

    public C2512o(e2.F f10, d5.d0 d0Var) {
        this.o = f10;
        this.f22883k = d0Var;
    }

    public static Pair A(int i7, Object obj) {
        return Pair.create(Integer.valueOf(i7), obj);
    }

    public static int y(Object obj) {
        return ((Integer) ((Pair) obj).first).intValue();
    }

    public static Object z(Object obj) {
        return ((Pair) obj).second;
    }

    public final void B() {
        int i7 = 0;
        while (true) {
            d5.d0 d0Var = this.f22883k;
            if (i7 >= d0Var.size()) {
                return;
            }
            C2511n c2511n = (C2511n) d0Var.get(i7);
            if (c2511n.f22879e == 0) {
                C2506i c2506i = (C2506i) this.f22842h.get(Integer.valueOf(c2511n.f22878b));
                c2506i.getClass();
                c2506i.f22838a.c(c2506i.f22839b);
            }
            i7++;
        }
    }

    public final C2510m C() {
        int i7;
        d5.d0 d0Var;
        int i10;
        e2.e0 e0Var;
        long j3;
        e2.e0 e0Var2 = new e2.e0();
        e2.c0 c0Var = new e2.c0();
        C1077F c1077fU = d5.I.u();
        C1077F c1077fU2 = d5.I.u();
        C1077F c1077fU3 = d5.I.u();
        d5.d0 d0Var2 = this.f22883k;
        int size = d0Var2.size();
        boolean z6 = true;
        boolean z10 = true;
        boolean z11 = true;
        int i11 = 0;
        Object obj = null;
        int iH = 0;
        boolean z12 = false;
        boolean z13 = false;
        long j10 = 0;
        long j11 = 0;
        long j12 = 0;
        while (i11 < size) {
            C2511n c2511n = (C2511n) d0Var2.get(i11);
            C2518v c2518v = c2511n.f22877a.o;
            AbstractC1346c.b("Can't concatenate empty child Timeline.", c2518v.p() ^ z6);
            c1077fU.c(c2518v);
            c1077fU2.c(Integer.valueOf(iH));
            iH = c2518v.h() + iH;
            int i12 = 0;
            while (i12 < c2518v.o()) {
                c2518v.n(i12, e0Var2);
                if (!z12) {
                    obj = e0Var2.d;
                    z12 = true;
                }
                if (z10 && AbstractC1343A.a(obj, e0Var2.d)) {
                    i7 = i11;
                    z10 = true;
                } else {
                    i7 = i11;
                    z10 = false;
                }
                long j13 = e0Var2.f14094m;
                if (j13 == -9223372036854775807L) {
                    j13 = c2511n.c;
                    if (j13 == -9223372036854775807L) {
                        return null;
                    }
                }
                j10 += j13;
                int i13 = c2511n.f22878b;
                if (i13 == 0 && i12 == 0) {
                    d0Var = d0Var2;
                    j11 = e0Var2.l;
                    j12 = -e0Var2.f14096p;
                } else {
                    d0Var = d0Var2;
                }
                z11 &= e0Var2.f14090h || e0Var2.f14093k;
                z13 |= e0Var2.f14091i;
                int i14 = e0Var2.f14095n;
                while (i14 <= e0Var2.o) {
                    c1077fU3.c(Long.valueOf(j12));
                    c2518v.f(i14, c0Var, true);
                    int i15 = size;
                    long j14 = c0Var.d;
                    if (j14 == -9223372036854775807L) {
                        AbstractC1346c.b("Can't apply placeholder duration to multiple periods with unknown duration in a single window.", e0Var2.f14095n == e0Var2.o);
                        j14 = e0Var2.f14096p + j13;
                    }
                    C2518v c2518v2 = c2518v;
                    if (i14 != e0Var2.f14095n || ((i13 == 0 && i12 == 0) || j14 == -9223372036854775807L)) {
                        i10 = i13;
                        e0Var = e0Var2;
                        j3 = 0;
                    } else {
                        i10 = i13;
                        j3 = -e0Var2.f14096p;
                        j14 += j3;
                        e0Var = e0Var2;
                    }
                    Object obj2 = c0Var.f14060b;
                    AbstractC1346c.g(obj2);
                    e2.c0 c0Var2 = c0Var;
                    int i16 = c2511n.f22879e;
                    long j15 = j13;
                    HashMap map = c2511n.d;
                    AbstractC1346c.b("Can't handle windows with changing offset in first period.", i16 == 0 || !map.containsKey(obj2) || ((Long) map.get(obj2)).equals(Long.valueOf(j3)));
                    map.put(obj2, Long.valueOf(j3));
                    j12 += j14;
                    i14++;
                    size = i15;
                    c2518v = c2518v2;
                    i13 = i10;
                    e0Var2 = e0Var;
                    c0Var = c0Var2;
                    j13 = j15;
                }
                i12++;
                i11 = i7;
                d0Var2 = d0Var;
            }
            i11++;
            z6 = true;
        }
        return new C2510m(h(), c1077fU.h(), c1077fU2.h(), c1077fU3.h(), z11, z13, j10, j11, z10 ? obj : null);
    }

    @Override // u2.AbstractC2498a
    public final InterfaceC2490A b(C2492C c2492c, x2.e eVar, long j3) {
        long jLongValue;
        Object obj = c2492c.f22663a;
        int iIntValue = ((Integer) ((Pair) obj).first).intValue();
        d5.d0 d0Var = this.f22883k;
        C2511n c2511n = (C2511n) d0Var.get(iIntValue);
        C2492C c2492cA = c2492c.a(((Pair) obj).second);
        long size = c2492c.d * ((long) d0Var.size());
        int i7 = c2511n.f22878b;
        C2492C c2492cB = c2492cA.b(size + ((long) i7));
        C2506i c2506i = (C2506i) this.f22842h.get(Integer.valueOf(i7));
        c2506i.getClass();
        c2506i.f22838a.e(c2506i.f22839b);
        c2511n.f22879e++;
        if (c2492c.c()) {
            jLongValue = 0;
        } else {
            Long l = (Long) c2511n.d.get(c2492cB.f22663a);
            AbstractC1346c.g(l);
            jLongValue = l.longValue();
        }
        m0 m0Var = new m0(c2511n.f22877a.b(c2492cB, eVar, j3 - jLongValue), jLongValue);
        this.l.put(m0Var, c2511n);
        B();
        return m0Var;
    }

    @Override // u2.AbstractC2507j, u2.AbstractC2498a
    public final void f() {
    }

    @Override // u2.AbstractC2498a
    public final e2.f0 g() {
        return C();
    }

    @Override // u2.AbstractC2498a
    public final synchronized e2.F h() {
        return this.o;
    }

    @Override // u2.AbstractC2507j, u2.AbstractC2498a
    public final void l(k2.E e10) {
        super.l(e10);
        this.f22884m = new Handler(new h2.k(this, 3));
        int i7 = 0;
        while (true) {
            d5.d0 d0Var = this.f22883k;
            if (i7 >= d0Var.size()) {
                break;
            }
            x(Integer.valueOf(i7), ((C2511n) d0Var.get(i7)).f22877a);
            i7++;
        }
        if (this.f22885n) {
            return;
        }
        Handler handler = this.f22884m;
        handler.getClass();
        handler.obtainMessage(1).sendToTarget();
        this.f22885n = true;
    }

    @Override // u2.AbstractC2498a
    public final void n(InterfaceC2490A interfaceC2490A) {
        IdentityHashMap identityHashMap = this.l;
        C2511n c2511n = (C2511n) identityHashMap.remove(interfaceC2490A);
        c2511n.getClass();
        c2511n.f22877a.n(((m0) interfaceC2490A).l);
        c2511n.f22879e--;
        if (identityHashMap.isEmpty()) {
            return;
        }
        B();
    }

    @Override // u2.AbstractC2507j, u2.AbstractC2498a
    public final void p() {
        super.p();
        Handler handler = this.f22884m;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.f22884m = null;
        }
        this.f22885n = false;
    }

    @Override // u2.AbstractC2498a
    public final synchronized void s(e2.F f10) {
        this.o = f10;
    }

    @Override // u2.AbstractC2507j
    public final C2492C t(Object obj, C2492C c2492c) {
        Integer num = (Integer) obj;
        long j3 = c2492c.d;
        d5.d0 d0Var = this.f22883k;
        if (num.intValue() != ((int) (j3 % ((long) d0Var.o)))) {
            return null;
        }
        return c2492c.a(Pair.create(num, c2492c.f22663a)).b(c2492c.d / ((long) d0Var.o));
    }

    @Override // u2.AbstractC2507j
    public final long u(Object obj, long j3, C2492C c2492c) {
        Long l;
        return (j3 == -9223372036854775807L || c2492c == null || c2492c.c() || (l = (Long) ((C2511n) this.f22883k.get(((Integer) obj).intValue())).d.get(c2492c.f22663a)) == null) ? j3 : j3 + AbstractC1343A.T(l.longValue());
    }

    @Override // u2.AbstractC2507j
    public final /* bridge */ /* synthetic */ int v(int i7, Object obj) {
        return 0;
    }

    @Override // u2.AbstractC2507j
    public final void w(Object obj, AbstractC2498a abstractC2498a, e2.f0 f0Var) {
        if (this.f22885n) {
            return;
        }
        Handler handler = this.f22884m;
        handler.getClass();
        handler.obtainMessage(1).sendToTarget();
        this.f22885n = true;
    }
}
