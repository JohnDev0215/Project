package androidx.fragment.app;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.d;

/* JADX INFO: compiled from: FragmentStateManager.java */
/* JADX INFO: loaded from: classes.dex */
class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final i f1476a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Fragment f1477b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f1478c = -1;

    /* JADX INFO: compiled from: FragmentStateManager.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1479a;

        static {
            int[] iArr = new int[d.b.values().length];
            f1479a = iArr;
            try {
                iArr[d.b.RESUMED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1479a[d.b.STARTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1479a[d.b.CREATED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    m(i iVar, Fragment fragment) {
        this.f1476a = iVar;
        this.f1477b = fragment;
    }

    private Bundle n() {
        Bundle bundle = new Bundle();
        this.f1477b.Y0(bundle);
        this.f1476a.j(this.f1477b, bundle, false);
        if (bundle.isEmpty()) {
            bundle = null;
        }
        if (this.f1477b.I != null) {
            p();
        }
        if (this.f1477b.f1364e != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", this.f1477b.f1364e);
        }
        if (!this.f1477b.K) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android:user_visible_hint", this.f1477b.K);
        }
        return bundle;
    }

    void a() {
        if (j.q0(3)) {
            Log.d("FragmentManager", "moveto ACTIVITY_CREATED: " + this.f1477b);
        }
        Fragment fragment = this.f1477b;
        fragment.E0(fragment.f1363d);
        i iVar = this.f1476a;
        Fragment fragment2 = this.f1477b;
        iVar.a(fragment2, fragment2.f1363d, false);
    }

    void b(g<?> gVar, j jVar, Fragment fragment) {
        Fragment fragment2 = this.f1477b;
        fragment2.u = gVar;
        fragment2.w = fragment;
        fragment2.t = jVar;
        this.f1476a.g(fragment2, gVar.h(), false);
        this.f1477b.F0();
        Fragment fragment3 = this.f1477b;
        Fragment fragment4 = fragment3.w;
        if (fragment4 == null) {
            gVar.k(fragment3);
        } else {
            fragment4.b0(fragment3);
        }
        this.f1476a.b(this.f1477b, gVar.h(), false);
    }

    int c() {
        int iMin = this.f1478c;
        Fragment fragment = this.f1477b;
        if (fragment.o) {
            iMin = fragment.p ? Math.max(iMin, 1) : iMin < 2 ? Math.min(iMin, fragment.f1362c) : Math.min(iMin, 1);
        }
        if (!this.f1477b.m) {
            iMin = Math.min(iMin, 1);
        }
        Fragment fragment2 = this.f1477b;
        if (fragment2.n) {
            iMin = fragment2.R() ? Math.min(iMin, 1) : Math.min(iMin, -1);
        }
        Fragment fragment3 = this.f1477b;
        if (fragment3.J && fragment3.f1362c < 3) {
            iMin = Math.min(iMin, 2);
        }
        int i2 = a.f1479a[this.f1477b.S.ordinal()];
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? Math.min(iMin, -1) : Math.min(iMin, 1) : Math.min(iMin, 3) : iMin;
    }

    void d() {
        if (j.q0(3)) {
            Log.d("FragmentManager", "moveto CREATED: " + this.f1477b);
        }
        Fragment fragment = this.f1477b;
        if (fragment.R) {
            fragment.e1(fragment.f1363d);
            this.f1477b.f1362c = 1;
            return;
        }
        this.f1476a.h(fragment, fragment.f1363d, false);
        Fragment fragment2 = this.f1477b;
        fragment2.I0(fragment2.f1363d);
        i iVar = this.f1476a;
        Fragment fragment3 = this.f1477b;
        iVar.c(fragment3, fragment3.f1363d, false);
    }

    void e(d dVar) {
        String resourceName;
        if (this.f1477b.o) {
            return;
        }
        if (j.q0(3)) {
            Log.d("FragmentManager", "moveto CREATE_VIEW: " + this.f1477b);
        }
        ViewGroup viewGroup = null;
        Fragment fragment = this.f1477b;
        ViewGroup viewGroup2 = fragment.H;
        if (viewGroup2 != null) {
            viewGroup = viewGroup2;
        } else {
            int i2 = fragment.y;
            if (i2 != 0) {
                if (i2 == -1) {
                    throw new IllegalArgumentException("Cannot create fragment " + this.f1477b + " for a container view with no id");
                }
                viewGroup = (ViewGroup) dVar.c(i2);
                if (viewGroup == null) {
                    Fragment fragment2 = this.f1477b;
                    if (!fragment2.q) {
                        try {
                            resourceName = fragment2.C().getResourceName(this.f1477b.y);
                        } catch (Resources.NotFoundException unused) {
                            resourceName = "unknown";
                        }
                        throw new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(this.f1477b.y) + " (" + resourceName + ") for fragment " + this.f1477b);
                    }
                }
            }
        }
        Fragment fragment3 = this.f1477b;
        fragment3.H = viewGroup;
        fragment3.K0(fragment3.O0(fragment3.f1363d), viewGroup, this.f1477b.f1363d);
        View view = this.f1477b.I;
        if (view != null) {
            boolean z = false;
            view.setSaveFromParentEnabled(false);
            Fragment fragment4 = this.f1477b;
            fragment4.I.setTag(b.k.b.fragment_container_view_tag, fragment4);
            if (viewGroup != null) {
                viewGroup.addView(this.f1477b.I);
            }
            Fragment fragment5 = this.f1477b;
            if (fragment5.A) {
                fragment5.I.setVisibility(8);
            }
            b.g.k.t.g0(this.f1477b.I);
            Fragment fragment6 = this.f1477b;
            fragment6.C0(fragment6.I, fragment6.f1363d);
            i iVar = this.f1476a;
            Fragment fragment7 = this.f1477b;
            iVar.m(fragment7, fragment7.I, fragment7.f1363d, false);
            Fragment fragment8 = this.f1477b;
            if (fragment8.I.getVisibility() == 0 && this.f1477b.H != null) {
                z = true;
            }
            fragment8.N = z;
        }
    }

    void f(g<?> gVar, l lVar) {
        if (j.q0(3)) {
            Log.d("FragmentManager", "movefrom CREATED: " + this.f1477b);
        }
        Fragment fragment = this.f1477b;
        boolean zIsChangingConfigurations = true;
        boolean z = fragment.n && !fragment.R();
        if (!(z || lVar.n(this.f1477b))) {
            this.f1477b.f1362c = 0;
            return;
        }
        if (gVar instanceof androidx.lifecycle.s) {
            zIsChangingConfigurations = lVar.l();
        } else if (gVar.h() instanceof Activity) {
            zIsChangingConfigurations = true ^ ((Activity) gVar.h()).isChangingConfigurations();
        }
        if (z || zIsChangingConfigurations) {
            lVar.f(this.f1477b);
        }
        this.f1477b.L0();
        this.f1476a.d(this.f1477b, false);
    }

    void g(l lVar) {
        if (j.q0(3)) {
            Log.d("FragmentManager", "movefrom ATTACHED: " + this.f1477b);
        }
        this.f1477b.N0();
        boolean z = false;
        this.f1476a.e(this.f1477b, false);
        Fragment fragment = this.f1477b;
        fragment.f1362c = -1;
        fragment.u = null;
        fragment.w = null;
        fragment.t = null;
        if (fragment.n && !fragment.R()) {
            z = true;
        }
        if (z || lVar.n(this.f1477b)) {
            if (j.q0(3)) {
                Log.d("FragmentManager", "initState called for fragment: " + this.f1477b);
            }
            this.f1477b.N();
        }
    }

    void h() {
        Fragment fragment = this.f1477b;
        if (fragment.o && fragment.p && !fragment.r) {
            if (j.q0(3)) {
                Log.d("FragmentManager", "moveto CREATE_VIEW: " + this.f1477b);
            }
            Fragment fragment2 = this.f1477b;
            fragment2.K0(fragment2.O0(fragment2.f1363d), null, this.f1477b.f1363d);
            View view = this.f1477b.I;
            if (view != null) {
                view.setSaveFromParentEnabled(false);
                Fragment fragment3 = this.f1477b;
                fragment3.I.setTag(b.k.b.fragment_container_view_tag, fragment3);
                Fragment fragment4 = this.f1477b;
                if (fragment4.A) {
                    fragment4.I.setVisibility(8);
                }
                Fragment fragment5 = this.f1477b;
                fragment5.C0(fragment5.I, fragment5.f1363d);
                i iVar = this.f1476a;
                Fragment fragment6 = this.f1477b;
                iVar.m(fragment6, fragment6.I, fragment6.f1363d, false);
            }
        }
    }

    Fragment i() {
        return this.f1477b;
    }

    void j() {
        if (j.q0(3)) {
            Log.d("FragmentManager", "movefrom RESUMED: " + this.f1477b);
        }
        this.f1477b.T0();
        this.f1476a.f(this.f1477b, false);
    }

    void k(ClassLoader classLoader) {
        Bundle bundle = this.f1477b.f1363d;
        if (bundle == null) {
            return;
        }
        bundle.setClassLoader(classLoader);
        Fragment fragment = this.f1477b;
        fragment.f1364e = fragment.f1363d.getSparseParcelableArray("android:view_state");
        Fragment fragment2 = this.f1477b;
        fragment2.j = fragment2.f1363d.getString("android:target_state");
        Fragment fragment3 = this.f1477b;
        if (fragment3.j != null) {
            fragment3.k = fragment3.f1363d.getInt("android:target_req_state", 0);
        }
        Fragment fragment4 = this.f1477b;
        Boolean bool = fragment4.f1365f;
        if (bool != null) {
            fragment4.K = bool.booleanValue();
            this.f1477b.f1365f = null;
        } else {
            fragment4.K = fragment4.f1363d.getBoolean("android:user_visible_hint", true);
        }
        Fragment fragment5 = this.f1477b;
        if (fragment5.K) {
            return;
        }
        fragment5.J = true;
    }

    void l() {
        if (j.q0(3)) {
            Log.d("FragmentManager", "moveto RESTORE_VIEW_STATE: " + this.f1477b);
        }
        Fragment fragment = this.f1477b;
        if (fragment.I != null) {
            fragment.f1(fragment.f1363d);
        }
        this.f1477b.f1363d = null;
    }

    void m() {
        if (j.q0(3)) {
            Log.d("FragmentManager", "moveto RESUMED: " + this.f1477b);
        }
        this.f1477b.X0();
        this.f1476a.i(this.f1477b, false);
        Fragment fragment = this.f1477b;
        fragment.f1363d = null;
        fragment.f1364e = null;
    }

    FragmentState o() {
        FragmentState fragmentState = new FragmentState(this.f1477b);
        Fragment fragment = this.f1477b;
        if (fragment.f1362c <= -1 || fragmentState.n != null) {
            fragmentState.n = fragment.f1363d;
        } else {
            Bundle bundleN = n();
            fragmentState.n = bundleN;
            if (this.f1477b.j != null) {
                if (bundleN == null) {
                    fragmentState.n = new Bundle();
                }
                fragmentState.n.putString("android:target_state", this.f1477b.j);
                int i2 = this.f1477b.k;
                if (i2 != 0) {
                    fragmentState.n.putInt("android:target_req_state", i2);
                }
            }
        }
        return fragmentState;
    }

    void p() {
        if (this.f1477b.I == null) {
            return;
        }
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        this.f1477b.I.saveHierarchyState(sparseArray);
        if (sparseArray.size() > 0) {
            this.f1477b.f1364e = sparseArray;
        }
    }

    void q(int i2) {
        this.f1478c = i2;
    }

    void r() {
        if (j.q0(3)) {
            Log.d("FragmentManager", "moveto STARTED: " + this.f1477b);
        }
        this.f1477b.Z0();
        this.f1476a.k(this.f1477b, false);
    }

    void s() {
        if (j.q0(3)) {
            Log.d("FragmentManager", "movefrom STARTED: " + this.f1477b);
        }
        this.f1477b.a1();
        this.f1476a.l(this.f1477b, false);
    }

    m(i iVar, ClassLoader classLoader, f fVar, FragmentState fragmentState) {
        this.f1476a = iVar;
        Fragment fragmentA = fVar.a(classLoader, fragmentState.f1392b);
        this.f1477b = fragmentA;
        Bundle bundle = fragmentState.k;
        if (bundle != null) {
            bundle.setClassLoader(classLoader);
        }
        fragmentA.i1(fragmentState.k);
        fragmentA.f1366g = fragmentState.f1393c;
        fragmentA.o = fragmentState.f1394d;
        fragmentA.q = true;
        fragmentA.x = fragmentState.f1395e;
        fragmentA.y = fragmentState.f1396f;
        fragmentA.z = fragmentState.f1397g;
        fragmentA.C = fragmentState.f1398h;
        fragmentA.n = fragmentState.f1399i;
        fragmentA.B = fragmentState.j;
        fragmentA.A = fragmentState.l;
        fragmentA.S = d.b.values()[fragmentState.m];
        Bundle bundle2 = fragmentState.n;
        if (bundle2 != null) {
            fragmentA.f1363d = bundle2;
        } else {
            fragmentA.f1363d = new Bundle();
        }
        if (j.q0(2)) {
            Log.v("FragmentManager", "Instantiated fragment " + fragmentA);
        }
    }

    m(i iVar, Fragment fragment, FragmentState fragmentState) {
        this.f1476a = iVar;
        this.f1477b = fragment;
        fragment.f1364e = null;
        fragment.s = 0;
        fragment.p = false;
        fragment.m = false;
        Fragment fragment2 = fragment.f1368i;
        fragment.j = fragment2 != null ? fragment2.f1366g : null;
        fragment.f1368i = null;
        Bundle bundle = fragmentState.n;
        if (bundle != null) {
            fragment.f1363d = bundle;
        } else {
            fragment.f1363d = new Bundle();
        }
    }
}
