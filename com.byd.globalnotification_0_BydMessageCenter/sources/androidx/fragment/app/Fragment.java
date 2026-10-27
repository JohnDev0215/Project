package androidx.fragment.app;

import a.l.d.e;
import a.l.d.m0;
import a.l.d.n;
import a.l.d.o;
import a.l.d.r;
import a.l.d.t;
import a.l.d.v;
import a.n.d0;
import a.n.e0;
import a.n.f;
import a.n.g;
import a.n.i;
import a.n.k;
import a.n.l;
import a.n.p;
import a.n.w;
import a.n.z;
import android.animation.Animator;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class Fragment implements ComponentCallbacks, View.OnCreateContextMenuListener, k, e0, f, a.q.c {
    public static final Object U = new Object();
    public boolean A;
    public boolean B;
    public boolean D;
    public ViewGroup E;
    public View F;
    public boolean G;
    public b I;
    public boolean J;
    public boolean K;
    public float L;
    public LayoutInflater M;
    public boolean N;
    public l P;
    public m0 Q;
    public z S;
    public a.q.b T;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Bundle f1554b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public SparseArray<Parcelable> f1555c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Boolean f1556d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Bundle f1558f;
    public Fragment g;
    public int i;
    public boolean k;
    public boolean l;
    public boolean m;
    public boolean n;
    public boolean o;
    public boolean p;
    public int q;
    public r r;
    public o<?> s;
    public Fragment u;
    public int v;
    public int w;
    public String x;
    public boolean y;
    public boolean z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1553a = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f1557e = UUID.randomUUID().toString();
    public String h = null;
    public Boolean j = null;
    public r t = new t();
    public boolean C = true;
    public boolean H = true;
    public g.b O = g.b.RESUMED;
    public p<k> R = new p<>();

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Fragment.this.b();
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public View f1561a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Animator f1562b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1563c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1564d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1565e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Object f1566f = null;
        public Object g;
        public Object h;
        public Object i;
        public Object j;
        public Object k;
        public Boolean l;
        public Boolean m;
        public a.h.c.c n;
        public a.h.c.c o;
        public boolean p;
        public d q;
        public boolean r;

        public b() {
            Object obj = Fragment.U;
            this.g = obj;
            this.h = null;
            this.i = obj;
            this.j = null;
            this.k = obj;
            this.n = null;
            this.o = null;
        }
    }

    public static class c extends RuntimeException {
        public c(String str, Exception exc) {
            super(str, exc);
        }
    }

    public interface d {
    }

    public Fragment() {
        t();
    }

    @Deprecated
    public static Fragment u(Context context, String str, Bundle bundle) {
        try {
            Fragment fragmentNewInstance = n.d(context.getClassLoader(), str).getConstructor(new Class[0]).newInstance(new Object[0]);
            if (bundle != null) {
                bundle.setClassLoader(fragmentNewInstance.getClass().getClassLoader());
                fragmentNewInstance.J(bundle);
            }
            return fragmentNewInstance;
        } catch (IllegalAccessException e2) {
            throw new c("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e2);
        } catch (InstantiationException e3) {
            throw new c("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e3);
        } catch (NoSuchMethodException e4) {
            throw new c("Unable to instantiate fragment " + str + ": could not find Fragment constructor", e4);
        } catch (InvocationTargetException e5) {
            throw new c("Unable to instantiate fragment " + str + ": calling Fragment constructor caused an exception", e5);
        }
    }

    public void A(AttributeSet attributeSet, Bundle bundle) {
        this.D = true;
        o<?> oVar = this.s;
        if ((oVar == null ? null : oVar.f1017a) != null) {
            this.D = false;
            this.D = true;
        }
    }

    public void B(Bundle bundle) {
    }

    public void C(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.t.R();
        this.p = true;
        this.Q = new m0();
        View viewZ = z(layoutInflater, viewGroup, bundle);
        this.F = viewZ;
        if (viewZ == null) {
            if (this.Q.f1015a != null) {
                throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
            }
            this.Q = null;
        } else {
            m0 m0Var = this.Q;
            if (m0Var.f1015a == null) {
                m0Var.f1015a = new l(m0Var);
            }
            this.R.h(this.Q);
        }
    }

    public LayoutInflater D(Bundle bundle) {
        o<?> oVar = this.s;
        if (oVar == null) {
            throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
        }
        e.a aVar = (e.a) oVar;
        LayoutInflater layoutInflaterCloneInContext = e.this.getLayoutInflater().cloneInContext(e.this);
        layoutInflaterCloneInContext.setFactory2(this.t.f1030f);
        this.M = layoutInflaterCloneInContext;
        return layoutInflaterCloneInContext;
    }

    public void E() {
        this.D = true;
        this.t.o();
    }

    public final e F() {
        o<?> oVar = this.s;
        e eVar = oVar == null ? null : (e) oVar.f1017a;
        if (eVar != null) {
            return eVar;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to an activity.");
    }

    public final View G() {
        View view = this.F;
        if (view != null) {
            return view;
        }
        throw new IllegalStateException("Fragment " + this + " did not return a View from onCreateView() or this was called before onCreateView().");
    }

    public void H(View view) {
        g().f1561a = view;
    }

    public void I(Animator animator) {
        g().f1562b = animator;
    }

    public void J(Bundle bundle) {
        r rVar = this.r;
        if (rVar != null) {
            if (rVar == null ? false : rVar.M()) {
                throw new IllegalStateException("Fragment already added and state has been saved");
            }
        }
        this.f1558f = bundle;
    }

    public void K(boolean z) {
        g().r = z;
    }

    public void L(int i) {
        if (this.I == null && i == 0) {
            return;
        }
        g().f1564d = i;
    }

    public void M(d dVar) {
        g();
        d dVar2 = this.I.q;
        if (dVar == dVar2) {
            return;
        }
        if (dVar != null && dVar2 != null) {
            throw new IllegalStateException("Trying to set a replacement startPostponedEnterTransition on " + this);
        }
        b bVar = this.I;
        if (bVar.p) {
            bVar.q = dVar;
        }
        if (dVar != null) {
            ((r.f) dVar).f1037c++;
        }
    }

    public void N(int i) {
        g().f1563c = i;
    }

    public void O() {
        r rVar = this.r;
        if (rVar == null || rVar.n == null) {
            g().p = false;
        } else if (Looper.myLooper() != this.r.n.f1019c.getLooper()) {
            this.r.n.f1019c.postAtFrontOfQueue(new a());
        } else {
            b();
        }
    }

    @Override // a.n.k
    public g a() {
        return this.P;
    }

    public void b() {
        b bVar = this.I;
        d dVar = null;
        if (bVar != null) {
            bVar.p = false;
            d dVar2 = bVar.q;
            bVar.q = null;
            dVar = dVar2;
        }
        if (dVar != null) {
            r.f fVar = (r.f) dVar;
            int i = fVar.f1037c - 1;
            fVar.f1037c = i;
            if (i != 0) {
                return;
            }
            r rVar = fVar.f1036b.q;
            synchronized (rVar.f1025a) {
                boolean z = (rVar.B == null || rVar.B.isEmpty()) ? false : true;
                boolean z2 = rVar.f1025a.size() == 1;
                if (z || z2) {
                    rVar.n.f1019c.removeCallbacks(rVar.D);
                    rVar.n.f1019c.post(rVar.D);
                    rVar.e0();
                }
            }
        }
    }

    @Override // a.q.c
    public final a.q.a d() {
        return this.T.f1276b;
    }

    @Override // a.n.e0
    public d0 e() {
        r rVar = this.r;
        if (rVar == null) {
            throw new IllegalStateException("Can't access ViewModels from detached fragment");
        }
        v vVar = rVar.C;
        d0 d0Var = vVar.f1048e.get(this.f1557e);
        if (d0Var != null) {
            return d0Var;
        }
        d0 d0Var2 = new d0();
        vVar.f1048e.put(this.f1557e, d0Var2);
        return d0Var2;
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void f(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String str2;
        printWriter.print(str);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.v));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.w));
        printWriter.print(" mTag=");
        printWriter.println(this.x);
        printWriter.print(str);
        printWriter.print("mState=");
        printWriter.print(this.f1553a);
        printWriter.print(" mWho=");
        printWriter.print(this.f1557e);
        printWriter.print(" mBackStackNesting=");
        printWriter.println(this.q);
        printWriter.print(str);
        printWriter.print("mAdded=");
        printWriter.print(this.k);
        printWriter.print(" mRemoving=");
        printWriter.print(this.l);
        printWriter.print(" mFromLayout=");
        printWriter.print(this.m);
        printWriter.print(" mInLayout=");
        printWriter.println(this.n);
        printWriter.print(str);
        printWriter.print("mHidden=");
        printWriter.print(this.y);
        printWriter.print(" mDetached=");
        printWriter.print(this.z);
        printWriter.print(" mMenuVisible=");
        printWriter.print(this.C);
        printWriter.print(" mHasMenu=");
        printWriter.println(false);
        printWriter.print(str);
        printWriter.print("mRetainInstance=");
        printWriter.print(this.A);
        printWriter.print(" mUserVisibleHint=");
        printWriter.println(this.H);
        if (this.r != null) {
            printWriter.print(str);
            printWriter.print("mFragmentManager=");
            printWriter.println(this.r);
        }
        if (this.s != null) {
            printWriter.print(str);
            printWriter.print("mHost=");
            printWriter.println(this.s);
        }
        if (this.u != null) {
            printWriter.print(str);
            printWriter.print("mParentFragment=");
            printWriter.println(this.u);
        }
        if (this.f1558f != null) {
            printWriter.print(str);
            printWriter.print("mArguments=");
            printWriter.println(this.f1558f);
        }
        if (this.f1554b != null) {
            printWriter.print(str);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.f1554b);
        }
        if (this.f1555c != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.f1555c);
        }
        Fragment fragmentE = this.g;
        if (fragmentE == null) {
            r rVar = this.r;
            fragmentE = (rVar == null || (str2 = this.h) == null) ? null : rVar.f1027c.e(str2);
        }
        if (fragmentE != null) {
            printWriter.print(str);
            printWriter.print("mTarget=");
            printWriter.print(fragmentE);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.i);
        }
        if (o() != 0) {
            printWriter.print(str);
            printWriter.print("mNextAnim=");
            printWriter.println(o());
        }
        if (this.E != null) {
            printWriter.print(str);
            printWriter.print("mContainer=");
            printWriter.println(this.E);
        }
        if (this.F != null) {
            printWriter.print(str);
            printWriter.print("mView=");
            printWriter.println(this.F);
        }
        if (i() != null) {
            printWriter.print(str);
            printWriter.print("mAnimatingAway=");
            printWriter.println(i());
            printWriter.print(str);
            printWriter.print("mStateAfterAnimating=");
            printWriter.println(s());
        }
        if (l() != null) {
            a.o.a.a.b(this).a(str, fileDescriptor, printWriter, strArr);
        }
        printWriter.print(str);
        printWriter.println("Child " + this.t + ":");
        this.t.x(b.a.a.a.a.c(str, "  "), fileDescriptor, printWriter, strArr);
    }

    public final b g() {
        if (this.I == null) {
            this.I = new b();
        }
        return this.I;
    }

    public Fragment h(String str) {
        return str.equals(this.f1557e) ? this : this.t.F(str);
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public View i() {
        b bVar = this.I;
        if (bVar == null) {
            return null;
        }
        return bVar.f1561a;
    }

    @Override // a.n.f
    public z j() {
        if (this.r == null) {
            throw new IllegalStateException("Can't access ViewModels from detached fragment");
        }
        if (this.S == null) {
            this.S = new w(F().getApplication(), this, this.f1558f);
        }
        return this.S;
    }

    public final r k() {
        if (this.s != null) {
            return this.t;
        }
        throw new IllegalStateException("Fragment " + this + " has not been attached yet.");
    }

    public Context l() {
        o<?> oVar = this.s;
        if (oVar == null) {
            return null;
        }
        return oVar.f1018b;
    }

    public Object m() {
        b bVar = this.I;
        if (bVar == null) {
            return null;
        }
        return bVar.f1566f;
    }

    public Object n() {
        b bVar = this.I;
        if (bVar == null) {
            return null;
        }
        return bVar.h;
    }

    public int o() {
        b bVar = this.I;
        if (bVar == null) {
            return 0;
        }
        return bVar.f1564d;
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        this.D = true;
    }

    @Override // android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        F().onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        this.D = true;
    }

    public final r p() {
        r rVar = this.r;
        if (rVar != null) {
            return rVar;
        }
        throw new IllegalStateException("Fragment " + this + " not associated with a fragment manager.");
    }

    public final Resources q() {
        Context contextL = l();
        if (contextL != null) {
            return contextL.getResources();
        }
        throw new IllegalStateException("Fragment " + this + " not attached to a context.");
    }

    public Object r() {
        b bVar = this.I;
        if (bVar == null) {
            return null;
        }
        return bVar.j;
    }

    public int s() {
        b bVar = this.I;
        if (bVar == null) {
            return 0;
        }
        return bVar.f1563c;
    }

    public final void t() {
        this.P = new l(this);
        this.T = new a.q.b(this);
        this.P.a(new i() { // from class: androidx.fragment.app.Fragment.2
            @Override // a.n.i
            public void g(k kVar, g.a aVar) {
                View view;
                if (aVar != g.a.ON_STOP || (view = Fragment.this.F) == null) {
                    return;
                }
                view.cancelPendingInputEvents();
            }
        });
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("}");
        sb.append(" (");
        sb.append(this.f1557e);
        sb.append(")");
        if (this.v != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.v));
        }
        if (this.x != null) {
            sb.append(" ");
            sb.append(this.x);
        }
        sb.append('}');
        return sb.toString();
    }

    public boolean v() {
        b bVar = this.I;
        if (bVar == null) {
            return false;
        }
        return bVar.r;
    }

    public final boolean w() {
        return this.q > 0;
    }

    public final boolean x() {
        Fragment fragment = this.u;
        return fragment != null && (fragment.l || fragment.x());
    }

    public void y(Bundle bundle) {
        Parcelable parcelable;
        this.D = true;
        if (bundle != null && (parcelable = bundle.getParcelable("android:support:fragments")) != null) {
            this.t.W(parcelable);
            this.t.l();
        }
        if (this.t.m >= 1) {
            return;
        }
        this.t.l();
    }

    public View z(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return null;
    }
}
