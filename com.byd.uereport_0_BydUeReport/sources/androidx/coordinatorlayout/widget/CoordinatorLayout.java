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
import o0.b0;
import o0.l;
import o0.m;
import o0.o;
import o0.p;
import o0.t;

/* JADX INFO: loaded from: classes.dex */
public class CoordinatorLayout extends ViewGroup implements l, m {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String f1160d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Class<?>[] f1161e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final ThreadLocal<Map<String, Constructor<c>>> f1162f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Comparator<View> f1163g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final n0.e<Rect> f1164h;
    public p A;
    public final o B;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final List<View> f1165i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final c0.a<View> f1166j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final List<View> f1167k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final List<View> f1168l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Paint f1169m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int[] f1170n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int[] f1171o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f1172p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f1173q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int[] f1174r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public View f1175s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public View f1176t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public g f1177u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f1178v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public b0 f1179w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f1180x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public Drawable f1181y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public ViewGroup.OnHierarchyChangeListener f1182z;

    public class a implements p {
        public a() {
        }

        @Override // o0.p
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

        public boolean A(CoordinatorLayout coordinatorLayout, V v7, View view, View view2, int i8, int i9) {
            if (i9 == 0) {
                return z(coordinatorLayout, v7, view, view2, i8);
            }
            return false;
        }

        @Deprecated
        public void B(CoordinatorLayout coordinatorLayout, V v7, View view) {
        }

        public void C(CoordinatorLayout coordinatorLayout, V v7, View view, int i8) {
            if (i8 == 0) {
                B(coordinatorLayout, v7, view);
            }
        }

        public boolean D(CoordinatorLayout coordinatorLayout, V v7, MotionEvent motionEvent) {
            return false;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v7) {
            return d(coordinatorLayout, v7) > 0.0f;
        }

        public boolean b(CoordinatorLayout coordinatorLayout, V v7, Rect rect) {
            return false;
        }

        public int c(CoordinatorLayout coordinatorLayout, V v7) {
            return -16777216;
        }

        public float d(CoordinatorLayout coordinatorLayout, V v7) {
            return 0.0f;
        }

        public boolean e(CoordinatorLayout coordinatorLayout, V v7, View view) {
            return false;
        }

        public b0 f(CoordinatorLayout coordinatorLayout, V v7, b0 b0Var) {
            return b0Var;
        }

        public void g(f fVar) {
        }

        public boolean h(CoordinatorLayout coordinatorLayout, V v7, View view) {
            return false;
        }

        public void i(CoordinatorLayout coordinatorLayout, V v7, View view) {
        }

        public void j() {
        }

        public boolean k(CoordinatorLayout coordinatorLayout, V v7, MotionEvent motionEvent) {
            return false;
        }

        public boolean l(CoordinatorLayout coordinatorLayout, V v7, int i8) {
            return false;
        }

        public boolean m(CoordinatorLayout coordinatorLayout, V v7, int i8, int i9, int i10, int i11) {
            return false;
        }

        public boolean n(CoordinatorLayout coordinatorLayout, V v7, View view, float f8, float f9, boolean z7) {
            return false;
        }

        public boolean o(CoordinatorLayout coordinatorLayout, V v7, View view, float f8, float f9) {
            return false;
        }

        @Deprecated
        public void p(CoordinatorLayout coordinatorLayout, V v7, View view, int i8, int i9, int[] iArr) {
        }

        public void q(CoordinatorLayout coordinatorLayout, V v7, View view, int i8, int i9, int[] iArr, int i10) {
            if (i10 == 0) {
                p(coordinatorLayout, v7, view, i8, i9, iArr);
            }
        }

        @Deprecated
        public void r(CoordinatorLayout coordinatorLayout, V v7, View view, int i8, int i9, int i10, int i11) {
        }

        @Deprecated
        public void s(CoordinatorLayout coordinatorLayout, V v7, View view, int i8, int i9, int i10, int i11, int i12) {
            if (i12 == 0) {
                r(coordinatorLayout, v7, view, i8, i9, i10, i11);
            }
        }

        public void t(CoordinatorLayout coordinatorLayout, V v7, View view, int i8, int i9, int i10, int i11, int i12, int[] iArr) {
            iArr[0] = iArr[0] + i10;
            iArr[1] = iArr[1] + i11;
            s(coordinatorLayout, v7, view, i8, i9, i10, i11, i12);
        }

        @Deprecated
        public void u(CoordinatorLayout coordinatorLayout, V v7, View view, View view2, int i8) {
        }

        public void v(CoordinatorLayout coordinatorLayout, V v7, View view, View view2, int i8, int i9) {
            if (i9 == 0) {
                u(coordinatorLayout, v7, view, view2, i8);
            }
        }

        public boolean w(CoordinatorLayout coordinatorLayout, V v7, Rect rect, boolean z7) {
            return false;
        }

        public void x(CoordinatorLayout coordinatorLayout, V v7, Parcelable parcelable) {
        }

        public Parcelable y(CoordinatorLayout coordinatorLayout, V v7) {
            return View.BaseSavedState.EMPTY_STATE;
        }

        @Deprecated
        public boolean z(CoordinatorLayout coordinatorLayout, V v7, View view, View view2, int i8) {
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
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = CoordinatorLayout.this.f1182z;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewAdded(view, view2);
            }
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewRemoved(View view, View view2) {
            CoordinatorLayout.this.L(2);
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = CoordinatorLayout.this.f1182z;
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
            float fK = t.K(view);
            float fK2 = t.K(view2);
            if (fK > fK2) {
                return -1;
            }
            return fK < fK2 ? 1 : 0;
        }
    }

    static {
        Package r02 = CoordinatorLayout.class.getPackage();
        f1160d = r02 != null ? r02.getName() : null;
        if (Build.VERSION.SDK_INT >= 21) {
            f1163g = new h();
        } else {
            f1163g = null;
        }
        f1161e = new Class[]{Context.class, AttributeSet.class};
        f1162f = new ThreadLocal<>();
        f1164h = new n0.g(12);
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
            String str2 = f1160d;
            if (!TextUtils.isEmpty(str2)) {
                str = str2 + '.' + str;
            }
        }
        try {
            ThreadLocal<Map<String, Constructor<c>>> threadLocal = f1162f;
            Map<String, Constructor<c>> map = threadLocal.get();
            if (map == null) {
                map = new HashMap<>();
                threadLocal.set(map);
            }
            Constructor<c> constructor = map.get(str);
            if (constructor == null) {
                constructor = Class.forName(str, false, context.getClassLoader()).getConstructor(f1161e);
                constructor.setAccessible(true);
                map.put(str, constructor);
            }
            return constructor.newInstance(context, attributeSet);
        } catch (Exception e8) {
            throw new RuntimeException("Could not inflate Behavior subclass " + str, e8);
        }
    }

    public static void S(Rect rect) {
        rect.setEmpty();
        f1164h.a(rect);
    }

    public static int V(int i8) {
        if (i8 == 0) {
            return 17;
        }
        return i8;
    }

    public static int W(int i8) {
        if ((i8 & 7) == 0) {
            i8 |= 8388611;
        }
        return (i8 & 112) == 0 ? i8 | 48 : i8;
    }

    public static int X(int i8) {
        if (i8 == 0) {
            return 8388661;
        }
        return i8;
    }

    public static Rect e() {
        Rect rectB = f1164h.b();
        return rectB == null ? new Rect() : rectB;
    }

    public static int g(int i8, int i9, int i10) {
        return i8 < i9 ? i9 : i8 > i10 ? i10 : i8;
    }

    public final int A(int i8) {
        int[] iArr = this.f1174r;
        if (iArr == null) {
            Log.e("CoordinatorLayout", "No keylines defined for " + this + " - attempted index lookup " + i8);
            return 0;
        }
        if (i8 >= 0 && i8 < iArr.length) {
            return iArr[i8];
        }
        Log.e("CoordinatorLayout", "Keyline index " + i8 + " out of range for " + this);
        return 0;
    }

    public void B(View view, Rect rect) {
        rect.set(((f) view.getLayoutParams()).h());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public f C(View view) {
        f fVar = (f) view.getLayoutParams();
        if (!fVar.f1187b) {
            if (view instanceof b) {
                c behavior = ((b) view).getBehavior();
                if (behavior == null) {
                    Log.e("CoordinatorLayout", "Attached behavior class is null");
                }
                fVar.o(behavior);
                fVar.f1187b = true;
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
                    } catch (Exception e8) {
                        Log.e("CoordinatorLayout", "Default behavior class " + dVar.value().getName() + " could not be instantiated. Did you forget a default constructor?", e8);
                    }
                }
                fVar.f1187b = true;
            }
        }
        return fVar;
    }

    public final void D(List<View> list) {
        list.clear();
        boolean zIsChildrenDrawingOrderEnabled = isChildrenDrawingOrderEnabled();
        int childCount = getChildCount();
        for (int i8 = childCount - 1; i8 >= 0; i8--) {
            list.add(getChildAt(zIsChildrenDrawingOrderEnabled ? getChildDrawingOrder(childCount, i8) : i8));
        }
        Comparator<View> comparator = f1163g;
        if (comparator != null) {
            Collections.sort(list, comparator);
        }
    }

    public final boolean E(View view) {
        return this.f1166j.j(view);
    }

    public boolean F(View view, int i8, int i9) {
        Rect rectE = e();
        x(view, rectE);
        try {
            return rectE.contains(i8, i9);
        } finally {
            S(rectE);
        }
    }

    public final void G(View view, int i8) {
        f fVar = (f) view.getLayoutParams();
        Rect rectE = e();
        rectE.set(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) fVar).leftMargin, getPaddingTop() + ((ViewGroup.MarginLayoutParams) fVar).topMargin, (getWidth() - getPaddingRight()) - ((ViewGroup.MarginLayoutParams) fVar).rightMargin, (getHeight() - getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin);
        if (this.f1179w != null && t.x(this) && !t.x(view)) {
            rectE.left += this.f1179w.f();
            rectE.top += this.f1179w.h();
            rectE.right -= this.f1179w.g();
            rectE.bottom -= this.f1179w.e();
        }
        Rect rectE2 = e();
        o0.d.a(W(fVar.f1188c), view.getMeasuredWidth(), view.getMeasuredHeight(), rectE, rectE2, i8);
        view.layout(rectE2.left, rectE2.top, rectE2.right, rectE2.bottom);
        S(rectE);
        S(rectE2);
    }

    public final void H(View view, View view2, int i8) {
        Rect rectE = e();
        Rect rectE2 = e();
        try {
            x(view2, rectE);
            y(view, i8, rectE, rectE2);
            view.layout(rectE2.left, rectE2.top, rectE2.right, rectE2.bottom);
        } finally {
            S(rectE);
            S(rectE2);
        }
    }

    public final void I(View view, int i8, int i9) {
        f fVar = (f) view.getLayoutParams();
        int iB = o0.d.b(X(fVar.f1188c), i9);
        int i10 = iB & 7;
        int i11 = iB & 112;
        int width = getWidth();
        int height = getHeight();
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        if (i9 == 1) {
            i8 = width - i8;
        }
        int iA = A(i8) - measuredWidth;
        int i12 = 0;
        if (i10 == 1) {
            iA += measuredWidth / 2;
        } else if (i10 == 5) {
            iA += measuredWidth;
        }
        if (i11 == 16) {
            i12 = 0 + (measuredHeight / 2);
        } else if (i11 == 80) {
            i12 = measuredHeight + 0;
        }
        int iMax = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) fVar).leftMargin, Math.min(iA, ((width - getPaddingRight()) - measuredWidth) - ((ViewGroup.MarginLayoutParams) fVar).rightMargin));
        int iMax2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) fVar).topMargin, Math.min(i12, ((height - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin));
        view.layout(iMax, iMax2, measuredWidth + iMax, measuredHeight + iMax2);
    }

    public final void J(View view, Rect rect, int i8) {
        boolean z7;
        boolean z8;
        int width;
        int i9;
        int i10;
        int i11;
        int height;
        int i12;
        int i13;
        int i14;
        if (t.Q(view) && view.getWidth() > 0 && view.getHeight() > 0) {
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
            int iB = o0.d.b(fVar.f1193h, i8);
            boolean z9 = true;
            if ((iB & 48) != 48 || (i13 = (rectE.top - ((ViewGroup.MarginLayoutParams) fVar).topMargin) - fVar.f1195j) >= (i14 = rect.top)) {
                z7 = false;
            } else {
                Z(view, i14 - i13);
                z7 = true;
            }
            if ((iB & 80) == 80 && (height = ((getHeight() - rectE.bottom) - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin) + fVar.f1195j) < (i12 = rect.bottom)) {
                Z(view, height - i12);
                z7 = true;
            }
            if (!z7) {
                Z(view, 0);
            }
            if ((iB & 3) != 3 || (i10 = (rectE.left - ((ViewGroup.MarginLayoutParams) fVar).leftMargin) - fVar.f1194i) >= (i11 = rect.left)) {
                z8 = false;
            } else {
                Y(view, i11 - i10);
                z8 = true;
            }
            if ((iB & 5) != 5 || (width = ((getWidth() - rectE.right) - ((ViewGroup.MarginLayoutParams) fVar).rightMargin) + fVar.f1194i) >= (i9 = rect.right)) {
                z9 = z8;
            } else {
                Y(view, width - i9);
            }
            if (!z9) {
                Y(view, 0);
            }
            S(rectE);
        }
    }

    public void K(View view, int i8) {
        c cVarF;
        f fVar = (f) view.getLayoutParams();
        if (fVar.f1196k != null) {
            Rect rectE = e();
            Rect rectE2 = e();
            Rect rectE3 = e();
            x(fVar.f1196k, rectE);
            u(view, false, rectE2);
            int measuredWidth = view.getMeasuredWidth();
            int measuredHeight = view.getMeasuredHeight();
            z(view, i8, rectE, rectE3, fVar, measuredWidth, measuredHeight);
            boolean z7 = (rectE3.left == rectE2.left && rectE3.top == rectE2.top) ? false : true;
            k(fVar, rectE3, measuredWidth, measuredHeight);
            int i9 = rectE3.left - rectE2.left;
            int i10 = rectE3.top - rectE2.top;
            if (i9 != 0) {
                t.V(view, i9);
            }
            if (i10 != 0) {
                t.W(view, i10);
            }
            if (z7 && (cVarF = fVar.f()) != null) {
                cVarF.h(this, view, fVar.f1196k);
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

    public void M(View view, int i8) {
        f fVar = (f) view.getLayoutParams();
        if (fVar.a()) {
            throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
        }
        View view2 = fVar.f1196k;
        if (view2 != null) {
            H(view, view2, i8);
            return;
        }
        int i9 = fVar.f1190e;
        if (i9 >= 0) {
            I(view, i9, i8);
        } else {
            G(view, i8);
        }
    }

    public void N(View view, int i8, int i9, int i10, int i11) {
        measureChildWithMargins(view, i8, i9, i10, i11);
    }

    public final boolean P(MotionEvent motionEvent, int i8) {
        int actionMasked = motionEvent.getActionMasked();
        List<View> list = this.f1167k;
        D(list);
        int size = list.size();
        MotionEvent motionEventObtain = null;
        boolean zK = false;
        boolean z7 = false;
        for (int i9 = 0; i9 < size; i9++) {
            View view = list.get(i9);
            f fVar = (f) view.getLayoutParams();
            c cVarF = fVar.f();
            if (!(zK || z7) || actionMasked == 0) {
                if (!zK && cVarF != null) {
                    if (i8 == 0) {
                        zK = cVarF.k(this, view, motionEvent);
                    } else if (i8 == 1) {
                        zK = cVarF.D(this, view, motionEvent);
                    }
                    if (zK) {
                        this.f1175s = view;
                    }
                }
                boolean zC = fVar.c();
                boolean zI = fVar.i(this, view);
                z7 = zI && !zC;
                if (zI && !z7) {
                    break;
                }
            } else if (cVarF != null) {
                if (motionEventObtain == null) {
                    long jUptimeMillis = SystemClock.uptimeMillis();
                    motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                }
                if (i8 == 0) {
                    cVarF.k(this, view, motionEventObtain);
                } else if (i8 == 1) {
                    cVarF.D(this, view, motionEventObtain);
                }
            }
        }
        list.clear();
        return zK;
    }

    public final void Q() {
        this.f1165i.clear();
        this.f1166j.c();
        int childCount = getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            f fVarC = C(childAt);
            fVarC.d(this, childAt);
            this.f1166j.b(childAt);
            for (int i9 = 0; i9 < childCount; i9++) {
                if (i9 != i8) {
                    View childAt2 = getChildAt(i9);
                    if (fVarC.b(this, childAt, childAt2)) {
                        if (!this.f1166j.d(childAt2)) {
                            this.f1166j.b(childAt2);
                        }
                        this.f1166j.a(childAt2, childAt);
                    }
                }
            }
        }
        this.f1165i.addAll(this.f1166j.i());
        Collections.reverse(this.f1165i);
    }

    public void R(View view, Rect rect) {
        ((f) view.getLayoutParams()).q(rect);
    }

    public void T() {
        if (this.f1173q && this.f1177u != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.f1177u);
        }
        this.f1178v = false;
    }

    public final void U(boolean z7) {
        int childCount = getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            c cVarF = ((f) childAt.getLayoutParams()).f();
            if (cVarF != null) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                if (z7) {
                    cVarF.k(this, childAt, motionEventObtain);
                } else {
                    cVarF.D(this, childAt, motionEventObtain);
                }
                motionEventObtain.recycle();
            }
        }
        for (int i9 = 0; i9 < childCount; i9++) {
            ((f) getChildAt(i9).getLayoutParams()).m();
        }
        this.f1175s = null;
        this.f1172p = false;
    }

    public final void Y(View view, int i8) {
        f fVar = (f) view.getLayoutParams();
        int i9 = fVar.f1194i;
        if (i9 != i8) {
            t.V(view, i8 - i9);
            fVar.f1194i = i8;
        }
    }

    public final void Z(View view, int i8) {
        f fVar = (f) view.getLayoutParams();
        int i9 = fVar.f1195j;
        if (i9 != i8) {
            t.W(view, i8 - i9);
            fVar.f1195j = i8;
        }
    }

    public final b0 a0(b0 b0Var) {
        if (n0.c.a(this.f1179w, b0Var)) {
            return b0Var;
        }
        this.f1179w = b0Var;
        boolean z7 = b0Var != null && b0Var.h() > 0;
        this.f1180x = z7;
        setWillNotDraw(!z7 && getBackground() == null);
        b0 b0VarL = l(b0Var);
        requestLayout();
        return b0VarL;
    }

    public final void b0() {
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        if (!t.x(this)) {
            t.y0(this, null);
            return;
        }
        if (this.A == null) {
            this.A = new a();
        }
        t.y0(this, this.A);
        setSystemUiVisibility(1280);
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof f) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j8) {
        f fVar = (f) view.getLayoutParams();
        c cVar = fVar.f1186a;
        if (cVar != null) {
            float fD = cVar.d(this, view);
            if (fD > 0.0f) {
                if (this.f1169m == null) {
                    this.f1169m = new Paint();
                }
                this.f1169m.setColor(fVar.f1186a.c(this, view));
                this.f1169m.setAlpha(g(Math.round(fD * 255.0f), 0, 255));
                int iSave = canvas.save();
                if (view.isOpaque()) {
                    canvas.clipRect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom(), Region.Op.DIFFERENCE);
                }
                canvas.drawRect(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom(), this.f1169m);
                canvas.restoreToCount(iSave);
            }
        }
        return super.drawChild(canvas, view, j8);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.f1181y;
        boolean state = false;
        if (drawable != null && drawable.isStateful()) {
            state = false | drawable.setState(drawableState);
        }
        if (state) {
            invalidate();
        }
    }

    public void f() {
        if (this.f1173q) {
            if (this.f1177u == null) {
                this.f1177u = new g();
            }
            getViewTreeObserver().addOnPreDrawListener(this.f1177u);
        }
        this.f1178v = true;
    }

    public final List<View> getDependencySortedChildren() {
        Q();
        return Collections.unmodifiableList(this.f1165i);
    }

    public final b0 getLastWindowInsets() {
        return this.f1179w;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.B.a();
    }

    public Drawable getStatusBarBackground() {
        return this.f1181y;
    }

    @Override // android.view.View
    public int getSuggestedMinimumHeight() {
        return Math.max(super.getSuggestedMinimumHeight(), getPaddingTop() + getPaddingBottom());
    }

    @Override // android.view.View
    public int getSuggestedMinimumWidth() {
        return Math.max(super.getSuggestedMinimumWidth(), getPaddingLeft() + getPaddingRight());
    }

    @Override // o0.l
    public void h(View view, View view2, int i8, int i9) {
        c cVarF;
        this.B.c(view, view2, i8, i9);
        this.f1176t = view2;
        int childCount = getChildCount();
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = getChildAt(i10);
            f fVar = (f) childAt.getLayoutParams();
            if (fVar.j(i9) && (cVarF = fVar.f()) != null) {
                cVarF.v(this, childAt, view, view2, i8, i9);
            }
        }
    }

    @Override // o0.l
    public void i(View view, int i8) {
        this.B.e(view, i8);
        int childCount = getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            f fVar = (f) childAt.getLayoutParams();
            if (fVar.j(i8)) {
                c cVarF = fVar.f();
                if (cVarF != null) {
                    cVarF.C(this, childAt, view, i8);
                }
                fVar.l(i8);
                fVar.k();
            }
        }
        this.f1176t = null;
    }

    @Override // o0.l
    public void j(View view, int i8, int i9, int[] iArr, int i10) {
        c cVarF;
        int childCount = getChildCount();
        boolean z7 = false;
        int iMax = 0;
        int iMax2 = 0;
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            if (childAt.getVisibility() != 8) {
                f fVar = (f) childAt.getLayoutParams();
                if (fVar.j(i10) && (cVarF = fVar.f()) != null) {
                    int[] iArr2 = this.f1170n;
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    cVarF.q(this, childAt, view, i8, i9, iArr2, i10);
                    int[] iArr3 = this.f1170n;
                    iMax = i8 > 0 ? Math.max(iMax, iArr3[0]) : Math.min(iMax, iArr3[0]);
                    int[] iArr4 = this.f1170n;
                    iMax2 = i9 > 0 ? Math.max(iMax2, iArr4[1]) : Math.min(iMax2, iArr4[1]);
                    z7 = true;
                }
            }
        }
        iArr[0] = iMax;
        iArr[1] = iMax2;
        if (z7) {
            L(1);
        }
    }

    public final void k(f fVar, Rect rect, int i8, int i9) {
        int width = getWidth();
        int height = getHeight();
        int iMax = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) fVar).leftMargin, Math.min(rect.left, ((width - getPaddingRight()) - i8) - ((ViewGroup.MarginLayoutParams) fVar).rightMargin));
        int iMax2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) fVar).topMargin, Math.min(rect.top, ((height - getPaddingBottom()) - i9) - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin));
        rect.set(iMax, iMax2, i8 + iMax, i9 + iMax2);
    }

    public final b0 l(b0 b0Var) {
        c cVarF;
        if (b0Var.m()) {
            return b0Var;
        }
        int childCount = getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            if (t.x(childAt) && (cVarF = ((f) childAt.getLayoutParams()).f()) != null) {
                b0Var = cVarF.f(this, childAt, b0Var);
                if (b0Var.m()) {
                    break;
                }
            }
        }
        return b0Var;
    }

    @Override // o0.m
    public void m(View view, int i8, int i9, int i10, int i11, int i12, int[] iArr) {
        c cVarF;
        boolean z7;
        int iMin;
        int childCount = getChildCount();
        boolean z8 = false;
        int iMax = 0;
        int i13 = 0;
        for (int i14 = 0; i14 < childCount; i14++) {
            View childAt = getChildAt(i14);
            if (childAt.getVisibility() != 8) {
                f fVar = (f) childAt.getLayoutParams();
                if (fVar.j(i12) && (cVarF = fVar.f()) != null) {
                    int[] iArr2 = this.f1170n;
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    cVarF.t(this, childAt, view, i8, i9, i10, i11, i12, iArr2);
                    int[] iArr3 = this.f1170n;
                    iMax = i10 > 0 ? Math.max(iMax, iArr3[0]) : Math.min(iMax, iArr3[0]);
                    if (i11 > 0) {
                        z7 = true;
                        iMin = Math.max(i13, this.f1170n[1]);
                    } else {
                        z7 = true;
                        iMin = Math.min(i13, this.f1170n[1]);
                    }
                    i13 = iMin;
                    z8 = z7;
                }
            }
        }
        iArr[0] = iArr[0] + iMax;
        iArr[1] = iArr[1] + i13;
        if (z8) {
            L(1);
        }
    }

    @Override // o0.l
    public void n(View view, int i8, int i9, int i10, int i11, int i12) {
        m(view, i8, i9, i10, i11, 0, this.f1171o);
    }

    @Override // o0.l
    public boolean o(View view, View view2, int i8, int i9) {
        int childCount = getChildCount();
        boolean z7 = false;
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = getChildAt(i10);
            if (childAt.getVisibility() != 8) {
                f fVar = (f) childAt.getLayoutParams();
                c cVarF = fVar.f();
                if (cVarF != null) {
                    boolean zA = cVarF.A(this, childAt, view, view2, i8, i9);
                    z7 |= zA;
                    fVar.r(i9, zA);
                } else {
                    fVar.r(i9, false);
                }
            }
        }
        return z7;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        U(false);
        if (this.f1178v) {
            if (this.f1177u == null) {
                this.f1177u = new g();
            }
            getViewTreeObserver().addOnPreDrawListener(this.f1177u);
        }
        if (this.f1179w == null && t.x(this)) {
            t.i0(this);
        }
        this.f1173q = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        U(false);
        if (this.f1178v && this.f1177u != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.f1177u);
        }
        View view = this.f1176t;
        if (view != null) {
            onStopNestedScroll(view);
        }
        this.f1173q = false;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.f1180x || this.f1181y == null) {
            return;
        }
        b0 b0Var = this.f1179w;
        int iH = b0Var != null ? b0Var.h() : 0;
        if (iH > 0) {
            this.f1181y.setBounds(0, 0, getWidth(), iH);
            this.f1181y.draw(canvas);
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
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        c cVarF;
        int iA = t.A(this);
        int size = this.f1165i.size();
        for (int i12 = 0; i12 < size; i12++) {
            View view = this.f1165i.get(i12);
            if (view.getVisibility() != 8 && ((cVarF = ((f) view.getLayoutParams()).f()) == null || !cVarF.l(this, view, iA))) {
                M(view, iA);
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

    @Override // android.view.ViewGroup, android.view.ViewParent, o0.n
    public boolean onNestedFling(View view, float f8, float f9, boolean z7) {
        c cVarF;
        int childCount = getChildCount();
        boolean zN = false;
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                f fVar = (f) childAt.getLayoutParams();
                if (fVar.j(0) && (cVarF = fVar.f()) != null) {
                    zN |= cVarF.n(this, childAt, view, f8, f9, z7);
                }
            }
        }
        if (zN) {
            L(1);
        }
        return zN;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, o0.n
    public boolean onNestedPreFling(View view, float f8, float f9) {
        c cVarF;
        int childCount = getChildCount();
        boolean zO = false;
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                f fVar = (f) childAt.getLayoutParams();
                if (fVar.j(0) && (cVarF = fVar.f()) != null) {
                    zO |= cVarF.o(this, childAt, view, f8, f9);
                }
            }
        }
        return zO;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, o0.n
    public void onNestedPreScroll(View view, int i8, int i9, int[] iArr) {
        j(view, i8, i9, iArr, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, o0.n
    public void onNestedScroll(View view, int i8, int i9, int i10, int i11) {
        n(view, i8, i9, i10, i11, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, o0.n
    public void onNestedScrollAccepted(View view, View view2, int i8) {
        h(view, view2, i8, 0);
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
        SparseArray<Parcelable> sparseArray = savedState.f1183f;
        int childCount = getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
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
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            int id = childAt.getId();
            c cVarF = ((f) childAt.getLayoutParams()).f();
            if (id != -1 && cVarF != null && (parcelableY = cVarF.y(this, childAt)) != null) {
                sparseArray.append(id, parcelableY);
            }
        }
        savedState.f1183f = sparseArray;
        return savedState;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, o0.n
    public boolean onStartNestedScroll(View view, View view2, int i8) {
        return o(view, view2, i8, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, o0.n
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
            android.view.View r3 = r0.f1175s
            r4 = 1
            r5 = 0
            if (r3 != 0) goto L15
            boolean r3 = r0.P(r1, r4)
            if (r3 == 0) goto L2b
            goto L16
        L15:
            r3 = r5
        L16:
            android.view.View r6 = r0.f1175s
            android.view.ViewGroup$LayoutParams r6 = r6.getLayoutParams()
            androidx.coordinatorlayout.widget.CoordinatorLayout$f r6 = (androidx.coordinatorlayout.widget.CoordinatorLayout.f) r6
            androidx.coordinatorlayout.widget.CoordinatorLayout$c r6 = r6.f()
            if (r6 == 0) goto L2b
            android.view.View r7 = r0.f1175s
            boolean r6 = r6.D(r0, r7, r1)
            goto L2c
        L2b:
            r6 = r5
        L2c:
            android.view.View r7 = r0.f1175s
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
        List listG = this.f1166j.g(view);
        if (listG == null || listG.isEmpty()) {
            return;
        }
        for (int i8 = 0; i8 < listG.size(); i8++) {
            View view2 = (View) listG.get(i8);
            c cVarF = ((f) view2.getLayoutParams()).f();
            if (cVarF != null) {
                cVarF.h(this, view2, view);
            }
        }
    }

    public void q() {
        int childCount = getChildCount();
        boolean z7 = false;
        int i8 = 0;
        while (true) {
            if (i8 >= childCount) {
                break;
            }
            if (E(getChildAt(i8))) {
                z7 = true;
                break;
            }
            i8++;
        }
        if (z7 != this.f1178v) {
            if (z7) {
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
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z7) {
        c cVarF = ((f) view.getLayoutParams()).f();
        if (cVarF == null || !cVarF.w(this, view, rect, z7)) {
            return super.requestChildRectangleOnScreen(view, rect, z7);
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z7) {
        super.requestDisallowInterceptTouchEvent(z7);
        if (!z7 || this.f1172p) {
            return;
        }
        U(false);
        this.f1172p = true;
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: s, reason: merged with bridge method [inline-methods] */
    public f generateLayoutParams(AttributeSet attributeSet) {
        return new f(getContext(), attributeSet);
    }

    @Override // android.view.View
    public void setFitsSystemWindows(boolean z7) {
        super.setFitsSystemWindows(z7);
        b0();
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.f1182z = onHierarchyChangeListener;
    }

    public void setStatusBarBackground(Drawable drawable) {
        Drawable drawable2 = this.f1181y;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.f1181y = drawableMutate;
            if (drawableMutate != null) {
                if (drawableMutate.isStateful()) {
                    this.f1181y.setState(getDrawableState());
                }
                h0.a.m(this.f1181y, t.A(this));
                this.f1181y.setVisible(getVisibility() == 0, false);
                this.f1181y.setCallback(this);
            }
            t.b0(this);
        }
    }

    public void setStatusBarBackgroundColor(int i8) {
        setStatusBarBackground(new ColorDrawable(i8));
    }

    public void setStatusBarBackgroundResource(int i8) {
        setStatusBarBackground(i8 != 0 ? e0.a.d(getContext(), i8) : null);
    }

    @Override // android.view.View
    public void setVisibility(int i8) {
        super.setVisibility(i8);
        boolean z7 = i8 == 0;
        Drawable drawable = this.f1181y;
        if (drawable == null || drawable.isVisible() == z7) {
            return;
        }
        this.f1181y.setVisible(z7, false);
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: t, reason: merged with bridge method [inline-methods] */
    public f generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof f ? new f((f) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new f((ViewGroup.MarginLayoutParams) layoutParams) : new f(layoutParams);
    }

    public void u(View view, boolean z7, Rect rect) {
        if (view.isLayoutRequested() || view.getVisibility() == 8) {
            rect.setEmpty();
        } else if (z7) {
            x(view, rect);
        } else {
            rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
    }

    public List<View> v(View view) {
        List<View> listH = this.f1166j.h(view);
        this.f1168l.clear();
        if (listH != null) {
            this.f1168l.addAll(listH);
        }
        return this.f1168l;
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f1181y;
    }

    public List<View> w(View view) {
        List listG = this.f1166j.g(view);
        this.f1168l.clear();
        if (listG != null) {
            this.f1168l.addAll(listG);
        }
        return this.f1168l;
    }

    public void x(View view, Rect rect) {
        c0.b.a(this, view, rect);
    }

    public void y(View view, int i8, Rect rect, Rect rect2) {
        f fVar = (f) view.getLayoutParams();
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        z(view, i8, rect, rect2, fVar, measuredWidth, measuredHeight);
        k(fVar, rect2, measuredWidth, measuredHeight);
    }

    public final void z(View view, int i8, Rect rect, Rect rect2, f fVar, int i9, int i10) {
        int iB = o0.d.b(V(fVar.f1188c), i8);
        int iB2 = o0.d.b(W(fVar.f1189d), i8);
        int i11 = iB & 7;
        int i12 = iB & 112;
        int i13 = iB2 & 7;
        int i14 = iB2 & 112;
        int iWidth = i13 != 1 ? i13 != 5 ? rect.left : rect.right : rect.left + (rect.width() / 2);
        int iHeight = i14 != 16 ? i14 != 80 ? rect.top : rect.bottom : rect.top + (rect.height() / 2);
        if (i11 == 1) {
            iWidth -= i9 / 2;
        } else if (i11 != 5) {
            iWidth -= i9;
        }
        if (i12 == 16) {
            iHeight -= i10 / 2;
        } else if (i12 != 80) {
            iHeight -= i10;
        }
        rect2.set(iWidth, iHeight, i9 + iWidth, i10 + iHeight);
    }

    public CoordinatorLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.coordinatorLayoutStyle);
    }

    public CoordinatorLayout(Context context, AttributeSet attributeSet, int i8) {
        TypedArray typedArrayObtainStyledAttributes;
        super(context, attributeSet, i8);
        this.f1165i = new ArrayList();
        this.f1166j = new c0.a<>();
        this.f1167k = new ArrayList();
        this.f1168l = new ArrayList();
        this.f1170n = new int[2];
        this.f1171o = new int[2];
        this.B = new o(this);
        if (i8 == 0) {
            typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CoordinatorLayout, 0, R$style.Widget_Support_CoordinatorLayout);
        } else {
            typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CoordinatorLayout, i8, 0);
        }
        if (Build.VERSION.SDK_INT >= 29) {
            if (i8 == 0) {
                saveAttributeDataForStyleable(context, R$styleable.CoordinatorLayout, attributeSet, typedArrayObtainStyledAttributes, 0, R$style.Widget_Support_CoordinatorLayout);
            } else {
                saveAttributeDataForStyleable(context, R$styleable.CoordinatorLayout, attributeSet, typedArrayObtainStyledAttributes, i8, 0);
            }
        }
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.CoordinatorLayout_keylines, 0);
        if (resourceId != 0) {
            Resources resources = context.getResources();
            this.f1174r = resources.getIntArray(resourceId);
            float f8 = resources.getDisplayMetrics().density;
            int length = this.f1174r.length;
            for (int i9 = 0; i9 < length; i9++) {
                this.f1174r[i9] = (int) (r12[i9] * f8);
            }
        }
        this.f1181y = typedArrayObtainStyledAttributes.getDrawable(R$styleable.CoordinatorLayout_statusBarBackground);
        typedArrayObtainStyledAttributes.recycle();
        b0();
        super.setOnHierarchyChangeListener(new e());
        if (t.y(this) == 0) {
            t.v0(this, 1);
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public SparseArray<Parcelable> f1183f;

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
            public SavedState[] newArray(int i8) {
                return new SavedState[i8];
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            int i8 = parcel.readInt();
            int[] iArr = new int[i8];
            parcel.readIntArray(iArr);
            Parcelable[] parcelableArray = parcel.readParcelableArray(classLoader);
            this.f1183f = new SparseArray<>(i8);
            for (int i9 = 0; i9 < i8; i9++) {
                this.f1183f.append(iArr[i9], parcelableArray[i9]);
            }
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            super.writeToParcel(parcel, i8);
            SparseArray<Parcelable> sparseArray = this.f1183f;
            int size = sparseArray != null ? sparseArray.size() : 0;
            parcel.writeInt(size);
            int[] iArr = new int[size];
            Parcelable[] parcelableArr = new Parcelable[size];
            for (int i9 = 0; i9 < size; i9++) {
                iArr[i9] = this.f1183f.keyAt(i9);
                parcelableArr[i9] = this.f1183f.valueAt(i9);
            }
            parcel.writeIntArray(iArr);
            parcel.writeParcelableArray(parcelableArr, i8);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public static class f extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public c f1186a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1187b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1188c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1189d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1190e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1191f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f1192g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f1193h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f1194i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public int f1195j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public View f1196k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public View f1197l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public boolean f1198m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public boolean f1199n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public boolean f1200o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public boolean f1201p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public final Rect f1202q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public Object f1203r;

        public f(int i8, int i9) {
            super(i8, i9);
            this.f1187b = false;
            this.f1188c = 0;
            this.f1189d = 0;
            this.f1190e = -1;
            this.f1191f = -1;
            this.f1192g = 0;
            this.f1193h = 0;
            this.f1202q = new Rect();
        }

        public boolean a() {
            return this.f1196k == null && this.f1191f != -1;
        }

        public boolean b(CoordinatorLayout coordinatorLayout, View view, View view2) {
            c cVar;
            return view2 == this.f1197l || s(view2, t.A(coordinatorLayout)) || ((cVar = this.f1186a) != null && cVar.e(coordinatorLayout, view, view2));
        }

        public boolean c() {
            if (this.f1186a == null) {
                this.f1198m = false;
            }
            return this.f1198m;
        }

        public View d(CoordinatorLayout coordinatorLayout, View view) {
            if (this.f1191f == -1) {
                this.f1197l = null;
                this.f1196k = null;
                return null;
            }
            if (this.f1196k == null || !t(view, coordinatorLayout)) {
                n(view, coordinatorLayout);
            }
            return this.f1196k;
        }

        public int e() {
            return this.f1191f;
        }

        public c f() {
            return this.f1186a;
        }

        public boolean g() {
            return this.f1201p;
        }

        public Rect h() {
            return this.f1202q;
        }

        public boolean i(CoordinatorLayout coordinatorLayout, View view) {
            boolean z7 = this.f1198m;
            if (z7) {
                return true;
            }
            c cVar = this.f1186a;
            boolean zA = (cVar != null ? cVar.a(coordinatorLayout, view) : false) | z7;
            this.f1198m = zA;
            return zA;
        }

        public boolean j(int i8) {
            if (i8 == 0) {
                return this.f1199n;
            }
            if (i8 != 1) {
                return false;
            }
            return this.f1200o;
        }

        public void k() {
            this.f1201p = false;
        }

        public void l(int i8) {
            r(i8, false);
        }

        public void m() {
            this.f1198m = false;
        }

        public final void n(View view, CoordinatorLayout coordinatorLayout) {
            View viewFindViewById = coordinatorLayout.findViewById(this.f1191f);
            this.f1196k = viewFindViewById;
            if (viewFindViewById == null) {
                if (coordinatorLayout.isInEditMode()) {
                    this.f1197l = null;
                    this.f1196k = null;
                    return;
                }
                throw new IllegalStateException("Could not find CoordinatorLayout descendant view with id " + coordinatorLayout.getResources().getResourceName(this.f1191f) + " to anchor view " + view);
            }
            if (viewFindViewById == coordinatorLayout) {
                if (!coordinatorLayout.isInEditMode()) {
                    throw new IllegalStateException("View can not be anchored to the the parent CoordinatorLayout");
                }
                this.f1197l = null;
                this.f1196k = null;
                return;
            }
            for (ViewParent parent = viewFindViewById.getParent(); parent != coordinatorLayout && parent != null; parent = parent.getParent()) {
                if (parent == view) {
                    if (!coordinatorLayout.isInEditMode()) {
                        throw new IllegalStateException("Anchor must not be a descendant of the anchored view");
                    }
                    this.f1197l = null;
                    this.f1196k = null;
                    return;
                }
                if (parent instanceof View) {
                    viewFindViewById = parent;
                }
            }
            this.f1197l = viewFindViewById;
        }

        public void o(c cVar) {
            c cVar2 = this.f1186a;
            if (cVar2 != cVar) {
                if (cVar2 != null) {
                    cVar2.j();
                }
                this.f1186a = cVar;
                this.f1203r = null;
                this.f1187b = true;
                if (cVar != null) {
                    cVar.g(this);
                }
            }
        }

        public void p(boolean z7) {
            this.f1201p = z7;
        }

        public void q(Rect rect) {
            this.f1202q.set(rect);
        }

        public void r(int i8, boolean z7) {
            if (i8 == 0) {
                this.f1199n = z7;
            } else {
                if (i8 != 1) {
                    return;
                }
                this.f1200o = z7;
            }
        }

        public final boolean s(View view, int i8) {
            int iB = o0.d.b(((f) view.getLayoutParams()).f1192g, i8);
            return iB != 0 && (o0.d.b(this.f1193h, i8) & iB) == iB;
        }

        public final boolean t(View view, CoordinatorLayout coordinatorLayout) {
            if (this.f1196k.getId() != this.f1191f) {
                return false;
            }
            View view2 = this.f1196k;
            for (ViewParent parent = view2.getParent(); parent != coordinatorLayout; parent = parent.getParent()) {
                if (parent == null || parent == view) {
                    this.f1197l = null;
                    this.f1196k = null;
                    return false;
                }
                if (parent instanceof View) {
                    view2 = parent;
                }
            }
            this.f1197l = view2;
            return true;
        }

        public f(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1187b = false;
            this.f1188c = 0;
            this.f1189d = 0;
            this.f1190e = -1;
            this.f1191f = -1;
            this.f1192g = 0;
            this.f1193h = 0;
            this.f1202q = new Rect();
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CoordinatorLayout_Layout);
            this.f1188c = typedArrayObtainStyledAttributes.getInteger(R$styleable.CoordinatorLayout_Layout_android_layout_gravity, 0);
            this.f1191f = typedArrayObtainStyledAttributes.getResourceId(R$styleable.CoordinatorLayout_Layout_layout_anchor, -1);
            this.f1189d = typedArrayObtainStyledAttributes.getInteger(R$styleable.CoordinatorLayout_Layout_layout_anchorGravity, 0);
            this.f1190e = typedArrayObtainStyledAttributes.getInteger(R$styleable.CoordinatorLayout_Layout_layout_keyline, -1);
            this.f1192g = typedArrayObtainStyledAttributes.getInt(R$styleable.CoordinatorLayout_Layout_layout_insetEdge, 0);
            this.f1193h = typedArrayObtainStyledAttributes.getInt(R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges, 0);
            int i8 = R$styleable.CoordinatorLayout_Layout_layout_behavior;
            boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(i8);
            this.f1187b = zHasValue;
            if (zHasValue) {
                this.f1186a = CoordinatorLayout.O(context, attributeSet, typedArrayObtainStyledAttributes.getString(i8));
            }
            typedArrayObtainStyledAttributes.recycle();
            c cVar = this.f1186a;
            if (cVar != null) {
                cVar.g(this);
            }
        }

        public f(f fVar) {
            super((ViewGroup.MarginLayoutParams) fVar);
            this.f1187b = false;
            this.f1188c = 0;
            this.f1189d = 0;
            this.f1190e = -1;
            this.f1191f = -1;
            this.f1192g = 0;
            this.f1193h = 0;
            this.f1202q = new Rect();
        }

        public f(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1187b = false;
            this.f1188c = 0;
            this.f1189d = 0;
            this.f1190e = -1;
            this.f1191f = -1;
            this.f1192g = 0;
            this.f1193h = 0;
            this.f1202q = new Rect();
        }

        public f(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1187b = false;
            this.f1188c = 0;
            this.f1189d = 0;
            this.f1190e = -1;
            this.f1191f = -1;
            this.f1192g = 0;
            this.f1193h = 0;
            this.f1202q = new Rect();
        }
    }
}
