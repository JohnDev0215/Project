package n3;

import K.p0;
import U8.EnumC0810a;
import V8.AbstractC0828q;
import V8.S;
import V8.f0;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import androidx.lifecycle.EnumC0981t;
import androidx.lifecycle.k0;
import e0.C1183w;
import h2.AbstractC1347d;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import n7.AbstractC2004s;
import n7.C1995j;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: n3.E, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1930E {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public int f19797A;
    public final ArrayList B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final S f19798C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final V8.M f19799D;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f19800a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Activity f19801b;
    public C1927B c;
    public Bundle d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Parcelable[] f19802e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f19803f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final C1995j f19804g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final f0 f19805h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final f0 f19806i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final V8.N f19807j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final LinkedHashMap f19808k;
    public final LinkedHashMap l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final LinkedHashMap f19809m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final LinkedHashMap f19810n;
    public androidx.lifecycle.A o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public r f19811p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final CopyOnWriteArrayList f19812q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public EnumC0981t f19813r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final C1947l f19814s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final X1.h f19815t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final boolean f19816u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final P f19817v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final LinkedHashMap f19818w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public A7.o f19819x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public C1950o f19820y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final LinkedHashMap f19821z;

    public C1930E(Context context) {
        Object next;
        A7.m.f(context, "context");
        this.f19800a = context;
        Iterator it = P8.m.d0(context, C1937b.o).iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (((Context) next) instanceof Activity) {
                    break;
                }
            }
        }
        this.f19801b = (Activity) next;
        this.f19804g = new C1995j();
        n7.u uVar = n7.u.l;
        this.f19805h = AbstractC0828q.c(uVar);
        f0 f0VarC = AbstractC0828q.c(uVar);
        this.f19806i = f0VarC;
        this.f19807j = new V8.N(f0VarC);
        this.f19808k = new LinkedHashMap();
        this.l = new LinkedHashMap();
        this.f19809m = new LinkedHashMap();
        this.f19810n = new LinkedHashMap();
        this.f19812q = new CopyOnWriteArrayList();
        this.f19813r = EnumC0981t.f12005m;
        this.f19814s = new C1947l(this, 0);
        this.f19815t = new X1.h(this, 2);
        this.f19816u = true;
        P p10 = new P();
        this.f19817v = p10;
        this.f19818w = new LinkedHashMap();
        this.f19821z = new LinkedHashMap();
        p10.a(new C1929D(p10));
        p10.a(new C1938c(this.f19800a));
        this.B = new ArrayList();
        h6.r.o(new C1183w(this, 5));
        S sB = AbstractC0828q.b(1, 0, EnumC0810a.f9223m, 2);
        this.f19798C = sB;
        this.f19799D = new V8.M(sB);
    }

    public static y f(y yVar, int i7, boolean z6, y yVar2) {
        C1927B c1927b;
        if (yVar.f19917q == i7 && (yVar2 == null || (yVar.equals(yVar2) && A7.m.a(yVar.f19914m, yVar2.f19914m)))) {
            return yVar;
        }
        if (yVar instanceof C1927B) {
            c1927b = (C1927B) yVar;
        } else {
            c1927b = yVar.f19914m;
            A7.m.c(c1927b);
        }
        return c1927b.s(i7, c1927b, z6, yVar2);
    }

    public static void p(C1930E c1930e, String str, C1933H c1933h, int i7) {
        if ((i7 & 2) != 0) {
            c1933h = null;
        }
        c1930e.getClass();
        A7.m.f(str, "route");
        if (c1930e.c == null) {
            throw new IllegalArgumentException(("Cannot navigate to " + str + ". Navigation graph has not been set for NavController " + c1930e + '.').toString());
        }
        C1927B c1927bL = c1930e.l(c1930e.f19804g);
        w wVarU = c1927bL.u(str, true, c1927bL);
        if (wVarU == null) {
            StringBuilder sbJ = AbstractC1347d.j("Navigation destination that matches route ", str, " cannot be found in the navigation graph ");
            sbJ.append(c1930e.c);
            throw new IllegalArgumentException(sbJ.toString());
        }
        y yVar = wVarU.l;
        Bundle bundleD = yVar.d(wVarU.f19908m);
        if (bundleD == null) {
            bundleD = new Bundle();
        }
        Intent intent = new Intent();
        int i10 = y.f19913t;
        String str2 = yVar.f19918r;
        Uri uri = Uri.parse(str2 != null ? "android-app://androidx.navigation/".concat(str2) : "");
        A7.m.b(uri);
        intent.setDataAndType(uri, null);
        intent.setAction(null);
        bundleD.putParcelable("android-support-nav:controller:deepLinkIntent", intent);
        c1930e.o(yVar, bundleD, c1933h);
    }

    public static /* synthetic */ void t(C1930E c1930e, C1945j c1945j) {
        c1930e.s(c1945j, false, new C1995j());
    }

    public final void a(y yVar, Bundle bundle, C1945j c1945j, List list) {
        Object objPrevious;
        Object objPrevious2;
        y yVar2 = c1945j.f19865m;
        boolean z6 = yVar2 instanceof InterfaceC1939d;
        C1995j c1995j = this.f19804g;
        if (!z6) {
            while (!c1995j.isEmpty() && (((C1945j) c1995j.last()).f19865m instanceof InterfaceC1939d) && r(((C1945j) c1995j.last()).f19865m.f19917q, true, false)) {
            }
        }
        C1995j<C1945j> c1995j2 = new C1995j();
        boolean z10 = yVar instanceof C1927B;
        Context context = this.f19800a;
        Object obj = null;
        if (z10) {
            y yVar3 = yVar2;
            do {
                A7.m.c(yVar3);
                yVar3 = yVar3.f19914m;
                if (yVar3 != null) {
                    ListIterator listIterator = list.listIterator(list.size());
                    while (true) {
                        if (listIterator.hasPrevious()) {
                            objPrevious2 = listIterator.previous();
                            if (A7.m.a(((C1945j) objPrevious2).f19865m, yVar3)) {
                                break;
                            }
                        } else {
                            objPrevious2 = null;
                            break;
                        }
                    }
                    C1945j c1945jK = (C1945j) objPrevious2;
                    if (c1945jK == null) {
                        c1945jK = l9.n.K(context, yVar3, bundle, k(), this.f19811p);
                    }
                    c1995j2.addFirst(c1945jK);
                    if (!c1995j.isEmpty() && ((C1945j) c1995j.last()).f19865m == yVar3) {
                        t(this, (C1945j) c1995j.last());
                    }
                }
                if (yVar3 == null) {
                    break;
                }
            } while (yVar3 != yVar);
        }
        y yVar4 = c1995j2.isEmpty() ? yVar2 : ((C1945j) c1995j2.first()).f19865m;
        while (yVar4 != null && e(yVar4.f19917q, yVar4) != yVar4) {
            yVar4 = yVar4.f19914m;
            if (yVar4 != null) {
                Bundle bundle2 = (bundle == null || !bundle.isEmpty()) ? bundle : null;
                ListIterator listIterator2 = list.listIterator(list.size());
                while (true) {
                    if (listIterator2.hasPrevious()) {
                        objPrevious = listIterator2.previous();
                        if (A7.m.a(((C1945j) objPrevious).f19865m, yVar4)) {
                            break;
                        }
                    } else {
                        objPrevious = null;
                        break;
                    }
                }
                C1945j c1945jK2 = (C1945j) objPrevious;
                if (c1945jK2 == null) {
                    c1945jK2 = l9.n.K(context, yVar4, yVar4.d(bundle2), k(), this.f19811p);
                }
                c1995j2.addFirst(c1945jK2);
            }
        }
        if (!c1995j2.isEmpty()) {
            yVar2 = ((C1945j) c1995j2.first()).f19865m;
        }
        while (!c1995j.isEmpty() && (((C1945j) c1995j.last()).f19865m instanceof C1927B)) {
            y yVar5 = ((C1945j) c1995j.last()).f19865m;
            A7.m.d(yVar5, "null cannot be cast to non-null type androidx.navigation.NavGraph");
            if (((C1927B) yVar5).f19790u.c(yVar2.f19917q) != null) {
                break;
            } else {
                t(this, (C1945j) c1995j.last());
            }
        }
        C1945j c1945j2 = (C1945j) (c1995j.isEmpty() ? null : c1995j.f20157m[c1995j.l]);
        if (c1945j2 == null) {
            c1945j2 = (C1945j) (c1995j2.isEmpty() ? null : c1995j2.f20157m[c1995j2.l]);
        }
        if (!A7.m.a(c1945j2 != null ? c1945j2.f19865m : null, this.c)) {
            ListIterator listIterator3 = list.listIterator(list.size());
            while (true) {
                if (!listIterator3.hasPrevious()) {
                    break;
                }
                Object objPrevious3 = listIterator3.previous();
                y yVar6 = ((C1945j) objPrevious3).f19865m;
                C1927B c1927b = this.c;
                A7.m.c(c1927b);
                if (A7.m.a(yVar6, c1927b)) {
                    obj = objPrevious3;
                    break;
                }
            }
            C1945j c1945jK3 = (C1945j) obj;
            if (c1945jK3 == null) {
                C1927B c1927b2 = this.c;
                A7.m.c(c1927b2);
                C1927B c1927b3 = this.c;
                A7.m.c(c1927b3);
                c1945jK3 = l9.n.K(context, c1927b2, c1927b3.d(bundle), k(), this.f19811p);
            }
            c1995j2.addFirst(c1945jK3);
        }
        for (C1945j c1945j3 : c1995j2) {
            Object obj2 = this.f19818w.get(this.f19817v.b(c1945j3.f19865m.l));
            if (obj2 == null) {
                throw new IllegalStateException(android.hardware.bydauto.ac.tsimpl.a.l(new StringBuilder("NavigatorBackStack for "), yVar.l, " should already be created").toString());
            }
            ((C1948m) obj2).a(c1945j3);
        }
        c1995j.addAll(c1995j2);
        c1995j.addLast(c1945j);
        for (C1945j c1945j4 : AbstractC1998m.o0(c1995j2, c1945j)) {
            C1927B c1927b4 = c1945j4.f19865m.f19914m;
            if (c1927b4 != null) {
                m(c1945j4, g(c1927b4.f19917q));
            }
        }
    }

    public final void b(InterfaceC1949n interfaceC1949n) {
        this.f19812q.add(interfaceC1949n);
        C1995j c1995j = this.f19804g;
        if (c1995j.isEmpty()) {
            return;
        }
        C1945j c1945j = (C1945j) c1995j.last();
        interfaceC1949n.a(this, c1945j.f19865m, c1945j.c());
    }

    public final boolean c() {
        C1995j c1995j;
        while (true) {
            c1995j = this.f19804g;
            if (c1995j.isEmpty() || !(((C1945j) c1995j.last()).f19865m instanceof C1927B)) {
                break;
            }
            t(this, (C1945j) c1995j.last());
        }
        C1945j c1945j = (C1945j) c1995j.t();
        ArrayList arrayList = this.B;
        if (c1945j != null) {
            arrayList.add(c1945j);
        }
        this.f19797A++;
        x();
        int i7 = this.f19797A - 1;
        this.f19797A = i7;
        if (i7 == 0) {
            ArrayList<C1945j> arrayListA0 = AbstractC1998m.A0(arrayList);
            arrayList.clear();
            for (C1945j c1945j2 : arrayListA0) {
                Iterator it = this.f19812q.iterator();
                while (it.hasNext()) {
                    ((InterfaceC1949n) it.next()).a(this, c1945j2.f19865m, c1945j2.c());
                }
                this.f19798C.j(c1945j2);
            }
            ArrayList arrayListA02 = AbstractC1998m.A0(c1995j);
            f0 f0Var = this.f19805h;
            f0Var.getClass();
            f0Var.m(null, arrayListA02);
            ArrayList arrayListU = u();
            f0 f0Var2 = this.f19806i;
            f0Var2.getClass();
            f0Var2.m(null, arrayListU);
        }
        return c1945j != null;
    }

    public final boolean d(ArrayList arrayList, y yVar, boolean z6, boolean z10) {
        String str;
        A7.v vVar = new A7.v();
        C1995j c1995j = new C1995j();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            O o = (O) it.next();
            A7.v vVar2 = new A7.v();
            C1945j c1945j = (C1945j) this.f19804g.last();
            this.f19820y = new C1950o(vVar2, vVar, this, z10, c1995j);
            o.e(c1945j, z10);
            this.f19820y = null;
            if (!vVar2.l) {
                break;
            }
        }
        if (z10) {
            LinkedHashMap linkedHashMap = this.f19809m;
            if (!z6) {
                P8.f fVar = new P8.f(new P8.j(P8.m.d0(yVar, C1937b.f19848q), new C1951p(this, 0)));
                while (fVar.hasNext()) {
                    Integer numValueOf = Integer.valueOf(((y) fVar.next()).f19917q);
                    C1946k c1946k = (C1946k) (c1995j.isEmpty() ? null : c1995j.f20157m[c1995j.l]);
                    linkedHashMap.put(numValueOf, c1946k != null ? c1946k.l : null);
                }
            }
            if (!c1995j.isEmpty()) {
                C1946k c1946k2 = (C1946k) c1995j.first();
                P8.f fVar2 = new P8.f(new P8.j(P8.m.d0(e(c1946k2.f19875m, null), C1937b.f19849r), new C1951p(this, 1)));
                while (true) {
                    boolean zHasNext = fVar2.hasNext();
                    str = c1946k2.l;
                    if (!zHasNext) {
                        break;
                    }
                    linkedHashMap.put(Integer.valueOf(((y) fVar2.next()).f19917q), str);
                }
                if (linkedHashMap.values().contains(str)) {
                    this.f19810n.put(str, c1995j);
                }
            }
        }
        y();
        return vVar.l;
    }

    public final y e(int i7, y yVar) {
        y yVar2;
        C1927B c1927b = this.c;
        if (c1927b == null) {
            return null;
        }
        if (c1927b.f19917q == i7) {
            if (yVar == null) {
                return c1927b;
            }
            if (A7.m.a(c1927b, yVar) && yVar.f19914m == null) {
                return this.c;
            }
        }
        C1945j c1945j = (C1945j) this.f19804g.t();
        if (c1945j == null || (yVar2 = c1945j.f19865m) == null) {
            yVar2 = this.c;
            A7.m.c(yVar2);
        }
        return f(yVar2, i7, false, yVar);
    }

    public final C1945j g(int i7) {
        Object objPrevious;
        C1995j c1995j = this.f19804g;
        ListIterator<E> listIterator = c1995j.listIterator(c1995j.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                objPrevious = null;
                break;
            }
            objPrevious = listIterator.previous();
            if (((C1945j) objPrevious).f19865m.f19917q == i7) {
                break;
            }
        }
        C1945j c1945j = (C1945j) objPrevious;
        if (c1945j != null) {
            return c1945j;
        }
        StringBuilder sbJ = M5.b.j("No destination with ID ", i7, " is on the NavController's back stack. The current destination is ");
        sbJ.append(i());
        throw new IllegalArgumentException(sbJ.toString().toString());
    }

    public final C1945j h() {
        return (C1945j) this.f19804g.t();
    }

    public final y i() {
        C1945j c1945jH = h();
        if (c1945jH != null) {
            return c1945jH.f19865m;
        }
        return null;
    }

    public final C1927B j() {
        C1927B c1927b = this.c;
        if (c1927b == null) {
            throw new IllegalStateException("You must call setGraph() before calling getGraph()");
        }
        A7.m.d(c1927b, "null cannot be cast to non-null type androidx.navigation.NavGraph");
        return c1927b;
    }

    public final EnumC0981t k() {
        return this.o == null ? EnumC0981t.f12006n : this.f19813r;
    }

    public final C1927B l(C1995j c1995j) {
        y yVar;
        C1945j c1945j = (C1945j) c1995j.t();
        if (c1945j == null || (yVar = c1945j.f19865m) == null) {
            yVar = this.c;
            A7.m.c(yVar);
        }
        if (yVar instanceof C1927B) {
            return (C1927B) yVar;
        }
        C1927B c1927b = yVar.f19914m;
        A7.m.c(c1927b);
        return c1927b;
    }

    public final void m(C1945j c1945j, C1945j c1945j2) {
        this.f19808k.put(c1945j, c1945j2);
        LinkedHashMap linkedHashMap = this.l;
        if (linkedHashMap.get(c1945j2) == null) {
            linkedHashMap.put(c1945j2, new AtomicInteger(0));
        }
        Object obj = linkedHashMap.get(c1945j2);
        A7.m.c(obj);
        ((AtomicInteger) obj).incrementAndGet();
    }

    public final void n(String str, InterfaceC2782k interfaceC2782k) {
        A7.m.f(str, "route");
        p(this, str, h6.i.x(interfaceC2782k), 4);
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01f8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void o(n3.y r29, android.os.Bundle r30, n3.C1933H r31) {
        /*
            Method dump skipped, instruction units count: 592
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: n3.C1930E.o(n3.y, android.os.Bundle, n3.H):void");
    }

    public final void q() {
        if (this.f19804g.isEmpty()) {
            return;
        }
        y yVarI = i();
        A7.m.c(yVarI);
        if (r(yVarI.f19917q, true, false)) {
            c();
        }
    }

    public final boolean r(int i7, boolean z6, boolean z10) {
        y yVar;
        C1995j c1995j = this.f19804g;
        if (c1995j.isEmpty()) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = AbstractC1998m.p0(c1995j).iterator();
        while (true) {
            if (!it.hasNext()) {
                yVar = null;
                break;
            }
            yVar = ((C1945j) it.next()).f19865m;
            O oB = this.f19817v.b(yVar.l);
            if (z6 || yVar.f19917q != i7) {
                arrayList.add(oB);
            }
            if (yVar.f19917q == i7) {
                break;
            }
        }
        if (yVar != null) {
            return d(arrayList, yVar, z6, z10);
        }
        int i10 = y.f19913t;
        Log.i("NavController", "Ignoring popBackStack to destination " + h6.f.j(this.f19800a, i7) + " as it was not found on the current back stack");
        return false;
    }

    public final void s(C1945j c1945j, boolean z6, C1995j c1995j) {
        r rVar;
        V8.N n10;
        Set set;
        C1995j c1995j2 = this.f19804g;
        C1945j c1945j2 = (C1945j) c1995j2.last();
        if (!A7.m.a(c1945j2, c1945j)) {
            throw new IllegalStateException(("Attempted to pop " + c1945j.f19865m + ", which is not the top of the back stack (" + c1945j2.f19865m + ')').toString());
        }
        AbstractC2004s.P(c1995j2);
        C1948m c1948m = (C1948m) this.f19818w.get(this.f19817v.b(c1945j2.f19865m.l));
        boolean z10 = true;
        if ((c1948m == null || (n10 = c1948m.f19881f) == null || (set = (Set) ((f0) n10.l).getValue()) == null || !set.contains(c1945j2)) && !this.l.containsKey(c1945j2)) {
            z10 = false;
        }
        EnumC0981t enumC0981t = c1945j2.f19870s.f11908p;
        EnumC0981t enumC0981t2 = EnumC0981t.f12006n;
        if (enumC0981t.compareTo(enumC0981t2) >= 0) {
            if (z6) {
                c1945j2.h(enumC0981t2);
                c1995j.addFirst(new C1946k(c1945j2));
            }
            if (z10) {
                c1945j2.h(enumC0981t2);
            } else {
                c1945j2.h(EnumC0981t.l);
                w(c1945j2);
            }
        }
        if (z6 || z10 || (rVar = this.f19811p) == null) {
            return;
        }
        String str = c1945j2.f19868q;
        A7.m.f(str, "backStackEntryId");
        k0 k0Var = (k0) rVar.f19890b.remove(str);
        if (k0Var != null) {
            k0Var.a();
        }
    }

    public final ArrayList u() {
        EnumC0981t enumC0981t;
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f19818w.values().iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            enumC0981t = EnumC0981t.o;
            if (!zHasNext) {
                break;
            }
            Iterable iterable = (Iterable) ((f0) ((C1948m) it.next()).f19881f.l).getValue();
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : iterable) {
                C1945j c1945j = (C1945j) obj;
                if (!arrayList.contains(c1945j) && c1945j.f19873v.compareTo(enumC0981t) < 0) {
                    arrayList2.add(obj);
                }
            }
            AbstractC2004s.L(arrayList, arrayList2);
        }
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : this.f19804g) {
            C1945j c1945j2 = (C1945j) obj2;
            if (!arrayList.contains(c1945j2) && c1945j2.f19873v.compareTo(enumC0981t) >= 0) {
                arrayList3.add(obj2);
            }
        }
        AbstractC2004s.L(arrayList, arrayList3);
        ArrayList arrayList4 = new ArrayList();
        for (Object obj3 : arrayList) {
            if (!(((C1945j) obj3).f19865m instanceof C1927B)) {
                arrayList4.add(obj3);
            }
        }
        return arrayList4;
    }

    public final boolean v(int i7, Bundle bundle, C1933H c1933h) {
        y yVarJ;
        C1945j c1945j;
        y yVar;
        LinkedHashMap linkedHashMap = this.f19809m;
        if (!linkedHashMap.containsKey(Integer.valueOf(i7))) {
            return false;
        }
        String str = (String) linkedHashMap.get(Integer.valueOf(i7));
        Collection collectionValues = linkedHashMap.values();
        A7.m.f(collectionValues, "<this>");
        Iterator it = collectionValues.iterator();
        while (it.hasNext()) {
            if (A7.m.a((String) it.next(), str)) {
                it.remove();
            }
        }
        C1995j<C1946k> c1995j = (C1995j) A7.E.c(this.f19810n).remove(str);
        ArrayList arrayList = new ArrayList();
        C1945j c1945j2 = (C1945j) this.f19804g.t();
        if (c1945j2 == null || (yVarJ = c1945j2.f19865m) == null) {
            yVarJ = j();
        }
        if (c1995j != null) {
            for (C1946k c1946k : c1995j) {
                y yVarF = f(yVarJ, c1946k.f19875m, true, null);
                Context context = this.f19800a;
                if (yVarF == null) {
                    int i10 = y.f19913t;
                    throw new IllegalStateException(("Restore State failed: destination " + h6.f.j(context, c1946k.f19875m) + " cannot be found from the current destination " + yVarJ).toString());
                }
                arrayList.add(c1946k.a(context, yVarF, k(), this.f19811p));
                yVarJ = yVarF;
            }
        }
        ArrayList<List> arrayList2 = new ArrayList();
        ArrayList<C1945j> arrayList3 = new ArrayList();
        for (Object obj : arrayList) {
            if (!(((C1945j) obj).f19865m instanceof C1927B)) {
                arrayList3.add(obj);
            }
        }
        for (C1945j c1945j3 : arrayList3) {
            List list = (List) AbstractC1998m.i0(arrayList2);
            if (A7.m.a((list == null || (c1945j = (C1945j) AbstractC1998m.h0(list)) == null || (yVar = c1945j.f19865m) == null) ? null : yVar.l, c1945j3.f19865m.l)) {
                list.add(c1945j3);
            } else {
                arrayList2.add(AbstractC1999n.F(c1945j3));
            }
        }
        A7.v vVar = new A7.v();
        for (List list2 : arrayList2) {
            O oB = this.f19817v.b(((C1945j) AbstractC1998m.Y(list2)).f19865m.l);
            this.f19819x = new p0(vVar, arrayList, new A7.x(), this, bundle, 3);
            oB.d(list2, c1933h);
            this.f19819x = null;
        }
        return vVar.l;
    }

    public final void w(C1945j c1945j) {
        A7.m.f(c1945j, "child");
        C1945j c1945j2 = (C1945j) this.f19808k.remove(c1945j);
        if (c1945j2 == null) {
            return;
        }
        LinkedHashMap linkedHashMap = this.l;
        AtomicInteger atomicInteger = (AtomicInteger) linkedHashMap.get(c1945j2);
        Integer numValueOf = atomicInteger != null ? Integer.valueOf(atomicInteger.decrementAndGet()) : null;
        if (numValueOf != null && numValueOf.intValue() == 0) {
            C1948m c1948m = (C1948m) this.f19818w.get(this.f19817v.b(c1945j2.f19865m.l));
            if (c1948m != null) {
                c1948m.b(c1945j2);
            }
            linkedHashMap.remove(c1945j2);
        }
    }

    public final void x() {
        AtomicInteger atomicInteger;
        V8.N n10;
        Set set;
        ArrayList<C1945j> arrayListA0 = AbstractC1998m.A0(this.f19804g);
        if (arrayListA0.isEmpty()) {
            return;
        }
        y yVar = ((C1945j) AbstractC1998m.h0(arrayListA0)).f19865m;
        ArrayList arrayList = new ArrayList();
        if (yVar instanceof InterfaceC1939d) {
            Iterator it = AbstractC1998m.p0(arrayListA0).iterator();
            while (it.hasNext()) {
                y yVar2 = ((C1945j) it.next()).f19865m;
                arrayList.add(yVar2);
                if (!(yVar2 instanceof InterfaceC1939d) && !(yVar2 instanceof C1927B)) {
                    break;
                }
            }
        }
        HashMap map = new HashMap();
        for (C1945j c1945j : AbstractC1998m.p0(arrayListA0)) {
            EnumC0981t enumC0981t = c1945j.f19873v;
            y yVar3 = c1945j.f19865m;
            EnumC0981t enumC0981t2 = EnumC0981t.f12007p;
            EnumC0981t enumC0981t3 = EnumC0981t.o;
            if (yVar != null && yVar3.f19917q == yVar.f19917q) {
                if (enumC0981t != enumC0981t2) {
                    C1948m c1948m = (C1948m) this.f19818w.get(this.f19817v.b(yVar3.l));
                    if (A7.m.a((c1948m == null || (n10 = c1948m.f19881f) == null || (set = (Set) ((f0) n10.l).getValue()) == null) ? null : Boolean.valueOf(set.contains(c1945j)), Boolean.TRUE) || ((atomicInteger = (AtomicInteger) this.l.get(c1945j)) != null && atomicInteger.get() == 0)) {
                        map.put(c1945j, enumC0981t3);
                    } else {
                        map.put(c1945j, enumC0981t2);
                    }
                }
                y yVar4 = (y) AbstractC1998m.a0(arrayList);
                if (yVar4 != null && yVar4.f19917q == yVar3.f19917q) {
                    AbstractC2004s.N(arrayList);
                }
                yVar = yVar.f19914m;
            } else if (arrayList.isEmpty() || yVar3.f19917q != ((y) AbstractC1998m.Y(arrayList)).f19917q) {
                c1945j.h(EnumC0981t.f12006n);
            } else {
                y yVar5 = (y) AbstractC2004s.N(arrayList);
                if (enumC0981t == enumC0981t2) {
                    c1945j.h(enumC0981t3);
                } else if (enumC0981t != enumC0981t3) {
                    map.put(c1945j, enumC0981t3);
                }
                C1927B c1927b = yVar5.f19914m;
                if (c1927b != null && !arrayList.contains(c1927b)) {
                    arrayList.add(c1927b);
                }
            }
        }
        for (C1945j c1945j2 : arrayListA0) {
            EnumC0981t enumC0981t4 = (EnumC0981t) map.get(c1945j2);
            if (enumC0981t4 != null) {
                c1945j2.h(enumC0981t4);
            } else {
                c1945j2.i();
            }
        }
    }

    /* JADX WARN: Type inference failed for: r4v2, types: [A7.j, z7.a] */
    public final void y() {
        int i7;
        boolean z6 = false;
        if (this.f19816u) {
            C1995j c1995j = this.f19804g;
            if (c1995j == null || !c1995j.isEmpty()) {
                Iterator it = c1995j.iterator();
                i7 = 0;
                while (it.hasNext()) {
                    if (!(((C1945j) it.next()).f19865m instanceof C1927B) && (i7 = i7 + 1) < 0) {
                        throw new ArithmeticException("Count overflow has happened.");
                    }
                }
            } else {
                i7 = 0;
            }
            if (i7 > 1) {
                z6 = true;
            }
        }
        X1.h hVar = this.f19815t;
        hVar.f15131a = z6;
        ?? r42 = hVar.c;
        if (r42 != 0) {
            r42.a();
        }
    }
}
