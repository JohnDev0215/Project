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
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import e6.m;
import j5.k;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import o0.b0;
import o0.t;
import z5.p;

/* JADX INFO: loaded from: classes2.dex */
public class BottomAppBar extends Toolbar implements CoordinatorLayout.b {
    public static final int S = R$style.Widget_MaterialComponents_BottomAppBar;
    public final int T;
    public final e6.h U;
    public Animator V;
    public Animator W;
    public int X;
    public int Y;
    public boolean Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public final boolean f3582a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public final boolean f3583b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public final boolean f3584c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public int f3585d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public ArrayList<i> f3586e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public boolean f3587f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public Behavior f3588g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public int f3589h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public int f3590i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public int f3591j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public AnimatorListenerAdapter f3592k0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public k<FloatingActionButton> f3593l0;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f3599f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f3600g;

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

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            super.writeToParcel(parcel, i8);
            parcel.writeInt(this.f3599f);
            parcel.writeInt(this.f3600g ? 1 : 0);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f3599f = parcel.readInt();
            this.f3600g = parcel.readInt() != 0;
        }
    }

    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            BottomAppBar bottomAppBar = BottomAppBar.this;
            bottomAppBar.E0(bottomAppBar.X, BottomAppBar.this.f3587f0);
        }
    }

    public class b implements k<FloatingActionButton> {
        public b() {
        }

        @Override // j5.k
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void b(FloatingActionButton floatingActionButton) {
            BottomAppBar.this.U.Y(floatingActionButton.getVisibility() == 0 ? floatingActionButton.getScaleY() : 0.0f);
        }

        @Override // j5.k
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void a(FloatingActionButton floatingActionButton) {
            float translationX = floatingActionButton.getTranslationX();
            if (BottomAppBar.this.getTopEdgeTreatment().g() != translationX) {
                BottomAppBar.this.getTopEdgeTreatment().l(translationX);
                BottomAppBar.this.U.invalidateSelf();
            }
            float fMax = Math.max(0.0f, -floatingActionButton.getTranslationY());
            if (BottomAppBar.this.getTopEdgeTreatment().c() != fMax) {
                BottomAppBar.this.getTopEdgeTreatment().h(fMax);
                BottomAppBar.this.U.invalidateSelf();
            }
            BottomAppBar.this.U.Y(floatingActionButton.getVisibility() == 0 ? floatingActionButton.getScaleY() : 0.0f);
        }
    }

    public class c implements p.d {
        public c() {
        }

        @Override // z5.p.d
        public b0 a(View view, b0 b0Var, p.e eVar) {
            boolean z7;
            if (BottomAppBar.this.f3582a0) {
                BottomAppBar.this.f3589h0 = b0Var.e();
            }
            boolean z8 = false;
            if (BottomAppBar.this.f3583b0) {
                z7 = BottomAppBar.this.f3591j0 != b0Var.f();
                BottomAppBar.this.f3591j0 = b0Var.f();
            } else {
                z7 = false;
            }
            if (BottomAppBar.this.f3584c0) {
                boolean z9 = BottomAppBar.this.f3590i0 != b0Var.g();
                BottomAppBar.this.f3590i0 = b0Var.g();
                z8 = z9;
            }
            if (z7 || z8) {
                BottomAppBar.this.t0();
                BottomAppBar.this.H0();
                BottomAppBar.this.G0();
            }
            return b0Var;
        }
    }

    public class d extends AnimatorListenerAdapter {
        public d() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            BottomAppBar.this.x0();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            BottomAppBar.this.y0();
        }
    }

    public class e extends FloatingActionButton.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f3605a;

        public class a extends FloatingActionButton.b {
            public a() {
            }

            @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.b
            public void b(FloatingActionButton floatingActionButton) {
                BottomAppBar.this.x0();
            }
        }

        public e(int i8) {
            this.f3605a = i8;
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.b
        public void a(FloatingActionButton floatingActionButton) {
            floatingActionButton.setTranslationX(BottomAppBar.this.C0(this.f3605a));
            floatingActionButton.s(new a());
        }
    }

    public class f extends AnimatorListenerAdapter {
        public f() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            BottomAppBar.this.x0();
            BottomAppBar.this.W = null;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            BottomAppBar.this.y0();
        }
    }

    public class g extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f3609d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ ActionMenuView f3610e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ int f3611f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ boolean f3612g;

        public g(ActionMenuView actionMenuView, int i8, boolean z7) {
            this.f3610e = actionMenuView;
            this.f3611f = i8;
            this.f3612g = z7;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f3609d = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f3609d) {
                return;
            }
            BottomAppBar.this.J0(this.f3610e, this.f3611f, this.f3612g);
        }
    }

    public class h extends AnimatorListenerAdapter {
        public h() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            BottomAppBar.this.f3592k0.onAnimationStart(animator);
            FloatingActionButton floatingActionButtonZ0 = BottomAppBar.this.z0();
            if (floatingActionButtonZ0 != null) {
                floatingActionButtonZ0.setTranslationX(BottomAppBar.this.getFabTranslationX());
            }
        }
    }

    public interface i {
        void a(BottomAppBar bottomAppBar);

        void b(BottomAppBar bottomAppBar);
    }

    public BottomAppBar(Context context) {
        this(context, null, 0);
    }

    private ActionMenuView getActionMenuView() {
        for (int i8 = 0; i8 < getChildCount(); i8++) {
            View childAt = getChildAt(i8);
            if (childAt instanceof ActionMenuView) {
                return (ActionMenuView) childAt;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getBottomInset() {
        return this.f3589h0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getFabTranslationX() {
        return C0(this.X);
    }

    private float getFabTranslationY() {
        return -getTopEdgeTreatment().c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getLeftInset() {
        return this.f3591j0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getRightInset() {
        return this.f3590i0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public m5.a getTopEdgeTreatment() {
        return (m5.a) this.U.D().p();
    }

    public final View A0() {
        if (!(getParent() instanceof CoordinatorLayout)) {
            return null;
        }
        for (View view : ((CoordinatorLayout) getParent()).w(this)) {
            if ((view instanceof FloatingActionButton) || (view instanceof ExtendedFloatingActionButton)) {
                return view;
            }
        }
        return null;
    }

    public int B0(ActionMenuView actionMenuView, int i8, boolean z7) {
        if (i8 != 1 || !z7) {
            return 0;
        }
        boolean zH = p.h(this);
        int measuredWidth = zH ? getMeasuredWidth() : 0;
        for (int i9 = 0; i9 < getChildCount(); i9++) {
            View childAt = getChildAt(i9);
            if ((childAt.getLayoutParams() instanceof Toolbar.e) && (((Toolbar.e) childAt.getLayoutParams()).f5929a & 8388615) == 8388611) {
                measuredWidth = zH ? Math.min(measuredWidth, childAt.getLeft()) : Math.max(measuredWidth, childAt.getRight());
            }
        }
        return measuredWidth - ((zH ? actionMenuView.getRight() : actionMenuView.getLeft()) + (zH ? this.f3590i0 : -this.f3591j0));
    }

    public final float C0(int i8) {
        boolean zH = p.h(this);
        if (i8 == 1) {
            return ((getMeasuredWidth() / 2) - (this.T + (zH ? this.f3591j0 : this.f3590i0))) * (zH ? -1 : 1);
        }
        return 0.0f;
    }

    public final boolean D0() {
        FloatingActionButton floatingActionButtonZ0 = z0();
        return floatingActionButtonZ0 != null && floatingActionButtonZ0.o();
    }

    public final void E0(int i8, boolean z7) {
        if (t.Q(this)) {
            Animator animator = this.W;
            if (animator != null) {
                animator.cancel();
            }
            ArrayList arrayList = new ArrayList();
            if (!D0()) {
                i8 = 0;
                z7 = false;
            }
            w0(i8, z7, arrayList);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(arrayList);
            this.W = animatorSet;
            animatorSet.addListener(new f());
            this.W.start();
        }
    }

    public final void F0(int i8) {
        if (this.X == i8 || !t.Q(this)) {
            return;
        }
        Animator animator = this.V;
        if (animator != null) {
            animator.cancel();
        }
        ArrayList arrayList = new ArrayList();
        if (this.Y == 1) {
            v0(i8, arrayList);
        } else {
            u0(i8, arrayList);
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(arrayList);
        this.V = animatorSet;
        animatorSet.addListener(new d());
        this.V.start();
    }

    public final void G0() {
        ActionMenuView actionMenuView = getActionMenuView();
        if (actionMenuView != null) {
            actionMenuView.setAlpha(1.0f);
            if (D0()) {
                J0(actionMenuView, this.X, this.f3587f0);
            } else {
                J0(actionMenuView, 0, false);
            }
        }
    }

    public final void H0() {
        getTopEdgeTreatment().l(getFabTranslationX());
        View viewA0 = A0();
        this.U.Y((this.f3587f0 && D0()) ? 1.0f : 0.0f);
        if (viewA0 != null) {
            viewA0.setTranslationY(getFabTranslationY());
            viewA0.setTranslationX(getFabTranslationX());
        }
    }

    public boolean I0(int i8) {
        float f8 = i8;
        if (f8 == getTopEdgeTreatment().f()) {
            return false;
        }
        getTopEdgeTreatment().k(f8);
        this.U.invalidateSelf();
        return true;
    }

    public final void J0(ActionMenuView actionMenuView, int i8, boolean z7) {
        actionMenuView.setTranslationX(B0(actionMenuView, i8, z7));
    }

    public ColorStateList getBackgroundTint() {
        return this.U.F();
    }

    public float getCradleVerticalOffset() {
        return getTopEdgeTreatment().c();
    }

    public int getFabAlignmentMode() {
        return this.X;
    }

    public int getFabAnimationMode() {
        return this.Y;
    }

    public float getFabCradleMargin() {
        return getTopEdgeTreatment().d();
    }

    public float getFabCradleRoundedCornerRadius() {
        return getTopEdgeTreatment().e();
    }

    public boolean getHideOnScroll() {
        return this.Z;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        e6.i.f(this, this.U);
        if (getParent() instanceof ViewGroup) {
            ((ViewGroup) getParent()).setClipChildren(false);
        }
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        super.onLayout(z7, i8, i9, i10, i11);
        if (z7) {
            t0();
            H0();
        }
        G0();
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.f());
        this.X = savedState.f3599f;
        this.f3587f0 = savedState.f3600g;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f3599f = this.X;
        savedState.f3600g = this.f3587f0;
        return savedState;
    }

    public final void s0(FloatingActionButton floatingActionButton) {
        floatingActionButton.e(this.f3592k0);
        floatingActionButton.f(new h());
        floatingActionButton.g(this.f3593l0);
    }

    public void setBackgroundTint(ColorStateList colorStateList) {
        h0.a.o(this.U, colorStateList);
    }

    public void setCradleVerticalOffset(float f8) {
        if (f8 != getCradleVerticalOffset()) {
            getTopEdgeTreatment().h(f8);
            this.U.invalidateSelf();
            H0();
        }
    }

    @Override // android.view.View
    public void setElevation(float f8) {
        this.U.W(f8);
        getBehavior().G(this, this.U.C() - this.U.B());
    }

    public void setFabAlignmentMode(int i8) {
        F0(i8);
        E0(i8, this.f3587f0);
        this.X = i8;
    }

    public void setFabAnimationMode(int i8) {
        this.Y = i8;
    }

    public void setFabCradleMargin(float f8) {
        if (f8 != getFabCradleMargin()) {
            getTopEdgeTreatment().i(f8);
            this.U.invalidateSelf();
        }
    }

    public void setFabCradleRoundedCornerRadius(float f8) {
        if (f8 != getFabCradleRoundedCornerRadius()) {
            getTopEdgeTreatment().j(f8);
            this.U.invalidateSelf();
        }
    }

    public void setHideOnScroll(boolean z7) {
        this.Z = z7;
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setSubtitle(CharSequence charSequence) {
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setTitle(CharSequence charSequence) {
    }

    public final void t0() {
        Animator animator = this.W;
        if (animator != null) {
            animator.cancel();
        }
        Animator animator2 = this.V;
        if (animator2 != null) {
            animator2.cancel();
        }
    }

    public void u0(int i8, List<Animator> list) {
        FloatingActionButton floatingActionButtonZ0 = z0();
        if (floatingActionButtonZ0 == null || floatingActionButtonZ0.n()) {
            return;
        }
        y0();
        floatingActionButtonZ0.l(new e(i8));
    }

    public final void v0(int i8, List<Animator> list) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(z0(), "translationX", C0(i8));
        objectAnimatorOfFloat.setDuration(300L);
        list.add(objectAnimatorOfFloat);
    }

    public final void w0(int i8, boolean z7, List<Animator> list) {
        ActionMenuView actionMenuView = getActionMenuView();
        if (actionMenuView == null) {
            return;
        }
        Animator animatorOfFloat = ObjectAnimator.ofFloat(actionMenuView, "alpha", 1.0f);
        if (Math.abs(actionMenuView.getTranslationX() - B0(actionMenuView, i8, z7)) <= 1.0f) {
            if (actionMenuView.getAlpha() < 1.0f) {
                list.add(animatorOfFloat);
            }
        } else {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(actionMenuView, "alpha", 0.0f);
            objectAnimatorOfFloat.addListener(new g(actionMenuView, i8, z7));
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(150L);
            animatorSet.playSequentially(objectAnimatorOfFloat, animatorOfFloat);
            list.add(animatorSet);
        }
    }

    public final void x0() {
        ArrayList<i> arrayList;
        int i8 = this.f3585d0 - 1;
        this.f3585d0 = i8;
        if (i8 != 0 || (arrayList = this.f3586e0) == null) {
            return;
        }
        Iterator<i> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().a(this);
        }
    }

    public final void y0() {
        ArrayList<i> arrayList;
        int i8 = this.f3585d0;
        this.f3585d0 = i8 + 1;
        if (i8 != 0 || (arrayList = this.f3586e0) == null) {
            return;
        }
        Iterator<i> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().b(this);
        }
    }

    public final FloatingActionButton z0() {
        View viewA0 = A0();
        if (viewA0 instanceof FloatingActionButton) {
            return (FloatingActionButton) viewA0;
        }
        return null;
    }

    public BottomAppBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.bottomAppBarStyle);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.b
    public Behavior getBehavior() {
        if (this.f3588g0 == null) {
            this.f3588g0 = new Behavior();
        }
        return this.f3588g0;
    }

    public static class Behavior extends HideBottomViewOnScrollBehavior<BottomAppBar> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final Rect f3594e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public WeakReference<BottomAppBar> f3595f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f3596g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final View.OnLayoutChangeListener f3597h;

        public class a implements View.OnLayoutChangeListener {
            public a() {
            }

            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15) {
                BottomAppBar bottomAppBar = (BottomAppBar) Behavior.this.f3595f.get();
                if (bottomAppBar == null || !(view instanceof FloatingActionButton)) {
                    view.removeOnLayoutChangeListener(this);
                    return;
                }
                FloatingActionButton floatingActionButton = (FloatingActionButton) view;
                floatingActionButton.j(Behavior.this.f3594e);
                int iHeight = Behavior.this.f3594e.height();
                bottomAppBar.I0(iHeight);
                CoordinatorLayout.f fVar = (CoordinatorLayout.f) view.getLayoutParams();
                if (Behavior.this.f3596g == 0) {
                    ((ViewGroup.MarginLayoutParams) fVar).bottomMargin = bottomAppBar.getBottomInset() + (bottomAppBar.getResources().getDimensionPixelOffset(R$dimen.mtrl_bottomappbar_fab_bottom_margin) - ((floatingActionButton.getMeasuredHeight() - iHeight) / 2));
                    ((ViewGroup.MarginLayoutParams) fVar).leftMargin = bottomAppBar.getLeftInset();
                    ((ViewGroup.MarginLayoutParams) fVar).rightMargin = bottomAppBar.getRightInset();
                    if (p.h(floatingActionButton)) {
                        ((ViewGroup.MarginLayoutParams) fVar).leftMargin += bottomAppBar.T;
                    } else {
                        ((ViewGroup.MarginLayoutParams) fVar).rightMargin += bottomAppBar.T;
                    }
                }
            }
        }

        public Behavior() {
            this.f3597h = new a();
            this.f3594e = new Rect();
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: M, reason: merged with bridge method [inline-methods] */
        public boolean l(CoordinatorLayout coordinatorLayout, BottomAppBar bottomAppBar, int i8) {
            this.f3595f = new WeakReference<>(bottomAppBar);
            View viewA0 = bottomAppBar.A0();
            if (viewA0 != null && !t.Q(viewA0)) {
                CoordinatorLayout.f fVar = (CoordinatorLayout.f) viewA0.getLayoutParams();
                fVar.f1189d = 49;
                this.f3596g = ((ViewGroup.MarginLayoutParams) fVar).bottomMargin;
                if (viewA0 instanceof FloatingActionButton) {
                    FloatingActionButton floatingActionButton = (FloatingActionButton) viewA0;
                    floatingActionButton.addOnLayoutChangeListener(this.f3597h);
                    bottomAppBar.s0(floatingActionButton);
                }
                bottomAppBar.H0();
            }
            coordinatorLayout.M(bottomAppBar, i8);
            return super.l(coordinatorLayout, bottomAppBar, i8);
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: N, reason: merged with bridge method [inline-methods] */
        public boolean A(CoordinatorLayout coordinatorLayout, BottomAppBar bottomAppBar, View view, View view2, int i8, int i9) {
            return bottomAppBar.getHideOnScroll() && super.A(coordinatorLayout, bottomAppBar, view, view2, i8, i9);
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f3597h = new a();
            this.f3594e = new Rect();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public BottomAppBar(Context context, AttributeSet attributeSet, int i8) {
        int i9 = S;
        super(i6.a.c(context, attributeSet, i8, i9), attributeSet, i8);
        e6.h hVar = new e6.h();
        this.U = hVar;
        this.f3585d0 = 0;
        this.f3587f0 = true;
        this.f3592k0 = new a();
        this.f3593l0 = new b();
        Context context2 = getContext();
        TypedArray typedArrayH = z5.k.h(context2, attributeSet, R$styleable.BottomAppBar, i8, i9, new int[0]);
        ColorStateList colorStateListA = b6.c.a(context2, typedArrayH, R$styleable.BottomAppBar_backgroundTint);
        int dimensionPixelSize = typedArrayH.getDimensionPixelSize(R$styleable.BottomAppBar_elevation, 0);
        float dimensionPixelOffset = typedArrayH.getDimensionPixelOffset(R$styleable.BottomAppBar_fabCradleMargin, 0);
        float dimensionPixelOffset2 = typedArrayH.getDimensionPixelOffset(R$styleable.BottomAppBar_fabCradleRoundedCornerRadius, 0);
        float dimensionPixelOffset3 = typedArrayH.getDimensionPixelOffset(R$styleable.BottomAppBar_fabCradleVerticalOffset, 0);
        this.X = typedArrayH.getInt(R$styleable.BottomAppBar_fabAlignmentMode, 0);
        this.Y = typedArrayH.getInt(R$styleable.BottomAppBar_fabAnimationMode, 0);
        this.Z = typedArrayH.getBoolean(R$styleable.BottomAppBar_hideOnScroll, false);
        this.f3582a0 = typedArrayH.getBoolean(R$styleable.BottomAppBar_paddingBottomSystemWindowInsets, false);
        this.f3583b0 = typedArrayH.getBoolean(R$styleable.BottomAppBar_paddingLeftSystemWindowInsets, false);
        this.f3584c0 = typedArrayH.getBoolean(R$styleable.BottomAppBar_paddingRightSystemWindowInsets, false);
        typedArrayH.recycle();
        this.T = getResources().getDimensionPixelOffset(R$dimen.mtrl_bottomappbar_fabOffsetEndMode);
        hVar.setShapeAppearanceModel(m.a().A(new m5.a(dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3)).m());
        hVar.f0(2);
        hVar.a0(Paint.Style.FILL);
        hVar.N(context2);
        setElevation(dimensionPixelSize);
        h0.a.o(hVar, colorStateListA);
        t.o0(this, hVar);
        p.a(this, attributeSet, i8, i9, new c());
    }
}
