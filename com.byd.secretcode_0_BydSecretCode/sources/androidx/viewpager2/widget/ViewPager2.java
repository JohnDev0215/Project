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
import defpackage.ea;
import defpackage.ed;
import defpackage.ha;
import defpackage.lf;
import defpackage.nf;
import defpackage.of;
import defpackage.pf;
import defpackage.qf;
import defpackage.t9;

/* JADX INFO: loaded from: classes.dex */
public final class ViewPager2 extends ViewGroup {
    public static boolean b = true;
    public final Rect c;
    public final Rect d;
    public nf e;
    public int f;
    public boolean g;
    public RecyclerView.i h;
    public LinearLayoutManager i;
    public int j;
    public Parcelable k;
    public RecyclerView l;
    public ed m;
    public qf n;
    public nf o;
    public of p;
    public pf q;
    public RecyclerView.l r;
    public boolean s;
    public boolean t;
    public int u;
    public e v;

    public class a extends g {
        public a() {
            super(null);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void a() {
            ViewPager2 viewPager2 = ViewPager2.this;
            viewPager2.g = true;
            viewPager2.n.l();
        }
    }

    public class b extends i {
        public b() {
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

    public class c extends i {
        public c() {
        }

        @Override // androidx.viewpager2.widget.ViewPager2.i
        public void c(int i) {
            ViewPager2.this.clearFocus();
            if (ViewPager2.this.hasFocus()) {
                ViewPager2.this.l.requestFocus(2);
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

        public boolean b(int i) {
            return false;
        }

        public boolean c(int i, Bundle bundle) {
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

        public void h(nf nfVar, RecyclerView recyclerView) {
        }

        public void i(AccessibilityNodeInfo accessibilityNodeInfo) {
        }

        public void j(ea eaVar) {
        }

        public boolean k(int i) {
            throw new IllegalStateException("Not implemented.");
        }

        public boolean l(int i, Bundle bundle) {
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
        public boolean b(int i) {
            return (i == 8192 || i == 4096) && !ViewPager2.this.e();
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public boolean d() {
            return true;
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public void j(ea eaVar) {
            if (ViewPager2.this.e()) {
                return;
            }
            eaVar.S(ea.a.n);
            eaVar.S(ea.a.m);
            eaVar.v0(false);
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
        public void N0(RecyclerView.v vVar, RecyclerView.z zVar, ea eaVar) {
            super.N0(vVar, zVar, eaVar);
            ViewPager2.this.v.j(eaVar);
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

    public class j extends e {
        public final ha b;
        public final ha c;
        public RecyclerView.i d;

        public class a implements ha {
            public a() {
            }

            @Override // defpackage.ha
            public boolean a(View view, ha.a aVar) {
                j.this.v(((ViewPager2) view).getCurrentItem() + 1);
                return true;
            }
        }

        public class b implements ha {
            public b() {
            }

            @Override // defpackage.ha
            public boolean a(View view, ha.a aVar) {
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
            this.b = new a();
            this.c = new b();
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
                gVar.r(this.d);
            }
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e
        public void f(RecyclerView.g<?> gVar) {
            if (gVar != null) {
                gVar.t(this.d);
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
        public void h(nf nfVar, RecyclerView recyclerView) {
            t9.t0(recyclerView, 2);
            this.d = new c();
            if (t9.x(ViewPager2.this) == 0) {
                t9.t0(ViewPager2.this, 1);
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
            ea.D0(accessibilityNodeInfo).d0(ea.b.b(iC, iC2, false, 0));
        }

        public final void u(AccessibilityNodeInfo accessibilityNodeInfo) {
            int iC;
            RecyclerView.g adapter = ViewPager2.this.getAdapter();
            if (adapter == null || (iC = adapter.c()) == 0 || !ViewPager2.this.e()) {
                return;
            }
            if (ViewPager2.this.f > 0) {
                accessibilityNodeInfo.addAction(8192);
            }
            if (ViewPager2.this.f < iC - 1) {
                accessibilityNodeInfo.addAction(4096);
            }
            accessibilityNodeInfo.setScrollable(true);
        }

        public void v(int i) {
            if (ViewPager2.this.e()) {
                ViewPager2.this.j(i, true);
            }
        }

        public void w() {
            int iC;
            ViewPager2 viewPager2 = ViewPager2.this;
            int i = R.id.accessibilityActionPageLeft;
            t9.d0(viewPager2, R.id.accessibilityActionPageLeft);
            t9.d0(viewPager2, R.id.accessibilityActionPageRight);
            t9.d0(viewPager2, R.id.accessibilityActionPageUp);
            t9.d0(viewPager2, R.id.accessibilityActionPageDown);
            if (ViewPager2.this.getAdapter() == null || (iC = ViewPager2.this.getAdapter().c()) == 0 || !ViewPager2.this.e()) {
                return;
            }
            if (ViewPager2.this.getOrientation() != 0) {
                if (ViewPager2.this.f < iC - 1) {
                    t9.f0(viewPager2, new ea.a(R.id.accessibilityActionPageDown, null), null, this.b);
                }
                if (ViewPager2.this.f > 0) {
                    t9.f0(viewPager2, new ea.a(R.id.accessibilityActionPageUp, null), null, this.c);
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
                t9.f0(viewPager2, new ea.a(i2, null), null, this.b);
            }
            if (ViewPager2.this.f > 0) {
                t9.f0(viewPager2, new ea.a(i, null), null, this.c);
            }
        }
    }

    public interface k {
        void a(View view, float f);
    }

    public class l extends ed {
        public l() {
        }

        @Override // defpackage.ed, defpackage.id
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

    public static class n implements Runnable {
        public final int b;
        public final RecyclerView c;

        public n(int i, RecyclerView recyclerView) {
            this.b = i;
            this.c = recyclerView;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.c.q1(this.b);
        }
    }

    public ViewPager2(Context context) {
        super(context);
        this.c = new Rect();
        this.d = new Rect();
        this.e = new nf(3);
        this.g = false;
        this.h = new a();
        this.j = -1;
        this.r = null;
        this.s = false;
        this.t = true;
        this.u = -1;
        b(context, null);
    }

    public final RecyclerView.q a() {
        return new d();
    }

    public final void b(Context context, AttributeSet attributeSet) {
        this.v = b ? new j() : new f();
        m mVar = new m(context);
        this.l = mVar;
        mVar.setId(t9.j());
        this.l.setDescendantFocusability(131072);
        h hVar = new h(context);
        this.i = hVar;
        this.l.setLayoutManager(hVar);
        this.l.setScrollingTouchSlop(1);
        k(context, attributeSet);
        this.l.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.l.j(a());
        qf qfVar = new qf(this);
        this.n = qfVar;
        this.p = new of(this, qfVar, this.l);
        l lVar = new l();
        this.m = lVar;
        lVar.b(this.l);
        this.l.l(this.n);
        nf nfVar = new nf(3);
        this.o = nfVar;
        this.n.o(nfVar);
        b bVar = new b();
        c cVar = new c();
        this.o.d(bVar);
        this.o.d(cVar);
        this.v.h(this.o, this.l);
        this.o.d(this.e);
        pf pfVar = new pf(this.i);
        this.q = pfVar;
        this.o.d(pfVar);
        RecyclerView recyclerView = this.l;
        attachViewToParent(recyclerView, 0, recyclerView.getLayoutParams());
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

    public boolean d() {
        return this.i.Z() == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        Parcelable parcelable = sparseArray.get(getId());
        if (parcelable instanceof SavedState) {
            int i2 = ((SavedState) parcelable).b;
            sparseArray.put(this.l.getId(), sparseArray.get(i2));
            sparseArray.remove(i2);
        }
        super.dispatchRestoreInstanceState(sparseArray);
        h();
    }

    public boolean e() {
        return this.t;
    }

    public final void f(RecyclerView.g<?> gVar) {
        if (gVar != null) {
            gVar.r(this.h);
        }
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
        return this.i.p2();
    }

    public int getPageSize() {
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

    /* JADX WARN: Multi-variable type inference failed */
    public final void h() {
        RecyclerView.g adapter;
        if (this.j == -1 || (adapter = getAdapter()) == 0) {
            return;
        }
        Parcelable parcelable = this.k;
        if (parcelable != null) {
            if (adapter instanceof lf) {
                ((lf) adapter).b(parcelable);
            }
            this.k = null;
        }
        int iMax = Math.max(0, Math.min(this.j, adapter.c() - 1));
        this.f = iMax;
        this.j = -1;
        this.l.i1(iMax);
        this.v.m();
    }

    public void i(int i2, boolean z) {
        if (c()) {
            throw new IllegalStateException("Cannot change current item when ViewPager2 is fake dragging");
        }
        j(i2, z);
    }

    public void j(int i2, boolean z) {
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
            this.l.i1(iMin);
            return;
        }
        double d2 = iMin;
        if (Math.abs(d2 - dG) <= 3.0d) {
            this.l.q1(iMin);
            return;
        }
        this.l.i1(d2 > dG ? iMin - 3 : iMin + 3);
        RecyclerView recyclerView = this.l;
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
            gVar.t(this.h);
        }
    }

    public void m() {
        ed edVar = this.m;
        if (edVar == null) {
            throw new IllegalStateException("Design assumption violated.");
        }
        View viewH = edVar.h(this.i);
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
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int measuredWidth = this.l.getMeasuredWidth();
        int measuredHeight = this.l.getMeasuredHeight();
        this.c.left = getPaddingLeft();
        this.c.right = (i4 - i2) - getPaddingRight();
        this.c.top = getPaddingTop();
        this.c.bottom = (i5 - i3) - getPaddingBottom();
        Gravity.apply(8388659, measuredWidth, measuredHeight, this.c, this.d);
        RecyclerView recyclerView = this.l;
        Rect rect = this.d;
        recyclerView.layout(rect.left, rect.top, rect.right, rect.bottom);
        if (this.g) {
            m();
        }
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        measureChild(this.l, i2, i3);
        int measuredWidth = this.l.getMeasuredWidth();
        int measuredHeight = this.l.getMeasuredHeight();
        int measuredState = this.l.getMeasuredState();
        int paddingLeft = measuredWidth + getPaddingLeft() + getPaddingRight();
        int paddingTop = measuredHeight + getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(ViewGroup.resolveSizeAndState(Math.max(paddingLeft, getSuggestedMinimumWidth()), i2, measuredState), ViewGroup.resolveSizeAndState(Math.max(paddingTop, getSuggestedMinimumHeight()), i3, measuredState << 16));
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.j = savedState.c;
        this.k = savedState.d;
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.b = this.l.getId();
        int i2 = this.j;
        if (i2 == -1) {
            i2 = this.f;
        }
        savedState.c = i2;
        Parcelable parcelable = this.k;
        if (parcelable != null) {
            savedState.d = parcelable;
        } else {
            Object adapter = this.l.getAdapter();
            if (adapter instanceof lf) {
                savedState.d = ((lf) adapter).a();
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
        this.i.D2(i2);
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

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public int b;
        public int c;
        public Parcelable d;

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
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            f(parcel, classLoader);
        }

        public final void f(Parcel parcel, ClassLoader classLoader) {
            this.b = parcel.readInt();
            this.c = parcel.readInt();
            this.d = parcel.readParcelable(classLoader);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
            parcel.writeParcelable(this.d, i);
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
        this.c = new Rect();
        this.d = new Rect();
        this.e = new nf(3);
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
        this.c = new Rect();
        this.d = new Rect();
        this.e = new nf(3);
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
