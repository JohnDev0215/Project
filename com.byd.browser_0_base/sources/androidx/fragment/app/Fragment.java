package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.d;
import androidx.lifecycle.viewmodel.R$id;
import defpackage.C0175b;
import defpackage.an3;
import defpackage.b71;
import defpackage.bn3;
import defpackage.bp0;
import defpackage.bz;
import defpackage.eq0;
import defpackage.fe2;
import defpackage.g10;
import defpackage.ge2;
import defpackage.gq2;
import defpackage.hj2;
import defpackage.hp0;
import defpackage.i21;
import defpackage.lp0;
import defpackage.n3;
import defpackage.oj2;
import defpackage.t2;
import defpackage.tp0;
import defpackage.ts0;
import defpackage.vd1;
import defpackage.w2;
import defpackage.xj1;
import defpackage.z7;
import defpackage.zj1;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class Fragment implements ComponentCallbacks, View.OnCreateContextMenuListener, b71, bn3, androidx.lifecycle.c, ge2 {
    public static final Object Y = new Object();
    public int A;
    public String B;
    public boolean C;
    public boolean D;
    public boolean E;
    public boolean F;
    public boolean H;
    public ViewGroup I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public View f12J;
    public boolean K;
    public c M;
    public boolean N;
    public LayoutInflater O;
    public boolean P;
    public String Q;
    public androidx.lifecycle.g S;
    public eq0 T;
    public fe2 V;
    public final ArrayList<e> W;
    public final a X;
    public Bundle f;
    public SparseArray<Parcelable> g;
    public Bundle h;
    public Bundle j;
    public Fragment k;
    public int m;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public boolean s;
    public boolean t;
    public int u;
    public FragmentManager v;
    public hp0<?> w;
    public Fragment y;
    public int z;
    public int e = -1;
    public String i = UUID.randomUUID().toString();
    public String l = null;
    public Boolean n = null;
    public lp0 x = new lp0();
    public boolean G = true;
    public boolean L = true;
    public d.b R = d.b.RESUMED;
    public final zj1<b71> U = new zj1<>();

    public class a extends e {
        public a() {
        }

        @Override // androidx.fragment.app.Fragment.e
        public final void a() {
            Fragment fragment = Fragment.this;
            fragment.V.a();
            androidx.lifecycle.k.a(fragment);
            Bundle bundle = fragment.f;
            fragment.V.b(bundle != null ? bundle.getBundle("registryState") : null);
        }
    }

    public class b extends vd1 {
        public b() {
        }

        @Override // defpackage.vd1
        public final View r(int i) {
            Fragment fragment = Fragment.this;
            View view = fragment.f12J;
            if (view != null) {
                return view.findViewById(i);
            }
            throw new IllegalStateException(C0175b.i("Fragment ", fragment, " does not have a view"));
        }

        @Override // defpackage.vd1
        public final boolean x() {
            return Fragment.this.f12J != null;
        }
    }

    public static class c {
        public boolean a;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public ArrayList<String> g;
        public ArrayList<String> h;
        public final Object i;
        public final Object j;
        public final Object k;
        public float l;
        public View m;

        public c() {
            Object obj = Fragment.Y;
            this.i = obj;
            this.j = obj;
            this.k = obj;
            this.l = 1.0f;
            this.m = null;
        }
    }

    public static class d extends RuntimeException {
        public d(String str, Exception exc) {
            super(str, exc);
        }
    }

    public static abstract class e {
        public abstract void a();
    }

    public Fragment() {
        new AtomicInteger();
        this.W = new ArrayList<>();
        this.X = new a();
        R();
    }

    @Deprecated
    public static Fragment U(Context context, String str, Bundle bundle) {
        try {
            Fragment fragmentNewInstance = f.c(context.getClassLoader(), str).getConstructor(new Class[0]).newInstance(new Object[0]);
            if (bundle != null) {
                bundle.setClassLoader(fragmentNewInstance.getClass().getClassLoader());
                fragmentNewInstance.C0(bundle);
            }
            return fragmentNewInstance;
        } catch (IllegalAccessException e2) {
            throw new d(gq2.g("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e2);
        } catch (InstantiationException e3) {
            throw new d(gq2.g("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e3);
        } catch (NoSuchMethodException e4) {
            throw new d(gq2.g("Unable to instantiate fragment ", str, ": could not find Fragment constructor"), e4);
        } catch (InvocationTargetException e5) {
            throw new d(gq2.g("Unable to instantiate fragment ", str, ": calling Fragment constructor caused an exception"), e5);
        }
    }

    private void registerOnPreAttachListener(e eVar) {
        if (this.e >= 0) {
            eVar.a();
        } else {
            this.W.add(eVar);
        }
    }

    public final void A0(int i, int i2, int i3, int i4) {
        if (this.M == null && i == 0 && i2 == 0 && i3 == 0 && i4 == 0) {
            return;
        }
        H().b = i;
        H().c = i2;
        H().d = i3;
        H().e = i4;
    }

    public void C0(Bundle bundle) {
        FragmentManager fragmentManager = this.v;
        if (fragmentManager != null) {
            if (fragmentManager == null ? false : fragmentManager.L()) {
                throw new IllegalStateException("Fragment already added and state has been saved");
            }
        }
        this.j = bundle;
    }

    @Deprecated
    public final void D0() {
        if (!this.F) {
            this.F = true;
            if (!V() || W()) {
                return;
            }
            this.w.M();
        }
    }

    @Deprecated
    public final void E0(int i, Fragment fragment) {
        if (fragment != null) {
            tp0.b bVar = tp0.a;
            hj2 hj2Var = new hj2(this, fragment, i);
            tp0.b bVarA = tp0.a(this);
            if (bVarA.a.contains(tp0.a.DETECT_TARGET_FRAGMENT_USAGE) && tp0.d(bVarA, getClass(), hj2.class)) {
                tp0.b(bVarA, hj2Var);
            }
        }
        FragmentManager fragmentManager = this.v;
        FragmentManager fragmentManager2 = fragment != null ? fragment.v : null;
        if (fragmentManager != null && fragmentManager2 != null && fragmentManager != fragmentManager2) {
            throw new IllegalArgumentException(C0175b.i("Fragment ", fragment, " must share the same FragmentManager to be set as a target fragment"));
        }
        for (Fragment fragmentQ = fragment; fragmentQ != null; fragmentQ = fragmentQ.Q(false)) {
            if (fragmentQ.equals(this)) {
                throw new IllegalArgumentException("Setting " + fragment + " as the target of " + this + " would create a target cycle");
            }
        }
        if (fragment == null) {
            this.l = null;
            this.k = null;
        } else if (this.v == null || fragment.v == null) {
            this.l = null;
            this.k = fragment;
        } else {
            this.l = fragment.i;
            this.k = null;
        }
        this.m = i;
    }

    public final void F0(Intent intent) {
        hp0<?> hp0Var = this.w;
        if (hp0Var == null) {
            throw new IllegalStateException(C0175b.i("Fragment ", this, " not attached to Activity"));
        }
        Object obj = bz.a;
        hp0Var.l.startActivity(intent, null);
    }

    public vd1 G() {
        return new b();
    }

    @Deprecated
    public final void G0(Intent intent, int i) throws Exception {
        if (this.w == null) {
            throw new IllegalStateException(C0175b.i("Fragment ", this, " not attached to Activity"));
        }
        FragmentManager fragmentManagerM = M();
        if (fragmentManagerM.A == null) {
            hp0<?> hp0Var = fragmentManagerM.u;
            hp0Var.getClass();
            if (i != -1) {
                throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
            }
            Object obj = bz.a;
            hp0Var.l.startActivity(intent, null);
            return;
        }
        fragmentManagerM.D.addLast(new FragmentManager.LaunchedFragmentInfo(this.i, i));
        w2 w2Var = fragmentManagerM.A;
        w2Var.getClass();
        androidx.activity.result.a aVar = w2Var.m;
        HashMap map = aVar.b;
        String str = w2Var.k;
        Integer num = (Integer) map.get(str);
        t2 t2Var = w2Var.l;
        if (num != null) {
            aVar.d.add(str);
            try {
                aVar.b(num.intValue(), t2Var, intent);
                return;
            } catch (Exception e2) {
                aVar.d.remove(str);
                throw e2;
            }
        }
        throw new IllegalStateException("Attempting to launch an unregistered ActivityResultLauncher with contract " + t2Var + " and input " + intent + ". You must ensure the ActivityResultLauncher is registered before calling launch().");
    }

    public final c H() {
        if (this.M == null) {
            this.M = new c();
        }
        return this.M;
    }

    /* JADX INFO: renamed from: I, reason: merged with bridge method [inline-methods] */
    public final FragmentActivity D() {
        hp0<?> hp0Var = this.w;
        if (hp0Var == null) {
            return null;
        }
        return (FragmentActivity) hp0Var.k;
    }

    public final FragmentManager J() {
        if (this.w != null) {
            return this.x;
        }
        throw new IllegalStateException(C0175b.i("Fragment ", this, " has not been attached yet."));
    }

    public final Context K() {
        hp0<?> hp0Var = this.w;
        if (hp0Var == null) {
            return null;
        }
        return hp0Var.l;
    }

    public final int L() {
        d.b bVar = this.R;
        return (bVar == d.b.INITIALIZED || this.y == null) ? bVar.ordinal() : Math.min(bVar.ordinal(), this.y.L());
    }

    public final FragmentManager M() {
        FragmentManager fragmentManager = this.v;
        if (fragmentManager != null) {
            return fragmentManager;
        }
        throw new IllegalStateException(C0175b.i("Fragment ", this, " not associated with a fragment manager."));
    }

    public final Resources N() {
        return x0().getResources();
    }

    public final String O(int i) {
        return N().getString(i);
    }

    public final String P(int i, Object... objArr) {
        return N().getString(i, objArr);
    }

    public final Fragment Q(boolean z) {
        String str;
        if (z) {
            tp0.b bVar = tp0.a;
            ts0 ts0Var = new ts0(this);
            tp0.b bVarA = tp0.a(this);
            if (bVarA.a.contains(tp0.a.DETECT_TARGET_FRAGMENT_USAGE) && tp0.d(bVarA, getClass(), ts0.class)) {
                tp0.b(bVarA, ts0Var);
            }
        }
        Fragment fragment = this.k;
        if (fragment != null) {
            return fragment;
        }
        FragmentManager fragmentManager = this.v;
        if (fragmentManager == null || (str = this.l) == null) {
            return null;
        }
        return fragmentManager.z(str);
    }

    public final void R() {
        this.S = new androidx.lifecycle.g(this);
        this.V = new fe2(this);
        ArrayList<e> arrayList = this.W;
        a aVar = this.X;
        if (arrayList.contains(aVar)) {
            return;
        }
        registerOnPreAttachListener(aVar);
    }

    @Override // androidx.lifecycle.c
    public final g10 S() {
        Application application;
        Context applicationContext = x0().getApplicationContext();
        while (true) {
            if (!(applicationContext instanceof ContextWrapper)) {
                application = null;
                break;
            }
            if (applicationContext instanceof Application) {
                application = (Application) applicationContext;
                break;
            }
            applicationContext = ((ContextWrapper) applicationContext).getBaseContext();
        }
        xj1 xj1Var = new xj1();
        LinkedHashMap linkedHashMap = xj1Var.a;
        if (application != null) {
            linkedHashMap.put(oj2.q, application);
        }
        linkedHashMap.put(androidx.lifecycle.k.a, this);
        linkedHashMap.put(androidx.lifecycle.k.b, this);
        Bundle bundle = this.j;
        if (bundle != null) {
            linkedHashMap.put(androidx.lifecycle.k.c, bundle);
        }
        return xj1Var;
    }

    public final void T() {
        R();
        this.Q = this.i;
        this.i = UUID.randomUUID().toString();
        this.o = false;
        this.p = false;
        this.q = false;
        this.r = false;
        this.s = false;
        this.u = 0;
        this.v = null;
        this.x = new lp0();
        this.w = null;
        this.z = 0;
        this.A = 0;
        this.B = null;
        this.C = false;
        this.D = false;
    }

    public final boolean V() {
        return this.w != null && this.o;
    }

    public final boolean W() {
        if (!this.C) {
            FragmentManager fragmentManager = this.v;
            if (fragmentManager == null) {
                return false;
            }
            Fragment fragment = this.y;
            fragmentManager.getClass();
            if (!(fragment == null ? false : fragment.W())) {
                return false;
            }
        }
        return true;
    }

    public final boolean X() {
        return this.u > 0;
    }

    @Deprecated
    public void Y() {
        this.H = true;
    }

    public void a0(Context context) {
        this.H = true;
        hp0<?> hp0Var = this.w;
        if ((hp0Var == null ? null : hp0Var.k) != null) {
            this.H = true;
        }
    }

    public void c0(Bundle bundle) {
        this.H = true;
        z0();
        lp0 lp0Var = this.x;
        if (lp0Var.t >= 1) {
            return;
        }
        lp0Var.F = false;
        lp0Var.G = false;
        lp0Var.M.h = false;
        lp0Var.r(1);
    }

    public View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return null;
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void f0() {
        this.H = true;
    }

    public void g0() {
        this.H = true;
    }

    @Override // defpackage.bn3
    public final an3 h0() {
        if (this.v == null) {
            throw new IllegalStateException("Can't access ViewModels from detached fragment");
        }
        if (L() == 1) {
            throw new IllegalStateException("Calling getViewModelStore() before a Fragment reaches onCreate() when using setMaxLifecycle(INITIALIZED) is not supported");
        }
        HashMap<String, an3> map = this.v.M.e;
        an3 an3Var = map.get(this.i);
        if (an3Var != null) {
            return an3Var;
        }
        an3 an3Var2 = new an3();
        map.put(this.i, an3Var2);
        return an3Var2;
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public void i0() {
        this.H = true;
    }

    public LayoutInflater j0(Bundle bundle) {
        hp0<?> hp0Var = this.w;
        if (hp0Var == null) {
            throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
        }
        LayoutInflater layoutInflaterL = hp0Var.L();
        layoutInflaterL.setFactory2(this.x.f);
        return layoutInflaterL;
    }

    @Override // defpackage.b71
    public final androidx.lifecycle.g j1() {
        return this.S;
    }

    @Deprecated
    public boolean k0(MenuItem menuItem) {
        return false;
    }

    public void l0() {
        this.H = true;
    }

    public void m0() {
        this.H = true;
    }

    public void o0() {
        this.H = true;
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        this.H = true;
    }

    @Override // android.view.View.OnCreateContextMenuListener
    public final void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        v0().onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
        this.H = true;
    }

    public void p0() {
        this.H = true;
    }

    public void r0(Bundle bundle) {
        this.H = true;
    }

    @Override // defpackage.ge2
    public final androidx.savedstate.a s0() {
        return this.V.b;
    }

    public void t0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.x.N();
        this.t = true;
        this.T = new eq0(this, h0(), new bp0(0, this));
        View viewE0 = e0(layoutInflater, viewGroup, bundle);
        this.f12J = viewE0;
        if (viewE0 == null) {
            if (this.T.h != null) {
                throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
            }
            this.T = null;
            return;
        }
        this.T.b();
        z7.f0(this.f12J, this.T);
        View view = this.f12J;
        eq0 eq0Var = this.T;
        i21.e(view, "<this>");
        view.setTag(R$id.view_tree_view_model_store_owner, eq0Var);
        n3.g1(this.f12J, this.T);
        this.U.i(this.T);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} (");
        sb.append(this.i);
        if (this.z != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.z));
        }
        if (this.B != null) {
            sb.append(" tag=");
            sb.append(this.B);
        }
        sb.append(")");
        return sb.toString();
    }

    public final LayoutInflater u0(Bundle bundle) {
        LayoutInflater layoutInflaterJ0 = j0(bundle);
        this.O = layoutInflaterJ0;
        return layoutInflaterJ0;
    }

    public final FragmentActivity v0() {
        FragmentActivity fragmentActivityD = D();
        if (fragmentActivityD != null) {
            return fragmentActivityD;
        }
        throw new IllegalStateException(C0175b.i("Fragment ", this, " not attached to an activity."));
    }

    public final Bundle w0() {
        Bundle bundle = this.j;
        if (bundle != null) {
            return bundle;
        }
        throw new IllegalStateException(C0175b.i("Fragment ", this, " does not have any arguments."));
    }

    public final Context x0() {
        Context contextK = K();
        if (contextK != null) {
            return contextK;
        }
        throw new IllegalStateException(C0175b.i("Fragment ", this, " not attached to a context."));
    }

    public final View y0() {
        View view = this.f12J;
        if (view != null) {
            return view;
        }
        throw new IllegalStateException(C0175b.i("Fragment ", this, " did not return a View from onCreateView() or this was called before onCreateView()."));
    }

    public final void z0() {
        Bundle bundle;
        Bundle bundle2 = this.f;
        if (bundle2 == null || (bundle = bundle2.getBundle("childFragmentManager")) == null) {
            return;
        }
        this.x.T(bundle);
        lp0 lp0Var = this.x;
        lp0Var.F = false;
        lp0Var.G = false;
        lp0Var.M.h = false;
        lp0Var.r(1);
    }

    @SuppressLint({"BanParcelableUsage, ParcelClassLoader"})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public final Bundle e;

        public class a implements Parcelable.ClassLoaderCreator<SavedState> {
            @Override // android.os.Parcelable.Creator
            public final Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            public final Object[] newArray(int i) {
                return new SavedState[i];
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            public final SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }
        }

        public SavedState(Bundle bundle) {
            this.e = bundle;
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeBundle(this.e);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            Bundle bundle = parcel.readBundle();
            this.e = bundle;
            if (classLoader == null || bundle == null) {
                return;
            }
            bundle.setClassLoader(classLoader);
        }
    }

    @Deprecated
    public void b0(Fragment fragment) {
    }

    public void n0(Bundle bundle) {
    }

    @Deprecated
    public void d0(Menu menu, MenuInflater menuInflater) {
    }

    public void q0(View view, Bundle bundle) {
    }

    @Deprecated
    public void Z(int i, int i2, Intent intent) {
    }
}
