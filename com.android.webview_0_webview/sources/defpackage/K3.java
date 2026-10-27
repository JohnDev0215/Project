package defpackage;

import android.animation.Animator;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
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
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class K3 implements ComponentCallbacks, View.OnCreateContextMenuListener, I4, R4 {
    public static final C1633v1 n0 = new C1633v1();
    public static final Object o0 = new Object();
    public int A;
    public boolean B;
    public boolean C;
    public boolean D;
    public boolean E;
    public boolean F;
    public boolean G;
    public int H;
    public LayoutInflaterFactory2C0974j4 I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public R3 f4J;
    public LayoutInflaterFactory2C0974j4 K;
    public C1030k4 L;
    public Q4 M;
    public K3 N;
    public int O;
    public int P;
    public String Q;
    public boolean R;
    public boolean S;
    public boolean T;
    public boolean U;
    public boolean V;
    public boolean X;
    public ViewGroup Y;
    public View Z;
    public View a0;
    public boolean b0;
    public H3 d0;
    public boolean e0;
    public boolean f0;
    public float g0;
    public LayoutInflater h0;
    public boolean i0;
    public J4 k0;
    public I4 l0;
    public Bundle t;
    public SparseArray u;
    public String w;
    public Bundle x;
    public K3 y;
    public int s = 0;
    public int v = -1;
    public int z = -1;
    public boolean W = true;
    public boolean c0 = true;
    public J4 j0 = new J4(this);
    public L4 m0 = new L4();

    public static K3 v(Context context, String str, Bundle bundle) {
        C1633v1 c1633v1 = n0;
        try {
            Class<?> clsLoadClass = (Class) c1633v1.get(str);
            if (clsLoadClass == null) {
                clsLoadClass = context.getClassLoader().loadClass(str);
                c1633v1.put(str, clsLoadClass);
            }
            K3 k3 = (K3) clsLoadClass.getConstructor(new Class[0]).newInstance(new Object[0]);
            if (bundle != null) {
                bundle.setClassLoader(k3.getClass().getClassLoader());
                k3.a0(bundle);
            }
            return k3;
        } catch (ClassNotFoundException e) {
            throw new I3("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e);
        } catch (IllegalAccessException e2) {
            throw new I3("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e2);
        } catch (InstantiationException e3) {
            throw new I3("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e3);
        } catch (NoSuchMethodException e4) {
            throw new I3(Z7.d("Unable to instantiate fragment ", str, ": could not find Fragment constructor"), e4);
        } catch (InvocationTargetException e5) {
            throw new I3(Z7.d("Unable to instantiate fragment ", str, ": calling Fragment constructor caused an exception"), e5);
        }
    }

    public void A(int i, int i2, Intent intent) {
    }

    public void B(Context context) {
        this.X = true;
        R3 r3 = this.f4J;
        if ((r3 == null ? null : r3.a) != null) {
            this.X = false;
            this.X = true;
        }
    }

    public void C(Bundle bundle) {
        this.X = true;
        X(bundle);
        LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = this.K;
        if (layoutInflaterFactory2C0974j4 != null) {
            if (layoutInflaterFactory2C0974j4.C >= 1) {
                return;
            }
            this.K.l();
        }
    }

    public void D(Menu menu, MenuInflater menuInflater) {
    }

    public View E(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return null;
    }

    public void F() {
        this.X = true;
        O3 o3F = f();
        boolean z = o3F != null && o3F.isChangingConfigurations();
        Q4 q4 = this.M;
        if (q4 == null || z) {
            return;
        }
        q4.a();
    }

    public void G() {
        this.X = true;
    }

    public void H() {
        this.X = true;
    }

    public LayoutInflater I(Bundle bundle) {
        R3 r3 = this.f4J;
        if (r3 == null) {
            throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
        }
        M3 m3 = (M3) r3;
        LayoutInflater layoutInflaterCloneInContext = m3.e.getLayoutInflater().cloneInContext(m3.e);
        if (this.K == null) {
            w();
            int i = this.s;
            if (i >= 4) {
                this.K.I();
            } else if (i >= 3) {
                this.K.J();
            } else if (i >= 2) {
                this.K.i();
            } else if (i >= 1) {
                this.K.l();
            }
        }
        LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = this.K;
        Objects.requireNonNull(layoutInflaterFactory2C0974j4);
        layoutInflaterCloneInContext.setFactory2(layoutInflaterFactory2C0974j4);
        return layoutInflaterCloneInContext;
    }

    public void J(AttributeSet attributeSet, Bundle bundle) {
        this.X = true;
        R3 r3 = this.f4J;
        if ((r3 == null ? null : r3.a) != null) {
            this.X = false;
            this.X = true;
        }
    }

    public boolean K(MenuItem menuItem) {
        return false;
    }

    public void L() {
        this.X = true;
    }

    public void M(Bundle bundle) {
    }

    public void N() {
        this.X = true;
    }

    public void O() {
        this.X = true;
    }

    public void P(View view, Bundle bundle) {
    }

    public boolean Q(MenuItem menuItem) {
        LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4;
        return (this.R || (layoutInflaterFactory2C0974j4 = this.K) == null || !layoutInflaterFactory2C0974j4.k(menuItem)) ? false : true;
    }

    public void R(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = this.K;
        if (layoutInflaterFactory2C0974j4 != null) {
            layoutInflaterFactory2C0974j4.d0();
        }
        this.G = true;
        this.l0 = new G3(this);
        this.k0 = null;
        View viewE = E(layoutInflater, viewGroup, bundle);
        this.Z = viewE;
        if (viewE != null) {
            this.l0.b();
            this.m0.a(this.l0);
        } else {
            if (this.k0 != null) {
                throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
            }
            this.l0 = null;
        }
    }

    public LayoutInflater S(Bundle bundle) {
        LayoutInflater layoutInflaterI = I(bundle);
        this.h0 = layoutInflaterI;
        return layoutInflaterI;
    }

    public void T() {
        this.X = true;
        LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = this.K;
        if (layoutInflaterFactory2C0974j4 != null) {
            layoutInflaterFactory2C0974j4.o();
        }
    }

    public boolean U(MenuItem menuItem) {
        if (this.R) {
            return false;
        }
        if (this.V && K(menuItem)) {
            return true;
        }
        LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = this.K;
        return layoutInflaterFactory2C0974j4 != null && layoutInflaterFactory2C0974j4.E(menuItem);
    }

    public boolean V(Menu menu) {
        if (this.R) {
            return false;
        }
        boolean z = this.V;
        LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = this.K;
        return layoutInflaterFactory2C0974j4 != null ? z | layoutInflaterFactory2C0974j4.H(menu) : z;
    }

    public void W(Bundle bundle) {
        Parcelable parcelableL0;
        M(bundle);
        LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = this.K;
        if (layoutInflaterFactory2C0974j4 == null || (parcelableL0 = layoutInflaterFactory2C0974j4.l0()) == null) {
            return;
        }
        bundle.putParcelable("android:support:fragments", parcelableL0);
    }

    public void X(Bundle bundle) {
        Parcelable parcelable;
        if (bundle == null || (parcelable = bundle.getParcelable("android:support:fragments")) == null) {
            return;
        }
        if (this.K == null) {
            w();
        }
        this.K.j0(parcelable, this.L);
        this.L = null;
        this.K.l();
    }

    public void Y(View view) {
        d().a = view;
    }

    public void Z(Animator animator) {
        d().b = animator;
    }

    @Override // defpackage.R4
    public Q4 a() {
        if (i() == null) {
            throw new IllegalStateException("Can't access ViewModels from detached fragment");
        }
        if (this.M == null) {
            this.M = new Q4();
        }
        return this.M;
    }

    public void a0(Bundle bundle) {
        if (this.v >= 0) {
            LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = this.I;
            if (layoutInflaterFactory2C0974j4 == null ? false : layoutInflaterFactory2C0974j4.V()) {
                throw new IllegalStateException("Fragment already active and state has been saved");
            }
        }
        this.x = bundle;
    }

    @Override // defpackage.I4
    public H4 b() {
        return this.j0;
    }

    public void b0(boolean z) {
        d().q = z;
    }

    public void c(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.O));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.P));
        printWriter.print(" mTag=");
        printWriter.println(this.Q);
        printWriter.print(str);
        printWriter.print("mState=");
        printWriter.print(this.s);
        printWriter.print(" mIndex=");
        printWriter.print(this.v);
        printWriter.print(" mWho=");
        printWriter.print(this.w);
        printWriter.print(" mBackStackNesting=");
        printWriter.println(this.H);
        printWriter.print(str);
        printWriter.print("mAdded=");
        printWriter.print(this.B);
        printWriter.print(" mRemoving=");
        printWriter.print(this.C);
        printWriter.print(" mFromLayout=");
        printWriter.print(this.D);
        printWriter.print(" mInLayout=");
        printWriter.println(this.E);
        printWriter.print(str);
        printWriter.print("mHidden=");
        printWriter.print(this.R);
        printWriter.print(" mDetached=");
        printWriter.print(this.S);
        printWriter.print(" mMenuVisible=");
        printWriter.print(true);
        printWriter.print(" mHasMenu=");
        printWriter.println(this.V);
        printWriter.print(str);
        printWriter.print("mRetainInstance=");
        printWriter.print(this.T);
        printWriter.print(" mRetaining=");
        printWriter.print(this.U);
        printWriter.print(" mUserVisibleHint=");
        printWriter.println(this.c0);
        if (this.I != null) {
            printWriter.print(str);
            printWriter.print("mFragmentManager=");
            printWriter.println(this.I);
        }
        if (this.f4J != null) {
            printWriter.print(str);
            printWriter.print("mHost=");
            printWriter.println(this.f4J);
        }
        if (this.N != null) {
            printWriter.print(str);
            printWriter.print("mParentFragment=");
            printWriter.println(this.N);
        }
        if (this.x != null) {
            printWriter.print(str);
            printWriter.print("mArguments=");
            printWriter.println(this.x);
        }
        if (this.t != null) {
            printWriter.print(str);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.t);
        }
        if (this.u != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.u);
        }
        if (this.y != null) {
            printWriter.print(str);
            printWriter.print("mTarget=");
            printWriter.print(this.y);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.A);
        }
        if (n() != 0) {
            printWriter.print(str);
            printWriter.print("mNextAnim=");
            printWriter.println(n());
        }
        if (this.Y != null) {
            printWriter.print(str);
            printWriter.print("mContainer=");
            printWriter.println(this.Y);
        }
        if (this.Z != null) {
            printWriter.print(str);
            printWriter.print("mView=");
            printWriter.println(this.Z);
        }
        if (this.a0 != null) {
            printWriter.print(str);
            printWriter.print("mInnerView=");
            printWriter.println(this.Z);
        }
        if (g() != null) {
            printWriter.print(str);
            printWriter.print("mAnimatingAway=");
            printWriter.println(g());
            printWriter.print(str);
            printWriter.print("mStateAfterAnimating=");
            printWriter.println(u());
        }
        if (i() != null) {
            S4.b(this).a(str, fileDescriptor, printWriter, strArr);
        }
        if (this.K != null) {
            printWriter.print(str);
            printWriter.println("Child " + this.K + ":");
            this.K.L(Z7.c(str, "  "), fileDescriptor, printWriter, strArr);
        }
    }

    public final void c0(int i, K3 k3) {
        this.v = i;
        if (k3 == null) {
            StringBuilder sbF = Z7.f("android:fragment:");
            sbF.append(this.v);
            this.w = sbF.toString();
        } else {
            this.w = k3.w + ":" + this.v;
        }
    }

    public final H3 d() {
        if (this.d0 == null) {
            this.d0 = new H3();
        }
        return this.d0;
    }

    public void d0(int i) {
        if (this.d0 == null && i == 0) {
            return;
        }
        d().d = i;
    }

    public K3 e(String str) {
        if (str.equals(this.w)) {
            return this;
        }
        LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = this.K;
        if (layoutInflaterFactory2C0974j4 != null) {
            return layoutInflaterFactory2C0974j4.T(str);
        }
        return null;
    }

    public void e0(J3 j3) {
        d();
        J3 j32 = this.d0.p;
        if (j3 == j32) {
            return;
        }
        if (j3 != null && j32 != null) {
            throw new IllegalStateException("Trying to set a replacement startPostponedEnterTransition on " + this);
        }
        H3 h3 = this.d0;
        if (h3.o) {
            h3.p = j3;
        }
        if (j3 != null) {
            ((C0919i4) j3).c++;
        }
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final O3 f() {
        R3 r3 = this.f4J;
        if (r3 == null) {
            return null;
        }
        return (O3) r3.a;
    }

    public void f0(Intent intent) {
        R3 r3 = this.f4J;
        if (r3 == null) {
            throw new IllegalStateException(Z7.b("Fragment ", this, " not attached to Activity"));
        }
        r3.d(this, intent, -1, null);
    }

    public View g() {
        H3 h3 = this.d0;
        if (h3 == null) {
            return null;
        }
        return h3.a;
    }

    public Animator h() {
        H3 h3 = this.d0;
        if (h3 == null) {
            return null;
        }
        return h3.b;
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public Context i() {
        R3 r3 = this.f4J;
        if (r3 == null) {
            return null;
        }
        return r3.b;
    }

    public Object j() {
        H3 h3 = this.d0;
        if (h3 == null) {
            return null;
        }
        Objects.requireNonNull(h3);
        return null;
    }

    public void k() {
        H3 h3 = this.d0;
        if (h3 == null) {
            return;
        }
        Objects.requireNonNull(h3);
    }

    public Object l() {
        H3 h3 = this.d0;
        if (h3 == null) {
            return null;
        }
        Objects.requireNonNull(h3);
        return null;
    }

    public final LayoutInflater m() {
        LayoutInflater layoutInflater = this.h0;
        return layoutInflater == null ? S(null) : layoutInflater;
    }

    public int n() {
        H3 h3 = this.d0;
        if (h3 == null) {
            return 0;
        }
        return h3.d;
    }

    public int o() {
        H3 h3 = this.d0;
        if (h3 == null) {
            return 0;
        }
        return h3.e;
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        this.X = true;
    }

    @Override // android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        f().onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        this.X = true;
    }

    public int p() {
        H3 h3 = this.d0;
        if (h3 == null) {
            return 0;
        }
        return h3.f;
    }

    public Object q() {
        H3 h3 = this.d0;
        if (h3 == null) {
            return null;
        }
        Object obj = h3.j;
        if (obj != o0) {
            return obj;
        }
        l();
        return null;
    }

    public Object r() {
        H3 h3 = this.d0;
        if (h3 == null) {
            return null;
        }
        Object obj = h3.h;
        if (obj != o0) {
            return obj;
        }
        j();
        return null;
    }

    public Object s() {
        H3 h3 = this.d0;
        if (h3 == null) {
            return null;
        }
        Objects.requireNonNull(h3);
        return null;
    }

    public Object t() {
        H3 h3 = this.d0;
        if (h3 == null) {
            return null;
        }
        Object obj = h3.l;
        if (obj != o0) {
            return obj;
        }
        s();
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        Y2.a(this, sb);
        if (this.v >= 0) {
            sb.append(" #");
            sb.append(this.v);
        }
        if (this.O != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.O));
        }
        if (this.Q != null) {
            sb.append(" ");
            sb.append(this.Q);
        }
        sb.append('}');
        return sb.toString();
    }

    public int u() {
        H3 h3 = this.d0;
        if (h3 == null) {
            return 0;
        }
        return h3.c;
    }

    public void w() {
        if (this.f4J == null) {
            throw new IllegalStateException("Fragment has not been attached yet.");
        }
        LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = new LayoutInflaterFactory2C0974j4();
        this.K = layoutInflaterFactory2C0974j4;
        R3 r3 = this.f4J;
        F3 f3 = new F3(this);
        if (layoutInflaterFactory2C0974j4.D != null) {
            throw new IllegalStateException("Already attached");
        }
        layoutInflaterFactory2C0974j4.D = r3;
        layoutInflaterFactory2C0974j4.E = f3;
        layoutInflaterFactory2C0974j4.F = this;
    }

    public boolean x() {
        H3 h3 = this.d0;
        if (h3 == null) {
            return false;
        }
        return h3.q;
    }

    public final boolean y() {
        return this.H > 0;
    }

    public void z(Bundle bundle) {
        this.X = true;
    }
}
