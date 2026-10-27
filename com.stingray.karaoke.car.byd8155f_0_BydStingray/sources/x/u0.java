package x;

import T.C0730a0;
import T.C0734c0;
import T.C0735d;
import T.C0738e0;
import T.Q0;
import e0.C1178r;

/* JADX INFO: loaded from: classes.dex */
public final class u0 implements Q0 {
    public final D0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C0738e0 f23632m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C0738e0 f23633n;
    public final C0738e0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public U f23634p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public p0 f23635q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final C0738e0 f23636r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final C0730a0 f23637s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f23638t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final C0738e0 f23639u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public AbstractC2652s f23640v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final C0734c0 f23641w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f23642x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final C2640i0 f23643y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final /* synthetic */ x0 f23644z;

    /* JADX WARN: Type inference failed for: r11v5, types: [java.lang.Object, java.util.Map] */
    public u0(x0 x0Var, Object obj, AbstractC2652s abstractC2652s, D0 d02) {
        this.f23644z = x0Var;
        this.l = d02;
        T.S s6 = T.S.f8224q;
        C0738e0 c0738e0O = C0735d.O(obj, s6);
        this.f23632m = c0738e0O;
        Object objB = null;
        this.f23633n = C0735d.O(AbstractC2631e.p(0.0f, 7, null), s6);
        this.o = C0735d.O(new p0(c(), d02, obj, c0738e0O.getValue(), abstractC2652s), s6);
        this.f23636r = C0735d.O(Boolean.TRUE, s6);
        this.f23637s = C0735d.L(-1.0f);
        this.f23639u = C0735d.O(obj, s6);
        this.f23640v = abstractC2652s;
        this.f23641w = C0735d.N(b().c());
        Float f10 = (Float) L0.f23437a.get(d02);
        if (f10 != null) {
            float fFloatValue = f10.floatValue();
            AbstractC2652s abstractC2652s2 = (AbstractC2652s) d02.f23394a.b(obj);
            int iB = abstractC2652s2.b();
            for (int i7 = 0; i7 < iB; i7++) {
                abstractC2652s2.e(fFloatValue, i7);
            }
            objB = this.l.f23395b.b(abstractC2652s2);
        }
        this.f23643y = AbstractC2631e.p(0.0f, 3, objB);
    }

    public final p0 b() {
        return (p0) this.o.getValue();
    }

    public final InterfaceC2615D c() {
        return (InterfaceC2615D) this.f23633n.getValue();
    }

    public final void d(long j3) {
        if (this.f23637s.i() == -1.0f) {
            this.f23642x = true;
            if (A7.m.a(b().c, b().d)) {
                e(b().c);
            } else {
                e(b().b(j3));
                this.f23640v = b().f(j3);
            }
        }
    }

    public final void e(Object obj) {
        this.f23639u.setValue(obj);
    }

    public final void g(Object obj, boolean z6) {
        p0 p0Var = this.f23635q;
        Object obj2 = p0Var != null ? p0Var.c : null;
        C0738e0 c0738e0 = this.f23632m;
        boolean zA = A7.m.a(obj2, c0738e0.getValue());
        C0734c0 c0734c0 = this.f23641w;
        C0738e0 c0738e02 = this.o;
        if (zA) {
            c0738e02.setValue(new p0(this.f23643y, this.l, obj, obj, this.f23640v.c()));
            this.f23638t = true;
            c0734c0.j(b().c());
            return;
        }
        InterfaceC2615D interfaceC2615DC = (!z6 || this.f23642x || (c() instanceof C2640i0)) ? c() : this.f23643y;
        x0 x0Var = this.f23644z;
        c0738e02.setValue(new p0(x0Var.e() <= 0 ? interfaceC2615DC : new C2642j0(interfaceC2615DC, x0Var.e()), this.l, obj, c0738e0.getValue(), this.f23640v));
        c0734c0.j(b().c());
        this.f23638t = false;
        Boolean bool = Boolean.TRUE;
        C0738e0 c0738e03 = x0Var.f23666h;
        c0738e03.setValue(bool);
        if (x0Var.g()) {
            C1178r c1178r = x0Var.f23667i;
            int size = c1178r.size();
            long jMax = 0;
            for (int i7 = 0; i7 < size; i7++) {
                u0 u0Var = (u0) c1178r.get(i7);
                jMax = Math.max(jMax, u0Var.f23641w.i());
                u0Var.d(0L);
            }
            c0738e03.setValue(Boolean.FALSE);
        }
    }

    @Override // T.Q0
    public final Object getValue() {
        return this.f23639u.getValue();
    }

    public final void h(Object obj, Object obj2, InterfaceC2615D interfaceC2615D) {
        this.f23632m.setValue(obj2);
        this.f23633n.setValue(interfaceC2615D);
        if (A7.m.a(b().d, obj) && A7.m.a(b().c, obj2)) {
            return;
        }
        g(obj, false);
    }

    public final void i(Object obj, InterfaceC2615D interfaceC2615D) {
        if (this.f23638t) {
            p0 p0Var = this.f23635q;
            if (A7.m.a(obj, p0Var != null ? p0Var.c : null)) {
                return;
            }
        }
        C0738e0 c0738e0 = this.f23632m;
        boolean zA = A7.m.a(c0738e0.getValue(), obj);
        C0730a0 c0730a0 = this.f23637s;
        if (zA && c0730a0.i() == -1.0f) {
            return;
        }
        c0738e0.setValue(obj);
        this.f23633n.setValue(interfaceC2615D);
        Object value = c0730a0.i() == -3.0f ? obj : this.f23639u.getValue();
        C0738e0 c0738e02 = this.f23636r;
        g(value, !((Boolean) c0738e02.getValue()).booleanValue());
        c0738e02.setValue(Boolean.valueOf(c0730a0.i() == -3.0f));
        if (c0730a0.i() >= 0.0f) {
            e(b().b((long) (c0730a0.i() * b().c())));
        } else if (c0730a0.i() == -3.0f) {
            e(obj);
        }
        this.f23638t = false;
        c0730a0.j(-1.0f);
    }

    public final String toString() {
        return "current value: " + this.f23639u.getValue() + ", target: " + this.f23632m.getValue() + ", spec: " + c();
    }
}
