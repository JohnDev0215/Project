package androidx.fragment.app;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.b0;
import androidx.lifecycle.d0;
import androidx.lifecycle.e0;
import androidx.lifecycle.f0;
import androidx.lifecycle.g0;
import androidx.lifecycle.i;
import androidx.savedstate.SavedStateRegistry;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class Fragment implements ComponentCallbacks, View.OnCreateContextMenuListener, androidx.lifecycle.o, e0, androidx.lifecycle.h, androidx.savedstate.c {

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    static final Object f2104f0 = new Object();
    Fragment A;
    int B;
    int C;
    String D;
    boolean E;
    boolean F;
    boolean G;
    boolean H;
    boolean I;
    boolean J;
    private boolean K;
    ViewGroup L;
    View M;
    boolean N;
    boolean O;
    e P;
    Runnable Q;
    boolean R;
    boolean S;
    float T;
    LayoutInflater U;
    boolean V;
    i.c W;
    androidx.lifecycle.p X;
    v Y;
    androidx.lifecycle.t<androidx.lifecycle.o> Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    b0.b f2105a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    androidx.savedstate.b f2106b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    private int f2107c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    private final AtomicInteger f2108d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f2109e;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    private final ArrayList<g> f2110e0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    Bundle f2111g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    SparseArray<Parcelable> f2112h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    Bundle f2113i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    Boolean f2114j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    String f2115k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    Bundle f2116l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    Fragment f2117m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    String f2118n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    int f2119o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private Boolean f2120p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    boolean f2121q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    boolean f2122r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    boolean f2123s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    boolean f2124t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    boolean f2125u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    boolean f2126v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    int f2127w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    FragmentManager f2128x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    i<?> f2129y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    FragmentManager f2130z;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Fragment.this.M1();
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Fragment.this.b(false);
        }
    }

    class c implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ x f2135e;

        c(x xVar) {
            this.f2135e = xVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f2135e.g();
        }
    }

    class d extends androidx.fragment.app.f {
        d() {
        }

        @Override // androidx.fragment.app.f
        public View f(int i7) {
            View view = Fragment.this.M;
            if (view != null) {
                return view.findViewById(i7);
            }
            throw new IllegalStateException("Fragment " + Fragment.this + " does not have a view");
        }

        @Override // androidx.fragment.app.f
        public boolean h() {
            return Fragment.this.M != null;
        }
    }

    static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        View f2138a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Animator f2139b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f2140c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f2141d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f2142e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f2143f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        int f2144g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        int f2145h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        ArrayList<String> f2146i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        ArrayList<String> f2147j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        Object f2148k = null;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        Object f2149l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        Object f2150m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        Object f2151n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        Object f2152o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        Object f2153p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        Boolean f2154q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        Boolean f2155r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        float f2156s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        View f2157t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        boolean f2158u;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        h f2159v;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        boolean f2160w;

        e() {
            Object obj = Fragment.f2104f0;
            this.f2149l = obj;
            this.f2150m = null;
            this.f2151n = obj;
            this.f2152o = null;
            this.f2153p = obj;
            this.f2156s = 1.0f;
            this.f2157t = null;
        }
    }

    public static class f extends RuntimeException {
        public f(String str, Exception exc) {
            super(str, exc);
        }
    }

    private static abstract class g {
        private g() {
        }

        abstract void a();
    }

    interface h {
        void a();

        void b();
    }

    public Fragment() {
        this.f2109e = -1;
        this.f2115k = UUID.randomUUID().toString();
        this.f2118n = null;
        this.f2120p = null;
        this.f2130z = new l();
        this.J = true;
        this.O = true;
        this.Q = new a();
        this.W = i.c.RESUMED;
        this.Z = new androidx.lifecycle.t<>();
        this.f2108d0 = new AtomicInteger();
        this.f2110e0 = new ArrayList<>();
        Z();
    }

    private int E() {
        i.c cVar = this.W;
        return (cVar == i.c.INITIALIZED || this.A == null) ? cVar.ordinal() : Math.min(cVar.ordinal(), this.A.E());
    }

    private void Z() {
        this.X = new androidx.lifecycle.p(this);
        this.f2106b0 = androidx.savedstate.b.a(this);
        this.f2105a0 = null;
    }

    @Deprecated
    public static Fragment b0(Context context, String str, Bundle bundle) {
        try {
            Fragment fragmentNewInstance = androidx.fragment.app.h.d(context.getClassLoader(), str).getConstructor(new Class[0]).newInstance(new Object[0]);
            if (bundle != null) {
                bundle.setClassLoader(fragmentNewInstance.getClass().getClassLoader());
                fragmentNewInstance.C1(bundle);
            }
            return fragmentNewInstance;
        } catch (IllegalAccessException e7) {
            throw new f("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e7);
        } catch (InstantiationException e8) {
            throw new f("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e8);
        } catch (NoSuchMethodException e9) {
            throw new f("Unable to instantiate fragment " + str + ": could not find Fragment constructor", e9);
        } catch (InvocationTargetException e10) {
            throw new f("Unable to instantiate fragment " + str + ": calling Fragment constructor caused an exception", e10);
        }
    }

    private e h() {
        if (this.P == null) {
            this.P = new e();
        }
        return this.P;
    }

    private void x1() {
        if (FragmentManager.F0(3)) {
            Log.d("FragmentManager", "moveto RESTORE_VIEW_STATE: " + this);
        }
        if (this.M != null) {
            y1(this.f2111g);
        }
        this.f2111g = null;
    }

    @Deprecated
    public final FragmentManager A() {
        return this.f2128x;
    }

    public void A0() {
    }

    void A1(int i7, int i8, int i9, int i10) {
        if (this.P == null && i7 == 0 && i8 == 0 && i9 == 0 && i10 == 0) {
            return;
        }
        h().f2141d = i7;
        h().f2142e = i8;
        h().f2143f = i9;
        h().f2144g = i10;
    }

    public final Object B() {
        i<?> iVar = this.f2129y;
        if (iVar == null) {
            return null;
        }
        return iVar.n();
    }

    public void B0() {
        this.K = true;
    }

    void B1(Animator animator) {
        h().f2139b = animator;
    }

    public final int C() {
        return this.B;
    }

    public void C0() {
        this.K = true;
    }

    public void C1(Bundle bundle) {
        if (this.f2128x != null && l0()) {
            throw new IllegalStateException("Fragment already added and state has been saved");
        }
        this.f2116l = bundle;
    }

    @Deprecated
    public LayoutInflater D(Bundle bundle) {
        i<?> iVar = this.f2129y;
        if (iVar == null) {
            throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
        }
        LayoutInflater layoutInflaterO = iVar.o();
        i0.g.a(layoutInflaterO, this.f2130z.u0());
        return layoutInflaterO;
    }

    public LayoutInflater D0(Bundle bundle) {
        return D(bundle);
    }

    void D1(View view) {
        h().f2157t = view;
    }

    public void E0(boolean z6) {
    }

    void E1(boolean z6) {
        h().f2160w = z6;
    }

    int F() {
        e eVar = this.P;
        if (eVar == null) {
            return 0;
        }
        return eVar.f2145h;
    }

    @Deprecated
    public void F0(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        this.K = true;
    }

    public void F1(SavedState savedState) {
        Bundle bundle;
        if (this.f2128x != null) {
            throw new IllegalStateException("Fragment already added");
        }
        if (savedState == null || (bundle = savedState.f2132e) == null) {
            bundle = null;
        }
        this.f2111g = bundle;
    }

    public final Fragment G() {
        return this.A;
    }

    public void G0(Context context, AttributeSet attributeSet, Bundle bundle) {
        this.K = true;
        i<?> iVar = this.f2129y;
        Activity activityI = iVar == null ? null : iVar.i();
        if (activityI != null) {
            this.K = false;
            F0(activityI, attributeSet, bundle);
        }
    }

    public void G1(boolean z6) {
        if (this.J != z6) {
            this.J = z6;
            if (this.I && c0() && !d0()) {
                this.f2129y.q();
            }
        }
    }

    public final FragmentManager H() {
        FragmentManager fragmentManager = this.f2128x;
        if (fragmentManager != null) {
            return fragmentManager;
        }
        throw new IllegalStateException("Fragment " + this + " not associated with a fragment manager.");
    }

    public void H0(boolean z6) {
    }

    void H1(int i7) {
        if (this.P == null && i7 == 0) {
            return;
        }
        h();
        this.P.f2145h = i7;
    }

    boolean I() {
        e eVar = this.P;
        if (eVar == null) {
            return false;
        }
        return eVar.f2140c;
    }

    public boolean I0(MenuItem menuItem) {
        return false;
    }

    void I1(h hVar) {
        h();
        e eVar = this.P;
        h hVar2 = eVar.f2159v;
        if (hVar == hVar2) {
            return;
        }
        if (hVar != null && hVar2 != null) {
            throw new IllegalStateException("Trying to set a replacement startPostponedEnterTransition on " + this);
        }
        if (eVar.f2158u) {
            eVar.f2159v = hVar;
        }
        if (hVar != null) {
            hVar.b();
        }
    }

    int J() {
        e eVar = this.P;
        if (eVar == null) {
            return 0;
        }
        return eVar.f2143f;
    }

    public void J0(Menu menu) {
    }

    void J1(boolean z6) {
        if (this.P == null) {
            return;
        }
        h().f2140c = z6;
    }

    int K() {
        e eVar = this.P;
        if (eVar == null) {
            return 0;
        }
        return eVar.f2144g;
    }

    public void K0() {
        this.K = true;
    }

    void K1(float f7) {
        h().f2156s = f7;
    }

    float L() {
        e eVar = this.P;
        if (eVar == null) {
            return 1.0f;
        }
        return eVar.f2156s;
    }

    public void L0(boolean z6) {
    }

    void L1(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        h();
        e eVar = this.P;
        eVar.f2146i = arrayList;
        eVar.f2147j = arrayList2;
    }

    public Object M() {
        e eVar = this.P;
        if (eVar == null) {
            return null;
        }
        Object obj = eVar.f2151n;
        return obj == f2104f0 ? x() : obj;
    }

    public void M0(Menu menu) {
    }

    public void M1() {
        if (this.P == null || !h().f2158u) {
            return;
        }
        if (this.f2129y == null) {
            h().f2158u = false;
        } else if (Looper.myLooper() != this.f2129y.l().getLooper()) {
            this.f2129y.l().postAtFrontOfQueue(new b());
        } else {
            b(true);
        }
    }

    public final Resources N() {
        return u1().getResources();
    }

    public void N0(boolean z6) {
    }

    public Object O() {
        e eVar = this.P;
        if (eVar == null) {
            return null;
        }
        Object obj = eVar.f2149l;
        return obj == f2104f0 ? u() : obj;
    }

    @Deprecated
    public void O0(int i7, String[] strArr, int[] iArr) {
    }

    public Object P() {
        e eVar = this.P;
        if (eVar == null) {
            return null;
        }
        return eVar.f2152o;
    }

    public void P0() {
        this.K = true;
    }

    public Object Q() {
        e eVar = this.P;
        if (eVar == null) {
            return null;
        }
        Object obj = eVar.f2153p;
        return obj == f2104f0 ? P() : obj;
    }

    public void Q0(Bundle bundle) {
    }

    ArrayList<String> R() {
        ArrayList<String> arrayList;
        e eVar = this.P;
        return (eVar == null || (arrayList = eVar.f2146i) == null) ? new ArrayList<>() : arrayList;
    }

    public void R0() {
        this.K = true;
    }

    ArrayList<String> S() {
        ArrayList<String> arrayList;
        e eVar = this.P;
        return (eVar == null || (arrayList = eVar.f2147j) == null) ? new ArrayList<>() : arrayList;
    }

    public void S0() {
        this.K = true;
    }

    public final String T(int i7) {
        return N().getString(i7);
    }

    public void T0(View view, Bundle bundle) {
    }

    public final String U() {
        return this.D;
    }

    public void U0(Bundle bundle) {
        this.K = true;
    }

    @Deprecated
    public final Fragment V() {
        String str;
        Fragment fragment = this.f2117m;
        if (fragment != null) {
            return fragment;
        }
        FragmentManager fragmentManager = this.f2128x;
        if (fragmentManager == null || (str = this.f2118n) == null) {
            return null;
        }
        return fragmentManager.f0(str);
    }

    void V0(Bundle bundle) {
        this.f2130z.Q0();
        this.f2109e = 3;
        this.K = false;
        o0(bundle);
        if (this.K) {
            x1();
            this.f2130z.y();
        } else {
            throw new z("Fragment " + this + " did not call through to super.onActivityCreated()");
        }
    }

    public View W() {
        return this.M;
    }

    void W0() {
        Iterator<g> it = this.f2110e0.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.f2110e0.clear();
        this.f2130z.j(this.f2129y, e(), this);
        this.f2109e = 0;
        this.K = false;
        r0(this.f2129y.j());
        if (this.K) {
            this.f2128x.I(this);
            this.f2130z.z();
        } else {
            throw new z("Fragment " + this + " did not call through to super.onAttach()");
        }
    }

    public androidx.lifecycle.o X() {
        v vVar = this.Y;
        if (vVar != null) {
            return vVar;
        }
        throw new IllegalStateException("Can't access the Fragment View's LifecycleOwner when getView() is null i.e., before onCreateView() or after onDestroyView()");
    }

    void X0(Configuration configuration) {
        onConfigurationChanged(configuration);
        this.f2130z.A(configuration);
    }

    public LiveData<androidx.lifecycle.o> Y() {
        return this.Z;
    }

    boolean Y0(MenuItem menuItem) {
        if (this.E) {
            return false;
        }
        if (t0(menuItem)) {
            return true;
        }
        return this.f2130z.B(menuItem);
    }

    void Z0(Bundle bundle) {
        this.f2130z.Q0();
        this.f2109e = 1;
        this.K = false;
        this.X.a(new androidx.lifecycle.m() { // from class: androidx.fragment.app.Fragment.5
            @Override // androidx.lifecycle.m
            public void d(androidx.lifecycle.o oVar, i.b bVar) {
                View view;
                if (bVar != i.b.ON_STOP || (view = Fragment.this.M) == null) {
                    return;
                }
                view.cancelPendingInputEvents();
            }
        });
        this.f2106b0.c(bundle);
        u0(bundle);
        this.V = true;
        if (this.K) {
            this.X.h(i.b.ON_CREATE);
            return;
        }
        throw new z("Fragment " + this + " did not call through to super.onCreate()");
    }

    @Override // androidx.lifecycle.o
    public androidx.lifecycle.i a() {
        return this.X;
    }

    void a0() {
        Z();
        this.f2115k = UUID.randomUUID().toString();
        this.f2121q = false;
        this.f2122r = false;
        this.f2123s = false;
        this.f2124t = false;
        this.f2125u = false;
        this.f2127w = 0;
        this.f2128x = null;
        this.f2130z = new l();
        this.f2129y = null;
        this.B = 0;
        this.C = 0;
        this.D = null;
        this.E = false;
        this.F = false;
    }

    boolean a1(Menu menu, MenuInflater menuInflater) {
        boolean z6 = false;
        if (this.E) {
            return false;
        }
        if (this.I && this.J) {
            z6 = true;
            x0(menu, menuInflater);
        }
        return z6 | this.f2130z.D(menu, menuInflater);
    }

    void b(boolean z6) {
        ViewGroup viewGroup;
        FragmentManager fragmentManager;
        e eVar = this.P;
        h hVar = null;
        if (eVar != null) {
            eVar.f2158u = false;
            h hVar2 = eVar.f2159v;
            eVar.f2159v = null;
            hVar = hVar2;
        }
        if (hVar != null) {
            hVar.a();
            return;
        }
        if (!FragmentManager.P || this.M == null || (viewGroup = this.L) == null || (fragmentManager = this.f2128x) == null) {
            return;
        }
        x xVarN = x.n(viewGroup, fragmentManager);
        xVarN.p();
        if (z6) {
            this.f2129y.l().post(new c(xVarN));
        } else {
            xVarN.g();
        }
    }

    void b1(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f2130z.Q0();
        this.f2126v = true;
        this.Y = new v(this, k());
        View viewY0 = y0(layoutInflater, viewGroup, bundle);
        this.M = viewY0;
        if (viewY0 == null) {
            if (this.Y.f()) {
                throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
            }
            this.Y = null;
        } else {
            this.Y.e();
            f0.a(this.M, this.Y);
            g0.a(this.M, this.Y);
            androidx.savedstate.d.a(this.M, this.Y);
            this.Z.m(this.Y);
        }
    }

    public final boolean c0() {
        return this.f2129y != null && this.f2121q;
    }

    void c1() {
        this.f2130z.E();
        this.X.h(i.b.ON_DESTROY);
        this.f2109e = 0;
        this.K = false;
        this.V = false;
        z0();
        if (this.K) {
            return;
        }
        throw new z("Fragment " + this + " did not call through to super.onDestroy()");
    }

    @Override // androidx.savedstate.c
    public final SavedStateRegistry d() {
        return this.f2106b0.b();
    }

    public final boolean d0() {
        return this.E;
    }

    void d1() {
        this.f2130z.F();
        if (this.M != null && this.Y.a().b().isAtLeast(i.c.CREATED)) {
            this.Y.b(i.b.ON_DESTROY);
        }
        this.f2109e = 1;
        this.K = false;
        B0();
        if (this.K) {
            androidx.loader.app.a.b(this).c();
            this.f2126v = false;
        } else {
            throw new z("Fragment " + this + " did not call through to super.onDestroyView()");
        }
    }

    androidx.fragment.app.f e() {
        return new d();
    }

    boolean e0() {
        e eVar = this.P;
        if (eVar == null) {
            return false;
        }
        return eVar.f2160w;
    }

    void e1() {
        this.f2109e = -1;
        this.K = false;
        C0();
        this.U = null;
        if (this.K) {
            if (this.f2130z.E0()) {
                return;
            }
            this.f2130z.E();
            this.f2130z = new l();
            return;
        }
        throw new z("Fragment " + this + " did not call through to super.onDetach()");
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    final boolean f0() {
        return this.f2127w > 0;
    }

    LayoutInflater f1(Bundle bundle) {
        LayoutInflater layoutInflaterD0 = D0(bundle);
        this.U = layoutInflaterD0;
        return layoutInflaterD0;
    }

    public void g(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.B));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.C));
        printWriter.print(" mTag=");
        printWriter.println(this.D);
        printWriter.print(str);
        printWriter.print("mState=");
        printWriter.print(this.f2109e);
        printWriter.print(" mWho=");
        printWriter.print(this.f2115k);
        printWriter.print(" mBackStackNesting=");
        printWriter.println(this.f2127w);
        printWriter.print(str);
        printWriter.print("mAdded=");
        printWriter.print(this.f2121q);
        printWriter.print(" mRemoving=");
        printWriter.print(this.f2122r);
        printWriter.print(" mFromLayout=");
        printWriter.print(this.f2123s);
        printWriter.print(" mInLayout=");
        printWriter.println(this.f2124t);
        printWriter.print(str);
        printWriter.print("mHidden=");
        printWriter.print(this.E);
        printWriter.print(" mDetached=");
        printWriter.print(this.F);
        printWriter.print(" mMenuVisible=");
        printWriter.print(this.J);
        printWriter.print(" mHasMenu=");
        printWriter.println(this.I);
        printWriter.print(str);
        printWriter.print("mRetainInstance=");
        printWriter.print(this.G);
        printWriter.print(" mUserVisibleHint=");
        printWriter.println(this.O);
        if (this.f2128x != null) {
            printWriter.print(str);
            printWriter.print("mFragmentManager=");
            printWriter.println(this.f2128x);
        }
        if (this.f2129y != null) {
            printWriter.print(str);
            printWriter.print("mHost=");
            printWriter.println(this.f2129y);
        }
        if (this.A != null) {
            printWriter.print(str);
            printWriter.print("mParentFragment=");
            printWriter.println(this.A);
        }
        if (this.f2116l != null) {
            printWriter.print(str);
            printWriter.print("mArguments=");
            printWriter.println(this.f2116l);
        }
        if (this.f2111g != null) {
            printWriter.print(str);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.f2111g);
        }
        if (this.f2112h != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.f2112h);
        }
        if (this.f2113i != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewRegistryState=");
            printWriter.println(this.f2113i);
        }
        Fragment fragmentV = V();
        if (fragmentV != null) {
            printWriter.print(str);
            printWriter.print("mTarget=");
            printWriter.print(fragmentV);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.f2119o);
        }
        printWriter.print(str);
        printWriter.print("mPopDirection=");
        printWriter.println(I());
        if (t() != 0) {
            printWriter.print(str);
            printWriter.print("getEnterAnim=");
            printWriter.println(t());
        }
        if (w() != 0) {
            printWriter.print(str);
            printWriter.print("getExitAnim=");
            printWriter.println(w());
        }
        if (J() != 0) {
            printWriter.print(str);
            printWriter.print("getPopEnterAnim=");
            printWriter.println(J());
        }
        if (K() != 0) {
            printWriter.print(str);
            printWriter.print("getPopExitAnim=");
            printWriter.println(K());
        }
        if (this.L != null) {
            printWriter.print(str);
            printWriter.print("mContainer=");
            printWriter.println(this.L);
        }
        if (this.M != null) {
            printWriter.print(str);
            printWriter.print("mView=");
            printWriter.println(this.M);
        }
        if (o() != null) {
            printWriter.print(str);
            printWriter.print("mAnimatingAway=");
            printWriter.println(o());
        }
        if (s() != null) {
            androidx.loader.app.a.b(this).a(str, fileDescriptor, printWriter, strArr);
        }
        printWriter.print(str);
        printWriter.println("Child " + this.f2130z + ":");
        this.f2130z.W(str + "  ", fileDescriptor, printWriter, strArr);
    }

    public final boolean g0() {
        FragmentManager fragmentManager;
        return this.J && ((fragmentManager = this.f2128x) == null || fragmentManager.H0(this.A));
    }

    void g1() {
        onLowMemory();
        this.f2130z.G();
    }

    boolean h0() {
        e eVar = this.P;
        if (eVar == null) {
            return false;
        }
        return eVar.f2158u;
    }

    void h1(boolean z6) {
        H0(z6);
        this.f2130z.H(z6);
    }

    public final int hashCode() {
        return super.hashCode();
    }

    Fragment i(String str) {
        return str.equals(this.f2115k) ? this : this.f2130z.i0(str);
    }

    public final boolean i0() {
        return this.f2122r;
    }

    boolean i1(MenuItem menuItem) {
        if (this.E) {
            return false;
        }
        if (this.I && this.J && I0(menuItem)) {
            return true;
        }
        return this.f2130z.J(menuItem);
    }

    public final androidx.fragment.app.d j() {
        i<?> iVar = this.f2129y;
        if (iVar == null) {
            return null;
        }
        return (androidx.fragment.app.d) iVar.i();
    }

    final boolean j0() {
        Fragment fragmentG = G();
        return fragmentG != null && (fragmentG.i0() || fragmentG.j0());
    }

    void j1(Menu menu) {
        if (this.E) {
            return;
        }
        if (this.I && this.J) {
            J0(menu);
        }
        this.f2130z.K(menu);
    }

    @Override // androidx.lifecycle.e0
    public d0 k() {
        if (this.f2128x == null) {
            throw new IllegalStateException("Can't access ViewModels from detached fragment");
        }
        if (E() != i.c.INITIALIZED.ordinal()) {
            return this.f2128x.A0(this);
        }
        throw new IllegalStateException("Calling getViewModelStore() before a Fragment reaches onCreate() when using setMaxLifecycle(INITIALIZED) is not supported");
    }

    public final boolean k0() {
        return this.f2109e >= 7;
    }

    void k1() {
        this.f2130z.M();
        if (this.M != null) {
            this.Y.b(i.b.ON_PAUSE);
        }
        this.X.h(i.b.ON_PAUSE);
        this.f2109e = 6;
        this.K = false;
        K0();
        if (this.K) {
            return;
        }
        throw new z("Fragment " + this + " did not call through to super.onPause()");
    }

    public boolean l() {
        Boolean bool;
        e eVar = this.P;
        if (eVar == null || (bool = eVar.f2155r) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public final boolean l0() {
        FragmentManager fragmentManager = this.f2128x;
        if (fragmentManager == null) {
            return false;
        }
        return fragmentManager.K0();
    }

    void l1(boolean z6) {
        L0(z6);
        this.f2130z.N(z6);
    }

    @Override // androidx.lifecycle.h
    public b0.b m() {
        if (this.f2128x == null) {
            throw new IllegalStateException("Can't access ViewModels from detached fragment");
        }
        if (this.f2105a0 == null) {
            Application application = null;
            Context applicationContext = u1().getApplicationContext();
            while (true) {
                if (!(applicationContext instanceof ContextWrapper)) {
                    break;
                }
                if (applicationContext instanceof Application) {
                    application = (Application) applicationContext;
                    break;
                }
                applicationContext = ((ContextWrapper) applicationContext).getBaseContext();
            }
            if (application == null && FragmentManager.F0(3)) {
                Log.d("FragmentManager", "Could not find Application instance from Context " + u1().getApplicationContext() + ", you will not be able to use AndroidViewModel with the default ViewModelProvider.Factory");
            }
            this.f2105a0 = new androidx.lifecycle.z(application, this, q());
        }
        return this.f2105a0;
    }

    public final boolean m0() {
        View view;
        return (!c0() || d0() || (view = this.M) == null || view.getWindowToken() == null || this.M.getVisibility() != 0) ? false : true;
    }

    boolean m1(Menu menu) {
        boolean z6 = false;
        if (this.E) {
            return false;
        }
        if (this.I && this.J) {
            z6 = true;
            M0(menu);
        }
        return z6 | this.f2130z.O(menu);
    }

    public boolean n() {
        Boolean bool;
        e eVar = this.P;
        if (eVar == null || (bool = eVar.f2154q) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    void n0() {
        this.f2130z.Q0();
    }

    void n1() {
        boolean zI0 = this.f2128x.I0(this);
        Boolean bool = this.f2120p;
        if (bool == null || bool.booleanValue() != zI0) {
            this.f2120p = Boolean.valueOf(zI0);
            N0(zI0);
            this.f2130z.P();
        }
    }

    View o() {
        e eVar = this.P;
        if (eVar == null) {
            return null;
        }
        return eVar.f2138a;
    }

    @Deprecated
    public void o0(Bundle bundle) {
        this.K = true;
    }

    void o1() {
        this.f2130z.Q0();
        this.f2130z.a0(true);
        this.f2109e = 7;
        this.K = false;
        P0();
        if (!this.K) {
            throw new z("Fragment " + this + " did not call through to super.onResume()");
        }
        androidx.lifecycle.p pVar = this.X;
        i.b bVar = i.b.ON_RESUME;
        pVar.h(bVar);
        if (this.M != null) {
            this.Y.b(bVar);
        }
        this.f2130z.Q();
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        this.K = true;
    }

    @Override // android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        t1().onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        this.K = true;
    }

    Animator p() {
        e eVar = this.P;
        if (eVar == null) {
            return null;
        }
        return eVar.f2139b;
    }

    @Deprecated
    public void p0(int i7, int i8, Intent intent) {
        if (FragmentManager.F0(2)) {
            Log.v("FragmentManager", "Fragment " + this + " received the following in onActivityResult(): requestCode: " + i7 + " resultCode: " + i8 + " data: " + intent);
        }
    }

    void p1(Bundle bundle) {
        Q0(bundle);
        this.f2106b0.d(bundle);
        Parcelable parcelableH1 = this.f2130z.h1();
        if (parcelableH1 != null) {
            bundle.putParcelable("android:support:fragments", parcelableH1);
        }
    }

    public final Bundle q() {
        return this.f2116l;
    }

    @Deprecated
    public void q0(Activity activity) {
        this.K = true;
    }

    void q1() {
        this.f2130z.Q0();
        this.f2130z.a0(true);
        this.f2109e = 5;
        this.K = false;
        R0();
        if (!this.K) {
            throw new z("Fragment " + this + " did not call through to super.onStart()");
        }
        androidx.lifecycle.p pVar = this.X;
        i.b bVar = i.b.ON_START;
        pVar.h(bVar);
        if (this.M != null) {
            this.Y.b(bVar);
        }
        this.f2130z.R();
    }

    public final FragmentManager r() {
        if (this.f2129y != null) {
            return this.f2130z;
        }
        throw new IllegalStateException("Fragment " + this + " has not been attached yet.");
    }

    public void r0(Context context) {
        this.K = true;
        i<?> iVar = this.f2129y;
        Activity activityI = iVar == null ? null : iVar.i();
        if (activityI != null) {
            this.K = false;
            q0(activityI);
        }
    }

    void r1() {
        this.f2130z.T();
        if (this.M != null) {
            this.Y.b(i.b.ON_STOP);
        }
        this.X.h(i.b.ON_STOP);
        this.f2109e = 4;
        this.K = false;
        S0();
        if (this.K) {
            return;
        }
        throw new z("Fragment " + this + " did not call through to super.onStop()");
    }

    public Context s() {
        i<?> iVar = this.f2129y;
        if (iVar == null) {
            return null;
        }
        return iVar.j();
    }

    @Deprecated
    public void s0(Fragment fragment) {
    }

    void s1() {
        T0(this.M, this.f2111g);
        this.f2130z.U();
    }

    int t() {
        e eVar = this.P;
        if (eVar == null) {
            return 0;
        }
        return eVar.f2141d;
    }

    public boolean t0(MenuItem menuItem) {
        return false;
    }

    public final androidx.fragment.app.d t1() {
        androidx.fragment.app.d dVarJ = j();
        if (dVarJ != null) {
            return dVarJ;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to an activity.");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("}");
        sb.append(" (");
        sb.append(this.f2115k);
        if (this.B != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.B));
        }
        if (this.D != null) {
            sb.append(" tag=");
            sb.append(this.D);
        }
        sb.append(")");
        return sb.toString();
    }

    public Object u() {
        e eVar = this.P;
        if (eVar == null) {
            return null;
        }
        return eVar.f2148k;
    }

    public void u0(Bundle bundle) {
        this.K = true;
        w1(bundle);
        if (this.f2130z.J0(1)) {
            return;
        }
        this.f2130z.C();
    }

    public final Context u1() {
        Context contextS = s();
        if (contextS != null) {
            return contextS;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to a context.");
    }

    w.h v() {
        e eVar = this.P;
        if (eVar == null) {
            return null;
        }
        Objects.requireNonNull(eVar);
        return null;
    }

    public Animation v0(int i7, boolean z6, int i8) {
        return null;
    }

    public final View v1() {
        View viewW = W();
        if (viewW != null) {
            return viewW;
        }
        throw new IllegalStateException("Fragment " + this + " did not return a View from onCreateView() or this was called before onCreateView().");
    }

    int w() {
        e eVar = this.P;
        if (eVar == null) {
            return 0;
        }
        return eVar.f2142e;
    }

    public Animator w0(int i7, boolean z6, int i8) {
        return null;
    }

    void w1(Bundle bundle) {
        Parcelable parcelable;
        if (bundle == null || (parcelable = bundle.getParcelable("android:support:fragments")) == null) {
            return;
        }
        this.f2130z.f1(parcelable);
        this.f2130z.C();
    }

    public Object x() {
        e eVar = this.P;
        if (eVar == null) {
            return null;
        }
        return eVar.f2150m;
    }

    public void x0(Menu menu, MenuInflater menuInflater) {
    }

    w.h y() {
        e eVar = this.P;
        if (eVar == null) {
            return null;
        }
        Objects.requireNonNull(eVar);
        return null;
    }

    public View y0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i7 = this.f2107c0;
        if (i7 != 0) {
            return layoutInflater.inflate(i7, viewGroup, false);
        }
        return null;
    }

    final void y1(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = this.f2112h;
        if (sparseArray != null) {
            this.M.restoreHierarchyState(sparseArray);
            this.f2112h = null;
        }
        if (this.M != null) {
            this.Y.g(this.f2113i);
            this.f2113i = null;
        }
        this.K = false;
        U0(bundle);
        if (this.K) {
            if (this.M != null) {
                this.Y.b(i.b.ON_CREATE);
            }
        } else {
            throw new z("Fragment " + this + " did not call through to super.onViewStateRestored()");
        }
    }

    View z() {
        e eVar = this.P;
        if (eVar == null) {
            return null;
        }
        return eVar.f2157t;
    }

    public void z0() {
        this.K = true;
    }

    void z1(View view) {
        h().f2138a = view;
    }

    @SuppressLint({"BanParcelableUsage, ParcelClassLoader"})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final Bundle f2132e;

        class a implements Parcelable.ClassLoaderCreator<SavedState> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i7) {
                return new SavedState[i7];
            }
        }

        SavedState(Bundle bundle) {
            this.f2132e = bundle;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i7) {
            parcel.writeBundle(this.f2132e);
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            Bundle bundle = parcel.readBundle();
            this.f2132e = bundle;
            if (classLoader == null || bundle == null) {
                return;
            }
            bundle.setClassLoader(classLoader);
        }
    }

    public Fragment(int i7) {
        this();
        this.f2107c0 = i7;
    }
}
