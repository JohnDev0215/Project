package androidx.slidingpanelayout.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.customview.view.AbsSavedState;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import o0.t;
import t0.c;

/* JADX INFO: loaded from: classes.dex */
public class SlidingPaneLayout extends ViewGroup {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1770d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1771e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Drawable f1772f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Drawable f1773g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f1774h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1775i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public View f1776j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f1777k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f1778l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f1779m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f1780n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f1781o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f1782p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f1783q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public e f1784r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final t0.c f1785s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f1786t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f1787u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final Rect f1788v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final ArrayList<b> f1789w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public Method f1790x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public Field f1791y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f1792z;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f1793f;

        public static class a implements Parcelable.ClassLoaderCreator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i8) {
                return new SavedState[i8];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            super.writeToParcel(parcel, i8);
            parcel.writeInt(this.f1793f ? 1 : 0);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f1793f = parcel.readInt() != 0;
        }
    }

    public class a extends o0.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Rect f1794d = new Rect();

        public a() {
        }

        @Override // o0.a
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            super.f(view, accessibilityEvent);
            accessibilityEvent.setClassName(SlidingPaneLayout.class.getName());
        }

        @Override // o0.a
        public void g(View view, p0.c cVar) {
            p0.c cVarQ = p0.c.Q(cVar);
            super.g(view, cVarQ);
            n(cVar, cVarQ);
            cVarQ.S();
            cVar.c0(SlidingPaneLayout.class.getName());
            cVar.A0(view);
            Object objG = t.G(view);
            if (objG instanceof View) {
                cVar.s0((View) objG);
            }
            int childCount = SlidingPaneLayout.this.getChildCount();
            for (int i8 = 0; i8 < childCount; i8++) {
                View childAt = SlidingPaneLayout.this.getChildAt(i8);
                if (!o(childAt) && childAt.getVisibility() == 0) {
                    t.v0(childAt, 1);
                    cVar.c(childAt);
                }
            }
        }

        @Override // o0.a
        public boolean i(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            if (o(view)) {
                return false;
            }
            return super.i(viewGroup, view, accessibilityEvent);
        }

        public final void n(p0.c cVar, p0.c cVar2) {
            Rect rect = this.f1794d;
            cVar2.m(rect);
            cVar.X(rect);
            cVar2.n(rect);
            cVar.Y(rect);
            cVar.D0(cVar2.N());
            cVar.q0(cVar2.v());
            cVar.c0(cVar2.p());
            cVar.g0(cVar2.r());
            cVar.h0(cVar2.F());
            cVar.d0(cVar2.E());
            cVar.j0(cVar2.G());
            cVar.k0(cVar2.H());
            cVar.V(cVar2.B());
            cVar.y0(cVar2.L());
            cVar.n0(cVar2.I());
            cVar.a(cVar2.k());
            cVar.p0(cVar2.t());
        }

        public boolean o(View view) {
            return SlidingPaneLayout.this.h(view);
        }
    }

    public class b implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final View f1796d;

        public b(View view) {
            this.f1796d = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f1796d.getParent() == SlidingPaneLayout.this) {
                this.f1796d.setLayerType(0, null);
                SlidingPaneLayout.this.g(this.f1796d);
            }
            SlidingPaneLayout.this.f1789w.remove(this);
        }
    }

    public class c extends c.AbstractC0123c {
        public c() {
        }

        @Override // t0.c.AbstractC0123c
        public int a(View view, int i8, int i9) {
            d dVar = (d) SlidingPaneLayout.this.f1776j.getLayoutParams();
            if (SlidingPaneLayout.this.i()) {
                int width = SlidingPaneLayout.this.getWidth() - ((SlidingPaneLayout.this.getPaddingRight() + ((ViewGroup.MarginLayoutParams) dVar).rightMargin) + SlidingPaneLayout.this.f1776j.getWidth());
                return Math.max(Math.min(i8, width), width - SlidingPaneLayout.this.f1779m);
            }
            int paddingLeft = SlidingPaneLayout.this.getPaddingLeft() + ((ViewGroup.MarginLayoutParams) dVar).leftMargin;
            return Math.min(Math.max(i8, paddingLeft), SlidingPaneLayout.this.f1779m + paddingLeft);
        }

        @Override // t0.c.AbstractC0123c
        public int b(View view, int i8, int i9) {
            return view.getTop();
        }

        @Override // t0.c.AbstractC0123c
        public int d(View view) {
            return SlidingPaneLayout.this.f1779m;
        }

        @Override // t0.c.AbstractC0123c
        public void f(int i8, int i9) {
            SlidingPaneLayout slidingPaneLayout = SlidingPaneLayout.this;
            slidingPaneLayout.f1785s.c(slidingPaneLayout.f1776j, i9);
        }

        @Override // t0.c.AbstractC0123c
        public void i(View view, int i8) {
            SlidingPaneLayout.this.p();
        }

        @Override // t0.c.AbstractC0123c
        public void j(int i8) {
            if (SlidingPaneLayout.this.f1785s.A() == 0) {
                SlidingPaneLayout slidingPaneLayout = SlidingPaneLayout.this;
                if (slidingPaneLayout.f1777k != 0.0f) {
                    slidingPaneLayout.e(slidingPaneLayout.f1776j);
                    SlidingPaneLayout.this.f1786t = true;
                } else {
                    slidingPaneLayout.r(slidingPaneLayout.f1776j);
                    SlidingPaneLayout slidingPaneLayout2 = SlidingPaneLayout.this;
                    slidingPaneLayout2.d(slidingPaneLayout2.f1776j);
                    SlidingPaneLayout.this.f1786t = false;
                }
            }
        }

        @Override // t0.c.AbstractC0123c
        public void k(View view, int i8, int i9, int i10, int i11) {
            SlidingPaneLayout.this.l(i8);
            SlidingPaneLayout.this.invalidate();
        }

        @Override // t0.c.AbstractC0123c
        public void l(View view, float f8, float f9) {
            int paddingLeft;
            d dVar = (d) view.getLayoutParams();
            if (SlidingPaneLayout.this.i()) {
                int paddingRight = SlidingPaneLayout.this.getPaddingRight() + ((ViewGroup.MarginLayoutParams) dVar).rightMargin;
                if (f8 < 0.0f || (f8 == 0.0f && SlidingPaneLayout.this.f1777k > 0.5f)) {
                    paddingRight += SlidingPaneLayout.this.f1779m;
                }
                paddingLeft = (SlidingPaneLayout.this.getWidth() - paddingRight) - SlidingPaneLayout.this.f1776j.getWidth();
            } else {
                paddingLeft = ((ViewGroup.MarginLayoutParams) dVar).leftMargin + SlidingPaneLayout.this.getPaddingLeft();
                if (f8 > 0.0f || (f8 == 0.0f && SlidingPaneLayout.this.f1777k > 0.5f)) {
                    paddingLeft += SlidingPaneLayout.this.f1779m;
                }
            }
            SlidingPaneLayout.this.f1785s.N(paddingLeft, view.getTop());
            SlidingPaneLayout.this.invalidate();
        }

        @Override // t0.c.AbstractC0123c
        public boolean m(View view, int i8) {
            if (SlidingPaneLayout.this.f1780n) {
                return false;
            }
            return ((d) view.getLayoutParams()).f1801c;
        }
    }

    public interface e {
        void a(View view, float f8);

        void b(View view);

        void c(View view);
    }

    public SlidingPaneLayout(Context context) {
        this(context, null);
    }

    public static boolean s(View view) {
        Drawable background;
        if (view.isOpaque()) {
            return true;
        }
        return Build.VERSION.SDK_INT < 18 && (background = view.getBackground()) != null && background.getOpacity() == -1;
    }

    public boolean a() {
        return b(this.f1776j, 0);
    }

    public final boolean b(View view, int i8) {
        if (!this.f1787u && !q(0.0f, i8)) {
            return false;
        }
        this.f1786t = false;
        return true;
    }

    public final void c(View view, float f8, int i8) {
        d dVar = (d) view.getLayoutParams();
        if (f8 > 0.0f && i8 != 0) {
            int i9 = (((int) ((((-16777216) & i8) >>> 24) * f8)) << 24) | (i8 & 16777215);
            if (dVar.f1803e == null) {
                dVar.f1803e = new Paint();
            }
            dVar.f1803e.setColorFilter(new PorterDuffColorFilter(i9, PorterDuff.Mode.SRC_OVER));
            if (view.getLayerType() != 2) {
                view.setLayerType(2, dVar.f1803e);
            }
            g(view);
            return;
        }
        if (view.getLayerType() != 0) {
            Paint paint = dVar.f1803e;
            if (paint != null) {
                paint.setColorFilter(null);
            }
            b bVar = new b(view);
            this.f1789w.add(bVar);
            t.d0(this, bVar);
        }
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof d) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.f1785s.n(true)) {
            if (this.f1775i) {
                t.b0(this);
            } else {
                this.f1785s.a();
            }
        }
    }

    public void d(View view) {
        e eVar = this.f1784r;
        if (eVar != null) {
            eVar.c(view);
        }
        sendAccessibilityEvent(32);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int i8;
        int right;
        super.draw(canvas);
        Drawable drawable = i() ? this.f1773g : this.f1772f;
        View childAt = getChildCount() > 1 ? getChildAt(1) : null;
        if (childAt == null || drawable == null) {
            return;
        }
        int top = childAt.getTop();
        int bottom = childAt.getBottom();
        int intrinsicWidth = drawable.getIntrinsicWidth();
        if (i()) {
            right = childAt.getRight();
            i8 = intrinsicWidth + right;
        } else {
            int left = childAt.getLeft();
            int i9 = left - intrinsicWidth;
            i8 = left;
            right = i9;
        }
        drawable.setBounds(right, top, i8, bottom);
        drawable.draw(canvas);
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j8) {
        d dVar = (d) view.getLayoutParams();
        int iSave = canvas.save();
        if (this.f1775i && !dVar.f1801c && this.f1776j != null) {
            canvas.getClipBounds(this.f1788v);
            if (i()) {
                Rect rect = this.f1788v;
                rect.left = Math.max(rect.left, this.f1776j.getRight());
            } else {
                Rect rect2 = this.f1788v;
                rect2.right = Math.min(rect2.right, this.f1776j.getLeft());
            }
            canvas.clipRect(this.f1788v);
        }
        boolean zDrawChild = super.drawChild(canvas, view, j8);
        canvas.restoreToCount(iSave);
        return zDrawChild;
    }

    public void e(View view) {
        e eVar = this.f1784r;
        if (eVar != null) {
            eVar.b(view);
        }
        sendAccessibilityEvent(32);
    }

    public void f(View view) {
        e eVar = this.f1784r;
        if (eVar != null) {
            eVar.a(view, this.f1777k);
        }
    }

    public void g(View view) {
        Field field;
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 17) {
            t.x0(view, ((d) view.getLayoutParams()).f1803e);
            return;
        }
        if (i8 >= 16) {
            if (!this.f1792z) {
                try {
                    this.f1790x = View.class.getDeclaredMethod("getDisplayList", null);
                } catch (NoSuchMethodException e8) {
                    Log.e("SlidingPaneLayout", "Couldn't fetch getDisplayList method; dimming won't work right.", e8);
                }
                try {
                    Field declaredField = View.class.getDeclaredField("mRecreateDisplayList");
                    this.f1791y = declaredField;
                    declaredField.setAccessible(true);
                } catch (NoSuchFieldException e9) {
                    Log.e("SlidingPaneLayout", "Couldn't fetch mRecreateDisplayList field; dimming will be slow.", e9);
                }
                this.f1792z = true;
            }
            if (this.f1790x == null || (field = this.f1791y) == null) {
                view.invalidate();
                return;
            }
            try {
                field.setBoolean(view, true);
                this.f1790x.invoke(view, null);
            } catch (Exception e10) {
                Log.e("SlidingPaneLayout", "Error refreshing display list state", e10);
            }
        }
        t.c0(this, view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new d();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new d((ViewGroup.MarginLayoutParams) layoutParams) : new d(layoutParams);
    }

    public int getCoveredFadeColor() {
        return this.f1771e;
    }

    public int getParallaxDistance() {
        return this.f1781o;
    }

    public int getSliderFadeColor() {
        return this.f1770d;
    }

    public boolean h(View view) {
        if (view == null) {
            return false;
        }
        return this.f1775i && ((d) view.getLayoutParams()).f1802d && this.f1777k > 0.0f;
    }

    public boolean i() {
        return t.A(this) == 1;
    }

    public boolean j() {
        return !this.f1775i || this.f1777k == 1.0f;
    }

    public boolean k() {
        return this.f1775i;
    }

    public void l(int i8) {
        if (this.f1776j == null) {
            this.f1777k = 0.0f;
            return;
        }
        boolean zI = i();
        d dVar = (d) this.f1776j.getLayoutParams();
        int width = this.f1776j.getWidth();
        if (zI) {
            i8 = (getWidth() - i8) - width;
        }
        float paddingRight = (i8 - ((zI ? getPaddingRight() : getPaddingLeft()) + (zI ? ((ViewGroup.MarginLayoutParams) dVar).rightMargin : ((ViewGroup.MarginLayoutParams) dVar).leftMargin))) / this.f1779m;
        this.f1777k = paddingRight;
        if (this.f1781o != 0) {
            o(paddingRight);
        }
        if (dVar.f1802d) {
            c(this.f1776j, this.f1777k, this.f1770d);
        }
        f(this.f1776j);
    }

    public boolean m() {
        return n(this.f1776j, 0);
    }

    public final boolean n(View view, int i8) {
        if (!this.f1787u && !q(1.0f, i8)) {
            return false;
        }
        this.f1786t = true;
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void o(float r10) {
        /*
            r9 = this;
            boolean r0 = r9.i()
            android.view.View r1 = r9.f1776j
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            androidx.slidingpanelayout.widget.SlidingPaneLayout$d r1 = (androidx.slidingpanelayout.widget.SlidingPaneLayout.d) r1
            boolean r2 = r1.f1802d
            r3 = 0
            if (r2 == 0) goto L1c
            if (r0 == 0) goto L16
            int r1 = r1.rightMargin
            goto L18
        L16:
            int r1 = r1.leftMargin
        L18:
            if (r1 > 0) goto L1c
            r1 = 1
            goto L1d
        L1c:
            r1 = r3
        L1d:
            int r2 = r9.getChildCount()
        L21:
            if (r3 >= r2) goto L57
            android.view.View r4 = r9.getChildAt(r3)
            android.view.View r5 = r9.f1776j
            if (r4 != r5) goto L2c
            goto L54
        L2c:
            float r5 = r9.f1778l
            r6 = 1065353216(0x3f800000, float:1.0)
            float r5 = r6 - r5
            int r7 = r9.f1781o
            float r8 = (float) r7
            float r5 = r5 * r8
            int r5 = (int) r5
            r9.f1778l = r10
            float r8 = r6 - r10
            float r7 = (float) r7
            float r8 = r8 * r7
            int r7 = (int) r8
            int r5 = r5 - r7
            if (r0 == 0) goto L42
            int r5 = -r5
        L42:
            r4.offsetLeftAndRight(r5)
            if (r1 == 0) goto L54
            float r5 = r9.f1778l
            if (r0 == 0) goto L4d
            float r5 = r5 - r6
            goto L4f
        L4d:
            float r5 = r6 - r5
        L4f:
            int r6 = r9.f1771e
            r9.c(r4, r5, r6)
        L54:
            int r3 = r3 + 1
            goto L21
        L57:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.slidingpanelayout.widget.SlidingPaneLayout.o(float):void");
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f1787u = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f1787u = true;
        int size = this.f1789w.size();
        for (int i8 = 0; i8 < size; i8++) {
            this.f1789w.get(i8).run();
        }
        this.f1789w.clear();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z7;
        View childAt;
        int actionMasked = motionEvent.getActionMasked();
        if (!this.f1775i && actionMasked == 0 && getChildCount() > 1 && (childAt = getChildAt(1)) != null) {
            this.f1786t = !this.f1785s.E(childAt, (int) motionEvent.getX(), (int) motionEvent.getY());
        }
        if (!this.f1775i || (this.f1780n && actionMasked != 0)) {
            this.f1785s.b();
            return super.onInterceptTouchEvent(motionEvent);
        }
        if (actionMasked == 3 || actionMasked == 1) {
            this.f1785s.b();
            return false;
        }
        if (actionMasked == 0) {
            this.f1780n = false;
            float x7 = motionEvent.getX();
            float y7 = motionEvent.getY();
            this.f1782p = x7;
            this.f1783q = y7;
            if (this.f1785s.E(this.f1776j, (int) x7, (int) y7) && h(this.f1776j)) {
                z7 = true;
            }
            return this.f1785s.O(motionEvent) || z7;
        }
        if (actionMasked == 2) {
            float x8 = motionEvent.getX();
            float y8 = motionEvent.getY();
            float fAbs = Math.abs(x8 - this.f1782p);
            float fAbs2 = Math.abs(y8 - this.f1783q);
            if (fAbs > this.f1785s.z() && fAbs2 > fAbs) {
                this.f1785s.b();
                this.f1780n = true;
                return false;
            }
        }
        z7 = false;
        if (this.f1785s.O(motionEvent)) {
            return true;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        int i12;
        int i13;
        int i14;
        int i15;
        boolean zI = i();
        if (zI) {
            this.f1785s.L(2);
        } else {
            this.f1785s.L(1);
        }
        int i16 = i10 - i8;
        int paddingRight = zI ? getPaddingRight() : getPaddingLeft();
        int paddingLeft = zI ? getPaddingLeft() : getPaddingRight();
        int paddingTop = getPaddingTop();
        int childCount = getChildCount();
        if (this.f1787u) {
            this.f1777k = (this.f1775i && this.f1786t) ? 1.0f : 0.0f;
        }
        int i17 = paddingRight;
        for (int i18 = 0; i18 < childCount; i18++) {
            View childAt = getChildAt(i18);
            if (childAt.getVisibility() != 8) {
                d dVar = (d) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                if (dVar.f1801c) {
                    int i19 = i16 - paddingLeft;
                    int iMin = (Math.min(paddingRight, i19 - this.f1774h) - i17) - (((ViewGroup.MarginLayoutParams) dVar).leftMargin + ((ViewGroup.MarginLayoutParams) dVar).rightMargin);
                    this.f1779m = iMin;
                    int i20 = zI ? ((ViewGroup.MarginLayoutParams) dVar).rightMargin : ((ViewGroup.MarginLayoutParams) dVar).leftMargin;
                    dVar.f1802d = ((i17 + i20) + iMin) + (measuredWidth / 2) > i19;
                    int i21 = (int) (iMin * this.f1777k);
                    i17 += i20 + i21;
                    this.f1777k = i21 / iMin;
                    i12 = 0;
                } else if (!this.f1775i || (i13 = this.f1781o) == 0) {
                    i17 = paddingRight;
                    i12 = 0;
                } else {
                    i12 = (int) ((1.0f - this.f1777k) * i13);
                    i17 = paddingRight;
                }
                if (zI) {
                    i15 = (i16 - i17) + i12;
                    i14 = i15 - measuredWidth;
                } else {
                    i14 = i17 - i12;
                    i15 = i14 + measuredWidth;
                }
                childAt.layout(i14, paddingTop, i15, childAt.getMeasuredHeight() + paddingTop);
                paddingRight += childAt.getWidth();
            }
        }
        if (this.f1787u) {
            if (this.f1775i) {
                if (this.f1781o != 0) {
                    o(this.f1777k);
                }
                if (((d) this.f1776j.getLayoutParams()).f1802d) {
                    c(this.f1776j, this.f1777k, this.f1770d);
                }
            } else {
                for (int i22 = 0; i22 < childCount; i22++) {
                    c(getChildAt(i22), 0.0f, this.f1770d);
                }
            }
            r(this.f1776j);
        }
        this.f1787u = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00ad A[PHI: r13
  0x00ad: PHI (r13v2 float) = (r13v1 float), (r13v8 float) binds: [B:36:0x00a4, B:38:0x00a9] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x013a  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onMeasure(int r21, int r22) {
        /*
            Method dump skipped, instruction units count: 565
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.slidingpanelayout.widget.SlidingPaneLayout.onMeasure(int, int):void");
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.f());
        if (savedState.f1793f) {
            m();
        } else {
            a();
        }
        this.f1786t = savedState.f1793f;
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f1793f = k() ? j() : this.f1786t;
        return savedState;
    }

    @Override // android.view.View
    public void onSizeChanged(int i8, int i9, int i10, int i11) {
        super.onSizeChanged(i8, i9, i10, i11);
        if (i8 != i10) {
            this.f1787u = true;
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.f1775i) {
            return super.onTouchEvent(motionEvent);
        }
        this.f1785s.F(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            float x7 = motionEvent.getX();
            float y7 = motionEvent.getY();
            this.f1782p = x7;
            this.f1783q = y7;
        } else if (actionMasked == 1 && h(this.f1776j)) {
            float x8 = motionEvent.getX();
            float y8 = motionEvent.getY();
            float f8 = x8 - this.f1782p;
            float f9 = y8 - this.f1783q;
            int iZ = this.f1785s.z();
            if ((f8 * f8) + (f9 * f9) < iZ * iZ && this.f1785s.E(this.f1776j, (int) x8, (int) y8)) {
                b(this.f1776j, 0);
            }
        }
        return true;
    }

    public void p() {
        int childCount = getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() == 4) {
                childAt.setVisibility(0);
            }
        }
    }

    public boolean q(float f8, int i8) {
        int paddingLeft;
        if (!this.f1775i) {
            return false;
        }
        boolean zI = i();
        d dVar = (d) this.f1776j.getLayoutParams();
        if (zI) {
            paddingLeft = (int) (getWidth() - (((getPaddingRight() + ((ViewGroup.MarginLayoutParams) dVar).rightMargin) + (f8 * this.f1779m)) + this.f1776j.getWidth()));
        } else {
            paddingLeft = (int) (getPaddingLeft() + ((ViewGroup.MarginLayoutParams) dVar).leftMargin + (f8 * this.f1779m));
        }
        t0.c cVar = this.f1785s;
        View view = this.f1776j;
        if (!cVar.P(view, paddingLeft, view.getTop())) {
            return false;
        }
        p();
        t.b0(this);
        return true;
    }

    public void r(View view) {
        int left;
        int right;
        int top;
        int bottom;
        View childAt;
        boolean z7;
        View view2 = view;
        boolean zI = i();
        int width = zI ? getWidth() - getPaddingRight() : getPaddingLeft();
        int paddingLeft = zI ? getPaddingLeft() : getWidth() - getPaddingRight();
        int paddingTop = getPaddingTop();
        int height = getHeight() - getPaddingBottom();
        if (view2 == null || !s(view)) {
            left = 0;
            right = 0;
            top = 0;
            bottom = 0;
        } else {
            left = view.getLeft();
            right = view.getRight();
            top = view.getTop();
            bottom = view.getBottom();
        }
        int childCount = getChildCount();
        int i8 = 0;
        while (i8 < childCount && (childAt = getChildAt(i8)) != view2) {
            if (childAt.getVisibility() == 8) {
                z7 = zI;
            } else {
                z7 = zI;
                childAt.setVisibility((Math.max(zI ? paddingLeft : width, childAt.getLeft()) < left || Math.max(paddingTop, childAt.getTop()) < top || Math.min(zI ? width : paddingLeft, childAt.getRight()) > right || Math.min(height, childAt.getBottom()) > bottom) ? 0 : 4);
            }
            i8++;
            view2 = view;
            zI = z7;
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        super.requestChildFocus(view, view2);
        if (isInTouchMode() || this.f1775i) {
            return;
        }
        this.f1786t = view == this.f1776j;
    }

    public void setCoveredFadeColor(int i8) {
        this.f1771e = i8;
    }

    public void setPanelSlideListener(e eVar) {
        this.f1784r = eVar;
    }

    public void setParallaxDistance(int i8) {
        this.f1781o = i8;
        requestLayout();
    }

    @Deprecated
    public void setShadowDrawable(Drawable drawable) {
        setShadowDrawableLeft(drawable);
    }

    public void setShadowDrawableLeft(Drawable drawable) {
        this.f1772f = drawable;
    }

    public void setShadowDrawableRight(Drawable drawable) {
        this.f1773g = drawable;
    }

    @Deprecated
    public void setShadowResource(int i8) {
        setShadowDrawable(getResources().getDrawable(i8));
    }

    public void setShadowResourceLeft(int i8) {
        setShadowDrawableLeft(e0.a.d(getContext(), i8));
    }

    public void setShadowResourceRight(int i8) {
        setShadowDrawableRight(e0.a.d(getContext(), i8));
    }

    public void setSliderFadeColor(int i8) {
        this.f1770d = i8;
    }

    public static class d extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final int[] f1799a = {R.attr.layout_weight};

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f1800b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1801c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1802d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Paint f1803e;

        public d() {
            super(-1, -1);
            this.f1800b = 0.0f;
        }

        public d(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1800b = 0.0f;
        }

        public d(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1800b = 0.0f;
        }

        public d(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1800b = 0.0f;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f1799a);
            this.f1800b = typedArrayObtainStyledAttributes.getFloat(0, 0.0f);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public SlidingPaneLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new d(getContext(), attributeSet);
    }

    public SlidingPaneLayout(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f1770d = -858993460;
        this.f1787u = true;
        this.f1788v = new Rect();
        this.f1789w = new ArrayList<>();
        float f8 = context.getResources().getDisplayMetrics().density;
        this.f1774h = (int) ((32.0f * f8) + 0.5f);
        setWillNotDraw(false);
        t.l0(this, new a());
        t.v0(this, 1);
        t0.c cVarO = t0.c.o(this, 0.5f, new c());
        this.f1785s = cVarO;
        cVarO.M(f8 * 400.0f);
    }
}
