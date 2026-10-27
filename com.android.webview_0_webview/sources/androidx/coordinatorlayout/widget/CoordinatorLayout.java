package androidx.coordinatorlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import androidx.customview.view.AbsSavedState;
import defpackage.A1;
import defpackage.AbstractC0245Nm;
import defpackage.AbstractC0622cn;
import defpackage.AbstractC0678dn;
import defpackage.AbstractC1251o3;
import defpackage.AbstractC1798y1;
import defpackage.B1;
import defpackage.C0583c3;
import defpackage.C0973j3;
import defpackage.C1;
import defpackage.C1361q3;
import defpackage.C1743x1;
import defpackage.D1;
import defpackage.E1;
import defpackage.F1;
import defpackage.G1;
import defpackage.InterfaceC0471a3;
import defpackage.InterfaceC0918i3;
import defpackage.InterfaceC1029k3;
import defpackage.InterfaceC1853z1;
import defpackage.ViewOnApplyWindowInsetsListenerC1085l3;
import defpackage.Z7;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class CoordinatorLayout extends ViewGroup implements InterfaceC0918i3 {

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public static final String f17J;
    public static final Class[] K;
    public static final ThreadLocal L;
    public static final Comparator M;
    public static final InterfaceC0471a3 N;
    public View A;
    public C1 B;
    public boolean C;
    public C1361q3 D;
    public boolean E;
    public Drawable F;
    public ViewGroup.OnHierarchyChangeListener G;
    public InterfaceC1029k3 H;
    public final C0973j3 I;
    public final List s;
    public final F1 t;
    public final List u;
    public final int[] v;
    public boolean w;
    public boolean x;
    public int[] y;
    public View z;

    static {
        Package r0 = CoordinatorLayout.class.getPackage();
        f17J = r0 != null ? r0.getName() : null;
        M = new E1();
        K = new Class[]{Context.class, AttributeSet.class};
        L = new ThreadLocal();
        N = new C0583c3(12);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public CoordinatorLayout(Context context, AttributeSet attributeSet) {
        int i = AbstractC0245Nm.n2;
        int[] iArr = AbstractC0678dn.I;
        super(context, attributeSet, i);
        this.s = new ArrayList();
        this.t = new F1();
        this.u = new ArrayList();
        new ArrayList();
        this.v = new int[2];
        this.I = new C0973j3(this);
        TypedArray typedArrayObtainStyledAttributes = i == 0 ? context.obtainStyledAttributes(attributeSet, iArr, 0, AbstractC0622cn.Ta) : context.obtainStyledAttributes(attributeSet, iArr, i, 0);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        if (resourceId != 0) {
            Resources resources = context.getResources();
            this.y = resources.getIntArray(resourceId);
            float f = resources.getDisplayMetrics().density;
            int length = this.y.length;
            for (int i2 = 0; i2 < length; i2++) {
                this.y[i2] = (int) (r1[i2] * f);
            }
        }
        this.F = typedArrayObtainStyledAttributes.getDrawable(1);
        typedArrayObtainStyledAttributes.recycle();
        s();
        super.setOnHierarchyChangeListener(new A1(this));
    }

    public static Rect f() {
        Rect rect = (Rect) N.b();
        return rect == null ? new Rect() : rect;
    }

    public static void n(Context context, AttributeSet attributeSet, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (str.startsWith(".")) {
            str = context.getPackageName() + str;
        } else if (str.indexOf(46) < 0 && !TextUtils.isEmpty(f17J)) {
            str = f17J + '.' + str;
        }
        try {
            Map map = (Map) L.get();
            if (map == null) {
                map = new HashMap();
                L.set(map);
            }
            Constructor<?> constructor = (Constructor) map.get(str);
            if (constructor == null) {
                constructor = context.getClassLoader().loadClass(str).getConstructor(K);
                constructor.setAccessible(true);
                map.put(str, constructor);
            }
        } catch (Exception e) {
            throw new RuntimeException(Z7.c("Could not inflate Behavior subclass ", str), e);
        }
    }

    @Override // defpackage.InterfaceC0918i3
    public void a(View view, View view2, int i, int i2) {
        this.I.a = i;
        this.A = view2;
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            ((B1) getChildAt(i3).getLayoutParams()).a(i2);
        }
    }

    @Override // defpackage.InterfaceC0918i3
    public void b(View view, int i) {
        this.I.a = 0;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            B1 b1 = (B1) getChildAt(i2).getLayoutParams();
            if (b1.a(i)) {
                b1.b(i, false);
                b1.p = false;
            }
        }
        this.A = null;
    }

    @Override // defpackage.InterfaceC0918i3
    public void c(View view, int i, int i2, int[] iArr, int i3) {
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() != 8) {
                ((B1) childAt.getLayoutParams()).a(i3);
            }
        }
        iArr[0] = 0;
        iArr[1] = 0;
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof B1) && super.checkLayoutParams(layoutParams);
    }

    @Override // defpackage.InterfaceC0918i3
    public void d(View view, int i, int i2, int i3, int i4, int i5) {
        int childCount = getChildCount();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                ((B1) childAt.getLayoutParams()).a(i5);
            }
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j) {
        return super.drawChild(canvas, view, j);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.F;
        boolean state = false;
        if (drawable != null && drawable.isStateful()) {
            state = false | drawable.setState(drawableState);
        }
        if (state) {
            invalidate();
        }
    }

    @Override // defpackage.InterfaceC0918i3
    public boolean e(View view, View view2, int i, int i2) {
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                B1 b1 = (B1) childAt.getLayoutParams();
                Objects.requireNonNull(b1);
                b1.b(i2, false);
            }
        }
        return false;
    }

    public final void g(B1 b1, Rect rect, int i, int i2) {
        int width = getWidth();
        int height = getHeight();
        int iMax = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) b1).leftMargin, Math.min(rect.left, ((width - getPaddingRight()) - i) - ((ViewGroup.MarginLayoutParams) b1).rightMargin));
        int iMax2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) b1).topMargin, Math.min(rect.top, ((height - getPaddingBottom()) - i2) - ((ViewGroup.MarginLayoutParams) b1).bottomMargin));
        rect.set(iMax, iMax2, i + iMax, i2 + iMax2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new B1(-2, -2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new B1(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.I.a;
    }

    @Override // android.view.View
    public int getSuggestedMinimumHeight() {
        return Math.max(super.getSuggestedMinimumHeight(), getPaddingBottom() + getPaddingTop());
    }

    @Override // android.view.View
    public int getSuggestedMinimumWidth() {
        return Math.max(super.getSuggestedMinimumWidth(), getPaddingRight() + getPaddingLeft());
    }

    public void h(View view, boolean z, Rect rect) {
        if (view.isLayoutRequested() || view.getVisibility() == 8) {
            rect.setEmpty();
        } else if (z) {
            G1.a(this, view, rect);
        } else {
            rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
    }

    public final void i(int i, Rect rect, Rect rect2, B1 b1, int i2, int i3) {
        int i4 = b1.c;
        if (i4 == 0) {
            i4 = 17;
        }
        int absoluteGravity = Gravity.getAbsoluteGravity(i4, i);
        int i5 = b1.d;
        if ((i5 & 7) == 0) {
            i5 |= 8388611;
        }
        if ((i5 & 112) == 0) {
            i5 |= 48;
        }
        int absoluteGravity2 = Gravity.getAbsoluteGravity(i5, i);
        int i6 = absoluteGravity & 7;
        int i7 = absoluteGravity & 112;
        int i8 = absoluteGravity2 & 7;
        int i9 = absoluteGravity2 & 112;
        int iWidth = i8 != 1 ? i8 != 5 ? rect.left : rect.right : rect.left + (rect.width() / 2);
        int iHeight = i9 != 16 ? i9 != 80 ? rect.top : rect.bottom : rect.top + (rect.height() / 2);
        if (i6 == 1) {
            iWidth -= i2 / 2;
        } else if (i6 != 5) {
            iWidth -= i2;
        }
        if (i7 == 16) {
            iHeight -= i3 / 2;
        } else if (i7 != 80) {
            iHeight -= i3;
        }
        rect2.set(iWidth, iHeight, i2 + iWidth, i3 + iHeight);
    }

    public final int j(int i) {
        int[] iArr = this.y;
        if (iArr == null) {
            Log.e("CoordinatorLayout", "No keylines defined for " + this + " - attempted index lookup " + i);
            return 0;
        }
        if (i >= 0 && i < iArr.length) {
            return iArr[i];
        }
        Log.e("CoordinatorLayout", "Keyline index " + i + " out of range for " + this);
        return 0;
    }

    public B1 k(View view) {
        B1 b1 = (B1) view.getLayoutParams();
        if (!b1.b) {
            InterfaceC1853z1 interfaceC1853z1 = null;
            for (Class<?> superclass = view.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
                interfaceC1853z1 = (InterfaceC1853z1) superclass.getAnnotation(InterfaceC1853z1.class);
                if (interfaceC1853z1 != null) {
                    break;
                }
            }
            if (interfaceC1853z1 != null) {
                try {
                    AbstractC1798y1 abstractC1798y1 = (AbstractC1798y1) interfaceC1853z1.value().getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                    if (abstractC1798y1 != null) {
                        b1.a = abstractC1798y1;
                        b1.b = true;
                        throw null;
                    }
                } catch (Exception e) {
                    StringBuilder sbF = Z7.f("Default behavior class ");
                    sbF.append(interfaceC1853z1.value().getName());
                    sbF.append(" could not be instantiated. Did you forget");
                    sbF.append(" a default constructor?");
                    Log.e("CoordinatorLayout", sbF.toString(), e);
                }
            }
            b1.b = true;
        }
        return b1;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0255  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x025d A[LOOP:2: B:92:0x025b->B:93:0x025d, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void l(int r25) {
        /*
            Method dump skipped, instruction units count: 658
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.l(int):void");
    }

    public void m(View view, int i, int i2, int i3, int i4) {
        measureChildWithMargins(view, i, i2, i3, i4);
    }

    public final boolean o(MotionEvent motionEvent, int i) {
        motionEvent.getActionMasked();
        List list = this.u;
        list.clear();
        boolean zIsChildrenDrawingOrderEnabled = isChildrenDrawingOrderEnabled();
        int childCount = getChildCount();
        for (int i2 = childCount - 1; i2 >= 0; i2--) {
            list.add(getChildAt(zIsChildrenDrawingOrderEnabled ? getChildDrawingOrder(childCount, i2) : i2));
        }
        Comparator comparator = M;
        if (comparator != null) {
            Collections.sort(list, comparator);
        }
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            B1 b1 = (B1) ((View) list.get(i3)).getLayoutParams();
            Objects.requireNonNull(b1);
            b1.m = false;
            b1.m = false;
        }
        list.clear();
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        p(false);
        if (this.C) {
            if (this.B == null) {
                this.B = new C1(this);
            }
            getViewTreeObserver().addOnPreDrawListener(this.B);
        }
        if (this.D == null && AbstractC1251o3.i(this)) {
            requestApplyInsets();
        }
        this.x = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        p(false);
        if (this.C && this.B != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.B);
        }
        View view = this.A;
        if (view != null) {
            onStopNestedScroll(view);
        }
        this.x = false;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.E || this.F == null) {
            return;
        }
        C1361q3 c1361q3 = this.D;
        int iA = c1361q3 != null ? c1361q3.a() : 0;
        if (iA > 0) {
            this.F.setBounds(0, 0, getWidth(), iA);
            this.F.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            p(true);
        }
        o(motionEvent, 0);
        if (actionMasked == 1 || actionMasked == 3) {
            p(true);
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        Rect rectF;
        Rect rectF2;
        int iK = AbstractC1251o3.k(this);
        int size = this.s.size();
        for (int i5 = 0; i5 < size; i5++) {
            View view = (View) this.s.get(i5);
            if (view.getVisibility() != 8) {
                Objects.requireNonNull((B1) view.getLayoutParams());
                B1 b1 = (B1) view.getLayoutParams();
                if (b1.k == null && b1.f != -1) {
                    throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
                }
                View view2 = b1.k;
                if (view2 != null) {
                    rectF = f();
                    rectF2 = f();
                    try {
                        G1.a(this, view2, rectF);
                        B1 b12 = (B1) view.getLayoutParams();
                        int measuredWidth = view.getMeasuredWidth();
                        int measuredHeight = view.getMeasuredHeight();
                        i(iK, rectF, rectF2, b12, measuredWidth, measuredHeight);
                        g(b12, rectF2, measuredWidth, measuredHeight);
                        view.layout(rectF2.left, rectF2.top, rectF2.right, rectF2.bottom);
                    } finally {
                        rectF.setEmpty();
                        N.a(rectF);
                        rectF2.setEmpty();
                        N.a(rectF2);
                    }
                } else {
                    int i6 = b1.e;
                    if (i6 >= 0) {
                        B1 b13 = (B1) view.getLayoutParams();
                        int i7 = b13.c;
                        if (i7 == 0) {
                            i7 = 8388661;
                        }
                        int absoluteGravity = Gravity.getAbsoluteGravity(i7, iK);
                        int i8 = absoluteGravity & 7;
                        int i9 = absoluteGravity & 112;
                        int width = getWidth();
                        int height = getHeight();
                        int measuredWidth2 = view.getMeasuredWidth();
                        int measuredHeight2 = view.getMeasuredHeight();
                        if (iK == 1) {
                            i6 = width - i6;
                        }
                        int iJ = j(i6) - measuredWidth2;
                        if (i8 == 1) {
                            iJ += measuredWidth2 / 2;
                        } else if (i8 == 5) {
                            iJ += measuredWidth2;
                        }
                        int i10 = i9 != 16 ? i9 != 80 ? 0 : measuredHeight2 + 0 : (measuredHeight2 / 2) + 0;
                        int iMax = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) b13).leftMargin, Math.min(iJ, ((width - getPaddingRight()) - measuredWidth2) - ((ViewGroup.MarginLayoutParams) b13).rightMargin));
                        int iMax2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) b13).topMargin, Math.min(i10, ((height - getPaddingBottom()) - measuredHeight2) - ((ViewGroup.MarginLayoutParams) b13).bottomMargin));
                        view.layout(iMax, iMax2, measuredWidth2 + iMax, measuredHeight2 + iMax2);
                    } else {
                        B1 b14 = (B1) view.getLayoutParams();
                        rectF = f();
                        rectF.set(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) b14).leftMargin, getPaddingTop() + ((ViewGroup.MarginLayoutParams) b14).topMargin, (getWidth() - getPaddingRight()) - ((ViewGroup.MarginLayoutParams) b14).rightMargin, (getHeight() - getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) b14).bottomMargin);
                        if (this.D != null && AbstractC1251o3.i(this) && !view.getFitsSystemWindows()) {
                            rectF.left = ((WindowInsets) this.D.a).getSystemWindowInsetLeft() + rectF.left;
                            rectF.top = this.D.a() + rectF.top;
                            rectF.right -= ((WindowInsets) this.D.a).getSystemWindowInsetRight();
                            rectF.bottom -= ((WindowInsets) this.D.a).getSystemWindowInsetBottom();
                        }
                        rectF2 = f();
                        int i11 = b14.c;
                        if ((i11 & 7) == 0) {
                            i11 |= 8388611;
                        }
                        if ((i11 & 112) == 0) {
                            i11 |= 48;
                        }
                        Gravity.apply(i11, view.getMeasuredWidth(), view.getMeasuredHeight(), rectF, rectF2, iK);
                        view.layout(rectF2.left, rectF2.top, rectF2.right, rectF2.bottom);
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:177:0x031d  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0160 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0106  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onMeasure(int r29, int r30) {
        /*
            Method dump skipped, instruction units count: 906
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.onMeasure(int, int):void");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8) {
                ((B1) childAt.getLayoutParams()).a(0);
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View view, float f, float f2) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8) {
                ((B1) childAt.getLayoutParams()).a(0);
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        c(view, i, i2, iArr, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        d(view, i, i2, i3, i4, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScrollAccepted(View view, View view2, int i) {
        a(view, view2, i, 0);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        super.onRestoreInstanceState(((SavedState) parcelable).s);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            childAt.getId();
            k(childAt);
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SparseArray sparseArray = new SparseArray();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            childAt.getId();
            Objects.requireNonNull((B1) childAt.getLayoutParams());
        }
        savedState.u = sparseArray;
        return savedState;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onStartNestedScroll(View view, View view2, int i) {
        e(view, view2, i, 0);
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View view) {
        b(view, 0);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        o(motionEvent, 1);
        boolean zOnTouchEvent = super.onTouchEvent(motionEvent) | false;
        if (actionMasked == 1 || actionMasked == 3) {
            p(false);
        }
        return zOnTouchEvent;
    }

    public final void p(boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            Objects.requireNonNull((B1) getChildAt(i).getLayoutParams());
        }
        for (int i2 = 0; i2 < childCount; i2++) {
            ((B1) getChildAt(i2).getLayoutParams()).m = false;
        }
        this.z = null;
        this.w = false;
    }

    public final void q(View view, int i) {
        B1 b1 = (B1) view.getLayoutParams();
        int i2 = b1.i;
        if (i2 != i) {
            AbstractC1251o3.w(view, i - i2);
            b1.i = i;
        }
    }

    public final void r(View view, int i) {
        B1 b1 = (B1) view.getLayoutParams();
        int i2 = b1.j;
        if (i2 != i) {
            AbstractC1251o3.x(view, i - i2);
            b1.j = i;
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        Objects.requireNonNull((B1) view.getLayoutParams());
        return super.requestChildRectangleOnScreen(view, rect, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        if (!z || this.w) {
            return;
        }
        p(false);
        this.w = true;
    }

    public final void s() {
        if (!AbstractC1251o3.i(this)) {
            setOnApplyWindowInsetsListener(null);
            return;
        }
        if (this.H == null) {
            this.H = new C1743x1(this);
        }
        InterfaceC1029k3 interfaceC1029k3 = this.H;
        if (interfaceC1029k3 == null) {
            setOnApplyWindowInsetsListener(null);
        } else {
            setOnApplyWindowInsetsListener(new ViewOnApplyWindowInsetsListenerC1085l3(interfaceC1029k3));
        }
        setSystemUiVisibility(1280);
    }

    @Override // android.view.View
    public void setFitsSystemWindows(boolean z) {
        super.setFitsSystemWindows(z);
        s();
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.G = onHierarchyChangeListener;
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        Drawable drawable = this.F;
        if (drawable == null || drawable.isVisible() == z) {
            return;
        }
        this.F.setVisible(z, false);
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.F;
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof B1 ? new B1((B1) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new B1((ViewGroup.MarginLayoutParams) layoutParams) : new B1(layoutParams);
    }

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR = new D1();
        public SparseArray u;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            int i = parcel.readInt();
            int[] iArr = new int[i];
            parcel.readIntArray(iArr);
            Parcelable[] parcelableArray = parcel.readParcelableArray(classLoader);
            this.u = new SparseArray(i);
            for (int i2 = 0; i2 < i; i2++) {
                this.u.append(iArr[i2], parcelableArray[i2]);
            }
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.s, i);
            SparseArray sparseArray = this.u;
            int size = sparseArray != null ? sparseArray.size() : 0;
            parcel.writeInt(size);
            int[] iArr = new int[size];
            Parcelable[] parcelableArr = new Parcelable[size];
            for (int i2 = 0; i2 < size; i2++) {
                iArr[i2] = this.u.keyAt(i2);
                parcelableArr[i2] = (Parcelable) this.u.valueAt(i2);
            }
            parcel.writeIntArray(iArr);
            parcel.writeParcelableArray(parcelableArr, i);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }
}
