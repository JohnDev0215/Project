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
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.fragment.R$id;
import androidx.fragment.app.SpecialEffectsController;
import androidx.lifecycle.Lifecycle;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final k f703a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final q f704b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @NonNull
    private final Fragment f705c;
    private boolean d = false;
    private int e = -1;
    private androidx.core.os.b f;
    private androidx.core.os.b g;
    private androidx.core.os.b h;

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements View.OnAttachStateChangeListener {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f706c;

        a(p pVar, View view) {
            this.f706c = view;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            this.f706c.removeOnAttachStateChangeListener(this);
            ViewCompat.J(this.f706c);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f707a;

        static {
            int[] iArr = new int[Lifecycle.State.values().length];
            f707a = iArr;
            try {
                iArr[Lifecycle.State.RESUMED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f707a[Lifecycle.State.STARTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f707a[Lifecycle.State.CREATED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    p(@NonNull k kVar, @NonNull q qVar, @NonNull Fragment fragment) {
        this.f703a = kVar;
        this.f704b = qVar;
        this.f705c = fragment;
    }

    private Bundle o() {
        Bundle bundle = new Bundle();
        this.f705c.Y0(bundle);
        this.f703a.j(this.f705c, bundle, false);
        if (bundle.isEmpty()) {
            bundle = null;
        }
        if (this.f705c.J != null) {
            q();
        }
        if (this.f705c.e != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", this.f705c.e);
        }
        if (this.f705c.f != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBundle("android:view_registry_state", this.f705c.f);
        }
        if (!this.f705c.L) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android:user_visible_hint", this.f705c.L);
        }
        return bundle;
    }

    void a() {
        if (FragmentManager.D0(3)) {
            Log.d("FragmentManager", "moveto ACTIVITY_CREATED: " + this.f705c);
        }
        Fragment fragment = this.f705c;
        fragment.E0(fragment.d);
        k kVar = this.f703a;
        Fragment fragment2 = this.f705c;
        kVar.a(fragment2, fragment2.d, false);
    }

    void b() {
        if (FragmentManager.D0(3)) {
            Log.d("FragmentManager", "moveto ATTACHED: " + this.f705c);
        }
        Fragment fragment = this.f705c;
        Fragment fragment2 = fragment.j;
        p pVarM = null;
        if (fragment2 != null) {
            p pVarM2 = this.f704b.m(fragment2.h);
            if (pVarM2 == null) {
                throw new IllegalStateException("Fragment " + this.f705c + " declared target fragment " + this.f705c.j + " that does not belong to this FragmentManager!");
            }
            Fragment fragment3 = this.f705c;
            fragment3.k = fragment3.j.h;
            fragment3.j = null;
            pVarM = pVarM2;
        } else {
            String str = fragment.k;
            if (str != null && (pVarM = this.f704b.m(str)) == null) {
                throw new IllegalStateException("Fragment " + this.f705c + " declared target fragment " + this.f705c.k + " that does not belong to this FragmentManager!");
            }
        }
        if (pVarM != null && (FragmentManager.P || pVarM.j().f612c < 1)) {
            pVarM.k();
        }
        Fragment fragment4 = this.f705c;
        fragment4.v = fragment4.u.s0();
        Fragment fragment5 = this.f705c;
        fragment5.x = fragment5.u.v0();
        this.f703a.g(this.f705c, false);
        this.f705c.F0();
        this.f703a.b(this.f705c, false);
    }

    int c() {
        Fragment fragment;
        ViewGroup viewGroup;
        Fragment fragment2 = this.f705c;
        if (fragment2.u == null) {
            return fragment2.f612c;
        }
        int iMin = this.e;
        if (fragment2.p) {
            iMin = fragment2.q ? Math.max(iMin, 2) : iMin < 4 ? Math.min(iMin, fragment2.f612c) : Math.min(iMin, 1);
        }
        if (!this.f705c.n) {
            iMin = Math.min(iMin, 1);
        }
        SpecialEffectsController.Operation.Type typeJ = null;
        if (FragmentManager.P && (viewGroup = (fragment = this.f705c).I) != null) {
            typeJ = SpecialEffectsController.l(viewGroup, fragment.B()).j(this);
        }
        if (typeJ == SpecialEffectsController.Operation.Type.ADD) {
            iMin = Math.min(iMin, 6);
        } else if (typeJ == SpecialEffectsController.Operation.Type.REMOVE) {
            iMin = Math.max(iMin, 3);
        } else {
            Fragment fragment3 = this.f705c;
            if (fragment3.o) {
                iMin = fragment3.Q() ? Math.min(iMin, 1) : Math.min(iMin, -1);
            }
        }
        Fragment fragment4 = this.f705c;
        if (fragment4.K && fragment4.f612c < 5) {
            iMin = Math.min(iMin, 4);
        }
        int i = b.f707a[this.f705c.S.ordinal()];
        return i != 1 ? i != 2 ? i != 3 ? Math.min(iMin, -1) : Math.min(iMin, 1) : Math.min(iMin, 5) : iMin;
    }

    void d() {
        if (FragmentManager.D0(3)) {
            Log.d("FragmentManager", "moveto CREATED: " + this.f705c);
        }
        Fragment fragment = this.f705c;
        if (fragment.R) {
            fragment.f1(fragment.d);
            this.f705c.f612c = 1;
            return;
        }
        this.f703a.h(fragment, fragment.d, false);
        Fragment fragment2 = this.f705c;
        fragment2.I0(fragment2.d);
        k kVar = this.f703a;
        Fragment fragment3 = this.f705c;
        kVar.c(fragment3, fragment3.d, false);
    }

    void e() {
        String resourceName;
        if (this.f705c.p) {
            return;
        }
        if (FragmentManager.D0(3)) {
            Log.d("FragmentManager", "moveto CREATE_VIEW: " + this.f705c);
        }
        Fragment fragment = this.f705c;
        LayoutInflater layoutInflaterO0 = fragment.O0(fragment.d);
        ViewGroup viewGroup = null;
        Fragment fragment2 = this.f705c;
        ViewGroup viewGroup2 = fragment2.I;
        if (viewGroup2 != null) {
            viewGroup = viewGroup2;
        } else {
            int i = fragment2.z;
            if (i != 0) {
                if (i == -1) {
                    throw new IllegalArgumentException("Cannot create fragment " + this.f705c + " for a container view with no id");
                }
                viewGroup = (ViewGroup) fragment2.u.n0().g(this.f705c.z);
                if (viewGroup == null) {
                    Fragment fragment3 = this.f705c;
                    if (!fragment3.r) {
                        try {
                            resourceName = fragment3.D().getResourceName(this.f705c.z);
                        } catch (Resources.NotFoundException unused) {
                            resourceName = "unknown";
                        }
                        throw new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(this.f705c.z) + " (" + resourceName + ") for fragment " + this.f705c);
                    }
                }
            }
        }
        Fragment fragment4 = this.f705c;
        fragment4.I = viewGroup;
        fragment4.K0(layoutInflaterO0, viewGroup, fragment4.d);
        View view = this.f705c.J;
        if (view != null) {
            boolean z = false;
            view.setSaveFromParentEnabled(false);
            Fragment fragment5 = this.f705c;
            fragment5.J.setTag(R$id.fragment_container_view_tag, fragment5);
            if (viewGroup != null) {
                viewGroup.addView(this.f705c.J, this.f704b.j(this.f705c));
                if (FragmentManager.P) {
                    this.f705c.J.setVisibility(4);
                }
            }
            Fragment fragment6 = this.f705c;
            if (fragment6.B) {
                fragment6.J.setVisibility(8);
            }
            if (ViewCompat.A(this.f705c.J)) {
                ViewCompat.J(this.f705c.J);
            } else {
                View view2 = this.f705c.J;
                view2.addOnAttachStateChangeListener(new a(this, view2));
            }
            this.f705c.b1();
            k kVar = this.f703a;
            Fragment fragment7 = this.f705c;
            kVar.m(fragment7, fragment7.J, fragment7.d, false);
            Fragment fragment8 = this.f705c;
            if (fragment8.J.getVisibility() == 0 && this.f705c.I != null) {
                z = true;
            }
            fragment8.N = z;
        }
        this.f705c.f612c = 2;
    }

    void f() {
        Fragment fragmentF;
        if (FragmentManager.D0(3)) {
            Log.d("FragmentManager", "movefrom CREATED: " + this.f705c);
        }
        Fragment fragment = this.f705c;
        boolean zIsChangingConfigurations = true;
        boolean z = fragment.o && !fragment.Q();
        if (!(z || this.f704b.o().o(this.f705c))) {
            String str = this.f705c.k;
            if (str != null && (fragmentF = this.f704b.f(str)) != null && fragmentF.D) {
                this.f705c.j = fragmentF;
            }
            this.f705c.f612c = 0;
            return;
        }
        i<?> iVar = this.f705c.v;
        if (iVar instanceof androidx.lifecycle.q) {
            zIsChangingConfigurations = this.f704b.o().l();
        } else if (iVar.k() instanceof Activity) {
            zIsChangingConfigurations = true ^ ((Activity) iVar.k()).isChangingConfigurations();
        }
        if (z || zIsChangingConfigurations) {
            this.f704b.o().f(this.f705c);
        }
        this.f705c.L0();
        this.f703a.d(this.f705c, false);
        for (p pVar : this.f704b.k()) {
            if (pVar != null) {
                Fragment fragmentJ = pVar.j();
                if (this.f705c.h.equals(fragmentJ.k)) {
                    fragmentJ.j = this.f705c;
                    fragmentJ.k = null;
                }
            }
        }
        Fragment fragment2 = this.f705c;
        String str2 = fragment2.k;
        if (str2 != null) {
            fragment2.j = this.f704b.f(str2);
        }
        this.f704b.q(this);
    }

    void g() {
        this.f705c.M0();
        this.f703a.n(this.f705c, false);
        Fragment fragment = this.f705c;
        fragment.I = null;
        fragment.J = null;
        fragment.U = null;
        fragment.V.l(null);
        this.f705c.q = false;
    }

    void h() {
        if (FragmentManager.D0(3)) {
            Log.d("FragmentManager", "movefrom ATTACHED: " + this.f705c);
        }
        this.f705c.N0();
        boolean z = false;
        this.f703a.e(this.f705c, false);
        Fragment fragment = this.f705c;
        fragment.f612c = -1;
        fragment.v = null;
        fragment.x = null;
        fragment.u = null;
        if (fragment.o && !fragment.Q()) {
            z = true;
        }
        if (z || this.f704b.o().o(this.f705c)) {
            if (FragmentManager.D0(3)) {
                Log.d("FragmentManager", "initState called for fragment: " + this.f705c);
            }
            this.f705c.N();
        }
    }

    void i() {
        Fragment fragment = this.f705c;
        if (fragment.p && fragment.q && !fragment.s) {
            if (FragmentManager.D0(3)) {
                Log.d("FragmentManager", "moveto CREATE_VIEW: " + this.f705c);
            }
            Fragment fragment2 = this.f705c;
            fragment2.K0(fragment2.O0(fragment2.d), null, this.f705c.d);
            View view = this.f705c.J;
            if (view != null) {
                view.setSaveFromParentEnabled(false);
                Fragment fragment3 = this.f705c;
                fragment3.J.setTag(R$id.fragment_container_view_tag, fragment3);
                Fragment fragment4 = this.f705c;
                if (fragment4.B) {
                    fragment4.J.setVisibility(8);
                }
                this.f705c.b1();
                k kVar = this.f703a;
                Fragment fragment5 = this.f705c;
                kVar.m(fragment5, fragment5.J, fragment5.d, false);
                this.f705c.f612c = 2;
            }
        }
    }

    @NonNull
    Fragment j() {
        return this.f705c;
    }

    void k() {
        if (this.d) {
            if (FragmentManager.D0(2)) {
                Log.v("FragmentManager", "Ignoring re-entrant call to moveToExpectedState() for " + j());
                return;
            }
            return;
        }
        try {
            this.d = true;
            while (true) {
                int iC = c();
                if (iC == this.f705c.f612c) {
                    if (FragmentManager.P && this.f705c.O) {
                        if (this.f705c.J != null && this.f705c.I != null) {
                            if (this.g != null) {
                                this.g.a();
                            }
                            SpecialEffectsController specialEffectsControllerL = SpecialEffectsController.l(this.f705c.I, this.f705c.B());
                            androidx.core.os.b bVar = new androidx.core.os.b();
                            this.g = bVar;
                            if (this.f705c.B) {
                                specialEffectsControllerL.d(this, bVar);
                            } else {
                                specialEffectsControllerL.f(this, bVar);
                            }
                        }
                        this.f705c.O = false;
                        this.f705c.n0(this.f705c.B);
                    }
                    return;
                }
                if (iC <= this.f705c.f612c) {
                    int i = this.f705c.f612c - 1;
                    if (this.f != null) {
                        this.f.a();
                    }
                    switch (i) {
                        case -1:
                            h();
                            break;
                        case 0:
                            f();
                            break;
                        case 1:
                            this.f705c.f612c = 1;
                            break;
                        case 2:
                            g();
                            this.f705c.f612c = 2;
                            break;
                        case 3:
                            if (FragmentManager.D0(3)) {
                                Log.d("FragmentManager", "movefrom ACTIVITY_CREATED: " + this.f705c);
                            }
                            if (this.f705c.J != null && this.f705c.e == null) {
                                q();
                            }
                            if (this.f705c.J != null && this.f705c.I != null && this.e > -1) {
                                SpecialEffectsController specialEffectsControllerL2 = SpecialEffectsController.l(this.f705c.I, this.f705c.B());
                                if (this.g != null) {
                                    this.g.a();
                                }
                                androidx.core.os.b bVar2 = new androidx.core.os.b();
                                this.h = bVar2;
                                specialEffectsControllerL2.e(this, bVar2);
                            }
                            this.f705c.f612c = 3;
                            break;
                        case 4:
                            t();
                            break;
                        case 5:
                            this.f705c.f612c = 5;
                            break;
                        case 6:
                            l();
                            break;
                    }
                } else {
                    int i2 = this.f705c.f612c + 1;
                    if (this.h != null) {
                        this.h.a();
                    }
                    switch (i2) {
                        case 0:
                            b();
                            break;
                        case 1:
                            d();
                            break;
                        case 2:
                            i();
                            e();
                            break;
                        case 3:
                            a();
                            break;
                        case 4:
                            if (this.f705c.J != null && this.f705c.I != null) {
                                SpecialEffectsController specialEffectsControllerL3 = SpecialEffectsController.l(this.f705c.I, this.f705c.B());
                                if (this.g != null) {
                                    this.g.a();
                                }
                                androidx.core.os.b bVar3 = new androidx.core.os.b();
                                this.f = bVar3;
                                specialEffectsControllerL3.c(this, bVar3);
                            }
                            this.f705c.f612c = 4;
                            break;
                        case 5:
                            s();
                            break;
                        case 6:
                            this.f705c.f612c = 6;
                            break;
                        case 7:
                            n();
                            break;
                    }
                }
            }
        } finally {
            this.d = false;
        }
    }

    void l() {
        if (FragmentManager.D0(3)) {
            Log.d("FragmentManager", "movefrom RESUMED: " + this.f705c);
        }
        this.f705c.T0();
        this.f703a.f(this.f705c, false);
    }

    void m(@NonNull ClassLoader classLoader) {
        Bundle bundle = this.f705c.d;
        if (bundle == null) {
            return;
        }
        bundle.setClassLoader(classLoader);
        Fragment fragment = this.f705c;
        fragment.e = fragment.d.getSparseParcelableArray("android:view_state");
        Fragment fragment2 = this.f705c;
        fragment2.f = fragment2.d.getBundle("android:view_registry_state");
        Fragment fragment3 = this.f705c;
        fragment3.k = fragment3.d.getString("android:target_state");
        Fragment fragment4 = this.f705c;
        if (fragment4.k != null) {
            fragment4.l = fragment4.d.getInt("android:target_req_state", 0);
        }
        Fragment fragment5 = this.f705c;
        Boolean bool = fragment5.g;
        if (bool != null) {
            fragment5.L = bool.booleanValue();
            this.f705c.g = null;
        } else {
            fragment5.L = fragment5.d.getBoolean("android:user_visible_hint", true);
        }
        Fragment fragment6 = this.f705c;
        if (fragment6.L) {
            return;
        }
        fragment6.K = true;
    }

    void n() {
        if (FragmentManager.D0(3)) {
            Log.d("FragmentManager", "moveto RESUMED: " + this.f705c);
        }
        this.f705c.X0();
        this.f703a.i(this.f705c, false);
        Fragment fragment = this.f705c;
        fragment.d = null;
        fragment.e = null;
        fragment.f = null;
    }

    @NonNull
    FragmentState p() {
        FragmentState fragmentState = new FragmentState(this.f705c);
        if (this.f705c.f612c <= -1 || fragmentState.o != null) {
            fragmentState.o = this.f705c.d;
        } else {
            Bundle bundleO = o();
            fragmentState.o = bundleO;
            if (this.f705c.k != null) {
                if (bundleO == null) {
                    fragmentState.o = new Bundle();
                }
                fragmentState.o.putString("android:target_state", this.f705c.k);
                int i = this.f705c.l;
                if (i != 0) {
                    fragmentState.o.putInt("android:target_req_state", i);
                }
            }
        }
        return fragmentState;
    }

    void q() {
        if (this.f705c.J == null) {
            return;
        }
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        this.f705c.J.saveHierarchyState(sparseArray);
        if (sparseArray.size() > 0) {
            this.f705c.e = sparseArray;
        }
        Bundle bundle = new Bundle();
        this.f705c.U.h(bundle);
        if (bundle.isEmpty()) {
            return;
        }
        this.f705c.f = bundle;
    }

    void r(int i) {
        this.e = i;
    }

    void s() {
        if (FragmentManager.D0(3)) {
            Log.d("FragmentManager", "moveto STARTED: " + this.f705c);
        }
        this.f705c.Z0();
        this.f703a.k(this.f705c, false);
    }

    void t() {
        if (FragmentManager.D0(3)) {
            Log.d("FragmentManager", "movefrom STARTED: " + this.f705c);
        }
        this.f705c.a1();
        this.f703a.l(this.f705c, false);
    }

    p(@NonNull k kVar, @NonNull q qVar, @NonNull ClassLoader classLoader, @NonNull h hVar, @NonNull FragmentState fragmentState) {
        this.f703a = kVar;
        this.f704b = qVar;
        this.f705c = hVar.a(classLoader, fragmentState.f644c);
        Bundle bundle = fragmentState.l;
        if (bundle != null) {
            bundle.setClassLoader(classLoader);
        }
        this.f705c.k1(fragmentState.l);
        Fragment fragment = this.f705c;
        fragment.h = fragmentState.d;
        fragment.p = fragmentState.e;
        fragment.r = true;
        fragment.y = fragmentState.f;
        fragment.z = fragmentState.g;
        fragment.A = fragmentState.h;
        fragment.D = fragmentState.i;
        fragment.o = fragmentState.j;
        fragment.C = fragmentState.k;
        fragment.B = fragmentState.m;
        fragment.S = Lifecycle.State.values()[fragmentState.n];
        Bundle bundle2 = fragmentState.o;
        if (bundle2 != null) {
            this.f705c.d = bundle2;
        } else {
            this.f705c.d = new Bundle();
        }
        if (FragmentManager.D0(2)) {
            Log.v("FragmentManager", "Instantiated fragment " + this.f705c);
        }
    }

    p(@NonNull k kVar, @NonNull q qVar, @NonNull Fragment fragment, @NonNull FragmentState fragmentState) {
        this.f703a = kVar;
        this.f704b = qVar;
        this.f705c = fragment;
        fragment.e = null;
        fragment.f = null;
        fragment.t = 0;
        fragment.q = false;
        fragment.n = false;
        Fragment fragment2 = fragment.j;
        fragment.k = fragment2 != null ? fragment2.h : null;
        Fragment fragment3 = this.f705c;
        fragment3.j = null;
        Bundle bundle = fragmentState.o;
        if (bundle != null) {
            fragment3.d = bundle;
        } else {
            fragment3.d = new Bundle();
        }
    }
}
