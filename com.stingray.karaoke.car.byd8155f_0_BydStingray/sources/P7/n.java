package P7;

import A7.B;
import A7.s;
import F8.A;
import F8.C0289y;
import F8.T;
import F8.W;
import H7.w;
import K7.K;
import Q7.AbstractC0669w;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import T7.C0790i;
import T7.C0792k;
import T7.C0799s;
import T7.Q;
import T7.t;
import T7.z;
import a.AbstractC0904a;
import android.hardware.bydauto.properties.CarSettings;
import d8.C1122i;
import d8.C1127n;
import h8.AbstractC1387d;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import n7.AbstractC2000o;
import n7.u;
import o8.C2057b;
import o8.C2058c;
import o8.C2060e;
import o8.C2061f;
import v8.AbstractC2564d;

/* JADX INFO: loaded from: classes.dex */
public final class n implements S7.b, S7.d {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final /* synthetic */ w[] f5447g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z f5448a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final E8.i f5449b;
    public final A c;
    public final E8.i d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final E8.e f5450e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final E8.i f5451f;

    static {
        B b10 = A7.A.f443a;
        f5447g = new w[]{b10.h(new s(b10.b(n.class), CarSettings.UserTableData.TABLE_NAME_SETTINGS, "getSettings()Lorg/jetbrains/kotlin/builtins/jvm/JvmBuiltIns$Settings;")), b10.h(new s(b10.b(n.class), "cloneableType", "getCloneableType()Lorg/jetbrains/kotlin/types/SimpleType;")), b10.h(new s(b10.b(n.class), "notConsideredDeprecation", "getNotConsideredDeprecation()Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;"))};
    }

    public n(z zVar, E8.l lVar, K k10) {
        this.f5448a = zVar;
        this.f5449b = new E8.i(lVar, k10);
        C0792k c0792k = new C0792k(new k(zVar, new C2058c("java.io"), 0), C2061f.e("Serializable"), 4, 2, AbstractC1387d.j(new C0289y(lVar, new l(this, 0))), lVar);
        c0792k.o0(y8.m.f24213b, n7.w.l, null);
        this.c = c0792k.j();
        this.d = new E8.i(lVar, new B8.A(this, 21, lVar));
        this.f5450e = new E8.e(lVar, new ConcurrentHashMap(3, 1.0f, 2), new E8.f(), 0);
        this.f5451f = new E8.i(lVar, new l(this, 1));
    }

    @Override // S7.b
    public final Collection a(InterfaceC0652e interfaceC0652e) {
        Set setE;
        A7.m.f(interfaceC0652e, "classDescriptor");
        g().getClass();
        Set set = n7.w.l;
        C1122i c1122iF = f(interfaceC0652e);
        if (c1122iF != null && (setE = c1122iF.o0().e()) != null) {
            set = setE;
        }
        return set;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.Collection, n7.u] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.util.Collection] */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.util.ArrayList] */
    @Override // S7.b
    public final Collection b(InterfaceC0652e interfaceC0652e) {
        InterfaceC0652e interfaceC0652eC;
        A7.m.f(interfaceC0652e, "classDescriptor");
        int iE = interfaceC0652e.E();
        ?? arrayList = u.l;
        if (iE == 1) {
            g().getClass();
            C1122i c1122iF = f(interfaceC0652e);
            if (c1122iF == null || (interfaceC0652eC = e.c(AbstractC2564d.g(c1122iF), b.f5414f)) == null) {
                return arrayList;
            }
            W w10 = new W(AbstractC0904a.z(interfaceC0652eC, c1122iF));
            List list = (List) c1122iF.B.f13607q.a();
            ArrayList<C0790i> arrayList2 = new ArrayList();
            Iterator it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                C0790i c0790i = (C0790i) next;
                C0790i c0790i2 = c0790i;
                if (c0790i2.e().f7187a.f7186b) {
                    Collection collectionH = interfaceC0652eC.H();
                    A7.m.e(collectionH, "defaultKotlinVersion.constructors");
                    Collection<C0790i> collection = collectionH;
                    if (!(collection instanceof Collection) || !collection.isEmpty()) {
                        for (C0790i c0790i3 : collection) {
                            A7.m.e(c0790i3, "it");
                            if (r8.l.j(c0790i3, c0790i.f(w10)) == 1) {
                                break;
                            }
                        }
                    }
                    if (c0790i2.D0().size() == 1) {
                        List listD0 = c0790i2.D0();
                        A7.m.e(listD0, "valueParameters");
                        InterfaceC0654g interfaceC0654gN = ((Q) AbstractC1998m.r0(listD0)).b().B0().n();
                        if (A7.m.a(interfaceC0654gN != null ? AbstractC2564d.h(interfaceC0654gN) : null, AbstractC2564d.h(interfaceC0652e))) {
                        }
                    }
                    if (!N7.i.B(c0790i) && !p.f5456e.contains(h6.e.r(c1122iF, h6.f.c(c0790i, 3)))) {
                        arrayList2.add(next);
                    }
                }
            }
            arrayList = new ArrayList(AbstractC2000o.J(arrayList2, 10));
            for (C0790i c0790i4 : arrayList2) {
                C0790i c0790i5 = c0790i4;
                c0790i5.getClass();
                C0799s c0799sI1 = c0790i5.i1(W.f2454b);
                c0799sI1.f8871m = interfaceC0652e;
                c0799sI1.E(interfaceC0652e.j());
                c0799sI1.f8883z = true;
                T tF = w10.f();
                if (tF == null) {
                    C0799s.b(37);
                    throw null;
                }
                c0799sI1.l = tF;
                if (!p.f5457f.contains(h6.e.r(c1122iF, h6.f.c(c0790i4, 3)))) {
                    c0799sI1.i((R7.h) C7.a.N(this.f5451f, f5447g[2]));
                }
                t tVarF1 = c0799sI1.f8870I.f1(c0799sI1);
                A7.m.d(tVarF1, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassConstructorDescriptor");
                arrayList.add((C0790i) tVarF1);
            }
        }
        return arrayList;
    }

    @Override // S7.b
    public final Collection c(InterfaceC0652e interfaceC0652e) {
        boolean zIsAssignableFrom = true;
        A7.m.f(interfaceC0652e, "classDescriptor");
        C2060e c2060eH = AbstractC2564d.h(interfaceC0652e);
        LinkedHashSet linkedHashSet = p.f5454a;
        C2060e c2060e = N7.n.f4850g;
        boolean z6 = c2060eH.equals(c2060e) || N7.n.f4847c0.get(c2060eH) != null;
        A a10 = this.c;
        if (z6) {
            A a11 = (A) C7.a.N(this.d, f5447g[1]);
            A7.m.e(a11, "cloneableType");
            return AbstractC1999n.D(a11, a10);
        }
        if (!c2060eH.equals(c2060e) && N7.n.f4847c0.get(c2060eH) == null) {
            String str = d.f5417a;
            C2057b c2057bF = d.f(c2060eH);
            if (c2057bF == null) {
                zIsAssignableFrom = false;
            } else {
                try {
                    zIsAssignableFrom = Serializable.class.isAssignableFrom(Class.forName(c2057bF.b().b()));
                } catch (ClassNotFoundException unused) {
                    zIsAssignableFrom = false;
                }
            }
        }
        return zIsAssignableFrom ? AbstractC1387d.j(a10) : u.l;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x01bb A[EDGE_INSN: B:121:0x01bb->B:59:0x01bb BREAK  A[LOOP:3: B:72:0x01e8->B:122:?]] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0240  */
    @Override // S7.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.Collection d(o8.C2061f r17, Q7.InterfaceC0652e r18) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 765
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: P7.n.d(o8.f, Q7.e):java.util.Collection");
    }

    @Override // S7.d
    public final boolean e(InterfaceC0652e interfaceC0652e, D8.u uVar) {
        A7.m.f(interfaceC0652e, "classDescriptor");
        C1122i c1122iF = f(interfaceC0652e);
        if (c1122iF == null || !uVar.k().k(S7.e.f8080a)) {
            return true;
        }
        g().getClass();
        String strC = h6.f.c(uVar, 3);
        C1127n c1127nO0 = c1122iF.o0();
        C2061f name = uVar.getName();
        A7.m.e(name, "functionDescriptor.name");
        Collection collectionA = c1127nO0.a(name, Y7.b.l);
        if (!(collectionA instanceof Collection) || !collectionA.isEmpty()) {
            Iterator it = collectionA.iterator();
            while (it.hasNext()) {
                if (h6.f.c((T7.K) it.next(), 3).equals(strC)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final C1122i f(InterfaceC0652e interfaceC0652e) {
        if (interfaceC0652e == null) {
            N7.i.a(108);
            throw null;
        }
        C2061f c2061f = N7.i.f4796e;
        if (N7.i.b(interfaceC0652e, N7.n.f4843a) || !N7.i.G(interfaceC0652e)) {
            return null;
        }
        C2060e c2060eH = AbstractC2564d.h(interfaceC0652e);
        if (!c2060eH.d()) {
            return null;
        }
        String str = d.f5417a;
        C2057b c2057bF = d.f(c2060eH);
        if (c2057bF == null) {
            return null;
        }
        InterfaceC0652e interfaceC0652eJ = AbstractC0669w.j(g().f5435a, c2057bF.b());
        if (interfaceC0652eJ instanceof C1122i) {
            return (C1122i) interfaceC0652eJ;
        }
        return null;
    }

    public final h g() {
        return (h) C7.a.N(this.f5449b, f5447g[0]);
    }
}
