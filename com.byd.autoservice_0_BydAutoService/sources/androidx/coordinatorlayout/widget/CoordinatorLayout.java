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
import androidx.coordinatorlayout.R$attr;
import androidx.coordinatorlayout.R$style;
import androidx.coordinatorlayout.R$styleable;
import androidx.customview.view.AbsSavedState;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import l0.b0;
import l0.l;
import l0.m;
import l0.o;
import l0.p;
import l0.t;

/* JADX INFO: loaded from: classes.dex */
public class CoordinatorLayout extends ViewGroup implements l, m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f1069b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Class<?>[] f1070c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final ThreadLocal<Map<String, Constructor<c>>> f1071d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Comparator<View> f1072e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final k0.e<Rect> f1073f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final List<View> f1074g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final z.a<View> f1075h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final List<View> f1076i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final List<View> f1077j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Paint f1078k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int[] f1079l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int[] f1080m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f1081n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f1082o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int[] f1083p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public View f1084q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public View f1085r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public g f1086s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f1087t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public b0 f1088u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f1089v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public Drawable f1090w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public ViewGroup.OnHierarchyChangeListener f1091x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public p f1092y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final o f1093z;

    public class a implements p {
        public a() {
        }

        @Override // l0.p
        public b0 a(View view, b0 b0Var) {
            return CoordinatorLayout.this.a0(b0Var);
        }
    }

    public interface b {
        c getBehavior();
    }

    public static abstract class c<V extends View> {
        public c() {
        }

        public boolean A(CoordinatorLayout coordinatorLayout, V v3, View view, View view2, int i3, int i4) {
            if (i4 == 0) {
                return z(coordinatorLayout, v3, view, view2, i3);
            }
            return false;
        }

        @Deprecated
        public void B(CoordinatorLayout coordinatorLayout, V v3, View view) {
        }

        public void C(CoordinatorLayout coordinatorLayout, V v3, View view, int i3) {
            if (i3 == 0) {
                B(coordinatorLayout, v3, view);
            }
        }

        public boolean D(CoordinatorLayout coordinatorLayout, V v3, MotionEvent motionEvent) {
            return false;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v3) {
            return d(coordinatorLayout, v3) > 0.0f;
        }

        public boolean b(CoordinatorLayout coordinatorLayout, V v3, Rect rect) {
            return false;
        }

        public int c(CoordinatorLayout coordinatorLayout, V v3) {
            return -16777216;
        }

        public float d(CoordinatorLayout coordinatorLayout, V v3) {
            return 0.0f;
        }

        public boolean e(CoordinatorLayout coordinatorLayout, V v3, View view) {
            return false;
        }

        public b0 f(CoordinatorLayout coordinatorLayout, V v3, b0 b0Var) {
            return b0Var;
        }

        public void g(f fVar) {
        }

        public boolean h(CoordinatorLayout coordinatorLayout, V v3, View view) {
            return false;
        }

        public void i(CoordinatorLayout coordinatorLayout, V v3, View view) {
        }

        public void j() {
        }

        public boolean k(CoordinatorLayout coordinatorLayout, V v3, MotionEvent motionEvent) {
            return false;
        }

        public boolean l(CoordinatorLayout coordinatorLayout, V v3, int i3) {
            return false;
        }

        public boolean m(CoordinatorLayout coordinatorLayout, V v3, int i3, int i4, int i5, int i6) {
            return false;
        }

        public boolean n(CoordinatorLayout coordinatorLayout, V v3, View view, float f3, float f4, boolean z3) {
            return false;
        }

        public boolean o(CoordinatorLayout coordinatorLayout, V v3, View view, float f3, float f4) {
            return false;
        }

        @Deprecated
        public void p(CoordinatorLayout coordinatorLayout, V v3, View view, int i3, int i4, int[] iArr) {
        }

        public void q(CoordinatorLayout coordinatorLayout, V v3, View view, int i3, int i4, int[] iArr, int i5) {
            if (i5 == 0) {
                p(coordinatorLayout, v3, view, i3, i4, iArr);
            }
        }

        @Deprecated
        public void r(CoordinatorLayout coordinatorLayout, V v3, View view, int i3, int i4, int i5, int i6) {
        }

        @Deprecated
        public void s(CoordinatorLayout coordinatorLayout, V v3, View view, int i3, int i4, int i5, int i6, int i7) {
            if (i7 == 0) {
                r(coordinatorLayout, v3, view, i3, i4, i5, i6);
            }
        }

        public void t(CoordinatorLayout coordinatorLayout, V v3, View view, int i3, int i4, int i5, int i6, int i7, int[] iArr) {
            iArr[0] = iArr[0] + i5;
            iArr[1] = iArr[1] + i6;
            s(coordinatorLayout, v3, view, i3, i4, i5, i6, i7);
        }

        @Deprecated
        public void u(CoordinatorLayout coordinatorLayout, V v3, View view, View view2, int i3) {
        }

        public void v(CoordinatorLayout coordinatorLayout, V v3, View view, View view2, int i3, int i4) {
            if (i4 == 0) {
                u(coordinatorLayout, v3, view, view2, i3);
            }
        }

        public boolean w(CoordinatorLayout coordinatorLayout, V v3, Rect rect, boolean z3) {
            return false;
        }

        public void x(CoordinatorLayout coordinatorLayout, V v3, Parcelable parcelable) {
        }

        public Parcelable y(CoordinatorLayout coordinatorLayout, V v3) {
            return View.BaseSavedState.EMPTY_STATE;
        }

        @Deprecated
        public boolean z(CoordinatorLayout coordinatorLayout, V v3, View view, View view2, int i3) {
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

    public class e implements ViewGroup.OnHierarchyChangeListener {
        public e() {
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewAdded(View view, View view2) {
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = CoordinatorLayout.this.f1091x;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewAdded(view, view2);
            }
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewRemoved(View view, View view2) {
            CoordinatorLayout.this.L(2);
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = CoordinatorLayout.this.f1091x;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewRemoved(view, view2);
            }
        }
    }

    public class g implements ViewTreeObserver.OnPreDrawListener {
        public g() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            CoordinatorLayout.this.L(0);
            return true;
        }
    }

    public static class h implements Comparator<View> {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(View view, View view2) {
            float fJ = t.J(view);
            float fJ2 = t.J(view2);
            if (fJ > fJ2) {
                return -1;
            }
            return fJ < fJ2 ? 1 : 0;
        }
    }

    static {
        Package r02 = CoordinatorLayout.class.getPackage();
        f1069b = r02 != null ? r02.getName() : null;
        if (Build.VERSION.SDK_INT >= 21) {
            f1072e = new h();
        } else {
            f1072e = null;
        }
        f1070c = new Class[]{Context.class, AttributeSet.class};
        f1071d = new ThreadLocal<>();
        f1073f = new k0.g(12);
    }

    public CoordinatorLayout(Context context) {
        this(context, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static c O(Context context, AttributeSet attributeSet, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.startsWith(".")) {
            str = context.getPackageName() + str;
        } else if (str.indexOf(46) < 0) {
            String str2 = f1069b;
            if (!TextUtils.isEmpty(str2)) {
                str = str2 + '.' + str;
            }
        }
        try {
            ThreadLocal<Map<String, Constructor<c>>> threadLocal = f1071d;
            Map<String, Constructor<c>> map = threadLocal.get();
            if (map == null) {
                map = new HashMap<>();
                threadLocal.set(map);
            }
            Constructor<c> constructor = map.get(str);
            if (constructor == null) {
                constructor = Class.forName(str, false, context.getClassLoader()).getConstructor(f1070c);
                constructor.setAccessible(true);
                map.put(str, constructor);
            }
            return constructor.newInstance(context, attributeSet);
        } catch (Exception e3) {
            throw new RuntimeException("Could not inflate Behavior subclass " + str, e3);
        }
    }

    public static void S(Rect rect) {
        rect.setEmpty();
        f1073f.a(rect);
    }

    public static int V(int i3) {
        if (i3 == 0) {
            return 17;
        }
        return i3;
    }

    public static int W(int i3) {
        if ((i3 & 7) == 0) {
            i3 |= 8388611;
        }
        return (i3 & 112) == 0 ? i3 | 48 : i3;
    }

    public static int X(int i3) {
        if (i3 == 0) {
            return 8388661;
        }
        return i3;
    }

    public static Rect e() {
        Rect rectB = f1073f.b();
        return rectB == null ? new Rect() : rectB;
    }

    public static int g(int i3, int i4, int i5) {
        return i3 < i4 ? i4 : i3 > i5 ? i5 : i3;
    }

    public final int A(int i3) {
        int[] iArr = this.f1083p;
        if (iArr == null) {
            Log.e("CoordinatorLayout", "No keylines defined for " + this + " - attempted index lookup " + i3);
            return 0;
        }
        if (i3 >= 0 && i3 < iArr.length) {
            return iArr[i3];
        }
        Log.e("CoordinatorLayout", "Keyline index " + i3 + " out of range for " + this);
        return 0;
    }

    public void B(View view, Rect rect) {
        rect.set(((f) view.getLayoutParams()).h());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public f C(View view) {
        f fVar = (f) view.getLayoutParams();
        if (!fVar.f1098b) {
            if (view instanceof b) {
                c behavior = ((b) view).getBehavior();
                if (behavior == null) {
                    Log.e("CoordinatorLayout", "Attached behavior class is null");
                }
                fVar.o(behavior);
                fVar.f1098b = true;
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
                    } catch (Exception e3) {
                        Log.e("CoordinatorLayout", "Default behavior class " + dVar.value().getName() + " could not be instantiated. Did you forget a default constructor?", e3);
                    }
                }
                fVar.f1098b = true;
            }
        }
        return fVar;
    }

    public final void D(List<View> list) {
        list.clear();
        boolean zIsChildrenDrawingOrderEnabled = isChildrenDrawingOrderEnabled();
        int childCount = getChildCount();
        for (int i3 = childCount - 1; i3 >= 0; i3--) {
            list.add(getChildAt(zIsChildrenDrawingOrderEnabled ? getChildDrawingOrder(childCount, i3) : i3));
        }
        Comparator<View> comparator = f1072e;
        if (comparator != null) {
            Collections.sort(list, comparator);
        }
    }

    public final boolean E(View view) {
        return this.f1075h.j(view);
    }

    public boolean F(View view, int i3, int i4) {
        Rect rectE = e();
        x(view, rectE);
        try {
            return rectE.contains(i3, i4);
        } finally {
            S(rectE);
        }
    }

    public final void G(View view, int i3) {
        f fVar = (f) view.getLayoutParams();
        Rect rectE = e();
        rectE.set(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) fVar).leftMargin, getPaddingTop() + ((ViewGroup.MarginLayoutParams) fVar).topMargin, (getWidth() - getPaddingRight()) - ((ViewGroup.MarginLayoutParams) fVar).rightMargin, (getHeight() - getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin);
        if (this.f1088u != null && t.w(this) && !t.w(view)) {
            rectE.left += this.f1088u.f();
            rectE.top += this.f1088u.h();
            rectE.right -= this.f1088u.g();
            rectE.bottom -= this.f1088u.e();
        }
        Rect rectE2 = e();
        l0.d.a(W(fVar.f1099c), view.getMeasuredWidth(), view.getMeasuredHeight(), rectE, rectE2, i3);
        view.layout(rectE2.left, rectE2.top, rectE2.right, rectE2.bottom);
        S(rectE);
        S(rectE2);
    }

    public final void H(View view, View view2, int i3) {
        Rect rectE = e();
        Rect rectE2 = e();
        try {
            x(view2, rectE);
            y(view, i3, rectE, rectE2);
            view.layout(rectE2.left, rectE2.top, rectE2.right, rectE2.bottom);
        } finally {
            S(rectE);
            S(rectE2);
        }
    }

    public final void I(View view, int i3, int i4) {
        f fVar = (f) view.getLayoutParams();
        int iB = l0.d.b(X(fVar.f1099c), i4);
        int i5 = iB & 7;
        int i6 = iB & 112;
        int width = getWidth();
        int height = getHeight();
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        if (i4 == 1) {
            i3 = width - i3;
        }
        int iA = A(i3) - measuredWidth;
        int i7 = 0;
        if (i5 == 1) {
            iA += measuredWidth / 2;
        } else if (i5 == 5) {
            iA += measuredWidth;
        }
        if (i6 == 16) {
            i7 = 0 + (measuredHeight / 2);
        } else if (i6 == 80) {
            i7 = measuredHeight + 0;
        }
        int iMax = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) fVar).leftMargin, Math.min(iA, ((width - getPaddingRight()) - measuredWidth) - ((ViewGroup.MarginLayoutParams) fVar).rightMargin));
        int iMax2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) fVar).topMargin, Math.min(i7, ((height - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin));
        view.layout(iMax, iMax2, measuredWidth + iMax, measuredHeight + iMax2);
    }

    public final void J(View view, Rect rect, int i3) {
        boolean z3;
        boolean z4;
        int width;
        int i4;
        int i5;
        int i6;
        int height;
        int i7;
        int i8;
        int i9;
        if (t.P(view) && view.getWidth() > 0 && view.getHeight() > 0) {
            f fVar = (f) view.getLayoutParams();
            c cVarF = fVar.f();
            Rect rectE = e();
            Rect rectE2 = e();
            rectE2.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            if (cVarF == null || !cVarF.b(this, view, rectE)) {
                rectE.set(rectE2);
            } else if (!rectE2.contains(rectE)) {
                throw new IllegalArgumentException("Rect should be within the child's bounds. Rect:" + rectE.toShortString() + " | Bounds:" + rectE2.toShortString());
            }
            S(rectE2);
            if (rectE.isEmpty()) {
                S(rectE);
                return;
            }
            int iB = l0.d.b(fVar.f1104h, i3);
            boolean z5 = true;
            if ((iB & 48) != 48 || (i8 = (rectE.top - ((ViewGroup.MarginLayoutParams) fVar).topMargin) - fVar.f1106j) >= (i9 = rect.top)) {
                z3 = false;
            } else {
                Z(view, i9 - i8);
                z3 = true;
            }
            if ((iB & 80) == 80 && (height = ((getHeight() - rectE.bottom) - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin) + fVar.f1106j) < (i7 = rect.bottom)) {
                Z(view, height - i7);
                z3 = true;
            }
            if (!z3) {
                Z(view, 0);
            }
            if ((iB & 3) != 3 || (i5 = (rectE.left - ((ViewGroup.MarginLayoutParams) fVar).leftMargin) - fVar.f1105i) >= (i6 = rect.left)) {
                z4 = false;
            } else {
                Y(view, i6 - i5);
                z4 = true;
            }
            if ((iB & 5) != 5 || (width = ((getWidth() - rectE.right) - ((ViewGroup.MarginLayoutParams) fVar).rightMargin) + fVar.f1105i) >= (i4 = rect.right)) {
                z5 = z4;
            } else {
                Y(view, width - i4);
            }
            if (!z5) {
                Y(view, 0);
            }
            S(rectE);
        }
    }

    public void K(View view, int i3) {
        c cVarF;
        f fVar = (f) view.getLayoutParams();
        if (fVar.f1107k != null) {
            Rect rectE = e();
            Rect rectE2 = e();
            Rect rectE3 = e();
            x(fVar.f1107k, rectE);
            u(view, false, rectE2);
            int measuredWidth = view.getMeasuredWidth();
            int measuredHeight = view.getMeasuredHeight();
            z(view, i3, rectE, rectE3, fVar, measuredWidth, measuredHeight);
            boolean z3 = (rectE3.left == rectE2.left && rectE3.top == rectE2.top) ? false : true;
            k(fVar, rectE3, measuredWidth, measuredHeight);
            int i4 = rectE3.left - rectE2.left;
            int i5 = rectE3.top - rectE2.top;
            if (i4 != 0) {
                t.U(view, i4);
            }
            if (i5 != 0) {
                t.V(view, i5);
            }
            if (z3 && (cVarF = fVar.f()) != null) {
                cVarF.h(this, view, fVar.f1107k);
            }
            S(rectE);
            S(rectE2);
            S(rectE3);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00ca  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void L(int r18) {
        /*
            Method dump skipped, instruction units count: 273
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.L(int):void");
    }

    public void M(View view, int i3) {
        f fVar = (f) view.getLayoutParams();
        if (fVar.a()) {
            throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
        }
        View view2 = fVar.f1107k;
        if (view2 != null) {
            H(view, view2, i3);
            return;
        }
        int i4 = fVar.f1101e;
        if (i4 >= 0) {
            I(view, i4, i3);
        } else {
            G(view, i3);
        }
    }

    public void N(View view, int i3, int i4, int i5, int i6) {
        measureChildWithMargins(view, i3, i4, i5, i6);
    }

    public final boolean P(MotionEvent motionEvent, int i3) {
        int actionMasked = motionEvent.getActionMasked();
        List<View> list = this.f1076i;
        D(list);
        int size = list.size();
        MotionEvent motionEventObtain = null;
        boolean zK = false;
        boolean z3 = false;
        for (int i4 = 0; i4 < size; i4++) {
            View view = list.get(i4);
            f fVar = (f) view.getLayoutParams();
            c cVarF = fVar.f();
            if (!(zK || z3) || actionMasked == 0) {
                if (!zK && cVarF != null) {
                    if (i3 == 0) {
                        zK = cVarF.k(this, view, motionEvent);
                    } else if (i3 == 1) {
                        zK = cVarF.D(this, view, motionEvent);
                    }
                    if (zK) {
                        this.f1084q = view;
                    }
                }
                boolean zC = fVar.c();
                boolean zI = fVar.i(this, view);
                z3 = zI && !zC;
                if (zI && !z3) {
                    break;
                }
            } else if (cVarF != null) {
                if (motionEventObtain == null) {
                    long jUptimeMillis = SystemClock.uptimeMillis();
                    motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                }
                if (i3 == 0) {
                    cVarF.k(this, view, motionEventObtain);
                } else if (i3 == 1) {
                    cVarF.D(this, view, motionEventObtain);
                }
            }
        }
        list.clear();
        return zK;
    }

    public final void Q() {
        this.f1074g.clear();
        this.f1075h.c();
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            f fVarC = C(childAt);
            fVarC.d(this, childAt);
            this.f1075h.b(childAt);
            for (int i4 = 0; i4 < childCount; i4++) {
                if (i4 != i3) {
                    View childAt2 = getChildAt(i4);
                    if (fVarC.b(this, childAt, childAt2)) {
                        if (!this.f1075h.d(childAt2)) {
                            this.f1075h.b(childAt2);
                        }
                        this.f1075h.a(childAt2, childAt);
                    }
                }
            }
        }
        this.f1074g.addAll(this.f1075h.i());
        Collections.reverse(this.f1074g);
    }

    public void R(View view, Rect rect) {
        ((f) view.getLayoutParams()).q(rect);
    }

    public void T() {
        if (this.f1082o && this.f1086s != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.f1086s);
        }
        this.f1087t = false;
    }

    public final void U(boolean z3) {
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            c cVarF = ((f) childAt.getLayoutParams()).f();
            if (cVarF != null) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                if (z3) {
                    cVarF.k(this, childAt, motionEventObtain);
                } else {
                    cVarF.D(this, childAt, motionEventObtain);
                }
                motionEventObtain.recycle();
            }
        }
        for (int i4 = 0; i4 < childCount; i4++) {
            ((f) getChildAt(i4).getLayoutParams()).m();
        }
        this.f1084q = null;
        this.f1081n = false;
    }

    public final void Y(View view, int i3) {
        f fVar = (f) view.getLayoutParams();
        int i4 = fVar.f1105i;
        if (i4 != i3) {
            t.U(view, i3 - i4);
            fVar.f1105i = i3;
        }
    }

    public final void Z(View view, int i3) {
        f fVar = (f) view.getLayoutParams();
        int i4 = fVar.f1106j;
        if (i4 != i3) {
            t.V(view, i3 - i4);
            fVar.f1106j = i3;
        }
    }

    public final b0 a0(b0 b0Var) {
        if (k0.c.a(this.f1088u, b0Var)) {
            return b0Var;
        }
        this.f1088u = b0Var;
        boolean z3 = b0Var != null && b0Var.h() > 0;
        this.f1089v = z3;
        setWillNotDraw(!z3 && getBackground() == null);
        b0 b0VarL = l(b0Var);
        requestLayout();
        return b0VarL;
    }

    public final void b0() {
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        if (!t.w(this)) {
            t.v0(this, null);
            return;
        }
        if (this.f1092y == null) {
            this.f1092y = new a();
        }
        t.v0(this, this.f1092y);
        setSystemUiVisibility(1280);
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof f) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j3) {
        f fVar = (f) view.getLayoutParams();
        c cVar = fVar.f1097a;
        if (cVar != null) {
            float fD = cVar.d(this, view);
            if (fD > 0.0f) {
                if (this.f1078k == null) {
                    this.f1078k = new Paint();
                }
                this.f1078k.setColor(fVar.f1097a.c(this, view));
                this.f1078k.setAlpha(g(Math.round(fD * 255.0f), 0, 255));
                int iSave = canvas.save();
                if (view.isOpaque()) {
                    canvas.clipRect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom(), Region.Op.DIFFERENCE);
                }
                canvas.drawRect(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom(), this.f1078k);
                canvas.restoreToCount(iSave);
            }
        }
        return super.drawChild(canvas, view, j3);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.f1090w;
        boolean state = false;
        if (drawable != null && drawable.isStateful()) {
            state = false | drawable.setState(drawableState);
        }
        if (state) {
            invalidate();
        }
    }

    public void f() {
        if (this.f1082o) {
            if (this.f1086s == null) {
                this.f1086s = new g();
            }
            getViewTreeObserver().addOnPreDrawListener(this.f1086s);
        }
        this.f1087t = true;
    }

    public final List<View> getDependencySortedChildren() {
        Q();
        return Collections.unmodifiableList(this.f1074g);
    }

    public final b0 getLastWindowInsets() {
        return this.f1088u;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.f1093z.a();
    }

    public Drawable getStatusBarBackground() {
        return this.f1090w;
    }

    @Override // android.view.View
    public int getSuggestedMinimumHeight() {
        return Math.max(super.getSuggestedMinimumHeight(), getPaddingTop() + getPaddingBottom());
    }

    @Override // android.view.View
    public int getSuggestedMinimumWidth() {
        return Math.max(super.getSuggestedMinimumWidth(), getPaddingLeft() + getPaddingRight());
    }

    @Override // l0.l
    public void h(View view, View view2, int i3, int i4) {
        c cVarF;
        this.f1093z.c(view, view2, i3, i4);
        this.f1085r = view2;
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            f fVar = (f) childAt.getLayoutParams();
            if (fVar.j(i4) && (cVarF = fVar.f()) != null) {
                cVarF.v(this, childAt, view, view2, i3, i4);
            }
        }
    }

    @Override // l0.l
    public void i(View view, int i3) {
        this.f1093z.d(view, i3);
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            f fVar = (f) childAt.getLayoutParams();
            if (fVar.j(i3)) {
                c cVarF = fVar.f();
                if (cVarF != null) {
                    cVarF.C(this, childAt, view, i3);
                }
                fVar.l(i3);
                fVar.k();
            }
        }
        this.f1085r = null;
    }

    @Override // l0.l
    public void j(View view, int i3, int i4, int[] iArr, int i5) {
        c cVarF;
        int childCount = getChildCount();
        boolean z3 = false;
        int iMax = 0;
        int iMax2 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                f fVar = (f) childAt.getLayoutParams();
                if (fVar.j(i5) && (cVarF = fVar.f()) != null) {
                    int[] iArr2 = this.f1079l;
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    cVarF.q(this, childAt, view, i3, i4, iArr2, i5);
                    int[] iArr3 = this.f1079l;
                    iMax = i3 > 0 ? Math.max(iMax, iArr3[0]) : Math.min(iMax, iArr3[0]);
                    int[] iArr4 = this.f1079l;
                    iMax2 = i4 > 0 ? Math.max(iMax2, iArr4[1]) : Math.min(iMax2, iArr4[1]);
                    z3 = true;
                }
            }
        }
        iArr[0] = iMax;
        iArr[1] = iMax2;
        if (z3) {
            L(1);
        }
    }

    public final void k(f fVar, Rect rect, int i3, int i4) {
        int width = getWidth();
        int height = getHeight();
        int iMax = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) fVar).leftMargin, Math.min(rect.left, ((width - getPaddingRight()) - i3) - ((ViewGroup.MarginLayoutParams) fVar).rightMargin));
        int iMax2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) fVar).topMargin, Math.min(rect.top, ((height - getPaddingBottom()) - i4) - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin));
        rect.set(iMax, iMax2, i3 + iMax, i4 + iMax2);
    }

    public final b0 l(b0 b0Var) {
        c cVarF;
        if (b0Var.m()) {
            return b0Var;
        }
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (t.w(childAt) && (cVarF = ((f) childAt.getLayoutParams()).f()) != null) {
                b0Var = cVarF.f(this, childAt, b0Var);
                if (b0Var.m()) {
                    break;
                }
            }
        }
        return b0Var;
    }

    @Override // l0.m
    public void m(View view, int i3, int i4, int i5, int i6, int i7, int[] iArr) {
        c cVarF;
        boolean z3;
        int iMin;
        int childCount = getChildCount();
        boolean z4 = false;
        int iMax = 0;
        int i8 = 0;
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                f fVar = (f) childAt.getLayoutParams();
                if (fVar.j(i7) && (cVarF = fVar.f()) != null) {
                    int[] iArr2 = this.f1079l;
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    cVarF.t(this, childAt, view, i3, i4, i5, i6, i7, iArr2);
                    int[] iArr3 = this.f1079l;
                    iMax = i5 > 0 ? Math.max(iMax, iArr3[0]) : Math.min(iMax, iArr3[0]);
                    if (i6 > 0) {
                        z3 = true;
                        iMin = Math.max(i8, this.f1079l[1]);
                    } else {
                        z3 = true;
                        iMin = Math.min(i8, this.f1079l[1]);
                    }
                    i8 = iMin;
                    z4 = z3;
                }
            }
        }
        iArr[0] = iArr[0] + iMax;
        iArr[1] = iArr[1] + i8;
        if (z4) {
            L(1);
        }
    }

    @Override // l0.l
    public void n(View view, int i3, int i4, int i5, int i6, int i7) {
        m(view, i3, i4, i5, i6, 0, this.f1080m);
    }

    @Override // l0.l
    public boolean o(View view, View view2, int i3, int i4) {
        int childCount = getChildCount();
        boolean z3 = false;
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                f fVar = (f) childAt.getLayoutParams();
                c cVarF = fVar.f();
                if (cVarF != null) {
                    boolean zA = cVarF.A(this, childAt, view, view2, i3, i4);
                    z3 |= zA;
                    fVar.r(i4, zA);
                } else {
                    fVar.r(i4, false);
                }
            }
        }
        return z3;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        U(false);
        if (this.f1087t) {
            if (this.f1086s == null) {
                this.f1086s = new g();
            }
            getViewTreeObserver().addOnPreDrawListener(this.f1086s);
        }
        if (this.f1088u == null && t.w(this)) {
            t.g0(this);
        }
        this.f1082o = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        U(false);
        if (this.f1087t && this.f1086s != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.f1086s);
        }
        View view = this.f1085r;
        if (view != null) {
            onStopNestedScroll(view);
        }
        this.f1082o = false;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.f1089v || this.f1090w == null) {
            return;
        }
        b0 b0Var = this.f1088u;
        int iH = b0Var != null ? b0Var.h() : 0;
        if (iH > 0) {
            this.f1090w.setBounds(0, 0, getWidth(), iH);
            this.f1090w.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            U(true);
        }
        boolean zP = P(motionEvent, 0);
        if (actionMasked == 1 || actionMasked == 3) {
            U(true);
        }
        return zP;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z3, int i3, int i4, int i5, int i6) {
        c cVarF;
        int iZ = t.z(this);
        int size = this.f1074g.size();
        for (int i7 = 0; i7 < size; i7++) {
            View view = this.f1074g.get(i7);
            if (view.getVisibility() != 8 && ((cVarF = ((f) view.getLayoutParams()).f()) == null || !cVarF.l(this, view, iZ))) {
                M(view, iZ);
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
    public void onMeasure(int r31, int r32) {
        /*
            Method dump skipped, instruction units count: 391
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.onMeasure(int, int):void");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, l0.n
    public boolean onNestedFling(View view, float f3, float f4, boolean z3) {
        c cVarF;
        int childCount = getChildCount();
        boolean zN = false;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                f fVar = (f) childAt.getLayoutParams();
                if (fVar.j(0) && (cVarF = fVar.f()) != null) {
                    zN |= cVarF.n(this, childAt, view, f3, f4, z3);
                }
            }
        }
        if (zN) {
            L(1);
        }
        return zN;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, l0.n
    public boolean onNestedPreFling(View view, float f3, float f4) {
        c cVarF;
        int childCount = getChildCount();
        boolean zO = false;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                f fVar = (f) childAt.getLayoutParams();
                if (fVar.j(0) && (cVarF = fVar.f()) != null) {
                    zO |= cVarF.o(this, childAt, view, f3, f4);
                }
            }
        }
        return zO;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, l0.n
    public void onNestedPreScroll(View view, int i3, int i4, int[] iArr) {
        j(view, i3, i4, iArr, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, l0.n
    public void onNestedScroll(View view, int i3, int i4, int i5, int i6) {
        n(view, i3, i4, i5, i6, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, l0.n
    public void onNestedScrollAccepted(View view, View view2, int i3) {
        h(view, view2, i3, 0);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.f());
        SparseArray<Parcelable> sparseArray = savedState.f1094d;
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            int id = childAt.getId();
            c cVarF = C(childAt).f();
            if (id != -1 && cVarF != null && (parcelable2 = sparseArray.get(id)) != null) {
                cVarF.x(this, childAt, parcelable2);
            }
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        Parcelable parcelableY;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            int id = childAt.getId();
            c cVarF = ((f) childAt.getLayoutParams()).f();
            if (id != -1 && cVarF != null && (parcelableY = cVarF.y(this, childAt)) != null) {
                sparseArray.append(id, parcelableY);
            }
        }
        savedState.f1094d = sparseArray;
        return savedState;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, l0.n
    public boolean onStartNestedScroll(View view, View view2, int i3) {
        return o(view, view2, i3, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, l0.n
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
            android.view.View r3 = r0.f1084q
            r4 = 1
            r5 = 0
            if (r3 != 0) goto L15
            boolean r3 = r0.P(r1, r4)
            if (r3 == 0) goto L2b
            goto L16
        L15:
            r3 = r5
        L16:
            android.view.View r6 = r0.f1084q
            android.view.ViewGroup$LayoutParams r6 = r6.getLayoutParams()
            androidx.coordinatorlayout.widget.CoordinatorLayout$f r6 = (androidx.coordinatorlayout.widget.CoordinatorLayout.f) r6
            androidx.coordinatorlayout.widget.CoordinatorLayout$c r6 = r6.f()
            if (r6 == 0) goto L2b
            android.view.View r7 = r0.f1084q
            boolean r6 = r6.D(r0, r7, r1)
            goto L2c
        L2b:
            r6 = r5
        L2c:
            android.view.View r7 = r0.f1084q
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
            r0.U(r5)
        L57:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void p(View view) {
        List listG = this.f1075h.g(view);
        if (listG == null || listG.isEmpty()) {
            return;
        }
        for (int i3 = 0; i3 < listG.size(); i3++) {
            View view2 = (View) listG.get(i3);
            c cVarF = ((f) view2.getLayoutParams()).f();
            if (cVarF != null) {
                cVarF.h(this, view2, view);
            }
        }
    }

    public void q() {
        int childCount = getChildCount();
        boolean z3 = false;
        int i3 = 0;
        while (true) {
            if (i3 >= childCount) {
                break;
            }
            if (E(getChildAt(i3))) {
                z3 = true;
                break;
            }
            i3++;
        }
        if (z3 != this.f1087t) {
            if (z3) {
                f();
            } else {
                T();
            }
        }
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: r, reason: merged with bridge method [inline-methods] */
    public f generateDefaultLayoutParams() {
        return new f(-2, -2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z3) {
        c cVarF = ((f) view.getLayoutParams()).f();
        if (cVarF == null || !cVarF.w(this, view, rect, z3)) {
            return super.requestChildRectangleOnScreen(view, rect, z3);
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z3) {
        super.requestDisallowInterceptTouchEvent(z3);
        if (!z3 || this.f1081n) {
            return;
        }
        U(false);
        this.f1081n = true;
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: s, reason: merged with bridge method [inline-methods] */
    public f generateLayoutParams(AttributeSet attributeSet) {
        return new f(getContext(), attributeSet);
    }

    @Override // android.view.View
    public void setFitsSystemWindows(boolean z3) {
        super.setFitsSystemWindows(z3);
        b0();
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.f1091x = onHierarchyChangeListener;
    }

    public void setStatusBarBackground(Drawable drawable) {
        Drawable drawable2 = this.f1090w;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.f1090w = drawableMutate;
            if (drawableMutate != null) {
                if (drawableMutate.isStateful()) {
                    this.f1090w.setState(getDrawableState());
                }
                e0.a.m(this.f1090w, t.z(this));
                this.f1090w.setVisible(getVisibility() == 0, false);
                this.f1090w.setCallback(this);
            }
            t.a0(this);
        }
    }

    public void setStatusBarBackgroundColor(int i3) {
        setStatusBarBackground(new ColorDrawable(i3));
    }

    public void setStatusBarBackgroundResource(int i3) {
        setStatusBarBackground(i3 != 0 ? b0.a.d(getContext(), i3) : null);
    }

    @Override // android.view.View
    public void setVisibility(int i3) {
        super.setVisibility(i3);
        boolean z3 = i3 == 0;
        Drawable drawable = this.f1090w;
        if (drawable == null || drawable.isVisible() == z3) {
            return;
        }
        this.f1090w.setVisible(z3, false);
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: t, reason: merged with bridge method [inline-methods] */
    public f generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof f ? new f((f) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new f((ViewGroup.MarginLayoutParams) layoutParams) : new f(layoutParams);
    }

    public void u(View view, boolean z3, Rect rect) {
        if (view.isLayoutRequested() || view.getVisibility() == 8) {
            rect.setEmpty();
        } else if (z3) {
            x(view, rect);
        } else {
            rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
    }

    public List<View> v(View view) {
        List<View> listH = this.f1075h.h(view);
        this.f1077j.clear();
        if (listH != null) {
            this.f1077j.addAll(listH);
        }
        return this.f1077j;
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f1090w;
    }

    public List<View> w(View view) {
        List listG = this.f1075h.g(view);
        this.f1077j.clear();
        if (listG != null) {
            this.f1077j.addAll(listG);
        }
        return this.f1077j;
    }

    public void x(View view, Rect rect) {
        z.b.a(this, view, rect);
    }

    public void y(View view, int i3, Rect rect, Rect rect2) {
        f fVar = (f) view.getLayoutParams();
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        z(view, i3, rect, rect2, fVar, measuredWidth, measuredHeight);
        k(fVar, rect2, measuredWidth, measuredHeight);
    }

    public final void z(View view, int i3, Rect rect, Rect rect2, f fVar, int i4, int i5) {
        int iB = l0.d.b(V(fVar.f1099c), i3);
        int iB2 = l0.d.b(W(fVar.f1100d), i3);
        int i6 = iB & 7;
        int i7 = iB & 112;
        int i8 = iB2 & 7;
        int i9 = iB2 & 112;
        int iWidth = i8 != 1 ? i8 != 5 ? rect.left : rect.right : rect.left + (rect.width() / 2);
        int iHeight = i9 != 16 ? i9 != 80 ? rect.top : rect.bottom : rect.top + (rect.height() / 2);
        if (i6 == 1) {
            iWidth -= i4 / 2;
        } else if (i6 != 5) {
            iWidth -= i4;
        }
        if (i7 == 16) {
            iHeight -= i5 / 2;
        } else if (i7 != 80) {
            iHeight -= i5;
        }
        rect2.set(iWidth, iHeight, i4 + iWidth, i5 + iHeight);
    }

    public CoordinatorLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.coordinatorLayoutStyle);
    }

    public CoordinatorLayout(Context context, AttributeSet attributeSet, int i3) {
        TypedArray typedArrayObtainStyledAttributes;
        super(context, attributeSet, i3);
        this.f1074g = new ArrayList();
        this.f1075h = new z.a<>();
        this.f1076i = new ArrayList();
        this.f1077j = new ArrayList();
        this.f1079l = new int[2];
        this.f1080m = new int[2];
        this.f1093z = new o(this);
        if (i3 == 0) {
            typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CoordinatorLayout, 0, R$style.Widget_Support_CoordinatorLayout);
        } else {
            typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CoordinatorLayout, i3, 0);
        }
        if (Build.VERSION.SDK_INT >= 29) {
            if (i3 == 0) {
                saveAttributeDataForStyleable(context, R$styleable.CoordinatorLayout, attributeSet, typedArrayObtainStyledAttributes, 0, R$style.Widget_Support_CoordinatorLayout);
            } else {
                saveAttributeDataForStyleable(context, R$styleable.CoordinatorLayout, attributeSet, typedArrayObtainStyledAttributes, i3, 0);
            }
        }
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.CoordinatorLayout_keylines, 0);
        if (resourceId != 0) {
            Resources resources = context.getResources();
            this.f1083p = resources.getIntArray(resourceId);
            float f3 = resources.getDisplayMetrics().density;
            int length = this.f1083p.length;
            for (int i4 = 0; i4 < length; i4++) {
                this.f1083p[i4] = (int) (r12[i4] * f3);
            }
        }
        this.f1090w = typedArrayObtainStyledAttributes.getDrawable(R$styleable.CoordinatorLayout_statusBarBackground);
        typedArrayObtainStyledAttributes.recycle();
        b0();
        super.setOnHierarchyChangeListener(new e());
        if (t.x(this) == 0) {
            t.t0(this, 1);
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public SparseArray<Parcelable> f1094d;

        public static class a implements Parcelable.ClassLoaderCreator<SavedState> {
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
            public SavedState[] newArray(int i3) {
                return new SavedState[i3];
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            int i3 = parcel.readInt();
            int[] iArr = new int[i3];
            parcel.readIntArray(iArr);
            Parcelable[] parcelableArray = parcel.readParcelableArray(classLoader);
            this.f1094d = new SparseArray<>(i3);
            for (int i4 = 0; i4 < i3; i4++) {
                this.f1094d.append(iArr[i4], parcelableArray[i4]);
            }
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i3) {
            super.writeToParcel(parcel, i3);
            SparseArray<Parcelable> sparseArray = this.f1094d;
            int size = sparseArray != null ? sparseArray.size() : 0;
            parcel.writeInt(size);
            int[] iArr = new int[size];
            Parcelable[] parcelableArr = new Parcelable[size];
            for (int i4 = 0; i4 < size; i4++) {
                iArr[i4] = this.f1094d.keyAt(i4);
                parcelableArr[i4] = this.f1094d.valueAt(i4);
            }
            parcel.writeIntArray(iArr);
            parcel.writeParcelableArray(parcelableArr, i3);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public static class f extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public c f1097a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1098b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1099c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1100d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1101e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1102f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f1103g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f1104h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f1105i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public int f1106j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public View f1107k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public View f1108l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public boolean f1109m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public boolean f1110n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public boolean f1111o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public boolean f1112p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public final Rect f1113q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public Object f1114r;

        public f(int i3, int i4) {
            super(i3, i4);
            this.f1098b = false;
            this.f1099c = 0;
            this.f1100d = 0;
            this.f1101e = -1;
            this.f1102f = -1;
            this.f1103g = 0;
            this.f1104h = 0;
            this.f1113q = new Rect();
        }

        public boolean a() {
            return this.f1107k == null && this.f1102f != -1;
        }

        public boolean b(CoordinatorLayout coordinatorLayout, View view, View view2) {
            c cVar;
            return view2 == this.f1108l || s(view2, t.z(coordinatorLayout)) || ((cVar = this.f1097a) != null && cVar.e(coordinatorLayout, view, view2));
        }

        public boolean c() {
            if (this.f1097a == null) {
                this.f1109m = false;
            }
            return this.f1109m;
        }

        public View d(CoordinatorLayout coordinatorLayout, View view) {
            if (this.f1102f == -1) {
                this.f1108l = null;
                this.f1107k = null;
                return null;
            }
            if (this.f1107k == null || !t(view, coordinatorLayout)) {
                n(view, coordinatorLayout);
            }
            return this.f1107k;
        }

        public int e() {
            return this.f1102f;
        }

        public c f() {
            return this.f1097a;
        }

        public boolean g() {
            return this.f1112p;
        }

        public Rect h() {
            return this.f1113q;
        }

        public boolean i(CoordinatorLayout coordinatorLayout, View view) {
            boolean z3 = this.f1109m;
            if (z3) {
                return true;
            }
            c cVar = this.f1097a;
            boolean zA = (cVar != null ? cVar.a(coordinatorLayout, view) : false) | z3;
            this.f1109m = zA;
            return zA;
        }

        public boolean j(int i3) {
            if (i3 == 0) {
                return this.f1110n;
            }
            if (i3 != 1) {
                return false;
            }
            return this.f1111o;
        }

        public void k() {
            this.f1112p = false;
        }

        public void l(int i3) {
            r(i3, false);
        }

        public void m() {
            this.f1109m = false;
        }

        public final void n(View view, CoordinatorLayout coordinatorLayout) {
            View viewFindViewById = coordinatorLayout.findViewById(this.f1102f);
            this.f1107k = viewFindViewById;
            if (viewFindViewById == null) {
                if (coordinatorLayout.isInEditMode()) {
                    this.f1108l = null;
                    this.f1107k = null;
                    return;
                }
                throw new IllegalStateException("Could not find CoordinatorLayout descendant view with id " + coordinatorLayout.getResources().getResourceName(this.f1102f) + " to anchor view " + view);
            }
            if (viewFindViewById == coordinatorLayout) {
                if (!coordinatorLayout.isInEditMode()) {
                    throw new IllegalStateException("View can not be anchored to the the parent CoordinatorLayout");
                }
                this.f1108l = null;
                this.f1107k = null;
                return;
            }
            for (ViewParent parent = viewFindViewById.getParent(); parent != coordinatorLayout && parent != null; parent = parent.getParent()) {
                if (parent == view) {
                    if (!coordinatorLayout.isInEditMode()) {
                        throw new IllegalStateException("Anchor must not be a descendant of the anchored view");
                    }
                    this.f1108l = null;
                    this.f1107k = null;
                    return;
                }
                if (parent instanceof View) {
                    viewFindViewById = parent;
                }
            }
            this.f1108l = viewFindViewById;
        }

        public void o(c cVar) {
            c cVar2 = this.f1097a;
            if (cVar2 != cVar) {
                if (cVar2 != null) {
                    cVar2.j();
                }
                this.f1097a = cVar;
                this.f1114r = null;
                this.f1098b = true;
                if (cVar != null) {
                    cVar.g(this);
                }
            }
        }

        public void p(boolean z3) {
            this.f1112p = z3;
        }

        public void q(Rect rect) {
            this.f1113q.set(rect);
        }

        public void r(int i3, boolean z3) {
            if (i3 == 0) {
                this.f1110n = z3;
            } else {
                if (i3 != 1) {
                    return;
                }
                this.f1111o = z3;
            }
        }

        public final boolean s(View view, int i3) {
            int iB = l0.d.b(((f) view.getLayoutParams()).f1103g, i3);
            return iB != 0 && (l0.d.b(this.f1104h, i3) & iB) == iB;
        }

        public final boolean t(View view, CoordinatorLayout coordinatorLayout) {
            if (this.f1107k.getId() != this.f1102f) {
                return false;
            }
            View view2 = this.f1107k;
            for (ViewParent parent = view2.getParent(); parent != coordinatorLayout; parent = parent.getParent()) {
                if (parent == null || parent == view) {
                    this.f1108l = null;
                    this.f1107k = null;
                    return false;
                }
                if (parent instanceof View) {
                    view2 = parent;
                }
            }
            this.f1108l = view2;
            return true;
        }

        public f(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1098b = false;
            this.f1099c = 0;
            this.f1100d = 0;
            this.f1101e = -1;
            this.f1102f = -1;
            this.f1103g = 0;
            this.f1104h = 0;
            this.f1113q = new Rect();
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CoordinatorLayout_Layout);
            this.f1099c = typedArrayObtainStyledAttributes.getInteger(R$styleable.CoordinatorLayout_Layout_android_layout_gravity, 0);
            this.f1102f = typedArrayObtainStyledAttributes.getResourceId(R$styleable.CoordinatorLayout_Layout_layout_anchor, -1);
            this.f1100d = typedArrayObtainStyledAttributes.getInteger(R$styleable.CoordinatorLayout_Layout_layout_anchorGravity, 0);
            this.f1101e = typedArrayObtainStyledAttributes.getInteger(R$styleable.CoordinatorLayout_Layout_layout_keyline, -1);
            this.f1103g = typedArrayObtainStyledAttributes.getInt(R$styleable.CoordinatorLayout_Layout_layout_insetEdge, 0);
            this.f1104h = typedArrayObtainStyledAttributes.getInt(R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges, 0);
            int i3 = R$styleable.CoordinatorLayout_Layout_layout_behavior;
            boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(i3);
            this.f1098b = zHasValue;
            if (zHasValue) {
                this.f1097a = CoordinatorLayout.O(context, attributeSet, typedArrayObtainStyledAttributes.getString(i3));
            }
            typedArrayObtainStyledAttributes.recycle();
            c cVar = this.f1097a;
            if (cVar != null) {
                cVar.g(this);
            }
        }

        public f(f fVar) {
            super((ViewGroup.MarginLayoutParams) fVar);
            this.f1098b = false;
            this.f1099c = 0;
            this.f1100d = 0;
            this.f1101e = -1;
            this.f1102f = -1;
            this.f1103g = 0;
            this.f1104h = 0;
            this.f1113q = new Rect();
        }

        public f(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1098b = false;
            this.f1099c = 0;
            this.f1100d = 0;
            this.f1101e = -1;
            this.f1102f = -1;
            this.f1103g = 0;
            this.f1104h = 0;
            this.f1113q = new Rect();
        }

        public f(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1098b = false;
            this.f1099c = 0;
            this.f1100d = 0;
            this.f1101e = -1;
            this.f1102f = -1;
            this.f1103g = 0;
            this.f1104h = 0;
            this.f1113q = new Rect();
        }
    }
}
