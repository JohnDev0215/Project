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
import b.g.k.t;
import b.i.a.c;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class SlidingPaneLayout extends ViewGroup {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f1837b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f1838c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Drawable f1839d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Drawable f1840e;
    private final int f;
    private boolean g;
    View h;
    float i;
    private float j;
    int k;
    boolean l;
    private int m;
    private float n;
    private float o;
    private e p;
    final b.i.a.c q;
    boolean r;
    private boolean s;
    private final Rect t;
    final ArrayList<b> u;
    private Method v;
    private Field w;
    private boolean x;

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f1841d;

        static class a implements Parcelable.ClassLoaderCreator<SavedState> {
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
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f1841d ? 1 : 0);
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f1841d = parcel.readInt() != 0;
        }
    }

    class a extends b.g.k.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final Rect f1842d = new Rect();

        a() {
        }

        private void n(b.g.k.c0.c cVar, b.g.k.c0.c cVar2) {
            Rect rect = this.f1842d;
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

        @Override // b.g.k.a
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            super.f(view, accessibilityEvent);
            accessibilityEvent.setClassName(SlidingPaneLayout.class.getName());
        }

        @Override // b.g.k.a
        public void g(View view, b.g.k.c0.c cVar) {
            b.g.k.c0.c cVarQ = b.g.k.c0.c.Q(cVar);
            super.g(view, cVarQ);
            n(cVar, cVarQ);
            cVarQ.S();
            cVar.c0(SlidingPaneLayout.class.getName());
            cVar.A0(view);
            Object objF = t.F(view);
            if (objF instanceof View) {
                cVar.s0((View) objF);
            }
            int childCount = SlidingPaneLayout.this.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = SlidingPaneLayout.this.getChildAt(i);
                if (!o(childAt) && childAt.getVisibility() == 0) {
                    t.u0(childAt, 1);
                    cVar.c(childAt);
                }
            }
        }

        @Override // b.g.k.a
        public boolean i(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            if (o(view)) {
                return false;
            }
            return super.i(viewGroup, view, accessibilityEvent);
        }

        public boolean o(View view) {
            return SlidingPaneLayout.this.h(view);
        }
    }

    private class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final View f1844b;

        b(View view) {
            this.f1844b = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f1844b.getParent() == SlidingPaneLayout.this) {
                this.f1844b.setLayerType(0, null);
                SlidingPaneLayout.this.g(this.f1844b);
            }
            SlidingPaneLayout.this.u.remove(this);
        }
    }

    private class c extends c.AbstractC0059c {
        c() {
        }

        @Override // b.i.a.c.AbstractC0059c
        public int a(View view, int i, int i2) {
            d dVar = (d) SlidingPaneLayout.this.h.getLayoutParams();
            if (SlidingPaneLayout.this.i()) {
                int width = SlidingPaneLayout.this.getWidth() - ((SlidingPaneLayout.this.getPaddingRight() + ((ViewGroup.MarginLayoutParams) dVar).rightMargin) + SlidingPaneLayout.this.h.getWidth());
                return Math.max(Math.min(i, width), width - SlidingPaneLayout.this.k);
            }
            int paddingLeft = SlidingPaneLayout.this.getPaddingLeft() + ((ViewGroup.MarginLayoutParams) dVar).leftMargin;
            return Math.min(Math.max(i, paddingLeft), SlidingPaneLayout.this.k + paddingLeft);
        }

        @Override // b.i.a.c.AbstractC0059c
        public int b(View view, int i, int i2) {
            return view.getTop();
        }

        @Override // b.i.a.c.AbstractC0059c
        public int d(View view) {
            return SlidingPaneLayout.this.k;
        }

        @Override // b.i.a.c.AbstractC0059c
        public void f(int i, int i2) {
            SlidingPaneLayout slidingPaneLayout = SlidingPaneLayout.this;
            slidingPaneLayout.q.c(slidingPaneLayout.h, i2);
        }

        @Override // b.i.a.c.AbstractC0059c
        public void i(View view, int i) {
            SlidingPaneLayout.this.p();
        }

        @Override // b.i.a.c.AbstractC0059c
        public void j(int i) {
            if (SlidingPaneLayout.this.q.A() == 0) {
                SlidingPaneLayout slidingPaneLayout = SlidingPaneLayout.this;
                if (slidingPaneLayout.i != 0.0f) {
                    slidingPaneLayout.e(slidingPaneLayout.h);
                    SlidingPaneLayout.this.r = true;
                } else {
                    slidingPaneLayout.r(slidingPaneLayout.h);
                    SlidingPaneLayout slidingPaneLayout2 = SlidingPaneLayout.this;
                    slidingPaneLayout2.d(slidingPaneLayout2.h);
                    SlidingPaneLayout.this.r = false;
                }
            }
        }

        @Override // b.i.a.c.AbstractC0059c
        public void k(View view, int i, int i2, int i3, int i4) {
            SlidingPaneLayout.this.l(i);
            SlidingPaneLayout.this.invalidate();
        }

        @Override // b.i.a.c.AbstractC0059c
        public void l(View view, float f, float f2) {
            int paddingLeft;
            d dVar = (d) view.getLayoutParams();
            if (SlidingPaneLayout.this.i()) {
                int paddingRight = SlidingPaneLayout.this.getPaddingRight() + ((ViewGroup.MarginLayoutParams) dVar).rightMargin;
                if (f < 0.0f || (f == 0.0f && SlidingPaneLayout.this.i > 0.5f)) {
                    paddingRight += SlidingPaneLayout.this.k;
                }
                paddingLeft = (SlidingPaneLayout.this.getWidth() - paddingRight) - SlidingPaneLayout.this.h.getWidth();
            } else {
                paddingLeft = ((ViewGroup.MarginLayoutParams) dVar).leftMargin + SlidingPaneLayout.this.getPaddingLeft();
                if (f > 0.0f || (f == 0.0f && SlidingPaneLayout.this.i > 0.5f)) {
                    paddingLeft += SlidingPaneLayout.this.k;
                }
            }
            SlidingPaneLayout.this.q.N(paddingLeft, view.getTop());
            SlidingPaneLayout.this.invalidate();
        }

        @Override // b.i.a.c.AbstractC0059c
        public boolean m(View view, int i) {
            if (SlidingPaneLayout.this.l) {
                return false;
            }
            return ((d) view.getLayoutParams()).f1849c;
        }
    }

    public interface e {
        void a(View view, float f);

        void b(View view);

        void c(View view);
    }

    public SlidingPaneLayout(Context context) {
        this(context, null);
    }

    private boolean b(View view, int i) {
        if (!this.s && !q(0.0f, i)) {
            return false;
        }
        this.r = false;
        return true;
    }

    private void c(View view, float f, int i) {
        d dVar = (d) view.getLayoutParams();
        if (f > 0.0f && i != 0) {
            int i2 = (((int) ((((-16777216) & i) >>> 24) * f)) << 24) | (i & 16777215);
            if (dVar.f1851e == null) {
                dVar.f1851e = new Paint();
            }
            dVar.f1851e.setColorFilter(new PorterDuffColorFilter(i2, PorterDuff.Mode.SRC_OVER));
            if (view.getLayerType() != 2) {
                view.setLayerType(2, dVar.f1851e);
            }
            g(view);
            return;
        }
        if (view.getLayerType() != 0) {
            Paint paint = dVar.f1851e;
            if (paint != null) {
                paint.setColorFilter(null);
            }
            b bVar = new b(view);
            this.u.add(bVar);
            t.c0(this, bVar);
        }
    }

    private boolean n(View view, int i) {
        if (!this.s && !q(1.0f, i)) {
            return false;
        }
        this.r = true;
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void o(float r10) {
        /*
            r9 = this;
            boolean r0 = r9.i()
            android.view.View r1 = r9.h
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            androidx.slidingpanelayout.widget.SlidingPaneLayout$d r1 = (androidx.slidingpanelayout.widget.SlidingPaneLayout.d) r1
            boolean r2 = r1.f1850d
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
            android.view.View r5 = r9.h
            if (r4 != r5) goto L2c
            goto L54
        L2c:
            float r5 = r9.j
            r6 = 1065353216(0x3f800000, float:1.0)
            float r5 = r6 - r5
            int r7 = r9.m
            float r8 = (float) r7
            float r5 = r5 * r8
            int r5 = (int) r5
            r9.j = r10
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
            float r5 = r9.j
            if (r0 == 0) goto L4d
            float r5 = r5 - r6
            goto L4f
        L4d:
            float r5 = r6 - r5
        L4f:
            int r6 = r9.f1838c
            r9.c(r4, r5, r6)
        L54:
            int r3 = r3 + 1
            goto L21
        L57:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.slidingpanelayout.widget.SlidingPaneLayout.o(float):void");
    }

    private static boolean s(View view) {
        Drawable background;
        if (view.isOpaque()) {
            return true;
        }
        return Build.VERSION.SDK_INT < 18 && (background = view.getBackground()) != null && background.getOpacity() == -1;
    }

    public boolean a() {
        return b(this.h, 0);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof d) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.q.n(true)) {
            if (this.g) {
                t.a0(this);
            } else {
                this.q.a();
            }
        }
    }

    void d(View view) {
        e eVar = this.p;
        if (eVar != null) {
            eVar.c(view);
        }
        sendAccessibilityEvent(32);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int i;
        int right;
        super.draw(canvas);
        Drawable drawable = i() ? this.f1840e : this.f1839d;
        View childAt = getChildCount() > 1 ? getChildAt(1) : null;
        if (childAt == null || drawable == null) {
            return;
        }
        int top = childAt.getTop();
        int bottom = childAt.getBottom();
        int intrinsicWidth = drawable.getIntrinsicWidth();
        if (i()) {
            right = childAt.getRight();
            i = intrinsicWidth + right;
        } else {
            int left = childAt.getLeft();
            int i2 = left - intrinsicWidth;
            i = left;
            right = i2;
        }
        drawable.setBounds(right, top, i, bottom);
        drawable.draw(canvas);
    }

    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View view, long j) {
        d dVar = (d) view.getLayoutParams();
        int iSave = canvas.save();
        if (this.g && !dVar.f1849c && this.h != null) {
            canvas.getClipBounds(this.t);
            if (i()) {
                Rect rect = this.t;
                rect.left = Math.max(rect.left, this.h.getRight());
            } else {
                Rect rect2 = this.t;
                rect2.right = Math.min(rect2.right, this.h.getLeft());
            }
            canvas.clipRect(this.t);
        }
        boolean zDrawChild = super.drawChild(canvas, view, j);
        canvas.restoreToCount(iSave);
        return zDrawChild;
    }

    void e(View view) {
        e eVar = this.p;
        if (eVar != null) {
            eVar.b(view);
        }
        sendAccessibilityEvent(32);
    }

    void f(View view) {
        e eVar = this.p;
        if (eVar != null) {
            eVar.a(view, this.i);
        }
    }

    void g(View view) {
        Field field;
        int i = Build.VERSION.SDK_INT;
        if (i >= 17) {
            t.w0(view, ((d) view.getLayoutParams()).f1851e);
            return;
        }
        if (i >= 16) {
            if (!this.x) {
                try {
                    this.v = View.class.getDeclaredMethod("getDisplayList", null);
                } catch (NoSuchMethodException e2) {
                    Log.e("SlidingPaneLayout", "Couldn't fetch getDisplayList method; dimming won't work right.", e2);
                }
                try {
                    Field declaredField = View.class.getDeclaredField("mRecreateDisplayList");
                    this.w = declaredField;
                    declaredField.setAccessible(true);
                } catch (NoSuchFieldException e3) {
                    Log.e("SlidingPaneLayout", "Couldn't fetch mRecreateDisplayList field; dimming will be slow.", e3);
                }
                this.x = true;
            }
            if (this.v == null || (field = this.w) == null) {
                view.invalidate();
                return;
            }
            try {
                field.setBoolean(view, true);
                this.v.invoke(view, null);
            } catch (Exception e4) {
                Log.e("SlidingPaneLayout", "Error refreshing display list state", e4);
            }
        }
        t.b0(this, view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new d();
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new d((ViewGroup.MarginLayoutParams) layoutParams) : new d(layoutParams);
    }

    public int getCoveredFadeColor() {
        return this.f1838c;
    }

    public int getParallaxDistance() {
        return this.m;
    }

    public int getSliderFadeColor() {
        return this.f1837b;
    }

    boolean h(View view) {
        if (view == null) {
            return false;
        }
        return this.g && ((d) view.getLayoutParams()).f1850d && this.i > 0.0f;
    }

    boolean i() {
        return t.z(this) == 1;
    }

    public boolean j() {
        return !this.g || this.i == 1.0f;
    }

    public boolean k() {
        return this.g;
    }

    void l(int i) {
        if (this.h == null) {
            this.i = 0.0f;
            return;
        }
        boolean zI = i();
        d dVar = (d) this.h.getLayoutParams();
        int width = this.h.getWidth();
        if (zI) {
            i = (getWidth() - i) - width;
        }
        float paddingRight = (i - ((zI ? getPaddingRight() : getPaddingLeft()) + (zI ? ((ViewGroup.MarginLayoutParams) dVar).rightMargin : ((ViewGroup.MarginLayoutParams) dVar).leftMargin))) / this.k;
        this.i = paddingRight;
        if (this.m != 0) {
            o(paddingRight);
        }
        if (dVar.f1850d) {
            c(this.h, this.i, this.f1837b);
        }
        f(this.h);
    }

    public boolean m() {
        return n(this.h, 0);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.s = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.s = true;
        int size = this.u.size();
        for (int i = 0; i < size; i++) {
            this.u.get(i).run();
        }
        this.u.clear();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z;
        View childAt;
        int actionMasked = motionEvent.getActionMasked();
        if (!this.g && actionMasked == 0 && getChildCount() > 1 && (childAt = getChildAt(1)) != null) {
            this.r = !this.q.E(childAt, (int) motionEvent.getX(), (int) motionEvent.getY());
        }
        if (!this.g || (this.l && actionMasked != 0)) {
            this.q.b();
            return super.onInterceptTouchEvent(motionEvent);
        }
        if (actionMasked == 3 || actionMasked == 1) {
            this.q.b();
            return false;
        }
        if (actionMasked == 0) {
            this.l = false;
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            this.n = x;
            this.o = y;
            if (this.q.E(this.h, (int) x, (int) y) && h(this.h)) {
                z = true;
            }
            return this.q.O(motionEvent) || z;
        }
        if (actionMasked == 2) {
            float x2 = motionEvent.getX();
            float y2 = motionEvent.getY();
            float fAbs = Math.abs(x2 - this.n);
            float fAbs2 = Math.abs(y2 - this.o);
            if (fAbs > this.q.z() && fAbs2 > fAbs) {
                this.q.b();
                this.l = true;
                return false;
            }
        }
        z = false;
        if (this.q.O(motionEvent)) {
            return true;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        boolean zI = i();
        if (zI) {
            this.q.L(2);
        } else {
            this.q.L(1);
        }
        int i9 = i3 - i;
        int paddingRight = zI ? getPaddingRight() : getPaddingLeft();
        int paddingLeft = zI ? getPaddingLeft() : getPaddingRight();
        int paddingTop = getPaddingTop();
        int childCount = getChildCount();
        if (this.s) {
            this.i = (this.g && this.r) ? 1.0f : 0.0f;
        }
        int i10 = paddingRight;
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            if (childAt.getVisibility() != 8) {
                d dVar = (d) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                if (dVar.f1849c) {
                    int i12 = i9 - paddingLeft;
                    int iMin = (Math.min(paddingRight, i12 - this.f) - i10) - (((ViewGroup.MarginLayoutParams) dVar).leftMargin + ((ViewGroup.MarginLayoutParams) dVar).rightMargin);
                    this.k = iMin;
                    int i13 = zI ? ((ViewGroup.MarginLayoutParams) dVar).rightMargin : ((ViewGroup.MarginLayoutParams) dVar).leftMargin;
                    dVar.f1850d = ((i10 + i13) + iMin) + (measuredWidth / 2) > i12;
                    int i14 = (int) (iMin * this.i);
                    i10 += i13 + i14;
                    this.i = i14 / iMin;
                    i5 = 0;
                } else if (!this.g || (i6 = this.m) == 0) {
                    i10 = paddingRight;
                    i5 = 0;
                } else {
                    i5 = (int) ((1.0f - this.i) * i6);
                    i10 = paddingRight;
                }
                if (zI) {
                    i8 = (i9 - i10) + i5;
                    i7 = i8 - measuredWidth;
                } else {
                    i7 = i10 - i5;
                    i8 = i7 + measuredWidth;
                }
                childAt.layout(i7, paddingTop, i8, childAt.getMeasuredHeight() + paddingTop);
                paddingRight += childAt.getWidth();
            }
        }
        if (this.s) {
            if (this.g) {
                if (this.m != 0) {
                    o(this.i);
                }
                if (((d) this.h.getLayoutParams()).f1850d) {
                    c(this.h, this.i, this.f1837b);
                }
            } else {
                for (int i15 = 0; i15 < childCount; i15++) {
                    c(getChildAt(i15), 0.0f, this.f1837b);
                }
            }
            r(this.h);
        }
        this.s = false;
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
    protected void onMeasure(int r21, int r22) {
        /*
            Method dump skipped, instruction units count: 565
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.slidingpanelayout.widget.SlidingPaneLayout.onMeasure(int, int):void");
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        if (savedState.f1841d) {
            m();
        } else {
            a();
        }
        this.r = savedState.f1841d;
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f1841d = k() ? j() : this.r;
        return savedState;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3) {
            this.s = true;
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.g) {
            return super.onTouchEvent(motionEvent);
        }
        this.q.F(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            this.n = x;
            this.o = y;
        } else if (actionMasked == 1 && h(this.h)) {
            float x2 = motionEvent.getX();
            float y2 = motionEvent.getY();
            float f = x2 - this.n;
            float f2 = y2 - this.o;
            int iZ = this.q.z();
            if ((f * f) + (f2 * f2) < iZ * iZ && this.q.E(this.h, (int) x2, (int) y2)) {
                b(this.h, 0);
            }
        }
        return true;
    }

    void p() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 4) {
                childAt.setVisibility(0);
            }
        }
    }

    boolean q(float f, int i) {
        int paddingLeft;
        if (!this.g) {
            return false;
        }
        boolean zI = i();
        d dVar = (d) this.h.getLayoutParams();
        if (zI) {
            paddingLeft = (int) (getWidth() - (((getPaddingRight() + ((ViewGroup.MarginLayoutParams) dVar).rightMargin) + (f * this.k)) + this.h.getWidth()));
        } else {
            paddingLeft = (int) (getPaddingLeft() + ((ViewGroup.MarginLayoutParams) dVar).leftMargin + (f * this.k));
        }
        b.i.a.c cVar = this.q;
        View view = this.h;
        if (!cVar.P(view, paddingLeft, view.getTop())) {
            return false;
        }
        p();
        t.a0(this);
        return true;
    }

    void r(View view) {
        int left;
        int right;
        int top;
        int bottom;
        View childAt;
        boolean z;
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
        int i = 0;
        while (i < childCount && (childAt = getChildAt(i)) != view2) {
            if (childAt.getVisibility() == 8) {
                z = zI;
            } else {
                z = zI;
                childAt.setVisibility((Math.max(zI ? paddingLeft : width, childAt.getLeft()) < left || Math.max(paddingTop, childAt.getTop()) < top || Math.min(zI ? width : paddingLeft, childAt.getRight()) > right || Math.min(height, childAt.getBottom()) > bottom) ? 0 : 4);
            }
            i++;
            view2 = view;
            zI = z;
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        super.requestChildFocus(view, view2);
        if (isInTouchMode() || this.g) {
            return;
        }
        this.r = view == this.h;
    }

    public void setCoveredFadeColor(int i) {
        this.f1838c = i;
    }

    public void setPanelSlideListener(e eVar) {
        this.p = eVar;
    }

    public void setParallaxDistance(int i) {
        this.m = i;
        requestLayout();
    }

    @Deprecated
    public void setShadowDrawable(Drawable drawable) {
        setShadowDrawableLeft(drawable);
    }

    public void setShadowDrawableLeft(Drawable drawable) {
        this.f1839d = drawable;
    }

    public void setShadowDrawableRight(Drawable drawable) {
        this.f1840e = drawable;
    }

    @Deprecated
    public void setShadowResource(int i) {
        setShadowDrawable(getResources().getDrawable(i));
    }

    public void setShadowResourceLeft(int i) {
        setShadowDrawableLeft(androidx.core.content.a.d(getContext(), i));
    }

    public void setShadowResourceRight(int i) {
        setShadowDrawableRight(androidx.core.content.a.d(getContext(), i));
    }

    public void setSliderFadeColor(int i) {
        this.f1837b = i;
    }

    public static class d extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final int[] f1847a = {R.attr.layout_weight};

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f1848b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f1849c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f1850d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        Paint f1851e;

        public d() {
            super(-1, -1);
            this.f1848b = 0.0f;
        }

        public d(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1848b = 0.0f;
        }

        public d(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1848b = 0.0f;
        }

        public d(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1848b = 0.0f;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f1847a);
            this.f1848b = typedArrayObtainStyledAttributes.getFloat(0, 0.0f);
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

    public SlidingPaneLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1837b = -858993460;
        this.s = true;
        this.t = new Rect();
        this.u = new ArrayList<>();
        float f = context.getResources().getDisplayMetrics().density;
        this.f = (int) ((32.0f * f) + 0.5f);
        setWillNotDraw(false);
        t.k0(this, new a());
        t.u0(this, 1);
        b.i.a.c cVarO = b.i.a.c.o(this, 0.5f, new c());
        this.q = cVarO;
        cVarO.M(f * 400.0f);
    }
}
