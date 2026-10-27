package u2;

import d5.AbstractC1095q;
import d5.C1078G;
import e2.C1212u;
import e2.C1214w;
import e2.C1216y;
import e2.C1217z;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import n0.C1886o;

/* JADX INFO: loaded from: classes.dex */
public final class M extends AbstractC2507j {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final e2.F f22684s;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final AbstractC2498a[] f22685k;
    public final ArrayList l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final e2.f0[] f22686m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ArrayList f22687n;
    public final C1886o o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f22688p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public long[][] f22689q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public E5.s f22690r;

    static {
        C1212u c1212u = new C1212u();
        C1078G c1078g = d5.I.f13384m;
        d5.d0 d0Var = d5.d0.f13415p;
        Collections.emptyList();
        d5.d0 d0Var2 = d5.d0.f13415p;
        C1216y c1216y = new C1216y();
        f22684s = new e2.F("MergingMediaSource", new C1214w(c1212u), null, new C1217z(c1216y), e2.I.f13914K, e2.B.d);
    }

    public M(AbstractC2498a... abstractC2498aArr) {
        C1886o c1886o = new C1886o(12);
        this.f22685k = abstractC2498aArr;
        this.o = c1886o;
        this.f22687n = new ArrayList(Arrays.asList(abstractC2498aArr));
        this.f22688p = -1;
        this.l = new ArrayList(abstractC2498aArr.length);
        for (int i7 = 0; i7 < abstractC2498aArr.length; i7++) {
            this.l.add(new ArrayList());
        }
        this.f22686m = new e2.f0[abstractC2498aArr.length];
        this.f22689q = new long[0][];
        new HashMap();
        AbstractC1095q.e(8, "expectedKeys");
        new d5.V(7).c().g();
    }

    @Override // u2.AbstractC2498a
    public final boolean a(e2.F f10) {
        AbstractC2498a[] abstractC2498aArr = this.f22685k;
        return abstractC2498aArr.length > 0 && abstractC2498aArr[0].a(f10);
    }

    @Override // u2.AbstractC2498a
    public final InterfaceC2490A b(C2492C c2492c, x2.e eVar, long j3) {
        AbstractC2498a[] abstractC2498aArr = this.f22685k;
        int length = abstractC2498aArr.length;
        InterfaceC2490A[] interfaceC2490AArr = new InterfaceC2490A[length];
        e2.f0[] f0VarArr = this.f22686m;
        int iB = f0VarArr[0].b(c2492c.f22663a);
        for (int i7 = 0; i7 < length; i7++) {
            C2492C c2492cA = c2492c.a(f0VarArr[i7].l(iB));
            interfaceC2490AArr[i7] = abstractC2498aArr[i7].b(c2492cA, eVar, j3 - this.f22689q[iB][i7]);
            ((List) this.l.get(i7)).add(new L(c2492cA, interfaceC2490AArr[i7]));
        }
        return new K(this.o, this.f22689q[iB], interfaceC2490AArr);
    }

    @Override // u2.AbstractC2498a
    public final e2.F h() {
        AbstractC2498a[] abstractC2498aArr = this.f22685k;
        return abstractC2498aArr.length > 0 ? abstractC2498aArr[0].h() : f22684s;
    }

    @Override // u2.AbstractC2507j, u2.AbstractC2498a
    public final void j() throws E5.s {
        E5.s sVar = this.f22690r;
        if (sVar != null) {
            throw sVar;
        }
        super.j();
    }

    @Override // u2.AbstractC2507j, u2.AbstractC2498a
    public final void l(k2.E e10) {
        super.l(e10);
        int i7 = 0;
        while (true) {
            AbstractC2498a[] abstractC2498aArr = this.f22685k;
            if (i7 >= abstractC2498aArr.length) {
                return;
            }
            x(Integer.valueOf(i7), abstractC2498aArr[i7]);
            i7++;
        }
    }

    @Override // u2.AbstractC2498a
    public final void n(InterfaceC2490A interfaceC2490A) {
        K k10 = (K) interfaceC2490A;
        int i7 = 0;
        while (true) {
            AbstractC2498a[] abstractC2498aArr = this.f22685k;
            if (i7 >= abstractC2498aArr.length) {
                return;
            }
            List list = (List) this.l.get(i7);
            int i10 = 0;
            while (true) {
                if (i10 >= list.size()) {
                    break;
                }
                if (((L) list.get(i10)).f22683b.equals(interfaceC2490A)) {
                    list.remove(i10);
                    break;
                }
                i10++;
            }
            AbstractC2498a abstractC2498a = abstractC2498aArr[i7];
            InterfaceC2490A interfaceC2490A2 = k10.l[i7];
            if (interfaceC2490A2 instanceof m0) {
                interfaceC2490A2 = ((m0) interfaceC2490A2).l;
            }
            abstractC2498a.n(interfaceC2490A2);
            i7++;
        }
    }

    @Override // u2.AbstractC2507j, u2.AbstractC2498a
    public final void p() {
        super.p();
        Arrays.fill(this.f22686m, (Object) null);
        this.f22688p = -1;
        this.f22690r = null;
        ArrayList arrayList = this.f22687n;
        arrayList.clear();
        Collections.addAll(arrayList, this.f22685k);
    }

    @Override // u2.AbstractC2498a
    public final void s(e2.F f10) {
        this.f22685k[0].s(f10);
    }

    @Override // u2.AbstractC2507j
    public final C2492C t(Object obj, C2492C c2492c) {
        ArrayList arrayList = this.l;
        List list = (List) arrayList.get(((Integer) obj).intValue());
        for (int i7 = 0; i7 < list.size(); i7++) {
            if (((L) list.get(i7)).f22682a.equals(c2492c)) {
                return ((L) ((List) arrayList.get(0)).get(i7)).f22682a;
            }
        }
        return null;
    }

    @Override // u2.AbstractC2507j
    public final void w(Object obj, AbstractC2498a abstractC2498a, e2.f0 f0Var) {
        Integer num = (Integer) obj;
        if (this.f22690r != null) {
            return;
        }
        if (this.f22688p == -1) {
            this.f22688p = f0Var.h();
        } else if (f0Var.h() != this.f22688p) {
            this.f22690r = new E5.s();
            return;
        }
        int length = this.f22689q.length;
        e2.f0[] f0VarArr = this.f22686m;
        if (length == 0) {
            this.f22689q = (long[][]) Array.newInstance((Class<?>) Long.TYPE, this.f22688p, f0VarArr.length);
        }
        ArrayList arrayList = this.f22687n;
        arrayList.remove(abstractC2498a);
        f0VarArr[num.intValue()] = f0Var;
        if (arrayList.isEmpty()) {
            m(f0VarArr[0]);
        }
    }
}
