package x;

import T.C0734c0;
import T.C0735d;
import T.C0738e0;
import e0.C1178r;
import w.C2582m;

/* JADX INFO: loaded from: classes.dex */
public final class x0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final A9.b f23661a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final x0 f23662b;
    public final String c;
    public final C0738e0 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0738e0 f23663e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C0734c0 f23664f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final C0734c0 f23665g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final C0738e0 f23666h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final C1178r f23667i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final C1178r f23668j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final C0738e0 f23669k;
    public final T.D l;

    public x0(A9.b bVar, x0 x0Var, String str) {
        this.f23661a = bVar;
        this.f23662b = x0Var;
        this.c = str;
        Object objP0 = bVar.P0();
        T.S s6 = T.S.f8224q;
        this.d = C0735d.O(objP0, s6);
        this.f23663e = C0735d.O(new t0(bVar.P0(), bVar.P0()), s6);
        this.f23664f = C0735d.N(0L);
        this.f23665g = C0735d.N(Long.MIN_VALUE);
        Boolean bool = Boolean.FALSE;
        this.f23666h = C0735d.O(bool, s6);
        this.f23667i = new C1178r();
        this.f23668j = new C1178r();
        this.f23669k = C0735d.O(bool, s6);
        this.l = C0735d.G(new C2582m(this, 1));
        bVar.X0(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0089  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(java.lang.Object r10, T.C0761q r11, int r12) {
        /*
            Method dump skipped, instruction units count: 235
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: x.x0.a(java.lang.Object, T.q, int):void");
    }

    public final long b() {
        C1178r c1178r = this.f23667i;
        int size = c1178r.size();
        long jMax = 0;
        for (int i7 = 0; i7 < size; i7++) {
            jMax = Math.max(jMax, ((u0) c1178r.get(i7)).f23641w.i());
        }
        C1178r c1178r2 = this.f23668j;
        int size2 = c1178r2.size();
        for (int i10 = 0; i10 < size2; i10++) {
            jMax = Math.max(jMax, ((x0) c1178r2.get(i10)).b());
        }
        return jMax;
    }

    public final void c() {
        C1178r c1178r = this.f23667i;
        int size = c1178r.size();
        for (int i7 = 0; i7 < size; i7++) {
            u0 u0Var = (u0) c1178r.get(i7);
            u0Var.f23635q = null;
            u0Var.f23634p = null;
            u0Var.f23638t = false;
        }
        C1178r c1178r2 = this.f23668j;
        int size2 = c1178r2.size();
        for (int i10 = 0; i10 < size2; i10++) {
            ((x0) c1178r2.get(i10)).c();
        }
    }

    public final boolean d() {
        C1178r c1178r = this.f23667i;
        int size = c1178r.size();
        int i7 = 0;
        while (true) {
            if (i7 >= size) {
                C1178r c1178r2 = this.f23668j;
                int size2 = c1178r2.size();
                for (int i10 = 0; i10 < size2; i10++) {
                    if (!((x0) c1178r2.get(i10)).d()) {
                    }
                }
                return false;
            }
            if (((u0) c1178r.get(i7)).f23634p != null) {
                break;
            }
            i7++;
        }
        return true;
    }

    public final long e() {
        x0 x0Var = this.f23662b;
        return x0Var != null ? x0Var.e() : this.f23664f.i();
    }

    public final s0 f() {
        return (s0) this.f23663e.getValue();
    }

    public final boolean g() {
        return ((Boolean) this.f23669k.getValue()).booleanValue();
    }

    public final void h(long j3, boolean z6) {
        C0734c0 c0734c0 = this.f23665g;
        long jI = c0734c0.i();
        A9.b bVar = this.f23661a;
        if (jI == Long.MIN_VALUE) {
            c0734c0.j(j3);
            ((C0738e0) bVar.f476m).setValue(Boolean.TRUE);
        } else if (!((Boolean) ((C0738e0) bVar.f476m).getValue()).booleanValue()) {
            ((C0738e0) bVar.f476m).setValue(Boolean.TRUE);
        }
        this.f23666h.setValue(Boolean.FALSE);
        C1178r c1178r = this.f23667i;
        int size = c1178r.size();
        boolean z10 = true;
        for (int i7 = 0; i7 < size; i7++) {
            u0 u0Var = (u0) c1178r.get(i7);
            boolean zBooleanValue = ((Boolean) u0Var.f23636r.getValue()).booleanValue();
            C0738e0 c0738e0 = u0Var.f23636r;
            if (!zBooleanValue) {
                long jC = z6 ? u0Var.b().c() : j3;
                u0Var.e(u0Var.b().b(jC));
                u0Var.f23640v = u0Var.b().f(jC);
                if (u0Var.b().g(jC)) {
                    c0738e0.setValue(Boolean.TRUE);
                }
            }
            if (!((Boolean) c0738e0.getValue()).booleanValue()) {
                z10 = false;
            }
        }
        C1178r c1178r2 = this.f23668j;
        int size2 = c1178r2.size();
        for (int i10 = 0; i10 < size2; i10++) {
            x0 x0Var = (x0) c1178r2.get(i10);
            Object value = x0Var.d.getValue();
            A9.b bVar2 = x0Var.f23661a;
            if (!A7.m.a(value, bVar2.P0())) {
                x0Var.h(j3, z6);
            }
            if (!A7.m.a(x0Var.d.getValue(), bVar2.P0())) {
                z10 = false;
            }
        }
        if (z10) {
            i();
        }
    }

    public final void i() {
        this.f23665g.j(Long.MIN_VALUE);
        A9.b bVar = this.f23661a;
        if (bVar instanceof P) {
            bVar.W0(this.d.getValue());
        }
        o(0L);
        ((C0738e0) bVar.f476m).setValue(Boolean.FALSE);
        C1178r c1178r = this.f23668j;
        int size = c1178r.size();
        for (int i7 = 0; i7 < size; i7++) {
            ((x0) c1178r.get(i7)).i();
        }
    }

    public final void j(float f10) {
        C1178r c1178r = this.f23667i;
        int size = c1178r.size();
        for (int i7 = 0; i7 < size; i7++) {
            u0 u0Var = (u0) c1178r.get(i7);
            u0Var.getClass();
            if (f10 == -4.0f || f10 == -5.0f) {
                p0 p0Var = u0Var.f23635q;
                if (p0Var != null) {
                    u0Var.b().h(p0Var.c);
                    u0Var.f23634p = null;
                    u0Var.f23635q = null;
                }
                Object obj = f10 == -4.0f ? u0Var.b().d : u0Var.b().c;
                u0Var.b().h(obj);
                u0Var.b().i(obj);
                u0Var.e(obj);
                u0Var.f23641w.j(u0Var.b().c());
            } else {
                u0Var.f23637s.j(f10);
            }
        }
        C1178r c1178r2 = this.f23668j;
        int size2 = c1178r2.size();
        for (int i10 = 0; i10 < size2; i10++) {
            ((x0) c1178r2.get(i10)).j(f10);
        }
    }

    public final void k() {
        C1178r c1178r = this.f23667i;
        int size = c1178r.size();
        for (int i7 = 0; i7 < size; i7++) {
            ((u0) c1178r.get(i7)).f23637s.j(-2.0f);
        }
        C1178r c1178r2 = this.f23668j;
        int size2 = c1178r2.size();
        for (int i10 = 0; i10 < size2; i10++) {
            ((x0) c1178r2.get(i10)).k();
        }
    }

    public final void l(Object obj, Object obj2) {
        this.f23665g.j(Long.MIN_VALUE);
        Boolean bool = Boolean.FALSE;
        A9.b bVar = this.f23661a;
        ((C0738e0) bVar.f476m).setValue(bool);
        boolean zG = g();
        C0738e0 c0738e0 = this.d;
        if (!zG || !A7.m.a(bVar.P0(), obj) || !A7.m.a(c0738e0.getValue(), obj2)) {
            if (!A7.m.a(bVar.P0(), obj) && (bVar instanceof P)) {
                bVar.W0(obj);
            }
            c0738e0.setValue(obj2);
            this.f23669k.setValue(Boolean.TRUE);
            this.f23663e.setValue(new t0(obj, obj2));
        }
        C1178r c1178r = this.f23668j;
        int size = c1178r.size();
        for (int i7 = 0; i7 < size; i7++) {
            x0 x0Var = (x0) c1178r.get(i7);
            A7.m.d(x0Var, "null cannot be cast to non-null type androidx.compose.animation.core.Transition<kotlin.Any>");
            if (x0Var.g()) {
                x0Var.l(x0Var.f23661a.P0(), x0Var.d.getValue());
            }
        }
        C1178r c1178r2 = this.f23667i;
        int size2 = c1178r2.size();
        for (int i10 = 0; i10 < size2; i10++) {
            ((u0) c1178r2.get(i10)).d(0L);
        }
    }

    public final void m(long j3) {
        C0734c0 c0734c0 = this.f23665g;
        if (c0734c0.i() == Long.MIN_VALUE) {
            c0734c0.j(j3);
        }
        o(j3);
        this.f23666h.setValue(Boolean.FALSE);
        C1178r c1178r = this.f23667i;
        int size = c1178r.size();
        for (int i7 = 0; i7 < size; i7++) {
            ((u0) c1178r.get(i7)).d(j3);
        }
        C1178r c1178r2 = this.f23668j;
        int size2 = c1178r2.size();
        for (int i10 = 0; i10 < size2; i10++) {
            x0 x0Var = (x0) c1178r2.get(i10);
            if (!A7.m.a(x0Var.d.getValue(), x0Var.f23661a.P0())) {
                x0Var.m(j3);
            }
        }
    }

    public final void n(U u4) {
        C1178r c1178r = this.f23667i;
        int size = c1178r.size();
        for (int i7 = 0; i7 < size; i7++) {
            u0 u0Var = (u0) c1178r.get(i7);
            if (!A7.m.a(u0Var.b().c, u0Var.b().d)) {
                u0Var.f23635q = u0Var.b();
                u0Var.f23634p = u4;
            }
            C0738e0 c0738e0 = u0Var.f23639u;
            u0Var.o.setValue(new p0(u0Var.f23643y, u0Var.l, c0738e0.getValue(), c0738e0.getValue(), u0Var.f23640v.c()));
            u0Var.f23641w.j(u0Var.b().c());
            u0Var.f23638t = true;
        }
        C1178r c1178r2 = this.f23668j;
        int size2 = c1178r2.size();
        for (int i10 = 0; i10 < size2; i10++) {
            ((x0) c1178r2.get(i10)).n(u4);
        }
    }

    public final void o(long j3) {
        if (this.f23662b == null) {
            this.f23664f.j(j3);
        }
    }

    public final void p() {
        p0 p0Var;
        C1178r c1178r = this.f23667i;
        int size = c1178r.size();
        for (int i7 = 0; i7 < size; i7++) {
            u0 u0Var = (u0) c1178r.get(i7);
            U u4 = u0Var.f23634p;
            if (u4 != null && (p0Var = u0Var.f23635q) != null) {
                long jY = C7.a.Y(u4.f23458g * ((double) u4.d));
                Object objB = p0Var.b(jY);
                if (u0Var.f23638t) {
                    u0Var.b().i(objB);
                }
                u0Var.b().h(objB);
                u0Var.f23641w.j(u0Var.b().c());
                if (u0Var.f23637s.i() == -2.0f || u0Var.f23638t) {
                    u0Var.e(objB);
                } else {
                    u0Var.d(u0Var.f23644z.e());
                }
                if (jY >= u4.f23458g) {
                    u0Var.f23634p = null;
                    u0Var.f23635q = null;
                } else {
                    u4.c = false;
                }
            }
        }
        C1178r c1178r2 = this.f23668j;
        int size2 = c1178r2.size();
        for (int i10 = 0; i10 < size2; i10++) {
            ((x0) c1178r2.get(i10)).p();
        }
    }

    public final void q(Object obj) {
        C0738e0 c0738e0 = this.d;
        if (A7.m.a(c0738e0.getValue(), obj)) {
            return;
        }
        this.f23663e.setValue(new t0(c0738e0.getValue(), obj));
        A9.b bVar = this.f23661a;
        if (!A7.m.a(bVar.P0(), c0738e0.getValue())) {
            bVar.W0(c0738e0.getValue());
        }
        c0738e0.setValue(obj);
        if (this.f23665g.i() == Long.MIN_VALUE) {
            this.f23666h.setValue(Boolean.TRUE);
        }
        k();
    }

    public final String toString() {
        C1178r c1178r = this.f23667i;
        int size = c1178r.size();
        String str = "Transition animation values: ";
        for (int i7 = 0; i7 < size; i7++) {
            str = str + ((u0) c1178r.get(i7)) + ", ";
        }
        return str;
    }
}
