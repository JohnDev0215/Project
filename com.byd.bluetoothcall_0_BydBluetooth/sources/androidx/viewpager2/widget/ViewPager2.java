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
import b.g.k.c0.c;
import b.g.k.c0.f;
import b.g.k.t;
import ts.car.service.carplay.data.config.CarPlayConfig;

/* JADX INFO: loaded from: classes.dex */
public final class ViewPager2 extends ViewGroup {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static boolean f1924b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Rect f1925c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Rect f1926d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private androidx.viewpager2.widget.b f1927e;
    int f;
    boolean g;
    private RecyclerView.i h;
    private LinearLayoutManager i;
    private int j;
    private Parcelable k;
    RecyclerView l;
    private androidx.recyclerview.widget.k m;
    androidx.viewpager2.widget.e n;
    private androidx.viewpager2.widget.b o;
    private androidx.viewpager2.widget.c p;
    private androidx.viewpager2.widget.d q;
    private RecyclerView.l r;
    private boolean s;
    private boolean t;
    private int u;
    e v;

    class a extends g {
        a() {
            super(null);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void a() {
            ViewPager2 viewPager2 = ViewPager2.this;
            viewPager2.g = true;
            viewPager2.n.l();
        }
    }

    class b extends i {
        b() {
        }

        @Override // androidx.viewpager2.widget.ViewPager2.i
        public void a(int i) {
            if (i == 0) {
                ViewPager2.this.m();
            }
        }

        @Override // androidx.viewpager2.widget.ViewPager2.i
        public void c(int i) {
            ViewPager2 viewPager2 = ViewPager2.this;
            if (viewPager2.f != i) {
                viewPager2.f = i;
                viewPager2.v.q();
            }
        }
    }

    class c extends i {
        c() {
        }

        @Override // androidx.viewpager2.widget.ViewPager2.i
        public void c(int i) {
            ViewPager2.this.clearFocus();
            if (ViewPager2.this.hasFocus()) {
                ViewPager2.this.l.requestFocus(2);
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

        boolean b(int i) {
            return false;
        }

        boolean c(int i, Bundle bundle) {
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

        void j(b.g.k.c0.c cVar) {
        }

        boolean k(int i) {
            throw new IllegalStateException("Not implemented.");
        }

        boolean l(int i, Bundle bundle) {
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
        public boolean b(int i) {
            return (i == 8192 || i == 4096) && !ViewPager2.this.e();
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public boolean d() {
            return true;
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public void j(b.g.k.c0.c cVar) {
            if (ViewPager2.this.e()) {
                return;
            }
            cVar.T(c.a.n);
            cVar.T(c.a.m);
            cVar.x0(false);
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public boolean k(int i) {
            if (b(i)) {
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

        /* synthetic */ g(a aVar) {
            this();
        }
    }

    private class h extends LinearLayoutManager {
        h(Context context) {
            super(context);
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
        public void N0(RecyclerView.v vVar, RecyclerView.z zVar, b.g.k.c0.c cVar) {
            super.N0(vVar, zVar, cVar);
            ViewPager2.this.v.j(cVar);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.o
        public boolean h1(RecyclerView.v vVar, RecyclerView.z zVar, int i, Bundle bundle) {
            return ViewPager2.this.v.b(i) ? ViewPager2.this.v.k(i) : super.h1(vVar, zVar, i, bundle);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.o
        public boolean s1(RecyclerView recyclerView, View view, Rect rect, boolean z, boolean z2) {
            return false;
        }
    }

    public static abstract class i {
        public void a(int i) {
        }

        public void b(int i, float f, int i2) {
        }

        public void c(int i) {
        }
    }

    class j extends e {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final b.g.k.c0.f f1937b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final b.g.k.c0.f f1938c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private RecyclerView.i f1939d;

        class a implements b.g.k.c0.f {
            a() {
            }

            @Override // b.g.k.c0.f
            public boolean a(View view, f.a aVar) {
                j.this.v(((ViewPager2) view).getCurrentItem() + 1);
                return true;
            }
        }

        class b implements b.g.k.c0.f {
            b() {
            }

            @Override // b.g.k.c0.f
            public boolean a(View view, f.a aVar) {
                j.this.v(((ViewPager2) view).getCurrentItem() - 1);
                return true;
            }
        }

        class c extends g {
            c() {
                super(null);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.i
            public void a() {
                j.this.w();
            }
        }

        j() {
            super(ViewPager2.this, null);
            this.f1937b = new a();
            this.f1938c = new b();
        }

        private void t(AccessibilityNodeInfo accessibilityNodeInfo) {
            int iC;
            int iC2;
            if (ViewPager2.this.getAdapter() == null) {
                iC = 0;
                iC2 = 0;
            } else if (ViewPager2.this.getOrientation() == 1) {
                iC = ViewPager2.this.getAdapter().c();
                iC2 = 0;
            } else {
                iC2 = ViewPager2.this.getAdapter().c();
                iC = 0;
            }
            b.g.k.c0.c.F0(accessibilityNodeInfo).e0(c.b.b(iC, iC2, false, 0));
        }

        private void u(AccessibilityNodeInfo accessibilityNodeInfo) {
            int iC;
            RecyclerView.g adapter = ViewPager2.this.getAdapter();
            if (adapter == null || (iC = adapter.c()) == 0 || !ViewPager2.this.e()) {
                return;
            }
            if (ViewPager2.this.f > 0) {
                accessibilityNodeInfo.addAction(CarPlayConfig.IapFeature.IAP_FEATURE_OOB_BT_PAIRING);
            }
            if (ViewPager2.this.f < iC - 1) {
                accessibilityNodeInfo.addAction(4096);
            }
            accessibilityNodeInfo.setScrollable(true);
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public boolean a() {
            return true;
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public boolean c(int i, Bundle bundle) {
            return i == 8192 || i == 4096;
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public void e(RecyclerView.g<?> gVar) {
            w();
            if (gVar != null) {
                gVar.r(this.f1939d);
            }
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public void f(RecyclerView.g<?> gVar) {
            if (gVar != null) {
                gVar.t(this.f1939d);
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
            t.u0(recyclerView, 2);
            this.f1939d = new c();
            if (t.x(ViewPager2.this) == 0) {
                t.u0(ViewPager2.this, 1);
            }
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public void i(AccessibilityNodeInfo accessibilityNodeInfo) {
            t(accessibilityNodeInfo);
            if (Build.VERSION.SDK_INT >= 16) {
                u(accessibilityNodeInfo);
            }
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public boolean l(int i, Bundle bundle) {
            if (!c(i, bundle)) {
                throw new IllegalStateException();
            }
            v(i == 8192 ? ViewPager2.this.getCurrentItem() - 1 : ViewPager2.this.getCurrentItem() + 1);
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
            if (Build.VERSION.SDK_INT < 21) {
                ViewPager2.this.sendAccessibilityEvent(2048);
            }
        }

        void v(int i) {
            if (ViewPager2.this.e()) {
                ViewPager2.this.j(i, true);
            }
        }

        void w() {
            int iC;
            ViewPager2 viewPager2 = ViewPager2.this;
            int i = R.id.accessibilityActionPageLeft;
            t.e0(viewPager2, R.id.accessibilityActionPageLeft);
            t.e0(viewPager2, R.id.accessibilityActionPageRight);
            t.e0(viewPager2, R.id.accessibilityActionPageUp);
            t.e0(viewPager2, R.id.accessibilityActionPageDown);
            if (ViewPager2.this.getAdapter() == null || (iC = ViewPager2.this.getAdapter().c()) == 0 || !ViewPager2.this.e()) {
                return;
            }
            if (ViewPager2.this.getOrientation() != 0) {
                if (ViewPager2.this.f < iC - 1) {
                    t.g0(viewPager2, new c.a(R.id.accessibilityActionPageDown, null), null, this.f1937b);
                }
                if (ViewPager2.this.f > 0) {
                    t.g0(viewPager2, new c.a(R.id.accessibilityActionPageUp, null), null, this.f1938c);
                    return;
                }
                return;
            }
            boolean zD = ViewPager2.this.d();
            int i2 = zD ? 16908360 : 16908361;
            if (zD) {
                i = 16908361;
            }
            if (ViewPager2.this.f < iC - 1) {
                t.g0(viewPager2, new c.a(i2, null), null, this.f1937b);
            }
            if (ViewPager2.this.f > 0) {
                t.g0(viewPager2, new c.a(i, null), null, this.f1938c);
            }
        }
    }

    public interface k {
        void a(View view, float f);
    }

    private class l extends androidx.recyclerview.widget.k {
        l() {
        }

        @Override // androidx.recyclerview.widget.k, androidx.recyclerview.widget.o
        public View h(RecyclerView.o oVar) {
            if (ViewPager2.this.c()) {
                return null;
            }
            return super.h(oVar);
        }
    }

    private class m extends RecyclerView {
        m(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public CharSequence getAccessibilityClassName() {
            return ViewPager2.this.v.d() ? ViewPager2.this.v.n() : super.getAccessibilityClassName();
        }

        @Override // android.view.View
        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setFromIndex(ViewPager2.this.f);
            accessibilityEvent.setToIndex(ViewPager2.this.f);
            ViewPager2.this.v.o(accessibilityEvent);
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

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f1944b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final RecyclerView f1945c;

        n(int i, RecyclerView recyclerView) {
            this.f1944b = i;
            this.f1945c = recyclerView;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f1945c.s1(this.f1944b);
        }
    }

    public ViewPager2(Context context) {
        super(context);
        this.f1925c = new Rect();
        this.f1926d = new Rect();
        this.f1927e = new androidx.viewpager2.widget.b(3);
        this.g = false;
        this.h = new a();
        this.j = -1;
        this.r = null;
        this.s = false;
        this.t = true;
        this.u = -1;
        b(context, null);
    }

    private RecyclerView.q a() {
        return new d();
    }

    private void b(Context context, AttributeSet attributeSet) {
        this.v = f1924b ? new j() : new f();
        m mVar = new m(context);
        this.l = mVar;
        mVar.setId(t.j());
        this.l.setDescendantFocusability(CarPlayConfig.IapFeature.IAP_FEATURE_APP_LAUNCH);
        h hVar = new h(context);
        this.i = hVar;
        this.l.setLayoutManager(hVar);
        this.l.setScrollingTouchSlop(1);
        k(context, attributeSet);
        this.l.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.l.j(a());
        androidx.viewpager2.widget.e eVar = new androidx.viewpager2.widget.e(this);
        this.n = eVar;
        this.p = new androidx.viewpager2.widget.c(this, eVar, this.l);
        l lVar = new l();
        this.m = lVar;
        lVar.b(this.l);
        this.l.l(this.n);
        androidx.viewpager2.widget.b bVar = new androidx.viewpager2.widget.b(3);
        this.o = bVar;
        this.n.o(bVar);
        b bVar2 = new b();
        c cVar = new c();
        this.o.d(bVar2);
        this.o.d(cVar);
        this.v.h(this.o, this.l);
        this.o.d(this.f1927e);
        androidx.viewpager2.widget.d dVar = new androidx.viewpager2.widget.d(this.i);
        this.q = dVar;
        this.o.d(dVar);
        RecyclerView recyclerView = this.l;
        attachViewToParent(recyclerView, 0, recyclerView.getLayoutParams());
    }

    private void f(RecyclerView.g<?> gVar) {
        if (gVar != null) {
            gVar.r(this.h);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void h() {
        RecyclerView.g adapter;
        if (this.j == -1 || (adapter = getAdapter()) == 0) {
            return;
        }
        Parcelable parcelable = this.k;
        if (parcelable != null) {
            if (adapter instanceof androidx.viewpager2.adapter.a) {
                ((androidx.viewpager2.adapter.a) adapter).b(parcelable);
            }
            this.k = null;
        }
        int iMax = Math.max(0, Math.min(this.j, adapter.c() - 1));
        this.f = iMax;
        this.j = -1;
        this.l.k1(iMax);
        this.v.m();
    }

    private void k(Context context, AttributeSet attributeSet) {
        int[] iArr = b.o.a.g;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr);
        if (Build.VERSION.SDK_INT >= 29) {
            saveAttributeDataForStyleable(context, iArr, attributeSet, typedArrayObtainStyledAttributes, 0, 0);
        }
        try {
            setOrientation(typedArrayObtainStyledAttributes.getInt(b.o.a.h, 0));
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    private void l(RecyclerView.g<?> gVar) {
        if (gVar != null) {
            gVar.t(this.h);
        }
    }

    public boolean c() {
        return this.p.a();
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i2) {
        return this.l.canScrollHorizontally(i2);
    }

    @Override // android.view.View
    public boolean canScrollVertically(int i2) {
        return this.l.canScrollVertically(i2);
    }

    boolean d() {
        return this.i.Z() == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        Parcelable parcelable = sparseArray.get(getId());
        if (parcelable instanceof SavedState) {
            int i2 = ((SavedState) parcelable).f1928b;
            sparseArray.put(this.l.getId(), sparseArray.get(i2));
            sparseArray.remove(i2);
        }
        super.dispatchRestoreInstanceState(sparseArray);
        h();
    }

    public boolean e() {
        return this.t;
    }

    public void g() {
        if (this.q.d() == null) {
            return;
        }
        double dG = this.n.g();
        int i2 = (int) dG;
        float f2 = (float) (dG - ((double) i2));
        this.q.b(i2, f2, Math.round(getPageSize() * f2));
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return this.v.a() ? this.v.g() : super.getAccessibilityClassName();
    }

    public RecyclerView.g getAdapter() {
        return this.l.getAdapter();
    }

    public int getCurrentItem() {
        return this.f;
    }

    public int getItemDecorationCount() {
        return this.l.getItemDecorationCount();
    }

    public int getOffscreenPageLimit() {
        return this.u;
    }

    public int getOrientation() {
        return this.i.r2();
    }

    int getPageSize() {
        int height;
        int paddingBottom;
        RecyclerView recyclerView = this.l;
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
        return this.n.h();
    }

    public void i(int i2, boolean z) {
        if (c()) {
            throw new IllegalStateException("Cannot change current item when ViewPager2 is fake dragging");
        }
        j(i2, z);
    }

    void j(int i2, boolean z) {
        RecyclerView.g adapter = getAdapter();
        if (adapter == null) {
            if (this.j != -1) {
                this.j = Math.max(i2, 0);
                return;
            }
            return;
        }
        if (adapter.c() <= 0) {
            return;
        }
        int iMin = Math.min(Math.max(i2, 0), adapter.c() - 1);
        if (iMin == this.f && this.n.j()) {
            return;
        }
        int i3 = this.f;
        if (iMin == i3 && z) {
            return;
        }
        double dG = i3;
        this.f = iMin;
        this.v.q();
        if (!this.n.j()) {
            dG = this.n.g();
        }
        this.n.m(iMin, z);
        if (!z) {
            this.l.k1(iMin);
            return;
        }
        double d2 = iMin;
        if (Math.abs(d2 - dG) <= 3.0d) {
            this.l.s1(iMin);
            return;
        }
        this.l.k1(d2 > dG ? iMin - 3 : iMin + 3);
        RecyclerView recyclerView = this.l;
        recyclerView.post(new n(iMin, recyclerView));
    }

    void m() {
        androidx.recyclerview.widget.k kVar = this.m;
        if (kVar == null) {
            throw new IllegalStateException("Design assumption violated.");
        }
        View viewH = kVar.h(this.i);
        if (viewH == null) {
            return;
        }
        int iH0 = this.i.h0(viewH);
        if (iH0 != this.f && getScrollState() == 0) {
            this.o.c(iH0);
        }
        this.g = false;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        this.v.i(accessibilityNodeInfo);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int measuredWidth = this.l.getMeasuredWidth();
        int measuredHeight = this.l.getMeasuredHeight();
        this.f1925c.left = getPaddingLeft();
        this.f1925c.right = (i4 - i2) - getPaddingRight();
        this.f1925c.top = getPaddingTop();
        this.f1925c.bottom = (i5 - i3) - getPaddingBottom();
        Gravity.apply(8388659, measuredWidth, measuredHeight, this.f1925c, this.f1926d);
        RecyclerView recyclerView = this.l;
        Rect rect = this.f1926d;
        recyclerView.layout(rect.left, rect.top, rect.right, rect.bottom);
        if (this.g) {
            m();
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        measureChild(this.l, i2, i3);
        int measuredWidth = this.l.getMeasuredWidth();
        int measuredHeight = this.l.getMeasuredHeight();
        int measuredState = this.l.getMeasuredState();
        int paddingLeft = measuredWidth + getPaddingLeft() + getPaddingRight();
        int paddingTop = measuredHeight + getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(ViewGroup.resolveSizeAndState(Math.max(paddingLeft, getSuggestedMinimumWidth()), i2, measuredState), ViewGroup.resolveSizeAndState(Math.max(paddingTop, getSuggestedMinimumHeight()), i3, measuredState << 16));
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.j = savedState.f1929c;
        this.k = savedState.f1930d;
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f1928b = this.l.getId();
        int i2 = this.j;
        if (i2 == -1) {
            i2 = this.f;
        }
        savedState.f1929c = i2;
        Parcelable parcelable = this.k;
        if (parcelable != null) {
            savedState.f1930d = parcelable;
        } else {
            Object adapter = this.l.getAdapter();
            if (adapter instanceof androidx.viewpager2.adapter.a) {
                savedState.f1930d = ((androidx.viewpager2.adapter.a) adapter).a();
            }
        }
        return savedState;
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        throw new IllegalStateException(ViewPager2.class.getSimpleName() + " does not support direct child views");
    }

    @Override // android.view.View
    public boolean performAccessibilityAction(int i2, Bundle bundle) {
        return this.v.c(i2, bundle) ? this.v.l(i2, bundle) : super.performAccessibilityAction(i2, bundle);
    }

    public void setAdapter(RecyclerView.g gVar) {
        RecyclerView.g adapter = this.l.getAdapter();
        this.v.f(adapter);
        l(adapter);
        this.l.setAdapter(gVar);
        this.f = 0;
        h();
        this.v.e(gVar);
        f(gVar);
    }

    public void setCurrentItem(int i2) {
        i(i2, true);
    }

    @Override // android.view.View
    public void setLayoutDirection(int i2) {
        super.setLayoutDirection(i2);
        this.v.p();
    }

    public void setOffscreenPageLimit(int i2) {
        if (i2 < 1 && i2 != -1) {
            throw new IllegalArgumentException("Offscreen page limit must be OFFSCREEN_PAGE_LIMIT_DEFAULT or a number > 0");
        }
        this.u = i2;
        this.l.requestLayout();
    }

    public void setOrientation(int i2) {
        this.i.F2(i2);
        this.v.r();
    }

    public void setPageTransformer(k kVar) {
        if (kVar != null) {
            if (!this.s) {
                this.r = this.l.getItemAnimator();
                this.s = true;
            }
            this.l.setItemAnimator(null);
        } else if (this.s) {
            this.l.setItemAnimator(this.r);
            this.r = null;
            this.s = false;
        }
        if (kVar == this.q.d()) {
            return;
        }
        this.q.e(kVar);
        g();
    }

    public void setUserInputEnabled(boolean z) {
        this.t = z;
        this.v.s();
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1928b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1929c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        Parcelable f1930d;

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
                return Build.VERSION.SDK_INT >= 24 ? new SavedState(parcel, classLoader) : new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            a(parcel, classLoader);
        }

        private void a(Parcel parcel, ClassLoader classLoader) {
            this.f1928b = parcel.readInt();
            this.f1929c = parcel.readInt();
            this.f1930d = parcel.readParcelable(classLoader);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f1928b);
            parcel.writeInt(this.f1929c);
            parcel.writeParcelable(this.f1930d, i);
        }

        SavedState(Parcel parcel) {
            super(parcel);
            a(parcel, null);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public ViewPager2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1925c = new Rect();
        this.f1926d = new Rect();
        this.f1927e = new androidx.viewpager2.widget.b(3);
        this.g = false;
        this.h = new a();
        this.j = -1;
        this.r = null;
        this.s = false;
        this.t = true;
        this.u = -1;
        b(context, attributeSet);
    }

    public ViewPager2(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f1925c = new Rect();
        this.f1926d = new Rect();
        this.f1927e = new androidx.viewpager2.widget.b(3);
        this.g = false;
        this.h = new a();
        this.j = -1;
        this.r = null;
        this.s = false;
        this.t = true;
        this.u = -1;
        b(context, attributeSet);
    }
}
