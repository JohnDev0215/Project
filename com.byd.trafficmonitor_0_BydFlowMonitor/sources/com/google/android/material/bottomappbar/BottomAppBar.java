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
import b.h.m.b0;
import b.h.m.t;
import c.b.a.a.d0.p;
import c.b.a.a.i0.m;
import c.b.a.a.k;
import c.b.a.a.l;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BottomAppBar extends Toolbar implements CoordinatorLayout.b {
    public static final int Q = k.Widget_MaterialComponents_BottomAppBar;
    public final int R;
    public final c.b.a.a.i0.h S;
    public Animator T;
    public Animator U;
    public int V;
    public int W;
    public boolean X;
    public final boolean Y;
    public final boolean Z;
    public final boolean a0;
    public int b0;
    public ArrayList<i> c0;
    public boolean d0;
    public Behavior e0;
    public int f0;
    public int g0;
    public int h0;
    public AnimatorListenerAdapter i0;
    public c.b.a.a.m.k<FloatingActionButton> j0;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f4531d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f4532e;

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
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f4531d);
            parcel.writeInt(this.f4532e ? 1 : 0);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f4531d = parcel.readInt();
            this.f4532e = parcel.readInt() != 0;
        }
    }

    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            BottomAppBar bottomAppBar = BottomAppBar.this;
            bottomAppBar.E0(bottomAppBar.V, BottomAppBar.this.d0);
        }
    }

    public class b implements c.b.a.a.m.k<FloatingActionButton> {
        public b() {
        }

        @Override // c.b.a.a.m.k
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void a(FloatingActionButton floatingActionButton) {
            BottomAppBar.this.S.Y(floatingActionButton.getVisibility() == 0 ? floatingActionButton.getScaleY() : 0.0f);
        }

        @Override // c.b.a.a.m.k
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

    public class c implements p.e {
        public c() {
        }

        @Override // c.b.a.a.d0.p.e
        public b0 a(View view, b0 b0Var, p.f fVar) {
            boolean z;
            if (BottomAppBar.this.Y) {
                BottomAppBar.this.f0 = b0Var.e();
            }
            boolean z2 = false;
            if (BottomAppBar.this.Z) {
                z = BottomAppBar.this.h0 != b0Var.f();
                BottomAppBar.this.h0 = b0Var.f();
            } else {
                z = false;
            }
            if (BottomAppBar.this.a0) {
                boolean z3 = BottomAppBar.this.g0 != b0Var.g();
                BottomAppBar.this.g0 = b0Var.g();
                z2 = z3;
            }
            if (z || z2) {
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
        public final /* synthetic */ int f4537a;

        public class a extends FloatingActionButton.b {
            public a() {
            }

            @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.b
            public void b(FloatingActionButton floatingActionButton) {
                BottomAppBar.this.x0();
            }
        }

        public e(int i2) {
            this.f4537a = i2;
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.b
        public void a(FloatingActionButton floatingActionButton) {
            floatingActionButton.setTranslationX(BottomAppBar.this.C0(this.f4537a));
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
        public boolean f4541a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ActionMenuView f4542b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f4543c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ boolean f4544d;

        public g(ActionMenuView actionMenuView, int i2, boolean z) {
            this.f4542b = actionMenuView;
            this.f4543c = i2;
            this.f4544d = z;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f4541a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f4541a) {
                return;
            }
            BottomAppBar.this.J0(this.f4542b, this.f4543c, this.f4544d);
        }
    }

    public class h extends AnimatorListenerAdapter {
        public h() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            BottomAppBar.this.i0.onAnimationStart(animator);
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
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt instanceof ActionMenuView) {
                return (ActionMenuView) childAt;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getBottomInset() {
        return this.f0;
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
        return this.h0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getRightInset() {
        return this.g0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public c.b.a.a.p.a getTopEdgeTreatment() {
        return (c.b.a.a.p.a) this.S.D().p();
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

    public int B0(ActionMenuView actionMenuView, int i2, boolean z) {
        if (i2 != 1 || !z) {
            return 0;
        }
        boolean zH = p.h(this);
        int measuredWidth = zH ? getMeasuredWidth() : 0;
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            View childAt = getChildAt(i3);
            if ((childAt.getLayoutParams() instanceof Toolbar.e) && (((Toolbar.e) childAt.getLayoutParams()).f1426a & 8388615) == 8388611) {
                measuredWidth = zH ? Math.min(measuredWidth, childAt.getLeft()) : Math.max(measuredWidth, childAt.getRight());
            }
        }
        return measuredWidth - ((zH ? actionMenuView.getRight() : actionMenuView.getLeft()) + (zH ? this.g0 : -this.h0));
    }

    public final float C0(int i2) {
        boolean zH = p.h(this);
        if (i2 == 1) {
            return ((getMeasuredWidth() / 2) - (this.R + (zH ? this.h0 : this.g0))) * (zH ? -1 : 1);
        }
        return 0.0f;
    }

    public final boolean D0() {
        FloatingActionButton floatingActionButtonZ0 = z0();
        return floatingActionButtonZ0 != null && floatingActionButtonZ0.o();
    }

    public final void E0(int i2, boolean z) {
        if (t.P(this)) {
            Animator animator = this.U;
            if (animator != null) {
                animator.cancel();
            }
            ArrayList arrayList = new ArrayList();
            if (!D0()) {
                i2 = 0;
                z = false;
            }
            w0(i2, z, arrayList);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(arrayList);
            this.U = animatorSet;
            animatorSet.addListener(new f());
            this.U.start();
        }
    }

    public final void F0(int i2) {
        if (this.V == i2 || !t.P(this)) {
            return;
        }
        Animator animator = this.T;
        if (animator != null) {
            animator.cancel();
        }
        ArrayList arrayList = new ArrayList();
        if (this.W == 1) {
            v0(i2, arrayList);
        } else {
            u0(i2, arrayList);
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
                J0(actionMenuView, this.V, this.d0);
            } else {
                J0(actionMenuView, 0, false);
            }
        }
    }

    public final void H0() {
        getTopEdgeTreatment().l(getFabTranslationX());
        View viewA0 = A0();
        this.S.Y((this.d0 && D0()) ? 1.0f : 0.0f);
        if (viewA0 != null) {
            viewA0.setTranslationY(getFabTranslationY());
            viewA0.setTranslationX(getFabTranslationX());
        }
    }

    public boolean I0(int i2) {
        float f2 = i2;
        if (f2 == getTopEdgeTreatment().f()) {
            return false;
        }
        getTopEdgeTreatment().k(f2);
        this.S.invalidateSelf();
        return true;
    }

    public final void J0(ActionMenuView actionMenuView, int i2, boolean z) {
        actionMenuView.setTranslationX(B0(actionMenuView, i2, z));
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
        c.b.a.a.i0.i.f(this, this.S);
        if (getParent() instanceof ViewGroup) {
            ((ViewGroup) getParent()).setClipChildren(false);
        }
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (z) {
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
        super.onRestoreInstanceState(savedState.j());
        this.V = savedState.f4531d;
        this.d0 = savedState.f4532e;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f4531d = this.V;
        savedState.f4532e = this.d0;
        return savedState;
    }

    public final void s0(FloatingActionButton floatingActionButton) {
        floatingActionButton.e(this.i0);
        floatingActionButton.f(new h());
        floatingActionButton.g(this.j0);
    }

    public void setBackgroundTint(ColorStateList colorStateList) {
        b.h.f.l.a.o(this.S, colorStateList);
    }

    public void setCradleVerticalOffset(float f2) {
        if (f2 != getCradleVerticalOffset()) {
            getTopEdgeTreatment().h(f2);
            this.S.invalidateSelf();
            H0();
        }
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        this.S.W(f2);
        getBehavior().G(this, this.S.C() - this.S.B());
    }

    public void setFabAlignmentMode(int i2) {
        F0(i2);
        E0(i2, this.d0);
        this.V = i2;
    }

    public void setFabAnimationMode(int i2) {
        this.W = i2;
    }

    public void setFabCradleMargin(float f2) {
        if (f2 != getFabCradleMargin()) {
            getTopEdgeTreatment().i(f2);
            this.S.invalidateSelf();
        }
    }

    public void setFabCradleRoundedCornerRadius(float f2) {
        if (f2 != getFabCradleRoundedCornerRadius()) {
            getTopEdgeTreatment().j(f2);
            this.S.invalidateSelf();
        }
    }

    public void setHideOnScroll(boolean z) {
        this.X = z;
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

    public void u0(int i2, List<Animator> list) {
        FloatingActionButton floatingActionButtonZ0 = z0();
        if (floatingActionButtonZ0 == null || floatingActionButtonZ0.n()) {
            return;
        }
        y0();
        floatingActionButtonZ0.l(new e(i2));
    }

    public final void v0(int i2, List<Animator> list) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(z0(), "translationX", C0(i2));
        objectAnimatorOfFloat.setDuration(300L);
        list.add(objectAnimatorOfFloat);
    }

    public final void w0(int i2, boolean z, List<Animator> list) {
        ActionMenuView actionMenuView = getActionMenuView();
        if (actionMenuView == null) {
            return;
        }
        Animator animatorOfFloat = ObjectAnimator.ofFloat(actionMenuView, "alpha", 1.0f);
        if (Math.abs(actionMenuView.getTranslationX() - B0(actionMenuView, i2, z)) <= 1.0f) {
            if (actionMenuView.getAlpha() < 1.0f) {
                list.add(animatorOfFloat);
            }
        } else {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(actionMenuView, "alpha", 0.0f);
            objectAnimatorOfFloat.addListener(new g(actionMenuView, i2, z));
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(150L);
            animatorSet.playSequentially(objectAnimatorOfFloat, animatorOfFloat);
            list.add(animatorSet);
        }
    }

    public final void x0() {
        ArrayList<i> arrayList;
        int i2 = this.b0 - 1;
        this.b0 = i2;
        if (i2 != 0 || (arrayList = this.c0) == null) {
            return;
        }
        Iterator<i> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().a(this);
        }
    }

    public final void y0() {
        ArrayList<i> arrayList;
        int i2 = this.b0;
        this.b0 = i2 + 1;
        if (i2 != 0 || (arrayList = this.c0) == null) {
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
        this(context, attributeSet, c.b.a.a.b.bottomAppBarStyle);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.b
    public Behavior getBehavior() {
        if (this.e0 == null) {
            this.e0 = new Behavior();
        }
        return this.e0;
    }

    public static class Behavior extends HideBottomViewOnScrollBehavior<BottomAppBar> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final Rect f4526e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public WeakReference<BottomAppBar> f4527f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f4528g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final View.OnLayoutChangeListener f4529h;

        public class a implements View.OnLayoutChangeListener {
            public a() {
            }

            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                BottomAppBar bottomAppBar = (BottomAppBar) Behavior.this.f4527f.get();
                if (bottomAppBar == null || !(view instanceof FloatingActionButton)) {
                    view.removeOnLayoutChangeListener(this);
                    return;
                }
                FloatingActionButton floatingActionButton = (FloatingActionButton) view;
                floatingActionButton.j(Behavior.this.f4526e);
                int iHeight = Behavior.this.f4526e.height();
                bottomAppBar.I0(iHeight);
                CoordinatorLayout.f fVar = (CoordinatorLayout.f) view.getLayoutParams();
                if (Behavior.this.f4528g == 0) {
                    ((ViewGroup.MarginLayoutParams) fVar).bottomMargin = bottomAppBar.getBottomInset() + (bottomAppBar.getResources().getDimensionPixelOffset(c.b.a.a.d.mtrl_bottomappbar_fab_bottom_margin) - ((floatingActionButton.getMeasuredHeight() - iHeight) / 2));
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
            this.f4529h = new a();
            this.f4526e = new Rect();
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: M, reason: merged with bridge method [inline-methods] */
        public boolean l(CoordinatorLayout coordinatorLayout, BottomAppBar bottomAppBar, int i2) {
            this.f4527f = new WeakReference<>(bottomAppBar);
            View viewA0 = bottomAppBar.A0();
            if (viewA0 != null && !t.P(viewA0)) {
                CoordinatorLayout.f fVar = (CoordinatorLayout.f) viewA0.getLayoutParams();
                fVar.f753d = 49;
                this.f4528g = ((ViewGroup.MarginLayoutParams) fVar).bottomMargin;
                if (viewA0 instanceof FloatingActionButton) {
                    FloatingActionButton floatingActionButton = (FloatingActionButton) viewA0;
                    floatingActionButton.addOnLayoutChangeListener(this.f4529h);
                    bottomAppBar.s0(floatingActionButton);
                }
                bottomAppBar.H0();
            }
            coordinatorLayout.M(bottomAppBar, i2);
            return super.l(coordinatorLayout, bottomAppBar, i2);
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: N, reason: merged with bridge method [inline-methods] */
        public boolean A(CoordinatorLayout coordinatorLayout, BottomAppBar bottomAppBar, View view, View view2, int i2, int i3) {
            return bottomAppBar.getHideOnScroll() && super.A(coordinatorLayout, bottomAppBar, view, view2, i2, i3);
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f4529h = new a();
            this.f4526e = new Rect();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public BottomAppBar(Context context, AttributeSet attributeSet, int i2) {
        int i3 = Q;
        super(c.b.a.a.m0.a.a.c(context, attributeSet, i2, i3), attributeSet, i2);
        c.b.a.a.i0.h hVar = new c.b.a.a.i0.h();
        this.S = hVar;
        this.b0 = 0;
        this.d0 = true;
        this.i0 = new a();
        this.j0 = new b();
        Context context2 = getContext();
        TypedArray typedArrayH = c.b.a.a.d0.k.h(context2, attributeSet, l.BottomAppBar, i2, i3, new int[0]);
        ColorStateList colorStateListA = c.b.a.a.f0.c.a(context2, typedArrayH, l.BottomAppBar_backgroundTint);
        int dimensionPixelSize = typedArrayH.getDimensionPixelSize(l.BottomAppBar_elevation, 0);
        float dimensionPixelOffset = typedArrayH.getDimensionPixelOffset(l.BottomAppBar_fabCradleMargin, 0);
        float dimensionPixelOffset2 = typedArrayH.getDimensionPixelOffset(l.BottomAppBar_fabCradleRoundedCornerRadius, 0);
        float dimensionPixelOffset3 = typedArrayH.getDimensionPixelOffset(l.BottomAppBar_fabCradleVerticalOffset, 0);
        this.V = typedArrayH.getInt(l.BottomAppBar_fabAlignmentMode, 0);
        this.W = typedArrayH.getInt(l.BottomAppBar_fabAnimationMode, 0);
        this.X = typedArrayH.getBoolean(l.BottomAppBar_hideOnScroll, false);
        this.Y = typedArrayH.getBoolean(l.BottomAppBar_paddingBottomSystemWindowInsets, false);
        this.Z = typedArrayH.getBoolean(l.BottomAppBar_paddingLeftSystemWindowInsets, false);
        this.a0 = typedArrayH.getBoolean(l.BottomAppBar_paddingRightSystemWindowInsets, false);
        typedArrayH.recycle();
        this.R = getResources().getDimensionPixelOffset(c.b.a.a.d.mtrl_bottomappbar_fabOffsetEndMode);
        hVar.setShapeAppearanceModel(m.a().A(new c.b.a.a.p.a(dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3)).m());
        hVar.f0(2);
        hVar.a0(Paint.Style.FILL);
        hVar.N(context2);
        setElevation(dimensionPixelSize);
        b.h.f.l.a.o(hVar, colorStateListA);
        t.n0(this, hVar);
        p.a(this, attributeSet, i2, i3, new c());
    }
}
