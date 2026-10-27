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
import androidx.savedstate.SavedStateRegistry;
import b.f.e.i;
import b.j.a.g;
import b.j.a.m;
import b.l.f;
import b.l.k;
import b.l.p;
import b.l.q;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class Fragment implements ComponentCallbacks, View.OnCreateContextMenuListener, f, q, b.o.b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f493c = new Object();
    public Bundle F3;
    public Fragment G3;
    public int I3;
    public boolean K3;
    public boolean L3;
    public boolean M3;
    public boolean N3;
    public boolean O3;
    public boolean P3;
    public int Q3;
    public g R3;
    public b.j.a.e S3;
    public Fragment U3;
    public int V3;
    public int W3;
    public String X3;
    public boolean Y3;
    public boolean Z3;
    public boolean a4;
    public boolean b4;
    public boolean c4;
    public boolean e4;
    public ViewGroup f4;
    public View g4;
    public View h4;
    public boolean i4;
    public d k4;
    public boolean m4;
    public boolean n4;
    public float o4;
    public LayoutInflater p4;
    public boolean q4;
    public Bundle s;
    public b.l.g s4;
    public m t4;
    public b.o.a v4;
    public int w4;
    public SparseArray<Parcelable> x;
    public Boolean y;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f494d = 0;
    public String E3 = UUID.randomUUID().toString();
    public String H3 = null;
    public Boolean J3 = null;
    public g T3 = new g();
    public boolean d4 = true;
    public boolean j4 = true;
    public Runnable l4 = new a();
    public Lifecycle.State r4 = Lifecycle.State.RESUMED;
    public k<f> u4 = new k<>();

    public static class InstantiationException extends RuntimeException {
        public InstantiationException(String str, Exception exc) {
            super(str, exc);
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Bundle f496c;

        public static class a implements Parcelable.ClassLoaderCreator<SavedState> {
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
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            Bundle bundle = parcel.readBundle();
            this.f496c = bundle;
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
            parcel.writeBundle(this.f496c);
        }
    }

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Fragment.this.j1();
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Fragment.this.d();
        }
    }

    public class c extends b.j.a.b {
        public c() {
        }

        @Override // b.j.a.b
        public View e(int i2) {
            View view = Fragment.this.g4;
            if (view != null) {
                return view.findViewById(i2);
            }
            throw new IllegalStateException("Fragment " + this + " does not have a view");
        }

        @Override // b.j.a.b
        public boolean f() {
            return Fragment.this.g4 != null;
        }
    }

    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public View f500a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Animator f501b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f502c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f503d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f504e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f505f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public Object f506g = null;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public Object f507h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public Object f508i;
        public Object j;
        public Object k;
        public Object l;
        public Boolean m;
        public Boolean n;
        public i o;
        public i p;
        public boolean q;
        public e r;
        public boolean s;

        public d() {
            Object obj = Fragment.f493c;
            this.f507h = obj;
            this.f508i = null;
            this.j = obj;
            this.k = null;
            this.l = obj;
        }
    }

    public interface e {
        void a();

        void b();
    }

    public Fragment() {
        J();
    }

    @Deprecated
    public static Fragment L(Context context, String str, Bundle bundle) {
        try {
            Fragment fragmentNewInstance = b.j.a.d.d(context.getClassLoader(), str).getConstructor(new Class[0]).newInstance(new Object[0]);
            if (bundle != null) {
                bundle.setClassLoader(fragmentNewInstance.getClass().getClassLoader());
                fragmentNewInstance.d1(bundle);
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

    public Object A() {
        d dVar = this.k4;
        if (dVar == null) {
            return null;
        }
        Object obj = dVar.j;
        return obj == f493c ? r() : obj;
    }

    public void A0() {
        this.T3.o(this.S3, new c(), this);
        this.e4 = false;
        V(this.S3.i());
        if (this.e4) {
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onAttach()");
    }

    public final Resources B() {
        return X0().getResources();
    }

    public void B0(Configuration configuration) {
        onConfigurationChanged(configuration);
        this.T3.y(configuration);
    }

    public final boolean C() {
        return this.a4;
    }

    public boolean C0(MenuItem menuItem) {
        if (this.Y3) {
            return false;
        }
        return X(menuItem) || this.T3.z(menuItem);
    }

    public Object D() {
        d dVar = this.k4;
        if (dVar == null) {
            return null;
        }
        Object obj = dVar.f507h;
        return obj == f493c ? p() : obj;
    }

    public void D0(Bundle bundle) {
        this.T3.R0();
        this.f494d = 1;
        this.e4 = false;
        this.v4.c(bundle);
        Y(bundle);
        this.q4 = true;
        if (this.e4) {
            this.s4.i(Lifecycle.Event.ON_CREATE);
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onCreate()");
    }

    public Object E() {
        d dVar = this.k4;
        if (dVar == null) {
            return null;
        }
        return dVar.k;
    }

    public boolean E0(Menu menu, MenuInflater menuInflater) {
        boolean z = false;
        if (this.Y3) {
            return false;
        }
        if (this.c4 && this.d4) {
            z = true;
            b0(menu, menuInflater);
        }
        return z | this.T3.B(menu, menuInflater);
    }

    public Object F() {
        d dVar = this.k4;
        if (dVar == null) {
            return null;
        }
        Object obj = dVar.l;
        return obj == f493c ? E() : obj;
    }

    public void F0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.T3.R0();
        this.P3 = true;
        this.t4 = new m();
        View viewC0 = c0(layoutInflater, viewGroup, bundle);
        this.g4 = viewC0;
        if (viewC0 != null) {
            this.t4.e();
            this.u4.h(this.t4);
        } else {
            if (this.t4.f()) {
                throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
            }
            this.t4 = null;
        }
    }

    public int G() {
        d dVar = this.k4;
        if (dVar == null) {
            return 0;
        }
        return dVar.f502c;
    }

    public void G0() {
        this.T3.C();
        this.s4.i(Lifecycle.Event.ON_DESTROY);
        this.f494d = 0;
        this.e4 = false;
        this.q4 = false;
        d0();
        if (this.e4) {
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDestroy()");
    }

    public final Fragment H() {
        String str;
        Fragment fragment = this.G3;
        if (fragment != null) {
            return fragment;
        }
        g gVar = this.R3;
        if (gVar == null || (str = this.H3) == null) {
            return null;
        }
        return gVar.I3.get(str);
    }

    public void H0() {
        this.T3.D();
        if (this.g4 != null) {
            this.t4.d(Lifecycle.Event.ON_DESTROY);
        }
        this.f494d = 1;
        this.e4 = false;
        f0();
        if (this.e4) {
            b.m.a.a.b(this).c();
            this.P3 = false;
        } else {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDestroyView()");
        }
    }

    public View I() {
        return this.g4;
    }

    public void I0() {
        this.e4 = false;
        g0();
        this.p4 = null;
        if (this.e4) {
            if (this.T3.C0()) {
                return;
            }
            this.T3.C();
            this.T3 = new g();
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDetach()");
    }

    public final void J() {
        this.s4 = new b.l.g(this);
        this.v4 = b.o.a.a(this);
        if (Build.VERSION.SDK_INT >= 19) {
            this.s4.a(new b.l.d() { // from class: androidx.fragment.app.Fragment.2
                @Override // b.l.d
                public void d(f fVar, Lifecycle.Event event) {
                    View view;
                    if (event != Lifecycle.Event.ON_STOP || (view = Fragment.this.g4) == null) {
                        return;
                    }
                    view.cancelPendingInputEvents();
                }
            });
        }
    }

    public LayoutInflater J0(Bundle bundle) {
        LayoutInflater layoutInflaterH0 = h0(bundle);
        this.p4 = layoutInflaterH0;
        return layoutInflaterH0;
    }

    public void K() {
        J();
        this.E3 = UUID.randomUUID().toString();
        this.K3 = false;
        this.L3 = false;
        this.M3 = false;
        this.N3 = false;
        this.O3 = false;
        this.Q3 = 0;
        this.R3 = null;
        this.T3 = new g();
        this.S3 = null;
        this.V3 = 0;
        this.W3 = 0;
        this.X3 = null;
        this.Y3 = false;
        this.Z3 = false;
    }

    public void K0() {
        onLowMemory();
        this.T3.E();
    }

    public void L0(boolean z) {
        l0(z);
        this.T3.F(z);
    }

    public final boolean M() {
        return this.Z3;
    }

    public boolean M0(MenuItem menuItem) {
        if (this.Y3) {
            return false;
        }
        return (this.c4 && this.d4 && m0(menuItem)) || this.T3.U(menuItem);
    }

    public boolean N() {
        d dVar = this.k4;
        if (dVar == null) {
            return false;
        }
        return dVar.s;
    }

    public void N0(Menu menu) {
        if (this.Y3) {
            return;
        }
        if (this.c4 && this.d4) {
            n0(menu);
        }
        this.T3.V(menu);
    }

    public final boolean O() {
        return this.Q3 > 0;
    }

    public void O0() {
        this.T3.X();
        if (this.g4 != null) {
            this.t4.d(Lifecycle.Event.ON_PAUSE);
        }
        this.s4.i(Lifecycle.Event.ON_PAUSE);
        this.f494d = 3;
        this.e4 = false;
        o0();
        if (this.e4) {
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onPause()");
    }

    public boolean P() {
        d dVar = this.k4;
        if (dVar == null) {
            return false;
        }
        return dVar.q;
    }

    public void P0(boolean z) {
        p0(z);
        this.T3.Y(z);
    }

    public final boolean Q() {
        g gVar = this.R3;
        if (gVar == null) {
            return false;
        }
        return gVar.G0();
    }

    public boolean Q0(Menu menu) {
        boolean z = false;
        if (this.Y3) {
            return false;
        }
        if (this.c4 && this.d4) {
            z = true;
            q0(menu);
        }
        return z | this.T3.Z(menu);
    }

    public void R() {
        this.T3.R0();
    }

    public void R0() {
        boolean zE0 = this.R3.E0(this);
        Boolean bool = this.J3;
        if (bool == null || bool.booleanValue() != zE0) {
            this.J3 = Boolean.valueOf(zE0);
            r0(zE0);
            this.T3.a0();
        }
    }

    public void S(Bundle bundle) {
        this.e4 = true;
    }

    public void S0() {
        this.T3.R0();
        this.T3.k0();
        this.f494d = 4;
        this.e4 = false;
        t0();
        if (!this.e4) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onResume()");
        }
        b.l.g gVar = this.s4;
        Lifecycle.Event event = Lifecycle.Event.ON_RESUME;
        gVar.i(event);
        if (this.g4 != null) {
            this.t4.d(event);
        }
        this.T3.b0();
        this.T3.k0();
    }

    public void T(int i2, int i3, Intent intent) {
    }

    public void T0(Bundle bundle) {
        u0(bundle);
        this.v4.d(bundle);
        Parcelable parcelableD1 = this.T3.d1();
        if (parcelableD1 != null) {
            bundle.putParcelable("android:support:fragments", parcelableD1);
        }
    }

    @Deprecated
    public void U(Activity activity) {
        this.e4 = true;
    }

    public void U0() {
        this.T3.R0();
        this.T3.k0();
        this.f494d = 3;
        this.e4 = false;
        v0();
        if (!this.e4) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onStart()");
        }
        b.l.g gVar = this.s4;
        Lifecycle.Event event = Lifecycle.Event.ON_START;
        gVar.i(event);
        if (this.g4 != null) {
            this.t4.d(event);
        }
        this.T3.c0();
    }

    public void V(Context context) {
        this.e4 = true;
        b.j.a.e eVar = this.S3;
        Activity activityG = eVar == null ? null : eVar.g();
        if (activityG != null) {
            this.e4 = false;
            U(activityG);
        }
    }

    public void V0() {
        this.T3.e0();
        if (this.g4 != null) {
            this.t4.d(Lifecycle.Event.ON_STOP);
        }
        this.s4.i(Lifecycle.Event.ON_STOP);
        this.f494d = 2;
        this.e4 = false;
        w0();
        if (this.e4) {
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onStop()");
    }

    public void W(Fragment fragment) {
    }

    public final FragmentActivity W0() {
        FragmentActivity fragmentActivityI = i();
        if (fragmentActivityI != null) {
            return fragmentActivityI;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to an activity.");
    }

    public boolean X(MenuItem menuItem) {
        return false;
    }

    public final Context X0() {
        Context contextO = o();
        if (contextO != null) {
            return contextO;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to a context.");
    }

    public void Y(Bundle bundle) {
        this.e4 = true;
        Z0(bundle);
        if (this.T3.F0(1)) {
            return;
        }
        this.T3.A();
    }

    public final View Y0() {
        View viewI = I();
        if (viewI != null) {
            return viewI;
        }
        throw new IllegalStateException("Fragment " + this + " did not return a View from onCreateView() or this was called before onCreateView().");
    }

    public Animation Z(int i2, boolean z, int i3) {
        return null;
    }

    public void Z0(Bundle bundle) {
        Parcelable parcelable;
        if (bundle == null || (parcelable = bundle.getParcelable("android:support:fragments")) == null) {
            return;
        }
        this.T3.b1(parcelable);
        this.T3.A();
    }

    @Override // b.l.f
    public Lifecycle a() {
        return this.s4;
    }

    public Animator a0(int i2, boolean z, int i3) {
        return null;
    }

    public final void a1(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = this.x;
        if (sparseArray != null) {
            this.h4.restoreHierarchyState(sparseArray);
            this.x = null;
        }
        this.e4 = false;
        y0(bundle);
        if (this.e4) {
            if (this.g4 != null) {
                this.t4.d(Lifecycle.Event.ON_CREATE);
            }
        } else {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onViewStateRestored()");
        }
    }

    public void b0(Menu menu, MenuInflater menuInflater) {
    }

    public void b1(View view) {
        f().f500a = view;
    }

    @Override // b.o.b
    public final SavedStateRegistry c() {
        return this.v4.b();
    }

    public View c0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i2 = this.w4;
        if (i2 != 0) {
            return layoutInflater.inflate(i2, viewGroup, false);
        }
        return null;
    }

    public void c1(Animator animator) {
        f().f501b = animator;
    }

    public void d() {
        d dVar = this.k4;
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

    public void d0() {
        this.e4 = true;
    }

    public void d1(Bundle bundle) {
        if (this.R3 != null && Q()) {
            throw new IllegalStateException("Fragment already added and state has been saved");
        }
        this.F3 = bundle;
    }

    public void e(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.V3));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.W3));
        printWriter.print(" mTag=");
        printWriter.println(this.X3);
        printWriter.print(str);
        printWriter.print("mState=");
        printWriter.print(this.f494d);
        printWriter.print(" mWho=");
        printWriter.print(this.E3);
        printWriter.print(" mBackStackNesting=");
        printWriter.println(this.Q3);
        printWriter.print(str);
        printWriter.print("mAdded=");
        printWriter.print(this.K3);
        printWriter.print(" mRemoving=");
        printWriter.print(this.L3);
        printWriter.print(" mFromLayout=");
        printWriter.print(this.M3);
        printWriter.print(" mInLayout=");
        printWriter.println(this.N3);
        printWriter.print(str);
        printWriter.print("mHidden=");
        printWriter.print(this.Y3);
        printWriter.print(" mDetached=");
        printWriter.print(this.Z3);
        printWriter.print(" mMenuVisible=");
        printWriter.print(this.d4);
        printWriter.print(" mHasMenu=");
        printWriter.println(this.c4);
        printWriter.print(str);
        printWriter.print("mRetainInstance=");
        printWriter.print(this.a4);
        printWriter.print(" mUserVisibleHint=");
        printWriter.println(this.j4);
        if (this.R3 != null) {
            printWriter.print(str);
            printWriter.print("mFragmentManager=");
            printWriter.println(this.R3);
        }
        if (this.S3 != null) {
            printWriter.print(str);
            printWriter.print("mHost=");
            printWriter.println(this.S3);
        }
        if (this.U3 != null) {
            printWriter.print(str);
            printWriter.print("mParentFragment=");
            printWriter.println(this.U3);
        }
        if (this.F3 != null) {
            printWriter.print(str);
            printWriter.print("mArguments=");
            printWriter.println(this.F3);
        }
        if (this.s != null) {
            printWriter.print(str);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.s);
        }
        if (this.x != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.x);
        }
        Fragment fragmentH = H();
        if (fragmentH != null) {
            printWriter.print(str);
            printWriter.print("mTarget=");
            printWriter.print(fragmentH);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.I3);
        }
        if (w() != 0) {
            printWriter.print(str);
            printWriter.print("mNextAnim=");
            printWriter.println(w());
        }
        if (this.f4 != null) {
            printWriter.print(str);
            printWriter.print("mContainer=");
            printWriter.println(this.f4);
        }
        if (this.g4 != null) {
            printWriter.print(str);
            printWriter.print("mView=");
            printWriter.println(this.g4);
        }
        if (this.h4 != null) {
            printWriter.print(str);
            printWriter.print("mInnerView=");
            printWriter.println(this.g4);
        }
        if (l() != null) {
            printWriter.print(str);
            printWriter.print("mAnimatingAway=");
            printWriter.println(l());
            printWriter.print(str);
            printWriter.print("mStateAfterAnimating=");
            printWriter.println(G());
        }
        if (o() != null) {
            b.m.a.a.b(this).a(str, fileDescriptor, printWriter, strArr);
        }
        printWriter.print(str);
        printWriter.println("Child " + this.T3 + ":");
        this.T3.b(str + "  ", fileDescriptor, printWriter, strArr);
    }

    public void e0() {
    }

    public void e1(boolean z) {
        f().s = z;
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final d f() {
        if (this.k4 == null) {
            this.k4 = new d();
        }
        return this.k4;
    }

    public void f0() {
        this.e4 = true;
    }

    public void f1(int i2) {
        if (this.k4 == null && i2 == 0) {
            return;
        }
        f().f503d = i2;
    }

    public Fragment g(String str) {
        return str.equals(this.E3) ? this : this.T3.p0(str);
    }

    public void g0() {
        this.e4 = true;
    }

    public void g1(int i2, int i3) {
        if (this.k4 == null && i2 == 0 && i3 == 0) {
            return;
        }
        f();
        d dVar = this.k4;
        dVar.f504e = i2;
        dVar.f505f = i3;
    }

    @Override // b.l.q
    public p h() {
        g gVar = this.R3;
        if (gVar != null) {
            return gVar.z0(this);
        }
        throw new IllegalStateException("Can't access ViewModels from detached fragment");
    }

    public LayoutInflater h0(Bundle bundle) {
        return v(bundle);
    }

    public void h1(e eVar) {
        f();
        d dVar = this.k4;
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

    public final int hashCode() {
        return super.hashCode();
    }

    public final FragmentActivity i() {
        b.j.a.e eVar = this.S3;
        if (eVar == null) {
            return null;
        }
        return (FragmentActivity) eVar.g();
    }

    public void i0(boolean z) {
    }

    public void i1(int i2) {
        f().f502c = i2;
    }

    public boolean j() {
        Boolean bool;
        d dVar = this.k4;
        if (dVar == null || (bool = dVar.n) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    @Deprecated
    public void j0(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        this.e4 = true;
    }

    public void j1() {
        g gVar = this.R3;
        if (gVar == null || gVar.S3 == null) {
            f().q = false;
        } else if (Looper.myLooper() != this.R3.S3.j().getLooper()) {
            this.R3.S3.j().postAtFrontOfQueue(new b());
        } else {
            d();
        }
    }

    public boolean k() {
        Boolean bool;
        d dVar = this.k4;
        if (dVar == null || (bool = dVar.m) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public void k0(Context context, AttributeSet attributeSet, Bundle bundle) {
        this.e4 = true;
        b.j.a.e eVar = this.S3;
        Activity activityG = eVar == null ? null : eVar.g();
        if (activityG != null) {
            this.e4 = false;
            j0(activityG, attributeSet, bundle);
        }
    }

    public View l() {
        d dVar = this.k4;
        if (dVar == null) {
            return null;
        }
        return dVar.f500a;
    }

    public void l0(boolean z) {
    }

    public Animator m() {
        d dVar = this.k4;
        if (dVar == null) {
            return null;
        }
        return dVar.f501b;
    }

    public boolean m0(MenuItem menuItem) {
        return false;
    }

    public final b.j.a.f n() {
        if (this.S3 != null) {
            return this.T3;
        }
        throw new IllegalStateException("Fragment " + this + " has not been attached yet.");
    }

    public void n0(Menu menu) {
    }

    public Context o() {
        b.j.a.e eVar = this.S3;
        if (eVar == null) {
            return null;
        }
        return eVar.i();
    }

    public void o0() {
        this.e4 = true;
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        this.e4 = true;
    }

    @Override // android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        W0().onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        this.e4 = true;
    }

    public Object p() {
        d dVar = this.k4;
        if (dVar == null) {
            return null;
        }
        return dVar.f506g;
    }

    public void p0(boolean z) {
    }

    public i q() {
        d dVar = this.k4;
        if (dVar == null) {
            return null;
        }
        return dVar.o;
    }

    public void q0(Menu menu) {
    }

    public Object r() {
        d dVar = this.k4;
        if (dVar == null) {
            return null;
        }
        return dVar.f508i;
    }

    public void r0(boolean z) {
    }

    public i s() {
        d dVar = this.k4;
        if (dVar == null) {
            return null;
        }
        return dVar.p;
    }

    public void s0(int i2, String[] strArr, int[] iArr) {
    }

    public final b.j.a.f t() {
        return this.R3;
    }

    public void t0() {
        this.e4 = true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        b.f.l.a.a(this, sb);
        sb.append(" (");
        sb.append(this.E3);
        sb.append(")");
        if (this.V3 != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.V3));
        }
        if (this.X3 != null) {
            sb.append(" ");
            sb.append(this.X3);
        }
        sb.append('}');
        return sb.toString();
    }

    public final Object u() {
        b.j.a.e eVar = this.S3;
        if (eVar == null) {
            return null;
        }
        return eVar.m();
    }

    public void u0(Bundle bundle) {
    }

    @Deprecated
    public LayoutInflater v(Bundle bundle) {
        b.j.a.e eVar = this.S3;
        if (eVar == null) {
            throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
        }
        LayoutInflater layoutInflaterN = eVar.n();
        b.f.m.f.b(layoutInflaterN, this.T3.x0());
        return layoutInflaterN;
    }

    public void v0() {
        this.e4 = true;
    }

    public int w() {
        d dVar = this.k4;
        if (dVar == null) {
            return 0;
        }
        return dVar.f503d;
    }

    public void w0() {
        this.e4 = true;
    }

    public int x() {
        d dVar = this.k4;
        if (dVar == null) {
            return 0;
        }
        return dVar.f504e;
    }

    public void x0(View view, Bundle bundle) {
    }

    public int y() {
        d dVar = this.k4;
        if (dVar == null) {
            return 0;
        }
        return dVar.f505f;
    }

    public void y0(Bundle bundle) {
        this.e4 = true;
    }

    public final Fragment z() {
        return this.U3;
    }

    public void z0(Bundle bundle) {
        this.T3.R0();
        this.f494d = 2;
        this.e4 = false;
        S(bundle);
        if (this.e4) {
            this.T3.x();
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onActivityCreated()");
    }
}
