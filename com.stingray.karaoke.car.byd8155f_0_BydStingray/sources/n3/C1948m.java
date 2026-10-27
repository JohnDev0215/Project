package n3;

import V8.AbstractC0828q;
import V8.f0;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import n7.AbstractC1985B;
import n7.AbstractC1998m;
import n7.C1995j;

/* JADX INFO: renamed from: n3.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1948m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ReentrantLock f19878a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f0 f19879b;
    public final f0 c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final V8.N f19880e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final V8.N f19881f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final O f19882g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ C1930E f19883h;

    public C1948m(C1930E c1930e, O o) {
        A7.m.f(o, "navigator");
        this.f19883h = c1930e;
        this.f19878a = new ReentrantLock(true);
        f0 f0VarC = AbstractC0828q.c(n7.u.l);
        this.f19879b = f0VarC;
        f0 f0VarC2 = AbstractC0828q.c(n7.w.l);
        this.c = f0VarC2;
        this.f19880e = new V8.N(f0VarC);
        this.f19881f = new V8.N(f0VarC2);
        this.f19882g = o;
    }

    public final void a(C1945j c1945j) {
        A7.m.f(c1945j, "backStackEntry");
        ReentrantLock reentrantLock = this.f19878a;
        reentrantLock.lock();
        try {
            f0 f0Var = this.f19879b;
            ArrayList arrayListO0 = AbstractC1998m.o0((Collection) f0Var.getValue(), c1945j);
            f0Var.getClass();
            f0Var.m(null, arrayListO0);
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x007d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(n3.C1945j r7) {
        /*
            r6 = this;
            java.lang.String r0 = "entry"
            A7.m.f(r7, r0)
            n3.E r0 = r6.f19883h
            java.util.LinkedHashMap r1 = r0.f19821z
            java.lang.Object r2 = r1.get(r7)
            java.lang.Boolean r3 = java.lang.Boolean.TRUE
            boolean r2 = A7.m.a(r2, r3)
            V8.f0 r3 = r6.c
            java.lang.Object r4 = r3.getValue()
            java.util.Set r4 = (java.util.Set) r4
            java.util.LinkedHashSet r4 = n7.AbstractC1985B.p(r4, r7)
            r5 = 0
            r3.m(r5, r4)
            r1.remove(r7)
            n7.j r1 = r0.f19804g
            boolean r3 = r1.contains(r7)
            V8.f0 r4 = r0.f19806i
            if (r3 != 0) goto L8e
            r0.w(r7)
            androidx.lifecycle.C r6 = r7.f19870s
            androidx.lifecycle.t r6 = r6.f11908p
            androidx.lifecycle.t r3 = androidx.lifecycle.EnumC0981t.f12006n
            int r6 = r6.compareTo(r3)
            if (r6 < 0) goto L44
            androidx.lifecycle.t r6 = androidx.lifecycle.EnumC0981t.l
            r7.h(r6)
        L44:
            java.lang.String r6 = r7.f19868q
            if (r1 == 0) goto L4f
            boolean r7 = r1.isEmpty()
            if (r7 == 0) goto L4f
            goto L68
        L4f:
            java.util.Iterator r7 = r1.iterator()
        L53:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto L68
            java.lang.Object r1 = r7.next()
            n3.j r1 = (n3.C1945j) r1
            java.lang.String r1 = r1.f19868q
            boolean r1 = A7.m.a(r1, r6)
            if (r1 == 0) goto L53
            goto L80
        L68:
            if (r2 != 0) goto L80
            n3.r r7 = r0.f19811p
            if (r7 == 0) goto L80
            java.lang.String r1 = "backStackEntryId"
            A7.m.f(r6, r1)
            java.util.LinkedHashMap r7 = r7.f19890b
            java.lang.Object r6 = r7.remove(r6)
            androidx.lifecycle.k0 r6 = (androidx.lifecycle.k0) r6
            if (r6 == 0) goto L80
            r6.a()
        L80:
            r0.x()
            java.util.ArrayList r6 = r0.u()
            r4.getClass()
            r4.m(r5, r6)
            goto Lab
        L8e:
            boolean r6 = r6.d
            if (r6 != 0) goto Lab
            r0.x()
            java.util.ArrayList r6 = n7.AbstractC1998m.A0(r1)
            V8.f0 r7 = r0.f19805h
            r7.getClass()
            r7.m(r5, r6)
            java.util.ArrayList r6 = r0.u()
            r4.getClass()
            r4.m(r5, r6)
        Lab:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: n3.C1948m.b(n3.j):void");
    }

    public final void c(C1945j c1945j, boolean z6) {
        A7.m.f(c1945j, "popUpTo");
        C1930E c1930e = this.f19883h;
        O oB = c1930e.f19817v.b(c1945j.f19865m.l);
        c1930e.f19821z.put(c1945j, Boolean.valueOf(z6));
        if (!oB.equals(this.f19882g)) {
            Object obj = c1930e.f19818w.get(oB);
            A7.m.c(obj);
            ((C1948m) obj).c(c1945j, z6);
            return;
        }
        C1950o c1950o = c1930e.f19820y;
        if (c1950o != null) {
            c1950o.b(c1945j);
            d(c1945j);
            return;
        }
        C1995j c1995j = c1930e.f19804g;
        int iIndexOf = c1995j.indexOf(c1945j);
        if (iIndexOf < 0) {
            Log.i("NavController", "Ignoring pop of " + c1945j + " as it was not found on the current back stack");
            return;
        }
        int i7 = iIndexOf + 1;
        if (i7 != c1995j.f20158n) {
            c1930e.r(((C1945j) c1995j.get(i7)).f19865m.f19917q, true, false);
        }
        C1930E.t(c1930e, c1945j);
        d(c1945j);
        c1930e.y();
        c1930e.c();
    }

    public final void d(C1945j c1945j) {
        A7.m.f(c1945j, "popUpTo");
        ReentrantLock reentrantLock = this.f19878a;
        reentrantLock.lock();
        try {
            f0 f0Var = this.f19879b;
            Iterable iterable = (Iterable) f0Var.getValue();
            ArrayList arrayList = new ArrayList();
            for (Object obj : iterable) {
                if (A7.m.a((C1945j) obj, c1945j)) {
                    break;
                } else {
                    arrayList.add(obj);
                }
            }
            f0Var.getClass();
            f0Var.m(null, arrayList);
            reentrantLock.unlock();
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    public final void e(C1945j c1945j, boolean z6) {
        Object objPrevious;
        A7.m.f(c1945j, "popUpTo");
        f0 f0Var = this.c;
        Iterable iterable = (Iterable) f0Var.getValue();
        boolean z10 = iterable instanceof Collection;
        V8.N n10 = this.f19880e;
        if (!z10 || !((Collection) iterable).isEmpty()) {
            Iterator it = iterable.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (((C1945j) it.next()) == c1945j) {
                    Iterable iterable2 = (Iterable) ((f0) n10.l).getValue();
                    if ((iterable2 instanceof Collection) && ((Collection) iterable2).isEmpty()) {
                        return;
                    }
                    Iterator it2 = iterable2.iterator();
                    while (it2.hasNext()) {
                        if (((C1945j) it2.next()) == c1945j) {
                        }
                    }
                    return;
                }
            }
        }
        f0Var.m(null, AbstractC1985B.r((Set) f0Var.getValue(), c1945j));
        List list = (List) ((f0) n10.l).getValue();
        ListIterator listIterator = list.listIterator(list.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                objPrevious = null;
                break;
            }
            objPrevious = listIterator.previous();
            C1945j c1945j2 = (C1945j) objPrevious;
            if (!A7.m.a(c1945j2, c1945j)) {
                V8.L l = n10.l;
                if (((List) ((f0) l).getValue()).lastIndexOf(c1945j2) < ((List) ((f0) l).getValue()).lastIndexOf(c1945j)) {
                    break;
                }
            }
        }
        C1945j c1945j3 = (C1945j) objPrevious;
        if (c1945j3 != null) {
            f0Var.m(null, AbstractC1985B.r((Set) f0Var.getValue(), c1945j3));
        }
        c(c1945j, z6);
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [A7.o, z7.k] */
    public final void f(C1945j c1945j) {
        A7.m.f(c1945j, "backStackEntry");
        C1930E c1930e = this.f19883h;
        O oB = c1930e.f19817v.b(c1945j.f19865m.l);
        if (!oB.equals(this.f19882g)) {
            Object obj = c1930e.f19818w.get(oB);
            if (obj == null) {
                throw new IllegalStateException(android.hardware.bydauto.ac.tsimpl.a.l(new StringBuilder("NavigatorBackStack for "), c1945j.f19865m.l, " should already be created").toString());
            }
            ((C1948m) obj).f(c1945j);
            return;
        }
        ?? r02 = c1930e.f19819x;
        if (r02 != 0) {
            r02.b(c1945j);
            a(c1945j);
        } else {
            Log.i("NavController", "Ignoring add of destination " + c1945j.f19865m + " outside of the call to navigate(). ");
        }
    }
}
