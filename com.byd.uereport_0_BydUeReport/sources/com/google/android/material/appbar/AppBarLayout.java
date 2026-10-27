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
import e6.h;
import e6.i;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import o0.b0;
import o0.j;
import o0.p;
import o0.t;
import p0.c;
import p0.f;
import z5.k;

/* JADX INFO: loaded from: classes2.dex */
public class AppBarLayout extends LinearLayout implements CoordinatorLayout.b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f3458d = R$style.Widget_Design_AppBarLayout;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3459e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3460f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f3461g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f3462h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f3463i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f3464j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public b0 f3465k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public List<c> f3466l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f3467m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f3468n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f3469o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f3470p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f3471q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public WeakReference<View> f3472r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public ValueAnimator f3473s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int[] f3474t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Drawable f3475u;

    public static class Behavior extends BaseBehavior<AppBarLayout> {
        public Behavior() {
        }

        @Override // k5.c
        public /* bridge */ /* synthetic */ int E() {
            return super.E();
        }

        @Override // k5.c
        public /* bridge */ /* synthetic */ boolean G(int i8) {
            return super.G(i8);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* JADX INFO: renamed from: g0 */
        public /* bridge */ /* synthetic */ boolean l(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i8) {
            return super.l(coordinatorLayout, appBarLayout, i8);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* JADX INFO: renamed from: h0 */
        public /* bridge */ /* synthetic */ boolean m(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i8, int i9, int i10, int i11) {
            return super.m(coordinatorLayout, appBarLayout, i8, i9, i10, i11);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* JADX INFO: renamed from: i0 */
        public /* bridge */ /* synthetic */ void q(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i8, int i9, int[] iArr, int i10) {
            super.q(coordinatorLayout, appBarLayout, view, i8, i9, iArr, i10);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* JADX INFO: renamed from: j0 */
        public /* bridge */ /* synthetic */ void t(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i8, int i9, int i10, int i11, int i12, int[] iArr) {
            super.t(coordinatorLayout, appBarLayout, view, i8, i9, i10, i11, i12, iArr);
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
        public /* bridge */ /* synthetic */ boolean A(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, View view2, int i8, int i9) {
            return super.A(coordinatorLayout, appBarLayout, view, view2, i8, i9);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        /* JADX INFO: renamed from: n0 */
        public /* bridge */ /* synthetic */ void C(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i8) {
            super.C(coordinatorLayout, appBarLayout, view, i8);
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public static class ScrollingViewBehavior extends k5.b {
        public ScrollingViewBehavior() {
        }

        public static int R(AppBarLayout appBarLayout) {
            CoordinatorLayout.c cVarF = ((CoordinatorLayout.f) appBarLayout.getLayoutParams()).f();
            if (cVarF instanceof BaseBehavior) {
                return ((BaseBehavior) cVarF).M();
            }
            return 0;
        }

        @Override // k5.b
        public float J(View view) {
            int i8;
            if (view instanceof AppBarLayout) {
                AppBarLayout appBarLayout = (AppBarLayout) view;
                int totalScrollRange = appBarLayout.getTotalScrollRange();
                int downNestedPreScrollRange = appBarLayout.getDownNestedPreScrollRange();
                int iR = R(appBarLayout);
                if ((downNestedPreScrollRange == 0 || totalScrollRange + iR > downNestedPreScrollRange) && (i8 = totalScrollRange - downNestedPreScrollRange) != 0) {
                    return (iR / i8) + 1.0f;
                }
            }
            return 0.0f;
        }

        @Override // k5.b
        public int L(View view) {
            return view instanceof AppBarLayout ? ((AppBarLayout) view).getTotalScrollRange() : super.L(view);
        }

        @Override // k5.b
        /* JADX INFO: renamed from: Q, reason: merged with bridge method [inline-methods] */
        public AppBarLayout H(List<View> list) {
            int size = list.size();
            for (int i8 = 0; i8 < size; i8++) {
                View view = list.get(i8);
                if (view instanceof AppBarLayout) {
                    return (AppBarLayout) view;
                }
            }
            return null;
        }

        public final void S(View view, View view2) {
            CoordinatorLayout.c cVarF = ((CoordinatorLayout.f) view2.getLayoutParams()).f();
            if (cVarF instanceof BaseBehavior) {
                t.W(view, (((view2.getBottom() - view.getTop()) + ((BaseBehavior) cVarF).f3476k) + M()) - I(view2));
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
                t.f0(coordinatorLayout, c.a.f6980m.b());
                t.f0(coordinatorLayout, c.a.f6981n.b());
            }
        }

        @Override // k5.c, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public /* bridge */ /* synthetic */ boolean l(CoordinatorLayout coordinatorLayout, View view, int i8) {
            return super.l(coordinatorLayout, view, i8);
        }

        @Override // k5.b, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public /* bridge */ /* synthetic */ boolean m(CoordinatorLayout coordinatorLayout, View view, int i8, int i9, int i10, int i11) {
            return super.m(coordinatorLayout, view, i8, i9, i10, i11);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public boolean w(CoordinatorLayout coordinatorLayout, View view, Rect rect, boolean z7) {
            AppBarLayout appBarLayoutH = H(coordinatorLayout.v(view));
            if (appBarLayoutH != null) {
                rect.offset(view.getLeft(), view.getTop());
                Rect rect2 = this.f5887d;
                rect2.set(0, 0, coordinatorLayout.getWidth(), coordinatorLayout.getHeight());
                if (!rect2.contains(rect)) {
                    appBarLayoutH.r(false, !z7);
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

        @Override // o0.p
        public b0 a(View view, b0 b0Var) {
            return AppBarLayout.this.n(b0Var);
        }
    }

    public class b implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ h f3499d;

        public b(h hVar) {
            this.f3499d = hVar;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f3499d.W(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    public interface c<T extends AppBarLayout> {
        void a(T t7, int i8);
    }

    public interface e extends c<AppBarLayout> {
    }

    public AppBarLayout(Context context) {
        this(context, null);
    }

    public void a(c cVar) {
        if (this.f3466l == null) {
            this.f3466l = new ArrayList();
        }
        if (cVar == null || this.f3466l.contains(cVar)) {
            return;
        }
        this.f3466l.add(cVar);
    }

    public void b(e eVar) {
        a(eVar);
    }

    public final void c() {
        WeakReference<View> weakReference = this.f3472r;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.f3472r = null;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof d;
    }

    public final View d(View view) {
        int i8;
        if (this.f3472r == null && (i8 = this.f3471q) != -1) {
            View viewFindViewById = view != null ? view.findViewById(i8) : null;
            if (viewFindViewById == null && (getParent() instanceof ViewGroup)) {
                viewFindViewById = ((ViewGroup) getParent()).findViewById(this.f3471q);
            }
            if (viewFindViewById != null) {
                this.f3472r = new WeakReference<>(viewFindViewById);
            }
        }
        WeakReference<View> weakReference = this.f3472r;
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
            canvas.translate(0.0f, -this.f3459e);
            this.f3475u.draw(canvas);
            canvas.restoreToCount(iSave);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.f3475u;
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
        int iB;
        int i8 = this.f3461g;
        if (i8 != -1) {
            return i8;
        }
        int i9 = 0;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            d dVar = (d) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int i10 = dVar.f3501a;
            if ((i10 & 5) != 5) {
                if (i9 > 0) {
                    break;
                }
            } else {
                int i11 = ((LinearLayout.LayoutParams) dVar).topMargin + ((LinearLayout.LayoutParams) dVar).bottomMargin;
                if ((i10 & 8) != 0) {
                    iB = t.B(childAt);
                } else if ((i10 & 2) != 0) {
                    iB = measuredHeight - t.B(childAt);
                } else {
                    iMin = i11 + measuredHeight;
                    if (childCount == 0 && t.x(childAt)) {
                        iMin = Math.min(iMin, measuredHeight - getTopInset());
                    }
                    i9 += iMin;
                }
                iMin = i11 + iB;
                if (childCount == 0) {
                    iMin = Math.min(iMin, measuredHeight - getTopInset());
                }
                i9 += iMin;
            }
        }
        int iMax = Math.max(0, i9);
        this.f3461g = iMax;
        return iMax;
    }

    public int getDownNestedScrollRange() {
        int i8 = this.f3462h;
        if (i8 != -1) {
            return i8;
        }
        int childCount = getChildCount();
        int i9 = 0;
        int iB = 0;
        while (true) {
            if (i9 >= childCount) {
                break;
            }
            View childAt = getChildAt(i9);
            d dVar = (d) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight() + ((LinearLayout.LayoutParams) dVar).topMargin + ((LinearLayout.LayoutParams) dVar).bottomMargin;
            int i10 = dVar.f3501a;
            if ((i10 & 1) == 0) {
                break;
            }
            iB += measuredHeight;
            if ((i10 & 2) != 0) {
                iB -= t.B(childAt);
                break;
            }
            i9++;
        }
        int iMax = Math.max(0, iB);
        this.f3462h = iMax;
        return iMax;
    }

    public int getLiftOnScrollTargetViewId() {
        return this.f3471q;
    }

    public final int getMinimumHeightForVisibleOverlappingContent() {
        int topInset = getTopInset();
        int iB = t.B(this);
        if (iB == 0) {
            int childCount = getChildCount();
            iB = childCount >= 1 ? t.B(getChildAt(childCount - 1)) : 0;
            if (iB == 0) {
                return getHeight() / 3;
            }
        }
        return (iB * 2) + topInset;
    }

    public int getPendingAction() {
        return this.f3464j;
    }

    public Drawable getStatusBarForeground() {
        return this.f3475u;
    }

    @Deprecated
    public float getTargetElevation() {
        return 0.0f;
    }

    public final int getTopInset() {
        b0 b0Var = this.f3465k;
        if (b0Var != null) {
            return b0Var.h();
        }
        return 0;
    }

    public final int getTotalScrollRange() {
        int i8 = this.f3460f;
        if (i8 != -1) {
            return i8;
        }
        int childCount = getChildCount();
        int i9 = 0;
        int iB = 0;
        while (true) {
            if (i9 >= childCount) {
                break;
            }
            View childAt = getChildAt(i9);
            d dVar = (d) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int i10 = dVar.f3501a;
            if ((i10 & 1) == 0) {
                break;
            }
            iB += measuredHeight + ((LinearLayout.LayoutParams) dVar).topMargin + ((LinearLayout.LayoutParams) dVar).bottomMargin;
            if (i9 == 0 && t.x(childAt)) {
                iB -= getTopInset();
            }
            if ((i10 & 2) != 0) {
                iB -= t.B(childAt);
                break;
            }
            i9++;
        }
        int iMax = Math.max(0, iB);
        this.f3460f = iMax;
        return iMax;
    }

    public int getUpNestedPreScrollRange() {
        return getTotalScrollRange();
    }

    public boolean h() {
        return this.f3463i;
    }

    public final boolean i() {
        int childCount = getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            if (((d) getChildAt(i8).getLayoutParams()).c()) {
                return true;
            }
        }
        return false;
    }

    public boolean j() {
        return getTotalScrollRange() != 0;
    }

    public final void k() {
        this.f3460f = -1;
        this.f3461g = -1;
        this.f3462h = -1;
    }

    public boolean l() {
        return this.f3470p;
    }

    public void m(int i8) {
        this.f3459e = i8;
        if (!willNotDraw()) {
            t.b0(this);
        }
        List<c> list = this.f3466l;
        if (list != null) {
            int size = list.size();
            for (int i9 = 0; i9 < size; i9++) {
                c cVar = this.f3466l.get(i9);
                if (cVar != null) {
                    cVar.a(this, i8);
                }
            }
        }
    }

    public b0 n(b0 b0Var) {
        b0 b0Var2 = t.x(this) ? b0Var : null;
        if (!n0.c.a(this.f3465k, b0Var2)) {
            this.f3465k = b0Var2;
            z();
            requestLayout();
        }
        return b0Var;
    }

    public void o(c cVar) {
        List<c> list = this.f3466l;
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
    public int[] onCreateDrawableState(int i8) {
        if (this.f3474t == null) {
            this.f3474t = new int[4];
        }
        int[] iArr = this.f3474t;
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i8 + iArr.length);
        boolean z7 = this.f3468n;
        int i9 = R$attr.state_liftable;
        if (!z7) {
            i9 = -i9;
        }
        iArr[0] = i9;
        iArr[1] = (z7 && this.f3469o) ? R$attr.state_lifted : -R$attr.state_lifted;
        int i10 = R$attr.state_collapsible;
        if (!z7) {
            i10 = -i10;
        }
        iArr[2] = i10;
        iArr[3] = (z7 && this.f3469o) ? R$attr.state_collapsed : -R$attr.state_collapsed;
        return LinearLayout.mergeDrawableStates(iArrOnCreateDrawableState, iArr);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        super.onLayout(z7, i8, i9, i10, i11);
        boolean z8 = true;
        if (t.x(this) && x()) {
            int topInset = getTopInset();
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                t.W(getChildAt(childCount), topInset);
            }
        }
        k();
        this.f3463i = false;
        int childCount2 = getChildCount();
        int i12 = 0;
        while (true) {
            if (i12 >= childCount2) {
                break;
            }
            if (((d) getChildAt(i12).getLayoutParams()).b() != null) {
                this.f3463i = true;
                break;
            }
            i12++;
        }
        Drawable drawable = this.f3475u;
        if (drawable != null) {
            drawable.setBounds(0, 0, getWidth(), getTopInset());
        }
        if (this.f3467m) {
            return;
        }
        if (!this.f3470p && !i()) {
            z8 = false;
        }
        t(z8);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i8, int i9) {
        super.onMeasure(i8, i9);
        int mode = View.MeasureSpec.getMode(i9);
        if (mode != 1073741824 && t.x(this) && x()) {
            int measuredHeight = getMeasuredHeight();
            if (mode == Integer.MIN_VALUE) {
                measuredHeight = j0.a.b(getMeasuredHeight() + getTopInset(), 0, View.MeasureSpec.getSize(i9));
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
        this.f3464j = 0;
    }

    public void r(boolean z7, boolean z8) {
        s(z7, z8, true);
    }

    public final void s(boolean z7, boolean z8, boolean z9) {
        this.f3464j = (z7 ? 1 : 2) | (z8 ? 4 : 0) | (z9 ? 8 : 0);
        requestLayout();
    }

    @Override // android.view.View
    public void setElevation(float f8) {
        super.setElevation(f8);
        i.d(this, f8);
    }

    public void setExpanded(boolean z7) {
        r(z7, t.Q(this));
    }

    public void setLiftOnScroll(boolean z7) {
        this.f3470p = z7;
    }

    public void setLiftOnScrollTargetViewId(int i8) {
        this.f3471q = i8;
        c();
    }

    @Override // android.widget.LinearLayout
    public void setOrientation(int i8) {
        if (i8 != 1) {
            throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
        }
        super.setOrientation(i8);
    }

    public void setStatusBarForeground(Drawable drawable) {
        Drawable drawable2 = this.f3475u;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.f3475u = drawableMutate;
            if (drawableMutate != null) {
                if (drawableMutate.isStateful()) {
                    this.f3475u.setState(getDrawableState());
                }
                h0.a.m(this.f3475u, t.A(this));
                this.f3475u.setVisible(getVisibility() == 0, false);
                this.f3475u.setCallback(this);
            }
            z();
            t.b0(this);
        }
    }

    public void setStatusBarForegroundColor(int i8) {
        setStatusBarForeground(new ColorDrawable(i8));
    }

    public void setStatusBarForegroundResource(int i8) {
        setStatusBarForeground(m.a.d(getContext(), i8));
    }

    @Deprecated
    public void setTargetElevation(float f8) {
        if (Build.VERSION.SDK_INT >= 21) {
            k5.e.b(this, f8);
        }
    }

    @Override // android.view.View
    public void setVisibility(int i8) {
        super.setVisibility(i8);
        boolean z7 = i8 == 0;
        Drawable drawable = this.f3475u;
        if (drawable != null) {
            drawable.setVisible(z7, false);
        }
    }

    public final boolean t(boolean z7) {
        if (this.f3468n == z7) {
            return false;
        }
        this.f3468n = z7;
        refreshDrawableState();
        return true;
    }

    public boolean u(boolean z7) {
        if (this.f3469o == z7) {
            return false;
        }
        this.f3469o = z7;
        refreshDrawableState();
        if (!this.f3470p || !(getBackground() instanceof h)) {
            return true;
        }
        y((h) getBackground(), z7);
        return true;
    }

    public final boolean v() {
        return this.f3475u != null && getTopInset() > 0;
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f3475u;
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
        return (childAt.getVisibility() == 8 || t.x(childAt)) ? false : true;
    }

    public final void y(h hVar, boolean z7) {
        float dimension = getResources().getDimension(R$dimen.design_appbar_elevation);
        float f8 = z7 ? 0.0f : dimension;
        if (!z7) {
            dimension = 0.0f;
        }
        ValueAnimator valueAnimator = this.f3473s;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f8, dimension);
        this.f3473s = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(getResources().getInteger(R$integer.app_bar_elevation_anim_duration));
        this.f3473s.setInterpolator(j5.a.f5578a);
        this.f3473s.addUpdateListener(new b(hVar));
        this.f3473s.start();
    }

    public final void z() {
        setWillNotDraw(!v());
    }

    public static class BaseBehavior<T extends AppBarLayout> extends k5.a<T> {

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f3476k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public int f3477l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public ValueAnimator f3478m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public int f3479n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public boolean f3480o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public float f3481p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public WeakReference<View> f3482q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public d f3483r;

        public class a implements ValueAnimator.AnimatorUpdateListener {

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ CoordinatorLayout f3487d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ AppBarLayout f3488e;

            public a(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
                this.f3487d = coordinatorLayout;
                this.f3488e = appBarLayout;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                BaseBehavior.this.P(this.f3487d, this.f3488e, ((Integer) valueAnimator.getAnimatedValue()).intValue());
            }
        }

        public class b implements f {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ CoordinatorLayout f3490a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AppBarLayout f3491b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ View f3492c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ int f3493d;

            public b(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i8) {
                this.f3490a = coordinatorLayout;
                this.f3491b = appBarLayout;
                this.f3492c = view;
                this.f3493d = i8;
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
            @Override // p0.f
            public boolean perform(View view, f.a aVar) {
                BaseBehavior.this.q(this.f3490a, this.f3491b, this.f3492c, 0, this.f3493d, new int[]{0, 0}, 1);
                return true;
            }
        }

        public class c implements f {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ AppBarLayout f3495a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ boolean f3496b;

            public c(AppBarLayout appBarLayout, boolean z7) {
                this.f3495a = appBarLayout;
                this.f3496b = z7;
            }

            @Override // p0.f
            public boolean perform(View view, f.a aVar) {
                this.f3495a.setExpanded(this.f3496b);
                return true;
            }
        }

        public static abstract class d<T extends AppBarLayout> {
        }

        public BaseBehavior() {
            this.f3479n = -1;
        }

        public static boolean Y(int i8, int i9) {
            return (i8 & i9) == i9;
        }

        public static View a0(AppBarLayout appBarLayout, int i8) {
            int iAbs = Math.abs(i8);
            int childCount = appBarLayout.getChildCount();
            for (int i9 = 0; i9 < childCount; i9++) {
                View childAt = appBarLayout.getChildAt(i9);
                if (iAbs >= childAt.getTop() && iAbs <= childAt.getBottom()) {
                    return childAt;
                }
            }
            return null;
        }

        @Override // k5.a
        public int M() {
            return E() + this.f3476k;
        }

        public final void S(CoordinatorLayout coordinatorLayout, T t7, View view) {
            if (M() != (-t7.getTotalScrollRange()) && view.canScrollVertically(1)) {
                T(coordinatorLayout, t7, c.a.f6980m, false);
            }
            if (M() != 0) {
                if (!view.canScrollVertically(-1)) {
                    T(coordinatorLayout, t7, c.a.f6981n, true);
                    return;
                }
                int i8 = -t7.getDownNestedPreScrollRange();
                if (i8 != 0) {
                    t.h0(coordinatorLayout, c.a.f6981n, null, new b(coordinatorLayout, t7, view, i8));
                }
            }
        }

        public final void T(CoordinatorLayout coordinatorLayout, T t7, c.a aVar, boolean z7) {
            t.h0(coordinatorLayout, aVar, null, new c(t7, z7));
        }

        public final void U(CoordinatorLayout coordinatorLayout, T t7, int i8, float f8) {
            int iAbs = Math.abs(M() - i8);
            float fAbs = Math.abs(f8);
            V(coordinatorLayout, t7, i8, fAbs > 0.0f ? Math.round((iAbs / fAbs) * 1000.0f) * 3 : (int) (((iAbs / t7.getHeight()) + 1.0f) * 150.0f));
        }

        public final void V(CoordinatorLayout coordinatorLayout, T t7, int i8, int i9) {
            int iM = M();
            if (iM == i8) {
                ValueAnimator valueAnimator = this.f3478m;
                if (valueAnimator == null || !valueAnimator.isRunning()) {
                    return;
                }
                this.f3478m.cancel();
                return;
            }
            ValueAnimator valueAnimator2 = this.f3478m;
            if (valueAnimator2 == null) {
                ValueAnimator valueAnimator3 = new ValueAnimator();
                this.f3478m = valueAnimator3;
                valueAnimator3.setInterpolator(j5.a.f5582e);
                this.f3478m.addUpdateListener(new a(coordinatorLayout, t7));
            } else {
                valueAnimator2.cancel();
            }
            this.f3478m.setDuration(Math.min(i9, 600));
            this.f3478m.setIntValues(iM, i8);
            this.f3478m.start();
        }

        @Override // k5.a
        /* JADX INFO: renamed from: W, reason: merged with bridge method [inline-methods] */
        public boolean H(T t7) {
            if (this.f3483r != null) {
                throw null;
            }
            WeakReference<View> weakReference = this.f3482q;
            if (weakReference == null) {
                return true;
            }
            View view = weakReference.get();
            return (view == null || !view.isShown() || view.canScrollVertically(-1)) ? false : true;
        }

        public final boolean X(CoordinatorLayout coordinatorLayout, T t7, View view) {
            return t7.j() && coordinatorLayout.getHeight() - view.getHeight() <= t7.getHeight();
        }

        public final View Z(CoordinatorLayout coordinatorLayout) {
            int childCount = coordinatorLayout.getChildCount();
            for (int i8 = 0; i8 < childCount; i8++) {
                View childAt = coordinatorLayout.getChildAt(i8);
                if ((childAt instanceof j) || (childAt instanceof ListView) || (childAt instanceof ScrollView)) {
                    return childAt;
                }
            }
            return null;
        }

        public final int b0(T t7, int i8) {
            int childCount = t7.getChildCount();
            for (int i9 = 0; i9 < childCount; i9++) {
                View childAt = t7.getChildAt(i9);
                int top = childAt.getTop();
                int bottom = childAt.getBottom();
                d dVar = (d) childAt.getLayoutParams();
                if (Y(dVar.a(), 32)) {
                    top -= ((LinearLayout.LayoutParams) dVar).topMargin;
                    bottom += ((LinearLayout.LayoutParams) dVar).bottomMargin;
                }
                int i10 = -i8;
                if (top <= i10 && bottom >= i10) {
                    return i9;
                }
            }
            return -1;
        }

        @Override // k5.a
        /* JADX INFO: renamed from: c0, reason: merged with bridge method [inline-methods] */
        public int K(T t7) {
            return -t7.getDownNestedScrollRange();
        }

        @Override // k5.a
        /* JADX INFO: renamed from: d0, reason: merged with bridge method [inline-methods] */
        public int L(T t7) {
            return t7.getTotalScrollRange();
        }

        public final int e0(T t7, int i8) {
            int iAbs = Math.abs(i8);
            int childCount = t7.getChildCount();
            int topInset = 0;
            int i9 = 0;
            while (true) {
                if (i9 >= childCount) {
                    break;
                }
                View childAt = t7.getChildAt(i9);
                d dVar = (d) childAt.getLayoutParams();
                Interpolator interpolatorB = dVar.b();
                if (iAbs < childAt.getTop() || iAbs > childAt.getBottom()) {
                    i9++;
                } else if (interpolatorB != null) {
                    int iA = dVar.a();
                    if ((iA & 1) != 0) {
                        topInset = 0 + childAt.getHeight() + ((LinearLayout.LayoutParams) dVar).topMargin + ((LinearLayout.LayoutParams) dVar).bottomMargin;
                        if ((iA & 2) != 0) {
                            topInset -= t.B(childAt);
                        }
                    }
                    if (t.x(childAt)) {
                        topInset -= t7.getTopInset();
                    }
                    if (topInset > 0) {
                        float f8 = topInset;
                        return Integer.signum(i8) * (childAt.getTop() + Math.round(f8 * interpolatorB.getInterpolation((iAbs - childAt.getTop()) / f8)));
                    }
                }
            }
            return i8;
        }

        @Override // k5.a
        /* JADX INFO: renamed from: f0, reason: merged with bridge method [inline-methods] */
        public void N(CoordinatorLayout coordinatorLayout, T t7) {
            q0(coordinatorLayout, t7);
            if (t7.l()) {
                t7.u(t7.w(Z(coordinatorLayout)));
            }
        }

        @Override // k5.c, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: g0, reason: merged with bridge method [inline-methods] */
        public boolean l(CoordinatorLayout coordinatorLayout, T t7, int i8) {
            boolean zL = super.l(coordinatorLayout, t7, i8);
            int pendingAction = t7.getPendingAction();
            int i9 = this.f3479n;
            if (i9 >= 0 && (pendingAction & 8) == 0) {
                View childAt = t7.getChildAt(i9);
                P(coordinatorLayout, t7, (-childAt.getBottom()) + (this.f3480o ? t.B(childAt) + t7.getTopInset() : Math.round(childAt.getHeight() * this.f3481p)));
            } else if (pendingAction != 0) {
                boolean z7 = (pendingAction & 4) != 0;
                if ((pendingAction & 2) != 0) {
                    int i10 = -t7.getUpNestedPreScrollRange();
                    if (z7) {
                        U(coordinatorLayout, t7, i10, 0.0f);
                    } else {
                        P(coordinatorLayout, t7, i10);
                    }
                } else if ((pendingAction & 1) != 0) {
                    if (z7) {
                        U(coordinatorLayout, t7, 0, 0.0f);
                    } else {
                        P(coordinatorLayout, t7, 0);
                    }
                }
            }
            t7.q();
            this.f3479n = -1;
            G(j0.a.b(E(), -t7.getTotalScrollRange(), 0));
            s0(coordinatorLayout, t7, E(), 0, true);
            t7.m(E());
            r0(coordinatorLayout, t7);
            return zL;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: h0, reason: merged with bridge method [inline-methods] */
        public boolean m(CoordinatorLayout coordinatorLayout, T t7, int i8, int i9, int i10, int i11) {
            if (((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.f) t7.getLayoutParams())).height != -2) {
                return super.m(coordinatorLayout, t7, i8, i9, i10, i11);
            }
            coordinatorLayout.N(t7, i8, i9, View.MeasureSpec.makeMeasureSpec(0, 0), i11);
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: i0, reason: merged with bridge method [inline-methods] */
        public void q(CoordinatorLayout coordinatorLayout, T t7, View view, int i8, int i9, int[] iArr, int i10) {
            int i11;
            int downNestedPreScrollRange;
            if (i9 != 0) {
                if (i9 < 0) {
                    i11 = -t7.getTotalScrollRange();
                    downNestedPreScrollRange = t7.getDownNestedPreScrollRange() + i11;
                } else {
                    i11 = -t7.getUpNestedPreScrollRange();
                    downNestedPreScrollRange = 0;
                }
                int i12 = i11;
                int i13 = downNestedPreScrollRange;
                if (i12 != i13) {
                    iArr[1] = O(coordinatorLayout, t7, i9, i12, i13);
                }
            }
            if (t7.l()) {
                t7.u(t7.w(view));
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: j0, reason: merged with bridge method [inline-methods] */
        public void t(CoordinatorLayout coordinatorLayout, T t7, View view, int i8, int i9, int i10, int i11, int i12, int[] iArr) {
            if (i11 < 0) {
                iArr[1] = O(coordinatorLayout, t7, i11, -t7.getDownNestedScrollRange(), 0);
            }
            if (i11 == 0) {
                r0(coordinatorLayout, t7);
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: k0, reason: merged with bridge method [inline-methods] */
        public void x(CoordinatorLayout coordinatorLayout, T t7, Parcelable parcelable) {
            if (!(parcelable instanceof SavedState)) {
                super.x(coordinatorLayout, t7, parcelable);
                this.f3479n = -1;
                return;
            }
            SavedState savedState = (SavedState) parcelable;
            super.x(coordinatorLayout, t7, savedState.f());
            this.f3479n = savedState.f3484f;
            this.f3481p = savedState.f3485g;
            this.f3480o = savedState.f3486h;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: l0, reason: merged with bridge method [inline-methods] */
        public Parcelable y(CoordinatorLayout coordinatorLayout, T t7) {
            Parcelable parcelableY = super.y(coordinatorLayout, t7);
            int iE = E();
            int childCount = t7.getChildCount();
            for (int i8 = 0; i8 < childCount; i8++) {
                View childAt = t7.getChildAt(i8);
                int bottom = childAt.getBottom() + iE;
                if (childAt.getTop() + iE <= 0 && bottom >= 0) {
                    SavedState savedState = new SavedState(parcelableY);
                    savedState.f3484f = i8;
                    savedState.f3486h = bottom == t.B(childAt) + t7.getTopInset();
                    savedState.f3485g = bottom / childAt.getHeight();
                    return savedState;
                }
            }
            return parcelableY;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: m0, reason: merged with bridge method [inline-methods] */
        public boolean A(CoordinatorLayout coordinatorLayout, T t7, View view, View view2, int i8, int i9) {
            ValueAnimator valueAnimator;
            boolean z7 = (i8 & 2) != 0 && (t7.l() || X(coordinatorLayout, t7, view));
            if (z7 && (valueAnimator = this.f3478m) != null) {
                valueAnimator.cancel();
            }
            this.f3482q = null;
            this.f3477l = i9;
            return z7;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: n0, reason: merged with bridge method [inline-methods] */
        public void C(CoordinatorLayout coordinatorLayout, T t7, View view, int i8) {
            if (this.f3477l == 0 || i8 == 1) {
                q0(coordinatorLayout, t7);
                if (t7.l()) {
                    t7.u(t7.w(view));
                }
            }
            this.f3482q = new WeakReference<>(view);
        }

        @Override // k5.a
        /* JADX INFO: renamed from: o0, reason: merged with bridge method [inline-methods] */
        public int Q(CoordinatorLayout coordinatorLayout, T t7, int i8, int i9, int i10) {
            int iM = M();
            int i11 = 0;
            if (i9 == 0 || iM < i9 || iM > i10) {
                this.f3476k = 0;
            } else {
                int iB = j0.a.b(i8, i9, i10);
                if (iM != iB) {
                    int iE0 = t7.h() ? e0(t7, iB) : iB;
                    boolean zG = G(iE0);
                    i11 = iM - iB;
                    this.f3476k = iB - iE0;
                    if (!zG && t7.h()) {
                        coordinatorLayout.p(t7);
                    }
                    t7.m(E());
                    s0(coordinatorLayout, t7, iB, iB < iM ? -1 : 1, false);
                }
            }
            r0(coordinatorLayout, t7);
            return i11;
        }

        public final boolean p0(CoordinatorLayout coordinatorLayout, T t7) {
            List<View> listW = coordinatorLayout.w(t7);
            int size = listW.size();
            for (int i8 = 0; i8 < size; i8++) {
                CoordinatorLayout.c cVarF = ((CoordinatorLayout.f) listW.get(i8).getLayoutParams()).f();
                if (cVarF instanceof ScrollingViewBehavior) {
                    return ((ScrollingViewBehavior) cVarF).K() != 0;
                }
            }
            return false;
        }

        public final void q0(CoordinatorLayout coordinatorLayout, T t7) {
            int iM = M();
            int iB0 = b0(t7, iM);
            if (iB0 >= 0) {
                View childAt = t7.getChildAt(iB0);
                d dVar = (d) childAt.getLayoutParams();
                int iA = dVar.a();
                if ((iA & 17) == 17) {
                    int i8 = -childAt.getTop();
                    int iB = -childAt.getBottom();
                    if (iB0 == t7.getChildCount() - 1) {
                        iB += t7.getTopInset();
                    }
                    if (Y(iA, 2)) {
                        iB += t.B(childAt);
                    } else if (Y(iA, 5)) {
                        int iB2 = t.B(childAt) + iB;
                        if (iM < iB2) {
                            i8 = iB2;
                        } else {
                            iB = iB2;
                        }
                    }
                    if (Y(iA, 32)) {
                        i8 += ((LinearLayout.LayoutParams) dVar).topMargin;
                        iB -= ((LinearLayout.LayoutParams) dVar).bottomMargin;
                    }
                    if (iM < (iB + i8) / 2) {
                        i8 = iB;
                    }
                    U(coordinatorLayout, t7, j0.a.b(i8, -t7.getTotalScrollRange(), 0), 0.0f);
                }
            }
        }

        public final void r0(CoordinatorLayout coordinatorLayout, T t7) {
            t.f0(coordinatorLayout, c.a.f6980m.b());
            t.f0(coordinatorLayout, c.a.f6981n.b());
            View viewZ = Z(coordinatorLayout);
            if (viewZ == null || t7.getTotalScrollRange() == 0 || !(((CoordinatorLayout.f) viewZ.getLayoutParams()).f() instanceof ScrollingViewBehavior)) {
                return;
            }
            S(coordinatorLayout, t7, viewZ);
        }

        public final void s0(CoordinatorLayout coordinatorLayout, T t7, int i8, int i9, boolean z7) {
            View viewA0 = a0(t7, i8);
            if (viewA0 != null) {
                int iA = ((d) viewA0.getLayoutParams()).a();
                boolean zW = false;
                if ((iA & 1) != 0) {
                    int iB = t.B(viewA0);
                    if (i9 <= 0 || (iA & 12) == 0 ? !((iA & 2) == 0 || (-i8) < (viewA0.getBottom() - iB) - t7.getTopInset()) : (-i8) >= (viewA0.getBottom() - iB) - t7.getTopInset()) {
                        zW = true;
                    }
                }
                if (t7.l()) {
                    zW = t7.w(Z(coordinatorLayout));
                }
                boolean zU = t7.u(zW);
                if (z7 || (zU && p0(coordinatorLayout, t7))) {
                    t7.jumpDrawablesToCurrentState();
                }
            }
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f3479n = -1;
        }

        public static class SavedState extends AbsSavedState {
            public static final Parcelable.Creator<SavedState> CREATOR = new a();

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            public int f3484f;

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            public float f3485g;

            /* JADX INFO: renamed from: h, reason: collision with root package name */
            public boolean f3486h;

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
                public SavedState[] newArray(int i8) {
                    return new SavedState[i8];
                }
            }

            public SavedState(Parcel parcel, ClassLoader classLoader) {
                super(parcel, classLoader);
                this.f3484f = parcel.readInt();
                this.f3485g = parcel.readFloat();
                this.f3486h = parcel.readByte() != 0;
            }

            @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i8) {
                super.writeToParcel(parcel, i8);
                parcel.writeInt(this.f3484f);
                parcel.writeFloat(this.f3485g);
                parcel.writeByte(this.f3486h ? (byte) 1 : (byte) 0);
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
    public AppBarLayout(Context context, AttributeSet attributeSet, int i8) {
        int i9 = f3458d;
        super(i6.a.c(context, attributeSet, i8, i9), attributeSet, i8);
        this.f3460f = -1;
        this.f3461g = -1;
        this.f3462h = -1;
        this.f3464j = 0;
        Context context2 = getContext();
        setOrientation(1);
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 21) {
            k5.e.a(this);
            k5.e.c(this, attributeSet, i8, i9);
        }
        TypedArray typedArrayH = k.h(context2, attributeSet, R$styleable.AppBarLayout, i8, i9, new int[0]);
        t.o0(this, typedArrayH.getDrawable(R$styleable.AppBarLayout_android_background));
        if (getBackground() instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) getBackground();
            h hVar = new h();
            hVar.X(ColorStateList.valueOf(colorDrawable.getColor()));
            hVar.N(context2);
            t.o0(this, hVar);
        }
        int i11 = R$styleable.AppBarLayout_expanded;
        if (typedArrayH.hasValue(i11)) {
            s(typedArrayH.getBoolean(i11, false), false, false);
        }
        if (i10 >= 21) {
            if (typedArrayH.hasValue(R$styleable.AppBarLayout_elevation)) {
                k5.e.b(this, typedArrayH.getDimensionPixelSize(r13, 0));
            }
        }
        if (i10 >= 26) {
            int i12 = R$styleable.AppBarLayout_android_keyboardNavigationCluster;
            if (typedArrayH.hasValue(i12)) {
                setKeyboardNavigationCluster(typedArrayH.getBoolean(i12, false));
            }
            int i13 = R$styleable.AppBarLayout_android_touchscreenBlocksFocus;
            if (typedArrayH.hasValue(i13)) {
                setTouchscreenBlocksFocus(typedArrayH.getBoolean(i13, false));
            }
        }
        this.f3470p = typedArrayH.getBoolean(R$styleable.AppBarLayout_liftOnScroll, false);
        this.f3471q = typedArrayH.getResourceId(R$styleable.AppBarLayout_liftOnScrollTargetViewId, -1);
        setStatusBarForeground(typedArrayH.getDrawable(R$styleable.AppBarLayout_statusBarForeground));
        typedArrayH.recycle();
        t.y0(this, new a());
    }

    public static class d extends LinearLayout.LayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f3501a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Interpolator f3502b;

        public d(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f3501a = 1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.AppBarLayout_Layout);
            this.f3501a = typedArrayObtainStyledAttributes.getInt(R$styleable.AppBarLayout_Layout_layout_scrollFlags, 0);
            int i8 = R$styleable.AppBarLayout_Layout_layout_scrollInterpolator;
            if (typedArrayObtainStyledAttributes.hasValue(i8)) {
                this.f3502b = AnimationUtils.loadInterpolator(context, typedArrayObtainStyledAttributes.getResourceId(i8, 0));
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public int a() {
            return this.f3501a;
        }

        public Interpolator b() {
            return this.f3502b;
        }

        public boolean c() {
            int i8 = this.f3501a;
            return (i8 & 1) == 1 && (i8 & 10) != 0;
        }

        public d(int i8, int i9) {
            super(i8, i9);
            this.f3501a = 1;
        }

        public d(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f3501a = 1;
        }

        public d(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f3501a = 1;
        }

        public d(LinearLayout.LayoutParams layoutParams) {
            super(layoutParams);
            this.f3501a = 1;
        }
    }
}
