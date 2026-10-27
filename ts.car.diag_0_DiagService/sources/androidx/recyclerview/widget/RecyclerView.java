package androidx.recyclerview.widget;

import android.R;
import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.FocusFinder;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.core.view.accessibility.b;
import androidx.customview.view.AbsSavedState;
import androidx.recyclerview.R$attr;
import androidx.recyclerview.R$dimen;
import androidx.recyclerview.R$styleable;
import androidx.recyclerview.widget.ViewBoundsCheck;
import androidx.recyclerview.widget.a;
import androidx.recyclerview.widget.d;
import androidx.recyclerview.widget.g;
import androidx.recyclerview.widget.l;
import androidx.recyclerview.widget.o;
import com.byd.mediacenter.api.media.MediaControl;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class RecyclerView extends ViewGroup implements androidx.core.view.j {
    static final boolean A0;
    static final boolean B0;
    private static final boolean C0;
    private static final boolean D0;
    private static final Class<?>[] E0;
    static final Interpolator F0;
    private static final int[] x0;
    static final boolean y0;
    static final boolean z0;
    boolean A;
    private final AccessibilityManager B;
    private List<o> C;
    boolean D;
    boolean E;
    private int F;
    private int G;

    @NonNull
    private EdgeEffectFactory H;
    private EdgeEffect I;
    private EdgeEffect J;
    private EdgeEffect K;
    private EdgeEffect L;
    ItemAnimator M;
    private int N;
    private int O;
    private VelocityTracker P;
    private int Q;
    private int R;
    private int S;
    private int T;
    private int U;
    private p V;
    private final int W;
    private final int a0;
    private float b0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final v f1091c;
    private float c0;
    final t d;
    private boolean d0;
    SavedState e;
    final z e0;
    a f;
    androidx.recyclerview.widget.g f0;
    androidx.recyclerview.widget.d g;
    g.b g0;
    final androidx.recyclerview.widget.o h;
    final x h0;
    boolean i;
    private r i0;
    final Runnable j;
    private List<r> j0;
    final Rect k;
    boolean k0;
    private final Rect l;
    boolean l0;
    final RectF m;
    private ItemAnimator.b m0;

    @VisibleForTesting
    boolean mFirstLayoutComplete;

    @VisibleForTesting
    m mLayout;

    @VisibleForTesting
    final List<A> mPendingAccessibilityImportanceChange;
    Adapter n;
    boolean n0;
    u o;
    androidx.recyclerview.widget.l o0;
    final ArrayList<l> p;
    private j p0;
    private final ArrayList<q> q;
    private final int[] q0;
    private q r;
    private androidx.core.view.k r0;
    boolean s;
    private final int[] s0;
    boolean t;
    private final int[] t0;
    boolean u;
    final int[] u0;
    private int v;
    private Runnable v0;
    boolean w;
    private final o.b w0;
    boolean x;
    private boolean y;
    private int z;

    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class A {
        private static final List<Object> s = Collections.emptyList();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        @NonNull
        public final View f1092a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        WeakReference<RecyclerView> f1093b;
        int j;
        RecyclerView q;
        Adapter<? extends A> r;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1094c = -1;
        int d = -1;
        long e = -1;
        int f = -1;
        int g = -1;
        A h = null;
        A i = null;
        List<Object> k = null;
        List<Object> l = null;
        private int m = 0;
        t n = null;
        boolean o = false;
        private int p = 0;

        @VisibleForTesting
        int mPendingAccessibilityState = -1;

        public A(@NonNull View view) {
            if (view == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.f1092a = view;
        }

        private void g() {
            if (this.k == null) {
                ArrayList arrayList = new ArrayList();
                this.k = arrayList;
                this.l = Collections.unmodifiableList(arrayList);
            }
        }

        void A(int i, boolean z) {
            if (this.d == -1) {
                this.d = this.f1094c;
            }
            if (this.g == -1) {
                this.g = this.f1094c;
            }
            if (z) {
                this.g += i;
            }
            this.f1094c += i;
            if (this.f1092a.getLayoutParams() != null) {
                ((n) this.f1092a.getLayoutParams()).f1120c = true;
            }
        }

        void B(RecyclerView recyclerView) {
            int i = this.mPendingAccessibilityState;
            if (i != -1) {
                this.p = i;
            } else {
                this.p = ViewCompat.o(this.f1092a);
            }
            recyclerView.setChildImportantForAccessibilityInternal(this, 4);
        }

        void C(RecyclerView recyclerView) {
            recyclerView.setChildImportantForAccessibilityInternal(this, this.p);
            this.p = 0;
        }

        void D() {
            this.j = 0;
            this.f1094c = -1;
            this.d = -1;
            this.e = -1L;
            this.g = -1;
            this.m = 0;
            this.h = null;
            this.i = null;
            d();
            this.p = 0;
            this.mPendingAccessibilityState = -1;
            RecyclerView.q(this);
        }

        void E() {
            if (this.d == -1) {
                this.d = this.f1094c;
            }
        }

        void F(int i, int i2) {
            this.j = (i & i2) | (this.j & (~i2));
        }

        public final void G(boolean z) {
            int i = this.m;
            int i2 = z ? i - 1 : i + 1;
            this.m = i2;
            if (i2 < 0) {
                this.m = 0;
                Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
                return;
            }
            if (!z && i2 == 1) {
                this.j |= 16;
            } else if (z && this.m == 0) {
                this.j &= -17;
            }
        }

        void H(t tVar, boolean z) {
            this.n = tVar;
            this.o = z;
        }

        boolean I() {
            return (this.j & 16) != 0;
        }

        boolean J() {
            return (this.j & 128) != 0;
        }

        void K() {
            this.n.G(this);
        }

        boolean L() {
            return (this.j & 32) != 0;
        }

        void a(Object obj) {
            if (obj == null) {
                b(1024);
            } else if ((1024 & this.j) == 0) {
                g();
                this.k.add(obj);
            }
        }

        void b(int i) {
            this.j = i | this.j;
        }

        void c() {
            this.d = -1;
            this.g = -1;
        }

        void d() {
            List<Object> list = this.k;
            if (list != null) {
                list.clear();
            }
            this.j &= -1025;
        }

        void e() {
            this.j &= -33;
        }

        void f() {
            this.j &= -257;
        }

        boolean h() {
            return (this.j & 16) == 0 && ViewCompat.y(this.f1092a);
        }

        void i(int i, int i2, boolean z) {
            b(8);
            A(i2, z);
            this.f1094c = i;
        }

        public final int j() {
            RecyclerView recyclerView = this.q;
            if (recyclerView == null) {
                return -1;
            }
            return recyclerView.b0(this);
        }

        public final long k() {
            return this.e;
        }

        public final int l() {
            return this.f;
        }

        public final int m() {
            int i = this.g;
            return i == -1 ? this.f1094c : i;
        }

        public final int n() {
            return this.d;
        }

        List<Object> o() {
            if ((this.j & 1024) != 0) {
                return s;
            }
            List<Object> list = this.k;
            return (list == null || list.size() == 0) ? s : this.l;
        }

        boolean p(int i) {
            return (this.j & i) != 0;
        }

        boolean q() {
            return (this.j & 512) != 0 || t();
        }

        boolean r() {
            return (this.f1092a.getParent() == null || this.f1092a.getParent() == this.q) ? false : true;
        }

        boolean s() {
            return (this.j & 1) != 0;
        }

        boolean t() {
            return (this.j & 4) != 0;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder((getClass().isAnonymousClass() ? "ViewHolder" : getClass().getSimpleName()) + "{" + Integer.toHexString(hashCode()) + " position=" + this.f1094c + " id=" + this.e + ", oldPos=" + this.d + ", pLpos:" + this.g);
            if (w()) {
                sb.append(" scrap ");
                sb.append(this.o ? "[changeScrap]" : "[attachedScrap]");
            }
            if (t()) {
                sb.append(" invalid");
            }
            if (!s()) {
                sb.append(" unbound");
            }
            if (z()) {
                sb.append(" update");
            }
            if (v()) {
                sb.append(" removed");
            }
            if (J()) {
                sb.append(" ignored");
            }
            if (x()) {
                sb.append(" tmpDetached");
            }
            if (!u()) {
                sb.append(" not recyclable(" + this.m + ")");
            }
            if (q()) {
                sb.append(" undefined adapter position");
            }
            if (this.f1092a.getParent() == null) {
                sb.append(" no parent");
            }
            sb.append("}");
            return sb.toString();
        }

        public final boolean u() {
            return (this.j & 16) == 0 && !ViewCompat.y(this.f1092a);
        }

        boolean v() {
            return (this.j & 8) != 0;
        }

        boolean w() {
            return this.n != null;
        }

        boolean x() {
            return (this.j & MediaControl.ACTION_CUSTOM) != 0;
        }

        boolean y() {
            return (this.j & 2) != 0;
        }

        boolean z() {
            return (this.j & 2) != 0;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class Adapter<VH extends A> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final h f1095a = new h();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f1096b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private StateRestorationPolicy f1097c = StateRestorationPolicy.ALLOW;

        /* JADX INFO: compiled from: caradapter-bug */
        public enum StateRestorationPolicy {
            ALLOW,
            PREVENT_WHEN_EMPTY,
            PREVENT
        }

        public void A(boolean z) {
            if (i()) {
                throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
            }
            this.f1096b = z;
        }

        public void B(@NonNull i iVar) {
            this.f1095a.unregisterObserver(iVar);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void c(@NonNull VH vh, int i) {
            boolean z = vh.r == null;
            if (z) {
                vh.f1094c = i;
                if (j()) {
                    vh.e = g(i);
                }
                vh.F(1, 519);
                androidx.core.os.c.a("RV OnBindView");
            }
            vh.r = this;
            s(vh, i, vh.o());
            if (z) {
                vh.d();
                ViewGroup.LayoutParams layoutParams = vh.f1092a.getLayoutParams();
                if (layoutParams instanceof n) {
                    ((n) layoutParams).f1120c = true;
                }
                androidx.core.os.c.b();
            }
        }

        boolean d() {
            int i = g.f1108a[this.f1097c.ordinal()];
            if (i != 1) {
                return i != 2 || f() > 0;
            }
            return false;
        }

        @NonNull
        public final VH e(@NonNull ViewGroup viewGroup, int i) {
            try {
                androidx.core.os.c.a("RV CreateView");
                VH vh = (VH) t(viewGroup, i);
                if (vh.f1092a.getParent() != null) {
                    throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
                }
                vh.f = i;
                return vh;
            } finally {
                androidx.core.os.c.b();
            }
        }

        public abstract int f();

        public long g(int i) {
            return -1L;
        }

        public abstract int h(int i);

        public final boolean i() {
            return this.f1095a.a();
        }

        public final boolean j() {
            return this.f1096b;
        }

        public final void k() {
            this.f1095a.b();
        }

        public final void l(int i, @Nullable Object obj) {
            this.f1095a.d(i, 1, obj);
        }

        public final void m(int i, int i2) {
            this.f1095a.c(i, i2);
        }

        public final void n(int i, int i2, @Nullable Object obj) {
            this.f1095a.d(i, i2, obj);
        }

        public final void o(int i, int i2) {
            this.f1095a.e(i, i2);
        }

        public final void p(int i, int i2) {
            this.f1095a.f(i, i2);
        }

        public void q(@NonNull RecyclerView recyclerView) {
        }

        public abstract void r(@NonNull VH vh, int i);

        public void s(@NonNull VH vh, int i, @NonNull List<Object> list) {
            r(vh, i);
        }

        @NonNull
        public abstract VH t(@NonNull ViewGroup viewGroup, int i);

        public void u(@NonNull RecyclerView recyclerView) {
        }

        public boolean v(@NonNull VH vh) {
            return false;
        }

        public void w(@NonNull VH vh) {
        }

        public void x(@NonNull VH vh) {
        }

        public void y(@NonNull VH vh) {
        }

        public void z(@NonNull i iVar) {
            this.f1095a.registerObserver(iVar);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class EdgeEffectFactory {

        /* JADX INFO: compiled from: caradapter-bug */
        @Retention(RetentionPolicy.SOURCE)
        public @interface EdgeDirection {
        }

        @NonNull
        protected EdgeEffect a(@NonNull RecyclerView recyclerView, int i) {
            return new EdgeEffect(recyclerView.getContext());
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class ItemAnimator {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private b f1098a = null;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private ArrayList<a> f1099b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private long f1100c = 120;
        private long d = 120;
        private long e = 250;
        private long f = 250;

        /* JADX INFO: compiled from: caradapter-bug */
        @Retention(RetentionPolicy.SOURCE)
        public @interface AdapterChanges {
        }

        /* JADX INFO: compiled from: caradapter-bug */
        public interface a {
            void a();
        }

        /* JADX INFO: compiled from: caradapter-bug */
        interface b {
            void a(@NonNull A a2);
        }

        /* JADX INFO: compiled from: caradapter-bug */
        public static class c {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f1101a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public int f1102b;

            @NonNull
            public c a(@NonNull A a2) {
                b(a2, 0);
                return this;
            }

            @NonNull
            public c b(@NonNull A a2, int i) {
                View view = a2.f1092a;
                this.f1101a = view.getLeft();
                this.f1102b = view.getTop();
                view.getRight();
                view.getBottom();
                return this;
            }
        }

        static int e(A a2) {
            int i = a2.j & 14;
            if (a2.t()) {
                return 4;
            }
            if ((i & 4) != 0) {
                return i;
            }
            int iN = a2.n();
            int iJ = a2.j();
            return (iN == -1 || iJ == -1 || iN == iJ) ? i : i | 2048;
        }

        public abstract boolean a(@NonNull A a2, @Nullable c cVar, @NonNull c cVar2);

        public abstract boolean b(@NonNull A a2, @NonNull A a3, @NonNull c cVar, @NonNull c cVar2);

        public abstract boolean c(@NonNull A a2, @NonNull c cVar, @Nullable c cVar2);

        public abstract boolean d(@NonNull A a2, @NonNull c cVar, @NonNull c cVar2);

        public abstract boolean f(@NonNull A a2);

        public boolean g(@NonNull A a2, @NonNull List<Object> list) {
            return f(a2);
        }

        public final void h(@NonNull A a2) {
            r(a2);
            b bVar = this.f1098a;
            if (bVar != null) {
                bVar.a(a2);
            }
        }

        public final void i() {
            int size = this.f1099b.size();
            for (int i = 0; i < size; i++) {
                this.f1099b.get(i).a();
            }
            this.f1099b.clear();
        }

        public abstract void j(@NonNull A a2);

        public abstract void k();

        public long l() {
            return this.f1100c;
        }

        public long m() {
            return this.f;
        }

        public long n() {
            return this.e;
        }

        public long o() {
            return this.d;
        }

        public abstract boolean p();

        @NonNull
        public c q() {
            return new c();
        }

        public void r(@NonNull A a2) {
        }

        @NonNull
        public c s(@NonNull x xVar, @NonNull A a2) {
            c cVarQ = q();
            cVarQ.a(a2);
            return cVarQ;
        }

        @NonNull
        public c t(@NonNull x xVar, @NonNull A a2, int i, @NonNull List<Object> list) {
            c cVarQ = q();
            cVarQ.a(a2);
            return cVarQ;
        }

        public abstract void u();

        void v(b bVar) {
            this.f1098a = bVar;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface Orientation {
    }

    /* JADX INFO: renamed from: androidx.recyclerview.widget.RecyclerView$a, reason: case insensitive filesystem */
    /* JADX INFO: compiled from: caradapter-bug */
    class RunnableC0084a implements Runnable {
        RunnableC0084a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            RecyclerView recyclerView = RecyclerView.this;
            if (!recyclerView.mFirstLayoutComplete || recyclerView.isLayoutRequested()) {
                return;
            }
            RecyclerView recyclerView2 = RecyclerView.this;
            if (!recyclerView2.s) {
                recyclerView2.requestLayout();
            } else if (recyclerView2.x) {
                recyclerView2.w = true;
            } else {
                recyclerView2.t();
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ItemAnimator itemAnimator = RecyclerView.this.M;
            if (itemAnimator != null) {
                itemAnimator.u();
            }
            RecyclerView.this.n0 = false;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class c implements Interpolator {
        c() {
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class d implements o.b {
        d() {
        }

        @Override // androidx.recyclerview.widget.o.b
        public void a(A a2) {
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.mLayout.j1(a2.f1092a, recyclerView.d);
        }

        @Override // androidx.recyclerview.widget.o.b
        public void b(A a2, ItemAnimator.c cVar, ItemAnimator.c cVar2) {
            RecyclerView.this.k(a2, cVar, cVar2);
        }

        @Override // androidx.recyclerview.widget.o.b
        public void c(A a2, @NonNull ItemAnimator.c cVar, @Nullable ItemAnimator.c cVar2) {
            RecyclerView.this.d.G(a2);
            RecyclerView.this.m(a2, cVar, cVar2);
        }

        @Override // androidx.recyclerview.widget.o.b
        public void d(A a2, @NonNull ItemAnimator.c cVar, @NonNull ItemAnimator.c cVar2) {
            a2.G(false);
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.D) {
                if (recyclerView.M.b(a2, a2, cVar, cVar2)) {
                    RecyclerView.this.P0();
                }
            } else if (recyclerView.M.d(a2, cVar, cVar2)) {
                RecyclerView.this.P0();
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class e implements d.b {
        e() {
        }

        @Override // androidx.recyclerview.widget.d.b
        public View a(int i) {
            return RecyclerView.this.getChildAt(i);
        }

        @Override // androidx.recyclerview.widget.d.b
        public void b(View view) {
            A aF0 = RecyclerView.f0(view);
            if (aF0 != null) {
                aF0.B(RecyclerView.this);
            }
        }

        @Override // androidx.recyclerview.widget.d.b
        public void c(int i) {
            View childAt = RecyclerView.this.getChildAt(i);
            if (childAt != null) {
                RecyclerView.this.y(childAt);
                childAt.clearAnimation();
            }
            RecyclerView.this.removeViewAt(i);
        }

        @Override // androidx.recyclerview.widget.d.b
        public void d() {
            int iK = k();
            for (int i = 0; i < iK; i++) {
                View viewA = a(i);
                RecyclerView.this.y(viewA);
                viewA.clearAnimation();
            }
            RecyclerView.this.removeAllViews();
        }

        @Override // androidx.recyclerview.widget.d.b
        public A e(View view) {
            return RecyclerView.f0(view);
        }

        @Override // androidx.recyclerview.widget.d.b
        public void f(int i) {
            A aF0;
            View viewA = a(i);
            if (viewA != null && (aF0 = RecyclerView.f0(viewA)) != null) {
                if (aF0.x() && !aF0.J()) {
                    throw new IllegalArgumentException("called detach on an already detached child " + aF0 + RecyclerView.this.O());
                }
                aF0.b(MediaControl.ACTION_CUSTOM);
            }
            RecyclerView.this.detachViewFromParent(i);
        }

        @Override // androidx.recyclerview.widget.d.b
        public void g(View view) {
            A aF0 = RecyclerView.f0(view);
            if (aF0 != null) {
                aF0.C(RecyclerView.this);
            }
        }

        @Override // androidx.recyclerview.widget.d.b
        public void h(View view, int i, ViewGroup.LayoutParams layoutParams) {
            A aF0 = RecyclerView.f0(view);
            if (aF0 != null) {
                if (!aF0.x() && !aF0.J()) {
                    throw new IllegalArgumentException("Called attach on a child which is not detached: " + aF0 + RecyclerView.this.O());
                }
                aF0.f();
            }
            RecyclerView.this.attachViewToParent(view, i, layoutParams);
        }

        @Override // androidx.recyclerview.widget.d.b
        public void i(View view, int i) {
            RecyclerView.this.addView(view, i);
            RecyclerView.this.x(view);
        }

        @Override // androidx.recyclerview.widget.d.b
        public int j(View view) {
            return RecyclerView.this.indexOfChild(view);
        }

        @Override // androidx.recyclerview.widget.d.b
        public int k() {
            return RecyclerView.this.getChildCount();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class f implements a.InterfaceC0039a {
        f() {
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0039a
        public void a(int i, int i2) {
            RecyclerView.this.F0(i, i2);
            RecyclerView.this.k0 = true;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0039a
        public void b(a.b bVar) {
            i(bVar);
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0039a
        public A c(int i) {
            A aY = RecyclerView.this.Y(i, true);
            if (aY == null || RecyclerView.this.g.n(aY.f1092a)) {
                return null;
            }
            return aY;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0039a
        public void d(int i, int i2) {
            RecyclerView.this.G0(i, i2, true);
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.k0 = true;
            recyclerView.h0.d += i2;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0039a
        public void e(int i, int i2) {
            RecyclerView.this.G0(i, i2, false);
            RecyclerView.this.k0 = true;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0039a
        public void f(a.b bVar) {
            i(bVar);
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0039a
        public void g(int i, int i2) {
            RecyclerView.this.E0(i, i2);
            RecyclerView.this.k0 = true;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0039a
        public void h(int i, int i2, Object obj) {
            RecyclerView.this.B1(i, i2, obj);
            RecyclerView.this.l0 = true;
        }

        void i(a.b bVar) {
            int i = bVar.f1153a;
            if (i == 1) {
                RecyclerView recyclerView = RecyclerView.this;
                recyclerView.mLayout.P0(recyclerView, bVar.f1154b, bVar.d);
                return;
            }
            if (i == 2) {
                RecyclerView recyclerView2 = RecyclerView.this;
                recyclerView2.mLayout.S0(recyclerView2, bVar.f1154b, bVar.d);
            } else if (i == 4) {
                RecyclerView recyclerView3 = RecyclerView.this;
                recyclerView3.mLayout.U0(recyclerView3, bVar.f1154b, bVar.d, bVar.f1155c);
            } else {
                if (i != 8) {
                    return;
                }
                RecyclerView recyclerView4 = RecyclerView.this;
                recyclerView4.mLayout.R0(recyclerView4, bVar.f1154b, bVar.d, 1);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static /* synthetic */ class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1108a;

        static {
            int[] iArr = new int[Adapter.StateRestorationPolicy.values().length];
            f1108a = iArr;
            try {
                iArr[Adapter.StateRestorationPolicy.PREVENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1108a[Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static class h extends Observable<i> {
        h() {
        }

        public boolean a() {
            return !((Observable) this).mObservers.isEmpty();
        }

        public void b() {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((i) ((Observable) this).mObservers.get(size)).a();
            }
        }

        public void c(int i, int i2) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((i) ((Observable) this).mObservers.get(size)).e(i, i2, 1);
            }
        }

        public void d(int i, int i2, @Nullable Object obj) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((i) ((Observable) this).mObservers.get(size)).c(i, i2, obj);
            }
        }

        public void e(int i, int i2) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((i) ((Observable) this).mObservers.get(size)).d(i, i2);
            }
        }

        public void f(int i, int i2) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((i) ((Observable) this).mObservers.get(size)).f(i, i2);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class i {
        public void a() {
        }

        public void b(int i, int i2) {
        }

        public void c(int i, int i2, @Nullable Object obj) {
            b(i, i2);
        }

        public void d(int i, int i2) {
        }

        public void e(int i, int i2, int i3) {
        }

        public void f(int i, int i2) {
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface j {
        int a(int i, int i2);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class k implements ItemAnimator.b {
        k() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator.b
        public void a(A a2) {
            a2.G(true);
            if (a2.h != null && a2.i == null) {
                a2.h = null;
            }
            a2.i = null;
            if (a2.I() || RecyclerView.this.Y0(a2.f1092a) || !a2.x()) {
                return;
            }
            RecyclerView.this.removeDetachedView(a2.f1092a, false);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class l {
        @Deprecated
        public void d(@NonNull Rect rect, int i, @NonNull RecyclerView recyclerView) {
            rect.set(0, 0, 0, 0);
        }

        public void e(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull x xVar) {
            d(rect, ((n) view.getLayoutParams()).a(), recyclerView);
        }

        @Deprecated
        public void f(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView) {
        }

        public void g(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull x xVar) {
            f(canvas, recyclerView);
        }

        @Deprecated
        public void h(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView) {
        }

        public void i(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull x xVar) {
            h(canvas, recyclerView);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        androidx.recyclerview.widget.d f1110a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        RecyclerView f1111b;

        @Nullable
        w g;
        int m;
        boolean n;
        private int o;
        private int p;
        private int q;
        private int r;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final ViewBoundsCheck.b f1112c = new a();
        private final ViewBoundsCheck.b d = new b();
        ViewBoundsCheck e = new ViewBoundsCheck(this.f1112c);
        ViewBoundsCheck f = new ViewBoundsCheck(this.d);
        boolean h = false;
        boolean i = false;
        boolean j = false;
        private boolean k = true;
        private boolean l = true;

        /* JADX INFO: compiled from: caradapter-bug */
        class a implements ViewBoundsCheck.b {
            a() {
            }

            @Override // androidx.recyclerview.widget.ViewBoundsCheck.b
            public View a(int i) {
                return m.this.H(i);
            }

            @Override // androidx.recyclerview.widget.ViewBoundsCheck.b
            public int b() {
                return m.this.m0() - m.this.d0();
            }

            @Override // androidx.recyclerview.widget.ViewBoundsCheck.b
            public int c() {
                return m.this.c0();
            }

            @Override // androidx.recyclerview.widget.ViewBoundsCheck.b
            public int d(View view) {
                return m.this.S(view) + ((ViewGroup.MarginLayoutParams) ((n) view.getLayoutParams())).rightMargin;
            }

            @Override // androidx.recyclerview.widget.ViewBoundsCheck.b
            public int e(View view) {
                return m.this.P(view) - ((ViewGroup.MarginLayoutParams) ((n) view.getLayoutParams())).leftMargin;
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        class b implements ViewBoundsCheck.b {
            b() {
            }

            @Override // androidx.recyclerview.widget.ViewBoundsCheck.b
            public View a(int i) {
                return m.this.H(i);
            }

            @Override // androidx.recyclerview.widget.ViewBoundsCheck.b
            public int b() {
                return m.this.V() - m.this.b0();
            }

            @Override // androidx.recyclerview.widget.ViewBoundsCheck.b
            public int c() {
                return m.this.e0();
            }

            @Override // androidx.recyclerview.widget.ViewBoundsCheck.b
            public int d(View view) {
                return m.this.N(view) + ((ViewGroup.MarginLayoutParams) ((n) view.getLayoutParams())).bottomMargin;
            }

            @Override // androidx.recyclerview.widget.ViewBoundsCheck.b
            public int e(View view) {
                return m.this.T(view) - ((ViewGroup.MarginLayoutParams) ((n) view.getLayoutParams())).topMargin;
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        public interface c {
            void a(int i, int i2);
        }

        /* JADX INFO: compiled from: caradapter-bug */
        public static class d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f1115a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public int f1116b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public boolean f1117c;
            public boolean d;
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x0020  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x002f  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static int J(int r4, int r5, int r6, int r7, boolean r8) {
            /*
                int r4 = r4 - r6
                r6 = 0
                int r4 = java.lang.Math.max(r6, r4)
                r0 = -2
                r1 = -1
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = 1073741824(0x40000000, float:2.0)
                if (r8 == 0) goto L1a
                if (r7 < 0) goto L11
                goto L1c
            L11:
                if (r7 != r1) goto L2f
                if (r5 == r2) goto L20
                if (r5 == 0) goto L2f
                if (r5 == r3) goto L20
                goto L2f
            L1a:
                if (r7 < 0) goto L1e
            L1c:
                r5 = r3
                goto L31
            L1e:
                if (r7 != r1) goto L22
            L20:
                r7 = r4
                goto L31
            L22:
                if (r7 != r0) goto L2f
                if (r5 == r2) goto L2c
                if (r5 != r3) goto L29
                goto L2c
            L29:
                r7 = r4
                r5 = r6
                goto L31
            L2c:
                r7 = r4
                r5 = r2
                goto L31
            L2f:
                r5 = r6
                r7 = r5
            L31:
                int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r7, r5)
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.m.J(int, int, int, int, boolean):int");
        }

        private int[] K(View view, Rect rect) {
            int[] iArr = new int[2];
            int iC0 = c0();
            int iE0 = e0();
            int iM0 = m0() - d0();
            int iV = V() - b0();
            int left = (view.getLeft() + rect.left) - view.getScrollX();
            int top = (view.getTop() + rect.top) - view.getScrollY();
            int iWidth = rect.width() + left;
            int iHeight = rect.height() + top;
            int i = left - iC0;
            int iMin = Math.min(0, i);
            int i2 = top - iE0;
            int iMin2 = Math.min(0, i2);
            int i3 = iWidth - iM0;
            int iMax = Math.max(0, i3);
            int iMax2 = Math.max(0, iHeight - iV);
            if (X() != 1) {
                if (iMin == 0) {
                    iMin = Math.min(i, iMax);
                }
                iMax = iMin;
            } else if (iMax == 0) {
                iMax = Math.max(iMin, i3);
            }
            if (iMin2 == 0) {
                iMin2 = Math.min(i2, iMax2);
            }
            iArr[0] = iMax;
            iArr[1] = iMin2;
            return iArr;
        }

        private void e(View view, int i, boolean z) {
            A aF0 = RecyclerView.f0(view);
            if (z || aF0.v()) {
                this.f1111b.h.b(aF0);
            } else {
                this.f1111b.h.p(aF0);
            }
            n nVar = (n) view.getLayoutParams();
            if (aF0.L() || aF0.w()) {
                if (aF0.w()) {
                    aF0.K();
                } else {
                    aF0.e();
                }
                this.f1110a.c(view, i, view.getLayoutParams(), false);
            } else if (view.getParent() == this.f1111b) {
                int iM = this.f1110a.m(view);
                if (i == -1) {
                    i = this.f1110a.g();
                }
                if (iM == -1) {
                    throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.f1111b.indexOfChild(view) + this.f1111b.O());
                }
                if (iM != i) {
                    this.f1111b.mLayout.z0(iM, i);
                }
            } else {
                this.f1110a.a(view, i, false);
                nVar.f1120c = true;
                w wVar = this.g;
                if (wVar != null && wVar.c()) {
                    this.g.e(view);
                }
            }
            if (nVar.d) {
                aF0.f1092a.invalidate();
                nVar.d = false;
            }
        }

        public static d g0(@NonNull Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
            d dVar = new d();
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RecyclerView, i, i2);
            dVar.f1115a = typedArrayObtainStyledAttributes.getInt(R$styleable.RecyclerView_android_orientation, 1);
            dVar.f1116b = typedArrayObtainStyledAttributes.getInt(R$styleable.RecyclerView_spanCount, 1);
            dVar.f1117c = typedArrayObtainStyledAttributes.getBoolean(R$styleable.RecyclerView_reverseLayout, false);
            dVar.d = typedArrayObtainStyledAttributes.getBoolean(R$styleable.RecyclerView_stackFromEnd, false);
            typedArrayObtainStyledAttributes.recycle();
            return dVar;
        }

        public static int m(int i, int i2, int i3) {
            int mode = View.MeasureSpec.getMode(i);
            int size = View.MeasureSpec.getSize(i);
            return mode != Integer.MIN_VALUE ? mode != 1073741824 ? Math.max(i2, i3) : size : Math.min(size, Math.max(i2, i3));
        }

        private boolean r0(RecyclerView recyclerView, int i, int i2) {
            View focusedChild = recyclerView.getFocusedChild();
            if (focusedChild == null) {
                return false;
            }
            int iC0 = c0();
            int iE0 = e0();
            int iM0 = m0() - d0();
            int iV = V() - b0();
            Rect rect = this.f1111b.k;
            O(focusedChild, rect);
            return rect.left - i < iM0 && rect.right - i > iC0 && rect.top - i2 < iV && rect.bottom - i2 > iE0;
        }

        private void s1(t tVar, int i, View view) {
            A aF0 = RecyclerView.f0(view);
            if (aF0.J()) {
                return;
            }
            if (aF0.t() && !aF0.v() && !this.f1111b.n.j()) {
                n1(i);
                tVar.C(aF0);
            } else {
                w(i);
                tVar.D(view);
                this.f1111b.h.k(aF0);
            }
        }

        private static boolean u0(int i, int i2, int i3) {
            int mode = View.MeasureSpec.getMode(i2);
            int size = View.MeasureSpec.getSize(i2);
            if (i3 > 0 && i != i3) {
                return false;
            }
            if (mode == Integer.MIN_VALUE) {
                return size >= i;
            }
            if (mode != 0) {
                return mode == 1073741824 && size == i;
            }
            return true;
        }

        private void x(int i, @NonNull View view) {
            this.f1110a.d(i);
        }

        @Nullable
        public View A(@NonNull View view) {
            View viewQ;
            RecyclerView recyclerView = this.f1111b;
            if (recyclerView == null || (viewQ = recyclerView.Q(view)) == null || this.f1110a.n(viewQ)) {
                return null;
            }
            return viewQ;
        }

        public void A0(@Px int i) {
            RecyclerView recyclerView = this.f1111b;
            if (recyclerView != null) {
                recyclerView.C0(i);
            }
        }

        void A1(RecyclerView recyclerView) {
            if (recyclerView == null) {
                this.f1111b = null;
                this.f1110a = null;
                this.q = 0;
                this.r = 0;
            } else {
                this.f1111b = recyclerView;
                this.f1110a = recyclerView.g;
                this.q = recyclerView.getWidth();
                this.r = recyclerView.getHeight();
            }
            this.o = 1073741824;
            this.p = 1073741824;
        }

        @Nullable
        public View B(int i) {
            int I = I();
            for (int i2 = 0; i2 < I; i2++) {
                View viewH = H(i2);
                A aF0 = RecyclerView.f0(viewH);
                if (aF0 != null && aF0.m() == i && !aF0.J() && (this.f1111b.h0.e() || !aF0.v())) {
                    return viewH;
                }
            }
            return null;
        }

        public void B0(@Px int i) {
            RecyclerView recyclerView = this.f1111b;
            if (recyclerView != null) {
                recyclerView.D0(i);
            }
        }

        boolean B1(View view, int i, int i2, n nVar) {
            return (!view.isLayoutRequested() && this.k && u0(view.getWidth(), i, ((ViewGroup.MarginLayoutParams) nVar).width) && u0(view.getHeight(), i2, ((ViewGroup.MarginLayoutParams) nVar).height)) ? false : true;
        }

        public abstract n C();

        public void C0(@Nullable Adapter adapter, @Nullable Adapter adapter2) {
        }

        boolean C1() {
            return false;
        }

        public n D(Context context, AttributeSet attributeSet) {
            return new n(context, attributeSet);
        }

        public boolean D0(@NonNull RecyclerView recyclerView, @NonNull ArrayList<View> arrayList, int i, int i2) {
            return false;
        }

        boolean D1(View view, int i, int i2, n nVar) {
            return (this.k && u0(view.getMeasuredWidth(), i, ((ViewGroup.MarginLayoutParams) nVar).width) && u0(view.getMeasuredHeight(), i2, ((ViewGroup.MarginLayoutParams) nVar).height)) ? false : true;
        }

        public n E(ViewGroup.LayoutParams layoutParams) {
            return layoutParams instanceof n ? new n((n) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new n((ViewGroup.MarginLayoutParams) layoutParams) : new n(layoutParams);
        }

        @CallSuper
        public void E0(RecyclerView recyclerView) {
        }

        void E1() {
            w wVar = this.g;
            if (wVar == null) {
                return;
            }
            wVar.g();
            throw null;
        }

        public int F() {
            return -1;
        }

        @Deprecated
        public void F0(RecyclerView recyclerView) {
        }

        public boolean F1() {
            return false;
        }

        public int G(@NonNull View view) {
            return ((n) view.getLayoutParams()).f1119b.bottom;
        }

        @CallSuper
        public void G0(RecyclerView recyclerView, t tVar) {
            F0(recyclerView);
        }

        @Nullable
        public View H(int i) {
            androidx.recyclerview.widget.d dVar = this.f1110a;
            if (dVar != null) {
                return dVar.f(i);
            }
            return null;
        }

        @Nullable
        public View H0(@NonNull View view, int i, @NonNull t tVar, @NonNull x xVar) {
            return null;
        }

        public int I() {
            androidx.recyclerview.widget.d dVar = this.f1110a;
            if (dVar != null) {
                return dVar.g();
            }
            return 0;
        }

        public void I0(@NonNull AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.f1111b;
            J0(recyclerView.d, recyclerView.h0, accessibilityEvent);
        }

        public void J0(@NonNull t tVar, @NonNull x xVar, @NonNull AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.f1111b;
            if (recyclerView == null || accessibilityEvent == null) {
                return;
            }
            boolean z = true;
            if (!recyclerView.canScrollVertically(1) && !this.f1111b.canScrollVertically(-1) && !this.f1111b.canScrollHorizontally(-1) && !this.f1111b.canScrollHorizontally(1)) {
                z = false;
            }
            accessibilityEvent.setScrollable(z);
            Adapter adapter = this.f1111b.n;
            if (adapter != null) {
                accessibilityEvent.setItemCount(adapter.f());
            }
        }

        void K0(androidx.core.view.accessibility.b bVar) {
            RecyclerView recyclerView = this.f1111b;
            L0(recyclerView.d, recyclerView.h0, bVar);
        }

        public boolean L() {
            RecyclerView recyclerView = this.f1111b;
            return recyclerView != null && recyclerView.i;
        }

        public void L0(@NonNull t tVar, @NonNull x xVar, @NonNull androidx.core.view.accessibility.b bVar) {
            if (this.f1111b.canScrollVertically(-1) || this.f1111b.canScrollHorizontally(-1)) {
                bVar.a(8192);
                bVar.P(true);
            }
            if (this.f1111b.canScrollVertically(1) || this.f1111b.canScrollHorizontally(1)) {
                bVar.a(4096);
                bVar.P(true);
            }
            bVar.K(b.C0016b.a(i0(tVar, xVar), M(tVar, xVar), t0(tVar, xVar), j0(tVar, xVar)));
        }

        public int M(@NonNull t tVar, @NonNull x xVar) {
            return -1;
        }

        void M0(View view, androidx.core.view.accessibility.b bVar) {
            A aF0 = RecyclerView.f0(view);
            if (aF0 == null || aF0.v() || this.f1110a.n(aF0.f1092a)) {
                return;
            }
            RecyclerView recyclerView = this.f1111b;
            N0(recyclerView.d, recyclerView.h0, view, bVar);
        }

        public int N(@NonNull View view) {
            return view.getBottom() + G(view);
        }

        public void N0(@NonNull t tVar, @NonNull x xVar, @NonNull View view, @NonNull androidx.core.view.accessibility.b bVar) {
        }

        public void O(@NonNull View view, @NonNull Rect rect) {
            RecyclerView.g0(view, rect);
        }

        @Nullable
        public View O0(@NonNull View view, int i) {
            return null;
        }

        public int P(@NonNull View view) {
            return view.getLeft() - Y(view);
        }

        public void P0(@NonNull RecyclerView recyclerView, int i, int i2) {
        }

        public int Q(@NonNull View view) {
            Rect rect = ((n) view.getLayoutParams()).f1119b;
            return view.getMeasuredHeight() + rect.top + rect.bottom;
        }

        public void Q0(@NonNull RecyclerView recyclerView) {
        }

        public int R(@NonNull View view) {
            Rect rect = ((n) view.getLayoutParams()).f1119b;
            return view.getMeasuredWidth() + rect.left + rect.right;
        }

        public void R0(@NonNull RecyclerView recyclerView, int i, int i2, int i3) {
        }

        public int S(@NonNull View view) {
            return view.getRight() + h0(view);
        }

        public void S0(@NonNull RecyclerView recyclerView, int i, int i2) {
        }

        public int T(@NonNull View view) {
            return view.getTop() - k0(view);
        }

        public void T0(@NonNull RecyclerView recyclerView, int i, int i2) {
        }

        @Nullable
        public View U() {
            View focusedChild;
            RecyclerView recyclerView = this.f1111b;
            if (recyclerView == null || (focusedChild = recyclerView.getFocusedChild()) == null || this.f1110a.n(focusedChild)) {
                return null;
            }
            return focusedChild;
        }

        public void U0(@NonNull RecyclerView recyclerView, int i, int i2, @Nullable Object obj) {
            T0(recyclerView, i, i2);
        }

        @Px
        public int V() {
            return this.r;
        }

        public void V0(t tVar, x xVar) {
            Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
        }

        public int W() {
            return this.p;
        }

        public void W0(x xVar) {
        }

        public int X() {
            return ViewCompat.q(this.f1111b);
        }

        public void X0(@NonNull t tVar, @NonNull x xVar, int i, int i2) {
            this.f1111b.v(i, i2);
        }

        public int Y(@NonNull View view) {
            return ((n) view.getLayoutParams()).f1119b.left;
        }

        @Deprecated
        public boolean Y0(@NonNull RecyclerView recyclerView, @NonNull View view, @Nullable View view2) {
            return v0() || recyclerView.x0();
        }

        @Px
        public int Z() {
            return ViewCompat.r(this.f1111b);
        }

        public boolean Z0(@NonNull RecyclerView recyclerView, @NonNull x xVar, @NonNull View view, @Nullable View view2) {
            return Y0(recyclerView, view, view2);
        }

        public void a(View view) {
            b(view, -1);
        }

        @Px
        public int a0() {
            return ViewCompat.s(this.f1111b);
        }

        public void a1(Parcelable parcelable) {
        }

        public void b(View view, int i) {
            e(view, i, true);
        }

        @Px
        public int b0() {
            RecyclerView recyclerView = this.f1111b;
            if (recyclerView != null) {
                return recyclerView.getPaddingBottom();
            }
            return 0;
        }

        @Nullable
        public Parcelable b1() {
            return null;
        }

        public void c(View view) {
            d(view, -1);
        }

        @Px
        public int c0() {
            RecyclerView recyclerView = this.f1111b;
            if (recyclerView != null) {
                return recyclerView.getPaddingLeft();
            }
            return 0;
        }

        public void c1(int i) {
        }

        public void d(View view, int i) {
            e(view, i, false);
        }

        @Px
        public int d0() {
            RecyclerView recyclerView = this.f1111b;
            if (recyclerView != null) {
                return recyclerView.getPaddingRight();
            }
            return 0;
        }

        boolean d1(int i, @Nullable Bundle bundle) {
            RecyclerView recyclerView = this.f1111b;
            return e1(recyclerView.d, recyclerView.h0, i, bundle);
        }

        @Px
        public int e0() {
            RecyclerView recyclerView = this.f1111b;
            if (recyclerView != null) {
                return recyclerView.getPaddingTop();
            }
            return 0;
        }

        public boolean e1(@NonNull t tVar, @NonNull x xVar, int i, @Nullable Bundle bundle) {
            int iV;
            int iM0;
            int i2;
            int i3;
            RecyclerView recyclerView = this.f1111b;
            if (recyclerView == null) {
                return false;
            }
            if (i == 4096) {
                iV = recyclerView.canScrollVertically(1) ? (V() - e0()) - b0() : 0;
                if (this.f1111b.canScrollHorizontally(1)) {
                    iM0 = (m0() - c0()) - d0();
                    i2 = iV;
                    i3 = iM0;
                }
                i2 = iV;
                i3 = 0;
            } else if (i != 8192) {
                i3 = 0;
                i2 = 0;
            } else {
                iV = recyclerView.canScrollVertically(-1) ? -((V() - e0()) - b0()) : 0;
                if (this.f1111b.canScrollHorizontally(-1)) {
                    iM0 = -((m0() - c0()) - d0());
                    i2 = iV;
                    i3 = iM0;
                }
                i2 = iV;
                i3 = 0;
            }
            if (i2 == 0 && i3 == 0) {
                return false;
            }
            this.f1111b.u1(i3, i2, null, Integer.MIN_VALUE, true);
            return true;
        }

        public void f(String str) {
            RecyclerView recyclerView = this.f1111b;
            if (recyclerView != null) {
                recyclerView.n(str);
            }
        }

        public int f0(@NonNull View view) {
            return ((n) view.getLayoutParams()).a();
        }

        boolean f1(@NonNull View view, int i, @Nullable Bundle bundle) {
            RecyclerView recyclerView = this.f1111b;
            return g1(recyclerView.d, recyclerView.h0, view, i, bundle);
        }

        public void g(@NonNull View view, int i) {
            h(view, i, (n) view.getLayoutParams());
        }

        public boolean g1(@NonNull t tVar, @NonNull x xVar, @NonNull View view, int i, @Nullable Bundle bundle) {
            return false;
        }

        public void h(@NonNull View view, int i, n nVar) {
            A aF0 = RecyclerView.f0(view);
            if (aF0.v()) {
                this.f1111b.h.b(aF0);
            } else {
                this.f1111b.h.p(aF0);
            }
            this.f1110a.c(view, i, nVar, aF0.v());
        }

        public int h0(@NonNull View view) {
            return ((n) view.getLayoutParams()).f1119b.right;
        }

        public void h1(@NonNull t tVar) {
            for (int I = I() - 1; I >= 0; I--) {
                if (!RecyclerView.f0(H(I)).J()) {
                    k1(I, tVar);
                }
            }
        }

        public void i(@NonNull View view, @NonNull Rect rect) {
            RecyclerView recyclerView = this.f1111b;
            if (recyclerView == null) {
                rect.set(0, 0, 0, 0);
            } else {
                rect.set(recyclerView.j0(view));
            }
        }

        public int i0(@NonNull t tVar, @NonNull x xVar) {
            return -1;
        }

        void i1(t tVar) {
            int iJ = tVar.j();
            for (int i = iJ - 1; i >= 0; i--) {
                View viewN = tVar.n(i);
                A aF0 = RecyclerView.f0(viewN);
                if (!aF0.J()) {
                    aF0.G(false);
                    if (aF0.x()) {
                        this.f1111b.removeDetachedView(viewN, false);
                    }
                    ItemAnimator itemAnimator = this.f1111b.M;
                    if (itemAnimator != null) {
                        itemAnimator.j(aF0);
                    }
                    aF0.G(true);
                    tVar.y(viewN);
                }
            }
            tVar.e();
            if (iJ > 0) {
                this.f1111b.invalidate();
            }
        }

        public boolean j() {
            return false;
        }

        public int j0(@NonNull t tVar, @NonNull x xVar) {
            return 0;
        }

        public void j1(@NonNull View view, @NonNull t tVar) {
            m1(view);
            tVar.B(view);
        }

        public boolean k() {
            return false;
        }

        public int k0(@NonNull View view) {
            return ((n) view.getLayoutParams()).f1119b.top;
        }

        public void k1(int i, @NonNull t tVar) {
            View viewH = H(i);
            n1(i);
            tVar.B(viewH);
        }

        public boolean l(n nVar) {
            return nVar != null;
        }

        public void l0(@NonNull View view, boolean z, @NonNull Rect rect) {
            Matrix matrix;
            if (z) {
                Rect rect2 = ((n) view.getLayoutParams()).f1119b;
                rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
            } else {
                rect.set(0, 0, view.getWidth(), view.getHeight());
            }
            if (this.f1111b != null && (matrix = view.getMatrix()) != null && !matrix.isIdentity()) {
                RectF rectF = this.f1111b.m;
                rectF.set(rect);
                matrix.mapRect(rectF);
                rect.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
            }
            rect.offset(view.getLeft(), view.getTop());
        }

        public boolean l1(Runnable runnable) {
            RecyclerView recyclerView = this.f1111b;
            if (recyclerView != null) {
                return recyclerView.removeCallbacks(runnable);
            }
            return false;
        }

        @Px
        public int m0() {
            return this.q;
        }

        public void m1(View view) {
            this.f1110a.p(view);
        }

        public void n(int i, int i2, x xVar, c cVar) {
        }

        public int n0() {
            return this.o;
        }

        public void n1(int i) {
            if (H(i) != null) {
                this.f1110a.q(i);
            }
        }

        public void o(int i, c cVar) {
        }

        boolean o0() {
            int I = I();
            for (int i = 0; i < I; i++) {
                ViewGroup.LayoutParams layoutParams = H(i).getLayoutParams();
                if (layoutParams.width < 0 && layoutParams.height < 0) {
                    return true;
                }
            }
            return false;
        }

        public boolean o1(@NonNull RecyclerView recyclerView, @NonNull View view, @NonNull Rect rect, boolean z) {
            return p1(recyclerView, view, rect, z, false);
        }

        public int p(@NonNull x xVar) {
            return 0;
        }

        public boolean p0() {
            return this.i;
        }

        public boolean p1(@NonNull RecyclerView recyclerView, @NonNull View view, @NonNull Rect rect, boolean z, boolean z2) {
            int[] iArrK = K(view, rect);
            int i = iArrK[0];
            int i2 = iArrK[1];
            if ((z2 && !r0(recyclerView, i, i2)) || (i == 0 && i2 == 0)) {
                return false;
            }
            if (z) {
                recyclerView.scrollBy(i, i2);
            } else {
                recyclerView.r1(i, i2);
            }
            return true;
        }

        public int q(@NonNull x xVar) {
            return 0;
        }

        public boolean q0() {
            return this.j;
        }

        public void q1() {
            RecyclerView recyclerView = this.f1111b;
            if (recyclerView != null) {
                recyclerView.requestLayout();
            }
        }

        public int r(@NonNull x xVar) {
            return 0;
        }

        public void r1() {
            this.h = true;
        }

        public int s(@NonNull x xVar) {
            return 0;
        }

        public final boolean s0() {
            return this.l;
        }

        public int t(@NonNull x xVar) {
            return 0;
        }

        public boolean t0(@NonNull t tVar, @NonNull x xVar) {
            return false;
        }

        public int t1(int i, t tVar, x xVar) {
            return 0;
        }

        public int u(@NonNull x xVar) {
            return 0;
        }

        public int u1(int i, t tVar, x xVar) {
            return 0;
        }

        public void v(@NonNull t tVar) {
            for (int I = I() - 1; I >= 0; I--) {
                s1(tVar, I, H(I));
            }
        }

        public boolean v0() {
            w wVar = this.g;
            return wVar != null && wVar.c();
        }

        void v1(RecyclerView recyclerView) {
            w1(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 1073741824));
        }

        public void w(int i) {
            x(i, H(i));
        }

        public boolean w0(@NonNull View view, boolean z, boolean z2) {
            boolean z3 = this.e.b(view, 24579) && this.f.b(view, 24579);
            return z ? z3 : !z3;
        }

        void w1(int i, int i2) {
            this.q = View.MeasureSpec.getSize(i);
            int mode = View.MeasureSpec.getMode(i);
            this.o = mode;
            if (mode == 0 && !RecyclerView.z0) {
                this.q = 0;
            }
            this.r = View.MeasureSpec.getSize(i2);
            int mode2 = View.MeasureSpec.getMode(i2);
            this.p = mode2;
            if (mode2 != 0 || RecyclerView.z0) {
                return;
            }
            this.r = 0;
        }

        public void x0(@NonNull View view, int i, int i2, int i3, int i4) {
            n nVar = (n) view.getLayoutParams();
            Rect rect = nVar.f1119b;
            view.layout(i + rect.left + ((ViewGroup.MarginLayoutParams) nVar).leftMargin, i2 + rect.top + ((ViewGroup.MarginLayoutParams) nVar).topMargin, (i3 - rect.right) - ((ViewGroup.MarginLayoutParams) nVar).rightMargin, (i4 - rect.bottom) - ((ViewGroup.MarginLayoutParams) nVar).bottomMargin);
        }

        public void x1(int i, int i2) {
            this.f1111b.setMeasuredDimension(i, i2);
        }

        void y(RecyclerView recyclerView) {
            this.i = true;
            E0(recyclerView);
        }

        public void y0(@NonNull View view, int i, int i2) {
            n nVar = (n) view.getLayoutParams();
            Rect rectJ0 = this.f1111b.j0(view);
            int i3 = i + rectJ0.left + rectJ0.right;
            int i4 = i2 + rectJ0.top + rectJ0.bottom;
            int iJ = J(m0(), n0(), c0() + d0() + ((ViewGroup.MarginLayoutParams) nVar).leftMargin + ((ViewGroup.MarginLayoutParams) nVar).rightMargin + i3, ((ViewGroup.MarginLayoutParams) nVar).width, j());
            int iJ2 = J(V(), W(), e0() + b0() + ((ViewGroup.MarginLayoutParams) nVar).topMargin + ((ViewGroup.MarginLayoutParams) nVar).bottomMargin + i4, ((ViewGroup.MarginLayoutParams) nVar).height, k());
            if (B1(view, iJ, iJ2, nVar)) {
                view.measure(iJ, iJ2);
            }
        }

        public void y1(Rect rect, int i, int i2) {
            x1(m(i, rect.width() + c0() + d0(), a0()), m(i2, rect.height() + e0() + b0(), Z()));
        }

        void z(RecyclerView recyclerView, t tVar) {
            this.i = false;
            G0(recyclerView, tVar);
        }

        public void z0(int i, int i2) {
            View viewH = H(i);
            if (viewH != null) {
                w(i);
                g(viewH, i2);
            } else {
                throw new IllegalArgumentException("Cannot move a child from non-existing index:" + i + this.f1111b.toString());
            }
        }

        void z1(int i, int i2) {
            int I = I();
            if (I == 0) {
                this.f1111b.v(i, i2);
                return;
            }
            int i3 = Integer.MIN_VALUE;
            int i4 = Integer.MAX_VALUE;
            int i5 = Integer.MAX_VALUE;
            int i6 = Integer.MIN_VALUE;
            for (int i7 = 0; i7 < I; i7++) {
                View viewH = H(i7);
                Rect rect = this.f1111b.k;
                O(viewH, rect);
                int i8 = rect.left;
                if (i8 < i4) {
                    i4 = i8;
                }
                int i9 = rect.right;
                if (i9 > i3) {
                    i3 = i9;
                }
                int i10 = rect.top;
                if (i10 < i5) {
                    i5 = i10;
                }
                int i11 = rect.bottom;
                if (i11 > i6) {
                    i6 = i11;
                }
            }
            this.f1111b.k.set(i4, i5, i3, i6);
            y1(this.f1111b.k, i, i2);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface o {
        void a(@NonNull View view);

        void b(@NonNull View view);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class p {
        public abstract boolean a(int i, int i2);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface q {
        boolean a(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent);

        void b(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent);

        void c(boolean z);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class r {
        public void a(@NonNull RecyclerView recyclerView, int i) {
        }

        public void b(@NonNull RecyclerView recyclerView, int i, int i2) {
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class s {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        SparseArray<a> f1121a = new SparseArray<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f1122b = 0;

        /* JADX INFO: compiled from: caradapter-bug */
        static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final ArrayList<A> f1123a = new ArrayList<>();

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            int f1124b = 5;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            long f1125c = 0;
            long d = 0;

            a() {
            }
        }

        private a g(int i) {
            a aVar = this.f1121a.get(i);
            if (aVar != null) {
                return aVar;
            }
            a aVar2 = new a();
            this.f1121a.put(i, aVar2);
            return aVar2;
        }

        void a() {
            this.f1122b++;
        }

        public void b() {
            for (int i = 0; i < this.f1121a.size(); i++) {
                this.f1121a.valueAt(i).f1123a.clear();
            }
        }

        void c() {
            this.f1122b--;
        }

        void d(int i, long j) {
            a aVarG = g(i);
            aVarG.d = j(aVarG.d, j);
        }

        void e(int i, long j) {
            a aVarG = g(i);
            aVarG.f1125c = j(aVarG.f1125c, j);
        }

        @Nullable
        public A f(int i) {
            a aVar = this.f1121a.get(i);
            if (aVar == null || aVar.f1123a.isEmpty()) {
                return null;
            }
            ArrayList<A> arrayList = aVar.f1123a;
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                if (!arrayList.get(size).r()) {
                    return arrayList.remove(size);
                }
            }
            return null;
        }

        void h(Adapter adapter, Adapter adapter2, boolean z) {
            if (adapter != null) {
                c();
            }
            if (!z && this.f1122b == 0) {
                b();
            }
            if (adapter2 != null) {
                a();
            }
        }

        public void i(A a2) {
            int iL = a2.l();
            ArrayList<A> arrayList = g(iL).f1123a;
            if (this.f1121a.get(iL).f1124b <= arrayList.size()) {
                return;
            }
            a2.D();
            arrayList.add(a2);
        }

        long j(long j, long j2) {
            return j == 0 ? j2 : ((j / 4) * 3) + (j2 / 4);
        }

        boolean k(int i, long j, long j2) {
            long j3 = g(i).d;
            return j3 == 0 || j + j3 < j2;
        }

        boolean l(int i, long j, long j2) {
            long j3 = g(i).f1125c;
            return j3 == 0 || j + j3 < j2;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public final class t {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final ArrayList<A> f1126a = new ArrayList<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        ArrayList<A> f1127b = null;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final ArrayList<A> f1128c = new ArrayList<>();
        private final List<A> d = Collections.unmodifiableList(this.f1126a);
        private int e = 2;
        int f = 2;
        s g;
        private y h;

        public t() {
        }

        private boolean E(@NonNull A a2, int i, int i2, long j) {
            a2.r = null;
            a2.q = RecyclerView.this;
            int iL = a2.l();
            long jL0 = RecyclerView.this.l0();
            if (j != Long.MAX_VALUE && !this.g.k(iL, jL0, j)) {
                return false;
            }
            RecyclerView.this.n.c(a2, i);
            this.g.d(a2.l(), RecyclerView.this.l0() - jL0);
            b(a2);
            if (!RecyclerView.this.h0.e()) {
                return true;
            }
            a2.g = i2;
            return true;
        }

        private void b(A a2) {
            if (RecyclerView.this.w0()) {
                View view = a2.f1092a;
                if (ViewCompat.o(view) == 0) {
                    ViewCompat.R(view, 1);
                }
                androidx.recyclerview.widget.l lVar = RecyclerView.this.o0;
                if (lVar == null) {
                    return;
                }
                androidx.core.view.a aVarN = lVar.n();
                if (aVarN instanceof l.a) {
                    ((l.a) aVarN).o(view);
                }
                ViewCompat.M(view, aVarN);
            }
        }

        private void q(ViewGroup viewGroup, boolean z) {
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (childAt instanceof ViewGroup) {
                    q((ViewGroup) childAt, true);
                }
            }
            if (z) {
                if (viewGroup.getVisibility() == 4) {
                    viewGroup.setVisibility(0);
                    viewGroup.setVisibility(4);
                } else {
                    int visibility = viewGroup.getVisibility();
                    viewGroup.setVisibility(4);
                    viewGroup.setVisibility(visibility);
                }
            }
        }

        private void r(A a2) {
            View view = a2.f1092a;
            if (view instanceof ViewGroup) {
                q((ViewGroup) view, false);
            }
        }

        void A(int i) {
            a(this.f1128c.get(i), true);
            this.f1128c.remove(i);
        }

        public void B(@NonNull View view) {
            A aF0 = RecyclerView.f0(view);
            if (aF0.x()) {
                RecyclerView.this.removeDetachedView(view, false);
            }
            if (aF0.w()) {
                aF0.K();
            } else if (aF0.L()) {
                aF0.e();
            }
            C(aF0);
            if (RecyclerView.this.M == null || aF0.u()) {
                return;
            }
            RecyclerView.this.M.j(aF0);
        }

        void C(A a2) {
            boolean z;
            boolean z2 = true;
            if (a2.w() || a2.f1092a.getParent() != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Scrapped or attached views may not be recycled. isScrap:");
                sb.append(a2.w());
                sb.append(" isAttached:");
                sb.append(a2.f1092a.getParent() != null);
                sb.append(RecyclerView.this.O());
                throw new IllegalArgumentException(sb.toString());
            }
            if (a2.x()) {
                throw new IllegalArgumentException("Tmp detached view should be removed from RecyclerView before it can be recycled: " + a2 + RecyclerView.this.O());
            }
            if (a2.J()) {
                throw new IllegalArgumentException("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle." + RecyclerView.this.O());
            }
            boolean zH = a2.h();
            Adapter adapter = RecyclerView.this.n;
            if ((adapter != null && zH && adapter.v(a2)) || a2.u()) {
                if (this.f <= 0 || a2.p(526)) {
                    z = false;
                } else {
                    int size = this.f1128c.size();
                    if (size >= this.f && size > 0) {
                        A(0);
                        size--;
                    }
                    if (RecyclerView.B0 && size > 0 && !RecyclerView.this.g0.d(a2.f1094c)) {
                        int i = size - 1;
                        while (i >= 0) {
                            if (!RecyclerView.this.g0.d(this.f1128c.get(i).f1094c)) {
                                break;
                            } else {
                                i--;
                            }
                        }
                        size = i + 1;
                    }
                    this.f1128c.add(size, a2);
                    z = true;
                }
                if (z) {
                    z2 = false;
                } else {
                    a(a2, true);
                }
                z = z;
            } else {
                z2 = false;
            }
            RecyclerView.this.h.q(a2);
            if (z || z2 || !zH) {
                return;
            }
            a2.r = null;
            a2.q = null;
        }

        void D(View view) {
            A aF0 = RecyclerView.f0(view);
            if (!aF0.p(12) && aF0.y() && !RecyclerView.this.o(aF0)) {
                if (this.f1127b == null) {
                    this.f1127b = new ArrayList<>();
                }
                aF0.H(this, true);
                this.f1127b.add(aF0);
                return;
            }
            if (!aF0.t() || aF0.v() || RecyclerView.this.n.j()) {
                aF0.H(this, false);
                this.f1126a.add(aF0);
            } else {
                throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool." + RecyclerView.this.O());
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:100:0x020c  */
        /* JADX WARN: Removed duplicated region for block: B:106:0x0228 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:108:0x022b  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0037  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x005c  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x005f  */
        /* JADX WARN: Removed duplicated region for block: B:73:0x0181 A[PHI: r1 r4
  0x0181: PHI (r1v12 androidx.recyclerview.widget.RecyclerView$A) = (r1v11 androidx.recyclerview.widget.RecyclerView$A), (r1v31 androidx.recyclerview.widget.RecyclerView$A) binds: [B:28:0x005d, B:59:0x0102] A[DONT_GENERATE, DONT_INLINE]
  0x0181: PHI (r4v3 boolean) = (r4v2 boolean), (r4v7 boolean) binds: [B:28:0x005d, B:59:0x0102] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:82:0x01a2  */
        /* JADX WARN: Removed duplicated region for block: B:88:0x01ce  */
        /* JADX WARN: Removed duplicated region for block: B:99:0x01fe  */
        @androidx.annotation.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        androidx.recyclerview.widget.RecyclerView.A F(int r17, boolean r18, long r19) {
            /*
                Method dump skipped, instruction units count: 615
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.t.F(int, boolean, long):androidx.recyclerview.widget.RecyclerView$A");
        }

        void G(A a2) {
            if (a2.o) {
                this.f1127b.remove(a2);
            } else {
                this.f1126a.remove(a2);
            }
            a2.n = null;
            a2.o = false;
            a2.e();
        }

        void H() {
            m mVar = RecyclerView.this.mLayout;
            this.f = this.e + (mVar != null ? mVar.m : 0);
            for (int size = this.f1128c.size() - 1; size >= 0 && this.f1128c.size() > this.f; size--) {
                A(size);
            }
        }

        boolean I(A a2) {
            if (a2.v()) {
                return RecyclerView.this.h0.e();
            }
            int i = a2.f1094c;
            if (i >= 0 && i < RecyclerView.this.n.f()) {
                if (RecyclerView.this.h0.e() || RecyclerView.this.n.h(a2.f1094c) == a2.l()) {
                    return !RecyclerView.this.n.j() || a2.k() == RecyclerView.this.n.g(a2.f1094c);
                }
                return false;
            }
            throw new IndexOutOfBoundsException("Inconsistency detected. Invalid view holder adapter position" + a2 + RecyclerView.this.O());
        }

        void J(int i, int i2) {
            int i3;
            int i4 = i2 + i;
            for (int size = this.f1128c.size() - 1; size >= 0; size--) {
                A a2 = this.f1128c.get(size);
                if (a2 != null && (i3 = a2.f1094c) >= i && i3 < i4) {
                    a2.b(2);
                    A(size);
                }
            }
        }

        void a(@NonNull A a2, boolean z) {
            RecyclerView.q(a2);
            View view = a2.f1092a;
            androidx.recyclerview.widget.l lVar = RecyclerView.this.o0;
            if (lVar != null) {
                androidx.core.view.a aVarN = lVar.n();
                ViewCompat.M(view, aVarN instanceof l.a ? ((l.a) aVarN).n(view) : null);
            }
            if (z) {
                g(a2);
            }
            a2.r = null;
            a2.q = null;
            i().i(a2);
        }

        public void c() {
            this.f1126a.clear();
            z();
        }

        void d() {
            int size = this.f1128c.size();
            for (int i = 0; i < size; i++) {
                this.f1128c.get(i).c();
            }
            int size2 = this.f1126a.size();
            for (int i2 = 0; i2 < size2; i2++) {
                this.f1126a.get(i2).c();
            }
            ArrayList<A> arrayList = this.f1127b;
            if (arrayList != null) {
                int size3 = arrayList.size();
                for (int i3 = 0; i3 < size3; i3++) {
                    this.f1127b.get(i3).c();
                }
            }
        }

        void e() {
            this.f1126a.clear();
            ArrayList<A> arrayList = this.f1127b;
            if (arrayList != null) {
                arrayList.clear();
            }
        }

        public int f(int i) {
            if (i >= 0 && i < RecyclerView.this.h0.b()) {
                return !RecyclerView.this.h0.e() ? i : RecyclerView.this.f.m(i);
            }
            throw new IndexOutOfBoundsException("invalid position " + i + ". State item count is " + RecyclerView.this.h0.b() + RecyclerView.this.O());
        }

        void g(@NonNull A a2) {
            u uVar = RecyclerView.this.o;
            if (uVar != null) {
                uVar.a(a2);
            }
            Adapter adapter = RecyclerView.this.n;
            if (adapter != null) {
                adapter.y(a2);
            }
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.h0 != null) {
                recyclerView.h.q(a2);
            }
        }

        A h(int i) {
            int size;
            int iM;
            ArrayList<A> arrayList = this.f1127b;
            if (arrayList != null && (size = arrayList.size()) != 0) {
                for (int i2 = 0; i2 < size; i2++) {
                    A a2 = this.f1127b.get(i2);
                    if (!a2.L() && a2.m() == i) {
                        a2.b(32);
                        return a2;
                    }
                }
                if (RecyclerView.this.n.j() && (iM = RecyclerView.this.f.m(i)) > 0 && iM < RecyclerView.this.n.f()) {
                    long jG = RecyclerView.this.n.g(iM);
                    for (int i3 = 0; i3 < size; i3++) {
                        A a3 = this.f1127b.get(i3);
                        if (!a3.L() && a3.k() == jG) {
                            a3.b(32);
                            return a3;
                        }
                    }
                }
            }
            return null;
        }

        s i() {
            if (this.g == null) {
                this.g = new s();
            }
            return this.g;
        }

        int j() {
            return this.f1126a.size();
        }

        @NonNull
        public List<A> k() {
            return this.d;
        }

        A l(long j, int i, boolean z) {
            for (int size = this.f1126a.size() - 1; size >= 0; size--) {
                A a2 = this.f1126a.get(size);
                if (a2.k() == j && !a2.L()) {
                    if (i == a2.l()) {
                        a2.b(32);
                        if (a2.v() && !RecyclerView.this.h0.e()) {
                            a2.F(2, 14);
                        }
                        return a2;
                    }
                    if (!z) {
                        this.f1126a.remove(size);
                        RecyclerView.this.removeDetachedView(a2.f1092a, false);
                        y(a2.f1092a);
                    }
                }
            }
            int size2 = this.f1128c.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return null;
                }
                A a3 = this.f1128c.get(size2);
                if (a3.k() == j && !a3.r()) {
                    if (i == a3.l()) {
                        if (!z) {
                            this.f1128c.remove(size2);
                        }
                        return a3;
                    }
                    if (!z) {
                        A(size2);
                        return null;
                    }
                }
            }
        }

        A m(int i, boolean z) {
            View viewE;
            int size = this.f1126a.size();
            for (int i2 = 0; i2 < size; i2++) {
                A a2 = this.f1126a.get(i2);
                if (!a2.L() && a2.m() == i && !a2.t() && (RecyclerView.this.h0.h || !a2.v())) {
                    a2.b(32);
                    return a2;
                }
            }
            if (z || (viewE = RecyclerView.this.g.e(i)) == null) {
                int size2 = this.f1128c.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    A a3 = this.f1128c.get(i3);
                    if (!a3.t() && a3.m() == i && !a3.r()) {
                        if (!z) {
                            this.f1128c.remove(i3);
                        }
                        return a3;
                    }
                }
                return null;
            }
            A aF0 = RecyclerView.f0(viewE);
            RecyclerView.this.g.s(viewE);
            int iM = RecyclerView.this.g.m(viewE);
            if (iM != -1) {
                RecyclerView.this.g.d(iM);
                D(viewE);
                aF0.b(8224);
                return aF0;
            }
            throw new IllegalStateException("layout index should not be -1 after unhiding a view:" + aF0 + RecyclerView.this.O());
        }

        View n(int i) {
            return this.f1126a.get(i).f1092a;
        }

        @NonNull
        public View o(int i) {
            return p(i, false);
        }

        View p(int i, boolean z) {
            return F(i, z, Long.MAX_VALUE).f1092a;
        }

        void s() {
            int size = this.f1128c.size();
            for (int i = 0; i < size; i++) {
                n nVar = (n) this.f1128c.get(i).f1092a.getLayoutParams();
                if (nVar != null) {
                    nVar.f1120c = true;
                }
            }
        }

        void t() {
            int size = this.f1128c.size();
            for (int i = 0; i < size; i++) {
                A a2 = this.f1128c.get(i);
                if (a2 != null) {
                    a2.b(6);
                    a2.a(null);
                }
            }
            Adapter adapter = RecyclerView.this.n;
            if (adapter == null || !adapter.j()) {
                z();
            }
        }

        void u(int i, int i2) {
            int size = this.f1128c.size();
            for (int i3 = 0; i3 < size; i3++) {
                A a2 = this.f1128c.get(i3);
                if (a2 != null && a2.f1094c >= i) {
                    a2.A(i2, true);
                }
            }
        }

        void v(int i, int i2) {
            int i3;
            int i4;
            int i5;
            int i6;
            if (i < i2) {
                i3 = -1;
                i5 = i;
                i4 = i2;
            } else {
                i3 = 1;
                i4 = i;
                i5 = i2;
            }
            int size = this.f1128c.size();
            for (int i7 = 0; i7 < size; i7++) {
                A a2 = this.f1128c.get(i7);
                if (a2 != null && (i6 = a2.f1094c) >= i5 && i6 <= i4) {
                    if (i6 == i) {
                        a2.A(i2 - i, false);
                    } else {
                        a2.A(i3, false);
                    }
                }
            }
        }

        void w(int i, int i2, boolean z) {
            int i3 = i + i2;
            for (int size = this.f1128c.size() - 1; size >= 0; size--) {
                A a2 = this.f1128c.get(size);
                if (a2 != null) {
                    int i4 = a2.f1094c;
                    if (i4 >= i3) {
                        a2.A(-i2, z);
                    } else if (i4 >= i) {
                        a2.b(8);
                        A(size);
                    }
                }
            }
        }

        void x(Adapter adapter, Adapter adapter2, boolean z) {
            c();
            i().h(adapter, adapter2, z);
        }

        void y(View view) {
            A aF0 = RecyclerView.f0(view);
            aF0.n = null;
            aF0.o = false;
            aF0.e();
            C(aF0);
        }

        void z() {
            for (int size = this.f1128c.size() - 1; size >= 0; size--) {
                A(size);
            }
            this.f1128c.clear();
            if (RecyclerView.B0) {
                RecyclerView.this.g0.b();
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface u {
        void a(@NonNull A a2);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class v extends i {
        v() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void a() {
            RecyclerView.this.n(null);
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.h0.g = true;
            recyclerView.S0(true);
            if (RecyclerView.this.f.p()) {
                return;
            }
            RecyclerView.this.requestLayout();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void c(int i, int i2, Object obj) {
            RecyclerView.this.n(null);
            if (RecyclerView.this.f.r(i, i2, obj)) {
                g();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void d(int i, int i2) {
            RecyclerView.this.n(null);
            if (RecyclerView.this.f.s(i, i2)) {
                g();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void e(int i, int i2, int i3) {
            RecyclerView.this.n(null);
            if (RecyclerView.this.f.t(i, i2, i3)) {
                g();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void f(int i, int i2) {
            RecyclerView.this.n(null);
            if (RecyclerView.this.f.u(i, i2)) {
                g();
            }
        }

        void g() {
            if (RecyclerView.A0) {
                RecyclerView recyclerView = RecyclerView.this;
                if (recyclerView.t && recyclerView.s) {
                    ViewCompat.H(recyclerView, recyclerView.j);
                    return;
                }
            }
            RecyclerView recyclerView2 = RecyclerView.this;
            recyclerView2.A = true;
            recyclerView2.requestLayout();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class w {
        public abstract int a();

        public abstract boolean b();

        public abstract boolean c();

        abstract void d(int i, int i2);

        protected abstract void e(View view);

        public abstract void f(int i);

        protected final void g() {
            throw null;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class x {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private SparseArray<Object> f1131b;
        int m;
        long n;
        int o;
        int p;
        int q;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1130a = -1;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1132c = 0;
        int d = 0;
        int e = 1;
        int f = 0;
        boolean g = false;
        boolean h = false;
        boolean i = false;
        boolean j = false;
        boolean k = false;
        boolean l = false;

        void a(int i) {
            if ((this.e & i) != 0) {
                return;
            }
            throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(i) + " but it is " + Integer.toBinaryString(this.e));
        }

        public int b() {
            return this.h ? this.f1132c - this.d : this.f;
        }

        public int c() {
            return this.f1130a;
        }

        public boolean d() {
            return this.f1130a != -1;
        }

        public boolean e() {
            return this.h;
        }

        void f(Adapter adapter) {
            this.e = 1;
            this.f = adapter.f();
            this.h = false;
            this.i = false;
            this.j = false;
        }

        public boolean g() {
            return this.l;
        }

        public String toString() {
            return "State{mTargetPosition=" + this.f1130a + ", mData=" + this.f1131b + ", mItemCount=" + this.f + ", mIsMeasuring=" + this.j + ", mPreviousLayoutItemCount=" + this.f1132c + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.d + ", mStructureChanged=" + this.g + ", mInPreLayout=" + this.h + ", mRunSimpleAnimations=" + this.k + ", mRunPredictiveAnimations=" + this.l + '}';
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class y {
        @Nullable
        public abstract View a(@NonNull t tVar, int i, int i2);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class z implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f1133c;
        private int d;
        OverScroller e;
        Interpolator f = RecyclerView.F0;
        private boolean g = false;
        private boolean h = false;

        z() {
            this.e = new OverScroller(RecyclerView.this.getContext(), RecyclerView.F0);
        }

        private int a(int i, int i2, int i3, int i4) {
            int iRound;
            int iAbs = Math.abs(i);
            int iAbs2 = Math.abs(i2);
            boolean z = iAbs > iAbs2;
            int iSqrt = (int) Math.sqrt((i3 * i3) + (i4 * i4));
            int iSqrt2 = (int) Math.sqrt((i * i) + (i2 * i2));
            RecyclerView recyclerView = RecyclerView.this;
            int width = z ? recyclerView.getWidth() : recyclerView.getHeight();
            int i5 = width / 2;
            float f = width;
            float f2 = i5;
            float fB = f2 + (b(Math.min(1.0f, (iSqrt2 * 1.0f) / f)) * f2);
            if (iSqrt > 0) {
                iRound = Math.round(Math.abs(fB / iSqrt) * 1000.0f) * 4;
            } else {
                if (!z) {
                    iAbs = iAbs2;
                }
                iRound = (int) (((iAbs / f) + 1.0f) * 300.0f);
            }
            return Math.min(iRound, 2000);
        }

        private float b(float f) {
            return (float) Math.sin((f - 0.5f) * 0.47123894f);
        }

        private void d() {
            RecyclerView.this.removeCallbacks(this);
            ViewCompat.H(RecyclerView.this, this);
        }

        public void c(int i, int i2) {
            RecyclerView.this.p1(2);
            this.d = 0;
            this.f1133c = 0;
            Interpolator interpolator = this.f;
            Interpolator interpolator2 = RecyclerView.F0;
            if (interpolator != interpolator2) {
                this.f = interpolator2;
                this.e = new OverScroller(RecyclerView.this.getContext(), RecyclerView.F0);
            }
            this.e.fling(0, 0, i, i2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            e();
        }

        void e() {
            if (this.g) {
                this.h = true;
            } else {
                d();
            }
        }

        public void f(int i, int i2, int i3, @Nullable Interpolator interpolator) {
            if (i3 == Integer.MIN_VALUE) {
                i3 = a(i, i2, 0, 0);
            }
            int i4 = i3;
            if (interpolator == null) {
                interpolator = RecyclerView.F0;
            }
            if (this.f != interpolator) {
                this.f = interpolator;
                this.e = new OverScroller(RecyclerView.this.getContext(), interpolator);
            }
            this.d = 0;
            this.f1133c = 0;
            RecyclerView.this.p1(2);
            this.e.startScroll(0, 0, i, i2, i4);
            if (Build.VERSION.SDK_INT < 23) {
                this.e.computeScrollOffset();
            }
            e();
        }

        public void g() {
            RecyclerView.this.removeCallbacks(this);
            this.e.abortAnimation();
        }

        @Override // java.lang.Runnable
        public void run() {
            int i;
            int i2;
            int i3;
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.mLayout == null) {
                g();
                return;
            }
            this.h = false;
            this.g = true;
            recyclerView.t();
            OverScroller overScroller = this.e;
            if (overScroller.computeScrollOffset()) {
                int currX = overScroller.getCurrX();
                int currY = overScroller.getCurrY();
                int i4 = currX - this.f1133c;
                int i5 = currY - this.d;
                this.f1133c = currX;
                this.d = currY;
                RecyclerView recyclerView2 = RecyclerView.this;
                int[] iArr = recyclerView2.u0;
                iArr[0] = 0;
                iArr[1] = 0;
                if (recyclerView2.E(i4, i5, iArr, null, 1)) {
                    int[] iArr2 = RecyclerView.this.u0;
                    i4 -= iArr2[0];
                    i5 -= iArr2[1];
                }
                if (RecyclerView.this.getOverScrollMode() != 2) {
                    RecyclerView.this.s(i4, i5);
                }
                RecyclerView recyclerView3 = RecyclerView.this;
                if (recyclerView3.n != null) {
                    int[] iArr3 = recyclerView3.u0;
                    iArr3[0] = 0;
                    iArr3[1] = 0;
                    recyclerView3.j1(i4, i5, iArr3);
                    RecyclerView recyclerView4 = RecyclerView.this;
                    int[] iArr4 = recyclerView4.u0;
                    int i6 = iArr4[0];
                    int i7 = iArr4[1];
                    int i8 = i4 - i6;
                    i5 -= i7;
                    w wVar = recyclerView4.mLayout.g;
                    if (wVar != null && !wVar.b() && wVar.c()) {
                        int iB = RecyclerView.this.h0.b();
                        if (iB == 0) {
                            wVar.g();
                            throw null;
                        }
                        if (wVar.a() >= iB) {
                            wVar.f(iB - 1);
                            wVar.d(i6, i7);
                        } else {
                            wVar.d(i6, i7);
                        }
                    }
                    i3 = i8;
                    i = i7;
                    i2 = i6;
                } else {
                    i = 0;
                    i2 = 0;
                    i3 = i4;
                }
                int i9 = i5;
                if (!RecyclerView.this.p.isEmpty()) {
                    RecyclerView.this.invalidate();
                }
                RecyclerView recyclerView5 = RecyclerView.this;
                int[] iArr5 = recyclerView5.u0;
                iArr5[0] = 0;
                iArr5[1] = 0;
                recyclerView5.F(i2, i, i3, i9, null, 1, iArr5);
                int[] iArr6 = RecyclerView.this.u0;
                int i10 = i3 - iArr6[0];
                int i11 = i9 - iArr6[1];
                if (i2 != 0 || i != 0) {
                    RecyclerView.this.H(i2, i);
                }
                if (!RecyclerView.this.awakenScrollBars()) {
                    RecyclerView.this.invalidate();
                }
                boolean z = overScroller.isFinished() || (((overScroller.getCurrX() == overScroller.getFinalX()) || i10 != 0) && ((overScroller.getCurrY() == overScroller.getFinalY()) || i11 != 0));
                w wVar2 = RecyclerView.this.mLayout.g;
                if ((wVar2 != null && wVar2.b()) || !z) {
                    e();
                    RecyclerView recyclerView6 = RecyclerView.this;
                    androidx.recyclerview.widget.g gVar = recyclerView6.f0;
                    if (gVar != null) {
                        gVar.f(recyclerView6, i2, i);
                    }
                } else {
                    if (RecyclerView.this.getOverScrollMode() != 2) {
                        int currVelocity = (int) overScroller.getCurrVelocity();
                        int i12 = i10 < 0 ? -currVelocity : i10 > 0 ? currVelocity : 0;
                        if (i11 < 0) {
                            currVelocity = -currVelocity;
                        } else if (i11 <= 0) {
                            currVelocity = 0;
                        }
                        RecyclerView.this.a(i12, currVelocity);
                    }
                    if (RecyclerView.B0) {
                        RecyclerView.this.g0.b();
                    }
                }
            }
            w wVar3 = RecyclerView.this.mLayout.g;
            if (wVar3 != null && wVar3.b()) {
                wVar3.d(0, 0);
            }
            this.g = false;
            if (this.h) {
                d();
            } else {
                RecyclerView.this.p1(0);
                RecyclerView.this.y1(1);
            }
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        x0 = new int[]{R.attr.nestedScrollingEnabled};
        y0 = i2 == 18 || i2 == 19 || i2 == 20;
        z0 = i2 >= 23;
        A0 = i2 >= 16;
        B0 = i2 >= 21;
        C0 = i2 <= 15;
        D0 = i2 <= 15;
        Class<?> cls = Integer.TYPE;
        E0 = new Class[]{Context.class, AttributeSet.class, cls, cls};
        F0 = new c();
    }

    public RecyclerView(@NonNull Context context) {
        this(context, null);
    }

    private void A1() {
        this.e0.g();
        m mVar = this.mLayout;
        if (mVar != null) {
            mVar.E1();
        }
    }

    private void B() {
        this.h0.a(1);
        P(this.h0);
        this.h0.j = false;
        v1();
        this.h.f();
        J0();
        R0();
        g1();
        x xVar = this.h0;
        xVar.i = xVar.k && this.l0;
        this.l0 = false;
        this.k0 = false;
        x xVar2 = this.h0;
        xVar2.h = xVar2.l;
        xVar2.f = this.n.f();
        T(this.q0);
        if (this.h0.k) {
            int iG = this.g.g();
            for (int i2 = 0; i2 < iG; i2++) {
                A aF0 = f0(this.g.f(i2));
                if (!aF0.J() && (!aF0.t() || this.n.j())) {
                    this.h.e(aF0, this.M.t(this.h0, aF0, ItemAnimator.e(aF0), aF0.o()));
                    if (this.h0.i && aF0.y() && !aF0.v() && !aF0.J() && !aF0.t()) {
                        this.h.c(c0(aF0), aF0);
                    }
                }
            }
        }
        if (this.h0.l) {
            h1();
            x xVar3 = this.h0;
            boolean z2 = xVar3.g;
            xVar3.g = false;
            this.mLayout.V0(this.d, xVar3);
            this.h0.g = z2;
            for (int i3 = 0; i3 < this.g.g(); i3++) {
                A aF02 = f0(this.g.f(i3));
                if (!aF02.J() && !this.h.i(aF02)) {
                    int iE = ItemAnimator.e(aF02);
                    boolean zP = aF02.p(8192);
                    if (!zP) {
                        iE |= 4096;
                    }
                    ItemAnimator.c cVarT = this.M.t(this.h0, aF02, iE, aF02.o());
                    if (zP) {
                        U0(aF02, cVarT);
                    } else {
                        this.h.a(aF02, cVarT);
                    }
                }
            }
            r();
        } else {
            r();
        }
        K0();
        x1(false);
        this.h0.e = 2;
    }

    private void B0(int i2, int i3, @Nullable MotionEvent motionEvent, int i4) {
        m mVar = this.mLayout;
        if (mVar == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.x) {
            return;
        }
        int[] iArr = this.u0;
        iArr[0] = 0;
        iArr[1] = 0;
        boolean zJ = mVar.j();
        boolean zK = this.mLayout.k();
        int i5 = zJ ? 1 : 0;
        if (zK) {
            i5 |= 2;
        }
        w1(i5, i4);
        if (E(zJ ? i2 : 0, zK ? i3 : 0, this.u0, this.s0, i4)) {
            int[] iArr2 = this.u0;
            i2 -= iArr2[0];
            i3 -= iArr2[1];
        }
        i1(zJ ? i2 : 0, zK ? i3 : 0, motionEvent, i4);
        if (this.f0 != null && (i2 != 0 || i3 != 0)) {
            this.f0.f(this, i2, i3);
        }
        y1(i4);
    }

    private void C() {
        v1();
        J0();
        this.h0.a(6);
        this.f.j();
        this.h0.f = this.n.f();
        this.h0.d = 0;
        if (this.e != null && this.n.d()) {
            Parcelable parcelable = this.e.e;
            if (parcelable != null) {
                this.mLayout.a1(parcelable);
            }
            this.e = null;
        }
        x xVar = this.h0;
        xVar.h = false;
        this.mLayout.V0(this.d, xVar);
        x xVar2 = this.h0;
        xVar2.g = false;
        xVar2.k = xVar2.k && this.M != null;
        this.h0.e = 4;
        K0();
        x1(false);
    }

    private void D() {
        this.h0.a(4);
        v1();
        J0();
        x xVar = this.h0;
        xVar.e = 1;
        if (xVar.k) {
            for (int iG = this.g.g() - 1; iG >= 0; iG--) {
                A aF0 = f0(this.g.f(iG));
                if (!aF0.J()) {
                    long jC0 = c0(aF0);
                    ItemAnimator.c cVarS = this.M.s(this.h0, aF0);
                    A aG = this.h.g(jC0);
                    if (aG == null || aG.J()) {
                        this.h.d(aF0, cVarS);
                    } else {
                        boolean zH = this.h.h(aG);
                        boolean zH2 = this.h.h(aF0);
                        if (zH && aG == aF0) {
                            this.h.d(aF0, cVarS);
                        } else {
                            ItemAnimator.c cVarN = this.h.n(aG);
                            this.h.d(aF0, cVarS);
                            ItemAnimator.c cVarM = this.h.m(aF0);
                            if (cVarN == null) {
                                o0(jC0, aF0, aG);
                            } else {
                                l(aG, aF0, cVarN, cVarM, zH, zH2);
                            }
                        }
                    }
                }
            }
            this.h.o(this.w0);
        }
        this.mLayout.i1(this.d);
        x xVar2 = this.h0;
        xVar2.f1132c = xVar2.f;
        this.D = false;
        this.E = false;
        xVar2.k = false;
        xVar2.l = false;
        this.mLayout.h = false;
        ArrayList<A> arrayList = this.d.f1127b;
        if (arrayList != null) {
            arrayList.clear();
        }
        m mVar = this.mLayout;
        if (mVar.n) {
            mVar.m = 0;
            mVar.n = false;
            this.d.H();
        }
        this.mLayout.W0(this.h0);
        K0();
        x1(false);
        this.h.f();
        int[] iArr = this.q0;
        if (w(iArr[0], iArr[1])) {
            H(0, 0);
        }
        V0();
        e1();
    }

    private boolean J(MotionEvent motionEvent) {
        q qVar = this.r;
        if (qVar == null) {
            if (motionEvent.getAction() == 0) {
                return false;
            }
            return S(motionEvent);
        }
        qVar.b(this, motionEvent);
        int action = motionEvent.getAction();
        if (action == 3 || action == 1) {
            this.r = null;
        }
        return true;
    }

    private void M0(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.O) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.O = motionEvent.getPointerId(i2);
            int x2 = (int) (motionEvent.getX(i2) + 0.5f);
            this.S = x2;
            this.Q = x2;
            int y2 = (int) (motionEvent.getY(i2) + 0.5f);
            this.T = y2;
            this.R = y2;
        }
    }

    private boolean Q0() {
        return this.M != null && this.mLayout.F1();
    }

    private void R0() {
        if (this.D) {
            this.f.y();
            if (this.E) {
                this.mLayout.Q0(this);
            }
        }
        if (Q0()) {
            this.f.w();
        } else {
            this.f.j();
        }
        boolean z2 = false;
        boolean z3 = this.k0 || this.l0;
        this.h0.k = this.mFirstLayoutComplete && this.M != null && (this.D || z3 || this.mLayout.h) && (!this.D || this.n.j());
        x xVar = this.h0;
        if (xVar.k && z3 && !this.D && Q0()) {
            z2 = true;
        }
        xVar.l = z2;
    }

    private boolean S(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        int size = this.q.size();
        for (int i2 = 0; i2 < size; i2++) {
            q qVar = this.q.get(i2);
            if (qVar.a(this, motionEvent) && action != 3) {
                this.r = qVar;
                return true;
            }
        }
        return false;
    }

    private void T(int[] iArr) {
        int iG = this.g.g();
        if (iG == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i2 = Integer.MAX_VALUE;
        int i3 = Integer.MIN_VALUE;
        for (int i4 = 0; i4 < iG; i4++) {
            A aF0 = f0(this.g.f(i4));
            if (!aF0.J()) {
                int iM = aF0.m();
                if (iM < i2) {
                    i2 = iM;
                }
                if (iM > i3) {
                    i3 = iM;
                }
            }
        }
        iArr[0] = i2;
        iArr[1] = i3;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void T0(float r7, float r8, float r9, float r10) {
        /*
            r6 = this;
            r0 = 0
            int r1 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            r2 = 1065353216(0x3f800000, float:1.0)
            r3 = 1
            if (r1 >= 0) goto L21
            r6.L()
            android.widget.EdgeEffect r1 = r6.I
            float r4 = -r8
            int r5 = r6.getWidth()
            float r5 = (float) r5
            float r4 = r4 / r5
            int r5 = r6.getHeight()
            float r5 = (float) r5
            float r9 = r9 / r5
            float r9 = r2 - r9
            androidx.core.widget.d.a(r1, r4, r9)
        L1f:
            r9 = r3
            goto L3c
        L21:
            int r1 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r1 <= 0) goto L3b
            r6.M()
            android.widget.EdgeEffect r1 = r6.K
            int r4 = r6.getWidth()
            float r4 = (float) r4
            float r4 = r8 / r4
            int r5 = r6.getHeight()
            float r5 = (float) r5
            float r9 = r9 / r5
            androidx.core.widget.d.a(r1, r4, r9)
            goto L1f
        L3b:
            r9 = 0
        L3c:
            int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r1 >= 0) goto L56
            r6.N()
            android.widget.EdgeEffect r9 = r6.J
            float r1 = -r10
            int r2 = r6.getHeight()
            float r2 = (float) r2
            float r1 = r1 / r2
            int r2 = r6.getWidth()
            float r2 = (float) r2
            float r7 = r7 / r2
            androidx.core.widget.d.a(r9, r1, r7)
            goto L72
        L56:
            int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r1 <= 0) goto L71
            r6.K()
            android.widget.EdgeEffect r9 = r6.L
            int r1 = r6.getHeight()
            float r1 = (float) r1
            float r1 = r10 / r1
            int r4 = r6.getWidth()
            float r4 = (float) r4
            float r7 = r7 / r4
            float r2 = r2 - r7
            androidx.core.widget.d.a(r9, r1, r2)
            goto L72
        L71:
            r3 = r9
        L72:
            if (r3 != 0) goto L7c
            int r7 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r7 != 0) goto L7c
            int r7 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r7 == 0) goto L7f
        L7c:
            androidx.core.view.ViewCompat.G(r6)
        L7f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.T0(float, float, float, float):void");
    }

    @Nullable
    static RecyclerView U(@NonNull View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view instanceof RecyclerView) {
            return (RecyclerView) view;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            RecyclerView recyclerViewU = U(viewGroup.getChildAt(i2));
            if (recyclerViewU != null) {
                return recyclerViewU;
            }
        }
        return null;
    }

    @Nullable
    private View V() {
        A aW;
        int i2 = this.h0.m;
        if (i2 == -1) {
            i2 = 0;
        }
        int iB = this.h0.b();
        for (int i3 = i2; i3 < iB; i3++) {
            A aW2 = W(i3);
            if (aW2 == null) {
                break;
            }
            if (aW2.f1092a.hasFocusable()) {
                return aW2.f1092a;
            }
        }
        int iMin = Math.min(iB, i2);
        do {
            iMin--;
            if (iMin < 0 || (aW = W(iMin)) == null) {
                return null;
            }
        } while (!aW.f1092a.hasFocusable());
        return aW.f1092a;
    }

    private void V0() {
        View viewFindViewById;
        if (!this.d0 || this.n == null || !hasFocus() || getDescendantFocusability() == 393216) {
            return;
        }
        if (getDescendantFocusability() == 131072 && isFocused()) {
            return;
        }
        if (!isFocused()) {
            View focusedChild = getFocusedChild();
            if (!D0 || (focusedChild.getParent() != null && focusedChild.hasFocus())) {
                if (!this.g.n(focusedChild)) {
                    return;
                }
            } else if (this.g.g() == 0) {
                requestFocus();
                return;
            }
        }
        View viewV = null;
        A aX = (this.h0.n == -1 || !this.n.j()) ? null : X(this.h0.n);
        if (aX != null && !this.g.n(aX.f1092a) && aX.f1092a.hasFocusable()) {
            viewV = aX.f1092a;
        } else if (this.g.g() > 0) {
            viewV = V();
        }
        if (viewV != null) {
            int i2 = this.h0.o;
            if (i2 != -1 && (viewFindViewById = viewV.findViewById(i2)) != null && viewFindViewById.isFocusable()) {
                viewV = viewFindViewById;
            }
            viewV.requestFocus();
        }
    }

    private void W0() {
        boolean zIsFinished;
        EdgeEffect edgeEffect = this.I;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            zIsFinished = this.I.isFinished();
        } else {
            zIsFinished = false;
        }
        EdgeEffect edgeEffect2 = this.J;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            zIsFinished |= this.J.isFinished();
        }
        EdgeEffect edgeEffect3 = this.K;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            zIsFinished |= this.K.isFinished();
        }
        EdgeEffect edgeEffect4 = this.L;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            zIsFinished |= this.L.isFinished();
        }
        if (zIsFinished) {
            ViewCompat.G(this);
        }
    }

    private void d1(@NonNull View view, @Nullable View view2) {
        View view3 = view2 != null ? view2 : view;
        this.k.set(0, 0, view3.getWidth(), view3.getHeight());
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof n) {
            n nVar = (n) layoutParams;
            if (!nVar.f1120c) {
                Rect rect = nVar.f1119b;
                Rect rect2 = this.k;
                rect2.left -= rect.left;
                rect2.right += rect.right;
                rect2.top -= rect.top;
                rect2.bottom += rect.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, this.k);
            offsetRectIntoDescendantCoords(view, this.k);
        }
        this.mLayout.p1(this, view, this.k, !this.mFirstLayoutComplete, view2 == null);
    }

    private void e1() {
        x xVar = this.h0;
        xVar.n = -1L;
        xVar.m = -1;
        xVar.o = -1;
    }

    private void f(A a2) {
        View view = a2.f1092a;
        boolean z2 = view.getParent() == this;
        this.d.G(e0(view));
        if (a2.x()) {
            this.g.c(view, -1, view.getLayoutParams(), true);
        } else if (z2) {
            this.g.k(view);
        } else {
            this.g.b(view, true);
        }
    }

    static A f0(View view) {
        if (view == null) {
            return null;
        }
        return ((n) view.getLayoutParams()).f1118a;
    }

    private void f1() {
        VelocityTracker velocityTracker = this.P;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        y1(0);
        W0();
    }

    static void g0(View view, Rect rect) {
        n nVar = (n) view.getLayoutParams();
        Rect rect2 = nVar.f1119b;
        rect.set((view.getLeft() - rect2.left) - ((ViewGroup.MarginLayoutParams) nVar).leftMargin, (view.getTop() - rect2.top) - ((ViewGroup.MarginLayoutParams) nVar).topMargin, view.getRight() + rect2.right + ((ViewGroup.MarginLayoutParams) nVar).rightMargin, view.getBottom() + rect2.bottom + ((ViewGroup.MarginLayoutParams) nVar).bottomMargin);
    }

    private void g1() {
        View focusedChild = (this.d0 && hasFocus() && this.n != null) ? getFocusedChild() : null;
        A aR = focusedChild != null ? R(focusedChild) : null;
        if (aR == null) {
            e1();
            return;
        }
        this.h0.n = this.n.j() ? aR.k() : -1L;
        this.h0.m = this.D ? -1 : aR.v() ? aR.d : aR.j();
        this.h0.o = h0(aR.f1092a);
    }

    private int h0(View view) {
        int id = view.getId();
        while (!view.isFocused() && (view instanceof ViewGroup) && view.hasFocus()) {
            view = ((ViewGroup) view).getFocusedChild();
            if (view.getId() != -1) {
                id = view.getId();
            }
        }
        return id;
    }

    private String i0(Context context, String str) {
        if (str.charAt(0) == '.') {
            return context.getPackageName() + str;
        }
        if (str.contains(".")) {
            return str;
        }
        return RecyclerView.class.getPackage().getName() + '.' + str;
    }

    private void l(@NonNull A a2, @NonNull A a3, @NonNull ItemAnimator.c cVar, @NonNull ItemAnimator.c cVar2, boolean z2, boolean z3) {
        a2.G(false);
        if (z2) {
            f(a2);
        }
        if (a2 != a3) {
            if (z3) {
                f(a3);
            }
            a2.h = a3;
            f(a2);
            this.d.G(a2);
            a3.G(false);
            a3.i = a2;
        }
        if (this.M.b(a2, a3, cVar, cVar2)) {
            P0();
        }
    }

    private void m1(@Nullable Adapter adapter, boolean z2, boolean z3) {
        Adapter adapter2 = this.n;
        if (adapter2 != null) {
            adapter2.B(this.f1091c);
            this.n.u(this);
        }
        if (!z2 || z3) {
            X0();
        }
        this.f.y();
        Adapter adapter3 = this.n;
        this.n = adapter;
        if (adapter != null) {
            adapter.z(this.f1091c);
            adapter.q(this);
        }
        m mVar = this.mLayout;
        if (mVar != null) {
            mVar.C0(adapter3, this.n);
        }
        this.d.x(adapter3, this.n, z2);
        this.h0.g = true;
    }

    private androidx.core.view.k n0() {
        if (this.r0 == null) {
            this.r0 = new androidx.core.view.k(this);
        }
        return this.r0;
    }

    private void o0(long j2, A a2, A a3) {
        int iG = this.g.g();
        for (int i2 = 0; i2 < iG; i2++) {
            A aF0 = f0(this.g.f(i2));
            if (aF0 != a2 && c0(aF0) == j2) {
                Adapter adapter = this.n;
                if (adapter == null || !adapter.j()) {
                    throw new IllegalStateException("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:" + aF0 + " \n View Holder 2:" + a2 + O());
                }
                throw new IllegalStateException("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:" + aF0 + " \n View Holder 2:" + a2 + O());
            }
        }
        Log.e("RecyclerView", "Problem while matching changed view holders with the newones. The pre-layout information for the change holder " + a3 + " cannot be found but it is necessary for " + a2 + O());
    }

    private void p() {
        f1();
        p1(0);
    }

    static void q(@NonNull A a2) {
        WeakReference<RecyclerView> weakReference = a2.f1093b;
        if (weakReference != null) {
            RecyclerView recyclerView = weakReference.get();
            while (recyclerView != null) {
                if (recyclerView == a2.f1092a) {
                    return;
                }
                Object parent = recyclerView.getParent();
                recyclerView = parent instanceof View ? (View) parent : null;
            }
            a2.f1093b = null;
        }
    }

    private boolean q0() {
        int iG = this.g.g();
        for (int i2 = 0; i2 < iG; i2++) {
            A aF0 = f0(this.g.f(i2));
            if (aF0 != null && !aF0.J() && aF0.y()) {
                return true;
            }
        }
        return false;
    }

    @SuppressLint({"InlinedApi"})
    private void s0() {
        if (ViewCompat.p(this) == 0) {
            ViewCompat.S(this, 8);
        }
    }

    private void t0() {
        this.g = new androidx.recyclerview.widget.d(new e());
    }

    private void u(Context context, String str, AttributeSet attributeSet, int i2, int i3) {
        Constructor constructor;
        if (str != null) {
            String strTrim = str.trim();
            if (strTrim.isEmpty()) {
                return;
            }
            String strI0 = i0(context, strTrim);
            try {
                Class<? extends U> clsAsSubclass = Class.forName(strI0, false, isInEditMode() ? getClass().getClassLoader() : context.getClassLoader()).asSubclass(m.class);
                Object[] objArr = null;
                try {
                    constructor = clsAsSubclass.getConstructor(E0);
                    objArr = new Object[]{context, attributeSet, Integer.valueOf(i2), Integer.valueOf(i3)};
                } catch (NoSuchMethodException e2) {
                    try {
                        constructor = clsAsSubclass.getConstructor(new Class[0]);
                    } catch (NoSuchMethodException e3) {
                        e3.initCause(e2);
                        throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + strI0, e3);
                    }
                }
                constructor.setAccessible(true);
                o1((m) constructor.newInstance(objArr));
            } catch (ClassCastException e4) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + strI0, e4);
            } catch (ClassNotFoundException e5) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + strI0, e5);
            } catch (IllegalAccessException e6) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + strI0, e6);
            } catch (InstantiationException e7) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + strI0, e7);
            } catch (InvocationTargetException e8) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + strI0, e8);
            }
        }
    }

    private boolean w(int i2, int i3) {
        T(this.q0);
        int[] iArr = this.q0;
        return (iArr[0] == i2 && iArr[1] == i3) ? false : true;
    }

    private boolean y0(View view, View view2, int i2) {
        int i3;
        if (view2 == null || view2 == this || Q(view2) == null) {
            return false;
        }
        if (view == null || Q(view) == null) {
            return true;
        }
        this.k.set(0, 0, view.getWidth(), view.getHeight());
        this.l.set(0, 0, view2.getWidth(), view2.getHeight());
        offsetDescendantRectToMyCoords(view, this.k);
        offsetDescendantRectToMyCoords(view2, this.l);
        byte b2 = -1;
        int i4 = this.mLayout.X() == 1 ? -1 : 1;
        Rect rect = this.k;
        int i5 = rect.left;
        int i6 = this.l.left;
        if ((i5 < i6 || rect.right <= i6) && this.k.right < this.l.right) {
            i3 = 1;
        } else {
            Rect rect2 = this.k;
            int i7 = rect2.right;
            int i8 = this.l.right;
            i3 = ((i7 > i8 || rect2.left >= i8) && this.k.left > this.l.left) ? -1 : 0;
        }
        Rect rect3 = this.k;
        int i9 = rect3.top;
        int i10 = this.l.top;
        if ((i9 < i10 || rect3.bottom <= i10) && this.k.bottom < this.l.bottom) {
            b2 = 1;
        } else {
            Rect rect4 = this.k;
            int i11 = rect4.bottom;
            int i12 = this.l.bottom;
            if ((i11 <= i12 && rect4.top < i12) || this.k.top <= this.l.top) {
                b2 = 0;
            }
        }
        if (i2 == 1) {
            return b2 < 0 || (b2 == 0 && i3 * i4 <= 0);
        }
        if (i2 == 2) {
            return b2 > 0 || (b2 == 0 && i3 * i4 >= 0);
        }
        if (i2 == 17) {
            return i3 < 0;
        }
        if (i2 == 33) {
            return b2 < 0;
        }
        if (i2 == 66) {
            return i3 > 0;
        }
        if (i2 == 130) {
            return b2 > 0;
        }
        throw new IllegalArgumentException("Invalid direction: " + i2 + O());
    }

    private void z() {
        int i2 = this.z;
        this.z = 0;
        if (i2 == 0 || !w0()) {
            return;
        }
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
        accessibilityEventObtain.setEventType(2048);
        AccessibilityEventCompat.b(accessibilityEventObtain, i2);
        sendAccessibilityEventUnchecked(accessibilityEventObtain);
    }

    void A() {
        if (this.n == null) {
            Log.w("RecyclerView", "No adapter attached; skipping layout");
            return;
        }
        if (this.mLayout == null) {
            Log.e("RecyclerView", "No layout manager attached; skipping layout");
            return;
        }
        x xVar = this.h0;
        xVar.j = false;
        if (xVar.e == 1) {
            B();
            this.mLayout.v1(this);
            C();
        } else if (!this.f.q() && this.mLayout.m0() == getWidth() && this.mLayout.V() == getHeight()) {
            this.mLayout.v1(this);
        } else {
            this.mLayout.v1(this);
            C();
        }
        D();
    }

    void A0() {
        int iJ = this.g.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            A aF0 = f0(this.g.i(i2));
            if (aF0 != null && !aF0.J()) {
                aF0.b(6);
            }
        }
        z0();
        this.d.t();
    }

    void B1(int i2, int i3, Object obj) {
        int i4;
        int iJ = this.g.j();
        int i5 = i2 + i3;
        for (int i6 = 0; i6 < iJ; i6++) {
            View viewI = this.g.i(i6);
            A aF0 = f0(viewI);
            if (aF0 != null && !aF0.J() && (i4 = aF0.f1094c) >= i2 && i4 < i5) {
                aF0.b(2);
                aF0.a(obj);
                ((n) viewI.getLayoutParams()).f1120c = true;
            }
        }
        this.d.J(i2, i3);
    }

    public void C0(@Px int i2) {
        int iG = this.g.g();
        for (int i3 = 0; i3 < iG; i3++) {
            this.g.f(i3).offsetLeftAndRight(i2);
        }
    }

    public void D0(@Px int i2) {
        int iG = this.g.g();
        for (int i3 = 0; i3 < iG; i3++) {
            this.g.f(i3).offsetTopAndBottom(i2);
        }
    }

    public boolean E(int i2, int i3, int[] iArr, int[] iArr2, int i4) {
        return n0().d(i2, i3, iArr, iArr2, i4);
    }

    void E0(int i2, int i3) {
        int iJ = this.g.j();
        for (int i4 = 0; i4 < iJ; i4++) {
            A aF0 = f0(this.g.i(i4));
            if (aF0 != null && !aF0.J() && aF0.f1094c >= i2) {
                aF0.A(i3, false);
                this.h0.g = true;
            }
        }
        this.d.u(i2, i3);
        requestLayout();
    }

    public final void F(int i2, int i3, int i4, int i5, int[] iArr, int i6, @NonNull int[] iArr2) {
        n0().e(i2, i3, i4, i5, iArr, i6, iArr2);
    }

    void F0(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        int iJ = this.g.j();
        if (i2 < i3) {
            i6 = -1;
            i5 = i2;
            i4 = i3;
        } else {
            i4 = i2;
            i5 = i3;
            i6 = 1;
        }
        for (int i8 = 0; i8 < iJ; i8++) {
            A aF0 = f0(this.g.i(i8));
            if (aF0 != null && (i7 = aF0.f1094c) >= i5 && i7 <= i4) {
                if (i7 == i2) {
                    aF0.A(i3 - i2, false);
                } else {
                    aF0.A(i6, false);
                }
                this.h0.g = true;
            }
        }
        this.d.v(i2, i3);
        requestLayout();
    }

    void G(int i2) {
        m mVar = this.mLayout;
        if (mVar != null) {
            mVar.c1(i2);
        }
        N0(i2);
        r rVar = this.i0;
        if (rVar != null) {
            rVar.a(this, i2);
        }
        List<r> list = this.j0;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.j0.get(size).a(this, i2);
            }
        }
    }

    void G0(int i2, int i3, boolean z2) {
        int i4 = i2 + i3;
        int iJ = this.g.j();
        for (int i5 = 0; i5 < iJ; i5++) {
            A aF0 = f0(this.g.i(i5));
            if (aF0 != null && !aF0.J()) {
                int i6 = aF0.f1094c;
                if (i6 >= i4) {
                    aF0.A(-i3, z2);
                    this.h0.g = true;
                } else if (i6 >= i2) {
                    aF0.i(i2 - 1, -i3, z2);
                    this.h0.g = true;
                }
            }
        }
        this.d.w(i2, i3, z2);
        requestLayout();
    }

    void H(int i2, int i3) {
        this.G++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX - i2, scrollY - i3);
        O0(i2, i3);
        r rVar = this.i0;
        if (rVar != null) {
            rVar.b(this, i2, i3);
        }
        List<r> list = this.j0;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.j0.get(size).b(this, i2, i3);
            }
        }
        this.G--;
    }

    public void H0(@NonNull View view) {
    }

    void I() {
        int i2;
        for (int size = this.mPendingAccessibilityImportanceChange.size() - 1; size >= 0; size--) {
            A a2 = this.mPendingAccessibilityImportanceChange.get(size);
            if (a2.f1092a.getParent() == this && !a2.J() && (i2 = a2.mPendingAccessibilityState) != -1) {
                ViewCompat.R(a2.f1092a, i2);
                a2.mPendingAccessibilityState = -1;
            }
        }
        this.mPendingAccessibilityImportanceChange.clear();
    }

    public void I0(@NonNull View view) {
    }

    void J0() {
        this.F++;
    }

    void K() {
        if (this.L != null) {
            return;
        }
        EdgeEffect edgeEffectA = this.H.a(this, 3);
        this.L = edgeEffectA;
        if (this.i) {
            edgeEffectA.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffectA.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    void K0() {
        L0(true);
    }

    void L() {
        if (this.I != null) {
            return;
        }
        EdgeEffect edgeEffectA = this.H.a(this, 0);
        this.I = edgeEffectA;
        if (this.i) {
            edgeEffectA.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffectA.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    void L0(boolean z2) {
        int i2 = this.F - 1;
        this.F = i2;
        if (i2 < 1) {
            this.F = 0;
            if (z2) {
                z();
                I();
            }
        }
    }

    void M() {
        if (this.K != null) {
            return;
        }
        EdgeEffect edgeEffectA = this.H.a(this, 2);
        this.K = edgeEffectA;
        if (this.i) {
            edgeEffectA.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffectA.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    void N() {
        if (this.J != null) {
            return;
        }
        EdgeEffect edgeEffectA = this.H.a(this, 1);
        this.J = edgeEffectA;
        if (this.i) {
            edgeEffectA.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffectA.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void N0(int i2) {
    }

    String O() {
        return " " + super.toString() + ", adapter:" + this.n + ", layout:" + this.mLayout + ", context:" + getContext();
    }

    public void O0(@Px int i2, @Px int i3) {
    }

    final void P(x xVar) {
        if (m0() != 2) {
            xVar.p = 0;
            xVar.q = 0;
        } else {
            OverScroller overScroller = this.e0.e;
            xVar.p = overScroller.getFinalX() - overScroller.getCurrX();
            xVar.q = overScroller.getFinalY() - overScroller.getCurrY();
        }
    }

    void P0() {
        if (this.n0 || !this.s) {
            return;
        }
        ViewCompat.H(this, this.v0);
        this.n0 = true;
    }

    @Nullable
    public View Q(@NonNull View view) {
        ViewParent parent = view.getParent();
        while (parent != null && parent != this && (parent instanceof View)) {
            view = parent;
            parent = view.getParent();
        }
        if (parent == this) {
            return view;
        }
        return null;
    }

    @Nullable
    public A R(@NonNull View view) {
        View viewQ = Q(view);
        if (viewQ == null) {
            return null;
        }
        return e0(viewQ);
    }

    void S0(boolean z2) {
        this.E = z2 | this.E;
        this.D = true;
        A0();
    }

    void U0(A a2, ItemAnimator.c cVar) {
        a2.F(0, 8192);
        if (this.h0.i && a2.y() && !a2.v() && !a2.J()) {
            this.h.c(c0(a2), a2);
        }
        this.h.e(a2, cVar);
    }

    @Nullable
    public A W(int i2) {
        A a2 = null;
        if (this.D) {
            return null;
        }
        int iJ = this.g.j();
        for (int i3 = 0; i3 < iJ; i3++) {
            A aF0 = f0(this.g.i(i3));
            if (aF0 != null && !aF0.v() && b0(aF0) == i2) {
                if (!this.g.n(aF0.f1092a)) {
                    return aF0;
                }
                a2 = aF0;
            }
        }
        return a2;
    }

    public A X(long j2) {
        Adapter adapter = this.n;
        A a2 = null;
        if (adapter != null && adapter.j()) {
            int iJ = this.g.j();
            for (int i2 = 0; i2 < iJ; i2++) {
                A aF0 = f0(this.g.i(i2));
                if (aF0 != null && !aF0.v() && aF0.k() == j2) {
                    if (!this.g.n(aF0.f1092a)) {
                        return aF0;
                    }
                    a2 = aF0;
                }
            }
        }
        return a2;
    }

    void X0() {
        ItemAnimator itemAnimator = this.M;
        if (itemAnimator != null) {
            itemAnimator.k();
        }
        m mVar = this.mLayout;
        if (mVar != null) {
            mVar.h1(this.d);
            this.mLayout.i1(this.d);
        }
        this.d.c();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002a  */
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    androidx.recyclerview.widget.RecyclerView.A Y(int r6, boolean r7) {
        /*
            r5 = this;
            androidx.recyclerview.widget.d r0 = r5.g
            int r0 = r0.j()
            r1 = 0
            r2 = 0
        L8:
            if (r2 >= r0) goto L3a
            androidx.recyclerview.widget.d r3 = r5.g
            android.view.View r3 = r3.i(r2)
            androidx.recyclerview.widget.RecyclerView$A r3 = f0(r3)
            if (r3 == 0) goto L37
            boolean r4 = r3.v()
            if (r4 != 0) goto L37
            if (r7 == 0) goto L23
            int r4 = r3.f1094c
            if (r4 == r6) goto L2a
            goto L37
        L23:
            int r4 = r3.m()
            if (r4 == r6) goto L2a
            goto L37
        L2a:
            androidx.recyclerview.widget.d r1 = r5.g
            android.view.View r4 = r3.f1092a
            boolean r1 = r1.n(r4)
            if (r1 == 0) goto L36
            r1 = r3
            goto L37
        L36:
            return r3
        L37:
            int r2 = r2 + 1
            goto L8
        L3a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.Y(int, boolean):androidx.recyclerview.widget.RecyclerView$A");
    }

    boolean Y0(View view) {
        v1();
        boolean zR = this.g.r(view);
        if (zR) {
            A aF0 = f0(view);
            this.d.G(aF0);
            this.d.C(aF0);
        }
        x1(!zR);
        return zR;
    }

    public boolean Z(int i2, int i3) {
        m mVar = this.mLayout;
        if (mVar == null) {
            Log.e("RecyclerView", "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return false;
        }
        if (this.x) {
            return false;
        }
        boolean zJ = mVar.j();
        boolean zK = this.mLayout.k();
        if (!zJ || Math.abs(i2) < this.W) {
            i2 = 0;
        }
        if (!zK || Math.abs(i3) < this.W) {
            i3 = 0;
        }
        if (i2 == 0 && i3 == 0) {
            return false;
        }
        float f2 = i2;
        float f3 = i3;
        if (!dispatchNestedPreFling(f2, f3)) {
            boolean z2 = zJ || zK;
            dispatchNestedFling(f2, f3, z2);
            p pVar = this.V;
            if (pVar != null && pVar.a(i2, i3)) {
                return true;
            }
            if (z2) {
                int i4 = zJ ? 1 : 0;
                if (zK) {
                    i4 |= 2;
                }
                w1(i4, 1);
                int i5 = this.a0;
                int iMax = Math.max(-i5, Math.min(i2, i5));
                int i6 = this.a0;
                this.e0.c(iMax, Math.max(-i6, Math.min(i3, i6)));
                return true;
            }
        }
        return false;
    }

    public void Z0(@NonNull l lVar) {
        m mVar = this.mLayout;
        if (mVar != null) {
            mVar.f("Cannot remove item decoration during a scroll  or layout");
        }
        this.p.remove(lVar);
        if (this.p.isEmpty()) {
            setWillNotDraw(getOverScrollMode() == 2);
        }
        z0();
        requestLayout();
    }

    void a(int i2, int i3) {
        if (i2 < 0) {
            L();
            if (this.I.isFinished()) {
                this.I.onAbsorb(-i2);
            }
        } else if (i2 > 0) {
            M();
            if (this.K.isFinished()) {
                this.K.onAbsorb(i2);
            }
        }
        if (i3 < 0) {
            N();
            if (this.J.isFinished()) {
                this.J.onAbsorb(-i3);
            }
        } else if (i3 > 0) {
            K();
            if (this.L.isFinished()) {
                this.L.onAbsorb(i3);
            }
        }
        if (i2 == 0 && i3 == 0) {
            return;
        }
        ViewCompat.G(this);
    }

    @Nullable
    public Adapter a0() {
        return this.n;
    }

    public void a1(@NonNull q qVar) {
        this.q.remove(qVar);
        if (this.r == qVar) {
            this.r = null;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        m mVar = this.mLayout;
        if (mVar == null || !mVar.D0(this, arrayList, i2, i3)) {
            super.addFocusables(arrayList, i2, i3);
        }
    }

    int b0(A a2) {
        if (a2.p(524) || !a2.s()) {
            return -1;
        }
        return this.f.e(a2.f1094c);
    }

    public void b1(@NonNull r rVar) {
        List<r> list = this.j0;
        if (list != null) {
            list.remove(rVar);
        }
    }

    long c0(A a2) {
        return this.n.j() ? a2.k() : a2.f1094c;
    }

    void c1() {
        A a2;
        int iG = this.g.g();
        for (int i2 = 0; i2 < iG; i2++) {
            View viewF = this.g.f(i2);
            A aE0 = e0(viewF);
            if (aE0 != null && (a2 = aE0.i) != null) {
                View view = a2.f1092a;
                int left = viewF.getLeft();
                int top = viewF.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof n) && this.mLayout.l((n) layoutParams);
    }

    @Override // android.view.View
    public int computeHorizontalScrollExtent() {
        m mVar = this.mLayout;
        if (mVar != null && mVar.j()) {
            return this.mLayout.p(this.h0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeHorizontalScrollOffset() {
        m mVar = this.mLayout;
        if (mVar != null && mVar.j()) {
            return this.mLayout.q(this.h0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeHorizontalScrollRange() {
        m mVar = this.mLayout;
        if (mVar != null && mVar.j()) {
            return this.mLayout.r(this.h0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollExtent() {
        m mVar = this.mLayout;
        if (mVar != null && mVar.k()) {
            return this.mLayout.s(this.h0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollOffset() {
        m mVar = this.mLayout;
        if (mVar != null && mVar.k()) {
            return this.mLayout.t(this.h0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollRange() {
        m mVar = this.mLayout;
        if (mVar != null && mVar.k()) {
            return this.mLayout.u(this.h0);
        }
        return 0;
    }

    public int d0(@NonNull View view) {
        A aF0 = f0(view);
        if (aF0 != null) {
            return aF0.j();
        }
        return -1;
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float f2, float f3, boolean z2) {
        return n0().a(f2, f3, z2);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f2, float f3) {
        return n0().b(f2, f3);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2) {
        return n0().c(i2, i3, iArr, iArr2);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr) {
        return n0().f(i2, i3, i4, i5, iArr);
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean z2;
        super.draw(canvas);
        int size = this.p.size();
        boolean z3 = false;
        for (int i2 = 0; i2 < size; i2++) {
            this.p.get(i2).i(canvas, this, this.h0);
        }
        EdgeEffect edgeEffect = this.I;
        if (edgeEffect == null || edgeEffect.isFinished()) {
            z2 = false;
        } else {
            int iSave = canvas.save();
            int paddingBottom = this.i ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((-getHeight()) + paddingBottom, 0.0f);
            EdgeEffect edgeEffect2 = this.I;
            z2 = edgeEffect2 != null && edgeEffect2.draw(canvas);
            canvas.restoreToCount(iSave);
        }
        EdgeEffect edgeEffect3 = this.J;
        if (edgeEffect3 != null && !edgeEffect3.isFinished()) {
            int iSave2 = canvas.save();
            if (this.i) {
                canvas.translate(getPaddingLeft(), getPaddingTop());
            }
            EdgeEffect edgeEffect4 = this.J;
            z2 |= edgeEffect4 != null && edgeEffect4.draw(canvas);
            canvas.restoreToCount(iSave2);
        }
        EdgeEffect edgeEffect5 = this.K;
        if (edgeEffect5 != null && !edgeEffect5.isFinished()) {
            int iSave3 = canvas.save();
            int width = getWidth();
            int paddingTop = this.i ? getPaddingTop() : 0;
            canvas.rotate(90.0f);
            canvas.translate(-paddingTop, -width);
            EdgeEffect edgeEffect6 = this.K;
            z2 |= edgeEffect6 != null && edgeEffect6.draw(canvas);
            canvas.restoreToCount(iSave3);
        }
        EdgeEffect edgeEffect7 = this.L;
        if (edgeEffect7 != null && !edgeEffect7.isFinished()) {
            int iSave4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.i) {
                canvas.translate((-getWidth()) + getPaddingRight(), (-getHeight()) + getPaddingBottom());
            } else {
                canvas.translate(-getWidth(), -getHeight());
            }
            EdgeEffect edgeEffect8 = this.L;
            if (edgeEffect8 != null && edgeEffect8.draw(canvas)) {
                z3 = true;
            }
            z2 |= z3;
            canvas.restoreToCount(iSave4);
        }
        if ((z2 || this.M == null || this.p.size() <= 0 || !this.M.p()) ? z2 : true) {
            ViewCompat.G(this);
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j2) {
        return super.drawChild(canvas, view, j2);
    }

    public A e0(@NonNull View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this) {
            return f0(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public View focusSearch(View view, int i2) {
        View viewH0;
        boolean z2;
        View viewO0 = this.mLayout.O0(view, i2);
        if (viewO0 != null) {
            return viewO0;
        }
        boolean z3 = (this.n == null || this.mLayout == null || x0() || this.x) ? false : true;
        FocusFinder focusFinder = FocusFinder.getInstance();
        if (z3 && (i2 == 2 || i2 == 1)) {
            if (this.mLayout.k()) {
                int i3 = i2 == 2 ? 130 : 33;
                z2 = focusFinder.findNextFocus(this, view, i3) == null;
                if (C0) {
                    i2 = i3;
                }
            } else {
                z2 = false;
            }
            if (!z2 && this.mLayout.j()) {
                int i4 = (this.mLayout.X() == 1) ^ (i2 == 2) ? 66 : 17;
                boolean z4 = focusFinder.findNextFocus(this, view, i4) == null;
                if (C0) {
                    i2 = i4;
                }
                z2 = z4;
            }
            if (z2) {
                t();
                if (Q(view) == null) {
                    return null;
                }
                v1();
                this.mLayout.H0(view, i2, this.d, this.h0);
                x1(false);
            }
            viewH0 = focusFinder.findNextFocus(this, view, i2);
        } else {
            View viewFindNextFocus = focusFinder.findNextFocus(this, view, i2);
            if (viewFindNextFocus == null && z3) {
                t();
                if (Q(view) == null) {
                    return null;
                }
                v1();
                viewH0 = this.mLayout.H0(view, i2, this.d, this.h0);
                x1(false);
            } else {
                viewH0 = viewFindNextFocus;
            }
        }
        if (viewH0 == null || viewH0.hasFocusable()) {
            return y0(view, viewH0, i2) ? viewH0 : super.focusSearch(view, i2);
        }
        if (getFocusedChild() == null) {
            return super.focusSearch(view, i2);
        }
        d1(viewH0, null);
        return view;
    }

    public void g(@NonNull l lVar) {
        h(lVar, -1);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        m mVar = this.mLayout;
        if (mVar != null) {
            return mVar.C();
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + O());
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        m mVar = this.mLayout;
        if (mVar != null) {
            return mVar.D(getContext(), attributeSet);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + O());
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return "androidx.recyclerview.widget.RecyclerView";
    }

    @Override // android.view.View
    public int getBaseline() {
        m mVar = this.mLayout;
        return mVar != null ? mVar.F() : super.getBaseline();
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i2, int i3) {
        j jVar = this.p0;
        return jVar == null ? super.getChildDrawingOrder(i2, i3) : jVar.a(i2, i3);
    }

    @Override // android.view.ViewGroup
    public boolean getClipToPadding() {
        return this.i;
    }

    public void h(@NonNull l lVar, int i2) {
        m mVar = this.mLayout;
        if (mVar != null) {
            mVar.f("Cannot add item decoration during a scroll  or layout");
        }
        if (this.p.isEmpty()) {
            setWillNotDraw(false);
        }
        if (i2 < 0) {
            this.p.add(lVar);
        } else {
            this.p.add(i2, lVar);
        }
        z0();
        requestLayout();
    }

    void h1() {
        int iJ = this.g.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            A aF0 = f0(this.g.i(i2));
            if (!aF0.J()) {
                aF0.E();
            }
        }
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return n0().j();
    }

    public void i(@NonNull q qVar) {
        this.q.add(qVar);
    }

    boolean i1(int i2, int i3, MotionEvent motionEvent, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        t();
        if (this.n != null) {
            int[] iArr = this.u0;
            iArr[0] = 0;
            iArr[1] = 0;
            j1(i2, i3, iArr);
            int[] iArr2 = this.u0;
            int i9 = iArr2[0];
            int i10 = iArr2[1];
            i5 = i10;
            i6 = i9;
            i7 = i2 - i9;
            i8 = i3 - i10;
        } else {
            i5 = 0;
            i6 = 0;
            i7 = 0;
            i8 = 0;
        }
        if (!this.p.isEmpty()) {
            invalidate();
        }
        int[] iArr3 = this.u0;
        iArr3[0] = 0;
        iArr3[1] = 0;
        F(i6, i5, i7, i8, this.s0, i4, iArr3);
        int[] iArr4 = this.u0;
        int i11 = i7 - iArr4[0];
        int i12 = i8 - iArr4[1];
        boolean z2 = (iArr4[0] == 0 && iArr4[1] == 0) ? false : true;
        int i13 = this.S;
        int[] iArr5 = this.s0;
        this.S = i13 - iArr5[0];
        this.T -= iArr5[1];
        int[] iArr6 = this.t0;
        iArr6[0] = iArr6[0] + iArr5[0];
        iArr6[1] = iArr6[1] + iArr5[1];
        if (getOverScrollMode() != 2) {
            if (motionEvent != null && !androidx.core.view.i.a(motionEvent, 8194)) {
                T0(motionEvent.getX(), i11, motionEvent.getY(), i12);
            }
            s(i2, i3);
        }
        if (i6 != 0 || i5 != 0) {
            H(i6, i5);
        }
        if (!awakenScrollBars()) {
            invalidate();
        }
        return (!z2 && i6 == 0 && i5 == 0) ? false : true;
    }

    @VisibleForTesting
    void initFastScroller(StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2) {
        if (stateListDrawable != null && drawable != null && stateListDrawable2 != null && drawable2 != null) {
            Resources resources = getContext().getResources();
            new FastScroller(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(R$dimen.fastscroll_default_thickness), resources.getDimensionPixelSize(R$dimen.fastscroll_minimum_range), resources.getDimensionPixelOffset(R$dimen.fastscroll_margin));
        } else {
            throw new IllegalArgumentException("Trying to set fast scroller without both required drawables." + O());
        }
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return this.s;
    }

    @Override // android.view.ViewGroup
    public final boolean isLayoutSuppressed() {
        return this.x;
    }

    @Override // android.view.View
    public boolean isNestedScrollingEnabled() {
        return n0().l();
    }

    public void j(@NonNull r rVar) {
        if (this.j0 == null) {
            this.j0 = new ArrayList();
        }
        this.j0.add(rVar);
    }

    Rect j0(View view) {
        n nVar = (n) view.getLayoutParams();
        if (!nVar.f1120c) {
            return nVar.f1119b;
        }
        if (this.h0.e() && (nVar.b() || nVar.d())) {
            return nVar.f1119b;
        }
        Rect rect = nVar.f1119b;
        rect.set(0, 0, 0, 0);
        int size = this.p.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.k.set(0, 0, 0, 0);
            this.p.get(i2).e(this.k, view, this, this.h0);
            int i3 = rect.left;
            Rect rect2 = this.k;
            rect.left = i3 + rect2.left;
            rect.top += rect2.top;
            rect.right += rect2.right;
            rect.bottom += rect2.bottom;
        }
        nVar.f1120c = false;
        return rect;
    }

    void j1(int i2, int i3, @Nullable int[] iArr) {
        v1();
        J0();
        androidx.core.os.c.a("RV Scroll");
        P(this.h0);
        int iT1 = i2 != 0 ? this.mLayout.t1(i2, this.d, this.h0) : 0;
        int iU1 = i3 != 0 ? this.mLayout.u1(i3, this.d, this.h0) : 0;
        androidx.core.os.c.b();
        c1();
        K0();
        x1(false);
        if (iArr != null) {
            iArr[0] = iT1;
            iArr[1] = iU1;
        }
    }

    void k(@NonNull A a2, @Nullable ItemAnimator.c cVar, @NonNull ItemAnimator.c cVar2) {
        a2.G(false);
        if (this.M.a(a2, cVar, cVar2)) {
            P0();
        }
    }

    @Nullable
    public m k0() {
        return this.mLayout;
    }

    public void k1(@Nullable androidx.recyclerview.widget.l lVar) {
        this.o0 = lVar;
        ViewCompat.M(this, lVar);
    }

    long l0() {
        if (B0) {
            return System.nanoTime();
        }
        return 0L;
    }

    public void l1(@Nullable Adapter adapter) {
        n1(false);
        m1(adapter, false, true);
        S0(false);
        requestLayout();
    }

    void m(@NonNull A a2, @NonNull ItemAnimator.c cVar, @Nullable ItemAnimator.c cVar2) {
        f(a2);
        a2.G(false);
        if (this.M.c(a2, cVar, cVar2)) {
            P0();
        }
    }

    public int m0() {
        return this.N;
    }

    void n(String str) {
        if (x0()) {
            if (str != null) {
                throw new IllegalStateException(str);
            }
            throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling" + O());
        }
        if (this.G > 0) {
            Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException("" + O()));
        }
    }

    @Deprecated
    public void n1(boolean z2) {
        suppressLayout(z2);
    }

    boolean o(A a2) {
        ItemAnimator itemAnimator = this.M;
        return itemAnimator == null || itemAnimator.g(a2, a2.o());
    }

    public void o1(@Nullable m mVar) {
        if (mVar == this.mLayout) {
            return;
        }
        z1();
        if (this.mLayout != null) {
            ItemAnimator itemAnimator = this.M;
            if (itemAnimator != null) {
                itemAnimator.k();
            }
            this.mLayout.h1(this.d);
            this.mLayout.i1(this.d);
            this.d.c();
            if (this.s) {
                this.mLayout.z(this, this.d);
            }
            this.mLayout.A1(null);
            this.mLayout = null;
        } else {
            this.d.c();
        }
        this.g.o();
        this.mLayout = mVar;
        if (mVar != null) {
            if (mVar.f1111b != null) {
                throw new IllegalArgumentException("LayoutManager " + mVar + " is already attached to a RecyclerView:" + mVar.f1111b.O());
            }
            mVar.A1(this);
            if (this.s) {
                this.mLayout.y(this);
            }
        }
        this.d.H();
        requestLayout();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.F = 0;
        this.s = true;
        this.mFirstLayoutComplete = this.mFirstLayoutComplete && !isLayoutRequested();
        m mVar = this.mLayout;
        if (mVar != null) {
            mVar.y(this);
        }
        this.n0 = false;
        if (B0) {
            androidx.recyclerview.widget.g gVar = androidx.recyclerview.widget.g.g.get();
            this.f0 = gVar;
            if (gVar == null) {
                this.f0 = new androidx.recyclerview.widget.g();
                Display displayN = ViewCompat.n(this);
                float f2 = 60.0f;
                if (!isInEditMode() && displayN != null) {
                    float refreshRate = displayN.getRefreshRate();
                    if (refreshRate >= 30.0f) {
                        f2 = refreshRate;
                    }
                }
                androidx.recyclerview.widget.g gVar2 = this.f0;
                gVar2.e = (long) (1.0E9f / f2);
                androidx.recyclerview.widget.g.g.set(gVar2);
            }
            this.f0.a(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        androidx.recyclerview.widget.g gVar;
        super.onDetachedFromWindow();
        ItemAnimator itemAnimator = this.M;
        if (itemAnimator != null) {
            itemAnimator.k();
        }
        z1();
        this.s = false;
        m mVar = this.mLayout;
        if (mVar != null) {
            mVar.z(this, this.d);
        }
        this.mPendingAccessibilityImportanceChange.clear();
        removeCallbacks(this.v0);
        this.h.j();
        if (!B0 || (gVar = this.f0) == null) {
            return;
        }
        gVar.j(this);
        this.f0 = null;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = this.p.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.p.get(i2).g(canvas, this, this.h0);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006c  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onGenericMotionEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            androidx.recyclerview.widget.RecyclerView$m r0 = r5.mLayout
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            boolean r0 = r5.x
            if (r0 == 0) goto Lb
            return r1
        Lb:
            int r0 = r6.getAction()
            r2 = 8
            if (r0 != r2) goto L78
            int r0 = r6.getSource()
            r0 = r0 & 2
            r2 = 0
            if (r0 == 0) goto L3e
            androidx.recyclerview.widget.RecyclerView$m r0 = r5.mLayout
            boolean r0 = r0.k()
            if (r0 == 0) goto L2c
            r0 = 9
            float r0 = r6.getAxisValue(r0)
            float r0 = -r0
            goto L2d
        L2c:
            r0 = r2
        L2d:
            androidx.recyclerview.widget.RecyclerView$m r3 = r5.mLayout
            boolean r3 = r3.j()
            if (r3 == 0) goto L3c
            r3 = 10
            float r3 = r6.getAxisValue(r3)
            goto L64
        L3c:
            r3 = r2
            goto L64
        L3e:
            int r0 = r6.getSource()
            r3 = 4194304(0x400000, float:5.877472E-39)
            r0 = r0 & r3
            if (r0 == 0) goto L62
            r0 = 26
            float r0 = r6.getAxisValue(r0)
            androidx.recyclerview.widget.RecyclerView$m r3 = r5.mLayout
            boolean r3 = r3.k()
            if (r3 == 0) goto L57
            float r0 = -r0
            goto L3c
        L57:
            androidx.recyclerview.widget.RecyclerView$m r3 = r5.mLayout
            boolean r3 = r3.j()
            if (r3 == 0) goto L62
            r3 = r0
            r0 = r2
            goto L64
        L62:
            r0 = r2
            r3 = r0
        L64:
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L6c
            int r2 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r2 == 0) goto L78
        L6c:
            float r2 = r5.b0
            float r3 = r3 * r2
            int r2 = (int) r3
            float r3 = r5.c0
            float r0 = r0 * r3
            int r0 = (int) r0
            r3 = 1
            r5.B0(r2, r0, r6, r3)
        L78:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onGenericMotionEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z2;
        if (this.x) {
            return false;
        }
        this.r = null;
        if (S(motionEvent)) {
            p();
            return true;
        }
        m mVar = this.mLayout;
        if (mVar == null) {
            return false;
        }
        boolean zJ = mVar.j();
        boolean zK = this.mLayout.k();
        if (this.P == null) {
            this.P = VelocityTracker.obtain();
        }
        this.P.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            if (this.y) {
                this.y = false;
            }
            this.O = motionEvent.getPointerId(0);
            int x2 = (int) (motionEvent.getX() + 0.5f);
            this.S = x2;
            this.Q = x2;
            int y2 = (int) (motionEvent.getY() + 0.5f);
            this.T = y2;
            this.R = y2;
            if (this.N == 2) {
                getParent().requestDisallowInterceptTouchEvent(true);
                p1(1);
                y1(1);
            }
            int[] iArr = this.t0;
            iArr[1] = 0;
            iArr[0] = 0;
            int i2 = zJ ? 1 : 0;
            if (zK) {
                i2 |= 2;
            }
            w1(i2, 0);
        } else if (actionMasked == 1) {
            this.P.clear();
            y1(0);
        } else if (actionMasked == 2) {
            int iFindPointerIndex = motionEvent.findPointerIndex(this.O);
            if (iFindPointerIndex < 0) {
                Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.O + " not found. Did any MotionEvents get skipped?");
                return false;
            }
            int x3 = (int) (motionEvent.getX(iFindPointerIndex) + 0.5f);
            int y3 = (int) (motionEvent.getY(iFindPointerIndex) + 0.5f);
            if (this.N != 1) {
                int i3 = x3 - this.Q;
                int i4 = y3 - this.R;
                if (!zJ || Math.abs(i3) <= this.U) {
                    z2 = false;
                } else {
                    this.S = x3;
                    z2 = true;
                }
                if (zK && Math.abs(i4) > this.U) {
                    this.T = y3;
                    z2 = true;
                }
                if (z2) {
                    p1(1);
                }
            }
        } else if (actionMasked == 3) {
            p();
        } else if (actionMasked == 5) {
            this.O = motionEvent.getPointerId(actionIndex);
            int x4 = (int) (motionEvent.getX(actionIndex) + 0.5f);
            this.S = x4;
            this.Q = x4;
            int y4 = (int) (motionEvent.getY(actionIndex) + 0.5f);
            this.T = y4;
            this.R = y4;
        } else if (actionMasked == 6) {
            M0(motionEvent);
        }
        return this.N == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        androidx.core.os.c.a("RV OnLayout");
        A();
        androidx.core.os.c.b();
        this.mFirstLayoutComplete = true;
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        m mVar = this.mLayout;
        if (mVar == null) {
            v(i2, i3);
            return;
        }
        boolean z2 = false;
        if (mVar.q0()) {
            int mode = View.MeasureSpec.getMode(i2);
            int mode2 = View.MeasureSpec.getMode(i3);
            this.mLayout.X0(this.d, this.h0, i2, i3);
            if (mode == 1073741824 && mode2 == 1073741824) {
                z2 = true;
            }
            if (z2 || this.n == null) {
                return;
            }
            if (this.h0.e == 1) {
                B();
            }
            this.mLayout.w1(i2, i3);
            this.h0.j = true;
            C();
            this.mLayout.z1(i2, i3);
            if (this.mLayout.C1()) {
                this.mLayout.w1(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
                this.h0.j = true;
                C();
                this.mLayout.z1(i2, i3);
                return;
            }
            return;
        }
        if (this.t) {
            this.mLayout.X0(this.d, this.h0, i2, i3);
            return;
        }
        if (this.A) {
            v1();
            J0();
            R0();
            K0();
            x xVar = this.h0;
            if (xVar.l) {
                xVar.h = true;
            } else {
                this.f.j();
                this.h0.h = false;
            }
            this.A = false;
            x1(false);
        } else if (this.h0.l) {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
            return;
        }
        Adapter adapter = this.n;
        if (adapter != null) {
            this.h0.f = adapter.f();
        } else {
            this.h0.f = 0;
        }
        v1();
        this.mLayout.X0(this.d, this.h0, i2, i3);
        x1(false);
        this.h0.h = false;
    }

    @Override // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int i2, Rect rect) {
        if (x0()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i2, rect);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        this.e = savedState;
        super.onRestoreInstanceState(savedState.a());
        requestLayout();
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SavedState savedState2 = this.e;
        if (savedState2 != null) {
            savedState.b(savedState2);
        } else {
            m mVar = this.mLayout;
            if (mVar != null) {
                savedState.e = mVar.b1();
            } else {
                savedState.e = null;
            }
        }
        return savedState;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 == i4 && i3 == i5) {
            return;
        }
        u0();
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00df A[PHI: r0
  0x00df: PHI (r0v42 int) = (r0v31 int), (r0v46 int) binds: [B:41:0x00c8, B:45:0x00db] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00f8  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r18) {
        /*
            Method dump skipped, instruction units count: 490
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public boolean p0() {
        return !this.mFirstLayoutComplete || this.D || this.f.p();
    }

    void p1(int i2) {
        if (i2 == this.N) {
            return;
        }
        this.N = i2;
        if (i2 != 2) {
            A1();
        }
        G(i2);
    }

    boolean q1(AccessibilityEvent accessibilityEvent) {
        if (!x0()) {
            return false;
        }
        int iA = accessibilityEvent != null ? AccessibilityEventCompat.a(accessibilityEvent) : 0;
        this.z |= iA != 0 ? iA : 0;
        return true;
    }

    void r() {
        int iJ = this.g.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            A aF0 = f0(this.g.i(i2));
            if (!aF0.J()) {
                aF0.c();
            }
        }
        this.d.d();
    }

    void r0() {
        this.f = new a(new f());
    }

    public void r1(@Px int i2, @Px int i3) {
        s1(i2, i3, null);
    }

    @Override // android.view.ViewGroup
    protected void removeDetachedView(View view, boolean z2) {
        A aF0 = f0(view);
        if (aF0 != null) {
            if (aF0.x()) {
                aF0.f();
            } else if (!aF0.J()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + aF0 + O());
            }
        }
        view.clearAnimation();
        y(view);
        super.removeDetachedView(view, z2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        if (!this.mLayout.Z0(this, this.h0, view, view2) && view2 != null) {
            d1(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z2) {
        return this.mLayout.o1(this, view, rect, z2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z2) {
        int size = this.q.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.q.get(i2).c(z2);
        }
        super.requestDisallowInterceptTouchEvent(z2);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.v != 0 || this.x) {
            this.w = true;
        } else {
            super.requestLayout();
        }
    }

    void s(int i2, int i3) {
        boolean zIsFinished;
        EdgeEffect edgeEffect = this.I;
        if (edgeEffect == null || edgeEffect.isFinished() || i2 <= 0) {
            zIsFinished = false;
        } else {
            this.I.onRelease();
            zIsFinished = this.I.isFinished();
        }
        EdgeEffect edgeEffect2 = this.K;
        if (edgeEffect2 != null && !edgeEffect2.isFinished() && i2 < 0) {
            this.K.onRelease();
            zIsFinished |= this.K.isFinished();
        }
        EdgeEffect edgeEffect3 = this.J;
        if (edgeEffect3 != null && !edgeEffect3.isFinished() && i3 > 0) {
            this.J.onRelease();
            zIsFinished |= this.J.isFinished();
        }
        EdgeEffect edgeEffect4 = this.L;
        if (edgeEffect4 != null && !edgeEffect4.isFinished() && i3 < 0) {
            this.L.onRelease();
            zIsFinished |= this.L.isFinished();
        }
        if (zIsFinished) {
            ViewCompat.G(this);
        }
    }

    public void s1(@Px int i2, @Px int i3, @Nullable Interpolator interpolator) {
        t1(i2, i3, interpolator, Integer.MIN_VALUE);
    }

    @Override // android.view.View
    public void scrollBy(int i2, int i3) {
        m mVar = this.mLayout;
        if (mVar == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.x) {
            return;
        }
        boolean zJ = mVar.j();
        boolean zK = this.mLayout.k();
        if (zJ || zK) {
            if (!zJ) {
                i2 = 0;
            }
            if (!zK) {
                i3 = 0;
            }
            i1(i2, i3, null, 0);
        }
    }

    @Override // android.view.View
    public void scrollTo(int i2, int i3) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (q1(accessibilityEvent)) {
            return;
        }
        super.sendAccessibilityEventUnchecked(accessibilityEvent);
    }

    @VisibleForTesting
    boolean setChildImportantForAccessibilityInternal(A a2, int i2) {
        if (!x0()) {
            ViewCompat.R(a2.f1092a, i2);
            return true;
        }
        a2.mPendingAccessibilityState = i2;
        this.mPendingAccessibilityImportanceChange.add(a2);
        return false;
    }

    @Override // android.view.ViewGroup
    public void setClipToPadding(boolean z2) {
        if (z2 != this.i) {
            u0();
        }
        this.i = z2;
        super.setClipToPadding(z2);
        if (this.mFirstLayoutComplete) {
            requestLayout();
        }
    }

    @Override // android.view.ViewGroup
    @Deprecated
    public void setLayoutTransition(LayoutTransition layoutTransition) {
        if (Build.VERSION.SDK_INT < 18) {
            if (layoutTransition == null) {
                suppressLayout(false);
                return;
            } else if (layoutTransition.getAnimator(0) == null && layoutTransition.getAnimator(1) == null && layoutTransition.getAnimator(2) == null && layoutTransition.getAnimator(3) == null && layoutTransition.getAnimator(4) == null) {
                suppressLayout(true);
                return;
            }
        }
        if (layoutTransition != null) {
            throw new IllegalArgumentException("Providing a LayoutTransition into RecyclerView is not supported. Please use setItemAnimator() instead for animating changes to the items in this RecyclerView");
        }
        super.setLayoutTransition(null);
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z2) {
        n0().m(z2);
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i2) {
        return n0().o(i2);
    }

    @Override // android.view.View, androidx.core.view.j
    public void stopNestedScroll() {
        n0().q();
    }

    @Override // android.view.ViewGroup
    public final void suppressLayout(boolean z2) {
        if (z2 != this.x) {
            n("Do not suppressLayout in layout or scroll");
            if (z2) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                onTouchEvent(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0));
                this.x = true;
                this.y = true;
                z1();
                return;
            }
            this.x = false;
            if (this.w && this.mLayout != null && this.n != null) {
                requestLayout();
            }
            this.w = false;
        }
    }

    void t() {
        if (!this.mFirstLayoutComplete || this.D) {
            androidx.core.os.c.a("RV FullInvalidate");
            A();
            androidx.core.os.c.b();
            return;
        }
        if (this.f.p()) {
            if (!this.f.o(4) || this.f.o(11)) {
                if (this.f.p()) {
                    androidx.core.os.c.a("RV FullInvalidate");
                    A();
                    androidx.core.os.c.b();
                    return;
                }
                return;
            }
            androidx.core.os.c.a("RV PartialInvalidate");
            v1();
            J0();
            this.f.w();
            if (!this.w) {
                if (q0()) {
                    A();
                } else {
                    this.f.i();
                }
            }
            x1(true);
            K0();
            androidx.core.os.c.b();
        }
    }

    public void t1(@Px int i2, @Px int i3, @Nullable Interpolator interpolator, int i4) {
        u1(i2, i3, interpolator, i4, false);
    }

    void u0() {
        this.L = null;
        this.J = null;
        this.K = null;
        this.I = null;
    }

    void u1(@Px int i2, @Px int i3, @Nullable Interpolator interpolator, int i4, boolean z2) {
        m mVar = this.mLayout;
        if (mVar == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.x) {
            return;
        }
        if (!mVar.j()) {
            i2 = 0;
        }
        if (!this.mLayout.k()) {
            i3 = 0;
        }
        if (i2 == 0 && i3 == 0) {
            return;
        }
        if (!(i4 == Integer.MIN_VALUE || i4 > 0)) {
            scrollBy(i2, i3);
            return;
        }
        if (z2) {
            int i5 = i2 != 0 ? 1 : 0;
            if (i3 != 0) {
                i5 |= 2;
            }
            w1(i5, 1);
        }
        this.e0.f(i2, i3, i4, interpolator);
    }

    void v(int i2, int i3) {
        setMeasuredDimension(m.m(i2, getPaddingLeft() + getPaddingRight(), ViewCompat.s(this)), m.m(i3, getPaddingTop() + getPaddingBottom(), ViewCompat.r(this)));
    }

    public void v0() {
        if (this.p.size() == 0) {
            return;
        }
        m mVar = this.mLayout;
        if (mVar != null) {
            mVar.f("Cannot invalidate item decorations during a scroll or layout");
        }
        z0();
        requestLayout();
    }

    void v1() {
        int i2 = this.v + 1;
        this.v = i2;
        if (i2 != 1 || this.x) {
            return;
        }
        this.w = false;
    }

    boolean w0() {
        AccessibilityManager accessibilityManager = this.B;
        return accessibilityManager != null && accessibilityManager.isEnabled();
    }

    public boolean w1(int i2, int i3) {
        return n0().p(i2, i3);
    }

    void x(View view) {
        A aF0 = f0(view);
        H0(view);
        Adapter adapter = this.n;
        if (adapter != null && aF0 != null) {
            adapter.w(aF0);
        }
        List<o> list = this.C;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.C.get(size).a(view);
            }
        }
    }

    public boolean x0() {
        return this.F > 0;
    }

    void x1(boolean z2) {
        if (this.v < 1) {
            this.v = 1;
        }
        if (!z2 && !this.x) {
            this.w = false;
        }
        if (this.v == 1) {
            if (z2 && this.w && !this.x && this.mLayout != null && this.n != null) {
                A();
            }
            if (!this.x) {
                this.w = false;
            }
        }
        this.v--;
    }

    void y(View view) {
        A aF0 = f0(view);
        I0(view);
        Adapter adapter = this.n;
        if (adapter != null && aF0 != null) {
            adapter.x(aF0);
        }
        List<o> list = this.C;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.C.get(size).b(view);
            }
        }
    }

    public void y1(int i2) {
        n0().r(i2);
    }

    void z0() {
        int iJ = this.g.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            ((n) this.g.i(i2).getLayoutParams()).f1120c = true;
        }
        this.d.s();
    }

    public void z1() {
        p1(0);
        A1();
    }

    public RecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.recyclerViewStyle);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        Parcelable e;

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

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.e = parcel.readParcelable(classLoader == null ? m.class.getClassLoader() : classLoader);
        }

        void b(SavedState savedState) {
            this.e = savedState.e;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(this.e, 0);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public RecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f1091c = new v();
        this.d = new t();
        this.h = new androidx.recyclerview.widget.o();
        this.j = new RunnableC0084a();
        this.k = new Rect();
        this.l = new Rect();
        this.m = new RectF();
        this.p = new ArrayList<>();
        this.q = new ArrayList<>();
        this.v = 0;
        this.D = false;
        this.E = false;
        this.F = 0;
        this.G = 0;
        this.H = new EdgeEffectFactory();
        this.M = new androidx.recyclerview.widget.e();
        this.N = 0;
        this.O = -1;
        this.b0 = Float.MIN_VALUE;
        this.c0 = Float.MIN_VALUE;
        boolean z2 = true;
        this.d0 = true;
        this.e0 = new z();
        this.g0 = B0 ? new g.b() : null;
        this.h0 = new x();
        this.k0 = false;
        this.l0 = false;
        this.m0 = new k();
        this.n0 = false;
        this.q0 = new int[2];
        this.s0 = new int[2];
        this.t0 = new int[2];
        this.u0 = new int[2];
        this.mPendingAccessibilityImportanceChange = new ArrayList();
        this.v0 = new b();
        this.w0 = new d();
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.U = viewConfiguration.getScaledTouchSlop();
        this.b0 = androidx.core.view.s.b(viewConfiguration, context);
        this.c0 = androidx.core.view.s.d(viewConfiguration, context);
        this.W = viewConfiguration.getScaledMinimumFlingVelocity();
        this.a0 = viewConfiguration.getScaledMaximumFlingVelocity();
        setWillNotDraw(getOverScrollMode() == 2);
        this.M.v(this.m0);
        r0();
        t0();
        s0();
        if (ViewCompat.o(this) == 0) {
            ViewCompat.R(this, 1);
        }
        this.B = (AccessibilityManager) getContext().getSystemService("accessibility");
        k1(new androidx.recyclerview.widget.l(this));
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RecyclerView, i2, 0);
        ViewCompat.K(this, context, R$styleable.RecyclerView, attributeSet, typedArrayObtainStyledAttributes, i2, 0);
        String string = typedArrayObtainStyledAttributes.getString(R$styleable.RecyclerView_layoutManager);
        if (typedArrayObtainStyledAttributes.getInt(R$styleable.RecyclerView_android_descendantFocusability, -1) == -1) {
            setDescendantFocusability(262144);
        }
        this.i = typedArrayObtainStyledAttributes.getBoolean(R$styleable.RecyclerView_android_clipToPadding, true);
        boolean z3 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.RecyclerView_fastScrollEnabled, false);
        this.u = z3;
        if (z3) {
            initFastScroller((StateListDrawable) typedArrayObtainStyledAttributes.getDrawable(R$styleable.RecyclerView_fastScrollVerticalThumbDrawable), typedArrayObtainStyledAttributes.getDrawable(R$styleable.RecyclerView_fastScrollVerticalTrackDrawable), (StateListDrawable) typedArrayObtainStyledAttributes.getDrawable(R$styleable.RecyclerView_fastScrollHorizontalThumbDrawable), typedArrayObtainStyledAttributes.getDrawable(R$styleable.RecyclerView_fastScrollHorizontalTrackDrawable));
        }
        typedArrayObtainStyledAttributes.recycle();
        u(context, string, attributeSet, i2, 0);
        if (Build.VERSION.SDK_INT >= 21) {
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, x0, i2, 0);
            ViewCompat.K(this, context, x0, attributeSet, typedArrayObtainStyledAttributes2, i2, 0);
            z2 = typedArrayObtainStyledAttributes2.getBoolean(0, true);
            typedArrayObtainStyledAttributes2.recycle();
        }
        setNestedScrollingEnabled(z2);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class n extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        A f1118a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Rect f1119b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f1120c;
        boolean d;

        public n(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1119b = new Rect();
            this.f1120c = true;
            this.d = false;
        }

        public int a() {
            return this.f1118a.m();
        }

        public boolean b() {
            return this.f1118a.y();
        }

        public boolean c() {
            return this.f1118a.v();
        }

        public boolean d() {
            return this.f1118a.t();
        }

        public n(int i, int i2) {
            super(i, i2);
            this.f1119b = new Rect();
            this.f1120c = true;
            this.d = false;
        }

        public n(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1119b = new Rect();
            this.f1120c = true;
            this.d = false;
        }

        public n(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1119b = new Rect();
            this.f1120c = true;
            this.d = false;
        }

        public n(n nVar) {
            super((ViewGroup.LayoutParams) nVar);
            this.f1119b = new Rect();
            this.f1120c = true;
            this.d = false;
        }
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        m mVar = this.mLayout;
        if (mVar != null) {
            return mVar.E(layoutParams);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + O());
    }
}
