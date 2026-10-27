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
import android.view.ViewParent;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.x;
import androidx.lifecycle.e0;
import androidx.lifecycle.i;

/* JADX INFO: compiled from: FragmentStateManager.java */
/* JADX INFO: loaded from: classes.dex */
class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final k f2355a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final q f2356b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Fragment f2357c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f2358d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f2359e = -1;

    /* JADX INFO: compiled from: FragmentStateManager.java */
    class a implements View.OnAttachStateChangeListener {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ View f2360e;

        a(View view) {
            this.f2360e = view;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            this.f2360e.removeOnAttachStateChangeListener(this);
            i0.u.h0(this.f2360e);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    /* JADX INFO: compiled from: FragmentStateManager.java */
    static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2362a;

        static {
            int[] iArr = new int[i.c.values().length];
            f2362a = iArr;
            try {
                iArr[i.c.RESUMED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2362a[i.c.STARTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2362a[i.c.CREATED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2362a[i.c.INITIALIZED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    p(k kVar, q qVar, Fragment fragment) {
        this.f2355a = kVar;
        this.f2356b = qVar;
        this.f2357c = fragment;
    }

    private boolean l(View view) {
        if (view == this.f2357c.M) {
            return true;
        }
        for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
            if (parent == this.f2357c.M) {
                return true;
            }
        }
        return false;
    }

    private Bundle q() {
        Bundle bundle = new Bundle();
        this.f2357c.p1(bundle);
        this.f2355a.j(this.f2357c, bundle, false);
        if (bundle.isEmpty()) {
            bundle = null;
        }
        if (this.f2357c.M != null) {
            t();
        }
        if (this.f2357c.f2112h != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", this.f2357c.f2112h);
        }
        if (this.f2357c.f2113i != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBundle("android:view_registry_state", this.f2357c.f2113i);
        }
        if (!this.f2357c.O) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android:user_visible_hint", this.f2357c.O);
        }
        return bundle;
    }

    void a() {
        if (FragmentManager.F0(3)) {
            Log.d("FragmentManager", "moveto ACTIVITY_CREATED: " + this.f2357c);
        }
        Fragment fragment = this.f2357c;
        fragment.V0(fragment.f2111g);
        k kVar = this.f2355a;
        Fragment fragment2 = this.f2357c;
        kVar.a(fragment2, fragment2.f2111g, false);
    }

    void b() {
        int iJ = this.f2356b.j(this.f2357c);
        Fragment fragment = this.f2357c;
        fragment.L.addView(fragment.M, iJ);
    }

    void c() {
        if (FragmentManager.F0(3)) {
            Log.d("FragmentManager", "moveto ATTACHED: " + this.f2357c);
        }
        Fragment fragment = this.f2357c;
        Fragment fragment2 = fragment.f2117m;
        p pVarM = null;
        if (fragment2 != null) {
            p pVarM2 = this.f2356b.m(fragment2.f2115k);
            if (pVarM2 == null) {
                throw new IllegalStateException("Fragment " + this.f2357c + " declared target fragment " + this.f2357c.f2117m + " that does not belong to this FragmentManager!");
            }
            Fragment fragment3 = this.f2357c;
            fragment3.f2118n = fragment3.f2117m.f2115k;
            fragment3.f2117m = null;
            pVarM = pVarM2;
        } else {
            String str = fragment.f2118n;
            if (str != null && (pVarM = this.f2356b.m(str)) == null) {
                throw new IllegalStateException("Fragment " + this.f2357c + " declared target fragment " + this.f2357c.f2118n + " that does not belong to this FragmentManager!");
            }
        }
        if (pVarM != null && (FragmentManager.P || pVarM.k().f2109e < 1)) {
            pVarM.m();
        }
        Fragment fragment4 = this.f2357c;
        fragment4.f2129y = fragment4.f2128x.t0();
        Fragment fragment5 = this.f2357c;
        fragment5.A = fragment5.f2128x.w0();
        this.f2355a.g(this.f2357c, false);
        this.f2357c.W0();
        this.f2355a.b(this.f2357c, false);
    }

    int d() {
        Fragment fragment;
        ViewGroup viewGroup;
        Fragment fragment2 = this.f2357c;
        if (fragment2.f2128x == null) {
            return fragment2.f2109e;
        }
        int iMin = this.f2359e;
        int i7 = b.f2362a[fragment2.W.ordinal()];
        if (i7 != 1) {
            iMin = i7 != 2 ? i7 != 3 ? i7 != 4 ? Math.min(iMin, -1) : Math.min(iMin, 0) : Math.min(iMin, 1) : Math.min(iMin, 5);
        }
        Fragment fragment3 = this.f2357c;
        if (fragment3.f2123s) {
            if (fragment3.f2124t) {
                iMin = Math.max(this.f2359e, 2);
                View view = this.f2357c.M;
                if (view != null && view.getParent() == null) {
                    iMin = Math.min(iMin, 2);
                }
            } else {
                iMin = this.f2359e < 4 ? Math.min(iMin, fragment3.f2109e) : Math.min(iMin, 1);
            }
        }
        if (!this.f2357c.f2121q) {
            iMin = Math.min(iMin, 1);
        }
        x.e.b bVarL = null;
        if (FragmentManager.P && (viewGroup = (fragment = this.f2357c).L) != null) {
            bVarL = x.n(viewGroup, fragment.H()).l(this);
        }
        if (bVarL == x.e.b.ADDING) {
            iMin = Math.min(iMin, 6);
        } else if (bVarL == x.e.b.REMOVING) {
            iMin = Math.max(iMin, 3);
        } else {
            Fragment fragment4 = this.f2357c;
            if (fragment4.f2122r) {
                iMin = fragment4.f0() ? Math.min(iMin, 1) : Math.min(iMin, -1);
            }
        }
        Fragment fragment5 = this.f2357c;
        if (fragment5.N && fragment5.f2109e < 5) {
            iMin = Math.min(iMin, 4);
        }
        if (FragmentManager.F0(2)) {
            Log.v("FragmentManager", "computeExpectedState() of " + iMin + " for " + this.f2357c);
        }
        return iMin;
    }

    void e() {
        if (FragmentManager.F0(3)) {
            Log.d("FragmentManager", "moveto CREATED: " + this.f2357c);
        }
        Fragment fragment = this.f2357c;
        if (fragment.V) {
            fragment.w1(fragment.f2111g);
            this.f2357c.f2109e = 1;
            return;
        }
        this.f2355a.h(fragment, fragment.f2111g, false);
        Fragment fragment2 = this.f2357c;
        fragment2.Z0(fragment2.f2111g);
        k kVar = this.f2355a;
        Fragment fragment3 = this.f2357c;
        kVar.c(fragment3, fragment3.f2111g, false);
    }

    void f() {
        String resourceName;
        if (this.f2357c.f2123s) {
            return;
        }
        if (FragmentManager.F0(3)) {
            Log.d("FragmentManager", "moveto CREATE_VIEW: " + this.f2357c);
        }
        Fragment fragment = this.f2357c;
        LayoutInflater layoutInflaterF1 = fragment.f1(fragment.f2111g);
        ViewGroup viewGroup = null;
        Fragment fragment2 = this.f2357c;
        ViewGroup viewGroup2 = fragment2.L;
        if (viewGroup2 != null) {
            viewGroup = viewGroup2;
        } else {
            int i7 = fragment2.C;
            if (i7 != 0) {
                if (i7 == -1) {
                    throw new IllegalArgumentException("Cannot create fragment " + this.f2357c + " for a container view with no id");
                }
                viewGroup = (ViewGroup) fragment2.f2128x.n0().f(this.f2357c.C);
                if (viewGroup == null) {
                    Fragment fragment3 = this.f2357c;
                    if (!fragment3.f2125u) {
                        try {
                            resourceName = fragment3.N().getResourceName(this.f2357c.C);
                        } catch (Resources.NotFoundException unused) {
                            resourceName = "unknown";
                        }
                        throw new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(this.f2357c.C) + " (" + resourceName + ") for fragment " + this.f2357c);
                    }
                }
            }
        }
        Fragment fragment4 = this.f2357c;
        fragment4.L = viewGroup;
        fragment4.b1(layoutInflaterF1, viewGroup, fragment4.f2111g);
        View view = this.f2357c.M;
        if (view != null) {
            boolean z6 = false;
            view.setSaveFromParentEnabled(false);
            Fragment fragment5 = this.f2357c;
            fragment5.M.setTag(q0.b.fragment_container_view_tag, fragment5);
            if (viewGroup != null) {
                b();
            }
            Fragment fragment6 = this.f2357c;
            if (fragment6.E) {
                fragment6.M.setVisibility(8);
            }
            if (i0.u.O(this.f2357c.M)) {
                i0.u.h0(this.f2357c.M);
            } else {
                View view2 = this.f2357c.M;
                view2.addOnAttachStateChangeListener(new a(view2));
            }
            this.f2357c.s1();
            k kVar = this.f2355a;
            Fragment fragment7 = this.f2357c;
            kVar.m(fragment7, fragment7.M, fragment7.f2111g, false);
            int visibility = this.f2357c.M.getVisibility();
            float alpha = this.f2357c.M.getAlpha();
            if (FragmentManager.P) {
                this.f2357c.K1(alpha);
                Fragment fragment8 = this.f2357c;
                if (fragment8.L != null && visibility == 0) {
                    View viewFindFocus = fragment8.M.findFocus();
                    if (viewFindFocus != null) {
                        this.f2357c.D1(viewFindFocus);
                        if (FragmentManager.F0(2)) {
                            Log.v("FragmentManager", "requestFocus: Saved focused view " + viewFindFocus + " for Fragment " + this.f2357c);
                        }
                    }
                    this.f2357c.M.setAlpha(0.0f);
                }
            } else {
                Fragment fragment9 = this.f2357c;
                if (visibility == 0 && fragment9.L != null) {
                    z6 = true;
                }
                fragment9.R = z6;
            }
        }
        this.f2357c.f2109e = 2;
    }

    void g() {
        Fragment fragmentF;
        if (FragmentManager.F0(3)) {
            Log.d("FragmentManager", "movefrom CREATED: " + this.f2357c);
        }
        Fragment fragment = this.f2357c;
        boolean zIsChangingConfigurations = true;
        boolean z6 = fragment.f2122r && !fragment.f0();
        if (!(z6 || this.f2356b.o().r(this.f2357c))) {
            String str = this.f2357c.f2118n;
            if (str != null && (fragmentF = this.f2356b.f(str)) != null && fragmentF.G) {
                this.f2357c.f2117m = fragmentF;
            }
            this.f2357c.f2109e = 0;
            return;
        }
        i<?> iVar = this.f2357c.f2129y;
        if (iVar instanceof e0) {
            zIsChangingConfigurations = this.f2356b.o().o();
        } else if (iVar.j() instanceof Activity) {
            zIsChangingConfigurations = true ^ ((Activity) iVar.j()).isChangingConfigurations();
        }
        if (z6 || zIsChangingConfigurations) {
            this.f2356b.o().i(this.f2357c);
        }
        this.f2357c.c1();
        this.f2355a.d(this.f2357c, false);
        for (p pVar : this.f2356b.k()) {
            if (pVar != null) {
                Fragment fragmentK = pVar.k();
                if (this.f2357c.f2115k.equals(fragmentK.f2118n)) {
                    fragmentK.f2117m = this.f2357c;
                    fragmentK.f2118n = null;
                }
            }
        }
        Fragment fragment2 = this.f2357c;
        String str2 = fragment2.f2118n;
        if (str2 != null) {
            fragment2.f2117m = this.f2356b.f(str2);
        }
        this.f2356b.q(this);
    }

    void h() {
        View view;
        if (FragmentManager.F0(3)) {
            Log.d("FragmentManager", "movefrom CREATE_VIEW: " + this.f2357c);
        }
        Fragment fragment = this.f2357c;
        ViewGroup viewGroup = fragment.L;
        if (viewGroup != null && (view = fragment.M) != null) {
            viewGroup.removeView(view);
        }
        this.f2357c.d1();
        this.f2355a.n(this.f2357c, false);
        Fragment fragment2 = this.f2357c;
        fragment2.L = null;
        fragment2.M = null;
        fragment2.Y = null;
        fragment2.Z.m(null);
        this.f2357c.f2124t = false;
    }

    void i() {
        if (FragmentManager.F0(3)) {
            Log.d("FragmentManager", "movefrom ATTACHED: " + this.f2357c);
        }
        this.f2357c.e1();
        boolean z6 = false;
        this.f2355a.e(this.f2357c, false);
        Fragment fragment = this.f2357c;
        fragment.f2109e = -1;
        fragment.f2129y = null;
        fragment.A = null;
        fragment.f2128x = null;
        if (fragment.f2122r && !fragment.f0()) {
            z6 = true;
        }
        if (z6 || this.f2356b.o().r(this.f2357c)) {
            if (FragmentManager.F0(3)) {
                Log.d("FragmentManager", "initState called for fragment: " + this.f2357c);
            }
            this.f2357c.a0();
        }
    }

    void j() {
        Fragment fragment = this.f2357c;
        if (fragment.f2123s && fragment.f2124t && !fragment.f2126v) {
            if (FragmentManager.F0(3)) {
                Log.d("FragmentManager", "moveto CREATE_VIEW: " + this.f2357c);
            }
            Fragment fragment2 = this.f2357c;
            fragment2.b1(fragment2.f1(fragment2.f2111g), null, this.f2357c.f2111g);
            View view = this.f2357c.M;
            if (view != null) {
                view.setSaveFromParentEnabled(false);
                Fragment fragment3 = this.f2357c;
                fragment3.M.setTag(q0.b.fragment_container_view_tag, fragment3);
                Fragment fragment4 = this.f2357c;
                if (fragment4.E) {
                    fragment4.M.setVisibility(8);
                }
                this.f2357c.s1();
                k kVar = this.f2355a;
                Fragment fragment5 = this.f2357c;
                kVar.m(fragment5, fragment5.M, fragment5.f2111g, false);
                this.f2357c.f2109e = 2;
            }
        }
    }

    Fragment k() {
        return this.f2357c;
    }

    void m() {
        ViewGroup viewGroup;
        ViewGroup viewGroup2;
        ViewGroup viewGroup3;
        if (this.f2358d) {
            if (FragmentManager.F0(2)) {
                Log.v("FragmentManager", "Ignoring re-entrant call to moveToExpectedState() for " + k());
                return;
            }
            return;
        }
        try {
            this.f2358d = true;
            while (true) {
                int iD = d();
                Fragment fragment = this.f2357c;
                int i7 = fragment.f2109e;
                if (iD == i7) {
                    if (FragmentManager.P && fragment.S) {
                        if (fragment.M != null && (viewGroup = fragment.L) != null) {
                            x xVarN = x.n(viewGroup, fragment.H());
                            if (this.f2357c.E) {
                                xVarN.c(this);
                            } else {
                                xVarN.e(this);
                            }
                        }
                        Fragment fragment2 = this.f2357c;
                        FragmentManager fragmentManager = fragment2.f2128x;
                        if (fragmentManager != null) {
                            fragmentManager.D0(fragment2);
                        }
                        Fragment fragment3 = this.f2357c;
                        fragment3.S = false;
                        fragment3.E0(fragment3.E);
                    }
                    return;
                }
                if (iD <= i7) {
                    switch (i7 - 1) {
                        case -1:
                            i();
                            break;
                        case 0:
                            g();
                            break;
                        case 1:
                            h();
                            this.f2357c.f2109e = 1;
                            break;
                        case 2:
                            fragment.f2124t = false;
                            fragment.f2109e = 2;
                            break;
                        case 3:
                            if (FragmentManager.F0(3)) {
                                Log.d("FragmentManager", "movefrom ACTIVITY_CREATED: " + this.f2357c);
                            }
                            Fragment fragment4 = this.f2357c;
                            if (fragment4.M != null && fragment4.f2112h == null) {
                                t();
                            }
                            Fragment fragment5 = this.f2357c;
                            if (fragment5.M != null && (viewGroup3 = fragment5.L) != null) {
                                x.n(viewGroup3, fragment5.H()).d(this);
                            }
                            this.f2357c.f2109e = 3;
                            break;
                        case 4:
                            w();
                            break;
                        case 5:
                            fragment.f2109e = 5;
                            break;
                        case 6:
                            n();
                            break;
                    }
                } else {
                    switch (i7 + 1) {
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
                            if (fragment.M != null && (viewGroup2 = fragment.L) != null) {
                                x.n(viewGroup2, fragment.H()).b(x.e.c.b(this.f2357c.M.getVisibility()), this);
                            }
                            this.f2357c.f2109e = 4;
                            break;
                        case 5:
                            v();
                            break;
                        case 6:
                            fragment.f2109e = 6;
                            break;
                        case 7:
                            p();
                            break;
                    }
                }
            }
        } finally {
            this.f2358d = false;
        }
    }

    void n() {
        if (FragmentManager.F0(3)) {
            Log.d("FragmentManager", "movefrom RESUMED: " + this.f2357c);
        }
        this.f2357c.k1();
        this.f2355a.f(this.f2357c, false);
    }

    void o(ClassLoader classLoader) {
        Bundle bundle = this.f2357c.f2111g;
        if (bundle == null) {
            return;
        }
        bundle.setClassLoader(classLoader);
        Fragment fragment = this.f2357c;
        fragment.f2112h = fragment.f2111g.getSparseParcelableArray("android:view_state");
        Fragment fragment2 = this.f2357c;
        fragment2.f2113i = fragment2.f2111g.getBundle("android:view_registry_state");
        Fragment fragment3 = this.f2357c;
        fragment3.f2118n = fragment3.f2111g.getString("android:target_state");
        Fragment fragment4 = this.f2357c;
        if (fragment4.f2118n != null) {
            fragment4.f2119o = fragment4.f2111g.getInt("android:target_req_state", 0);
        }
        Fragment fragment5 = this.f2357c;
        Boolean bool = fragment5.f2114j;
        if (bool != null) {
            fragment5.O = bool.booleanValue();
            this.f2357c.f2114j = null;
        } else {
            fragment5.O = fragment5.f2111g.getBoolean("android:user_visible_hint", true);
        }
        Fragment fragment6 = this.f2357c;
        if (fragment6.O) {
            return;
        }
        fragment6.N = true;
    }

    void p() {
        if (FragmentManager.F0(3)) {
            Log.d("FragmentManager", "moveto RESUMED: " + this.f2357c);
        }
        View viewZ = this.f2357c.z();
        if (viewZ != null && l(viewZ)) {
            boolean zRequestFocus = viewZ.requestFocus();
            if (FragmentManager.F0(2)) {
                StringBuilder sb = new StringBuilder();
                sb.append("requestFocus: Restoring focused view ");
                sb.append(viewZ);
                sb.append(" ");
                sb.append(zRequestFocus ? "succeeded" : "failed");
                sb.append(" on Fragment ");
                sb.append(this.f2357c);
                sb.append(" resulting in focused view ");
                sb.append(this.f2357c.M.findFocus());
                Log.v("FragmentManager", sb.toString());
            }
        }
        this.f2357c.D1(null);
        this.f2357c.o1();
        this.f2355a.i(this.f2357c, false);
        Fragment fragment = this.f2357c;
        fragment.f2111g = null;
        fragment.f2112h = null;
        fragment.f2113i = null;
    }

    Fragment.SavedState r() {
        Bundle bundleQ;
        if (this.f2357c.f2109e <= -1 || (bundleQ = q()) == null) {
            return null;
        }
        return new Fragment.SavedState(bundleQ);
    }

    FragmentState s() {
        FragmentState fragmentState = new FragmentState(this.f2357c);
        Fragment fragment = this.f2357c;
        if (fragment.f2109e <= -1 || fragmentState.f2238r != null) {
            fragmentState.f2238r = fragment.f2111g;
        } else {
            Bundle bundleQ = q();
            fragmentState.f2238r = bundleQ;
            if (this.f2357c.f2118n != null) {
                if (bundleQ == null) {
                    fragmentState.f2238r = new Bundle();
                }
                fragmentState.f2238r.putString("android:target_state", this.f2357c.f2118n);
                int i7 = this.f2357c.f2119o;
                if (i7 != 0) {
                    fragmentState.f2238r.putInt("android:target_req_state", i7);
                }
            }
        }
        return fragmentState;
    }

    void t() {
        if (this.f2357c.M == null) {
            return;
        }
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        this.f2357c.M.saveHierarchyState(sparseArray);
        if (sparseArray.size() > 0) {
            this.f2357c.f2112h = sparseArray;
        }
        Bundle bundle = new Bundle();
        this.f2357c.Y.h(bundle);
        if (bundle.isEmpty()) {
            return;
        }
        this.f2357c.f2113i = bundle;
    }

    void u(int i7) {
        this.f2359e = i7;
    }

    void v() {
        if (FragmentManager.F0(3)) {
            Log.d("FragmentManager", "moveto STARTED: " + this.f2357c);
        }
        this.f2357c.q1();
        this.f2355a.k(this.f2357c, false);
    }

    void w() {
        if (FragmentManager.F0(3)) {
            Log.d("FragmentManager", "movefrom STARTED: " + this.f2357c);
        }
        this.f2357c.r1();
        this.f2355a.l(this.f2357c, false);
    }

    p(k kVar, q qVar, ClassLoader classLoader, h hVar, FragmentState fragmentState) {
        this.f2355a = kVar;
        this.f2356b = qVar;
        Fragment fragmentA = hVar.a(classLoader, fragmentState.f2226e);
        this.f2357c = fragmentA;
        Bundle bundle = fragmentState.f2235o;
        if (bundle != null) {
            bundle.setClassLoader(classLoader);
        }
        fragmentA.C1(fragmentState.f2235o);
        fragmentA.f2115k = fragmentState.f2227g;
        fragmentA.f2123s = fragmentState.f2228h;
        fragmentA.f2125u = true;
        fragmentA.B = fragmentState.f2229i;
        fragmentA.C = fragmentState.f2230j;
        fragmentA.D = fragmentState.f2231k;
        fragmentA.G = fragmentState.f2232l;
        fragmentA.f2122r = fragmentState.f2233m;
        fragmentA.F = fragmentState.f2234n;
        fragmentA.E = fragmentState.f2236p;
        fragmentA.W = i.c.values()[fragmentState.f2237q];
        Bundle bundle2 = fragmentState.f2238r;
        if (bundle2 != null) {
            fragmentA.f2111g = bundle2;
        } else {
            fragmentA.f2111g = new Bundle();
        }
        if (FragmentManager.F0(2)) {
            Log.v("FragmentManager", "Instantiated fragment " + fragmentA);
        }
    }

    p(k kVar, q qVar, Fragment fragment, FragmentState fragmentState) {
        this.f2355a = kVar;
        this.f2356b = qVar;
        this.f2357c = fragment;
        fragment.f2112h = null;
        fragment.f2113i = null;
        fragment.f2127w = 0;
        fragment.f2124t = false;
        fragment.f2121q = false;
        Fragment fragment2 = fragment.f2117m;
        fragment.f2118n = fragment2 != null ? fragment2.f2115k : null;
        fragment.f2117m = null;
        Bundle bundle = fragmentState.f2238r;
        if (bundle != null) {
            fragment.f2111g = bundle;
        } else {
            fragment.f2111g = new Bundle();
        }
    }
}
