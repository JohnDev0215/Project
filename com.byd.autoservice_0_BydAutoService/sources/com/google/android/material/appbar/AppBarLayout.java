package com.google.android.material.appbar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
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
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$integer;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import i2.k;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import l0.b0;
import l0.j;
import l0.p;
import l0.t;
import m0.c;
import m0.f;
import n2.h;
import n2.i;

/* JADX INFO: loaded from: classes.dex */
public class AppBarLayout extends LinearLayout implements CoordinatorLayout.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f1874b = R$style.Widget_Design_AppBarLayout;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1875c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1876d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1877e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1878f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1879g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1880h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public b0 f1881i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public List<c> f1882j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f1883k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f1884l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f1885m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f1886n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f1887o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public WeakReference<View> f1888p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ValueAnimator f1889q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int[] f1890r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Drawable f1891s;

    public static class Behavior extends BaseBehavior<AppBarLayout> {
        public Behavior() {
        }

        @Override // t1.c
        public /* bridge */ /* synthetic */ int E() {
            return super.E();
        }

        @Override // t1.c
        public /* bridge */ /* synthetic */ boolean G(int i3) {
            return super.G(i3);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* JADX INFO: renamed from: g0 */
        public /* bridge */ /* synthetic */ boolean l(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i3) {
            return super.l(coordinatorLayout, appBarLayout, i3);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* JADX INFO: renamed from: h0 */
        public /* bridge */ /* synthetic */ boolean m(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i3, int i4, int i5, int i6) {
            return super.m(coordinatorLayout, appBarLayout, i3, i4, i5, i6);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* JADX INFO: renamed from: i0 */
        public /* bridge */ /* synthetic */ void q(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i3, int i4, int[] iArr, int i5) {
            super.q(coordinatorLayout, appBarLayout, view, i3, i4, iArr, i5);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* JADX INFO: renamed from: j0 */
        public /* bridge */ /* synthetic */ void t(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i3, int i4, int i5, int i6, int i7, int[] iArr) {
            super.t(coordinatorLayout, appBarLayout, view, i3, i4, i5, i6, i7, iArr);
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
        public /* bridge */ /* synthetic */ boolean A(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, View view2, int i3, int i4) {
            return super.A(coordinatorLayout, appBarLayout, view, view2, i3, i4);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* JADX INFO: renamed from: n0 */
        public /* bridge */ /* synthetic */ void C(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i3) {
            super.C(coordinatorLayout, appBarLayout, view, i3);
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public static class ScrollingViewBehavior extends t1.b {
        public ScrollingViewBehavior() {
        }

        public static int R(AppBarLayout appBarLayout) {
            CoordinatorLayout.c cVarF = ((CoordinatorLayout.f) appBarLayout.getLayoutParams()).f();
            if (cVarF instanceof BaseBehavior) {
                return ((BaseBehavior) cVarF).M();
            }
            return 0;
        }

        @Override // t1.b
        public float J(View view) {
            int i3;
            if (view instanceof AppBarLayout) {
                AppBarLayout appBarLayout = (AppBarLayout) view;
                int totalScrollRange = appBarLayout.getTotalScrollRange();
                int downNestedPreScrollRange = appBarLayout.getDownNestedPreScrollRange();
                int iR = R(appBarLayout);
                if ((downNestedPreScrollRange == 0 || totalScrollRange + iR > downNestedPreScrollRange) && (i3 = totalScrollRange - downNestedPreScrollRange) != 0) {
                    return (iR / i3) + 1.0f;
                }
            }
            return 0.0f;
        }

        @Override // t1.b
        public int L(View view) {
            return view instanceof AppBarLayout ? ((AppBarLayout) view).getTotalScrollRange() : super.L(view);
        }

        @Override // t1.b
        /* JADX INFO: renamed from: Q, reason: merged with bridge method [inline-methods] */
        public AppBarLayout H(List<View> list) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                View view = list.get(i3);
                if (view instanceof AppBarLayout) {
                    return (AppBarLayout) view;
                }
            }
            return null;
        }

        public final void S(View view, View view2) {
            CoordinatorLayout.c cVarF = ((CoordinatorLayout.f) view2.getLayoutParams()).f();
            if (cVarF instanceof BaseBehavior) {
                t.V(view, (((view2.getBottom() - view.getTop()) + ((BaseBehavior) cVarF).f1892k) + M()) - I(view2));
            }
        }

        public final void T(View view, View view2) {
            if (view2 instanceof AppBarLayout) {
                AppBarLayout appBarLayout = (AppBarLayout) view2;
                if (appBarLayout.l()) {
                    appBarLayout.u(appBarLayout.w(view));
                }
            }
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
                t.d0(coordinatorLayout, c.a.f3814m.b());
                t.d0(coordinatorLayout, c.a.f3815n.b());
            }
        }

        @Override // t1.c, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public /* bridge */ /* synthetic */ boolean l(CoordinatorLayout coordinatorLayout, View view, int i3) {
            return super.l(coordinatorLayout, view, i3);
        }

        @Override // t1.b, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public /* bridge */ /* synthetic */ boolean m(CoordinatorLayout coordinatorLayout, View view, int i3, int i4, int i5, int i6) {
            return super.m(coordinatorLayout, view, i3, i4, i5, i6);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public boolean w(CoordinatorLayout coordinatorLayout, View view, Rect rect, boolean z3) {
            AppBarLayout appBarLayoutH = H(coordinatorLayout.v(view));
            if (appBarLayoutH != null) {
                rect.offset(view.getLeft(), view.getTop());
                Rect rect2 = this.f5026d;
                rect2.set(0, 0, coordinatorLayout.getWidth(), coordinatorLayout.getHeight());
                if (!rect2.contains(rect)) {
                    appBarLayoutH.r(false, !z3);
                    return true;
                }
            }
            return false;
        }

        public ScrollingViewBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ScrollingViewBehavior_Layout);
            O(typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ScrollingViewBehavior_Layout_behavior_overlapTop, 0));
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public class a implements p {
        public a() {
        }

        @Override // l0.p
        public b0 a(View view, b0 b0Var) {
            return AppBarLayout.this.n(b0Var);
        }
    }

    public class b implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ h f1915a;

        public b(h hVar) {
            this.f1915a = hVar;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f1915a.W(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    public interface c<T extends AppBarLayout> {
        void a(T t3, int i3);
    }

    public interface e extends c<AppBarLayout> {
    }

    public AppBarLayout(Context context) {
        this(context, null);
    }

    public void a(c cVar) {
        if (this.f1882j == null) {
            this.f1882j = new ArrayList();
        }
        if (cVar == null || this.f1882j.contains(cVar)) {
            return;
        }
        this.f1882j.add(cVar);
    }

    public void b(e eVar) {
        a(eVar);
    }

    public final void c() {
        WeakReference<View> weakReference = this.f1888p;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.f1888p = null;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof d;
    }

    public final View d(View view) {
        int i3;
        if (this.f1888p == null && (i3 = this.f1887o) != -1) {
            View viewFindViewById = view != null ? view.findViewById(i3) : null;
            if (viewFindViewById == null && (getParent() instanceof ViewGroup)) {
                viewFindViewById = ((ViewGroup) getParent()).findViewById(this.f1887o);
            }
            if (viewFindViewById != null) {
                this.f1888p = new WeakReference<>(viewFindViewById);
            }
        }
        WeakReference<View> weakReference = this.f1888p;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (v()) {
            int iSave = canvas.save();
            canvas.translate(0.0f, -this.f1875c);
            this.f1891s.draw(canvas);
            canvas.restoreToCount(iSave);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.f1891s;
        if (drawable != null && drawable.isStateful() && drawable.setState(drawableState)) {
            invalidateDrawable(drawable);
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public d generateDefaultLayoutParams() {
        return new d(-1, -2);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public d generateLayoutParams(AttributeSet attributeSet) {
        return new d(getContext(), attributeSet);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public d generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (Build.VERSION.SDK_INT < 19 || !(layoutParams instanceof LinearLayout.LayoutParams)) ? layoutParams instanceof ViewGroup.MarginLayoutParams ? new d((ViewGroup.MarginLayoutParams) layoutParams) : new d(layoutParams) : new d((LinearLayout.LayoutParams) layoutParams);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.b
    public CoordinatorLayout.c<AppBarLayout> getBehavior() {
        return new Behavior();
    }

    public int getDownNestedPreScrollRange() {
        int iMin;
        int iA;
        int i3 = this.f1877e;
        if (i3 != -1) {
            return i3;
        }
        int i4 = 0;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            d dVar = (d) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int i5 = dVar.f1917a;
            if ((i5 & 5) != 5) {
                if (i4 > 0) {
                    break;
                }
            } else {
                int i6 = ((LinearLayout.LayoutParams) dVar).topMargin + ((LinearLayout.LayoutParams) dVar).bottomMargin;
                if ((i5 & 8) != 0) {
                    iA = t.A(childAt);
                } else if ((i5 & 2) != 0) {
                    iA = measuredHeight - t.A(childAt);
                } else {
                    iMin = i6 + measuredHeight;
                    if (childCount == 0 && t.w(childAt)) {
                        iMin = Math.min(iMin, measuredHeight - getTopInset());
                    }
                    i4 += iMin;
                }
                iMin = i6 + iA;
                if (childCount == 0) {
                    iMin = Math.min(iMin, measuredHeight - getTopInset());
                }
                i4 += iMin;
            }
        }
        int iMax = Math.max(0, i4);
        this.f1877e = iMax;
        return iMax;
    }

    public int getDownNestedScrollRange() {
        int i3 = this.f1878f;
        if (i3 != -1) {
            return i3;
        }
        int childCount = getChildCount();
        int i4 = 0;
        int iA = 0;
        while (true) {
            if (i4 >= childCount) {
                break;
            }
            View childAt = getChildAt(i4);
            d dVar = (d) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight() + ((LinearLayout.LayoutParams) dVar).topMargin + ((LinearLayout.LayoutParams) dVar).bottomMargin;
            int i5 = dVar.f1917a;
            if ((i5 & 1) == 0) {
                break;
            }
            iA += measuredHeight;
            if ((i5 & 2) != 0) {
                iA -= t.A(childAt);
                break;
            }
            i4++;
        }
        int iMax = Math.max(0, iA);
        this.f1878f = iMax;
        return iMax;
    }

    public int getLiftOnScrollTargetViewId() {
        return this.f1887o;
    }

    public final int getMinimumHeightForVisibleOverlappingContent() {
        int topInset = getTopInset();
        int iA = t.A(this);
        if (iA == 0) {
            int childCount = getChildCount();
            iA = childCount >= 1 ? t.A(getChildAt(childCount - 1)) : 0;
            if (iA == 0) {
                return getHeight() / 3;
            }
        }
        return (iA * 2) + topInset;
    }

    public int getPendingAction() {
        return this.f1880h;
    }

    public Drawable getStatusBarForeground() {
        return this.f1891s;
    }

    @Deprecated
    public float getTargetElevation() {
        return 0.0f;
    }

    public final int getTopInset() {
        b0 b0Var = this.f1881i;
        if (b0Var != null) {
            return b0Var.h();
        }
        return 0;
    }

    public final int getTotalScrollRange() {
        int i3 = this.f1876d;
        if (i3 != -1) {
            return i3;
        }
        int childCount = getChildCount();
        int i4 = 0;
        int iA = 0;
        while (true) {
            if (i4 >= childCount) {
                break;
            }
            View childAt = getChildAt(i4);
            d dVar = (d) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int i5 = dVar.f1917a;
            if ((i5 & 1) == 0) {
                break;
            }
            iA += measuredHeight + ((LinearLayout.LayoutParams) dVar).topMargin + ((LinearLayout.LayoutParams) dVar).bottomMargin;
            if (i4 == 0 && t.w(childAt)) {
                iA -= getTopInset();
            }
            if ((i5 & 2) != 0) {
                iA -= t.A(childAt);
                break;
            }
            i4++;
        }
        int iMax = Math.max(0, iA);
        this.f1876d = iMax;
        return iMax;
    }

    public int getUpNestedPreScrollRange() {
        return getTotalScrollRange();
    }

    public boolean h() {
        return this.f1879g;
    }

    public final boolean i() {
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            if (((d) getChildAt(i3).getLayoutParams()).c()) {
                return true;
            }
        }
        return false;
    }

    public boolean j() {
        return getTotalScrollRange() != 0;
    }

    public final void k() {
        this.f1876d = -1;
        this.f1877e = -1;
        this.f1878f = -1;
    }

    public boolean l() {
        return this.f1886n;
    }

    public void m(int i3) {
        this.f1875c = i3;
        if (!willNotDraw()) {
            t.a0(this);
        }
        List<c> list = this.f1882j;
        if (list != null) {
            int size = list.size();
            for (int i4 = 0; i4 < size; i4++) {
                c cVar = this.f1882j.get(i4);
                if (cVar != null) {
                    cVar.a(this, i3);
                }
            }
        }
    }

    public b0 n(b0 b0Var) {
        b0 b0Var2 = t.w(this) ? b0Var : null;
        if (!k0.c.a(this.f1881i, b0Var2)) {
            this.f1881i = b0Var2;
            z();
            requestLayout();
        }
        return b0Var;
    }

    public void o(c cVar) {
        List<c> list = this.f1882j;
        if (list == null || cVar == null) {
            return;
        }
        list.remove(cVar);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        i.e(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int i3) {
        if (this.f1890r == null) {
            this.f1890r = new int[4];
        }
        int[] iArr = this.f1890r;
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i3 + iArr.length);
        boolean z3 = this.f1884l;
        int i4 = R$attr.state_liftable;
        if (!z3) {
            i4 = -i4;
        }
        iArr[0] = i4;
        iArr[1] = (z3 && this.f1885m) ? R$attr.state_lifted : -R$attr.state_lifted;
        int i5 = R$attr.state_collapsible;
        if (!z3) {
            i5 = -i5;
        }
        iArr[2] = i5;
        iArr[3] = (z3 && this.f1885m) ? R$attr.state_collapsed : -R$attr.state_collapsed;
        return LinearLayout.mergeDrawableStates(iArrOnCreateDrawableState, iArr);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z3, int i3, int i4, int i5, int i6) {
        super.onLayout(z3, i3, i4, i5, i6);
        boolean z4 = true;
        if (t.w(this) && x()) {
            int topInset = getTopInset();
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                t.V(getChildAt(childCount), topInset);
            }
        }
        k();
        this.f1879g = false;
        int childCount2 = getChildCount();
        int i7 = 0;
        while (true) {
            if (i7 >= childCount2) {
                break;
            }
            if (((d) getChildAt(i7).getLayoutParams()).b() != null) {
                this.f1879g = true;
                break;
            }
            i7++;
        }
        Drawable drawable = this.f1891s;
        if (drawable != null) {
            drawable.setBounds(0, 0, getWidth(), getTopInset());
        }
        if (this.f1883k) {
            return;
        }
        if (!this.f1886n && !i()) {
            z4 = false;
        }
        t(z4);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i3, int i4) {
        super.onMeasure(i3, i4);
        int mode = View.MeasureSpec.getMode(i4);
        if (mode != 1073741824 && t.w(this) && x()) {
            int measuredHeight = getMeasuredHeight();
            if (mode == Integer.MIN_VALUE) {
                measuredHeight = g0.a.b(getMeasuredHeight() + getTopInset(), 0, View.MeasureSpec.getSize(i4));
            } else if (mode == 0) {
                measuredHeight += getTopInset();
            }
            setMeasuredDimension(getMeasuredWidth(), measuredHeight);
        }
        k();
    }

    public void p(e eVar) {
        o(eVar);
    }

    public void q() {
        this.f1880h = 0;
    }

    public void r(boolean z3, boolean z4) {
        s(z3, z4, true);
    }

    public final void s(boolean z3, boolean z4, boolean z5) {
        this.f1880h = (z3 ? 1 : 2) | (z4 ? 4 : 0) | (z5 ? 8 : 0);
        requestLayout();
    }

    @Override // android.view.View
    public void setElevation(float f3) {
        super.setElevation(f3);
        i.d(this, f3);
    }

    public void setExpanded(boolean z3) {
        r(z3, t.P(this));
    }

    public void setLiftOnScroll(boolean z3) {
        this.f1886n = z3;
    }

    public void setLiftOnScrollTargetViewId(int i3) {
        this.f1887o = i3;
        c();
    }

    @Override // android.widget.LinearLayout
    public void setOrientation(int i3) {
        if (i3 != 1) {
            throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
        }
        super.setOrientation(i3);
    }

    public void setStatusBarForeground(Drawable drawable) {
        Drawable drawable2 = this.f1891s;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.f1891s = drawableMutate;
            if (drawableMutate != null) {
                if (drawableMutate.isStateful()) {
                    this.f1891s.setState(getDrawableState());
                }
                e0.a.m(this.f1891s, t.z(this));
                this.f1891s.setVisible(getVisibility() == 0, false);
                this.f1891s.setCallback(this);
            }
            z();
            t.a0(this);
        }
    }

    public void setStatusBarForegroundColor(int i3) {
        setStatusBarForeground(new ColorDrawable(i3));
    }

    public void setStatusBarForegroundResource(int i3) {
        setStatusBarForeground(j.a.d(getContext(), i3));
    }

    @Deprecated
    public void setTargetElevation(float f3) {
        if (Build.VERSION.SDK_INT >= 21) {
            t1.e.b(this, f3);
        }
    }

    @Override // android.view.View
    public void setVisibility(int i3) {
        super.setVisibility(i3);
        boolean z3 = i3 == 0;
        Drawable drawable = this.f1891s;
        if (drawable != null) {
            drawable.setVisible(z3, false);
        }
    }

    public final boolean t(boolean z3) {
        if (this.f1884l == z3) {
            return false;
        }
        this.f1884l = z3;
        refreshDrawableState();
        return true;
    }

    public boolean u(boolean z3) {
        if (this.f1885m == z3) {
            return false;
        }
        this.f1885m = z3;
        refreshDrawableState();
        if (!this.f1886n || !(getBackground() instanceof h)) {
            return true;
        }
        y((h) getBackground(), z3);
        return true;
    }

    public final boolean v() {
        return this.f1891s != null && getTopInset() > 0;
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f1891s;
    }

    public boolean w(View view) {
        View viewD = d(view);
        if (viewD != null) {
            view = viewD;
        }
        return view != null && (view.canScrollVertically(-1) || view.getScrollY() > 0);
    }

    public final boolean x() {
        if (getChildCount() <= 0) {
            return false;
        }
        View childAt = getChildAt(0);
        return (childAt.getVisibility() == 8 || t.w(childAt)) ? false : true;
    }

    public final void y(h hVar, boolean z3) {
        float dimension = getResources().getDimension(R$dimen.design_appbar_elevation);
        float f3 = z3 ? 0.0f : dimension;
        if (!z3) {
            dimension = 0.0f;
        }
        ValueAnimator valueAnimator = this.f1889q;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f3, dimension);
        this.f1889q = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(getResources().getInteger(R$integer.app_bar_elevation_anim_duration));
        this.f1889q.setInterpolator(s1.a.f4901a);
        this.f1889q.addUpdateListener(new b(hVar));
        this.f1889q.start();
    }

    public final void z() {
        setWillNotDraw(!v());
    }

    public static class BaseBehavior<T extends AppBarLayout> extends t1.a<T> {

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f1892k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public int f1893l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public ValueAnimator f1894m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public int f1895n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public boolean f1896o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public float f1897p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public WeakReference<View> f1898q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public d f1899r;

        public class a implements ValueAnimator.AnimatorUpdateListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ CoordinatorLayout f1903a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AppBarLayout f1904b;

            public a(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
                this.f1903a = coordinatorLayout;
                this.f1904b = appBarLayout;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                BaseBehavior.this.P(this.f1903a, this.f1904b, ((Integer) valueAnimator.getAnimatedValue()).intValue());
            }
        }

        public class b implements f {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ CoordinatorLayout f1906a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AppBarLayout f1907b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ View f1908c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ int f1909d;

            public b(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i3) {
                this.f1906a = coordinatorLayout;
                this.f1907b = appBarLayout;
                this.f1908c = view;
                this.f1909d = i3;
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
            @Override // m0.f
            public boolean a(View view, f.a aVar) {
                BaseBehavior.this.q(this.f1906a, this.f1907b, this.f1908c, 0, this.f1909d, new int[]{0, 0}, 1);
                return true;
            }
        }

        public class c implements f {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ AppBarLayout f1911a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ boolean f1912b;

            public c(AppBarLayout appBarLayout, boolean z3) {
                this.f1911a = appBarLayout;
                this.f1912b = z3;
            }

            @Override // m0.f
            public boolean a(View view, f.a aVar) {
                this.f1911a.setExpanded(this.f1912b);
                return true;
            }
        }

        public static abstract class d<T extends AppBarLayout> {
        }

        public BaseBehavior() {
            this.f1895n = -1;
        }

        public static boolean Y(int i3, int i4) {
            return (i3 & i4) == i4;
        }

        public static View a0(AppBarLayout appBarLayout, int i3) {
            int iAbs = Math.abs(i3);
            int childCount = appBarLayout.getChildCount();
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = appBarLayout.getChildAt(i4);
                if (iAbs >= childAt.getTop() && iAbs <= childAt.getBottom()) {
                    return childAt;
                }
            }
            return null;
        }

        @Override // t1.a
        public int M() {
            return E() + this.f1892k;
        }

        public final void S(CoordinatorLayout coordinatorLayout, T t3, View view) {
            if (M() != (-t3.getTotalScrollRange()) && view.canScrollVertically(1)) {
                T(coordinatorLayout, t3, c.a.f3814m, false);
            }
            if (M() != 0) {
                if (!view.canScrollVertically(-1)) {
                    T(coordinatorLayout, t3, c.a.f3815n, true);
                    return;
                }
                int i3 = -t3.getDownNestedPreScrollRange();
                if (i3 != 0) {
                    t.f0(coordinatorLayout, c.a.f3815n, null, new b(coordinatorLayout, t3, view, i3));
                }
            }
        }

        public final void T(CoordinatorLayout coordinatorLayout, T t3, c.a aVar, boolean z3) {
            t.f0(coordinatorLayout, aVar, null, new c(t3, z3));
        }

        public final void U(CoordinatorLayout coordinatorLayout, T t3, int i3, float f3) {
            int iAbs = Math.abs(M() - i3);
            float fAbs = Math.abs(f3);
            V(coordinatorLayout, t3, i3, fAbs > 0.0f ? Math.round((iAbs / fAbs) * 1000.0f) * 3 : (int) (((iAbs / t3.getHeight()) + 1.0f) * 150.0f));
        }

        public final void V(CoordinatorLayout coordinatorLayout, T t3, int i3, int i4) {
            int iM = M();
            if (iM == i3) {
                ValueAnimator valueAnimator = this.f1894m;
                if (valueAnimator == null || !valueAnimator.isRunning()) {
                    return;
                }
                this.f1894m.cancel();
                return;
            }
            ValueAnimator valueAnimator2 = this.f1894m;
            if (valueAnimator2 == null) {
                ValueAnimator valueAnimator3 = new ValueAnimator();
                this.f1894m = valueAnimator3;
                valueAnimator3.setInterpolator(s1.a.f4905e);
                this.f1894m.addUpdateListener(new a(coordinatorLayout, t3));
            } else {
                valueAnimator2.cancel();
            }
            this.f1894m.setDuration(Math.min(i4, 600));
            this.f1894m.setIntValues(iM, i3);
            this.f1894m.start();
        }

        @Override // t1.a
        /* JADX INFO: renamed from: W, reason: merged with bridge method [inline-methods] */
        public boolean H(T t3) {
            if (this.f1899r != null) {
                throw null;
            }
            WeakReference<View> weakReference = this.f1898q;
            if (weakReference == null) {
                return true;
            }
            View view = weakReference.get();
            return (view == null || !view.isShown() || view.canScrollVertically(-1)) ? false : true;
        }

        public final boolean X(CoordinatorLayout coordinatorLayout, T t3, View view) {
            return t3.j() && coordinatorLayout.getHeight() - view.getHeight() <= t3.getHeight();
        }

        public final View Z(CoordinatorLayout coordinatorLayout) {
            int childCount = coordinatorLayout.getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = coordinatorLayout.getChildAt(i3);
                if ((childAt instanceof j) || (childAt instanceof ListView) || (childAt instanceof ScrollView)) {
                    return childAt;
                }
            }
            return null;
        }

        public final int b0(T t3, int i3) {
            int childCount = t3.getChildCount();
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = t3.getChildAt(i4);
                int top = childAt.getTop();
                int bottom = childAt.getBottom();
                d dVar = (d) childAt.getLayoutParams();
                if (Y(dVar.a(), 32)) {
                    top -= ((LinearLayout.LayoutParams) dVar).topMargin;
                    bottom += ((LinearLayout.LayoutParams) dVar).bottomMargin;
                }
                int i5 = -i3;
                if (top <= i5 && bottom >= i5) {
                    return i4;
                }
            }
            return -1;
        }

        @Override // t1.a
        /* JADX INFO: renamed from: c0, reason: merged with bridge method [inline-methods] */
        public int K(T t3) {
            return -t3.getDownNestedScrollRange();
        }

        @Override // t1.a
        /* JADX INFO: renamed from: d0, reason: merged with bridge method [inline-methods] */
        public int L(T t3) {
            return t3.getTotalScrollRange();
        }

        public final int e0(T t3, int i3) {
            int iAbs = Math.abs(i3);
            int childCount = t3.getChildCount();
            int topInset = 0;
            int i4 = 0;
            while (true) {
                if (i4 >= childCount) {
                    break;
                }
                View childAt = t3.getChildAt(i4);
                d dVar = (d) childAt.getLayoutParams();
                Interpolator interpolatorB = dVar.b();
                if (iAbs < childAt.getTop() || iAbs > childAt.getBottom()) {
                    i4++;
                } else if (interpolatorB != null) {
                    int iA = dVar.a();
                    if ((iA & 1) != 0) {
                        topInset = 0 + childAt.getHeight() + ((LinearLayout.LayoutParams) dVar).topMargin + ((LinearLayout.LayoutParams) dVar).bottomMargin;
                        if ((iA & 2) != 0) {
                            topInset -= t.A(childAt);
                        }
                    }
                    if (t.w(childAt)) {
                        topInset -= t3.getTopInset();
                    }
                    if (topInset > 0) {
                        float f3 = topInset;
                        return Integer.signum(i3) * (childAt.getTop() + Math.round(f3 * interpolatorB.getInterpolation((iAbs - childAt.getTop()) / f3)));
                    }
                }
            }
            return i3;
        }

        @Override // t1.a
        /* JADX INFO: renamed from: f0, reason: merged with bridge method [inline-methods] */
        public void N(CoordinatorLayout coordinatorLayout, T t3) {
            q0(coordinatorLayout, t3);
            if (t3.l()) {
                t3.u(t3.w(Z(coordinatorLayout)));
            }
        }

        @Override // t1.c, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: g0, reason: merged with bridge method [inline-methods] */
        public boolean l(CoordinatorLayout coordinatorLayout, T t3, int i3) {
            boolean zL = super.l(coordinatorLayout, t3, i3);
            int pendingAction = t3.getPendingAction();
            int i4 = this.f1895n;
            if (i4 >= 0 && (pendingAction & 8) == 0) {
                View childAt = t3.getChildAt(i4);
                P(coordinatorLayout, t3, (-childAt.getBottom()) + (this.f1896o ? t.A(childAt) + t3.getTopInset() : Math.round(childAt.getHeight() * this.f1897p)));
            } else if (pendingAction != 0) {
                boolean z3 = (pendingAction & 4) != 0;
                if ((pendingAction & 2) != 0) {
                    int i5 = -t3.getUpNestedPreScrollRange();
                    if (z3) {
                        U(coordinatorLayout, t3, i5, 0.0f);
                    } else {
                        P(coordinatorLayout, t3, i5);
                    }
                } else if ((pendingAction & 1) != 0) {
                    if (z3) {
                        U(coordinatorLayout, t3, 0, 0.0f);
                    } else {
                        P(coordinatorLayout, t3, 0);
                    }
                }
            }
            t3.q();
            this.f1895n = -1;
            G(g0.a.b(E(), -t3.getTotalScrollRange(), 0));
            s0(coordinatorLayout, t3, E(), 0, true);
            t3.m(E());
            r0(coordinatorLayout, t3);
            return zL;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: h0, reason: merged with bridge method [inline-methods] */
        public boolean m(CoordinatorLayout coordinatorLayout, T t3, int i3, int i4, int i5, int i6) {
            if (((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.f) t3.getLayoutParams())).height != -2) {
                return super.m(coordinatorLayout, t3, i3, i4, i5, i6);
            }
            coordinatorLayout.N(t3, i3, i4, View.MeasureSpec.makeMeasureSpec(0, 0), i6);
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: i0, reason: merged with bridge method [inline-methods] */
        public void q(CoordinatorLayout coordinatorLayout, T t3, View view, int i3, int i4, int[] iArr, int i5) {
            int i6;
            int downNestedPreScrollRange;
            if (i4 != 0) {
                if (i4 < 0) {
                    i6 = -t3.getTotalScrollRange();
                    downNestedPreScrollRange = t3.getDownNestedPreScrollRange() + i6;
                } else {
                    i6 = -t3.getUpNestedPreScrollRange();
                    downNestedPreScrollRange = 0;
                }
                int i7 = i6;
                int i8 = downNestedPreScrollRange;
                if (i7 != i8) {
                    iArr[1] = O(coordinatorLayout, t3, i4, i7, i8);
                }
            }
            if (t3.l()) {
                t3.u(t3.w(view));
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: j0, reason: merged with bridge method [inline-methods] */
        public void t(CoordinatorLayout coordinatorLayout, T t3, View view, int i3, int i4, int i5, int i6, int i7, int[] iArr) {
            if (i6 < 0) {
                iArr[1] = O(coordinatorLayout, t3, i6, -t3.getDownNestedScrollRange(), 0);
            }
            if (i6 == 0) {
                r0(coordinatorLayout, t3);
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: k0, reason: merged with bridge method [inline-methods] */
        public void x(CoordinatorLayout coordinatorLayout, T t3, Parcelable parcelable) {
            if (!(parcelable instanceof SavedState)) {
                super.x(coordinatorLayout, t3, parcelable);
                this.f1895n = -1;
                return;
            }
            SavedState savedState = (SavedState) parcelable;
            super.x(coordinatorLayout, t3, savedState.f());
            this.f1895n = savedState.f1900d;
            this.f1897p = savedState.f1901e;
            this.f1896o = savedState.f1902f;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: l0, reason: merged with bridge method [inline-methods] */
        public Parcelable y(CoordinatorLayout coordinatorLayout, T t3) {
            Parcelable parcelableY = super.y(coordinatorLayout, t3);
            int iE = E();
            int childCount = t3.getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = t3.getChildAt(i3);
                int bottom = childAt.getBottom() + iE;
                if (childAt.getTop() + iE <= 0 && bottom >= 0) {
                    SavedState savedState = new SavedState(parcelableY);
                    savedState.f1900d = i3;
                    savedState.f1902f = bottom == t.A(childAt) + t3.getTopInset();
                    savedState.f1901e = bottom / childAt.getHeight();
                    return savedState;
                }
            }
            return parcelableY;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: m0, reason: merged with bridge method [inline-methods] */
        public boolean A(CoordinatorLayout coordinatorLayout, T t3, View view, View view2, int i3, int i4) {
            ValueAnimator valueAnimator;
            boolean z3 = (i3 & 2) != 0 && (t3.l() || X(coordinatorLayout, t3, view));
            if (z3 && (valueAnimator = this.f1894m) != null) {
                valueAnimator.cancel();
            }
            this.f1898q = null;
            this.f1893l = i4;
            return z3;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: n0, reason: merged with bridge method [inline-methods] */
        public void C(CoordinatorLayout coordinatorLayout, T t3, View view, int i3) {
            if (this.f1893l == 0 || i3 == 1) {
                q0(coordinatorLayout, t3);
                if (t3.l()) {
                    t3.u(t3.w(view));
                }
            }
            this.f1898q = new WeakReference<>(view);
        }

        @Override // t1.a
        /* JADX INFO: renamed from: o0, reason: merged with bridge method [inline-methods] */
        public int Q(CoordinatorLayout coordinatorLayout, T t3, int i3, int i4, int i5) {
            int iM = M();
            int i6 = 0;
            if (i4 == 0 || iM < i4 || iM > i5) {
                this.f1892k = 0;
            } else {
                int iB = g0.a.b(i3, i4, i5);
                if (iM != iB) {
                    int iE0 = t3.h() ? e0(t3, iB) : iB;
                    boolean zG = G(iE0);
                    i6 = iM - iB;
                    this.f1892k = iB - iE0;
                    if (!zG && t3.h()) {
                        coordinatorLayout.p(t3);
                    }
                    t3.m(E());
                    s0(coordinatorLayout, t3, iB, iB < iM ? -1 : 1, false);
                }
            }
            r0(coordinatorLayout, t3);
            return i6;
        }

        public final boolean p0(CoordinatorLayout coordinatorLayout, T t3) {
            List<View> listW = coordinatorLayout.w(t3);
            int size = listW.size();
            for (int i3 = 0; i3 < size; i3++) {
                CoordinatorLayout.c cVarF = ((CoordinatorLayout.f) listW.get(i3).getLayoutParams()).f();
                if (cVarF instanceof ScrollingViewBehavior) {
                    return ((ScrollingViewBehavior) cVarF).K() != 0;
                }
            }
            return false;
        }

        public final void q0(CoordinatorLayout coordinatorLayout, T t3) {
            int iM = M();
            int iB0 = b0(t3, iM);
            if (iB0 >= 0) {
                View childAt = t3.getChildAt(iB0);
                d dVar = (d) childAt.getLayoutParams();
                int iA = dVar.a();
                if ((iA & 17) == 17) {
                    int i3 = -childAt.getTop();
                    int iA2 = -childAt.getBottom();
                    if (iB0 == t3.getChildCount() - 1) {
                        iA2 += t3.getTopInset();
                    }
                    if (Y(iA, 2)) {
                        iA2 += t.A(childAt);
                    } else if (Y(iA, 5)) {
                        int iA3 = t.A(childAt) + iA2;
                        if (iM < iA3) {
                            i3 = iA3;
                        } else {
                            iA2 = iA3;
                        }
                    }
                    if (Y(iA, 32)) {
                        i3 += ((LinearLayout.LayoutParams) dVar).topMargin;
                        iA2 -= ((LinearLayout.LayoutParams) dVar).bottomMargin;
                    }
                    if (iM < (iA2 + i3) / 2) {
                        i3 = iA2;
                    }
                    U(coordinatorLayout, t3, g0.a.b(i3, -t3.getTotalScrollRange(), 0), 0.0f);
                }
            }
        }

        public final void r0(CoordinatorLayout coordinatorLayout, T t3) {
            t.d0(coordinatorLayout, c.a.f3814m.b());
            t.d0(coordinatorLayout, c.a.f3815n.b());
            View viewZ = Z(coordinatorLayout);
            if (viewZ == null || t3.getTotalScrollRange() == 0 || !(((CoordinatorLayout.f) viewZ.getLayoutParams()).f() instanceof ScrollingViewBehavior)) {
                return;
            }
            S(coordinatorLayout, t3, viewZ);
        }

        public final void s0(CoordinatorLayout coordinatorLayout, T t3, int i3, int i4, boolean z3) {
            View viewA0 = a0(t3, i3);
            if (viewA0 != null) {
                int iA = ((d) viewA0.getLayoutParams()).a();
                boolean zW = false;
                if ((iA & 1) != 0) {
                    int iA2 = t.A(viewA0);
                    if (i4 <= 0 || (iA & 12) == 0 ? !((iA & 2) == 0 || (-i3) < (viewA0.getBottom() - iA2) - t3.getTopInset()) : (-i3) >= (viewA0.getBottom() - iA2) - t3.getTopInset()) {
                        zW = true;
                    }
                }
                if (t3.l()) {
                    zW = t3.w(Z(coordinatorLayout));
                }
                boolean zU = t3.u(zW);
                if (z3 || (zU && p0(coordinatorLayout, t3))) {
                    t3.jumpDrawablesToCurrentState();
                }
            }
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1895n = -1;
        }

        public static class SavedState extends AbsSavedState {
            public static final Parcelable.Creator<SavedState> CREATOR = new a();

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public int f1900d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public float f1901e;

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            public boolean f1902f;

            public static class a implements Parcelable.ClassLoaderCreator<SavedState> {
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
                public SavedState[] newArray(int i3) {
                    return new SavedState[i3];
                }
            }

            public SavedState(Parcel parcel, ClassLoader classLoader) {
                super(parcel, classLoader);
                this.f1900d = parcel.readInt();
                this.f1901e = parcel.readFloat();
                this.f1902f = parcel.readByte() != 0;
            }

            @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i3) {
                super.writeToParcel(parcel, i3);
                parcel.writeInt(this.f1900d);
                parcel.writeFloat(this.f1901e);
                parcel.writeByte(this.f1902f ? (byte) 1 : (byte) 0);
            }

            public SavedState(Parcelable parcelable) {
                super(parcelable);
            }
        }
    }

    public AppBarLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.appBarLayoutStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public AppBarLayout(Context context, AttributeSet attributeSet, int i3) {
        int i4 = f1874b;
        super(r2.a.c(context, attributeSet, i3, i4), attributeSet, i3);
        this.f1876d = -1;
        this.f1877e = -1;
        this.f1878f = -1;
        this.f1880h = 0;
        Context context2 = getContext();
        setOrientation(1);
        int i5 = Build.VERSION.SDK_INT;
        if (i5 >= 21) {
            t1.e.a(this);
            t1.e.c(this, attributeSet, i3, i4);
        }
        TypedArray typedArrayH = k.h(context2, attributeSet, R$styleable.AppBarLayout, i3, i4, new int[0]);
        t.m0(this, typedArrayH.getDrawable(R$styleable.AppBarLayout_android_background));
        if (getBackground() instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) getBackground();
            h hVar = new h();
            hVar.X(ColorStateList.valueOf(colorDrawable.getColor()));
            hVar.N(context2);
            t.m0(this, hVar);
        }
        int i6 = R$styleable.AppBarLayout_expanded;
        if (typedArrayH.hasValue(i6)) {
            s(typedArrayH.getBoolean(i6, false), false, false);
        }
        if (i5 >= 21) {
            if (typedArrayH.hasValue(R$styleable.AppBarLayout_elevation)) {
                t1.e.b(this, typedArrayH.getDimensionPixelSize(r13, 0));
            }
        }
        if (i5 >= 26) {
            int i7 = R$styleable.AppBarLayout_android_keyboardNavigationCluster;
            if (typedArrayH.hasValue(i7)) {
                setKeyboardNavigationCluster(typedArrayH.getBoolean(i7, false));
            }
            int i8 = R$styleable.AppBarLayout_android_touchscreenBlocksFocus;
            if (typedArrayH.hasValue(i8)) {
                setTouchscreenBlocksFocus(typedArrayH.getBoolean(i8, false));
            }
        }
        this.f1886n = typedArrayH.getBoolean(R$styleable.AppBarLayout_liftOnScroll, false);
        this.f1887o = typedArrayH.getResourceId(R$styleable.AppBarLayout_liftOnScrollTargetViewId, -1);
        setStatusBarForeground(typedArrayH.getDrawable(R$styleable.AppBarLayout_statusBarForeground));
        typedArrayH.recycle();
        t.v0(this, new a());
    }

    public static class d extends LinearLayout.LayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1917a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Interpolator f1918b;

        public d(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1917a = 1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.AppBarLayout_Layout);
            this.f1917a = typedArrayObtainStyledAttributes.getInt(R$styleable.AppBarLayout_Layout_layout_scrollFlags, 0);
            int i3 = R$styleable.AppBarLayout_Layout_layout_scrollInterpolator;
            if (typedArrayObtainStyledAttributes.hasValue(i3)) {
                this.f1918b = AnimationUtils.loadInterpolator(context, typedArrayObtainStyledAttributes.getResourceId(i3, 0));
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public int a() {
            return this.f1917a;
        }

        public Interpolator b() {
            return this.f1918b;
        }

        public boolean c() {
            int i3 = this.f1917a;
            return (i3 & 1) == 1 && (i3 & 10) != 0;
        }

        public d(int i3, int i4) {
            super(i3, i4);
            this.f1917a = 1;
        }

        public d(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1917a = 1;
        }

        public d(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1917a = 1;
        }

        public d(LinearLayout.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1917a = 1;
        }
    }
}
