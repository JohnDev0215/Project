package androidx.viewpager2.widget;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
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
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.u;
import androidx.viewpager2.R$styleable;
import androidx.viewpager2.widget.c;
import defpackage.fn3;
import defpackage.in3;
import defpackage.j9;
import defpackage.om3;
import defpackage.ps2;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class ViewPager2 extends ViewGroup {
    public final Rect e;
    public final Rect f;
    public final androidx.viewpager2.widget.a g;
    public int h;
    public boolean i;
    public final a j;
    public f k;
    public int l;
    public Parcelable m;
    public k n;
    public j o;
    public androidx.viewpager2.widget.c p;
    public androidx.viewpager2.widget.a q;
    public j9 r;
    public androidx.viewpager2.widget.b s;
    public RecyclerView.j t;
    public boolean u;
    public boolean v;
    public int w;
    public h x;

    public class a extends e {
        public a() {
            super(0);
        }

        @Override // androidx.viewpager2.widget.ViewPager2.e, androidx.recyclerview.widget.RecyclerView.g
        public final void a() {
            ViewPager2 viewPager2 = ViewPager2.this;
            viewPager2.i = true;
            viewPager2.p.p = true;
        }
    }

    public class b extends g {
        public b() {
        }

        @Override // androidx.viewpager2.widget.ViewPager2.g
        public final void a(int i) {
            if (i == 0) {
                ViewPager2.this.e();
            }
        }

        @Override // androidx.viewpager2.widget.ViewPager2.g
        public final void c(int i) {
            ViewPager2 viewPager2 = ViewPager2.this;
            if (viewPager2.h != i) {
                viewPager2.h = i;
                viewPager2.x.b();
            }
        }
    }

    public class c extends g {
        public c() {
        }

        @Override // androidx.viewpager2.widget.ViewPager2.g
        public final void c(int i) {
            ViewPager2 viewPager2 = ViewPager2.this;
            viewPager2.clearFocus();
            if (viewPager2.hasFocus()) {
                viewPager2.n.requestFocus(2);
            }
        }
    }

    public abstract class d {
    }

    public static abstract class e extends RecyclerView.g {
        public e(int i) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public abstract void a();

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public final void b() {
            a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public final void c(int i, int i2, Object obj) {
            a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public final void d(int i, int i2) {
            a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public final void e(int i, int i2) {
            a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public final void f(int i, int i2) {
            a();
        }
    }

    public class f extends LinearLayoutManager {
        public f(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        public final void I0(RecyclerView.w wVar, int[] iArr) {
            ViewPager2 viewPager2 = ViewPager2.this;
            int offscreenPageLimit = viewPager2.getOffscreenPageLimit();
            if (offscreenPageLimit == -1) {
                super.I0(wVar, iArr);
                return;
            }
            int pageSize = viewPager2.getPageSize() * offscreenPageLimit;
            iArr[0] = pageSize;
            iArr[1] = pageSize;
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
        public final void Z(RecyclerView.s sVar, RecyclerView.w wVar, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.Z(sVar, wVar, accessibilityNodeInfoCompat);
            ViewPager2.this.x.getClass();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.m
        public final void b0(RecyclerView.s sVar, RecyclerView.w wVar, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            int iK;
            ViewPager2 viewPager2 = ViewPager2.this;
            int iK2 = 0;
            if (viewPager2.getOrientation() == 1) {
                viewPager2.k.getClass();
                iK = RecyclerView.m.K(view);
            } else {
                iK = 0;
            }
            if (viewPager2.getOrientation() == 0) {
                viewPager2.k.getClass();
                iK2 = RecyclerView.m.K(view);
            }
            accessibilityNodeInfoCompat.q(AccessibilityNodeInfoCompat.d.a(iK, 1, iK2, 1, false, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.m
        public final boolean n0(RecyclerView.s sVar, RecyclerView.w wVar, int i, Bundle bundle) {
            ViewPager2.this.x.getClass();
            return super.n0(sVar, wVar, i, bundle);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.m
        public final boolean s0(RecyclerView recyclerView, View view, Rect rect, boolean z, boolean z2) {
            return false;
        }
    }

    public class h extends d {
        public final a a = new a();
        public final b b = new b();
        public androidx.viewpager2.widget.d c;

        public class a implements androidx.core.view.accessibility.a {
            public a() {
            }

            @Override // androidx.core.view.accessibility.a
            public final boolean f(View view) {
                int currentItem = ((ViewPager2) view).getCurrentItem() + 1;
                ViewPager2 viewPager2 = ViewPager2.this;
                if (viewPager2.v) {
                    viewPager2.d(currentItem, true);
                }
                return true;
            }
        }

        public class b implements androidx.core.view.accessibility.a {
            public b() {
            }

            @Override // androidx.core.view.accessibility.a
            public final boolean f(View view) {
                int currentItem = ((ViewPager2) view).getCurrentItem() - 1;
                ViewPager2 viewPager2 = ViewPager2.this;
                if (viewPager2.v) {
                    viewPager2.d(currentItem, true);
                }
                return true;
            }
        }

        public h() {
        }

        public final void a(RecyclerView recyclerView) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.d.p(recyclerView, 2);
            this.c = new androidx.viewpager2.widget.d(this);
            ViewPager2 viewPager2 = ViewPager2.this;
            if (om3.d.b(viewPager2) == 0) {
                om3.d.p(viewPager2, 1);
            }
        }

        public final void b() {
            int iK;
            ViewPager2 viewPager2 = ViewPager2.this;
            int i = R.id.accessibilityActionPageLeft;
            om3.i(viewPager2, R.id.accessibilityActionPageLeft);
            om3.f(viewPager2, 0);
            om3.i(viewPager2, R.id.accessibilityActionPageRight);
            om3.f(viewPager2, 0);
            om3.i(viewPager2, R.id.accessibilityActionPageUp);
            om3.f(viewPager2, 0);
            om3.i(viewPager2, R.id.accessibilityActionPageDown);
            om3.f(viewPager2, 0);
            if (viewPager2.getAdapter() == null || (iK = viewPager2.getAdapter().k()) == 0 || !viewPager2.v) {
                return;
            }
            int orientation = viewPager2.getOrientation();
            b bVar = this.b;
            a aVar = this.a;
            if (orientation != 0) {
                if (viewPager2.h < iK - 1) {
                    om3.j(viewPager2, new AccessibilityNodeInfoCompat.a(R.id.accessibilityActionPageDown), null, aVar);
                }
                if (viewPager2.h > 0) {
                    om3.j(viewPager2, new AccessibilityNodeInfoCompat.a(R.id.accessibilityActionPageUp), null, bVar);
                    return;
                }
                return;
            }
            boolean z = viewPager2.k.E() == 1;
            int i2 = z ? 16908360 : 16908361;
            if (z) {
                i = 16908361;
            }
            if (viewPager2.h < iK - 1) {
                om3.j(viewPager2, new AccessibilityNodeInfoCompat.a(i2), null, aVar);
            }
            if (viewPager2.h > 0) {
                om3.j(viewPager2, new AccessibilityNodeInfoCompat.a(i), null, bVar);
            }
        }
    }

    public interface i {
    }

    public class j extends u {
        public j() {
        }

        @Override // androidx.recyclerview.widget.u, androidx.recyclerview.widget.a0
        public final View c(RecyclerView.m mVar) {
            if (((androidx.viewpager2.widget.c) ViewPager2.this.r.b).q) {
                return null;
            }
            return super.c(mVar);
        }
    }

    public class k extends RecyclerView {
        public k(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public final CharSequence getAccessibilityClassName() {
            ViewPager2.this.x.getClass();
            return super.getAccessibilityClassName();
        }

        @Override // android.view.View
        public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            ViewPager2 viewPager2 = ViewPager2.this;
            accessibilityEvent.setFromIndex(viewPager2.h);
            accessibilityEvent.setToIndex(viewPager2.h);
            accessibilityEvent.setSource(ViewPager2.this);
            accessibilityEvent.setClassName("androidx.viewpager.widget.ViewPager");
        }

        @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            return ViewPager2.this.v && super.onInterceptTouchEvent(motionEvent);
        }

        @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
        @SuppressLint({"ClickableViewAccessibility"})
        public final boolean onTouchEvent(MotionEvent motionEvent) {
            return ViewPager2.this.v && super.onTouchEvent(motionEvent);
        }
    }

    public static class l implements Runnable {
        public final int e;
        public final RecyclerView f;

        public l(int i, RecyclerView recyclerView) {
            this.e = i;
            this.f = recyclerView;
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.f.k0(this.e);
        }
    }

    public ViewPager2(Context context) {
        super(context);
        this.e = new Rect();
        this.f = new Rect();
        this.g = new androidx.viewpager2.widget.a();
        this.i = false;
        this.j = new a();
        this.l = -1;
        this.t = null;
        this.u = false;
        this.v = true;
        this.w = -1;
        a(context, null);
    }

    public final void a(Context context, AttributeSet attributeSet) {
        this.x = new h();
        k kVar = new k(context);
        this.n = kVar;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        kVar.setId(om3.e.a());
        this.n.setDescendantFocusability(131072);
        f fVar = new f(context);
        this.k = fVar;
        this.n.setLayoutManager(fVar);
        this.n.setScrollingTouchSlop(1);
        int[] iArr = R$styleable.ViewPager2;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr);
        om3.n.b(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, 0, 0);
        try {
            setOrientation(typedArrayObtainStyledAttributes.getInt(R$styleable.ViewPager2_android_orientation, 0));
            typedArrayObtainStyledAttributes.recycle();
            this.n.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            this.n.addOnChildAttachStateChangeListener(new fn3());
            androidx.viewpager2.widget.c cVar = new androidx.viewpager2.widget.c(this);
            this.p = cVar;
            this.r = new j9(this, cVar, this.n);
            j jVar = new j();
            this.o = jVar;
            jVar.a(this.n);
            this.n.addOnScrollListener(this.p);
            androidx.viewpager2.widget.a aVar = new androidx.viewpager2.widget.a();
            this.q = aVar;
            this.p.e = aVar;
            b bVar = new b();
            c cVar2 = new c();
            this.q.a.add(bVar);
            this.q.a.add(cVar2);
            this.x.a(this.n);
            this.q.a.add(this.g);
            androidx.viewpager2.widget.b bVar2 = new androidx.viewpager2.widget.b(this.k);
            this.s = bVar2;
            this.q.a.add(bVar2);
            k kVar2 = this.n;
            attachViewToParent(kVar2, 0, kVar2.getLayoutParams());
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void b() {
        RecyclerView.e adapter;
        if (this.l == -1 || (adapter = getAdapter()) == 0) {
            return;
        }
        Parcelable parcelable = this.m;
        if (parcelable != null) {
            if (adapter instanceof ps2) {
                ((ps2) adapter).c(parcelable);
            }
            this.m = null;
        }
        int iMax = Math.max(0, Math.min(this.l, adapter.k() - 1));
        this.h = iMax;
        this.l = -1;
        this.n.g0(iMax);
        this.x.b();
    }

    public final void c(int i2, boolean z) {
        if (((androidx.viewpager2.widget.c) this.r.b).q) {
            throw new IllegalStateException("Cannot change current item when ViewPager2 is fake dragging");
        }
        d(i2, z);
    }

    @Override // android.view.View
    public final boolean canScrollHorizontally(int i2) {
        return this.n.canScrollHorizontally(i2);
    }

    @Override // android.view.View
    public final boolean canScrollVertically(int i2) {
        return this.n.canScrollVertically(i2);
    }

    public final void d(int i2, boolean z) {
        g gVar;
        RecyclerView.e adapter = getAdapter();
        if (adapter == null) {
            if (this.l != -1) {
                this.l = Math.max(i2, 0);
                return;
            }
            return;
        }
        if (adapter.k() <= 0) {
            return;
        }
        int iMin = Math.min(Math.max(i2, 0), adapter.k() - 1);
        int i3 = this.h;
        if (iMin == i3) {
            if (this.p.j == 0) {
                return;
            }
        }
        if (iMin == i3 && z) {
            return;
        }
        double d2 = i3;
        this.h = iMin;
        this.x.b();
        androidx.viewpager2.widget.c cVar = this.p;
        if (!(cVar.j == 0)) {
            cVar.f();
            c.a aVar = cVar.k;
            d2 = ((double) aVar.a) + ((double) aVar.b);
        }
        androidx.viewpager2.widget.c cVar2 = this.p;
        cVar2.getClass();
        cVar2.i = z ? 2 : 3;
        cVar2.q = false;
        boolean z2 = cVar2.m != iMin;
        cVar2.m = iMin;
        cVar2.c(2);
        if (z2 && (gVar = cVar2.e) != null) {
            gVar.c(iMin);
        }
        if (!z) {
            this.n.g0(iMin);
            return;
        }
        double d3 = iMin;
        if (Math.abs(d3 - d2) <= 3.0d) {
            this.n.k0(iMin);
            return;
        }
        this.n.g0(d3 > d2 ? iMin - 3 : iMin + 3);
        k kVar = this.n;
        kVar.post(new l(iMin, kVar));
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        Parcelable parcelable = sparseArray.get(getId());
        if (parcelable instanceof SavedState) {
            int i2 = ((SavedState) parcelable).e;
            sparseArray.put(this.n.getId(), sparseArray.get(i2));
            sparseArray.remove(i2);
        }
        super.dispatchRestoreInstanceState(sparseArray);
        b();
    }

    public final void e() {
        j jVar = this.o;
        if (jVar == null) {
            throw new IllegalStateException("Design assumption violated.");
        }
        View viewC = jVar.c(this.k);
        if (viewC == null) {
            return;
        }
        this.k.getClass();
        int iK = RecyclerView.m.K(viewC);
        if (iK != this.h && getScrollState() == 0) {
            this.q.c(iK);
        }
        this.i = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        this.x.getClass();
        this.x.getClass();
        return "androidx.viewpager.widget.ViewPager";
    }

    public RecyclerView.e getAdapter() {
        return this.n.getAdapter();
    }

    public int getCurrentItem() {
        return this.h;
    }

    public int getItemDecorationCount() {
        return this.n.getItemDecorationCount();
    }

    public int getOffscreenPageLimit() {
        return this.w;
    }

    public int getOrientation() {
        return this.k.p == 1 ? 1 : 0;
    }

    public int getPageSize() {
        int height;
        int paddingBottom;
        k kVar = this.n;
        if (getOrientation() == 0) {
            height = kVar.getWidth() - kVar.getPaddingLeft();
            paddingBottom = kVar.getPaddingRight();
        } else {
            height = kVar.getHeight() - kVar.getPaddingTop();
            paddingBottom = kVar.getPaddingBottom();
        }
        return height - paddingBottom;
    }

    public int getScrollState() {
        return this.p.j;
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        int iK;
        int iK2;
        int iK3;
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        h hVar = this.x;
        hVar.getClass();
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = new AccessibilityNodeInfoCompat(accessibilityNodeInfo);
        ViewPager2 viewPager2 = ViewPager2.this;
        if (viewPager2.getAdapter() == null) {
            iK = 0;
            iK2 = 0;
        } else if (viewPager2.getOrientation() == 1) {
            iK = viewPager2.getAdapter().k();
            iK2 = 1;
        } else {
            iK2 = viewPager2.getAdapter().k();
            iK = 1;
        }
        accessibilityNodeInfoCompat.p(AccessibilityNodeInfoCompat.c.a(iK, iK2, 0));
        RecyclerView.e adapter = viewPager2.getAdapter();
        if (adapter == null || (iK3 = adapter.k()) == 0 || !viewPager2.v) {
            return;
        }
        if (viewPager2.h > 0) {
            accessibilityNodeInfoCompat.a(8192);
        }
        if (viewPager2.h < iK3 - 1) {
            accessibilityNodeInfoCompat.a(4096);
        }
        accessibilityNodeInfoCompat.s(true);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int measuredWidth = this.n.getMeasuredWidth();
        int measuredHeight = this.n.getMeasuredHeight();
        int paddingLeft = getPaddingLeft();
        Rect rect = this.e;
        rect.left = paddingLeft;
        rect.right = (i4 - i2) - getPaddingRight();
        rect.top = getPaddingTop();
        rect.bottom = (i5 - i3) - getPaddingBottom();
        Rect rect2 = this.f;
        Gravity.apply(8388659, measuredWidth, measuredHeight, rect, rect2);
        this.n.layout(rect2.left, rect2.top, rect2.right, rect2.bottom);
        if (this.i) {
            e();
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i2, int i3) {
        measureChild(this.n, i2, i3);
        int measuredWidth = this.n.getMeasuredWidth();
        int measuredHeight = this.n.getMeasuredHeight();
        int measuredState = this.n.getMeasuredState();
        int paddingRight = getPaddingRight() + getPaddingLeft() + measuredWidth;
        int paddingBottom = getPaddingBottom() + getPaddingTop() + measuredHeight;
        setMeasuredDimension(View.resolveSizeAndState(Math.max(paddingRight, getSuggestedMinimumWidth()), i2, measuredState), View.resolveSizeAndState(Math.max(paddingBottom, getSuggestedMinimumHeight()), i3, measuredState << 16));
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.l = savedState.f;
        this.m = savedState.g;
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.e = this.n.getId();
        int i2 = this.l;
        if (i2 == -1) {
            i2 = this.h;
        }
        savedState.f = i2;
        Parcelable parcelable = this.m;
        if (parcelable != null) {
            savedState.g = parcelable;
        } else {
            Object adapter = this.n.getAdapter();
            if (adapter instanceof ps2) {
                savedState.g = ((ps2) adapter).a();
            }
        }
        return savedState;
    }

    @Override // android.view.ViewGroup
    public final void onViewAdded(View view) {
        throw new IllegalStateException("ViewPager2 does not support direct child views");
    }

    @Override // android.view.View
    public final boolean performAccessibilityAction(int i2, Bundle bundle) {
        this.x.getClass();
        if (!(i2 == 8192 || i2 == 4096)) {
            return super.performAccessibilityAction(i2, bundle);
        }
        h hVar = this.x;
        hVar.getClass();
        if (!(i2 == 8192 || i2 == 4096)) {
            throw new IllegalStateException();
        }
        ViewPager2 viewPager2 = ViewPager2.this;
        int currentItem = i2 == 8192 ? viewPager2.getCurrentItem() - 1 : viewPager2.getCurrentItem() + 1;
        if (viewPager2.v) {
            viewPager2.d(currentItem, true);
        }
        return true;
    }

    public void setAdapter(RecyclerView.e eVar) {
        RecyclerView.e adapter = this.n.getAdapter();
        h hVar = this.x;
        if (adapter != null) {
            adapter.C(hVar.c);
        } else {
            hVar.getClass();
        }
        a aVar = this.j;
        if (adapter != null) {
            adapter.C(aVar);
        }
        this.n.setAdapter(eVar);
        this.h = 0;
        b();
        h hVar2 = this.x;
        hVar2.b();
        if (eVar != null) {
            eVar.A(hVar2.c);
        }
        if (eVar != null) {
            eVar.A(aVar);
        }
    }

    public void setCurrentItem(int i2) {
        c(i2, true);
    }

    @Override // android.view.View
    public void setLayoutDirection(int i2) {
        super.setLayoutDirection(i2);
        this.x.b();
    }

    public void setOffscreenPageLimit(int i2) {
        if (i2 < 1 && i2 != -1) {
            throw new IllegalArgumentException("Offscreen page limit must be OFFSCREEN_PAGE_LIMIT_DEFAULT or a number > 0");
        }
        this.w = i2;
        this.n.requestLayout();
    }

    public void setOrientation(int i2) {
        this.k.h1(i2);
        this.x.b();
    }

    public void setPageTransformer(i iVar) {
        if (iVar != null) {
            if (!this.u) {
                this.t = this.n.getItemAnimator();
                this.u = true;
            }
            this.n.setItemAnimator(null);
        } else if (this.u) {
            this.n.setItemAnimator(this.t);
            this.t = null;
            this.u = false;
        }
        this.s.getClass();
        if (iVar == null) {
            return;
        }
        this.s.getClass();
        this.s.getClass();
    }

    public void setUserInputEnabled(boolean z) {
        this.v = z;
        this.x.b();
    }

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public int e;
        public int f;
        public Parcelable g;

        public class a implements Parcelable.ClassLoaderCreator<SavedState> {
            @Override // android.os.Parcelable.Creator
            public final Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            public final Object[] newArray(int i) {
                return new SavedState[i];
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            public final SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }
        }

        public SavedState() {
            throw null;
        }

        @SuppressLint({"ClassVerificationFailure"})
        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.e = parcel.readInt();
            this.f = parcel.readInt();
            this.g = parcel.readParcelable(classLoader);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.e);
            parcel.writeInt(this.f);
            parcel.writeParcelable(this.g, i);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public ViewPager2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new Rect();
        this.f = new Rect();
        this.g = new androidx.viewpager2.widget.a();
        this.i = false;
        this.j = new a();
        this.l = -1;
        this.t = null;
        this.u = false;
        this.v = true;
        this.w = -1;
        a(context, attributeSet);
    }

    public static abstract class g {
        public void a(int i) {
        }

        public void c(int i) {
        }

        public void b(int i, int i2, float f) {
        }
    }

    public ViewPager2(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.e = new Rect();
        this.f = new Rect();
        this.g = new androidx.viewpager2.widget.a();
        this.i = false;
        this.j = new a();
        this.l = -1;
        this.t = null;
        this.u = false;
        this.v = true;
        this.w = -1;
        a(context, attributeSet);
    }
}
