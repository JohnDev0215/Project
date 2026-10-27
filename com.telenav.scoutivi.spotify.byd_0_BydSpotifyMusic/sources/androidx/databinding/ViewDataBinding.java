package androidx.databinding;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.Choreographer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.c;
import androidx.databinding.j;
import androidx.databinding.m;
import androidx.databinding.n;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.i;
import androidx.lifecycle.u;
import androidx.lifecycle.v;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class ViewDataBinding extends androidx.databinding.a {

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private static final int f2034x = 8;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Runnable f2037g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f2038h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f2039i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private r[] f2040j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final View f2041k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private androidx.databinding.c<p, ViewDataBinding, Void> f2042l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private boolean f2043m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private Choreographer f2044n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private final Choreographer.FrameCallback f2045o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private Handler f2046p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    protected final androidx.databinding.f f2047q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private ViewDataBinding f2048r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private androidx.lifecycle.o f2049s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private OnStartListener f2050t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private boolean f2051u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    protected boolean f2052v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    static int f2033w = Build.VERSION.SDK_INT;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private static final boolean f2035y = true;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private static final androidx.databinding.d f2036z = new a();
    private static final androidx.databinding.d A = new b();
    private static final androidx.databinding.d B = new c();
    private static final androidx.databinding.d C = new d();
    private static final c.a<p, ViewDataBinding, Void> D = new e();
    private static final ReferenceQueue<ViewDataBinding> E = new ReferenceQueue<>();
    private static final View.OnAttachStateChangeListener F = new f();

    static class OnStartListener implements androidx.lifecycle.n {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final WeakReference<ViewDataBinding> f2053e;

        /* synthetic */ OnStartListener(ViewDataBinding viewDataBinding, a aVar) {
            this(viewDataBinding);
        }

        @v(i.b.ON_START)
        public void onStart() {
            ViewDataBinding viewDataBinding = this.f2053e.get();
            if (viewDataBinding != null) {
                viewDataBinding.A();
            }
        }

        private OnStartListener(ViewDataBinding viewDataBinding) {
            this.f2053e = new WeakReference<>(viewDataBinding);
        }
    }

    class a implements androidx.databinding.d {
        a() {
        }

        @Override // androidx.databinding.d
        public r a(ViewDataBinding viewDataBinding, int i7, ReferenceQueue<ViewDataBinding> referenceQueue) {
            return new m(viewDataBinding, i7, referenceQueue).f();
        }
    }

    class b implements androidx.databinding.d {
        b() {
        }

        @Override // androidx.databinding.d
        public r a(ViewDataBinding viewDataBinding, int i7, ReferenceQueue<ViewDataBinding> referenceQueue) {
            return new k(viewDataBinding, i7, referenceQueue).j();
        }
    }

    class c implements androidx.databinding.d {
        c() {
        }

        @Override // androidx.databinding.d
        public r a(ViewDataBinding viewDataBinding, int i7, ReferenceQueue<ViewDataBinding> referenceQueue) {
            return new l(viewDataBinding, i7, referenceQueue).e();
        }
    }

    class d implements androidx.databinding.d {
        d() {
        }

        @Override // androidx.databinding.d
        public r a(ViewDataBinding viewDataBinding, int i7, ReferenceQueue<ViewDataBinding> referenceQueue) {
            return new j(viewDataBinding, i7, referenceQueue).g();
        }
    }

    class e extends c.a<p, ViewDataBinding, Void> {
        e() {
        }

        @Override // androidx.databinding.c.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(p pVar, ViewDataBinding viewDataBinding, int i7, Void r42) {
            if (i7 == 1) {
                if (pVar.c(viewDataBinding)) {
                    return;
                }
                viewDataBinding.f2039i = true;
            } else if (i7 == 2) {
                pVar.b(viewDataBinding);
            } else {
                if (i7 != 3) {
                    return;
                }
                pVar.a(viewDataBinding);
            }
        }
    }

    class f implements View.OnAttachStateChangeListener {
        f() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        @TargetApi(19)
        public void onViewAttachedToWindow(View view) {
            ViewDataBinding.D(view).f2037g.run();
            view.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    class g implements Runnable {
        g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (this) {
                ViewDataBinding.this.f2038h = false;
            }
            ViewDataBinding.Q();
            if (ViewDataBinding.this.f2041k.isAttachedToWindow()) {
                ViewDataBinding.this.A();
            } else {
                ViewDataBinding.this.f2041k.removeOnAttachStateChangeListener(ViewDataBinding.F);
                ViewDataBinding.this.f2041k.addOnAttachStateChangeListener(ViewDataBinding.F);
            }
        }
    }

    class h implements Choreographer.FrameCallback {
        h() {
        }

        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long j7) {
            ViewDataBinding.this.f2037g.run();
        }
    }

    protected static class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String[][] f2056a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int[][] f2057b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int[][] f2058c;

        public i(int i7) {
            this.f2056a = new String[i7][];
            this.f2057b = new int[i7][];
            this.f2058c = new int[i7][];
        }

        public void a(int i7, String[] strArr, int[] iArr, int[] iArr2) {
            this.f2056a[i7] = strArr;
            this.f2057b[i7] = iArr;
            this.f2058c[i7] = iArr2;
        }
    }

    private static class j implements u, o<LiveData<?>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final r<LiveData<?>> f2059a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        WeakReference<androidx.lifecycle.o> f2060b = null;

        public j(ViewDataBinding viewDataBinding, int i7, ReferenceQueue<ViewDataBinding> referenceQueue) {
            this.f2059a = new r<>(viewDataBinding, i7, this, referenceQueue);
        }

        private androidx.lifecycle.o f() {
            WeakReference<androidx.lifecycle.o> weakReference = this.f2060b;
            if (weakReference == null) {
                return null;
            }
            return weakReference.get();
        }

        @Override // androidx.databinding.o
        public void b(androidx.lifecycle.o oVar) {
            androidx.lifecycle.o oVarF = f();
            LiveData<?> liveDataB = this.f2059a.b();
            if (liveDataB != null) {
                if (oVarF != null) {
                    liveDataB.k(this);
                }
                if (oVar != null) {
                    liveDataB.f(oVar, this);
                }
            }
            if (oVar != null) {
                this.f2060b = new WeakReference<>(oVar);
            }
        }

        @Override // androidx.lifecycle.u
        public void c(Object obj) {
            ViewDataBinding viewDataBindingA = this.f2059a.a();
            if (viewDataBindingA != null) {
                r<LiveData<?>> rVar = this.f2059a;
                viewDataBindingA.H(rVar.f2083b, rVar.b(), 0);
            }
        }

        @Override // androidx.databinding.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public void a(LiveData<?> liveData) {
            androidx.lifecycle.o oVarF = f();
            if (oVarF != null) {
                liveData.f(oVarF, this);
            }
        }

        public r<LiveData<?>> g() {
            return this.f2059a;
        }

        @Override // androidx.databinding.o
        /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
        public void d(LiveData<?> liveData) {
            liveData.k(this);
        }
    }

    private static class k extends m.a implements o<androidx.databinding.m> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final r<androidx.databinding.m> f2061a;

        public k(ViewDataBinding viewDataBinding, int i7, ReferenceQueue<ViewDataBinding> referenceQueue) {
            this.f2061a = new r<>(viewDataBinding, i7, this, referenceQueue);
        }

        @Override // androidx.databinding.o
        public void b(androidx.lifecycle.o oVar) {
        }

        @Override // androidx.databinding.m.a
        public void c(androidx.databinding.m mVar) {
            androidx.databinding.m mVarB;
            ViewDataBinding viewDataBindingA = this.f2061a.a();
            if (viewDataBindingA != null && (mVarB = this.f2061a.b()) == mVar) {
                viewDataBindingA.H(this.f2061a.f2083b, mVarB, 0);
            }
        }

        @Override // androidx.databinding.m.a
        public void e(androidx.databinding.m mVar, int i7, int i8) {
            c(mVar);
        }

        @Override // androidx.databinding.m.a
        public void f(androidx.databinding.m mVar, int i7, int i8) {
            c(mVar);
        }

        @Override // androidx.databinding.m.a
        public void g(androidx.databinding.m mVar, int i7, int i8, int i9) {
            c(mVar);
        }

        @Override // androidx.databinding.m.a
        public void h(androidx.databinding.m mVar, int i7, int i8) {
            c(mVar);
        }

        @Override // androidx.databinding.o
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public void a(androidx.databinding.m mVar) {
            mVar.b(this);
        }

        public r<androidx.databinding.m> j() {
            return this.f2061a;
        }

        @Override // androidx.databinding.o
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public void d(androidx.databinding.m mVar) {
            mVar.a(this);
        }
    }

    private static class l extends n.a implements o<n> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final r<n> f2062a;

        public l(ViewDataBinding viewDataBinding, int i7, ReferenceQueue<ViewDataBinding> referenceQueue) {
            this.f2062a = new r<>(viewDataBinding, i7, this, referenceQueue);
        }

        @Override // androidx.databinding.o
        public void b(androidx.lifecycle.o oVar) {
        }

        @Override // androidx.databinding.o
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void a(n nVar) {
            nVar.a(this);
        }

        public r<n> e() {
            return this.f2062a;
        }

        @Override // androidx.databinding.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(n nVar) {
            nVar.b(this);
        }
    }

    private static class m extends j.a implements o<androidx.databinding.j> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final r<androidx.databinding.j> f2063a;

        public m(ViewDataBinding viewDataBinding, int i7, ReferenceQueue<ViewDataBinding> referenceQueue) {
            this.f2063a = new r<>(viewDataBinding, i7, this, referenceQueue);
        }

        @Override // androidx.databinding.o
        public void b(androidx.lifecycle.o oVar) {
        }

        @Override // androidx.databinding.j.a
        public void c(androidx.databinding.j jVar, int i7) {
            ViewDataBinding viewDataBindingA = this.f2063a.a();
            if (viewDataBindingA != null && this.f2063a.b() == jVar) {
                viewDataBindingA.H(this.f2063a.f2083b, jVar, i7);
            }
        }

        @Override // androidx.databinding.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public void a(androidx.databinding.j jVar) {
            jVar.b(this);
        }

        public r<androidx.databinding.j> f() {
            return this.f2063a;
        }

        @Override // androidx.databinding.o
        /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
        public void d(androidx.databinding.j jVar) {
            jVar.k(this);
        }
    }

    protected ViewDataBinding(androidx.databinding.f fVar, View view, int i7) {
        this.f2037g = new g();
        this.f2038h = false;
        this.f2039i = false;
        this.f2047q = fVar;
        this.f2040j = new r[i7];
        this.f2041k = view;
        if (Looper.myLooper() == null) {
            throw new IllegalStateException("DataBinding must be created in view's UI Thread");
        }
        if (f2035y) {
            this.f2044n = Choreographer.getInstance();
            this.f2045o = new h();
        } else {
            this.f2045o = null;
            this.f2046p = new Handler(Looper.myLooper());
        }
    }

    private static int B(String str, int i7, i iVar, int i8) {
        CharSequence charSequenceSubSequence = str.subSequence(str.indexOf(47) + 1, str.length() - 2);
        String[] strArr = iVar.f2056a[i8];
        int length = strArr.length;
        while (i7 < length) {
            if (TextUtils.equals(charSequenceSubSequence, strArr[i7])) {
                return i7;
            }
            i7++;
        }
        return -1;
    }

    private static int C(ViewGroup viewGroup, int i7) {
        String str = (String) viewGroup.getChildAt(i7).getTag();
        String strSubstring = str.substring(0, str.length() - 1);
        int length = strSubstring.length();
        int childCount = viewGroup.getChildCount();
        for (int i8 = i7 + 1; i8 < childCount; i8++) {
            View childAt = viewGroup.getChildAt(i8);
            String str2 = childAt.getTag() instanceof String ? (String) childAt.getTag() : null;
            if (str2 != null && str2.startsWith(strSubstring)) {
                if (str2.length() == str.length() && str2.charAt(str2.length() - 1) == '0') {
                    return i7;
                }
                if (L(str2, length)) {
                    i7 = i8;
                }
            }
        }
        return i7;
    }

    static ViewDataBinding D(View view) {
        if (view != null) {
            return (ViewDataBinding) view.getTag(o0.a.dataBinding);
        }
        return null;
    }

    public static int E() {
        return f2033w;
    }

    protected static int F(View view, int i7) {
        return view.getContext().getColor(i7);
    }

    protected static <T extends ViewDataBinding> T J(LayoutInflater layoutInflater, int i7, ViewGroup viewGroup, boolean z6, Object obj) {
        return (T) androidx.databinding.g.j(layoutInflater, i7, viewGroup, z6, w(obj));
    }

    private static boolean L(String str, int i7) {
        int length = str.length();
        if (length == i7) {
            return false;
        }
        while (i7 < length) {
            if (!Character.isDigit(str.charAt(i7))) {
                return false;
            }
            i7++;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00fc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void M(androidx.databinding.f r17, android.view.View r18, java.lang.Object[] r19, androidx.databinding.ViewDataBinding.i r20, android.util.SparseIntArray r21, boolean r22) {
        /*
            Method dump skipped, instruction units count: 282
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.databinding.ViewDataBinding.M(androidx.databinding.f, android.view.View, java.lang.Object[], androidx.databinding.ViewDataBinding$i, android.util.SparseIntArray, boolean):void");
    }

    protected static Object[] N(androidx.databinding.f fVar, View view, int i7, i iVar, SparseIntArray sparseIntArray) {
        Object[] objArr = new Object[i7];
        M(fVar, view, objArr, iVar, sparseIntArray, true);
        return objArr;
    }

    private static int P(String str, int i7) {
        int iCharAt = 0;
        while (i7 < str.length()) {
            iCharAt = (iCharAt * 10) + (str.charAt(i7) - '0');
            i7++;
        }
        return iCharAt;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void Q() {
        while (true) {
            Reference<? extends ViewDataBinding> referencePoll = E.poll();
            if (referencePoll == null) {
                return;
            }
            if (referencePoll instanceof r) {
                ((r) referencePoll).e();
            }
        }
    }

    protected static int T(Integer num) {
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    protected static boolean U(Boolean bool) {
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    private static androidx.databinding.f w(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof androidx.databinding.f) {
            return (androidx.databinding.f) obj;
        }
        throw new IllegalArgumentException("The provided bindingComponent parameter must be an instance of DataBindingComponent. See  https://issuetracker.google.com/issues/116541301 for details of why this parameter is not defined as DataBindingComponent");
    }

    private void y() {
        if (this.f2043m) {
            S();
            return;
        }
        if (I()) {
            this.f2043m = true;
            this.f2039i = false;
            androidx.databinding.c<p, ViewDataBinding, Void> cVar = this.f2042l;
            if (cVar != null) {
                cVar.d(this, 1, null);
                if (this.f2039i) {
                    this.f2042l.d(this, 2, null);
                }
            }
            if (!this.f2039i) {
                x();
                androidx.databinding.c<p, ViewDataBinding, Void> cVar2 = this.f2042l;
                if (cVar2 != null) {
                    cVar2.d(this, 3, null);
                }
            }
            this.f2043m = false;
        }
    }

    protected static void z(ViewDataBinding viewDataBinding) {
        viewDataBinding.y();
    }

    public void A() {
        ViewDataBinding viewDataBinding = this.f2048r;
        if (viewDataBinding == null) {
            y();
        } else {
            viewDataBinding.A();
        }
    }

    public View G() {
        return this.f2041k;
    }

    protected void H(int i7, Object obj, int i8) {
        if (this.f2051u || this.f2052v || !O(i7, obj, i8)) {
            return;
        }
        S();
    }

    public abstract boolean I();

    public abstract void K();

    protected abstract boolean O(int i7, Object obj, int i8);

    protected void R(int i7, Object obj, androidx.databinding.d dVar) {
        if (obj == null) {
            return;
        }
        r rVarA = this.f2040j[i7];
        if (rVarA == null) {
            rVarA = dVar.a(this, i7, E);
            this.f2040j[i7] = rVarA;
            androidx.lifecycle.o oVar = this.f2049s;
            if (oVar != null) {
                rVarA.c(oVar);
            }
        }
        rVarA.d(obj);
    }

    protected void S() {
        ViewDataBinding viewDataBinding = this.f2048r;
        if (viewDataBinding != null) {
            viewDataBinding.S();
            return;
        }
        androidx.lifecycle.o oVar = this.f2049s;
        if (oVar == null || oVar.a().b().isAtLeast(i.c.STARTED)) {
            synchronized (this) {
                if (this.f2038h) {
                    return;
                }
                this.f2038h = true;
                if (f2035y) {
                    this.f2044n.postFrameCallback(this.f2045o);
                } else {
                    this.f2046p.post(this.f2037g);
                }
            }
        }
    }

    protected void V(ViewDataBinding viewDataBinding) {
        if (viewDataBinding != null) {
            viewDataBinding.f2048r = this;
        }
    }

    public void W(androidx.lifecycle.o oVar) {
        if (oVar instanceof Fragment) {
            Log.w("DataBinding", "Setting the fragment as the LifecycleOwner might cause memory leaks because views lives shorter than the Fragment. Consider using Fragment's view lifecycle");
        }
        androidx.lifecycle.o oVar2 = this.f2049s;
        if (oVar2 == oVar) {
            return;
        }
        if (oVar2 != null) {
            oVar2.a().c(this.f2050t);
        }
        this.f2049s = oVar;
        if (oVar != null) {
            if (this.f2050t == null) {
                this.f2050t = new OnStartListener(this, null);
            }
            oVar.a().a(this.f2050t);
        }
        for (r rVar : this.f2040j) {
            if (rVar != null) {
                rVar.c(oVar);
            }
        }
    }

    protected void X(View view) {
        view.setTag(o0.a.dataBinding, this);
    }

    protected boolean Y(int i7) {
        r rVar = this.f2040j[i7];
        if (rVar != null) {
            return rVar.e();
        }
        return false;
    }

    protected boolean Z(int i7, LiveData<?> liveData) {
        this.f2051u = true;
        try {
            return c0(i7, liveData, C);
        } finally {
            this.f2051u = false;
        }
    }

    protected boolean a0(int i7, androidx.databinding.j jVar) {
        return c0(i7, jVar, f2036z);
    }

    protected boolean b0(int i7, androidx.databinding.m mVar) {
        return c0(i7, mVar, A);
    }

    protected boolean c0(int i7, Object obj, androidx.databinding.d dVar) {
        if (obj == null) {
            return Y(i7);
        }
        r rVar = this.f2040j[i7];
        if (rVar == null) {
            R(i7, obj, dVar);
            return true;
        }
        if (rVar.b() == obj) {
            return false;
        }
        Y(i7);
        R(i7, obj, dVar);
        return true;
    }

    protected abstract void x();

    protected ViewDataBinding(Object obj, View view, int i7) {
        this(w(obj), view, i7);
    }
}
