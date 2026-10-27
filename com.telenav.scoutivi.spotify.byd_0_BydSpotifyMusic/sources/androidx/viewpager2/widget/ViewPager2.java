package androidx.viewpager2.widget;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import i0.u;
import j0.c;
import j0.f;

/* JADX INFO: loaded from: classes.dex */
public final class ViewPager2 extends ViewGroup {

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    static boolean f3297z = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Rect f3298e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Rect f3299g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private androidx.viewpager2.widget.b f3300h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    int f3301i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    boolean f3302j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private RecyclerView.i f3303k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private LinearLayoutManager f3304l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private int f3305m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private Parcelable f3306n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    RecyclerView f3307o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private androidx.recyclerview.widget.n f3308p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    androidx.viewpager2.widget.e f3309q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private androidx.viewpager2.widget.b f3310r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private androidx.viewpager2.widget.c f3311s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private androidx.viewpager2.widget.d f3312t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private RecyclerView.l f3313u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private boolean f3314v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private boolean f3315w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private int f3316x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    e f3317y;

    class a extends g {
        a() {
            super(null);
        }

        @Override // androidx.viewpager2.widget.ViewPager2.g, androidx.recyclerview.widget.RecyclerView.i
        public void a() {
            ViewPager2 viewPager2 = ViewPager2.this;
            viewPager2.f3302j = true;
            viewPager2.f3309q.l();
        }
    }

    class b extends i {
        b() {
        }

        @Override // androidx.viewpager2.widget.ViewPager2.i
        public void a(int i7) {
            if (i7 == 0) {
                ViewPager2.this.o();
            }
        }

        @Override // androidx.viewpager2.widget.ViewPager2.i
        public void c(int i7) {
            ViewPager2 viewPager2 = ViewPager2.this;
            if (viewPager2.f3301i != i7) {
                viewPager2.f3301i = i7;
                viewPager2.f3317y.q();
            }
        }
    }

    class c extends i {
        c() {
        }

        @Override // androidx.viewpager2.widget.ViewPager2.i
        public void c(int i7) {
            ViewPager2.this.clearFocus();
            if (ViewPager2.this.hasFocus()) {
                ViewPager2.this.f3307o.requestFocus(2);
            }
        }
    }

    class d implements RecyclerView.q {
        d() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.q
        public void a(View view) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            if (((ViewGroup.MarginLayoutParams) pVar).width != -1 || ((ViewGroup.MarginLayoutParams) pVar).height != -1) {
                throw new IllegalStateException("Pages must fill the whole ViewPager2 (use match_parent)");
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.q
        public void b(View view) {
        }
    }

    private abstract class e {
        private e() {
        }

        boolean a() {
            return false;
        }

        boolean b(int i7) {
            return false;
        }

        boolean c(int i7, Bundle bundle) {
            return false;
        }

        boolean d() {
            return false;
        }

        void e(RecyclerView.g<?> gVar) {
        }

        void f(RecyclerView.g<?> gVar) {
        }

        String g() {
            throw new IllegalStateException("Not implemented.");
        }

        void h(androidx.viewpager2.widget.b bVar, RecyclerView recyclerView) {
        }

        void i(AccessibilityNodeInfo accessibilityNodeInfo) {
        }

        void j(j0.c cVar) {
        }

        boolean k(int i7) {
            throw new IllegalStateException("Not implemented.");
        }

        boolean l(int i7, Bundle bundle) {
            throw new IllegalStateException("Not implemented.");
        }

        void m() {
        }

        CharSequence n() {
            throw new IllegalStateException("Not implemented.");
        }

        void o(AccessibilityEvent accessibilityEvent) {
        }

        void p() {
        }

        void q() {
        }

        void r() {
        }

        void s() {
        }

        /* synthetic */ e(ViewPager2 viewPager2, a aVar) {
            this();
        }
    }

    class f extends e {
        f() {
            super(ViewPager2.this, null);
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public boolean b(int i7) {
            return (i7 == 8192 || i7 == 4096) && !ViewPager2.this.e();
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public boolean d() {
            return true;
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public void j(j0.c cVar) {
            if (ViewPager2.this.e()) {
                return;
            }
            cVar.J(c.a.f7126r);
            cVar.J(c.a.f7125q);
            cVar.h0(false);
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public boolean k(int i7) {
            if (b(i7)) {
                return false;
            }
            throw new IllegalStateException();
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public CharSequence n() {
            if (d()) {
                return "androidx.viewpager.widget.ViewPager";
            }
            throw new IllegalStateException();
        }
    }

    private static abstract class g extends RecyclerView.i {
        private g() {
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

        /* synthetic */ g(a aVar) {
            this();
        }
    }

    private class h extends LinearLayoutManager {
        h(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.o
        public void M0(RecyclerView.v vVar, RecyclerView.z zVar, j0.c cVar) {
            super.M0(vVar, zVar, cVar);
            ViewPager2.this.f3317y.j(cVar);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        protected void M1(RecyclerView.z zVar, int[] iArr) {
            int offscreenPageLimit = ViewPager2.this.getOffscreenPageLimit();
            if (offscreenPageLimit == -1) {
                super.M1(zVar, iArr);
                return;
            }
            int pageSize = ViewPager2.this.getPageSize() * offscreenPageLimit;
            iArr[0] = pageSize;
            iArr[1] = pageSize;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.o
        public boolean h1(RecyclerView.v vVar, RecyclerView.z zVar, int i7, Bundle bundle) {
            return ViewPager2.this.f3317y.b(i7) ? ViewPager2.this.f3317y.k(i7) : super.h1(vVar, zVar, i7, bundle);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.o
        public boolean s1(RecyclerView recyclerView, View view, Rect rect, boolean z6, boolean z7) {
            return false;
        }
    }

    public static abstract class i {
        public void a(int i7) {
        }

        public void b(int i7, float f7, int i8) {
        }

        public void c(int i7) {
        }
    }

    class j extends e {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final j0.f f3327b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final j0.f f3328c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private RecyclerView.i f3329d;

        class a implements j0.f {
            a() {
            }

            @Override // j0.f
            public boolean a(View view, f.a aVar) {
                j.this.v(((ViewPager2) view).getCurrentItem() + 1);
                return true;
            }
        }

        class b implements j0.f {
            b() {
            }

            @Override // j0.f
            public boolean a(View view, f.a aVar) {
                j.this.v(((ViewPager2) view).getCurrentItem() - 1);
                return true;
            }
        }

        class c extends g {
            c() {
                super(null);
            }

            @Override // androidx.viewpager2.widget.ViewPager2.g, androidx.recyclerview.widget.RecyclerView.i
            public void a() {
                j.this.w();
            }
        }

        j() {
            super(ViewPager2.this, null);
            this.f3327b = new a();
            this.f3328c = new b();
        }

        private void t(AccessibilityNodeInfo accessibilityNodeInfo) {
            int iE;
            int iE2;
            if (ViewPager2.this.getAdapter() == null) {
                iE = 0;
                iE2 = 0;
            } else if (ViewPager2.this.getOrientation() == 1) {
                iE = ViewPager2.this.getAdapter().e();
                iE2 = 0;
            } else {
                iE2 = ViewPager2.this.getAdapter().e();
                iE = 0;
            }
            j0.c.p0(accessibilityNodeInfo).R(c.b.a(iE, iE2, false, 0));
        }

        private void u(AccessibilityNodeInfo accessibilityNodeInfo) {
            int iE;
            RecyclerView.g adapter = ViewPager2.this.getAdapter();
            if (adapter == null || (iE = adapter.e()) == 0 || !ViewPager2.this.e()) {
                return;
            }
            if (ViewPager2.this.f3301i > 0) {
                accessibilityNodeInfo.addAction(8192);
            }
            if (ViewPager2.this.f3301i < iE - 1) {
                accessibilityNodeInfo.addAction(4096);
            }
            accessibilityNodeInfo.setScrollable(true);
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public boolean a() {
            return true;
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public boolean c(int i7, Bundle bundle) {
            return i7 == 8192 || i7 == 4096;
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public void e(RecyclerView.g<?> gVar) {
            w();
            if (gVar != null) {
                gVar.y(this.f3329d);
            }
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public void f(RecyclerView.g<?> gVar) {
            if (gVar != null) {
                gVar.A(this.f3329d);
            }
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public String g() {
            if (a()) {
                return "androidx.viewpager.widget.ViewPager";
            }
            throw new IllegalStateException();
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public void h(androidx.viewpager2.widget.b bVar, RecyclerView recyclerView) {
            u.t0(recyclerView, 2);
            this.f3329d = new c();
            if (u.w(ViewPager2.this) == 0) {
                u.t0(ViewPager2.this, 1);
            }
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public void i(AccessibilityNodeInfo accessibilityNodeInfo) {
            t(accessibilityNodeInfo);
            u(accessibilityNodeInfo);
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public boolean l(int i7, Bundle bundle) {
            if (!c(i7, bundle)) {
                throw new IllegalStateException();
            }
            v(i7 == 8192 ? ViewPager2.this.getCurrentItem() - 1 : ViewPager2.this.getCurrentItem() + 1);
            return true;
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public void m() {
            w();
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public void o(AccessibilityEvent accessibilityEvent) {
            accessibilityEvent.setSource(ViewPager2.this);
            accessibilityEvent.setClassName(g());
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public void p() {
            w();
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public void q() {
            w();
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public void r() {
            w();
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public void s() {
            w();
        }

        void v(int i7) {
            if (ViewPager2.this.e()) {
                ViewPager2.this.k(i7, true);
            }
        }

        void w() {
            int iE;
            ViewPager2 viewPager2 = ViewPager2.this;
            int i7 = R.id.accessibilityActionPageLeft;
            u.e0(viewPager2, R.id.accessibilityActionPageLeft);
            u.e0(viewPager2, R.id.accessibilityActionPageRight);
            u.e0(viewPager2, R.id.accessibilityActionPageUp);
            u.e0(viewPager2, R.id.accessibilityActionPageDown);
            if (ViewPager2.this.getAdapter() == null || (iE = ViewPager2.this.getAdapter().e()) == 0 || !ViewPager2.this.e()) {
                return;
            }
            if (ViewPager2.this.getOrientation() != 0) {
                if (ViewPager2.this.f3301i < iE - 1) {
                    u.g0(viewPager2, new c.a(R.id.accessibilityActionPageDown, null), null, this.f3327b);
                }
                if (ViewPager2.this.f3301i > 0) {
                    u.g0(viewPager2, new c.a(R.id.accessibilityActionPageUp, null), null, this.f3328c);
                    return;
                }
                return;
            }
            boolean zD = ViewPager2.this.d();
            int i8 = zD ? 16908360 : 16908361;
            if (zD) {
                i7 = 16908361;
            }
            if (ViewPager2.this.f3301i < iE - 1) {
                u.g0(viewPager2, new c.a(i8, null), null, this.f3327b);
            }
            if (ViewPager2.this.f3301i > 0) {
                u.g0(viewPager2, new c.a(i7, null), null, this.f3328c);
            }
        }
    }

    public interface k {
        void a(View view, float f7);
    }

    private class l extends androidx.recyclerview.widget.n {
        l() {
        }

        @Override // androidx.recyclerview.widget.n, androidx.recyclerview.widget.r
        public View g(RecyclerView.o oVar) {
            if (ViewPager2.this.c()) {
                return null;
            }
            return super.g(oVar);
        }
    }

    private class m extends RecyclerView {
        m(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public CharSequence getAccessibilityClassName() {
            return ViewPager2.this.f3317y.d() ? ViewPager2.this.f3317y.n() : super.getAccessibilityClassName();
        }

        @Override // android.view.View
        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setFromIndex(ViewPager2.this.f3301i);
            accessibilityEvent.setToIndex(ViewPager2.this.f3301i);
            ViewPager2.this.f3317y.o(accessibilityEvent);
        }

        @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            return ViewPager2.this.e() && super.onInterceptTouchEvent(motionEvent);
        }

        @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouchEvent(MotionEvent motionEvent) {
            return ViewPager2.this.e() && super.onTouchEvent(motionEvent);
        }
    }

    private static class n implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final int f3335e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final RecyclerView f3336g;

        n(int i7, RecyclerView recyclerView) {
            this.f3335e = i7;
            this.f3336g = recyclerView;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f3336g.r1(this.f3335e);
        }
    }

    public ViewPager2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3298e = new Rect();
        this.f3299g = new Rect();
        this.f3300h = new androidx.viewpager2.widget.b(3);
        this.f3302j = false;
        this.f3303k = new a();
        this.f3305m = -1;
        this.f3313u = null;
        this.f3314v = false;
        this.f3315w = true;
        this.f3316x = -1;
        b(context, attributeSet);
    }

    private RecyclerView.q a() {
        return new d();
    }

    private void b(Context context, AttributeSet attributeSet) {
        this.f3317y = f3297z ? new j() : new f();
        m mVar = new m(context);
        this.f3307o = mVar;
        mVar.setId(u.h());
        this.f3307o.setDescendantFocusability(131072);
        h hVar = new h(context);
        this.f3304l = hVar;
        this.f3307o.setLayoutManager(hVar);
        this.f3307o.setScrollingTouchSlop(1);
        l(context, attributeSet);
        this.f3307o.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.f3307o.j(a());
        androidx.viewpager2.widget.e eVar = new androidx.viewpager2.widget.e(this);
        this.f3309q = eVar;
        this.f3311s = new androidx.viewpager2.widget.c(this, eVar, this.f3307o);
        l lVar = new l();
        this.f3308p = lVar;
        lVar.b(this.f3307o);
        this.f3307o.l(this.f3309q);
        androidx.viewpager2.widget.b bVar = new androidx.viewpager2.widget.b(3);
        this.f3310r = bVar;
        this.f3309q.o(bVar);
        b bVar2 = new b();
        c cVar = new c();
        this.f3310r.d(bVar2);
        this.f3310r.d(cVar);
        this.f3317y.h(this.f3310r, this.f3307o);
        this.f3310r.d(this.f3300h);
        androidx.viewpager2.widget.d dVar = new androidx.viewpager2.widget.d(this.f3304l);
        this.f3312t = dVar;
        this.f3310r.d(dVar);
        RecyclerView recyclerView = this.f3307o;
        attachViewToParent(recyclerView, 0, recyclerView.getLayoutParams());
    }

    private void f(RecyclerView.g<?> gVar) {
        if (gVar != null) {
            gVar.y(this.f3303k);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void i() {
        RecyclerView.g adapter;
        if (this.f3305m == -1 || (adapter = getAdapter()) == 0) {
            return;
        }
        Parcelable parcelable = this.f3306n;
        if (parcelable != null) {
            if (adapter instanceof androidx.viewpager2.adapter.b) {
                ((androidx.viewpager2.adapter.b) adapter).b(parcelable);
            }
            this.f3306n = null;
        }
        int iMax = Math.max(0, Math.min(this.f3305m, adapter.e() - 1));
        this.f3301i = iMax;
        this.f3305m = -1;
        this.f3307o.j1(iMax);
        this.f3317y.m();
    }

    private void l(Context context, AttributeSet attributeSet) {
        int[] iArr = h1.a.ViewPager2;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr);
        if (Build.VERSION.SDK_INT >= 29) {
            saveAttributeDataForStyleable(context, iArr, attributeSet, typedArrayObtainStyledAttributes, 0, 0);
        }
        try {
            setOrientation(typedArrayObtainStyledAttributes.getInt(h1.a.ViewPager2_android_orientation, 0));
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    private void m(RecyclerView.g<?> gVar) {
        if (gVar != null) {
            gVar.A(this.f3303k);
        }
    }

    public boolean c() {
        return this.f3311s.a();
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i7) {
        return this.f3307o.canScrollHorizontally(i7);
    }

    @Override // android.view.View
    public boolean canScrollVertically(int i7) {
        return this.f3307o.canScrollVertically(i7);
    }

    boolean d() {
        return this.f3304l.Z() == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        Parcelable parcelable = sparseArray.get(getId());
        if (parcelable instanceof SavedState) {
            int i7 = ((SavedState) parcelable).f3318e;
            sparseArray.put(this.f3307o.getId(), sparseArray.get(i7));
            sparseArray.remove(i7);
        }
        super.dispatchRestoreInstanceState(sparseArray);
        i();
    }

    public boolean e() {
        return this.f3315w;
    }

    public void g(i iVar) {
        this.f3300h.d(iVar);
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return this.f3317y.a() ? this.f3317y.g() : super.getAccessibilityClassName();
    }

    public RecyclerView.g getAdapter() {
        return this.f3307o.getAdapter();
    }

    public int getCurrentItem() {
        return this.f3301i;
    }

    public int getItemDecorationCount() {
        return this.f3307o.getItemDecorationCount();
    }

    public int getOffscreenPageLimit() {
        return this.f3316x;
    }

    public int getOrientation() {
        return this.f3304l.p2();
    }

    int getPageSize() {
        int height;
        int paddingBottom;
        RecyclerView recyclerView = this.f3307o;
        if (getOrientation() == 0) {
            height = recyclerView.getWidth() - recyclerView.getPaddingLeft();
            paddingBottom = recyclerView.getPaddingRight();
        } else {
            height = recyclerView.getHeight() - recyclerView.getPaddingTop();
            paddingBottom = recyclerView.getPaddingBottom();
        }
        return height - paddingBottom;
    }

    public int getScrollState() {
        return this.f3309q.h();
    }

    public void h() {
        if (this.f3312t.d() == null) {
            return;
        }
        double dG = this.f3309q.g();
        int i7 = (int) dG;
        float f7 = (float) (dG - ((double) i7));
        this.f3312t.b(i7, f7, Math.round(getPageSize() * f7));
    }

    public void j(int i7, boolean z6) {
        if (c()) {
            throw new IllegalStateException("Cannot change current item when ViewPager2 is fake dragging");
        }
        k(i7, z6);
    }

    void k(int i7, boolean z6) {
        RecyclerView.g adapter = getAdapter();
        if (adapter == null) {
            if (this.f3305m != -1) {
                this.f3305m = Math.max(i7, 0);
                return;
            }
            return;
        }
        if (adapter.e() <= 0) {
            return;
        }
        int iMin = Math.min(Math.max(i7, 0), adapter.e() - 1);
        if (iMin == this.f3301i && this.f3309q.j()) {
            return;
        }
        int i8 = this.f3301i;
        if (iMin == i8 && z6) {
            return;
        }
        double dG = i8;
        this.f3301i = iMin;
        this.f3317y.q();
        if (!this.f3309q.j()) {
            dG = this.f3309q.g();
        }
        this.f3309q.m(iMin, z6);
        if (!z6) {
            this.f3307o.j1(iMin);
            return;
        }
        double d7 = iMin;
        if (Math.abs(d7 - dG) <= 3.0d) {
            this.f3307o.r1(iMin);
            return;
        }
        this.f3307o.j1(d7 > dG ? iMin - 3 : iMin + 3);
        RecyclerView recyclerView = this.f3307o;
        recyclerView.post(new n(iMin, recyclerView));
    }

    public void n(i iVar) {
        this.f3300h.e(iVar);
    }

    void o() {
        androidx.recyclerview.widget.n nVar = this.f3308p;
        if (nVar == null) {
            throw new IllegalStateException("Design assumption violated.");
        }
        View viewG = nVar.g(this.f3304l);
        if (viewG == null) {
            return;
        }
        int iH0 = this.f3304l.h0(viewG);
        if (iH0 != this.f3301i && getScrollState() == 0) {
            this.f3310r.c(iH0);
        }
        this.f3302j = false;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        this.f3317y.i(accessibilityNodeInfo);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        int measuredWidth = this.f3307o.getMeasuredWidth();
        int measuredHeight = this.f3307o.getMeasuredHeight();
        this.f3298e.left = getPaddingLeft();
        this.f3298e.right = (i9 - i7) - getPaddingRight();
        this.f3298e.top = getPaddingTop();
        this.f3298e.bottom = (i10 - i8) - getPaddingBottom();
        Gravity.apply(8388659, measuredWidth, measuredHeight, this.f3298e, this.f3299g);
        RecyclerView recyclerView = this.f3307o;
        Rect rect = this.f3299g;
        recyclerView.layout(rect.left, rect.top, rect.right, rect.bottom);
        if (this.f3302j) {
            o();
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i7, int i8) {
        measureChild(this.f3307o, i7, i8);
        int measuredWidth = this.f3307o.getMeasuredWidth();
        int measuredHeight = this.f3307o.getMeasuredHeight();
        int measuredState = this.f3307o.getMeasuredState();
        int paddingLeft = measuredWidth + getPaddingLeft() + getPaddingRight();
        int paddingTop = measuredHeight + getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(ViewGroup.resolveSizeAndState(Math.max(paddingLeft, getSuggestedMinimumWidth()), i7, measuredState), ViewGroup.resolveSizeAndState(Math.max(paddingTop, getSuggestedMinimumHeight()), i8, measuredState << 16));
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.f3305m = savedState.f3319g;
        this.f3306n = savedState.f3320h;
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f3318e = this.f3307o.getId();
        int i7 = this.f3305m;
        if (i7 == -1) {
            i7 = this.f3301i;
        }
        savedState.f3319g = i7;
        Parcelable parcelable = this.f3306n;
        if (parcelable != null) {
            savedState.f3320h = parcelable;
        } else {
            Object adapter = this.f3307o.getAdapter();
            if (adapter instanceof androidx.viewpager2.adapter.b) {
                savedState.f3320h = ((androidx.viewpager2.adapter.b) adapter).a();
            }
        }
        return savedState;
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        throw new IllegalStateException(ViewPager2.class.getSimpleName() + " does not support direct child views");
    }

    @Override // android.view.View
    public boolean performAccessibilityAction(int i7, Bundle bundle) {
        return this.f3317y.c(i7, bundle) ? this.f3317y.l(i7, bundle) : super.performAccessibilityAction(i7, bundle);
    }

    public void setAdapter(RecyclerView.g gVar) {
        RecyclerView.g adapter = this.f3307o.getAdapter();
        this.f3317y.f(adapter);
        m(adapter);
        this.f3307o.setAdapter(gVar);
        this.f3301i = 0;
        i();
        this.f3317y.e(gVar);
        f(gVar);
    }

    public void setCurrentItem(int i7) {
        j(i7, true);
    }

    @Override // android.view.View
    public void setLayoutDirection(int i7) {
        super.setLayoutDirection(i7);
        this.f3317y.p();
    }

    public void setOffscreenPageLimit(int i7) {
        if (i7 < 1 && i7 != -1) {
            throw new IllegalArgumentException("Offscreen page limit must be OFFSCREEN_PAGE_LIMIT_DEFAULT or a number > 0");
        }
        this.f3316x = i7;
        this.f3307o.requestLayout();
    }

    public void setOrientation(int i7) {
        this.f3304l.C2(i7);
        this.f3317y.r();
    }

    public void setPageTransformer(k kVar) {
        if (kVar != null) {
            if (!this.f3314v) {
                this.f3313u = this.f3307o.getItemAnimator();
                this.f3314v = true;
            }
            this.f3307o.setItemAnimator(null);
        } else if (this.f3314v) {
            this.f3307o.setItemAnimator(this.f3313u);
            this.f3313u = null;
            this.f3314v = false;
        }
        if (kVar == this.f3312t.d()) {
            return;
        }
        this.f3312t.e(kVar);
        h();
    }

    public void setUserInputEnabled(boolean z6) {
        this.f3315w = z6;
        this.f3317y.s();
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f3318e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        int f3319g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        Parcelable f3320h;

        static class a implements Parcelable.ClassLoaderCreator<SavedState> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return createFromParcel(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i7) {
                return new SavedState[i7];
            }
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            b(parcel, classLoader);
        }

        private void b(Parcel parcel, ClassLoader classLoader) {
            this.f3318e = parcel.readInt();
            this.f3319g = parcel.readInt();
            this.f3320h = parcel.readParcelable(classLoader);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i7) {
            super.writeToParcel(parcel, i7);
            parcel.writeInt(this.f3318e);
            parcel.writeInt(this.f3319g);
            parcel.writeParcelable(this.f3320h, i7);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }
}
