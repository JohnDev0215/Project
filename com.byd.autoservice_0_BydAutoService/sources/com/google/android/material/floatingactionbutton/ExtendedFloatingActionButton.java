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
import com.google.android.material.R$animator;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import i2.k;
import java.util.Iterator;
import java.util.List;
import l0.t;
import n2.m;

/* JADX INFO: loaded from: classes.dex */
public class ExtendedFloatingActionButton extends MaterialButton implements CoordinatorLayout.b {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final int f2278s = R$style.Widget_MaterialComponents_ExtendedFloatingActionButton_Icon;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final Property<View, Float> f2279t = new d(Float.class, "width");

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final Property<View, Float> f2280u = new e(Float.class, "height");
    public final h2.f A;
    public final CoordinatorLayout.c<ExtendedFloatingActionButton> B;
    public boolean C;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f2281v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final h2.a f2282w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final h2.f f2283x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final h2.f f2284y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final h2.f f2285z;

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
        public boolean f2293a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ h2.f f2294b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ h f2295c;

        public c(h2.f fVar, h hVar) {
            this.f2294b = fVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f2293a = true;
            this.f2294b.d();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2294b.c();
            if (this.f2293a) {
                return;
            }
            this.f2294b.f(this.f2295c);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f2294b.onAnimationStart(animator);
            this.f2293a = false;
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
        public void set(View view, Float f3) {
            view.getLayoutParams().width = f3.intValue();
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
        public void set(View view, Float f3) {
            view.getLayoutParams().height = f3.intValue();
            view.requestLayout();
        }
    }

    public class f extends h2.b {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final j f2297g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final boolean f2298h;

        public f(h2.a aVar, j jVar, boolean z3) {
            super(ExtendedFloatingActionButton.this, aVar);
            this.f2297g = jVar;
            this.f2298h = z3;
        }

        @Override // h2.b, h2.f
        public AnimatorSet b() {
            s1.h hVarL = l();
            if (hVarL.j("width")) {
                PropertyValuesHolder[] propertyValuesHolderArrG = hVarL.g("width");
                propertyValuesHolderArrG[0].setFloatValues(ExtendedFloatingActionButton.this.getWidth(), this.f2297g.a());
                hVarL.l("width", propertyValuesHolderArrG);
            }
            if (hVarL.j("height")) {
                PropertyValuesHolder[] propertyValuesHolderArrG2 = hVarL.g("height");
                propertyValuesHolderArrG2[0].setFloatValues(ExtendedFloatingActionButton.this.getHeight(), this.f2297g.b());
                hVarL.l("height", propertyValuesHolderArrG2);
            }
            return super.k(hVarL);
        }

        @Override // h2.b, h2.f
        public void c() {
            super.c();
            ExtendedFloatingActionButton.this.setHorizontallyScrolling(false);
            ViewGroup.LayoutParams layoutParams = ExtendedFloatingActionButton.this.getLayoutParams();
            if (layoutParams == null) {
                return;
            }
            layoutParams.width = this.f2297g.c().width;
            layoutParams.height = this.f2297g.c().height;
        }

        @Override // h2.f
        public void f(h hVar) {
            if (hVar == null) {
                return;
            }
            if (!this.f2298h) {
                throw null;
            }
            throw null;
        }

        @Override // h2.f
        public boolean g() {
            return this.f2298h == ExtendedFloatingActionButton.this.C || ExtendedFloatingActionButton.this.getIcon() == null || TextUtils.isEmpty(ExtendedFloatingActionButton.this.getText());
        }

        @Override // h2.f
        public int i() {
            return R$animator.mtrl_extended_fab_change_size_motion_spec;
        }

        @Override // h2.f
        public void j() {
            ExtendedFloatingActionButton.this.C = this.f2298h;
            ViewGroup.LayoutParams layoutParams = ExtendedFloatingActionButton.this.getLayoutParams();
            if (layoutParams == null) {
                return;
            }
            layoutParams.width = this.f2297g.c().width;
            layoutParams.height = this.f2297g.c().height;
            ExtendedFloatingActionButton.this.requestLayout();
        }

        @Override // h2.b, h2.f
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            ExtendedFloatingActionButton.this.C = this.f2298h;
            ExtendedFloatingActionButton.this.setHorizontallyScrolling(true);
        }
    }

    public class g extends h2.b {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f2300g;

        public g(h2.a aVar) {
            super(ExtendedFloatingActionButton.this, aVar);
        }

        @Override // h2.b, h2.f
        public void c() {
            super.c();
            ExtendedFloatingActionButton.this.f2281v = 0;
            if (this.f2300g) {
                return;
            }
            ExtendedFloatingActionButton.this.setVisibility(8);
        }

        @Override // h2.b, h2.f
        public void d() {
            super.d();
            this.f2300g = true;
        }

        @Override // h2.f
        public void f(h hVar) {
            if (hVar != null) {
                throw null;
            }
        }

        @Override // h2.f
        public boolean g() {
            return ExtendedFloatingActionButton.this.s();
        }

        @Override // h2.f
        public int i() {
            return R$animator.mtrl_extended_fab_hide_motion_spec;
        }

        @Override // h2.f
        public void j() {
            ExtendedFloatingActionButton.this.setVisibility(8);
        }

        @Override // h2.b, h2.f
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            this.f2300g = false;
            ExtendedFloatingActionButton.this.setVisibility(0);
            ExtendedFloatingActionButton.this.f2281v = 1;
        }
    }

    public static abstract class h {
    }

    public class i extends h2.b {
        public i(h2.a aVar) {
            super(ExtendedFloatingActionButton.this, aVar);
        }

        @Override // h2.b, h2.f
        public void c() {
            super.c();
            ExtendedFloatingActionButton.this.f2281v = 0;
        }

        @Override // h2.f
        public void f(h hVar) {
            if (hVar != null) {
                throw null;
            }
        }

        @Override // h2.f
        public boolean g() {
            return ExtendedFloatingActionButton.this.t();
        }

        @Override // h2.f
        public int i() {
            return R$animator.mtrl_extended_fab_show_motion_spec;
        }

        @Override // h2.f
        public void j() {
            ExtendedFloatingActionButton.this.setVisibility(0);
            ExtendedFloatingActionButton.this.setAlpha(1.0f);
            ExtendedFloatingActionButton.this.setScaleY(1.0f);
            ExtendedFloatingActionButton.this.setScaleX(1.0f);
        }

        @Override // h2.b, h2.f
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            ExtendedFloatingActionButton.this.setVisibility(0);
            ExtendedFloatingActionButton.this.f2281v = 2;
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

    public s1.h getExtendMotionSpec() {
        return this.f2284y.a();
    }

    public s1.h getHideMotionSpec() {
        return this.A.a();
    }

    public s1.h getShowMotionSpec() {
        return this.f2285z.a();
    }

    public s1.h getShrinkMotionSpec() {
        return this.f2283x.a();
    }

    @Override // com.google.android.material.button.MaterialButton, android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.C && TextUtils.isEmpty(getText()) && getIcon() != null) {
            this.C = false;
            this.f2283x.j();
        }
    }

    public final boolean s() {
        return getVisibility() == 0 ? this.f2281v == 1 : this.f2281v != 2;
    }

    public void setExtendMotionSpec(s1.h hVar) {
        this.f2284y.h(hVar);
    }

    public void setExtendMotionSpecResource(int i3) {
        setExtendMotionSpec(s1.h.d(getContext(), i3));
    }

    public void setExtended(boolean z3) {
        if (this.C == z3) {
            return;
        }
        h2.f fVar = z3 ? this.f2284y : this.f2283x;
        if (fVar.g()) {
            return;
        }
        fVar.j();
    }

    public void setHideMotionSpec(s1.h hVar) {
        this.A.h(hVar);
    }

    public void setHideMotionSpecResource(int i3) {
        setHideMotionSpec(s1.h.d(getContext(), i3));
    }

    public void setShowMotionSpec(s1.h hVar) {
        this.f2285z.h(hVar);
    }

    public void setShowMotionSpecResource(int i3) {
        setShowMotionSpec(s1.h.d(getContext(), i3));
    }

    public void setShrinkMotionSpec(s1.h hVar) {
        this.f2283x.h(hVar);
    }

    public void setShrinkMotionSpecResource(int i3) {
        setShrinkMotionSpec(s1.h.d(getContext(), i3));
    }

    public final boolean t() {
        return getVisibility() != 0 ? this.f2281v == 2 : this.f2281v != 1;
    }

    public final void u(h2.f fVar, h hVar) {
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
        public Rect f2286a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public h f2287b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public h f2288c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f2289d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f2290e;

        public ExtendedFloatingActionButtonBehavior() {
            this.f2289d = false;
            this.f2290e = true;
        }

        public static boolean G(View view) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.f) {
                return ((CoordinatorLayout.f) layoutParams).f() instanceof BottomSheetBehavior;
            }
            return false;
        }

        public void E(ExtendedFloatingActionButton extendedFloatingActionButton) {
            boolean z3 = this.f2290e;
            extendedFloatingActionButton.u(z3 ? extendedFloatingActionButton.f2284y : extendedFloatingActionButton.f2285z, z3 ? this.f2288c : this.f2287b);
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
        public boolean l(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, int i3) {
            List<View> listV = coordinatorLayout.v(extendedFloatingActionButton);
            int size = listV.size();
            for (int i4 = 0; i4 < size; i4++) {
                View view = listV.get(i4);
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
            coordinatorLayout.M(extendedFloatingActionButton, i3);
            return true;
        }

        public final boolean J(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            return (this.f2289d || this.f2290e) && ((CoordinatorLayout.f) extendedFloatingActionButton.getLayoutParams()).e() == view.getId();
        }

        public void K(ExtendedFloatingActionButton extendedFloatingActionButton) {
            boolean z3 = this.f2290e;
            extendedFloatingActionButton.u(z3 ? extendedFloatingActionButton.f2283x : extendedFloatingActionButton.A, z3 ? this.f2288c : this.f2287b);
        }

        public final boolean L(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (!J(appBarLayout, extendedFloatingActionButton)) {
                return false;
            }
            if (this.f2286a == null) {
                this.f2286a = new Rect();
            }
            Rect rect = this.f2286a;
            i2.c.a(coordinatorLayout, appBarLayout, rect);
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
            if (fVar.f1104h == 0) {
                fVar.f1104h = 80;
            }
        }

        public ExtendedFloatingActionButtonBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ExtendedFloatingActionButton_Behavior_Layout);
            this.f2289d = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoHide, false);
            this.f2290e = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoShrink, true);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ExtendedFloatingActionButton(Context context, AttributeSet attributeSet, int i3) {
        int i4 = f2278s;
        super(r2.a.c(context, attributeSet, i3, i4), attributeSet, i3);
        this.f2281v = 0;
        h2.a aVar = new h2.a();
        this.f2282w = aVar;
        i iVar = new i(aVar);
        this.f2285z = iVar;
        g gVar = new g(aVar);
        this.A = gVar;
        this.C = true;
        Context context2 = getContext();
        this.B = new ExtendedFloatingActionButtonBehavior(context2, attributeSet);
        TypedArray typedArrayH = k.h(context2, attributeSet, R$styleable.ExtendedFloatingActionButton, i3, i4, new int[0]);
        s1.h hVarC = s1.h.c(context2, typedArrayH, R$styleable.ExtendedFloatingActionButton_showMotionSpec);
        s1.h hVarC2 = s1.h.c(context2, typedArrayH, R$styleable.ExtendedFloatingActionButton_hideMotionSpec);
        s1.h hVarC3 = s1.h.c(context2, typedArrayH, R$styleable.ExtendedFloatingActionButton_extendMotionSpec);
        s1.h hVarC4 = s1.h.c(context2, typedArrayH, R$styleable.ExtendedFloatingActionButton_shrinkMotionSpec);
        h2.a aVar2 = new h2.a();
        f fVar = new f(aVar2, new a(), true);
        this.f2284y = fVar;
        f fVar2 = new f(aVar2, new b(), false);
        this.f2283x = fVar2;
        iVar.h(hVarC);
        gVar.h(hVarC2);
        fVar.h(hVarC3);
        fVar2.h(hVarC4);
        typedArrayH.recycle();
        setShapeAppearanceModel(m.g(context2, attributeSet, i3, i4, m.f4152a).m());
    }
}
