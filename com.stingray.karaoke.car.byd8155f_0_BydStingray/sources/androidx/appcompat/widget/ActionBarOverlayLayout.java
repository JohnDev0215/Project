package androidx.appcompat.widget;

import F1.A;
import F1.AbstractC0265y;
import F1.C0254m;
import F1.H;
import F1.InterfaceC0252k;
import F1.InterfaceC0253l;
import F1.L;
import F1.M;
import F1.N;
import F1.U;
import F1.V;
import N4.a;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.OverScroller;
import com.stingray.drivemodedetector.R;
import h6.f;
import r.A0;
import r.C2244d;
import r.C2246e;
import r.InterfaceC2224L;
import r.InterfaceC2242c;
import r.RunnableC2240b;
import y1.b;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"UnknownNullness"})
public class ActionBarOverlayLayout extends ViewGroup implements InterfaceC0252k, InterfaceC0253l {

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public static final int[] f11307K = {R.attr.actionBarSize, android.R.attr.windowContentOverlay};

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public static final V f11308L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public static final Rect f11309M;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public V f11310A;
    public V B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public V f11311C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public OverScroller f11312D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public ViewPropertyAnimator f11313E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final a f11314F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final RunnableC2240b f11315G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final RunnableC2240b f11316H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public final C0254m f11317I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final C2246e f11318J;
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ContentFrameLayout f11319m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ActionBarContainer f11320n;
    public InterfaceC2224L o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Drawable f11321p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f11322q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f11323r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f11324s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f11325t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f11326u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final Rect f11327v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final Rect f11328w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final Rect f11329x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final Rect f11330y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public V f11331z;

    static {
        N m10 = Build.VERSION.SDK_INT >= 30 ? new M() : new L();
        m10.c(b.a(0, 1, 0, 1));
        f11308L = m10.b();
        f11309M = new Rect();
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f11327v = new Rect();
        this.f11328w = new Rect();
        this.f11329x = new Rect();
        this.f11330y = new Rect();
        new Rect();
        new Rect();
        new Rect();
        new Rect();
        V v10 = V.f2320b;
        this.f11331z = v10;
        this.f11310A = v10;
        this.B = v10;
        this.f11311C = v10;
        this.f11314F = new a(this, 2);
        this.f11315G = new RunnableC2240b(this, 0);
        this.f11316H = new RunnableC2240b(this, 1);
        f(context);
        this.f11317I = new C0254m(0);
        C2246e c2246e = new C2246e(context);
        c2246e.setWillNotDraw(true);
        this.f11318J = c2246e;
        addView(c2246e);
    }

    public static boolean d(View view, Rect rect, boolean z6) {
        boolean z10;
        C2244d c2244d = (C2244d) view.getLayoutParams();
        int i7 = ((ViewGroup.MarginLayoutParams) c2244d).leftMargin;
        int i10 = rect.left;
        if (i7 != i10) {
            ((ViewGroup.MarginLayoutParams) c2244d).leftMargin = i10;
            z10 = true;
        } else {
            z10 = false;
        }
        int i11 = ((ViewGroup.MarginLayoutParams) c2244d).topMargin;
        int i12 = rect.top;
        if (i11 != i12) {
            ((ViewGroup.MarginLayoutParams) c2244d).topMargin = i12;
            z10 = true;
        }
        int i13 = ((ViewGroup.MarginLayoutParams) c2244d).rightMargin;
        int i14 = rect.right;
        if (i13 != i14) {
            ((ViewGroup.MarginLayoutParams) c2244d).rightMargin = i14;
            z10 = true;
        }
        if (z6) {
            int i15 = ((ViewGroup.MarginLayoutParams) c2244d).bottomMargin;
            int i16 = rect.bottom;
            if (i15 != i16) {
                ((ViewGroup.MarginLayoutParams) c2244d).bottomMargin = i16;
                return true;
            }
        }
        return z10;
    }

    @Override // F1.InterfaceC0252k
    public final void a(View view, View view2, int i7, int i10) {
        if (i10 == 0) {
            onNestedScrollAccepted(view, view2, i7);
        }
    }

    @Override // F1.InterfaceC0252k
    public final void b(View view, int i7) {
        if (i7 == 0) {
            onStopNestedScroll(view);
        }
    }

    @Override // F1.InterfaceC0252k
    public final void c(View view, int i7, int i10, int[] iArr, int i11) {
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C2244d;
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        int translationY;
        super.draw(canvas);
        if (this.f11321p != null) {
            if (this.f11320n.getVisibility() == 0) {
                translationY = (int) (this.f11320n.getTranslationY() + this.f11320n.getBottom() + 0.5f);
            } else {
                translationY = 0;
            }
            this.f11321p.setBounds(0, translationY, getWidth(), this.f11321p.getIntrinsicHeight() + translationY);
            this.f11321p.draw(canvas);
        }
    }

    public final void e() {
        removeCallbacks(this.f11315G);
        removeCallbacks(this.f11316H);
        ViewPropertyAnimator viewPropertyAnimator = this.f11313E;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    public final void f(Context context) {
        TypedArray typedArrayObtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(f11307K);
        this.l = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(1);
        this.f11321p = drawable;
        setWillNotDraw(drawable == null);
        typedArrayObtainStyledAttributes.recycle();
        this.f11312D = new OverScroller(context);
    }

    @Override // android.view.View
    public final boolean fitSystemWindows(Rect rect) {
        return super.fitSystemWindows(rect);
    }

    @Override // F1.InterfaceC0253l
    public final void g(View view, int i7, int i10, int i11, int i12, int i13, int[] iArr) {
        h(view, i7, i10, i11, i12, i13);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new C2244d(-1, -1);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new C2244d(getContext(), attributeSet);
    }

    public int getActionBarHideOffset() {
        ActionBarContainer actionBarContainer = this.f11320n;
        if (actionBarContainer != null) {
            return -((int) actionBarContainer.getTranslationY());
        }
        return 0;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        C0254m c0254m = this.f11317I;
        return c0254m.c | c0254m.f2343b;
    }

    public CharSequence getTitle() {
        j();
        return ((A0) this.o).f21408a.getTitle();
    }

    @Override // F1.InterfaceC0252k
    public final void h(View view, int i7, int i10, int i11, int i12, int i13) {
        if (i13 == 0) {
            onNestedScroll(view, i7, i10, i11, i12);
        }
    }

    @Override // F1.InterfaceC0252k
    public final boolean i(View view, View view2, int i7, int i10) {
        return i10 == 0 && onStartNestedScroll(view, view2, i7);
    }

    public final void j() {
        InterfaceC2224L wrapper;
        if (this.f11319m == null) {
            this.f11319m = (ContentFrameLayout) findViewById(R.id.action_bar_activity_content);
            this.f11320n = (ActionBarContainer) findViewById(R.id.action_bar_container);
            KeyEvent.Callback callbackFindViewById = findViewById(R.id.action_bar);
            if (callbackFindViewById instanceof InterfaceC2224L) {
                wrapper = (InterfaceC2224L) callbackFindViewById;
            } else {
                if (!(callbackFindViewById instanceof Toolbar)) {
                    throw new IllegalStateException("Can't make a decor toolbar out of ".concat(callbackFindViewById.getClass().getSimpleName()));
                }
                wrapper = ((Toolbar) callbackFindViewById).getWrapper();
            }
            this.o = wrapper;
        }
    }

    @Override // android.view.View
    public final WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        j();
        V vC = V.c(windowInsets, this);
        U u4 = vC.f2321a;
        boolean zD = d(this.f11320n, new Rect(u4.h().f24113a, vC.a(), u4.h().c, u4.h().d), false);
        int[] iArr = H.f2303a;
        Rect rect = this.f11327v;
        A.b(this, vC, rect);
        V vI = u4.i(rect.left, rect.top, rect.right, rect.bottom);
        this.f11331z = vI;
        boolean z6 = true;
        if (!this.f11310A.equals(vI)) {
            this.f11310A = this.f11331z;
            zD = true;
        }
        Rect rect2 = this.f11328w;
        if (rect2.equals(rect)) {
            z6 = zD;
        } else {
            rect2.set(rect);
        }
        if (z6) {
            requestLayout();
        }
        return u4.a().f2321a.c().f2321a.b().b();
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        f(getContext());
        int[] iArr = H.f2303a;
        AbstractC0265y.b(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        e();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i10, int i11, int i12) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i13 = 0; i13 < childCount; i13++) {
            View childAt = getChildAt(i13);
            if (childAt.getVisibility() != 8) {
                C2244d c2244d = (C2244d) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i14 = ((ViewGroup.MarginLayoutParams) c2244d).leftMargin + paddingLeft;
                int i15 = ((ViewGroup.MarginLayoutParams) c2244d).topMargin + paddingTop;
                childAt.layout(i14, i15, measuredWidth + i14, measuredHeight + i15);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00aa  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onMeasure(int r13, int r14) {
        /*
            Method dump skipped, instruction units count: 389
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActionBarOverlayLayout.onMeasure(int, int):void");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f10, float f11, boolean z6) {
        if (!this.f11324s || !z6) {
            return false;
        }
        this.f11312D.fling(0, 0, 0, (int) f11, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (this.f11312D.getFinalY() > this.f11320n.getHeight()) {
            e();
            this.f11316H.run();
        } else {
            e();
            this.f11315G.run();
        }
        this.f11325t = true;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f10, float f11) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedPreScroll(View view, int i7, int i10, int[] iArr) {
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScroll(View view, int i7, int i10, int i11, int i12) {
        int i13 = this.f11326u + i10;
        this.f11326u = i13;
        setActionBarHideOffset(i13);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScrollAccepted(View view, View view2, int i7) {
        this.f11317I.f2343b = i7;
        this.f11326u = getActionBarHideOffset();
        e();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onStartNestedScroll(View view, View view2, int i7) {
        if ((i7 & 2) == 0 || this.f11320n.getVisibility() != 0) {
            return false;
        }
        return this.f11324s;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onStopNestedScroll(View view) {
        if (!this.f11324s || this.f11325t) {
            return;
        }
        if (this.f11326u <= this.f11320n.getHeight()) {
            e();
            postDelayed(this.f11315G, 600L);
        } else {
            e();
            postDelayed(this.f11316H, 600L);
        }
    }

    @Override // android.view.View
    public final void onWindowSystemUiVisibilityChanged(int i7) {
        super.onWindowSystemUiVisibilityChanged(i7);
        j();
    }

    @Override // android.view.View
    public final void onWindowVisibilityChanged(int i7) {
        super.onWindowVisibilityChanged(i7);
    }

    public void setActionBarHideOffset(int i7) {
        e();
        this.f11320n.setTranslationY(-Math.max(0, Math.min(i7, this.f11320n.getHeight())));
    }

    public void setActionBarVisibilityCallback(InterfaceC2242c interfaceC2242c) {
        if (getWindowToken() != null) {
            throw null;
        }
    }

    public void setHasNonEmbeddedTabs(boolean z6) {
        this.f11323r = z6;
    }

    public void setHideOnContentScrollEnabled(boolean z6) {
        if (z6 != this.f11324s) {
            this.f11324s = z6;
            if (z6) {
                return;
            }
            e();
            setActionBarHideOffset(0);
        }
    }

    public void setIcon(int i7) {
        j();
        A0 a02 = (A0) this.o;
        a02.d = i7 != 0 ? f.k(a02.f21408a.getContext(), i7) : null;
        a02.c();
    }

    public void setLogo(int i7) {
        j();
        A0 a02 = (A0) this.o;
        a02.f21410e = i7 != 0 ? f.k(a02.f21408a.getContext(), i7) : null;
        a02.c();
    }

    public void setOverlayMode(boolean z6) {
        this.f11322q = z6;
    }

    public void setShowingForActionMode(boolean z6) {
    }

    public void setUiOptions(int i7) {
    }

    public void setWindowCallback(Window.Callback callback) {
        j();
        ((A0) this.o).f21416k = callback;
    }

    public void setWindowTitle(CharSequence charSequence) {
        j();
        A0 a02 = (A0) this.o;
        if (a02.f21412g) {
            return;
        }
        a02.f21413h = charSequence;
        if ((a02.f21409b & 8) != 0) {
            Toolbar toolbar = a02.f21408a;
            toolbar.setTitle(charSequence);
            if (a02.f21412g) {
                H.h(toolbar.getRootView(), charSequence);
            }
        }
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new C2244d(layoutParams);
    }

    public void setIcon(Drawable drawable) {
        j();
        A0 a02 = (A0) this.o;
        a02.d = drawable;
        a02.c();
    }
}
