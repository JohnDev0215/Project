package androidx.coordinatorlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.spotify.music.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.WeakHashMap;
import p.fu2;
import p.fx2;
import p.gh2;
import p.it2;
import p.j00;
import p.jt2;
import p.k00;
import p.kd2;
import p.l00;
import p.m00;
import p.mt2;
import p.n00;
import p.o00;
import p.ot2;
import p.ow1;
import p.oz;
import p.p00;
import p.p01;
import p.qm1;
import p.rm1;
import p.sm1;
import p.sz;
import p.tz1;
import p.x01;
import p.za0;
import p.zt2;

/* JADX INFO: loaded from: classes.dex */
public class CoordinatorLayout extends ViewGroup implements qm1, rm1 {
    public static final String B;
    public static final Class[] C;
    public static final ThreadLocal D;
    public static final p00 E;
    public static final ow1 F;
    public final sm1 A;
    public final ArrayList c;
    public final gh2 e;
    public final ArrayList k;
    public final ArrayList l;
    public final int[] m;
    public final int[] n;
    public boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f17p;
    public final int[] q;
    public View r;
    public View s;
    public n00 t;
    public boolean u;
    public fx2 v;
    public boolean w;
    public Drawable x;
    public ViewGroup.OnHierarchyChangeListener y;
    public p01 z;

    static {
        Package r0 = CoordinatorLayout.class.getPackage();
        B = r0 != null ? r0.getName() : null;
        E = new p00(0);
        C = new Class[]{Context.class, AttributeSet.class};
        D = new ThreadLocal();
        F = new ow1();
    }

    public CoordinatorLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.coordinatorLayoutStyle);
        this.c = new ArrayList();
        this.e = new gh2(2);
        this.k = new ArrayList();
        this.l = new ArrayList();
        this.m = new int[2];
        this.n = new int[2];
        this.A = new sm1();
        int[] iArr = tz1.a;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, R.attr.coordinatorLayoutStyle, 0);
        if (Build.VERSION.SDK_INT >= 29) {
            saveAttributeDataForStyleable(context, iArr, attributeSet, typedArrayObtainStyledAttributes, R.attr.coordinatorLayoutStyle, 0);
        }
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        if (resourceId != 0) {
            Resources resources = context.getResources();
            int[] intArray = resources.getIntArray(resourceId);
            this.q = intArray;
            float f = resources.getDisplayMetrics().density;
            int length = intArray.length;
            for (int i = 0; i < length; i++) {
                this.q[i] = (int) (r1[i] * f);
            }
        }
        this.x = typedArrayObtainStyledAttributes.getDrawable(1);
        typedArrayObtainStyledAttributes.recycle();
        w();
        super.setOnHierarchyChangeListener(new l00(this));
        WeakHashMap weakHashMap = zt2.a;
        if (it2.c(this) == 0) {
            it2.s(this, 1);
        }
    }

    public static Rect g() {
        Rect rect = (Rect) F.a();
        return rect == null ? new Rect() : rect;
    }

    public static void l(int i, Rect rect, Rect rect2, m00 m00Var, int i2, int i3) {
        int i4 = m00Var.c;
        if (i4 == 0) {
            i4 = 17;
        }
        int absoluteGravity = Gravity.getAbsoluteGravity(i4, i);
        int i5 = m00Var.d;
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

    public static m00 n(View view) {
        m00 m00Var = (m00) view.getLayoutParams();
        if (!m00Var.b) {
            k00 k00Var = null;
            for (Class<?> superclass = view.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
                k00Var = (k00) superclass.getAnnotation(k00.class);
                if (k00Var != null) {
                    break;
                }
            }
            if (k00Var != null) {
                try {
                    j00 j00Var = (j00) k00Var.value().getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                    j00 j00Var2 = m00Var.a;
                    if (j00Var2 != j00Var) {
                        if (j00Var2 != null) {
                            j00Var2.e();
                        }
                        m00Var.a = j00Var;
                        m00Var.b = true;
                        if (j00Var != null) {
                            j00Var.c(m00Var);
                        }
                    }
                } catch (Exception e) {
                    Log.e("CoordinatorLayout", "Default behavior class " + k00Var.value().getName() + " could not be instantiated. Did you forget a default constructor?", e);
                }
            }
            m00Var.b = true;
        }
        return m00Var;
    }

    public static void u(View view, int i) {
        m00 m00Var = (m00) view.getLayoutParams();
        int i2 = m00Var.i;
        if (i2 != i) {
            WeakHashMap weakHashMap = zt2.a;
            view.offsetLeftAndRight(i - i2);
            m00Var.i = i;
        }
    }

    public static void v(View view, int i) {
        m00 m00Var = (m00) view.getLayoutParams();
        int i2 = m00Var.j;
        if (i2 != i) {
            WeakHashMap weakHashMap = zt2.a;
            view.offsetTopAndBottom(i - i2);
            m00Var.j = i;
        }
    }

    @Override // p.qm1
    public final void a(View view, View view2, int i, int i2) {
        sm1 sm1Var = this.A;
        if (i2 == 1) {
            sm1Var.c = i;
        } else {
            sm1Var.b = i;
        }
        this.s = view2;
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            ((m00) getChildAt(i3).getLayoutParams()).getClass();
        }
    }

    @Override // p.qm1
    public final void b(View view, int i) {
        sm1 sm1Var = this.A;
        if (i == 1) {
            sm1Var.c = 0;
        } else {
            sm1Var.b = 0;
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            m00 m00Var = (m00) childAt.getLayoutParams();
            if (m00Var.a(i)) {
                j00 j00Var = m00Var.a;
                if (j00Var != null) {
                    j00Var.p(childAt, view, i);
                }
                if (i == 0) {
                    m00Var.n = false;
                } else if (i == 1) {
                    m00Var.o = false;
                }
                m00Var.getClass();
            }
        }
        this.s = null;
    }

    @Override // p.qm1
    public final void c(View view, int i, int i2, int[] iArr, int i3) {
        j00 j00Var;
        int childCount = getChildCount();
        int iMax = 0;
        int iMax2 = 0;
        boolean z = false;
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() != 8) {
                m00 m00Var = (m00) childAt.getLayoutParams();
                if (m00Var.a(i3) && (j00Var = m00Var.a) != null) {
                    int[] iArr2 = this.m;
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    j00Var.j(childAt, view, i2, iArr2, i3);
                    iMax = i > 0 ? Math.max(iMax, iArr2[0]) : Math.min(iMax, iArr2[0]);
                    iMax2 = i2 > 0 ? Math.max(iMax2, iArr2[1]) : Math.min(iMax2, iArr2[1]);
                    z = true;
                }
            }
        }
        iArr[0] = iMax;
        iArr[1] = iMax2;
        if (z) {
            p(1);
        }
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof m00) && super.checkLayoutParams(layoutParams);
    }

    @Override // p.rm1
    public final void d(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        j00 j00Var;
        int childCount = getChildCount();
        boolean z = false;
        int iMax = 0;
        int iMax2 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                m00 m00Var = (m00) childAt.getLayoutParams();
                if (m00Var.a(i5) && (j00Var = m00Var.a) != null) {
                    int[] iArr2 = this.m;
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    j00Var.k(this, childAt, i2, i3, i4, iArr2);
                    iMax = i3 > 0 ? Math.max(iMax, iArr2[0]) : Math.min(iMax, iArr2[0]);
                    iMax2 = i4 > 0 ? Math.max(iMax2, iArr2[1]) : Math.min(iMax2, iArr2[1]);
                    z = true;
                }
            }
        }
        iArr[0] = iArr[0] + iMax;
        iArr[1] = iArr[1] + iMax2;
        if (z) {
            p(1);
        }
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j) {
        j00 j00Var = ((m00) view.getLayoutParams()).a;
        if (j00Var != null) {
            j00Var.getClass();
        }
        return super.drawChild(canvas, view, j);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.x;
        if (drawable != null && drawable.isStateful() && drawable.setState(drawableState)) {
            invalidate();
        }
    }

    @Override // p.qm1
    public final void e(View view, int i, int i2, int i3, int i4, int i5) {
        d(view, i, i2, i3, i4, 0, this.n);
    }

    @Override // p.qm1
    public final boolean f(View view, View view2, int i, int i2) {
        int childCount = getChildCount();
        boolean z = false;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                m00 m00Var = (m00) childAt.getLayoutParams();
                j00 j00Var = m00Var.a;
                if (j00Var != null) {
                    boolean zO = j00Var.o(childAt, i, i2);
                    z |= zO;
                    if (i2 == 0) {
                        m00Var.n = zO;
                    } else if (i2 == 1) {
                        m00Var.o = zO;
                    }
                } else if (i2 == 0) {
                    m00Var.n = false;
                } else if (i2 == 1) {
                    m00Var.o = false;
                }
            }
        }
        return z;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new m00();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new m00(getContext(), attributeSet);
    }

    public final List<View> getDependencySortedChildren() {
        s();
        return Collections.unmodifiableList(this.c);
    }

    public final fx2 getLastWindowInsets() {
        return this.v;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        sm1 sm1Var = this.A;
        return sm1Var.c | sm1Var.b;
    }

    public Drawable getStatusBarBackground() {
        return this.x;
    }

    @Override // android.view.View
    public int getSuggestedMinimumHeight() {
        return Math.max(super.getSuggestedMinimumHeight(), getPaddingBottom() + getPaddingTop());
    }

    @Override // android.view.View
    public int getSuggestedMinimumWidth() {
        return Math.max(super.getSuggestedMinimumWidth(), getPaddingRight() + getPaddingLeft());
    }

    public final void h(m00 m00Var, Rect rect, int i, int i2) {
        int width = getWidth();
        int height = getHeight();
        int iMax = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) m00Var).leftMargin, Math.min(rect.left, ((width - getPaddingRight()) - i) - ((ViewGroup.MarginLayoutParams) m00Var).rightMargin));
        int iMax2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) m00Var).topMargin, Math.min(rect.top, ((height - getPaddingBottom()) - i2) - ((ViewGroup.MarginLayoutParams) m00Var).bottomMargin));
        rect.set(iMax, iMax2, i + iMax, i2 + iMax2);
    }

    public final void i(View view, Rect rect, boolean z) {
        if (view.isLayoutRequested() || view.getVisibility() == 8) {
            rect.setEmpty();
        } else if (z) {
            k(view, rect);
        } else {
            rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
    }

    public final ArrayList j(View view) {
        gh2 gh2Var = this.e;
        int i = ((kd2) gh2Var.m).k;
        ArrayList arrayList = null;
        for (int i2 = 0; i2 < i; i2++) {
            ArrayList arrayList2 = (ArrayList) ((kd2) gh2Var.m).i(i2);
            if (arrayList2 != null && arrayList2.contains(view)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(((kd2) gh2Var.m).f(i2));
            }
        }
        ArrayList arrayList3 = this.l;
        arrayList3.clear();
        if (arrayList != null) {
            arrayList3.addAll(arrayList);
        }
        return arrayList3;
    }

    public final void k(View view, Rect rect) {
        ThreadLocal threadLocal = fu2.a;
        rect.set(0, 0, view.getWidth(), view.getHeight());
        ThreadLocal threadLocal2 = fu2.a;
        Matrix matrix = (Matrix) threadLocal2.get();
        if (matrix == null) {
            matrix = new Matrix();
            threadLocal2.set(matrix);
        } else {
            matrix.reset();
        }
        fu2.a(this, view, matrix);
        ThreadLocal threadLocal3 = fu2.b;
        RectF rectF = (RectF) threadLocal3.get();
        if (rectF == null) {
            rectF = new RectF();
            threadLocal3.set(rectF);
        }
        rectF.set(rect);
        matrix.mapRect(rectF);
        rect.set((int) (rectF.left + 0.5f), (int) (rectF.top + 0.5f), (int) (rectF.right + 0.5f), (int) (rectF.bottom + 0.5f));
    }

    public final int m(int i) {
        int[] iArr = this.q;
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

    public final boolean o(View view, int i, int i2) {
        ow1 ow1Var = F;
        Rect rectG = g();
        k(view, rectG);
        try {
            return rectG.contains(i, i2);
        } finally {
            rectG.setEmpty();
            ow1Var.b(rectG);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        t(false);
        if (this.u) {
            if (this.t == null) {
                this.t = new n00(this);
            }
            getViewTreeObserver().addOnPreDrawListener(this.t);
        }
        if (this.v == null) {
            WeakHashMap weakHashMap = zt2.a;
            if (it2.b(this)) {
                mt2.c(this);
            }
        }
        this.f17p = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        t(false);
        if (this.u && this.t != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.t);
        }
        View view = this.s;
        if (view != null) {
            onStopNestedScroll(view);
        }
        this.f17p = false;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.w || this.x == null) {
            return;
        }
        fx2 fx2Var = this.v;
        int iD = fx2Var != null ? fx2Var.d() : 0;
        if (iD > 0) {
            this.x.setBounds(0, 0, getWidth(), iD);
            this.x.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            t(true);
        }
        boolean zR = r(motionEvent, 0);
        if (actionMasked == 1 || actionMasked == 3) {
            t(true);
        }
        return zR;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        j00 j00Var;
        WeakHashMap weakHashMap = zt2.a;
        int iD = jt2.d(this);
        ArrayList arrayList = this.c;
        int size = arrayList.size();
        for (int i5 = 0; i5 < size; i5++) {
            View view = (View) arrayList.get(i5);
            if (view.getVisibility() != 8 && ((j00Var = ((m00) view.getLayoutParams()).a) == null || !j00Var.g(this, view, iD))) {
                q(view, iD);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:72:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0193  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onMeasure(int r31, int r32) {
        /*
            Method dump skipped, instruction units count: 519
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.onMeasure(int, int):void");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f, float f2, boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8) {
                m00 m00Var = (m00) childAt.getLayoutParams();
                if (m00Var.a(0)) {
                    j00 j00Var = m00Var.a;
                }
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f, float f2) {
        j00 j00Var;
        int childCount = getChildCount();
        boolean zI = false;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8) {
                m00 m00Var = (m00) childAt.getLayoutParams();
                if (m00Var.a(0) && (j00Var = m00Var.a) != null) {
                    zI |= j00Var.i(view);
                }
            }
        }
        return zI;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        c(view, i, i2, iArr, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        e(view, i, i2, i3, i4, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScrollAccepted(View view, View view2, int i) {
        a(view, view2, i, 0);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof o00)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        o00 o00Var = (o00) parcelable;
        super.onRestoreInstanceState(o00Var.c);
        SparseArray sparseArray = o00Var.k;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int id = childAt.getId();
            j00 j00Var = n(childAt).a;
            if (id != -1 && j00Var != null && (parcelable2 = (Parcelable) sparseArray.get(id)) != null) {
                j00Var.m(childAt, parcelable2);
            }
        }
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        Parcelable parcelableN;
        o00 o00Var = new o00(super.onSaveInstanceState());
        SparseArray sparseArray = new SparseArray();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int id = childAt.getId();
            j00 j00Var = ((m00) childAt.getLayoutParams()).a;
            if (id != -1 && j00Var != null && (parcelableN = j00Var.n(childAt)) != null) {
                sparseArray.append(id, parcelableN);
            }
        }
        o00Var.k = sparseArray;
        return o00Var;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onStartNestedScroll(View view, View view2, int i) {
        return f(view, view2, i, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onStopNestedScroll(View view) {
        b(view, 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015 A[PHI: r3
  0x0015: PHI (r3v4 boolean) = (r3v2 boolean), (r3v5 boolean) binds: [B:10:0x0022, B:5:0x0012] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouchEvent(android.view.MotionEvent r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            int r2 = r18.getActionMasked()
            android.view.View r3 = r0.r
            r4 = 1
            r5 = 0
            if (r3 != 0) goto L17
            boolean r3 = r0.r(r1, r4)
            if (r3 == 0) goto L15
            goto L18
        L15:
            r6 = r5
            goto L2a
        L17:
            r3 = r5
        L18:
            android.view.View r6 = r0.r
            android.view.ViewGroup$LayoutParams r6 = r6.getLayoutParams()
            p.m00 r6 = (p.m00) r6
            p.j00 r6 = r6.a
            if (r6 == 0) goto L15
            android.view.View r7 = r0.r
            boolean r6 = r6.q(r7, r1)
        L2a:
            android.view.View r7 = r0.r
            r8 = 0
            if (r7 != 0) goto L35
            boolean r1 = super.onTouchEvent(r18)
            r6 = r6 | r1
            goto L48
        L35:
            if (r3 == 0) goto L48
            long r11 = android.os.SystemClock.uptimeMillis()
            r13 = 3
            r14 = 0
            r15 = 0
            r16 = 0
            r9 = r11
            android.view.MotionEvent r8 = android.view.MotionEvent.obtain(r9, r11, r13, r14, r15, r16)
            super.onTouchEvent(r8)
        L48:
            if (r8 == 0) goto L4d
            r8.recycle()
        L4d:
            if (r2 == r4) goto L52
            r1 = 3
            if (r2 != r1) goto L55
        L52:
            r0.t(r5)
        L55:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x02ab  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00ea  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void p(int r26) {
        /*
            Method dump skipped, instruction units count: 745
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.p(int):void");
    }

    public final void q(View view, int i) {
        Rect rectG;
        Rect rectG2;
        m00 m00Var = (m00) view.getLayoutParams();
        View view2 = m00Var.k;
        if (view2 == null && m00Var.f != -1) {
            throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
        }
        ow1 ow1Var = F;
        if (view2 != null) {
            rectG = g();
            rectG2 = g();
            try {
                k(view2, rectG);
                m00 m00Var2 = (m00) view.getLayoutParams();
                int measuredWidth = view.getMeasuredWidth();
                int measuredHeight = view.getMeasuredHeight();
                l(i, rectG, rectG2, m00Var2, measuredWidth, measuredHeight);
                h(m00Var2, rectG2, measuredWidth, measuredHeight);
                view.layout(rectG2.left, rectG2.top, rectG2.right, rectG2.bottom);
                return;
            } finally {
                rectG.setEmpty();
                ow1Var.b(rectG);
                rectG2.setEmpty();
                ow1Var.b(rectG2);
            }
        }
        int i2 = m00Var.e;
        if (i2 < 0) {
            m00 m00Var3 = (m00) view.getLayoutParams();
            rectG = g();
            rectG.set(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) m00Var3).leftMargin, getPaddingTop() + ((ViewGroup.MarginLayoutParams) m00Var3).topMargin, (getWidth() - getPaddingRight()) - ((ViewGroup.MarginLayoutParams) m00Var3).rightMargin, (getHeight() - getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) m00Var3).bottomMargin);
            if (this.v != null) {
                WeakHashMap weakHashMap = zt2.a;
                if (it2.b(this) && !it2.b(view)) {
                    rectG.left = this.v.b() + rectG.left;
                    rectG.top = this.v.d() + rectG.top;
                    rectG.right -= this.v.c();
                    rectG.bottom -= this.v.a();
                }
            }
            rectG2 = g();
            int i3 = m00Var3.c;
            if ((i3 & 7) == 0) {
                i3 |= 8388611;
            }
            if ((i3 & 112) == 0) {
                i3 |= 48;
            }
            x01.b(i3, view.getMeasuredWidth(), view.getMeasuredHeight(), rectG, rectG2, i);
            view.layout(rectG2.left, rectG2.top, rectG2.right, rectG2.bottom);
            return;
        }
        m00 m00Var4 = (m00) view.getLayoutParams();
        int i4 = m00Var4.c;
        if (i4 == 0) {
            i4 = 8388661;
        }
        int absoluteGravity = Gravity.getAbsoluteGravity(i4, i);
        int i5 = absoluteGravity & 7;
        int i6 = absoluteGravity & 112;
        int width = getWidth();
        int height = getHeight();
        int measuredWidth2 = view.getMeasuredWidth();
        int measuredHeight2 = view.getMeasuredHeight();
        if (i == 1) {
            i2 = width - i2;
        }
        int iM = m(i2) - measuredWidth2;
        if (i5 == 1) {
            iM += measuredWidth2 / 2;
        } else if (i5 == 5) {
            iM += measuredWidth2;
        }
        int i7 = i6 != 16 ? i6 != 80 ? 0 : measuredHeight2 : measuredHeight2 / 2;
        int iMax = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) m00Var4).leftMargin, Math.min(iM, ((width - getPaddingRight()) - measuredWidth2) - ((ViewGroup.MarginLayoutParams) m00Var4).rightMargin));
        int iMax2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) m00Var4).topMargin, Math.min(i7, ((height - getPaddingBottom()) - measuredHeight2) - ((ViewGroup.MarginLayoutParams) m00Var4).bottomMargin));
        view.layout(iMax, iMax2, measuredWidth2 + iMax, measuredHeight2 + iMax2);
    }

    public final boolean r(MotionEvent motionEvent, int i) {
        boolean z;
        int actionMasked = motionEvent.getActionMasked();
        ArrayList arrayList = this.k;
        arrayList.clear();
        boolean zIsChildrenDrawingOrderEnabled = isChildrenDrawingOrderEnabled();
        int childCount = getChildCount();
        for (int i2 = childCount - 1; i2 >= 0; i2--) {
            arrayList.add(getChildAt(zIsChildrenDrawingOrderEnabled ? getChildDrawingOrder(childCount, i2) : i2));
        }
        p00 p00Var = E;
        if (p00Var != null) {
            Collections.sort(arrayList, p00Var);
        }
        int size = arrayList.size();
        MotionEvent motionEventObtain = null;
        boolean zF = false;
        boolean z2 = false;
        for (int i3 = 0; i3 < size; i3++) {
            View view = (View) arrayList.get(i3);
            m00 m00Var = (m00) view.getLayoutParams();
            j00 j00Var = m00Var.a;
            if (!(zF || z2) || actionMasked == 0) {
                if (!zF && j00Var != null) {
                    if (i == 0) {
                        zF = j00Var.f(this, view, motionEvent);
                    } else if (i == 1) {
                        zF = j00Var.q(view, motionEvent);
                    }
                    if (zF) {
                        this.r = view;
                    }
                }
                if (m00Var.a == null) {
                    m00Var.m = false;
                }
                boolean z3 = m00Var.m;
                if (z3) {
                    z = true;
                } else {
                    m00Var.m = z3;
                    z = z3;
                }
                z2 = z && !z3;
                if (z && !z2) {
                    break;
                }
            } else if (j00Var != null) {
                if (motionEventObtain == null) {
                    long jUptimeMillis = SystemClock.uptimeMillis();
                    motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                }
                if (i == 0) {
                    j00Var.f(this, view, motionEventObtain);
                } else if (i == 1) {
                    j00Var.q(view, motionEventObtain);
                }
            }
        }
        arrayList.clear();
        return zF;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        j00 j00Var = ((m00) view.getLayoutParams()).a;
        if (j00Var != null) {
            j00Var.l(this, view);
        }
        return super.requestChildRectangleOnScreen(view, rect, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        if (!z || this.o) {
            return;
        }
        t(false);
        this.o = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0111  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void s() {
        /*
            Method dump skipped, instruction units count: 466
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.s():void");
    }

    @Override // android.view.View
    public void setFitsSystemWindows(boolean z) {
        super.setFitsSystemWindows(z);
        w();
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.y = onHierarchyChangeListener;
    }

    public void setStatusBarBackground(Drawable drawable) {
        Drawable drawable2 = this.x;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.x = drawableMutate;
            if (drawableMutate != null) {
                if (drawableMutate.isStateful()) {
                    this.x.setState(getDrawableState());
                }
                Drawable drawable3 = this.x;
                WeakHashMap weakHashMap = zt2.a;
                za0.b(drawable3, jt2.d(this));
                this.x.setVisible(getVisibility() == 0, false);
                this.x.setCallback(this);
            }
            WeakHashMap weakHashMap2 = zt2.a;
            it2.k(this);
        }
    }

    public void setStatusBarBackgroundColor(int i) {
        setStatusBarBackground(new ColorDrawable(i));
    }

    public void setStatusBarBackgroundResource(int i) {
        Drawable drawableB;
        if (i != 0) {
            Context context = getContext();
            Object obj = sz.a;
            drawableB = oz.b(context, i);
        } else {
            drawableB = null;
        }
        setStatusBarBackground(drawableB);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        Drawable drawable = this.x;
        if (drawable == null || drawable.isVisible() == z) {
            return;
        }
        this.x.setVisible(z, false);
    }

    public final void t(boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            j00 j00Var = ((m00) childAt.getLayoutParams()).a;
            if (j00Var != null) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                if (z) {
                    j00Var.f(this, childAt, motionEventObtain);
                } else {
                    j00Var.q(childAt, motionEventObtain);
                }
                motionEventObtain.recycle();
            }
        }
        for (int i2 = 0; i2 < childCount; i2++) {
            ((m00) getChildAt(i2).getLayoutParams()).m = false;
        }
        this.r = null;
        this.o = false;
    }

    @Override // android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.x;
    }

    public final void w() {
        WeakHashMap weakHashMap = zt2.a;
        if (!it2.b(this)) {
            ot2.u(this, null);
            return;
        }
        if (this.z == null) {
            this.z = new p01(4, this);
        }
        ot2.u(this, this.z);
        setSystemUiVisibility(1280);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof m00 ? new m00((m00) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new m00((ViewGroup.MarginLayoutParams) layoutParams) : new m00(layoutParams);
    }
}
