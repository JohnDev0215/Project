package com.google.android.material.bottomappbar;

import a.b.k.l;
import a.h.k.q;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import b.d.a.a.o.b;
import b.d.a.a.o.c;
import b.d.a.a.o.d;
import b.d.a.a.o.f;
import b.d.a.a.o.g;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class BottomAppBar extends Toolbar implements CoordinatorLayout.b {
    public Animator N;
    public Animator O;
    public int P;
    public int Q;
    public boolean R;
    public int S;
    public int T;
    public boolean U;
    public boolean V;
    public Behavior W;
    public AnimatorListenerAdapter X;

    public static class Behavior extends HideBottomViewOnScrollBehavior<BottomAppBar> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final Rect f2378e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public WeakReference<BottomAppBar> f2379f;
        public int g;
        public final View.OnLayoutChangeListener h;

        public class a implements View.OnLayoutChangeListener {
            public a() {
            }

            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                if (Behavior.this.f2379f.get() == null || !(view instanceof FloatingActionButton)) {
                    view.removeOnLayoutChangeListener(this);
                } else {
                    FloatingActionButton floatingActionButton = (FloatingActionButton) view;
                    Behavior.this.f2378e.set(0, 0, floatingActionButton.getMeasuredWidth(), floatingActionButton.getMeasuredHeight());
                    throw null;
                }
            }
        }

        public Behavior() {
            this.h = new a();
            this.f2378e = new Rect();
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.h = new a();
            this.f2378e = new Rect();
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public boolean k(CoordinatorLayout coordinatorLayout, View view, int i) {
            BottomAppBar bottomAppBar = (BottomAppBar) view;
            this.f2379f = new WeakReference<>(bottomAppBar);
            View viewA = bottomAppBar.A();
            if (viewA == null || q.C(viewA)) {
                coordinatorLayout.q(bottomAppBar, i);
                this.f2361a = bottomAppBar.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) bottomAppBar.getLayoutParams()).bottomMargin;
                return false;
            }
            CoordinatorLayout.f fVar = (CoordinatorLayout.f) viewA.getLayoutParams();
            fVar.f1517d = 49;
            this.g = ((ViewGroup.MarginLayoutParams) fVar).bottomMargin;
            if (viewA instanceof FloatingActionButton) {
                FloatingActionButton floatingActionButton = (FloatingActionButton) viewA;
                floatingActionButton.addOnLayoutChangeListener(this.h);
                floatingActionButton.d(null);
                floatingActionButton.e(new f(bottomAppBar));
                floatingActionButton.f(null);
            }
            bottomAppBar.E();
            throw null;
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public boolean x(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i, int i2) {
            if (((BottomAppBar) view).getHideOnScroll()) {
                if (i == 2) {
                    return true;
                }
            }
            return false;
        }
    }

    public static class a extends a.j.a.a {
        public static final Parcelable.Creator<a> CREATOR = new C0056a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2381c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f2382d;

        /* JADX INFO: renamed from: com.google.android.material.bottomappbar.BottomAppBar$a$a, reason: collision with other inner class name */
        public static class C0056a implements Parcelable.ClassLoaderCreator<a> {
            @Override // android.os.Parcelable.Creator
            public Object createFromParcel(Parcel parcel) {
                return new a(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            public Object[] newArray(int i) {
                return new a[i];
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            public a createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new a(parcel, classLoader);
            }
        }

        public a(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f2381c = parcel.readInt();
            this.f2382d = parcel.readInt() != 0;
        }

        public a(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // a.j.a.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.f892a, i);
            parcel.writeInt(this.f2381c);
            parcel.writeInt(this.f2382d ? 1 : 0);
        }
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

    private int getBottomInset() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getFabTranslationX() {
        return C(this.P);
    }

    private float getFabTranslationY() {
        return -getTopEdgeTreatment().f2036c;
    }

    private int getLeftInset() {
        return 0;
    }

    private int getRightInset() {
        return 0;
    }

    private g getTopEdgeTreatment() {
        throw null;
    }

    public static void w(BottomAppBar bottomAppBar) {
        bottomAppBar.S--;
    }

    public final View A() {
        if (!(getParent() instanceof CoordinatorLayout)) {
            return null;
        }
        for (View view : ((CoordinatorLayout) getParent()).e(this)) {
            if ((view instanceof FloatingActionButton) || (view instanceof ExtendedFloatingActionButton)) {
                return view;
            }
        }
        return null;
    }

    public int B(ActionMenuView actionMenuView, int i, boolean z) {
        if (i != 1 || !z) {
            return 0;
        }
        boolean zM = l.i.M(this);
        int measuredWidth = zM ? getMeasuredWidth() : 0;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if ((childAt.getLayoutParams() instanceof Toolbar.e) && (((Toolbar.e) childAt.getLayoutParams()).f10a & 8388615) == 8388611) {
                measuredWidth = zM ? Math.min(measuredWidth, childAt.getLeft()) : Math.max(measuredWidth, childAt.getRight());
            }
        }
        return measuredWidth - ((zM ? actionMenuView.getRight() : actionMenuView.getLeft()) + 0);
    }

    public final float C(int i) {
        boolean zM = l.i.M(this);
        if (i == 1) {
            return ((getMeasuredWidth() / 2) + 0) * (zM ? -1 : 1);
        }
        return 0.0f;
    }

    public final boolean D() {
        FloatingActionButton floatingActionButtonZ = z();
        return floatingActionButtonZ != null && floatingActionButtonZ.k();
    }

    public final void E() {
        getTopEdgeTreatment().f2037d = getFabTranslationX();
        A();
        if (this.V) {
            D();
        }
        throw null;
    }

    public ColorStateList getBackgroundTint() {
        throw null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.b
    public Behavior getBehavior() {
        if (this.W == null) {
            this.W = new Behavior();
        }
        return this.W;
    }

    public float getCradleVerticalOffset() {
        return getTopEdgeTreatment().f2036c;
    }

    public int getFabAlignmentMode() {
        return this.P;
    }

    public int getFabAnimationMode() {
        return this.Q;
    }

    public float getFabCradleMargin() {
        return getTopEdgeTreatment().f2035b;
    }

    public float getFabCradleRoundedCornerRadius() {
        return getTopEdgeTreatment().f2034a;
    }

    public boolean getHideOnScroll() {
        return this.R;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        throw null;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (!z) {
            ActionMenuView actionMenuView = getActionMenuView();
            if (actionMenuView == null || this.O != null) {
                return;
            }
            actionMenuView.setAlpha(1.0f);
            actionMenuView.setTranslationX(!D() ? B(actionMenuView, 0, false) : B(actionMenuView, this.P, this.V));
            return;
        }
        Animator animator = this.O;
        if (animator != null) {
            animator.cancel();
        }
        Animator animator2 = this.N;
        if (animator2 != null) {
            animator2.cancel();
        }
        E();
        throw null;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof a)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        a aVar = (a) parcelable;
        super.onRestoreInstanceState(aVar.f892a);
        this.P = aVar.f2381c;
        this.V = aVar.f2382d;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    public Parcelable onSaveInstanceState() {
        a aVar = new a(super.onSaveInstanceState());
        aVar.f2381c = this.P;
        aVar.f2382d = this.V;
        return aVar;
    }

    public void setBackgroundTint(ColorStateList colorStateList) {
        throw null;
    }

    public void setCradleVerticalOffset(float f2) {
        if (f2 != getCradleVerticalOffset()) {
            g topEdgeTreatment = getTopEdgeTreatment();
            if (topEdgeTreatment == null) {
                throw null;
            }
            if (f2 < 0.0f) {
                throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
            }
            topEdgeTreatment.f2036c = f2;
            throw null;
        }
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        throw null;
    }

    public void setFabAlignmentMode(int i) {
        int i2;
        this.T = 0;
        boolean z = this.V;
        if (q.C(this)) {
            Animator animator = this.O;
            if (animator != null) {
                animator.cancel();
            }
            ArrayList arrayList = new ArrayList();
            if (D()) {
                i2 = i;
            } else {
                z = false;
                i2 = 0;
            }
            ActionMenuView actionMenuView = getActionMenuView();
            if (actionMenuView != null) {
                ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(actionMenuView, "alpha", 1.0f);
                if (Math.abs(actionMenuView.getTranslationX() - B(actionMenuView, i2, z)) > 1.0f) {
                    ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(actionMenuView, "alpha", 0.0f);
                    objectAnimatorOfFloat2.addListener(new d(this, actionMenuView, i2, z));
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.setDuration(150L);
                    animatorSet.playSequentially(objectAnimatorOfFloat2, objectAnimatorOfFloat);
                    arrayList.add(animatorSet);
                } else if (actionMenuView.getAlpha() < 1.0f) {
                    arrayList.add(objectAnimatorOfFloat);
                }
            }
            AnimatorSet animatorSet2 = new AnimatorSet();
            animatorSet2.playTogether(arrayList);
            this.O = animatorSet2;
            animatorSet2.addListener(new c(this));
            this.O.start();
        } else {
            int i3 = this.T;
            if (i3 != 0) {
                this.T = 0;
                getMenu().clear();
                n(i3);
            }
        }
        if (this.P != i && q.C(this)) {
            Animator animator2 = this.N;
            if (animator2 != null) {
                animator2.cancel();
            }
            ArrayList arrayList2 = new ArrayList();
            if (this.Q == 1) {
                ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(z(), "translationX", C(i));
                objectAnimatorOfFloat3.setDuration(300L);
                arrayList2.add(objectAnimatorOfFloat3);
            } else {
                y(i);
            }
            AnimatorSet animatorSet3 = new AnimatorSet();
            animatorSet3.playTogether(arrayList2);
            this.N = animatorSet3;
            animatorSet3.addListener(new b.d.a.a.o.a(this));
            this.N.start();
        }
        this.P = i;
    }

    public void setFabAnimationMode(int i) {
        this.Q = i;
    }

    public void setFabCornerSize(float f2) {
        if (f2 == getTopEdgeTreatment().f2038e) {
            return;
        }
        getTopEdgeTreatment().f2038e = f2;
        throw null;
    }

    public void setFabCradleMargin(float f2) {
        if (f2 == getFabCradleMargin()) {
            return;
        }
        getTopEdgeTreatment().f2035b = f2;
        throw null;
    }

    public void setFabCradleRoundedCornerRadius(float f2) {
        if (f2 == getFabCradleRoundedCornerRadius()) {
            return;
        }
        getTopEdgeTreatment().f2034a = f2;
        throw null;
    }

    public void setHideOnScroll(boolean z) {
        this.R = z;
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setSubtitle(CharSequence charSequence) {
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setTitle(CharSequence charSequence) {
    }

    public void y(int i) {
        FloatingActionButton floatingActionButtonZ = z();
        if (floatingActionButtonZ == null || floatingActionButtonZ.j()) {
            return;
        }
        this.S++;
        floatingActionButtonZ.i(new b(this, i), true);
    }

    public final FloatingActionButton z() {
        View viewA = A();
        if (viewA instanceof FloatingActionButton) {
            return (FloatingActionButton) viewA;
        }
        return null;
    }
}
