package androidx.appcompat.widget;

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
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import androidx.appcompat.R;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPresenter;
import defpackage.in3;
import defpackage.n01;
import defpackage.om3;
import defpackage.pt3;
import defpackage.sl1;
import defpackage.tl1;
import defpackage.ul1;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"UnknownNullness"})
public class ActionBarOverlayLayout extends ViewGroup implements DecorContentParent, sl1, tl1 {
    public static final int[] F = {R.attr.actionBarSize, android.R.attr.windowContentOverlay};
    public ViewPropertyAnimator A;
    public final AnimatorListenerAdapter B;
    public final Runnable C;
    public final Runnable D;
    public final ul1 E;
    public int e;
    public int f;
    public ContentFrameLayout g;
    public ActionBarContainer h;
    public DecorToolbar i;
    public Drawable j;
    public boolean k;
    public boolean l;
    public boolean m;
    public boolean n;
    public boolean o;
    public int p;
    public int q;
    public final Rect r;
    public final Rect s;
    public final Rect t;
    public pt3 u;
    public pt3 v;
    public pt3 w;
    public pt3 x;
    public ActionBarVisibilityCallback y;
    public OverScroller z;

    /* JADX INFO: renamed from: androidx.appcompat.widget.ActionBarOverlayLayout$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        public AnonymousClass2() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.q();
            actionBarOverlayLayout.A = actionBarOverlayLayout.h.animate().translationY(0.0f).setListener(actionBarOverlayLayout.B);
        }
    }

    /* JADX INFO: renamed from: androidx.appcompat.widget.ActionBarOverlayLayout$3, reason: invalid class name */
    public class AnonymousClass3 implements Runnable {
        public AnonymousClass3() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.q();
            actionBarOverlayLayout.A = actionBarOverlayLayout.h.animate().translationY(-actionBarOverlayLayout.h.getHeight()).setListener(actionBarOverlayLayout.B);
        }
    }

    public interface ActionBarVisibilityCallback {
        void a();

        void b();

        void c(boolean z);

        void d();

        void e();

        void f(int i);
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams() {
            super(-1, -1);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public ActionBarOverlayLayout(Context context) {
        this(context, null);
    }

    public static boolean p(FrameLayout frameLayout, Rect rect, boolean z) {
        boolean z2;
        LayoutParams layoutParams = (LayoutParams) frameLayout.getLayoutParams();
        int i = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
        int i2 = rect.left;
        if (i != i2) {
            ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = i2;
            z2 = true;
        } else {
            z2 = false;
        }
        int i3 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
        int i4 = rect.top;
        if (i3 != i4) {
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = i4;
            z2 = true;
        }
        int i5 = ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
        int i6 = rect.right;
        if (i5 != i6) {
            ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin = i6;
            z2 = true;
        }
        if (z) {
            int i7 = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
            int i8 = rect.bottom;
            if (i7 != i8) {
                ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin = i8;
                return true;
            }
        }
        return z2;
    }

    @Override // androidx.appcompat.widget.DecorContentParent
    public final boolean a() {
        s();
        return this.i.a();
    }

    @Override // androidx.appcompat.widget.DecorContentParent
    public final void b() {
        s();
        this.i.b();
    }

    @Override // androidx.appcompat.widget.DecorContentParent
    public final boolean c() {
        s();
        return this.i.c();
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // androidx.appcompat.widget.DecorContentParent
    public final void d(MenuBuilder menuBuilder, MenuPresenter.Callback callback) {
        s();
        this.i.d(menuBuilder, callback);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        int translationY;
        super.draw(canvas);
        if (this.j == null || this.k) {
            return;
        }
        if (this.h.getVisibility() == 0) {
            translationY = (int) (this.h.getTranslationY() + this.h.getBottom() + 0.5f);
        } else {
            translationY = 0;
        }
        this.j.setBounds(0, translationY, getWidth(), this.j.getIntrinsicHeight() + translationY);
        this.j.draw(canvas);
    }

    @Override // androidx.appcompat.widget.DecorContentParent
    public final boolean e() {
        s();
        return this.i.e();
    }

    @Override // androidx.appcompat.widget.DecorContentParent
    public final boolean f() {
        s();
        return this.i.f();
    }

    @Override // android.view.View
    public final boolean fitSystemWindows(Rect rect) {
        return super.fitSystemWindows(rect);
    }

    @Override // androidx.appcompat.widget.DecorContentParent
    public final boolean g() {
        s();
        return this.i.g();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public int getActionBarHideOffset() {
        ActionBarContainer actionBarContainer = this.h;
        if (actionBarContainer != null) {
            return -((int) actionBarContainer.getTranslationY());
        }
        return 0;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        ul1 ul1Var = this.E;
        return ul1Var.b | ul1Var.a;
    }

    public CharSequence getTitle() {
        s();
        return this.i.getTitle();
    }

    @Override // androidx.appcompat.widget.DecorContentParent
    public final void h(int i) {
        s();
        if (i == 2) {
            this.i.r();
        } else if (i == 5) {
            this.i.s();
        } else {
            if (i != 109) {
                return;
            }
            setOverlayMode(true);
        }
    }

    @Override // androidx.appcompat.widget.DecorContentParent
    public final void i() {
        s();
        this.i.h();
    }

    @Override // defpackage.tl1
    public final void j(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        k(view, i, i2, i3, i4, i5);
    }

    @Override // defpackage.sl1
    public final void k(View view, int i, int i2, int i3, int i4, int i5) {
        if (i5 == 0) {
            onNestedScroll(view, i, i2, i3, i4);
        }
    }

    @Override // defpackage.sl1
    public final boolean l(View view, View view2, int i, int i2) {
        return i2 == 0 && onStartNestedScroll(view, view2, i);
    }

    @Override // defpackage.sl1
    public final void m(View view, View view2, int i, int i2) {
        if (i2 == 0) {
            onNestedScrollAccepted(view, view2, i);
        }
    }

    @Override // defpackage.sl1
    public final void n(View view, int i) {
        if (i == 0) {
            onStopNestedScroll(view);
        }
    }

    @Override // defpackage.sl1
    public final void o(View view, int i, int i2, int[] iArr, int i3) {
        if (i3 == 0) {
            onNestedPreScroll(view, i, i2, iArr);
        }
    }

    @Override // android.view.View
    public final WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        s();
        pt3 pt3VarI = pt3.i(this, windowInsets);
        boolean zP = p(this.h, new Rect(pt3VarI.c(), pt3VarI.e(), pt3VarI.d(), pt3VarI.b()), false);
        WeakHashMap<View, in3> weakHashMap = om3.a;
        Rect rect = this.r;
        om3.i.b(this, pt3VarI, rect);
        int i = rect.left;
        int i2 = rect.top;
        int i3 = rect.right;
        int i4 = rect.bottom;
        pt3.i iVar = pt3VarI.a;
        pt3 pt3VarL = iVar.l(i, i2, i3, i4);
        this.u = pt3VarL;
        boolean z = true;
        if (!this.v.equals(pt3VarL)) {
            this.v = this.u;
            zP = true;
        }
        Rect rect2 = this.s;
        if (rect2.equals(rect)) {
            z = zP;
        } else {
            rect2.set(rect);
        }
        if (z) {
            requestLayout();
        }
        return iVar.a().a.c().a.b().h();
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        r(getContext());
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.h.c(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        q();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i6 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + paddingLeft;
                int i7 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + paddingTop;
                childAt.layout(i6, i7, measuredWidth + i6, measuredHeight + i7);
            }
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int measuredHeight;
        s();
        measureChildWithMargins(this.h, i, 0, i2, 0);
        LayoutParams layoutParams = (LayoutParams) this.h.getLayoutParams();
        int iMax = Math.max(0, this.h.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin);
        int iMax2 = Math.max(0, this.h.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
        int iCombineMeasuredStates = View.combineMeasuredStates(0, this.h.getMeasuredState());
        WeakHashMap<View, in3> weakHashMap = om3.a;
        boolean z = (om3.d.f(this) & 256) != 0;
        if (z) {
            measuredHeight = this.e;
            if (this.m && this.h.getTabContainer() != null) {
                measuredHeight += this.e;
            }
        } else {
            measuredHeight = this.h.getVisibility() != 8 ? this.h.getMeasuredHeight() : 0;
        }
        Rect rect = this.r;
        Rect rect2 = this.t;
        rect2.set(rect);
        pt3 pt3Var = this.u;
        this.w = pt3Var;
        if (this.l || z) {
            n01 n01VarB = n01.b(pt3Var.c(), this.w.e() + measuredHeight, this.w.d(), this.w.b() + 0);
            pt3 pt3Var2 = this.w;
            pt3.c bVar = Build.VERSION.SDK_INT >= 30 ? new pt3.b(pt3Var2) : new pt3.a(pt3Var2);
            bVar.g(n01VarB);
            this.w = bVar.b();
        } else {
            rect2.top += measuredHeight;
            rect2.bottom += 0;
            this.w = pt3Var.a.l(0, measuredHeight, 0, 0);
        }
        p(this.g, rect2, true);
        if (!this.x.equals(this.w)) {
            pt3 pt3Var3 = this.w;
            this.x = pt3Var3;
            om3.c(this.g, pt3Var3);
        }
        measureChildWithMargins(this.g, i, 0, i2, 0);
        LayoutParams layoutParams2 = (LayoutParams) this.g.getLayoutParams();
        int iMax3 = Math.max(iMax, this.g.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams2).rightMargin);
        int iMax4 = Math.max(iMax2, this.g.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin);
        int iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.g.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(getPaddingRight() + getPaddingLeft() + iMax3, getSuggestedMinimumWidth()), i, iCombineMeasuredStates2), View.resolveSizeAndState(Math.max(getPaddingBottom() + getPaddingTop() + iMax4, getSuggestedMinimumHeight()), i2, iCombineMeasuredStates2 << 16));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f, float f2, boolean z) {
        if (!this.n || !z) {
            return false;
        }
        this.z.fling(0, 0, 0, (int) f2, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (this.z.getFinalY() > this.h.getHeight()) {
            q();
            ((AnonymousClass3) this.D).run();
        } else {
            q();
            ((AnonymousClass2) this.C).run();
        }
        this.o = true;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f, float f2) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        int i5 = this.p + i2;
        this.p = i5;
        setActionBarHideOffset(i5);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScrollAccepted(View view, View view2, int i) {
        this.E.a = i;
        this.p = getActionBarHideOffset();
        q();
        ActionBarVisibilityCallback actionBarVisibilityCallback = this.y;
        if (actionBarVisibilityCallback != null) {
            actionBarVisibilityCallback.e();
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onStartNestedScroll(View view, View view2, int i) {
        if ((i & 2) == 0 || this.h.getVisibility() != 0) {
            return false;
        }
        return this.n;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onStopNestedScroll(View view) {
        if (this.n && !this.o) {
            if (this.p <= this.h.getHeight()) {
                q();
                postDelayed(this.C, 600L);
            } else {
                q();
                postDelayed(this.D, 600L);
            }
        }
        ActionBarVisibilityCallback actionBarVisibilityCallback = this.y;
        if (actionBarVisibilityCallback != null) {
            actionBarVisibilityCallback.b();
        }
    }

    @Override // android.view.View
    @Deprecated
    public final void onWindowSystemUiVisibilityChanged(int i) {
        super.onWindowSystemUiVisibilityChanged(i);
        s();
        int i2 = this.q ^ i;
        this.q = i;
        boolean z = (i & 4) == 0;
        boolean z2 = (i & 256) != 0;
        ActionBarVisibilityCallback actionBarVisibilityCallback = this.y;
        if (actionBarVisibilityCallback != null) {
            actionBarVisibilityCallback.c(!z2);
            if (z || !z2) {
                this.y.a();
            } else {
                this.y.d();
            }
        }
        if ((i2 & 256) == 0 || this.y == null) {
            return;
        }
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.h.c(this);
    }

    @Override // android.view.View
    public final void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.f = i;
        ActionBarVisibilityCallback actionBarVisibilityCallback = this.y;
        if (actionBarVisibilityCallback != null) {
            actionBarVisibilityCallback.f(i);
        }
    }

    public final void q() {
        removeCallbacks(this.C);
        removeCallbacks(this.D);
        ViewPropertyAnimator viewPropertyAnimator = this.A;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    public final void r(Context context) {
        TypedArray typedArrayObtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(F);
        this.e = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(1);
        this.j = drawable;
        setWillNotDraw(drawable == null);
        typedArrayObtainStyledAttributes.recycle();
        this.k = context.getApplicationInfo().targetSdkVersion < 19;
        this.z = new OverScroller(context);
    }

    public final void s() {
        DecorToolbar wrapper;
        if (this.g == null) {
            this.g = (ContentFrameLayout) findViewById(R.id.action_bar_activity_content);
            this.h = (ActionBarContainer) findViewById(R.id.action_bar_container);
            KeyEvent.Callback callbackFindViewById = findViewById(R.id.action_bar);
            if (callbackFindViewById instanceof DecorToolbar) {
                wrapper = (DecorToolbar) callbackFindViewById;
            } else {
                if (!(callbackFindViewById instanceof Toolbar)) {
                    throw new IllegalStateException("Can't make a decor toolbar out of ".concat(callbackFindViewById.getClass().getSimpleName()));
                }
                wrapper = ((Toolbar) callbackFindViewById).getWrapper();
            }
            this.i = wrapper;
        }
    }

    public void setActionBarHideOffset(int i) {
        q();
        this.h.setTranslationY(-Math.max(0, Math.min(i, this.h.getHeight())));
    }

    public void setActionBarVisibilityCallback(ActionBarVisibilityCallback actionBarVisibilityCallback) {
        this.y = actionBarVisibilityCallback;
        if (getWindowToken() != null) {
            this.y.f(this.f);
            int i = this.q;
            if (i != 0) {
                onWindowSystemUiVisibilityChanged(i);
                WeakHashMap<View, in3> weakHashMap = om3.a;
                om3.h.c(this);
            }
        }
    }

    public void setHasNonEmbeddedTabs(boolean z) {
        this.m = z;
    }

    public void setHideOnContentScrollEnabled(boolean z) {
        if (z != this.n) {
            this.n = z;
            if (z) {
                return;
            }
            q();
            setActionBarHideOffset(0);
        }
    }

    public void setIcon(int i) {
        s();
        this.i.setIcon(i);
    }

    public void setLogo(int i) {
        s();
        this.i.m(i);
    }

    public void setOverlayMode(boolean z) {
        this.l = z;
        this.k = z && getContext().getApplicationInfo().targetSdkVersion < 19;
    }

    @Override // androidx.appcompat.widget.DecorContentParent
    public void setWindowCallback(Window.Callback callback) {
        s();
        this.i.setWindowCallback(callback);
    }

    @Override // androidx.appcompat.widget.DecorContentParent
    public void setWindowTitle(CharSequence charSequence) {
        s();
        this.i.setWindowTitle(charSequence);
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = 0;
        this.r = new Rect();
        this.s = new Rect();
        this.t = new Rect();
        new Rect();
        new Rect();
        new Rect();
        new Rect();
        pt3 pt3Var = pt3.b;
        this.u = pt3Var;
        this.v = pt3Var;
        this.w = pt3Var;
        this.x = pt3Var;
        this.B = new AnimatorListenerAdapter() { // from class: androidx.appcompat.widget.ActionBarOverlayLayout.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
                actionBarOverlayLayout.A = null;
                actionBarOverlayLayout.o = false;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
                actionBarOverlayLayout.A = null;
                actionBarOverlayLayout.o = false;
            }
        };
        this.C = new AnonymousClass2();
        this.D = new AnonymousClass3();
        r(context);
        this.E = new ul1();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public void setIcon(Drawable drawable) {
        s();
        this.i.setIcon(drawable);
    }

    public void setShowingForActionMode(boolean z) {
    }

    public void setUiOptions(int i) {
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
    }
}
