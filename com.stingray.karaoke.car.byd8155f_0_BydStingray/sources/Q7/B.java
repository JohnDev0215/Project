package Q7;

import F8.C0275j;
import T7.AbstractC0791j;
import T7.C0790i;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import n7.AbstractC1985B;
import n7.AbstractC2000o;
import o8.C2061f;
import v8.AbstractC2564d;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class B extends AbstractC0791j {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final boolean f7169r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final ArrayList f7170s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final C0275j f7171t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public B(E8.o oVar, InterfaceC0653f interfaceC0653f, C2061f c2061f, boolean z6, int i7) {
        super(oVar, interfaceC0653f, c2061f, N.c);
        A7.m.f(oVar, "storageManager");
        A7.m.f(interfaceC0653f, "container");
        this.f7169r = z6;
        G7.d dVarB0 = AbstractC2673a.b0(0, i7);
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(dVarB0, 10));
        G7.c it = dVarB0.iterator();
        while (it.f2715n) {
            int iB = it.b();
            arrayList.add(T7.O.f1(this, 1, C2061f.e("T" + iB), iB, oVar));
        }
        this.f7170s = arrayList;
        this.f7171t = new C0275j(this, AbstractC0669w.c(this), AbstractC1985B.t(AbstractC2564d.j(this).l().e()), oVar);
    }

    @Override // T7.y
    public final y8.n A(G8.f fVar) {
        return y8.m.f24213b;
    }

    @Override // Q7.InterfaceC0654g
    public final F8.K C() {
        return this.f7171t;
    }

    @Override // Q7.InterfaceC0652e
    public final int E() {
        return 1;
    }

    @Override // Q7.InterfaceC0652e
    public final boolean F() {
        return false;
    }

    @Override // Q7.InterfaceC0652e
    public final Collection H() {
        return n7.w.l;
    }

    @Override // Q7.InterfaceC0652e
    public final boolean H0() {
        return false;
    }

    @Override // Q7.InterfaceC0652e
    public final boolean L() {
        return false;
    }

    @Override // Q7.InterfaceC0652e
    public final Collection X() {
        return n7.u.l;
    }

    @Override // Q7.InterfaceC0652e
    public final boolean b0() {
        return false;
    }

    @Override // Q7.InterfaceC0670x
    public final boolean c0() {
        return false;
    }

    @Override // Q7.InterfaceC0655h
    public final boolean d0() {
        return this.f7169r;
    }

    @Override // Q7.InterfaceC0652e, Q7.InterfaceC0660m, Q7.InterfaceC0670x
    public final C0661n e() {
        C0661n c0661n = AbstractC0662o.f7191e;
        A7.m.e(c0661n, "PUBLIC");
        return c0661n;
    }

    @Override // Q7.InterfaceC0652e, Q7.InterfaceC0670x
    public final int h() {
        return 1;
    }

    @Override // R7.a
    public final R7.h k() {
        return R7.g.f7535a;
    }

    @Override // Q7.InterfaceC0652e
    public final S m0() {
        return null;
    }

    @Override // Q7.InterfaceC0652e
    public final boolean n() {
        return false;
    }

    @Override // Q7.InterfaceC0652e
    public final C0790i p0() {
        return null;
    }

    @Override // Q7.InterfaceC0652e
    public final /* bridge */ /* synthetic */ y8.n q0() {
        return y8.m.f24213b;
    }

    @Override // Q7.InterfaceC0652e, Q7.InterfaceC0655h
    public final List s() {
        return this.f7170s;
    }

    public final String toString() {
        return "class " + getName() + " (not found)";
    }

    @Override // Q7.InterfaceC0670x
    public final boolean y0() {
        return false;
    }

    @Override // T7.AbstractC0791j, Q7.InterfaceC0670x
    public final boolean z() {
        return false;
    }
}
