package O7;

import A7.m;
import E8.l;
import F8.K;
import N7.o;
import Q7.AbstractC0662o;
import Q7.C0661n;
import Q7.InterfaceC0657j;
import Q7.N;
import Q7.S;
import R7.h;
import T7.AbstractC0783b;
import T7.C0790i;
import T7.O;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import m7.q;
import n7.AbstractC1998m;
import n7.AbstractC2000o;
import n7.u;
import o8.C2057b;
import o8.C2061f;
import y8.n;

/* JADX INFO: loaded from: classes.dex */
public final class c extends AbstractC0783b {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final C2057b f5170w = new C2057b(o.f4875j, C2061f.e("Function"));

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final C2057b f5171x = new C2057b(o.f4873h, C2061f.e("KFunction"));

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final l f5172p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final C8.c f5173q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final e f5174r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int f5175s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final b f5176t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final f f5177u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final List f5178v;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(l lVar, C8.c cVar, e eVar, int i7) {
        super(lVar, eVar.a(i7));
        m.f(cVar, "containingDeclaration");
        this.f5172p = lVar;
        this.f5173q = cVar;
        this.f5174r = eVar;
        this.f5175s = i7;
        this.f5176t = new b(this);
        this.f5177u = new f(lVar, this);
        ArrayList arrayList = new ArrayList();
        G7.d dVar = new G7.d(1, i7, 1);
        ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(dVar, 10));
        G7.c it = dVar.iterator();
        while (it.f2715n) {
            arrayList.add(O.f1(this, 2, C2061f.e("P" + it.b()), arrayList.size(), this.f5172p));
            arrayList2.add(q.f19267a);
        }
        arrayList.add(O.f1(this, 3, C2061f.e("R"), arrayList.size(), this.f5172p));
        this.f5178v = AbstractC1998m.z0(arrayList);
    }

    @Override // T7.y
    public final n A(G8.f fVar) {
        return this.f5177u;
    }

    @Override // Q7.InterfaceC0654g
    public final K C() {
        return this.f5176t;
    }

    @Override // Q7.InterfaceC0652e
    public final int E() {
        return 2;
    }

    @Override // Q7.InterfaceC0652e
    public final boolean F() {
        return false;
    }

    @Override // Q7.InterfaceC0652e
    public final /* bridge */ /* synthetic */ Collection H() {
        return u.l;
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
    public final /* bridge */ /* synthetic */ Collection X() {
        return u.l;
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
        return false;
    }

    @Override // Q7.InterfaceC0652e, Q7.InterfaceC0660m, Q7.InterfaceC0670x
    public final C0661n e() {
        C0661n c0661n = AbstractC0662o.f7191e;
        m.e(c0661n, "PUBLIC");
        return c0661n;
    }

    @Override // Q7.InterfaceC0658k
    public final N g() {
        return N.c;
    }

    @Override // Q7.InterfaceC0652e, Q7.InterfaceC0670x
    public final int h() {
        return 4;
    }

    @Override // R7.a
    public final h k() {
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

    @Override // Q7.InterfaceC0657j
    public final InterfaceC0657j o() {
        return this.f5173q;
    }

    @Override // Q7.InterfaceC0652e
    public final /* bridge */ /* synthetic */ C0790i p0() {
        return null;
    }

    @Override // Q7.InterfaceC0652e
    public final /* bridge */ /* synthetic */ n q0() {
        return y8.m.f24213b;
    }

    @Override // Q7.InterfaceC0652e, Q7.InterfaceC0655h
    public final List s() {
        return this.f5178v;
    }

    public final String toString() {
        String strB = getName().b();
        m.e(strB, "name.asString()");
        return strB;
    }

    @Override // Q7.InterfaceC0670x
    public final boolean y0() {
        return false;
    }

    @Override // Q7.InterfaceC0670x
    public final boolean z() {
        return false;
    }
}
