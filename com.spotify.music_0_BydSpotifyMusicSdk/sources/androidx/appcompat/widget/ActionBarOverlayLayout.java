package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import com.spotify.music.R;
import java.util.WeakHashMap;
import p.dm2;
import p.dx2;
import p.fx2;
import p.g4;
import p.g8;
import p.hk0;
import p.hm2;
import p.i60;
import p.it2;
import p.j60;
import p.l13;
import p.mt2;
import p.ot2;
import p.ow2;
import p.qm1;
import p.rm1;
import p.si1;
import p.sm1;
import p.u3;
import p.v3;
import p.vu2;
import p.w3;
import p.w41;
import p.ww2;
import p.x3;
import p.zt2;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"UnknownNullness"})
public class ActionBarOverlayLayout extends ViewGroup implements i60, qm1, rm1 {
    public static final int[] J = {R.attr.actionBarSize, android.R.attr.windowContentOverlay};
    public fx2 A;
    public fx2 B;
    public w3 C;
    public OverScroller D;
    public ViewPropertyAnimator E;
    public final u3 F;
    public final v3 G;
    public final v3 H;
    public final sm1 I;
    public int c;
    public int e;
    public ContentFrameLayout k;
    public ActionBarContainer l;
    public j60 m;
    public Drawable n;
    public boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f8p;
    public boolean q;
    public boolean r;
    public boolean s;
    public int t;
    public int u;
    public final Rect v;
    public final Rect w;
    public final Rect x;
    public fx2 y;
    public fx2 z;

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = 0;
        this.v = new Rect();
        this.w = new Rect();
        this.x = new Rect();
        new Rect();
        new Rect();
        new Rect();
        new Rect();
        fx2 fx2Var = fx2.b;
        this.y = fx2Var;
        this.z = fx2Var;
        this.A = fx2Var;
        this.B = fx2Var;
        this.F = new u3(0, this);
        this.G = new v3(this, 0);
        this.H = new v3(this, 1);
        i(context);
        this.I = new sm1();
    }

    public static boolean g(FrameLayout frameLayout, Rect rect, boolean z) {
        boolean z2;
        x3 x3Var = (x3) frameLayout.getLayoutParams();
        int i = ((ViewGroup.MarginLayoutParams) x3Var).leftMargin;
        int i2 = rect.left;
        if (i != i2) {
            ((ViewGroup.MarginLayoutParams) x3Var).leftMargin = i2;
            z2 = true;
        } else {
            z2 = false;
        }
        int i3 = ((ViewGroup.MarginLayoutParams) x3Var).topMargin;
        int i4 = rect.top;
        if (i3 != i4) {
            ((ViewGroup.MarginLayoutParams) x3Var).topMargin = i4;
            z2 = true;
        }
        int i5 = ((ViewGroup.MarginLayoutParams) x3Var).rightMargin;
        int i6 = rect.right;
        if (i5 != i6) {
            ((ViewGroup.MarginLayoutParams) x3Var).rightMargin = i6;
            z2 = true;
        }
        if (z) {
            int i7 = ((ViewGroup.MarginLayoutParams) x3Var).bottomMargin;
            int i8 = rect.bottom;
            if (i7 != i8) {
                ((ViewGroup.MarginLayoutParams) x3Var).bottomMargin = i8;
                return true;
            }
        }
        return z2;
    }

    @Override // p.qm1
    public final void a(View view, View view2, int i, int i2) {
        if (i2 == 0) {
            onNestedScrollAccepted(view, view2, i);
        }
    }

    @Override // p.qm1
    public final void b(View view, int i) {
        if (i == 0) {
            onStopNestedScroll(view);
        }
    }

    @Override // p.qm1
    public final void c(View view, int i, int i2, int[] iArr, int i3) {
        if (i3 == 0) {
            onNestedPreScroll(view, i, i2, iArr);
        }
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof x3;
    }

    @Override // p.rm1
    public final void d(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        e(view, i, i2, i3, i4, i5);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        int translationY;
        super.draw(canvas);
        if (this.n == null || this.o) {
            return;
        }
        if (this.l.getVisibility() == 0) {
            translationY = (int) (this.l.getTranslationY() + this.l.getBottom() + 0.5f);
        } else {
            translationY = 0;
        }
        this.n.setBounds(0, translationY, getWidth(), this.n.getIntrinsicHeight() + translationY);
        this.n.draw(canvas);
    }

    @Override // p.qm1
    public final void e(View view, int i, int i2, int i3, int i4, int i5) {
        if (i5 == 0) {
            onNestedScroll(view, i, i2, i3, i4);
        }
    }

    @Override // p.qm1
    public final boolean f(View view, View view2, int i, int i2) {
        return i2 == 0 && onStartNestedScroll(view, view2, i);
    }

    @Override // android.view.View
    public final boolean fitSystemWindows(Rect rect) {
        return super.fitSystemWindows(rect);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new x3(-1, -1);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new x3(getContext(), attributeSet);
    }

    public int getActionBarHideOffset() {
        ActionBarContainer actionBarContainer = this.l;
        if (actionBarContainer != null) {
            return -((int) actionBarContainer.getTranslationY());
        }
        return 0;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        sm1 sm1Var = this.I;
        return sm1Var.c | sm1Var.b;
    }

    public CharSequence getTitle() {
        k();
        return ((hm2) this.m).a.getTitle();
    }

    public final void h() {
        removeCallbacks(this.G);
        removeCallbacks(this.H);
        ViewPropertyAnimator viewPropertyAnimator = this.E;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    public final void i(Context context) {
        TypedArray typedArrayObtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(J);
        this.c = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(1);
        this.n = drawable;
        setWillNotDraw(drawable == null);
        typedArrayObtainStyledAttributes.recycle();
        this.o = context.getApplicationInfo().targetSdkVersion < 19;
        this.D = new OverScroller(context);
    }

    public final void j(int i) {
        k();
        if (i == 2) {
            this.m.getClass();
        } else if (i == 5) {
            this.m.getClass();
        } else {
            if (i != 109) {
                return;
            }
            setOverlayMode(true);
        }
    }

    public final void k() {
        j60 wrapper;
        if (this.k == null) {
            this.k = (ContentFrameLayout) findViewById(R.id.action_bar_activity_content);
            this.l = (ActionBarContainer) findViewById(R.id.action_bar_container);
            KeyEvent.Callback callbackFindViewById = findViewById(R.id.action_bar);
            if (callbackFindViewById instanceof j60) {
                wrapper = (j60) callbackFindViewById;
            } else {
                if (!(callbackFindViewById instanceof Toolbar)) {
                    throw new IllegalStateException("Can't make a decor toolbar out of ".concat(callbackFindViewById.getClass().getSimpleName()));
                }
                wrapper = ((Toolbar) callbackFindViewById).getWrapper();
            }
            this.m = wrapper;
        }
    }

    public final void l(si1 si1Var, g8 g8Var) {
        k();
        hm2 hm2Var = (hm2) this.m;
        g4 g4Var = hm2Var.m;
        Toolbar toolbar = hm2Var.a;
        if (g4Var == null) {
            hm2Var.m = new g4(toolbar.getContext());
        }
        g4 g4Var2 = hm2Var.m;
        g4Var2.m = g8Var;
        if (si1Var == null && toolbar.c == null) {
            return;
        }
        toolbar.f();
        si1 si1Var2 = toolbar.c.x;
        if (si1Var2 == si1Var) {
            return;
        }
        if (si1Var2 != null) {
            si1Var2.r(toolbar.S);
            si1Var2.r(toolbar.T);
        }
        if (toolbar.T == null) {
            toolbar.T = new dm2(toolbar);
        }
        g4Var2.y = true;
        if (si1Var != null) {
            si1Var.b(g4Var2, toolbar.r);
            si1Var.b(toolbar.T, toolbar.r);
        } else {
            g4Var2.g(toolbar.r, null);
            toolbar.T.g(toolbar.r, null);
            g4Var2.d();
            toolbar.T.d();
        }
        toolbar.c.setPopupTheme(toolbar.s);
        toolbar.c.setPresenter(g4Var2);
        toolbar.S = g4Var2;
        toolbar.u();
    }

    @Override // android.view.View
    public final WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        k();
        fx2 fx2VarG = fx2.g(windowInsets, this);
        boolean zG = g(this.l, new Rect(fx2VarG.b(), fx2VarG.d(), fx2VarG.c(), fx2VarG.a()), false);
        WeakHashMap weakHashMap = zt2.a;
        Rect rect = this.v;
        ot2.b(this, fx2VarG, rect);
        int i = rect.left;
        int i2 = rect.top;
        int i3 = rect.right;
        int i4 = rect.bottom;
        dx2 dx2Var = fx2VarG.a;
        fx2 fx2VarL = dx2Var.l(i, i2, i3, i4);
        this.y = fx2VarL;
        if (!this.z.equals(fx2VarL)) {
            this.z = this.y;
            zG = true;
        }
        Rect rect2 = this.w;
        if (rect2.equals(rect)) {
            if (zG) {
            }
            return dx2Var.a().a.c().a.b().f();
        }
        rect2.set(rect);
        requestLayout();
        return dx2Var.a().a.c().a.b().f();
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        i(getContext());
        WeakHashMap weakHashMap = zt2.a;
        mt2.c(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        h();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                x3 x3Var = (x3) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i6 = ((ViewGroup.MarginLayoutParams) x3Var).leftMargin + paddingLeft;
                int i7 = ((ViewGroup.MarginLayoutParams) x3Var).topMargin + paddingTop;
                childAt.layout(i6, i7, measuredWidth + i6, measuredHeight + i7);
            }
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int measuredHeight;
        k();
        measureChildWithMargins(this.l, i, 0, i2, 0);
        x3 x3Var = (x3) this.l.getLayoutParams();
        int iMax = Math.max(0, this.l.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) x3Var).leftMargin + ((ViewGroup.MarginLayoutParams) x3Var).rightMargin);
        int iMax2 = Math.max(0, this.l.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) x3Var).topMargin + ((ViewGroup.MarginLayoutParams) x3Var).bottomMargin);
        int iCombineMeasuredStates = View.combineMeasuredStates(0, this.l.getMeasuredState());
        WeakHashMap weakHashMap = zt2.a;
        boolean z = (it2.g(this) & 256) != 0;
        if (z) {
            measuredHeight = this.c;
            if (this.q && this.l.getTabContainer() != null) {
                measuredHeight += this.c;
            }
        } else {
            measuredHeight = this.l.getVisibility() != 8 ? this.l.getMeasuredHeight() : 0;
        }
        Rect rect = this.v;
        Rect rect2 = this.x;
        rect2.set(rect);
        fx2 fx2Var = this.y;
        this.A = fx2Var;
        if (this.f8p || z) {
            w41 w41VarA = w41.a(fx2Var.b(), this.A.d() + measuredHeight, this.A.c(), this.A.a());
            hk0 hk0Var = new hk0(this.A);
            ((ww2) hk0Var.e).d(w41VarA);
            this.A = ((ww2) hk0Var.e).b();
        } else {
            rect2.top += measuredHeight;
            rect2.bottom = rect2.bottom;
            this.A = fx2Var.a.l(0, measuredHeight, 0, 0);
        }
        g(this.k, rect2, true);
        if (!this.B.equals(this.A)) {
            fx2 fx2Var2 = this.A;
            this.B = fx2Var2;
            ContentFrameLayout contentFrameLayout = this.k;
            WindowInsets windowInsetsF = fx2Var2.f();
            if (windowInsetsF != null) {
                WindowInsets windowInsetsA = mt2.a(contentFrameLayout, windowInsetsF);
                if (!windowInsetsA.equals(windowInsetsF)) {
                    fx2.g(windowInsetsA, contentFrameLayout);
                }
            }
        }
        measureChildWithMargins(this.k, i, 0, i2, 0);
        x3 x3Var2 = (x3) this.k.getLayoutParams();
        int iMax3 = Math.max(iMax, this.k.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) x3Var2).leftMargin + ((ViewGroup.MarginLayoutParams) x3Var2).rightMargin);
        int iMax4 = Math.max(iMax2, this.k.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) x3Var2).topMargin + ((ViewGroup.MarginLayoutParams) x3Var2).bottomMargin);
        int iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.k.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(getPaddingRight() + getPaddingLeft() + iMax3, getSuggestedMinimumWidth()), i, iCombineMeasuredStates2), View.resolveSizeAndState(Math.max(getPaddingBottom() + getPaddingTop() + iMax4, getSuggestedMinimumHeight()), i2, iCombineMeasuredStates2 << 16));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f, float f2, boolean z) {
        if (!this.r || !z) {
            return false;
        }
        this.D.fling(0, 0, 0, (int) f2, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (this.D.getFinalY() > this.l.getHeight()) {
            h();
            this.H.run();
        } else {
            h();
            this.G.run();
        }
        this.s = true;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f, float f2) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        int i5 = this.t + i2;
        this.t = i5;
        setActionBarHideOffset(i5);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScrollAccepted(View view, View view2, int i) {
        ow2 ow2Var;
        vu2 vu2Var;
        this.I.b = i;
        this.t = getActionBarHideOffset();
        h();
        w3 w3Var = this.C;
        if (w3Var == null || (vu2Var = (ow2Var = (ow2) w3Var).A) == null) {
            return;
        }
        vu2Var.a();
        ow2Var.A = null;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onStartNestedScroll(View view, View view2, int i) {
        if ((i & 2) == 0 || this.l.getVisibility() != 0) {
            return false;
        }
        return this.r;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onStopNestedScroll(View view) {
        if (!this.r || this.s) {
            return;
        }
        if (this.t <= this.l.getHeight()) {
            h();
            postDelayed(this.G, 600L);
        } else {
            h();
            postDelayed(this.H, 600L);
        }
    }

    @Override // android.view.View
    public final void onWindowSystemUiVisibilityChanged(int i) {
        super.onWindowSystemUiVisibilityChanged(i);
        k();
        int i2 = this.u ^ i;
        this.u = i;
        boolean z = (i & 4) == 0;
        boolean z2 = (i & 256) != 0;
        w3 w3Var = this.C;
        if (w3Var != null) {
            ((ow2) w3Var).w = !z2;
            if (z || !z2) {
                ow2 ow2Var = (ow2) w3Var;
                if (ow2Var.x) {
                    ow2Var.x = false;
                    ow2Var.N0(true);
                }
            } else {
                ow2 ow2Var2 = (ow2) w3Var;
                if (!ow2Var2.x) {
                    ow2Var2.x = true;
                    ow2Var2.N0(true);
                }
            }
        }
        if ((i2 & 256) == 0 || this.C == null) {
            return;
        }
        WeakHashMap weakHashMap = zt2.a;
        mt2.c(this);
    }

    @Override // android.view.View
    public final void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.e = i;
        w3 w3Var = this.C;
        if (w3Var != null) {
            ((ow2) w3Var).v = i;
        }
    }

    public void setActionBarHideOffset(int i) {
        h();
        this.l.setTranslationY(-Math.max(0, Math.min(i, this.l.getHeight())));
    }

    public void setActionBarVisibilityCallback(w3 w3Var) {
        this.C = w3Var;
        if (getWindowToken() != null) {
            ((ow2) this.C).v = this.e;
            int i = this.u;
            if (i != 0) {
                onWindowSystemUiVisibilityChanged(i);
                WeakHashMap weakHashMap = zt2.a;
                mt2.c(this);
            }
        }
    }

    public void setHasNonEmbeddedTabs(boolean z) {
        this.q = z;
    }

    public void setHideOnContentScrollEnabled(boolean z) {
        if (z != this.r) {
            this.r = z;
            if (z) {
                return;
            }
            h();
            setActionBarHideOffset(0);
        }
    }

    public void setIcon(int i) {
        k();
        hm2 hm2Var = (hm2) this.m;
        hm2Var.d = i != 0 ? l13.f(hm2Var.a.getContext(), i) : null;
        hm2Var.c();
    }

    public void setLogo(int i) {
        k();
        hm2 hm2Var = (hm2) this.m;
        hm2Var.e = i != 0 ? l13.f(hm2Var.a.getContext(), i) : null;
        hm2Var.c();
    }

    public void setOverlayMode(boolean z) {
        this.f8p = z;
        this.o = z && getContext().getApplicationInfo().targetSdkVersion < 19;
    }

    public void setShowingForActionMode(boolean z) {
    }

    public void setUiOptions(int i) {
    }

    @Override // p.i60
    public void setWindowCallback(Window.Callback callback) {
        k();
        ((hm2) this.m).k = callback;
    }

    @Override // p.i60
    public void setWindowTitle(CharSequence charSequence) {
        k();
        hm2 hm2Var = (hm2) this.m;
        if (hm2Var.g) {
            return;
        }
        hm2Var.h = charSequence;
        if ((hm2Var.b & 8) != 0) {
            Toolbar toolbar = hm2Var.a;
            toolbar.setTitle(charSequence);
            if (hm2Var.g) {
                zt2.l(toolbar.getRootView(), charSequence);
            }
        }
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new x3(layoutParams);
    }

    public void setIcon(Drawable drawable) {
        k();
        hm2 hm2Var = (hm2) this.m;
        hm2Var.d = drawable;
        hm2Var.c();
    }
}
