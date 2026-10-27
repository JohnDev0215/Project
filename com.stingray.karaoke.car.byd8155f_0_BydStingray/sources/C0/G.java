package C0;

import F0.w1;
import T.AbstractC0764s;
import T.C0735d;
import T.C0761q;
import T.C0768u;
import T.InterfaceC0749k;
import android.view.ViewGroup;
import e0.AbstractC1167g;
import e0.AbstractC1174n;
import e0.AbstractC1179s;
import e0.C1162b;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import u.C2453A;
import u.C2484x;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class G implements InterfaceC0749k {
    public final E0.F l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public AbstractC0764s f849m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public i0 f850n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f851p;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f860y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f861z;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final HashMap f852q = new HashMap();

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final HashMap f853r = new HashMap();

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final B f854s = new B(this);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final C0118z f855t = new C0118z(this);

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final HashMap f856u = new HashMap();

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final h0 f857v = new h0();

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final LinkedHashMap f858w = new LinkedHashMap();

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final V.d f859x = new V.d(new Object[16]);

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final String f848A = "Asking for intrinsic measurements of SubcomposeLayout layouts is not supported. This includes components that are built on top of SubcomposeLayout, such as lazy lists, BoxWithConstraints, TabRow, etc. To mitigate this:\n- if intrinsic measurements are used to achieve 'match parent' sizing, consider replacing the parent of the component with a custom layout which controls the order in which children are measured, making intrinsic measurement not needed\n- adding a size modifier to the component, in order to fast return the queried intrinsic measurement.";

    public G(E0.F f10, i0 i0Var) {
        this.l = f10;
        this.f850n = i0Var;
    }

    public static C0768u i(C0768u c0768u, E0.F f10, boolean z6, AbstractC0764s abstractC0764s, b0.b bVar) {
        if (c0768u == null || c0768u.f8369C) {
            ViewGroup.LayoutParams layoutParams = w1.f2293a;
            c0768u = new C0768u(abstractC0764s, new l3.P(f10));
        }
        if (z6) {
            C0761q c0761q = c0768u.B;
            c0761q.f8325y = 100;
            c0761q.f8324x = true;
            c0768u.j(bVar);
            if (c0761q.f8293E || c0761q.f8325y != 100) {
                C0735d.X("Cannot disable reuse from root if it was caused by other groups");
                throw null;
            }
            c0761q.f8325y = -1;
            c0761q.f8324x = false;
        } else {
            c0768u.j(bVar);
        }
        return c0768u;
    }

    public final void a(int i7) {
        boolean z6;
        boolean z10 = false;
        this.f860y = 0;
        int i10 = (((V.a) this.l.r()).l.f9279n - this.f861z) - 1;
        if (i7 <= i10) {
            this.f857v.clear();
            if (i7 <= i10) {
                int i11 = i7;
                while (true) {
                    Object obj = this.f852q.get((E0.F) ((V.a) this.l.r()).get(i11));
                    A7.m.c(obj);
                    this.f857v.l.add(((C0117y) obj).f921a);
                    if (i11 == i10) {
                        break;
                    } else {
                        i11++;
                    }
                }
            }
            this.f850n.i(this.f857v);
            AbstractC1167g abstractC1167gC = AbstractC1179s.c();
            InterfaceC2782k interfaceC2782kF = abstractC1167gC != null ? abstractC1167gC.f() : null;
            AbstractC1167g abstractC1167gD = AbstractC1179s.d(abstractC1167gC);
            z6 = false;
            while (i10 >= i7) {
                try {
                    E0.F f10 = (E0.F) ((V.a) this.l.r()).get(i10);
                    Object obj2 = this.f852q.get(f10);
                    A7.m.c(obj2);
                    C0117y c0117y = (C0117y) obj2;
                    Object obj3 = c0117y.f921a;
                    if (this.f857v.l.contains(obj3)) {
                        this.f860y++;
                        if (((Boolean) c0117y.f924f.getValue()).booleanValue()) {
                            f10.z().f1548v = 3;
                            E0.K kY = f10.y();
                            if (kY != null) {
                                kY.f1518t = 3;
                            }
                            c0117y.f924f.setValue(Boolean.FALSE);
                            z6 = true;
                        }
                    } else {
                        E0.F f11 = this.l;
                        f11.f1490w = true;
                        this.f852q.remove(f10);
                        C0768u c0768u = c0117y.c;
                        if (c0768u != null) {
                            c0768u.l();
                        }
                        this.l.n0(i10, 1);
                        f11.f1490w = false;
                    }
                    this.f853r.remove(obj3);
                    i10--;
                } catch (Throwable th) {
                    AbstractC1179s.f(abstractC1167gC, abstractC1167gD, interfaceC2782kF);
                    throw th;
                }
            }
            AbstractC1179s.f(abstractC1167gC, abstractC1167gD, interfaceC2782kF);
        } else {
            z6 = false;
        }
        if (z6) {
            synchronized (AbstractC1174n.f13721b) {
                C2453A c2453a = ((C1162b) AbstractC1174n.f13726i.get()).f13696h;
                if (c2453a != null) {
                    if (c2453a.h()) {
                        z10 = true;
                    }
                }
            }
            if (z10) {
                AbstractC1174n.a();
            }
        }
        b();
    }

    public final void b() {
        int i7 = ((V.a) this.l.r()).l.f9279n;
        HashMap map = this.f852q;
        if (map.size() != i7) {
            throw new IllegalArgumentException(("Inconsistency between the count of nodes tracked by the state (" + map.size() + ") and the children count on the SubcomposeLayout (" + i7 + "). Are you trying to use the state of the disposed SubcomposeLayout?").toString());
        }
        if ((i7 - this.f860y) - this.f861z < 0) {
            StringBuilder sbJ = M5.b.j("Incorrect state. Total children ", i7, ". Reusable children ");
            sbJ.append(this.f860y);
            sbJ.append(". Precomposed children ");
            sbJ.append(this.f861z);
            throw new IllegalArgumentException(sbJ.toString().toString());
        }
        HashMap map2 = this.f856u;
        if (map2.size() == this.f861z) {
            return;
        }
        throw new IllegalArgumentException(("Incorrect state. Precomposed children " + this.f861z + ". Map size " + map2.size()).toString());
    }

    public final void c(boolean z6) {
        this.f861z = 0;
        this.f856u.clear();
        E0.F f10 = this.l;
        int i7 = ((V.a) f10.r()).l.f9279n;
        if (this.f860y != i7) {
            this.f860y = i7;
            AbstractC1167g abstractC1167gC = AbstractC1179s.c();
            InterfaceC2782k interfaceC2782kF = abstractC1167gC != null ? abstractC1167gC.f() : null;
            AbstractC1167g abstractC1167gD = AbstractC1179s.d(abstractC1167gC);
            for (int i10 = 0; i10 < i7; i10++) {
                try {
                    E0.F f11 = (E0.F) ((V.a) f10.r()).get(i10);
                    C0117y c0117y = (C0117y) this.f852q.get(f11);
                    if (c0117y != null && ((Boolean) c0117y.f924f.getValue()).booleanValue()) {
                        f11.z().f1548v = 3;
                        E0.K kY = f11.y();
                        if (kY != null) {
                            kY.f1518t = 3;
                        }
                        if (z6) {
                            C0768u c0768u = c0117y.c;
                            if (c0768u != null) {
                                c0768u.k();
                            }
                            c0117y.f924f = C0735d.O(Boolean.FALSE, T.S.f8224q);
                        } else {
                            c0117y.f924f.setValue(Boolean.FALSE);
                        }
                        c0117y.f921a = c0.f891a;
                    }
                } catch (Throwable th) {
                    AbstractC1179s.f(abstractC1167gC, abstractC1167gD, interfaceC2782kF);
                    throw th;
                }
            }
            AbstractC1179s.f(abstractC1167gC, abstractC1167gD, interfaceC2782kF);
            this.f853r.clear();
        }
        b();
    }

    @Override // T.InterfaceC0749k
    public final void d() {
        c(false);
    }

    @Override // T.InterfaceC0749k
    public final void e() {
        E0.F f10 = this.l;
        f10.f1490w = true;
        HashMap map = this.f852q;
        Iterator it = map.values().iterator();
        while (it.hasNext()) {
            C0768u c0768u = ((C0117y) it.next()).c;
            if (c0768u != null) {
                c0768u.l();
            }
        }
        f10.m0();
        f10.f1490w = false;
        map.clear();
        this.f853r.clear();
        this.f861z = 0;
        this.f860y = 0;
        this.f856u.clear();
        b();
    }

    @Override // T.InterfaceC0749k
    public final void f() {
        c(true);
    }

    public final d0 g(Object obj, InterfaceC2785n interfaceC2785n) {
        E0.F f10 = this.l;
        if (!f10.V()) {
            return new E();
        }
        b();
        if (!this.f853r.containsKey(obj)) {
            this.f858w.remove(obj);
            HashMap map = this.f856u;
            Object objJ = map.get(obj);
            if (objJ == null) {
                objJ = j(obj);
                if (objJ != null) {
                    int iK = ((V.a) f10.r()).l.k(objJ);
                    int i7 = ((V.a) f10.r()).l.f9279n;
                    f10.f1490w = true;
                    f10.g0(iK, i7, 1);
                    f10.f1490w = false;
                    this.f861z++;
                } else {
                    int i10 = ((V.a) f10.r()).l.f9279n;
                    E0.F f11 = new E0.F(2, 0, true);
                    f10.f1490w = true;
                    f10.N(i10, f11);
                    f10.f1490w = false;
                    this.f861z++;
                    objJ = f11;
                }
                map.put(obj, objJ);
            }
            h((E0.F) objJ, obj, interfaceC2785n);
        }
        return new F(this, obj);
    }

    public final void h(E0.F f10, Object obj, InterfaceC2785n interfaceC2785n) {
        boolean z6;
        HashMap map = this.f852q;
        Object obj2 = map.get(f10);
        Object obj3 = obj2;
        if (obj2 == null) {
            b0.b bVar = AbstractC0101h.f901a;
            C0117y c0117y = new C0117y();
            c0117y.f921a = obj;
            c0117y.f922b = bVar;
            c0117y.c = null;
            c0117y.f924f = C0735d.O(Boolean.TRUE, T.S.f8224q);
            map.put(f10, c0117y);
            obj3 = c0117y;
        }
        C0117y c0117y2 = (C0117y) obj3;
        C0768u c0768u = c0117y2.c;
        if (c0768u != null) {
            synchronized (c0768u.o) {
                z6 = ((C2484x) c0768u.f8381y.l).f22645e > 0;
            }
        } else {
            z6 = true;
        }
        if (c0117y2.f922b != interfaceC2785n || z6 || c0117y2.d) {
            c0117y2.f922b = interfaceC2785n;
            AbstractC1167g abstractC1167gC = AbstractC1179s.c();
            InterfaceC2782k interfaceC2782kF = abstractC1167gC != null ? abstractC1167gC.f() : null;
            AbstractC1167g abstractC1167gD = AbstractC1179s.d(abstractC1167gC);
            try {
                E0.F f11 = this.l;
                f11.f1490w = true;
                InterfaceC2785n interfaceC2785n2 = c0117y2.f922b;
                C0768u c0768u2 = c0117y2.c;
                AbstractC0764s abstractC0764s = this.f849m;
                if (abstractC0764s == null) {
                    throw new IllegalStateException("parent composition reference not set");
                }
                c0117y2.c = i(c0768u2, f10, c0117y2.f923e, abstractC0764s, new b0.b(-1750409193, new A.L(c0117y2, 2, interfaceC2785n2), true));
                c0117y2.f923e = false;
                f11.f1490w = false;
                AbstractC1179s.f(abstractC1167gC, abstractC1167gD, interfaceC2782kF);
                c0117y2.d = false;
            } catch (Throwable th) {
                AbstractC1179s.f(abstractC1167gC, abstractC1167gD, interfaceC2782kF);
                throw th;
            }
        }
    }

    public final E0.F j(Object obj) {
        HashMap map;
        int i7;
        if (this.f860y == 0) {
            return null;
        }
        E0.F f10 = this.l;
        int i10 = ((V.a) f10.r()).l.f9279n - this.f861z;
        int i11 = i10 - this.f860y;
        int i12 = i10 - 1;
        int i13 = i12;
        while (true) {
            map = this.f852q;
            if (i13 < i11) {
                i7 = -1;
                break;
            }
            Object obj2 = map.get((E0.F) ((V.a) f10.r()).get(i13));
            A7.m.c(obj2);
            if (A7.m.a(((C0117y) obj2).f921a, obj)) {
                i7 = i13;
                break;
            }
            i13--;
        }
        if (i7 == -1) {
            while (i12 >= i11) {
                Object obj3 = map.get((E0.F) ((V.a) f10.r()).get(i12));
                A7.m.c(obj3);
                C0117y c0117y = (C0117y) obj3;
                Object obj4 = c0117y.f921a;
                if (obj4 == c0.f891a || this.f850n.e(obj, obj4)) {
                    c0117y.f921a = obj;
                    i13 = i12;
                    i7 = i13;
                    break;
                }
                i12--;
            }
            i13 = i12;
        }
        if (i7 == -1) {
            return null;
        }
        if (i13 != i11) {
            f10.f1490w = true;
            f10.g0(i13, i11, 1);
            f10.f1490w = false;
        }
        this.f860y--;
        E0.F f11 = (E0.F) ((V.a) f10.r()).get(i11);
        Object obj5 = map.get(f11);
        A7.m.c(obj5);
        C0117y c0117y2 = (C0117y) obj5;
        c0117y2.f924f = C0735d.O(Boolean.TRUE, T.S.f8224q);
        c0117y2.f923e = true;
        c0117y2.d = true;
        return f11;
    }
}
