package com.google.android.material.bottomappbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R$animator;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import defpackage.ij1;
import defpackage.in3;
import defpackage.oj2;
import defpackage.om3;
import defpackage.pt3;
import defpackage.r83;
import defpackage.rc1;
import defpackage.sc1;
import defpackage.u5;
import defpackage.uc1;
import defpackage.uh;
import defpackage.uj2;
import defpackage.vh;
import defpackage.wh;
import defpackage.wm2;
import defpackage.xh;
import defpackage.xn3;
import defpackage.yh;
import defpackage.yn3;
import defpackage.ze3;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class BottomAppBar extends Toolbar implements CoordinatorLayout.b {
    public static final int v0 = R$style.Widget_MaterialComponents_BottomAppBar;
    public static final int w0 = R$attr.motionDurationLong2;
    public static final int x0 = R$attr.motionEasingEmphasizedInterpolator;
    public Integer b0;
    public final int c0;
    public final sc1 d0;
    public Animator e0;
    public Animator f0;
    public int g0;
    public int h0;
    public boolean i0;
    public final boolean j0;
    public final boolean k0;
    public final boolean l0;
    public int m0;
    public boolean n0;
    public boolean o0;
    public Behavior p0;
    public int q0;
    public int r0;
    public int s0;
    public final a t0;
    public final b u0;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public int g;
        public boolean h;

        public class a implements Parcelable.ClassLoaderCreator<SavedState> {
            @Override // android.os.Parcelable.ClassLoaderCreator
            public final SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public final Object[] newArray(int i) {
                return new SavedState[i];
            }

            @Override // android.os.Parcelable.Creator
            public final Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }
        }

        public SavedState(Toolbar.SavedState savedState) {
            super(savedState);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.e, i);
            parcel.writeInt(this.g);
            parcel.writeInt(this.h ? 1 : 0);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.g = parcel.readInt();
            this.h = parcel.readInt() != 0;
        }
    }

    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            BottomAppBar bottomAppBar = BottomAppBar.this;
            if (bottomAppBar.n0) {
                return;
            }
            bottomAppBar.G(bottomAppBar.g0, bottomAppBar.o0);
        }
    }

    public class b implements ze3<FloatingActionButton> {
        public b() {
        }
    }

    public class c implements yn3.b {
        public c() {
        }

        @Override // yn3.b
        public final pt3 a(View view, pt3 pt3Var, yn3.c cVar) {
            boolean z;
            BottomAppBar bottomAppBar = BottomAppBar.this;
            if (bottomAppBar.j0) {
                bottomAppBar.q0 = pt3Var.b();
            }
            boolean z2 = false;
            if (bottomAppBar.k0) {
                z = bottomAppBar.s0 != pt3Var.c();
                bottomAppBar.s0 = pt3Var.c();
            } else {
                z = false;
            }
            if (bottomAppBar.l0) {
                boolean z3 = bottomAppBar.r0 != pt3Var.d();
                bottomAppBar.r0 = pt3Var.d();
                z2 = z3;
            }
            if (z || z2) {
                Animator animator = bottomAppBar.f0;
                if (animator != null) {
                    animator.cancel();
                }
                Animator animator2 = bottomAppBar.e0;
                if (animator2 != null) {
                    animator2.cancel();
                }
                bottomAppBar.I();
                bottomAppBar.H();
            }
            return pt3Var;
        }
    }

    public class d extends AnimatorListenerAdapter {
        public d() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            int i = BottomAppBar.v0;
            BottomAppBar bottomAppBar = BottomAppBar.this;
            bottomAppBar.getClass();
            bottomAppBar.n0 = false;
            bottomAppBar.f0 = null;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            int i = BottomAppBar.v0;
            BottomAppBar.this.getClass();
        }
    }

    public class e implements Runnable {
        public final /* synthetic */ ActionMenuView e;
        public final /* synthetic */ int f;
        public final /* synthetic */ boolean g;

        public e(ActionMenuView actionMenuView, int i, boolean z) {
            this.e = actionMenuView;
            this.f = i;
            this.g = z;
        }

        @Override // java.lang.Runnable
        public final void run() {
            int i = this.f;
            boolean z = this.g;
            BottomAppBar bottomAppBar = BottomAppBar.this;
            this.e.setTranslationX(bottomAppBar.E(r3, i, z));
        }
    }

    public BottomAppBar(Context context) {
        this(context, null);
    }

    private ActionMenuView getActionMenuView() {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof ActionMenuView) {
                return (ActionMenuView) childAt;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getBottomInset() {
        return this.q0;
    }

    private int getFabAlignmentAnimationDuration() {
        return ij1.c(getContext(), w0, 300);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getFabTranslationX() {
        return F(this.g0);
    }

    private float getFabTranslationY() {
        return -getTopEdgeTreatment().h;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getLeftInset() {
        return this.s0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getRightInset() {
        return this.r0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public yh getTopEdgeTreatment() {
        return (yh) this.d0.e.a.i;
    }

    public final FloatingActionButton C() {
        View viewD = D();
        if (viewD instanceof FloatingActionButton) {
            return (FloatingActionButton) viewD;
        }
        return null;
    }

    public final View D() {
        if (!(getParent() instanceof CoordinatorLayout)) {
            return null;
        }
        ArrayList arrayList = (ArrayList) ((wm2) ((CoordinatorLayout) getParent()).f.g).get(this);
        List<View> arrayList2 = arrayList == null ? null : new ArrayList(arrayList);
        if (arrayList2 == null) {
            arrayList2 = Collections.emptyList();
        }
        for (View view : arrayList2) {
            if ((view instanceof FloatingActionButton) || (view instanceof ExtendedFloatingActionButton)) {
                return view;
            }
        }
        return null;
    }

    public final int E(ActionMenuView actionMenuView, int i, boolean z) {
        if (i != 1 || !z) {
            return 0;
        }
        boolean zD = yn3.d(this);
        int measuredWidth = zD ? getMeasuredWidth() : 0;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if ((childAt.getLayoutParams() instanceof Toolbar.LayoutParams) && (((Toolbar.LayoutParams) childAt.getLayoutParams()).a & 8388615) == 8388611) {
                measuredWidth = zD ? Math.min(measuredWidth, childAt.getLeft()) : Math.max(measuredWidth, childAt.getRight());
            }
        }
        return measuredWidth - ((zD ? actionMenuView.getRight() : actionMenuView.getLeft()) + (zD ? this.r0 : -this.s0));
    }

    public final float F(int i) {
        boolean zD = yn3.d(this);
        if (i == 1) {
            return ((getMeasuredWidth() / 2) - (this.c0 + (zD ? this.s0 : this.r0))) * (zD ? -1 : 1);
        }
        return 0.0f;
    }

    public final void G(int i, boolean z) {
        WeakHashMap<View, in3> weakHashMap = om3.a;
        if (!om3.g.c(this)) {
            this.n0 = false;
            int i2 = this.m0;
            if (i2 != 0) {
                this.m0 = 0;
                getMenu().clear();
                o(i2);
                return;
            }
            return;
        }
        Animator animator = this.f0;
        if (animator != null) {
            animator.cancel();
        }
        ArrayList arrayList = new ArrayList();
        FloatingActionButton floatingActionButtonC = C();
        if (!(floatingActionButtonC != null && floatingActionButtonC.k())) {
            i = 0;
            z = false;
        }
        ActionMenuView actionMenuView = getActionMenuView();
        if (actionMenuView != null) {
            float fabAlignmentAnimationDuration = getFabAlignmentAnimationDuration();
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(actionMenuView, "alpha", 1.0f);
            objectAnimatorOfFloat.setDuration((long) (0.8f * fabAlignmentAnimationDuration));
            if (Math.abs(actionMenuView.getTranslationX() - E(actionMenuView, i, z)) > 1.0f) {
                ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(actionMenuView, "alpha", 0.0f);
                objectAnimatorOfFloat2.setDuration((long) (fabAlignmentAnimationDuration * 0.2f));
                objectAnimatorOfFloat2.addListener(new wh(this, actionMenuView, i, z));
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playSequentially(objectAnimatorOfFloat2, objectAnimatorOfFloat);
                arrayList.add(animatorSet);
            } else if (actionMenuView.getAlpha() < 1.0f) {
                arrayList.add(objectAnimatorOfFloat);
            }
        }
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playTogether(arrayList);
        this.f0 = animatorSet2;
        animatorSet2.addListener(new d());
        this.f0.start();
    }

    public final void H() {
        ActionMenuView actionMenuView = getActionMenuView();
        if (actionMenuView == null || this.f0 != null) {
            return;
        }
        actionMenuView.setAlpha(1.0f);
        FloatingActionButton floatingActionButtonC = C();
        if (floatingActionButtonC != null && floatingActionButtonC.k()) {
            K(actionMenuView, this.g0, this.o0, false);
        } else {
            K(actionMenuView, 0, false, false);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void I() {
        /*
            r3 = this;
            yh r0 = r3.getTopEdgeTreatment()
            float r1 = r3.getFabTranslationX()
            r0.i = r1
            android.view.View r0 = r3.D()
            sc1 r1 = r3.d0
            boolean r2 = r3.o0
            if (r2 == 0) goto L28
            com.google.android.material.floatingactionbutton.FloatingActionButton r2 = r3.C()
            if (r2 == 0) goto L22
            boolean r2 = r2.k()
            if (r2 == 0) goto L22
            r2 = 1
            goto L23
        L22:
            r2 = 0
        L23:
            if (r2 == 0) goto L28
            r2 = 1065353216(0x3f800000, float:1.0)
            goto L29
        L28:
            r2 = 0
        L29:
            r1.o(r2)
            if (r0 == 0) goto L3c
            float r1 = r3.getFabTranslationY()
            r0.setTranslationY(r1)
            float r3 = r3.getFabTranslationX()
            r0.setTranslationX(r3)
        L3c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomappbar.BottomAppBar.I():void");
    }

    public final void J(int i) {
        float f = i;
        if (f != getTopEdgeTreatment().g) {
            getTopEdgeTreatment().g = f;
            this.d0.invalidateSelf();
        }
    }

    public final void K(ActionMenuView actionMenuView, int i, boolean z, boolean z2) {
        e eVar = new e(actionMenuView, i, z);
        if (z2) {
            actionMenuView.post(eVar);
        } else {
            eVar.run();
        }
    }

    public void addOnScrollStateChangedListener(HideBottomViewOnScrollBehavior.a aVar) {
        getBehavior().addOnScrollStateChangedListener(aVar);
    }

    public ColorStateList getBackgroundTint() {
        return this.d0.e.f;
    }

    public float getCradleVerticalOffset() {
        return getTopEdgeTreatment().h;
    }

    public int getFabAlignmentMode() {
        return this.g0;
    }

    public int getFabAnimationMode() {
        return this.h0;
    }

    public float getFabCradleMargin() {
        return getTopEdgeTreatment().f;
    }

    public float getFabCradleRoundedCornerRadius() {
        return getTopEdgeTreatment().e;
    }

    public boolean getHideOnScroll() {
        return this.i0;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        oj2.e0(this, this.d0);
        if (getParent() instanceof ViewGroup) {
            ((ViewGroup) getParent()).setClipChildren(false);
        }
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            Animator animator = this.f0;
            if (animator != null) {
                animator.cancel();
            }
            Animator animator2 = this.e0;
            if (animator2 != null) {
                animator2.cancel();
            }
            I();
        }
        H();
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.e);
        this.g0 = savedState.g;
        this.o0 = savedState.h;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState((Toolbar.SavedState) super.onSaveInstanceState());
        savedState.g = this.g0;
        savedState.h = this.o0;
        return savedState;
    }

    public void removeOnScrollStateChangedListener(HideBottomViewOnScrollBehavior.a aVar) {
        getBehavior().removeOnScrollStateChangedListener(aVar);
    }

    public void setBackgroundTint(ColorStateList colorStateList) {
        this.d0.setTintList(colorStateList);
    }

    public void setCradleVerticalOffset(float f) {
        if (f != getCradleVerticalOffset()) {
            yh topEdgeTreatment = getTopEdgeTreatment();
            if (f < 0.0f) {
                topEdgeTreatment.getClass();
                throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
            }
            topEdgeTreatment.h = f;
            this.d0.invalidateSelf();
            I();
        }
    }

    @Override // android.view.View
    public void setElevation(float f) {
        sc1 sc1Var = this.d0;
        sc1Var.m(f);
        int i = sc1Var.e.q - sc1Var.i();
        Behavior behavior = getBehavior();
        behavior.d = i;
        if (behavior.c == 1) {
            setTranslationY(behavior.b + i);
        }
    }

    public void setFabAlignmentMode(int i) {
        this.m0 = 0;
        this.n0 = true;
        G(i, this.o0);
        if (this.g0 != i) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            if (om3.g.c(this)) {
                Animator animator = this.e0;
                if (animator != null) {
                    animator.cancel();
                }
                ArrayList arrayList = new ArrayList();
                if (this.h0 == 1) {
                    ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(C(), "translationX", F(i));
                    objectAnimatorOfFloat.setDuration(getFabAlignmentAnimationDuration());
                    arrayList.add(objectAnimatorOfFloat);
                } else {
                    FloatingActionButton floatingActionButtonC = C();
                    if (floatingActionButtonC != null && !floatingActionButtonC.j()) {
                        floatingActionButtonC.hide(new vh(this, i));
                    }
                }
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(arrayList);
                animatorSet.setInterpolator(ij1.d(getContext(), x0, u5.a));
                this.e0 = animatorSet;
                animatorSet.addListener(new uh(this));
                this.e0.start();
            }
        }
        this.g0 = i;
    }

    public void setFabAnimationMode(int i) {
        this.h0 = i;
    }

    public void setFabCornerSize(float f) {
        if (f != getTopEdgeTreatment().j) {
            getTopEdgeTreatment().j = f;
            this.d0.invalidateSelf();
        }
    }

    public void setFabCradleMargin(float f) {
        if (f != getFabCradleMargin()) {
            getTopEdgeTreatment().f = f;
            this.d0.invalidateSelf();
        }
    }

    public void setFabCradleRoundedCornerRadius(float f) {
        if (f != getFabCradleRoundedCornerRadius()) {
            getTopEdgeTreatment().e = f;
            this.d0.invalidateSelf();
        }
    }

    public void setHideOnScroll(boolean z) {
        this.i0 = z;
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null && this.b0 != null) {
            drawable = drawable.mutate();
            drawable.setTint(this.b0.intValue());
        }
        super.setNavigationIcon(drawable);
    }

    public void setNavigationIconTint(int i) {
        this.b0 = Integer.valueOf(i);
        Drawable navigationIcon = getNavigationIcon();
        if (navigationIcon != null) {
            setNavigationIcon(navigationIcon);
        }
    }

    public BottomAppBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.bottomAppBarStyle);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.b
    public Behavior getBehavior() {
        if (this.p0 == null) {
            this.p0 = new Behavior();
        }
        return this.p0;
    }

    public static class Behavior extends HideBottomViewOnScrollBehavior<BottomAppBar> {
        public final Rect f;
        public WeakReference<BottomAppBar> g;
        public int h;
        public final a i;

        public class a implements View.OnLayoutChangeListener {
            public a() {
            }

            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                Behavior behavior = Behavior.this;
                BottomAppBar bottomAppBar = behavior.g.get();
                if (bottomAppBar == null || !(view instanceof FloatingActionButton)) {
                    view.removeOnLayoutChangeListener(this);
                    return;
                }
                FloatingActionButton floatingActionButton = (FloatingActionButton) view;
                int measuredWidth = floatingActionButton.getMeasuredWidth();
                int measuredHeight = floatingActionButton.getMeasuredHeight();
                Rect rect = behavior.f;
                rect.set(0, 0, measuredWidth, measuredHeight);
                floatingActionButton.l(rect);
                int iHeight = rect.height();
                bottomAppBar.J(iHeight);
                bottomAppBar.setFabCornerSize(floatingActionButton.getShapeAppearanceModel().e.a(new RectF(rect)));
                CoordinatorLayout.e eVar = (CoordinatorLayout.e) view.getLayoutParams();
                if (behavior.h == 0) {
                    ((ViewGroup.MarginLayoutParams) eVar).bottomMargin = bottomAppBar.getBottomInset() + (bottomAppBar.getResources().getDimensionPixelOffset(R$dimen.mtrl_bottomappbar_fab_bottom_margin) - ((floatingActionButton.getMeasuredHeight() - iHeight) / 2));
                    ((ViewGroup.MarginLayoutParams) eVar).leftMargin = bottomAppBar.getLeftInset();
                    ((ViewGroup.MarginLayoutParams) eVar).rightMargin = bottomAppBar.getRightInset();
                    boolean zD = yn3.d(floatingActionButton);
                    int i9 = bottomAppBar.c0;
                    if (zD) {
                        ((ViewGroup.MarginLayoutParams) eVar).leftMargin += i9;
                    } else {
                        ((ViewGroup.MarginLayoutParams) eVar).rightMargin += i9;
                    }
                }
            }
        }

        public Behavior() {
            this.i = new a();
            this.f = new Rect();
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean h(CoordinatorLayout coordinatorLayout, View view, int i) {
            BottomAppBar bottomAppBar = (BottomAppBar) view;
            this.g = new WeakReference<>(bottomAppBar);
            int i2 = BottomAppBar.v0;
            View viewD = bottomAppBar.D();
            if (viewD != null) {
                WeakHashMap<View, in3> weakHashMap = om3.a;
                if (!om3.g.c(viewD)) {
                    CoordinatorLayout.e eVar = (CoordinatorLayout.e) viewD.getLayoutParams();
                    eVar.d = 49;
                    this.h = ((ViewGroup.MarginLayoutParams) eVar).bottomMargin;
                    if (viewD instanceof FloatingActionButton) {
                        FloatingActionButton floatingActionButton = (FloatingActionButton) viewD;
                        if (floatingActionButton.getShowMotionSpec() == null) {
                            floatingActionButton.setShowMotionSpecResource(R$animator.mtrl_fab_show_motion_spec);
                        }
                        if (floatingActionButton.getHideMotionSpec() == null) {
                            floatingActionButton.setHideMotionSpecResource(R$animator.mtrl_fab_hide_motion_spec);
                        }
                        floatingActionButton.addOnLayoutChangeListener(this.i);
                        floatingActionButton.d(bottomAppBar.t0);
                        floatingActionButton.f(new xh(bottomAppBar));
                        floatingActionButton.g(bottomAppBar.u0);
                    }
                    bottomAppBar.I();
                }
            }
            coordinatorLayout.w(bottomAppBar, i);
            super.h(coordinatorLayout, bottomAppBar, i);
            return false;
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean p(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i, int i2) {
            BottomAppBar bottomAppBar = (BottomAppBar) view;
            return bottomAppBar.getHideOnScroll() && super.p(coordinatorLayout, bottomAppBar, view2, view3, i, i2);
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.i = new a();
            this.f = new Rect();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public BottomAppBar(Context context, AttributeSet attributeSet, int i) {
        int i2 = v0;
        super(uc1.a(context, attributeSet, i, i2), attributeSet, i);
        sc1 sc1Var = new sc1();
        this.d0 = sc1Var;
        this.m0 = 0;
        this.n0 = false;
        this.o0 = true;
        this.t0 = new a();
        this.u0 = new b();
        Context context2 = getContext();
        TypedArray typedArrayD = r83.d(context2, attributeSet, R$styleable.BottomAppBar, i, i2, new int[0]);
        ColorStateList colorStateListA = rc1.a(context2, typedArrayD, R$styleable.BottomAppBar_backgroundTint);
        int i3 = R$styleable.BottomAppBar_navigationIconTint;
        if (typedArrayD.hasValue(i3)) {
            setNavigationIconTint(typedArrayD.getColor(i3, -1));
        }
        int dimensionPixelSize = typedArrayD.getDimensionPixelSize(R$styleable.BottomAppBar_elevation, 0);
        float dimensionPixelOffset = typedArrayD.getDimensionPixelOffset(R$styleable.BottomAppBar_fabCradleMargin, 0);
        float dimensionPixelOffset2 = typedArrayD.getDimensionPixelOffset(R$styleable.BottomAppBar_fabCradleRoundedCornerRadius, 0);
        float dimensionPixelOffset3 = typedArrayD.getDimensionPixelOffset(R$styleable.BottomAppBar_fabCradleVerticalOffset, 0);
        this.g0 = typedArrayD.getInt(R$styleable.BottomAppBar_fabAlignmentMode, 0);
        this.h0 = typedArrayD.getInt(R$styleable.BottomAppBar_fabAnimationMode, 0);
        this.i0 = typedArrayD.getBoolean(R$styleable.BottomAppBar_hideOnScroll, false);
        this.j0 = typedArrayD.getBoolean(R$styleable.BottomAppBar_paddingBottomSystemWindowInsets, false);
        this.k0 = typedArrayD.getBoolean(R$styleable.BottomAppBar_paddingLeftSystemWindowInsets, false);
        this.l0 = typedArrayD.getBoolean(R$styleable.BottomAppBar_paddingRightSystemWindowInsets, false);
        typedArrayD.recycle();
        this.c0 = getResources().getDimensionPixelOffset(R$dimen.mtrl_bottomappbar_fabOffsetEndMode);
        yh yhVar = new yh(dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3);
        uj2.a aVar = new uj2.a();
        aVar.i = yhVar;
        sc1Var.setShapeAppearanceModel(new uj2(aVar));
        sc1Var.r();
        sc1Var.p(Paint.Style.FILL);
        sc1Var.k(context2);
        setElevation(dimensionPixelSize);
        sc1Var.setTintList(colorStateListA);
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.d.n(this, sc1Var);
        c cVar = new c();
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.Insets, i, i2);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(R$styleable.Insets_paddingBottomSystemWindowInsets, false);
        boolean z2 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.Insets_paddingLeftSystemWindowInsets, false);
        boolean z3 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.Insets_paddingRightSystemWindowInsets, false);
        typedArrayObtainStyledAttributes.recycle();
        yn3.a(this, new xn3(z, z2, z3, cVar));
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setSubtitle(CharSequence charSequence) {
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setTitle(CharSequence charSequence) {
    }
}
