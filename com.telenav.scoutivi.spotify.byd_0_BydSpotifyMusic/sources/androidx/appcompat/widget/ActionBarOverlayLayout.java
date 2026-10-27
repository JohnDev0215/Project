package androidx.appcompat.widget;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.OverScroller;
import androidx.appcompat.view.menu.j;
import i0.c0;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"UnknownNullness"})
public class ActionBarOverlayLayout extends ViewGroup implements z, i0.m, i0.n {
    static final int[] K = {e.a.actionBarSize, R.attr.windowContentOverlay};
    private i0.c0 A;
    private i0.c0 B;
    private i0.c0 C;
    private d D;
    private OverScroller E;
    ViewPropertyAnimator F;
    final AnimatorListenerAdapter G;
    private final Runnable H;
    private final Runnable I;
    private final i0.o J;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f779e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f780g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private ContentFrameLayout f781h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    ActionBarContainer f782i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private a0 f783j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private Drawable f784k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private boolean f785l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private boolean f786m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private boolean f787n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private boolean f788o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    boolean f789p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private int f790q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private int f791r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private final Rect f792s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private final Rect f793t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private final Rect f794u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private final Rect f795v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private final Rect f796w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private final Rect f797x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private final Rect f798y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private i0.c0 f799z;

    class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.F = null;
            actionBarOverlayLayout.f789p = false;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.F = null;
            actionBarOverlayLayout.f789p = false;
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionBarOverlayLayout.this.u();
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.F = actionBarOverlayLayout.f782i.animate().translationY(0.0f).setListener(ActionBarOverlayLayout.this.G);
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionBarOverlayLayout.this.u();
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.F = actionBarOverlayLayout.f782i.animate().translationY(-ActionBarOverlayLayout.this.f782i.getHeight()).setListener(ActionBarOverlayLayout.this.G);
        }
    }

    public interface d {
        void a(boolean z6);

        void b();

        void c();

        void d(int i7);

        void e();

        void f();
    }

    public static class e extends ViewGroup.MarginLayoutParams {
        public e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public e(int i7, int i8) {
            super(i7, i8);
        }

        public e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f780g = 0;
        this.f792s = new Rect();
        this.f793t = new Rect();
        this.f794u = new Rect();
        this.f795v = new Rect();
        this.f796w = new Rect();
        this.f797x = new Rect();
        this.f798y = new Rect();
        i0.c0 c0Var = i0.c0.f6843b;
        this.f799z = c0Var;
        this.A = c0Var;
        this.B = c0Var;
        this.C = c0Var;
        this.G = new a();
        this.H = new b();
        this.I = new c();
        v(context);
        this.J = new i0.o(this);
    }

    private void A() {
        u();
        this.H.run();
    }

    private boolean B(float f7) {
        this.E.fling(0, 0, 0, (int) f7, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return this.E.getFinalY() > this.f782i.getHeight();
    }

    private void p() {
        u();
        this.I.run();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean q(android.view.View r3, android.graphics.Rect r4, boolean r5, boolean r6, boolean r7, boolean r8) {
        /*
            r2 = this;
            android.view.ViewGroup$LayoutParams r3 = r3.getLayoutParams()
            androidx.appcompat.widget.ActionBarOverlayLayout$e r3 = (androidx.appcompat.widget.ActionBarOverlayLayout.e) r3
            r0 = 1
            if (r5 == 0) goto L13
            int r5 = r3.leftMargin
            int r1 = r4.left
            if (r5 == r1) goto L13
            r3.leftMargin = r1
            r5 = r0
            goto L14
        L13:
            r5 = 0
        L14:
            if (r6 == 0) goto L1f
            int r6 = r3.topMargin
            int r1 = r4.top
            if (r6 == r1) goto L1f
            r3.topMargin = r1
            r5 = r0
        L1f:
            if (r8 == 0) goto L2a
            int r6 = r3.rightMargin
            int r8 = r4.right
            if (r6 == r8) goto L2a
            r3.rightMargin = r8
            r5 = r0
        L2a:
            if (r7 == 0) goto L35
            int r6 = r3.bottomMargin
            int r4 = r4.bottom
            if (r6 == r4) goto L35
            r3.bottomMargin = r4
            goto L36
        L35:
            r0 = r5
        L36:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActionBarOverlayLayout.q(android.view.View, android.graphics.Rect, boolean, boolean, boolean, boolean):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private a0 t(View view) {
        if (view instanceof a0) {
            return (a0) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        throw new IllegalStateException("Can't make a decor toolbar out of " + view.getClass().getSimpleName());
    }

    private void v(Context context) {
        TypedArray typedArrayObtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(K);
        this.f779e = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(1);
        this.f784k = drawable;
        setWillNotDraw(drawable == null);
        typedArrayObtainStyledAttributes.recycle();
        this.f785l = context.getApplicationInfo().targetSdkVersion < 19;
        this.E = new OverScroller(context);
    }

    private void x() {
        u();
        postDelayed(this.I, 600L);
    }

    private void y() {
        u();
        postDelayed(this.H, 600L);
    }

    @Override // androidx.appcompat.widget.z
    public void a(Menu menu, j.a aVar) {
        z();
        this.f783j.a(menu, aVar);
    }

    @Override // androidx.appcompat.widget.z
    public boolean b() {
        z();
        return this.f783j.b();
    }

    @Override // androidx.appcompat.widget.z
    public boolean c() {
        z();
        return this.f783j.c();
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof e;
    }

    @Override // androidx.appcompat.widget.z
    public boolean d() {
        z();
        return this.f783j.d();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.f784k == null || this.f785l) {
            return;
        }
        int bottom = this.f782i.getVisibility() == 0 ? (int) (this.f782i.getBottom() + this.f782i.getTranslationY() + 0.5f) : 0;
        this.f784k.setBounds(0, bottom, getWidth(), this.f784k.getIntrinsicHeight() + bottom);
        this.f784k.draw(canvas);
    }

    @Override // androidx.appcompat.widget.z
    public boolean e() {
        z();
        return this.f783j.e();
    }

    @Override // androidx.appcompat.widget.z
    public void f() {
        z();
        this.f783j.f();
    }

    @Override // android.view.View
    protected boolean fitSystemWindows(Rect rect) {
        return super.fitSystemWindows(rect);
    }

    @Override // androidx.appcompat.widget.z
    public boolean g() {
        z();
        return this.f783j.g();
    }

    public int getActionBarHideOffset() {
        ActionBarContainer actionBarContainer = this.f782i;
        if (actionBarContainer != null) {
            return -((int) actionBarContainer.getTranslationY());
        }
        return 0;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.J.a();
    }

    public CharSequence getTitle() {
        z();
        return this.f783j.getTitle();
    }

    @Override // i0.m
    public void h(View view, View view2, int i7, int i8) {
        if (i8 == 0) {
            onNestedScrollAccepted(view, view2, i7);
        }
    }

    @Override // i0.m
    public void i(View view, int i7) {
        if (i7 == 0) {
            onStopNestedScroll(view);
        }
    }

    @Override // i0.m
    public void j(View view, int i7, int i8, int[] iArr, int i9) {
        if (i9 == 0) {
            onNestedPreScroll(view, i7, i8, iArr);
        }
    }

    @Override // androidx.appcompat.widget.z
    public void k(int i7) {
        z();
        if (i7 == 2) {
            this.f783j.q();
        } else if (i7 == 5) {
            this.f783j.s();
        } else {
            if (i7 != 109) {
                return;
            }
            setOverlayMode(true);
        }
    }

    @Override // androidx.appcompat.widget.z
    public void l() {
        z();
        this.f783j.h();
    }

    @Override // i0.n
    public void m(View view, int i7, int i8, int i9, int i10, int i11, int[] iArr) {
        n(view, i7, i8, i9, i10, i11);
    }

    @Override // i0.m
    public void n(View view, int i7, int i8, int i9, int i10, int i11) {
        if (i11 == 0) {
            onNestedScroll(view, i7, i8, i9, i10);
        }
    }

    @Override // i0.m
    public boolean o(View view, View view2, int i7, int i8) {
        return i8 == 0 && onStartNestedScroll(view, view2, i7);
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        z();
        i0.c0 c0VarT = i0.c0.t(windowInsets, this);
        boolean zQ = q(this.f782i, new Rect(c0VarT.g(), c0VarT.i(), c0VarT.h(), c0VarT.f()), true, true, false, true);
        i0.u.e(this, c0VarT, this.f792s);
        Rect rect = this.f792s;
        i0.c0 c0VarJ = c0VarT.j(rect.left, rect.top, rect.right, rect.bottom);
        this.f799z = c0VarJ;
        boolean z6 = true;
        if (!this.A.equals(c0VarJ)) {
            this.A = this.f799z;
            zQ = true;
        }
        if (this.f793t.equals(this.f792s)) {
            z6 = zQ;
        } else {
            this.f793t.set(this.f792s);
        }
        if (z6) {
            requestLayout();
        }
        return c0VarT.a().c().b().r();
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        v(getContext());
        i0.u.h0(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        u();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i12 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin + paddingLeft;
                int i13 = ((ViewGroup.MarginLayoutParams) eVar).topMargin + paddingTop;
                childAt.layout(i12, i13, measuredWidth + i12, measuredHeight + i13);
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i7, int i8) {
        int measuredHeight;
        z();
        measureChildWithMargins(this.f782i, i7, 0, i8, 0);
        e eVar = (e) this.f782i.getLayoutParams();
        int iMax = Math.max(0, this.f782i.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) eVar).leftMargin + ((ViewGroup.MarginLayoutParams) eVar).rightMargin);
        int iMax2 = Math.max(0, this.f782i.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar).topMargin + ((ViewGroup.MarginLayoutParams) eVar).bottomMargin);
        int iCombineMeasuredStates = View.combineMeasuredStates(0, this.f782i.getMeasuredState());
        boolean z6 = (i0.u.I(this) & 256) != 0;
        if (z6) {
            measuredHeight = this.f779e;
            if (this.f787n && this.f782i.getTabContainer() != null) {
                measuredHeight += this.f779e;
            }
        } else {
            measuredHeight = this.f782i.getVisibility() != 8 ? this.f782i.getMeasuredHeight() : 0;
        }
        this.f794u.set(this.f792s);
        i0.c0 c0Var = this.f799z;
        this.B = c0Var;
        if (this.f786m || z6) {
            this.B = new c0.a(this.B).c(z.b.b(c0Var.g(), this.B.i() + measuredHeight, this.B.h(), this.B.f() + 0)).a();
        } else {
            Rect rect = this.f794u;
            rect.top += measuredHeight;
            rect.bottom += 0;
            this.B = c0Var.j(0, measuredHeight, 0, 0);
        }
        q(this.f781h, this.f794u, true, true, true, true);
        if (!this.C.equals(this.B)) {
            i0.c0 c0Var2 = this.B;
            this.C = c0Var2;
            i0.u.f(this.f781h, c0Var2);
        }
        measureChildWithMargins(this.f781h, i7, 0, i8, 0);
        e eVar2 = (e) this.f781h.getLayoutParams();
        int iMax3 = Math.max(iMax, this.f781h.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) eVar2).leftMargin + ((ViewGroup.MarginLayoutParams) eVar2).rightMargin);
        int iMax4 = Math.max(iMax2, this.f781h.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar2).topMargin + ((ViewGroup.MarginLayoutParams) eVar2).bottomMargin);
        int iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.f781h.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(iMax3 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i7, iCombineMeasuredStates2), View.resolveSizeAndState(Math.max(iMax4 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i8, iCombineMeasuredStates2 << 16));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View view, float f7, float f8, boolean z6) {
        if (!this.f788o || !z6) {
            return false;
        }
        if (B(f8)) {
            p();
        } else {
            A();
        }
        this.f789p = true;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View view, float f7, float f8) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedPreScroll(View view, int i7, int i8, int[] iArr) {
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View view, int i7, int i8, int i9, int i10) {
        int i11 = this.f790q + i8;
        this.f790q = i11;
        setActionBarHideOffset(i11);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScrollAccepted(View view, View view2, int i7) {
        this.J.b(view, view2, i7);
        this.f790q = getActionBarHideOffset();
        u();
        d dVar = this.D;
        if (dVar != null) {
            dVar.c();
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onStartNestedScroll(View view, View view2, int i7) {
        if ((i7 & 2) == 0 || this.f782i.getVisibility() != 0) {
            return false;
        }
        return this.f788o;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View view) {
        if (this.f788o && !this.f789p) {
            if (this.f790q <= this.f782i.getHeight()) {
                y();
            } else {
                x();
            }
        }
        d dVar = this.D;
        if (dVar != null) {
            dVar.f();
        }
    }

    @Override // android.view.View
    @Deprecated
    public void onWindowSystemUiVisibilityChanged(int i7) {
        super.onWindowSystemUiVisibilityChanged(i7);
        z();
        int i8 = this.f791r ^ i7;
        this.f791r = i7;
        boolean z6 = (i7 & 4) == 0;
        boolean z7 = (i7 & 256) != 0;
        d dVar = this.D;
        if (dVar != null) {
            dVar.a(!z7);
            if (z6 || !z7) {
                this.D.b();
            } else {
                this.D.e();
            }
        }
        if ((i8 & 256) == 0 || this.D == null) {
            return;
        }
        i0.u.h0(this);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i7) {
        super.onWindowVisibilityChanged(i7);
        this.f780g = i7;
        d dVar = this.D;
        if (dVar != null) {
            dVar.d(i7);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: r, reason: merged with bridge method [inline-methods] */
    public e generateDefaultLayoutParams() {
        return new e(-1, -1);
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: s, reason: merged with bridge method [inline-methods] */
    public e generateLayoutParams(AttributeSet attributeSet) {
        return new e(getContext(), attributeSet);
    }

    public void setActionBarHideOffset(int i7) {
        u();
        this.f782i.setTranslationY(-Math.max(0, Math.min(i7, this.f782i.getHeight())));
    }

    public void setActionBarVisibilityCallback(d dVar) {
        this.D = dVar;
        if (getWindowToken() != null) {
            this.D.d(this.f780g);
            int i7 = this.f791r;
            if (i7 != 0) {
                onWindowSystemUiVisibilityChanged(i7);
                i0.u.h0(this);
            }
        }
    }

    public void setHasNonEmbeddedTabs(boolean z6) {
        this.f787n = z6;
    }

    public void setHideOnContentScrollEnabled(boolean z6) {
        if (z6 != this.f788o) {
            this.f788o = z6;
            if (z6) {
                return;
            }
            u();
            setActionBarHideOffset(0);
        }
    }

    public void setIcon(int i7) {
        z();
        this.f783j.setIcon(i7);
    }

    public void setLogo(int i7) {
        z();
        this.f783j.k(i7);
    }

    public void setOverlayMode(boolean z6) {
        this.f786m = z6;
        this.f785l = z6 && getContext().getApplicationInfo().targetSdkVersion < 19;
    }

    public void setShowingForActionMode(boolean z6) {
    }

    public void setUiOptions(int i7) {
    }

    @Override // androidx.appcompat.widget.z
    public void setWindowCallback(Window.Callback callback) {
        z();
        this.f783j.setWindowCallback(callback);
    }

    @Override // androidx.appcompat.widget.z
    public void setWindowTitle(CharSequence charSequence) {
        z();
        this.f783j.setWindowTitle(charSequence);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    void u() {
        removeCallbacks(this.H);
        removeCallbacks(this.I);
        ViewPropertyAnimator viewPropertyAnimator = this.F;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    public boolean w() {
        return this.f786m;
    }

    void z() {
        if (this.f781h == null) {
            this.f781h = (ContentFrameLayout) findViewById(e.f.action_bar_activity_content);
            this.f782i = (ActionBarContainer) findViewById(e.f.action_bar_container);
            this.f783j = t(findViewById(e.f.action_bar));
        }
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new e(layoutParams);
    }

    public void setIcon(Drawable drawable) {
        z();
        this.f783j.setIcon(drawable);
    }
}
