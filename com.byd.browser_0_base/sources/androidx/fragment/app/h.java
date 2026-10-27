package androidx.fragment.app;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.R$id;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.j;
import androidx.lifecycle.d;
import androidx.lifecycle.l;
import defpackage.C0175b;
import defpackage.b71;
import defpackage.bu3;
import defpackage.eq0;
import defpackage.gq2;
import defpackage.i21;
import defpackage.in3;
import defpackage.j91;
import defpackage.la0;
import defpackage.lp0;
import defpackage.mp0;
import defpackage.mw2;
import defpackage.np0;
import defpackage.nq2;
import defpackage.om3;
import defpackage.tp0;
import defpackage.up0;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: FragmentStateManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class h {
    public final g a;
    public final la0 b;
    public final Fragment c;
    public boolean d = false;
    public int e = -1;

    public h(g gVar, la0 la0Var, Fragment fragment) {
        this.a = gVar;
        this.b = la0Var;
        this.c = fragment;
    }

    public final void a() {
        Fragment fragment = this.c;
        Bundle bundle = fragment.f;
        if (bundle != null) {
            bundle.getBundle("savedInstanceState");
        }
        fragment.x.N();
        fragment.e = 3;
        fragment.H = false;
        fragment.Y();
        if (!fragment.H) {
            throw new mw2(C0175b.i("Fragment ", fragment, " did not call through to super.onActivityCreated()"));
        }
        if (fragment.f12J != null) {
            Bundle bundle2 = fragment.f;
            Bundle bundle3 = bundle2 != null ? bundle2.getBundle("savedInstanceState") : null;
            SparseArray<Parcelable> sparseArray = fragment.g;
            if (sparseArray != null) {
                fragment.f12J.restoreHierarchyState(sparseArray);
                fragment.g = null;
            }
            fragment.H = false;
            fragment.r0(bundle3);
            if (!fragment.H) {
                throw new mw2(C0175b.i("Fragment ", fragment, " did not call through to super.onViewStateRestored()"));
            }
            if (fragment.f12J != null) {
                fragment.T.a(d.a.ON_CREATE);
            }
        }
        fragment.f = null;
        lp0 lp0Var = fragment.x;
        lp0Var.F = false;
        lp0Var.G = false;
        lp0Var.M.h = false;
        lp0Var.r(4);
        this.a.a(false);
    }

    public final void b() {
        Fragment fragment;
        int iIndexOfChild;
        View view;
        View view2;
        Fragment fragment2 = this.c;
        View view3 = fragment2.I;
        while (true) {
            fragment = null;
            if (view3 == null) {
                break;
            }
            Object tag = view3.getTag(R$id.fragment_container_view_tag);
            Fragment fragment3 = tag instanceof Fragment ? (Fragment) tag : null;
            if (fragment3 != null) {
                fragment = fragment3;
                break;
            } else {
                Object parent = view3.getParent();
                view3 = parent instanceof View ? (View) parent : null;
            }
        }
        Fragment fragment4 = fragment2.y;
        if (fragment != null && !fragment.equals(fragment4)) {
            int i = fragment2.A;
            tp0.b bVar = tp0.a;
            bu3 bu3Var = new bu3(fragment2, fragment, i);
            tp0.b bVarA = tp0.a(fragment2);
            if (bVarA.a.contains(tp0.a.DETECT_WRONG_NESTED_HIERARCHY) && tp0.d(bVarA, fragment2.getClass(), bu3.class)) {
                tp0.b(bVarA, bu3Var);
            }
        }
        la0 la0Var = this.b;
        la0Var.getClass();
        ViewGroup viewGroup = fragment2.I;
        if (viewGroup == null) {
            iIndexOfChild = -1;
        } else {
            ArrayList arrayList = (ArrayList) la0Var.e;
            int iIndexOf = arrayList.indexOf(fragment2);
            int i2 = iIndexOf - 1;
            while (true) {
                if (i2 < 0) {
                    while (true) {
                        iIndexOf++;
                        if (iIndexOf >= arrayList.size()) {
                            break;
                        }
                        Fragment fragment5 = (Fragment) arrayList.get(iIndexOf);
                        if (fragment5.I == viewGroup && (view = fragment5.f12J) != null) {
                            iIndexOfChild = viewGroup.indexOfChild(view);
                            break;
                        }
                    }
                } else {
                    Fragment fragment6 = (Fragment) arrayList.get(i2);
                    if (fragment6.I == viewGroup && (view2 = fragment6.f12J) != null) {
                        iIndexOfChild = viewGroup.indexOfChild(view2) + 1;
                        break;
                    }
                    i2--;
                }
            }
            iIndexOfChild = -1;
        }
        fragment2.I.addView(fragment2.f12J, iIndexOfChild);
    }

    public final void c() {
        Fragment fragment = this.c;
        Fragment fragment2 = fragment.k;
        h hVar = null;
        la0 la0Var = this.b;
        if (fragment2 != null) {
            h hVar2 = (h) ((HashMap) la0Var.f).get(fragment2.i);
            if (hVar2 == null) {
                throw new IllegalStateException("Fragment " + fragment + " declared target fragment " + fragment.k + " that does not belong to this FragmentManager!");
            }
            fragment.l = fragment.k.i;
            fragment.k = null;
            hVar = hVar2;
        } else {
            String str = fragment.l;
            if (str != null && (hVar = (h) ((HashMap) la0Var.f).get(str)) == null) {
                StringBuilder sb = new StringBuilder("Fragment ");
                sb.append(fragment);
                sb.append(" declared target fragment ");
                throw new IllegalStateException(gq2.i(sb, fragment.l, " that does not belong to this FragmentManager!"));
            }
        }
        if (hVar != null) {
            hVar.k();
        }
        FragmentManager fragmentManager = fragment.v;
        fragment.w = fragmentManager.u;
        fragment.y = fragmentManager.w;
        g gVar = this.a;
        gVar.g(false);
        ArrayList<Fragment.e> arrayList = fragment.W;
        Iterator<Fragment.e> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        arrayList.clear();
        fragment.x.b(fragment.w, fragment.G(), fragment);
        fragment.e = 0;
        fragment.H = false;
        fragment.a0(fragment.w.l);
        if (!fragment.H) {
            throw new mw2(C0175b.i("Fragment ", fragment, " did not call through to super.onAttach()"));
        }
        Iterator<np0> it2 = fragment.v.n.iterator();
        while (it2.hasNext()) {
            it2.next().a(fragment);
        }
        lp0 lp0Var = fragment.x;
        lp0Var.F = false;
        lp0Var.G = false;
        lp0Var.M.h = false;
        lp0Var.r(0);
        gVar.b(false);
    }

    public final int d() {
        Object next;
        Fragment fragment = this.c;
        if (fragment.v == null) {
            return fragment.e;
        }
        int iMin = this.e;
        int iOrdinal = fragment.R.ordinal();
        if (iOrdinal == 1) {
            iMin = Math.min(iMin, 0);
        } else if (iOrdinal == 2) {
            iMin = Math.min(iMin, 1);
        } else if (iOrdinal == 3) {
            iMin = Math.min(iMin, 5);
        } else if (iOrdinal != 4) {
            iMin = Math.min(iMin, -1);
        }
        if (fragment.q) {
            if (fragment.r) {
                iMin = Math.max(this.e, 2);
                View view = fragment.f12J;
                if (view != null && view.getParent() == null) {
                    iMin = Math.min(iMin, 2);
                }
            } else {
                iMin = this.e < 4 ? Math.min(iMin, fragment.e) : Math.min(iMin, 1);
            }
        }
        if (!fragment.o) {
            iMin = Math.min(iMin, 1);
        }
        ViewGroup viewGroup = fragment.I;
        if (viewGroup != null) {
            j jVarF = j.f(viewGroup, fragment.M());
            jVarF.getClass();
            j.b bVarD = jVarF.d(fragment);
            int i = bVarD != null ? bVarD.b : 0;
            Iterator it = jVarF.c.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                j.b bVar = (j.b) next;
                if (i21.a(bVar.c, fragment) && !bVar.f) {
                    break;
                }
            }
            j.b bVar2 = (j.b) next;
            i = bVar2 != null ? bVar2.b : 0;
            int i2 = i == 0 ? -1 : j.c.a[gq2.m(i)];
            if (i2 != -1 && i2 != 1) {
                i = i;
            }
        }
        if (i == 2) {
            iMin = Math.min(iMin, 6);
        } else if (i == 3) {
            iMin = Math.max(iMin, 3);
        } else if (fragment.p) {
            iMin = fragment.X() ? Math.min(iMin, 1) : Math.min(iMin, -1);
        }
        return (!fragment.K || fragment.e >= 5) ? iMin : Math.min(iMin, 4);
    }

    public final void e() {
        final Fragment fragment = this.c;
        Bundle bundle = fragment.f;
        Bundle bundle2 = bundle != null ? bundle.getBundle("savedInstanceState") : null;
        if (fragment.P) {
            fragment.e = 1;
            fragment.z0();
            return;
        }
        g gVar = this.a;
        gVar.h(false);
        fragment.x.N();
        fragment.e = 1;
        fragment.H = false;
        fragment.S.a(new androidx.lifecycle.f() { // from class: androidx.fragment.app.Fragment.6
            public AnonymousClass6() {
            }

            @Override // androidx.lifecycle.f
            public final void a(b71 b71Var, d.a aVar) {
                View view;
                if (aVar != d.a.ON_STOP || (view = Fragment.this.f12J) == null) {
                    return;
                }
                view.cancelPendingInputEvents();
            }
        });
        fragment.c0(bundle2);
        fragment.P = true;
        if (!fragment.H) {
            throw new mw2(C0175b.i("Fragment ", fragment, " did not call through to super.onCreate()"));
        }
        fragment.S.e(d.a.ON_CREATE);
        gVar.c(false);
    }

    public final void f() {
        String resourceName;
        Fragment fragment = this.c;
        if (fragment.q) {
            return;
        }
        Bundle bundle = fragment.f;
        Bundle bundle2 = bundle != null ? bundle.getBundle("savedInstanceState") : null;
        LayoutInflater layoutInflaterU0 = fragment.u0(bundle2);
        ViewGroup viewGroup = fragment.I;
        if (viewGroup == null) {
            int i = fragment.A;
            if (i == 0) {
                viewGroup = null;
            } else {
                if (i == -1) {
                    throw new IllegalArgumentException(C0175b.i("Cannot create fragment ", fragment, " for a container view with no id"));
                }
                viewGroup = (ViewGroup) fragment.v.v.r(i);
                if (viewGroup == null) {
                    if (!fragment.s) {
                        try {
                            resourceName = fragment.N().getResourceName(fragment.A);
                        } catch (Resources.NotFoundException unused) {
                            resourceName = "unknown";
                        }
                        throw new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(fragment.A) + " (" + resourceName + ") for fragment " + fragment);
                    }
                } else if (!(viewGroup instanceof FragmentContainerView)) {
                    tp0.b bVar = tp0.a;
                    up0 up0Var = new up0(fragment, viewGroup, 1);
                    tp0.b bVarA = tp0.a(fragment);
                    if (bVarA.a.contains(tp0.a.DETECT_WRONG_FRAGMENT_CONTAINER) && tp0.d(bVarA, fragment.getClass(), up0.class)) {
                        tp0.b(bVarA, up0Var);
                    }
                }
            }
        }
        fragment.I = viewGroup;
        fragment.t0(layoutInflaterU0, viewGroup, bundle2);
        View view = fragment.f12J;
        if (view != null) {
            view.setSaveFromParentEnabled(false);
            fragment.f12J.setTag(R$id.fragment_container_view_tag, fragment);
            if (viewGroup != null) {
                b();
            }
            if (fragment.C) {
                fragment.f12J.setVisibility(8);
            }
            View view2 = fragment.f12J;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            if (om3.g.b(view2)) {
                om3.h.c(fragment.f12J);
            } else {
                View view3 = fragment.f12J;
                view3.addOnAttachStateChangeListener(new a(view3));
            }
            Bundle bundle3 = fragment.f;
            fragment.q0(fragment.f12J, bundle3 != null ? bundle3.getBundle("savedInstanceState") : null);
            fragment.x.r(2);
            this.a.m(fragment, fragment.f12J, false);
            int visibility = fragment.f12J.getVisibility();
            fragment.H().l = fragment.f12J.getAlpha();
            if (fragment.I != null && visibility == 0) {
                View viewFindFocus = fragment.f12J.findFocus();
                if (viewFindFocus != null) {
                    fragment.H().m = viewFindFocus;
                }
                fragment.f12J.setAlpha(0.0f);
            }
        }
        fragment.e = 2;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void g() {
        /*
            Method dump skipped, instruction units count: 219
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.h.g():void");
    }

    public final void h() {
        View view;
        Fragment fragment = this.c;
        ViewGroup viewGroup = fragment.I;
        if (viewGroup != null && (view = fragment.f12J) != null) {
            viewGroup.removeView(view);
        }
        fragment.x.r(1);
        if (fragment.f12J != null) {
            eq0 eq0Var = fragment.T;
            eq0Var.b();
            if (eq0Var.h.c.compareTo(d.b.CREATED) >= 0) {
                fragment.T.a(d.a.ON_DESTROY);
            }
        }
        fragment.e = 1;
        fragment.H = false;
        fragment.g0();
        if (!fragment.H) {
            throw new mw2(C0175b.i("Fragment ", fragment, " did not call through to super.onDestroyView()"));
        }
        nq2<j91.a> nq2Var = ((j91.b) new l(fragment.h0(), j91.b.d).a(j91.b.class)).c;
        int i = nq2Var.g;
        for (int i2 = 0; i2 < i; i2++) {
            ((j91.a) nq2Var.f[i2]).getClass();
        }
        fragment.t = false;
        this.a.n(false);
        fragment.I = null;
        fragment.f12J = null;
        fragment.T = null;
        fragment.U.i(null);
        fragment.r = false;
    }

    public final void i() {
        Fragment fragment = this.c;
        fragment.e = -1;
        boolean z = false;
        fragment.H = false;
        fragment.i0();
        fragment.O = null;
        if (!fragment.H) {
            throw new mw2(C0175b.i("Fragment ", fragment, " did not call through to super.onDetach()"));
        }
        lp0 lp0Var = fragment.x;
        if (!lp0Var.H) {
            lp0Var.i();
            fragment.x = new lp0();
        }
        this.a.e(false);
        fragment.e = -1;
        fragment.w = null;
        fragment.y = null;
        fragment.v = null;
        boolean z2 = true;
        if (fragment.p && !fragment.X()) {
            z = true;
        }
        if (!z) {
            mp0 mp0Var = (mp0) this.b.h;
            if (mp0Var.c.containsKey(fragment.i) && mp0Var.f) {
                z2 = mp0Var.g;
            }
            if (!z2) {
                return;
            }
        }
        fragment.T();
    }

    public final void j() {
        Fragment fragment = this.c;
        if (fragment.q && fragment.r && !fragment.t) {
            Bundle bundle = fragment.f;
            Bundle bundle2 = bundle != null ? bundle.getBundle("savedInstanceState") : null;
            fragment.t0(fragment.u0(bundle2), null, bundle2);
            View view = fragment.f12J;
            if (view != null) {
                view.setSaveFromParentEnabled(false);
                fragment.f12J.setTag(R$id.fragment_container_view_tag, fragment);
                if (fragment.C) {
                    fragment.f12J.setVisibility(8);
                }
                Bundle bundle3 = fragment.f;
                fragment.q0(fragment.f12J, bundle3 != null ? bundle3.getBundle("savedInstanceState") : null);
                fragment.x.r(2);
                this.a.m(fragment, fragment.f12J, false);
                fragment.e = 2;
            }
        }
    }

    public final void k() {
        ViewGroup viewGroup;
        ViewGroup viewGroup2;
        ViewGroup viewGroup3;
        la0 la0Var = this.b;
        Fragment fragment = this.c;
        if (this.d) {
            return;
        }
        try {
            this.d = true;
            boolean z = false;
            while (true) {
                int iD = d();
                int i = fragment.e;
                int i2 = 3;
                if (iD == i) {
                    if (!z && i == -1 && fragment.p && !fragment.X()) {
                        mp0 mp0Var = (mp0) la0Var.h;
                        mp0Var.getClass();
                        mp0Var.b(fragment.i);
                        la0Var.l(this);
                        fragment.T();
                    }
                    if (fragment.N) {
                        if (fragment.f12J != null && (viewGroup = fragment.I) != null) {
                            j jVarF = j.f(viewGroup, fragment.M());
                            if (fragment.C) {
                                jVarF.getClass();
                                jVarF.a(3, 1, this);
                            } else {
                                jVarF.getClass();
                                jVarF.a(2, 1, this);
                            }
                        }
                        FragmentManager fragmentManager = fragment.v;
                        if (fragmentManager != null && fragment.o && FragmentManager.H(fragment)) {
                            fragmentManager.E = true;
                        }
                        fragment.N = false;
                        fragment.x.l();
                    }
                    return;
                }
                if (iD <= i) {
                    switch (i - 1) {
                        case -1:
                            i();
                            break;
                        case 0:
                            g();
                            break;
                        case 1:
                            h();
                            fragment.e = 1;
                            break;
                        case 2:
                            fragment.r = false;
                            fragment.e = 2;
                            break;
                        case 3:
                            if (fragment.f12J != null && fragment.g == null) {
                                p();
                            }
                            if (fragment.f12J != null && (viewGroup2 = fragment.I) != null) {
                                j jVarF2 = j.f(viewGroup2, fragment.M());
                                jVarF2.getClass();
                                jVarF2.a(1, 3, this);
                            }
                            fragment.e = 3;
                            break;
                        case 4:
                            r();
                            break;
                        case 5:
                            fragment.e = 5;
                            break;
                        case 6:
                            l();
                            break;
                    }
                } else {
                    switch (i + 1) {
                        case 0:
                            c();
                            break;
                        case 1:
                            e();
                            break;
                        case 2:
                            j();
                            f();
                            break;
                        case 3:
                            a();
                            break;
                        case 4:
                            if (fragment.f12J != null && (viewGroup3 = fragment.I) != null) {
                                j jVarF3 = j.f(viewGroup3, fragment.M());
                                int visibility = fragment.f12J.getVisibility();
                                if (visibility == 0) {
                                    i2 = 2;
                                } else if (visibility == 4) {
                                    i2 = 4;
                                } else if (visibility != 8) {
                                    throw new IllegalArgumentException("Unknown visibility " + visibility);
                                }
                                jVarF3.getClass();
                                jVarF3.a(i2, 2, this);
                            }
                            fragment.e = 4;
                            break;
                        case 5:
                            q();
                            break;
                        case 6:
                            fragment.e = 6;
                            break;
                        case 7:
                            n();
                            break;
                    }
                }
                z = true;
            }
        } finally {
            this.d = false;
        }
    }

    public final void l() {
        Fragment fragment = this.c;
        fragment.x.r(5);
        if (fragment.f12J != null) {
            fragment.T.a(d.a.ON_PAUSE);
        }
        fragment.S.e(d.a.ON_PAUSE);
        fragment.e = 6;
        fragment.H = false;
        fragment.l0();
        if (!fragment.H) {
            throw new mw2(C0175b.i("Fragment ", fragment, " did not call through to super.onPause()"));
        }
        this.a.f(false);
    }

    public final void m(ClassLoader classLoader) {
        Fragment fragment = this.c;
        Bundle bundle = fragment.f;
        if (bundle == null) {
            return;
        }
        bundle.setClassLoader(classLoader);
        if (fragment.f.getBundle("savedInstanceState") == null) {
            fragment.f.putBundle("savedInstanceState", new Bundle());
        }
        fragment.g = fragment.f.getSparseParcelableArray("viewState");
        fragment.h = fragment.f.getBundle("viewRegistryState");
        FragmentState fragmentState = (FragmentState) fragment.f.getParcelable("state");
        if (fragmentState != null) {
            fragment.l = fragmentState.p;
            fragment.m = fragmentState.q;
            fragment.L = fragmentState.r;
        }
        if (fragment.L) {
            return;
        }
        fragment.K = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void n() {
        /*
            r7 = this;
            androidx.fragment.app.Fragment r0 = r7.c
            androidx.fragment.app.Fragment$c r1 = r0.M
            r2 = 0
            if (r1 != 0) goto L9
            r1 = r2
            goto Lb
        L9:
            android.view.View r1 = r1.m
        Lb:
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L2b
            android.view.View r5 = r0.f12J
            if (r1 != r5) goto L14
            goto L1e
        L14:
            android.view.ViewParent r5 = r1.getParent()
        L18:
            if (r5 == 0) goto L25
            android.view.View r6 = r0.f12J
            if (r5 != r6) goto L20
        L1e:
            r5 = r4
            goto L26
        L20:
            android.view.ViewParent r5 = r5.getParent()
            goto L18
        L25:
            r5 = r3
        L26:
            if (r5 == 0) goto L2b
            r1.requestFocus()
        L2b:
            androidx.fragment.app.Fragment$c r1 = r0.H()
            r1.m = r2
            lp0 r1 = r0.x
            r1.N()
            lp0 r1 = r0.x
            r1.w(r4)
            r1 = 7
            r0.e = r1
            r0.H = r3
            r0.m0()
            boolean r4 = r0.H
            if (r4 == 0) goto L72
            androidx.lifecycle.g r4 = r0.S
            androidx.lifecycle.d$a r5 = androidx.lifecycle.d.a.ON_RESUME
            r4.e(r5)
            android.view.View r4 = r0.f12J
            if (r4 == 0) goto L59
            eq0 r4 = r0.T
            androidx.lifecycle.g r4 = r4.h
            r4.e(r5)
        L59:
            lp0 r4 = r0.x
            r4.F = r3
            r4.G = r3
            mp0 r5 = r4.M
            r5.h = r3
            r4.r(r1)
            androidx.fragment.app.g r7 = r7.a
            r7.i(r3)
            r0.f = r2
            r0.g = r2
            r0.h = r2
            return
        L72:
            mw2 r7 = new mw2
            java.lang.String r1 = "Fragment "
            java.lang.String r2 = " did not call through to super.onResume()"
            java.lang.String r0 = defpackage.C0175b.i(r1, r0, r2)
            r7.<init>(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.h.n():void");
    }

    public final Bundle o() {
        Bundle bundle;
        Bundle bundle2 = new Bundle();
        Fragment fragment = this.c;
        if (fragment.e == -1 && (bundle = fragment.f) != null) {
            bundle2.putAll(bundle);
        }
        bundle2.putParcelable("state", new FragmentState(fragment));
        if (fragment.e > -1) {
            Bundle bundle3 = new Bundle();
            fragment.n0(bundle3);
            if (!bundle3.isEmpty()) {
                bundle2.putBundle("savedInstanceState", bundle3);
            }
            this.a.j(false);
            Bundle bundle4 = new Bundle();
            fragment.V.c(bundle4);
            if (!bundle4.isEmpty()) {
                bundle2.putBundle("registryState", bundle4);
            }
            Bundle bundleU = fragment.x.U();
            if (!bundleU.isEmpty()) {
                bundle2.putBundle("childFragmentManager", bundleU);
            }
            if (fragment.f12J != null) {
                p();
            }
            SparseArray<Parcelable> sparseArray = fragment.g;
            if (sparseArray != null) {
                bundle2.putSparseParcelableArray("viewState", sparseArray);
            }
            Bundle bundle5 = fragment.h;
            if (bundle5 != null) {
                bundle2.putBundle("viewRegistryState", bundle5);
            }
        }
        Bundle bundle6 = fragment.j;
        if (bundle6 != null) {
            bundle2.putBundle("arguments", bundle6);
        }
        return bundle2;
    }

    public final void p() {
        Fragment fragment = this.c;
        if (fragment.f12J == null) {
            return;
        }
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        fragment.f12J.saveHierarchyState(sparseArray);
        if (sparseArray.size() > 0) {
            fragment.g = sparseArray;
        }
        Bundle bundle = new Bundle();
        fragment.T.i.c(bundle);
        if (bundle.isEmpty()) {
            return;
        }
        fragment.h = bundle;
    }

    public final void q() {
        Fragment fragment = this.c;
        fragment.x.N();
        fragment.x.w(true);
        fragment.e = 5;
        fragment.H = false;
        fragment.o0();
        if (!fragment.H) {
            throw new mw2(C0175b.i("Fragment ", fragment, " did not call through to super.onStart()"));
        }
        androidx.lifecycle.g gVar = fragment.S;
        d.a aVar = d.a.ON_START;
        gVar.e(aVar);
        if (fragment.f12J != null) {
            fragment.T.h.e(aVar);
        }
        lp0 lp0Var = fragment.x;
        lp0Var.F = false;
        lp0Var.G = false;
        lp0Var.M.h = false;
        lp0Var.r(5);
        this.a.k(false);
    }

    public final void r() {
        Fragment fragment = this.c;
        lp0 lp0Var = fragment.x;
        lp0Var.G = true;
        lp0Var.M.h = true;
        lp0Var.r(4);
        if (fragment.f12J != null) {
            fragment.T.a(d.a.ON_STOP);
        }
        fragment.S.e(d.a.ON_STOP);
        fragment.e = 4;
        fragment.H = false;
        fragment.p0();
        if (!fragment.H) {
            throw new mw2(C0175b.i("Fragment ", fragment, " did not call through to super.onStop()"));
        }
        this.a.l(false);
    }

    public h(g gVar, la0 la0Var, ClassLoader classLoader, f fVar, Bundle bundle) {
        this.a = gVar;
        this.b = la0Var;
        FragmentState fragmentState = (FragmentState) bundle.getParcelable("state");
        Fragment fragmentA = fVar.a(fragmentState.e);
        fragmentA.i = fragmentState.f;
        fragmentA.q = fragmentState.g;
        fragmentA.s = true;
        fragmentA.z = fragmentState.h;
        fragmentA.A = fragmentState.i;
        fragmentA.B = fragmentState.j;
        fragmentA.E = fragmentState.k;
        fragmentA.p = fragmentState.l;
        fragmentA.D = fragmentState.m;
        fragmentA.C = fragmentState.n;
        fragmentA.R = d.b.values()[fragmentState.o];
        fragmentA.l = fragmentState.p;
        fragmentA.m = fragmentState.q;
        fragmentA.L = fragmentState.r;
        this.c = fragmentA;
        fragmentA.f = bundle;
        Bundle bundle2 = bundle.getBundle("arguments");
        if (bundle2 != null) {
            bundle2.setClassLoader(classLoader);
        }
        fragmentA.C0(bundle2);
    }

    /* JADX INFO: compiled from: FragmentStateManager.java */
    public class a implements View.OnAttachStateChangeListener {
        public final /* synthetic */ View e;

        public a(View view) {
            this.e = view;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(View view) {
            View view2 = this.e;
            view2.removeOnAttachStateChangeListener(this);
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.h.c(view2);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(View view) {
        }
    }

    public h(g gVar, la0 la0Var, Fragment fragment, Bundle bundle) {
        this.a = gVar;
        this.b = la0Var;
        this.c = fragment;
        fragment.g = null;
        fragment.h = null;
        fragment.u = 0;
        fragment.r = false;
        fragment.o = false;
        Fragment fragment2 = fragment.k;
        fragment.l = fragment2 != null ? fragment2.i : null;
        fragment.k = null;
        fragment.f = bundle;
        fragment.j = bundle.getBundle("arguments");
    }
}
