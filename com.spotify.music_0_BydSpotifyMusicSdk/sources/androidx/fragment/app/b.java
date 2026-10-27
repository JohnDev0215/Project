package androidx.fragment.app;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.strictmode.FragmentStrictMode$Flag;
import androidx.fragment.app.strictmode.WrongFragmentContainerViolation;
import androidx.fragment.app.strictmode.WrongNestedHierarchyViolation;
import androidx.lifecycle.Lifecycle$Event;
import androidx.lifecycle.Lifecycle$State;
import com.spotify.music.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.WeakHashMap;
import p.bf2;
import p.bx0;
import p.cf2;
import p.cx0;
import p.df2;
import p.gh2;
import p.he1;
import p.hy0;
import p.ix0;
import p.lt2;
import p.mt2;
import p.mu2;
import p.ox0;
import p.p91;
import p.pe2;
import p.qx0;
import p.rx0;
import p.s91;
import p.sw0;
import p.sx0;
import p.tx0;
import p.u4;
import p.ux0;
import p.v03;
import p.v4;
import p.vb1;
import p.ww0;
import p.ww1;
import p.yw0;
import p.zt2;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    public final ww1 a;
    public final gh2 b;
    public final ww0 c;
    public boolean d = false;
    public int e = -1;

    public b(ww1 ww1Var, gh2 gh2Var, ww0 ww0Var) {
        this.a = ww1Var;
        this.b = gh2Var;
        this.c = ww0Var;
    }

    public final void a() {
        boolean zIsLoggable = Log.isLoggable("FragmentManager", 3);
        ww0 ww0Var = this.c;
        if (zIsLoggable) {
            Objects.toString(ww0Var);
        }
        Bundle bundle = ww0Var.e;
        if (bundle != null) {
            bundle.getBundle("savedInstanceState");
        }
        ww0Var.B.K();
        ww0Var.c = 3;
        ww0Var.K = false;
        ww0Var.p();
        if (!ww0Var.K) {
            throw new SuperNotCalledException("Fragment " + ww0Var + " did not call through to super.onActivityCreated()");
        }
        if (Log.isLoggable("FragmentManager", 3)) {
            ww0Var.toString();
        }
        if (ww0Var.M != null) {
            Bundle bundle2 = ww0Var.e;
            Bundle bundle3 = bundle2 != null ? bundle2.getBundle("savedInstanceState") : null;
            SparseArray<Parcelable> sparseArray = ww0Var.k;
            if (sparseArray != null) {
                ww0Var.M.restoreHierarchyState(sparseArray);
                ww0Var.k = null;
            }
            ww0Var.K = false;
            ww0Var.z(bundle3);
            if (!ww0Var.K) {
                throw new SuperNotCalledException("Fragment " + ww0Var + " did not call through to super.onViewStateRestored()");
            }
            if (ww0Var.M != null) {
                ww0Var.V.b(Lifecycle$Event.ON_CREATE);
            }
        }
        ww0Var.e = null;
        ox0 ox0Var = ww0Var.B;
        ox0Var.E = false;
        ox0Var.F = false;
        ox0Var.L.h = false;
        ox0Var.t(4);
        this.a.b(false);
    }

    public final void b() {
        ww0 ww0Var;
        View view;
        View view2;
        ww0 ww0Var2 = this.c;
        View view3 = ww0Var2.L;
        while (true) {
            ww0Var = null;
            if (view3 == null) {
                break;
            }
            Object tag = view3.getTag(R.id.fragment_container_view_tag);
            ww0 ww0Var3 = tag instanceof ww0 ? (ww0) tag : null;
            if (ww0Var3 != null) {
                ww0Var = ww0Var3;
                break;
            } else {
                Object parent = view3.getParent();
                view3 = parent instanceof View ? (View) parent : null;
            }
        }
        ww0 ww0Var4 = ww0Var2.C;
        if (ww0Var != null && !ww0Var.equals(ww0Var4)) {
            int i = ww0Var2.E;
            tx0 tx0Var = ux0.a;
            WrongNestedHierarchyViolation wrongNestedHierarchyViolation = new WrongNestedHierarchyViolation(ww0Var2, ww0Var, i);
            ux0.c(wrongNestedHierarchyViolation);
            tx0 tx0VarA = ux0.a(ww0Var2);
            if (tx0VarA.a.contains(FragmentStrictMode$Flag.m) && ux0.e(tx0VarA, ww0Var2.getClass(), WrongNestedHierarchyViolation.class)) {
                ux0.b(tx0VarA, wrongNestedHierarchyViolation);
            }
        }
        gh2 gh2Var = this.b;
        gh2Var.getClass();
        ViewGroup viewGroup = ww0Var2.L;
        int iIndexOfChild = -1;
        if (viewGroup != null) {
            int iIndexOf = ((ArrayList) gh2Var.l).indexOf(ww0Var2);
            int i2 = iIndexOf - 1;
            while (true) {
                if (i2 < 0) {
                    while (true) {
                        iIndexOf++;
                        if (iIndexOf >= ((ArrayList) gh2Var.l).size()) {
                            break;
                        }
                        ww0 ww0Var5 = (ww0) ((ArrayList) gh2Var.l).get(iIndexOf);
                        if (ww0Var5.L == viewGroup && (view = ww0Var5.M) != null) {
                            iIndexOfChild = viewGroup.indexOfChild(view);
                            break;
                        }
                    }
                } else {
                    ww0 ww0Var6 = (ww0) ((ArrayList) gh2Var.l).get(i2);
                    if (ww0Var6.L == viewGroup && (view2 = ww0Var6.M) != null) {
                        iIndexOfChild = viewGroup.indexOfChild(view2) + 1;
                        break;
                    }
                    i2--;
                }
            }
        }
        ww0Var2.L.addView(ww0Var2.M, iIndexOfChild);
    }

    public final void c() {
        b bVar;
        boolean zIsLoggable = Log.isLoggable("FragmentManager", 3);
        ww0 ww0Var = this.c;
        if (zIsLoggable) {
            Objects.toString(ww0Var);
        }
        ww0 ww0Var2 = ww0Var.o;
        gh2 gh2Var = this.b;
        if (ww0Var2 != null) {
            bVar = (b) ((HashMap) gh2Var.e).get(ww0Var2.m);
            if (bVar == null) {
                throw new IllegalStateException("Fragment " + ww0Var + " declared target fragment " + ww0Var.o + " that does not belong to this FragmentManager!");
            }
            ww0Var.f205p = ww0Var.o.m;
            ww0Var.o = null;
        } else {
            String str = ww0Var.f205p;
            if (str != null) {
                bVar = (b) ((HashMap) gh2Var.e).get(str);
                if (bVar == null) {
                    StringBuilder sb = new StringBuilder("Fragment ");
                    sb.append(ww0Var);
                    sb.append(" declared target fragment ");
                    throw new IllegalStateException(u4.m(sb, ww0Var.f205p, " that does not belong to this FragmentManager!"));
                }
            } else {
                bVar = null;
            }
        }
        if (bVar != null) {
            bVar.k();
        }
        ox0 ox0Var = ww0Var.z;
        ww0Var.A = ox0Var.t;
        ww0Var.C = ox0Var.v;
        ww1 ww1Var = this.a;
        ww1Var.i(false);
        ArrayList arrayList = ww0Var.Y;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ww0 ww0Var3 = ((sw0) it.next()).a;
            ww0Var3.X.a();
            he1.p(ww0Var3);
            Bundle bundle = ww0Var3.e;
            ww0Var3.X.b(bundle != null ? bundle.getBundle("registryState") : null);
        }
        arrayList.clear();
        ww0Var.B.b(ww0Var.A, ww0Var.b(), ww0Var);
        ww0Var.c = 0;
        ww0Var.K = false;
        ww0Var.q(ww0Var.A.r);
        if (!ww0Var.K) {
            throw new SuperNotCalledException("Fragment " + ww0Var + " did not call through to super.onAttach()");
        }
        Iterator it2 = ww0Var.z.m.iterator();
        while (it2.hasNext()) {
            ((rx0) it2.next()).b();
        }
        ox0 ox0Var2 = ww0Var.B;
        ox0Var2.E = false;
        ox0Var2.F = false;
        ox0Var2.L.h = false;
        ox0Var2.t(0);
        ww1Var.d(false);
    }

    public final int d() {
        Object next;
        ww0 ww0Var = this.c;
        if (ww0Var.z == null) {
            return ww0Var.c;
        }
        int iMin = this.e;
        int iOrdinal = ww0Var.T.ordinal();
        if (iOrdinal == 1) {
            iMin = Math.min(iMin, 0);
        } else if (iOrdinal == 2) {
            iMin = Math.min(iMin, 1);
        } else if (iOrdinal == 3) {
            iMin = Math.min(iMin, 5);
        } else if (iOrdinal != 4) {
            iMin = Math.min(iMin, -1);
        }
        if (ww0Var.u) {
            if (ww0Var.v) {
                iMin = Math.max(this.e, 2);
                View view = ww0Var.M;
                if (view != null && view.getParent() == null) {
                    iMin = Math.min(iMin, 2);
                }
            } else {
                iMin = this.e < 4 ? Math.min(iMin, ww0Var.c) : Math.min(iMin, 1);
            }
        }
        if (!ww0Var.s) {
            iMin = Math.min(iMin, 1);
        }
        ViewGroup viewGroup = ww0Var.L;
        if (viewGroup != null) {
            df2 df2VarF = df2.f(viewGroup, ww0Var.h());
            df2VarF.getClass();
            bf2 bf2VarD = df2VarF.d(ww0Var);
            SpecialEffectsController$Operation$LifecycleImpact specialEffectsController$Operation$LifecycleImpact = bf2VarD != null ? bf2VarD.b : null;
            Iterator it = df2VarF.c.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                bf2 bf2Var = (bf2) next;
                if (v03.b(bf2Var.c, ww0Var) && !bf2Var.f) {
                    break;
                }
            }
            bf2 bf2Var2 = (bf2) next;
            specialEffectsController$Operation$LifecycleImpact = bf2Var2 != null ? bf2Var2.b : null;
            int i = specialEffectsController$Operation$LifecycleImpact == null ? -1 : cf2.a[specialEffectsController$Operation$LifecycleImpact.ordinal()];
            if (i != -1 && i != 1) {
                specialEffectsController$Operation$LifecycleImpact = specialEffectsController$Operation$LifecycleImpact;
            }
        }
        if (specialEffectsController$Operation$LifecycleImpact == SpecialEffectsController$Operation$LifecycleImpact.e) {
            iMin = Math.min(iMin, 6);
        } else if (specialEffectsController$Operation$LifecycleImpact == SpecialEffectsController$Operation$LifecycleImpact.k) {
            iMin = Math.max(iMin, 3);
        } else if (ww0Var.t) {
            iMin = ww0Var.o() ? Math.min(iMin, 1) : Math.min(iMin, -1);
        }
        if (ww0Var.N && ww0Var.c < 5) {
            iMin = Math.min(iMin, 4);
        }
        if (Log.isLoggable("FragmentManager", 2)) {
            Objects.toString(ww0Var);
        }
        return iMin;
    }

    public final void e() {
        Bundle bundle;
        boolean zIsLoggable = Log.isLoggable("FragmentManager", 3);
        final ww0 ww0Var = this.c;
        if (zIsLoggable) {
            Objects.toString(ww0Var);
        }
        Bundle bundle2 = ww0Var.e;
        Bundle bundle3 = bundle2 != null ? bundle2.getBundle("savedInstanceState") : null;
        if (ww0Var.R) {
            ww0Var.c = 1;
            Bundle bundle4 = ww0Var.e;
            if (bundle4 == null || (bundle = bundle4.getBundle("childFragmentManager")) == null) {
                return;
            }
            ww0Var.B.Q(bundle);
            ox0 ox0Var = ww0Var.B;
            ox0Var.E = false;
            ox0Var.F = false;
            ox0Var.L.h = false;
            ox0Var.t(1);
            return;
        }
        ww1 ww1Var = this.a;
        ww1Var.l(false);
        ww0Var.B.K();
        ww0Var.c = 1;
        ww0Var.K = false;
        ww0Var.U.a(new p91() { // from class: androidx.fragment.app.Fragment$6
            @Override // p.p91
            public final void d(s91 s91Var, Lifecycle$Event lifecycle$Event) {
                View view;
                if (lifecycle$Event != Lifecycle$Event.ON_STOP || (view = ww0Var.M) == null) {
                    return;
                }
                view.cancelPendingInputEvents();
            }
        });
        ww0Var.r(bundle3);
        ww0Var.R = true;
        if (ww0Var.K) {
            ww0Var.U.f(Lifecycle$Event.ON_CREATE);
            ww1Var.e(false);
        } else {
            throw new SuperNotCalledException("Fragment " + ww0Var + " did not call through to super.onCreate()");
        }
    }

    public final void f() {
        String resourceName;
        ww0 ww0Var = this.c;
        if (ww0Var.u) {
            return;
        }
        if (Log.isLoggable("FragmentManager", 3)) {
            Objects.toString(ww0Var);
        }
        Bundle bundle = ww0Var.e;
        ViewGroup viewGroup = null;
        Bundle bundle2 = bundle != null ? bundle.getBundle("savedInstanceState") : null;
        LayoutInflater layoutInflaterV = ww0Var.v(bundle2);
        ViewGroup viewGroup2 = ww0Var.L;
        if (viewGroup2 != null) {
            viewGroup = viewGroup2;
        } else {
            int i = ww0Var.E;
            if (i != 0) {
                if (i == -1) {
                    throw new IllegalArgumentException("Cannot create fragment " + ww0Var + " for a container view with no id");
                }
                viewGroup = (ViewGroup) ww0Var.z.u.O(i);
                if (viewGroup == null) {
                    if (!ww0Var.w) {
                        try {
                            resourceName = ww0Var.B().getResources().getResourceName(ww0Var.E);
                        } catch (Resources.NotFoundException unused) {
                            resourceName = "unknown";
                        }
                        throw new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(ww0Var.E) + " (" + resourceName + ") for fragment " + ww0Var);
                    }
                } else if (!(viewGroup instanceof bx0)) {
                    tx0 tx0Var = ux0.a;
                    WrongFragmentContainerViolation wrongFragmentContainerViolation = new WrongFragmentContainerViolation(ww0Var, viewGroup);
                    ux0.c(wrongFragmentContainerViolation);
                    tx0 tx0VarA = ux0.a(ww0Var);
                    if (tx0VarA.a.contains(FragmentStrictMode$Flag.n) && ux0.e(tx0VarA, ww0Var.getClass(), WrongFragmentContainerViolation.class)) {
                        ux0.b(tx0VarA, wrongFragmentContainerViolation);
                    }
                }
            }
        }
        ww0Var.L = viewGroup;
        ww0Var.A(layoutInflaterV, viewGroup, bundle2);
        if (ww0Var.M != null) {
            if (Log.isLoggable("FragmentManager", 3)) {
                Objects.toString(ww0Var);
            }
            ww0Var.M.setSaveFromParentEnabled(false);
            ww0Var.M.setTag(R.id.fragment_container_view_tag, ww0Var);
            if (viewGroup != null) {
                b();
            }
            if (ww0Var.G) {
                ww0Var.M.setVisibility(8);
            }
            View view = ww0Var.M;
            WeakHashMap weakHashMap = zt2.a;
            if (lt2.b(view)) {
                mt2.c(ww0Var.M);
            } else {
                View view2 = ww0Var.M;
                view2.addOnAttachStateChangeListener(new cx0(this, view2));
            }
            Bundle bundle3 = ww0Var.e;
            if (bundle3 != null) {
                bundle3.getBundle("savedInstanceState");
            }
            ww0Var.B.t(2);
            this.a.r(false);
            int visibility = ww0Var.M.getVisibility();
            ww0Var.c().l = ww0Var.M.getAlpha();
            if (ww0Var.L != null && visibility == 0) {
                View viewFindFocus = ww0Var.M.findFocus();
                if (viewFindFocus != null) {
                    ww0Var.c().m = viewFindFocus;
                    if (Log.isLoggable("FragmentManager", 2)) {
                        viewFindFocus.toString();
                        Objects.toString(ww0Var);
                    }
                }
                ww0Var.M.setAlpha(0.0f);
            }
        }
        ww0Var.c = 2;
    }

    public final void g() {
        boolean z;
        ww0 ww0VarJ;
        boolean zIsLoggable = Log.isLoggable("FragmentManager", 3);
        ww0 ww0Var = this.c;
        if (zIsLoggable) {
            Objects.toString(ww0Var);
        }
        boolean z2 = ww0Var.t && !ww0Var.o();
        gh2 gh2Var = this.b;
        if (z2) {
            gh2Var.w(null, ww0Var.m);
        }
        if (!z2) {
            qx0 qx0Var = (qx0) gh2Var.m;
            if (qx0Var.c.containsKey(ww0Var.m) && qx0Var.f && !qx0Var.g) {
                String str = ww0Var.f205p;
                if (str != null && (ww0VarJ = gh2Var.j(str)) != null && ww0VarJ.I) {
                    ww0Var.o = ww0VarJ;
                }
                ww0Var.c = 0;
                return;
            }
        }
        yw0 yw0Var = ww0Var.A;
        if (yw0Var instanceof mu2) {
            z = ((qx0) gh2Var.m).g;
        } else {
            z = yw0Var.r instanceof Activity ? !((Activity) r8).isChangingConfigurations() : true;
        }
        if (z2 || z) {
            qx0 qx0Var2 = (qx0) gh2Var.m;
            qx0Var2.getClass();
            if (Log.isLoggable("FragmentManager", 3)) {
                Objects.toString(ww0Var);
            }
            qx0Var2.c(ww0Var.m, false);
        }
        ww0Var.B.k();
        ww0Var.U.f(Lifecycle$Event.ON_DESTROY);
        ww0Var.c = 0;
        ww0Var.R = false;
        ww0Var.K = true;
        this.a.f(false);
        for (b bVar : gh2Var.n()) {
            if (bVar != null) {
                String str2 = ww0Var.m;
                ww0 ww0Var2 = bVar.c;
                if (str2.equals(ww0Var2.f205p)) {
                    ww0Var2.o = ww0Var;
                    ww0Var2.f205p = null;
                }
            }
        }
        String str3 = ww0Var.f205p;
        if (str3 != null) {
            ww0Var.o = gh2Var.j(str3);
        }
        gh2Var.u(this);
    }

    public final void h() {
        View view;
        boolean zIsLoggable = Log.isLoggable("FragmentManager", 3);
        ww0 ww0Var = this.c;
        if (zIsLoggable) {
            Objects.toString(ww0Var);
        }
        ViewGroup viewGroup = ww0Var.L;
        if (viewGroup != null && (view = ww0Var.M) != null) {
            viewGroup.removeView(view);
        }
        ww0Var.B.t(1);
        if (ww0Var.M != null) {
            hy0 hy0Var = ww0Var.V;
            hy0Var.c();
            if (hy0Var.l.c.compareTo(Lifecycle$State.k) >= 0) {
                ww0Var.V.b(Lifecycle$Event.ON_DESTROY);
            }
        }
        ww0Var.c = 1;
        ww0Var.K = false;
        ww0Var.t();
        if (!ww0Var.K) {
            throw new SuperNotCalledException("Fragment " + ww0Var + " did not call through to super.onDestroyView()");
        }
        v4 v4Var = new v4(ww0Var.j(), vb1.d);
        String canonicalName = vb1.class.getCanonicalName();
        if (canonicalName == null) {
            throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
        }
        pe2 pe2Var = ((vb1) v4Var.m(vb1.class, "androidx.lifecycle.ViewModelProvider.DefaultKey:".concat(canonicalName))).c;
        if (pe2Var.k > 0) {
            u4.z(pe2Var.e[0]);
            throw null;
        }
        ww0Var.x = false;
        this.a.s(false);
        ww0Var.L = null;
        ww0Var.M = null;
        ww0Var.V = null;
        ww0Var.W.e(null);
        ww0Var.v = false;
    }

    public final void i() {
        boolean zIsLoggable = Log.isLoggable("FragmentManager", 3);
        ww0 ww0Var = this.c;
        if (zIsLoggable) {
            Objects.toString(ww0Var);
        }
        ww0Var.c = -1;
        ww0Var.K = false;
        ww0Var.u();
        if (!ww0Var.K) {
            throw new SuperNotCalledException("Fragment " + ww0Var + " did not call through to super.onDetach()");
        }
        ox0 ox0Var = ww0Var.B;
        if (!ox0Var.G) {
            ox0Var.k();
            ww0Var.B = new ox0();
        }
        this.a.g(false);
        ww0Var.c = -1;
        ww0Var.A = null;
        ww0Var.C = null;
        ww0Var.z = null;
        if (!ww0Var.t || ww0Var.o()) {
            qx0 qx0Var = (qx0) this.b.m;
            if (qx0Var.c.containsKey(ww0Var.m) && qx0Var.f && !qx0Var.g) {
                return;
            }
        }
        if (Log.isLoggable("FragmentManager", 3)) {
            Objects.toString(ww0Var);
        }
        ww0Var.k();
    }

    public final void j() {
        ww0 ww0Var = this.c;
        if (ww0Var.u && ww0Var.v && !ww0Var.x) {
            if (Log.isLoggable("FragmentManager", 3)) {
                Objects.toString(ww0Var);
            }
            Bundle bundle = ww0Var.e;
            Bundle bundle2 = bundle != null ? bundle.getBundle("savedInstanceState") : null;
            ww0Var.A(ww0Var.v(bundle2), null, bundle2);
            View view = ww0Var.M;
            if (view != null) {
                view.setSaveFromParentEnabled(false);
                ww0Var.M.setTag(R.id.fragment_container_view_tag, ww0Var);
                if (ww0Var.G) {
                    ww0Var.M.setVisibility(8);
                }
                Bundle bundle3 = ww0Var.e;
                if (bundle3 != null) {
                    bundle3.getBundle("savedInstanceState");
                }
                ww0Var.B.t(2);
                this.a.r(false);
                ww0Var.c = 2;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:138:0x0101, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void k() {
        /*
            Method dump skipped, instruction units count: 446
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.b.k():void");
    }

    public final void l() {
        boolean zIsLoggable = Log.isLoggable("FragmentManager", 3);
        ww0 ww0Var = this.c;
        if (zIsLoggable) {
            Objects.toString(ww0Var);
        }
        ww0Var.B.t(5);
        if (ww0Var.M != null) {
            ww0Var.V.b(Lifecycle$Event.ON_PAUSE);
        }
        ww0Var.U.f(Lifecycle$Event.ON_PAUSE);
        ww0Var.c = 6;
        ww0Var.K = true;
        this.a.h(false);
    }

    public final void m(ClassLoader classLoader) {
        ww0 ww0Var = this.c;
        Bundle bundle = ww0Var.e;
        if (bundle == null) {
            return;
        }
        bundle.setClassLoader(classLoader);
        if (ww0Var.e.getBundle("savedInstanceState") == null) {
            ww0Var.e.putBundle("savedInstanceState", new Bundle());
        }
        ww0Var.k = ww0Var.e.getSparseParcelableArray("viewState");
        ww0Var.l = ww0Var.e.getBundle("viewRegistryState");
        sx0 sx0Var = (sx0) ww0Var.e.getParcelable("state");
        if (sx0Var != null) {
            ww0Var.f205p = sx0Var.t;
            ww0Var.q = sx0Var.u;
            ww0Var.O = sx0Var.v;
        }
        if (ww0Var.O) {
            return;
        }
        ww0Var.N = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void n() {
        /*
            r6 = this;
            r0 = 3
            java.lang.String r1 = "FragmentManager"
            boolean r0 = android.util.Log.isLoggable(r1, r0)
            p.ww0 r2 = r6.c
            if (r0 == 0) goto Le
            java.util.Objects.toString(r2)
        Le:
            p.uw0 r0 = r2.P
            r3 = 0
            if (r0 != 0) goto L15
            r0 = r3
            goto L17
        L15:
            android.view.View r0 = r0.m
        L17:
            if (r0 == 0) goto L47
            android.view.View r4 = r2.M
            if (r0 != r4) goto L1e
            goto L28
        L1e:
            android.view.ViewParent r4 = r0.getParent()
        L22:
            if (r4 == 0) goto L47
            android.view.View r5 = r2.M
            if (r4 != r5) goto L42
        L28:
            r0.requestFocus()
            r4 = 2
            boolean r1 = android.util.Log.isLoggable(r1, r4)
            if (r1 == 0) goto L47
            r0.toString()
            java.util.Objects.toString(r2)
            android.view.View r0 = r2.M
            android.view.View r0 = r0.findFocus()
            java.util.Objects.toString(r0)
            goto L47
        L42:
            android.view.ViewParent r4 = r4.getParent()
            goto L22
        L47:
            p.uw0 r0 = r2.c()
            r0.m = r3
            p.ox0 r0 = r2.B
            r0.K()
            p.ox0 r0 = r2.B
            r1 = 1
            r0.x(r1)
            r0 = 7
            r2.c = r0
            r2.K = r1
            androidx.lifecycle.a r1 = r2.U
            androidx.lifecycle.Lifecycle$Event r4 = androidx.lifecycle.Lifecycle$Event.ON_RESUME
            r1.f(r4)
            android.view.View r1 = r2.M
            if (r1 == 0) goto L6f
            p.hy0 r1 = r2.V
            androidx.lifecycle.a r1 = r1.l
            r1.f(r4)
        L6f:
            p.ox0 r1 = r2.B
            r4 = 0
            r1.E = r4
            r1.F = r4
            p.qx0 r5 = r1.L
            r5.h = r4
            r1.t(r0)
            p.ww1 r0 = r6.a
            r0.n(r4)
            p.gh2 r0 = r6.b
            java.lang.String r1 = r2.m
            r0.w(r3, r1)
            r2.e = r3
            r2.k = r3
            r2.l = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.b.n():void");
    }

    public final Bundle o() {
        Bundle bundle;
        Bundle bundle2 = new Bundle();
        ww0 ww0Var = this.c;
        if (ww0Var.c == -1 && (bundle = ww0Var.e) != null) {
            bundle2.putAll(bundle);
        }
        bundle2.putParcelable("state", new sx0(ww0Var));
        if (ww0Var.c > -1) {
            Bundle bundle3 = new Bundle();
            ww0Var.w(bundle3);
            if (!bundle3.isEmpty()) {
                bundle2.putBundle("savedInstanceState", bundle3);
            }
            this.a.o(false);
            Bundle bundle4 = new Bundle();
            ww0Var.X.c(bundle4);
            if (!bundle4.isEmpty()) {
                bundle2.putBundle("registryState", bundle4);
            }
            Bundle bundleR = ww0Var.B.R();
            if (!bundleR.isEmpty()) {
                bundle2.putBundle("childFragmentManager", bundleR);
            }
            if (ww0Var.M != null) {
                p();
            }
            SparseArray<? extends Parcelable> sparseArray = ww0Var.k;
            if (sparseArray != null) {
                bundle2.putSparseParcelableArray("viewState", sparseArray);
            }
            Bundle bundle5 = ww0Var.l;
            if (bundle5 != null) {
                bundle2.putBundle("viewRegistryState", bundle5);
            }
        }
        Bundle bundle6 = ww0Var.n;
        if (bundle6 != null) {
            bundle2.putBundle("arguments", bundle6);
        }
        return bundle2;
    }

    public final void p() {
        ww0 ww0Var = this.c;
        if (ww0Var.M == null) {
            return;
        }
        if (Log.isLoggable("FragmentManager", 2)) {
            Objects.toString(ww0Var);
            Objects.toString(ww0Var.M);
        }
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        ww0Var.M.saveHierarchyState(sparseArray);
        if (sparseArray.size() > 0) {
            ww0Var.k = sparseArray;
        }
        Bundle bundle = new Bundle();
        ww0Var.V.m.c(bundle);
        if (bundle.isEmpty()) {
            return;
        }
        ww0Var.l = bundle;
    }

    public final void q() {
        boolean zIsLoggable = Log.isLoggable("FragmentManager", 3);
        ww0 ww0Var = this.c;
        if (zIsLoggable) {
            Objects.toString(ww0Var);
        }
        ww0Var.B.K();
        ww0Var.B.x(true);
        ww0Var.c = 5;
        ww0Var.K = false;
        ww0Var.x();
        if (!ww0Var.K) {
            throw new SuperNotCalledException("Fragment " + ww0Var + " did not call through to super.onStart()");
        }
        androidx.lifecycle.a aVar = ww0Var.U;
        Lifecycle$Event lifecycle$Event = Lifecycle$Event.ON_START;
        aVar.f(lifecycle$Event);
        if (ww0Var.M != null) {
            ww0Var.V.l.f(lifecycle$Event);
        }
        ox0 ox0Var = ww0Var.B;
        ox0Var.E = false;
        ox0Var.F = false;
        ox0Var.L.h = false;
        ox0Var.t(5);
        this.a.p(false);
    }

    public final void r() {
        boolean zIsLoggable = Log.isLoggable("FragmentManager", 3);
        ww0 ww0Var = this.c;
        if (zIsLoggable) {
            Objects.toString(ww0Var);
        }
        ox0 ox0Var = ww0Var.B;
        ox0Var.F = true;
        ox0Var.L.h = true;
        ox0Var.t(4);
        if (ww0Var.M != null) {
            ww0Var.V.b(Lifecycle$Event.ON_STOP);
        }
        ww0Var.U.f(Lifecycle$Event.ON_STOP);
        ww0Var.c = 4;
        ww0Var.K = false;
        ww0Var.y();
        if (ww0Var.K) {
            this.a.q(false);
            return;
        }
        throw new SuperNotCalledException("Fragment " + ww0Var + " did not call through to super.onStop()");
    }

    public b(ww1 ww1Var, gh2 gh2Var, ClassLoader classLoader, ix0 ix0Var, Bundle bundle) {
        this.a = ww1Var;
        this.b = gh2Var;
        sx0 sx0Var = (sx0) bundle.getParcelable("state");
        ww0 ww0VarA = ix0Var.a(sx0Var.c);
        ww0VarA.m = sx0Var.e;
        ww0VarA.u = sx0Var.k;
        ww0VarA.w = true;
        ww0VarA.D = sx0Var.l;
        ww0VarA.E = sx0Var.m;
        ww0VarA.F = sx0Var.n;
        ww0VarA.I = sx0Var.o;
        ww0VarA.t = sx0Var.f183p;
        ww0VarA.H = sx0Var.q;
        ww0VarA.G = sx0Var.r;
        ww0VarA.T = Lifecycle$State.values()[sx0Var.s];
        ww0VarA.f205p = sx0Var.t;
        ww0VarA.q = sx0Var.u;
        ww0VarA.O = sx0Var.v;
        this.c = ww0VarA;
        ww0VarA.e = bundle;
        Bundle bundle2 = bundle.getBundle("arguments");
        if (bundle2 != null) {
            bundle2.setClassLoader(classLoader);
        }
        ww0VarA.E(bundle2);
        if (Log.isLoggable("FragmentManager", 2)) {
            Objects.toString(ww0VarA);
        }
    }

    public b(ww1 ww1Var, gh2 gh2Var, ww0 ww0Var, Bundle bundle) {
        this.a = ww1Var;
        this.b = gh2Var;
        this.c = ww0Var;
        ww0Var.k = null;
        ww0Var.l = null;
        ww0Var.y = 0;
        ww0Var.v = false;
        ww0Var.s = false;
        ww0 ww0Var2 = ww0Var.o;
        ww0Var.f205p = ww0Var2 != null ? ww0Var2.m : null;
        ww0Var.o = null;
        ww0Var.e = bundle;
        ww0Var.n = bundle.getBundle("arguments");
    }
}
