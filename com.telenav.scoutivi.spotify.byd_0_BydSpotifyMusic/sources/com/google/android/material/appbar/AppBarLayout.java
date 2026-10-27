package com.google.android.material.appbar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.customview.view.AbsSavedState;
import e3.g;
import e3.h;
import i0.c0;
import i0.u;
import j0.c;
import j0.f;
import java.lang.ref.WeakReference;
import java.util.List;
import o2.k;
import o2.l;

/* JADX INFO: loaded from: classes.dex */
public class AppBarLayout extends LinearLayout implements CoordinatorLayout.b {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private static final int f4018w = k.Widget_Design_AppBarLayout;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f4019e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f4020g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f4021h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f4022i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private boolean f4023j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f4024k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private c0 f4025l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private List<b> f4026m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private boolean f4027n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private boolean f4028o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private boolean f4029p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private boolean f4030q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private int f4031r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private WeakReference<View> f4032s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private ValueAnimator f4033t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private int[] f4034u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private Drawable f4035v;

    public static class Behavior extends BaseBehavior<AppBarLayout> {
        public Behavior() {
        }

        @Override // com.google.android.material.appbar.c
        public /* bridge */ /* synthetic */ int E() {
            return super.E();
        }

        @Override // com.google.android.material.appbar.c
        public /* bridge */ /* synthetic */ boolean G(int i7) {
            return super.G(i7);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* JADX INFO: renamed from: g0 */
        public /* bridge */ /* synthetic */ boolean l(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i7) {
            return super.l(coordinatorLayout, appBarLayout, i7);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* JADX INFO: renamed from: h0 */
        public /* bridge */ /* synthetic */ boolean m(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i7, int i8, int i9, int i10) {
            return super.m(coordinatorLayout, appBarLayout, i7, i8, i9, i10);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* JADX INFO: renamed from: i0 */
        public /* bridge */ /* synthetic */ void q(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i7, int i8, int[] iArr, int i9) {
            super.q(coordinatorLayout, appBarLayout, view, i7, i8, iArr, i9);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* JADX INFO: renamed from: j0 */
        public /* bridge */ /* synthetic */ void t(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i7, int i8, int i9, int i10, int i11, int[] iArr) {
            super.t(coordinatorLayout, appBarLayout, view, i7, i8, i9, i10, i11, iArr);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* JADX INFO: renamed from: k0 */
        public /* bridge */ /* synthetic */ void x(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, Parcelable parcelable) {
            super.x(coordinatorLayout, appBarLayout, parcelable);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* JADX INFO: renamed from: l0 */
        public /* bridge */ /* synthetic */ Parcelable y(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
            return super.y(coordinatorLayout, appBarLayout);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* JADX INFO: renamed from: m0 */
        public /* bridge */ /* synthetic */ boolean A(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, View view2, int i7, int i8) {
            return super.A(coordinatorLayout, appBarLayout, view, view2, i7, i8);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* JADX INFO: renamed from: n0 */
        public /* bridge */ /* synthetic */ void C(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i7) {
            super.C(coordinatorLayout, appBarLayout, view, i7);
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public static class ScrollingViewBehavior extends com.google.android.material.appbar.b {
        public ScrollingViewBehavior() {
        }

        private static int R(AppBarLayout appBarLayout) {
            CoordinatorLayout.c cVarF = ((CoordinatorLayout.f) appBarLayout.getLayoutParams()).f();
            if (cVarF instanceof BaseBehavior) {
                return ((BaseBehavior) cVarF).M();
            }
            return 0;
        }

        private void S(View view, View view2) {
            CoordinatorLayout.c cVarF = ((CoordinatorLayout.f) view2.getLayoutParams()).f();
            if (cVarF instanceof BaseBehavior) {
                u.V(view, (((view2.getBottom() - view.getTop()) + ((BaseBehavior) cVarF).f4036k) + M()) - I(view2));
            }
        }

        private void T(View view, View view2) {
            if (view2 instanceof AppBarLayout) {
                AppBarLayout appBarLayout = (AppBarLayout) view2;
                if (appBarLayout.j()) {
                    appBarLayout.p(appBarLayout.r(view));
                }
            }
        }

        @Override // com.google.android.material.appbar.b
        float J(View view) {
            int i7;
            if (view instanceof AppBarLayout) {
                AppBarLayout appBarLayout = (AppBarLayout) view;
                int totalScrollRange = appBarLayout.getTotalScrollRange();
                int downNestedPreScrollRange = appBarLayout.getDownNestedPreScrollRange();
                int iR = R(appBarLayout);
                if ((downNestedPreScrollRange == 0 || totalScrollRange + iR > downNestedPreScrollRange) && (i7 = totalScrollRange - downNestedPreScrollRange) != 0) {
                    return (iR / i7) + 1.0f;
                }
            }
            return 0.0f;
        }

        @Override // com.google.android.material.appbar.b
        int L(View view) {
            return view instanceof AppBarLayout ? ((AppBarLayout) view).getTotalScrollRange() : super.L(view);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.material.appbar.b
        /* JADX INFO: renamed from: Q, reason: merged with bridge method [inline-methods] */
        public AppBarLayout H(List<View> list) {
            int size = list.size();
            for (int i7 = 0; i7 < size; i7++) {
                View view = list.get(i7);
                if (view instanceof AppBarLayout) {
                    return (AppBarLayout) view;
                }
            }
            return null;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public boolean e(CoordinatorLayout coordinatorLayout, View view, View view2) {
            return view2 instanceof AppBarLayout;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public boolean h(CoordinatorLayout coordinatorLayout, View view, View view2) {
            S(view, view2);
            T(view, view2);
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public void i(CoordinatorLayout coordinatorLayout, View view, View view2) {
            if (view2 instanceof AppBarLayout) {
                u.e0(coordinatorLayout, c.a.f7125q.b());
                u.e0(coordinatorLayout, c.a.f7126r.b());
            }
        }

        @Override // com.google.android.material.appbar.c, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public /* bridge */ /* synthetic */ boolean l(CoordinatorLayout coordinatorLayout, View view, int i7) {
            return super.l(coordinatorLayout, view, i7);
        }

        @Override // com.google.android.material.appbar.b, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public /* bridge */ /* synthetic */ boolean m(CoordinatorLayout coordinatorLayout, View view, int i7, int i8, int i9, int i10) {
            return super.m(coordinatorLayout, view, i7, i8, i9, i10);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public boolean w(CoordinatorLayout coordinatorLayout, View view, Rect rect, boolean z6) {
            AppBarLayout appBarLayoutH = H(coordinatorLayout.r(view));
            if (appBarLayoutH != null) {
                rect.offset(view.getLeft(), view.getTop());
                Rect rect2 = this.f4072d;
                rect2.set(0, 0, coordinatorLayout.getWidth(), coordinatorLayout.getHeight());
                if (!rect2.contains(rect)) {
                    appBarLayoutH.m(false, !z6);
                    return true;
                }
            }
            return false;
        }

        public ScrollingViewBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, l.ScrollingViewBehavior_Layout);
            O(typedArrayObtainStyledAttributes.getDimensionPixelSize(l.ScrollingViewBehavior_Layout_behavior_overlapTop, 0));
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    class a implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ g f4057a;

        a(g gVar) {
            this.f4057a = gVar;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f4057a.W(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    public interface b<T extends AppBarLayout> {
        void a(T t6, int i7);
    }

    private void a() {
        WeakReference<View> weakReference = this.f4032s;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.f4032s = null;
    }

    private View b(View view) {
        int i7;
        if (this.f4032s == null && (i7 = this.f4031r) != -1) {
            View viewFindViewById = view != null ? view.findViewById(i7) : null;
            if (viewFindViewById == null && (getParent() instanceof ViewGroup)) {
                viewFindViewById = ((ViewGroup) getParent()).findViewById(this.f4031r);
            }
            if (viewFindViewById != null) {
                this.f4032s = new WeakReference<>(viewFindViewById);
            }
        }
        WeakReference<View> weakReference = this.f4032s;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    private boolean g() {
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            if (((c) getChildAt(i7).getLayoutParams()).c()) {
                return true;
            }
        }
        return false;
    }

    private void i() {
        this.f4020g = -1;
        this.f4021h = -1;
        this.f4022i = -1;
    }

    private void n(boolean z6, boolean z7, boolean z8) {
        this.f4024k = (z6 ? 1 : 2) | (z7 ? 4 : 0) | (z8 ? 8 : 0);
        requestLayout();
    }

    private boolean o(boolean z6) {
        if (this.f4028o == z6) {
            return false;
        }
        this.f4028o = z6;
        refreshDrawableState();
        return true;
    }

    private boolean q() {
        return this.f4035v != null && getTopInset() > 0;
    }

    private boolean s() {
        if (getChildCount() <= 0) {
            return false;
        }
        View childAt = getChildAt(0);
        return (childAt.getVisibility() == 8 || u.v(childAt)) ? false : true;
    }

    private void t(g gVar, boolean z6) {
        float dimension = getResources().getDimension(o2.d.design_appbar_elevation);
        float f7 = z6 ? 0.0f : dimension;
        if (!z6) {
            dimension = 0.0f;
        }
        ValueAnimator valueAnimator = this.f4033t;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f7, dimension);
        this.f4033t = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(getResources().getInteger(o2.g.app_bar_elevation_anim_duration));
        this.f4033t.setInterpolator(p2.a.f8820a);
        this.f4033t.addUpdateListener(new a(gVar));
        this.f4033t.start();
    }

    private void u() {
        setWillNotDraw(!q());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.LinearLayout, android.view.ViewGroup
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public c generateDefaultLayoutParams() {
        return new c(-1, -2);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof c;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public c generateLayoutParams(AttributeSet attributeSet) {
        return new c(getContext(), attributeSet);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (q()) {
            int iSave = canvas.save();
            canvas.translate(0.0f, -this.f4019e);
            this.f4035v.draw(canvas);
            canvas.restoreToCount(iSave);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.f4035v;
        if (drawable != null && drawable.isStateful() && drawable.setState(drawableState)) {
            invalidateDrawable(drawable);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.LinearLayout, android.view.ViewGroup
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public c generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LinearLayout.LayoutParams ? new c((LinearLayout.LayoutParams) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new c((ViewGroup.MarginLayoutParams) layoutParams) : new c(layoutParams);
    }

    boolean f() {
        return this.f4023j;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.b
    public CoordinatorLayout.c<AppBarLayout> getBehavior() {
        return new Behavior();
    }

    int getDownNestedPreScrollRange() {
        int iMin;
        int iZ;
        int i7 = this.f4021h;
        if (i7 != -1) {
            return i7;
        }
        int i8 = 0;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            c cVar = (c) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int i9 = cVar.f4059a;
            if ((i9 & 5) != 5) {
                if (i8 > 0) {
                    break;
                }
            } else {
                int i10 = ((LinearLayout.LayoutParams) cVar).topMargin + ((LinearLayout.LayoutParams) cVar).bottomMargin;
                if ((i9 & 8) != 0) {
                    iZ = u.z(childAt);
                } else if ((i9 & 2) != 0) {
                    iZ = measuredHeight - u.z(childAt);
                } else {
                    iMin = i10 + measuredHeight;
                    if (childCount == 0 && u.v(childAt)) {
                        iMin = Math.min(iMin, measuredHeight - getTopInset());
                    }
                    i8 += iMin;
                }
                iMin = i10 + iZ;
                if (childCount == 0) {
                    iMin = Math.min(iMin, measuredHeight - getTopInset());
                }
                i8 += iMin;
            }
        }
        int iMax = Math.max(0, i8);
        this.f4021h = iMax;
        return iMax;
    }

    int getDownNestedScrollRange() {
        int i7 = this.f4022i;
        if (i7 != -1) {
            return i7;
        }
        int childCount = getChildCount();
        int i8 = 0;
        int iZ = 0;
        while (true) {
            if (i8 >= childCount) {
                break;
            }
            View childAt = getChildAt(i8);
            c cVar = (c) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight() + ((LinearLayout.LayoutParams) cVar).topMargin + ((LinearLayout.LayoutParams) cVar).bottomMargin;
            int i9 = cVar.f4059a;
            if ((i9 & 1) == 0) {
                break;
            }
            iZ += measuredHeight;
            if ((i9 & 2) != 0) {
                iZ -= u.z(childAt);
                break;
            }
            i8++;
        }
        int iMax = Math.max(0, iZ);
        this.f4022i = iMax;
        return iMax;
    }

    public int getLiftOnScrollTargetViewId() {
        return this.f4031r;
    }

    public final int getMinimumHeightForVisibleOverlappingContent() {
        int topInset = getTopInset();
        int iZ = u.z(this);
        if (iZ == 0) {
            int childCount = getChildCount();
            iZ = childCount >= 1 ? u.z(getChildAt(childCount - 1)) : 0;
            if (iZ == 0) {
                return getHeight() / 3;
            }
        }
        return (iZ * 2) + topInset;
    }

    int getPendingAction() {
        return this.f4024k;
    }

    public Drawable getStatusBarForeground() {
        return this.f4035v;
    }

    @Deprecated
    public float getTargetElevation() {
        return 0.0f;
    }

    final int getTopInset() {
        c0 c0Var = this.f4025l;
        if (c0Var != null) {
            return c0Var.i();
        }
        return 0;
    }

    public final int getTotalScrollRange() {
        int i7 = this.f4020g;
        if (i7 != -1) {
            return i7;
        }
        int childCount = getChildCount();
        int i8 = 0;
        int iZ = 0;
        while (true) {
            if (i8 >= childCount) {
                break;
            }
            View childAt = getChildAt(i8);
            c cVar = (c) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int i9 = cVar.f4059a;
            if ((i9 & 1) == 0) {
                break;
            }
            iZ += measuredHeight + ((LinearLayout.LayoutParams) cVar).topMargin + ((LinearLayout.LayoutParams) cVar).bottomMargin;
            if (i8 == 0 && u.v(childAt)) {
                iZ -= getTopInset();
            }
            if ((i9 & 2) != 0) {
                iZ -= u.z(childAt);
                break;
            }
            i8++;
        }
        int iMax = Math.max(0, iZ);
        this.f4020g = iMax;
        return iMax;
    }

    int getUpNestedPreScrollRange() {
        return getTotalScrollRange();
    }

    boolean h() {
        return getTotalScrollRange() != 0;
    }

    public boolean j() {
        return this.f4030q;
    }

    void k(int i7) {
        this.f4019e = i7;
        if (!willNotDraw()) {
            u.b0(this);
        }
        List<b> list = this.f4026m;
        if (list != null) {
            int size = list.size();
            for (int i8 = 0; i8 < size; i8++) {
                b bVar = this.f4026m.get(i8);
                if (bVar != null) {
                    bVar.a(this, i7);
                }
            }
        }
    }

    void l() {
        this.f4024k = 0;
    }

    public void m(boolean z6, boolean z7) {
        n(z6, z7, true);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        h.e(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected int[] onCreateDrawableState(int i7) {
        if (this.f4034u == null) {
            this.f4034u = new int[4];
        }
        int[] iArr = this.f4034u;
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i7 + iArr.length);
        boolean z6 = this.f4028o;
        int i8 = o2.b.state_liftable;
        if (!z6) {
            i8 = -i8;
        }
        iArr[0] = i8;
        iArr[1] = (z6 && this.f4029p) ? o2.b.state_lifted : -o2.b.state_lifted;
        int i9 = o2.b.state_collapsible;
        if (!z6) {
            i9 = -i9;
        }
        iArr[2] = i9;
        iArr[3] = (z6 && this.f4029p) ? o2.b.state_collapsed : -o2.b.state_collapsed;
        return LinearLayout.mergeDrawableStates(iArrOnCreateDrawableState, iArr);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
        boolean z7 = true;
        if (u.v(this) && s()) {
            int topInset = getTopInset();
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                u.V(getChildAt(childCount), topInset);
            }
        }
        i();
        this.f4023j = false;
        int childCount2 = getChildCount();
        int i11 = 0;
        while (true) {
            if (i11 >= childCount2) {
                break;
            }
            if (((c) getChildAt(i11).getLayoutParams()).b() != null) {
                this.f4023j = true;
                break;
            }
            i11++;
        }
        Drawable drawable = this.f4035v;
        if (drawable != null) {
            drawable.setBounds(0, 0, getWidth(), getTopInset());
        }
        if (this.f4027n) {
            return;
        }
        if (!this.f4030q && !g()) {
            z7 = false;
        }
        o(z7);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i7, int i8) {
        super.onMeasure(i7, i8);
        int mode = View.MeasureSpec.getMode(i8);
        if (mode != 1073741824 && u.v(this) && s()) {
            int measuredHeight = getMeasuredHeight();
            if (mode == Integer.MIN_VALUE) {
                measuredHeight = c0.a.b(getMeasuredHeight() + getTopInset(), 0, View.MeasureSpec.getSize(i8));
            } else if (mode == 0) {
                measuredHeight += getTopInset();
            }
            setMeasuredDimension(getMeasuredWidth(), measuredHeight);
        }
        i();
    }

    boolean p(boolean z6) {
        if (this.f4029p == z6) {
            return false;
        }
        this.f4029p = z6;
        refreshDrawableState();
        if (!this.f4030q || !(getBackground() instanceof g)) {
            return true;
        }
        t((g) getBackground(), z6);
        return true;
    }

    boolean r(View view) {
        View viewB = b(view);
        if (viewB != null) {
            view = viewB;
        }
        return view != null && (view.canScrollVertically(-1) || view.getScrollY() > 0);
    }

    @Override // android.view.View
    public void setElevation(float f7) {
        super.setElevation(f7);
        h.d(this, f7);
    }

    public void setExpanded(boolean z6) {
        m(z6, u.P(this));
    }

    public void setLiftOnScroll(boolean z6) {
        this.f4030q = z6;
    }

    public void setLiftOnScrollTargetViewId(int i7) {
        this.f4031r = i7;
        a();
    }

    @Override // android.widget.LinearLayout
    public void setOrientation(int i7) {
        if (i7 != 1) {
            throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
        }
        super.setOrientation(i7);
    }

    public void setStatusBarForeground(Drawable drawable) {
        Drawable drawable2 = this.f4035v;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.f4035v = drawableMutate;
            if (drawableMutate != null) {
                if (drawableMutate.isStateful()) {
                    this.f4035v.setState(getDrawableState());
                }
                a0.a.g(this.f4035v, u.y(this));
                this.f4035v.setVisible(getVisibility() == 0, false);
                this.f4035v.setCallback(this);
            }
            u();
            u.b0(this);
        }
    }

    public void setStatusBarForegroundColor(int i7) {
        setStatusBarForeground(new ColorDrawable(i7));
    }

    public void setStatusBarForegroundResource(int i7) {
        setStatusBarForeground(f.a.b(getContext(), i7));
    }

    @Deprecated
    public void setTargetElevation(float f7) {
        e.a(this, f7);
    }

    @Override // android.view.View
    public void setVisibility(int i7) {
        super.setVisibility(i7);
        boolean z6 = i7 == 0;
        Drawable drawable = this.f4035v;
        if (drawable != null) {
            drawable.setVisible(z6, false);
        }
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f4035v;
    }

    protected static class BaseBehavior<T extends AppBarLayout> extends com.google.android.material.appbar.a<T> {

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private int f4036k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        private int f4037l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        private ValueAnimator f4038m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        private int f4039n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        private boolean f4040o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        private float f4041p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        private WeakReference<View> f4042q;

        class a implements ValueAnimator.AnimatorUpdateListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ CoordinatorLayout f4046a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ AppBarLayout f4047b;

            a(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
                this.f4046a = coordinatorLayout;
                this.f4047b = appBarLayout;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                BaseBehavior.this.P(this.f4046a, this.f4047b, ((Integer) valueAnimator.getAnimatedValue()).intValue());
            }
        }

        class b implements f {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ CoordinatorLayout f4049a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ AppBarLayout f4050b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ View f4051c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ int f4052d;

            b(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i7) {
                this.f4049a = coordinatorLayout;
                this.f4050b = appBarLayout;
                this.f4051c = view;
                this.f4052d = i7;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // j0.f
            public boolean a(View view, f.a aVar) {
                BaseBehavior.this.q(this.f4049a, this.f4050b, this.f4051c, 0, this.f4052d, new int[]{0, 0}, 1);
                return true;
            }
        }

        class c implements f {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ AppBarLayout f4054a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ boolean f4055b;

            c(AppBarLayout appBarLayout, boolean z6) {
                this.f4054a = appBarLayout;
                this.f4055b = z6;
            }

            @Override // j0.f
            public boolean a(View view, f.a aVar) {
                this.f4054a.setExpanded(this.f4055b);
                return true;
            }
        }

        public BaseBehavior() {
            this.f4039n = -1;
        }

        private void S(CoordinatorLayout coordinatorLayout, T t6, View view) {
            if (M() != (-t6.getTotalScrollRange()) && view.canScrollVertically(1)) {
                T(coordinatorLayout, t6, c.a.f7125q, false);
            }
            if (M() != 0) {
                if (!view.canScrollVertically(-1)) {
                    T(coordinatorLayout, t6, c.a.f7126r, true);
                    return;
                }
                int i7 = -t6.getDownNestedPreScrollRange();
                if (i7 != 0) {
                    u.g0(coordinatorLayout, c.a.f7126r, null, new b(coordinatorLayout, t6, view, i7));
                }
            }
        }

        private void T(CoordinatorLayout coordinatorLayout, T t6, c.a aVar, boolean z6) {
            u.g0(coordinatorLayout, aVar, null, new c(t6, z6));
        }

        private void U(CoordinatorLayout coordinatorLayout, T t6, int i7, float f7) {
            int iAbs = Math.abs(M() - i7);
            float fAbs = Math.abs(f7);
            V(coordinatorLayout, t6, i7, fAbs > 0.0f ? Math.round((iAbs / fAbs) * 1000.0f) * 3 : (int) (((iAbs / t6.getHeight()) + 1.0f) * 150.0f));
        }

        private void V(CoordinatorLayout coordinatorLayout, T t6, int i7, int i8) {
            int iM = M();
            if (iM == i7) {
                ValueAnimator valueAnimator = this.f4038m;
                if (valueAnimator == null || !valueAnimator.isRunning()) {
                    return;
                }
                this.f4038m.cancel();
                return;
            }
            ValueAnimator valueAnimator2 = this.f4038m;
            if (valueAnimator2 == null) {
                ValueAnimator valueAnimator3 = new ValueAnimator();
                this.f4038m = valueAnimator3;
                valueAnimator3.setInterpolator(p2.a.f8824e);
                this.f4038m.addUpdateListener(new a(coordinatorLayout, t6));
            } else {
                valueAnimator2.cancel();
            }
            this.f4038m.setDuration(Math.min(i8, 600));
            this.f4038m.setIntValues(iM, i7);
            this.f4038m.start();
        }

        private boolean X(CoordinatorLayout coordinatorLayout, T t6, View view) {
            return t6.h() && coordinatorLayout.getHeight() - view.getHeight() <= t6.getHeight();
        }

        private static boolean Y(int i7, int i8) {
            return (i7 & i8) == i8;
        }

        private View Z(CoordinatorLayout coordinatorLayout) {
            int childCount = coordinatorLayout.getChildCount();
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = coordinatorLayout.getChildAt(i7);
                if ((childAt instanceof i0.k) || (childAt instanceof ListView) || (childAt instanceof ScrollView)) {
                    return childAt;
                }
            }
            return null;
        }

        private static View a0(AppBarLayout appBarLayout, int i7) {
            int iAbs = Math.abs(i7);
            int childCount = appBarLayout.getChildCount();
            for (int i8 = 0; i8 < childCount; i8++) {
                View childAt = appBarLayout.getChildAt(i8);
                if (iAbs >= childAt.getTop() && iAbs <= childAt.getBottom()) {
                    return childAt;
                }
            }
            return null;
        }

        private int b0(T t6, int i7) {
            int childCount = t6.getChildCount();
            for (int i8 = 0; i8 < childCount; i8++) {
                View childAt = t6.getChildAt(i8);
                int top = childAt.getTop();
                int bottom = childAt.getBottom();
                c cVar = (c) childAt.getLayoutParams();
                if (Y(cVar.a(), 32)) {
                    top -= ((LinearLayout.LayoutParams) cVar).topMargin;
                    bottom += ((LinearLayout.LayoutParams) cVar).bottomMargin;
                }
                int i9 = -i7;
                if (top <= i9 && bottom >= i9) {
                    return i8;
                }
            }
            return -1;
        }

        private int e0(T t6, int i7) {
            int iAbs = Math.abs(i7);
            int childCount = t6.getChildCount();
            int topInset = 0;
            int i8 = 0;
            while (true) {
                if (i8 >= childCount) {
                    break;
                }
                View childAt = t6.getChildAt(i8);
                c cVar = (c) childAt.getLayoutParams();
                Interpolator interpolatorB = cVar.b();
                if (iAbs < childAt.getTop() || iAbs > childAt.getBottom()) {
                    i8++;
                } else if (interpolatorB != null) {
                    int iA = cVar.a();
                    if ((iA & 1) != 0) {
                        topInset = 0 + childAt.getHeight() + ((LinearLayout.LayoutParams) cVar).topMargin + ((LinearLayout.LayoutParams) cVar).bottomMargin;
                        if ((iA & 2) != 0) {
                            topInset -= u.z(childAt);
                        }
                    }
                    if (u.v(childAt)) {
                        topInset -= t6.getTopInset();
                    }
                    if (topInset > 0) {
                        float f7 = topInset;
                        return Integer.signum(i7) * (childAt.getTop() + Math.round(f7 * interpolatorB.getInterpolation((iAbs - childAt.getTop()) / f7)));
                    }
                }
            }
            return i7;
        }

        private boolean p0(CoordinatorLayout coordinatorLayout, T t6) {
            List<View> listS = coordinatorLayout.s(t6);
            int size = listS.size();
            for (int i7 = 0; i7 < size; i7++) {
                CoordinatorLayout.c cVarF = ((CoordinatorLayout.f) listS.get(i7).getLayoutParams()).f();
                if (cVarF instanceof ScrollingViewBehavior) {
                    return ((ScrollingViewBehavior) cVarF).K() != 0;
                }
            }
            return false;
        }

        private void q0(CoordinatorLayout coordinatorLayout, T t6) {
            int iM = M();
            int iB0 = b0(t6, iM);
            if (iB0 >= 0) {
                View childAt = t6.getChildAt(iB0);
                c cVar = (c) childAt.getLayoutParams();
                int iA = cVar.a();
                if ((iA & 17) == 17) {
                    int i7 = -childAt.getTop();
                    int iZ = -childAt.getBottom();
                    if (iB0 == t6.getChildCount() - 1) {
                        iZ += t6.getTopInset();
                    }
                    if (Y(iA, 2)) {
                        iZ += u.z(childAt);
                    } else if (Y(iA, 5)) {
                        int iZ2 = u.z(childAt) + iZ;
                        if (iM < iZ2) {
                            i7 = iZ2;
                        } else {
                            iZ = iZ2;
                        }
                    }
                    if (Y(iA, 32)) {
                        i7 += ((LinearLayout.LayoutParams) cVar).topMargin;
                        iZ -= ((LinearLayout.LayoutParams) cVar).bottomMargin;
                    }
                    if (iM < (iZ + i7) / 2) {
                        i7 = iZ;
                    }
                    U(coordinatorLayout, t6, c0.a.b(i7, -t6.getTotalScrollRange(), 0), 0.0f);
                }
            }
        }

        private void r0(CoordinatorLayout coordinatorLayout, T t6) {
            u.e0(coordinatorLayout, c.a.f7125q.b());
            u.e0(coordinatorLayout, c.a.f7126r.b());
            View viewZ = Z(coordinatorLayout);
            if (viewZ == null || t6.getTotalScrollRange() == 0 || !(((CoordinatorLayout.f) viewZ.getLayoutParams()).f() instanceof ScrollingViewBehavior)) {
                return;
            }
            S(coordinatorLayout, t6, viewZ);
        }

        private void s0(CoordinatorLayout coordinatorLayout, T t6, int i7, int i8, boolean z6) {
            View viewA0 = a0(t6, i7);
            if (viewA0 != null) {
                int iA = ((c) viewA0.getLayoutParams()).a();
                boolean zR = false;
                if ((iA & 1) != 0) {
                    int iZ = u.z(viewA0);
                    if (i8 <= 0 || (iA & 12) == 0 ? !((iA & 2) == 0 || (-i7) < (viewA0.getBottom() - iZ) - t6.getTopInset()) : (-i7) >= (viewA0.getBottom() - iZ) - t6.getTopInset()) {
                        zR = true;
                    }
                }
                if (t6.j()) {
                    zR = t6.r(Z(coordinatorLayout));
                }
                boolean zP = t6.p(zR);
                if (z6 || (zP && p0(coordinatorLayout, t6))) {
                    t6.jumpDrawablesToCurrentState();
                }
            }
        }

        @Override // com.google.android.material.appbar.a
        int M() {
            return E() + this.f4036k;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.material.appbar.a
        /* JADX INFO: renamed from: W, reason: merged with bridge method [inline-methods] */
        public boolean H(T t6) {
            WeakReference<View> weakReference = this.f4042q;
            if (weakReference == null) {
                return true;
            }
            View view = weakReference.get();
            return (view == null || !view.isShown() || view.canScrollVertically(-1)) ? false : true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.material.appbar.a
        /* JADX INFO: renamed from: c0, reason: merged with bridge method [inline-methods] */
        public int K(T t6) {
            return -t6.getDownNestedScrollRange();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.material.appbar.a
        /* JADX INFO: renamed from: d0, reason: merged with bridge method [inline-methods] */
        public int L(T t6) {
            return t6.getTotalScrollRange();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.material.appbar.a
        /* JADX INFO: renamed from: f0, reason: merged with bridge method [inline-methods] */
        public void N(CoordinatorLayout coordinatorLayout, T t6) {
            q0(coordinatorLayout, t6);
            if (t6.j()) {
                t6.p(t6.r(Z(coordinatorLayout)));
            }
        }

        @Override // com.google.android.material.appbar.c, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: g0, reason: merged with bridge method [inline-methods] */
        public boolean l(CoordinatorLayout coordinatorLayout, T t6, int i7) {
            boolean zL = super.l(coordinatorLayout, t6, i7);
            int pendingAction = t6.getPendingAction();
            int i8 = this.f4039n;
            if (i8 >= 0 && (pendingAction & 8) == 0) {
                View childAt = t6.getChildAt(i8);
                P(coordinatorLayout, t6, (-childAt.getBottom()) + (this.f4040o ? u.z(childAt) + t6.getTopInset() : Math.round(childAt.getHeight() * this.f4041p)));
            } else if (pendingAction != 0) {
                boolean z6 = (pendingAction & 4) != 0;
                if ((pendingAction & 2) != 0) {
                    int i9 = -t6.getUpNestedPreScrollRange();
                    if (z6) {
                        U(coordinatorLayout, t6, i9, 0.0f);
                    } else {
                        P(coordinatorLayout, t6, i9);
                    }
                } else if ((pendingAction & 1) != 0) {
                    if (z6) {
                        U(coordinatorLayout, t6, 0, 0.0f);
                    } else {
                        P(coordinatorLayout, t6, 0);
                    }
                }
            }
            t6.l();
            this.f4039n = -1;
            G(c0.a.b(E(), -t6.getTotalScrollRange(), 0));
            s0(coordinatorLayout, t6, E(), 0, true);
            t6.k(E());
            r0(coordinatorLayout, t6);
            return zL;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: h0, reason: merged with bridge method [inline-methods] */
        public boolean m(CoordinatorLayout coordinatorLayout, T t6, int i7, int i8, int i9, int i10) {
            if (((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.f) t6.getLayoutParams())).height != -2) {
                return super.m(coordinatorLayout, t6, i7, i8, i9, i10);
            }
            coordinatorLayout.J(t6, i7, i8, View.MeasureSpec.makeMeasureSpec(0, 0), i10);
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: i0, reason: merged with bridge method [inline-methods] */
        public void q(CoordinatorLayout coordinatorLayout, T t6, View view, int i7, int i8, int[] iArr, int i9) {
            int i10;
            int downNestedPreScrollRange;
            if (i8 != 0) {
                if (i8 < 0) {
                    i10 = -t6.getTotalScrollRange();
                    downNestedPreScrollRange = t6.getDownNestedPreScrollRange() + i10;
                } else {
                    i10 = -t6.getUpNestedPreScrollRange();
                    downNestedPreScrollRange = 0;
                }
                int i11 = i10;
                int i12 = downNestedPreScrollRange;
                if (i11 != i12) {
                    iArr[1] = O(coordinatorLayout, t6, i8, i11, i12);
                }
            }
            if (t6.j()) {
                t6.p(t6.r(view));
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: j0, reason: merged with bridge method [inline-methods] */
        public void t(CoordinatorLayout coordinatorLayout, T t6, View view, int i7, int i8, int i9, int i10, int i11, int[] iArr) {
            if (i10 < 0) {
                iArr[1] = O(coordinatorLayout, t6, i10, -t6.getDownNestedScrollRange(), 0);
            }
            if (i10 == 0) {
                r0(coordinatorLayout, t6);
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: k0, reason: merged with bridge method [inline-methods] */
        public void x(CoordinatorLayout coordinatorLayout, T t6, Parcelable parcelable) {
            if (!(parcelable instanceof SavedState)) {
                super.x(coordinatorLayout, t6, parcelable);
                this.f4039n = -1;
                return;
            }
            SavedState savedState = (SavedState) parcelable;
            super.x(coordinatorLayout, t6, savedState.b());
            this.f4039n = savedState.f4043h;
            this.f4041p = savedState.f4044i;
            this.f4040o = savedState.f4045j;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: l0, reason: merged with bridge method [inline-methods] */
        public Parcelable y(CoordinatorLayout coordinatorLayout, T t6) {
            Parcelable parcelableY = super.y(coordinatorLayout, t6);
            int iE = E();
            int childCount = t6.getChildCount();
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = t6.getChildAt(i7);
                int bottom = childAt.getBottom() + iE;
                if (childAt.getTop() + iE <= 0 && bottom >= 0) {
                    SavedState savedState = new SavedState(parcelableY);
                    savedState.f4043h = i7;
                    savedState.f4045j = bottom == u.z(childAt) + t6.getTopInset();
                    savedState.f4044i = bottom / childAt.getHeight();
                    return savedState;
                }
            }
            return parcelableY;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: m0, reason: merged with bridge method [inline-methods] */
        public boolean A(CoordinatorLayout coordinatorLayout, T t6, View view, View view2, int i7, int i8) {
            ValueAnimator valueAnimator;
            boolean z6 = (i7 & 2) != 0 && (t6.j() || X(coordinatorLayout, t6, view));
            if (z6 && (valueAnimator = this.f4038m) != null) {
                valueAnimator.cancel();
            }
            this.f4042q = null;
            this.f4037l = i8;
            return z6;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: n0, reason: merged with bridge method [inline-methods] */
        public void C(CoordinatorLayout coordinatorLayout, T t6, View view, int i7) {
            if (this.f4037l == 0 || i7 == 1) {
                q0(coordinatorLayout, t6);
                if (t6.j()) {
                    t6.p(t6.r(view));
                }
            }
            this.f4042q = new WeakReference<>(view);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.material.appbar.a
        /* JADX INFO: renamed from: o0, reason: merged with bridge method [inline-methods] */
        public int Q(CoordinatorLayout coordinatorLayout, T t6, int i7, int i8, int i9) {
            int iM = M();
            int i10 = 0;
            if (i8 == 0 || iM < i8 || iM > i9) {
                this.f4036k = 0;
            } else {
                int iB = c0.a.b(i7, i8, i9);
                if (iM != iB) {
                    int iE0 = t6.f() ? e0(t6, iB) : iB;
                    boolean zG = G(iE0);
                    i10 = iM - iB;
                    this.f4036k = iB - iE0;
                    if (!zG && t6.f()) {
                        coordinatorLayout.f(t6);
                    }
                    t6.k(E());
                    s0(coordinatorLayout, t6, iB, iB < iM ? -1 : 1, false);
                }
            }
            r0(coordinatorLayout, t6);
            return i10;
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f4039n = -1;
        }

        protected static class SavedState extends AbsSavedState {
            public static final Parcelable.Creator<SavedState> CREATOR = new a();

            /* JADX INFO: renamed from: h, reason: collision with root package name */
            int f4043h;

            /* JADX INFO: renamed from: i, reason: collision with root package name */
            float f4044i;

            /* JADX INFO: renamed from: j, reason: collision with root package name */
            boolean f4045j;

            static class a implements Parcelable.ClassLoaderCreator<SavedState> {
                a() {
                }

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
                public SavedState[] newArray(int i7) {
                    return new SavedState[i7];
                }
            }

            public SavedState(Parcel parcel, ClassLoader classLoader) {
                super(parcel, classLoader);
                this.f4043h = parcel.readInt();
                this.f4044i = parcel.readFloat();
                this.f4045j = parcel.readByte() != 0;
            }

            @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i7) {
                super.writeToParcel(parcel, i7);
                parcel.writeInt(this.f4043h);
                parcel.writeFloat(this.f4044i);
                parcel.writeByte(this.f4045j ? (byte) 1 : (byte) 0);
            }

            public SavedState(Parcelable parcelable) {
                super(parcelable);
            }
        }
    }

    public static class c extends LinearLayout.LayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f4059a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Interpolator f4060b;

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f4059a = 1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, l.AppBarLayout_Layout);
            this.f4059a = typedArrayObtainStyledAttributes.getInt(l.AppBarLayout_Layout_layout_scrollFlags, 0);
            int i7 = l.AppBarLayout_Layout_layout_scrollInterpolator;
            if (typedArrayObtainStyledAttributes.hasValue(i7)) {
                this.f4060b = AnimationUtils.loadInterpolator(context, typedArrayObtainStyledAttributes.getResourceId(i7, 0));
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public int a() {
            return this.f4059a;
        }

        public Interpolator b() {
            return this.f4060b;
        }

        boolean c() {
            int i7 = this.f4059a;
            return (i7 & 1) == 1 && (i7 & 10) != 0;
        }

        public c(int i7, int i8) {
            super(i7, i8);
            this.f4059a = 1;
        }

        public c(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f4059a = 1;
        }

        public c(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f4059a = 1;
        }

        public c(LinearLayout.LayoutParams layoutParams) {
            super(layoutParams);
            this.f4059a = 1;
        }
    }
}
