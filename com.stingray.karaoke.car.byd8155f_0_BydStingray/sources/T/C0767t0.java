package T;

import N1.C0420a;
import S8.C0712i;
import S8.C0722t;
import S8.InterfaceC0710g;
import V8.AbstractC0828q;
import e0.AbstractC1167g;
import e0.AbstractC1174n;
import e0.C1163c;
import e0.C1168h;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import q7.InterfaceC2194h;
import u.C2453A;

/* JADX INFO: renamed from: T.t0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0767t0 extends AbstractC0764s {

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final V8.f0 f8349v = AbstractC0828q.c(Z.b.o);

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final AtomicReference f8350w = new AtomicReference(Boolean.FALSE);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0743h f8351a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f8352b;
    public S8.W c;
    public Throwable d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayList f8353e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f8354f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public C2453A f8355g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final V.d f8356h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayList f8357i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final ArrayList f8358j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final LinkedHashMap f8359k;
    public final LinkedHashMap l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ArrayList f8360m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public LinkedHashSet f8361n;
    public C0712i o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public C0420a f8362p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f8363q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final V8.f0 f8364r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final S8.Y f8365s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final InterfaceC2194h f8366t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final S f8367u;

    public C0767t0(InterfaceC2194h interfaceC2194h) {
        C0743h c0743h = new C0743h(new K7.K(this, 18));
        this.f8351a = c0743h;
        this.f8352b = new Object();
        this.f8353e = new ArrayList();
        this.f8355g = new C2453A();
        this.f8356h = new V.d(new C0768u[16]);
        this.f8357i = new ArrayList();
        this.f8358j = new ArrayList();
        this.f8359k = new LinkedHashMap();
        this.l = new LinkedHashMap();
        this.f8364r = AbstractC0828q.c(EnumC0756n0.f8277n);
        S8.Y y10 = new S8.Y((S8.W) interfaceC2194h.F(C0722t.f8137m));
        y10.z(new S0.p(this, 2));
        this.f8365s = y10;
        this.f8366t = interfaceC2194h.v(c0743h).v(y10);
        this.f8367u = new S(7);
    }

    public static /* synthetic */ void A(C0767t0 c0767t0, Exception exc, boolean z6, int i7) throws Exception {
        if ((i7 & 4) != 0) {
            z6 = false;
        }
        c0767t0.z(exc, null, z6);
    }

    public static final C0768u p(C0767t0 c0767t0, C0768u c0768u, C2453A c2453a) {
        C1163c c1163cB;
        if (c0768u.B.f8293E || c0768u.f8369C) {
            return null;
        }
        LinkedHashSet linkedHashSet = c0767t0.f8361n;
        if (linkedHashSet != null && linkedHashSet.contains(c0768u)) {
            return null;
        }
        S0.p pVar = new S0.p(c0768u, 3);
        C0741g c0741g = new C0741g(c0768u, 3, c2453a);
        AbstractC1167g abstractC1167gK = AbstractC1174n.k();
        C1163c c1163c = abstractC1167gK instanceof C1163c ? (C1163c) abstractC1167gK : null;
        if (c1163c == null || (c1163cB = c1163c.B(pVar, c0741g)) == null) {
            throw new IllegalStateException("Cannot create a mutable snapshot of an read-only snapshot");
        }
        try {
            AbstractC1167g abstractC1167gJ = c1163cB.j();
            if (c2453a != null) {
                try {
                    if (c2453a.h()) {
                        B8.A a10 = new B8.A(c2453a, 24, c0768u);
                        C0761q c0761q = c0768u.B;
                        if (c0761q.f8293E) {
                            C0735d.y("Preparing a composition while composing is not supported");
                            throw null;
                        }
                        c0761q.f8293E = true;
                        try {
                            a10.a();
                            c0761q.f8293E = false;
                        } catch (Throwable th) {
                            c0761q.f8293E = false;
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    AbstractC1167g.p(abstractC1167gJ);
                    throw th2;
                }
            }
            boolean zU = c0768u.u();
            AbstractC1167g.p(abstractC1167gJ);
            if (!zU) {
                c0768u = null;
            }
            return c0768u;
        } finally {
            r(c1163cB);
        }
    }

    public static final boolean q(C0767t0 c0767t0) {
        boolean z6;
        List listW;
        synchronized (c0767t0.f8352b) {
            z6 = true;
            if (!c0767t0.f8355g.g()) {
                V.f fVar = new V.f(c0767t0.f8355g);
                c0767t0.f8355g = new C2453A();
                synchronized (c0767t0.f8352b) {
                    listW = c0767t0.w();
                }
                try {
                    int size = listW.size();
                    for (int i7 = 0; i7 < size; i7++) {
                        ((C0768u) listW.get(i7)).v(fVar);
                        if (((EnumC0756n0) c0767t0.f8364r.getValue()).compareTo(EnumC0756n0.f8276m) <= 0) {
                            break;
                        }
                    }
                    synchronized (c0767t0.f8352b) {
                        c0767t0.f8355g = new C2453A();
                    }
                    synchronized (c0767t0.f8352b) {
                        if (c0767t0.t() != null) {
                            throw new IllegalStateException("called outside of runRecomposeAndApplyChanges");
                        }
                        if (!c0767t0.f8356h.m() && !c0767t0.u()) {
                            z6 = false;
                        }
                    }
                } catch (Throwable th) {
                    synchronized (c0767t0.f8352b) {
                        C2453A c2453a = c0767t0.f8355g;
                        c2453a.getClass();
                        for (Object obj : fVar) {
                            c2453a.f22580b[c2453a.d(obj)] = obj;
                        }
                        throw th;
                    }
                }
            } else if (!c0767t0.f8356h.m() && !c0767t0.u()) {
                z6 = false;
            }
        }
        return z6;
    }

    public static void r(C1163c c1163c) {
        try {
            if (c1163c.v() instanceof C1168h) {
                throw new IllegalStateException("Unsupported concurrent change during composition. A state object was modified by composition as well as being modified outside composition.");
            }
        } finally {
            c1163c.c();
        }
    }

    public static final void x(ArrayList arrayList, C0767t0 c0767t0, C0768u c0768u) {
        arrayList.clear();
        synchronized (c0767t0.f8352b) {
            Iterator it = c0767t0.f8358j.iterator();
            if (it.hasNext()) {
                ((V) it.next()).getClass();
                throw null;
            }
        }
    }

    public final void B(C0768u c0768u) {
        ArrayList arrayList = this.f8360m;
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.f8360m = arrayList;
        }
        if (!arrayList.contains(c0768u)) {
            arrayList.add(c0768u);
        }
        this.f8353e.remove(c0768u);
        this.f8354f = null;
    }

    @Override // T.AbstractC0764s
    public final void a(C0768u c0768u, b0.b bVar) throws Exception {
        C1163c c1163cB;
        boolean z6 = c0768u.B.f8293E;
        try {
            S0.p pVar = new S0.p(c0768u, 3);
            C0741g c0741g = new C0741g(c0768u, 3, null);
            AbstractC1167g abstractC1167gK = AbstractC1174n.k();
            C1163c c1163c = abstractC1167gK instanceof C1163c ? (C1163c) abstractC1167gK : null;
            if (c1163c == null || (c1163cB = c1163c.B(pVar, c0741g)) == null) {
                throw new IllegalStateException("Cannot create a mutable snapshot of an read-only snapshot");
            }
            try {
                AbstractC1167g abstractC1167gJ = c1163cB.j();
                try {
                    c0768u.i(bVar);
                    if (!z6) {
                        AbstractC1174n.k().m();
                    }
                    synchronized (this.f8352b) {
                        if (((EnumC0756n0) this.f8364r.getValue()).compareTo(EnumC0756n0.f8276m) > 0 && !w().contains(c0768u)) {
                            this.f8353e.add(c0768u);
                            this.f8354f = null;
                        }
                    }
                    try {
                        synchronized (this.f8352b) {
                            ArrayList arrayList = this.f8358j;
                            if (arrayList.size() > 0) {
                                ((V) arrayList.get(0)).getClass();
                                throw null;
                            }
                        }
                        try {
                            c0768u.d();
                            c0768u.f();
                            if (z6) {
                                return;
                            }
                            AbstractC1174n.k().m();
                        } catch (Exception e10) {
                            A(this, e10, false, 6);
                        }
                    } catch (Exception e11) {
                        z(e11, c0768u, true);
                    }
                } finally {
                    AbstractC1167g.p(abstractC1167gJ);
                }
            } finally {
                r(c1163cB);
            }
        } catch (Exception e12) {
            z(e12, c0768u, true);
        }
    }

    @Override // T.AbstractC0764s
    public final boolean c() {
        return ((Boolean) f8350w.get()).booleanValue();
    }

    @Override // T.AbstractC0764s
    public final boolean d() {
        return false;
    }

    @Override // T.AbstractC0764s
    public final boolean e() {
        return false;
    }

    @Override // T.AbstractC0764s
    public final int g() {
        return 1000;
    }

    @Override // T.AbstractC0764s
    public final InterfaceC2194h h() {
        return this.f8366t;
    }

    @Override // T.AbstractC0764s
    public final void i(C0768u c0768u) {
        InterfaceC0710g interfaceC0710gT;
        synchronized (this.f8352b) {
            if (this.f8356h.i(c0768u)) {
                interfaceC0710gT = null;
            } else {
                this.f8356h.b(c0768u);
                interfaceC0710gT = t();
            }
        }
        if (interfaceC0710gT != null) {
            ((C0712i) interfaceC0710gT).resumeWith(m7.q.f19267a);
        }
    }

    @Override // T.AbstractC0764s
    public final void j(Set set) {
    }

    @Override // T.AbstractC0764s
    public final void l(C0768u c0768u) {
        synchronized (this.f8352b) {
            try {
                LinkedHashSet linkedHashSet = this.f8361n;
                if (linkedHashSet == null) {
                    linkedHashSet = new LinkedHashSet();
                    this.f8361n = linkedHashSet;
                }
                linkedHashSet.add(c0768u);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // T.AbstractC0764s
    public final void o(C0768u c0768u) {
        synchronized (this.f8352b) {
            this.f8353e.remove(c0768u);
            this.f8354f = null;
            this.f8356h.n(c0768u);
            this.f8357i.remove(c0768u);
        }
    }

    public final void s() throws IllegalAccessException, InvocationTargetException {
        synchronized (this.f8352b) {
            if (((EnumC0756n0) this.f8364r.getValue()).compareTo(EnumC0756n0.f8278p) >= 0) {
                V8.f0 f0Var = this.f8364r;
                EnumC0756n0 enumC0756n0 = EnumC0756n0.f8276m;
                f0Var.getClass();
                f0Var.m(null, enumC0756n0);
            }
        }
        this.f8365s.g(null);
    }

    public final InterfaceC0710g t() throws IllegalAccessException, InvocationTargetException {
        V8.f0 f0Var = this.f8364r;
        int iCompareTo = ((EnumC0756n0) f0Var.getValue()).compareTo(EnumC0756n0.f8276m);
        ArrayList arrayList = this.f8358j;
        ArrayList arrayList2 = this.f8357i;
        V.d dVar = this.f8356h;
        if (iCompareTo <= 0) {
            this.f8353e.clear();
            this.f8354f = n7.u.l;
            this.f8355g = new C2453A();
            dVar.g();
            arrayList2.clear();
            arrayList.clear();
            this.f8360m = null;
            C0712i c0712i = this.o;
            if (c0712i != null) {
                c0712i.l(null);
            }
            this.o = null;
            this.f8362p = null;
            return null;
        }
        C0420a c0420a = this.f8362p;
        EnumC0756n0 enumC0756n0 = EnumC0756n0.f8279q;
        EnumC0756n0 enumC0756n02 = EnumC0756n0.f8277n;
        if (c0420a == null) {
            if (this.c == null) {
                this.f8355g = new C2453A();
                dVar.g();
                if (u()) {
                    enumC0756n02 = EnumC0756n0.o;
                }
            } else {
                enumC0756n02 = (dVar.m() || this.f8355g.h() || !arrayList2.isEmpty() || !arrayList.isEmpty() || u()) ? enumC0756n0 : EnumC0756n0.f8278p;
            }
        }
        f0Var.getClass();
        f0Var.m(null, enumC0756n02);
        if (enumC0756n02 != enumC0756n0) {
            return null;
        }
        C0712i c0712i2 = this.o;
        this.o = null;
        return c0712i2;
    }

    public final boolean u() {
        return (this.f8363q || this.f8351a.f8259q.get() == 0) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean v() {
        /*
            r2 = this;
            java.lang.Object r0 = r2.f8352b
            monitor-enter(r0)
            u.A r1 = r2.f8355g     // Catch: java.lang.Throwable -> L1c
            boolean r1 = r1.h()     // Catch: java.lang.Throwable -> L1c
            if (r1 != 0) goto L1e
            V.d r1 = r2.f8356h     // Catch: java.lang.Throwable -> L1c
            boolean r1 = r1.m()     // Catch: java.lang.Throwable -> L1c
            if (r1 != 0) goto L1e
            boolean r2 = r2.u()     // Catch: java.lang.Throwable -> L1c
            if (r2 == 0) goto L1a
            goto L1e
        L1a:
            r2 = 0
            goto L1f
        L1c:
            r2 = move-exception
            goto L21
        L1e:
            r2 = 1
        L1f:
            monitor-exit(r0)
            return r2
        L21:
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: T.C0767t0.v():boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    public final List w() {
        Object obj = this.f8354f;
        ?? r02 = obj;
        if (obj == null) {
            ArrayList arrayList = this.f8353e;
            Object arrayList2 = arrayList.isEmpty() ? n7.u.l : new ArrayList(arrayList);
            this.f8354f = arrayList2;
            r02 = arrayList2;
        }
        return r02;
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00df, code lost:
    
        r3 = r10.size();
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00e4, code lost:
    
        if (r4 >= r3) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00ee, code lost:
    
        if (((m7.i) r10.get(r4)).f19259m == null) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00f0, code lost:
    
        r4 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00f3, code lost:
    
        r3 = new java.util.ArrayList(r10.size());
        r4 = r10.size();
        r8 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0101, code lost:
    
        if (r8 >= r4) goto L98;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0103, code lost:
    
        r11 = (m7.i) r10.get(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x010b, code lost:
    
        if (r11.f19259m != null) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x010d, code lost:
    
        r11 = (T.V) r11.l;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0114, code lost:
    
        r8 = r8 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0117, code lost:
    
        r4 = r18.f8352b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0119, code lost:
    
        monitor-enter(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x011a, code lost:
    
        n7.AbstractC2004s.L(r18.f8358j, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x011f, code lost:
    
        monitor-exit(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0120, code lost:
    
        r3 = new java.util.ArrayList(r10.size());
        r4 = r10.size();
        r8 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x012e, code lost:
    
        if (r8 >= r4) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0130, code lost:
    
        r11 = r10.get(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0139, code lost:
    
        if (((m7.i) r11).f19259m == null) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x013b, code lost:
    
        r3.add(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x013e, code lost:
    
        r8 = r8 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0141, code lost:
    
        r10 = r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List y(java.util.List r19, u.C2453A r20) {
        /*
            Method dump skipped, instruction units count: 367
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: T.C0767t0.y(java.util.List, u.A):java.util.List");
    }

    public final void z(Exception exc, C0768u c0768u, boolean z6) throws Exception {
        if (!((Boolean) f8350w.get()).booleanValue() || (exc instanceof C0751l)) {
            synchronized (this.f8352b) {
                C0420a c0420a = this.f8362p;
                if (c0420a != null) {
                    throw ((Exception) c0420a.l);
                }
                this.f8362p = new C0420a(exc);
            }
            throw exc;
        }
        synchronized (this.f8352b) {
            try {
                int i7 = AbstractC0731b.f8233b;
                h6.n.i("ComposeInternal", "Error was captured in composition while live edit was enabled.", exc);
                this.f8357i.clear();
                this.f8356h.g();
                this.f8355g = new C2453A();
                this.f8358j.clear();
                this.f8359k.clear();
                this.l.clear();
                this.f8362p = new C0420a(exc);
                if (c0768u != null) {
                    B(c0768u);
                }
                t();
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
