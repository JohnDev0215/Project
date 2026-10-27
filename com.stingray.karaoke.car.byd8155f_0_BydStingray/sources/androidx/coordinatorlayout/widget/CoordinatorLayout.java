package androidx.coordinatorlayout.widget;

import B8.t;
import E1.d;
import E5.y;
import F1.A;
import F1.AbstractC0265y;
import F1.C0254m;
import F1.H;
import F1.InterfaceC0252k;
import F1.InterfaceC0253l;
import F1.V;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.stingray.drivemodedetector.R;
import h6.n;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import l3.C1764u;
import s1.AbstractC2360a;
import t1.AbstractC2425a;
import t1.AbstractC2431g;
import t1.C2428d;
import t1.C2430f;
import t1.InterfaceC2426b;
import t1.ViewGroupOnHierarchyChangeListenerC2427c;
import t1.ViewTreeObserverOnPreDrawListenerC2429e;
import u.C2458F;

/* JADX INFO: loaded from: classes.dex */
public class CoordinatorLayout extends ViewGroup implements InterfaceC0252k, InterfaceC0253l {

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public static final String f11715E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public static final Class[] f11716F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public static final ThreadLocal f11717G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public static final y f11718H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public static final d f11719I;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public Drawable f11720A;
    public ViewGroup.OnHierarchyChangeListener B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public C1764u f11721C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final C0254m f11722D;
    public final ArrayList l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final t f11723m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ArrayList f11724n;
    public final ArrayList o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int[] f11725p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int[] f11726q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f11727r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f11728s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final int[] f11729t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public View f11730u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public View f11731v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ViewTreeObserverOnPreDrawListenerC2429e f11732w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f11733x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public V f11734y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f11735z;

    static {
        Package r02 = CoordinatorLayout.class.getPackage();
        f11715E = r02 != null ? r02.getName() : null;
        f11718H = new y(12);
        f11716F = new Class[]{Context.class, AttributeSet.class};
        f11717G = new ThreadLocal();
        f11719I = new d(12);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoordinatorLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.coordinatorLayoutStyle);
        int i7 = 0;
        this.l = new ArrayList();
        this.f11723m = new t(24);
        this.f11724n = new ArrayList();
        this.o = new ArrayList();
        this.f11725p = new int[2];
        this.f11726q = new int[2];
        this.f11722D = new C0254m(i7);
        int[] iArr = AbstractC2360a.f22065a;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, R.attr.coordinatorLayoutStyle, 0);
        saveAttributeDataForStyleable(context, iArr, attributeSet, typedArrayObtainStyledAttributes, R.attr.coordinatorLayoutStyle, 0);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        if (resourceId != 0) {
            Resources resources = context.getResources();
            int[] intArray = resources.getIntArray(resourceId);
            this.f11729t = intArray;
            float f10 = resources.getDisplayMetrics().density;
            int length = intArray.length;
            while (i7 < length) {
                this.f11729t[i7] = (int) (r2[i7] * f10);
                i7++;
            }
        }
        this.f11720A = typedArrayObtainStyledAttributes.getDrawable(1);
        typedArrayObtainStyledAttributes.recycle();
        w();
        super.setOnHierarchyChangeListener(new ViewGroupOnHierarchyChangeListenerC2427c(this));
        int[] iArr2 = H.f2303a;
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
    }

    public static Rect d() {
        Rect rect = (Rect) f11719I.a();
        return rect == null ? new Rect() : rect;
    }

    public static void l(int i7, Rect rect, Rect rect2, C2428d c2428d, int i10, int i11) {
        int i12 = c2428d.c;
        if (i12 == 0) {
            i12 = 17;
        }
        int absoluteGravity = Gravity.getAbsoluteGravity(i12, i7);
        int i13 = c2428d.d;
        if ((i13 & 7) == 0) {
            i13 |= 8388611;
        }
        if ((i13 & 112) == 0) {
            i13 |= 48;
        }
        int absoluteGravity2 = Gravity.getAbsoluteGravity(i13, i7);
        int i14 = absoluteGravity & 7;
        int i15 = absoluteGravity & 112;
        int i16 = absoluteGravity2 & 7;
        int i17 = absoluteGravity2 & 112;
        int iWidth = i16 != 1 ? i16 != 5 ? rect.left : rect.right : rect.left + (rect.width() / 2);
        int iHeight = i17 != 16 ? i17 != 80 ? rect.top : rect.bottom : rect.top + (rect.height() / 2);
        if (i14 == 1) {
            iWidth -= i10 / 2;
        } else if (i14 != 5) {
            iWidth -= i10;
        }
        if (i15 == 16) {
            iHeight -= i11 / 2;
        } else if (i15 != 80) {
            iHeight -= i11;
        }
        rect2.set(iWidth, iHeight, i10 + iWidth, i11 + iHeight);
    }

    public static C2428d n(View view) {
        C2428d c2428d = (C2428d) view.getLayoutParams();
        if (!c2428d.f22430b) {
            InterfaceC2426b interfaceC2426b = null;
            for (Class<?> superclass = view.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
                interfaceC2426b = (InterfaceC2426b) superclass.getAnnotation(InterfaceC2426b.class);
                if (interfaceC2426b != null) {
                    break;
                }
            }
            if (interfaceC2426b != null) {
                try {
                    AbstractC2425a abstractC2425a = (AbstractC2425a) interfaceC2426b.value().getDeclaredConstructor(null).newInstance(null);
                    AbstractC2425a abstractC2425a2 = c2428d.f22429a;
                    if (abstractC2425a2 != abstractC2425a) {
                        if (abstractC2425a2 != null) {
                            abstractC2425a2.e();
                        }
                        c2428d.f22429a = abstractC2425a;
                        c2428d.f22430b = true;
                        if (abstractC2425a != null) {
                            abstractC2425a.c(c2428d);
                        }
                    }
                } catch (Exception e10) {
                    n.i("CoordinatorLayout", "Default behavior class " + interfaceC2426b.value().getName() + " could not be instantiated. Did you forget a default constructor?", e10);
                }
            }
            c2428d.f22430b = true;
        }
        return c2428d;
    }

    public static void u(View view, int i7) {
        C2428d c2428d = (C2428d) view.getLayoutParams();
        int i10 = c2428d.f22435i;
        if (i10 != i7) {
            int[] iArr = H.f2303a;
            view.offsetLeftAndRight(i7 - i10);
            c2428d.f22435i = i7;
        }
    }

    public static void v(View view, int i7) {
        C2428d c2428d = (C2428d) view.getLayoutParams();
        int i10 = c2428d.f22436j;
        if (i10 != i7) {
            int[] iArr = H.f2303a;
            view.offsetTopAndBottom(i7 - i10);
            c2428d.f22436j = i7;
        }
    }

    @Override // F1.InterfaceC0252k
    public final void a(View view, View view2, int i7, int i10) {
        C0254m c0254m = this.f11722D;
        if (i10 == 1) {
            c0254m.c = i7;
        } else {
            c0254m.f2343b = i7;
        }
        this.f11731v = view2;
        int childCount = getChildCount();
        for (int i11 = 0; i11 < childCount; i11++) {
            ((C2428d) getChildAt(i11).getLayoutParams()).getClass();
        }
    }

    @Override // F1.InterfaceC0252k
    public final void b(View view, int i7) {
        C0254m c0254m = this.f11722D;
        if (i7 == 1) {
            c0254m.c = 0;
        } else {
            c0254m.f2343b = 0;
        }
        int childCount = getChildCount();
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = getChildAt(i10);
            C2428d c2428d = (C2428d) childAt.getLayoutParams();
            if (c2428d.a(i7)) {
                AbstractC2425a abstractC2425a = c2428d.f22429a;
                if (abstractC2425a != null) {
                    abstractC2425a.p(childAt, view, i7);
                }
                if (i7 == 0) {
                    c2428d.f22438m = false;
                } else if (i7 == 1) {
                    c2428d.f22439n = false;
                }
            }
        }
        this.f11731v = null;
    }

    @Override // F1.InterfaceC0252k
    public final void c(View view, int i7, int i10, int[] iArr, int i11) {
        AbstractC2425a abstractC2425a;
        int childCount = getChildCount();
        boolean z6 = false;
        int iMax = 0;
        int iMax2 = 0;
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt = getChildAt(i12);
            if (childAt.getVisibility() != 8) {
                C2428d c2428d = (C2428d) childAt.getLayoutParams();
                if (c2428d.a(i11) && (abstractC2425a = c2428d.f22429a) != null) {
                    int[] iArr2 = this.f11725p;
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    abstractC2425a.j(this, childAt, view, i7, i10, iArr2, i11);
                    iMax = i7 > 0 ? Math.max(iMax, iArr2[0]) : Math.min(iMax, iArr2[0]);
                    iMax2 = i10 > 0 ? Math.max(iMax2, iArr2[1]) : Math.min(iMax2, iArr2[1]);
                    z6 = true;
                }
            }
        }
        iArr[0] = iMax;
        iArr[1] = iMax2;
        if (z6) {
            p(1);
        }
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof C2428d) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j3) {
        AbstractC2425a abstractC2425a = ((C2428d) view.getLayoutParams()).f22429a;
        if (abstractC2425a != null) {
            abstractC2425a.getClass();
        }
        return super.drawChild(canvas, view, j3);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.f11720A;
        if ((drawable == null || !drawable.isStateful()) ? false : drawable.setState(drawableState)) {
            invalidate();
        }
    }

    public final void e(C2428d c2428d, Rect rect, int i7, int i10) {
        int width = getWidth();
        int height = getHeight();
        int iMax = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) c2428d).leftMargin, Math.min(rect.left, ((width - getPaddingRight()) - i7) - ((ViewGroup.MarginLayoutParams) c2428d).rightMargin));
        int iMax2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) c2428d).topMargin, Math.min(rect.top, ((height - getPaddingBottom()) - i10) - ((ViewGroup.MarginLayoutParams) c2428d).bottomMargin));
        rect.set(iMax, iMax2, i7 + iMax, i10 + iMax2);
    }

    public final void f(View view, Rect rect, boolean z6) {
        if (view.isLayoutRequested() || view.getVisibility() == 8) {
            rect.setEmpty();
        } else if (z6) {
            k(view, rect);
        } else {
            rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
    }

    @Override // F1.InterfaceC0253l
    public final void g(View view, int i7, int i10, int i11, int i12, int i13, int[] iArr) {
        AbstractC2425a abstractC2425a;
        int childCount = getChildCount();
        boolean z6 = false;
        int iMax = 0;
        int iMax2 = 0;
        for (int i14 = 0; i14 < childCount; i14++) {
            View childAt = getChildAt(i14);
            if (childAt.getVisibility() != 8) {
                C2428d c2428d = (C2428d) childAt.getLayoutParams();
                if (c2428d.a(i13) && (abstractC2425a = c2428d.f22429a) != null) {
                    int[] iArr2 = this.f11725p;
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    abstractC2425a.k(this, childAt, i10, i11, i12, iArr2);
                    iMax = i11 > 0 ? Math.max(iMax, iArr2[0]) : Math.min(iMax, iArr2[0]);
                    iMax2 = i12 > 0 ? Math.max(iMax2, iArr2[1]) : Math.min(iMax2, iArr2[1]);
                    z6 = true;
                }
            }
        }
        iArr[0] = iArr[0] + iMax;
        iArr[1] = iArr[1] + iMax2;
        if (z6) {
            p(1);
        }
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new C2428d();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new C2428d(getContext(), attributeSet);
    }

    public final List<View> getDependencySortedChildren() {
        s();
        return Collections.unmodifiableList(this.l);
    }

    public final V getLastWindowInsets() {
        return this.f11734y;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        C0254m c0254m = this.f11722D;
        return c0254m.c | c0254m.f2343b;
    }

    public Drawable getStatusBarBackground() {
        return this.f11720A;
    }

    @Override // android.view.View
    public int getSuggestedMinimumHeight() {
        return Math.max(super.getSuggestedMinimumHeight(), getPaddingBottom() + getPaddingTop());
    }

    @Override // android.view.View
    public int getSuggestedMinimumWidth() {
        return Math.max(super.getSuggestedMinimumWidth(), getPaddingRight() + getPaddingLeft());
    }

    @Override // F1.InterfaceC0252k
    public final void h(View view, int i7, int i10, int i11, int i12, int i13) {
        g(view, i7, i10, i11, i12, 0, this.f11726q);
    }

    @Override // F1.InterfaceC0252k
    public final boolean i(View view, View view2, int i7, int i10) {
        int childCount = getChildCount();
        boolean z6 = false;
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            if (childAt.getVisibility() != 8) {
                C2428d c2428d = (C2428d) childAt.getLayoutParams();
                AbstractC2425a abstractC2425a = c2428d.f22429a;
                if (abstractC2425a != null) {
                    boolean zO = abstractC2425a.o(childAt, i7, i10);
                    z6 |= zO;
                    if (i10 == 0) {
                        c2428d.f22438m = zO;
                    } else if (i10 == 1) {
                        c2428d.f22439n = zO;
                    }
                } else if (i10 == 0) {
                    c2428d.f22438m = false;
                } else if (i10 == 1) {
                    c2428d.f22439n = false;
                }
            }
        }
        return z6;
    }

    public final ArrayList j(View view) {
        C2458F c2458f = (C2458F) this.f11723m.o;
        int i7 = c2458f.f22587n;
        ArrayList arrayList = null;
        for (int i10 = 0; i10 < i7; i10++) {
            ArrayList arrayList2 = (ArrayList) c2458f.k(i10);
            if (arrayList2 != null && arrayList2.contains(view)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(c2458f.g(i10));
            }
        }
        ArrayList arrayList3 = this.o;
        arrayList3.clear();
        if (arrayList != null) {
            arrayList3.addAll(arrayList);
        }
        return arrayList3;
    }

    public final void k(View view, Rect rect) {
        ThreadLocal threadLocal = AbstractC2431g.f22442a;
        rect.set(0, 0, view.getWidth(), view.getHeight());
        ThreadLocal threadLocal2 = AbstractC2431g.f22442a;
        Matrix matrix = (Matrix) threadLocal2.get();
        if (matrix == null) {
            matrix = new Matrix();
            threadLocal2.set(matrix);
        } else {
            matrix.reset();
        }
        AbstractC2431g.a(this, view, matrix);
        ThreadLocal threadLocal3 = AbstractC2431g.f22443b;
        RectF rectF = (RectF) threadLocal3.get();
        if (rectF == null) {
            rectF = new RectF();
            threadLocal3.set(rectF);
        }
        rectF.set(rect);
        matrix.mapRect(rectF);
        rect.set((int) (rectF.left + 0.5f), (int) (rectF.top + 0.5f), (int) (rectF.right + 0.5f), (int) (rectF.bottom + 0.5f));
    }

    public final int m(int i7) {
        int[] iArr = this.f11729t;
        if (iArr == null) {
            n.j("CoordinatorLayout", "No keylines defined for " + this + " - attempted index lookup " + i7);
            return 0;
        }
        if (i7 >= 0 && i7 < iArr.length) {
            return iArr[i7];
        }
        n.j("CoordinatorLayout", "Keyline index " + i7 + " out of range for " + this);
        return 0;
    }

    public final boolean o(View view, int i7, int i10) {
        d dVar = f11719I;
        Rect rectD = d();
        k(view, rectD);
        try {
            return rectD.contains(i7, i10);
        } finally {
            rectD.setEmpty();
            dVar.c(rectD);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        t(false);
        if (this.f11733x) {
            if (this.f11732w == null) {
                this.f11732w = new ViewTreeObserverOnPreDrawListenerC2429e(this);
            }
            getViewTreeObserver().addOnPreDrawListener(this.f11732w);
        }
        if (this.f11734y == null) {
            int[] iArr = H.f2303a;
            if (getFitsSystemWindows()) {
                AbstractC0265y.b(this);
            }
        }
        this.f11728s = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        t(false);
        if (this.f11733x && this.f11732w != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.f11732w);
        }
        View view = this.f11731v;
        if (view != null) {
            b(view, 0);
        }
        this.f11728s = false;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.f11735z || this.f11720A == null) {
            return;
        }
        V v10 = this.f11734y;
        int iA = v10 != null ? v10.a() : 0;
        if (iA > 0) {
            this.f11720A.setBounds(0, 0, getWidth(), iA);
            this.f11720A.draw(canvas);
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
    public final void onLayout(boolean z6, int i7, int i10, int i11, int i12) {
        AbstractC2425a abstractC2425a;
        int[] iArr = H.f2303a;
        int layoutDirection = getLayoutDirection();
        ArrayList arrayList = this.l;
        int size = arrayList.size();
        for (int i13 = 0; i13 < size; i13++) {
            View view = (View) arrayList.get(i13);
            if (view.getVisibility() != 8 && ((abstractC2425a = ((C2428d) view.getLayoutParams()).f22429a) == null || !abstractC2425a.g(this, view, layoutDirection))) {
                q(view, layoutDirection);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:71:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0186  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onMeasure(int r30, int r31) {
        /*
            Method dump skipped, instruction units count: 504
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.onMeasure(int, int):void");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f10, float f11, boolean z6) {
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                C2428d c2428d = (C2428d) childAt.getLayoutParams();
                if (c2428d.a(0)) {
                    AbstractC2425a abstractC2425a = c2428d.f22429a;
                }
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f10, float f11) {
        AbstractC2425a abstractC2425a;
        int childCount = getChildCount();
        boolean zI = false;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                C2428d c2428d = (C2428d) childAt.getLayoutParams();
                if (c2428d.a(0) && (abstractC2425a = c2428d.f22429a) != null) {
                    zI |= abstractC2425a.i(view);
                }
            }
        }
        return zI;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedPreScroll(View view, int i7, int i10, int[] iArr) {
        c(view, i7, i10, iArr, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScroll(View view, int i7, int i10, int i11, int i12) {
        h(view, i7, i10, i11, i12, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScrollAccepted(View view, View view2, int i7) {
        a(view, view2, i7, 0);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof C2430f)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        C2430f c2430f = (C2430f) parcelable;
        super.onRestoreInstanceState(c2430f.l);
        SparseArray sparseArray = c2430f.f22441n;
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            int id = childAt.getId();
            AbstractC2425a abstractC2425a = n(childAt).f22429a;
            if (id != -1 && abstractC2425a != null && (parcelable2 = (Parcelable) sparseArray.get(id)) != null) {
                abstractC2425a.m(childAt, parcelable2);
            }
        }
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        Parcelable parcelableN;
        C2430f c2430f = new C2430f(super.onSaveInstanceState());
        SparseArray sparseArray = new SparseArray();
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            int id = childAt.getId();
            AbstractC2425a abstractC2425a = ((C2428d) childAt.getLayoutParams()).f22429a;
            if (id != -1 && abstractC2425a != null && (parcelableN = abstractC2425a.n(childAt)) != null) {
                sparseArray.append(id, parcelableN);
            }
        }
        c2430f.f22441n = sparseArray;
        return c2430f;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onStartNestedScroll(View view, View view2, int i7) {
        return i(view, view2, i7, 0);
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
            android.view.View r3 = r0.f11730u
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
            android.view.View r6 = r0.f11730u
            android.view.ViewGroup$LayoutParams r6 = r6.getLayoutParams()
            t1.d r6 = (t1.C2428d) r6
            t1.a r6 = r6.f22429a
            if (r6 == 0) goto L15
            android.view.View r7 = r0.f11730u
            boolean r6 = r6.q(r7, r1)
        L2a:
            android.view.View r7 = r0.f11730u
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

    /* JADX WARN: Removed duplicated region for block: B:112:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x02df  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x004b A[EDGE_INSN: B:134:0x004b->B:10:0x004b BREAK  A[LOOP:2: B:122:0x02db->B:127:0x02f2], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00ec  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void p(int r26) {
        /*
            Method dump skipped, instruction units count: 794
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.p(int):void");
    }

    public final void q(View view, int i7) {
        Rect rectD;
        Rect rectD2;
        C2428d c2428d = (C2428d) view.getLayoutParams();
        View view2 = c2428d.f22437k;
        if (view2 == null && c2428d.f22432f != -1) {
            throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
        }
        d dVar = f11719I;
        if (view2 != null) {
            rectD = d();
            rectD2 = d();
            try {
                k(view2, rectD);
                C2428d c2428d2 = (C2428d) view.getLayoutParams();
                int measuredWidth = view.getMeasuredWidth();
                int measuredHeight = view.getMeasuredHeight();
                l(i7, rectD, rectD2, c2428d2, measuredWidth, measuredHeight);
                e(c2428d2, rectD2, measuredWidth, measuredHeight);
                view.layout(rectD2.left, rectD2.top, rectD2.right, rectD2.bottom);
                return;
            } finally {
                rectD.setEmpty();
                dVar.c(rectD);
                rectD2.setEmpty();
                dVar.c(rectD2);
            }
        }
        int i10 = c2428d.f22431e;
        if (i10 < 0) {
            C2428d c2428d3 = (C2428d) view.getLayoutParams();
            rectD = d();
            rectD.set(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) c2428d3).leftMargin, getPaddingTop() + ((ViewGroup.MarginLayoutParams) c2428d3).topMargin, (getWidth() - getPaddingRight()) - ((ViewGroup.MarginLayoutParams) c2428d3).rightMargin, (getHeight() - getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) c2428d3).bottomMargin);
            if (this.f11734y != null) {
                int[] iArr = H.f2303a;
                if (getFitsSystemWindows() && !view.getFitsSystemWindows()) {
                    rectD.left = this.f11734y.f2321a.h().f24113a + rectD.left;
                    rectD.top = this.f11734y.a() + rectD.top;
                    rectD.right -= this.f11734y.f2321a.h().c;
                    rectD.bottom -= this.f11734y.f2321a.h().d;
                }
            }
            rectD2 = d();
            int i11 = c2428d3.c;
            if ((i11 & 7) == 0) {
                i11 |= 8388611;
            }
            if ((i11 & 112) == 0) {
                i11 |= 48;
            }
            Gravity.apply(i11, view.getMeasuredWidth(), view.getMeasuredHeight(), rectD, rectD2, i7);
            view.layout(rectD2.left, rectD2.top, rectD2.right, rectD2.bottom);
            return;
        }
        C2428d c2428d4 = (C2428d) view.getLayoutParams();
        int i12 = c2428d4.c;
        if (i12 == 0) {
            i12 = 8388661;
        }
        int absoluteGravity = Gravity.getAbsoluteGravity(i12, i7);
        int i13 = absoluteGravity & 7;
        int i14 = absoluteGravity & 112;
        int width = getWidth();
        int height = getHeight();
        int measuredWidth2 = view.getMeasuredWidth();
        int measuredHeight2 = view.getMeasuredHeight();
        if (i7 == 1) {
            i10 = width - i10;
        }
        int iM = m(i10) - measuredWidth2;
        if (i13 == 1) {
            iM += measuredWidth2 / 2;
        } else if (i13 == 5) {
            iM += measuredWidth2;
        }
        int i15 = i14 != 16 ? i14 != 80 ? 0 : measuredHeight2 : measuredHeight2 / 2;
        int iMax = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) c2428d4).leftMargin, Math.min(iM, ((width - getPaddingRight()) - measuredWidth2) - ((ViewGroup.MarginLayoutParams) c2428d4).rightMargin));
        int iMax2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) c2428d4).topMargin, Math.min(i15, ((height - getPaddingBottom()) - measuredHeight2) - ((ViewGroup.MarginLayoutParams) c2428d4).bottomMargin));
        view.layout(iMax, iMax2, measuredWidth2 + iMax, measuredHeight2 + iMax2);
    }

    public final boolean r(MotionEvent motionEvent, int i7) {
        int actionMasked = motionEvent.getActionMasked();
        ArrayList arrayList = this.f11724n;
        arrayList.clear();
        boolean zIsChildrenDrawingOrderEnabled = isChildrenDrawingOrderEnabled();
        int childCount = getChildCount();
        for (int i10 = childCount - 1; i10 >= 0; i10--) {
            arrayList.add(getChildAt(zIsChildrenDrawingOrderEnabled ? getChildDrawingOrder(childCount, i10) : i10));
        }
        y yVar = f11718H;
        if (yVar != null) {
            Collections.sort(arrayList, yVar);
        }
        int size = arrayList.size();
        MotionEvent motionEventObtain = null;
        boolean zF = false;
        for (int i11 = 0; i11 < size; i11++) {
            View view = (View) arrayList.get(i11);
            AbstractC2425a abstractC2425a = ((C2428d) view.getLayoutParams()).f22429a;
            if (zF && actionMasked != 0) {
                if (abstractC2425a != null) {
                    if (motionEventObtain == null) {
                        long jUptimeMillis = SystemClock.uptimeMillis();
                        motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                    }
                    if (i7 == 0) {
                        abstractC2425a.f(this, view, motionEventObtain);
                    } else if (i7 == 1) {
                        abstractC2425a.q(view, motionEventObtain);
                    }
                }
            } else if (!zF && abstractC2425a != null) {
                if (i7 == 0) {
                    zF = abstractC2425a.f(this, view, motionEvent);
                } else if (i7 == 1) {
                    zF = abstractC2425a.q(view, motionEvent);
                }
                if (zF) {
                    this.f11730u = view;
                }
            }
        }
        arrayList.clear();
        return zF;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z6) {
        AbstractC2425a abstractC2425a = ((C2428d) view.getLayoutParams()).f22429a;
        if (abstractC2425a != null) {
            abstractC2425a.l(this, view);
        }
        return super.requestChildRectangleOnScreen(view, rect, z6);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z6) {
        super.requestDisallowInterceptTouchEvent(z6);
        if (!z6 || this.f11727r) {
            return;
        }
        t(false);
        this.f11727r = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0104  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void s() {
        /*
            Method dump skipped, instruction units count: 400
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.s():void");
    }

    @Override // android.view.View
    public void setFitsSystemWindows(boolean z6) {
        super.setFitsSystemWindows(z6);
        w();
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.B = onHierarchyChangeListener;
    }

    public void setStatusBarBackground(Drawable drawable) {
        Drawable drawable2 = this.f11720A;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.f11720A = drawableMutate;
            if (drawableMutate != null) {
                if (drawableMutate.isStateful()) {
                    this.f11720A.setState(getDrawableState());
                }
                Drawable drawable3 = this.f11720A;
                int[] iArr = H.f2303a;
                drawable3.setLayoutDirection(getLayoutDirection());
                this.f11720A.setVisible(getVisibility() == 0, false);
                this.f11720A.setCallback(this);
            }
            int[] iArr2 = H.f2303a;
            postInvalidateOnAnimation();
        }
    }

    public void setStatusBarBackgroundColor(int i7) {
        setStatusBarBackground(new ColorDrawable(i7));
    }

    public void setStatusBarBackgroundResource(int i7) {
        setStatusBarBackground(i7 != 0 ? getContext().getDrawable(i7) : null);
    }

    @Override // android.view.View
    public void setVisibility(int i7) {
        super.setVisibility(i7);
        boolean z6 = i7 == 0;
        Drawable drawable = this.f11720A;
        if (drawable == null || drawable.isVisible() == z6) {
            return;
        }
        this.f11720A.setVisible(z6, false);
    }

    public final void t(boolean z6) {
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            AbstractC2425a abstractC2425a = ((C2428d) childAt.getLayoutParams()).f22429a;
            if (abstractC2425a != null) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                if (z6) {
                    abstractC2425a.f(this, childAt, motionEventObtain);
                } else {
                    abstractC2425a.q(childAt, motionEventObtain);
                }
                motionEventObtain.recycle();
            }
        }
        for (int i10 = 0; i10 < childCount; i10++) {
            ((C2428d) getChildAt(i10).getLayoutParams()).getClass();
        }
        this.f11730u = null;
        this.f11727r = false;
    }

    @Override // android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f11720A;
    }

    public final void w() {
        int[] iArr = H.f2303a;
        if (!getFitsSystemWindows()) {
            A.k(this, null);
            return;
        }
        if (this.f11721C == null) {
            this.f11721C = new C1764u(this, 25);
        }
        A.k(this, this.f11721C);
        setSystemUiVisibility(1280);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C2428d ? new C2428d((C2428d) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new C2428d((ViewGroup.MarginLayoutParams) layoutParams) : new C2428d(layoutParams);
    }
}
