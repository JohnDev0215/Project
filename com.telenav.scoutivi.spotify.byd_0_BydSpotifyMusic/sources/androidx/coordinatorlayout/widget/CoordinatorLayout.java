package androidx.coordinatorlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import androidx.customview.view.AbsSavedState;
import i0.c0;
import i0.m;
import i0.n;
import i0.o;
import i0.p;
import i0.u;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class CoordinatorLayout extends ViewGroup implements m, n {
    static final Class<?>[] A;
    static final ThreadLocal<Map<String, Constructor<c>>> B;
    static final Comparator<View> C;
    private static final h0.e<Rect> D;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    static final String f1900z;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final List<View> f1901e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final androidx.coordinatorlayout.widget.a<View> f1902g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final List<View> f1903h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final List<View> f1904i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private Paint f1905j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final int[] f1906k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final int[] f1907l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private boolean f1908m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private boolean f1909n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private int[] f1910o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private View f1911p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private View f1912q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private g f1913r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private boolean f1914s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private c0 f1915t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private boolean f1916u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private Drawable f1917v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    ViewGroup.OnHierarchyChangeListener f1918w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private p f1919x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private final o f1920y;

    class a implements p {
        a() {
        }

        @Override // i0.p
        public c0 a(View view, c0 c0Var) {
            return CoordinatorLayout.this.W(c0Var);
        }
    }

    public interface b {
        c getBehavior();
    }

    public static abstract class c<V extends View> {
        public c() {
        }

        public boolean A(CoordinatorLayout coordinatorLayout, V v6, View view, View view2, int i7, int i8) {
            if (i8 == 0) {
                return z(coordinatorLayout, v6, view, view2, i7);
            }
            return false;
        }

        @Deprecated
        public void B(CoordinatorLayout coordinatorLayout, V v6, View view) {
        }

        public void C(CoordinatorLayout coordinatorLayout, V v6, View view, int i7) {
            if (i7 == 0) {
                B(coordinatorLayout, v6, view);
            }
        }

        public boolean D(CoordinatorLayout coordinatorLayout, V v6, MotionEvent motionEvent) {
            return false;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v6) {
            return d(coordinatorLayout, v6) > 0.0f;
        }

        public boolean b(CoordinatorLayout coordinatorLayout, V v6, Rect rect) {
            return false;
        }

        public int c(CoordinatorLayout coordinatorLayout, V v6) {
            return -16777216;
        }

        public float d(CoordinatorLayout coordinatorLayout, V v6) {
            return 0.0f;
        }

        public boolean e(CoordinatorLayout coordinatorLayout, V v6, View view) {
            return false;
        }

        public c0 f(CoordinatorLayout coordinatorLayout, V v6, c0 c0Var) {
            return c0Var;
        }

        public void g(f fVar) {
        }

        public boolean h(CoordinatorLayout coordinatorLayout, V v6, View view) {
            return false;
        }

        public void i(CoordinatorLayout coordinatorLayout, V v6, View view) {
        }

        public void j() {
        }

        public boolean k(CoordinatorLayout coordinatorLayout, V v6, MotionEvent motionEvent) {
            return false;
        }

        public boolean l(CoordinatorLayout coordinatorLayout, V v6, int i7) {
            return false;
        }

        public boolean m(CoordinatorLayout coordinatorLayout, V v6, int i7, int i8, int i9, int i10) {
            return false;
        }

        public boolean n(CoordinatorLayout coordinatorLayout, V v6, View view, float f7, float f8, boolean z6) {
            return false;
        }

        public boolean o(CoordinatorLayout coordinatorLayout, V v6, View view, float f7, float f8) {
            return false;
        }

        @Deprecated
        public void p(CoordinatorLayout coordinatorLayout, V v6, View view, int i7, int i8, int[] iArr) {
        }

        public void q(CoordinatorLayout coordinatorLayout, V v6, View view, int i7, int i8, int[] iArr, int i9) {
            if (i9 == 0) {
                p(coordinatorLayout, v6, view, i7, i8, iArr);
            }
        }

        @Deprecated
        public void r(CoordinatorLayout coordinatorLayout, V v6, View view, int i7, int i8, int i9, int i10) {
        }

        @Deprecated
        public void s(CoordinatorLayout coordinatorLayout, V v6, View view, int i7, int i8, int i9, int i10, int i11) {
            if (i11 == 0) {
                r(coordinatorLayout, v6, view, i7, i8, i9, i10);
            }
        }

        public void t(CoordinatorLayout coordinatorLayout, V v6, View view, int i7, int i8, int i9, int i10, int i11, int[] iArr) {
            iArr[0] = iArr[0] + i9;
            iArr[1] = iArr[1] + i10;
            s(coordinatorLayout, v6, view, i7, i8, i9, i10, i11);
        }

        @Deprecated
        public void u(CoordinatorLayout coordinatorLayout, V v6, View view, View view2, int i7) {
        }

        public void v(CoordinatorLayout coordinatorLayout, V v6, View view, View view2, int i7, int i8) {
            if (i8 == 0) {
                u(coordinatorLayout, v6, view, view2, i7);
            }
        }

        public boolean w(CoordinatorLayout coordinatorLayout, V v6, Rect rect, boolean z6) {
            return false;
        }

        public void x(CoordinatorLayout coordinatorLayout, V v6, Parcelable parcelable) {
        }

        public Parcelable y(CoordinatorLayout coordinatorLayout, V v6) {
            return View.BaseSavedState.EMPTY_STATE;
        }

        @Deprecated
        public boolean z(CoordinatorLayout coordinatorLayout, V v6, View view, View view2, int i7) {
            return false;
        }

        public c(Context context, AttributeSet attributeSet) {
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Deprecated
    public @interface d {
        Class<? extends c> value();
    }

    private class e implements ViewGroup.OnHierarchyChangeListener {
        e() {
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewAdded(View view, View view2) {
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = CoordinatorLayout.this.f1918w;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewAdded(view, view2);
            }
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewRemoved(View view, View view2) {
            CoordinatorLayout.this.H(2);
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = CoordinatorLayout.this.f1918w;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewRemoved(view, view2);
            }
        }
    }

    class g implements ViewTreeObserver.OnPreDrawListener {
        g() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            CoordinatorLayout.this.H(0);
            return true;
        }
    }

    static class h implements Comparator<View> {
        h() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(View view, View view2) {
            float fJ = u.J(view);
            float fJ2 = u.J(view2);
            if (fJ > fJ2) {
                return -1;
            }
            return fJ < fJ2 ? 1 : 0;
        }
    }

    static {
        Package r02 = CoordinatorLayout.class.getPackage();
        f1900z = r02 != null ? r02.getName() : null;
        C = new h();
        A = new Class[]{Context.class, AttributeSet.class};
        B = new ThreadLocal<>();
        D = new h0.g(12);
    }

    public CoordinatorLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, u.a.coordinatorLayoutStyle);
    }

    private boolean A(View view) {
        return this.f1902g.j(view);
    }

    private void C(View view, int i7) {
        f fVar = (f) view.getLayoutParams();
        Rect rectA = a();
        rectA.set(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) fVar).leftMargin, getPaddingTop() + ((ViewGroup.MarginLayoutParams) fVar).topMargin, (getWidth() - getPaddingRight()) - ((ViewGroup.MarginLayoutParams) fVar).rightMargin, (getHeight() - getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin);
        if (this.f1915t != null && u.v(this) && !u.v(view)) {
            rectA.left += this.f1915t.g();
            rectA.top += this.f1915t.i();
            rectA.right -= this.f1915t.h();
            rectA.bottom -= this.f1915t.f();
        }
        Rect rectA2 = a();
        i0.e.a(S(fVar.f1926c), view.getMeasuredWidth(), view.getMeasuredHeight(), rectA, rectA2, i7);
        view.layout(rectA2.left, rectA2.top, rectA2.right, rectA2.bottom);
        O(rectA);
        O(rectA2);
    }

    private void D(View view, View view2, int i7) {
        Rect rectA = a();
        Rect rectA2 = a();
        try {
            t(view2, rectA);
            u(view, i7, rectA, rectA2);
            view.layout(rectA2.left, rectA2.top, rectA2.right, rectA2.bottom);
        } finally {
            O(rectA);
            O(rectA2);
        }
    }

    private void E(View view, int i7, int i8) {
        f fVar = (f) view.getLayoutParams();
        int iB = i0.e.b(T(fVar.f1926c), i8);
        int i9 = iB & 7;
        int i10 = iB & 112;
        int width = getWidth();
        int height = getHeight();
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        if (i8 == 1) {
            i7 = width - i7;
        }
        int iW = w(i7) - measuredWidth;
        int i11 = 0;
        if (i9 == 1) {
            iW += measuredWidth / 2;
        } else if (i9 == 5) {
            iW += measuredWidth;
        }
        if (i10 == 16) {
            i11 = 0 + (measuredHeight / 2);
        } else if (i10 == 80) {
            i11 = measuredHeight + 0;
        }
        int iMax = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) fVar).leftMargin, Math.min(iW, ((width - getPaddingRight()) - measuredWidth) - ((ViewGroup.MarginLayoutParams) fVar).rightMargin));
        int iMax2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) fVar).topMargin, Math.min(i11, ((height - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin));
        view.layout(iMax, iMax2, measuredWidth + iMax, measuredHeight + iMax2);
    }

    private void F(View view, Rect rect, int i7) {
        boolean z6;
        boolean z7;
        int width;
        int i8;
        int i9;
        int i10;
        int height;
        int i11;
        int i12;
        int i13;
        if (u.P(view) && view.getWidth() > 0 && view.getHeight() > 0) {
            f fVar = (f) view.getLayoutParams();
            c cVarF = fVar.f();
            Rect rectA = a();
            Rect rectA2 = a();
            rectA2.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            if (cVarF == null || !cVarF.b(this, view, rectA)) {
                rectA.set(rectA2);
            } else if (!rectA2.contains(rectA)) {
                throw new IllegalArgumentException("Rect should be within the child's bounds. Rect:" + rectA.toShortString() + " | Bounds:" + rectA2.toShortString());
            }
            O(rectA2);
            if (rectA.isEmpty()) {
                O(rectA);
                return;
            }
            int iB = i0.e.b(fVar.f1931h, i7);
            boolean z8 = true;
            if ((iB & 48) != 48 || (i12 = (rectA.top - ((ViewGroup.MarginLayoutParams) fVar).topMargin) - fVar.f1933j) >= (i13 = rect.top)) {
                z6 = false;
            } else {
                V(view, i13 - i12);
                z6 = true;
            }
            if ((iB & 80) == 80 && (height = ((getHeight() - rectA.bottom) - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin) + fVar.f1933j) < (i11 = rect.bottom)) {
                V(view, height - i11);
                z6 = true;
            }
            if (!z6) {
                V(view, 0);
            }
            if ((iB & 3) != 3 || (i9 = (rectA.left - ((ViewGroup.MarginLayoutParams) fVar).leftMargin) - fVar.f1932i) >= (i10 = rect.left)) {
                z7 = false;
            } else {
                U(view, i10 - i9);
                z7 = true;
            }
            if ((iB & 5) != 5 || (width = ((getWidth() - rectA.right) - ((ViewGroup.MarginLayoutParams) fVar).rightMargin) + fVar.f1932i) >= (i8 = rect.right)) {
                z8 = z7;
            } else {
                U(view, width - i8);
            }
            if (!z8) {
                U(view, 0);
            }
            O(rectA);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static c K(Context context, AttributeSet attributeSet, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.startsWith(".")) {
            str = context.getPackageName() + str;
        } else if (str.indexOf(46) < 0) {
            String str2 = f1900z;
            if (!TextUtils.isEmpty(str2)) {
                str = str2 + '.' + str;
            }
        }
        try {
            ThreadLocal<Map<String, Constructor<c>>> threadLocal = B;
            Map<String, Constructor<c>> map = threadLocal.get();
            if (map == null) {
                map = new HashMap<>();
                threadLocal.set(map);
            }
            Constructor<c> constructor = map.get(str);
            if (constructor == null) {
                constructor = Class.forName(str, false, context.getClassLoader()).getConstructor(A);
                constructor.setAccessible(true);
                map.put(str, constructor);
            }
            return constructor.newInstance(context, attributeSet);
        } catch (Exception e7) {
            throw new RuntimeException("Could not inflate Behavior subclass " + str, e7);
        }
    }

    private boolean L(MotionEvent motionEvent, int i7) {
        int actionMasked = motionEvent.getActionMasked();
        List<View> list = this.f1903h;
        z(list);
        int size = list.size();
        MotionEvent motionEventObtain = null;
        boolean zK = false;
        boolean z6 = false;
        for (int i8 = 0; i8 < size; i8++) {
            View view = list.get(i8);
            f fVar = (f) view.getLayoutParams();
            c cVarF = fVar.f();
            if (!(zK || z6) || actionMasked == 0) {
                if (!zK && cVarF != null) {
                    if (i7 == 0) {
                        zK = cVarF.k(this, view, motionEvent);
                    } else if (i7 == 1) {
                        zK = cVarF.D(this, view, motionEvent);
                    }
                    if (zK) {
                        this.f1911p = view;
                    }
                }
                boolean zC = fVar.c();
                boolean zI = fVar.i(this, view);
                z6 = zI && !zC;
                if (zI && !z6) {
                    break;
                }
            } else if (cVarF != null) {
                if (motionEventObtain == null) {
                    long jUptimeMillis = SystemClock.uptimeMillis();
                    motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                }
                if (i7 == 0) {
                    cVarF.k(this, view, motionEventObtain);
                } else if (i7 == 1) {
                    cVarF.D(this, view, motionEventObtain);
                }
            }
        }
        list.clear();
        return zK;
    }

    private void M() {
        this.f1901e.clear();
        this.f1902g.c();
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            f fVarY = y(childAt);
            fVarY.d(this, childAt);
            this.f1902g.b(childAt);
            for (int i8 = 0; i8 < childCount; i8++) {
                if (i8 != i7) {
                    View childAt2 = getChildAt(i8);
                    if (fVarY.b(this, childAt, childAt2)) {
                        if (!this.f1902g.d(childAt2)) {
                            this.f1902g.b(childAt2);
                        }
                        this.f1902g.a(childAt2, childAt);
                    }
                }
            }
        }
        this.f1901e.addAll(this.f1902g.i());
        Collections.reverse(this.f1901e);
    }

    private static void O(Rect rect) {
        rect.setEmpty();
        D.a(rect);
    }

    private void Q(boolean z6) {
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            c cVarF = ((f) childAt.getLayoutParams()).f();
            if (cVarF != null) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                if (z6) {
                    cVarF.k(this, childAt, motionEventObtain);
                } else {
                    cVarF.D(this, childAt, motionEventObtain);
                }
                motionEventObtain.recycle();
            }
        }
        for (int i8 = 0; i8 < childCount; i8++) {
            ((f) getChildAt(i8).getLayoutParams()).m();
        }
        this.f1911p = null;
        this.f1908m = false;
    }

    private static int R(int i7) {
        if (i7 == 0) {
            return 17;
        }
        return i7;
    }

    private static int S(int i7) {
        if ((i7 & 7) == 0) {
            i7 |= 8388611;
        }
        return (i7 & 112) == 0 ? i7 | 48 : i7;
    }

    private static int T(int i7) {
        if (i7 == 0) {
            return 8388661;
        }
        return i7;
    }

    private void U(View view, int i7) {
        f fVar = (f) view.getLayoutParams();
        int i8 = fVar.f1932i;
        if (i8 != i7) {
            u.U(view, i7 - i8);
            fVar.f1932i = i7;
        }
    }

    private void V(View view, int i7) {
        f fVar = (f) view.getLayoutParams();
        int i8 = fVar.f1933j;
        if (i8 != i7) {
            u.V(view, i7 - i8);
            fVar.f1933j = i7;
        }
    }

    private void X() {
        if (!u.v(this)) {
            u.v0(this, null);
            return;
        }
        if (this.f1919x == null) {
            this.f1919x = new a();
        }
        u.v0(this, this.f1919x);
        setSystemUiVisibility(1280);
    }

    private static Rect a() {
        Rect rectB = D.b();
        return rectB == null ? new Rect() : rectB;
    }

    private static int c(int i7, int i8, int i9) {
        return i7 < i8 ? i8 : i7 > i9 ? i9 : i7;
    }

    private void d(f fVar, Rect rect, int i7, int i8) {
        int width = getWidth();
        int height = getHeight();
        int iMax = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) fVar).leftMargin, Math.min(rect.left, ((width - getPaddingRight()) - i7) - ((ViewGroup.MarginLayoutParams) fVar).rightMargin));
        int iMax2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) fVar).topMargin, Math.min(rect.top, ((height - getPaddingBottom()) - i8) - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin));
        rect.set(iMax, iMax2, i7 + iMax, i8 + iMax2);
    }

    private c0 e(c0 c0Var) {
        c cVarF;
        if (c0Var.l()) {
            return c0Var;
        }
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (u.v(childAt) && (cVarF = ((f) childAt.getLayoutParams()).f()) != null) {
                c0Var = cVarF.f(this, childAt, c0Var);
                if (c0Var.l()) {
                    break;
                }
            }
        }
        return c0Var;
    }

    private void v(View view, int i7, Rect rect, Rect rect2, f fVar, int i8, int i9) {
        int iB = i0.e.b(R(fVar.f1926c), i7);
        int iB2 = i0.e.b(S(fVar.f1927d), i7);
        int i10 = iB & 7;
        int i11 = iB & 112;
        int i12 = iB2 & 7;
        int i13 = iB2 & 112;
        int iWidth = i12 != 1 ? i12 != 5 ? rect.left : rect.right : rect.left + (rect.width() / 2);
        int iHeight = i13 != 16 ? i13 != 80 ? rect.top : rect.bottom : rect.top + (rect.height() / 2);
        if (i10 == 1) {
            iWidth -= i8 / 2;
        } else if (i10 != 5) {
            iWidth -= i8;
        }
        if (i11 == 16) {
            iHeight -= i9 / 2;
        } else if (i11 != 80) {
            iHeight -= i9;
        }
        rect2.set(iWidth, iHeight, i8 + iWidth, i9 + iHeight);
    }

    private int w(int i7) {
        int[] iArr = this.f1910o;
        if (iArr == null) {
            Log.e("CoordinatorLayout", "No keylines defined for " + this + " - attempted index lookup " + i7);
            return 0;
        }
        if (i7 >= 0 && i7 < iArr.length) {
            return iArr[i7];
        }
        Log.e("CoordinatorLayout", "Keyline index " + i7 + " out of range for " + this);
        return 0;
    }

    private void z(List<View> list) {
        list.clear();
        boolean zIsChildrenDrawingOrderEnabled = isChildrenDrawingOrderEnabled();
        int childCount = getChildCount();
        for (int i7 = childCount - 1; i7 >= 0; i7--) {
            list.add(getChildAt(zIsChildrenDrawingOrderEnabled ? getChildDrawingOrder(childCount, i7) : i7));
        }
        Comparator<View> comparator = C;
        if (comparator != null) {
            Collections.sort(list, comparator);
        }
    }

    public boolean B(View view, int i7, int i8) {
        Rect rectA = a();
        t(view, rectA);
        try {
            return rectA.contains(i7, i8);
        } finally {
            O(rectA);
        }
    }

    void G(View view, int i7) {
        c cVarF;
        f fVar = (f) view.getLayoutParams();
        if (fVar.f1934k != null) {
            Rect rectA = a();
            Rect rectA2 = a();
            Rect rectA3 = a();
            t(fVar.f1934k, rectA);
            q(view, false, rectA2);
            int measuredWidth = view.getMeasuredWidth();
            int measuredHeight = view.getMeasuredHeight();
            v(view, i7, rectA, rectA3, fVar, measuredWidth, measuredHeight);
            boolean z6 = (rectA3.left == rectA2.left && rectA3.top == rectA2.top) ? false : true;
            d(fVar, rectA3, measuredWidth, measuredHeight);
            int i8 = rectA3.left - rectA2.left;
            int i9 = rectA3.top - rectA2.top;
            if (i8 != 0) {
                u.U(view, i8);
            }
            if (i9 != 0) {
                u.V(view, i9);
            }
            if (z6 && (cVarF = fVar.f()) != null) {
                cVarF.h(this, view, fVar.f1934k);
            }
            O(rectA);
            O(rectA2);
            O(rectA3);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00ca  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final void H(int r18) {
        /*
            Method dump skipped, instruction units count: 273
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.H(int):void");
    }

    public void I(View view, int i7) {
        f fVar = (f) view.getLayoutParams();
        if (fVar.a()) {
            throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
        }
        View view2 = fVar.f1934k;
        if (view2 != null) {
            D(view, view2, i7);
            return;
        }
        int i8 = fVar.f1928e;
        if (i8 >= 0) {
            E(view, i8, i7);
        } else {
            C(view, i7);
        }
    }

    public void J(View view, int i7, int i8, int i9, int i10) {
        measureChildWithMargins(view, i7, i8, i9, i10);
    }

    void N(View view, Rect rect) {
        ((f) view.getLayoutParams()).q(rect);
    }

    void P() {
        if (this.f1909n && this.f1913r != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.f1913r);
        }
        this.f1914s = false;
    }

    final c0 W(c0 c0Var) {
        if (h0.c.a(this.f1915t, c0Var)) {
            return c0Var;
        }
        this.f1915t = c0Var;
        boolean z6 = c0Var != null && c0Var.i() > 0;
        this.f1916u = z6;
        setWillNotDraw(!z6 && getBackground() == null);
        c0 c0VarE = e(c0Var);
        requestLayout();
        return c0VarE;
    }

    void b() {
        if (this.f1909n) {
            if (this.f1913r == null) {
                this.f1913r = new g();
            }
            getViewTreeObserver().addOnPreDrawListener(this.f1913r);
        }
        this.f1914s = true;
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof f) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View view, long j7) {
        f fVar = (f) view.getLayoutParams();
        c cVar = fVar.f1924a;
        if (cVar != null) {
            float fD = cVar.d(this, view);
            if (fD > 0.0f) {
                if (this.f1905j == null) {
                    this.f1905j = new Paint();
                }
                this.f1905j.setColor(fVar.f1924a.c(this, view));
                this.f1905j.setAlpha(c(Math.round(fD * 255.0f), 0, 255));
                int iSave = canvas.save();
                if (view.isOpaque()) {
                    canvas.clipRect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom(), Region.Op.DIFFERENCE);
                }
                canvas.drawRect(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom(), this.f1905j);
                canvas.restoreToCount(iSave);
            }
        }
        return super.drawChild(canvas, view, j7);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.f1917v;
        boolean state = false;
        if (drawable != null && drawable.isStateful()) {
            state = false | drawable.setState(drawableState);
        }
        if (state) {
            invalidate();
        }
    }

    public void f(View view) {
        List listG = this.f1902g.g(view);
        if (listG == null || listG.isEmpty()) {
            return;
        }
        for (int i7 = 0; i7 < listG.size(); i7++) {
            View view2 = (View) listG.get(i7);
            c cVarF = ((f) view2.getLayoutParams()).f();
            if (cVarF != null) {
                cVarF.h(this, view2, view);
            }
        }
    }

    void g() {
        int childCount = getChildCount();
        boolean z6 = false;
        int i7 = 0;
        while (true) {
            if (i7 >= childCount) {
                break;
            }
            if (A(getChildAt(i7))) {
                z6 = true;
                break;
            }
            i7++;
        }
        if (z6 != this.f1914s) {
            if (z6) {
                b();
            } else {
                P();
            }
        }
    }

    final List<View> getDependencySortedChildren() {
        M();
        return Collections.unmodifiableList(this.f1901e);
    }

    public final c0 getLastWindowInsets() {
        return this.f1915t;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.f1920y.a();
    }

    public Drawable getStatusBarBackground() {
        return this.f1917v;
    }

    @Override // android.view.View
    protected int getSuggestedMinimumHeight() {
        return Math.max(super.getSuggestedMinimumHeight(), getPaddingTop() + getPaddingBottom());
    }

    @Override // android.view.View
    protected int getSuggestedMinimumWidth() {
        return Math.max(super.getSuggestedMinimumWidth(), getPaddingLeft() + getPaddingRight());
    }

    @Override // i0.m
    public void h(View view, View view2, int i7, int i8) {
        c cVarF;
        this.f1920y.c(view, view2, i7, i8);
        this.f1912q = view2;
        int childCount = getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            f fVar = (f) childAt.getLayoutParams();
            if (fVar.j(i8) && (cVarF = fVar.f()) != null) {
                cVarF.v(this, childAt, view, view2, i7, i8);
            }
        }
    }

    @Override // i0.m
    public void i(View view, int i7) {
        this.f1920y.d(view, i7);
        int childCount = getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            f fVar = (f) childAt.getLayoutParams();
            if (fVar.j(i7)) {
                c cVarF = fVar.f();
                if (cVarF != null) {
                    cVarF.C(this, childAt, view, i7);
                }
                fVar.l(i7);
                fVar.k();
            }
        }
        this.f1912q = null;
    }

    @Override // i0.m
    public void j(View view, int i7, int i8, int[] iArr, int i9) {
        c cVarF;
        int childCount = getChildCount();
        boolean z6 = false;
        int iMax = 0;
        int iMax2 = 0;
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = getChildAt(i10);
            if (childAt.getVisibility() != 8) {
                f fVar = (f) childAt.getLayoutParams();
                if (fVar.j(i9) && (cVarF = fVar.f()) != null) {
                    int[] iArr2 = this.f1906k;
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    cVarF.q(this, childAt, view, i7, i8, iArr2, i9);
                    int[] iArr3 = this.f1906k;
                    iMax = i7 > 0 ? Math.max(iMax, iArr3[0]) : Math.min(iMax, iArr3[0]);
                    int[] iArr4 = this.f1906k;
                    iMax2 = i8 > 0 ? Math.max(iMax2, iArr4[1]) : Math.min(iMax2, iArr4[1]);
                    z6 = true;
                }
            }
        }
        iArr[0] = iMax;
        iArr[1] = iMax2;
        if (z6) {
            H(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public f generateDefaultLayoutParams() {
        return new f(-2, -2);
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public f generateLayoutParams(AttributeSet attributeSet) {
        return new f(getContext(), attributeSet);
    }

    @Override // i0.n
    public void m(View view, int i7, int i8, int i9, int i10, int i11, int[] iArr) {
        c cVarF;
        boolean z6;
        int iMin;
        int childCount = getChildCount();
        boolean z7 = false;
        int iMax = 0;
        int i12 = 0;
        for (int i13 = 0; i13 < childCount; i13++) {
            View childAt = getChildAt(i13);
            if (childAt.getVisibility() != 8) {
                f fVar = (f) childAt.getLayoutParams();
                if (fVar.j(i11) && (cVarF = fVar.f()) != null) {
                    int[] iArr2 = this.f1906k;
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    cVarF.t(this, childAt, view, i7, i8, i9, i10, i11, iArr2);
                    int[] iArr3 = this.f1906k;
                    iMax = i9 > 0 ? Math.max(iMax, iArr3[0]) : Math.min(iMax, iArr3[0]);
                    if (i10 > 0) {
                        z6 = true;
                        iMin = Math.max(i12, this.f1906k[1]);
                    } else {
                        z6 = true;
                        iMin = Math.min(i12, this.f1906k[1]);
                    }
                    i12 = iMin;
                    z7 = z6;
                }
            }
        }
        iArr[0] = iArr[0] + iMax;
        iArr[1] = iArr[1] + i12;
        if (z7) {
            H(1);
        }
    }

    @Override // i0.m
    public void n(View view, int i7, int i8, int i9, int i10, int i11) {
        m(view, i7, i8, i9, i10, 0, this.f1907l);
    }

    @Override // i0.m
    public boolean o(View view, View view2, int i7, int i8) {
        int childCount = getChildCount();
        boolean z6 = false;
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                f fVar = (f) childAt.getLayoutParams();
                c cVarF = fVar.f();
                if (cVarF != null) {
                    boolean zA = cVarF.A(this, childAt, view, view2, i7, i8);
                    z6 |= zA;
                    fVar.r(i8, zA);
                } else {
                    fVar.r(i8, false);
                }
            }
        }
        return z6;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Q(false);
        if (this.f1914s) {
            if (this.f1913r == null) {
                this.f1913r = new g();
            }
            getViewTreeObserver().addOnPreDrawListener(this.f1913r);
        }
        if (this.f1915t == null && u.v(this)) {
            u.h0(this);
        }
        this.f1909n = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Q(false);
        if (this.f1914s && this.f1913r != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.f1913r);
        }
        View view = this.f1912q;
        if (view != null) {
            onStopNestedScroll(view);
        }
        this.f1909n = false;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.f1916u || this.f1917v == null) {
            return;
        }
        c0 c0Var = this.f1915t;
        int i7 = c0Var != null ? c0Var.i() : 0;
        if (i7 > 0) {
            this.f1917v.setBounds(0, 0, getWidth(), i7);
            this.f1917v.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            Q(true);
        }
        boolean zL = L(motionEvent, 0);
        if (actionMasked == 1 || actionMasked == 3) {
            Q(true);
        }
        return zL;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        c cVarF;
        int iY = u.y(this);
        int size = this.f1901e.size();
        for (int i11 = 0; i11 < size; i11++) {
            View view = this.f1901e.get(i11);
            if (view.getVisibility() != 8 && ((cVarF = ((f) view.getLayoutParams()).f()) == null || !cVarF.l(this, view, iY))) {
                I(view, iY);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x011d  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onMeasure(int r31, int r32) {
        /*
            Method dump skipped, instruction units count: 391
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.onMeasure(int, int):void");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View view, float f7, float f8, boolean z6) {
        c cVarF;
        int childCount = getChildCount();
        boolean zN = false;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                f fVar = (f) childAt.getLayoutParams();
                if (fVar.j(0) && (cVarF = fVar.f()) != null) {
                    zN |= cVarF.n(this, childAt, view, f7, f8, z6);
                }
            }
        }
        if (zN) {
            H(1);
        }
        return zN;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View view, float f7, float f8) {
        c cVarF;
        int childCount = getChildCount();
        boolean zO = false;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                f fVar = (f) childAt.getLayoutParams();
                if (fVar.j(0) && (cVarF = fVar.f()) != null) {
                    zO |= cVarF.o(this, childAt, view, f7, f8);
                }
            }
        }
        return zO;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedPreScroll(View view, int i7, int i8, int[] iArr) {
        j(view, i7, i8, iArr, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View view, int i7, int i8, int i9, int i10) {
        n(view, i7, i8, i9, i10, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScrollAccepted(View view, View view2, int i7) {
        h(view, view2, i7, 0);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.b());
        SparseArray<Parcelable> sparseArray = savedState.f1921h;
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            int id = childAt.getId();
            c cVarF = y(childAt).f();
            if (id != -1 && cVarF != null && (parcelable2 = sparseArray.get(id)) != null) {
                cVarF.x(this, childAt, parcelable2);
            }
        }
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelableY;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            int id = childAt.getId();
            c cVarF = ((f) childAt.getLayoutParams()).f();
            if (id != -1 && cVarF != null && (parcelableY = cVarF.y(this, childAt)) != null) {
                sparseArray.append(id, parcelableY);
            }
        }
        savedState.f1921h = sparseArray;
        return savedState;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onStartNestedScroll(View view, View view2, int i7) {
        return o(view, view2, i7, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View view) {
        i(view, 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002b A[PHI: r3
  0x002b: PHI (r3v4 boolean) = (r3v2 boolean), (r3v5 boolean) binds: [B:9:0x0022, B:5:0x0012] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0054  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            int r2 = r18.getActionMasked()
            android.view.View r3 = r0.f1911p
            r4 = 1
            r5 = 0
            if (r3 != 0) goto L15
            boolean r3 = r0.L(r1, r4)
            if (r3 == 0) goto L2b
            goto L16
        L15:
            r3 = r5
        L16:
            android.view.View r6 = r0.f1911p
            android.view.ViewGroup$LayoutParams r6 = r6.getLayoutParams()
            androidx.coordinatorlayout.widget.CoordinatorLayout$f r6 = (androidx.coordinatorlayout.widget.CoordinatorLayout.f) r6
            androidx.coordinatorlayout.widget.CoordinatorLayout$c r6 = r6.f()
            if (r6 == 0) goto L2b
            android.view.View r7 = r0.f1911p
            boolean r6 = r6.D(r0, r7, r1)
            goto L2c
        L2b:
            r6 = r5
        L2c:
            android.view.View r7 = r0.f1911p
            r8 = 0
            if (r7 != 0) goto L37
            boolean r1 = super.onTouchEvent(r18)
            r6 = r6 | r1
            goto L4a
        L37:
            if (r3 == 0) goto L4a
            long r11 = android.os.SystemClock.uptimeMillis()
            r13 = 3
            r14 = 0
            r15 = 0
            r16 = 0
            r9 = r11
            android.view.MotionEvent r8 = android.view.MotionEvent.obtain(r9, r11, r13, r14, r15, r16)
            super.onTouchEvent(r8)
        L4a:
            if (r8 == 0) goto L4f
            r8.recycle()
        L4f:
            if (r2 == r4) goto L54
            r1 = 3
            if (r2 != r1) goto L57
        L54:
            r0.Q(r5)
        L57:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public f generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof f ? new f((f) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new f((ViewGroup.MarginLayoutParams) layoutParams) : new f(layoutParams);
    }

    void q(View view, boolean z6, Rect rect) {
        if (view.isLayoutRequested() || view.getVisibility() == 8) {
            rect.setEmpty();
        } else if (z6) {
            t(view, rect);
        } else {
            rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
    }

    public List<View> r(View view) {
        List<View> listH = this.f1902g.h(view);
        this.f1904i.clear();
        if (listH != null) {
            this.f1904i.addAll(listH);
        }
        return this.f1904i;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z6) {
        c cVarF = ((f) view.getLayoutParams()).f();
        if (cVarF == null || !cVarF.w(this, view, rect, z6)) {
            return super.requestChildRectangleOnScreen(view, rect, z6);
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z6) {
        super.requestDisallowInterceptTouchEvent(z6);
        if (!z6 || this.f1908m) {
            return;
        }
        Q(false);
        this.f1908m = true;
    }

    public List<View> s(View view) {
        List listG = this.f1902g.g(view);
        this.f1904i.clear();
        if (listG != null) {
            this.f1904i.addAll(listG);
        }
        return this.f1904i;
    }

    @Override // android.view.View
    public void setFitsSystemWindows(boolean z6) {
        super.setFitsSystemWindows(z6);
        X();
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.f1918w = onHierarchyChangeListener;
    }

    public void setStatusBarBackground(Drawable drawable) {
        Drawable drawable2 = this.f1917v;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.f1917v = drawableMutate;
            if (drawableMutate != null) {
                if (drawableMutate.isStateful()) {
                    this.f1917v.setState(getDrawableState());
                }
                a0.a.g(this.f1917v, u.y(this));
                this.f1917v.setVisible(getVisibility() == 0, false);
                this.f1917v.setCallback(this);
            }
            u.b0(this);
        }
    }

    public void setStatusBarBackgroundColor(int i7) {
        setStatusBarBackground(new ColorDrawable(i7));
    }

    public void setStatusBarBackgroundResource(int i7) {
        setStatusBarBackground(i7 != 0 ? x.a.c(getContext(), i7) : null);
    }

    @Override // android.view.View
    public void setVisibility(int i7) {
        super.setVisibility(i7);
        boolean z6 = i7 == 0;
        Drawable drawable = this.f1917v;
        if (drawable == null || drawable.isVisible() == z6) {
            return;
        }
        this.f1917v.setVisible(z6, false);
    }

    void t(View view, Rect rect) {
        androidx.coordinatorlayout.widget.b.a(this, view, rect);
    }

    void u(View view, int i7, Rect rect, Rect rect2) {
        f fVar = (f) view.getLayoutParams();
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        v(view, i7, rect, rect2, fVar, measuredWidth, measuredHeight);
        d(fVar, rect2, measuredWidth, measuredHeight);
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f1917v;
    }

    void x(View view, Rect rect) {
        rect.set(((f) view.getLayoutParams()).h());
    }

    /* JADX WARN: Multi-variable type inference failed */
    f y(View view) {
        f fVar = (f) view.getLayoutParams();
        if (!fVar.f1925b) {
            if (view instanceof b) {
                c behavior = ((b) view).getBehavior();
                if (behavior == null) {
                    Log.e("CoordinatorLayout", "Attached behavior class is null");
                }
                fVar.o(behavior);
                fVar.f1925b = true;
            } else {
                d dVar = null;
                for (Class<?> superclass = view.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
                    dVar = (d) superclass.getAnnotation(d.class);
                    if (dVar != null) {
                        break;
                    }
                }
                if (dVar != null) {
                    try {
                        fVar.o(dVar.value().getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
                    } catch (Exception e7) {
                        Log.e("CoordinatorLayout", "Default behavior class " + dVar.value().getName() + " could not be instantiated. Did you forget a default constructor?", e7);
                    }
                }
                fVar.f1925b = true;
            }
        }
        return fVar;
    }

    public CoordinatorLayout(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1901e = new ArrayList();
        this.f1902g = new androidx.coordinatorlayout.widget.a<>();
        this.f1903h = new ArrayList();
        this.f1904i = new ArrayList();
        this.f1906k = new int[2];
        this.f1907l = new int[2];
        this.f1920y = new o(this);
        TypedArray typedArrayObtainStyledAttributes = i7 == 0 ? context.obtainStyledAttributes(attributeSet, u.c.CoordinatorLayout, 0, u.b.Widget_Support_CoordinatorLayout) : context.obtainStyledAttributes(attributeSet, u.c.CoordinatorLayout, i7, 0);
        if (Build.VERSION.SDK_INT >= 29) {
            if (i7 == 0) {
                saveAttributeDataForStyleable(context, u.c.CoordinatorLayout, attributeSet, typedArrayObtainStyledAttributes, 0, u.b.Widget_Support_CoordinatorLayout);
            } else {
                saveAttributeDataForStyleable(context, u.c.CoordinatorLayout, attributeSet, typedArrayObtainStyledAttributes, i7, 0);
            }
        }
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(u.c.CoordinatorLayout_keylines, 0);
        if (resourceId != 0) {
            Resources resources = context.getResources();
            this.f1910o = resources.getIntArray(resourceId);
            float f7 = resources.getDisplayMetrics().density;
            int length = this.f1910o.length;
            for (int i8 = 0; i8 < length; i8++) {
                this.f1910o[i8] = (int) (r12[i8] * f7);
            }
        }
        this.f1917v = typedArrayObtainStyledAttributes.getDrawable(u.c.CoordinatorLayout_statusBarBackground);
        typedArrayObtainStyledAttributes.recycle();
        X();
        super.setOnHierarchyChangeListener(new e());
        if (u.w(this) == 0) {
            u.t0(this, 1);
        }
    }

    protected static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        SparseArray<Parcelable> f1921h;

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
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i7) {
                return new SavedState[i7];
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            int i7 = parcel.readInt();
            int[] iArr = new int[i7];
            parcel.readIntArray(iArr);
            Parcelable[] parcelableArray = parcel.readParcelableArray(classLoader);
            this.f1921h = new SparseArray<>(i7);
            for (int i8 = 0; i8 < i7; i8++) {
                this.f1921h.append(iArr[i8], parcelableArray[i8]);
            }
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i7) {
            super.writeToParcel(parcel, i7);
            SparseArray<Parcelable> sparseArray = this.f1921h;
            int size = sparseArray != null ? sparseArray.size() : 0;
            parcel.writeInt(size);
            int[] iArr = new int[size];
            Parcelable[] parcelableArr = new Parcelable[size];
            for (int i8 = 0; i8 < size; i8++) {
                iArr[i8] = this.f1921h.keyAt(i8);
                parcelableArr[i8] = this.f1921h.valueAt(i8);
            }
            parcel.writeIntArray(iArr);
            parcel.writeParcelableArray(parcelableArr, i7);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public static class f extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        c f1924a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f1925b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1926c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1927d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1928e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f1929f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f1930g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f1931h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        int f1932i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        int f1933j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        View f1934k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        View f1935l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        private boolean f1936m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        private boolean f1937n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        private boolean f1938o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        private boolean f1939p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        final Rect f1940q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        Object f1941r;

        public f(int i7, int i8) {
            super(i7, i8);
            this.f1925b = false;
            this.f1926c = 0;
            this.f1927d = 0;
            this.f1928e = -1;
            this.f1929f = -1;
            this.f1930g = 0;
            this.f1931h = 0;
            this.f1940q = new Rect();
        }

        private void n(View view, CoordinatorLayout coordinatorLayout) {
            View viewFindViewById = coordinatorLayout.findViewById(this.f1929f);
            this.f1934k = viewFindViewById;
            if (viewFindViewById == null) {
                if (coordinatorLayout.isInEditMode()) {
                    this.f1935l = null;
                    this.f1934k = null;
                    return;
                }
                throw new IllegalStateException("Could not find CoordinatorLayout descendant view with id " + coordinatorLayout.getResources().getResourceName(this.f1929f) + " to anchor view " + view);
            }
            if (viewFindViewById == coordinatorLayout) {
                if (!coordinatorLayout.isInEditMode()) {
                    throw new IllegalStateException("View can not be anchored to the the parent CoordinatorLayout");
                }
                this.f1935l = null;
                this.f1934k = null;
                return;
            }
            for (ViewParent parent = viewFindViewById.getParent(); parent != coordinatorLayout && parent != null; parent = parent.getParent()) {
                if (parent == view) {
                    if (!coordinatorLayout.isInEditMode()) {
                        throw new IllegalStateException("Anchor must not be a descendant of the anchored view");
                    }
                    this.f1935l = null;
                    this.f1934k = null;
                    return;
                }
                if (parent instanceof View) {
                    viewFindViewById = parent;
                }
            }
            this.f1935l = viewFindViewById;
        }

        private boolean s(View view, int i7) {
            int iB = i0.e.b(((f) view.getLayoutParams()).f1930g, i7);
            return iB != 0 && (i0.e.b(this.f1931h, i7) & iB) == iB;
        }

        private boolean t(View view, CoordinatorLayout coordinatorLayout) {
            if (this.f1934k.getId() != this.f1929f) {
                return false;
            }
            View view2 = this.f1934k;
            for (ViewParent parent = view2.getParent(); parent != coordinatorLayout; parent = parent.getParent()) {
                if (parent == null || parent == view) {
                    this.f1935l = null;
                    this.f1934k = null;
                    return false;
                }
                if (parent instanceof View) {
                    view2 = parent;
                }
            }
            this.f1935l = view2;
            return true;
        }

        boolean a() {
            return this.f1934k == null && this.f1929f != -1;
        }

        boolean b(CoordinatorLayout coordinatorLayout, View view, View view2) {
            c cVar;
            return view2 == this.f1935l || s(view2, u.y(coordinatorLayout)) || ((cVar = this.f1924a) != null && cVar.e(coordinatorLayout, view, view2));
        }

        boolean c() {
            if (this.f1924a == null) {
                this.f1936m = false;
            }
            return this.f1936m;
        }

        View d(CoordinatorLayout coordinatorLayout, View view) {
            if (this.f1929f == -1) {
                this.f1935l = null;
                this.f1934k = null;
                return null;
            }
            if (this.f1934k == null || !t(view, coordinatorLayout)) {
                n(view, coordinatorLayout);
            }
            return this.f1934k;
        }

        public int e() {
            return this.f1929f;
        }

        public c f() {
            return this.f1924a;
        }

        boolean g() {
            return this.f1939p;
        }

        Rect h() {
            return this.f1940q;
        }

        boolean i(CoordinatorLayout coordinatorLayout, View view) {
            boolean z6 = this.f1936m;
            if (z6) {
                return true;
            }
            c cVar = this.f1924a;
            boolean zA = (cVar != null ? cVar.a(coordinatorLayout, view) : false) | z6;
            this.f1936m = zA;
            return zA;
        }

        boolean j(int i7) {
            if (i7 == 0) {
                return this.f1937n;
            }
            if (i7 != 1) {
                return false;
            }
            return this.f1938o;
        }

        void k() {
            this.f1939p = false;
        }

        void l(int i7) {
            r(i7, false);
        }

        void m() {
            this.f1936m = false;
        }

        public void o(c cVar) {
            c cVar2 = this.f1924a;
            if (cVar2 != cVar) {
                if (cVar2 != null) {
                    cVar2.j();
                }
                this.f1924a = cVar;
                this.f1941r = null;
                this.f1925b = true;
                if (cVar != null) {
                    cVar.g(this);
                }
            }
        }

        void p(boolean z6) {
            this.f1939p = z6;
        }

        void q(Rect rect) {
            this.f1940q.set(rect);
        }

        void r(int i7, boolean z6) {
            if (i7 == 0) {
                this.f1937n = z6;
            } else {
                if (i7 != 1) {
                    return;
                }
                this.f1938o = z6;
            }
        }

        f(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1925b = false;
            this.f1926c = 0;
            this.f1927d = 0;
            this.f1928e = -1;
            this.f1929f = -1;
            this.f1930g = 0;
            this.f1931h = 0;
            this.f1940q = new Rect();
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, u.c.CoordinatorLayout_Layout);
            this.f1926c = typedArrayObtainStyledAttributes.getInteger(u.c.CoordinatorLayout_Layout_android_layout_gravity, 0);
            this.f1929f = typedArrayObtainStyledAttributes.getResourceId(u.c.CoordinatorLayout_Layout_layout_anchor, -1);
            this.f1927d = typedArrayObtainStyledAttributes.getInteger(u.c.CoordinatorLayout_Layout_layout_anchorGravity, 0);
            this.f1928e = typedArrayObtainStyledAttributes.getInteger(u.c.CoordinatorLayout_Layout_layout_keyline, -1);
            this.f1930g = typedArrayObtainStyledAttributes.getInt(u.c.CoordinatorLayout_Layout_layout_insetEdge, 0);
            this.f1931h = typedArrayObtainStyledAttributes.getInt(u.c.CoordinatorLayout_Layout_layout_dodgeInsetEdges, 0);
            int i7 = u.c.CoordinatorLayout_Layout_layout_behavior;
            boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(i7);
            this.f1925b = zHasValue;
            if (zHasValue) {
                this.f1924a = CoordinatorLayout.K(context, attributeSet, typedArrayObtainStyledAttributes.getString(i7));
            }
            typedArrayObtainStyledAttributes.recycle();
            c cVar = this.f1924a;
            if (cVar != null) {
                cVar.g(this);
            }
        }

        public f(f fVar) {
            super((ViewGroup.MarginLayoutParams) fVar);
            this.f1925b = false;
            this.f1926c = 0;
            this.f1927d = 0;
            this.f1928e = -1;
            this.f1929f = -1;
            this.f1930g = 0;
            this.f1931h = 0;
            this.f1940q = new Rect();
        }

        public f(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1925b = false;
            this.f1926c = 0;
            this.f1927d = 0;
            this.f1928e = -1;
            this.f1929f = -1;
            this.f1930g = 0;
            this.f1931h = 0;
            this.f1940q = new Rect();
        }

        public f(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1925b = false;
            this.f1926c = 0;
            this.f1927d = 0;
            this.f1928e = -1;
            this.f1929f = -1;
            this.f1930g = 0;
            this.f1931h = 0;
            this.f1940q = new Rect();
        }
    }
}
