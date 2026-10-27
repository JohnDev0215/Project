package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import b.g.j.t;
import c.b.a.a.q.k;
import c.b.a.a.v.m;
import com.google.android.material.R$animator;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ExtendedFloatingActionButton extends MaterialButton implements CoordinatorLayout.b {
    public static final int s = R$style.Widget_MaterialComponents_ExtendedFloatingActionButton_Icon;
    public static final Property<View, Float> t = new d(Float.class, "width");
    public static final Property<View, Float> u = new e(Float.class, "height");
    public final c.b.a.a.p.f A;
    public final CoordinatorLayout.c<ExtendedFloatingActionButton> B;
    public boolean C;
    public int v;
    public final c.b.a.a.p.a w;
    public final c.b.a.a.p.f x;
    public final c.b.a.a.p.f y;
    public final c.b.a.a.p.f z;

    public class a implements j {
        public a() {
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.j
        public int a() {
            return ExtendedFloatingActionButton.this.getMeasuredWidth();
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.j
        public int b() {
            return ExtendedFloatingActionButton.this.getMeasuredHeight();
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.j
        public ViewGroup.LayoutParams c() {
            return new ViewGroup.LayoutParams(-2, -2);
        }
    }

    public class b implements j {
        public b() {
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.j
        public int a() {
            return ExtendedFloatingActionButton.this.getCollapsedSize();
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.j
        public int b() {
            return ExtendedFloatingActionButton.this.getCollapsedSize();
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.j
        public ViewGroup.LayoutParams c() {
            return new ViewGroup.LayoutParams(a(), b());
        }
    }

    public class c extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f2179a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ c.b.a.a.p.f f2180b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ h f2181c;

        public c(c.b.a.a.p.f fVar, h hVar) {
            this.f2180b = fVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f2179a = true;
            this.f2180b.d();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2180b.c();
            if (this.f2179a) {
                return;
            }
            this.f2180b.f(this.f2181c);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f2180b.onAnimationStart(animator);
            this.f2179a = false;
        }
    }

    public static class d extends Property<View, Float> {
        public d(Class cls, String str) {
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

    public static class e extends Property<View, Float> {
        public e(Class cls, String str) {
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

    public class f extends c.b.a.a.p.b {
        public final j g;
        public final boolean h;

        public f(c.b.a.a.p.a aVar, j jVar, boolean z) {
            super(ExtendedFloatingActionButton.this, aVar);
            this.g = jVar;
            this.h = z;
        }

        @Override // c.b.a.a.p.b, c.b.a.a.p.f
        public AnimatorSet b() {
            c.b.a.a.a.h hVarL = l();
            if (hVarL.j("width")) {
                PropertyValuesHolder[] propertyValuesHolderArrG = hVarL.g("width");
                propertyValuesHolderArrG[0].setFloatValues(ExtendedFloatingActionButton.this.getWidth(), this.g.a());
                hVarL.l("width", propertyValuesHolderArrG);
            }
            if (hVarL.j("height")) {
                PropertyValuesHolder[] propertyValuesHolderArrG2 = hVarL.g("height");
                propertyValuesHolderArrG2[0].setFloatValues(ExtendedFloatingActionButton.this.getHeight(), this.g.b());
                hVarL.l("height", propertyValuesHolderArrG2);
            }
            return super.k(hVarL);
        }

        @Override // c.b.a.a.p.b, c.b.a.a.p.f
        public void c() {
            super.c();
            ExtendedFloatingActionButton.this.setHorizontallyScrolling(false);
            ViewGroup.LayoutParams layoutParams = ExtendedFloatingActionButton.this.getLayoutParams();
            if (layoutParams == null) {
                return;
            }
            layoutParams.width = this.g.c().width;
            layoutParams.height = this.g.c().height;
        }

        @Override // c.b.a.a.p.f
        public void f(h hVar) {
            if (hVar == null) {
                return;
            }
            if (!this.h) {
                throw null;
            }
            throw null;
        }

        @Override // c.b.a.a.p.f
        public boolean g() {
            return this.h == ExtendedFloatingActionButton.this.C || ExtendedFloatingActionButton.this.getIcon() == null || TextUtils.isEmpty(ExtendedFloatingActionButton.this.getText());
        }

        @Override // c.b.a.a.p.f
        public int i() {
            return R$animator.mtrl_extended_fab_change_size_motion_spec;
        }

        @Override // c.b.a.a.p.f
        public void j() {
            ExtendedFloatingActionButton.this.C = this.h;
            ViewGroup.LayoutParams layoutParams = ExtendedFloatingActionButton.this.getLayoutParams();
            if (layoutParams == null) {
                return;
            }
            layoutParams.width = this.g.c().width;
            layoutParams.height = this.g.c().height;
            ExtendedFloatingActionButton.this.requestLayout();
        }

        @Override // c.b.a.a.p.b, c.b.a.a.p.f
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            ExtendedFloatingActionButton.this.C = this.h;
            ExtendedFloatingActionButton.this.setHorizontallyScrolling(true);
        }
    }

    public class g extends c.b.a.a.p.b {
        public boolean g;

        public g(c.b.a.a.p.a aVar) {
            super(ExtendedFloatingActionButton.this, aVar);
        }

        @Override // c.b.a.a.p.b, c.b.a.a.p.f
        public void c() {
            super.c();
            ExtendedFloatingActionButton.this.v = 0;
            if (this.g) {
                return;
            }
            ExtendedFloatingActionButton.this.setVisibility(8);
        }

        @Override // c.b.a.a.p.b, c.b.a.a.p.f
        public void d() {
            super.d();
            this.g = true;
        }

        @Override // c.b.a.a.p.f
        public void f(h hVar) {
            if (hVar != null) {
                throw null;
            }
        }

        @Override // c.b.a.a.p.f
        public boolean g() {
            return ExtendedFloatingActionButton.this.s();
        }

        @Override // c.b.a.a.p.f
        public int i() {
            return R$animator.mtrl_extended_fab_hide_motion_spec;
        }

        @Override // c.b.a.a.p.f
        public void j() {
            ExtendedFloatingActionButton.this.setVisibility(8);
        }

        @Override // c.b.a.a.p.b, c.b.a.a.p.f
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            this.g = false;
            ExtendedFloatingActionButton.this.setVisibility(0);
            ExtendedFloatingActionButton.this.v = 1;
        }
    }

    public static abstract class h {
    }

    public class i extends c.b.a.a.p.b {
        public i(c.b.a.a.p.a aVar) {
            super(ExtendedFloatingActionButton.this, aVar);
        }

        @Override // c.b.a.a.p.b, c.b.a.a.p.f
        public void c() {
            super.c();
            ExtendedFloatingActionButton.this.v = 0;
        }

        @Override // c.b.a.a.p.f
        public void f(h hVar) {
            if (hVar != null) {
                throw null;
            }
        }

        @Override // c.b.a.a.p.f
        public boolean g() {
            return ExtendedFloatingActionButton.this.t();
        }

        @Override // c.b.a.a.p.f
        public int i() {
            return R$animator.mtrl_extended_fab_show_motion_spec;
        }

        @Override // c.b.a.a.p.f
        public void j() {
            ExtendedFloatingActionButton.this.setVisibility(0);
            ExtendedFloatingActionButton.this.setAlpha(1.0f);
            ExtendedFloatingActionButton.this.setScaleY(1.0f);
            ExtendedFloatingActionButton.this.setScaleX(1.0f);
        }

        @Override // c.b.a.a.p.b, c.b.a.a.p.f
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            ExtendedFloatingActionButton.this.setVisibility(0);
            ExtendedFloatingActionButton.this.v = 2;
        }
    }

    public interface j {
        int a();

        int b();

        ViewGroup.LayoutParams c();
    }

    public ExtendedFloatingActionButton(Context context) {
        this(context, null);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.b
    public CoordinatorLayout.c<ExtendedFloatingActionButton> getBehavior() {
        return this.B;
    }

    public int getCollapsedSize() {
        return (Math.min(t.E(this), t.D(this)) * 2) + getIconSize();
    }

    public c.b.a.a.a.h getExtendMotionSpec() {
        return this.y.a();
    }

    public c.b.a.a.a.h getHideMotionSpec() {
        return this.A.a();
    }

    public c.b.a.a.a.h getShowMotionSpec() {
        return this.z.a();
    }

    public c.b.a.a.a.h getShrinkMotionSpec() {
        return this.x.a();
    }

    @Override // com.google.android.material.button.MaterialButton, android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.C && TextUtils.isEmpty(getText()) && getIcon() != null) {
            this.C = false;
            this.x.j();
        }
    }

    public final boolean s() {
        return getVisibility() == 0 ? this.v == 1 : this.v != 2;
    }

    public void setExtendMotionSpec(c.b.a.a.a.h hVar) {
        this.y.h(hVar);
    }

    public void setExtendMotionSpecResource(int i2) {
        setExtendMotionSpec(c.b.a.a.a.h.d(getContext(), i2));
    }

    public void setExtended(boolean z) {
        if (this.C == z) {
            return;
        }
        c.b.a.a.p.f fVar = z ? this.y : this.x;
        if (fVar.g()) {
            return;
        }
        fVar.j();
    }

    public void setHideMotionSpec(c.b.a.a.a.h hVar) {
        this.A.h(hVar);
    }

    public void setHideMotionSpecResource(int i2) {
        setHideMotionSpec(c.b.a.a.a.h.d(getContext(), i2));
    }

    public void setShowMotionSpec(c.b.a.a.a.h hVar) {
        this.z.h(hVar);
    }

    public void setShowMotionSpecResource(int i2) {
        setShowMotionSpec(c.b.a.a.a.h.d(getContext(), i2));
    }

    public void setShrinkMotionSpec(c.b.a.a.a.h hVar) {
        this.x.h(hVar);
    }

    public void setShrinkMotionSpecResource(int i2) {
        setShrinkMotionSpec(c.b.a.a.a.h.d(getContext(), i2));
    }

    public final boolean t() {
        return getVisibility() != 0 ? this.v == 2 : this.v != 1;
    }

    public final void u(c.b.a.a.p.f fVar, h hVar) {
        if (fVar.g()) {
            return;
        }
        if (!v()) {
            fVar.j();
            fVar.f(hVar);
            return;
        }
        measure(0, 0);
        AnimatorSet animatorSetB = fVar.b();
        animatorSetB.addListener(new c(fVar, hVar));
        Iterator<Animator.AnimatorListener> it = fVar.e().iterator();
        while (it.hasNext()) {
            animatorSetB.addListener(it.next());
        }
        animatorSetB.start();
    }

    public final boolean v() {
        return t.P(this) && !isInEditMode();
    }

    public ExtendedFloatingActionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.extendedFloatingActionButtonStyle);
    }

    public static class ExtendedFloatingActionButtonBehavior<T extends ExtendedFloatingActionButton> extends CoordinatorLayout.c<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Rect f2173a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public h f2174b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public h f2175c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f2176d;
        public boolean e;

        public ExtendedFloatingActionButtonBehavior() {
            this.f2176d = false;
            this.e = true;
        }

        public static boolean G(View view) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.f) {
                return ((CoordinatorLayout.f) layoutParams).f() instanceof BottomSheetBehavior;
            }
            return false;
        }

        public void E(ExtendedFloatingActionButton extendedFloatingActionButton) {
            boolean z = this.e;
            extendedFloatingActionButton.u(z ? extendedFloatingActionButton.y : extendedFloatingActionButton.z, z ? this.f2175c : this.f2174b);
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
            List<View> listV = coordinatorLayout.v(extendedFloatingActionButton);
            int size = listV.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view = listV.get(i2);
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
            coordinatorLayout.M(extendedFloatingActionButton, i);
            return true;
        }

        public final boolean J(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            return (this.f2176d || this.e) && ((CoordinatorLayout.f) extendedFloatingActionButton.getLayoutParams()).e() == view.getId();
        }

        public void K(ExtendedFloatingActionButton extendedFloatingActionButton) {
            boolean z = this.e;
            extendedFloatingActionButton.u(z ? extendedFloatingActionButton.x : extendedFloatingActionButton.A, z ? this.f2175c : this.f2174b);
        }

        public final boolean L(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (!J(appBarLayout, extendedFloatingActionButton)) {
                return false;
            }
            if (this.f2173a == null) {
                this.f2173a = new Rect();
            }
            Rect rect = this.f2173a;
            c.b.a.a.q.c.a(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                K(extendedFloatingActionButton);
                return true;
            }
            E(extendedFloatingActionButton);
            return true;
        }

        public final boolean M(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
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

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public void g(CoordinatorLayout.f fVar) {
            if (fVar.h == 0) {
                fVar.h = 80;
            }
        }

        public ExtendedFloatingActionButtonBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ExtendedFloatingActionButton_Behavior_Layout);
            this.f2176d = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoHide, false);
            this.e = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoShrink, true);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ExtendedFloatingActionButton(Context context, AttributeSet attributeSet, int i2) {
        int i3 = s;
        super(c.b.a.a.z.a.a.c(context, attributeSet, i2, i3), attributeSet, i2);
        this.v = 0;
        c.b.a.a.p.a aVar = new c.b.a.a.p.a();
        this.w = aVar;
        i iVar = new i(aVar);
        this.z = iVar;
        g gVar = new g(aVar);
        this.A = gVar;
        this.C = true;
        Context context2 = getContext();
        this.B = new ExtendedFloatingActionButtonBehavior(context2, attributeSet);
        TypedArray typedArrayH = k.h(context2, attributeSet, R$styleable.ExtendedFloatingActionButton, i2, i3, new int[0]);
        c.b.a.a.a.h hVarC = c.b.a.a.a.h.c(context2, typedArrayH, R$styleable.ExtendedFloatingActionButton_showMotionSpec);
        c.b.a.a.a.h hVarC2 = c.b.a.a.a.h.c(context2, typedArrayH, R$styleable.ExtendedFloatingActionButton_hideMotionSpec);
        c.b.a.a.a.h hVarC3 = c.b.a.a.a.h.c(context2, typedArrayH, R$styleable.ExtendedFloatingActionButton_extendMotionSpec);
        c.b.a.a.a.h hVarC4 = c.b.a.a.a.h.c(context2, typedArrayH, R$styleable.ExtendedFloatingActionButton_shrinkMotionSpec);
        c.b.a.a.p.a aVar2 = new c.b.a.a.p.a();
        f fVar = new f(aVar2, new a(), true);
        this.y = fVar;
        f fVar2 = new f(aVar2, new b(), false);
        this.x = fVar2;
        iVar.h(hVarC);
        gVar.h(hVarC2);
        fVar.h(hVarC3);
        fVar2.h(hVarC4);
        typedArrayH.recycle();
        setShapeAppearanceModel(m.g(context2, attributeSet, i2, i3, m.f1865a).m());
    }
}
