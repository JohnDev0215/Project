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
import i2.p;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import l0.b0;
import l0.t;
import n2.m;
import s1.k;

/* JADX INFO: loaded from: classes.dex */
public class BottomAppBar extends Toolbar implements CoordinatorLayout.b {
    public static final int Q = R$style.Widget_MaterialComponents_BottomAppBar;
    public final int R;
    public final n2.h S;
    public Animator T;
    public Animator U;
    public int V;
    public int W;
    public boolean X;
    public final boolean Y;
    public final boolean Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public final boolean f1999a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public int f2000b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public ArrayList<i> f2001c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public boolean f2002d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public Behavior f2003e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public int f2004f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public int f2005g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public int f2006h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public AnimatorListenerAdapter f2007i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public k<FloatingActionButton> f2008j0;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f2014d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f2015e;

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

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i3) {
            super.writeToParcel(parcel, i3);
            parcel.writeInt(this.f2014d);
            parcel.writeInt(this.f2015e ? 1 : 0);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f2014d = parcel.readInt();
            this.f2015e = parcel.readInt() != 0;
        }
    }

    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            BottomAppBar bottomAppBar = BottomAppBar.this;
            bottomAppBar.E0(bottomAppBar.V, BottomAppBar.this.f2002d0);
        }
    }

    public class b implements k<FloatingActionButton> {
        public b() {
        }

        @Override // s1.k
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void a(FloatingActionButton floatingActionButton) {
            BottomAppBar.this.S.Y(floatingActionButton.getVisibility() == 0 ? floatingActionButton.getScaleY() : 0.0f);
        }

        @Override // s1.k
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(FloatingActionButton floatingActionButton) {
            float translationX = floatingActionButton.getTranslationX();
            if (BottomAppBar.this.getTopEdgeTreatment().g() != translationX) {
                BottomAppBar.this.getTopEdgeTreatment().l(translationX);
                BottomAppBar.this.S.invalidateSelf();
            }
            float fMax = Math.max(0.0f, -floatingActionButton.getTranslationY());
            if (BottomAppBar.this.getTopEdgeTreatment().c() != fMax) {
                BottomAppBar.this.getTopEdgeTreatment().h(fMax);
                BottomAppBar.this.S.invalidateSelf();
            }
            BottomAppBar.this.S.Y(floatingActionButton.getVisibility() == 0 ? floatingActionButton.getScaleY() : 0.0f);
        }
    }

    public class c implements p.d {
        public c() {
        }

        @Override // i2.p.d
        public b0 a(View view, b0 b0Var, p.e eVar) {
            boolean z3;
            if (BottomAppBar.this.Y) {
                BottomAppBar.this.f2004f0 = b0Var.e();
            }
            boolean z4 = false;
            if (BottomAppBar.this.Z) {
                z3 = BottomAppBar.this.f2006h0 != b0Var.f();
                BottomAppBar.this.f2006h0 = b0Var.f();
            } else {
                z3 = false;
            }
            if (BottomAppBar.this.f1999a0) {
                boolean z5 = BottomAppBar.this.f2005g0 != b0Var.g();
                BottomAppBar.this.f2005g0 = b0Var.g();
                z4 = z5;
            }
            if (z3 || z4) {
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
        public final /* synthetic */ int f2020a;

        public class a extends FloatingActionButton.b {
            public a() {
            }

            @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.b
            public void b(FloatingActionButton floatingActionButton) {
                BottomAppBar.this.x0();
            }
        }

        public e(int i3) {
            this.f2020a = i3;
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.b
        public void a(FloatingActionButton floatingActionButton) {
            floatingActionButton.setTranslationX(BottomAppBar.this.C0(this.f2020a));
            floatingActionButton.s(new a());
        }
    }

    public class f extends AnimatorListenerAdapter {
        public f() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            BottomAppBar.this.x0();
            BottomAppBar.this.U = null;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            BottomAppBar.this.y0();
        }
    }

    public class g extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f2024a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ActionMenuView f2025b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f2026c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ boolean f2027d;

        public g(ActionMenuView actionMenuView, int i3, boolean z3) {
            this.f2025b = actionMenuView;
            this.f2026c = i3;
            this.f2027d = z3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f2024a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f2024a) {
                return;
            }
            BottomAppBar.this.J0(this.f2025b, this.f2026c, this.f2027d);
        }
    }

    public class h extends AnimatorListenerAdapter {
        public h() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            BottomAppBar.this.f2007i0.onAnimationStart(animator);
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
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            View childAt = getChildAt(i3);
            if (childAt instanceof ActionMenuView) {
                return (ActionMenuView) childAt;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getBottomInset() {
        return this.f2004f0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getFabTranslationX() {
        return C0(this.V);
    }

    private float getFabTranslationY() {
        return -getTopEdgeTreatment().c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getLeftInset() {
        return this.f2006h0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getRightInset() {
        return this.f2005g0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public v1.a getTopEdgeTreatment() {
        return (v1.a) this.S.D().p();
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

    public int B0(ActionMenuView actionMenuView, int i3, boolean z3) {
        if (i3 != 1 || !z3) {
            return 0;
        }
        boolean zH = p.h(this);
        int measuredWidth = zH ? getMeasuredWidth() : 0;
        for (int i4 = 0; i4 < getChildCount(); i4++) {
            View childAt = getChildAt(i4);
            if ((childAt.getLayoutParams() instanceof Toolbar.e) && (((Toolbar.e) childAt.getLayoutParams()).f3135a & 8388615) == 8388611) {
                measuredWidth = zH ? Math.min(measuredWidth, childAt.getLeft()) : Math.max(measuredWidth, childAt.getRight());
            }
        }
        return measuredWidth - ((zH ? actionMenuView.getRight() : actionMenuView.getLeft()) + (zH ? this.f2005g0 : -this.f2006h0));
    }

    public final float C0(int i3) {
        boolean zH = p.h(this);
        if (i3 == 1) {
            return ((getMeasuredWidth() / 2) - (this.R + (zH ? this.f2006h0 : this.f2005g0))) * (zH ? -1 : 1);
        }
        return 0.0f;
    }

    public final boolean D0() {
        FloatingActionButton floatingActionButtonZ0 = z0();
        return floatingActionButtonZ0 != null && floatingActionButtonZ0.o();
    }

    public final void E0(int i3, boolean z3) {
        if (t.P(this)) {
            Animator animator = this.U;
            if (animator != null) {
                animator.cancel();
            }
            ArrayList arrayList = new ArrayList();
            if (!D0()) {
                i3 = 0;
                z3 = false;
            }
            w0(i3, z3, arrayList);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(arrayList);
            this.U = animatorSet;
            animatorSet.addListener(new f());
            this.U.start();
        }
    }

    public final void F0(int i3) {
        if (this.V == i3 || !t.P(this)) {
            return;
        }
        Animator animator = this.T;
        if (animator != null) {
            animator.cancel();
        }
        ArrayList arrayList = new ArrayList();
        if (this.W == 1) {
            v0(i3, arrayList);
        } else {
            u0(i3, arrayList);
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(arrayList);
        this.T = animatorSet;
        animatorSet.addListener(new d());
        this.T.start();
    }

    public final void G0() {
        ActionMenuView actionMenuView = getActionMenuView();
        if (actionMenuView != null) {
            actionMenuView.setAlpha(1.0f);
            if (D0()) {
                J0(actionMenuView, this.V, this.f2002d0);
            } else {
                J0(actionMenuView, 0, false);
            }
        }
    }

    public final void H0() {
        getTopEdgeTreatment().l(getFabTranslationX());
        View viewA0 = A0();
        this.S.Y((this.f2002d0 && D0()) ? 1.0f : 0.0f);
        if (viewA0 != null) {
            viewA0.setTranslationY(getFabTranslationY());
            viewA0.setTranslationX(getFabTranslationX());
        }
    }

    public boolean I0(int i3) {
        float f3 = i3;
        if (f3 == getTopEdgeTreatment().f()) {
            return false;
        }
        getTopEdgeTreatment().k(f3);
        this.S.invalidateSelf();
        return true;
    }

    public final void J0(ActionMenuView actionMenuView, int i3, boolean z3) {
        actionMenuView.setTranslationX(B0(actionMenuView, i3, z3));
    }

    public ColorStateList getBackgroundTint() {
        return this.S.F();
    }

    public float getCradleVerticalOffset() {
        return getTopEdgeTreatment().c();
    }

    public int getFabAlignmentMode() {
        return this.V;
    }

    public int getFabAnimationMode() {
        return this.W;
    }

    public float getFabCradleMargin() {
        return getTopEdgeTreatment().d();
    }

    public float getFabCradleRoundedCornerRadius() {
        return getTopEdgeTreatment().e();
    }

    public boolean getHideOnScroll() {
        return this.X;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        n2.i.f(this, this.S);
        if (getParent() instanceof ViewGroup) {
            ((ViewGroup) getParent()).setClipChildren(false);
        }
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z3, int i3, int i4, int i5, int i6) {
        super.onLayout(z3, i3, i4, i5, i6);
        if (z3) {
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
        this.V = savedState.f2014d;
        this.f2002d0 = savedState.f2015e;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f2014d = this.V;
        savedState.f2015e = this.f2002d0;
        return savedState;
    }

    public final void s0(FloatingActionButton floatingActionButton) {
        floatingActionButton.e(this.f2007i0);
        floatingActionButton.f(new h());
        floatingActionButton.g(this.f2008j0);
    }

    public void setBackgroundTint(ColorStateList colorStateList) {
        e0.a.o(this.S, colorStateList);
    }

    public void setCradleVerticalOffset(float f3) {
        if (f3 != getCradleVerticalOffset()) {
            getTopEdgeTreatment().h(f3);
            this.S.invalidateSelf();
            H0();
        }
    }

    @Override // android.view.View
    public void setElevation(float f3) {
        this.S.W(f3);
        getBehavior().G(this, this.S.C() - this.S.B());
    }

    public void setFabAlignmentMode(int i3) {
        F0(i3);
        E0(i3, this.f2002d0);
        this.V = i3;
    }

    public void setFabAnimationMode(int i3) {
        this.W = i3;
    }

    public void setFabCradleMargin(float f3) {
        if (f3 != getFabCradleMargin()) {
            getTopEdgeTreatment().i(f3);
            this.S.invalidateSelf();
        }
    }

    public void setFabCradleRoundedCornerRadius(float f3) {
        if (f3 != getFabCradleRoundedCornerRadius()) {
            getTopEdgeTreatment().j(f3);
            this.S.invalidateSelf();
        }
    }

    public void setHideOnScroll(boolean z3) {
        this.X = z3;
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setSubtitle(CharSequence charSequence) {
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setTitle(CharSequence charSequence) {
    }

    public final void t0() {
        Animator animator = this.U;
        if (animator != null) {
            animator.cancel();
        }
        Animator animator2 = this.T;
        if (animator2 != null) {
            animator2.cancel();
        }
    }

    public void u0(int i3, List<Animator> list) {
        FloatingActionButton floatingActionButtonZ0 = z0();
        if (floatingActionButtonZ0 == null || floatingActionButtonZ0.n()) {
            return;
        }
        y0();
        floatingActionButtonZ0.l(new e(i3));
    }

    public final void v0(int i3, List<Animator> list) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(z0(), "translationX", C0(i3));
        objectAnimatorOfFloat.setDuration(300L);
        list.add(objectAnimatorOfFloat);
    }

    public final void w0(int i3, boolean z3, List<Animator> list) {
        ActionMenuView actionMenuView = getActionMenuView();
        if (actionMenuView == null) {
            return;
        }
        Animator animatorOfFloat = ObjectAnimator.ofFloat(actionMenuView, "alpha", 1.0f);
        if (Math.abs(actionMenuView.getTranslationX() - B0(actionMenuView, i3, z3)) <= 1.0f) {
            if (actionMenuView.getAlpha() < 1.0f) {
                list.add(animatorOfFloat);
            }
        } else {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(actionMenuView, "alpha", 0.0f);
            objectAnimatorOfFloat.addListener(new g(actionMenuView, i3, z3));
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(150L);
            animatorSet.playSequentially(objectAnimatorOfFloat, animatorOfFloat);
            list.add(animatorSet);
        }
    }

    public final void x0() {
        ArrayList<i> arrayList;
        int i3 = this.f2000b0 - 1;
        this.f2000b0 = i3;
        if (i3 != 0 || (arrayList = this.f2001c0) == null) {
            return;
        }
        Iterator<i> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().a(this);
        }
    }

    public final void y0() {
        ArrayList<i> arrayList;
        int i3 = this.f2000b0;
        this.f2000b0 = i3 + 1;
        if (i3 != 0 || (arrayList = this.f2001c0) == null) {
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
        if (this.f2003e0 == null) {
            this.f2003e0 = new Behavior();
        }
        return this.f2003e0;
    }

    public static class Behavior extends HideBottomViewOnScrollBehavior<BottomAppBar> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final Rect f2009e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public WeakReference<BottomAppBar> f2010f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f2011g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final View.OnLayoutChangeListener f2012h;

        public class a implements View.OnLayoutChangeListener {
            public a() {
            }

            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
                BottomAppBar bottomAppBar = (BottomAppBar) Behavior.this.f2010f.get();
                if (bottomAppBar == null || !(view instanceof FloatingActionButton)) {
                    view.removeOnLayoutChangeListener(this);
                    return;
                }
                FloatingActionButton floatingActionButton = (FloatingActionButton) view;
                floatingActionButton.j(Behavior.this.f2009e);
                int iHeight = Behavior.this.f2009e.height();
                bottomAppBar.I0(iHeight);
                CoordinatorLayout.f fVar = (CoordinatorLayout.f) view.getLayoutParams();
                if (Behavior.this.f2011g == 0) {
                    ((ViewGroup.MarginLayoutParams) fVar).bottomMargin = bottomAppBar.getBottomInset() + (bottomAppBar.getResources().getDimensionPixelOffset(R$dimen.mtrl_bottomappbar_fab_bottom_margin) - ((floatingActionButton.getMeasuredHeight() - iHeight) / 2));
                    ((ViewGroup.MarginLayoutParams) fVar).leftMargin = bottomAppBar.getLeftInset();
                    ((ViewGroup.MarginLayoutParams) fVar).rightMargin = bottomAppBar.getRightInset();
                    if (p.h(floatingActionButton)) {
                        ((ViewGroup.MarginLayoutParams) fVar).leftMargin += bottomAppBar.R;
                    } else {
                        ((ViewGroup.MarginLayoutParams) fVar).rightMargin += bottomAppBar.R;
                    }
                }
            }
        }

        public Behavior() {
            this.f2012h = new a();
            this.f2009e = new Rect();
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: M, reason: merged with bridge method [inline-methods] */
        public boolean l(CoordinatorLayout coordinatorLayout, BottomAppBar bottomAppBar, int i3) {
            this.f2010f = new WeakReference<>(bottomAppBar);
            View viewA0 = bottomAppBar.A0();
            if (viewA0 != null && !t.P(viewA0)) {
                CoordinatorLayout.f fVar = (CoordinatorLayout.f) viewA0.getLayoutParams();
                fVar.f1100d = 49;
                this.f2011g = ((ViewGroup.MarginLayoutParams) fVar).bottomMargin;
                if (viewA0 instanceof FloatingActionButton) {
                    FloatingActionButton floatingActionButton = (FloatingActionButton) viewA0;
                    floatingActionButton.addOnLayoutChangeListener(this.f2012h);
                    bottomAppBar.s0(floatingActionButton);
                }
                bottomAppBar.H0();
            }
            coordinatorLayout.M(bottomAppBar, i3);
            return super.l(coordinatorLayout, bottomAppBar, i3);
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: N, reason: merged with bridge method [inline-methods] */
        public boolean A(CoordinatorLayout coordinatorLayout, BottomAppBar bottomAppBar, View view, View view2, int i3, int i4) {
            return bottomAppBar.getHideOnScroll() && super.A(coordinatorLayout, bottomAppBar, view, view2, i3, i4);
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f2012h = new a();
            this.f2009e = new Rect();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public BottomAppBar(Context context, AttributeSet attributeSet, int i3) {
        int i4 = Q;
        super(r2.a.c(context, attributeSet, i3, i4), attributeSet, i3);
        n2.h hVar = new n2.h();
        this.S = hVar;
        this.f2000b0 = 0;
        this.f2002d0 = true;
        this.f2007i0 = new a();
        this.f2008j0 = new b();
        Context context2 = getContext();
        TypedArray typedArrayH = i2.k.h(context2, attributeSet, R$styleable.BottomAppBar, i3, i4, new int[0]);
        ColorStateList colorStateListA = k2.c.a(context2, typedArrayH, R$styleable.BottomAppBar_backgroundTint);
        int dimensionPixelSize = typedArrayH.getDimensionPixelSize(R$styleable.BottomAppBar_elevation, 0);
        float dimensionPixelOffset = typedArrayH.getDimensionPixelOffset(R$styleable.BottomAppBar_fabCradleMargin, 0);
        float dimensionPixelOffset2 = typedArrayH.getDimensionPixelOffset(R$styleable.BottomAppBar_fabCradleRoundedCornerRadius, 0);
        float dimensionPixelOffset3 = typedArrayH.getDimensionPixelOffset(R$styleable.BottomAppBar_fabCradleVerticalOffset, 0);
        this.V = typedArrayH.getInt(R$styleable.BottomAppBar_fabAlignmentMode, 0);
        this.W = typedArrayH.getInt(R$styleable.BottomAppBar_fabAnimationMode, 0);
        this.X = typedArrayH.getBoolean(R$styleable.BottomAppBar_hideOnScroll, false);
        this.Y = typedArrayH.getBoolean(R$styleable.BottomAppBar_paddingBottomSystemWindowInsets, false);
        this.Z = typedArrayH.getBoolean(R$styleable.BottomAppBar_paddingLeftSystemWindowInsets, false);
        this.f1999a0 = typedArrayH.getBoolean(R$styleable.BottomAppBar_paddingRightSystemWindowInsets, false);
        typedArrayH.recycle();
        this.R = getResources().getDimensionPixelOffset(R$dimen.mtrl_bottomappbar_fabOffsetEndMode);
        hVar.setShapeAppearanceModel(m.a().A(new v1.a(dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3)).m());
        hVar.f0(2);
        hVar.a0(Paint.Style.FILL);
        hVar.N(context2);
        setElevation(dimensionPixelSize);
        e0.a.o(hVar, colorStateListA);
        t.m0(this, hVar);
        p.a(this, attributeSet, i3, i4, new c());
    }
}
