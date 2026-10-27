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
import androidx.viewpager2.R$styleable;
import l0.t;
import m0.c;
import m0.f;

/* JADX INFO: loaded from: classes.dex */
public final class ViewPager2 extends ViewGroup {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f1716b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Rect f1717c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Rect f1718d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public d1.b f1719e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1720f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1721g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public RecyclerView.i f1722h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public LinearLayoutManager f1723i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f1724j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Parcelable f1725k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public RecyclerView f1726l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public w0.k f1727m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public d1.e f1728n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public d1.b f1729o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public d1.c f1730p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public d1.d f1731q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public RecyclerView.l f1732r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f1733s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f1734t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f1735u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public e f1736v;

    public class a extends g {
        public a() {
            super(null);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void a() {
            ViewPager2 viewPager2 = ViewPager2.this;
            viewPager2.f1721g = true;
            viewPager2.f1728n.l();
        }
    }

    public class b extends i {
        public b() {
        }

        @Override // androidx.viewpager2.widget.ViewPager2.i
        public void a(int i3) {
            if (i3 == 0) {
                ViewPager2.this.m();
            }
        }

        @Override // androidx.viewpager2.widget.ViewPager2.i
        public void c(int i3) {
            ViewPager2 viewPager2 = ViewPager2.this;
            if (viewPager2.f1720f != i3) {
                viewPager2.f1720f = i3;
                viewPager2.f1736v.q();
            }
        }
    }

    public class c extends i {
        public c() {
        }

        @Override // androidx.viewpager2.widget.ViewPager2.i
        public void c(int i3) {
            ViewPager2.this.clearFocus();
            if (ViewPager2.this.hasFocus()) {
                ViewPager2.this.f1726l.requestFocus(2);
            }
        }
    }

    public class d implements RecyclerView.q {
        public d() {
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

    public abstract class e {
        public e() {
        }

        public boolean a() {
            return false;
        }

        public boolean b(int i3) {
            return false;
        }

        public boolean c(int i3, Bundle bundle) {
            return false;
        }

        public boolean d() {
            return false;
        }

        public void e(RecyclerView.g<?> gVar) {
        }

        public void f(RecyclerView.g<?> gVar) {
        }

        public String g() {
            throw new IllegalStateException("Not implemented.");
        }

        public void h(d1.b bVar, RecyclerView recyclerView) {
        }

        public void i(AccessibilityNodeInfo accessibilityNodeInfo) {
        }

        public void j(m0.c cVar) {
        }

        public boolean k(int i3) {
            throw new IllegalStateException("Not implemented.");
        }

        public boolean l(int i3, Bundle bundle) {
            throw new IllegalStateException("Not implemented.");
        }

        public void m() {
        }

        public CharSequence n() {
            throw new IllegalStateException("Not implemented.");
        }

        public void o(AccessibilityEvent accessibilityEvent) {
        }

        public void p() {
        }

        public void q() {
        }

        public void r() {
        }

        public void s() {
        }

        public /* synthetic */ e(ViewPager2 viewPager2, a aVar) {
            this();
        }
    }

    public class f extends e {
        public f() {
            super(ViewPager2.this, null);
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public boolean b(int i3) {
            return (i3 == 8192 || i3 == 4096) && !ViewPager2.this.e();
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public boolean d() {
            return true;
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public void j(m0.c cVar) {
            if (ViewPager2.this.e()) {
                return;
            }
            cVar.S(c.a.f3815n);
            cVar.S(c.a.f3814m);
            cVar.v0(false);
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public boolean k(int i3) {
            if (b(i3)) {
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

    public static abstract class g extends RecyclerView.i {
        public g() {
        }

        public /* synthetic */ g(a aVar) {
            this();
        }
    }

    public class h extends LinearLayoutManager {
        public h(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        public void M1(RecyclerView.z zVar, int[] iArr) {
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
        public void N0(RecyclerView.v vVar, RecyclerView.z zVar, m0.c cVar) {
            super.N0(vVar, zVar, cVar);
            ViewPager2.this.f1736v.j(cVar);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.o
        public boolean h1(RecyclerView.v vVar, RecyclerView.z zVar, int i3, Bundle bundle) {
            return ViewPager2.this.f1736v.b(i3) ? ViewPager2.this.f1736v.k(i3) : super.h1(vVar, zVar, i3, bundle);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.o
        public boolean s1(RecyclerView recyclerView, View view, Rect rect, boolean z3, boolean z4) {
            return false;
        }
    }

    public static abstract class i {
        public void a(int i3) {
        }

        public void b(int i3, float f3, int i4) {
        }

        public void c(int i3) {
        }
    }

    public class j extends e {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final m0.f f1746b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final m0.f f1747c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public RecyclerView.i f1748d;

        public class a implements m0.f {
            public a() {
            }

            @Override // m0.f
            public boolean a(View view, f.a aVar) {
                j.this.v(((ViewPager2) view).getCurrentItem() + 1);
                return true;
            }
        }

        public class b implements m0.f {
            public b() {
            }

            @Override // m0.f
            public boolean a(View view, f.a aVar) {
                j.this.v(((ViewPager2) view).getCurrentItem() - 1);
                return true;
            }
        }

        public class c extends g {
            public c() {
                super(null);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.i
            public void a() {
                j.this.w();
            }
        }

        public j() {
            super(ViewPager2.this, null);
            this.f1746b = new a();
            this.f1747c = new b();
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public boolean a() {
            return true;
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public boolean c(int i3, Bundle bundle) {
            return i3 == 8192 || i3 == 4096;
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public void e(RecyclerView.g<?> gVar) {
            w();
            if (gVar != null) {
                gVar.r(this.f1748d);
            }
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public void f(RecyclerView.g<?> gVar) {
            if (gVar != null) {
                gVar.t(this.f1748d);
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
        public void h(d1.b bVar, RecyclerView recyclerView) {
            t.t0(recyclerView, 2);
            this.f1748d = new c();
            if (t.x(ViewPager2.this) == 0) {
                t.t0(ViewPager2.this, 1);
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
        public boolean l(int i3, Bundle bundle) {
            if (!c(i3, bundle)) {
                throw new IllegalStateException();
            }
            v(i3 == 8192 ? ViewPager2.this.getCurrentItem() - 1 : ViewPager2.this.getCurrentItem() + 1);
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

        public final void t(AccessibilityNodeInfo accessibilityNodeInfo) {
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
            m0.c.D0(accessibilityNodeInfo).d0(c.b.b(iC, iC2, false, 0));
        }

        public final void u(AccessibilityNodeInfo accessibilityNodeInfo) {
            int iC;
            RecyclerView.g adapter = ViewPager2.this.getAdapter();
            if (adapter == null || (iC = adapter.c()) == 0 || !ViewPager2.this.e()) {
                return;
            }
            if (ViewPager2.this.f1720f > 0) {
                accessibilityNodeInfo.addAction(8192);
            }
            if (ViewPager2.this.f1720f < iC - 1) {
                accessibilityNodeInfo.addAction(4096);
            }
            accessibilityNodeInfo.setScrollable(true);
        }

        public void v(int i3) {
            if (ViewPager2.this.e()) {
                ViewPager2.this.j(i3, true);
            }
        }

        public void w() {
            int iC;
            ViewPager2 viewPager2 = ViewPager2.this;
            int i3 = R.id.accessibilityActionPageLeft;
            t.d0(viewPager2, R.id.accessibilityActionPageLeft);
            t.d0(viewPager2, R.id.accessibilityActionPageRight);
            t.d0(viewPager2, R.id.accessibilityActionPageUp);
            t.d0(viewPager2, R.id.accessibilityActionPageDown);
            if (ViewPager2.this.getAdapter() == null || (iC = ViewPager2.this.getAdapter().c()) == 0 || !ViewPager2.this.e()) {
                return;
            }
            if (ViewPager2.this.getOrientation() != 0) {
                if (ViewPager2.this.f1720f < iC - 1) {
                    t.f0(viewPager2, new c.a(R.id.accessibilityActionPageDown, null), null, this.f1746b);
                }
                if (ViewPager2.this.f1720f > 0) {
                    t.f0(viewPager2, new c.a(R.id.accessibilityActionPageUp, null), null, this.f1747c);
                    return;
                }
                return;
            }
            boolean zD = ViewPager2.this.d();
            int i4 = zD ? 16908360 : 16908361;
            if (zD) {
                i3 = 16908361;
            }
            if (ViewPager2.this.f1720f < iC - 1) {
                t.f0(viewPager2, new c.a(i4, null), null, this.f1746b);
            }
            if (ViewPager2.this.f1720f > 0) {
                t.f0(viewPager2, new c.a(i3, null), null, this.f1747c);
            }
        }
    }

    public interface k {
        void a(View view, float f3);
    }

    public class l extends w0.k {
        public l() {
        }

        @Override // w0.k, w0.o
        public View h(RecyclerView.o oVar) {
            if (ViewPager2.this.c()) {
                return null;
            }
            return super.h(oVar);
        }
    }

    public class m extends RecyclerView {
        public m(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public CharSequence getAccessibilityClassName() {
            return ViewPager2.this.f1736v.d() ? ViewPager2.this.f1736v.n() : super.getAccessibilityClassName();
        }

        @Override // android.view.View
        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setFromIndex(ViewPager2.this.f1720f);
            accessibilityEvent.setToIndex(ViewPager2.this.f1720f);
            ViewPager2.this.f1736v.o(accessibilityEvent);
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

    public static class n implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f1754b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final RecyclerView f1755c;

        public n(int i3, RecyclerView recyclerView) {
            this.f1754b = i3;
            this.f1755c = recyclerView;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f1755c.p1(this.f1754b);
        }
    }

    public ViewPager2(Context context) {
        super(context);
        this.f1717c = new Rect();
        this.f1718d = new Rect();
        this.f1719e = new d1.b(3);
        this.f1721g = false;
        this.f1722h = new a();
        this.f1724j = -1;
        this.f1732r = null;
        this.f1733s = false;
        this.f1734t = true;
        this.f1735u = -1;
        b(context, null);
    }

    public final RecyclerView.q a() {
        return new d();
    }

    public final void b(Context context, AttributeSet attributeSet) {
        this.f1736v = f1716b ? new j() : new f();
        m mVar = new m(context);
        this.f1726l = mVar;
        mVar.setId(t.j());
        this.f1726l.setDescendantFocusability(131072);
        h hVar = new h(context);
        this.f1723i = hVar;
        this.f1726l.setLayoutManager(hVar);
        this.f1726l.setScrollingTouchSlop(1);
        k(context, attributeSet);
        this.f1726l.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.f1726l.j(a());
        d1.e eVar = new d1.e(this);
        this.f1728n = eVar;
        this.f1730p = new d1.c(this, eVar, this.f1726l);
        l lVar = new l();
        this.f1727m = lVar;
        lVar.b(this.f1726l);
        this.f1726l.l(this.f1728n);
        d1.b bVar = new d1.b(3);
        this.f1729o = bVar;
        this.f1728n.o(bVar);
        b bVar2 = new b();
        c cVar = new c();
        this.f1729o.d(bVar2);
        this.f1729o.d(cVar);
        this.f1736v.h(this.f1729o, this.f1726l);
        this.f1729o.d(this.f1719e);
        d1.d dVar = new d1.d(this.f1723i);
        this.f1731q = dVar;
        this.f1729o.d(dVar);
        RecyclerView recyclerView = this.f1726l;
        attachViewToParent(recyclerView, 0, recyclerView.getLayoutParams());
    }

    public boolean c() {
        return this.f1730p.a();
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i3) {
        return this.f1726l.canScrollHorizontally(i3);
    }

    @Override // android.view.View
    public boolean canScrollVertically(int i3) {
        return this.f1726l.canScrollVertically(i3);
    }

    public boolean d() {
        return this.f1723i.Z() == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        Parcelable parcelable = sparseArray.get(getId());
        if (parcelable instanceof SavedState) {
            int i3 = ((SavedState) parcelable).f1737b;
            sparseArray.put(this.f1726l.getId(), sparseArray.get(i3));
            sparseArray.remove(i3);
        }
        super.dispatchRestoreInstanceState(sparseArray);
        h();
    }

    public boolean e() {
        return this.f1734t;
    }

    public final void f(RecyclerView.g<?> gVar) {
        if (gVar != null) {
            gVar.r(this.f1722h);
        }
    }

    public void g() {
        if (this.f1731q.d() == null) {
            return;
        }
        double dG = this.f1728n.g();
        int i3 = (int) dG;
        float f3 = (float) (dG - ((double) i3));
        this.f1731q.b(i3, f3, Math.round(getPageSize() * f3));
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return this.f1736v.a() ? this.f1736v.g() : super.getAccessibilityClassName();
    }

    public RecyclerView.g getAdapter() {
        return this.f1726l.getAdapter();
    }

    public int getCurrentItem() {
        return this.f1720f;
    }

    public int getItemDecorationCount() {
        return this.f1726l.getItemDecorationCount();
    }

    public int getOffscreenPageLimit() {
        return this.f1735u;
    }

    public int getOrientation() {
        return this.f1723i.p2();
    }

    public int getPageSize() {
        int height;
        int paddingBottom;
        RecyclerView recyclerView = this.f1726l;
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
        return this.f1728n.h();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void h() {
        RecyclerView.g adapter;
        if (this.f1724j == -1 || (adapter = getAdapter()) == 0) {
            return;
        }
        Parcelable parcelable = this.f1725k;
        if (parcelable != null) {
            if (adapter instanceof c1.a) {
                ((c1.a) adapter).b(parcelable);
            }
            this.f1725k = null;
        }
        int iMax = Math.max(0, Math.min(this.f1724j, adapter.c() - 1));
        this.f1720f = iMax;
        this.f1724j = -1;
        this.f1726l.h1(iMax);
        this.f1736v.m();
    }

    public void i(int i3, boolean z3) {
        if (c()) {
            throw new IllegalStateException("Cannot change current item when ViewPager2 is fake dragging");
        }
        j(i3, z3);
    }

    public void j(int i3, boolean z3) {
        RecyclerView.g adapter = getAdapter();
        if (adapter == null) {
            if (this.f1724j != -1) {
                this.f1724j = Math.max(i3, 0);
                return;
            }
            return;
        }
        if (adapter.c() <= 0) {
            return;
        }
        int iMin = Math.min(Math.max(i3, 0), adapter.c() - 1);
        if (iMin == this.f1720f && this.f1728n.j()) {
            return;
        }
        int i4 = this.f1720f;
        if (iMin == i4 && z3) {
            return;
        }
        double dG = i4;
        this.f1720f = iMin;
        this.f1736v.q();
        if (!this.f1728n.j()) {
            dG = this.f1728n.g();
        }
        this.f1728n.m(iMin, z3);
        if (!z3) {
            this.f1726l.h1(iMin);
            return;
        }
        double d3 = iMin;
        if (Math.abs(d3 - dG) <= 3.0d) {
            this.f1726l.p1(iMin);
            return;
        }
        this.f1726l.h1(d3 > dG ? iMin - 3 : iMin + 3);
        RecyclerView recyclerView = this.f1726l;
        recyclerView.post(new n(iMin, recyclerView));
    }

    public final void k(Context context, AttributeSet attributeSet) {
        int[] iArr = R$styleable.ViewPager2;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr);
        if (Build.VERSION.SDK_INT >= 29) {
            saveAttributeDataForStyleable(context, iArr, attributeSet, typedArrayObtainStyledAttributes, 0, 0);
        }
        try {
            setOrientation(typedArrayObtainStyledAttributes.getInt(R$styleable.ViewPager2_android_orientation, 0));
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public final void l(RecyclerView.g<?> gVar) {
        if (gVar != null) {
            gVar.t(this.f1722h);
        }
    }

    public void m() {
        w0.k kVar = this.f1727m;
        if (kVar == null) {
            throw new IllegalStateException("Design assumption violated.");
        }
        View viewH = kVar.h(this.f1723i);
        if (viewH == null) {
            return;
        }
        int iH0 = this.f1723i.h0(viewH);
        if (iH0 != this.f1720f && getScrollState() == 0) {
            this.f1729o.c(iH0);
        }
        this.f1721g = false;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        this.f1736v.i(accessibilityNodeInfo);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z3, int i3, int i4, int i5, int i6) {
        int measuredWidth = this.f1726l.getMeasuredWidth();
        int measuredHeight = this.f1726l.getMeasuredHeight();
        this.f1717c.left = getPaddingLeft();
        this.f1717c.right = (i5 - i3) - getPaddingRight();
        this.f1717c.top = getPaddingTop();
        this.f1717c.bottom = (i6 - i4) - getPaddingBottom();
        Gravity.apply(8388659, measuredWidth, measuredHeight, this.f1717c, this.f1718d);
        RecyclerView recyclerView = this.f1726l;
        Rect rect = this.f1718d;
        recyclerView.layout(rect.left, rect.top, rect.right, rect.bottom);
        if (this.f1721g) {
            m();
        }
    }

    @Override // android.view.View
    public void onMeasure(int i3, int i4) {
        measureChild(this.f1726l, i3, i4);
        int measuredWidth = this.f1726l.getMeasuredWidth();
        int measuredHeight = this.f1726l.getMeasuredHeight();
        int measuredState = this.f1726l.getMeasuredState();
        int paddingLeft = measuredWidth + getPaddingLeft() + getPaddingRight();
        int paddingTop = measuredHeight + getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(ViewGroup.resolveSizeAndState(Math.max(paddingLeft, getSuggestedMinimumWidth()), i3, measuredState), ViewGroup.resolveSizeAndState(Math.max(paddingTop, getSuggestedMinimumHeight()), i4, measuredState << 16));
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.f1724j = savedState.f1738c;
        this.f1725k = savedState.f1739d;
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f1737b = this.f1726l.getId();
        int i3 = this.f1724j;
        if (i3 == -1) {
            i3 = this.f1720f;
        }
        savedState.f1738c = i3;
        Parcelable parcelable = this.f1725k;
        if (parcelable != null) {
            savedState.f1739d = parcelable;
        } else {
            Object adapter = this.f1726l.getAdapter();
            if (adapter instanceof c1.a) {
                savedState.f1739d = ((c1.a) adapter).a();
            }
        }
        return savedState;
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        throw new IllegalStateException(ViewPager2.class.getSimpleName() + " does not support direct child views");
    }

    @Override // android.view.View
    public boolean performAccessibilityAction(int i3, Bundle bundle) {
        return this.f1736v.c(i3, bundle) ? this.f1736v.l(i3, bundle) : super.performAccessibilityAction(i3, bundle);
    }

    public void setAdapter(RecyclerView.g gVar) {
        RecyclerView.g adapter = this.f1726l.getAdapter();
        this.f1736v.f(adapter);
        l(adapter);
        this.f1726l.setAdapter(gVar);
        this.f1720f = 0;
        h();
        this.f1736v.e(gVar);
        f(gVar);
    }

    public void setCurrentItem(int i3) {
        i(i3, true);
    }

    @Override // android.view.View
    public void setLayoutDirection(int i3) {
        super.setLayoutDirection(i3);
        this.f1736v.p();
    }

    public void setOffscreenPageLimit(int i3) {
        if (i3 < 1 && i3 != -1) {
            throw new IllegalArgumentException("Offscreen page limit must be OFFSCREEN_PAGE_LIMIT_DEFAULT or a number > 0");
        }
        this.f1735u = i3;
        this.f1726l.requestLayout();
    }

    public void setOrientation(int i3) {
        this.f1723i.C2(i3);
        this.f1736v.r();
    }

    public void setPageTransformer(k kVar) {
        if (kVar != null) {
            if (!this.f1733s) {
                this.f1732r = this.f1726l.getItemAnimator();
                this.f1733s = true;
            }
            this.f1726l.setItemAnimator(null);
        } else if (this.f1733s) {
            this.f1726l.setItemAnimator(this.f1732r);
            this.f1732r = null;
            this.f1733s = false;
        }
        if (kVar == this.f1731q.d()) {
            return;
        }
        this.f1731q.e(kVar);
        g();
    }

    public void setUserInputEnabled(boolean z3) {
        this.f1734t = z3;
        this.f1736v.s();
    }

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1737b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1738c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Parcelable f1739d;

        public static class a implements Parcelable.ClassLoaderCreator<SavedState> {
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
            public SavedState[] newArray(int i3) {
                return new SavedState[i3];
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            f(parcel, classLoader);
        }

        public final void f(Parcel parcel, ClassLoader classLoader) {
            this.f1737b = parcel.readInt();
            this.f1738c = parcel.readInt();
            this.f1739d = parcel.readParcelable(classLoader);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i3) {
            super.writeToParcel(parcel, i3);
            parcel.writeInt(this.f1737b);
            parcel.writeInt(this.f1738c);
            parcel.writeParcelable(this.f1739d, i3);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            f(parcel, null);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public ViewPager2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1717c = new Rect();
        this.f1718d = new Rect();
        this.f1719e = new d1.b(3);
        this.f1721g = false;
        this.f1722h = new a();
        this.f1724j = -1;
        this.f1732r = null;
        this.f1733s = false;
        this.f1734t = true;
        this.f1735u = -1;
        b(context, attributeSet);
    }

    public ViewPager2(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        this.f1717c = new Rect();
        this.f1718d = new Rect();
        this.f1719e = new d1.b(3);
        this.f1721g = false;
        this.f1722h = new a();
        this.f1724j = -1;
        this.f1732r = null;
        this.f1733s = false;
        this.f1734t = true;
        this.f1735u = -1;
        b(context, attributeSet);
    }
}
