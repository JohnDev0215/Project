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
import androidx.lifecycle.q;
import androidx.lifecycle.r;
import androidx.savedstate.SavedStateRegistry;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class Fragment implements ComponentCallbacks, View.OnCreateContextMenuListener, androidx.lifecycle.g, r, androidx.savedstate.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final Object f1091b = new Object();
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
    n V;
    androidx.savedstate.a X;
    private int Y;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    Bundle f1093d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    SparseArray<Parcelable> f1094e;
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
    int f1092c = 0;
    String g = UUID.randomUUID().toString();
    String j = null;
    private Boolean l = null;
    h v = new h();
    boolean F = true;
    boolean L = true;
    Runnable N = new a();
    Lifecycle.State T = Lifecycle.State.RESUMED;
    androidx.lifecycle.l<androidx.lifecycle.g> W = new androidx.lifecycle.l<>();

    public static class InstantiationException extends RuntimeException {
        public InstantiationException(String str, Exception exc) {
            super(str, exc);
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Bundle f1096b;

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
            this.f1096b = bundle;
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
            parcel.writeBundle(this.f1096b);
        }
    }

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
        View f1100a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Animator f1101b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1102c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1103d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f1104e;
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
            Object obj = Fragment.f1091b;
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
        U();
    }

    private void U() {
        this.U = new androidx.lifecycle.h(this);
        this.X = androidx.savedstate.a.a(this);
        if (Build.VERSION.SDK_INT >= 19) {
            this.U.a(new androidx.lifecycle.e() { // from class: androidx.fragment.app.Fragment.2
                @Override // androidx.lifecycle.e
                public void d(androidx.lifecycle.g gVar, Lifecycle.Event event) {
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
    public static Fragment W(Context context, String str, Bundle bundle) {
        try {
            Fragment fragmentNewInstance = androidx.fragment.app.e.d(context.getClassLoader(), str).getConstructor(new Class[0]).newInstance(new Object[0]);
            if (bundle != null) {
                bundle.setClassLoader(fragmentNewInstance.getClass().getClassLoader());
                fragmentNewInstance.q1(bundle);
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

    private d j() {
        if (this.M == null) {
            this.M = new d();
        }
        return this.M;
    }

    public Object A() {
        d dVar = this.M;
        if (dVar == null) {
            return null;
        }
        return dVar.i;
    }

    public void A0() {
        this.G = true;
    }

    androidx.core.app.e B() {
        d dVar = this.M;
        if (dVar == null) {
            return null;
        }
        return dVar.p;
    }

    public void B0(boolean z) {
    }

    public final g C() {
        return this.t;
    }

    public void C0(Menu menu) {
    }

    public final Object D() {
        f fVar = this.u;
        if (fVar == null) {
            return null;
        }
        return fVar.l();
    }

    public void D0(boolean z) {
    }

    @Deprecated
    public LayoutInflater E(Bundle bundle) {
        f fVar = this.u;
        if (fVar == null) {
            throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
        }
        LayoutInflater layoutInflaterM = fVar.m();
        b.g.k.f.b(layoutInflaterM, this.v.y0());
        return layoutInflaterM;
    }

    public void E0(int i, String[] strArr, int[] iArr) {
    }

    int F() {
        d dVar = this.M;
        if (dVar == null) {
            return 0;
        }
        return dVar.f1103d;
    }

    public void F0() {
        this.G = true;
    }

    int G() {
        d dVar = this.M;
        if (dVar == null) {
            return 0;
        }
        return dVar.f1104e;
    }

    public void G0(Bundle bundle) {
    }

    int H() {
        d dVar = this.M;
        if (dVar == null) {
            return 0;
        }
        return dVar.f;
    }

    public void H0() {
        this.G = true;
    }

    public final Fragment I() {
        return this.w;
    }

    public void I0() {
        this.G = true;
    }

    public Object J() {
        d dVar = this.M;
        if (dVar == null) {
            return null;
        }
        Object obj = dVar.j;
        return obj == f1091b ? A() : obj;
    }

    public void J0(View view, Bundle bundle) {
    }

    public final Resources K() {
        return j1().getResources();
    }

    public void K0(Bundle bundle) {
        this.G = true;
    }

    public final boolean L() {
        return this.C;
    }

    void L0(Bundle bundle) {
        this.v.S0();
        this.f1092c = 2;
        this.G = false;
        e0(bundle);
        if (this.G) {
            this.v.y();
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onActivityCreated()");
    }

    public Object M() {
        d dVar = this.M;
        if (dVar == null) {
            return null;
        }
        Object obj = dVar.h;
        return obj == f1091b ? y() : obj;
    }

    void M0() {
        this.v.p(this.u, new c(), this);
        this.G = false;
        h0(this.u.h());
        if (this.G) {
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onAttach()");
    }

    public Object N() {
        d dVar = this.M;
        if (dVar == null) {
            return null;
        }
        return dVar.k;
    }

    void N0(Configuration configuration) {
        onConfigurationChanged(configuration);
        this.v.z(configuration);
    }

    public Object O() {
        d dVar = this.M;
        if (dVar == null) {
            return null;
        }
        Object obj = dVar.l;
        return obj == f1091b ? N() : obj;
    }

    boolean O0(MenuItem menuItem) {
        if (this.A) {
            return false;
        }
        return j0(menuItem) || this.v.A(menuItem);
    }

    int P() {
        d dVar = this.M;
        if (dVar == null) {
            return 0;
        }
        return dVar.f1102c;
    }

    void P0(Bundle bundle) {
        this.v.S0();
        this.f1092c = 1;
        this.G = false;
        this.X.c(bundle);
        k0(bundle);
        this.S = true;
        if (this.G) {
            this.U.i(Lifecycle.Event.ON_CREATE);
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onCreate()");
    }

    public final String Q(int i) {
        return K().getString(i);
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
        return z | this.v.C(menu, menuInflater);
    }

    public final String R(int i, Object... objArr) {
        return K().getString(i, objArr);
    }

    void R0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.v.S0();
        this.r = true;
        this.V = new n();
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

    public final Fragment S() {
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

    void S0() {
        this.v.D();
        this.U.i(Lifecycle.Event.ON_DESTROY);
        this.f1092c = 0;
        this.G = false;
        this.S = false;
        p0();
        if (this.G) {
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDestroy()");
    }

    public View T() {
        return this.I;
    }

    void T0() {
        this.v.E();
        if (this.I != null) {
            this.V.b(Lifecycle.Event.ON_DESTROY);
        }
        this.f1092c = 1;
        this.G = false;
        r0();
        if (this.G) {
            b.l.a.a.b(this).c();
            this.r = false;
        } else {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDestroyView()");
        }
    }

    void U0() {
        this.G = false;
        s0();
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

    void V() {
        U();
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

    LayoutInflater V0(Bundle bundle) {
        LayoutInflater layoutInflaterT0 = t0(bundle);
        this.R = layoutInflaterT0;
        return layoutInflaterT0;
    }

    void W0() {
        onLowMemory();
        this.v.F();
    }

    public final boolean X() {
        return this.B;
    }

    void X0(boolean z) {
        x0(z);
        this.v.G(z);
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
        return (this.E && this.F && y0(menuItem)) || this.v.V(menuItem);
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
        this.v.W(menu);
    }

    @Override // androidx.lifecycle.g
    public Lifecycle a() {
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
        this.v.Y();
        if (this.I != null) {
            this.V.b(Lifecycle.Event.ON_PAUSE);
        }
        this.U.i(Lifecycle.Event.ON_PAUSE);
        this.f1092c = 3;
        this.G = false;
        A0();
        if (this.G) {
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onPause()");
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

    public final boolean b0() {
        return this.f1092c >= 4;
    }

    void b1(boolean z) {
        B0(z);
        this.v.Z(z);
    }

    public final boolean c0() {
        h hVar = this.t;
        if (hVar == null) {
            return false;
        }
        return hVar.H0();
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
        return z | this.v.a0(menu);
    }

    @Override // androidx.savedstate.b
    public final SavedStateRegistry d() {
        return this.X.b();
    }

    void d0() {
        this.v.S0();
    }

    void d1() {
        boolean zF0 = this.t.F0(this);
        Boolean bool = this.l;
        if (bool == null || bool.booleanValue() != zF0) {
            this.l = Boolean.valueOf(zF0);
            D0(zF0);
            this.v.b0();
        }
    }

    public void e0(Bundle bundle) {
        this.G = true;
    }

    void e1() {
        this.v.S0();
        this.v.l0();
        this.f1092c = 4;
        this.G = false;
        F0();
        if (!this.G) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onResume()");
        }
        androidx.lifecycle.h hVar = this.U;
        Lifecycle.Event event = Lifecycle.Event.ON_RESUME;
        hVar.i(event);
        if (this.I != null) {
            this.V.b(event);
        }
        this.v.c0();
        this.v.l0();
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void f0(int i, int i2, Intent intent) {
    }

    void f1(Bundle bundle) {
        G0(bundle);
        this.X.d(bundle);
        Parcelable parcelableE1 = this.v.e1();
        if (parcelableE1 != null) {
            bundle.putParcelable("android:support:fragments", parcelableE1);
        }
    }

    @Deprecated
    public void g0(Activity activity) {
        this.G = true;
    }

    void g1() {
        this.v.S0();
        this.v.l0();
        this.f1092c = 3;
        this.G = false;
        H0();
        if (!this.G) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onStart()");
        }
        androidx.lifecycle.h hVar = this.U;
        Lifecycle.Event event = Lifecycle.Event.ON_START;
        hVar.i(event);
        if (this.I != null) {
            this.V.b(event);
        }
        this.v.d0();
    }

    public void h0(Context context) {
        this.G = true;
        f fVar = this.u;
        Activity activityG = fVar == null ? null : fVar.g();
        if (activityG != null) {
            this.G = false;
            g0(activityG);
        }
    }

    void h1() {
        this.v.f0();
        if (this.I != null) {
            this.V.b(Lifecycle.Event.ON_STOP);
        }
        this.U.i(Lifecycle.Event.ON_STOP);
        this.f1092c = 2;
        this.G = false;
        I0();
        if (this.G) {
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onStop()");
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
        printWriter.print(this.f1092c);
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
        if (this.f1093d != null) {
            printWriter.print(str);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.f1093d);
        }
        if (this.f1094e != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.f1094e);
        }
        Fragment fragmentS = S();
        if (fragmentS != null) {
            printWriter.print(str);
            printWriter.print("mTarget=");
            printWriter.print(fragmentS);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.k);
        }
        if (F() != 0) {
            printWriter.print(str);
            printWriter.print("mNextAnim=");
            printWriter.println(F());
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
        if (t() != null) {
            printWriter.print(str);
            printWriter.print("mAnimatingAway=");
            printWriter.println(t());
            printWriter.print(str);
            printWriter.print("mStateAfterAnimating=");
            printWriter.println(P());
        }
        if (x() != null) {
            b.l.a.a.b(this).a(str, fileDescriptor, printWriter, strArr);
        }
        printWriter.print(str);
        printWriter.println("Child " + this.v + ":");
        this.v.b(str + "  ", fileDescriptor, printWriter, strArr);
    }

    public void i0(Fragment fragment) {
    }

    public final FragmentActivity i1() {
        FragmentActivity fragmentActivityP = p();
        if (fragmentActivityP != null) {
            return fragmentActivityP;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to an activity.");
    }

    public boolean j0(MenuItem menuItem) {
        return false;
    }

    public final Context j1() {
        Context contextX = x();
        if (contextX != null) {
            return contextX;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to a context.");
    }

    public void k0(Bundle bundle) {
        this.G = true;
        m1(bundle);
        if (this.v.G0(1)) {
            return;
        }
        this.v.B();
    }

    public final g k1() {
        g gVarC = C();
        if (gVarC != null) {
            return gVarC;
        }
        throw new IllegalStateException("Fragment " + this + " not associated with a fragment manager.");
    }

    public Animation l0(int i, boolean z, int i2) {
        return null;
    }

    public final View l1() {
        View viewT = T();
        if (viewT != null) {
            return viewT;
        }
        throw new IllegalStateException("Fragment " + this + " did not return a View from onCreateView() or this was called before onCreateView().");
    }

    Fragment m(String str) {
        return str.equals(this.g) ? this : this.v.q0(str);
    }

    public Animator m0(int i, boolean z, int i2) {
        return null;
    }

    void m1(Bundle bundle) {
        Parcelable parcelable;
        if (bundle == null || (parcelable = bundle.getParcelable("android:support:fragments")) == null) {
            return;
        }
        this.v.c1(parcelable);
        this.v.B();
    }

    public void n0(Menu menu, MenuInflater menuInflater) {
    }

    final void n1(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = this.f1094e;
        if (sparseArray != null) {
            this.J.restoreHierarchyState(sparseArray);
            this.f1094e = null;
        }
        this.G = false;
        K0(bundle);
        if (this.G) {
            if (this.I != null) {
                this.V.b(Lifecycle.Event.ON_CREATE);
            }
        } else {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onViewStateRestored()");
        }
    }

    @Override // androidx.lifecycle.r
    public q o() {
        h hVar = this.t;
        if (hVar != null) {
            return hVar.A0(this);
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
        j().f1100a = view;
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

    public final FragmentActivity p() {
        f fVar = this.u;
        if (fVar == null) {
            return null;
        }
        return (FragmentActivity) fVar.g();
    }

    public void p0() {
        this.G = true;
    }

    void p1(Animator animator) {
        j().f1101b = animator;
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

    public boolean s() {
        Boolean bool;
        d dVar = this.M;
        if (dVar == null || (bool = dVar.m) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public void s0() {
        this.G = true;
    }

    void s1(int i) {
        if (this.M == null && i == 0) {
            return;
        }
        j().f1103d = i;
    }

    View t() {
        d dVar = this.M;
        if (dVar == null) {
            return null;
        }
        return dVar.f1100a;
    }

    public LayoutInflater t0(Bundle bundle) {
        return E(bundle);
    }

    void t1(int i, int i2) {
        if (this.M == null && i == 0 && i2 == 0) {
            return;
        }
        j();
        d dVar = this.M;
        dVar.f1104e = i;
        dVar.f = i2;
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

    Animator u() {
        d dVar = this.M;
        if (dVar == null) {
            return null;
        }
        return dVar.f1101b;
    }

    public void u0(boolean z) {
    }

    void u1(e eVar) {
        j();
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

    public final Bundle v() {
        return this.h;
    }

    @Deprecated
    public void v0(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        this.G = true;
    }

    void v1(int i) {
        j().f1102c = i;
    }

    public final g w() {
        if (this.u != null) {
            return this.v;
        }
        throw new IllegalStateException("Fragment " + this + " has not been attached yet.");
    }

    public void w0(Context context, AttributeSet attributeSet, Bundle bundle) {
        this.G = true;
        f fVar = this.u;
        Activity activityG = fVar == null ? null : fVar.g();
        if (activityG != null) {
            this.G = false;
            v0(activityG, attributeSet, bundle);
        }
    }

    public void w1(@SuppressLint({"UnknownNullness"}) Intent intent) {
        x1(intent, null);
    }

    public Context x() {
        f fVar = this.u;
        if (fVar == null) {
            return null;
        }
        return fVar.h();
    }

    public void x0(boolean z) {
    }

    public void x1(@SuppressLint({"UnknownNullness"}) Intent intent, Bundle bundle) {
        f fVar = this.u;
        if (fVar != null) {
            fVar.r(this, intent, -1, bundle);
            return;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }

    public Object y() {
        d dVar = this.M;
        if (dVar == null) {
            return null;
        }
        return dVar.g;
    }

    public boolean y0(MenuItem menuItem) {
        return false;
    }

    public void y1() {
        h hVar = this.t;
        if (hVar == null || hVar.u == null) {
            j().q = false;
        } else if (Looper.myLooper() != this.t.u.i().getLooper()) {
            this.t.u.i().postAtFrontOfQueue(new b());
        } else {
            b();
        }
    }

    androidx.core.app.e z() {
        d dVar = this.M;
        if (dVar == null) {
            return null;
        }
        return dVar.o;
    }

    public void z0(Menu menu) {
    }
}
