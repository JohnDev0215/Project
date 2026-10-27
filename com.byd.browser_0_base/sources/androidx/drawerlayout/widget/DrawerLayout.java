package androidx.drawerlayout.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;
import androidx.drawerlayout.R$attr;
import androidx.drawerlayout.R$dimen;
import androidx.drawerlayout.R$styleable;
import defpackage.C0214v;
import defpackage.bz;
import defpackage.gx;
import defpackage.in3;
import defpackage.n01;
import defpackage.om3;
import defpackage.pb3;
import defpackage.pt3;
import defpackage.qm3;
import defpackage.wd0;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class DrawerLayout extends ViewGroup {
    public static final int[] I = {R.attr.colorPrimaryDark};

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public static final int[] f11J = {R.attr.layout_gravity};
    public float A;
    public Drawable B;
    public pt3 C;
    public boolean D;
    public final ArrayList<View> E;
    public Rect F;
    public Matrix G;
    public final gx H;
    public float e;
    public final int f;
    public int g;
    public float h;
    public final Paint i;
    public final qm3 j;
    public final qm3 k;
    public final e l;
    public final e m;
    public int n;
    public boolean o;
    public boolean p;
    public pb3 q;
    public OnBackInvokedDispatcher r;
    public int s;
    public int t;
    public int u;
    public int v;
    public boolean w;
    public d x;
    public ArrayList y;
    public float z;

    public class a extends C0214v {
        public a() {
            new Rect();
        }

        @Override // defpackage.C0214v
        public final boolean a(View view, AccessibilityEvent accessibilityEvent) {
            if (accessibilityEvent.getEventType() != 32) {
                return super.a(view, accessibilityEvent);
            }
            accessibilityEvent.getText();
            DrawerLayout drawerLayout = DrawerLayout.this;
            View viewF = drawerLayout.f();
            if (viewF == null) {
                return true;
            }
            int iH = drawerLayout.h(viewF);
            drawerLayout.getClass();
            WeakHashMap<View, in3> weakHashMap = om3.a;
            Gravity.getAbsoluteGravity(iH, om3.e.c(drawerLayout));
            return true;
        }

        @Override // defpackage.C0214v
        public final void c(View view, AccessibilityEvent accessibilityEvent) {
            super.c(view, accessibilityEvent);
            accessibilityEvent.setClassName("androidx.drawerlayout.widget.DrawerLayout");
        }

        @Override // defpackage.C0214v
        public final void d(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            int[] iArr = DrawerLayout.I;
            View.AccessibilityDelegate accessibilityDelegate = this.a;
            AccessibilityNodeInfo accessibilityNodeInfo = accessibilityNodeInfoCompat.a;
            accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            accessibilityNodeInfoCompat.n("androidx.drawerlayout.widget.DrawerLayout");
            accessibilityNodeInfo.setFocusable(false);
            accessibilityNodeInfo.setFocused(false);
            accessibilityNodeInfoCompat.k(AccessibilityNodeInfoCompat.a.e);
            accessibilityNodeInfoCompat.k(AccessibilityNodeInfoCompat.a.f);
        }

        @Override // defpackage.C0214v
        public final boolean f(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            int[] iArr = DrawerLayout.I;
            return super.f(viewGroup, view, accessibilityEvent);
        }
    }

    public static class b {
        public static void a(Object obj, Object obj2) {
            ((OnBackInvokedDispatcher) obj).registerOnBackInvokedCallback(1000000, (OnBackInvokedCallback) obj2);
        }

        public static void b(Object obj, Object obj2) {
            ((OnBackInvokedDispatcher) obj).unregisterOnBackInvokedCallback((OnBackInvokedCallback) obj2);
        }
    }

    public static final class c extends C0214v {
        @Override // defpackage.C0214v
        public final void d(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            View.AccessibilityDelegate accessibilityDelegate = this.a;
            AccessibilityNodeInfo accessibilityNodeInfo = accessibilityNodeInfoCompat.a;
            accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            int[] iArr = DrawerLayout.I;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            if ((om3.d.b(view) == 4 || om3.d.b(view) == 2) ? false : true) {
                return;
            }
            accessibilityNodeInfoCompat.b = -1;
            accessibilityNodeInfo.setParent(null);
        }
    }

    public interface d {
        void a();

        void b();

        void c();

        void d();
    }

    public class e extends qm3.c {
        public final int a;
        public qm3 b;
        public final androidx.appcompat.widget.a c = new androidx.appcompat.widget.a(3, this);

        public e(int i) {
            this.a = i;
        }

        @Override // qm3.c
        public final int a(View view, int i) {
            DrawerLayout drawerLayout = DrawerLayout.this;
            if (drawerLayout.a(view, 3)) {
                return Math.max(-view.getWidth(), Math.min(i, 0));
            }
            int width = drawerLayout.getWidth();
            return Math.max(width - view.getWidth(), Math.min(i, width));
        }

        @Override // qm3.c
        public final int b(View view, int i) {
            return view.getTop();
        }

        @Override // qm3.c
        public final int c(View view) {
            DrawerLayout.this.getClass();
            if (DrawerLayout.l(view)) {
                return view.getWidth();
            }
            return 0;
        }

        @Override // qm3.c
        public final void e(int i, int i2) {
            int i3 = i & 1;
            DrawerLayout drawerLayout = DrawerLayout.this;
            View viewD = i3 == 1 ? drawerLayout.d(3) : drawerLayout.d(5);
            if (viewD == null || drawerLayout.g(viewD) != 0) {
                return;
            }
            this.b.c(viewD, i2);
        }

        @Override // qm3.c
        public final void f(int i) {
            DrawerLayout.this.postDelayed(this.c, 160L);
        }

        @Override // qm3.c
        public final void g(View view, int i) {
            ((LayoutParams) view.getLayoutParams()).c = false;
            int i2 = this.a == 3 ? 5 : 3;
            DrawerLayout drawerLayout = DrawerLayout.this;
            View viewD = drawerLayout.d(i2);
            if (viewD != null) {
                drawerLayout.b(viewD);
            }
        }

        @Override // qm3.c
        public final void h(int i) {
            DrawerLayout.this.t(this.b.t, i);
        }

        @Override // qm3.c
        public final void i(View view, int i, int i2) {
            int width = view.getWidth();
            DrawerLayout drawerLayout = DrawerLayout.this;
            float width2 = (drawerLayout.a(view, 3) ? i + width : drawerLayout.getWidth() - i) / width;
            drawerLayout.p(view, width2);
            view.setVisibility(width2 == 0.0f ? 4 : 0);
            drawerLayout.invalidate();
        }

        @Override // qm3.c
        public final void j(View view, float f, float f2) {
            int i;
            DrawerLayout drawerLayout = DrawerLayout.this;
            drawerLayout.getClass();
            float f3 = ((LayoutParams) view.getLayoutParams()).b;
            int width = view.getWidth();
            if (drawerLayout.a(view, 3)) {
                i = (f > 0.0f || (f == 0.0f && f3 > 0.5f)) ? 0 : -width;
            } else {
                int width2 = drawerLayout.getWidth();
                if (f < 0.0f || (f == 0.0f && f3 > 0.5f)) {
                    width2 -= width;
                }
                i = width2;
            }
            this.b.t(i, view.getTop());
            drawerLayout.invalidate();
        }

        @Override // qm3.c
        public final boolean k(View view, int i) {
            DrawerLayout drawerLayout = DrawerLayout.this;
            drawerLayout.getClass();
            return DrawerLayout.l(view) && drawerLayout.a(view, this.a) && drawerLayout.g(view) == 0;
        }
    }

    public DrawerLayout(Context context) {
        this(context, null);
    }

    public static String i(int i) {
        return (i & 3) == 3 ? "LEFT" : (i & 5) == 5 ? "RIGHT" : Integer.toHexString(i);
    }

    public static boolean j(View view) {
        return ((LayoutParams) view.getLayoutParams()).a == 0;
    }

    public static boolean k(View view) {
        if (l(view)) {
            return (((LayoutParams) view.getLayoutParams()).d & 1) == 1;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    public static boolean l(View view) {
        int i = ((LayoutParams) view.getLayoutParams()).a;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        int absoluteGravity = Gravity.getAbsoluteGravity(i, om3.e.c(view));
        return ((absoluteGravity & 3) == 0 && (absoluteGravity & 5) == 0) ? false : true;
    }

    public static boolean m(View view) {
        if (l(view)) {
            return ((LayoutParams) view.getLayoutParams()).b > 0.0f;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    public final boolean a(View view, int i) {
        return (h(view) & i) == i;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        ArrayList<View> arrayList2;
        if (getDescendantFocusability() == 393216) {
            return;
        }
        int childCount = getChildCount();
        int i3 = 0;
        boolean z = false;
        while (true) {
            arrayList2 = this.E;
            if (i3 >= childCount) {
                break;
            }
            View childAt = getChildAt(i3);
            if (!l(childAt)) {
                arrayList2.add(childAt);
            } else if (k(childAt)) {
                childAt.addFocusables(arrayList, i, i2);
                z = true;
            }
            i3++;
        }
        if (!z) {
            int size = arrayList2.size();
            for (int i4 = 0; i4 < size; i4++) {
                View view = arrayList2.get(i4);
                if (view.getVisibility() == 0) {
                    view.addFocusables(arrayList, i, i2);
                }
            }
        }
        arrayList2.clear();
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        if (e() != null || l(view)) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.d.p(view, 4);
        } else {
            WeakHashMap<View, in3> weakHashMap2 = om3.a;
            om3.d.p(view, 1);
        }
    }

    public final void b(View view) {
        if (!l(view)) {
            throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
        }
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (this.p) {
            layoutParams.b = 0.0f;
            layoutParams.d = 0;
        } else {
            layoutParams.d |= 4;
            if (a(view, 3)) {
                this.j.v(view, -view.getWidth(), view.getTop());
            } else {
                this.k.v(view, getWidth(), view.getTop());
            }
        }
        invalidate();
    }

    public final void c(boolean z) {
        int childCount = getChildCount();
        boolean zV = false;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (l(childAt) && (!z || layoutParams.c)) {
                zV |= a(childAt, 3) ? this.j.v(childAt, -childAt.getWidth(), childAt.getTop()) : this.k.v(childAt, getWidth(), childAt.getTop());
                layoutParams.c = false;
            }
        }
        e eVar = this.l;
        DrawerLayout.this.removeCallbacks(eVar.c);
        e eVar2 = this.m;
        DrawerLayout.this.removeCallbacks(eVar2.c);
        if (zV) {
            invalidate();
        }
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public final void computeScroll() {
        int childCount = getChildCount();
        float fMax = 0.0f;
        for (int i = 0; i < childCount; i++) {
            fMax = Math.max(fMax, ((LayoutParams) getChildAt(i).getLayoutParams()).b);
        }
        this.h = fMax;
        boolean zH = this.j.h();
        boolean zH2 = this.k.h();
        if (zH || zH2) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.d.j(this);
        }
    }

    public final View d(int i) {
        WeakHashMap<View, in3> weakHashMap = om3.a;
        int absoluteGravity = Gravity.getAbsoluteGravity(i, om3.e.c(this)) & 7;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if ((h(childAt) & 7) == absoluteGravity) {
                return childAt;
            }
        }
        return null;
    }

    @Override // android.view.View
    public final boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        boolean zDispatchGenericMotionEvent;
        if ((motionEvent.getSource() & 2) == 0 || motionEvent.getAction() == 10 || this.h <= 0.0f) {
            return super.dispatchGenericMotionEvent(motionEvent);
        }
        int childCount = getChildCount();
        if (childCount == 0) {
            return false;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        for (int i = childCount - 1; i >= 0; i--) {
            View childAt = getChildAt(i);
            if (this.F == null) {
                this.F = new Rect();
            }
            childAt.getHitRect(this.F);
            if (this.F.contains((int) x, (int) y) && !j(childAt)) {
                if (childAt.getMatrix().isIdentity()) {
                    float scrollX = getScrollX() - childAt.getLeft();
                    float scrollY = getScrollY() - childAt.getTop();
                    motionEvent.offsetLocation(scrollX, scrollY);
                    zDispatchGenericMotionEvent = childAt.dispatchGenericMotionEvent(motionEvent);
                    motionEvent.offsetLocation(-scrollX, -scrollY);
                } else {
                    float scrollX2 = getScrollX() - childAt.getLeft();
                    float scrollY2 = getScrollY() - childAt.getTop();
                    MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
                    motionEventObtain.offsetLocation(scrollX2, scrollY2);
                    Matrix matrix = childAt.getMatrix();
                    if (!matrix.isIdentity()) {
                        if (this.G == null) {
                            this.G = new Matrix();
                        }
                        matrix.invert(this.G);
                        motionEventObtain.transform(this.G);
                    }
                    zDispatchGenericMotionEvent = childAt.dispatchGenericMotionEvent(motionEventObtain);
                    motionEventObtain.recycle();
                }
                if (zDispatchGenericMotionEvent) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j) {
        int height = getHeight();
        boolean zJ = j(view);
        int width = getWidth();
        int iSave = canvas.save();
        int i = 0;
        if (zJ) {
            int childCount = getChildCount();
            int i2 = 0;
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                if (childAt != view && childAt.getVisibility() == 0) {
                    Drawable background = childAt.getBackground();
                    if ((background != null && background.getOpacity() == -1) && l(childAt) && childAt.getHeight() >= height) {
                        if (a(childAt, 3)) {
                            int right = childAt.getRight();
                            if (right > i2) {
                                i2 = right;
                            }
                        } else {
                            int left = childAt.getLeft();
                            if (left < width) {
                                width = left;
                            }
                        }
                    }
                }
            }
            canvas.clipRect(i2, 0, width, getHeight());
            i = i2;
        }
        boolean zDrawChild = super.drawChild(canvas, view, j);
        canvas.restoreToCount(iSave);
        float f = this.h;
        if (f > 0.0f && zJ) {
            int i4 = this.g;
            Paint paint = this.i;
            paint.setColor((((int) ((((-16777216) & i4) >>> 24) * f)) << 24) | (i4 & 16777215));
            canvas.drawRect(i, 0.0f, width, getHeight(), paint);
        }
        return zDrawChild;
    }

    public final View e() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if ((((LayoutParams) childAt.getLayoutParams()).d & 1) == 1) {
                return childAt;
            }
        }
        return null;
    }

    public final View f() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (l(childAt) && m(childAt)) {
                return childAt;
            }
        }
        return null;
    }

    public final int g(View view) {
        if (!l(view)) {
            throw new IllegalArgumentException("View " + view + " is not a drawer");
        }
        int i = ((LayoutParams) view.getLayoutParams()).a;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        int iC = om3.e.c(this);
        if (i == 3) {
            int i2 = this.s;
            if (i2 != 3) {
                return i2;
            }
            int i3 = iC == 0 ? this.u : this.v;
            if (i3 != 3) {
                return i3;
            }
        } else if (i == 5) {
            int i4 = this.t;
            if (i4 != 3) {
                return i4;
            }
            int i5 = iC == 0 ? this.v : this.u;
            if (i5 != 3) {
                return i5;
            }
        } else if (i == 8388611) {
            int i6 = this.u;
            if (i6 != 3) {
                return i6;
            }
            int i7 = iC == 0 ? this.s : this.t;
            if (i7 != 3) {
                return i7;
            }
        } else if (i == 8388613) {
            int i8 = this.v;
            if (i8 != 3) {
                return i8;
            }
            int i9 = iC == 0 ? this.t : this.s;
            if (i9 != 3) {
                return i9;
            }
        }
        return 0;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams ? new LayoutParams((LayoutParams) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    public float getDrawerElevation() {
        return this.e;
    }

    public Drawable getStatusBarBackgroundDrawable() {
        return this.B;
    }

    public final int h(View view) {
        int i = ((LayoutParams) view.getLayoutParams()).a;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        return Gravity.getAbsoluteGravity(i, om3.e.c(this));
    }

    public final void n(View view) {
        if (!l(view)) {
            throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
        }
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (this.p) {
            layoutParams.b = 1.0f;
            layoutParams.d = 1;
            s(view, true);
            r(view);
            q();
        } else {
            layoutParams.d |= 2;
            if (a(view, 3)) {
                this.j.v(view, 0, view.getTop());
            } else {
                this.k.v(view, getWidth() - view.getWidth(), view.getTop());
            }
        }
        invalidate();
    }

    public final void o(int i, int i2) {
        View viewD;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        int absoluteGravity = Gravity.getAbsoluteGravity(i2, om3.e.c(this));
        if (i2 == 3) {
            this.s = i;
        } else if (i2 == 5) {
            this.t = i;
        } else if (i2 == 8388611) {
            this.u = i;
        } else if (i2 == 8388613) {
            this.v = i;
        }
        if (i != 0) {
            (absoluteGravity == 3 ? this.j : this.k).b();
        }
        if (i != 1) {
            if (i == 2 && (viewD = d(absoluteGravity)) != null) {
                n(viewD);
                return;
            }
            return;
        }
        View viewD2 = d(absoluteGravity);
        if (viewD2 != null) {
            b(viewD2);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.p = true;
        q();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.p = true;
        q();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.D || this.B == null) {
            return;
        }
        pt3 pt3Var = this.C;
        int iE = pt3Var != null ? pt3Var.e() : 0;
        if (iE > 0) {
            this.B.setBounds(0, 0, getWidth(), iE);
            this.B.draw(canvas);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0069 A[LOOP:1: B:14:0x0026->B:37:0x0069, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0067 A[SYNTHETIC] */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onInterceptTouchEvent(android.view.MotionEvent r10) {
        /*
            Method dump skipped, instruction units count: 215
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.drawerlayout.widget.DrawerLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (f() != null) {
                keyEvent.startTracking();
                return true;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        View viewF = f();
        if (viewF != null && g(viewF) == 0) {
            c(false);
        }
        return viewF != null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float f;
        int i5;
        boolean z2 = true;
        this.o = true;
        int i6 = i3 - i;
        int childCount = getChildCount();
        int i7 = 0;
        while (i7 < childCount) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (j(childAt)) {
                    int i8 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
                    childAt.layout(i8, ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, childAt.getMeasuredWidth() + i8, childAt.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin);
                } else {
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (a(childAt, 3)) {
                        float f2 = measuredWidth;
                        i5 = (-measuredWidth) + ((int) (layoutParams.b * f2));
                        f = (measuredWidth + i5) / f2;
                    } else {
                        float f3 = measuredWidth;
                        f = (i6 - r11) / f3;
                        i5 = i6 - ((int) (layoutParams.b * f3));
                    }
                    boolean z3 = f != layoutParams.b ? z2 : false;
                    int i9 = layoutParams.a & 112;
                    if (i9 == 16) {
                        int i10 = i4 - i2;
                        int i11 = (i10 - measuredHeight) / 2;
                        int i12 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
                        if (i11 < i12) {
                            i11 = i12;
                        } else {
                            int i13 = i11 + measuredHeight;
                            int i14 = i10 - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
                            if (i13 > i14) {
                                i11 = i14 - measuredHeight;
                            }
                        }
                        childAt.layout(i5, i11, measuredWidth + i5, measuredHeight + i11);
                    } else if (i9 != 80) {
                        int i15 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
                        childAt.layout(i5, i15, measuredWidth + i5, measuredHeight + i15);
                    } else {
                        int i16 = i4 - i2;
                        childAt.layout(i5, (i16 - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin) - childAt.getMeasuredHeight(), measuredWidth + i5, i16 - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
                    }
                    if (z3) {
                        p(childAt, f);
                    }
                    int i17 = layoutParams.b > 0.0f ? 0 : 4;
                    if (childAt.getVisibility() != i17) {
                        childAt.setVisibility(i17);
                    }
                }
            }
            i7++;
            z2 = true;
        }
        WeakHashMap<View, in3> weakHashMap = om3.a;
        pt3 pt3VarA = om3.j.a(this);
        if (pt3VarA != null) {
            n01 n01VarI = pt3VarA.a.i();
            qm3 qm3Var = this.j;
            qm3Var.o = Math.max(qm3Var.p, n01VarI.a);
            qm3 qm3Var2 = this.k;
            qm3Var2.o = Math.max(qm3Var2.p, n01VarI.c);
        }
        this.o = false;
        this.p = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0038  */
    @Override // android.view.View
    @android.annotation.SuppressLint({"WrongConstant"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onMeasure(int r18, int r19) {
        /*
            Method dump skipped, instruction units count: 410
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.drawerlayout.widget.DrawerLayout.onMeasure(int, int):void");
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        View viewD;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.e);
        int i = savedState.g;
        if (i != 0 && (viewD = d(i)) != null) {
            n(viewD);
        }
        int i2 = savedState.h;
        if (i2 != 3) {
            o(i2, 3);
        }
        int i3 = savedState.i;
        if (i3 != 3) {
            o(i3, 5);
        }
        int i4 = savedState.j;
        if (i4 != 3) {
            o(i4, 8388611);
        }
        int i5 = savedState.k;
        if (i5 != 3) {
            o(i5, 8388613);
        }
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            LayoutParams layoutParams = (LayoutParams) getChildAt(i).getLayoutParams();
            int i2 = layoutParams.d;
            boolean z = i2 == 1;
            boolean z2 = i2 == 2;
            if (z || z2) {
                savedState.g = layoutParams.a;
                break;
            }
        }
        savedState.h = this.s;
        savedState.i = this.t;
        savedState.j = this.u;
        savedState.k = this.v;
        return savedState;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0054  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            qm3 r0 = r6.j
            r0.m(r7)
            qm3 r1 = r6.k
            r1.m(r7)
            int r1 = r7.getAction()
            r1 = r1 & 255(0xff, float:3.57E-43)
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L59
            if (r1 == r3) goto L20
            r7 = 3
            if (r1 == r7) goto L1a
            goto L67
        L1a:
            r6.c(r3)
            r6.w = r2
            goto L67
        L20:
            float r1 = r7.getX()
            float r7 = r7.getY()
            int r4 = (int) r1
            int r5 = (int) r7
            android.view.View r4 = r0.j(r4, r5)
            if (r4 == 0) goto L54
            boolean r4 = j(r4)
            if (r4 == 0) goto L54
            float r4 = r6.z
            float r1 = r1 - r4
            float r4 = r6.A
            float r7 = r7 - r4
            int r0 = r0.b
            float r1 = r1 * r1
            float r7 = r7 * r7
            float r7 = r7 + r1
            int r0 = r0 * r0
            float r0 = (float) r0
            int r7 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r7 >= 0) goto L54
            android.view.View r7 = r6.e()
            if (r7 == 0) goto L54
            int r7 = r6.g(r7)
            r0 = 2
            if (r7 != r0) goto L55
        L54:
            r2 = r3
        L55:
            r6.c(r2)
            goto L67
        L59:
            float r0 = r7.getX()
            float r7 = r7.getY()
            r6.z = r0
            r6.A = r7
            r6.w = r2
        L67:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.drawerlayout.widget.DrawerLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public final void p(View view, float f) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (f == layoutParams.b) {
            return;
        }
        layoutParams.b = f;
        ArrayList arrayList = this.y;
        if (arrayList == null) {
            return;
        }
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                return;
            } else {
                ((d) this.y.get(size)).c();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void q() {
        /*
            r3 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 33
            if (r0 < r1) goto L50
            android.view.View r0 = r3.f()
            android.window.OnBackInvokedDispatcher r1 = r3.findOnBackInvokedDispatcher()
            if (r0 == 0) goto L22
            if (r1 == 0) goto L22
            int r0 = r3.g(r0)
            if (r0 != 0) goto L22
            java.util.WeakHashMap<android.view.View, in3> r0 = defpackage.om3.a
            boolean r0 = om3.g.b(r3)
            if (r0 == 0) goto L22
            r0 = 1
            goto L23
        L22:
            r0 = 0
        L23:
            if (r0 == 0) goto L42
            android.window.OnBackInvokedDispatcher r2 = r3.r
            if (r2 != 0) goto L42
            pb3 r0 = r3.q
            if (r0 != 0) goto L3a
            androidx.appcompat.widget.a r0 = new androidx.appcompat.widget.a
            r2 = 2
            r0.<init>(r2, r3)
            pb3 r2 = new pb3
            r2.<init>(r0)
            r3.q = r2
        L3a:
            pb3 r0 = r3.q
            androidx.drawerlayout.widget.DrawerLayout.b.a(r1, r0)
            r3.r = r1
            goto L50
        L42:
            if (r0 != 0) goto L50
            android.window.OnBackInvokedDispatcher r0 = r3.r
            if (r0 == 0) goto L50
            pb3 r1 = r3.q
            androidx.drawerlayout.widget.DrawerLayout.b.b(r0, r1)
            r0 = 0
            r3.r = r0
        L50:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.drawerlayout.widget.DrawerLayout.q():void");
    }

    public final void r(View view) {
        AccessibilityNodeInfoCompat.a aVar = AccessibilityNodeInfoCompat.a.w;
        om3.i(view, aVar.a());
        om3.f(view, 0);
        if (!k(view) || g(view) == 2) {
            return;
        }
        om3.j(view, aVar, null, this.H);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        if (z) {
            c(true);
        }
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        if (this.o) {
            return;
        }
        super.requestLayout();
    }

    public final void s(View view, boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if ((z || l(childAt)) && !(z && childAt == view)) {
                WeakHashMap<View, in3> weakHashMap = om3.a;
                om3.d.p(childAt, 4);
            } else {
                WeakHashMap<View, in3> weakHashMap2 = om3.a;
                om3.d.p(childAt, 1);
            }
        }
    }

    public void setDrawerElevation(float f) {
        this.e = f;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (l(childAt)) {
                float f2 = this.e;
                WeakHashMap<View, in3> weakHashMap = om3.a;
                om3.i.l(childAt, f2);
            }
        }
    }

    @Deprecated
    public void setDrawerListener(d dVar) {
        ArrayList arrayList;
        d dVar2 = this.x;
        if (dVar2 != null && (arrayList = this.y) != null) {
            arrayList.remove(dVar2);
        }
        if (dVar != null) {
            if (this.y == null) {
                this.y = new ArrayList();
            }
            this.y.add(dVar);
        }
        this.x = dVar;
    }

    public void setDrawerLockMode(int i) {
        o(i, 3);
        o(i, 5);
    }

    public void setScrimColor(int i) {
        this.g = i;
        invalidate();
    }

    public void setStatusBarBackground(Drawable drawable) {
        this.B = drawable;
        invalidate();
    }

    public void setStatusBarBackgroundColor(int i) {
        this.B = new ColorDrawable(i);
        invalidate();
    }

    public final void t(View view, int i) {
        int i2;
        View rootView;
        int i3 = this.j.a;
        int i4 = this.k.a;
        if (i3 == 1 || i4 == 1) {
            i2 = 1;
        } else {
            i2 = 2;
            if (i3 != 2 && i4 != 2) {
                i2 = 0;
            }
        }
        if (view != null && i == 0) {
            float f = ((LayoutParams) view.getLayoutParams()).b;
            if (f == 0.0f) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                if ((layoutParams.d & 1) == 1) {
                    layoutParams.d = 0;
                    ArrayList arrayList = this.y;
                    if (arrayList != null) {
                        for (int size = arrayList.size() - 1; size >= 0; size--) {
                            ((d) this.y.get(size)).d();
                        }
                    }
                    s(view, false);
                    r(view);
                    q();
                    if (hasWindowFocus() && (rootView = getRootView()) != null) {
                        rootView.sendAccessibilityEvent(32);
                    }
                }
            } else if (f == 1.0f) {
                LayoutParams layoutParams2 = (LayoutParams) view.getLayoutParams();
                if ((layoutParams2.d & 1) == 0) {
                    layoutParams2.d = 1;
                    ArrayList arrayList2 = this.y;
                    if (arrayList2 != null) {
                        for (int size2 = arrayList2.size() - 1; size2 >= 0; size2--) {
                            ((d) this.y.get(size2)).a();
                        }
                    }
                    s(view, true);
                    r(view);
                    q();
                    if (hasWindowFocus()) {
                        sendAccessibilityEvent(32);
                    }
                }
            }
        }
        if (i2 != this.n) {
            this.n = i2;
            ArrayList arrayList3 = this.y;
            if (arrayList3 != null) {
                for (int size3 = arrayList3.size() - 1; size3 >= 0; size3--) {
                    ((d) this.y.get(size3)).b();
                }
            }
        }
    }

    public DrawerLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.drawerLayoutStyle);
    }

    public DrawerLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        new c();
        this.g = -1728053248;
        this.i = new Paint();
        this.p = true;
        this.s = 3;
        this.t = 3;
        this.u = 3;
        this.v = 3;
        this.H = new gx(1, this);
        setDescendantFocusability(262144);
        float f = getResources().getDisplayMetrics().density;
        this.f = (int) ((64.0f * f) + 0.5f);
        float f2 = f * 400.0f;
        e eVar = new e(3);
        this.l = eVar;
        e eVar2 = new e(5);
        this.m = eVar2;
        qm3 qm3VarI = qm3.i(this, 1.0f, eVar);
        this.j = qm3VarI;
        qm3VarI.q = 1;
        qm3VarI.n = f2;
        eVar.b = qm3VarI;
        qm3 qm3VarI2 = qm3.i(this, 1.0f, eVar2);
        this.k = qm3VarI2;
        qm3VarI2.q = 2;
        qm3VarI2.n = f2;
        eVar2.b = qm3VarI2;
        setFocusableInTouchMode(true);
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.d.p(this, 1);
        om3.k(this, new a());
        int i2 = 0;
        setMotionEventSplittingEnabled(false);
        if (om3.d.a(this)) {
            om3.i.m(this, new wd0(i2));
            setSystemUiVisibility(1280);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(I);
            try {
                this.B = typedArrayObtainStyledAttributes.getDrawable(0);
            } finally {
                typedArrayObtainStyledAttributes.recycle();
            }
        }
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, R$styleable.DrawerLayout, i, 0);
        try {
            int i3 = R$styleable.DrawerLayout_elevation;
            if (typedArrayObtainStyledAttributes2.hasValue(i3)) {
                this.e = typedArrayObtainStyledAttributes2.getDimension(i3, 0.0f);
            } else {
                this.e = getResources().getDimension(R$dimen.def_drawer_elevation);
            }
            typedArrayObtainStyledAttributes2.recycle();
            this.E = new ArrayList<>();
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes2.recycle();
            throw th;
        }
    }

    public void setStatusBarBackground(int i) {
        Drawable drawable;
        if (i != 0) {
            Context context = getContext();
            Object obj = bz.a;
            drawable = context.getDrawable(i);
        } else {
            drawable = null;
        }
        this.B = drawable;
        invalidate();
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public final int a;
        public float b;
        public boolean c;
        public int d;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.a = 0;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, DrawerLayout.f11J);
            this.a = typedArrayObtainStyledAttributes.getInt(0, 0);
            typedArrayObtainStyledAttributes.recycle();
        }

        public LayoutParams() {
            super(-1, -1);
            this.a = 0;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            this.a = 0;
            this.a = layoutParams.a;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.a = 0;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.a = 0;
        }
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public int g;
        public int h;
        public int i;
        public int j;
        public int k;

        public class a implements Parcelable.ClassLoaderCreator<SavedState> {
            @Override // android.os.Parcelable.ClassLoaderCreator
            public final SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public final Object[] newArray(int i) {
                return new SavedState[i];
            }

            @Override // android.os.Parcelable.Creator
            public final Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.g = 0;
            this.g = parcel.readInt();
            this.h = parcel.readInt();
            this.i = parcel.readInt();
            this.j = parcel.readInt();
            this.k = parcel.readInt();
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.e, i);
            parcel.writeInt(this.g);
            parcel.writeInt(this.h);
            parcel.writeInt(this.i);
            parcel.writeInt(this.j);
            parcel.writeInt(this.k);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
            this.g = 0;
        }
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i) {
    }
}
