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
import androidx.lifecycle.d;
import androidx.savedstate.SavedStateRegistry;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class Fragment implements ComponentCallbacks, View.OnCreateContextMenuListener, androidx.lifecycle.g, androidx.lifecycle.s, androidx.savedstate.b {

    /* JADX INFO: renamed from: b */
    static final Object f1361b = new Object();
    boolean A;
    boolean B;
    boolean C;
    boolean D;
    boolean E;
    boolean F;
    private boolean G;
    ViewGroup H;
    View I;
    boolean J;
    boolean K;
    d L;
    Runnable M;
    boolean N;
    boolean O;
    float P;
    LayoutInflater Q;
    boolean R;
    d.b S;
    androidx.lifecycle.h T;
    s U;
    androidx.lifecycle.l<androidx.lifecycle.g> V;
    androidx.savedstate.a W;
    private int X;

    /* JADX INFO: renamed from: c */
    int f1362c;

    /* JADX INFO: renamed from: d */
    Bundle f1363d;

    /* JADX INFO: renamed from: e */
    SparseArray<Parcelable> f1364e;

    /* JADX INFO: renamed from: f */
    Boolean f1365f;

    /* JADX INFO: renamed from: g */
    String f1366g;

    /* JADX INFO: renamed from: h */
    Bundle f1367h;

    /* JADX INFO: renamed from: i */
    Fragment f1368i;
    String j;
    int k;
    private Boolean l;
    boolean m;
    boolean n;
    boolean o;
    boolean p;
    boolean q;
    boolean r;
    int s;
    j t;
    g<?> u;
    j v;
    Fragment w;
    int x;
    int y;
    String z;

    /* JADX INFO: renamed from: androidx.fragment.app.Fragment$2 */
    class AnonymousClass2 implements androidx.lifecycle.e {
        AnonymousClass2() {
        }

        @Override // androidx.lifecycle.e
        public void d(androidx.lifecycle.g gVar, d.a aVar) {
            View view;
            if (aVar != d.a.ON_STOP || (view = Fragment.this.I) == null) {
                return;
            }
            view.cancelPendingInputEvents();
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b */
        final Bundle f1370b;

        static class a implements Parcelable.ClassLoaderCreator<SavedState> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c */
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            Bundle bundle = parcel.readBundle();
            this.f1370b = bundle;
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
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeBundle(this.f1370b);
        }
    }

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Fragment.this.o1();
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

    class c extends androidx.fragment.app.d {
        c() {
        }

        @Override // androidx.fragment.app.d
        public View c(int i2) {
            View view = Fragment.this.I;
            if (view != null) {
                return view.findViewById(i2);
            }
            throw new IllegalStateException("Fragment " + this + " does not have a view");
        }

        @Override // androidx.fragment.app.d
        public boolean f() {
            return Fragment.this.I != null;
        }
    }

    static class d {

        /* JADX INFO: renamed from: a */
        View f1374a;

        /* JADX INFO: renamed from: b */
        Animator f1375b;

        /* JADX INFO: renamed from: c */
        int f1376c;

        /* JADX INFO: renamed from: d */
        int f1377d;

        /* JADX INFO: renamed from: e */
        int f1378e;

        /* JADX INFO: renamed from: f */
        Object f1379f = null;

        /* JADX INFO: renamed from: g */
        Object f1380g;

        /* JADX INFO: renamed from: h */
        Object f1381h;

        /* JADX INFO: renamed from: i */
        Object f1382i;
        Object j;
        Object k;
        Boolean l;
        Boolean m;
        androidx.core.app.f n;
        androidx.core.app.f o;
        boolean p;
        f q;
        boolean r;

        d() {
            Object obj = Fragment.f1361b;
            this.f1380g = obj;
            this.f1381h = null;
            this.f1382i = obj;
            this.j = null;
            this.k = obj;
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
        this.f1362c = -1;
        this.f1366g = UUID.randomUUID().toString();
        this.j = null;
        this.l = null;
        this.v = new k();
        this.F = true;
        this.K = true;
        this.M = new a();
        this.S = d.b.RESUMED;
        this.V = new androidx.lifecycle.l<>();
        M();
    }

    private void M() {
        this.T = new androidx.lifecycle.h(this);
        this.W = androidx.savedstate.a.a(this);
        if (Build.VERSION.SDK_INT >= 19) {
            this.T.a(new androidx.lifecycle.e() { // from class: androidx.fragment.app.Fragment.2
                AnonymousClass2() {
                }

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
    public static Fragment O(Context context, String str, Bundle bundle) {
        try {
            Fragment fragmentNewInstance = androidx.fragment.app.f.d(context.getClassLoader(), str).getConstructor(new Class[0]).newInstance(new Object[0]);
            if (bundle != null) {
                bundle.setClassLoader(fragmentNewInstance.getClass().getClassLoader());
                fragmentNewInstance.i1(bundle);
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

    private d f() {
        if (this.L == null) {
            this.L = new d();
        }
        return this.L;
    }

    public final j A() {
        j jVar = this.t;
        if (jVar != null) {
            return jVar;
        }
        throw new IllegalStateException("Fragment " + this + " not associated with a fragment manager.");
    }

    public void A0() {
        this.G = true;
    }

    public Object B() {
        d dVar = this.L;
        if (dVar == null) {
            return null;
        }
        Object obj = dVar.f1382i;
        return obj == f1361b ? s() : obj;
    }

    public void B0() {
        this.G = true;
    }

    public final Resources C() {
        return c1().getResources();
    }

    public void C0(View view, Bundle bundle) {
    }

    public final boolean D() {
        return this.C;
    }

    public void D0(Bundle bundle) {
        this.G = true;
    }

    public Object E() {
        d dVar = this.L;
        if (dVar == null) {
            return null;
        }
        Object obj = dVar.f1380g;
        return obj == f1361b ? q() : obj;
    }

    void E0(Bundle bundle) {
        this.v.C0();
        this.f1362c = 2;
        this.G = false;
        X(bundle);
        if (this.G) {
            this.v.r();
            return;
        }
        throw new t("Fragment " + this + " did not call through to super.onActivityCreated()");
    }

    public Object F() {
        d dVar = this.L;
        if (dVar == null) {
            return null;
        }
        return dVar.j;
    }

    void F0() {
        this.v.g(this.u, new c(), this);
        this.f1362c = 0;
        this.G = false;
        a0(this.u.h());
        if (this.G) {
            return;
        }
        throw new t("Fragment " + this + " did not call through to super.onAttach()");
    }

    public Object G() {
        d dVar = this.L;
        if (dVar == null) {
            return null;
        }
        Object obj = dVar.k;
        return obj == f1361b ? F() : obj;
    }

    void G0(Configuration configuration) {
        onConfigurationChanged(configuration);
        this.v.s(configuration);
    }

    int H() {
        d dVar = this.L;
        if (dVar == null) {
            return 0;
        }
        return dVar.f1376c;
    }

    boolean H0(MenuItem menuItem) {
        if (this.A) {
            return false;
        }
        return c0(menuItem) || this.v.t(menuItem);
    }

    public final String I(int i2) {
        return C().getString(i2);
    }

    void I0(Bundle bundle) {
        this.v.C0();
        this.f1362c = 1;
        this.G = false;
        this.W.c(bundle);
        d0(bundle);
        this.R = true;
        if (this.G) {
            this.T.i(d.a.ON_CREATE);
            return;
        }
        throw new t("Fragment " + this + " did not call through to super.onCreate()");
    }

    public final String J() {
        return this.z;
    }

    boolean J0(Menu menu, MenuInflater menuInflater) {
        boolean z = false;
        if (this.A) {
            return false;
        }
        if (this.E && this.F) {
            z = true;
            g0(menu, menuInflater);
        }
        return z | this.v.v(menu, menuInflater);
    }

    public final Fragment K() {
        String str;
        Fragment fragment = this.f1368i;
        if (fragment != null) {
            return fragment;
        }
        j jVar = this.t;
        if (jVar == null || (str = this.j) == null) {
            return null;
        }
        return jVar.W(str);
    }

    void K0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.v.C0();
        this.r = true;
        this.U = new s();
        View viewH0 = h0(layoutInflater, viewGroup, bundle);
        this.I = viewH0;
        if (viewH0 != null) {
            this.U.c();
            this.V.h(this.U);
        } else {
            if (this.U.f()) {
                throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
            }
            this.U = null;
        }
    }

    public View L() {
        return this.I;
    }

    void L0() {
        this.v.w();
        this.T.i(d.a.ON_DESTROY);
        this.f1362c = 0;
        this.G = false;
        this.R = false;
        i0();
        if (this.G) {
            return;
        }
        throw new t("Fragment " + this + " did not call through to super.onDestroy()");
    }

    void M0() {
        this.v.x();
        if (this.I != null) {
            this.U.b(d.a.ON_DESTROY);
        }
        this.f1362c = 1;
        this.G = false;
        k0();
        if (this.G) {
            b.m.a.a.b(this).c();
            this.r = false;
        } else {
            throw new t("Fragment " + this + " did not call through to super.onDestroyView()");
        }
    }

    void N() {
        M();
        this.f1366g = UUID.randomUUID().toString();
        this.m = false;
        this.n = false;
        this.o = false;
        this.p = false;
        this.q = false;
        this.s = 0;
        this.t = null;
        this.v = new k();
        this.u = null;
        this.x = 0;
        this.y = 0;
        this.z = null;
        this.A = false;
        this.B = false;
    }

    void N0() {
        this.f1362c = -1;
        this.G = false;
        l0();
        this.Q = null;
        if (this.G) {
            if (this.v.p0()) {
                return;
            }
            this.v.w();
            this.v = new k();
            return;
        }
        throw new t("Fragment " + this + " did not call through to super.onDetach()");
    }

    LayoutInflater O0(Bundle bundle) {
        LayoutInflater layoutInflaterM0 = m0(bundle);
        this.Q = layoutInflaterM0;
        return layoutInflaterM0;
    }

    public final boolean P() {
        return this.B;
    }

    void P0() {
        onLowMemory();
        this.v.y();
    }

    boolean Q() {
        d dVar = this.L;
        if (dVar == null) {
            return false;
        }
        return dVar.r;
    }

    void Q0(boolean z) {
        q0(z);
        this.v.z(z);
    }

    final boolean R() {
        return this.s > 0;
    }

    boolean R0(MenuItem menuItem) {
        if (this.A) {
            return false;
        }
        return (this.E && this.F && r0(menuItem)) || this.v.A(menuItem);
    }

    boolean S() {
        d dVar = this.L;
        if (dVar == null) {
            return false;
        }
        return dVar.p;
    }

    void S0(Menu menu) {
        if (this.A) {
            return;
        }
        if (this.E && this.F) {
            s0(menu);
        }
        this.v.B(menu);
    }

    public final boolean T() {
        return this.n;
    }

    void T0() {
        this.v.D();
        if (this.I != null) {
            this.U.b(d.a.ON_PAUSE);
        }
        this.T.i(d.a.ON_PAUSE);
        this.f1362c = 3;
        this.G = false;
        t0();
        if (this.G) {
            return;
        }
        throw new t("Fragment " + this + " did not call through to super.onPause()");
    }

    final boolean U() {
        Fragment fragmentZ = z();
        return fragmentZ != null && (fragmentZ.T() || fragmentZ.U());
    }

    void U0(boolean z) {
        u0(z);
        this.v.E(z);
    }

    public final boolean V() {
        j jVar = this.t;
        if (jVar == null) {
            return false;
        }
        return jVar.u0();
    }

    boolean V0(Menu menu) {
        boolean z = false;
        if (this.A) {
            return false;
        }
        if (this.E && this.F) {
            z = true;
            v0(menu);
        }
        return z | this.v.F(menu);
    }

    void W() {
        this.v.C0();
    }

    void W0() {
        boolean zS0 = this.t.s0(this);
        Boolean bool = this.l;
        if (bool == null || bool.booleanValue() != zS0) {
            this.l = Boolean.valueOf(zS0);
            w0(zS0);
            this.v.G();
        }
    }

    public void X(Bundle bundle) {
        this.G = true;
    }

    void X0() {
        this.v.C0();
        this.v.Q(true);
        this.f1362c = 4;
        this.G = false;
        y0();
        if (!this.G) {
            throw new t("Fragment " + this + " did not call through to super.onResume()");
        }
        androidx.lifecycle.h hVar = this.T;
        d.a aVar = d.a.ON_RESUME;
        hVar.i(aVar);
        if (this.I != null) {
            this.U.b(aVar);
        }
        this.v.H();
    }

    public void Y(int i2, int i3, Intent intent) {
    }

    void Y0(Bundle bundle) {
        z0(bundle);
        this.W.d(bundle);
        Parcelable parcelableR0 = this.v.R0();
        if (parcelableR0 != null) {
            bundle.putParcelable("android:support:fragments", parcelableR0);
        }
    }

    @Deprecated
    public void Z(Activity activity) {
        this.G = true;
    }

    void Z0() {
        this.v.C0();
        this.v.Q(true);
        this.f1362c = 3;
        this.G = false;
        A0();
        if (!this.G) {
            throw new t("Fragment " + this + " did not call through to super.onStart()");
        }
        androidx.lifecycle.h hVar = this.T;
        d.a aVar = d.a.ON_START;
        hVar.i(aVar);
        if (this.I != null) {
            this.U.b(aVar);
        }
        this.v.I();
    }

    @Override // androidx.lifecycle.g
    public androidx.lifecycle.d a() {
        return this.T;
    }

    public void a0(Context context) {
        this.G = true;
        g<?> gVar = this.u;
        Activity activityG = gVar == null ? null : gVar.g();
        if (activityG != null) {
            this.G = false;
            Z(activityG);
        }
    }

    void a1() {
        this.v.K();
        if (this.I != null) {
            this.U.b(d.a.ON_STOP);
        }
        this.T.i(d.a.ON_STOP);
        this.f1362c = 2;
        this.G = false;
        B0();
        if (this.G) {
            return;
        }
        throw new t("Fragment " + this + " did not call through to super.onStop()");
    }

    void b() {
        d dVar = this.L;
        f fVar = null;
        if (dVar != null) {
            dVar.p = false;
            f fVar2 = dVar.q;
            dVar.q = null;
            fVar = fVar2;
        }
        if (fVar != null) {
            fVar.a();
        }
    }

    public void b0(Fragment fragment) {
    }

    public final FragmentActivity b1() {
        FragmentActivity fragmentActivityH = h();
        if (fragmentActivityH != null) {
            return fragmentActivityH;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to an activity.");
    }

    public void c(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.x));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.y));
        printWriter.print(" mTag=");
        printWriter.println(this.z);
        printWriter.print(str);
        printWriter.print("mState=");
        printWriter.print(this.f1362c);
        printWriter.print(" mWho=");
        printWriter.print(this.f1366g);
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
        printWriter.println(this.K);
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
        if (this.f1367h != null) {
            printWriter.print(str);
            printWriter.print("mArguments=");
            printWriter.println(this.f1367h);
        }
        if (this.f1363d != null) {
            printWriter.print(str);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.f1363d);
        }
        if (this.f1364e != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.f1364e);
        }
        Fragment fragmentK = K();
        if (fragmentK != null) {
            printWriter.print(str);
            printWriter.print("mTarget=");
            printWriter.print(fragmentK);
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
        if (l() != null) {
            printWriter.print(str);
            printWriter.print("mAnimatingAway=");
            printWriter.println(l());
            printWriter.print(str);
            printWriter.print("mStateAfterAnimating=");
            printWriter.println(H());
        }
        if (p() != null) {
            b.m.a.a.b(this).a(str, fileDescriptor, printWriter, strArr);
        }
        printWriter.print(str);
        printWriter.println("Child " + this.v + ":");
        this.v.M(str + "  ", fileDescriptor, printWriter, strArr);
    }

    public boolean c0(MenuItem menuItem) {
        return false;
    }

    public final Context c1() {
        Context contextP = p();
        if (contextP != null) {
            return contextP;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to a context.");
    }

    public void d0(Bundle bundle) {
        this.G = true;
        e1(bundle);
        if (this.v.t0(1)) {
            return;
        }
        this.v.u();
    }

    public final View d1() {
        View viewL = L();
        if (viewL != null) {
            return viewL;
        }
        throw new IllegalStateException("Fragment " + this + " did not return a View from onCreateView() or this was called before onCreateView().");
    }

    @Override // androidx.savedstate.b
    public final SavedStateRegistry e() {
        return this.W.b();
    }

    public Animation e0(int i2, boolean z, int i3) {
        return null;
    }

    void e1(Bundle bundle) {
        Parcelable parcelable;
        if (bundle == null || (parcelable = bundle.getParcelable("android:support:fragments")) == null) {
            return;
        }
        this.v.P0(parcelable);
        this.v.u();
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public Animator f0(int i2, boolean z, int i3) {
        return null;
    }

    final void f1(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = this.f1364e;
        if (sparseArray != null) {
            this.I.restoreHierarchyState(sparseArray);
            this.f1364e = null;
        }
        this.G = false;
        D0(bundle);
        if (this.G) {
            if (this.I != null) {
                this.U.b(d.a.ON_CREATE);
            }
        } else {
            throw new t("Fragment " + this + " did not call through to super.onViewStateRestored()");
        }
    }

    Fragment g(String str) {
        return str.equals(this.f1366g) ? this : this.v.Z(str);
    }

    public void g0(Menu menu, MenuInflater menuInflater) {
    }

    void g1(View view) {
        f().f1374a = view;
    }

    public final FragmentActivity h() {
        g<?> gVar = this.u;
        if (gVar == null) {
            return null;
        }
        return (FragmentActivity) gVar.g();
    }

    public View h0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i2 = this.X;
        if (i2 != 0) {
            return layoutInflater.inflate(i2, viewGroup, false);
        }
        return null;
    }

    void h1(Animator animator) {
        f().f1375b = animator;
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public boolean i() {
        Boolean bool;
        d dVar = this.L;
        if (dVar == null || (bool = dVar.m) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public void i0() {
        this.G = true;
    }

    public void i1(Bundle bundle) {
        if (this.t != null && V()) {
            throw new IllegalStateException("Fragment already added and state has been saved");
        }
        this.f1367h = bundle;
    }

    @Override // androidx.lifecycle.s
    public androidx.lifecycle.r j() {
        j jVar = this.t;
        if (jVar != null) {
            return jVar.m0(this);
        }
        throw new IllegalStateException("Can't access ViewModels from detached fragment");
    }

    public void j0() {
    }

    void j1(boolean z) {
        f().r = z;
    }

    public boolean k() {
        Boolean bool;
        d dVar = this.L;
        if (dVar == null || (bool = dVar.l) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public void k0() {
        this.G = true;
    }

    void k1(int i2) {
        if (this.L == null && i2 == 0) {
            return;
        }
        f().f1377d = i2;
    }

    View l() {
        d dVar = this.L;
        if (dVar == null) {
            return null;
        }
        return dVar.f1374a;
    }

    public void l0() {
        this.G = true;
    }

    void l1(int i2) {
        if (this.L == null && i2 == 0) {
            return;
        }
        f();
        this.L.f1378e = i2;
    }

    Animator m() {
        d dVar = this.L;
        if (dVar == null) {
            return null;
        }
        return dVar.f1375b;
    }

    public LayoutInflater m0(Bundle bundle) {
        return w(bundle);
    }

    void m1(f fVar) {
        f();
        d dVar = this.L;
        f fVar2 = dVar.q;
        if (fVar == fVar2) {
            return;
        }
        if (fVar != null && fVar2 != null) {
            throw new IllegalStateException("Trying to set a replacement startPostponedEnterTransition on " + this);
        }
        if (dVar.p) {
            dVar.q = fVar;
        }
        if (fVar != null) {
            fVar.b();
        }
    }

    public final Bundle n() {
        return this.f1367h;
    }

    public void n0(boolean z) {
    }

    void n1(int i2) {
        f().f1376c = i2;
    }

    public final j o() {
        if (this.u != null) {
            return this.v;
        }
        throw new IllegalStateException("Fragment " + this + " has not been attached yet.");
    }

    @Deprecated
    public void o0(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        this.G = true;
    }

    public void o1() {
        j jVar = this.t;
        if (jVar == null || jVar.p == null) {
            f().p = false;
        } else if (Looper.myLooper() != this.t.p.i().getLooper()) {
            this.t.p.i().postAtFrontOfQueue(new b());
        } else {
            b();
        }
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        this.G = true;
    }

    @Override // android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        b1().onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        this.G = true;
    }

    public Context p() {
        g<?> gVar = this.u;
        if (gVar == null) {
            return null;
        }
        return gVar.h();
    }

    public void p0(Context context, AttributeSet attributeSet, Bundle bundle) {
        this.G = true;
        g<?> gVar = this.u;
        Activity activityG = gVar == null ? null : gVar.g();
        if (activityG != null) {
            this.G = false;
            o0(activityG, attributeSet, bundle);
        }
    }

    public Object q() {
        d dVar = this.L;
        if (dVar == null) {
            return null;
        }
        return dVar.f1379f;
    }

    public void q0(boolean z) {
    }

    androidx.core.app.f r() {
        d dVar = this.L;
        if (dVar == null) {
            return null;
        }
        return dVar.n;
    }

    public boolean r0(MenuItem menuItem) {
        return false;
    }

    public Object s() {
        d dVar = this.L;
        if (dVar == null) {
            return null;
        }
        return dVar.f1381h;
    }

    public void s0(Menu menu) {
    }

    androidx.core.app.f t() {
        d dVar = this.L;
        if (dVar == null) {
            return null;
        }
        return dVar.o;
    }

    public void t0() {
        this.G = true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("}");
        sb.append(" (");
        sb.append(this.f1366g);
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

    public final Object u() {
        g<?> gVar = this.u;
        if (gVar == null) {
            return null;
        }
        return gVar.l();
    }

    public void u0(boolean z) {
    }

    public final int v() {
        return this.x;
    }

    public void v0(Menu menu) {
    }

    @Deprecated
    public LayoutInflater w(Bundle bundle) {
        g<?> gVar = this.u;
        if (gVar == null) {
            throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
        }
        LayoutInflater layoutInflaterM = gVar.m();
        b.g.k.f.b(layoutInflaterM, this.v.h0());
        return layoutInflaterM;
    }

    public void w0(boolean z) {
    }

    int x() {
        d dVar = this.L;
        if (dVar == null) {
            return 0;
        }
        return dVar.f1377d;
    }

    public void x0(int i2, String[] strArr, int[] iArr) {
    }

    int y() {
        d dVar = this.L;
        if (dVar == null) {
            return 0;
        }
        return dVar.f1378e;
    }

    public void y0() {
        this.G = true;
    }

    public final Fragment z() {
        return this.w;
    }

    public void z0(Bundle bundle) {
    }

    public Fragment(int i2) {
        this();
        this.X = i2;
    }
}
