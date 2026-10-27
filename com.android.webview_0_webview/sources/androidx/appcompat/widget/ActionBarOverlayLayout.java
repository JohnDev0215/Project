package androidx.appcompat.widget;

import android.R;
import android.animation.AnimatorListenerAdapter;
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
import android.widget.OverScroller;
import defpackage.AbstractC0245Nm;
import defpackage.AbstractC0371Um;
import defpackage.AbstractC1251o3;
import defpackage.C0973j3;
import defpackage.InterfaceC1193n0;
import defpackage.O;
import defpackage.P;
import defpackage.Q;
import defpackage.Q0;
import defpackage.S;
import defpackage.X0;
import defpackage.Z7;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ActionBarOverlayLayout extends ViewGroup {
    public static final int[] N = {AbstractC0245Nm.d, R.attr.windowContentOverlay};
    public final Rect A;
    public final Rect B;
    public final Rect C;
    public final Rect D;
    public final Rect E;
    public final Rect F;
    public final Rect G;
    public OverScroller H;
    public ViewPropertyAnimator I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final AnimatorListenerAdapter f13J;
    public final Runnable K;
    public final Runnable L;
    public final C0973j3 M;
    public int s;
    public ContentFrameLayout t;
    public ActionBarContainer u;
    public Drawable v;
    public boolean w;
    public boolean x;
    public int y;
    public int z;

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.A = new Rect();
        this.B = new Rect();
        this.C = new Rect();
        this.D = new Rect();
        this.E = new Rect();
        this.F = new Rect();
        this.G = new Rect();
        this.f13J = new O(this);
        this.K = new P(this);
        this.L = new Q(this);
        c(context);
        this.M = new C0973j3(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean a(android.view.View r3, android.graphics.Rect r4, boolean r5, boolean r6, boolean r7, boolean r8) {
        /*
            r2 = this;
            android.view.ViewGroup$LayoutParams r3 = r3.getLayoutParams()
            S r3 = (defpackage.S) r3
            r0 = 1
            if (r5 == 0) goto L13
            int r5 = r3.leftMargin
            int r1 = r4.left
            if (r5 == r1) goto L13
            r3.leftMargin = r1
            r5 = 1
            goto L14
        L13:
            r5 = 0
        L14:
            if (r6 == 0) goto L1f
            int r6 = r3.topMargin
            int r1 = r4.top
            if (r6 == r1) goto L1f
            r3.topMargin = r1
            r5 = 1
        L1f:
            if (r8 == 0) goto L2a
            int r6 = r3.rightMargin
            int r8 = r4.right
            if (r6 == r8) goto L2a
            r3.rightMargin = r8
            r5 = 1
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActionBarOverlayLayout.a(android.view.View, android.graphics.Rect, boolean, boolean, boolean, boolean):boolean");
    }

    public void b() {
        removeCallbacks(this.K);
        removeCallbacks(this.L);
        ViewPropertyAnimator viewPropertyAnimator = this.I;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    public final void c(Context context) {
        TypedArray typedArrayObtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(N);
        this.s = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(1);
        this.v = drawable;
        setWillNotDraw(drawable == null);
        typedArrayObtainStyledAttributes.recycle();
        this.w = context.getApplicationInfo().targetSdkVersion < 19;
        this.H = new OverScroller(context);
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof S;
    }

    public void d() {
        if (this.t == null) {
            this.t = (ContentFrameLayout) findViewById(AbstractC0371Um.i);
            this.u = (ActionBarContainer) findViewById(AbstractC0371Um.k);
            KeyEvent.Callback callbackFindViewById = findViewById(AbstractC0371Um.h);
            if (callbackFindViewById instanceof InterfaceC1193n0) {
                return;
            }
            if (!(callbackFindViewById instanceof Toolbar)) {
                StringBuilder sbF = Z7.f("Can't make a decor toolbar out of ");
                sbF.append(callbackFindViewById.getClass().getSimpleName());
                throw new IllegalStateException(sbF.toString());
            }
            Toolbar toolbar = (Toolbar) callbackFindViewById;
            if (toolbar.S == null) {
                toolbar.S = new Q0(toolbar, true);
            }
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int translationY;
        super.draw(canvas);
        if (this.v == null || this.w) {
            return;
        }
        if (this.u.getVisibility() == 0) {
            translationY = (int) (this.u.getTranslationY() + this.u.getBottom() + 0.5f);
        } else {
            translationY = 0;
        }
        this.v.setBounds(0, translationY, getWidth(), this.v.getIntrinsicHeight() + translationY);
        this.v.draw(canvas);
    }

    @Override // android.view.View
    public boolean fitSystemWindows(Rect rect) {
        d();
        int iQ = AbstractC1251o3.q(this) & 256;
        boolean zA = a(this.u, rect, true, true, false, true);
        this.D.set(rect);
        Rect rect2 = this.D;
        Rect rect3 = this.A;
        Method method = X0.a;
        if (method != null) {
            try {
                method.invoke(this, rect2, rect3);
            } catch (Exception unused) {
            }
        }
        if (!this.E.equals(this.D)) {
            this.E.set(this.D);
            zA = true;
        }
        if (!this.B.equals(this.A)) {
            this.B.set(this.A);
            zA = true;
        }
        if (zA) {
            requestLayout();
        }
        return true;
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new S(-1, -1);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new S(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.M.a;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        c(getContext());
        AbstractC1251o3.A(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        getPaddingRight();
        int paddingTop = getPaddingTop();
        getPaddingBottom();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                S s = (S) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i6 = ((ViewGroup.MarginLayoutParams) s).leftMargin + paddingLeft;
                int i7 = ((ViewGroup.MarginLayoutParams) s).topMargin + paddingTop;
                childAt.layout(i6, i7, measuredWidth + i6, measuredHeight + i7);
            }
        }
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        d();
        measureChildWithMargins(this.u, i, 0, i2, 0);
        S s = (S) this.u.getLayoutParams();
        int iMax = Math.max(0, this.u.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) s).leftMargin + ((ViewGroup.MarginLayoutParams) s).rightMargin);
        int iMax2 = Math.max(0, this.u.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) s).topMargin + ((ViewGroup.MarginLayoutParams) s).bottomMargin);
        int iCombineMeasuredStates = View.combineMeasuredStates(0, this.u.getMeasuredState());
        boolean z = (AbstractC1251o3.q(this) & 256) != 0;
        int measuredHeight = z ? this.s : this.u.getVisibility() != 8 ? this.u.getMeasuredHeight() : 0;
        this.C.set(this.A);
        this.F.set(this.D);
        if (z) {
            Rect rect = this.F;
            rect.top += measuredHeight;
            rect.bottom += 0;
        } else {
            Rect rect2 = this.C;
            rect2.top += measuredHeight;
            rect2.bottom += 0;
        }
        a(this.t, this.C, true, true, true, true);
        if (!this.G.equals(this.F)) {
            this.G.set(this.F);
            this.t.a(this.F);
        }
        measureChildWithMargins(this.t, i, 0, i2, 0);
        S s2 = (S) this.t.getLayoutParams();
        int iMax3 = Math.max(iMax, this.t.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) s2).leftMargin + ((ViewGroup.MarginLayoutParams) s2).rightMargin);
        int iMax4 = Math.max(iMax2, this.t.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) s2).topMargin + ((ViewGroup.MarginLayoutParams) s2).bottomMargin);
        int iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.t.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(getPaddingRight() + getPaddingLeft() + iMax3, getSuggestedMinimumWidth()), i, iCombineMeasuredStates2), View.resolveSizeAndState(Math.max(getPaddingBottom() + getPaddingTop() + iMax4, getSuggestedMinimumHeight()), i2, iCombineMeasuredStates2 << 16));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View view, float f, float f2) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        this.y = this.y + i2;
        b();
        this.u.setTranslationY(-Math.max(0, Math.min(r1, this.u.getHeight())));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScrollAccepted(View view, View view2, int i) {
        this.M.a = i;
        ActionBarContainer actionBarContainer = this.u;
        this.y = actionBarContainer != null ? -((int) actionBarContainer.getTranslationY()) : 0;
        b();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onStartNestedScroll(View view, View view2, int i) {
        if ((i & 2) == 0 || this.u.getVisibility() != 0) {
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View view) {
    }

    @Override // android.view.View
    public void onWindowSystemUiVisibilityChanged(int i) {
        super.onWindowSystemUiVisibilityChanged(i);
        d();
        int i2 = this.z ^ i;
        this.z = i;
        int i3 = i & 4;
        int i4 = i & 256;
        int i5 = i2 & 256;
    }

    @Override // android.view.View
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new S(layoutParams);
    }
}
