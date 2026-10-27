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
import androidx.activity.result.ActivityResultRegistry;
import androidx.annotation.CallSuper;
import androidx.annotation.LayoutRes;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.UiThread;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.savedstate.SavedStateRegistry;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class Fragment implements ComponentCallbacks, View.OnCreateContextMenuListener, androidx.lifecycle.f, androidx.lifecycle.q, androidx.savedstate.b {
    static final Object Z = new Object();
    String A;
    boolean B;
    boolean C;
    boolean D;
    boolean E;
    boolean F;
    private boolean H;
    ViewGroup I;
    View J;
    boolean K;
    c M;
    boolean N;
    boolean O;
    float P;
    LayoutInflater Q;
    boolean R;
    androidx.lifecycle.g T;

    @Nullable
    v U;
    androidx.savedstate.a W;

    @LayoutRes
    private int X;
    Bundle d;
    SparseArray<Parcelable> e;
    Bundle f;

    @Nullable
    Boolean g;
    Bundle i;
    Fragment j;
    int l;
    boolean n;
    boolean o;
    boolean p;
    boolean q;
    boolean r;
    boolean s;
    int t;
    FragmentManager u;
    i<?> v;
    Fragment x;
    int y;
    int z;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f612c = -1;

    @NonNull
    String h = UUID.randomUUID().toString();
    String k = null;
    private Boolean m = null;

    @NonNull
    FragmentManager w = new l();
    boolean G = true;
    boolean L = true;
    Lifecycle.State S = Lifecycle.State.RESUMED;
    androidx.lifecycle.k<androidx.lifecycle.f> V = new androidx.lifecycle.k<>();
    private final AtomicInteger Y = new AtomicInteger();

    /* JADX INFO: renamed from: androidx.fragment.app.Fragment$7, reason: invalid class name */
    /* JADX INFO: compiled from: caradapter-bug */
    class AnonymousClass7 implements androidx.lifecycle.d {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ androidx.arch.core.c.a f614c;
        final /* synthetic */ AtomicReference d;
        final /* synthetic */ androidx.activity.result.d.a e;
        final /* synthetic */ androidx.activity.result.a f;
        final /* synthetic */ Fragment g;

        @Override // androidx.lifecycle.d
        public void f(@NonNull androidx.lifecycle.f fVar, @NonNull Lifecycle.Event event) {
            if (Lifecycle.Event.ON_CREATE.equals(event)) {
                String strJ = this.g.j();
                this.d.set(((ActivityResultRegistry) this.f614c.apply(null)).g(strJ, this.g, this.e, this.f));
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class InstantiationException extends RuntimeException {
        public InstantiationException(@NonNull String str, @Nullable Exception exc) {
            super(str, exc);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @SuppressLint({"BanParcelableUsage, ParcelClassLoader"})
    public static class SavedState implements Parcelable {

        @NonNull
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final Bundle f615c;

        /* JADX INFO: compiled from: caradapter-bug */
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
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        SavedState(@NonNull Parcel parcel, @Nullable ClassLoader classLoader) {
            Bundle bundle = parcel.readBundle();
            this.f615c = bundle;
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
        public void writeToParcel(@NonNull Parcel parcel, int i) {
            parcel.writeBundle(this.f615c);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Fragment.this.d();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b extends e {
        b() {
        }

        @Override // androidx.fragment.app.e
        @Nullable
        public View g(int i) {
            View view = Fragment.this.J;
            if (view != null) {
                return view.findViewById(i);
            }
            throw new IllegalStateException("Fragment " + Fragment.this + " does not have a view");
        }

        @Override // androidx.fragment.app.e
        public boolean i() {
            return Fragment.this.J != null;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        View f618a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Animator f619b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f620c;
        int d;
        ArrayList<String> e;
        ArrayList<String> f;
        Object g = null;
        Object h;
        Object i;
        Object j;
        Object k;
        Object l;
        Boolean m;
        Boolean n;
        androidx.core.app.g o;
        androidx.core.app.g p;
        boolean q;
        d r;
        boolean s;

        c() {
            Object obj = Fragment.Z;
            this.h = obj;
            this.i = null;
            this.j = obj;
            this.k = null;
            this.l = obj;
            this.o = null;
            this.p = null;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    interface d {
        void a();

        void b();
    }

    public Fragment() {
        M();
    }

    private void M() {
        this.T = new androidx.lifecycle.g(this);
        this.W = androidx.savedstate.a.a(this);
        if (Build.VERSION.SDK_INT >= 19) {
            this.T.a(new androidx.lifecycle.d() { // from class: androidx.fragment.app.Fragment.2
                @Override // androidx.lifecycle.d
                public void f(@NonNull androidx.lifecycle.f fVar, @NonNull Lifecycle.Event event) {
                    View view;
                    if (event != Lifecycle.Event.ON_STOP || (view = Fragment.this.J) == null) {
                        return;
                    }
                    view.cancelPendingInputEvents();
                }
            });
        }
    }

    @NonNull
    @Deprecated
    public static Fragment O(@NonNull Context context, @NonNull String str, @Nullable Bundle bundle) {
        try {
            Fragment fragmentNewInstance = h.d(context.getClassLoader(), str).getConstructor(new Class[0]).newInstance(new Object[0]);
            if (bundle != null) {
                bundle.setClassLoader(fragmentNewInstance.getClass().getClassLoader());
                fragmentNewInstance.k1(bundle);
            }
            return fragmentNewInstance;
        } catch (IllegalAccessException e) {
            throw new InstantiationException("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e);
        } catch (java.lang.InstantiationException e2) {
            throw new InstantiationException("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e2);
        } catch (NoSuchMethodException e3) {
            throw new InstantiationException("Unable to instantiate fragment " + str + ": could not find Fragment constructor", e3);
        } catch (InvocationTargetException e4) {
            throw new InstantiationException("Unable to instantiate fragment " + str + ": calling Fragment constructor caused an exception", e4);
        }
    }

    private c g() {
        if (this.M == null) {
            this.M = new c();
        }
        return this.M;
    }

    private void g1() {
        if (FragmentManager.D0(3)) {
            Log.d("FragmentManager", "moveto RESTORE_VIEW_STATE: " + this);
        }
        if (this.J != null) {
            h1(this.d);
        }
        this.d = null;
    }

    @Nullable
    public final Fragment A() {
        return this.x;
    }

    @CallSuper
    @MainThread
    public void A0() {
        this.H = true;
    }

    @NonNull
    public final FragmentManager B() {
        FragmentManager fragmentManager = this.u;
        if (fragmentManager != null) {
            return fragmentManager;
        }
        throw new IllegalStateException("Fragment " + this + " not associated with a fragment manager.");
    }

    @CallSuper
    @MainThread
    public void B0() {
        this.H = true;
    }

    @Nullable
    public Object C() {
        c cVar = this.M;
        if (cVar == null) {
            return null;
        }
        Object obj = cVar.j;
        return obj == Z ? u() : obj;
    }

    @MainThread
    public void C0(@NonNull View view, @Nullable Bundle bundle) {
    }

    @NonNull
    public final Resources D() {
        return d1().getResources();
    }

    @CallSuper
    @MainThread
    public void D0(@Nullable Bundle bundle) {
        this.H = true;
    }

    @Nullable
    public Object E() {
        c cVar = this.M;
        if (cVar == null) {
            return null;
        }
        Object obj = cVar.h;
        return obj == Z ? s() : obj;
    }

    void E0(Bundle bundle) {
        this.w.O0();
        this.f612c = 3;
        this.H = false;
        X(bundle);
        if (this.H) {
            g1();
            this.w.y();
        } else {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onActivityCreated()");
        }
    }

    @Nullable
    public Object F() {
        c cVar = this.M;
        if (cVar == null) {
            return null;
        }
        return cVar.k;
    }

    void F0() {
        this.w.j(this.v, e(), this);
        this.f612c = 0;
        this.H = false;
        a0(this.v.k());
        if (this.H) {
            this.u.I(this);
            this.w.z();
        } else {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onAttach()");
        }
    }

    @Nullable
    public Object G() {
        c cVar = this.M;
        if (cVar == null) {
            return null;
        }
        Object obj = cVar.l;
        return obj == Z ? F() : obj;
    }

    void G0(@NonNull Configuration configuration) {
        onConfigurationChanged(configuration);
        this.w.A(configuration);
    }

    @NonNull
    ArrayList<String> H() {
        ArrayList<String> arrayList;
        c cVar = this.M;
        return (cVar == null || (arrayList = cVar.e) == null) ? new ArrayList<>() : arrayList;
    }

    boolean H0(@NonNull MenuItem menuItem) {
        if (this.B) {
            return false;
        }
        if (c0(menuItem)) {
            return true;
        }
        return this.w.B(menuItem);
    }

    @NonNull
    ArrayList<String> I() {
        ArrayList<String> arrayList;
        c cVar = this.M;
        return (cVar == null || (arrayList = cVar.f) == null) ? new ArrayList<>() : arrayList;
    }

    void I0(Bundle bundle) {
        this.w.O0();
        this.f612c = 1;
        this.H = false;
        this.W.c(bundle);
        d0(bundle);
        this.R = true;
        if (this.H) {
            this.T.g(Lifecycle.Event.ON_CREATE);
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onCreate()");
    }

    @Nullable
    @Deprecated
    public final Fragment J() {
        String str;
        Fragment fragment = this.j;
        if (fragment != null) {
            return fragment;
        }
        FragmentManager fragmentManager = this.u;
        if (fragmentManager == null || (str = this.k) == null) {
            return null;
        }
        return fragmentManager.f0(str);
    }

    boolean J0(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        boolean z = false;
        if (this.B) {
            return false;
        }
        if (this.F && this.G) {
            z = true;
            g0(menu, menuInflater);
        }
        return z | this.w.D(menu, menuInflater);
    }

    @Nullable
    public View K() {
        return this.J;
    }

    void K0(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        this.w.O0();
        this.s = true;
        this.U = new v();
        View viewH0 = h0(layoutInflater, viewGroup, bundle);
        this.J = viewH0;
        if (viewH0 == null) {
            if (this.U.f()) {
                throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
            }
            this.U = null;
        } else {
            this.U.e();
            androidx.lifecycle.r.a(this.J, this.U);
            androidx.lifecycle.s.a(this.J, this);
            androidx.savedstate.c.a(this.J, this.U);
            this.V.l(this.U);
        }
    }

    @NonNull
    public LiveData<androidx.lifecycle.f> L() {
        return this.V;
    }

    void L0() {
        this.w.E();
        this.T.g(Lifecycle.Event.ON_DESTROY);
        this.f612c = 0;
        this.H = false;
        this.R = false;
        i0();
        if (this.H) {
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDestroy()");
    }

    void M0() {
        this.w.F();
        if (this.J != null) {
            this.U.d(Lifecycle.Event.ON_DESTROY);
        }
        this.f612c = 1;
        this.H = false;
        k0();
        if (this.H) {
            androidx.loader.a.a.b(this).c();
            this.s = false;
        } else {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDestroyView()");
        }
    }

    void N() {
        M();
        this.h = UUID.randomUUID().toString();
        this.n = false;
        this.o = false;
        this.p = false;
        this.q = false;
        this.r = false;
        this.t = 0;
        this.u = null;
        this.w = new l();
        this.v = null;
        this.y = 0;
        this.z = 0;
        this.A = null;
        this.B = false;
        this.C = false;
    }

    void N0() {
        this.f612c = -1;
        this.H = false;
        l0();
        this.Q = null;
        if (this.H) {
            if (this.w.C0()) {
                return;
            }
            this.w.E();
            this.w = new l();
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDetach()");
    }

    @NonNull
    LayoutInflater O0(@Nullable Bundle bundle) {
        LayoutInflater layoutInflaterM0 = m0(bundle);
        this.Q = layoutInflaterM0;
        return layoutInflaterM0;
    }

    boolean P() {
        c cVar = this.M;
        if (cVar == null) {
            return false;
        }
        return cVar.s;
    }

    void P0() {
        onLowMemory();
        this.w.G();
    }

    final boolean Q() {
        return this.t > 0;
    }

    void Q0(boolean z) {
        q0(z);
        this.w.H(z);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public final boolean R() {
        FragmentManager fragmentManager;
        return this.G && ((fragmentManager = this.u) == null || fragmentManager.F0(this.x));
    }

    boolean R0(@NonNull MenuItem menuItem) {
        if (this.B) {
            return false;
        }
        if (this.F && this.G && r0(menuItem)) {
            return true;
        }
        return this.w.J(menuItem);
    }

    boolean S() {
        c cVar = this.M;
        if (cVar == null) {
            return false;
        }
        return cVar.q;
    }

    void S0(@NonNull Menu menu) {
        if (this.B) {
            return;
        }
        if (this.F && this.G) {
            s0(menu);
        }
        this.w.K(menu);
    }

    public final boolean T() {
        return this.o;
    }

    void T0() {
        this.w.M();
        if (this.J != null) {
            this.U.d(Lifecycle.Event.ON_PAUSE);
        }
        this.T.g(Lifecycle.Event.ON_PAUSE);
        this.f612c = 6;
        this.H = false;
        t0();
        if (this.H) {
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onPause()");
    }

    final boolean U() {
        Fragment fragmentA = A();
        return fragmentA != null && (fragmentA.T() || fragmentA.U());
    }

    void U0(boolean z) {
        u0(z);
        this.w.N(z);
    }

    public final boolean V() {
        FragmentManager fragmentManager = this.u;
        if (fragmentManager == null) {
            return false;
        }
        return fragmentManager.I0();
    }

    boolean V0(@NonNull Menu menu) {
        boolean z = false;
        if (this.B) {
            return false;
        }
        if (this.F && this.G) {
            z = true;
            v0(menu);
        }
        return z | this.w.O(menu);
    }

    void W() {
        this.w.O0();
    }

    void W0() {
        boolean zG0 = this.u.G0(this);
        Boolean bool = this.m;
        if (bool == null || bool.booleanValue() != zG0) {
            this.m = Boolean.valueOf(zG0);
            w0(zG0);
            this.w.P();
        }
    }

    @CallSuper
    @MainThread
    @Deprecated
    public void X(@Nullable Bundle bundle) {
        this.H = true;
    }

    void X0() {
        this.w.O0();
        this.w.a0(true);
        this.f612c = 7;
        this.H = false;
        y0();
        if (this.H) {
            this.T.g(Lifecycle.Event.ON_RESUME);
            if (this.J != null) {
                this.U.d(Lifecycle.Event.ON_RESUME);
            }
            this.w.Q();
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onResume()");
    }

    @Deprecated
    public void Y(int i, int i2, @Nullable Intent intent) {
    }

    void Y0(Bundle bundle) {
        z0(bundle);
        this.W.d(bundle);
        Parcelable parcelableB1 = this.w.b1();
        if (parcelableB1 != null) {
            bundle.putParcelable("android:support:fragments", parcelableB1);
        }
    }

    @CallSuper
    @MainThread
    @Deprecated
    public void Z(@NonNull Activity activity) {
        this.H = true;
    }

    void Z0() {
        this.w.O0();
        this.w.a0(true);
        this.f612c = 5;
        this.H = false;
        A0();
        if (this.H) {
            this.T.g(Lifecycle.Event.ON_START);
            if (this.J != null) {
                this.U.d(Lifecycle.Event.ON_START);
            }
            this.w.R();
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onStart()");
    }

    @Override // androidx.lifecycle.f
    @NonNull
    public Lifecycle a() {
        return this.T;
    }

    @CallSuper
    @MainThread
    public void a0(@NonNull Context context) {
        this.H = true;
        i<?> iVar = this.v;
        Activity activityJ = iVar == null ? null : iVar.j();
        if (activityJ != null) {
            this.H = false;
            Z(activityJ);
        }
    }

    void a1() {
        this.w.T();
        if (this.J != null) {
            this.U.d(Lifecycle.Event.ON_STOP);
        }
        this.T.g(Lifecycle.Event.ON_STOP);
        this.f612c = 4;
        this.H = false;
        B0();
        if (this.H) {
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onStop()");
    }

    @MainThread
    @Deprecated
    public void b0(@NonNull Fragment fragment) {
    }

    void b1() {
        C0(this.J, this.d);
        this.w.U();
    }

    @Override // androidx.savedstate.b
    @NonNull
    public final SavedStateRegistry c() {
        return this.W.b();
    }

    @MainThread
    public boolean c0(@NonNull MenuItem menuItem) {
        return false;
    }

    @NonNull
    public final FragmentActivity c1() {
        FragmentActivity fragmentActivityK = k();
        if (fragmentActivityK != null) {
            return fragmentActivityK;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to an activity.");
    }

    void d() {
        ViewGroup viewGroup;
        FragmentManager fragmentManager;
        c cVar = this.M;
        d dVar = null;
        if (cVar != null) {
            cVar.q = false;
            d dVar2 = cVar.r;
            cVar.r = null;
            dVar = dVar2;
        }
        if (dVar != null) {
            dVar.a();
            return;
        }
        if (!FragmentManager.P || this.J == null || (viewGroup = this.I) == null || (fragmentManager = this.u) == null) {
            return;
        }
        SpecialEffectsController specialEffectsControllerL = SpecialEffectsController.l(viewGroup, fragmentManager);
        specialEffectsControllerL.n();
        specialEffectsControllerL.h();
    }

    @CallSuper
    @MainThread
    public void d0(@Nullable Bundle bundle) {
        this.H = true;
        f1(bundle);
        if (this.w.H0(1)) {
            return;
        }
        this.w.C();
    }

    @NonNull
    public final Context d1() {
        Context contextR = r();
        if (contextR != null) {
            return contextR;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to a context.");
    }

    @NonNull
    e e() {
        return new b();
    }

    @Nullable
    @MainThread
    public Animation e0(int i, boolean z, int i2) {
        return null;
    }

    @NonNull
    public final View e1() {
        View viewK = K();
        if (viewK != null) {
            return viewK;
        }
        throw new IllegalStateException("Fragment " + this + " did not return a View from onCreateView() or this was called before onCreateView().");
    }

    public final boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    public void f(@NonNull String str, @Nullable FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @Nullable String[] strArr) {
        printWriter.print(str);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.y));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.z));
        printWriter.print(" mTag=");
        printWriter.println(this.A);
        printWriter.print(str);
        printWriter.print("mState=");
        printWriter.print(this.f612c);
        printWriter.print(" mWho=");
        printWriter.print(this.h);
        printWriter.print(" mBackStackNesting=");
        printWriter.println(this.t);
        printWriter.print(str);
        printWriter.print("mAdded=");
        printWriter.print(this.n);
        printWriter.print(" mRemoving=");
        printWriter.print(this.o);
        printWriter.print(" mFromLayout=");
        printWriter.print(this.p);
        printWriter.print(" mInLayout=");
        printWriter.println(this.q);
        printWriter.print(str);
        printWriter.print("mHidden=");
        printWriter.print(this.B);
        printWriter.print(" mDetached=");
        printWriter.print(this.C);
        printWriter.print(" mMenuVisible=");
        printWriter.print(this.G);
        printWriter.print(" mHasMenu=");
        printWriter.println(this.F);
        printWriter.print(str);
        printWriter.print("mRetainInstance=");
        printWriter.print(this.D);
        printWriter.print(" mUserVisibleHint=");
        printWriter.println(this.L);
        if (this.u != null) {
            printWriter.print(str);
            printWriter.print("mFragmentManager=");
            printWriter.println(this.u);
        }
        if (this.v != null) {
            printWriter.print(str);
            printWriter.print("mHost=");
            printWriter.println(this.v);
        }
        if (this.x != null) {
            printWriter.print(str);
            printWriter.print("mParentFragment=");
            printWriter.println(this.x);
        }
        if (this.i != null) {
            printWriter.print(str);
            printWriter.print("mArguments=");
            printWriter.println(this.i);
        }
        if (this.d != null) {
            printWriter.print(str);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.d);
        }
        if (this.e != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.e);
        }
        if (this.f != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewRegistryState=");
            printWriter.println(this.f);
        }
        Fragment fragmentJ = J();
        if (fragmentJ != null) {
            printWriter.print(str);
            printWriter.print("mTarget=");
            printWriter.print(fragmentJ);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.l);
        }
        if (y() != 0) {
            printWriter.print(str);
            printWriter.print("mNextAnim=");
            printWriter.println(y());
        }
        if (this.I != null) {
            printWriter.print(str);
            printWriter.print("mContainer=");
            printWriter.println(this.I);
        }
        if (this.J != null) {
            printWriter.print(str);
            printWriter.print("mView=");
            printWriter.println(this.J);
        }
        if (n() != null) {
            printWriter.print(str);
            printWriter.print("mAnimatingAway=");
            printWriter.println(n());
        }
        if (r() != null) {
            androidx.loader.a.a.b(this).a(str, fileDescriptor, printWriter, strArr);
        }
        printWriter.print(str);
        printWriter.println("Child " + this.w + ":");
        this.w.W(str + "  ", fileDescriptor, printWriter, strArr);
    }

    @Nullable
    @MainThread
    public Animator f0(int i, boolean z, int i2) {
        return null;
    }

    void f1(@Nullable Bundle bundle) {
        Parcelable parcelable;
        if (bundle == null || (parcelable = bundle.getParcelable("android:support:fragments")) == null) {
            return;
        }
        this.w.Z0(parcelable);
        this.w.C();
    }

    @MainThread
    public void g0(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
    }

    @Override // androidx.lifecycle.q
    @NonNull
    public androidx.lifecycle.p h() {
        FragmentManager fragmentManager = this.u;
        if (fragmentManager != null) {
            return fragmentManager.z0(this);
        }
        throw new IllegalStateException("Can't access ViewModels from detached fragment");
    }

    @Nullable
    @MainThread
    public View h0(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        int i = this.X;
        if (i != 0) {
            return layoutInflater.inflate(i, viewGroup, false);
        }
        return null;
    }

    final void h1(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = this.e;
        if (sparseArray != null) {
            this.J.restoreHierarchyState(sparseArray);
            this.e = null;
        }
        if (this.J != null) {
            this.U.g(this.f);
            this.f = null;
        }
        this.H = false;
        D0(bundle);
        if (this.H) {
            if (this.J != null) {
                this.U.d(Lifecycle.Event.ON_CREATE);
            }
        } else {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onViewStateRestored()");
        }
    }

    public final int hashCode() {
        return super.hashCode();
    }

    @Nullable
    Fragment i(@NonNull String str) {
        return str.equals(this.h) ? this : this.w.i0(str);
    }

    @CallSuper
    @MainThread
    public void i0() {
        this.H = true;
    }

    void i1(View view) {
        g().f618a = view;
    }

    @NonNull
    String j() {
        return "fragment_" + this.h + "_rq#" + this.Y.getAndIncrement();
    }

    @MainThread
    public void j0() {
    }

    void j1(Animator animator) {
        g().f619b = animator;
    }

    @Nullable
    public final FragmentActivity k() {
        i<?> iVar = this.v;
        if (iVar == null) {
            return null;
        }
        return (FragmentActivity) iVar.j();
    }

    @CallSuper
    @MainThread
    public void k0() {
        this.H = true;
    }

    public void k1(@Nullable Bundle bundle) {
        if (this.u != null && V()) {
            throw new IllegalStateException("Fragment already added and state has been saved");
        }
        this.i = bundle;
    }

    public boolean l() {
        Boolean bool;
        c cVar = this.M;
        if (cVar == null || (bool = cVar.n) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    @CallSuper
    @MainThread
    public void l0() {
        this.H = true;
    }

    void l1(boolean z) {
        g().s = z;
    }

    public boolean m() {
        Boolean bool;
        c cVar = this.M;
        if (cVar == null || (bool = cVar.m) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    @NonNull
    public LayoutInflater m0(@Nullable Bundle bundle) {
        return x(bundle);
    }

    void m1(int i) {
        if (this.M == null && i == 0) {
            return;
        }
        g().f620c = i;
    }

    View n() {
        c cVar = this.M;
        if (cVar == null) {
            return null;
        }
        return cVar.f618a;
    }

    @MainThread
    public void n0(boolean z) {
    }

    void n1(int i) {
        if (this.M == null && i == 0) {
            return;
        }
        g();
        this.M.d = i;
    }

    Animator o() {
        c cVar = this.M;
        if (cVar == null) {
            return null;
        }
        return cVar.f619b;
    }

    @CallSuper
    @UiThread
    @Deprecated
    public void o0(@NonNull Activity activity, @NonNull AttributeSet attributeSet, @Nullable Bundle bundle) {
        this.H = true;
    }

    void o1(d dVar) {
        g();
        d dVar2 = this.M.r;
        if (dVar == dVar2) {
            return;
        }
        if (dVar != null && dVar2 != null) {
            throw new IllegalStateException("Trying to set a replacement startPostponedEnterTransition on " + this);
        }
        c cVar = this.M;
        if (cVar.q) {
            cVar.r = dVar;
        }
        if (dVar != null) {
            dVar.b();
        }
    }

    @Override // android.content.ComponentCallbacks
    @CallSuper
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        this.H = true;
    }

    @Override // android.view.View.OnCreateContextMenuListener
    @MainThread
    public void onCreateContextMenu(@NonNull ContextMenu contextMenu, @NonNull View view, @Nullable ContextMenu.ContextMenuInfo contextMenuInfo) {
        c1().onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    @Override // android.content.ComponentCallbacks
    @CallSuper
    @MainThread
    public void onLowMemory() {
        this.H = true;
    }

    @Nullable
    public final Bundle p() {
        return this.i;
    }

    @CallSuper
    @UiThread
    public void p0(@NonNull Context context, @NonNull AttributeSet attributeSet, @Nullable Bundle bundle) {
        this.H = true;
        i<?> iVar = this.v;
        Activity activityJ = iVar == null ? null : iVar.j();
        if (activityJ != null) {
            this.H = false;
            o0(activityJ, attributeSet, bundle);
        }
    }

    void p1(@Nullable ArrayList<String> arrayList, @Nullable ArrayList<String> arrayList2) {
        g();
        c cVar = this.M;
        cVar.e = arrayList;
        cVar.f = arrayList2;
    }

    @NonNull
    public final FragmentManager q() {
        if (this.v != null) {
            return this.w;
        }
        throw new IllegalStateException("Fragment " + this + " has not been attached yet.");
    }

    public void q0(boolean z) {
    }

    public void q1() {
        if (this.M == null || !g().q) {
            return;
        }
        if (this.v == null) {
            g().q = false;
        } else if (Looper.myLooper() != this.v.l().getLooper()) {
            this.v.l().postAtFrontOfQueue(new a());
        } else {
            d();
        }
    }

    @Nullable
    public Context r() {
        i<?> iVar = this.v;
        if (iVar == null) {
            return null;
        }
        return iVar.k();
    }

    @MainThread
    public boolean r0(@NonNull MenuItem menuItem) {
        return false;
    }

    @Nullable
    public Object s() {
        c cVar = this.M;
        if (cVar == null) {
            return null;
        }
        return cVar.g;
    }

    @MainThread
    public void s0(@NonNull Menu menu) {
    }

    androidx.core.app.g t() {
        c cVar = this.M;
        if (cVar == null) {
            return null;
        }
        return cVar.o;
    }

    @CallSuper
    @MainThread
    public void t0() {
        this.H = true;
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("}");
        sb.append(" (");
        sb.append(this.h);
        sb.append(")");
        if (this.y != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.y));
        }
        if (this.A != null) {
            sb.append(" ");
            sb.append(this.A);
        }
        sb.append('}');
        return sb.toString();
    }

    @Nullable
    public Object u() {
        c cVar = this.M;
        if (cVar == null) {
            return null;
        }
        return cVar.i;
    }

    public void u0(boolean z) {
    }

    androidx.core.app.g v() {
        c cVar = this.M;
        if (cVar == null) {
            return null;
        }
        return cVar.p;
    }

    @MainThread
    public void v0(@NonNull Menu menu) {
    }

    @Nullable
    public final Object w() {
        i<?> iVar = this.v;
        if (iVar == null) {
            return null;
        }
        return iVar.m();
    }

    @MainThread
    public void w0(boolean z) {
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public LayoutInflater x(@Nullable Bundle bundle) {
        i<?> iVar = this.v;
        if (iVar == null) {
            throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
        }
        LayoutInflater layoutInflaterN = iVar.n();
        androidx.core.view.f.b(layoutInflaterN, this.w.t0());
        return layoutInflaterN;
    }

    @Deprecated
    public void x0(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
    }

    int y() {
        c cVar = this.M;
        if (cVar == null) {
            return 0;
        }
        return cVar.f620c;
    }

    @CallSuper
    @MainThread
    public void y0() {
        this.H = true;
    }

    int z() {
        c cVar = this.M;
        if (cVar == null) {
            return 0;
        }
        return cVar.d;
    }

    @MainThread
    public void z0(@NonNull Bundle bundle) {
    }
}
