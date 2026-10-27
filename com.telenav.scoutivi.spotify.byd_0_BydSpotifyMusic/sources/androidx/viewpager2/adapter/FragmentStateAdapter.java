package androidx.viewpager2.adapter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.r;
import androidx.lifecycle.i;
import androidx.lifecycle.m;
import androidx.lifecycle.o;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import h0.h;
import i0.u;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class FragmentStateAdapter extends RecyclerView.g<androidx.viewpager2.adapter.a> implements androidx.viewpager2.adapter.b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final i f3268c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final FragmentManager f3269d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final n.d<Fragment> f3270e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final n.d<Fragment.SavedState> f3271f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final n.d<Integer> f3272g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private FragmentMaxLifecycleEnforcer f3273h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    boolean f3274i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private boolean f3275j;

    class FragmentMaxLifecycleEnforcer {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private ViewPager2.i f3281a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private RecyclerView.i f3282b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private m f3283c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private ViewPager2 f3284d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private long f3285e = -1;

        class a extends ViewPager2.i {
            a() {
            }

            @Override // androidx.viewpager2.widget.ViewPager2.i
            public void a(int i7) {
                FragmentMaxLifecycleEnforcer.this.d(false);
            }

            @Override // androidx.viewpager2.widget.ViewPager2.i
            public void c(int i7) {
                FragmentMaxLifecycleEnforcer.this.d(false);
            }
        }

        class b extends d {
            b() {
                super(null);
            }

            @Override // androidx.viewpager2.adapter.FragmentStateAdapter.d, androidx.recyclerview.widget.RecyclerView.i
            public void a() {
                FragmentMaxLifecycleEnforcer.this.d(true);
            }
        }

        FragmentMaxLifecycleEnforcer() {
        }

        private ViewPager2 a(RecyclerView recyclerView) {
            ViewParent parent = recyclerView.getParent();
            if (parent instanceof ViewPager2) {
                return (ViewPager2) parent;
            }
            throw new IllegalStateException("Expected ViewPager2 instance. Got: " + parent);
        }

        void b(RecyclerView recyclerView) {
            this.f3284d = a(recyclerView);
            a aVar = new a();
            this.f3281a = aVar;
            this.f3284d.g(aVar);
            b bVar = new b();
            this.f3282b = bVar;
            FragmentStateAdapter.this.y(bVar);
            m mVar = new m() { // from class: androidx.viewpager2.adapter.FragmentStateAdapter.FragmentMaxLifecycleEnforcer.3
                @Override // androidx.lifecycle.m
                public void d(o oVar, i.b bVar2) {
                    FragmentMaxLifecycleEnforcer.this.d(false);
                }
            };
            this.f3283c = mVar;
            FragmentStateAdapter.this.f3268c.a(mVar);
        }

        void c(RecyclerView recyclerView) {
            a(recyclerView).n(this.f3281a);
            FragmentStateAdapter.this.A(this.f3282b);
            FragmentStateAdapter.this.f3268c.c(this.f3283c);
            this.f3284d = null;
        }

        void d(boolean z6) {
            int currentItem;
            Fragment fragmentE;
            if (FragmentStateAdapter.this.U() || this.f3284d.getScrollState() != 0 || FragmentStateAdapter.this.f3270e.h() || FragmentStateAdapter.this.e() == 0 || (currentItem = this.f3284d.getCurrentItem()) >= FragmentStateAdapter.this.e()) {
                return;
            }
            long jF = FragmentStateAdapter.this.f(currentItem);
            if ((jF != this.f3285e || z6) && (fragmentE = FragmentStateAdapter.this.f3270e.e(jF)) != null && fragmentE.c0()) {
                this.f3285e = jF;
                r rVarL = FragmentStateAdapter.this.f3269d.l();
                Fragment fragment = null;
                for (int i7 = 0; i7 < FragmentStateAdapter.this.f3270e.n(); i7++) {
                    long jI = FragmentStateAdapter.this.f3270e.i(i7);
                    Fragment fragmentO = FragmentStateAdapter.this.f3270e.o(i7);
                    if (fragmentO.c0()) {
                        if (jI != this.f3285e) {
                            rVarL.t(fragmentO, i.c.STARTED);
                        } else {
                            fragment = fragmentO;
                        }
                        fragmentO.G1(jI == this.f3285e);
                    }
                }
                if (fragment != null) {
                    rVarL.t(fragment, i.c.RESUMED);
                }
                if (rVarL.o()) {
                    return;
                }
                rVarL.k();
            }
        }
    }

    class a implements View.OnLayoutChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ FrameLayout f3290a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ androidx.viewpager2.adapter.a f3291b;

        a(FrameLayout frameLayout, androidx.viewpager2.adapter.a aVar) {
            this.f3290a = frameLayout;
            this.f3291b = aVar;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14) {
            if (this.f3290a.getParent() != null) {
                this.f3290a.removeOnLayoutChangeListener(this);
                FragmentStateAdapter.this.Q(this.f3291b);
            }
        }
    }

    class b extends FragmentManager.l {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Fragment f3293a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ FrameLayout f3294b;

        b(Fragment fragment, FrameLayout frameLayout) {
            this.f3293a = fragment;
            this.f3294b = frameLayout;
        }

        @Override // androidx.fragment.app.FragmentManager.l
        public void m(FragmentManager fragmentManager, Fragment fragment, View view, Bundle bundle) {
            if (fragment == this.f3293a) {
                fragmentManager.r1(this);
                FragmentStateAdapter.this.B(view, this.f3294b);
            }
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            FragmentStateAdapter fragmentStateAdapter = FragmentStateAdapter.this;
            fragmentStateAdapter.f3274i = false;
            fragmentStateAdapter.G();
        }
    }

    private static abstract class d extends RecyclerView.i {
        private d() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public abstract void a();

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public final void b(int i7, int i8) {
            a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public final void c(int i7, int i8, Object obj) {
            a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public final void d(int i7, int i8) {
            a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public final void e(int i7, int i8, int i9) {
            a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public final void f(int i7, int i8) {
            a();
        }

        /* synthetic */ d(a aVar) {
            this();
        }
    }

    public FragmentStateAdapter(Fragment fragment) {
        this(fragment.r(), fragment.a());
    }

    private static String E(String str, long j7) {
        return str + j7;
    }

    private void F(int i7) {
        long jF = f(i7);
        if (this.f3270e.c(jF)) {
            return;
        }
        Fragment fragmentD = D(i7);
        fragmentD.F1(this.f3271f.e(jF));
        this.f3270e.j(jF, fragmentD);
    }

    private boolean H(long j7) {
        View viewW;
        if (this.f3272g.c(j7)) {
            return true;
        }
        Fragment fragmentE = this.f3270e.e(j7);
        return (fragmentE == null || (viewW = fragmentE.W()) == null || viewW.getParent() == null) ? false : true;
    }

    private static boolean I(String str, String str2) {
        return str.startsWith(str2) && str.length() > str2.length();
    }

    private Long J(int i7) {
        Long lValueOf = null;
        for (int i8 = 0; i8 < this.f3272g.n(); i8++) {
            if (this.f3272g.o(i8).intValue() == i7) {
                if (lValueOf != null) {
                    throw new IllegalStateException("Design assumption violated: a ViewHolder can only be bound to one item at a time.");
                }
                lValueOf = Long.valueOf(this.f3272g.i(i8));
            }
        }
        return lValueOf;
    }

    private static long P(String str, String str2) {
        return Long.parseLong(str.substring(str2.length()));
    }

    private void R(long j7) {
        ViewParent parent;
        Fragment fragmentE = this.f3270e.e(j7);
        if (fragmentE == null) {
            return;
        }
        if (fragmentE.W() != null && (parent = fragmentE.W().getParent()) != null) {
            ((FrameLayout) parent).removeAllViews();
        }
        if (!C(j7)) {
            this.f3271f.k(j7);
        }
        if (!fragmentE.c0()) {
            this.f3270e.k(j7);
            return;
        }
        if (U()) {
            this.f3275j = true;
            return;
        }
        if (fragmentE.c0() && C(j7)) {
            this.f3271f.j(j7, this.f3269d.i1(fragmentE));
        }
        this.f3269d.l().p(fragmentE).k();
        this.f3270e.k(j7);
    }

    private void S() {
        final Handler handler = new Handler(Looper.getMainLooper());
        final c cVar = new c();
        this.f3268c.a(new m() { // from class: androidx.viewpager2.adapter.FragmentStateAdapter.5
            @Override // androidx.lifecycle.m
            public void d(o oVar, i.b bVar) {
                if (bVar == i.b.ON_DESTROY) {
                    handler.removeCallbacks(cVar);
                    oVar.a().c(this);
                }
            }
        });
        handler.postDelayed(cVar, 10000L);
    }

    private void T(Fragment fragment, FrameLayout frameLayout) {
        this.f3269d.a1(new b(fragment, frameLayout), false);
    }

    void B(View view, FrameLayout frameLayout) {
        if (frameLayout.getChildCount() > 1) {
            throw new IllegalStateException("Design assumption violated.");
        }
        if (view.getParent() == frameLayout) {
            return;
        }
        if (frameLayout.getChildCount() > 0) {
            frameLayout.removeAllViews();
        }
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        frameLayout.addView(view);
    }

    public boolean C(long j7) {
        return j7 >= 0 && j7 < ((long) e());
    }

    public abstract Fragment D(int i7);

    void G() {
        if (!this.f3275j || U()) {
            return;
        }
        n.b bVar = new n.b();
        for (int i7 = 0; i7 < this.f3270e.n(); i7++) {
            long jI = this.f3270e.i(i7);
            if (!C(jI)) {
                bVar.add(Long.valueOf(jI));
                this.f3272g.k(jI);
            }
        }
        if (!this.f3274i) {
            this.f3275j = false;
            for (int i8 = 0; i8 < this.f3270e.n(); i8++) {
                long jI2 = this.f3270e.i(i8);
                if (!H(jI2)) {
                    bVar.add(Long.valueOf(jI2));
                }
            }
        }
        Iterator<E> it = bVar.iterator();
        while (it.hasNext()) {
            R(((Long) it.next()).longValue());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: K, reason: merged with bridge method [inline-methods] */
    public final void q(androidx.viewpager2.adapter.a aVar, int i7) {
        long jK = aVar.k();
        int id = aVar.N().getId();
        Long lJ = J(id);
        if (lJ != null && lJ.longValue() != jK) {
            R(lJ.longValue());
            this.f3272g.k(lJ.longValue());
        }
        this.f3272g.j(jK, Integer.valueOf(id));
        F(i7);
        FrameLayout frameLayoutN = aVar.N();
        if (u.O(frameLayoutN)) {
            if (frameLayoutN.getParent() != null) {
                throw new IllegalStateException("Design assumption violated.");
            }
            frameLayoutN.addOnLayoutChangeListener(new a(frameLayoutN, aVar));
        }
        G();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: L, reason: merged with bridge method [inline-methods] */
    public final androidx.viewpager2.adapter.a s(ViewGroup viewGroup, int i7) {
        return androidx.viewpager2.adapter.a.M(viewGroup);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: M, reason: merged with bridge method [inline-methods] */
    public final boolean u(androidx.viewpager2.adapter.a aVar) {
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: N, reason: merged with bridge method [inline-methods] */
    public final void v(androidx.viewpager2.adapter.a aVar) {
        Q(aVar);
        G();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: O, reason: merged with bridge method [inline-methods] */
    public final void x(androidx.viewpager2.adapter.a aVar) {
        Long lJ = J(aVar.N().getId());
        if (lJ != null) {
            R(lJ.longValue());
            this.f3272g.k(lJ.longValue());
        }
    }

    void Q(final androidx.viewpager2.adapter.a aVar) {
        Fragment fragmentE = this.f3270e.e(aVar.k());
        if (fragmentE == null) {
            throw new IllegalStateException("Design assumption violated.");
        }
        FrameLayout frameLayoutN = aVar.N();
        View viewW = fragmentE.W();
        if (!fragmentE.c0() && viewW != null) {
            throw new IllegalStateException("Design assumption violated.");
        }
        if (fragmentE.c0() && viewW == null) {
            T(fragmentE, frameLayoutN);
            return;
        }
        if (fragmentE.c0() && viewW.getParent() != null) {
            if (viewW.getParent() != frameLayoutN) {
                B(viewW, frameLayoutN);
                return;
            }
            return;
        }
        if (fragmentE.c0()) {
            B(viewW, frameLayoutN);
            return;
        }
        if (U()) {
            if (this.f3269d.E0()) {
                return;
            }
            this.f3268c.a(new m() { // from class: androidx.viewpager2.adapter.FragmentStateAdapter.2
                @Override // androidx.lifecycle.m
                public void d(o oVar, i.b bVar) {
                    if (FragmentStateAdapter.this.U()) {
                        return;
                    }
                    oVar.a().c(this);
                    if (u.O(aVar.N())) {
                        FragmentStateAdapter.this.Q(aVar);
                    }
                }
            });
            return;
        }
        T(fragmentE, frameLayoutN);
        this.f3269d.l().e(fragmentE, "f" + aVar.k()).t(fragmentE, i.c.STARTED).k();
        this.f3273h.d(false);
    }

    boolean U() {
        return this.f3269d.K0();
    }

    @Override // androidx.viewpager2.adapter.b
    public final Parcelable a() {
        Bundle bundle = new Bundle(this.f3270e.n() + this.f3271f.n());
        for (int i7 = 0; i7 < this.f3270e.n(); i7++) {
            long jI = this.f3270e.i(i7);
            Fragment fragmentE = this.f3270e.e(jI);
            if (fragmentE != null && fragmentE.c0()) {
                this.f3269d.Z0(bundle, E("f#", jI), fragmentE);
            }
        }
        for (int i8 = 0; i8 < this.f3271f.n(); i8++) {
            long jI2 = this.f3271f.i(i8);
            if (C(jI2)) {
                bundle.putParcelable(E("s#", jI2), this.f3271f.e(jI2));
            }
        }
        return bundle;
    }

    @Override // androidx.viewpager2.adapter.b
    public final void b(Parcelable parcelable) {
        if (!this.f3271f.h() || !this.f3270e.h()) {
            throw new IllegalStateException("Expected the adapter to be 'fresh' while restoring state.");
        }
        Bundle bundle = (Bundle) parcelable;
        if (bundle.getClassLoader() == null) {
            bundle.setClassLoader(getClass().getClassLoader());
        }
        for (String str : bundle.keySet()) {
            if (I(str, "f#")) {
                this.f3270e.j(P(str, "f#"), this.f3269d.o0(bundle, str));
            } else {
                if (!I(str, "s#")) {
                    throw new IllegalArgumentException("Unexpected key in savedState: " + str);
                }
                long jP = P(str, "s#");
                Fragment.SavedState savedState = (Fragment.SavedState) bundle.getParcelable(str);
                if (C(jP)) {
                    this.f3271f.j(jP, savedState);
                }
            }
        }
        if (this.f3270e.h()) {
            return;
        }
        this.f3275j = true;
        this.f3274i = true;
        G();
        S();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public long f(int i7) {
        return i7;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void p(RecyclerView recyclerView) {
        h.a(this.f3273h == null);
        FragmentMaxLifecycleEnforcer fragmentMaxLifecycleEnforcer = new FragmentMaxLifecycleEnforcer();
        this.f3273h = fragmentMaxLifecycleEnforcer;
        fragmentMaxLifecycleEnforcer.b(recyclerView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void t(RecyclerView recyclerView) {
        this.f3273h.c(recyclerView);
        this.f3273h = null;
    }

    public FragmentStateAdapter(FragmentManager fragmentManager, i iVar) {
        this.f3270e = new n.d<>();
        this.f3271f = new n.d<>();
        this.f3272g = new n.d<>();
        this.f3274i = false;
        this.f3275j = false;
        this.f3269d = fragmentManager;
        this.f3268c = iVar;
        super.z(true);
    }
}
