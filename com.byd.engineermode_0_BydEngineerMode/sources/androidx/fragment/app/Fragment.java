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
import android.os.Parcel;
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
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.p;
import androidx.lifecycle.q;
import androidx.savedstate.SavedStateRegistry;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class Fragment implements ComponentCallbacks, View.OnCreateContextMenuListener, androidx.lifecycle.f, q, androidx.savedstate.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final Object f951b = new Object();
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
    androidx.lifecycle.g U;
    n V;
    androidx.savedstate.a X;
    private int Y;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    Bundle f953d;
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
    h t;
    f u;
    Fragment w;
    int x;
    int y;
    String z;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f952c = 0;
    String g = UUID.randomUUID().toString();
    String j = null;
    private Boolean l = null;
    h v = new h();
    boolean F = true;
    boolean L = true;
    Runnable N = new a();
    Lifecycle.State T = Lifecycle.State.RESUMED;
    androidx.lifecycle.k<androidx.lifecycle.f> W = new androidx.lifecycle.k<>();

    public static class InstantiationException extends RuntimeException {
        public InstantiationException(String str, Exception exc) {
            super(str, exc);
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Bundle f955b;

        static class a implements Parcelable.ClassLoaderCreator<SavedState> {
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
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            Bundle bundle = parcel.readBundle();
            this.f955b = bundle;
            if (classLoader == null || bundle == null) {
                return;
            }
            bundle.setClassLoader(classLoader);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeBundle(this.f955b);
        }
    }

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Fragment.this.m1();
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

    class c extends androidx.fragment.app.c {
        c() {
        }

        @Override // androidx.fragment.app.c
        public View e(int i) {
            View view = Fragment.this.I;
            if (view != null) {
                return view.findViewById(i);
            }
            throw new IllegalStateException("Fragment " + this + " does not have a view");
        }

        @Override // androidx.fragment.app.c
        public boolean f() {
            return Fragment.this.I != null;
        }
    }

    static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        View f959a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Animator f960b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f961c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f962d;
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
        androidx.core.app.e o;
        androidx.core.app.e p;
        boolean q;
        e r;
        boolean s;

        d() {
            Object obj = Fragment.f951b;
            this.h = obj;
            this.i = null;
            this.j = obj;
            this.k = null;
            this.l = obj;
        }
    }

    interface e {
        void a();

        void b();
    }

    public Fragment() {
        L();
    }

    private void L() {
        this.U = new androidx.lifecycle.g(this);
        this.X = androidx.savedstate.a.a(this);
        if (Build.VERSION.SDK_INT >= 19) {
            this.U.a(new androidx.lifecycle.d() { // from class: androidx.fragment.app.Fragment.2
                @Override // androidx.lifecycle.d
                public void d(androidx.lifecycle.f fVar, Lifecycle.Event event) {
                    View view;
                    if (event != Lifecycle.Event.ON_STOP || (view = Fragment.this.I) == null) {
                        return;
                    }
                    view.cancelPendingInputEvents();
                }
            });
        }
    }

    @Deprecated
    public static Fragment N(Context context, String str, Bundle bundle) {
        try {
            Fragment fragmentNewInstance = androidx.fragment.app.e.d(context.getClassLoader(), str).getConstructor(new Class[0]).newInstance(new Object[0]);
            if (bundle != null) {
                bundle.setClassLoader(fragmentNewInstance.getClass().getClassLoader());
                fragmentNewInstance.g1(bundle);
            }
            return fragmentNewInstance;
        } catch (IllegalAccessException e2) {
            throw new InstantiationException("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e2);
        } catch (java.lang.InstantiationException e3) {
            throw new InstantiationException("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e3);
        } catch (NoSuchMethodException e4) {
            throw new InstantiationException("Unable to instantiate fragment " + str + ": could not find Fragment constructor", e4);
        } catch (InvocationTargetException e5) {
            throw new InstantiationException("Unable to instantiate fragment " + str + ": calling Fragment constructor caused an exception", e5);
        }
    }

    private d f() {
        if (this.M == null) {
            this.M = new d();
        }
        return this.M;
    }

    public final Fragment A() {
        return this.w;
    }

    public void A0(Bundle bundle) {
        this.G = true;
    }

    public Object B() {
        d dVar = this.M;
        if (dVar == null) {
            return null;
        }
        Object obj = dVar.j;
        return obj == f951b ? s() : obj;
    }

    void B0(Bundle bundle) {
        this.v.S0();
        this.f952c = 2;
        this.G = false;
        U(bundle);
        if (this.G) {
            this.v.y();
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onActivityCreated()");
    }

    public final Resources C() {
        return Z0().getResources();
    }

    void C0() {
        this.v.p(this.u, new c(), this);
        this.G = false;
        X(this.u.i());
        if (this.G) {
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onAttach()");
    }

    public final boolean D() {
        return this.C;
    }

    void D0(Configuration configuration) {
        onConfigurationChanged(configuration);
        this.v.z(configuration);
    }

    public Object E() {
        d dVar = this.M;
        if (dVar == null) {
            return null;
        }
        Object obj = dVar.h;
        return obj == f951b ? q() : obj;
    }

    boolean E0(MenuItem menuItem) {
        if (this.A) {
            return false;
        }
        return Z(menuItem) || this.v.A(menuItem);
    }

    public Object F() {
        d dVar = this.M;
        if (dVar == null) {
            return null;
        }
        return dVar.k;
    }

    void F0(Bundle bundle) {
        this.v.S0();
        this.f952c = 1;
        this.G = false;
        this.X.c(bundle);
        a0(bundle);
        this.S = true;
        if (this.G) {
            this.U.i(Lifecycle.Event.ON_CREATE);
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onCreate()");
    }

    public Object G() {
        d dVar = this.M;
        if (dVar == null) {
            return null;
        }
        Object obj = dVar.l;
        return obj == f951b ? F() : obj;
    }

    boolean G0(Menu menu, MenuInflater menuInflater) {
        boolean z = false;
        if (this.A) {
            return false;
        }
        if (this.E && this.F) {
            z = true;
            d0(menu, menuInflater);
        }
        return z | this.v.C(menu, menuInflater);
    }

    int H() {
        d dVar = this.M;
        if (dVar == null) {
            return 0;
        }
        return dVar.f961c;
    }

    void H0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.v.S0();
        this.r = true;
        this.V = new n();
        View viewE0 = e0(layoutInflater, viewGroup, bundle);
        this.I = viewE0;
        if (viewE0 != null) {
            this.V.e();
            this.W.h(this.V);
        } else {
            if (this.V.f()) {
                throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
            }
            this.V = null;
        }
    }

    public final String I(int i) {
        return C().getString(i);
    }

    void I0() {
        this.v.D();
        this.U.i(Lifecycle.Event.ON_DESTROY);
        this.f952c = 0;
        this.G = false;
        this.S = false;
        f0();
        if (this.G) {
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDestroy()");
    }

    public final Fragment J() {
        String str;
        Fragment fragment = this.i;
        if (fragment != null) {
            return fragment;
        }
        h hVar = this.t;
        if (hVar == null || (str = this.j) == null) {
            return null;
        }
        return hVar.k.get(str);
    }

    void J0() {
        this.v.E();
        if (this.I != null) {
            this.V.b(Lifecycle.Event.ON_DESTROY);
        }
        this.f952c = 1;
        this.G = false;
        h0();
        if (this.G) {
            b.k.a.a.b(this).c();
            this.r = false;
        } else {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDestroyView()");
        }
    }

    public View K() {
        return this.I;
    }

    void K0() {
        this.G = false;
        i0();
        this.R = null;
        if (this.G) {
            if (this.v.D0()) {
                return;
            }
            this.v.D();
            this.v = new h();
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDetach()");
    }

    LayoutInflater L0(Bundle bundle) {
        LayoutInflater layoutInflaterJ0 = j0(bundle);
        this.R = layoutInflaterJ0;
        return layoutInflaterJ0;
    }

    void M() {
        L();
        this.g = UUID.randomUUID().toString();
        this.m = false;
        this.n = false;
        this.o = false;
        this.p = false;
        this.q = false;
        this.s = 0;
        this.t = null;
        this.v = new h();
        this.u = null;
        this.x = 0;
        this.y = 0;
        this.z = null;
        this.A = false;
        this.B = false;
    }

    void M0() {
        onLowMemory();
        this.v.F();
    }

    void N0(boolean z) {
        n0(z);
        this.v.G(z);
    }

    public final boolean O() {
        return this.B;
    }

    boolean O0(MenuItem menuItem) {
        if (this.A) {
            return false;
        }
        return (this.E && this.F && o0(menuItem)) || this.v.V(menuItem);
    }

    boolean P() {
        d dVar = this.M;
        if (dVar == null) {
            return false;
        }
        return dVar.s;
    }

    void P0(Menu menu) {
        if (this.A) {
            return;
        }
        if (this.E && this.F) {
            p0(menu);
        }
        this.v.W(menu);
    }

    final boolean Q() {
        return this.s > 0;
    }

    void Q0() {
        this.v.Y();
        if (this.I != null) {
            this.V.b(Lifecycle.Event.ON_PAUSE);
        }
        this.U.i(Lifecycle.Event.ON_PAUSE);
        this.f952c = 3;
        this.G = false;
        q0();
        if (this.G) {
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onPause()");
    }

    boolean R() {
        d dVar = this.M;
        if (dVar == null) {
            return false;
        }
        return dVar.q;
    }

    void R0(boolean z) {
        r0(z);
        this.v.Z(z);
    }

    public final boolean S() {
        h hVar = this.t;
        if (hVar == null) {
            return false;
        }
        return hVar.H0();
    }

    boolean S0(Menu menu) {
        boolean z = false;
        if (this.A) {
            return false;
        }
        if (this.E && this.F) {
            z = true;
            s0(menu);
        }
        return z | this.v.a0(menu);
    }

    void T() {
        this.v.S0();
    }

    void T0() {
        boolean zF0 = this.t.F0(this);
        Boolean bool = this.l;
        if (bool == null || bool.booleanValue() != zF0) {
            this.l = Boolean.valueOf(zF0);
            t0(zF0);
            this.v.b0();
        }
    }

    public void U(Bundle bundle) {
        this.G = true;
    }

    void U0() {
        this.v.S0();
        this.v.l0();
        this.f952c = 4;
        this.G = false;
        v0();
        if (!this.G) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onResume()");
        }
        androidx.lifecycle.g gVar = this.U;
        Lifecycle.Event event = Lifecycle.Event.ON_RESUME;
        gVar.i(event);
        if (this.I != null) {
            this.V.b(event);
        }
        this.v.c0();
        this.v.l0();
    }

    public void V(int i, int i2, Intent intent) {
    }

    void V0(Bundle bundle) {
        w0(bundle);
        this.X.d(bundle);
        Parcelable parcelableE1 = this.v.e1();
        if (parcelableE1 != null) {
            bundle.putParcelable("android:support:fragments", parcelableE1);
        }
    }

    @Deprecated
    public void W(Activity activity) {
        this.G = true;
    }

    void W0() {
        this.v.S0();
        this.v.l0();
        this.f952c = 3;
        this.G = false;
        x0();
        if (!this.G) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onStart()");
        }
        androidx.lifecycle.g gVar = this.U;
        Lifecycle.Event event = Lifecycle.Event.ON_START;
        gVar.i(event);
        if (this.I != null) {
            this.V.b(event);
        }
        this.v.d0();
    }

    public void X(Context context) {
        this.G = true;
        f fVar = this.u;
        Activity activityG = fVar == null ? null : fVar.g();
        if (activityG != null) {
            this.G = false;
            W(activityG);
        }
    }

    void X0() {
        this.v.f0();
        if (this.I != null) {
            this.V.b(Lifecycle.Event.ON_STOP);
        }
        this.U.i(Lifecycle.Event.ON_STOP);
        this.f952c = 2;
        this.G = false;
        y0();
        if (this.G) {
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onStop()");
    }

    public void Y(Fragment fragment) {
    }

    public final FragmentActivity Y0() {
        FragmentActivity fragmentActivityI = i();
        if (fragmentActivityI != null) {
            return fragmentActivityI;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to an activity.");
    }

    public boolean Z(MenuItem menuItem) {
        return false;
    }

    public final Context Z0() {
        Context contextP = p();
        if (contextP != null) {
            return contextP;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to a context.");
    }

    @Override // androidx.lifecycle.f
    public Lifecycle a() {
        return this.U;
    }

    public void a0(Bundle bundle) {
        this.G = true;
        c1(bundle);
        if (this.v.G0(1)) {
            return;
        }
        this.v.B();
    }

    public final g a1() {
        g gVarU = u();
        if (gVarU != null) {
            return gVarU;
        }
        throw new IllegalStateException("Fragment " + this + " not associated with a fragment manager.");
    }

    void b() {
        d dVar = this.M;
        e eVar = null;
        if (dVar != null) {
            dVar.q = false;
            e eVar2 = dVar.r;
            dVar.r = null;
            eVar = eVar2;
        }
        if (eVar != null) {
            eVar.a();
        }
    }

    public Animation b0(int i, boolean z, int i2) {
        return null;
    }

    public final View b1() {
        View viewK = K();
        if (viewK != null) {
            return viewK;
        }
        throw new IllegalStateException("Fragment " + this + " did not return a View from onCreateView() or this was called before onCreateView().");
    }

    public Animator c0(int i, boolean z, int i2) {
        return null;
    }

    void c1(Bundle bundle) {
        Parcelable parcelable;
        if (bundle == null || (parcelable = bundle.getParcelable("android:support:fragments")) == null) {
            return;
        }
        this.v.c1(parcelable);
        this.v.B();
    }

    @Override // androidx.savedstate.b
    public final SavedStateRegistry d() {
        return this.X.b();
    }

    public void d0(Menu menu, MenuInflater menuInflater) {
    }

    final void d1(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = this.e;
        if (sparseArray != null) {
            this.J.restoreHierarchyState(sparseArray);
            this.e = null;
        }
        this.G = false;
        A0(bundle);
        if (this.G) {
            if (this.I != null) {
                this.V.b(Lifecycle.Event.ON_CREATE);
            }
        } else {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onViewStateRestored()");
        }
    }

    public void e(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.x));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.y));
        printWriter.print(" mTag=");
        printWriter.println(this.z);
        printWriter.print(str);
        printWriter.print("mState=");
        printWriter.print(this.f952c);
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
        if (this.f953d != null) {
            printWriter.print(str);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.f953d);
        }
        if (this.e != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.e);
        }
        Fragment fragmentJ = J();
        if (fragmentJ != null) {
            printWriter.print(str);
            printWriter.print("mTarget=");
            printWriter.print(fragmentJ);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.k);
        }
        if (x() != 0) {
            printWriter.print(str);
            printWriter.print("mNextAnim=");
            printWriter.println(x());
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
        if (l() != null) {
            printWriter.print(str);
            printWriter.print("mAnimatingAway=");
            printWriter.println(l());
            printWriter.print(str);
            printWriter.print("mStateAfterAnimating=");
            printWriter.println(H());
        }
        if (p() != null) {
            b.k.a.a.b(this).a(str, fileDescriptor, printWriter, strArr);
        }
        printWriter.print(str);
        printWriter.println("Child " + this.v + ":");
        this.v.b(str + "  ", fileDescriptor, printWriter, strArr);
    }

    public View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i = this.Y;
        if (i != 0) {
            return layoutInflater.inflate(i, viewGroup, false);
        }
        return null;
    }

    void e1(View view) {
        f().f959a = view;
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void f0() {
        this.G = true;
    }

    void f1(Animator animator) {
        f().f960b = animator;
    }

    Fragment g(String str) {
        return str.equals(this.g) ? this : this.v.q0(str);
    }

    public void g0() {
    }

    public void g1(Bundle bundle) {
        if (this.t != null && S()) {
            throw new IllegalStateException("Fragment already added and state has been saved");
        }
        this.h = bundle;
    }

    @Override // androidx.lifecycle.q
    public p h() {
        h hVar = this.t;
        if (hVar != null) {
            return hVar.A0(this);
        }
        throw new IllegalStateException("Can't access ViewModels from detached fragment");
    }

    public void h0() {
        this.G = true;
    }

    void h1(boolean z) {
        f().s = z;
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public final FragmentActivity i() {
        f fVar = this.u;
        if (fVar == null) {
            return null;
        }
        return (FragmentActivity) fVar.g();
    }

    public void i0() {
        this.G = true;
    }

    void i1(int i) {
        if (this.M == null && i == 0) {
            return;
        }
        f().f962d = i;
    }

    public boolean j() {
        Boolean bool;
        d dVar = this.M;
        if (dVar == null || (bool = dVar.n) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public LayoutInflater j0(Bundle bundle) {
        return w(bundle);
    }

    void j1(int i, int i2) {
        if (this.M == null && i == 0 && i2 == 0) {
            return;
        }
        f();
        d dVar = this.M;
        dVar.e = i;
        dVar.f = i2;
    }

    public boolean k() {
        Boolean bool;
        d dVar = this.M;
        if (dVar == null || (bool = dVar.m) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public void k0(boolean z) {
    }

    void k1(e eVar) {
        f();
        d dVar = this.M;
        e eVar2 = dVar.r;
        if (eVar == eVar2) {
            return;
        }
        if (eVar != null && eVar2 != null) {
            throw new IllegalStateException("Trying to set a replacement startPostponedEnterTransition on " + this);
        }
        if (dVar.q) {
            dVar.r = eVar;
        }
        if (eVar != null) {
            eVar.b();
        }
    }

    View l() {
        d dVar = this.M;
        if (dVar == null) {
            return null;
        }
        return dVar.f959a;
    }

    @Deprecated
    public void l0(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        this.G = true;
    }

    void l1(int i) {
        f().f961c = i;
    }

    Animator m() {
        d dVar = this.M;
        if (dVar == null) {
            return null;
        }
        return dVar.f960b;
    }

    public void m0(Context context, AttributeSet attributeSet, Bundle bundle) {
        this.G = true;
        f fVar = this.u;
        Activity activityG = fVar == null ? null : fVar.g();
        if (activityG != null) {
            this.G = false;
            l0(activityG, attributeSet, bundle);
        }
    }

    public void m1() {
        h hVar = this.t;
        if (hVar == null || hVar.u == null) {
            f().q = false;
        } else if (Looper.myLooper() != this.t.u.j().getLooper()) {
            this.t.u.j().postAtFrontOfQueue(new b());
        } else {
            b();
        }
    }

    public final Bundle n() {
        return this.h;
    }

    public void n0(boolean z) {
    }

    public final g o() {
        if (this.u != null) {
            return this.v;
        }
        throw new IllegalStateException("Fragment " + this + " has not been attached yet.");
    }

    public boolean o0(MenuItem menuItem) {
        return false;
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        this.G = true;
    }

    @Override // android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        Y0().onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        this.G = true;
    }

    public Context p() {
        f fVar = this.u;
        if (fVar == null) {
            return null;
        }
        return fVar.i();
    }

    public void p0(Menu menu) {
    }

    public Object q() {
        d dVar = this.M;
        if (dVar == null) {
            return null;
        }
        return dVar.g;
    }

    public void q0() {
        this.G = true;
    }

    androidx.core.app.e r() {
        d dVar = this.M;
        if (dVar == null) {
            return null;
        }
        return dVar.o;
    }

    public void r0(boolean z) {
    }

    public Object s() {
        d dVar = this.M;
        if (dVar == null) {
            return null;
        }
        return dVar.i;
    }

    public void s0(Menu menu) {
    }

    androidx.core.app.e t() {
        d dVar = this.M;
        if (dVar == null) {
            return null;
        }
        return dVar.p;
    }

    public void t0(boolean z) {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        b.g.j.a.a(this, sb);
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

    public final g u() {
        return this.t;
    }

    public void u0(int i, String[] strArr, int[] iArr) {
    }

    public final Object v() {
        f fVar = this.u;
        if (fVar == null) {
            return null;
        }
        return fVar.m();
    }

    public void v0() {
        this.G = true;
    }

    @Deprecated
    public LayoutInflater w(Bundle bundle) {
        f fVar = this.u;
        if (fVar == null) {
            throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
        }
        LayoutInflater layoutInflaterN = fVar.n();
        b.g.k.f.b(layoutInflaterN, this.v.y0());
        return layoutInflaterN;
    }

    public void w0(Bundle bundle) {
    }

    int x() {
        d dVar = this.M;
        if (dVar == null) {
            return 0;
        }
        return dVar.f962d;
    }

    public void x0() {
        this.G = true;
    }

    int y() {
        d dVar = this.M;
        if (dVar == null) {
            return 0;
        }
        return dVar.e;
    }

    public void y0() {
        this.G = true;
    }

    int z() {
        d dVar = this.M;
        if (dVar == null) {
            return 0;
        }
        return dVar.f;
    }

    public void z0(View view, Bundle bundle) {
    }
}
