package androidx.appcompat.widget;

import a.b.f;
import a.b.k.v;
import a.b.o.g;
import a.b.o.i.m;
import a.b.p.c0;
import a.b.p.d0;
import a.h.k.h;
import a.h.k.i;
import a.h.k.j;
import a.h.k.q;
import a.h.k.w;
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
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.OverScroller;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"UnknownNullness"})
public class ActionBarOverlayLayout extends ViewGroup implements c0, h, i {
    public static final int[] F = {a.b.a.actionBarSize, R.attr.windowContentOverlay};
    public ViewPropertyAnimator A;
    public final AnimatorListenerAdapter B;
    public final Runnable C;
    public final Runnable D;
    public final j E;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1419a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1420b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ContentFrameLayout f1421c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ActionBarContainer f1422d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public d0 f1423e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Drawable f1424f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public boolean k;
    public int l;
    public int m;
    public final Rect n;
    public final Rect o;
    public final Rect p;
    public final Rect q;
    public final Rect r;
    public final Rect s;
    public final Rect t;
    public w u;
    public w v;
    public w w;
    public w x;
    public d y;
    public OverScroller z;

    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.A = null;
            actionBarOverlayLayout.k = false;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.A = null;
            actionBarOverlayLayout.k = false;
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionBarOverlayLayout.this.q();
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.A = actionBarOverlayLayout.f1422d.animate().translationY(0.0f).setListener(ActionBarOverlayLayout.this.B);
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionBarOverlayLayout.this.q();
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.A = actionBarOverlayLayout.f1422d.animate().translationY(-ActionBarOverlayLayout.this.f1422d.getHeight()).setListener(ActionBarOverlayLayout.this.B);
        }
    }

    public interface d {
    }

    public static class e extends ViewGroup.MarginLayoutParams {
        public e(int i, int i2) {
            super(i, i2);
        }

        public e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1420b = 0;
        this.n = new Rect();
        this.o = new Rect();
        this.p = new Rect();
        this.q = new Rect();
        this.r = new Rect();
        this.s = new Rect();
        this.t = new Rect();
        w wVar = w.f843b;
        this.u = wVar;
        this.v = wVar;
        this.w = wVar;
        this.x = wVar;
        this.B = new a();
        this.C = new b();
        this.D = new c();
        r(context);
        this.E = new j();
    }

    @Override // a.b.p.c0
    public boolean a() {
        s();
        return this.f1423e.a();
    }

    @Override // a.b.p.c0
    public void b(Menu menu, m.a aVar) {
        s();
        this.f1423e.b(menu, aVar);
    }

    @Override // a.b.p.c0
    public boolean c() {
        s();
        return this.f1423e.c();
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof e;
    }

    @Override // a.b.p.c0
    public void d() {
        s();
        this.f1423e.d();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int translationY;
        super.draw(canvas);
        if (this.f1424f == null || this.g) {
            return;
        }
        if (this.f1422d.getVisibility() == 0) {
            translationY = (int) (this.f1422d.getTranslationY() + this.f1422d.getBottom() + 0.5f);
        } else {
            translationY = 0;
        }
        this.f1424f.setBounds(0, translationY, getWidth(), this.f1424f.getIntrinsicHeight() + translationY);
        this.f1424f.draw(canvas);
    }

    @Override // a.b.p.c0
    public boolean e() {
        s();
        return this.f1423e.e();
    }

    @Override // a.b.p.c0
    public boolean f() {
        s();
        return this.f1423e.f();
    }

    @Override // android.view.View
    public boolean fitSystemWindows(Rect rect) {
        return super.fitSystemWindows(rect);
    }

    @Override // a.b.p.c0
    public boolean g() {
        s();
        return this.f1423e.g();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new e(-1, -1);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new e(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new e(layoutParams);
    }

    public int getActionBarHideOffset() {
        ActionBarContainer actionBarContainer = this.f1422d;
        if (actionBarContainer != null) {
            return -((int) actionBarContainer.getTranslationY());
        }
        return 0;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.E.a();
    }

    public CharSequence getTitle() {
        s();
        return this.f1423e.getTitle();
    }

    @Override // a.b.p.c0
    public void h(int i) {
        s();
        if (i == 2) {
            this.f1423e.l();
        } else if (i == 5) {
            this.f1423e.r();
        } else {
            if (i != 109) {
                return;
            }
            setOverlayMode(true);
        }
    }

    @Override // a.h.k.h
    public void i(View view, View view2, int i, int i2) {
        if (i2 == 0) {
            onNestedScrollAccepted(view, view2, i);
        }
    }

    @Override // a.b.p.c0
    public void j() {
        s();
        this.f1423e.h();
    }

    @Override // a.h.k.i
    public void k(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        if (i5 == 0) {
            onNestedScroll(view, i, i2, i3, i4);
        }
    }

    @Override // a.h.k.h
    public void l(View view, int i, int i2, int i3, int i4, int i5) {
        if (i5 == 0) {
            onNestedScroll(view, i, i2, i3, i4);
        }
    }

    @Override // a.h.k.h
    public void m(View view, int i) {
        if (i == 0) {
            onStopNestedScroll(view);
        }
    }

    @Override // a.h.k.h
    public void n(View view, int i, int i2, int[] iArr, int i3) {
        if (i3 == 0) {
            onNestedPreScroll(view, i, i2, iArr);
        }
    }

    @Override // a.h.k.h
    public boolean o(View view, View view2, int i, int i2) {
        return i2 == 0 && onStartNestedScroll(view, view2, i);
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        s();
        w wVarG = w.g(windowInsets);
        boolean zP = p(this.f1422d, new Rect(wVarG.b(), wVarG.d(), wVarG.c(), wVarG.a()), true, true, false, true);
        q.c(this, wVarG, this.n);
        Rect rect = this.n;
        w wVarI = wVarG.f844a.i(rect.left, rect.top, rect.right, rect.bottom);
        this.u = wVarI;
        boolean z = true;
        if (!this.v.equals(wVarI)) {
            this.v = this.u;
            zP = true;
        }
        if (this.o.equals(this.n)) {
            z = zP;
        } else {
            this.o.set(this.n);
        }
        if (z) {
            requestLayout();
        }
        return wVarG.f844a.a().f844a.c().f844a.b().f();
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        r(getContext());
        q.S(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        q();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i6 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin + paddingLeft;
                int i7 = ((ViewGroup.MarginLayoutParams) eVar).topMargin + paddingTop;
                childAt.layout(i6, i7, measuredWidth + i6, measuredHeight + i7);
            }
        }
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int measuredHeight;
        w wVarA;
        s();
        measureChildWithMargins(this.f1422d, i, 0, i2, 0);
        e eVar = (e) this.f1422d.getLayoutParams();
        int iMax = Math.max(0, this.f1422d.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) eVar).leftMargin + ((ViewGroup.MarginLayoutParams) eVar).rightMargin);
        int iMax2 = Math.max(0, this.f1422d.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar).topMargin + ((ViewGroup.MarginLayoutParams) eVar).bottomMargin);
        int iCombineMeasuredStates = View.combineMeasuredStates(0, this.f1422d.getMeasuredState());
        boolean z = (q.w(this) & 256) != 0;
        if (z) {
            measuredHeight = this.f1419a;
            if (this.i && this.f1422d.getTabContainer() != null) {
                measuredHeight += this.f1419a;
            }
        } else {
            measuredHeight = this.f1422d.getVisibility() != 8 ? this.f1422d.getMeasuredHeight() : 0;
        }
        this.p.set(this.n);
        w wVar = this.u;
        this.w = wVar;
        if (this.h || z) {
            a.h.e.b bVarA = a.h.e.b.a(this.w.b(), this.w.d() + measuredHeight, this.w.c(), this.w.a() + 0);
            w wVar2 = this.w;
            w.a bVar = Build.VERSION.SDK_INT >= 30 ? new w.b(wVar2) : new w.a(wVar2);
            bVar.f845b.setSystemWindowInsets(bVarA.b());
            wVarA = bVar.a();
        } else {
            Rect rect = this.p;
            rect.top += measuredHeight;
            rect.bottom += 0;
            wVarA = wVar.f844a.i(0, measuredHeight, 0, 0);
        }
        this.w = wVarA;
        p(this.f1421c, this.p, true, true, true, true);
        if (!this.x.equals(this.w)) {
            w wVar3 = this.w;
            this.x = wVar3;
            ContentFrameLayout contentFrameLayout = this.f1421c;
            WindowInsets windowInsetsF = wVar3.f();
            if (windowInsetsF != null) {
                WindowInsets windowInsetsDispatchApplyWindowInsets = contentFrameLayout.dispatchApplyWindowInsets(windowInsetsF);
                if (!windowInsetsDispatchApplyWindowInsets.equals(windowInsetsF)) {
                    w.h(windowInsetsDispatchApplyWindowInsets, contentFrameLayout);
                }
            }
        }
        measureChildWithMargins(this.f1421c, i, 0, i2, 0);
        e eVar2 = (e) this.f1421c.getLayoutParams();
        int iMax3 = Math.max(iMax, this.f1421c.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) eVar2).leftMargin + ((ViewGroup.MarginLayoutParams) eVar2).rightMargin);
        int iMax4 = Math.max(iMax2, this.f1421c.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar2).topMargin + ((ViewGroup.MarginLayoutParams) eVar2).bottomMargin);
        int iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.f1421c.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(getPaddingRight() + getPaddingLeft() + iMax3, getSuggestedMinimumWidth()), i, iCombineMeasuredStates2), View.resolveSizeAndState(Math.max(getPaddingBottom() + getPaddingTop() + iMax4, getSuggestedMinimumHeight()), i2, iCombineMeasuredStates2 << 16));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View view, float f2, float f3, boolean z) {
        if (!this.j || !z) {
            return false;
        }
        this.z.fling(0, 0, 0, (int) f3, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (this.z.getFinalY() > this.f1422d.getHeight()) {
            q();
            this.D.run();
        } else {
            q();
            this.C.run();
        }
        this.k = true;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View view, float f2, float f3) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        int i5 = this.l + i2;
        this.l = i5;
        setActionBarHideOffset(i5);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScrollAccepted(View view, View view2, int i) {
        v vVar;
        g gVar;
        this.E.f819a = i;
        this.l = getActionBarHideOffset();
        q();
        d dVar = this.y;
        if (dVar == null || (gVar = (vVar = (v) dVar).u) == null) {
            return;
        }
        gVar.a();
        vVar.u = null;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onStartNestedScroll(View view, View view2, int i) {
        if ((i & 2) == 0 || this.f1422d.getVisibility() != 0) {
            return false;
        }
        return this.j;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View view) {
        if (this.j && !this.k) {
            if (this.l <= this.f1422d.getHeight()) {
                q();
                postDelayed(this.C, 600L);
            } else {
                q();
                postDelayed(this.D, 600L);
            }
        }
        d dVar = this.y;
        if (dVar != null && ((v) dVar) == null) {
            throw null;
        }
    }

    @Override // android.view.View
    public void onWindowSystemUiVisibilityChanged(int i) {
        super.onWindowSystemUiVisibilityChanged(i);
        s();
        int i2 = this.m ^ i;
        this.m = i;
        boolean z = (i & 4) == 0;
        boolean z2 = (i & 256) != 0;
        d dVar = this.y;
        if (dVar != null) {
            ((v) dVar).p = !z2;
            if (z || !z2) {
                v vVar = (v) this.y;
                if (vVar.r) {
                    vVar.r = false;
                    vVar.n(true);
                }
            } else {
                v vVar2 = (v) dVar;
                if (!vVar2.r) {
                    vVar2.r = true;
                    vVar2.n(true);
                }
            }
        }
        if ((i2 & 256) == 0 || this.y == null) {
            return;
        }
        q.S(this);
    }

    @Override // android.view.View
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.f1420b = i;
        d dVar = this.y;
        if (dVar != null) {
            ((v) dVar).o = i;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean p(android.view.View r2, android.graphics.Rect r3, boolean r4, boolean r5, boolean r6, boolean r7) {
        /*
            r1 = this;
            android.view.ViewGroup$LayoutParams r1 = r2.getLayoutParams()
            androidx.appcompat.widget.ActionBarOverlayLayout$e r1 = (androidx.appcompat.widget.ActionBarOverlayLayout.e) r1
            r2 = 1
            if (r4 == 0) goto L13
            int r4 = r1.leftMargin
            int r0 = r3.left
            if (r4 == r0) goto L13
            r1.leftMargin = r0
            r4 = r2
            goto L14
        L13:
            r4 = 0
        L14:
            if (r5 == 0) goto L1f
            int r5 = r1.topMargin
            int r0 = r3.top
            if (r5 == r0) goto L1f
            r1.topMargin = r0
            r4 = r2
        L1f:
            if (r7 == 0) goto L2a
            int r5 = r1.rightMargin
            int r7 = r3.right
            if (r5 == r7) goto L2a
            r1.rightMargin = r7
            r4 = r2
        L2a:
            if (r6 == 0) goto L35
            int r5 = r1.bottomMargin
            int r3 = r3.bottom
            if (r5 == r3) goto L35
            r1.bottomMargin = r3
            goto L36
        L35:
            r2 = r4
        L36:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActionBarOverlayLayout.p(android.view.View, android.graphics.Rect, boolean, boolean, boolean, boolean):boolean");
    }

    public void q() {
        removeCallbacks(this.C);
        removeCallbacks(this.D);
        ViewPropertyAnimator viewPropertyAnimator = this.A;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    public final void r(Context context) {
        TypedArray typedArrayObtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(F);
        this.f1419a = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(1);
        this.f1424f = drawable;
        setWillNotDraw(drawable == null);
        typedArrayObtainStyledAttributes.recycle();
        this.g = context.getApplicationInfo().targetSdkVersion < 19;
        this.z = new OverScroller(context);
    }

    public void s() {
        d0 wrapper;
        if (this.f1421c == null) {
            this.f1421c = (ContentFrameLayout) findViewById(f.action_bar_activity_content);
            this.f1422d = (ActionBarContainer) findViewById(f.action_bar_container);
            KeyEvent.Callback callbackFindViewById = findViewById(f.action_bar);
            if (callbackFindViewById instanceof d0) {
                wrapper = (d0) callbackFindViewById;
            } else {
                if (!(callbackFindViewById instanceof Toolbar)) {
                    StringBuilder sbD = b.a.a.a.a.d("Can't make a decor toolbar out of ");
                    sbD.append(callbackFindViewById.getClass().getSimpleName());
                    throw new IllegalStateException(sbD.toString());
                }
                wrapper = ((Toolbar) callbackFindViewById).getWrapper();
            }
            this.f1423e = wrapper;
        }
    }

    public void setActionBarHideOffset(int i) {
        q();
        this.f1422d.setTranslationY(-Math.max(0, Math.min(i, this.f1422d.getHeight())));
    }

    public void setActionBarVisibilityCallback(d dVar) {
        this.y = dVar;
        if (getWindowToken() != null) {
            ((v) this.y).o = this.f1420b;
            int i = this.m;
            if (i != 0) {
                onWindowSystemUiVisibilityChanged(i);
                q.S(this);
            }
        }
    }

    public void setHasNonEmbeddedTabs(boolean z) {
        this.i = z;
    }

    public void setHideOnContentScrollEnabled(boolean z) {
        if (z != this.j) {
            this.j = z;
            if (z) {
                return;
            }
            q();
            setActionBarHideOffset(0);
        }
    }

    public void setIcon(int i) {
        s();
        this.f1423e.setIcon(i);
    }

    public void setIcon(Drawable drawable) {
        s();
        this.f1423e.setIcon(drawable);
    }

    public void setLogo(int i) {
        s();
        this.f1423e.n(i);
    }

    public void setOverlayMode(boolean z) {
        this.h = z;
        this.g = z && getContext().getApplicationInfo().targetSdkVersion < 19;
    }

    public void setShowingForActionMode(boolean z) {
    }

    public void setUiOptions(int i) {
    }

    @Override // a.b.p.c0
    public void setWindowCallback(Window.Callback callback) {
        s();
        this.f1423e.setWindowCallback(callback);
    }

    @Override // a.b.p.c0
    public void setWindowTitle(CharSequence charSequence) {
        s();
        this.f1423e.setWindowTitle(charSequence);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }
}
