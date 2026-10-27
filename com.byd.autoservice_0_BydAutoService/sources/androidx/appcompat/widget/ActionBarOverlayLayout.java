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
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import l0.b0;
import l0.l;
import l0.m;
import l0.n;
import l0.t;
import n.m;
import o.k0;
import o.o;
import o.p;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"UnknownNullness"})
public class ActionBarOverlayLayout extends ViewGroup implements o, n, l, m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f343b = {R$attr.actionBarSize, R.attr.windowContentOverlay};
    public d A;
    public OverScroller B;
    public ViewPropertyAnimator C;
    public final AnimatorListenerAdapter D;
    public final Runnable E;
    public final Runnable F;
    public final l0.o G;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f344c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f345d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ContentFrameLayout f346e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ActionBarContainer f347f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public p f348g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Drawable f349h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f350i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f351j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f352k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f353l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f354m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f355n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f356o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Rect f357p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Rect f358q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Rect f359r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final Rect f360s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final Rect f361t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final Rect f362u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final Rect f363v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public b0 f364w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public b0 f365x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public b0 f366y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public b0 f367z;

    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.C = null;
            actionBarOverlayLayout.f354m = false;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.C = null;
            actionBarOverlayLayout.f354m = false;
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionBarOverlayLayout.this.u();
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.C = actionBarOverlayLayout.f347f.animate().translationY(0.0f).setListener(ActionBarOverlayLayout.this.D);
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionBarOverlayLayout.this.u();
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.C = actionBarOverlayLayout.f347f.animate().translationY(-ActionBarOverlayLayout.this.f347f.getHeight()).setListener(ActionBarOverlayLayout.this.D);
        }
    }

    public interface d {
        void a(boolean z3);

        void b();

        void c();

        void d(int i3);

        void e();

        void f();
    }

    public static class e extends ViewGroup.MarginLayoutParams {
        public e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public e(int i3, int i4) {
            super(i3, i4);
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
        this.E.run();
    }

    public final boolean B(float f3) {
        this.B.fling(0, 0, 0, (int) f3, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return this.B.getFinalY() > this.f347f.getHeight();
    }

    @Override // o.o
    public void a(Menu menu, m.a aVar) {
        z();
        this.f348g.a(menu, aVar);
    }

    @Override // o.o
    public boolean b() {
        z();
        return this.f348g.b();
    }

    @Override // o.o
    public boolean c() {
        z();
        return this.f348g.c();
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof e;
    }

    @Override // o.o
    public boolean d() {
        z();
        return this.f348g.d();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.f349h == null || this.f350i) {
            return;
        }
        int bottom = this.f347f.getVisibility() == 0 ? (int) (this.f347f.getBottom() + this.f347f.getTranslationY() + 0.5f) : 0;
        this.f349h.setBounds(0, bottom, getWidth(), this.f349h.getIntrinsicHeight() + bottom);
        this.f349h.draw(canvas);
    }

    @Override // o.o
    public boolean e() {
        z();
        return this.f348g.e();
    }

    @Override // o.o
    public void f() {
        z();
        this.f348g.f();
    }

    @Override // android.view.View
    public boolean fitSystemWindows(Rect rect) {
        if (Build.VERSION.SDK_INT >= 21) {
            return super.fitSystemWindows(rect);
        }
        z();
        boolean zQ = q(this.f347f, rect, true, true, false, true);
        this.f360s.set(rect);
        k0.a(this, this.f360s, this.f357p);
        if (!this.f361t.equals(this.f360s)) {
            this.f361t.set(this.f360s);
            zQ = true;
        }
        if (!this.f358q.equals(this.f357p)) {
            this.f358q.set(this.f357p);
            zQ = true;
        }
        if (zQ) {
            requestLayout();
        }
        return true;
    }

    @Override // o.o
    public boolean g() {
        z();
        return this.f348g.g();
    }

    public int getActionBarHideOffset() {
        ActionBarContainer actionBarContainer = this.f347f;
        if (actionBarContainer != null) {
            return -((int) actionBarContainer.getTranslationY());
        }
        return 0;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.G.a();
    }

    public CharSequence getTitle() {
        z();
        return this.f348g.getTitle();
    }

    @Override // l0.l
    public void h(View view, View view2, int i3, int i4) {
        if (i4 == 0) {
            onNestedScrollAccepted(view, view2, i3);
        }
    }

    @Override // l0.l
    public void i(View view, int i3) {
        if (i3 == 0) {
            onStopNestedScroll(view);
        }
    }

    @Override // l0.l
    public void j(View view, int i3, int i4, int[] iArr, int i5) {
        if (i5 == 0) {
            onNestedPreScroll(view, i3, i4, iArr);
        }
    }

    @Override // o.o
    public void k(int i3) {
        z();
        if (i3 == 2) {
            this.f348g.t();
        } else if (i3 == 5) {
            this.f348g.v();
        } else {
            if (i3 != 109) {
                return;
            }
            setOverlayMode(true);
        }
    }

    @Override // o.o
    public void l() {
        z();
        this.f348g.h();
    }

    @Override // l0.m
    public void m(View view, int i3, int i4, int i5, int i6, int i7, int[] iArr) {
        n(view, i3, i4, i5, i6, i7);
    }

    @Override // l0.l
    public void n(View view, int i3, int i4, int i5, int i6, int i7) {
        if (i7 == 0) {
            onNestedScroll(view, i3, i4, i5, i6);
        }
    }

    @Override // l0.l
    public boolean o(View view, View view2, int i3, int i4) {
        return i4 == 0 && onStartNestedScroll(view, view2, i3);
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        z();
        b0 b0VarP = b0.p(windowInsets);
        boolean zQ = q(this.f347f, new Rect(b0VarP.f(), b0VarP.h(), b0VarP.g(), b0VarP.e()), true, true, false, true);
        t.f(this, b0VarP, this.f357p);
        Rect rect = this.f357p;
        b0 b0VarK = b0VarP.k(rect.left, rect.top, rect.right, rect.bottom);
        this.f364w = b0VarK;
        boolean z3 = true;
        if (!this.f365x.equals(b0VarK)) {
            this.f365x = this.f364w;
            zQ = true;
        }
        if (this.f358q.equals(this.f357p)) {
            z3 = zQ;
        } else {
            this.f358q.set(this.f357p);
        }
        if (z3) {
            requestLayout();
        }
        return b0VarP.a().c().b().o();
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        v(getContext());
        t.g0(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        u();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z3, int i3, int i4, int i5, int i6) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i8 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin + paddingLeft;
                int i9 = ((ViewGroup.MarginLayoutParams) eVar).topMargin + paddingTop;
                childAt.layout(i8, i9, measuredWidth + i8, measuredHeight + i9);
            }
        }
    }

    @Override // android.view.View
    public void onMeasure(int i3, int i4) {
        int measuredHeight;
        z();
        measureChildWithMargins(this.f347f, i3, 0, i4, 0);
        e eVar = (e) this.f347f.getLayoutParams();
        int iMax = Math.max(0, this.f347f.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) eVar).leftMargin + ((ViewGroup.MarginLayoutParams) eVar).rightMargin);
        int iMax2 = Math.max(0, this.f347f.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar).topMargin + ((ViewGroup.MarginLayoutParams) eVar).bottomMargin);
        int iCombineMeasuredStates = View.combineMeasuredStates(0, this.f347f.getMeasuredState());
        boolean z3 = (t.I(this) & 256) != 0;
        if (z3) {
            measuredHeight = this.f344c;
            if (this.f352k && this.f347f.getTabContainer() != null) {
                measuredHeight += this.f344c;
            }
        } else {
            measuredHeight = this.f347f.getVisibility() != 8 ? this.f347f.getMeasuredHeight() : 0;
        }
        this.f359r.set(this.f357p);
        int i5 = Build.VERSION.SDK_INT;
        if (i5 >= 21) {
            this.f366y = this.f364w;
        } else {
            this.f362u.set(this.f360s);
        }
        if (!this.f351j && !z3) {
            Rect rect = this.f359r;
            rect.top += measuredHeight;
            rect.bottom += 0;
            if (i5 >= 21) {
                this.f366y = this.f366y.k(0, measuredHeight, 0, 0);
            }
        } else if (i5 >= 21) {
            this.f366y = new b0.a(this.f366y).c(d0.b.a(this.f366y.f(), this.f366y.h() + measuredHeight, this.f366y.g(), this.f366y.e() + 0)).a();
        } else {
            Rect rect2 = this.f362u;
            rect2.top += measuredHeight;
            rect2.bottom += 0;
        }
        q(this.f346e, this.f359r, true, true, true, true);
        if (i5 >= 21 && !this.f367z.equals(this.f366y)) {
            b0 b0Var = this.f366y;
            this.f367z = b0Var;
            t.g(this.f346e, b0Var);
        } else if (i5 < 21 && !this.f363v.equals(this.f362u)) {
            this.f363v.set(this.f362u);
            this.f346e.a(this.f362u);
        }
        measureChildWithMargins(this.f346e, i3, 0, i4, 0);
        e eVar2 = (e) this.f346e.getLayoutParams();
        int iMax3 = Math.max(iMax, this.f346e.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) eVar2).leftMargin + ((ViewGroup.MarginLayoutParams) eVar2).rightMargin);
        int iMax4 = Math.max(iMax2, this.f346e.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar2).topMargin + ((ViewGroup.MarginLayoutParams) eVar2).bottomMargin);
        int iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.f346e.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(iMax3 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i3, iCombineMeasuredStates2), View.resolveSizeAndState(Math.max(iMax4 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i4, iCombineMeasuredStates2 << 16));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, l0.n
    public boolean onNestedFling(View view, float f3, float f4, boolean z3) {
        if (!this.f353l || !z3) {
            return false;
        }
        if (B(f4)) {
            p();
        } else {
            A();
        }
        this.f354m = true;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, l0.n
    public boolean onNestedPreFling(View view, float f3, float f4) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, l0.n
    public void onNestedPreScroll(View view, int i3, int i4, int[] iArr) {
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, l0.n
    public void onNestedScroll(View view, int i3, int i4, int i5, int i6) {
        int i7 = this.f355n + i4;
        this.f355n = i7;
        setActionBarHideOffset(i7);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, l0.n
    public void onNestedScrollAccepted(View view, View view2, int i3) {
        this.G.b(view, view2, i3);
        this.f355n = getActionBarHideOffset();
        u();
        d dVar = this.A;
        if (dVar != null) {
            dVar.c();
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, l0.n
    public boolean onStartNestedScroll(View view, View view2, int i3) {
        if ((i3 & 2) == 0 || this.f347f.getVisibility() != 0) {
            return false;
        }
        return this.f353l;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, l0.n
    public void onStopNestedScroll(View view) {
        if (this.f353l && !this.f354m) {
            if (this.f355n <= this.f347f.getHeight()) {
                y();
            } else {
                x();
            }
        }
        d dVar = this.A;
        if (dVar != null) {
            dVar.f();
        }
    }

    @Override // android.view.View
    public void onWindowSystemUiVisibilityChanged(int i3) {
        if (Build.VERSION.SDK_INT >= 16) {
            super.onWindowSystemUiVisibilityChanged(i3);
        }
        z();
        int i4 = this.f356o ^ i3;
        this.f356o = i3;
        boolean z3 = (i3 & 4) == 0;
        boolean z4 = (i3 & 256) != 0;
        d dVar = this.A;
        if (dVar != null) {
            dVar.a(!z4);
            if (z3 || !z4) {
                this.A.b();
            } else {
                this.A.e();
            }
        }
        if ((i4 & 256) == 0 || this.A == null) {
            return;
        }
        t.g0(this);
    }

    @Override // android.view.View
    public void onWindowVisibilityChanged(int i3) {
        super.onWindowVisibilityChanged(i3);
        this.f345d = i3;
        d dVar = this.A;
        if (dVar != null) {
            dVar.d(i3);
        }
    }

    public final void p() {
        u();
        this.F.run();
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

    public void setActionBarHideOffset(int i3) {
        u();
        this.f347f.setTranslationY(-Math.max(0, Math.min(i3, this.f347f.getHeight())));
    }

    public void setActionBarVisibilityCallback(d dVar) {
        this.A = dVar;
        if (getWindowToken() != null) {
            this.A.d(this.f345d);
            int i3 = this.f356o;
            if (i3 != 0) {
                onWindowSystemUiVisibilityChanged(i3);
                t.g0(this);
            }
        }
    }

    public void setHasNonEmbeddedTabs(boolean z3) {
        this.f352k = z3;
    }

    public void setHideOnContentScrollEnabled(boolean z3) {
        if (z3 != this.f353l) {
            this.f353l = z3;
            if (z3) {
                return;
            }
            u();
            setActionBarHideOffset(0);
        }
    }

    public void setIcon(int i3) {
        z();
        this.f348g.setIcon(i3);
    }

    public void setLogo(int i3) {
        z();
        this.f348g.m(i3);
    }

    public void setOverlayMode(boolean z3) {
        this.f351j = z3;
        this.f350i = z3 && getContext().getApplicationInfo().targetSdkVersion < 19;
    }

    public void setShowingForActionMode(boolean z3) {
    }

    public void setUiOptions(int i3) {
    }

    @Override // o.o
    public void setWindowCallback(Window.Callback callback) {
        z();
        this.f348g.setWindowCallback(callback);
    }

    @Override // o.o
    public void setWindowTitle(CharSequence charSequence) {
        z();
        this.f348g.setWindowTitle(charSequence);
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
        removeCallbacks(this.E);
        removeCallbacks(this.F);
        ViewPropertyAnimator viewPropertyAnimator = this.C;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    public final void v(Context context) {
        TypedArray typedArrayObtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(f343b);
        this.f344c = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(1);
        this.f349h = drawable;
        setWillNotDraw(drawable == null);
        typedArrayObtainStyledAttributes.recycle();
        this.f350i = context.getApplicationInfo().targetSdkVersion < 19;
        this.B = new OverScroller(context);
    }

    public boolean w() {
        return this.f351j;
    }

    public final void x() {
        u();
        postDelayed(this.F, 600L);
    }

    public final void y() {
        u();
        postDelayed(this.E, 600L);
    }

    public void z() {
        if (this.f346e == null) {
            this.f346e = (ContentFrameLayout) findViewById(R$id.action_bar_activity_content);
            this.f347f = (ActionBarContainer) findViewById(R$id.action_bar_container);
            this.f348g = t(findViewById(R$id.action_bar));
        }
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f345d = 0;
        this.f357p = new Rect();
        this.f358q = new Rect();
        this.f359r = new Rect();
        this.f360s = new Rect();
        this.f361t = new Rect();
        this.f362u = new Rect();
        this.f363v = new Rect();
        b0 b0Var = b0.f3616a;
        this.f364w = b0Var;
        this.f365x = b0Var;
        this.f366y = b0Var;
        this.f367z = b0Var;
        this.D = new a();
        this.E = new b();
        this.F = new c();
        v(context);
        this.G = new l0.o(this);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new e(layoutParams);
    }

    public void setIcon(Drawable drawable) {
        z();
        this.f348g.setIcon(drawable);
    }
}
