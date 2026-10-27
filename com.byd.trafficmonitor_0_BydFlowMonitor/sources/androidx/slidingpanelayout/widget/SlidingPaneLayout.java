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
import b.h.m.t;
import b.j.a.c;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class SlidingPaneLayout extends ViewGroup {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1312b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1313c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Drawable f1314d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Drawable f1315e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f1316f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1317g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public View f1318h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f1319i;
    public float j;
    public int k;
    public boolean l;
    public int m;
    public float n;
    public float o;
    public e p;
    public final b.j.a.c q;
    public boolean r;
    public boolean s;
    public final Rect t;
    public final ArrayList<b> u;
    public Method v;
    public Field w;
    public boolean x;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1320d;

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
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f1320d ? 1 : 0);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f1320d = parcel.readInt() != 0;
        }
    }

    public class a extends b.h.m.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Rect f1321d = new Rect();

        public a() {
        }

        @Override // b.h.m.a
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            super.f(view, accessibilityEvent);
            accessibilityEvent.setClassName(SlidingPaneLayout.class.getName());
        }

        @Override // b.h.m.a
        public void g(View view, b.h.m.c0.c cVar) {
            b.h.m.c0.c cVarQ = b.h.m.c0.c.Q(cVar);
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
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = SlidingPaneLayout.this.getChildAt(i2);
                if (!o(childAt) && childAt.getVisibility() == 0) {
                    t.u0(childAt, 1);
                    cVar.c(childAt);
                }
            }
        }

        @Override // b.h.m.a
        public boolean i(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            if (o(view)) {
                return false;
            }
            return super.i(viewGroup, view, accessibilityEvent);
        }

        public final void n(b.h.m.c0.c cVar, b.h.m.c0.c cVar2) {
            Rect rect = this.f1321d;
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

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final View f1323b;

        public b(View view) {
            this.f1323b = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f1323b.getParent() == SlidingPaneLayout.this) {
                this.f1323b.setLayerType(0, null);
                SlidingPaneLayout.this.g(this.f1323b);
            }
            SlidingPaneLayout.this.u.remove(this);
        }
    }

    public class c extends c.AbstractC0050c {
        public c() {
        }

        @Override // b.j.a.c.AbstractC0050c
        public int a(View view, int i2, int i3) {
            d dVar = (d) SlidingPaneLayout.this.f1318h.getLayoutParams();
            if (SlidingPaneLayout.this.i()) {
                int width = SlidingPaneLayout.this.getWidth() - ((SlidingPaneLayout.this.getPaddingRight() + ((ViewGroup.MarginLayoutParams) dVar).rightMargin) + SlidingPaneLayout.this.f1318h.getWidth());
                return Math.max(Math.min(i2, width), width - SlidingPaneLayout.this.k);
            }
            int paddingLeft = SlidingPaneLayout.this.getPaddingLeft() + ((ViewGroup.MarginLayoutParams) dVar).leftMargin;
            return Math.min(Math.max(i2, paddingLeft), SlidingPaneLayout.this.k + paddingLeft);
        }

        @Override // b.j.a.c.AbstractC0050c
        public int b(View view, int i2, int i3) {
            return view.getTop();
        }

        @Override // b.j.a.c.AbstractC0050c
        public int d(View view) {
            return SlidingPaneLayout.this.k;
        }

        @Override // b.j.a.c.AbstractC0050c
        public void f(int i2, int i3) {
            SlidingPaneLayout slidingPaneLayout = SlidingPaneLayout.this;
            slidingPaneLayout.q.c(slidingPaneLayout.f1318h, i3);
        }

        @Override // b.j.a.c.AbstractC0050c
        public void i(View view, int i2) {
            SlidingPaneLayout.this.p();
        }

        @Override // b.j.a.c.AbstractC0050c
        public void j(int i2) {
            if (SlidingPaneLayout.this.q.A() == 0) {
                SlidingPaneLayout slidingPaneLayout = SlidingPaneLayout.this;
                if (slidingPaneLayout.f1319i != 0.0f) {
                    slidingPaneLayout.e(slidingPaneLayout.f1318h);
                    SlidingPaneLayout.this.r = true;
                } else {
                    slidingPaneLayout.r(slidingPaneLayout.f1318h);
                    SlidingPaneLayout slidingPaneLayout2 = SlidingPaneLayout.this;
                    slidingPaneLayout2.d(slidingPaneLayout2.f1318h);
                    SlidingPaneLayout.this.r = false;
                }
            }
        }

        @Override // b.j.a.c.AbstractC0050c
        public void k(View view, int i2, int i3, int i4, int i5) {
            SlidingPaneLayout.this.l(i2);
            SlidingPaneLayout.this.invalidate();
        }

        @Override // b.j.a.c.AbstractC0050c
        public void l(View view, float f2, float f3) {
            int paddingLeft;
            d dVar = (d) view.getLayoutParams();
            if (SlidingPaneLayout.this.i()) {
                int paddingRight = SlidingPaneLayout.this.getPaddingRight() + ((ViewGroup.MarginLayoutParams) dVar).rightMargin;
                if (f2 < 0.0f || (f2 == 0.0f && SlidingPaneLayout.this.f1319i > 0.5f)) {
                    paddingRight += SlidingPaneLayout.this.k;
                }
                paddingLeft = (SlidingPaneLayout.this.getWidth() - paddingRight) - SlidingPaneLayout.this.f1318h.getWidth();
            } else {
                paddingLeft = ((ViewGroup.MarginLayoutParams) dVar).leftMargin + SlidingPaneLayout.this.getPaddingLeft();
                if (f2 > 0.0f || (f2 == 0.0f && SlidingPaneLayout.this.f1319i > 0.5f)) {
                    paddingLeft += SlidingPaneLayout.this.k;
                }
            }
            SlidingPaneLayout.this.q.N(paddingLeft, view.getTop());
            SlidingPaneLayout.this.invalidate();
        }

        @Override // b.j.a.c.AbstractC0050c
        public boolean m(View view, int i2) {
            if (SlidingPaneLayout.this.l) {
                return false;
            }
            return ((d) view.getLayoutParams()).f1328c;
        }
    }

    public interface e {
        void a(View view, float f2);

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
        return b(this.f1318h, 0);
    }

    public final boolean b(View view, int i2) {
        if (!this.s && !q(0.0f, i2)) {
            return false;
        }
        this.r = false;
        return true;
    }

    public final void c(View view, float f2, int i2) {
        d dVar = (d) view.getLayoutParams();
        if (f2 > 0.0f && i2 != 0) {
            int i3 = (((int) ((((-16777216) & i2) >>> 24) * f2)) << 24) | (i2 & 16777215);
            if (dVar.f1330e == null) {
                dVar.f1330e = new Paint();
            }
            dVar.f1330e.setColorFilter(new PorterDuffColorFilter(i3, PorterDuff.Mode.SRC_OVER));
            if (view.getLayerType() != 2) {
                view.setLayerType(2, dVar.f1330e);
            }
            g(view);
            return;
        }
        if (view.getLayerType() != 0) {
            Paint paint = dVar.f1330e;
            if (paint != null) {
                paint.setColorFilter(null);
            }
            b bVar = new b(view);
            this.u.add(bVar);
            t.c0(this, bVar);
        }
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof d) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.q.n(true)) {
            if (this.f1317g) {
                t.a0(this);
            } else {
                this.q.a();
            }
        }
    }

    public void d(View view) {
        e eVar = this.p;
        if (eVar != null) {
            eVar.c(view);
        }
        sendAccessibilityEvent(32);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int i2;
        int right;
        super.draw(canvas);
        Drawable drawable = i() ? this.f1315e : this.f1314d;
        View childAt = getChildCount() > 1 ? getChildAt(1) : null;
        if (childAt == null || drawable == null) {
            return;
        }
        int top = childAt.getTop();
        int bottom = childAt.getBottom();
        int intrinsicWidth = drawable.getIntrinsicWidth();
        if (i()) {
            right = childAt.getRight();
            i2 = intrinsicWidth + right;
        } else {
            int left = childAt.getLeft();
            int i3 = left - intrinsicWidth;
            i2 = left;
            right = i3;
        }
        drawable.setBounds(right, top, i2, bottom);
        drawable.draw(canvas);
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j) {
        d dVar = (d) view.getLayoutParams();
        int iSave = canvas.save();
        if (this.f1317g && !dVar.f1328c && this.f1318h != null) {
            canvas.getClipBounds(this.t);
            if (i()) {
                Rect rect = this.t;
                rect.left = Math.max(rect.left, this.f1318h.getRight());
            } else {
                Rect rect2 = this.t;
                rect2.right = Math.min(rect2.right, this.f1318h.getLeft());
            }
            canvas.clipRect(this.t);
        }
        boolean zDrawChild = super.drawChild(canvas, view, j);
        canvas.restoreToCount(iSave);
        return zDrawChild;
    }

    public void e(View view) {
        e eVar = this.p;
        if (eVar != null) {
            eVar.b(view);
        }
        sendAccessibilityEvent(32);
    }

    public void f(View view) {
        e eVar = this.p;
        if (eVar != null) {
            eVar.a(view, this.f1319i);
        }
    }

    public void g(View view) {
        Field field;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 17) {
            t.w0(view, ((d) view.getLayoutParams()).f1330e);
            return;
        }
        if (i2 >= 16) {
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
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new d();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new d((ViewGroup.MarginLayoutParams) layoutParams) : new d(layoutParams);
    }

    public int getCoveredFadeColor() {
        return this.f1313c;
    }

    public int getParallaxDistance() {
        return this.m;
    }

    public int getSliderFadeColor() {
        return this.f1312b;
    }

    public boolean h(View view) {
        if (view == null) {
            return false;
        }
        return this.f1317g && ((d) view.getLayoutParams()).f1329d && this.f1319i > 0.0f;
    }

    public boolean i() {
        return t.z(this) == 1;
    }

    public boolean j() {
        return !this.f1317g || this.f1319i == 1.0f;
    }

    public boolean k() {
        return this.f1317g;
    }

    public void l(int i2) {
        if (this.f1318h == null) {
            this.f1319i = 0.0f;
            return;
        }
        boolean zI = i();
        d dVar = (d) this.f1318h.getLayoutParams();
        int width = this.f1318h.getWidth();
        if (zI) {
            i2 = (getWidth() - i2) - width;
        }
        float paddingRight = (i2 - ((zI ? getPaddingRight() : getPaddingLeft()) + (zI ? ((ViewGroup.MarginLayoutParams) dVar).rightMargin : ((ViewGroup.MarginLayoutParams) dVar).leftMargin))) / this.k;
        this.f1319i = paddingRight;
        if (this.m != 0) {
            o(paddingRight);
        }
        if (dVar.f1329d) {
            c(this.f1318h, this.f1319i, this.f1312b);
        }
        f(this.f1318h);
    }

    public boolean m() {
        return n(this.f1318h, 0);
    }

    public final boolean n(View view, int i2) {
        if (!this.s && !q(1.0f, i2)) {
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
    public final void o(float r10) {
        /*
            r9 = this;
            boolean r0 = r9.i()
            android.view.View r1 = r9.f1318h
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            androidx.slidingpanelayout.widget.SlidingPaneLayout$d r1 = (androidx.slidingpanelayout.widget.SlidingPaneLayout.d) r1
            boolean r2 = r1.f1329d
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
            android.view.View r5 = r9.f1318h
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
            int r6 = r9.f1313c
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
        this.s = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.s = true;
        int size = this.u.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.u.get(i2).run();
        }
        this.u.clear();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z;
        View childAt;
        int actionMasked = motionEvent.getActionMasked();
        if (!this.f1317g && actionMasked == 0 && getChildCount() > 1 && (childAt = getChildAt(1)) != null) {
            this.r = !this.q.E(childAt, (int) motionEvent.getX(), (int) motionEvent.getY());
        }
        if (!this.f1317g || (this.l && actionMasked != 0)) {
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
            if (this.q.E(this.f1318h, (int) x, (int) y) && h(this.f1318h)) {
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
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int i8;
        int i9;
        boolean zI = i();
        if (zI) {
            this.q.L(2);
        } else {
            this.q.L(1);
        }
        int i10 = i4 - i2;
        int paddingRight = zI ? getPaddingRight() : getPaddingLeft();
        int paddingLeft = zI ? getPaddingLeft() : getPaddingRight();
        int paddingTop = getPaddingTop();
        int childCount = getChildCount();
        if (this.s) {
            this.f1319i = (this.f1317g && this.r) ? 1.0f : 0.0f;
        }
        int i11 = paddingRight;
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt = getChildAt(i12);
            if (childAt.getVisibility() != 8) {
                d dVar = (d) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                if (dVar.f1328c) {
                    int i13 = i10 - paddingLeft;
                    int iMin = (Math.min(paddingRight, i13 - this.f1316f) - i11) - (((ViewGroup.MarginLayoutParams) dVar).leftMargin + ((ViewGroup.MarginLayoutParams) dVar).rightMargin);
                    this.k = iMin;
                    int i14 = zI ? ((ViewGroup.MarginLayoutParams) dVar).rightMargin : ((ViewGroup.MarginLayoutParams) dVar).leftMargin;
                    dVar.f1329d = ((i11 + i14) + iMin) + (measuredWidth / 2) > i13;
                    int i15 = (int) (iMin * this.f1319i);
                    i11 += i14 + i15;
                    this.f1319i = i15 / iMin;
                    i6 = 0;
                } else if (!this.f1317g || (i7 = this.m) == 0) {
                    i11 = paddingRight;
                    i6 = 0;
                } else {
                    i6 = (int) ((1.0f - this.f1319i) * i7);
                    i11 = paddingRight;
                }
                if (zI) {
                    i9 = (i10 - i11) + i6;
                    i8 = i9 - measuredWidth;
                } else {
                    i8 = i11 - i6;
                    i9 = i8 + measuredWidth;
                }
                childAt.layout(i8, paddingTop, i9, childAt.getMeasuredHeight() + paddingTop);
                paddingRight += childAt.getWidth();
            }
        }
        if (this.s) {
            if (this.f1317g) {
                if (this.m != 0) {
                    o(this.f1319i);
                }
                if (((d) this.f1318h.getLayoutParams()).f1329d) {
                    c(this.f1318h, this.f1319i, this.f1312b);
                }
            } else {
                for (int i16 = 0; i16 < childCount; i16++) {
                    c(getChildAt(i16), 0.0f, this.f1312b);
                }
            }
            r(this.f1318h);
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
        super.onRestoreInstanceState(savedState.j());
        if (savedState.f1320d) {
            m();
        } else {
            a();
        }
        this.r = savedState.f1320d;
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f1320d = k() ? j() : this.r;
        return savedState;
    }

    @Override // android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 != i4) {
            this.s = true;
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.f1317g) {
            return super.onTouchEvent(motionEvent);
        }
        this.q.F(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            this.n = x;
            this.o = y;
        } else if (actionMasked == 1 && h(this.f1318h)) {
            float x2 = motionEvent.getX();
            float y2 = motionEvent.getY();
            float f2 = x2 - this.n;
            float f3 = y2 - this.o;
            int iZ = this.q.z();
            if ((f2 * f2) + (f3 * f3) < iZ * iZ && this.q.E(this.f1318h, (int) x2, (int) y2)) {
                b(this.f1318h, 0);
            }
        }
        return true;
    }

    public void p() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 4) {
                childAt.setVisibility(0);
            }
        }
    }

    public boolean q(float f2, int i2) {
        int paddingLeft;
        if (!this.f1317g) {
            return false;
        }
        boolean zI = i();
        d dVar = (d) this.f1318h.getLayoutParams();
        if (zI) {
            paddingLeft = (int) (getWidth() - (((getPaddingRight() + ((ViewGroup.MarginLayoutParams) dVar).rightMargin) + (f2 * this.k)) + this.f1318h.getWidth()));
        } else {
            paddingLeft = (int) (getPaddingLeft() + ((ViewGroup.MarginLayoutParams) dVar).leftMargin + (f2 * this.k));
        }
        b.j.a.c cVar = this.q;
        View view = this.f1318h;
        if (!cVar.P(view, paddingLeft, view.getTop())) {
            return false;
        }
        p();
        t.a0(this);
        return true;
    }

    public void r(View view) {
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
        int i2 = 0;
        while (i2 < childCount && (childAt = getChildAt(i2)) != view2) {
            if (childAt.getVisibility() == 8) {
                z = zI;
            } else {
                z = zI;
                childAt.setVisibility((Math.max(zI ? paddingLeft : width, childAt.getLeft()) < left || Math.max(paddingTop, childAt.getTop()) < top || Math.min(zI ? width : paddingLeft, childAt.getRight()) > right || Math.min(height, childAt.getBottom()) > bottom) ? 0 : 4);
            }
            i2++;
            view2 = view;
            zI = z;
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        super.requestChildFocus(view, view2);
        if (isInTouchMode() || this.f1317g) {
            return;
        }
        this.r = view == this.f1318h;
    }

    public void setCoveredFadeColor(int i2) {
        this.f1313c = i2;
    }

    public void setPanelSlideListener(e eVar) {
        this.p = eVar;
    }

    public void setParallaxDistance(int i2) {
        this.m = i2;
        requestLayout();
    }

    @Deprecated
    public void setShadowDrawable(Drawable drawable) {
        setShadowDrawableLeft(drawable);
    }

    public void setShadowDrawableLeft(Drawable drawable) {
        this.f1314d = drawable;
    }

    public void setShadowDrawableRight(Drawable drawable) {
        this.f1315e = drawable;
    }

    @Deprecated
    public void setShadowResource(int i2) {
        setShadowDrawable(getResources().getDrawable(i2));
    }

    public void setShadowResourceLeft(int i2) {
        setShadowDrawableLeft(b.h.e.a.d(getContext(), i2));
    }

    public void setShadowResourceRight(int i2) {
        setShadowDrawableRight(b.h.e.a.d(getContext(), i2));
    }

    public void setSliderFadeColor(int i2) {
        this.f1312b = i2;
    }

    public static class d extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final int[] f1326a = {R.attr.layout_weight};

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f1327b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1328c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1329d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Paint f1330e;

        public d() {
            super(-1, -1);
            this.f1327b = 0.0f;
        }

        public d(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1327b = 0.0f;
        }

        public d(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1327b = 0.0f;
        }

        public d(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1327b = 0.0f;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f1326a);
            this.f1327b = typedArrayObtainStyledAttributes.getFloat(0, 0.0f);
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

    public SlidingPaneLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f1312b = -858993460;
        this.s = true;
        this.t = new Rect();
        this.u = new ArrayList<>();
        float f2 = context.getResources().getDisplayMetrics().density;
        this.f1316f = (int) ((32.0f * f2) + 0.5f);
        setWillNotDraw(false);
        t.k0(this, new a());
        t.u0(this, 1);
        b.j.a.c cVarO = b.j.a.c.o(this, 0.5f, new c());
        this.q = cVarO;
        cVarO.M(f2 * 400.0f);
    }
}
