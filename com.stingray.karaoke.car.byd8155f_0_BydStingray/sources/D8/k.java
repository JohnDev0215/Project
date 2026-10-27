package D8;

import B8.D;
import F8.A;
import F8.K;
import Q7.C0661n;
import Q7.InterfaceC0657j;
import Q7.L;
import Q7.M;
import Q7.N;
import Q7.O;
import Q7.S;
import T.C0746i0;
import T7.AbstractC0783b;
import T7.C0790i;
import a.AbstractC0904a;
import j8.C1565j;
import j8.EnumC1555A;
import j8.EnumC1564i;
import j8.Q;
import j8.X;
import j8.e0;
import j8.f0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import l8.AbstractC1783a;
import l8.AbstractC1787e;
import l8.C1790h;
import l8.InterfaceC1788f;
import n7.AbstractC2000o;
import o8.C2057b;
import o8.C2061f;
import v8.AbstractC2564d;
import z8.C2793a;

/* JADX INFO: loaded from: classes.dex */
public final class k extends AbstractC0783b implements InterfaceC0657j {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final B8.t f1234A;
    public final InterfaceC0657j B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final E8.h f1235C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final E8.i f1236D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final E8.i f1237E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final E8.h f1238F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final B8.u f1239G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final R7.h f1240H;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final C1565j f1241p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final AbstractC1783a f1242q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final N f1243r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final C2057b f1244s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final int f1245t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final C0661n f1246u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final int f1247v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final B8.k f1248w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final y8.o f1249x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final i f1250y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final M f1251z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(B8.k kVar, C1565j c1565j, InterfaceC1788f interfaceC1788f, AbstractC1783a abstractC1783a, N n10) {
        int i7;
        C1790h c1790h;
        super(((B8.i) kVar.f779a).f762a, AbstractC0904a.D(interfaceC1788f, c1565j.f17395p).i());
        A7.m.f(kVar, "outerContext");
        A7.m.f(c1565j, "classProto");
        A7.m.f(interfaceC1788f, "nameResolver");
        A7.m.f(n10, "sourceElement");
        this.f1241p = c1565j;
        this.f1242q = abstractC1783a;
        this.f1243r = n10;
        this.f1244s = AbstractC0904a.D(interfaceC1788f, c1565j.f17395p);
        this.f1245t = B8.j.e((EnumC1555A) AbstractC1787e.f18841e.c(c1565j.o));
        this.f1246u = P8.n.t((f0) AbstractC1787e.d.c(c1565j.o));
        EnumC1564i enumC1564i = (EnumC1564i) AbstractC1787e.f18842f.c(c1565j.o);
        int i10 = 1;
        switch (enumC1564i == null ? -1 : B8.w.f817b[enumC1564i.ordinal()]) {
            case 2:
                i7 = 2;
                break;
            case 3:
                i7 = 3;
                break;
            case 4:
                i7 = 4;
                break;
            case 5:
                i7 = 5;
                break;
            case 6:
            case 7:
                i7 = 6;
                break;
            default:
                i7 = 1;
                break;
        }
        this.f1247v = i7;
        List list = c1565j.f17397r;
        A7.m.e(list, "classProto.typeParameterList");
        X x8 = c1565j.f17388P;
        A7.m.e(x8, "classProto.typeTable");
        C0746i0 c0746i0 = new C0746i0(x8);
        e0 e0Var = c1565j.f17390R;
        A7.m.e(e0Var, "classProto.versionRequirementTable");
        if (e0Var.f17332m.size() == 0) {
            c1790h = C1790h.f18862a;
        } else {
            A7.m.e(e0Var.f17332m, "table.requirementList");
            c1790h = new C1790h();
        }
        B8.k kVarA = kVar.a(this, list, interfaceC1788f, c0746i0, c1790h, abstractC1783a);
        this.f1248w = kVarA;
        B8.i iVar = (B8.i) kVarA.f779a;
        this.f1249x = i7 == 3 ? new y8.r(iVar.f762a, this) : y8.m.f24213b;
        this.f1250y = new i(this);
        O o = M.d;
        E8.o oVar = iVar.f762a;
        ((G8.l) iVar.f774q).getClass();
        j jVar = new j(i10, 0, this);
        o.getClass();
        A7.m.f(oVar, "storageManager");
        this.f1251z = new M(this, oVar, jVar);
        this.f1234A = i7 == 3 ? new B8.t(this) : null;
        InterfaceC0657j interfaceC0657j = (InterfaceC0657j) kVar.c;
        this.B = interfaceC0657j;
        h hVar = new h(this, 4);
        E8.o oVar2 = iVar.f762a;
        E8.l lVar = (E8.l) oVar2;
        lVar.getClass();
        this.f1235C = new E8.h(lVar, hVar);
        h hVar2 = new h(this, 3);
        E8.l lVar2 = (E8.l) oVar2;
        lVar2.getClass();
        this.f1236D = new E8.i(lVar2, hVar2);
        h hVar3 = new h(this, 2);
        E8.l lVar3 = (E8.l) oVar2;
        lVar3.getClass();
        new E8.h(lVar3, hVar3);
        h hVar4 = new h(this, 5);
        E8.l lVar4 = (E8.l) oVar2;
        lVar4.getClass();
        this.f1237E = new E8.i(lVar4, hVar4);
        h hVar5 = new h(this, 6);
        E8.l lVar5 = (E8.l) oVar2;
        lVar5.getClass();
        this.f1238F = new E8.h(lVar5, hVar5);
        k kVar2 = interfaceC0657j instanceof k ? (k) interfaceC0657j : null;
        this.f1239G = new B8.u(c1565j, (InterfaceC1788f) kVarA.f780b, (C0746i0) kVarA.d, n10, kVar2 != null ? kVar2.f1239G : null);
        this.f1240H = !AbstractC1787e.c.c(c1565j.o).booleanValue() ? R7.g.f7535a : new x(oVar2, new h(this, 1));
    }

    @Override // T7.y
    public final y8.n A(G8.f fVar) {
        M m10 = this.f1251z;
        AbstractC2564d.j(m10.f7178a);
        return (y8.n) C7.a.N(m10.c, M.f7177e[0]);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v11, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.lang.Iterable] */
    @Override // T7.AbstractC0783b, Q7.InterfaceC0652e
    public final List A0() {
        B8.k kVar = this.f1248w;
        C0746i0 c0746i0 = (C0746i0) kVar.d;
        C1565j c1565j = this.f1241p;
        A7.m.f(c1565j, "<this>");
        A7.m.f(c0746i0, "typeTable");
        List list = c1565j.f17403x;
        boolean zIsEmpty = list.isEmpty();
        ?? arrayList = list;
        if (zIsEmpty) {
            arrayList = 0;
        }
        if (arrayList == 0) {
            List<Integer> list2 = c1565j.f17404y;
            A7.m.e(list2, "contextReceiverTypeIdList");
            arrayList = new ArrayList(AbstractC2000o.J(list2, 10));
            for (Integer num : list2) {
                A7.m.e(num, "it");
                arrayList.add(c0746i0.c(num.intValue()));
            }
        }
        ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(new T7.u(J0(), new C2793a(this, ((D) kVar.f784h).g((Q) it.next()), (C2061f) null), R7.g.f7535a));
        }
        return arrayList2;
    }

    @Override // Q7.InterfaceC0654g
    public final K C() {
        return this.f1250y;
    }

    @Override // Q7.InterfaceC0652e
    public final int E() {
        return this.f1247v;
    }

    @Override // Q7.InterfaceC0652e
    public final boolean F() {
        return AbstractC1787e.f18842f.c(this.f1241p.o) == EnumC1564i.COMPANION_OBJECT;
    }

    @Override // Q7.InterfaceC0652e
    public final Collection H() {
        return (Collection) this.f1236D.a();
    }

    @Override // Q7.InterfaceC0652e
    public final boolean H0() {
        return AbstractC1787e.f18844h.c(this.f1241p.o).booleanValue();
    }

    @Override // Q7.InterfaceC0652e
    public final boolean L() {
        return AbstractC1787e.l.c(this.f1241p.o).booleanValue();
    }

    @Override // Q7.InterfaceC0652e
    public final Collection X() {
        return (Collection) this.f1237E.a();
    }

    @Override // Q7.InterfaceC0652e
    public final boolean b0() {
        return AbstractC1787e.f18847k.c(this.f1241p.o).booleanValue() && this.f1242q.a(1, 4, 2);
    }

    @Override // Q7.InterfaceC0670x
    public final boolean c0() {
        return AbstractC1787e.f18846j.c(this.f1241p.o).booleanValue();
    }

    @Override // Q7.InterfaceC0655h
    public final boolean d0() {
        return AbstractC1787e.f18843g.c(this.f1241p.o).booleanValue();
    }

    @Override // Q7.InterfaceC0652e, Q7.InterfaceC0660m, Q7.InterfaceC0670x
    public final C0661n e() {
        return this.f1246u;
    }

    @Override // Q7.InterfaceC0658k
    public final N g() {
        return this.f1243r;
    }

    @Override // Q7.InterfaceC0652e, Q7.InterfaceC0670x
    public final int h() {
        return this.f1245t;
    }

    @Override // R7.a
    public final R7.h k() {
        return this.f1240H;
    }

    @Override // Q7.InterfaceC0652e
    public final S m0() {
        return (S) this.f1238F.a();
    }

    @Override // Q7.InterfaceC0652e
    public final boolean n() {
        if (AbstractC1787e.f18847k.c(this.f1241p.o).booleanValue()) {
            AbstractC1783a abstractC1783a = this.f1242q;
            int i7 = abstractC1783a.f18823b;
            if (i7 < 1) {
                return true;
            }
            if (i7 <= 1) {
                int i10 = abstractC1783a.c;
                if (i10 < 4) {
                    return true;
                }
                if (i10 <= 4 && abstractC1783a.d <= 1) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // Q7.InterfaceC0657j
    public final InterfaceC0657j o() {
        return this.B;
    }

    public final g o0() {
        ((G8.l) ((B8.i) this.f1248w.f779a).f774q).getClass();
        M m10 = this.f1251z;
        AbstractC2564d.j(m10.f7178a);
        return (g) ((y8.n) C7.a.N(m10.c, M.f7177e[0]));
    }

    @Override // Q7.InterfaceC0652e
    public final C0790i p0() {
        return (C0790i) this.f1235C.a();
    }

    @Override // Q7.InterfaceC0652e
    public final y8.n q0() {
        return this.f1249x;
    }

    @Override // Q7.InterfaceC0652e, Q7.InterfaceC0655h
    public final List s() {
        return ((D) this.f1248w.f784h).b();
    }

    public final A t0(C2061f c2061f) {
        Iterator it = o0().b(c2061f, Y7.b.f10802r).iterator();
        boolean z6 = false;
        Object obj = null;
        while (true) {
            if (!it.hasNext()) {
                if (!z6) {
                    break;
                }
            } else {
                Object next = it.next();
                if (((L) next).K() == null) {
                    if (z6) {
                        break;
                    }
                    z6 = true;
                    obj = next;
                }
            }
        }
        obj = null;
        L l = (L) obj;
        return (A) (l != null ? l.b() : null);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("deserialized ");
        sb.append(c0() ? "expect " : "");
        sb.append("class ");
        sb.append(getName());
        return sb.toString();
    }

    @Override // Q7.InterfaceC0670x
    public final boolean y0() {
        return false;
    }

    @Override // Q7.InterfaceC0670x
    public final boolean z() {
        return AbstractC1787e.f18845i.c(this.f1241p.o).booleanValue();
    }
}
