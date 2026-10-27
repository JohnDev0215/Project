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
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
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
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.EdgeEffectCompat;
import androidx.customview.view.AbsSavedState;
import androidx.recyclerview.R$attr;
import androidx.recyclerview.R$dimen;
import androidx.recyclerview.R$styleable;
import androidx.recyclerview.widget.a;
import androidx.recyclerview.widget.b;
import androidx.recyclerview.widget.b0;
import androidx.recyclerview.widget.c0;
import androidx.recyclerview.widget.k;
import androidx.recyclerview.widget.x;
import defpackage.C0173a;
import defpackage.C0175b;
import defpackage.C0214v;
import defpackage.gq2;
import defpackage.in3;
import defpackage.om3;
import defpackage.q22;
import defpackage.ql1;
import defpackage.r22;
import defpackage.rl1;
import defpackage.s22;
import defpackage.wm2;
import defpackage.z7;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class RecyclerView extends ViewGroup implements ql1 {
    public static boolean F0 = false;
    public static boolean G0 = false;
    public static final int[] H0 = {R.attr.nestedScrollingEnabled};
    public static final float I0 = (float) (Math.log(0.78d) / Math.log(0.9d));
    public static final boolean J0 = true;
    public static final boolean K0 = true;
    public static final boolean L0 = true;
    public static final Class<?>[] M0;
    public static final c N0;
    public static final x O0;
    public int A;
    public final b A0;
    public boolean B;
    public boolean B0;
    public boolean C;
    public int C0;
    public boolean D;
    public int D0;
    public int E;
    public final d E0;
    public boolean F;
    public final AccessibilityManager G;
    public ArrayList H;
    public boolean I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public boolean f19J;
    public int K;
    public int L;
    public i M;
    public EdgeEffect N;
    public EdgeEffect O;
    public EdgeEffect P;
    public EdgeEffect Q;
    public j R;
    public int S;
    public int T;
    public VelocityTracker U;
    public int V;
    public int W;
    public int a0;
    public int b0;
    public int c0;
    public o d0;
    public final float e;
    public final int e0;
    public final u f;
    public final int f0;
    public final s g;
    public final float g0;
    public SavedState h;
    public final float h0;
    public androidx.recyclerview.widget.a i;
    public boolean i0;
    public androidx.recyclerview.widget.b j;
    public final z j0;
    public final c0 k;
    public androidx.recyclerview.widget.k k0;
    public boolean l;
    public final k.b l0;
    public final a m;
    public final w m0;
    public final Rect n;
    public q n0;
    public final Rect o;
    public ArrayList o0;
    public final RectF p;
    public boolean p0;
    public e q;
    public boolean q0;
    public m r;
    public final k r0;
    public t s;
    public boolean s0;
    public final ArrayList t;
    public androidx.recyclerview.widget.x t0;
    public final ArrayList<l> u;
    public final int[] u0;
    public final ArrayList<p> v;
    public rl1 v0;
    public p w;
    public final int[] w0;
    public boolean x;
    public final int[] x0;
    public boolean y;
    public final int[] y0;
    public boolean z;
    public final ArrayList z0;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            RecyclerView recyclerView = RecyclerView.this;
            if (!recyclerView.z || recyclerView.isLayoutRequested()) {
                return;
            }
            if (!recyclerView.x) {
                recyclerView.requestLayout();
            } else if (recyclerView.C) {
                recyclerView.B = true;
            } else {
                recyclerView.m();
            }
        }
    }

    public static abstract class a0 {
        public static final List<Object> x = Collections.emptyList();
        public final View e;
        public WeakReference<RecyclerView> f;
        public int n;
        public RecyclerView v;
        public e<? extends a0> w;
        public int g = -1;
        public int h = -1;
        public long i = -1;
        public int j = -1;
        public int k = -1;
        public a0 l = null;
        public a0 m = null;
        public ArrayList o = null;
        public List<Object> p = null;
        public int q = 0;
        public s r = null;
        public boolean s = false;
        public int t = 0;
        public int u = -1;

        public a0(View view) {
            if (view == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.e = view;
        }

        public final void c(Object obj) {
            if (obj == null) {
                d(1024);
                return;
            }
            if ((1024 & this.n) == 0) {
                if (this.o == null) {
                    ArrayList arrayList = new ArrayList();
                    this.o = arrayList;
                    this.p = Collections.unmodifiableList(arrayList);
                }
                this.o.add(obj);
            }
        }

        public final void d(int i) {
            this.n = i | this.n;
        }

        public final int e() {
            RecyclerView recyclerView = this.v;
            if (recyclerView == null) {
                return -1;
            }
            return recyclerView.G(this);
        }

        public final int f() {
            RecyclerView recyclerView;
            e<? extends a0> adapter;
            int iG;
            if (this.w == null || (recyclerView = this.v) == null || (adapter = recyclerView.getAdapter()) == null || (iG = this.v.G(this)) == -1 || this.w != adapter) {
                return -1;
            }
            return iG;
        }

        public final int g() {
            int i = this.k;
            return i == -1 ? this.g : i;
        }

        public final List<Object> h() {
            ArrayList arrayList;
            return ((this.n & 1024) != 0 || (arrayList = this.o) == null || arrayList.size() == 0) ? x : this.p;
        }

        public final boolean i() {
            View view = this.e;
            return (view.getParent() == null || view.getParent() == this.v) ? false : true;
        }

        final boolean j() {
            return (this.n & 1) != 0;
        }

        public final boolean k() {
            return (this.n & 4) != 0;
        }

        public final boolean l() {
            if ((this.n & 16) == 0) {
                WeakHashMap<View, in3> weakHashMap = om3.a;
                if (!om3.d.h(this.e)) {
                    return true;
                }
            }
            return false;
        }

        public final boolean m() {
            return (this.n & 8) != 0;
        }

        public final boolean n() {
            return this.r != null;
        }

        public final boolean o() {
            return (this.n & 256) != 0;
        }

        public final void p(int i, boolean z) {
            if (this.h == -1) {
                this.h = this.g;
            }
            if (this.k == -1) {
                this.k = this.g;
            }
            if (z) {
                this.k += i;
            }
            this.g += i;
            View view = this.e;
            if (view.getLayoutParams() != null) {
                ((LayoutParams) view.getLayoutParams()).c = true;
            }
        }

        public final void q() {
            this.n = 0;
            this.g = -1;
            this.h = -1;
            this.i = -1L;
            this.k = -1;
            this.q = 0;
            this.l = null;
            this.m = null;
            ArrayList arrayList = this.o;
            if (arrayList != null) {
                arrayList.clear();
            }
            this.n &= -1025;
            this.t = 0;
            this.u = -1;
            RecyclerView.i(this);
        }

        public final void r(boolean z) {
            int i = this.q;
            int i2 = z ? i - 1 : i + 1;
            this.q = i2;
            if (i2 < 0) {
                this.q = 0;
                if (RecyclerView.F0) {
                    throw new RuntimeException("isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
                }
                Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
            } else if (!z && i2 == 1) {
                this.n |= 16;
            } else if (z && i2 == 0) {
                this.n &= -17;
            }
            if (RecyclerView.G0) {
                toString();
            }
        }

        public final boolean s() {
            return (this.n & 128) != 0;
        }

        public final boolean t() {
            return (this.n & 32) != 0;
        }

        public final String toString() {
            StringBuilder sbK = C0173a.k(getClass().isAnonymousClass() ? "ViewHolder" : getClass().getSimpleName(), "{");
            sbK.append(Integer.toHexString(hashCode()));
            sbK.append(" position=");
            sbK.append(this.g);
            sbK.append(" id=");
            sbK.append(this.i);
            sbK.append(", oldPos=");
            sbK.append(this.h);
            sbK.append(", pLpos:");
            sbK.append(this.k);
            StringBuilder sb = new StringBuilder(sbK.toString());
            if (n()) {
                sb.append(" scrap ");
                sb.append(this.s ? "[changeScrap]" : "[attachedScrap]");
            }
            if (k()) {
                sb.append(" invalid");
            }
            if (!j()) {
                sb.append(" unbound");
            }
            boolean z = true;
            if ((this.n & 2) != 0) {
                sb.append(" update");
            }
            if (m()) {
                sb.append(" removed");
            }
            if (s()) {
                sb.append(" ignored");
            }
            if (o()) {
                sb.append(" tmpDetached");
            }
            if (!l()) {
                sb.append(" not recyclable(" + this.q + ")");
            }
            if ((this.n & 512) == 0 && !k()) {
                z = false;
            }
            if (z) {
                sb.append(" undefined adapter position");
            }
            if (this.e.getParent() == null) {
                sb.append(" no parent");
            }
            sb.append("}");
            return sb.toString();
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            RecyclerView recyclerView = RecyclerView.this;
            j jVar = recyclerView.R;
            if (jVar != null) {
                jVar.l();
            }
            recyclerView.s0 = false;
        }
    }

    public class c implements Interpolator {
        @Override // android.animation.TimeInterpolator
        public final float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    }

    public class d {
        public d() {
        }
    }

    public static class f extends Observable<g> {
        public final boolean a() {
            return !((Observable) this).mObservers.isEmpty();
        }

        public final void b() {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((g) ((Observable) this).mObservers.get(size)).a();
            }
        }

        public final void c(int i, int i2) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((g) ((Observable) this).mObservers.get(size)).e(i, i2);
            }
        }

        public final void d(int i, int i2, Object obj) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((g) ((Observable) this).mObservers.get(size)).c(i, i2, obj);
            }
        }

        public final void e(int i, int i2) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((g) ((Observable) this).mObservers.get(size)).d(i, i2);
            }
        }

        public final void f(int i, int i2) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((g) ((Observable) this).mObservers.get(size)).f(i, i2);
            }
        }
    }

    public interface h {
    }

    public static class i {
    }

    public static abstract class j {
        public b a = null;
        public final ArrayList<a> b = new ArrayList<>();
        public final long c = 120;
        public final long d = 120;
        public long e = 250;
        public long f = 250;

        public interface a {
            void a();
        }

        public interface b {
        }

        public static class c {
            public int a;
            public int b;

            public final void a(a0 a0Var) {
                View view = a0Var.e;
                this.a = view.getLeft();
                this.b = view.getTop();
                view.getRight();
                view.getBottom();
            }
        }

        public static void e(a0 a0Var) {
            int i = a0Var.n & 14;
            if (!a0Var.k() && (i & 4) == 0) {
                a0Var.e();
            }
        }

        public abstract boolean a(a0 a0Var, c cVar, c cVar2);

        public abstract boolean b(a0 a0Var, a0 a0Var2, c cVar, c cVar2);

        public abstract boolean c(a0 a0Var, c cVar, c cVar2);

        public abstract boolean d(a0 a0Var, c cVar, c cVar2);

        public boolean f(a0 a0Var) {
            return true;
        }

        public boolean g(a0 a0Var, List<Object> list) {
            return f(a0Var);
        }

        public final void h(a0 a0Var) {
            b bVar = this.a;
            if (bVar != null) {
                k kVar = (k) bVar;
                boolean z = true;
                a0Var.r(true);
                if (a0Var.l != null && a0Var.m == null) {
                    a0Var.l = null;
                }
                a0Var.m = null;
                if ((a0Var.n & 16) != 0) {
                    return;
                }
                RecyclerView recyclerView = RecyclerView.this;
                recyclerView.l0();
                androidx.recyclerview.widget.b bVar2 = recyclerView.j;
                androidx.recyclerview.widget.v vVar = (androidx.recyclerview.widget.v) bVar2.a;
                RecyclerView recyclerView2 = vVar.a;
                View view = a0Var.e;
                int iIndexOfChild = recyclerView2.indexOfChild(view);
                if (iIndexOfChild == -1) {
                    bVar2.k(view);
                } else {
                    b.a aVar = bVar2.b;
                    if (aVar.d(iIndexOfChild)) {
                        aVar.f(iIndexOfChild);
                        bVar2.k(view);
                        vVar.b(iIndexOfChild);
                    } else {
                        z = false;
                    }
                }
                if (z) {
                    a0 a0VarK = RecyclerView.K(view);
                    s sVar = recyclerView.g;
                    sVar.l(a0VarK);
                    sVar.i(a0VarK);
                    if (RecyclerView.G0) {
                        Objects.toString(view);
                        recyclerView.toString();
                    }
                }
                recyclerView.m0(!z);
                if (z || !a0Var.o()) {
                    return;
                }
                recyclerView.removeDetachedView(view, false);
            }
        }

        public abstract void i(a0 a0Var);

        public abstract void j();

        public abstract boolean k();

        public abstract void l();
    }

    public class k implements j.b {
        public k() {
        }
    }

    public interface n {
        void c(View view);

        void d(View view);
    }

    public static abstract class o {
    }

    public interface p {
        void a(MotionEvent motionEvent);

        boolean b(MotionEvent motionEvent);

        void e(boolean z);
    }

    public static class r {
        public final SparseArray<a> a = new SparseArray<>();
        public int b = 0;
        public final Set<e<?>> c = Collections.newSetFromMap(new IdentityHashMap());

        public static class a {
            public final ArrayList<a0> a = new ArrayList<>();
            public int b = 5;
            public long c = 0;
            public long d = 0;
        }

        public final void a() {
            int i = 0;
            while (true) {
                SparseArray<a> sparseArray = this.a;
                if (i >= sparseArray.size()) {
                    return;
                }
                a aVarValueAt = sparseArray.valueAt(i);
                Iterator<a0> it = aVarValueAt.a.iterator();
                while (it.hasNext()) {
                    q22.a(it.next().e);
                }
                aVarValueAt.a.clear();
                i++;
            }
        }

        public a0 b(int i) {
            a aVar = this.a.get(i);
            if (aVar == null) {
                return null;
            }
            ArrayList<a0> arrayList = aVar.a;
            if (arrayList.isEmpty()) {
                return null;
            }
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                if (!arrayList.get(size).i()) {
                    return arrayList.remove(size);
                }
            }
            return null;
        }

        public final a c(int i) {
            SparseArray<a> sparseArray = this.a;
            a aVar = sparseArray.get(i);
            if (aVar != null) {
                return aVar;
            }
            a aVar2 = new a();
            sparseArray.put(i, aVar2);
            return aVar2;
        }

        public final void d(int i, int i2) {
            a aVarC = c(i);
            aVarC.b = i2;
            ArrayList<a0> arrayList = aVarC.a;
            while (arrayList.size() > i2) {
                arrayList.remove(arrayList.size() - 1);
            }
        }
    }

    public final class s {
        public final ArrayList<a0> a;
        public ArrayList<a0> b;
        public final ArrayList<a0> c;
        public final List<a0> d;
        public int e;
        public int f;
        public r g;

        public s() {
            ArrayList<a0> arrayList = new ArrayList<>();
            this.a = arrayList;
            this.b = null;
            this.c = new ArrayList<>();
            this.d = Collections.unmodifiableList(arrayList);
            this.e = 2;
            this.f = 2;
        }

        public final void a(a0 a0Var, boolean z) {
            RecyclerView.i(a0Var);
            RecyclerView recyclerView = RecyclerView.this;
            androidx.recyclerview.widget.x xVar = recyclerView.t0;
            View view = a0Var.e;
            if (xVar != null) {
                C0214v c0214vJ = xVar.j();
                om3.k(view, c0214vJ instanceof x.a ? (C0214v) ((x.a) c0214vJ).e.remove(view) : null);
            }
            if (z) {
                t tVar = recyclerView.s;
                if (tVar != null) {
                    tVar.h(a0Var);
                }
                ArrayList arrayList = recyclerView.t;
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    ((t) arrayList.get(i)).h(a0Var);
                }
                e eVar = recyclerView.q;
                if (eVar != null) {
                    eVar.z(a0Var);
                }
                if (recyclerView.m0 != null) {
                    recyclerView.k.d(a0Var);
                }
                if (RecyclerView.G0) {
                    Objects.toString(a0Var);
                }
            }
            a0Var.w = null;
            a0Var.v = null;
            r rVarC = c();
            rVarC.getClass();
            int i2 = a0Var.j;
            ArrayList<a0> arrayList2 = rVarC.c(i2).a;
            if (rVarC.a.get(i2).b <= arrayList2.size()) {
                q22.a(view);
            } else {
                if (RecyclerView.F0 && arrayList2.contains(a0Var)) {
                    throw new IllegalArgumentException("this scrap item already exists");
                }
                a0Var.q();
                arrayList2.add(a0Var);
            }
        }

        public final int b(int i) {
            RecyclerView recyclerView = RecyclerView.this;
            if (i >= 0 && i < recyclerView.m0.b()) {
                return !recyclerView.m0.g ? i : recyclerView.i.f(i, 0);
            }
            StringBuilder sbM = C0175b.m("invalid position ", i, ". State item count is ");
            sbM.append(recyclerView.m0.b());
            sbM.append(recyclerView.z());
            throw new IndexOutOfBoundsException(sbM.toString());
        }

        public final r c() {
            if (this.g == null) {
                this.g = new r();
                d();
            }
            return this.g;
        }

        public final void d() {
            if (this.g != null) {
                RecyclerView recyclerView = RecyclerView.this;
                if (recyclerView.q == null || !recyclerView.isAttachedToWindow()) {
                    return;
                }
                r rVar = this.g;
                rVar.c.add(recyclerView.q);
            }
        }

        public final void e(e<?> eVar, boolean z) {
            r rVar = this.g;
            if (rVar == null) {
                return;
            }
            Set<e<?>> set = rVar.c;
            set.remove(eVar);
            if (set.size() != 0 || z) {
                return;
            }
            int i = 0;
            while (true) {
                SparseArray<r.a> sparseArray = rVar.a;
                if (i >= sparseArray.size()) {
                    return;
                }
                ArrayList<a0> arrayList = sparseArray.get(sparseArray.keyAt(i)).a;
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    q22.a(arrayList.get(i2).e);
                }
                i++;
            }
        }

        public final void f() {
            ArrayList<a0> arrayList = this.c;
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                g(size);
            }
            arrayList.clear();
            if (RecyclerView.L0) {
                k.b bVar = RecyclerView.this.l0;
                int[] iArr = bVar.c;
                if (iArr != null) {
                    Arrays.fill(iArr, -1);
                }
                bVar.d = 0;
            }
        }

        public final void g(int i) {
            boolean z = RecyclerView.F0;
            ArrayList<a0> arrayList = this.c;
            a0 a0Var = arrayList.get(i);
            if (RecyclerView.G0) {
                Objects.toString(a0Var);
            }
            a(a0Var, true);
            arrayList.remove(i);
        }

        public final void h(View view) {
            a0 a0VarK = RecyclerView.K(view);
            boolean zO = a0VarK.o();
            RecyclerView recyclerView = RecyclerView.this;
            if (zO) {
                recyclerView.removeDetachedView(view, false);
            }
            if (a0VarK.n()) {
                a0VarK.r.l(a0VarK);
            } else if (a0VarK.t()) {
                a0VarK.n &= -33;
            }
            i(a0VarK);
            if (recyclerView.R == null || a0VarK.l()) {
                return;
            }
            recyclerView.R.i(a0VarK);
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0030  */
        /* JADX WARN: Removed duplicated region for block: B:63:0x00b5 A[LOOP:1: B:63:0x00b5->B:102:?, LOOP_START, PHI: r5
  0x00b5: PHI (r5v15 int) = (r5v12 int), (r5v16 int) binds: [B:62:0x00b3, B:102:?] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:77:0x00e0  */
        /* JADX WARN: Removed duplicated region for block: B:79:0x00e3  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void i(androidx.recyclerview.widget.RecyclerView.a0 r13) {
            /*
                Method dump skipped, instruction units count: 336
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.s.i(androidx.recyclerview.widget.RecyclerView$a0):void");
        }

        public final void j(View view) {
            a0 a0VarK = RecyclerView.K(view);
            int i = a0VarK.n;
            boolean z = (i & 12) != 0;
            RecyclerView recyclerView = RecyclerView.this;
            if (!z) {
                if ((i & 2) != 0) {
                    j jVar = recyclerView.R;
                    if (!(jVar == null || jVar.g(a0VarK, a0VarK.h()))) {
                        if (this.b == null) {
                            this.b = new ArrayList<>();
                        }
                        a0VarK.r = this;
                        a0VarK.s = true;
                        this.b.add(a0VarK);
                        return;
                    }
                }
            }
            if (a0VarK.k() && !a0VarK.m() && !recyclerView.q.f) {
                throw new IllegalArgumentException(C0175b.h(recyclerView, new StringBuilder("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool.")));
            }
            a0VarK.r = this;
            a0VarK.s = false;
            this.a.add(a0VarK);
        }

        /* JADX WARN: Removed duplicated region for block: B:103:0x01b2  */
        /* JADX WARN: Removed duplicated region for block: B:127:0x0206  */
        /* JADX WARN: Removed duplicated region for block: B:142:0x024a  */
        /* JADX WARN: Removed duplicated region for block: B:222:0x03be  */
        /* JADX WARN: Removed duplicated region for block: B:223:0x03c0  */
        /* JADX WARN: Removed duplicated region for block: B:225:0x03c3  */
        /* JADX WARN: Removed duplicated region for block: B:233:0x03f0  */
        /* JADX WARN: Removed duplicated region for block: B:235:0x03f6  */
        /* JADX WARN: Removed duplicated region for block: B:253:0x0446  */
        /* JADX WARN: Removed duplicated region for block: B:262:0x0464  */
        /* JADX WARN: Removed duplicated region for block: B:318:0x053b  */
        /* JADX WARN: Removed duplicated region for block: B:319:0x0545  */
        /* JADX WARN: Removed duplicated region for block: B:325:0x055c A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:327:0x055f  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x0080  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x008b  */
        /* JADX WARN: Removed duplicated region for block: B:77:0x012b  */
        /* JADX WARN: Removed duplicated region for block: B:80:0x0134  */
        /* JADX WARN: Removed duplicated region for block: B:81:0x0142  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final androidx.recyclerview.widget.RecyclerView.a0 k(long r19, int r21) {
            /*
                Method dump skipped, instruction units count: 1428
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.s.k(long, int):androidx.recyclerview.widget.RecyclerView$a0");
        }

        public final void l(a0 a0Var) {
            if (a0Var.s) {
                this.b.remove(a0Var);
            } else {
                this.a.remove(a0Var);
            }
            a0Var.r = null;
            a0Var.s = false;
            a0Var.n &= -33;
        }

        public final void m() {
            m mVar = RecyclerView.this.r;
            this.f = this.e + (mVar != null ? mVar.j : 0);
            ArrayList<a0> arrayList = this.c;
            for (int size = arrayList.size() - 1; size >= 0 && arrayList.size() > this.f; size--) {
                g(size);
            }
        }
    }

    public interface t {
        void h(a0 a0Var);
    }

    public class u extends g {
        public u() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public final void a() {
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.h(null);
            recyclerView.m0.f = true;
            recyclerView.X(true);
            if (recyclerView.i.g()) {
                return;
            }
            recyclerView.requestLayout();
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0028  */
        /* JADX WARN: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
        @Override // androidx.recyclerview.widget.RecyclerView.g
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void c(int r5, int r6, java.lang.Object r7) {
            /*
                r4 = this;
                androidx.recyclerview.widget.RecyclerView r0 = androidx.recyclerview.widget.RecyclerView.this
                r1 = 0
                r0.h(r1)
                androidx.recyclerview.widget.a r0 = r0.i
                r1 = 1
                if (r6 >= r1) goto Lf
                r0.getClass()
                goto L25
            Lf:
                java.util.ArrayList<androidx.recyclerview.widget.a$b> r2 = r0.b
                r3 = 4
                androidx.recyclerview.widget.a$b r5 = r0.h(r3, r5, r6, r7)
                r2.add(r5)
                int r5 = r0.f
                r5 = r5 | r3
                r0.f = r5
                int r5 = r2.size()
                if (r5 != r1) goto L25
                goto L26
            L25:
                r1 = 0
            L26:
                if (r1 == 0) goto L2b
                r4.g()
            L2b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.u.c(int, int, java.lang.Object):void");
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0027  */
        /* JADX WARN: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
        @Override // androidx.recyclerview.widget.RecyclerView.g
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void d(int r5, int r6) {
            /*
                r4 = this;
                androidx.recyclerview.widget.RecyclerView r0 = androidx.recyclerview.widget.RecyclerView.this
                r1 = 0
                r0.h(r1)
                androidx.recyclerview.widget.a r0 = r0.i
                r2 = 1
                if (r6 >= r2) goto Lf
                r0.getClass()
                goto L24
            Lf:
                java.util.ArrayList<androidx.recyclerview.widget.a$b> r3 = r0.b
                androidx.recyclerview.widget.a$b r5 = r0.h(r2, r5, r6, r1)
                r3.add(r5)
                int r5 = r0.f
                r5 = r5 | r2
                r0.f = r5
                int r5 = r3.size()
                if (r5 != r2) goto L24
                goto L25
            L24:
                r2 = 0
            L25:
                if (r2 == 0) goto L2a
                r4.g()
            L2a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.u.d(int, int):void");
        }

        /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
        @Override // androidx.recyclerview.widget.RecyclerView.g
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void e(int r5, int r6) {
            /*
                r4 = this;
                androidx.recyclerview.widget.RecyclerView r0 = androidx.recyclerview.widget.RecyclerView.this
                r1 = 0
                r0.h(r1)
                androidx.recyclerview.widget.a r0 = r0.i
                r0.getClass()
                if (r5 != r6) goto Le
                goto L26
            Le:
                java.util.ArrayList<androidx.recyclerview.widget.a$b> r2 = r0.b
                r3 = 8
                androidx.recyclerview.widget.a$b r5 = r0.h(r3, r5, r6, r1)
                r2.add(r5)
                int r5 = r0.f
                r5 = r5 | r3
                r0.f = r5
                int r5 = r2.size()
                r6 = 1
                if (r5 != r6) goto L26
                goto L27
            L26:
                r6 = 0
            L27:
                if (r6 == 0) goto L2c
                r4.g()
            L2c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.u.e(int, int):void");
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0028  */
        /* JADX WARN: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
        @Override // androidx.recyclerview.widget.RecyclerView.g
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void f(int r6, int r7) {
            /*
                r5 = this;
                androidx.recyclerview.widget.RecyclerView r0 = androidx.recyclerview.widget.RecyclerView.this
                r1 = 0
                r0.h(r1)
                androidx.recyclerview.widget.a r0 = r0.i
                r2 = 1
                if (r7 >= r2) goto Lf
                r0.getClass()
                goto L25
            Lf:
                java.util.ArrayList<androidx.recyclerview.widget.a$b> r3 = r0.b
                r4 = 2
                androidx.recyclerview.widget.a$b r6 = r0.h(r4, r6, r7, r1)
                r3.add(r6)
                int r6 = r0.f
                r6 = r6 | r4
                r0.f = r6
                int r6 = r3.size()
                if (r6 != r2) goto L25
                goto L26
            L25:
                r2 = 0
            L26:
                if (r2 == 0) goto L2b
                r5.g()
            L2b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.u.f(int, int):void");
        }

        public final void g() {
            boolean z = RecyclerView.K0;
            RecyclerView recyclerView = RecyclerView.this;
            if (z && recyclerView.y && recyclerView.x) {
                WeakHashMap<View, in3> weakHashMap = om3.a;
                om3.d.k(recyclerView, recyclerView.m);
            } else {
                recyclerView.F = true;
                recyclerView.requestLayout();
            }
        }
    }

    public static abstract class v {
        public RecyclerView b;
        public m c;
        public boolean d;
        public boolean e;
        public View f;
        public boolean h;
        public int a = -1;
        public final a g = new a();

        public static class a {
            public int d = -1;
            public boolean f = false;
            public int g = 0;
            public int a = 0;
            public int b = 0;
            public int c = Integer.MIN_VALUE;
            public Interpolator e = null;

            public final void a(RecyclerView recyclerView) {
                int i = this.d;
                if (i >= 0) {
                    this.d = -1;
                    recyclerView.P(i);
                    this.f = false;
                    return;
                }
                if (!this.f) {
                    this.g = 0;
                    return;
                }
                Interpolator interpolator = this.e;
                if (interpolator != null && this.c < 1) {
                    throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
                }
                int i2 = this.c;
                if (i2 < 1) {
                    throw new IllegalStateException("Scroll duration must be a positive number");
                }
                recyclerView.j0.c(this.a, this.b, i2, interpolator);
                int i3 = this.g + 1;
                this.g = i3;
                if (i3 > 10) {
                    Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                }
                this.f = false;
            }
        }

        public interface b {
            PointF a(int i);
        }

        public final PointF a(int i) {
            Object obj = this.c;
            if (obj instanceof b) {
                return ((b) obj).a(i);
            }
            Log.w("RecyclerView", "You should override computeScrollVectorForPosition when the LayoutManager does not implement " + b.class.getCanonicalName());
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:50:0x00fb  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void b(int r9, int r10) {
            /*
                Method dump skipped, instruction units count: 280
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.v.b(int, int):void");
        }

        public abstract void c(View view, a aVar);

        public final void d() {
            if (this.e) {
                this.e = false;
                LinearSmoothScroller linearSmoothScroller = (LinearSmoothScroller) this;
                linearSmoothScroller.p = 0;
                linearSmoothScroller.o = 0;
                linearSmoothScroller.k = null;
                this.b.m0.a = -1;
                this.f = null;
                this.a = -1;
                this.d = false;
                m mVar = this.c;
                if (mVar.e == this) {
                    mVar.e = null;
                }
                this.c = null;
                this.b = null;
            }
        }
    }

    public static class w {
        public int a = -1;
        public int b = 0;
        public int c = 0;
        public int d = 1;
        public int e = 0;
        public boolean f = false;
        public boolean g = false;
        public boolean h = false;
        public boolean i = false;
        public boolean j = false;
        public boolean k = false;
        public int l;
        public long m;
        public int n;

        public final void a(int i) {
            if ((this.d & i) != 0) {
                return;
            }
            throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(i) + " but it is " + Integer.toBinaryString(this.d));
        }

        public final int b() {
            return this.g ? this.b - this.c : this.e;
        }

        public final String toString() {
            return "State{mTargetPosition=" + this.a + ", mData=null, mItemCount=" + this.e + ", mIsMeasuring=" + this.i + ", mPreviousLayoutItemCount=" + this.b + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.c + ", mStructureChanged=" + this.f + ", mInPreLayout=" + this.g + ", mRunSimpleAnimations=" + this.j + ", mRunPredictiveAnimations=" + this.k + '}';
        }
    }

    public static class x extends i {
    }

    public static abstract class y {
    }

    public class z implements Runnable {
        public int e;
        public int f;
        public OverScroller g;
        public Interpolator h;
        public boolean i;
        public boolean j;

        public z() {
            c cVar = RecyclerView.N0;
            this.h = cVar;
            this.i = false;
            this.j = false;
            this.g = new OverScroller(RecyclerView.this.getContext(), cVar);
        }

        public final void a(int i, int i2) {
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.setScrollState(2);
            this.f = 0;
            this.e = 0;
            Interpolator interpolator = this.h;
            c cVar = RecyclerView.N0;
            if (interpolator != cVar) {
                this.h = cVar;
                this.g = new OverScroller(recyclerView.getContext(), cVar);
            }
            this.g.fling(0, 0, i, i2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            b();
        }

        public final void b() {
            if (this.i) {
                this.j = true;
                return;
            }
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.removeCallbacks(this);
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.d.k(recyclerView, this);
        }

        public final void c(int i, int i2, int i3, Interpolator interpolator) {
            RecyclerView recyclerView = RecyclerView.this;
            if (i3 == Integer.MIN_VALUE) {
                int iAbs = Math.abs(i);
                int iAbs2 = Math.abs(i2);
                boolean z = iAbs > iAbs2;
                int width = z ? recyclerView.getWidth() : recyclerView.getHeight();
                if (!z) {
                    iAbs = iAbs2;
                }
                i3 = Math.min((int) (((iAbs / width) + 1.0f) * 300.0f), 2000);
            }
            int i4 = i3;
            if (interpolator == null) {
                interpolator = RecyclerView.N0;
            }
            if (this.h != interpolator) {
                this.h = interpolator;
                this.g = new OverScroller(recyclerView.getContext(), interpolator);
            }
            this.f = 0;
            this.e = 0;
            recyclerView.setScrollState(2);
            this.g.startScroll(0, 0, i, i2, i4);
            b();
        }

        @Override // java.lang.Runnable
        public final void run() {
            int i;
            int i2;
            int i3;
            int i4;
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.r == null) {
                recyclerView.removeCallbacks(this);
                this.g.abortAnimation();
                return;
            }
            this.j = false;
            this.i = true;
            recyclerView.m();
            OverScroller overScroller = this.g;
            if (overScroller.computeScrollOffset()) {
                int currX = overScroller.getCurrX();
                int currY = overScroller.getCurrY();
                int i5 = currX - this.e;
                int i6 = currY - this.f;
                this.e = currX;
                this.f = currY;
                int iL = RecyclerView.l(i5, recyclerView.N, recyclerView.P, recyclerView.getWidth());
                int iL2 = RecyclerView.l(i6, recyclerView.O, recyclerView.Q, recyclerView.getHeight());
                int[] iArr = recyclerView.y0;
                iArr[0] = 0;
                iArr[1] = 0;
                boolean zS = recyclerView.s(iL, iL2, 1, iArr, null);
                int[] iArr2 = recyclerView.y0;
                if (zS) {
                    iL -= iArr2[0];
                    iL2 -= iArr2[1];
                }
                if (recyclerView.getOverScrollMode() != 2) {
                    recyclerView.k(iL, iL2);
                }
                if (recyclerView.q != null) {
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    recyclerView.f0(iL, iL2, iArr2);
                    int i7 = iArr2[0];
                    int i8 = iArr2[1];
                    int i9 = iL - i7;
                    int i10 = iL2 - i8;
                    v vVar = recyclerView.r.e;
                    if (vVar != null && !vVar.d && vVar.e) {
                        int iB = recyclerView.m0.b();
                        if (iB == 0) {
                            vVar.d();
                        } else if (vVar.a >= iB) {
                            vVar.a = iB - 1;
                            vVar.b(i7, i8);
                        } else {
                            vVar.b(i7, i8);
                        }
                    }
                    i4 = i7;
                    i = i9;
                    i2 = i10;
                    i3 = i8;
                } else {
                    i = iL;
                    i2 = iL2;
                    i3 = 0;
                    i4 = 0;
                }
                if (!recyclerView.u.isEmpty()) {
                    recyclerView.invalidate();
                }
                int[] iArr3 = recyclerView.y0;
                iArr3[0] = 0;
                iArr3[1] = 0;
                int i11 = i3;
                recyclerView.t(i4, i3, i, i2, null, 1, iArr3);
                int i12 = i - iArr2[0];
                int i13 = i2 - iArr2[1];
                if (i4 != 0 || i11 != 0) {
                    recyclerView.u(i4, i11);
                }
                if (!recyclerView.awakenScrollBars()) {
                    recyclerView.invalidate();
                }
                boolean z = overScroller.isFinished() || (((overScroller.getCurrX() == overScroller.getFinalX()) || i12 != 0) && ((overScroller.getCurrY() == overScroller.getFinalY()) || i13 != 0));
                v vVar2 = recyclerView.r.e;
                if ((vVar2 != null && vVar2.d) || !z) {
                    b();
                    androidx.recyclerview.widget.k kVar = recyclerView.k0;
                    if (kVar != null) {
                        kVar.a(recyclerView, i4, i11);
                    }
                } else {
                    if (recyclerView.getOverScrollMode() != 2) {
                        int currVelocity = (int) overScroller.getCurrVelocity();
                        int i14 = i12 < 0 ? -currVelocity : i12 > 0 ? currVelocity : 0;
                        if (i13 < 0) {
                            currVelocity = -currVelocity;
                        } else if (i13 <= 0) {
                            currVelocity = 0;
                        }
                        if (i14 < 0) {
                            recyclerView.w();
                            if (recyclerView.N.isFinished()) {
                                recyclerView.N.onAbsorb(-i14);
                            }
                        } else if (i14 > 0) {
                            recyclerView.x();
                            if (recyclerView.P.isFinished()) {
                                recyclerView.P.onAbsorb(i14);
                            }
                        }
                        if (currVelocity < 0) {
                            recyclerView.y();
                            if (recyclerView.O.isFinished()) {
                                recyclerView.O.onAbsorb(-currVelocity);
                            }
                        } else if (currVelocity > 0) {
                            recyclerView.v();
                            if (recyclerView.Q.isFinished()) {
                                recyclerView.Q.onAbsorb(currVelocity);
                            }
                        }
                        if (i14 != 0 || currVelocity != 0) {
                            WeakHashMap<View, in3> weakHashMap = om3.a;
                            om3.d.j(recyclerView);
                        }
                    }
                    if (RecyclerView.L0) {
                        k.b bVar = recyclerView.l0;
                        int[] iArr4 = bVar.c;
                        if (iArr4 != null) {
                            Arrays.fill(iArr4, -1);
                        }
                        bVar.d = 0;
                    }
                }
            }
            v vVar3 = recyclerView.r.e;
            if (vVar3 != null && vVar3.d) {
                vVar3.b(0, 0);
            }
            this.i = false;
            if (!this.j) {
                recyclerView.setScrollState(0);
                recyclerView.n0(1);
            } else {
                recyclerView.removeCallbacks(this);
                WeakHashMap<View, in3> weakHashMap2 = om3.a;
                om3.d.k(recyclerView, this);
            }
        }
    }

    static {
        Class<?> cls = Integer.TYPE;
        M0 = new Class[]{Context.class, AttributeSet.class, cls, cls};
        N0 = new c();
        O0 = new x();
    }

    public RecyclerView(Context context) {
        this(context, null);
    }

    public static RecyclerView E(View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view instanceof RecyclerView) {
            return (RecyclerView) view;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            RecyclerView recyclerViewE = E(viewGroup.getChildAt(i2));
            if (recyclerViewE != null) {
                return recyclerViewE;
            }
        }
        return null;
    }

    public static int I(View view) {
        a0 a0VarK = K(view);
        if (a0VarK != null) {
            return a0VarK.e();
        }
        return -1;
    }

    public static a0 K(View view) {
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).a;
    }

    public static void L(View view, Rect rect) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        Rect rect2 = layoutParams.b;
        rect.set((view.getLeft() - rect2.left) - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, (view.getTop() - rect2.top) - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, view.getRight() + rect2.right + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, view.getBottom() + rect2.bottom + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
    }

    private int b0(float f2, int i2) {
        float width = f2 / getWidth();
        float height = i2 / getHeight();
        EdgeEffect edgeEffect = this.O;
        float f3 = 0.0f;
        if (edgeEffect == null || EdgeEffectCompat.a(edgeEffect) == 0.0f) {
            EdgeEffect edgeEffect2 = this.Q;
            if (edgeEffect2 != null && EdgeEffectCompat.a(edgeEffect2) != 0.0f) {
                if (canScrollVertically(1)) {
                    this.Q.onRelease();
                } else {
                    float fB = EdgeEffectCompat.b(this.Q, height, 1.0f - width);
                    if (EdgeEffectCompat.a(this.Q) == 0.0f) {
                        this.Q.onRelease();
                    }
                    f3 = fB;
                }
                invalidate();
            }
        } else {
            if (canScrollVertically(-1)) {
                this.O.onRelease();
            } else {
                float f4 = -EdgeEffectCompat.b(this.O, -height, width);
                if (EdgeEffectCompat.a(this.O) == 0.0f) {
                    this.O.onRelease();
                }
                f3 = f4;
            }
            invalidate();
        }
        return Math.round(f3 * getHeight());
    }

    private rl1 getScrollingChildHelper() {
        if (this.v0 == null) {
            this.v0 = new rl1(this);
        }
        return this.v0;
    }

    public static void i(a0 a0Var) {
        WeakReference<RecyclerView> weakReference = a0Var.f;
        if (weakReference != null) {
            RecyclerView recyclerView = weakReference.get();
            while (recyclerView != null) {
                if (recyclerView == a0Var.e) {
                    return;
                }
                Object parent = recyclerView.getParent();
                recyclerView = parent instanceof View ? (View) parent : null;
            }
            a0Var.f = null;
        }
    }

    public static int l(int i2, EdgeEffect edgeEffect, EdgeEffect edgeEffect2, int i3) {
        if (i2 > 0 && edgeEffect != null && EdgeEffectCompat.a(edgeEffect) != 0.0f) {
            int iRound = Math.round(EdgeEffectCompat.b(edgeEffect, ((-i2) * 4.0f) / i3, 0.5f) * ((-i3) / 4.0f));
            if (iRound != i2) {
                edgeEffect.finish();
            }
            return i2 - iRound;
        }
        if (i2 >= 0 || edgeEffect2 == null || EdgeEffectCompat.a(edgeEffect2) == 0.0f) {
            return i2;
        }
        float f2 = i3;
        int iRound2 = Math.round(EdgeEffectCompat.b(edgeEffect2, (i2 * 4.0f) / f2, 0.5f) * (f2 / 4.0f));
        if (iRound2 != i2) {
            edgeEffect2.finish();
        }
        return i2 - iRound2;
    }

    public static void setDebugAssertionsEnabled(boolean z2) {
        F0 = z2;
    }

    public static void setVerboseLoggingEnabled(boolean z2) {
        G0 = z2;
    }

    public final void A(w wVar) {
        if (getScrollState() != 2) {
            wVar.getClass();
            return;
        }
        OverScroller overScroller = this.j0.g;
        overScroller.getFinalX();
        overScroller.getCurrX();
        wVar.getClass();
        overScroller.getFinalY();
        overScroller.getCurrY();
    }

    public final View B(View view) {
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

    public final boolean C(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        ArrayList<p> arrayList = this.v;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            p pVar = arrayList.get(i2);
            if (pVar.b(motionEvent) && action != 3) {
                this.w = pVar;
                return true;
            }
        }
        return false;
    }

    public final void D(int[] iArr) {
        int iE = this.j.e();
        if (iE == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i2 = Integer.MAX_VALUE;
        int i3 = Integer.MIN_VALUE;
        for (int i4 = 0; i4 < iE; i4++) {
            a0 a0VarK = K(this.j.d(i4));
            if (!a0VarK.s()) {
                int iG = a0VarK.g();
                if (iG < i2) {
                    i2 = iG;
                }
                if (iG > i3) {
                    i3 = iG;
                }
            }
        }
        iArr[0] = i2;
        iArr[1] = i3;
    }

    public final a0 F(int i2) {
        a0 a0Var = null;
        if (this.I) {
            return null;
        }
        int iH = this.j.h();
        for (int i3 = 0; i3 < iH; i3++) {
            a0 a0VarK = K(this.j.g(i3));
            if (a0VarK != null && !a0VarK.m() && G(a0VarK) == i2) {
                if (!this.j.j(a0VarK.e)) {
                    return a0VarK;
                }
                a0Var = a0VarK;
            }
        }
        return a0Var;
    }

    public final int G(a0 a0Var) {
        if (!((a0Var.n & 524) != 0) && a0Var.j()) {
            androidx.recyclerview.widget.a aVar = this.i;
            int i2 = a0Var.g;
            ArrayList<a.b> arrayList = aVar.b;
            int size = arrayList.size();
            for (int i3 = 0; i3 < size; i3++) {
                a.b bVar = arrayList.get(i3);
                int i4 = bVar.a;
                if (i4 != 1) {
                    if (i4 == 2) {
                        int i5 = bVar.b;
                        if (i5 <= i2) {
                            int i6 = bVar.d;
                            if (i5 + i6 <= i2) {
                                i2 -= i6;
                            }
                        } else {
                            continue;
                        }
                    } else if (i4 == 8) {
                        int i7 = bVar.b;
                        if (i7 == i2) {
                            i2 = bVar.d;
                        } else {
                            if (i7 < i2) {
                                i2--;
                            }
                            if (bVar.d <= i2) {
                                i2++;
                            }
                        }
                    }
                } else if (bVar.b <= i2) {
                    i2 += bVar.d;
                }
            }
            return i2;
        }
        return -1;
    }

    public final long H(a0 a0Var) {
        return this.q.f ? a0Var.i : a0Var.g;
    }

    public final a0 J(View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this) {
            return K(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    public final Rect M(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        boolean z2 = layoutParams.c;
        Rect rect = layoutParams.b;
        if (!z2) {
            return rect;
        }
        w wVar = this.m0;
        if (wVar.g && (layoutParams.b() || layoutParams.a.k())) {
            return rect;
        }
        rect.set(0, 0, 0, 0);
        ArrayList<l> arrayList = this.u;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            Rect rect2 = this.n;
            rect2.set(0, 0, 0, 0);
            arrayList.get(i2).g(rect2, view, this, wVar);
            rect.left += rect2.left;
            rect.top += rect2.top;
            rect.right += rect2.right;
            rect.bottom += rect2.bottom;
        }
        layoutParams.c = false;
        return rect;
    }

    public final void N() {
        if (this.u.size() == 0) {
            return;
        }
        m mVar = this.r;
        if (mVar != null) {
            mVar.f("Cannot invalidate item decorations during a scroll or layout");
        }
        Q();
        requestLayout();
    }

    public final boolean O() {
        return this.K > 0;
    }

    public final void P(int i2) {
        if (this.r == null) {
            return;
        }
        setScrollState(2);
        this.r.v0(i2);
        awakenScrollBars();
    }

    public final void Q() {
        int iH = this.j.h();
        for (int i2 = 0; i2 < iH; i2++) {
            ((LayoutParams) this.j.g(i2).getLayoutParams()).c = true;
        }
        ArrayList<a0> arrayList = this.g.c;
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            LayoutParams layoutParams = (LayoutParams) arrayList.get(i3).e.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.c = true;
            }
        }
    }

    public final void R(int i2, int i3, boolean z2) {
        int i4 = i2 + i3;
        int iH = this.j.h();
        for (int i5 = 0; i5 < iH; i5++) {
            a0 a0VarK = K(this.j.g(i5));
            if (a0VarK != null && !a0VarK.s()) {
                int i6 = a0VarK.g;
                w wVar = this.m0;
                if (i6 >= i4) {
                    if (G0) {
                        a0VarK.toString();
                    }
                    a0VarK.p(-i3, z2);
                    wVar.f = true;
                } else if (i6 >= i2) {
                    if (G0) {
                        a0VarK.toString();
                    }
                    a0VarK.d(8);
                    a0VarK.p(-i3, z2);
                    a0VarK.g = i2 - 1;
                    wVar.f = true;
                }
            }
        }
        s sVar = this.g;
        ArrayList<a0> arrayList = sVar.c;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                requestLayout();
                return;
            }
            a0 a0Var = arrayList.get(size);
            if (a0Var != null) {
                int i7 = a0Var.g;
                if (i7 >= i4) {
                    if (G0) {
                        a0Var.toString();
                    }
                    a0Var.p(-i3, z2);
                } else if (i7 >= i2) {
                    a0Var.d(8);
                    sVar.g(size);
                }
            }
        }
    }

    public final void S() {
        this.K++;
    }

    public final void T(boolean z2) {
        int i2;
        int i3 = this.K - 1;
        this.K = i3;
        if (i3 < 1) {
            if (F0 && i3 < 0) {
                throw new IllegalStateException(C0175b.h(this, new StringBuilder("layout or scroll counter cannot go below zero.Some calls are not matching")));
            }
            this.K = 0;
            if (z2) {
                int i4 = this.E;
                this.E = 0;
                if (i4 != 0) {
                    AccessibilityManager accessibilityManager = this.G;
                    if (accessibilityManager != null && accessibilityManager.isEnabled()) {
                        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
                        accessibilityEventObtain.setEventType(2048);
                        accessibilityEventObtain.setContentChangeTypes(i4);
                        sendAccessibilityEventUnchecked(accessibilityEventObtain);
                    }
                }
                ArrayList arrayList = this.z0;
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    a0 a0Var = (a0) arrayList.get(size);
                    if (a0Var.e.getParent() == this && !a0Var.s() && (i2 = a0Var.u) != -1) {
                        WeakHashMap<View, in3> weakHashMap = om3.a;
                        om3.d.p(a0Var.e, i2);
                        a0Var.u = -1;
                    }
                }
                arrayList.clear();
            }
        }
    }

    public final void U(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.T) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.T = motionEvent.getPointerId(i2);
            int x2 = (int) (motionEvent.getX(i2) + 0.5f);
            this.a0 = x2;
            this.V = x2;
            int y2 = (int) (motionEvent.getY(i2) + 0.5f);
            this.b0 = y2;
            this.W = y2;
        }
    }

    public final void V() {
        if (this.s0 || !this.x) {
            return;
        }
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.d.k(this, this.A0);
        this.s0 = true;
    }

    public final void W() {
        boolean z2;
        boolean z3 = false;
        if (this.I) {
            androidx.recyclerview.widget.a aVar = this.i;
            aVar.l(aVar.b);
            aVar.l(aVar.c);
            aVar.f = 0;
            if (this.f19J) {
                this.r.d0();
            }
        }
        if (this.R != null && this.r.H0()) {
            this.i.j();
        } else {
            this.i.c();
        }
        boolean z4 = this.p0 || this.q0;
        boolean z5 = this.z && this.R != null && ((z2 = this.I) || z4 || this.r.f) && (!z2 || this.q.f);
        w wVar = this.m0;
        wVar.j = z5;
        if (z5 && z4 && !this.I) {
            if (this.R != null && this.r.H0()) {
                z3 = true;
            }
        }
        wVar.k = z3;
    }

    public final void X(boolean z2) {
        this.f19J = z2 | this.f19J;
        this.I = true;
        int iH = this.j.h();
        for (int i2 = 0; i2 < iH; i2++) {
            a0 a0VarK = K(this.j.g(i2));
            if (a0VarK != null && !a0VarK.s()) {
                a0VarK.d(6);
            }
        }
        Q();
        s sVar = this.g;
        ArrayList<a0> arrayList = sVar.c;
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            a0 a0Var = arrayList.get(i3);
            if (a0Var != null) {
                a0Var.d(6);
                a0Var.c(null);
            }
        }
        e eVar = RecyclerView.this.q;
        if (eVar == null || !eVar.f) {
            sVar.f();
        }
    }

    public final void Y(a0 a0Var, j.c cVar) {
        int i2 = (a0Var.n & (-8193)) | 0;
        a0Var.n = i2;
        boolean z2 = this.m0.h;
        c0 c0Var = this.k;
        if (z2) {
            if (((i2 & 2) != 0) && !a0Var.m() && !a0Var.s()) {
                c0Var.b.k(H(a0Var), a0Var);
            }
        }
        wm2<a0, c0.a> wm2Var = c0Var.a;
        c0.a aVarA = wm2Var.get(a0Var);
        if (aVarA == null) {
            aVarA = c0.a.a();
            wm2Var.put(a0Var, aVarA);
        }
        aVarA.b = cVar;
        aVarA.a |= 4;
    }

    public final void Z() {
        boolean zIsFinished;
        EdgeEffect edgeEffect = this.N;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            zIsFinished = this.N.isFinished();
        } else {
            zIsFinished = false;
        }
        EdgeEffect edgeEffect2 = this.O;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            zIsFinished |= this.O.isFinished();
        }
        EdgeEffect edgeEffect3 = this.P;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            zIsFinished |= this.P.isFinished();
        }
        EdgeEffect edgeEffect4 = this.Q;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            zIsFinished |= this.Q.isFinished();
        }
        if (zIsFinished) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.d.j(this);
        }
    }

    public final int a0(float f2, int i2) {
        float height = f2 / getHeight();
        float width = i2 / getWidth();
        EdgeEffect edgeEffect = this.N;
        float f3 = 0.0f;
        if (edgeEffect == null || EdgeEffectCompat.a(edgeEffect) == 0.0f) {
            EdgeEffect edgeEffect2 = this.P;
            if (edgeEffect2 != null && EdgeEffectCompat.a(edgeEffect2) != 0.0f) {
                if (canScrollHorizontally(1)) {
                    this.P.onRelease();
                } else {
                    float fB = EdgeEffectCompat.b(this.P, width, height);
                    if (EdgeEffectCompat.a(this.P) == 0.0f) {
                        this.P.onRelease();
                    }
                    f3 = fB;
                }
                invalidate();
            }
        } else {
            if (canScrollHorizontally(-1)) {
                this.N.onRelease();
            } else {
                float f4 = -EdgeEffectCompat.b(this.N, -width, 1.0f - height);
                if (EdgeEffectCompat.a(this.N) == 0.0f) {
                    this.N.onRelease();
                }
                f3 = f4;
            }
            invalidate();
        }
        return Math.round(f3 * getWidth());
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        m mVar = this.r;
        if (mVar != null) {
            mVar.getClass();
        }
        super.addFocusables(arrayList, i2, i3);
    }

    public void addOnChildAttachStateChangeListener(n nVar) {
        if (this.H == null) {
            this.H = new ArrayList();
        }
        this.H.add(nVar);
    }

    public void addOnItemTouchListener(p pVar) {
        this.v.add(pVar);
    }

    public void addOnScrollListener(q qVar) {
        if (this.o0 == null) {
            this.o0 = new ArrayList();
        }
        this.o0.add(qVar);
    }

    public final void c0(l lVar) {
        m mVar = this.r;
        if (mVar != null) {
            mVar.f("Cannot remove item decoration during a scroll  or layout");
        }
        ArrayList<l> arrayList = this.u;
        arrayList.remove(lVar);
        if (arrayList.isEmpty()) {
            setWillNotDraw(getOverScrollMode() == 2);
        }
        Q();
        requestLayout();
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && this.r.j((LayoutParams) layoutParams);
    }

    @Override // android.view.View
    public final int computeHorizontalScrollExtent() {
        m mVar = this.r;
        if (mVar != null && mVar.h()) {
            return this.r.n(this.m0);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeHorizontalScrollOffset() {
        m mVar = this.r;
        if (mVar != null && mVar.h()) {
            return this.r.o(this.m0);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeHorizontalScrollRange() {
        m mVar = this.r;
        if (mVar != null && mVar.h()) {
            return this.r.p(this.m0);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeVerticalScrollExtent() {
        m mVar = this.r;
        if (mVar != null && mVar.i()) {
            return this.r.q(this.m0);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeVerticalScrollOffset() {
        m mVar = this.r;
        if (mVar != null && mVar.i()) {
            return this.r.r(this.m0);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeVerticalScrollRange() {
        m mVar = this.r;
        if (mVar != null && mVar.i()) {
            return this.r.s(this.m0);
        }
        return 0;
    }

    public final void d0(View view, View view2) {
        View view3 = view2 != null ? view2 : view;
        int width = view3.getWidth();
        int height = view3.getHeight();
        Rect rect = this.n;
        rect.set(0, 0, width, height);
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof LayoutParams) {
            LayoutParams layoutParams2 = (LayoutParams) layoutParams;
            if (!layoutParams2.c) {
                int i2 = rect.left;
                Rect rect2 = layoutParams2.b;
                rect.left = i2 - rect2.left;
                rect.right += rect2.right;
                rect.top -= rect2.top;
                rect.bottom += rect2.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, rect);
            offsetRectIntoDescendantCoords(view, rect);
        }
        this.r.s0(this, view, this.n, !this.z, view2 == null);
    }

    @Override // android.view.View
    public final boolean dispatchNestedFling(float f2, float f3, boolean z2) {
        return getScrollingChildHelper().a(f2, f3, z2);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreFling(float f2, float f3) {
        return getScrollingChildHelper().b(f2, f3);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().c(i2, i3, 0, iArr, iArr2);
    }

    @Override // android.view.View
    public final boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr) {
        return getScrollingChildHelper().e(i2, i3, i4, i5, iArr, 0, null);
    }

    @Override // android.view.View
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        boolean z2;
        super.draw(canvas);
        ArrayList<l> arrayList = this.u;
        int size = arrayList.size();
        boolean z3 = false;
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.get(i2).i(canvas, this);
        }
        EdgeEffect edgeEffect = this.N;
        if (edgeEffect == null || edgeEffect.isFinished()) {
            z2 = false;
        } else {
            int iSave = canvas.save();
            int paddingBottom = this.l ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((-getHeight()) + paddingBottom, 0.0f);
            EdgeEffect edgeEffect2 = this.N;
            z2 = edgeEffect2 != null && edgeEffect2.draw(canvas);
            canvas.restoreToCount(iSave);
        }
        EdgeEffect edgeEffect3 = this.O;
        if (edgeEffect3 != null && !edgeEffect3.isFinished()) {
            int iSave2 = canvas.save();
            if (this.l) {
                canvas.translate(getPaddingLeft(), getPaddingTop());
            }
            EdgeEffect edgeEffect4 = this.O;
            z2 |= edgeEffect4 != null && edgeEffect4.draw(canvas);
            canvas.restoreToCount(iSave2);
        }
        EdgeEffect edgeEffect5 = this.P;
        if (edgeEffect5 != null && !edgeEffect5.isFinished()) {
            int iSave3 = canvas.save();
            int width = getWidth();
            int paddingTop = this.l ? getPaddingTop() : 0;
            canvas.rotate(90.0f);
            canvas.translate(paddingTop, -width);
            EdgeEffect edgeEffect6 = this.P;
            z2 |= edgeEffect6 != null && edgeEffect6.draw(canvas);
            canvas.restoreToCount(iSave3);
        }
        EdgeEffect edgeEffect7 = this.Q;
        if (edgeEffect7 != null && !edgeEffect7.isFinished()) {
            int iSave4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.l) {
                canvas.translate(getPaddingRight() + (-getWidth()), getPaddingBottom() + (-getHeight()));
            } else {
                canvas.translate(-getWidth(), -getHeight());
            }
            EdgeEffect edgeEffect8 = this.Q;
            if (edgeEffect8 != null && edgeEffect8.draw(canvas)) {
                z3 = true;
            }
            z2 |= z3;
            canvas.restoreToCount(iSave4);
        }
        if ((z2 || this.R == null || arrayList.size() <= 0 || !this.R.k()) ? z2 : true) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.d.j(this);
        }
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j2) {
        return super.drawChild(canvas, view, j2);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0116  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean e0(int r21, int r22, android.view.MotionEvent r23, int r24) {
        /*
            Method dump skipped, instruction units count: 332
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.e0(int, int, android.view.MotionEvent, int):boolean");
    }

    public final void f(a0 a0Var) {
        View view = a0Var.e;
        boolean z2 = view.getParent() == this;
        this.g.l(J(view));
        if (a0Var.o()) {
            this.j.b(view, -1, view.getLayoutParams(), true);
            return;
        }
        if (!z2) {
            this.j.a(view, -1, true);
            return;
        }
        androidx.recyclerview.widget.b bVar = this.j;
        int iIndexOfChild = ((androidx.recyclerview.widget.v) bVar.a).a.indexOfChild(view);
        if (iIndexOfChild >= 0) {
            bVar.b.h(iIndexOfChild);
            bVar.i(view);
        } else {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
    }

    public final void f0(int i2, int i3, int[] iArr) {
        a0 a0Var;
        l0();
        S();
        Trace.beginSection("RV Scroll");
        w wVar = this.m0;
        A(wVar);
        s sVar = this.g;
        int iU0 = i2 != 0 ? this.r.u0(i2, sVar, wVar) : 0;
        int iW0 = i3 != 0 ? this.r.w0(i3, sVar, wVar) : 0;
        Trace.endSection();
        int iE = this.j.e();
        for (int i4 = 0; i4 < iE; i4++) {
            View viewD = this.j.d(i4);
            a0 a0VarJ = J(viewD);
            if (a0VarJ != null && (a0Var = a0VarJ.m) != null) {
                int left = viewD.getLeft();
                int top = viewD.getTop();
                View view = a0Var.e;
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
        T(true);
        m0(false);
        if (iArr != null) {
            iArr[0] = iU0;
            iArr[1] = iW0;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:136:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0073  */
    @Override // android.view.ViewGroup, android.view.ViewParent
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View focusSearch(android.view.View r14, int r15) {
        /*
            Method dump skipped, instruction units count: 403
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.focusSearch(android.view.View, int):android.view.View");
    }

    public final void g(l lVar) {
        m mVar = this.r;
        if (mVar != null) {
            mVar.f("Cannot add item decoration during a scroll  or layout");
        }
        ArrayList<l> arrayList = this.u;
        if (arrayList.isEmpty()) {
            setWillNotDraw(false);
        }
        arrayList.add(lVar);
        Q();
        requestLayout();
    }

    public final void g0(int i2) {
        v vVar;
        if (this.C) {
            return;
        }
        setScrollState(0);
        z zVar = this.j0;
        RecyclerView.this.removeCallbacks(zVar);
        zVar.g.abortAnimation();
        m mVar = this.r;
        if (mVar != null && (vVar = mVar.e) != null) {
            vVar.d();
        }
        m mVar2 = this.r;
        if (mVar2 == null) {
            Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            mVar2.v0(i2);
            awakenScrollBars();
        }
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        m mVar = this.r;
        if (mVar != null) {
            return mVar.v();
        }
        throw new IllegalStateException(C0175b.h(this, new StringBuilder("RecyclerView has no LayoutManager")));
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        m mVar = this.r;
        if (mVar != null) {
            return mVar.w(getContext(), attributeSet);
        }
        throw new IllegalStateException(C0175b.h(this, new StringBuilder("RecyclerView has no LayoutManager")));
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return "androidx.recyclerview.widget.RecyclerView";
    }

    public e getAdapter() {
        return this.q;
    }

    @Override // android.view.View
    public int getBaseline() {
        m mVar = this.r;
        if (mVar == null) {
            return super.getBaseline();
        }
        mVar.getClass();
        return -1;
    }

    @Override // android.view.ViewGroup
    public final int getChildDrawingOrder(int i2, int i3) {
        return super.getChildDrawingOrder(i2, i3);
    }

    @Override // android.view.ViewGroup
    public boolean getClipToPadding() {
        return this.l;
    }

    public androidx.recyclerview.widget.x getCompatAccessibilityDelegate() {
        return this.t0;
    }

    public i getEdgeEffectFactory() {
        return this.M;
    }

    public j getItemAnimator() {
        return this.R;
    }

    public int getItemDecorationCount() {
        return this.u.size();
    }

    public m getLayoutManager() {
        return this.r;
    }

    public int getMaxFlingVelocity() {
        return this.f0;
    }

    public int getMinFlingVelocity() {
        return this.e0;
    }

    long getNanoTime() {
        if (L0) {
            return System.nanoTime();
        }
        return 0L;
    }

    public o getOnFlingListener() {
        return this.d0;
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.i0;
    }

    public r getRecycledViewPool() {
        return this.g.c();
    }

    public int getScrollState() {
        return this.S;
    }

    public final void h(String str) {
        if (O()) {
            if (str != null) {
                throw new IllegalStateException(str);
            }
            throw new IllegalStateException(C0175b.h(this, new StringBuilder("Cannot call this method while RecyclerView is computing a layout or scrolling")));
        }
        if (this.L > 0) {
            Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException(C0175b.h(this, new StringBuilder(""))));
        }
    }

    public final void h0(e eVar, boolean z2) {
        e eVar2 = this.q;
        u uVar = this.f;
        if (eVar2 != null) {
            eVar2.C(uVar);
            this.q.w(this);
        }
        j jVar = this.R;
        if (jVar != null) {
            jVar.j();
        }
        m mVar = this.r;
        s sVar = this.g;
        if (mVar != null) {
            mVar.o0(sVar);
            this.r.p0(sVar);
        }
        sVar.a.clear();
        sVar.f();
        androidx.recyclerview.widget.a aVar = this.i;
        aVar.l(aVar.b);
        aVar.l(aVar.c);
        aVar.f = 0;
        e<?> eVar3 = this.q;
        this.q = eVar;
        if (eVar != null) {
            eVar.A(uVar);
            eVar.s(this);
        }
        m mVar2 = this.r;
        if (mVar2 != null) {
            mVar2.V();
        }
        e eVar4 = this.q;
        sVar.a.clear();
        sVar.f();
        sVar.e(eVar3, true);
        r rVarC = sVar.c();
        if (eVar3 != null) {
            rVarC.b--;
        }
        if (!z2 && rVarC.b == 0) {
            rVarC.a();
        }
        if (eVar4 != null) {
            rVarC.b++;
        } else {
            rVarC.getClass();
        }
        sVar.d();
        this.m0.f = true;
    }

    @Override // android.view.View
    public final boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().f(0) != null;
    }

    public final boolean i0(EdgeEffect edgeEffect, int i2, int i3) {
        if (i2 > 0) {
            return true;
        }
        float fA = EdgeEffectCompat.a(edgeEffect) * i3;
        float fAbs = Math.abs(-i2) * 0.35f;
        float f2 = this.e * 0.015f;
        double dLog = Math.log(fAbs / f2);
        double d2 = I0;
        return ((float) (Math.exp((d2 / (d2 - 1.0d)) * dLog) * ((double) f2))) < fA;
    }

    @Override // android.view.View
    public final boolean isAttachedToWindow() {
        return this.x;
    }

    @Override // android.view.ViewGroup
    public final boolean isLayoutSuppressed() {
        return this.C;
    }

    @Override // android.view.View
    public final boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().d;
    }

    public final void j() {
        int iH = this.j.h();
        for (int i2 = 0; i2 < iH; i2++) {
            a0 a0VarK = K(this.j.g(i2));
            if (!a0VarK.s()) {
                a0VarK.h = -1;
                a0VarK.k = -1;
            }
        }
        s sVar = this.g;
        ArrayList<a0> arrayList = sVar.c;
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            a0 a0Var = arrayList.get(i3);
            a0Var.h = -1;
            a0Var.k = -1;
        }
        ArrayList<a0> arrayList2 = sVar.a;
        int size2 = arrayList2.size();
        for (int i4 = 0; i4 < size2; i4++) {
            a0 a0Var2 = arrayList2.get(i4);
            a0Var2.h = -1;
            a0Var2.k = -1;
        }
        ArrayList<a0> arrayList3 = sVar.b;
        if (arrayList3 != null) {
            int size3 = arrayList3.size();
            for (int i5 = 0; i5 < size3; i5++) {
                a0 a0Var3 = sVar.b.get(i5);
                a0Var3.h = -1;
                a0Var3.k = -1;
            }
        }
    }

    public final void j0(int i2, int i3, boolean z2) {
        m mVar = this.r;
        if (mVar == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.C) {
            return;
        }
        if (!mVar.h()) {
            i2 = 0;
        }
        if (!this.r.i()) {
            i3 = 0;
        }
        if (i2 == 0 && i3 == 0) {
            return;
        }
        if (z2) {
            int i4 = i2 != 0 ? 1 : 0;
            if (i3 != 0) {
                i4 |= 2;
            }
            getScrollingChildHelper().g(i4, 1);
        }
        this.j0.c(i2, i3, Integer.MIN_VALUE, null);
    }

    public final void k(int i2, int i3) {
        boolean zIsFinished;
        EdgeEffect edgeEffect = this.N;
        if (edgeEffect == null || edgeEffect.isFinished() || i2 <= 0) {
            zIsFinished = false;
        } else {
            this.N.onRelease();
            zIsFinished = this.N.isFinished();
        }
        EdgeEffect edgeEffect2 = this.P;
        if (edgeEffect2 != null && !edgeEffect2.isFinished() && i2 < 0) {
            this.P.onRelease();
            zIsFinished |= this.P.isFinished();
        }
        EdgeEffect edgeEffect3 = this.O;
        if (edgeEffect3 != null && !edgeEffect3.isFinished() && i3 > 0) {
            this.O.onRelease();
            zIsFinished |= this.O.isFinished();
        }
        EdgeEffect edgeEffect4 = this.Q;
        if (edgeEffect4 != null && !edgeEffect4.isFinished() && i3 < 0) {
            this.Q.onRelease();
            zIsFinished |= this.Q.isFinished();
        }
        if (zIsFinished) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.d.j(this);
        }
    }

    public final void k0(int i2) {
        if (this.C) {
            return;
        }
        m mVar = this.r;
        if (mVar == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            mVar.F0(this, i2);
        }
    }

    public final void l0() {
        int i2 = this.A + 1;
        this.A = i2;
        if (i2 != 1 || this.C) {
            return;
        }
        this.B = false;
    }

    public final void m() {
        if (!this.z || this.I) {
            Trace.beginSection("RV FullInvalidate");
            p();
            Trace.endSection();
            return;
        }
        if (this.i.g()) {
            androidx.recyclerview.widget.a aVar = this.i;
            int i2 = aVar.f;
            boolean z2 = false;
            if ((i2 & 4) != 0) {
                if (!((i2 & 11) != 0)) {
                    Trace.beginSection("RV PartialInvalidate");
                    l0();
                    S();
                    this.i.j();
                    if (!this.B) {
                        int iE = this.j.e();
                        int i3 = 0;
                        while (true) {
                            if (i3 >= iE) {
                                break;
                            }
                            a0 a0VarK = K(this.j.d(i3));
                            if (a0VarK != null && !a0VarK.s()) {
                                if ((a0VarK.n & 2) != 0) {
                                    z2 = true;
                                    break;
                                }
                            }
                            i3++;
                        }
                        if (z2) {
                            p();
                        } else {
                            this.i.b();
                        }
                    }
                    m0(true);
                    T(true);
                    Trace.endSection();
                    return;
                }
            }
            if (aVar.g()) {
                Trace.beginSection("RV FullInvalidate");
                p();
                Trace.endSection();
            }
        }
    }

    public final void m0(boolean z2) {
        if (this.A < 1) {
            if (F0) {
                throw new IllegalStateException(C0175b.h(this, new StringBuilder("stopInterceptRequestLayout was called more times than startInterceptRequestLayout.")));
            }
            this.A = 1;
        }
        if (!z2 && !this.C) {
            this.B = false;
        }
        if (this.A == 1) {
            if (z2 && this.B && !this.C && this.r != null && this.q != null) {
                p();
            }
            if (!this.C) {
                this.B = false;
            }
        }
        this.A--;
    }

    public final void n(int i2, int i3) {
        int paddingRight = getPaddingRight() + getPaddingLeft();
        WeakHashMap<View, in3> weakHashMap = om3.a;
        setMeasuredDimension(m.k(i2, paddingRight, om3.d.d(this)), m.k(i3, getPaddingBottom() + getPaddingTop(), om3.d.c(this)));
    }

    public final void n0(int i2) {
        getScrollingChildHelper().h(i2);
    }

    public final void o(View view) {
        K(view);
        e eVar = this.q;
        ArrayList arrayList = this.H;
        if (arrayList == null) {
            return;
        }
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                return;
            } else {
                ((n) this.H.get(size)).c(view);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0055  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onAttachedToWindow() {
        /*
            r5 = this;
            super.onAttachedToWindow()
            r0 = 0
            r5.K = r0
            r1 = 1
            r5.x = r1
            boolean r2 = r5.z
            if (r2 == 0) goto L15
            boolean r2 = r5.isLayoutRequested()
            if (r2 != 0) goto L15
            r2 = r1
            goto L16
        L15:
            r2 = r0
        L16:
            r5.z = r2
            androidx.recyclerview.widget.RecyclerView$s r2 = r5.g
            r2.d()
            androidx.recyclerview.widget.RecyclerView$m r2 = r5.r
            if (r2 == 0) goto L23
            r2.g = r1
        L23:
            r5.s0 = r0
            boolean r0 = androidx.recyclerview.widget.RecyclerView.L0
            if (r0 == 0) goto L80
            java.lang.ThreadLocal<androidx.recyclerview.widget.k> r0 = androidx.recyclerview.widget.k.i
            java.lang.Object r1 = r0.get()
            androidx.recyclerview.widget.k r1 = (androidx.recyclerview.widget.k) r1
            r5.k0 = r1
            if (r1 != 0) goto L63
            androidx.recyclerview.widget.k r1 = new androidx.recyclerview.widget.k
            r1.<init>()
            r5.k0 = r1
            java.util.WeakHashMap<android.view.View, in3> r1 = defpackage.om3.a
            android.view.Display r1 = om3.e.b(r5)
            boolean r2 = r5.isInEditMode()
            if (r2 != 0) goto L55
            if (r1 == 0) goto L55
            float r1 = r1.getRefreshRate()
            r2 = 1106247680(0x41f00000, float:30.0)
            int r2 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r2 < 0) goto L55
            goto L57
        L55:
            r1 = 1114636288(0x42700000, float:60.0)
        L57:
            androidx.recyclerview.widget.k r2 = r5.k0
            r3 = 1315859240(0x4e6e6b28, float:1.0E9)
            float r3 = r3 / r1
            long r3 = (long) r3
            r2.g = r3
            r0.set(r2)
        L63:
            androidx.recyclerview.widget.k r0 = r5.k0
            r0.getClass()
            boolean r1 = androidx.recyclerview.widget.RecyclerView.F0
            java.util.ArrayList<androidx.recyclerview.widget.RecyclerView> r0 = r0.e
            if (r1 == 0) goto L7d
            boolean r1 = r0.contains(r5)
            if (r1 != 0) goto L75
            goto L7d
        L75:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "RecyclerView already present in worker list!"
            r5.<init>(r0)
            throw r5
        L7d:
            r0.add(r5)
        L80:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onAttachedToWindow():void");
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        s sVar;
        androidx.recyclerview.widget.k kVar;
        v vVar;
        super.onDetachedFromWindow();
        j jVar = this.R;
        if (jVar != null) {
            jVar.j();
        }
        setScrollState(0);
        z zVar = this.j0;
        RecyclerView.this.removeCallbacks(zVar);
        zVar.g.abortAnimation();
        m mVar = this.r;
        if (mVar != null && (vVar = mVar.e) != null) {
            vVar.d();
        }
        this.x = false;
        m mVar2 = this.r;
        if (mVar2 != null) {
            mVar2.g = false;
            mVar2.W(this);
        }
        this.z0.clear();
        removeCallbacks(this.A0);
        this.k.getClass();
        while (c0.a.d.a() != null) {
        }
        int i2 = 0;
        while (true) {
            sVar = this.g;
            ArrayList<a0> arrayList = sVar.c;
            if (i2 >= arrayList.size()) {
                break;
            }
            q22.a(arrayList.get(i2).e);
            i2++;
        }
        sVar.e(RecyclerView.this.q, false);
        int i3 = q22.a;
        int i4 = 0;
        while (true) {
            if (!(i4 < getChildCount())) {
                if (!L0 || (kVar = this.k0) == null) {
                    return;
                }
                boolean zRemove = kVar.e.remove(this);
                if (F0 && !zRemove) {
                    throw new IllegalStateException("RecyclerView removal failed!");
                }
                this.k0 = null;
                return;
            }
            int i5 = i4 + 1;
            View childAt = getChildAt(i4);
            if (childAt == null) {
                throw new IndexOutOfBoundsException();
            }
            int i6 = q22.a;
            s22 s22Var = (s22) childAt.getTag(i6);
            if (s22Var == null) {
                s22Var = new s22();
                childAt.setTag(i6, s22Var);
            }
            ArrayList<r22> arrayList2 = s22Var.a;
            for (int iW = z7.w(arrayList2); -1 < iW; iW--) {
                arrayList2.get(iW).a();
            }
            i4 = i5;
        }
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ArrayList<l> arrayList = this.u;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.get(i2).h(canvas, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0082  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onGenericMotionEvent(android.view.MotionEvent r16) {
        /*
            Method dump skipped, instruction units count: 242
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onGenericMotionEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z2;
        boolean z3;
        if (this.C) {
            return false;
        }
        this.w = null;
        if (C(motionEvent)) {
            VelocityTracker velocityTracker = this.U;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
            n0(0);
            Z();
            setScrollState(0);
            return true;
        }
        m mVar = this.r;
        if (mVar == null) {
            return false;
        }
        boolean zH = mVar.h();
        boolean zI = this.r.i();
        if (this.U == null) {
            this.U = VelocityTracker.obtain();
        }
        this.U.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            if (this.D) {
                this.D = false;
            }
            this.T = motionEvent.getPointerId(0);
            int x2 = (int) (motionEvent.getX() + 0.5f);
            this.a0 = x2;
            this.V = x2;
            int y2 = (int) (motionEvent.getY() + 0.5f);
            this.b0 = y2;
            this.W = y2;
            EdgeEffect edgeEffect = this.N;
            if (edgeEffect == null || EdgeEffectCompat.a(edgeEffect) == 0.0f || canScrollHorizontally(-1)) {
                z2 = false;
            } else {
                EdgeEffectCompat.b(this.N, 0.0f, 1.0f - (motionEvent.getY() / getHeight()));
                z2 = true;
            }
            EdgeEffect edgeEffect2 = this.P;
            boolean z4 = z2;
            if (edgeEffect2 != null) {
                z4 = z2;
                if (EdgeEffectCompat.a(edgeEffect2) != 0.0f) {
                    z4 = z2;
                    if (!canScrollHorizontally(1)) {
                        EdgeEffectCompat.b(this.P, 0.0f, motionEvent.getY() / getHeight());
                        z4 = true;
                    }
                }
            }
            EdgeEffect edgeEffect3 = this.O;
            boolean z5 = z4;
            if (edgeEffect3 != null) {
                z5 = z4;
                if (EdgeEffectCompat.a(edgeEffect3) != 0.0f) {
                    z5 = z4;
                    if (!canScrollVertically(-1)) {
                        EdgeEffectCompat.b(this.O, 0.0f, motionEvent.getX() / getWidth());
                        z5 = true;
                    }
                }
            }
            EdgeEffect edgeEffect4 = this.Q;
            boolean z6 = z5;
            if (edgeEffect4 != null) {
                z6 = z5;
                if (EdgeEffectCompat.a(edgeEffect4) != 0.0f) {
                    z6 = z5;
                    if (!canScrollVertically(1)) {
                        EdgeEffectCompat.b(this.Q, 0.0f, 1.0f - (motionEvent.getX() / getWidth()));
                        z6 = true;
                    }
                }
            }
            if (z6 || this.S == 2) {
                getParent().requestDisallowInterceptTouchEvent(true);
                setScrollState(1);
                n0(1);
            }
            int[] iArr = this.x0;
            iArr[1] = 0;
            iArr[0] = 0;
            int i2 = zH;
            if (zI) {
                i2 = (zH ? 1 : 0) | 2;
            }
            getScrollingChildHelper().g(i2, 0);
        } else if (actionMasked == 1) {
            this.U.clear();
            n0(0);
        } else if (actionMasked == 2) {
            int iFindPointerIndex = motionEvent.findPointerIndex(this.T);
            if (iFindPointerIndex < 0) {
                C0173a.q(new StringBuilder("Error processing scroll; pointer index for id "), this.T, " not found. Did any MotionEvents get skipped?", "RecyclerView");
                return false;
            }
            int x3 = (int) (motionEvent.getX(iFindPointerIndex) + 0.5f);
            int y3 = (int) (motionEvent.getY(iFindPointerIndex) + 0.5f);
            if (this.S != 1) {
                int i3 = x3 - this.V;
                int i4 = y3 - this.W;
                if (!zH || Math.abs(i3) <= this.c0) {
                    z3 = false;
                } else {
                    this.a0 = x3;
                    z3 = true;
                }
                if (zI && Math.abs(i4) > this.c0) {
                    this.b0 = y3;
                    z3 = true;
                }
                if (z3) {
                    setScrollState(1);
                }
            }
        } else if (actionMasked == 3) {
            VelocityTracker velocityTracker2 = this.U;
            if (velocityTracker2 != null) {
                velocityTracker2.clear();
            }
            n0(0);
            Z();
            setScrollState(0);
        } else if (actionMasked == 5) {
            this.T = motionEvent.getPointerId(actionIndex);
            int x4 = (int) (motionEvent.getX(actionIndex) + 0.5f);
            this.a0 = x4;
            this.V = x4;
            int y4 = (int) (motionEvent.getY(actionIndex) + 0.5f);
            this.b0 = y4;
            this.W = y4;
        } else if (actionMasked == 6) {
            U(motionEvent);
        }
        return this.S == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        Trace.beginSection("RV OnLayout");
        p();
        Trace.endSection();
        this.z = true;
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        m mVar = this.r;
        if (mVar == null) {
            n(i2, i3);
            return;
        }
        boolean zQ = mVar.Q();
        boolean z2 = false;
        w wVar = this.m0;
        if (zQ) {
            int mode = View.MeasureSpec.getMode(i2);
            int mode2 = View.MeasureSpec.getMode(i3);
            this.r.b.n(i2, i3);
            if (mode == 1073741824 && mode2 == 1073741824) {
                z2 = true;
            }
            this.B0 = z2;
            if (z2 || this.q == null) {
                return;
            }
            if (wVar.d == 1) {
                q();
            }
            this.r.y0(i2, i3);
            wVar.i = true;
            r();
            this.r.A0(i2, i3);
            if (this.r.D0()) {
                this.r.y0(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
                wVar.i = true;
                r();
                this.r.A0(i2, i3);
            }
            this.C0 = getMeasuredWidth();
            this.D0 = getMeasuredHeight();
            return;
        }
        if (this.y) {
            this.r.b.n(i2, i3);
            return;
        }
        if (this.F) {
            l0();
            S();
            W();
            T(true);
            if (wVar.k) {
                wVar.g = true;
            } else {
                this.i.c();
                wVar.g = false;
            }
            this.F = false;
            m0(false);
        } else if (wVar.k) {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
            return;
        }
        e eVar = this.q;
        if (eVar != null) {
            wVar.e = eVar.k();
        } else {
            wVar.e = 0;
        }
        l0();
        this.r.b.n(i2, i3);
        m0(false);
        wVar.g = false;
    }

    @Override // android.view.ViewGroup
    public final boolean onRequestFocusInDescendants(int i2, Rect rect) {
        if (O()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i2, rect);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        this.h = savedState;
        super.onRestoreInstanceState(savedState.e);
        requestLayout();
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SavedState savedState2 = this.h;
        if (savedState2 != null) {
            savedState.g = savedState2.g;
        } else {
            m mVar = this.r;
            if (mVar != null) {
                savedState.g = mVar.k0();
            } else {
                savedState.g = null;
            }
        }
        return savedState;
    }

    @Override // android.view.View
    public final void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 == i4 && i3 == i5) {
            return;
        }
        this.Q = null;
        this.O = null;
        this.P = null;
        this.N = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:150:0x026a  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x02af A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:170:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0331  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0357  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x03fa  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x0402  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x0432  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x0438  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x0440  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x0446  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x047d  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x0485  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0106 A[PHI: r0
  0x0106: PHI (r0v61 int) = (r0v47 int), (r0v65 int) binds: [B:57:0x00ef, B:61:0x0102] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x011f  */
    /* JADX WARN: Type inference failed for: r4v4, types: [boolean] */
    /* JADX WARN: Type inference failed for: r4v6 */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r22) {
        /*
            Method dump skipped, instruction units count: 1165
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:165:0x034a  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x03a2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void p() {
        /*
            Method dump skipped, instruction units count: 1028
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.p():void");
    }

    public final void q() {
        View viewB;
        w wVar = this.m0;
        wVar.a(1);
        A(wVar);
        wVar.i = false;
        l0();
        c0 c0Var = this.k;
        c0Var.a.clear();
        c0Var.b.b();
        S();
        W();
        a0 a0VarJ = null;
        View focusedChild = (this.i0 && hasFocus() && this.q != null) ? getFocusedChild() : null;
        if (focusedChild != null && (viewB = B(focusedChild)) != null) {
            a0VarJ = J(viewB);
        }
        if (a0VarJ == null) {
            wVar.m = -1L;
            wVar.l = -1;
            wVar.n = -1;
        } else {
            wVar.m = this.q.f ? a0VarJ.i : -1L;
            wVar.l = this.I ? -1 : a0VarJ.m() ? a0VarJ.h : a0VarJ.e();
            View focusedChild2 = a0VarJ.e;
            int id = focusedChild2.getId();
            while (!focusedChild2.isFocused() && (focusedChild2 instanceof ViewGroup) && focusedChild2.hasFocus()) {
                focusedChild2 = ((ViewGroup) focusedChild2).getFocusedChild();
                if (focusedChild2.getId() != -1) {
                    id = focusedChild2.getId();
                }
            }
            wVar.n = id;
        }
        wVar.h = wVar.j && this.q0;
        this.q0 = false;
        this.p0 = false;
        wVar.g = wVar.k;
        wVar.e = this.q.k();
        D(this.u0);
        boolean z2 = wVar.j;
        wm2<a0, c0.a> wm2Var = c0Var.a;
        if (z2) {
            int iE = this.j.e();
            for (int i2 = 0; i2 < iE; i2++) {
                a0 a0VarK = K(this.j.d(i2));
                if (!a0VarK.s() && (!a0VarK.k() || this.q.f)) {
                    j jVar = this.R;
                    j.e(a0VarK);
                    a0VarK.h();
                    jVar.getClass();
                    j.c cVar = new j.c();
                    cVar.a(a0VarK);
                    c0.a aVarA = wm2Var.get(a0VarK);
                    if (aVarA == null) {
                        aVarA = c0.a.a();
                        wm2Var.put(a0VarK, aVarA);
                    }
                    aVarA.b = cVar;
                    aVarA.a |= 4;
                    if (wVar.h) {
                        if (((a0VarK.n & 2) != 0) && !a0VarK.m() && !a0VarK.s() && !a0VarK.k()) {
                            c0Var.b.k(H(a0VarK), a0VarK);
                        }
                    }
                }
            }
        }
        if (wVar.k) {
            int iH = this.j.h();
            for (int i3 = 0; i3 < iH; i3++) {
                a0 a0VarK2 = K(this.j.g(i3));
                if (F0 && a0VarK2.g == -1 && !a0VarK2.m()) {
                    throw new IllegalStateException(C0175b.h(this, new StringBuilder("view holder cannot have position -1 unless it is removed")));
                }
                if (!a0VarK2.s() && a0VarK2.h == -1) {
                    a0VarK2.h = a0VarK2.g;
                }
            }
            boolean z3 = wVar.f;
            wVar.f = false;
            this.r.h0(this.g, wVar);
            wVar.f = z3;
            for (int i4 = 0; i4 < this.j.e(); i4++) {
                a0 a0VarK3 = K(this.j.d(i4));
                if (!a0VarK3.s()) {
                    c0.a aVar = wm2Var.get(a0VarK3);
                    if (!((aVar == null || (aVar.a & 4) == 0) ? false : true)) {
                        j.e(a0VarK3);
                        boolean z4 = (a0VarK3.n & 8192) != 0;
                        j jVar2 = this.R;
                        a0VarK3.h();
                        jVar2.getClass();
                        j.c cVar2 = new j.c();
                        cVar2.a(a0VarK3);
                        if (z4) {
                            Y(a0VarK3, cVar2);
                        } else {
                            c0.a aVarA2 = wm2Var.get(a0VarK3);
                            if (aVarA2 == null) {
                                aVarA2 = c0.a.a();
                                wm2Var.put(a0VarK3, aVarA2);
                            }
                            aVarA2.a |= 2;
                            aVarA2.b = cVar2;
                        }
                    }
                }
            }
            j();
        } else {
            j();
        }
        T(true);
        m0(false);
        wVar.d = 2;
    }

    public final void r() {
        l0();
        S();
        w wVar = this.m0;
        wVar.a(6);
        this.i.c();
        wVar.e = this.q.k();
        wVar.c = 0;
        if (this.h != null) {
            e eVar = this.q;
            int iM = gq2.m(eVar.g);
            if (iM == 1 ? eVar.k() > 0 : iM != 2) {
                Parcelable parcelable = this.h.g;
                if (parcelable != null) {
                    this.r.j0(parcelable);
                }
                this.h = null;
            }
        }
        wVar.g = false;
        this.r.h0(this.g, wVar);
        wVar.f = false;
        wVar.j = wVar.j && this.R != null;
        wVar.d = 4;
        T(true);
        m0(false);
    }

    @Override // android.view.ViewGroup
    public final void removeDetachedView(View view, boolean z2) {
        a0 a0VarK = K(view);
        if (a0VarK != null) {
            if (a0VarK.o()) {
                a0VarK.n &= -257;
            } else if (!a0VarK.s()) {
                StringBuilder sb = new StringBuilder("Called removeDetachedView with a view which is not flagged as tmp detached.");
                sb.append(a0VarK);
                throw new IllegalArgumentException(C0175b.h(this, sb));
            }
        }
        view.clearAnimation();
        o(view);
        super.removeDetachedView(view, z2);
    }

    public void removeOnChildAttachStateChangeListener(n nVar) {
        ArrayList arrayList = this.H;
        if (arrayList == null) {
            return;
        }
        arrayList.remove(nVar);
    }

    public void removeOnItemTouchListener(p pVar) {
        this.v.remove(pVar);
        if (this.w == pVar) {
            this.w = null;
        }
    }

    public void removeOnScrollListener(q qVar) {
        ArrayList arrayList = this.o0;
        if (arrayList != null) {
            arrayList.remove(qVar);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestChildFocus(View view, View view2) {
        v vVar = this.r.e;
        boolean z2 = true;
        if (!(vVar != null && vVar.e) && !O()) {
            z2 = false;
        }
        if (!z2 && view2 != null) {
            d0(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z2) {
        return this.r.s0(this, view, rect, z2, false);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z2) {
        ArrayList<p> arrayList = this.v;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.get(i2).e(z2);
        }
        super.requestDisallowInterceptTouchEvent(z2);
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        if (this.A != 0 || this.C) {
            this.B = true;
        } else {
            super.requestLayout();
        }
    }

    public final boolean s(int i2, int i3, int i4, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().c(i2, i3, i4, iArr, iArr2);
    }

    @Override // android.view.View
    public final void scrollBy(int i2, int i3) {
        m mVar = this.r;
        if (mVar == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.C) {
            return;
        }
        boolean zH = mVar.h();
        boolean zI = this.r.i();
        if (zH || zI) {
            if (!zH) {
                i2 = 0;
            }
            if (!zI) {
                i3 = 0;
            }
            e0(i2, i3, null, 0);
        }
    }

    @Override // android.view.View
    public final void scrollTo(int i2, int i3) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public final void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (O()) {
            int contentChangeTypes = accessibilityEvent != null ? accessibilityEvent.getContentChangeTypes() : 0;
            this.E |= contentChangeTypes != 0 ? contentChangeTypes : 0;
            i = 1;
        }
        if (i != 0) {
            return;
        }
        super.sendAccessibilityEventUnchecked(accessibilityEvent);
    }

    public void setAccessibilityDelegateCompat(androidx.recyclerview.widget.x xVar) {
        this.t0 = xVar;
        om3.k(this, xVar);
    }

    public void setAdapter(e eVar) {
        setLayoutFrozen(false);
        h0(eVar, false);
        X(false);
        requestLayout();
    }

    public void setChildDrawingOrderCallback(h hVar) {
        if (hVar == null) {
            return;
        }
        setChildrenDrawingOrderEnabled(hVar != null);
    }

    @Override // android.view.ViewGroup
    public void setClipToPadding(boolean z2) {
        if (z2 != this.l) {
            this.Q = null;
            this.O = null;
            this.P = null;
            this.N = null;
        }
        this.l = z2;
        super.setClipToPadding(z2);
        if (this.z) {
            requestLayout();
        }
    }

    public void setEdgeEffectFactory(i iVar) {
        iVar.getClass();
        this.M = iVar;
        this.Q = null;
        this.O = null;
        this.P = null;
        this.N = null;
    }

    public void setHasFixedSize(boolean z2) {
        this.y = z2;
    }

    public void setItemAnimator(j jVar) {
        j jVar2 = this.R;
        if (jVar2 != null) {
            jVar2.j();
            this.R.a = null;
        }
        this.R = jVar;
        if (jVar != null) {
            jVar.a = this.r0;
        }
    }

    public void setItemViewCacheSize(int i2) {
        s sVar = this.g;
        sVar.e = i2;
        sVar.m();
    }

    @Deprecated
    public void setLayoutFrozen(boolean z2) {
        suppressLayout(z2);
    }

    public void setLayoutManager(m mVar) {
        b.InterfaceC0025b interfaceC0025b;
        RecyclerView recyclerView;
        v vVar;
        if (mVar == this.r) {
            return;
        }
        int i2 = 0;
        setScrollState(0);
        z zVar = this.j0;
        RecyclerView.this.removeCallbacks(zVar);
        zVar.g.abortAnimation();
        m mVar2 = this.r;
        if (mVar2 != null && (vVar = mVar2.e) != null) {
            vVar.d();
        }
        m mVar3 = this.r;
        s sVar = this.g;
        if (mVar3 != null) {
            j jVar = this.R;
            if (jVar != null) {
                jVar.j();
            }
            this.r.o0(sVar);
            this.r.p0(sVar);
            sVar.a.clear();
            sVar.f();
            if (this.x) {
                m mVar4 = this.r;
                mVar4.g = false;
                mVar4.W(this);
            }
            this.r.B0(null);
            this.r = null;
        } else {
            sVar.a.clear();
            sVar.f();
        }
        androidx.recyclerview.widget.b bVar = this.j;
        bVar.b.g();
        ArrayList arrayList = bVar.c;
        int size = arrayList.size();
        while (true) {
            size--;
            interfaceC0025b = bVar.a;
            if (size < 0) {
                break;
            }
            View view = (View) arrayList.get(size);
            androidx.recyclerview.widget.v vVar2 = (androidx.recyclerview.widget.v) interfaceC0025b;
            vVar2.getClass();
            a0 a0VarK = K(view);
            if (a0VarK != null) {
                int i3 = a0VarK.t;
                RecyclerView recyclerView2 = vVar2.a;
                if (recyclerView2.O()) {
                    a0VarK.u = i3;
                    recyclerView2.z0.add(a0VarK);
                } else {
                    WeakHashMap<View, in3> weakHashMap = om3.a;
                    om3.d.p(a0VarK.e, i3);
                }
                a0VarK.t = 0;
            }
            arrayList.remove(size);
        }
        androidx.recyclerview.widget.v vVar3 = (androidx.recyclerview.widget.v) interfaceC0025b;
        int iA = vVar3.a();
        while (true) {
            recyclerView = vVar3.a;
            if (i2 >= iA) {
                break;
            }
            View childAt = recyclerView.getChildAt(i2);
            recyclerView.o(childAt);
            childAt.clearAnimation();
            i2++;
        }
        recyclerView.removeAllViews();
        this.r = mVar;
        if (mVar != null) {
            if (mVar.b != null) {
                StringBuilder sb = new StringBuilder("LayoutManager ");
                sb.append(mVar);
                sb.append(" is already attached to a RecyclerView:");
                throw new IllegalArgumentException(C0175b.h(mVar.b, sb));
            }
            mVar.B0(this);
            if (this.x) {
                this.r.g = true;
            }
        }
        sVar.m();
        requestLayout();
    }

    @Override // android.view.ViewGroup
    @Deprecated
    public void setLayoutTransition(LayoutTransition layoutTransition) {
        if (layoutTransition != null) {
            throw new IllegalArgumentException("Providing a LayoutTransition into RecyclerView is not supported. Please use setItemAnimator() instead for animating changes to the items in this RecyclerView");
        }
        super.setLayoutTransition(null);
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z2) {
        rl1 scrollingChildHelper = getScrollingChildHelper();
        if (scrollingChildHelper.d) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.i.p(scrollingChildHelper.c);
        }
        scrollingChildHelper.d = z2;
    }

    public void setOnFlingListener(o oVar) {
        this.d0 = oVar;
    }

    @Deprecated
    public void setOnScrollListener(q qVar) {
        this.n0 = qVar;
    }

    public void setPreserveFocusAfterLayout(boolean z2) {
        this.i0 = z2;
    }

    public void setRecycledViewPool(r rVar) {
        s sVar = this.g;
        RecyclerView recyclerView = RecyclerView.this;
        sVar.e(recyclerView.q, false);
        if (sVar.g != null) {
            r1.b--;
        }
        sVar.g = rVar;
        if (rVar != null && recyclerView.getAdapter() != null) {
            sVar.g.b++;
        }
        sVar.d();
    }

    @Deprecated
    public void setRecyclerListener(t tVar) {
        this.s = tVar;
    }

    void setScrollState(int i2) {
        v vVar;
        if (i2 == this.S) {
            return;
        }
        if (G0) {
            new Exception();
        }
        this.S = i2;
        if (i2 != 2) {
            z zVar = this.j0;
            RecyclerView.this.removeCallbacks(zVar);
            zVar.g.abortAnimation();
            m mVar = this.r;
            if (mVar != null && (vVar = mVar.e) != null) {
                vVar.d();
            }
        }
        m mVar2 = this.r;
        if (mVar2 != null) {
            mVar2.l0(i2);
        }
        q qVar = this.n0;
        if (qVar != null) {
            qVar.a(i2, this);
        }
        ArrayList arrayList = this.o0;
        if (arrayList == null) {
            return;
        }
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                return;
            } else {
                ((q) this.o0.get(size)).a(i2, this);
            }
        }
    }

    public void setScrollingTouchSlop(int i2) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        if (i2 != 0) {
            if (i2 == 1) {
                this.c0 = viewConfiguration.getScaledPagingTouchSlop();
                return;
            }
            Log.w("RecyclerView", "setScrollingTouchSlop(): bad argument constant " + i2 + "; using default value");
        }
        this.c0 = viewConfiguration.getScaledTouchSlop();
    }

    public void setViewCacheExtension(y yVar) {
        this.g.getClass();
    }

    @Override // android.view.View
    public final boolean startNestedScroll(int i2) {
        return getScrollingChildHelper().g(i2, 0);
    }

    @Override // android.view.View
    public final void stopNestedScroll() {
        getScrollingChildHelper().h(0);
    }

    @Override // android.view.ViewGroup
    public final void suppressLayout(boolean z2) {
        v vVar;
        if (z2 != this.C) {
            h("Do not suppressLayout in layout or scroll");
            if (!z2) {
                this.C = false;
                if (this.B && this.r != null && this.q != null) {
                    requestLayout();
                }
                this.B = false;
                return;
            }
            long jUptimeMillis = SystemClock.uptimeMillis();
            onTouchEvent(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0));
            this.C = true;
            this.D = true;
            setScrollState(0);
            z zVar = this.j0;
            RecyclerView.this.removeCallbacks(zVar);
            zVar.g.abortAnimation();
            m mVar = this.r;
            if (mVar == null || (vVar = mVar.e) == null) {
                return;
            }
            vVar.d();
        }
    }

    public final void t(int i2, int i3, int i4, int i5, int[] iArr, int i6, int[] iArr2) {
        getScrollingChildHelper().e(i2, i3, i4, i5, iArr, i6, iArr2);
    }

    public final void u(int i2, int i3) {
        this.L++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX - i2, scrollY - i3);
        q qVar = this.n0;
        if (qVar != null) {
            qVar.b(this, i2, i3);
        }
        ArrayList arrayList = this.o0;
        if (arrayList != null) {
            int size = arrayList.size();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                } else {
                    ((q) this.o0.get(size)).b(this, i2, i3);
                }
            }
        }
        this.L--;
    }

    public final void v() {
        if (this.Q != null) {
            return;
        }
        ((x) this.M).getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.Q = edgeEffect;
        if (this.l) {
            edgeEffect.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffect.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public final void w() {
        if (this.N != null) {
            return;
        }
        ((x) this.M).getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.N = edgeEffect;
        if (this.l) {
            edgeEffect.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffect.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public final void x() {
        if (this.P != null) {
            return;
        }
        ((x) this.M).getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.P = edgeEffect;
        if (this.l) {
            edgeEffect.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffect.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public final void y() {
        if (this.O != null) {
            return;
        }
        ((x) this.M).getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.O = edgeEffect;
        if (this.l) {
            edgeEffect.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffect.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public final String z() {
        return " " + super.toString() + ", adapter:" + this.q + ", layout:" + this.r + ", context:" + getContext();
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.recyclerViewStyle);
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public Parcelable g;

        public class a implements Parcelable.ClassLoaderCreator<SavedState> {
            @Override // android.os.Parcelable.ClassLoaderCreator
            public final SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public final Object[] newArray(int i) {
                return new SavedState[i];
            }

            @Override // android.os.Parcelable.Creator
            public final Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.g = parcel.readParcelable(classLoader == null ? m.class.getClassLoader() : classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.e, i);
            parcel.writeParcelable(this.g, 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public RecyclerView(Context context, AttributeSet attributeSet, int i2) {
        TypedArray typedArray;
        char c2;
        ClassLoader classLoader;
        Constructor constructor;
        Object[] objArr;
        super(context, attributeSet, i2);
        this.f = new u();
        this.g = new s();
        this.k = new c0();
        this.m = new a();
        this.n = new Rect();
        this.o = new Rect();
        this.p = new RectF();
        this.t = new ArrayList();
        this.u = new ArrayList<>();
        this.v = new ArrayList<>();
        this.A = 0;
        this.I = false;
        this.f19J = false;
        this.K = 0;
        this.L = 0;
        this.M = O0;
        this.R = new androidx.recyclerview.widget.c();
        this.S = 0;
        this.T = -1;
        this.g0 = Float.MIN_VALUE;
        this.h0 = Float.MIN_VALUE;
        this.i0 = true;
        this.j0 = new z();
        this.l0 = L0 ? new k.b() : null;
        this.m0 = new w();
        this.p0 = false;
        this.q0 = false;
        k kVar = new k();
        this.r0 = kVar;
        this.s0 = false;
        this.u0 = new int[2];
        this.w0 = new int[2];
        this.x0 = new int[2];
        this.y0 = new int[2];
        this.z0 = new ArrayList();
        this.A0 = new b();
        this.C0 = 0;
        this.D0 = 0;
        this.E0 = new d();
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.c0 = viewConfiguration.getScaledTouchSlop();
        this.g0 = viewConfiguration.getScaledHorizontalScrollFactor();
        this.h0 = viewConfiguration.getScaledVerticalScrollFactor();
        this.e0 = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f0 = viewConfiguration.getScaledMaximumFlingVelocity();
        this.e = context.getResources().getDisplayMetrics().density * 160.0f * 386.0878f * 0.84f;
        setWillNotDraw(getOverScrollMode() == 2);
        this.R.a = kVar;
        this.i = new androidx.recyclerview.widget.a(new androidx.recyclerview.widget.w(this));
        this.j = new androidx.recyclerview.widget.b(new androidx.recyclerview.widget.v(this));
        WeakHashMap<View, in3> weakHashMap = om3.a;
        if (om3.l.a(this) == 0) {
            om3.l.b(this, 8);
        }
        if (om3.d.b(this) == 0) {
            om3.d.p(this, 1);
        }
        this.G = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new androidx.recyclerview.widget.x(this));
        int[] iArr = R$styleable.RecyclerView;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i2, 0);
        om3.n.b(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, i2, 0);
        String string = typedArrayObtainStyledAttributes.getString(R$styleable.RecyclerView_layoutManager);
        if (typedArrayObtainStyledAttributes.getInt(R$styleable.RecyclerView_android_descendantFocusability, -1) == -1) {
            setDescendantFocusability(262144);
        }
        this.l = typedArrayObtainStyledAttributes.getBoolean(R$styleable.RecyclerView_android_clipToPadding, true);
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.RecyclerView_fastScrollEnabled, false)) {
            StateListDrawable stateListDrawable = (StateListDrawable) typedArrayObtainStyledAttributes.getDrawable(R$styleable.RecyclerView_fastScrollVerticalThumbDrawable);
            Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R$styleable.RecyclerView_fastScrollVerticalTrackDrawable);
            StateListDrawable stateListDrawable2 = (StateListDrawable) typedArrayObtainStyledAttributes.getDrawable(R$styleable.RecyclerView_fastScrollHorizontalThumbDrawable);
            Drawable drawable2 = typedArrayObtainStyledAttributes.getDrawable(R$styleable.RecyclerView_fastScrollHorizontalTrackDrawable);
            if (stateListDrawable != null && drawable != null && stateListDrawable2 != null && drawable2 != null) {
                Resources resources = getContext().getResources();
                typedArray = typedArrayObtainStyledAttributes;
                c2 = 2;
                new androidx.recyclerview.widget.j(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(R$dimen.fastscroll_default_thickness), resources.getDimensionPixelSize(R$dimen.fastscroll_minimum_range), resources.getDimensionPixelOffset(R$dimen.fastscroll_margin));
            } else {
                throw new IllegalArgumentException(C0175b.h(this, new StringBuilder("Trying to set fast scroller without both required drawables.")));
            }
        } else {
            typedArray = typedArrayObtainStyledAttributes;
            c2 = 2;
        }
        typedArray.recycle();
        if (string != null) {
            String strTrim = string.trim();
            if (!strTrim.isEmpty()) {
                if (strTrim.charAt(0) == '.') {
                    strTrim = context.getPackageName() + strTrim;
                } else if (!strTrim.contains(".")) {
                    strTrim = RecyclerView.class.getPackage().getName() + '.' + strTrim;
                }
                String str = strTrim;
                try {
                    if (isInEditMode()) {
                        classLoader = getClass().getClassLoader();
                    } else {
                        classLoader = context.getClassLoader();
                    }
                    Class<? extends U> clsAsSubclass = Class.forName(str, false, classLoader).asSubclass(m.class);
                    try {
                        constructor = clsAsSubclass.getConstructor(M0);
                        objArr = new Object[4];
                        objArr[0] = context;
                        objArr[1] = attributeSet;
                        objArr[c2] = Integer.valueOf(i2);
                        objArr[3] = 0;
                    } catch (NoSuchMethodException e2) {
                        try {
                            constructor = clsAsSubclass.getConstructor(new Class[0]);
                            objArr = null;
                        } catch (NoSuchMethodException e3) {
                            e3.initCause(e2);
                            throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + str, e3);
                        }
                    }
                    constructor.setAccessible(true);
                    setLayoutManager((m) constructor.newInstance(objArr));
                } catch (ClassCastException e4) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + str, e4);
                } catch (ClassNotFoundException e5) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + str, e5);
                } catch (IllegalAccessException e6) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + str, e6);
                } catch (InstantiationException e7) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + str, e7);
                } catch (InvocationTargetException e8) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + str, e8);
                }
            }
        }
        int[] iArr2 = H0;
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, iArr2, i2, 0);
        om3.n.b(this, context, iArr2, attributeSet, typedArrayObtainStyledAttributes2, i2, 0);
        boolean z2 = typedArrayObtainStyledAttributes2.getBoolean(0, true);
        typedArrayObtainStyledAttributes2.recycle();
        setNestedScrollingEnabled(z2);
        setTag(q22.b, Boolean.TRUE);
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public a0 a;
        public final Rect b;
        public boolean c;
        public boolean d;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.b = new Rect();
            this.c = true;
            this.d = false;
        }

        public final int a() {
            return this.a.g();
        }

        public final boolean b() {
            return (this.a.n & 2) != 0;
        }

        public final boolean c() {
            return this.a.m();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.b = new Rect();
            this.c = true;
            this.d = false;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.b = new Rect();
            this.c = true;
            this.d = false;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.b = new Rect();
            this.c = true;
            this.d = false;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.LayoutParams) layoutParams);
            this.b = new Rect();
            this.c = true;
            this.d = false;
        }
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        m mVar = this.r;
        if (mVar != null) {
            return mVar.x(layoutParams);
        }
        throw new IllegalStateException(C0175b.h(this, new StringBuilder("RecyclerView has no LayoutManager")));
    }

    public static abstract class g {
        public void c(int i, int i2, Object obj) {
            b();
        }

        public void a() {
        }

        public void b() {
        }

        public void d(int i, int i2) {
        }

        public void e(int i, int i2) {
        }

        public void f(int i, int i2) {
        }
    }

    public static abstract class m {
        public androidx.recyclerview.widget.b a;
        public RecyclerView b;
        public final b0 c;
        public final b0 d;
        public v e;
        public boolean f;
        public boolean g;
        public final boolean h;
        public final boolean i;
        public int j;
        public boolean k;
        public int l;
        public int m;
        public int n;
        public int o;

        public class a implements b0.b {
            public a() {
            }

            @Override // androidx.recyclerview.widget.b0.b
            public final int a(View view) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                m.this.getClass();
                return (view.getLeft() - m.F(view)) - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
            }

            @Override // androidx.recyclerview.widget.b0.b
            public final int b() {
                return m.this.H();
            }

            @Override // androidx.recyclerview.widget.b0.b
            public final int c() {
                m mVar = m.this;
                return mVar.n - mVar.I();
            }

            @Override // androidx.recyclerview.widget.b0.b
            public final View d(int i) {
                return m.this.z(i);
            }

            @Override // androidx.recyclerview.widget.b0.b
            public final int e(View view) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                m.this.getClass();
                return m.M(view) + view.getRight() + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
            }
        }

        public class b implements b0.b {
            public b() {
            }

            @Override // androidx.recyclerview.widget.b0.b
            public final int a(View view) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                m.this.getClass();
                return (view.getTop() - m.O(view)) - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
            }

            @Override // androidx.recyclerview.widget.b0.b
            public final int b() {
                return m.this.J();
            }

            @Override // androidx.recyclerview.widget.b0.b
            public final int c() {
                m mVar = m.this;
                return mVar.o - mVar.G();
            }

            @Override // androidx.recyclerview.widget.b0.b
            public final View d(int i) {
                return m.this.z(i);
            }

            @Override // androidx.recyclerview.widget.b0.b
            public final int e(View view) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                m.this.getClass();
                return m.y(view) + view.getBottom() + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
            }
        }

        public interface c {
        }

        public static class d {
            public int a;
            public int b;
            public boolean c;
            public boolean d;
        }

        public m() {
            a aVar = new a();
            b bVar = new b();
            this.c = new b0(aVar);
            this.d = new b0(bVar);
            this.f = false;
            this.g = false;
            this.h = true;
            this.i = true;
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x0020  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x002f  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static int B(boolean r4, int r5, int r6, int r7, int r8) {
            /*
                int r5 = r5 - r7
                r7 = 0
                int r5 = java.lang.Math.max(r7, r5)
                r0 = -2
                r1 = -1
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = 1073741824(0x40000000, float:2.0)
                if (r4 == 0) goto L1a
                if (r8 < 0) goto L11
                goto L1c
            L11:
                if (r8 != r1) goto L2f
                if (r6 == r2) goto L20
                if (r6 == 0) goto L2f
                if (r6 == r3) goto L20
                goto L2f
            L1a:
                if (r8 < 0) goto L1e
            L1c:
                r6 = r3
                goto L31
            L1e:
                if (r8 != r1) goto L22
            L20:
                r8 = r5
                goto L31
            L22:
                if (r8 != r0) goto L2f
                if (r6 == r2) goto L2c
                if (r6 != r3) goto L29
                goto L2c
            L29:
                r8 = r5
                r6 = r7
                goto L31
            L2c:
                r8 = r5
                r6 = r2
                goto L31
            L2f:
                r6 = r7
                r8 = r6
            L31:
                int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r6)
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.m.B(boolean, int, int, int, int):int");
        }

        public static int F(View view) {
            return ((LayoutParams) view.getLayoutParams()).b.left;
        }

        public static int K(View view) {
            return ((LayoutParams) view.getLayoutParams()).a();
        }

        public static d L(Context context, AttributeSet attributeSet, int i, int i2) {
            d dVar = new d();
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RecyclerView, i, i2);
            dVar.a = typedArrayObtainStyledAttributes.getInt(R$styleable.RecyclerView_android_orientation, 1);
            dVar.b = typedArrayObtainStyledAttributes.getInt(R$styleable.RecyclerView_spanCount, 1);
            dVar.c = typedArrayObtainStyledAttributes.getBoolean(R$styleable.RecyclerView_reverseLayout, false);
            dVar.d = typedArrayObtainStyledAttributes.getBoolean(R$styleable.RecyclerView_stackFromEnd, false);
            typedArrayObtainStyledAttributes.recycle();
            return dVar;
        }

        public static int M(View view) {
            return ((LayoutParams) view.getLayoutParams()).b.right;
        }

        public static int O(View view) {
            return ((LayoutParams) view.getLayoutParams()).b.top;
        }

        public static boolean R(int i, int i2, int i3) {
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

        public static void S(View view, int i, int i2, int i3, int i4) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Rect rect = layoutParams.b;
            view.layout(i + rect.left + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, i2 + rect.top + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, (i3 - rect.right) - ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, (i4 - rect.bottom) - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
        }

        public static int k(int i, int i2, int i3) {
            int mode = View.MeasureSpec.getMode(i);
            int size = View.MeasureSpec.getSize(i);
            return mode != Integer.MIN_VALUE ? mode != 1073741824 ? Math.max(i2, i3) : size : Math.min(size, Math.max(i2, i3));
        }

        public static int y(View view) {
            return ((LayoutParams) view.getLayoutParams()).b.bottom;
        }

        public final int A() {
            androidx.recyclerview.widget.b bVar = this.a;
            if (bVar != null) {
                return bVar.e();
            }
            return 0;
        }

        public final void A0(int i, int i2) {
            int iA = A();
            if (iA == 0) {
                this.b.n(i, i2);
                return;
            }
            int i3 = Integer.MIN_VALUE;
            int i4 = Integer.MAX_VALUE;
            int i5 = Integer.MIN_VALUE;
            int i6 = Integer.MAX_VALUE;
            for (int i7 = 0; i7 < iA; i7++) {
                View viewZ = z(i7);
                Rect rect = this.b.n;
                RecyclerView.L(viewZ, rect);
                int i8 = rect.left;
                if (i8 < i6) {
                    i6 = i8;
                }
                int i9 = rect.right;
                if (i9 > i3) {
                    i3 = i9;
                }
                int i10 = rect.top;
                if (i10 < i4) {
                    i4 = i10;
                }
                int i11 = rect.bottom;
                if (i11 > i5) {
                    i5 = i11;
                }
            }
            this.b.n.set(i6, i4, i3, i5);
            z0(this.b.n, i, i2);
        }

        public final void B0(RecyclerView recyclerView) {
            if (recyclerView == null) {
                this.b = null;
                this.a = null;
                this.n = 0;
                this.o = 0;
            } else {
                this.b = recyclerView;
                this.a = recyclerView.j;
                this.n = recyclerView.getWidth();
                this.o = recyclerView.getHeight();
            }
            this.l = TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
            this.m = TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
        }

        public int C(s sVar, w wVar) {
            RecyclerView recyclerView = this.b;
            if (recyclerView == null || recyclerView.q == null || !h()) {
                return 1;
            }
            return this.b.q.k();
        }

        public final boolean C0(View view, int i, int i2, LayoutParams layoutParams) {
            return (!view.isLayoutRequested() && this.h && R(view.getWidth(), i, ((ViewGroup.MarginLayoutParams) layoutParams).width) && R(view.getHeight(), i2, ((ViewGroup.MarginLayoutParams) layoutParams).height)) ? false : true;
        }

        public int D() {
            RecyclerView recyclerView = this.b;
            e adapter = recyclerView != null ? recyclerView.getAdapter() : null;
            if (adapter != null) {
                return adapter.k();
            }
            return 0;
        }

        public boolean D0() {
            return false;
        }

        public final int E() {
            RecyclerView recyclerView = this.b;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            return om3.e.c(recyclerView);
        }

        public final boolean E0(View view, int i, int i2, LayoutParams layoutParams) {
            return (this.h && R(view.getMeasuredWidth(), i, ((ViewGroup.MarginLayoutParams) layoutParams).width) && R(view.getMeasuredHeight(), i2, ((ViewGroup.MarginLayoutParams) layoutParams).height)) ? false : true;
        }

        @SuppressLint({"UnknownNullness"})
        public void F0(RecyclerView recyclerView, int i) {
            Log.e("RecyclerView", "You must override smoothScrollToPosition to support smooth scrolling");
        }

        public final int G() {
            RecyclerView recyclerView = this.b;
            if (recyclerView != null) {
                return recyclerView.getPaddingBottom();
            }
            return 0;
        }

        @SuppressLint({"UnknownNullness"})
        public final void G0(LinearSmoothScroller linearSmoothScroller) {
            v vVar = this.e;
            if (vVar != null && linearSmoothScroller != vVar && vVar.e) {
                vVar.d();
            }
            this.e = linearSmoothScroller;
            RecyclerView recyclerView = this.b;
            z zVar = recyclerView.j0;
            RecyclerView.this.removeCallbacks(zVar);
            zVar.g.abortAnimation();
            if (linearSmoothScroller.h) {
                Log.w("RecyclerView", "An instance of " + linearSmoothScroller.getClass().getSimpleName() + " was started more than once. Each instance of" + linearSmoothScroller.getClass().getSimpleName() + " is intended to only be used once. You should create a new instance for each use.");
            }
            linearSmoothScroller.b = recyclerView;
            linearSmoothScroller.c = this;
            int i = linearSmoothScroller.a;
            if (i == -1) {
                throw new IllegalArgumentException("Invalid target position");
            }
            recyclerView.m0.a = i;
            linearSmoothScroller.e = true;
            linearSmoothScroller.d = true;
            linearSmoothScroller.f = recyclerView.r.u(i);
            linearSmoothScroller.b.j0.b();
            linearSmoothScroller.h = true;
        }

        public final int H() {
            RecyclerView recyclerView = this.b;
            if (recyclerView != null) {
                return recyclerView.getPaddingLeft();
            }
            return 0;
        }

        public boolean H0() {
            return false;
        }

        public final int I() {
            RecyclerView recyclerView = this.b;
            if (recyclerView != null) {
                return recyclerView.getPaddingRight();
            }
            return 0;
        }

        public final int J() {
            RecyclerView recyclerView = this.b;
            if (recyclerView != null) {
                return recyclerView.getPaddingTop();
            }
            return 0;
        }

        public int N(s sVar, w wVar) {
            RecyclerView recyclerView = this.b;
            if (recyclerView == null || recyclerView.q == null || !i()) {
                return 1;
            }
            return this.b.q.k();
        }

        public final void P(View view, Rect rect) {
            Matrix matrix;
            Rect rect2 = ((LayoutParams) view.getLayoutParams()).b;
            rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
            if (this.b != null && (matrix = view.getMatrix()) != null && !matrix.isIdentity()) {
                RectF rectF = this.b.p;
                rectF.set(rect);
                matrix.mapRect(rectF);
                rect.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
            }
            rect.offset(view.getLeft(), view.getTop());
        }

        public boolean Q() {
            return false;
        }

        public void T(int i) {
            RecyclerView recyclerView = this.b;
            if (recyclerView != null) {
                int iE = recyclerView.j.e();
                for (int i2 = 0; i2 < iE; i2++) {
                    recyclerView.j.d(i2).offsetLeftAndRight(i);
                }
            }
        }

        public void U(int i) {
            RecyclerView recyclerView = this.b;
            if (recyclerView != null) {
                int iE = recyclerView.j.e();
                for (int i2 = 0; i2 < iE; i2++) {
                    recyclerView.j.d(i2).offsetTopAndBottom(i);
                }
            }
        }

        public View X(View view, int i, s sVar, w wVar) {
            return null;
        }

        public void Y(AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.b;
            s sVar = recyclerView.g;
            w wVar = recyclerView.m0;
            if (recyclerView == null || accessibilityEvent == null) {
                return;
            }
            boolean z = true;
            if (!recyclerView.canScrollVertically(1) && !this.b.canScrollVertically(-1) && !this.b.canScrollHorizontally(-1) && !this.b.canScrollHorizontally(1)) {
                z = false;
            }
            accessibilityEvent.setScrollable(z);
            e eVar = this.b.q;
            if (eVar != null) {
                accessibilityEvent.setItemCount(eVar.k());
            }
        }

        public void Z(s sVar, w wVar, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (this.b.canScrollVertically(-1) || this.b.canScrollHorizontally(-1)) {
                accessibilityNodeInfoCompat.a(8192);
                accessibilityNodeInfoCompat.s(true);
            }
            if (this.b.canScrollVertically(1) || this.b.canScrollHorizontally(1)) {
                accessibilityNodeInfoCompat.a(4096);
                accessibilityNodeInfoCompat.s(true);
            }
            accessibilityNodeInfoCompat.p(AccessibilityNodeInfoCompat.c.a(N(sVar, wVar), C(sVar, wVar), 0));
        }

        public final void a0(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            a0 a0VarK = RecyclerView.K(view);
            if (a0VarK == null || a0VarK.m() || this.a.j(a0VarK.e)) {
                return;
            }
            RecyclerView recyclerView = this.b;
            b0(recyclerView.g, recyclerView.m0, view, accessibilityNodeInfoCompat);
        }

        public void b0(s sVar, w wVar, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            accessibilityNodeInfoCompat.q(AccessibilityNodeInfoCompat.d.a(i() ? K(view) : 0, 1, h() ? K(view) : 0, 1, false, false));
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x0065  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x006e  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x0076  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x00e4  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void e(android.view.View r8, int r9, boolean r10) {
            /*
                Method dump skipped, instruction units count: 341
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.m.e(android.view.View, int, boolean):void");
        }

        @SuppressLint({"UnknownNullness"})
        public void f(String str) {
            RecyclerView recyclerView = this.b;
            if (recyclerView != null) {
                recyclerView.h(str);
            }
        }

        public final void g(View view, Rect rect) {
            RecyclerView recyclerView = this.b;
            if (recyclerView == null) {
                rect.set(0, 0, 0, 0);
            } else {
                rect.set(recyclerView.M(view));
            }
        }

        public boolean h() {
            return false;
        }

        @SuppressLint({"UnknownNullness"})
        public void h0(s sVar, w wVar) {
            Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
        }

        public boolean i() {
            return false;
        }

        public boolean j(LayoutParams layoutParams) {
            return layoutParams != null;
        }

        public Parcelable k0() {
            return null;
        }

        public boolean m0(int i, Bundle bundle) {
            RecyclerView recyclerView = this.b;
            return n0(recyclerView.g, recyclerView.m0, i, bundle);
        }

        public int n(w wVar) {
            return 0;
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x0089 A[PHI: r3
  0x0089: PHI (r3v8 int) = (r3v5 int), (r3v11 int) binds: [B:27:0x007b, B:20:0x0053] A[DONT_GENERATE, DONT_INLINE]] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean n0(androidx.recyclerview.widget.RecyclerView.s r3, androidx.recyclerview.widget.RecyclerView.w r4, int r5, android.os.Bundle r6) {
            /*
                r2 = this;
                androidx.recyclerview.widget.RecyclerView r3 = r2.b
                r4 = 0
                if (r3 != 0) goto L6
                return r4
            L6:
                int r3 = r2.o
                int r6 = r2.n
                android.graphics.Rect r0 = new android.graphics.Rect
                r0.<init>()
                androidx.recyclerview.widget.RecyclerView r1 = r2.b
                android.graphics.Matrix r1 = r1.getMatrix()
                boolean r1 = r1.isIdentity()
                if (r1 == 0) goto L2b
                androidx.recyclerview.widget.RecyclerView r1 = r2.b
                boolean r1 = r1.getGlobalVisibleRect(r0)
                if (r1 == 0) goto L2b
                int r3 = r0.height()
                int r6 = r0.width()
            L2b:
                r0 = 4096(0x1000, float:5.74E-42)
                r1 = 1
                if (r5 == r0) goto L61
                r0 = 8192(0x2000, float:1.148E-41)
                if (r5 == r0) goto L37
                r3 = r4
                r5 = r3
                goto L8a
            L37:
                androidx.recyclerview.widget.RecyclerView r5 = r2.b
                r0 = -1
                boolean r5 = r5.canScrollVertically(r0)
                if (r5 == 0) goto L4c
                int r5 = r2.J()
                int r3 = r3 - r5
                int r5 = r2.G()
                int r3 = r3 - r5
                int r3 = -r3
                goto L4d
            L4c:
                r3 = r4
            L4d:
                androidx.recyclerview.widget.RecyclerView r5 = r2.b
                boolean r5 = r5.canScrollHorizontally(r0)
                if (r5 == 0) goto L89
                int r5 = r2.H()
                int r6 = r6 - r5
                int r5 = r2.I()
                int r6 = r6 - r5
                int r5 = -r6
                goto L8a
            L61:
                androidx.recyclerview.widget.RecyclerView r5 = r2.b
                boolean r5 = r5.canScrollVertically(r1)
                if (r5 == 0) goto L74
                int r5 = r2.J()
                int r3 = r3 - r5
                int r5 = r2.G()
                int r3 = r3 - r5
                goto L75
            L74:
                r3 = r4
            L75:
                androidx.recyclerview.widget.RecyclerView r5 = r2.b
                boolean r5 = r5.canScrollHorizontally(r1)
                if (r5 == 0) goto L89
                int r5 = r2.H()
                int r6 = r6 - r5
                int r5 = r2.I()
                int r5 = r6 - r5
                goto L8a
            L89:
                r5 = r4
            L8a:
                if (r3 != 0) goto L8f
                if (r5 != 0) goto L8f
                return r4
            L8f:
                androidx.recyclerview.widget.RecyclerView r2 = r2.b
                r2.j0(r5, r3, r1)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.m.n0(androidx.recyclerview.widget.RecyclerView$s, androidx.recyclerview.widget.RecyclerView$w, int, android.os.Bundle):boolean");
        }

        public int o(w wVar) {
            return 0;
        }

        public final void o0(s sVar) {
            int iA = A();
            while (true) {
                iA--;
                if (iA < 0) {
                    return;
                }
                if (!RecyclerView.K(z(iA)).s()) {
                    View viewZ = z(iA);
                    r0(iA);
                    sVar.h(viewZ);
                }
            }
        }

        public int p(w wVar) {
            return 0;
        }

        public final void p0(s sVar) {
            ArrayList<a0> arrayList;
            int size = sVar.a.size();
            int i = size - 1;
            while (true) {
                arrayList = sVar.a;
                if (i < 0) {
                    break;
                }
                View view = arrayList.get(i).e;
                a0 a0VarK = RecyclerView.K(view);
                if (!a0VarK.s()) {
                    a0VarK.r(false);
                    if (a0VarK.o()) {
                        this.b.removeDetachedView(view, false);
                    }
                    j jVar = this.b.R;
                    if (jVar != null) {
                        jVar.i(a0VarK);
                    }
                    a0VarK.r(true);
                    a0 a0VarK2 = RecyclerView.K(view);
                    a0VarK2.r = null;
                    a0VarK2.s = false;
                    a0VarK2.n &= -33;
                    sVar.i(a0VarK2);
                }
                i--;
            }
            arrayList.clear();
            ArrayList<a0> arrayList2 = sVar.b;
            if (arrayList2 != null) {
                arrayList2.clear();
            }
            if (size > 0) {
                this.b.invalidate();
            }
        }

        public int q(w wVar) {
            return 0;
        }

        @SuppressLint({"UnknownNullness"})
        public final void q0(View view) {
            androidx.recyclerview.widget.b bVar = this.a;
            androidx.recyclerview.widget.v vVar = (androidx.recyclerview.widget.v) bVar.a;
            int iIndexOfChild = vVar.a.indexOfChild(view);
            if (iIndexOfChild < 0) {
                return;
            }
            if (bVar.b.f(iIndexOfChild)) {
                bVar.k(view);
            }
            vVar.b(iIndexOfChild);
        }

        public int r(w wVar) {
            return 0;
        }

        public final void r0(int i) {
            if (z(i) != null) {
                androidx.recyclerview.widget.b bVar = this.a;
                int iF = bVar.f(i);
                androidx.recyclerview.widget.v vVar = (androidx.recyclerview.widget.v) bVar.a;
                View childAt = vVar.a.getChildAt(iF);
                if (childAt == null) {
                    return;
                }
                if (bVar.b.f(iF)) {
                    bVar.k(childAt);
                }
                vVar.b(iF);
            }
        }

        public int s(w wVar) {
            return 0;
        }

        /* JADX WARN: Removed duplicated region for block: B:28:0x00a8  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x00ab  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean s0(androidx.recyclerview.widget.RecyclerView r9, android.view.View r10, android.graphics.Rect r11, boolean r12, boolean r13) {
            /*
                r8 = this;
                int r0 = r8.H()
                int r1 = r8.J()
                int r2 = r8.n
                int r3 = r8.I()
                int r2 = r2 - r3
                int r3 = r8.o
                int r4 = r8.G()
                int r3 = r3 - r4
                int r4 = r10.getLeft()
                int r5 = r11.left
                int r4 = r4 + r5
                int r5 = r10.getScrollX()
                int r4 = r4 - r5
                int r5 = r10.getTop()
                int r6 = r11.top
                int r5 = r5 + r6
                int r10 = r10.getScrollY()
                int r5 = r5 - r10
                int r10 = r11.width()
                int r10 = r10 + r4
                int r11 = r11.height()
                int r11 = r11 + r5
                int r4 = r4 - r0
                r0 = 0
                int r6 = java.lang.Math.min(r0, r4)
                int r5 = r5 - r1
                int r1 = java.lang.Math.min(r0, r5)
                int r10 = r10 - r2
                int r2 = java.lang.Math.max(r0, r10)
                int r11 = r11 - r3
                int r11 = java.lang.Math.max(r0, r11)
                int r3 = r8.E()
                r7 = 1
                if (r3 != r7) goto L5c
                if (r2 == 0) goto L57
                goto L64
            L57:
                int r2 = java.lang.Math.max(r6, r10)
                goto L64
            L5c:
                if (r6 == 0) goto L5f
                goto L63
            L5f:
                int r6 = java.lang.Math.min(r4, r2)
            L63:
                r2 = r6
            L64:
                if (r1 == 0) goto L67
                goto L6b
            L67:
                int r1 = java.lang.Math.min(r5, r11)
            L6b:
                if (r13 == 0) goto Lab
                android.view.View r10 = r9.getFocusedChild()
                if (r10 != 0) goto L74
                goto La8
            L74:
                int r11 = r8.H()
                int r13 = r8.J()
                int r3 = r8.n
                int r4 = r8.I()
                int r3 = r3 - r4
                int r4 = r8.o
                int r5 = r8.G()
                int r4 = r4 - r5
                androidx.recyclerview.widget.RecyclerView r8 = r8.b
                android.graphics.Rect r8 = r8.n
                androidx.recyclerview.widget.RecyclerView.L(r10, r8)
                int r10 = r8.left
                int r10 = r10 - r2
                if (r10 >= r3) goto La8
                int r10 = r8.right
                int r10 = r10 - r2
                if (r10 <= r11) goto La8
                int r10 = r8.top
                int r10 = r10 - r1
                if (r10 >= r4) goto La8
                int r8 = r8.bottom
                int r8 = r8 - r1
                if (r8 > r13) goto La6
                goto La8
            La6:
                r8 = r7
                goto La9
            La8:
                r8 = r0
            La9:
                if (r8 == 0) goto Lb0
            Lab:
                if (r2 != 0) goto Lb1
                if (r1 == 0) goto Lb0
                goto Lb1
            Lb0:
                return r0
            Lb1:
                if (r12 == 0) goto Lb7
                r9.scrollBy(r2, r1)
                goto Lba
            Lb7:
                r9.j0(r2, r1, r0)
            Lba:
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.m.s0(androidx.recyclerview.widget.RecyclerView, android.view.View, android.graphics.Rect, boolean, boolean):boolean");
        }

        public final void t(s sVar) {
            int iA = A();
            while (true) {
                iA--;
                if (iA < 0) {
                    return;
                }
                View viewZ = z(iA);
                a0 a0VarK = RecyclerView.K(viewZ);
                if (a0VarK.s()) {
                    if (RecyclerView.G0) {
                        a0VarK.toString();
                    }
                } else if (!a0VarK.k() || a0VarK.m() || this.b.q.f) {
                    z(iA);
                    this.a.c(iA);
                    sVar.j(viewZ);
                    this.b.k.c(a0VarK);
                } else {
                    r0(iA);
                    sVar.i(a0VarK);
                }
            }
        }

        public final void t0() {
            RecyclerView recyclerView = this.b;
            if (recyclerView != null) {
                recyclerView.requestLayout();
            }
        }

        public View u(int i) {
            int iA = A();
            for (int i2 = 0; i2 < iA; i2++) {
                View viewZ = z(i2);
                a0 a0VarK = RecyclerView.K(viewZ);
                if (a0VarK != null && a0VarK.g() == i && !a0VarK.s() && (this.b.m0.g || !a0VarK.m())) {
                    return viewZ;
                }
            }
            return null;
        }

        @SuppressLint({"UnknownNullness"})
        public int u0(int i, s sVar, w wVar) {
            return 0;
        }

        @SuppressLint({"UnknownNullness"})
        public abstract LayoutParams v();

        public void v0(int i) {
            if (RecyclerView.G0) {
                Log.e("RecyclerView", "You MUST implement scrollToPosition. It will soon become abstract");
            }
        }

        @SuppressLint({"UnknownNullness"})
        public LayoutParams w(Context context, AttributeSet attributeSet) {
            return new LayoutParams(context, attributeSet);
        }

        @SuppressLint({"UnknownNullness"})
        public int w0(int i, s sVar, w wVar) {
            return 0;
        }

        @SuppressLint({"UnknownNullness"})
        public LayoutParams x(ViewGroup.LayoutParams layoutParams) {
            return layoutParams instanceof LayoutParams ? new LayoutParams((LayoutParams) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
        }

        public final void x0(RecyclerView recyclerView) {
            y0(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
        }

        public final void y0(int i, int i2) {
            this.n = View.MeasureSpec.getSize(i);
            int mode = View.MeasureSpec.getMode(i);
            this.l = mode;
            if (mode == 0 && !RecyclerView.J0) {
                this.n = 0;
            }
            this.o = View.MeasureSpec.getSize(i2);
            int mode2 = View.MeasureSpec.getMode(i2);
            this.m = mode2;
            if (mode2 != 0 || RecyclerView.J0) {
                return;
            }
            this.o = 0;
        }

        public final View z(int i) {
            androidx.recyclerview.widget.b bVar = this.a;
            if (bVar != null) {
                return bVar.d(i);
            }
            return null;
        }

        public void z0(Rect rect, int i, int i2) {
            int I = I() + H() + rect.width();
            int iG = G() + J() + rect.height();
            RecyclerView recyclerView = this.b;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            this.b.setMeasuredDimension(k(i, I, om3.d.d(recyclerView)), k(i2, iG, om3.d.c(this.b)));
        }

        public void V() {
        }

        public void d0() {
        }

        @SuppressLint({"UnknownNullness"})
        public void W(RecyclerView recyclerView) {
        }

        @SuppressLint({"UnknownNullness"})
        public void i0(w wVar) {
        }

        @SuppressLint({"UnknownNullness"})
        public void j0(Parcelable parcelable) {
        }

        public void l0(int i) {
        }

        public void c0(int i, int i2) {
        }

        public void e0(int i, int i2) {
        }

        public void f0(int i, int i2) {
        }

        public void g0(int i, int i2) {
        }

        @SuppressLint({"UnknownNullness"})
        public void m(int i, c cVar) {
        }

        @SuppressLint({"UnknownNullness"})
        public void l(int i, int i2, w wVar, c cVar) {
        }
    }

    public static abstract class e<VH extends a0> {
        public final f e = new f();
        public boolean f = false;
        public final int g = 1;

        public final void A(g gVar) {
            this.e.registerObserver(gVar);
        }

        public final void B(boolean z) {
            if (this.e.a()) {
                throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
            }
            this.f = z;
        }

        public final void C(g gVar) {
            this.e.unregisterObserver(gVar);
        }

        public abstract int k();

        public long l(int i) {
            return -1L;
        }

        public int m(int i) {
            return 0;
        }

        public final void o() {
            this.e.b();
        }

        public final void p(int i, int i2) {
            this.e.c(i, i2);
        }

        public final void q(int i, int i2) {
            this.e.e(i, i2);
        }

        public final void r(int i, int i2) {
            this.e.f(i, i2);
        }

        public abstract void t(VH vh, int i);

        public void u(VH vh, int i, List<Object> list) {
            t(vh, i);
        }

        public abstract a0 v(int i, RecyclerView recyclerView);

        public boolean x(VH vh) {
            return false;
        }

        public void s(RecyclerView recyclerView) {
        }

        public void w(RecyclerView recyclerView) {
        }

        public void y(VH vh) {
        }

        public void z(VH vh) {
        }
    }

    public static abstract class l {
        @Deprecated
        public void f(Rect rect, int i, RecyclerView recyclerView) {
            rect.set(0, 0, 0, 0);
        }

        public void g(Rect rect, View view, RecyclerView recyclerView, w wVar) {
            f(rect, ((LayoutParams) view.getLayoutParams()).a(), recyclerView);
        }

        public void h(Canvas canvas, RecyclerView recyclerView) {
        }

        public void i(Canvas canvas, RecyclerView recyclerView) {
        }
    }

    public static abstract class q {
        public void a(int i, RecyclerView recyclerView) {
        }

        public void b(RecyclerView recyclerView, int i, int i2) {
        }
    }
}
