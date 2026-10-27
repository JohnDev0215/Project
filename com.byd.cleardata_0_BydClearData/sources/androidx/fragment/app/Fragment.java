package androidx.fragment.app;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.lifecycle.d;
import androidx.savedstate.SavedStateRegistry;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class Fragment implements ComponentCallbacks, View.OnCreateContextMenuListener, androidx.lifecycle.g, androidx.lifecycle.s, androidx.savedstate.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final Object f1271b = new Object();
    boolean A;
    boolean B;
    boolean C;
    boolean D;
    boolean E;
    private boolean G;
    ViewGroup H;
    View I;
    View J;
    boolean K;
    d M;
    boolean O;
    boolean P;
    float Q;
    LayoutInflater R;
    boolean S;
    androidx.lifecycle.h U;
    r V;
    androidx.savedstate.a X;
    private int Y;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    Bundle f1273d;
    SparseArray<Parcelable> e;
    Boolean f;
    Bundle h;
    Fragment i;
    int k;
    boolean m;
    boolean n;
    boolean o;
    boolean p;
    boolean q;
    boolean r;
    int s;
    j t;
    h u;
    Fragment w;
    int x;
    int y;
    String z;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1272c = 0;
    String g = UUID.randomUUID().toString();
    String j = null;
    private Boolean l = null;
    j v = new j();
    boolean F = true;
    boolean L = true;
    Runnable N = new a();
    d.b T = d.b.RESUMED;
    androidx.lifecycle.l<androidx.lifecycle.g> W = new androidx.lifecycle.l<>();

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Fragment.this.y1();
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Fragment.this.b();
        }
    }

    class c extends androidx.fragment.app.e {
        c() {
        }

        @Override // androidx.fragment.app.e
        public View e(int i) {
            View view = Fragment.this.I;
            if (view != null) {
                return view.findViewById(i);
            }
            throw new IllegalStateException("Fragment " + this + " does not have a view");
        }

        @Override // androidx.fragment.app.e
        public boolean f() {
            return Fragment.this.I != null;
        }
    }

    static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        View f1278a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Animator f1279b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1280c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1281d;
        int e;
        int f;
        Object g = null;
        Object h;
        Object i;
        Object j;
        Object k;
        Object l;
        Boolean m;
        Boolean n;
        androidx.core.app.f o;
        androidx.core.app.f p;
        boolean q;
        f r;
        boolean s;

        d() {
            Object obj = Fragment.f1271b;
            this.h = obj;
            this.i = null;
            this.j = obj;
            this.k = null;
            this.l = obj;
        }
    }

    public static class e extends RuntimeException {
        public e(String str, Exception exc) {
            super(str, exc);
        }
    }

    interface f {
        void a();

        void b();
    }

    public Fragment() {
        V();
    }

    private void V() {
        this.U = new androidx.lifecycle.h(this);
        this.X = androidx.savedstate.a.a(this);
        if (Build.VERSION.SDK_INT >= 19) {
            this.U.a(new androidx.lifecycle.e() { // from class: androidx.fragment.app.Fragment.2
                @Override // androidx.lifecycle.e
                public void d(androidx.lifecycle.g gVar, d.a aVar) {
                    View view;
                    if (aVar != d.a.ON_STOP || (view = Fragment.this.I) == null) {
                        return;
                    }
                    view.cancelPendingInputEvents();
                }
            });
        }
    }

    @Deprecated
    public static Fragment X(Context context, String str, Bundle bundle) {
        try {
            Fragment fragmentNewInstance = g.d(context.getClassLoader(), str).getConstructor(new Class[0]).newInstance(new Object[0]);
            if (bundle != null) {
                bundle.setClassLoader(fragmentNewInstance.getClass().getClassLoader());
                fragmentNewInstance.q1(bundle);
            }
            return fragmentNewInstance;
        } catch (IllegalAccessException e2) {
            throw new e("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e2);
        } catch (InstantiationException e3) {
            throw new e("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e3);
        } catch (NoSuchMethodException e4) {
            throw new e("Unable to instantiate fragment " + str + ": could not find Fragment constructor", e4);
        } catch (InvocationTargetException e5) {
            throw new e("Unable to instantiate fragment " + str + ": calling Fragment constructor caused an exception", e5);
        }
    }

    private d j() {
        if (this.M == null) {
            this.M = new d();
        }
        return this.M;
    }

    androidx.core.app.f A() {
        d dVar = this.M;
        if (dVar == null) {
            return null;
        }
        return dVar.o;
    }

    public void A0() {
        this.G = true;
    }

    public Object B() {
        d dVar = this.M;
        if (dVar == null) {
            return null;
        }
        return dVar.i;
    }

    public void B0(boolean z) {
    }

    androidx.core.app.f C() {
        d dVar = this.M;
        if (dVar == null) {
            return null;
        }
        return dVar.p;
    }

    public void C0(Menu menu) {
    }

    public final i D() {
        return this.t;
    }

    public void D0(boolean z) {
    }

    public final Object E() {
        h hVar = this.u;
        if (hVar == null) {
            return null;
        }
        return hVar.l();
    }

    public void E0(int i, String[] strArr, int[] iArr) {
    }

    @Deprecated
    public LayoutInflater F(Bundle bundle) {
        h hVar = this.u;
        if (hVar == null) {
            throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
        }
        LayoutInflater layoutInflaterM = hVar.m();
        a.g.k.f.b(layoutInflaterM, this.v.x0());
        return layoutInflaterM;
    }

    public void F0() {
        this.G = true;
    }

    int G() {
        d dVar = this.M;
        if (dVar == null) {
            return 0;
        }
        return dVar.f1281d;
    }

    public void G0(Bundle bundle) {
    }

    int H() {
        d dVar = this.M;
        if (dVar == null) {
            return 0;
        }
        return dVar.e;
    }

    public void H0() {
        this.G = true;
    }

    int I() {
        d dVar = this.M;
        if (dVar == null) {
            return 0;
        }
        return dVar.f;
    }

    public void I0() {
        this.G = true;
    }

    public final Fragment J() {
        return this.w;
    }

    public void J0(View view, Bundle bundle) {
    }

    public Object K() {
        d dVar = this.M;
        if (dVar == null) {
            return null;
        }
        Object obj = dVar.j;
        return obj == f1271b ? B() : obj;
    }

    public void K0(Bundle bundle) {
        this.G = true;
    }

    public final Resources L() {
        return j1().getResources();
    }

    void L0(Bundle bundle) {
        this.v.R0();
        this.f1272c = 2;
        this.G = false;
        e0(bundle);
        if (this.G) {
            this.v.x();
            return;
        }
        throw new s("Fragment " + this + " did not call through to super.onActivityCreated()");
    }

    public final boolean M() {
        return this.C;
    }

    void M0() {
        this.v.o(this.u, new c(), this);
        this.G = false;
        h0(this.u.h());
        if (this.G) {
            return;
        }
        throw new s("Fragment " + this + " did not call through to super.onAttach()");
    }

    public Object N() {
        d dVar = this.M;
        if (dVar == null) {
            return null;
        }
        Object obj = dVar.h;
        return obj == f1271b ? z() : obj;
    }

    void N0(Configuration configuration) {
        onConfigurationChanged(configuration);
        this.v.y(configuration);
    }

    public Object O() {
        d dVar = this.M;
        if (dVar == null) {
            return null;
        }
        return dVar.k;
    }

    boolean O0(MenuItem menuItem) {
        if (this.A) {
            return false;
        }
        return j0(menuItem) || this.v.z(menuItem);
    }

    public Object P() {
        d dVar = this.M;
        if (dVar == null) {
            return null;
        }
        Object obj = dVar.l;
        return obj == f1271b ? O() : obj;
    }

    void P0(Bundle bundle) {
        this.v.R0();
        this.f1272c = 1;
        this.G = false;
        this.X.c(bundle);
        k0(bundle);
        this.S = true;
        if (this.G) {
            this.U.i(d.a.ON_CREATE);
            return;
        }
        throw new s("Fragment " + this + " did not call through to super.onCreate()");
    }

    int Q() {
        d dVar = this.M;
        if (dVar == null) {
            return 0;
        }
        return dVar.f1280c;
    }

    boolean Q0(Menu menu, MenuInflater menuInflater) {
        boolean z = false;
        if (this.A) {
            return false;
        }
        if (this.E && this.F) {
            z = true;
            n0(menu, menuInflater);
        }
        return z | this.v.B(menu, menuInflater);
    }

    public final String R(int i) {
        return L().getString(i);
    }

    void R0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.v.R0();
        this.r = true;
        this.V = new r();
        View viewO0 = o0(layoutInflater, viewGroup, bundle);
        this.I = viewO0;
        if (viewO0 != null) {
            this.V.e();
            this.W.h(this.V);
        } else {
            if (this.V.f()) {
                throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
            }
            this.V = null;
        }
    }

    public final String S(int i, Object... objArr) {
        return L().getString(i, objArr);
    }

    void S0() {
        this.v.C();
        this.U.i(d.a.ON_DESTROY);
        this.f1272c = 0;
        this.G = false;
        this.S = false;
        p0();
        if (this.G) {
            return;
        }
        throw new s("Fragment " + this + " did not call through to super.onDestroy()");
    }

    public final Fragment T() {
        String str;
        Fragment fragment = this.i;
        if (fragment != null) {
            return fragment;
        }
        j jVar = this.t;
        if (jVar == null || (str = this.j) == null) {
            return null;
        }
        return jVar.k.get(str);
    }

    void T0() {
        this.v.D();
        if (this.I != null) {
            this.V.b(d.a.ON_DESTROY);
        }
        this.f1272c = 1;
        this.G = false;
        r0();
        if (this.G) {
            a.l.a.a.b(this).c();
            this.r = false;
        } else {
            throw new s("Fragment " + this + " did not call through to super.onDestroyView()");
        }
    }

    public View U() {
        return this.I;
    }

    void U0() {
        this.G = false;
        s0();
        this.R = null;
        if (this.G) {
            if (this.v.C0()) {
                return;
            }
            this.v.C();
            this.v = new j();
            return;
        }
        throw new s("Fragment " + this + " did not call through to super.onDetach()");
    }

    LayoutInflater V0(Bundle bundle) {
        LayoutInflater layoutInflaterT0 = t0(bundle);
        this.R = layoutInflaterT0;
        return layoutInflaterT0;
    }

    void W() {
        V();
        this.g = UUID.randomUUID().toString();
        this.m = false;
        this.n = false;
        this.o = false;
        this.p = false;
        this.q = false;
        this.s = 0;
        this.t = null;
        this.v = new j();
        this.u = null;
        this.x = 0;
        this.y = 0;
        this.z = null;
        this.A = false;
        this.B = false;
    }

    void W0() {
        onLowMemory();
        this.v.E();
    }

    void X0(boolean z) {
        x0(z);
        this.v.F(z);
    }

    boolean Y() {
        d dVar = this.M;
        if (dVar == null) {
            return false;
        }
        return dVar.s;
    }

    boolean Y0(MenuItem menuItem) {
        if (this.A) {
            return false;
        }
        return (this.E && this.F && y0(menuItem)) || this.v.U(menuItem);
    }

    final boolean Z() {
        return this.s > 0;
    }

    void Z0(Menu menu) {
        if (this.A) {
            return;
        }
        if (this.E && this.F) {
            z0(menu);
        }
        this.v.V(menu);
    }

    @Override // androidx.lifecycle.g
    public androidx.lifecycle.d a() {
        return this.U;
    }

    boolean a0() {
        d dVar = this.M;
        if (dVar == null) {
            return false;
        }
        return dVar.q;
    }

    void a1() {
        this.v.X();
        if (this.I != null) {
            this.V.b(d.a.ON_PAUSE);
        }
        this.U.i(d.a.ON_PAUSE);
        this.f1272c = 3;
        this.G = false;
        A0();
        if (this.G) {
            return;
        }
        throw new s("Fragment " + this + " did not call through to super.onPause()");
    }

    void b() {
        d dVar = this.M;
        f fVar = null;
        if (dVar != null) {
            dVar.q = false;
            f fVar2 = dVar.r;
            dVar.r = null;
            fVar = fVar2;
        }
        if (fVar != null) {
            fVar.a();
        }
    }

    public final boolean b0() {
        return this.f1272c >= 4;
    }

    void b1(boolean z) {
        B0(z);
        this.v.Y(z);
    }

    public final boolean c0() {
        j jVar = this.t;
        if (jVar == null) {
            return false;
        }
        return jVar.G0();
    }

    boolean c1(Menu menu) {
        boolean z = false;
        if (this.A) {
            return false;
        }
        if (this.E && this.F) {
            z = true;
            C0(menu);
        }
        return z | this.v.Z(menu);
    }

    @Override // androidx.savedstate.b
    public final SavedStateRegistry d() {
        return this.X.b();
    }

    void d0() {
        this.v.R0();
    }

    void d1() {
        boolean zE0 = this.t.E0(this);
        Boolean bool = this.l;
        if (bool == null || bool.booleanValue() != zE0) {
            this.l = Boolean.valueOf(zE0);
            D0(zE0);
            this.v.a0();
        }
    }

    public void e0(Bundle bundle) {
        this.G = true;
    }

    void e1() {
        this.v.R0();
        this.v.k0();
        this.f1272c = 4;
        this.G = false;
        F0();
        if (!this.G) {
            throw new s("Fragment " + this + " did not call through to super.onResume()");
        }
        androidx.lifecycle.h hVar = this.U;
        d.a aVar = d.a.ON_RESUME;
        hVar.i(aVar);
        if (this.I != null) {
            this.V.b(aVar);
        }
        this.v.b0();
        this.v.k0();
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void f0(int i, int i2, Intent intent) {
    }

    void f1(Bundle bundle) {
        G0(bundle);
        this.X.d(bundle);
        Parcelable parcelableD1 = this.v.d1();
        if (parcelableD1 != null) {
            bundle.putParcelable("android:support:fragments", parcelableD1);
        }
    }

    @Deprecated
    public void g0(Activity activity) {
        this.G = true;
    }

    void g1() {
        this.v.R0();
        this.v.k0();
        this.f1272c = 3;
        this.G = false;
        H0();
        if (!this.G) {
            throw new s("Fragment " + this + " did not call through to super.onStart()");
        }
        androidx.lifecycle.h hVar = this.U;
        d.a aVar = d.a.ON_START;
        hVar.i(aVar);
        if (this.I != null) {
            this.V.b(aVar);
        }
        this.v.c0();
    }

    public void h0(Context context) {
        this.G = true;
        h hVar = this.u;
        Activity activityG = hVar == null ? null : hVar.g();
        if (activityG != null) {
            this.G = false;
            g0(activityG);
        }
    }

    void h1() {
        this.v.e0();
        if (this.I != null) {
            this.V.b(d.a.ON_STOP);
        }
        this.U.i(d.a.ON_STOP);
        this.f1272c = 2;
        this.G = false;
        I0();
        if (this.G) {
            return;
        }
        throw new s("Fragment " + this + " did not call through to super.onStop()");
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public void i(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.x));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.y));
        printWriter.print(" mTag=");
        printWriter.println(this.z);
        printWriter.print(str);
        printWriter.print("mState=");
        printWriter.print(this.f1272c);
        printWriter.print(" mWho=");
        printWriter.print(this.g);
        printWriter.print(" mBackStackNesting=");
        printWriter.println(this.s);
        printWriter.print(str);
        printWriter.print("mAdded=");
        printWriter.print(this.m);
        printWriter.print(" mRemoving=");
        printWriter.print(this.n);
        printWriter.print(" mFromLayout=");
        printWriter.print(this.o);
        printWriter.print(" mInLayout=");
        printWriter.println(this.p);
        printWriter.print(str);
        printWriter.print("mHidden=");
        printWriter.print(this.A);
        printWriter.print(" mDetached=");
        printWriter.print(this.B);
        printWriter.print(" mMenuVisible=");
        printWriter.print(this.F);
        printWriter.print(" mHasMenu=");
        printWriter.println(this.E);
        printWriter.print(str);
        printWriter.print("mRetainInstance=");
        printWriter.print(this.C);
        printWriter.print(" mUserVisibleHint=");
        printWriter.println(this.L);
        if (this.t != null) {
            printWriter.print(str);
            printWriter.print("mFragmentManager=");
            printWriter.println(this.t);
        }
        if (this.u != null) {
            printWriter.print(str);
            printWriter.print("mHost=");
            printWriter.println(this.u);
        }
        if (this.w != null) {
            printWriter.print(str);
            printWriter.print("mParentFragment=");
            printWriter.println(this.w);
        }
        if (this.h != null) {
            printWriter.print(str);
            printWriter.print("mArguments=");
            printWriter.println(this.h);
        }
        if (this.f1273d != null) {
            printWriter.print(str);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.f1273d);
        }
        if (this.e != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.e);
        }
        Fragment fragmentT = T();
        if (fragmentT != null) {
            printWriter.print(str);
            printWriter.print("mTarget=");
            printWriter.print(fragmentT);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.k);
        }
        if (G() != 0) {
            printWriter.print(str);
            printWriter.print("mNextAnim=");
            printWriter.println(G());
        }
        if (this.H != null) {
            printWriter.print(str);
            printWriter.print("mContainer=");
            printWriter.println(this.H);
        }
        if (this.I != null) {
            printWriter.print(str);
            printWriter.print("mView=");
            printWriter.println(this.I);
        }
        if (this.J != null) {
            printWriter.print(str);
            printWriter.print("mInnerView=");
            printWriter.println(this.I);
        }
        if (u() != null) {
            printWriter.print(str);
            printWriter.print("mAnimatingAway=");
            printWriter.println(u());
            printWriter.print(str);
            printWriter.print("mStateAfterAnimating=");
            printWriter.println(Q());
        }
        if (y() != null) {
            a.l.a.a.b(this).a(str, fileDescriptor, printWriter, strArr);
        }
        printWriter.print(str);
        printWriter.println("Child " + this.v + ":");
        this.v.b(str + "  ", fileDescriptor, printWriter, strArr);
    }

    public void i0(Fragment fragment) {
    }

    public final androidx.fragment.app.d i1() {
        androidx.fragment.app.d dVarP = p();
        if (dVarP != null) {
            return dVarP;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to an activity.");
    }

    public boolean j0(MenuItem menuItem) {
        return false;
    }

    public final Context j1() {
        Context contextY = y();
        if (contextY != null) {
            return contextY;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to a context.");
    }

    public void k0(Bundle bundle) {
        this.G = true;
        m1(bundle);
        if (this.v.F0(1)) {
            return;
        }
        this.v.A();
    }

    public final i k1() {
        i iVarD = D();
        if (iVarD != null) {
            return iVarD;
        }
        throw new IllegalStateException("Fragment " + this + " not associated with a fragment manager.");
    }

    Fragment l(String str) {
        return str.equals(this.g) ? this : this.v.p0(str);
    }

    public Animation l0(int i, boolean z, int i2) {
        return null;
    }

    public final View l1() {
        View viewU = U();
        if (viewU != null) {
            return viewU;
        }
        throw new IllegalStateException("Fragment " + this + " did not return a View from onCreateView() or this was called before onCreateView().");
    }

    public Animator m0(int i, boolean z, int i2) {
        return null;
    }

    void m1(Bundle bundle) {
        Parcelable parcelable;
        if (bundle == null || (parcelable = bundle.getParcelable("android:support:fragments")) == null) {
            return;
        }
        this.v.b1(parcelable);
        this.v.A();
    }

    public void n0(Menu menu, MenuInflater menuInflater) {
    }

    final void n1(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = this.e;
        if (sparseArray != null) {
            this.J.restoreHierarchyState(sparseArray);
            this.e = null;
        }
        this.G = false;
        K0(bundle);
        if (this.G) {
            if (this.I != null) {
                this.V.b(d.a.ON_CREATE);
            }
        } else {
            throw new s("Fragment " + this + " did not call through to super.onViewStateRestored()");
        }
    }

    @Override // androidx.lifecycle.s
    public androidx.lifecycle.r o() {
        j jVar = this.t;
        if (jVar != null) {
            return jVar.z0(this);
        }
        throw new IllegalStateException("Can't access ViewModels from detached fragment");
    }

    public View o0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i = this.Y;
        if (i != 0) {
            return layoutInflater.inflate(i, viewGroup, false);
        }
        return null;
    }

    void o1(View view) {
        j().f1278a = view;
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        this.G = true;
    }

    @Override // android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        i1().onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        this.G = true;
    }

    public final androidx.fragment.app.d p() {
        h hVar = this.u;
        if (hVar == null) {
            return null;
        }
        return (androidx.fragment.app.d) hVar.g();
    }

    public void p0() {
        this.G = true;
    }

    void p1(Animator animator) {
        j().f1279b = animator;
    }

    public boolean q() {
        Boolean bool;
        d dVar = this.M;
        if (dVar == null || (bool = dVar.n) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public void q0() {
    }

    public void q1(Bundle bundle) {
        if (this.t != null && c0()) {
            throw new IllegalStateException("Fragment already added and state has been saved");
        }
        this.h = bundle;
    }

    public void r0() {
        this.G = true;
    }

    void r1(boolean z) {
        j().s = z;
    }

    public void s0() {
        this.G = true;
    }

    void s1(int i) {
        if (this.M == null && i == 0) {
            return;
        }
        j().f1281d = i;
    }

    public boolean t() {
        Boolean bool;
        d dVar = this.M;
        if (dVar == null || (bool = dVar.m) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public LayoutInflater t0(Bundle bundle) {
        return F(bundle);
    }

    void t1(int i, int i2) {
        if (this.M == null && i == 0 && i2 == 0) {
            return;
        }
        j();
        d dVar = this.M;
        dVar.e = i;
        dVar.f = i2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        a.g.j.a.a(this, sb);
        sb.append(" (");
        sb.append(this.g);
        sb.append(")");
        if (this.x != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.x));
        }
        if (this.z != null) {
            sb.append(" ");
            sb.append(this.z);
        }
        sb.append('}');
        return sb.toString();
    }

    View u() {
        d dVar = this.M;
        if (dVar == null) {
            return null;
        }
        return dVar.f1278a;
    }

    public void u0(boolean z) {
    }

    void u1(f fVar) {
        j();
        d dVar = this.M;
        f fVar2 = dVar.r;
        if (fVar == fVar2) {
            return;
        }
        if (fVar != null && fVar2 != null) {
            throw new IllegalStateException("Trying to set a replacement startPostponedEnterTransition on " + this);
        }
        if (dVar.q) {
            dVar.r = fVar;
        }
        if (fVar != null) {
            fVar.b();
        }
    }

    Animator v() {
        d dVar = this.M;
        if (dVar == null) {
            return null;
        }
        return dVar.f1279b;
    }

    @Deprecated
    public void v0(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        this.G = true;
    }

    void v1(int i) {
        j().f1280c = i;
    }

    public final Bundle w() {
        return this.h;
    }

    public void w0(Context context, AttributeSet attributeSet, Bundle bundle) {
        this.G = true;
        h hVar = this.u;
        Activity activityG = hVar == null ? null : hVar.g();
        if (activityG != null) {
            this.G = false;
            v0(activityG, attributeSet, bundle);
        }
    }

    public void w1(@SuppressLint({"UnknownNullness"}) Intent intent) {
        x1(intent, null);
    }

    public final i x() {
        if (this.u != null) {
            return this.v;
        }
        throw new IllegalStateException("Fragment " + this + " has not been attached yet.");
    }

    public void x0(boolean z) {
    }

    public void x1(@SuppressLint({"UnknownNullness"}) Intent intent, Bundle bundle) {
        h hVar = this.u;
        if (hVar != null) {
            hVar.r(this, intent, -1, bundle);
            return;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }

    public Context y() {
        h hVar = this.u;
        if (hVar == null) {
            return null;
        }
        return hVar.h();
    }

    public boolean y0(MenuItem menuItem) {
        return false;
    }

    public void y1() {
        j jVar = this.t;
        if (jVar == null || jVar.u == null) {
            j().q = false;
        } else if (Looper.myLooper() != this.t.u.i().getLooper()) {
            this.t.u.i().postAtFrontOfQueue(new b());
        } else {
            b();
        }
    }

    public Object z() {
        d dVar = this.M;
        if (dVar == null) {
            return null;
        }
        return dVar.g;
    }

    public void z0(Menu menu) {
    }
}
