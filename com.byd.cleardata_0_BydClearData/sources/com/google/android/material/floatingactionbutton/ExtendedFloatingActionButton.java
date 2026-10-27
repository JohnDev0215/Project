package com.google.android.material.floatingactionbutton;

import a.g.k.t;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import b.a.a.a.k;
import b.a.a.a.l;
import b.a.a.a.m.h;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ExtendedFloatingActionButton extends MaterialButton implements CoordinatorLayout.b {
    private static final int s = k.s;
    static final Property<View, Float> t = new b(Float.class, "width");
    static final Property<View, Float> u = new c(Float.class, "height");
    private boolean A;
    private final com.google.android.material.floatingactionbutton.d v;
    private final com.google.android.material.floatingactionbutton.d w;
    private final com.google.android.material.floatingactionbutton.d x;
    private final com.google.android.material.floatingactionbutton.d y;
    private final CoordinatorLayout.c<ExtendedFloatingActionButton> z;

    protected static class ExtendedFloatingActionButtonBehavior<T extends ExtendedFloatingActionButton> extends CoordinatorLayout.c<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Rect f3085a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private d f3086b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private d f3087c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f3088d;
        private boolean e;

        public ExtendedFloatingActionButtonBehavior() {
            this.f3088d = false;
            this.e = true;
        }

        public ExtendedFloatingActionButtonBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, l.Y0);
            this.f3088d = typedArrayObtainStyledAttributes.getBoolean(l.Z0, false);
            this.e = typedArrayObtainStyledAttributes.getBoolean(l.a1, true);
            typedArrayObtainStyledAttributes.recycle();
        }

        private static boolean G(View view) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.f) {
                return ((CoordinatorLayout.f) layoutParams).f() instanceof BottomSheetBehavior;
            }
            return false;
        }

        private boolean J(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            return (this.f3088d || this.e) && ((CoordinatorLayout.f) extendedFloatingActionButton.getLayoutParams()).e() == view.getId();
        }

        private boolean L(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (!J(appBarLayout, extendedFloatingActionButton)) {
                return false;
            }
            if (this.f3085a == null) {
                this.f3085a = new Rect();
            }
            Rect rect = this.f3085a;
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
            boolean z = this.e;
            extendedFloatingActionButton.l(z ? extendedFloatingActionButton.w : extendedFloatingActionButton.x, z ? this.f3087c : this.f3086b);
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
        public boolean l(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, int i) {
            List<View> listR = coordinatorLayout.r(extendedFloatingActionButton);
            int size = listR.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view = listR.get(i2);
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
            coordinatorLayout.I(extendedFloatingActionButton, i);
            return true;
        }

        protected void K(ExtendedFloatingActionButton extendedFloatingActionButton) {
            boolean z = this.e;
            extendedFloatingActionButton.l(z ? extendedFloatingActionButton.v : extendedFloatingActionButton.y, z ? this.f3087c : this.f3086b);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public void g(CoordinatorLayout.f fVar) {
            if (fVar.h == 0) {
                fVar.h = 80;
            }
        }
    }

    class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f3089a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.google.android.material.floatingactionbutton.d f3090b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ d f3091c;

        a(com.google.android.material.floatingactionbutton.d dVar, d dVar2) {
            this.f3090b = dVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f3089a = true;
            this.f3090b.d();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f3090b.c();
            if (this.f3089a) {
                return;
            }
            this.f3090b.f(this.f3091c);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f3090b.onAnimationStart(animator);
            this.f3089a = false;
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
        public void set(View view, Float f) {
            view.getLayoutParams().width = f.intValue();
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
        public void set(View view, Float f) {
            view.getLayoutParams().height = f.intValue();
            view.requestLayout();
        }
    }

    public static abstract class d {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l(com.google.android.material.floatingactionbutton.d dVar, d dVar2) {
        if (dVar.g()) {
            return;
        }
        if (!m()) {
            dVar.i();
            dVar.f(dVar2);
            return;
        }
        measure(0, 0);
        AnimatorSet animatorSetB = dVar.b();
        animatorSetB.addListener(new a(dVar, dVar2));
        Iterator<Animator.AnimatorListener> it = dVar.e().iterator();
        while (it.hasNext()) {
            animatorSetB.addListener(it.next());
        }
        animatorSetB.start();
    }

    private boolean m() {
        return t.N(this) && !isInEditMode();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.b
    public CoordinatorLayout.c<ExtendedFloatingActionButton> getBehavior() {
        return this.z;
    }

    int getCollapsedSize() {
        return (Math.min(t.D(this), t.C(this)) * 2) + getIconSize();
    }

    public h getExtendMotionSpec() {
        return this.w.a();
    }

    public h getHideMotionSpec() {
        return this.y.a();
    }

    public h getShowMotionSpec() {
        return this.x.a();
    }

    public h getShrinkMotionSpec() {
        return this.v.a();
    }

    @Override // com.google.android.material.button.MaterialButton, android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.A && TextUtils.isEmpty(getText()) && getIcon() != null) {
            this.A = false;
            this.v.i();
        }
    }

    public void setExtendMotionSpec(h hVar) {
        this.w.h(hVar);
    }

    public void setExtendMotionSpecResource(int i) {
        setExtendMotionSpec(h.c(getContext(), i));
    }

    public void setExtended(boolean z) {
        if (this.A == z) {
            return;
        }
        com.google.android.material.floatingactionbutton.d dVar = z ? this.w : this.v;
        if (dVar.g()) {
            return;
        }
        dVar.i();
    }

    public void setHideMotionSpec(h hVar) {
        this.y.h(hVar);
    }

    public void setHideMotionSpecResource(int i) {
        setHideMotionSpec(h.c(getContext(), i));
    }

    public void setShowMotionSpec(h hVar) {
        this.x.h(hVar);
    }

    public void setShowMotionSpecResource(int i) {
        setShowMotionSpec(h.c(getContext(), i));
    }

    public void setShrinkMotionSpec(h hVar) {
        this.v.h(hVar);
    }

    public void setShrinkMotionSpecResource(int i) {
        setShrinkMotionSpec(h.c(getContext(), i));
    }
}
