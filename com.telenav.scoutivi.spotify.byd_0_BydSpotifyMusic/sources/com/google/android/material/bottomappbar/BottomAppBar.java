package com.google.android.material.bottomappbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.l;
import e3.h;
import i0.u;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import o2.k;

/* JADX INFO: loaded from: classes.dex */
public class BottomAppBar extends Toolbar implements CoordinatorLayout.b {

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    private static final int f4145p0 = k.Widget_MaterialComponents_BottomAppBar;
    private final int U;
    private final e3.g V;
    private Animator W;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    private Animator f4146a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    private int f4147b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    private int f4148c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    private boolean f4149d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    private int f4150e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    private ArrayList<g> f4151f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    private int f4152g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    private boolean f4153h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    private boolean f4154i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    private Behavior f4155j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    private int f4156k0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    private int f4157l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    private int f4158m0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    AnimatorListenerAdapter f4159n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    p2.k<FloatingActionButton> f4160o0;

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        int f4166h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        boolean f4167i;

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

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i7) {
            super.writeToParcel(parcel, i7);
            parcel.writeInt(this.f4166h);
            parcel.writeInt(this.f4167i ? 1 : 0);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f4166h = parcel.readInt();
            this.f4167i = parcel.readInt() != 0;
        }
    }

    class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            BottomAppBar.this.k0();
            BottomAppBar.this.W = null;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            BottomAppBar.this.l0();
        }
    }

    class b extends FloatingActionButton.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f4169a;

        class a extends FloatingActionButton.b {
            a() {
            }

            @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.b
            public void b(FloatingActionButton floatingActionButton) {
                BottomAppBar.this.k0();
            }
        }

        b(int i7) {
            this.f4169a = i7;
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.b
        public void a(FloatingActionButton floatingActionButton) {
            floatingActionButton.setTranslationX(BottomAppBar.this.p0(this.f4169a));
            floatingActionButton.s(new a());
        }
    }

    class c extends AnimatorListenerAdapter {
        c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            BottomAppBar.this.k0();
            BottomAppBar.this.f4153h0 = false;
            BottomAppBar.this.f4146a0 = null;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            BottomAppBar.this.l0();
        }
    }

    class d extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f4173a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ActionMenuView f4174b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f4175c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f4176d;

        d(ActionMenuView actionMenuView, int i7, boolean z6) {
            this.f4174b = actionMenuView;
            this.f4175c = i7;
            this.f4176d = z6;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f4173a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f4173a) {
                return;
            }
            boolean z6 = BottomAppBar.this.f4152g0 != 0;
            BottomAppBar bottomAppBar = BottomAppBar.this;
            bottomAppBar.t0(bottomAppBar.f4152g0);
            BottomAppBar.this.z0(this.f4174b, this.f4175c, this.f4176d, z6);
        }
    }

    class e implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ ActionMenuView f4178e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ int f4179g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ boolean f4180h;

        e(ActionMenuView actionMenuView, int i7, boolean z6) {
            this.f4178e = actionMenuView;
            this.f4179g = i7;
            this.f4180h = z6;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f4178e.setTranslationX(BottomAppBar.this.o0(r0, this.f4179g, this.f4180h));
        }
    }

    class f extends AnimatorListenerAdapter {
        f() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            BottomAppBar.this.f4159n0.onAnimationStart(animator);
            FloatingActionButton floatingActionButtonM0 = BottomAppBar.this.m0();
            if (floatingActionButtonM0 != null) {
                floatingActionButtonM0.setTranslationX(BottomAppBar.this.getFabTranslationX());
            }
        }
    }

    interface g {
        void a(BottomAppBar bottomAppBar);

        void b(BottomAppBar bottomAppBar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f0(FloatingActionButton floatingActionButton) {
        floatingActionButton.e(this.f4159n0);
        floatingActionButton.f(new f());
        floatingActionButton.g(this.f4160o0);
    }

    private void g0() {
        Animator animator = this.f4146a0;
        if (animator != null) {
            animator.cancel();
        }
        Animator animator2 = this.W;
        if (animator2 != null) {
            animator2.cancel();
        }
    }

    private ActionMenuView getActionMenuView() {
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            View childAt = getChildAt(i7);
            if (childAt instanceof ActionMenuView) {
                return (ActionMenuView) childAt;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getBottomInset() {
        return this.f4156k0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getFabTranslationX() {
        return p0(this.f4147b0);
    }

    private float getFabTranslationY() {
        return -getTopEdgeTreatment().c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getLeftInset() {
        return this.f4158m0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getRightInset() {
        return this.f4157l0;
    }

    private com.google.android.material.bottomappbar.a getTopEdgeTreatment() {
        return (com.google.android.material.bottomappbar.a) this.V.C().p();
    }

    private void i0(int i7, List<Animator> list) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(m0(), "translationX", p0(i7));
        objectAnimatorOfFloat.setDuration(300L);
        list.add(objectAnimatorOfFloat);
    }

    private void j0(int i7, boolean z6, List<Animator> list) {
        ActionMenuView actionMenuView = getActionMenuView();
        if (actionMenuView == null) {
            return;
        }
        Animator animatorOfFloat = ObjectAnimator.ofFloat(actionMenuView, "alpha", 1.0f);
        if (Math.abs(actionMenuView.getTranslationX() - o0(actionMenuView, i7, z6)) <= 1.0f) {
            if (actionMenuView.getAlpha() < 1.0f) {
                list.add(animatorOfFloat);
            }
        } else {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(actionMenuView, "alpha", 0.0f);
            objectAnimatorOfFloat.addListener(new d(actionMenuView, i7, z6));
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(150L);
            animatorSet.playSequentially(objectAnimatorOfFloat, animatorOfFloat);
            list.add(animatorSet);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k0() {
        ArrayList<g> arrayList;
        int i7 = this.f4150e0 - 1;
        this.f4150e0 = i7;
        if (i7 != 0 || (arrayList = this.f4151f0) == null) {
            return;
        }
        Iterator<g> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().a(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l0() {
        ArrayList<g> arrayList;
        int i7 = this.f4150e0;
        this.f4150e0 = i7 + 1;
        if (i7 != 0 || (arrayList = this.f4151f0) == null) {
            return;
        }
        Iterator<g> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().b(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FloatingActionButton m0() {
        View viewN0 = n0();
        if (viewN0 instanceof FloatingActionButton) {
            return (FloatingActionButton) viewN0;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View n0() {
        if (!(getParent() instanceof CoordinatorLayout)) {
            return null;
        }
        for (View view : ((CoordinatorLayout) getParent()).s(this)) {
            if ((view instanceof FloatingActionButton) || (view instanceof ExtendedFloatingActionButton)) {
                return view;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float p0(int i7) {
        boolean zD = l.d(this);
        if (i7 == 1) {
            return ((getMeasuredWidth() / 2) - (this.U + (zD ? this.f4158m0 : this.f4157l0))) * (zD ? -1 : 1);
        }
        return 0.0f;
    }

    private boolean q0() {
        FloatingActionButton floatingActionButtonM0 = m0();
        return floatingActionButtonM0 != null && floatingActionButtonM0.o();
    }

    private void r0(int i7, boolean z6) {
        if (!u.P(this)) {
            this.f4153h0 = false;
            t0(this.f4152g0);
            return;
        }
        Animator animator = this.f4146a0;
        if (animator != null) {
            animator.cancel();
        }
        ArrayList arrayList = new ArrayList();
        if (!q0()) {
            i7 = 0;
            z6 = false;
        }
        j0(i7, z6, arrayList);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(arrayList);
        this.f4146a0 = animatorSet;
        animatorSet.addListener(new c());
        this.f4146a0.start();
    }

    private void s0(int i7) {
        if (this.f4147b0 == i7 || !u.P(this)) {
            return;
        }
        Animator animator = this.W;
        if (animator != null) {
            animator.cancel();
        }
        ArrayList arrayList = new ArrayList();
        if (this.f4148c0 == 1) {
            i0(i7, arrayList);
        } else {
            h0(i7, arrayList);
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(arrayList);
        this.W = animatorSet;
        animatorSet.addListener(new a());
        this.W.start();
    }

    private void u0() {
        ActionMenuView actionMenuView = getActionMenuView();
        if (actionMenuView == null || this.f4146a0 != null) {
            return;
        }
        actionMenuView.setAlpha(1.0f);
        if (q0()) {
            y0(actionMenuView, this.f4147b0, this.f4154i0);
        } else {
            y0(actionMenuView, 0, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v0() {
        getTopEdgeTreatment().n(getFabTranslationX());
        View viewN0 = n0();
        this.V.Y((this.f4154i0 && q0()) ? 1.0f : 0.0f);
        if (viewN0 != null) {
            viewN0.setTranslationY(getFabTranslationY());
            viewN0.setTranslationX(getFabTranslationX());
        }
    }

    private void y0(ActionMenuView actionMenuView, int i7, boolean z6) {
        z0(actionMenuView, i7, z6, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z0(ActionMenuView actionMenuView, int i7, boolean z6, boolean z7) {
        e eVar = new e(actionMenuView, i7, z6);
        if (z7) {
            actionMenuView.post(eVar);
        } else {
            eVar.run();
        }
    }

    public ColorStateList getBackgroundTint() {
        return this.V.E();
    }

    public float getCradleVerticalOffset() {
        return getTopEdgeTreatment().c();
    }

    public int getFabAlignmentMode() {
        return this.f4147b0;
    }

    public int getFabAnimationMode() {
        return this.f4148c0;
    }

    public float getFabCradleMargin() {
        return getTopEdgeTreatment().e();
    }

    public float getFabCradleRoundedCornerRadius() {
        return getTopEdgeTreatment().f();
    }

    public boolean getHideOnScroll() {
        return this.f4149d0;
    }

    protected void h0(int i7, List<Animator> list) {
        FloatingActionButton floatingActionButtonM0 = m0();
        if (floatingActionButtonM0 == null || floatingActionButtonM0.n()) {
            return;
        }
        l0();
        floatingActionButtonM0.l(new b(i7));
    }

    protected int o0(ActionMenuView actionMenuView, int i7, boolean z6) {
        if (i7 != 1 || !z6) {
            return 0;
        }
        boolean zD = l.d(this);
        int measuredWidth = zD ? getMeasuredWidth() : 0;
        for (int i8 = 0; i8 < getChildCount(); i8++) {
            View childAt = getChildAt(i8);
            if ((childAt.getLayoutParams() instanceof Toolbar.e) && (((Toolbar.e) childAt.getLayoutParams()).f501a & 8388615) == 8388611) {
                measuredWidth = zD ? Math.min(measuredWidth, childAt.getLeft()) : Math.max(measuredWidth, childAt.getRight());
            }
        }
        return measuredWidth - ((zD ? actionMenuView.getRight() : actionMenuView.getLeft()) + (zD ? this.f4157l0 : -this.f4158m0));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        h.f(this, this.V);
        if (getParent() instanceof ViewGroup) {
            ((ViewGroup) getParent()).setClipChildren(false);
        }
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
        if (z6) {
            g0();
            v0();
        }
        u0();
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.b());
        this.f4147b0 = savedState.f4166h;
        this.f4154i0 = savedState.f4167i;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f4166h = this.f4147b0;
        savedState.f4167i = this.f4154i0;
        return savedState;
    }

    public void setBackgroundTint(ColorStateList colorStateList) {
        a0.a.i(this.V, colorStateList);
    }

    public void setCradleVerticalOffset(float f7) {
        if (f7 != getCradleVerticalOffset()) {
            getTopEdgeTreatment().h(f7);
            this.V.invalidateSelf();
            v0();
        }
    }

    @Override // android.view.View
    public void setElevation(float f7) {
        this.V.W(f7);
        getBehavior().G(this, this.V.B() - this.V.A());
    }

    public void setFabAlignmentMode(int i7) {
        w0(i7, 0);
    }

    public void setFabAnimationMode(int i7) {
        this.f4148c0 = i7;
    }

    void setFabCornerSize(float f7) {
        if (f7 != getTopEdgeTreatment().d()) {
            getTopEdgeTreatment().i(f7);
            this.V.invalidateSelf();
        }
    }

    public void setFabCradleMargin(float f7) {
        if (f7 != getFabCradleMargin()) {
            getTopEdgeTreatment().j(f7);
            this.V.invalidateSelf();
        }
    }

    public void setFabCradleRoundedCornerRadius(float f7) {
        if (f7 != getFabCradleRoundedCornerRadius()) {
            getTopEdgeTreatment().k(f7);
            this.V.invalidateSelf();
        }
    }

    public void setHideOnScroll(boolean z6) {
        this.f4149d0 = z6;
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setSubtitle(CharSequence charSequence) {
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setTitle(CharSequence charSequence) {
    }

    public void t0(int i7) {
        if (i7 != 0) {
            this.f4152g0 = 0;
            getMenu().clear();
            x(i7);
        }
    }

    public void w0(int i7, int i8) {
        this.f4152g0 = i8;
        this.f4153h0 = true;
        r0(i7, this.f4154i0);
        s0(i7);
        this.f4147b0 = i7;
    }

    boolean x0(int i7) {
        float f7 = i7;
        if (f7 == getTopEdgeTreatment().g()) {
            return false;
        }
        getTopEdgeTreatment().l(f7);
        this.V.invalidateSelf();
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.b
    public Behavior getBehavior() {
        if (this.f4155j0 == null) {
            this.f4155j0 = new Behavior();
        }
        return this.f4155j0;
    }

    public static class Behavior extends HideBottomViewOnScrollBehavior<BottomAppBar> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final Rect f4161e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private WeakReference<BottomAppBar> f4162f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private int f4163g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private final View.OnLayoutChangeListener f4164h;

        class a implements View.OnLayoutChangeListener {
            a() {
            }

            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14) {
                BottomAppBar bottomAppBar = (BottomAppBar) Behavior.this.f4162f.get();
                if (bottomAppBar == null || !(view instanceof FloatingActionButton)) {
                    view.removeOnLayoutChangeListener(this);
                    return;
                }
                FloatingActionButton floatingActionButton = (FloatingActionButton) view;
                floatingActionButton.j(Behavior.this.f4161e);
                int iHeight = Behavior.this.f4161e.height();
                bottomAppBar.x0(iHeight);
                bottomAppBar.setFabCornerSize(floatingActionButton.getShapeAppearanceModel().r().a(new RectF(Behavior.this.f4161e)));
                CoordinatorLayout.f fVar = (CoordinatorLayout.f) view.getLayoutParams();
                if (Behavior.this.f4163g == 0) {
                    ((ViewGroup.MarginLayoutParams) fVar).bottomMargin = bottomAppBar.getBottomInset() + (bottomAppBar.getResources().getDimensionPixelOffset(o2.d.mtrl_bottomappbar_fab_bottom_margin) - ((floatingActionButton.getMeasuredHeight() - iHeight) / 2));
                    ((ViewGroup.MarginLayoutParams) fVar).leftMargin = bottomAppBar.getLeftInset();
                    ((ViewGroup.MarginLayoutParams) fVar).rightMargin = bottomAppBar.getRightInset();
                    if (l.d(floatingActionButton)) {
                        ((ViewGroup.MarginLayoutParams) fVar).leftMargin += bottomAppBar.U;
                    } else {
                        ((ViewGroup.MarginLayoutParams) fVar).rightMargin += bottomAppBar.U;
                    }
                }
            }
        }

        public Behavior() {
            this.f4164h = new a();
            this.f4161e = new Rect();
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: M, reason: merged with bridge method [inline-methods] */
        public boolean l(CoordinatorLayout coordinatorLayout, BottomAppBar bottomAppBar, int i7) {
            this.f4162f = new WeakReference<>(bottomAppBar);
            View viewN0 = bottomAppBar.n0();
            if (viewN0 != null && !u.P(viewN0)) {
                CoordinatorLayout.f fVar = (CoordinatorLayout.f) viewN0.getLayoutParams();
                fVar.f1927d = 49;
                this.f4163g = ((ViewGroup.MarginLayoutParams) fVar).bottomMargin;
                if (viewN0 instanceof FloatingActionButton) {
                    FloatingActionButton floatingActionButton = (FloatingActionButton) viewN0;
                    floatingActionButton.addOnLayoutChangeListener(this.f4164h);
                    bottomAppBar.f0(floatingActionButton);
                }
                bottomAppBar.v0();
            }
            coordinatorLayout.I(bottomAppBar, i7);
            return super.l(coordinatorLayout, bottomAppBar, i7);
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: N, reason: merged with bridge method [inline-methods] */
        public boolean A(CoordinatorLayout coordinatorLayout, BottomAppBar bottomAppBar, View view, View view2, int i7, int i8) {
            return bottomAppBar.getHideOnScroll() && super.A(coordinatorLayout, bottomAppBar, view, view2, i7, i8);
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f4164h = new a();
            this.f4161e = new Rect();
        }
    }
}
