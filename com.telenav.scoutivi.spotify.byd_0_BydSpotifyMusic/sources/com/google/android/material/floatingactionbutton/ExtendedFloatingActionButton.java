package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import i0.u;
import java.util.Iterator;
import java.util.List;
import o2.k;
import o2.l;
import p2.h;

/* JADX INFO: loaded from: classes.dex */
public class ExtendedFloatingActionButton extends MaterialButton implements CoordinatorLayout.b {
    private static final int K = k.Widget_MaterialComponents_ExtendedFloatingActionButton_Icon;
    static final Property<View, Float> L = new b(Float.class, "width");
    static final Property<View, Float> M = new c(Float.class, "height");
    static final Property<View, Float> N = new d(Float.class, "paddingStart");
    static final Property<View, Float> O = new e(Float.class, "paddingEnd");
    private final com.google.android.material.floatingactionbutton.c A;
    private final com.google.android.material.floatingactionbutton.c B;
    private final int C;
    private int D;
    private int E;
    private final CoordinatorLayout.c<ExtendedFloatingActionButton> F;
    private boolean G;
    private boolean H;
    private boolean I;
    protected ColorStateList J;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private int f4503x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private final com.google.android.material.floatingactionbutton.c f4504y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private final com.google.android.material.floatingactionbutton.c f4505z;

    class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f4509a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.google.android.material.floatingactionbutton.c f4510b;

        a(com.google.android.material.floatingactionbutton.c cVar, f fVar) {
            this.f4510b = cVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f4509a = true;
            this.f4510b.d();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f4510b.c();
            if (this.f4509a) {
                return;
            }
            this.f4510b.f(null);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f4510b.onAnimationStart(animator);
            this.f4509a = false;
        }
    }

    static class b extends Property<View, Float> {
        b(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(View view) {
            return Float.valueOf(view.getLayoutParams().width);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, Float f7) {
            view.getLayoutParams().width = f7.intValue();
            view.requestLayout();
        }
    }

    static class c extends Property<View, Float> {
        c(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(View view) {
            return Float.valueOf(view.getLayoutParams().height);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, Float f7) {
            view.getLayoutParams().height = f7.intValue();
            view.requestLayout();
        }
    }

    static class d extends Property<View, Float> {
        d(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(View view) {
            return Float.valueOf(u.E(view));
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, Float f7) {
            u.w0(view, f7.intValue(), view.getPaddingTop(), u.D(view), view.getPaddingBottom());
        }
    }

    static class e extends Property<View, Float> {
        e(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(View view) {
            return Float.valueOf(u.D(view));
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, Float f7) {
            u.w0(view, u.E(view), view.getPaddingTop(), f7.intValue(), view.getPaddingBottom());
        }
    }

    public static abstract class f {
    }

    private boolean q() {
        return getVisibility() != 0 ? this.f4503x == 2 : this.f4503x != 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(com.google.android.material.floatingactionbutton.c cVar, f fVar) {
        if (cVar.g()) {
            return;
        }
        if (!t()) {
            cVar.i();
            cVar.f(fVar);
            return;
        }
        measure(0, 0);
        AnimatorSet animatorSetB = cVar.b();
        animatorSetB.addListener(new a(cVar, fVar));
        Iterator<Animator.AnimatorListener> it = cVar.e().iterator();
        while (it.hasNext()) {
            animatorSetB.addListener(it.next());
        }
        animatorSetB.start();
    }

    private void s() {
        this.J = getTextColors();
    }

    private boolean t() {
        return (u.P(this) || (!q() && this.I)) && !isInEditMode();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.b
    public CoordinatorLayout.c<ExtendedFloatingActionButton> getBehavior() {
        return this.F;
    }

    int getCollapsedPadding() {
        return (getCollapsedSize() - getIconSize()) / 2;
    }

    int getCollapsedSize() {
        int i7 = this.C;
        return i7 < 0 ? (Math.min(u.E(this), u.D(this)) * 2) + getIconSize() : i7;
    }

    public h getExtendMotionSpec() {
        return this.f4505z.a();
    }

    public h getHideMotionSpec() {
        return this.B.a();
    }

    public h getShowMotionSpec() {
        return this.A.a();
    }

    public h getShrinkMotionSpec() {
        return this.f4504y.a();
    }

    @Override // com.google.android.material.button.MaterialButton, android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.G && TextUtils.isEmpty(getText()) && getIcon() != null) {
            this.G = false;
            this.f4504y.i();
        }
    }

    public void setAnimateShowBeforeLayout(boolean z6) {
        this.I = z6;
    }

    public void setExtendMotionSpec(h hVar) {
        this.f4505z.h(hVar);
    }

    public void setExtendMotionSpecResource(int i7) {
        setExtendMotionSpec(h.c(getContext(), i7));
    }

    public void setExtended(boolean z6) {
        if (this.G == z6) {
            return;
        }
        com.google.android.material.floatingactionbutton.c cVar = z6 ? this.f4505z : this.f4504y;
        if (cVar.g()) {
            return;
        }
        cVar.i();
    }

    public void setHideMotionSpec(h hVar) {
        this.B.h(hVar);
    }

    public void setHideMotionSpecResource(int i7) {
        setHideMotionSpec(h.c(getContext(), i7));
    }

    @Override // android.widget.TextView, android.view.View
    public void setPadding(int i7, int i8, int i9, int i10) {
        super.setPadding(i7, i8, i9, i10);
        if (!this.G || this.H) {
            return;
        }
        this.D = u.E(this);
        this.E = u.D(this);
    }

    @Override // android.widget.TextView, android.view.View
    public void setPaddingRelative(int i7, int i8, int i9, int i10) {
        super.setPaddingRelative(i7, i8, i9, i10);
        if (!this.G || this.H) {
            return;
        }
        this.D = i7;
        this.E = i9;
    }

    public void setShowMotionSpec(h hVar) {
        this.A.h(hVar);
    }

    public void setShowMotionSpecResource(int i7) {
        setShowMotionSpec(h.c(getContext(), i7));
    }

    public void setShrinkMotionSpec(h hVar) {
        this.f4504y.h(hVar);
    }

    public void setShrinkMotionSpecResource(int i7) {
        setShrinkMotionSpec(h.c(getContext(), i7));
    }

    @Override // android.widget.TextView
    public void setTextColor(int i7) {
        super.setTextColor(i7);
        s();
    }

    protected static class ExtendedFloatingActionButtonBehavior<T extends ExtendedFloatingActionButton> extends CoordinatorLayout.c<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Rect f4506a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f4507b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f4508c;

        public ExtendedFloatingActionButtonBehavior() {
            this.f4507b = false;
            this.f4508c = true;
        }

        private static boolean G(View view) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.f) {
                return ((CoordinatorLayout.f) layoutParams).f() instanceof BottomSheetBehavior;
            }
            return false;
        }

        private boolean J(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            return (this.f4507b || this.f4508c) && ((CoordinatorLayout.f) extendedFloatingActionButton.getLayoutParams()).e() == view.getId();
        }

        private boolean L(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (!J(appBarLayout, extendedFloatingActionButton)) {
                return false;
            }
            if (this.f4506a == null) {
                this.f4506a = new Rect();
            }
            Rect rect = this.f4506a;
            com.google.android.material.internal.b.a(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                K(extendedFloatingActionButton);
                return true;
            }
            E(extendedFloatingActionButton);
            return true;
        }

        private boolean M(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (!J(view, extendedFloatingActionButton)) {
                return false;
            }
            if (view.getTop() < (extendedFloatingActionButton.getHeight() / 2) + ((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.f) extendedFloatingActionButton.getLayoutParams())).topMargin) {
                K(extendedFloatingActionButton);
                return true;
            }
            E(extendedFloatingActionButton);
            return true;
        }

        protected void E(ExtendedFloatingActionButton extendedFloatingActionButton) {
            extendedFloatingActionButton.r(this.f4508c ? extendedFloatingActionButton.f4505z : extendedFloatingActionButton.A, null);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: F, reason: merged with bridge method [inline-methods] */
        public boolean b(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, Rect rect) {
            return super.b(coordinatorLayout, extendedFloatingActionButton, rect);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: H, reason: merged with bridge method [inline-methods] */
        public boolean h(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, View view) {
            if (view instanceof AppBarLayout) {
                L(coordinatorLayout, (AppBarLayout) view, extendedFloatingActionButton);
                return false;
            }
            if (!G(view)) {
                return false;
            }
            M(view, extendedFloatingActionButton);
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: I, reason: merged with bridge method [inline-methods] */
        public boolean l(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, int i7) {
            List<View> listR = coordinatorLayout.r(extendedFloatingActionButton);
            int size = listR.size();
            for (int i8 = 0; i8 < size; i8++) {
                View view = listR.get(i8);
                if (!(view instanceof AppBarLayout)) {
                    if (G(view) && M(view, extendedFloatingActionButton)) {
                        break;
                    }
                } else {
                    if (L(coordinatorLayout, (AppBarLayout) view, extendedFloatingActionButton)) {
                        break;
                    }
                }
            }
            coordinatorLayout.I(extendedFloatingActionButton, i7);
            return true;
        }

        protected void K(ExtendedFloatingActionButton extendedFloatingActionButton) {
            extendedFloatingActionButton.r(this.f4508c ? extendedFloatingActionButton.f4504y : extendedFloatingActionButton.B, null);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public void g(CoordinatorLayout.f fVar) {
            if (fVar.f1931h == 0) {
                fVar.f1931h = 80;
            }
        }

        public ExtendedFloatingActionButtonBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, l.ExtendedFloatingActionButton_Behavior_Layout);
            this.f4507b = typedArrayObtainStyledAttributes.getBoolean(l.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoHide, false);
            this.f4508c = typedArrayObtainStyledAttributes.getBoolean(l.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoShrink, true);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // android.widget.TextView
    public void setTextColor(ColorStateList colorStateList) {
        super.setTextColor(colorStateList);
        s();
    }
}
