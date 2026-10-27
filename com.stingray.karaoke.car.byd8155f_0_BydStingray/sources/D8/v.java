package D8;

import F8.A;
import F8.AbstractC0268c;
import F8.K;
import F8.W;
import F8.Y;
import Q7.C0661n;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import Q7.InterfaceC0655h;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0658k;
import Q7.InterfaceC0659l;
import Q7.N;
import T.C0746i0;
import T7.AbstractC0795n;
import T7.C0785d;
import T7.C0786e;
import h8.C1391h;
import j8.T;
import java.util.List;
import l8.C1790h;
import l8.InterfaceC1788f;
import o8.C2061f;

/* JADX INFO: loaded from: classes.dex */
public final class v extends AbstractC0795n implements m, InterfaceC0655h {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public A f1282A;
    public List B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public A f1283C;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final C0661n f1284q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public List f1285r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final C0786e f1286s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final E8.o f1287t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final T f1288u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final InterfaceC1788f f1289v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final C0746i0 f1290w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final C1790h f1291x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final C1391h f1292y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public A f1293z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(E8.o oVar, InterfaceC0657j interfaceC0657j, R7.h hVar, C2061f c2061f, C0661n c0661n, T t10, InterfaceC1788f interfaceC1788f, C0746i0 c0746i0, C1790h c1790h, C1391h c1391h) {
        super(interfaceC0657j, hVar, c2061f, N.c);
        A7.m.f(oVar, "storageManager");
        A7.m.f(interfaceC0657j, "containingDeclaration");
        A7.m.f(c0661n, "visibility");
        A7.m.f(t10, "proto");
        A7.m.f(interfaceC1788f, "nameResolver");
        A7.m.f(c0746i0, "typeTable");
        A7.m.f(c1790h, "versionRequirementTable");
        this.f1284q = c0661n;
        this.f1286s = new C0786e(this);
        this.f1287t = oVar;
        this.f1288u = t10;
        this.f1289v = interfaceC1788f;
        this.f1290w = c0746i0;
        this.f1291x = c1790h;
        this.f1292y = c1391h;
    }

    @Override // Q7.InterfaceC0654g
    public final K C() {
        return this.f1286s;
    }

    @Override // D8.m
    public final InterfaceC1788f G0() {
        throw null;
    }

    @Override // Q7.InterfaceC0657j
    public final Object S(InterfaceC0659l interfaceC0659l, Object obj) {
        return interfaceC0659l.m(this, obj);
    }

    @Override // T7.AbstractC0795n, T7.AbstractC0794m, Q7.InterfaceC0657j, Q7.InterfaceC0654g
    /* JADX INFO: renamed from: a */
    public final InterfaceC0654g b1() {
        return this;
    }

    @Override // T7.AbstractC0795n
    public final InterfaceC0658k b1() {
        return this;
    }

    @Override // Q7.InterfaceC0670x
    public final boolean c0() {
        return false;
    }

    public final InterfaceC0652e c1() {
        if (AbstractC0268c.i(d1())) {
            return null;
        }
        InterfaceC0654g interfaceC0654gN = d1().B0().n();
        if (interfaceC0654gN instanceof InterfaceC0652e) {
            return (InterfaceC0652e) interfaceC0654gN;
        }
        return null;
    }

    @Override // Q7.InterfaceC0655h
    public final boolean d0() {
        return Y.c(e1(), new C0785d(this, 1), null);
    }

    public final A d1() {
        A a10 = this.f1282A;
        if (a10 != null) {
            return a10;
        }
        A7.m.j("expandedType");
        throw null;
    }

    @Override // Q7.InterfaceC0670x
    public final C0661n e() {
        return this.f1284q;
    }

    public final A e1() {
        A a10 = this.f1293z;
        if (a10 != null) {
            return a10;
        }
        A7.m.j("underlyingType");
        throw null;
    }

    @Override // Q7.P
    public final InterfaceC0658k f(W w10) {
        A7.m.f(w10, "substitutor");
        if (w10.f2455a.e()) {
            return this;
        }
        InterfaceC0657j interfaceC0657jO = o();
        A7.m.e(interfaceC0657jO, "containingDeclaration");
        R7.h hVarK = k();
        A7.m.e(hVarK, "annotations");
        C2061f name = getName();
        A7.m.e(name, "name");
        v vVar = new v(this.f1287t, interfaceC0657jO, hVarK, name, this.f1284q, this.f1288u, this.f1289v, this.f1290w, this.f1291x, this.f1292y);
        vVar.f1(s(), AbstractC0268c.b(w10.g(1, e1())), AbstractC0268c.b(w10.g(1, d1())));
        return vVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00bb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f1(java.util.List r32, F8.A r33, F8.A r34) {
        /*
            Method dump skipped, instruction units count: 486
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: D8.v.f1(java.util.List, F8.A, F8.A):void");
    }

    @Override // Q7.InterfaceC0654g
    public final A j() {
        A a10 = this.f1283C;
        if (a10 != null) {
            return a10;
        }
        A7.m.j("defaultTypeImpl");
        throw null;
    }

    @Override // D8.m
    public final C0746i0 r0() {
        throw null;
    }

    @Override // Q7.InterfaceC0655h
    public final List s() {
        List list = this.f1285r;
        if (list != null) {
            return list;
        }
        A7.m.j("declaredTypeParametersImpl");
        throw null;
    }

    @Override // T7.AbstractC0794m, A9.b
    public final String toString() {
        return "typealias " + getName().b();
    }

    @Override // D8.m
    public final l v() {
        return this.f1292y;
    }

    @Override // Q7.InterfaceC0670x
    public final boolean y0() {
        return false;
    }

    @Override // Q7.InterfaceC0670x
    public final boolean z() {
        return false;
    }

    @Override // T7.AbstractC0795n, T7.AbstractC0794m, Q7.InterfaceC0657j, Q7.InterfaceC0654g
    /* JADX INFO: renamed from: a */
    public final InterfaceC0657j b1() {
        return this;
    }
}
