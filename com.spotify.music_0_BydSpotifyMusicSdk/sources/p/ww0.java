package p;

import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Lifecycle$State;
import com.spotify.music.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public abstract class ww0 implements ComponentCallbacks, View.OnCreateContextMenuListener, s91, mu2, n11, x62 {
    public static final Object a0 = new Object();
    public yw0 A;
    public ww0 C;
    public int D;
    public int E;
    public String F;
    public boolean G;
    public boolean H;
    public boolean I;
    public boolean K;
    public ViewGroup L;
    public View M;
    public boolean N;
    public uw0 P;
    public boolean Q;
    public boolean R;
    public String S;
    public androidx.lifecycle.a U;
    public hy0 V;
    public w62 X;
    public final ArrayList Y;
    public final sw0 Z;
    public Bundle e;
    public SparseArray k;
    public Bundle l;
    public Bundle n;
    public ww0 o;
    public int q;
    public boolean s;
    public boolean t;
    public boolean u;
    public boolean v;
    public boolean w;
    public boolean x;
    public int y;
    public ox0 z;
    public int c = -1;
    public String m = UUID.randomUUID().toString();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f205p = null;
    public Boolean r = null;
    public ox0 B = new ox0();
    public final boolean J = true;
    public boolean O = true;
    public Lifecycle$State T = Lifecycle$State.m;
    public final androidx.lifecycle.b W = new androidx.lifecycle.b();

    public ww0() {
        new AtomicInteger();
        this.Y = new ArrayList();
        this.Z = new sw0(this);
        i();
    }

    public void A(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.B.K();
        this.x = true;
        this.V = new hy0(this, j(), new nv(6, this));
        View viewS = s(layoutInflater, viewGroup);
        this.M = viewS;
        if (viewS == null) {
            if (this.V.l != null) {
                throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
            }
            this.V = null;
            return;
        }
        this.V.c();
        if (Log.isLoggable("FragmentManager", 3)) {
            Objects.toString(this.M);
            toString();
        }
        l13.l(this.M, this.V);
        View view = this.M;
        hy0 hy0Var = this.V;
        v03.j(view, "<this>");
        view.setTag(R.id.view_tree_view_model_store_owner, hy0Var);
        l13.m(this.M, this.V);
        this.W.e(this.V);
    }

    public final Context B() {
        Context contextE = e();
        if (contextE != null) {
            return contextE;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to a context.");
    }

    public final View C() {
        View view = this.M;
        if (view != null) {
            return view;
        }
        throw new IllegalStateException("Fragment " + this + " did not return a View from onCreateView() or this was called before onCreateView().");
    }

    public final void D(int i, int i2, int i3, int i4) {
        if (this.P == null && i == 0 && i2 == 0 && i3 == 0 && i4 == 0) {
            return;
        }
        c().b = i;
        c().c = i2;
        c().d = i3;
        c().e = i4;
    }

    public final void E(Bundle bundle) {
        ox0 ox0Var = this.z;
        if (ox0Var != null && (ox0Var.E || ox0Var.F)) {
            throw new IllegalStateException("Fragment already added and state has been saved");
        }
        this.n = bundle;
    }

    @Override // p.x62
    public final v62 a() {
        return this.X.b;
    }

    public bk0 b() {
        return new tw0(this);
    }

    public final uw0 c() {
        if (this.P == null) {
            uw0 uw0Var = new uw0();
            Object obj = a0;
            uw0Var.i = obj;
            uw0Var.j = obj;
            uw0Var.k = obj;
            uw0Var.l = 1.0f;
            uw0Var.m = null;
            this.P = uw0Var;
        }
        return this.P;
    }

    public final ox0 d() {
        if (this.A != null) {
            return this.B;
        }
        throw new IllegalStateException("Fragment " + this + " has not been attached yet.");
    }

    public final Context e() {
        yw0 yw0Var = this.A;
        if (yw0Var == null) {
            return null;
        }
        return yw0Var.r;
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final int f() {
        Lifecycle$State lifecycle$State = this.T;
        return (lifecycle$State == Lifecycle$State.e || this.C == null) ? lifecycle$State.ordinal() : Math.min(lifecycle$State.ordinal(), this.C.f());
    }

    @Override // p.n11
    public final yl1 g() {
        Application application;
        Context applicationContext = B().getApplicationContext();
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
        if (application == null && Log.isLoggable("FragmentManager", 3)) {
            Objects.toString(B().getApplicationContext());
        }
        yl1 yl1Var = new yl1();
        LinkedHashMap linkedHashMap = yl1Var.a;
        if (application != null) {
            linkedHashMap.put(u11.u, application);
        }
        linkedHashMap.put(he1.e, this);
        linkedHashMap.put(he1.f, this);
        Bundle bundle = this.n;
        if (bundle != null) {
            linkedHashMap.put(he1.g, bundle);
        }
        return yl1Var;
    }

    public final ox0 h() {
        ox0 ox0Var = this.z;
        if (ox0Var != null) {
            return ox0Var;
        }
        throw new IllegalStateException("Fragment " + this + " not associated with a fragment manager.");
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public final void i() {
        this.U = new androidx.lifecycle.a(this);
        this.X = zb0.s(this);
        ArrayList arrayList = this.Y;
        sw0 sw0Var = this.Z;
        if (arrayList.contains(sw0Var)) {
            return;
        }
        if (this.c < 0) {
            arrayList.add(sw0Var);
            return;
        }
        ww0 ww0Var = sw0Var.a;
        ww0Var.X.a();
        he1.p(ww0Var);
        Bundle bundle = ww0Var.e;
        ww0Var.X.b(bundle != null ? bundle.getBundle("registryState") : null);
    }

    @Override // p.mu2
    public final lu2 j() {
        if (this.z == null) {
            throw new IllegalStateException("Can't access ViewModels from detached fragment");
        }
        if (f() == 1) {
            throw new IllegalStateException("Calling getViewModelStore() before a Fragment reaches onCreate() when using setMaxLifecycle(INITIALIZED) is not supported");
        }
        HashMap map = this.z.L.e;
        lu2 lu2Var = (lu2) map.get(this.m);
        if (lu2Var != null) {
            return lu2Var;
        }
        lu2 lu2Var2 = new lu2();
        map.put(this.m, lu2Var2);
        return lu2Var2;
    }

    public final void k() {
        i();
        this.S = this.m;
        this.m = UUID.randomUUID().toString();
        this.s = false;
        this.t = false;
        this.u = false;
        this.v = false;
        this.w = false;
        this.y = 0;
        this.z = null;
        this.B = new ox0();
        this.A = null;
        this.D = 0;
        this.E = 0;
        this.F = null;
        this.G = false;
        this.H = false;
    }

    @Override // p.s91
    public final l91 l() {
        return this.U;
    }

    public final boolean m() {
        return this.A != null && this.s;
    }

    public final boolean n() {
        if (!this.G) {
            ox0 ox0Var = this.z;
            if (ox0Var != null) {
                ww0 ww0Var = this.C;
                ox0Var.getClass();
                if (ww0Var != null && ww0Var.n()) {
                }
            }
            return false;
        }
        return true;
    }

    public final boolean o() {
        return this.y > 0;
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        this.K = true;
    }

    @Override // android.view.View.OnCreateContextMenuListener
    public final void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        yw0 yw0Var = this.A;
        zw0 zw0Var = yw0Var == null ? null : (zw0) yw0Var.q;
        if (zw0Var != null) {
            zw0Var.onCreateContextMenu(contextMenu, view, contextMenuInfo);
            return;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to an activity.");
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
        this.K = true;
    }

    public void p() {
        this.K = true;
    }

    public void q(Context context) {
        this.K = true;
        yw0 yw0Var = this.A;
        if ((yw0Var == null ? null : yw0Var.q) != null) {
            this.K = true;
        }
    }

    public void r(Bundle bundle) {
        Bundle bundle2;
        this.K = true;
        Bundle bundle3 = this.e;
        if (bundle3 != null && (bundle2 = bundle3.getBundle("childFragmentManager")) != null) {
            this.B.Q(bundle2);
            ox0 ox0Var = this.B;
            ox0Var.E = false;
            ox0Var.F = false;
            ox0Var.L.h = false;
            ox0Var.t(1);
        }
        ox0 ox0Var2 = this.B;
        if (ox0Var2.s >= 1) {
            return;
        }
        ox0Var2.E = false;
        ox0Var2.F = false;
        ox0Var2.L.h = false;
        ox0Var2.t(1);
    }

    public View s(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return null;
    }

    public void t() {
        this.K = true;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} (");
        sb.append(this.m);
        if (this.D != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.D));
        }
        if (this.F != null) {
            sb.append(" tag=");
            sb.append(this.F);
        }
        sb.append(")");
        return sb.toString();
    }

    public void u() {
        this.K = true;
    }

    public LayoutInflater v(Bundle bundle) {
        yw0 yw0Var = this.A;
        if (yw0Var == null) {
            throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
        }
        zw0 zw0Var = yw0Var.u;
        LayoutInflater layoutInflaterCloneInContext = zw0Var.getLayoutInflater().cloneInContext(zw0Var);
        layoutInflaterCloneInContext.setFactory2(this.B.f);
        return layoutInflaterCloneInContext;
    }

    public abstract void w(Bundle bundle);

    public void x() {
        this.K = true;
    }

    public void y() {
        this.K = true;
    }

    public void z(Bundle bundle) {
        this.K = true;
    }
}
