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
import android.os.Build;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.OverScroller;
import b.b.f;
import b.b.p.j.m;
import b.b.q.k0;
import b.b.q.o;
import b.b.q.p;
import b.f.m.a0;
import b.f.m.l;
import b.f.m.m;
import b.f.m.n;
import b.f.m.s;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"UnknownNullness"})
public class ActionBarOverlayLayout extends ViewGroup implements o, n, l, m {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f159c = {b.b.a.f705b, R.attr.windowContentOverlay};
    public p E3;
    public Drawable F3;
    public boolean G3;
    public boolean H3;
    public boolean I3;
    public boolean J3;
    public boolean K3;
    public int L3;
    public int M3;
    public final Rect N3;
    public final Rect O3;
    public final Rect P3;
    public final Rect Q3;
    public final Rect R3;
    public final Rect S3;
    public final Rect T3;
    public a0 U3;
    public a0 V3;
    public a0 W3;
    public a0 X3;
    public d Y3;
    public OverScroller Z3;
    public ViewPropertyAnimator a4;
    public final AnimatorListenerAdapter b4;
    public final Runnable c4;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f160d;
    public final Runnable d4;
    public final b.f.m.o e4;
    public int s;
    public ContentFrameLayout x;
    public ActionBarContainer y;

    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.a4 = null;
            actionBarOverlayLayout.K3 = false;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.a4 = null;
            actionBarOverlayLayout.K3 = false;
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionBarOverlayLayout.this.u();
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.a4 = actionBarOverlayLayout.y.animate().translationY(0.0f).setListener(ActionBarOverlayLayout.this.b4);
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionBarOverlayLayout.this.u();
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.a4 = actionBarOverlayLayout.y.animate().translationY(-ActionBarOverlayLayout.this.y.getHeight()).setListener(ActionBarOverlayLayout.this.b4);
        }
    }

    public interface d {
        void a(boolean z);

        void b();

        void c();

        void d(int i2);

        void e();

        void f();
    }

    public static class e extends ViewGroup.MarginLayoutParams {
        public e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public e(int i2, int i3) {
            super(i2, i3);
        }

        public e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public ActionBarOverlayLayout(Context context) {
        this(context, null);
    }

    public final void A() {
        u();
        this.c4.run();
    }

    public final boolean B(float f2) {
        this.Z3.fling(0, 0, 0, (int) f2, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return this.Z3.getFinalY() > this.y.getHeight();
    }

    @Override // b.b.q.o
    public void a(Menu menu, m.a aVar) {
        z();
        this.E3.a(menu, aVar);
    }

    @Override // b.b.q.o
    public boolean b() {
        z();
        return this.E3.b();
    }

    @Override // b.b.q.o
    public boolean c() {
        z();
        return this.E3.c();
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof e;
    }

    @Override // b.b.q.o
    public boolean d() {
        z();
        return this.E3.d();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.F3 == null || this.G3) {
            return;
        }
        int bottom = this.y.getVisibility() == 0 ? (int) (this.y.getBottom() + this.y.getTranslationY() + 0.5f) : 0;
        this.F3.setBounds(0, bottom, getWidth(), this.F3.getIntrinsicHeight() + bottom);
        this.F3.draw(canvas);
    }

    @Override // b.b.q.o
    public boolean e() {
        z();
        return this.E3.e();
    }

    @Override // b.b.q.o
    public void f() {
        z();
        this.E3.f();
    }

    @Override // android.view.View
    public boolean fitSystemWindows(Rect rect) {
        if (Build.VERSION.SDK_INT >= 21) {
            return super.fitSystemWindows(rect);
        }
        z();
        boolean zQ = q(this.y, rect, true, true, false, true);
        this.Q3.set(rect);
        k0.a(this, this.Q3, this.N3);
        if (!this.R3.equals(this.Q3)) {
            this.R3.set(this.Q3);
            zQ = true;
        }
        if (!this.O3.equals(this.N3)) {
            this.O3.set(this.N3);
            zQ = true;
        }
        if (zQ) {
            requestLayout();
        }
        return true;
    }

    @Override // b.b.q.o
    public boolean g() {
        z();
        return this.E3.g();
    }

    public int getActionBarHideOffset() {
        ActionBarContainer actionBarContainer = this.y;
        if (actionBarContainer != null) {
            return -((int) actionBarContainer.getTranslationY());
        }
        return 0;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.e4.a();
    }

    public CharSequence getTitle() {
        z();
        return this.E3.getTitle();
    }

    @Override // b.f.m.l
    public void h(View view, View view2, int i2, int i3) {
        if (i3 == 0) {
            onNestedScrollAccepted(view, view2, i2);
        }
    }

    @Override // b.f.m.l
    public void i(View view, int i2) {
        if (i2 == 0) {
            onStopNestedScroll(view);
        }
    }

    @Override // b.f.m.l
    public void j(View view, int i2, int i3, int[] iArr, int i4) {
        if (i4 == 0) {
            onNestedPreScroll(view, i2, i3, iArr);
        }
    }

    @Override // b.b.q.o
    public void k(int i2) {
        z();
        if (i2 == 2) {
            this.E3.r();
        } else if (i2 == 5) {
            this.E3.t();
        } else {
            if (i2 != 109) {
                return;
            }
            setOverlayMode(true);
        }
    }

    @Override // b.b.q.o
    public void l() {
        z();
        this.E3.h();
    }

    @Override // b.f.m.m
    public void m(View view, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
        n(view, i2, i3, i4, i5, i6);
    }

    @Override // b.f.m.l
    public void n(View view, int i2, int i3, int i4, int i5, int i6) {
        if (i6 == 0) {
            onNestedScroll(view, i2, i3, i4, i5);
        }
    }

    @Override // b.f.m.l
    public boolean o(View view, View view2, int i2, int i3) {
        return i3 == 0 && onStartNestedScroll(view, view2, i2);
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        z();
        a0 a0VarN = a0.n(windowInsets);
        boolean zQ = q(this.y, new Rect(a0VarN.e(), a0VarN.g(), a0VarN.f(), a0VarN.d()), true, true, false, true);
        s.e(this, a0VarN, this.N3);
        Rect rect = this.N3;
        a0 a0VarI = a0VarN.i(rect.left, rect.top, rect.right, rect.bottom);
        this.U3 = a0VarI;
        boolean z = true;
        if (!this.V3.equals(a0VarI)) {
            this.V3 = this.U3;
            zQ = true;
        }
        if (this.O3.equals(this.N3)) {
            z = zQ;
        } else {
            this.O3.set(this.N3);
        }
        if (z) {
            requestLayout();
        }
        return a0VarN.a().c().b().m();
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        v(getContext());
        s.Q(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        u();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i7 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin + paddingLeft;
                int i8 = ((ViewGroup.MarginLayoutParams) eVar).topMargin + paddingTop;
                childAt.layout(i7, i8, measuredWidth + i7, measuredHeight + i8);
            }
        }
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        int measuredHeight;
        z();
        measureChildWithMargins(this.y, i2, 0, i3, 0);
        e eVar = (e) this.y.getLayoutParams();
        int iMax = Math.max(0, this.y.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) eVar).leftMargin + ((ViewGroup.MarginLayoutParams) eVar).rightMargin);
        int iMax2 = Math.max(0, this.y.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar).topMargin + ((ViewGroup.MarginLayoutParams) eVar).bottomMargin);
        int iCombineMeasuredStates = View.combineMeasuredStates(0, this.y.getMeasuredState());
        boolean z = (s.B(this) & 256) != 0;
        if (z) {
            measuredHeight = this.f160d;
            if (this.I3 && this.y.getTabContainer() != null) {
                measuredHeight += this.f160d;
            }
        } else {
            measuredHeight = this.y.getVisibility() != 8 ? this.y.getMeasuredHeight() : 0;
        }
        this.P3.set(this.N3);
        int i4 = Build.VERSION.SDK_INT;
        if (i4 >= 21) {
            this.W3 = this.U3;
        } else {
            this.S3.set(this.Q3);
        }
        if (!this.H3 && !z) {
            Rect rect = this.P3;
            rect.top += measuredHeight;
            rect.bottom += 0;
            if (i4 >= 21) {
                this.W3 = this.W3.i(0, measuredHeight, 0, 0);
            }
        } else if (i4 >= 21) {
            this.W3 = new a0.a(this.W3).c(b.f.g.b.a(this.W3.e(), this.W3.g() + measuredHeight, this.W3.f(), this.W3.d() + 0)).a();
        } else {
            Rect rect2 = this.S3;
            rect2.top += measuredHeight;
            rect2.bottom += 0;
        }
        q(this.x, this.P3, true, true, true, true);
        if (i4 >= 21 && !this.X3.equals(this.W3)) {
            a0 a0Var = this.W3;
            this.X3 = a0Var;
            s.f(this.x, a0Var);
        } else if (i4 < 21 && !this.T3.equals(this.S3)) {
            this.T3.set(this.S3);
            this.x.a(this.S3);
        }
        measureChildWithMargins(this.x, i2, 0, i3, 0);
        e eVar2 = (e) this.x.getLayoutParams();
        int iMax3 = Math.max(iMax, this.x.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) eVar2).leftMargin + ((ViewGroup.MarginLayoutParams) eVar2).rightMargin);
        int iMax4 = Math.max(iMax2, this.x.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar2).topMargin + ((ViewGroup.MarginLayoutParams) eVar2).bottomMargin);
        int iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.x.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(iMax3 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i2, iCombineMeasuredStates2), View.resolveSizeAndState(Math.max(iMax4 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i3, iCombineMeasuredStates2 << 16));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.f.m.n
    public boolean onNestedFling(View view, float f2, float f3, boolean z) {
        if (!this.J3 || !z) {
            return false;
        }
        if (B(f3)) {
            p();
        } else {
            A();
        }
        this.K3 = true;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.f.m.n
    public boolean onNestedPreFling(View view, float f2, float f3) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.f.m.n
    public void onNestedPreScroll(View view, int i2, int i3, int[] iArr) {
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.f.m.n
    public void onNestedScroll(View view, int i2, int i3, int i4, int i5) {
        int i6 = this.L3 + i3;
        this.L3 = i6;
        setActionBarHideOffset(i6);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.f.m.n
    public void onNestedScrollAccepted(View view, View view2, int i2) {
        this.e4.b(view, view2, i2);
        this.L3 = getActionBarHideOffset();
        u();
        d dVar = this.Y3;
        if (dVar != null) {
            dVar.c();
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.f.m.n
    public boolean onStartNestedScroll(View view, View view2, int i2) {
        if ((i2 & 2) == 0 || this.y.getVisibility() != 0) {
            return false;
        }
        return this.J3;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.f.m.n
    public void onStopNestedScroll(View view) {
        if (this.J3 && !this.K3) {
            if (this.L3 <= this.y.getHeight()) {
                y();
            } else {
                x();
            }
        }
        d dVar = this.Y3;
        if (dVar != null) {
            dVar.f();
        }
    }

    @Override // android.view.View
    public void onWindowSystemUiVisibilityChanged(int i2) {
        if (Build.VERSION.SDK_INT >= 16) {
            super.onWindowSystemUiVisibilityChanged(i2);
        }
        z();
        int i3 = this.M3 ^ i2;
        this.M3 = i2;
        boolean z = (i2 & 4) == 0;
        boolean z2 = (i2 & 256) != 0;
        d dVar = this.Y3;
        if (dVar != null) {
            dVar.a(!z2);
            if (z || !z2) {
                this.Y3.b();
            } else {
                this.Y3.e();
            }
        }
        if ((i3 & 256) == 0 || this.Y3 == null) {
            return;
        }
        s.Q(this);
    }

    @Override // android.view.View
    public void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        this.s = i2;
        d dVar = this.Y3;
        if (dVar != null) {
            dVar.d(i2);
        }
    }

    public final void p() {
        u();
        this.d4.run();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean q(android.view.View r3, android.graphics.Rect r4, boolean r5, boolean r6, boolean r7, boolean r8) {
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

    public void setActionBarHideOffset(int i2) {
        u();
        this.y.setTranslationY(-Math.max(0, Math.min(i2, this.y.getHeight())));
    }

    public void setActionBarVisibilityCallback(d dVar) {
        this.Y3 = dVar;
        if (getWindowToken() != null) {
            this.Y3.d(this.s);
            int i2 = this.M3;
            if (i2 != 0) {
                onWindowSystemUiVisibilityChanged(i2);
                s.Q(this);
            }
        }
    }

    public void setHasNonEmbeddedTabs(boolean z) {
        this.I3 = z;
    }

    public void setHideOnContentScrollEnabled(boolean z) {
        if (z != this.J3) {
            this.J3 = z;
            if (z) {
                return;
            }
            u();
            setActionBarHideOffset(0);
        }
    }

    public void setIcon(int i2) {
        z();
        this.E3.setIcon(i2);
    }

    public void setLogo(int i2) {
        z();
        this.E3.k(i2);
    }

    public void setOverlayMode(boolean z) {
        this.H3 = z;
        this.G3 = z && getContext().getApplicationInfo().targetSdkVersion < 19;
    }

    public void setShowingForActionMode(boolean z) {
    }

    public void setUiOptions(int i2) {
    }

    @Override // b.b.q.o
    public void setWindowCallback(Window.Callback callback) {
        z();
        this.E3.setWindowCallback(callback);
    }

    @Override // b.b.q.o
    public void setWindowTitle(CharSequence charSequence) {
        z();
        this.E3.setWindowTitle(charSequence);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final p t(View view) {
        if (view instanceof p) {
            return (p) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        throw new IllegalStateException("Can't make a decor toolbar out of " + view.getClass().getSimpleName());
    }

    public void u() {
        removeCallbacks(this.c4);
        removeCallbacks(this.d4);
        ViewPropertyAnimator viewPropertyAnimator = this.a4;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    public final void v(Context context) {
        TypedArray typedArrayObtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(f159c);
        this.f160d = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(1);
        this.F3 = drawable;
        setWillNotDraw(drawable == null);
        typedArrayObtainStyledAttributes.recycle();
        this.G3 = context.getApplicationInfo().targetSdkVersion < 19;
        this.Z3 = new OverScroller(context);
    }

    public boolean w() {
        return this.H3;
    }

    public final void x() {
        u();
        postDelayed(this.d4, 600L);
    }

    public final void y() {
        u();
        postDelayed(this.c4, 600L);
    }

    public void z() {
        if (this.x == null) {
            this.x = (ContentFrameLayout) findViewById(f.f741b);
            this.y = (ActionBarContainer) findViewById(f.f742c);
            this.E3 = t(findViewById(f.f740a));
        }
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.s = 0;
        this.N3 = new Rect();
        this.O3 = new Rect();
        this.P3 = new Rect();
        this.Q3 = new Rect();
        this.R3 = new Rect();
        this.S3 = new Rect();
        this.T3 = new Rect();
        a0 a0Var = a0.f1735a;
        this.U3 = a0Var;
        this.V3 = a0Var;
        this.W3 = a0Var;
        this.X3 = a0Var;
        this.b4 = new a();
        this.c4 = new b();
        this.d4 = new c();
        v(context);
        this.e4 = new b.f.m.o(this);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new e(layoutParams);
    }

    public void setIcon(Drawable drawable) {
        z();
        this.E3.setIcon(drawable);
    }
}
