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
import e6.m;
import java.util.Iterator;
import java.util.List;
import o0.t;
import z5.k;

/* JADX INFO: loaded from: classes2.dex */
public class ExtendedFloatingActionButton extends MaterialButton implements CoordinatorLayout.b {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final int f3863u = R$style.Widget_MaterialComponents_ExtendedFloatingActionButton_Icon;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final Property<View, Float> f3864v = new d(Float.class, "width");

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final Property<View, Float> f3865w = new e(Float.class, "height");
    public final y5.f A;
    public final y5.f B;
    public final y5.f C;
    public final CoordinatorLayout.c<ExtendedFloatingActionButton> D;
    public boolean E;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f3866x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final y5.a f3867y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final y5.f f3868z;

    public class a implements j {
        public a() {
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.j
        public int a() {
            return ExtendedFloatingActionButton.this.getMeasuredHeight();
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.j
        public int b() {
            return ExtendedFloatingActionButton.this.getMeasuredWidth();
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
            return new ViewGroup.LayoutParams(b(), a());
        }
    }

    public class c extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f3876d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ y5.f f3877e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ h f3878f;

        public c(y5.f fVar, h hVar) {
            this.f3877e = fVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f3876d = true;
            this.f3877e.b();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f3877e.a();
            if (this.f3876d) {
                return;
            }
            this.f3877e.i(this.f3878f);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f3877e.onAnimationStart(animator);
            this.f3876d = false;
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
        public void set(View view, Float f8) {
            view.getLayoutParams().width = f8.intValue();
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
        public void set(View view, Float f8) {
            view.getLayoutParams().height = f8.intValue();
            view.requestLayout();
        }
    }

    public class f extends y5.b {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final j f3880g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final boolean f3881h;

        public f(y5.a aVar, j jVar, boolean z7) {
            super(ExtendedFloatingActionButton.this, aVar);
            this.f3880g = jVar;
            this.f3881h = z7;
        }

        @Override // y5.b, y5.f
        public void a() {
            super.a();
            ExtendedFloatingActionButton.this.setHorizontallyScrolling(false);
            ViewGroup.LayoutParams layoutParams = ExtendedFloatingActionButton.this.getLayoutParams();
            if (layoutParams == null) {
                return;
            }
            layoutParams.width = this.f3880g.c().width;
            layoutParams.height = this.f3880g.c().height;
        }

        @Override // y5.f
        public int d() {
            return R$animator.mtrl_extended_fab_change_size_motion_spec;
        }

        @Override // y5.f
        public void e() {
            ExtendedFloatingActionButton.this.E = this.f3881h;
            ViewGroup.LayoutParams layoutParams = ExtendedFloatingActionButton.this.getLayoutParams();
            if (layoutParams == null) {
                return;
            }
            layoutParams.width = this.f3880g.c().width;
            layoutParams.height = this.f3880g.c().height;
            ExtendedFloatingActionButton.this.requestLayout();
        }

        @Override // y5.b, y5.f
        public AnimatorSet g() {
            j5.h hVarL = l();
            if (hVarL.j("width")) {
                PropertyValuesHolder[] propertyValuesHolderArrG = hVarL.g("width");
                propertyValuesHolderArrG[0].setFloatValues(ExtendedFloatingActionButton.this.getWidth(), this.f3880g.b());
                hVarL.l("width", propertyValuesHolderArrG);
            }
            if (hVarL.j("height")) {
                PropertyValuesHolder[] propertyValuesHolderArrG2 = hVarL.g("height");
                propertyValuesHolderArrG2[0].setFloatValues(ExtendedFloatingActionButton.this.getHeight(), this.f3880g.a());
                hVarL.l("height", propertyValuesHolderArrG2);
            }
            return super.k(hVarL);
        }

        @Override // y5.f
        public void i(h hVar) {
            if (hVar == null) {
                return;
            }
            if (!this.f3881h) {
                throw null;
            }
            throw null;
        }

        @Override // y5.f
        public boolean j() {
            return this.f3881h == ExtendedFloatingActionButton.this.E || ExtendedFloatingActionButton.this.getIcon() == null || TextUtils.isEmpty(ExtendedFloatingActionButton.this.getText());
        }

        @Override // y5.b, y5.f
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            ExtendedFloatingActionButton.this.E = this.f3881h;
            ExtendedFloatingActionButton.this.setHorizontallyScrolling(true);
        }
    }

    public class g extends y5.b {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f3883g;

        public g(y5.a aVar) {
            super(ExtendedFloatingActionButton.this, aVar);
        }

        @Override // y5.b, y5.f
        public void a() {
            super.a();
            ExtendedFloatingActionButton.this.f3866x = 0;
            if (this.f3883g) {
                return;
            }
            ExtendedFloatingActionButton.this.setVisibility(8);
        }

        @Override // y5.b, y5.f
        public void b() {
            super.b();
            this.f3883g = true;
        }

        @Override // y5.f
        public int d() {
            return R$animator.mtrl_extended_fab_hide_motion_spec;
        }

        @Override // y5.f
        public void e() {
            ExtendedFloatingActionButton.this.setVisibility(8);
        }

        @Override // y5.f
        public void i(h hVar) {
            if (hVar != null) {
                throw null;
            }
        }

        @Override // y5.f
        public boolean j() {
            return ExtendedFloatingActionButton.this.s();
        }

        @Override // y5.b, y5.f
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            this.f3883g = false;
            ExtendedFloatingActionButton.this.setVisibility(0);
            ExtendedFloatingActionButton.this.f3866x = 1;
        }
    }

    public static abstract class h {
    }

    public class i extends y5.b {
        public i(y5.a aVar) {
            super(ExtendedFloatingActionButton.this, aVar);
        }

        @Override // y5.b, y5.f
        public void a() {
            super.a();
            ExtendedFloatingActionButton.this.f3866x = 0;
        }

        @Override // y5.f
        public int d() {
            return R$animator.mtrl_extended_fab_show_motion_spec;
        }

        @Override // y5.f
        public void e() {
            ExtendedFloatingActionButton.this.setVisibility(0);
            ExtendedFloatingActionButton.this.setAlpha(1.0f);
            ExtendedFloatingActionButton.this.setScaleY(1.0f);
            ExtendedFloatingActionButton.this.setScaleX(1.0f);
        }

        @Override // y5.f
        public void i(h hVar) {
            if (hVar != null) {
                throw null;
            }
        }

        @Override // y5.f
        public boolean j() {
            return ExtendedFloatingActionButton.this.t();
        }

        @Override // y5.b, y5.f
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            ExtendedFloatingActionButton.this.setVisibility(0);
            ExtendedFloatingActionButton.this.f3866x = 2;
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
        return this.D;
    }

    public int getCollapsedSize() {
        return (Math.min(t.F(this), t.E(this)) * 2) + getIconSize();
    }

    public j5.h getExtendMotionSpec() {
        return this.A.f();
    }

    public j5.h getHideMotionSpec() {
        return this.C.f();
    }

    public j5.h getShowMotionSpec() {
        return this.B.f();
    }

    public j5.h getShrinkMotionSpec() {
        return this.f3868z.f();
    }

    @Override // com.google.android.material.button.MaterialButton, android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.E && TextUtils.isEmpty(getText()) && getIcon() != null) {
            this.E = false;
            this.f3868z.e();
        }
    }

    public final boolean s() {
        return getVisibility() == 0 ? this.f3866x == 1 : this.f3866x != 2;
    }

    public void setExtendMotionSpec(j5.h hVar) {
        this.A.c(hVar);
    }

    public void setExtendMotionSpecResource(int i8) {
        setExtendMotionSpec(j5.h.d(getContext(), i8));
    }

    public void setExtended(boolean z7) {
        if (this.E == z7) {
            return;
        }
        y5.f fVar = z7 ? this.A : this.f3868z;
        if (fVar.j()) {
            return;
        }
        fVar.e();
    }

    public void setHideMotionSpec(j5.h hVar) {
        this.C.c(hVar);
    }

    public void setHideMotionSpecResource(int i8) {
        setHideMotionSpec(j5.h.d(getContext(), i8));
    }

    public void setShowMotionSpec(j5.h hVar) {
        this.B.c(hVar);
    }

    public void setShowMotionSpecResource(int i8) {
        setShowMotionSpec(j5.h.d(getContext(), i8));
    }

    public void setShrinkMotionSpec(j5.h hVar) {
        this.f3868z.c(hVar);
    }

    public void setShrinkMotionSpecResource(int i8) {
        setShrinkMotionSpec(j5.h.d(getContext(), i8));
    }

    public final boolean t() {
        return getVisibility() != 0 ? this.f3866x == 2 : this.f3866x != 1;
    }

    public final void u(y5.f fVar, h hVar) {
        if (fVar.j()) {
            return;
        }
        if (!v()) {
            fVar.e();
            fVar.i(hVar);
            return;
        }
        measure(0, 0);
        AnimatorSet animatorSetG = fVar.g();
        animatorSetG.addListener(new c(fVar, hVar));
        Iterator<Animator.AnimatorListener> it = fVar.h().iterator();
        while (it.hasNext()) {
            animatorSetG.addListener(it.next());
        }
        animatorSetG.start();
    }

    public final boolean v() {
        return t.Q(this) && !isInEditMode();
    }

    public ExtendedFloatingActionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.extendedFloatingActionButtonStyle);
    }

    public static class ExtendedFloatingActionButtonBehavior<T extends ExtendedFloatingActionButton> extends CoordinatorLayout.c<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Rect f3869a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public h f3870b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public h f3871c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f3872d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f3873e;

        public ExtendedFloatingActionButtonBehavior() {
            this.f3872d = false;
            this.f3873e = true;
        }

        public static boolean G(View view) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.f) {
                return ((CoordinatorLayout.f) layoutParams).f() instanceof BottomSheetBehavior;
            }
            return false;
        }

        public void E(ExtendedFloatingActionButton extendedFloatingActionButton) {
            boolean z7 = this.f3873e;
            extendedFloatingActionButton.u(z7 ? extendedFloatingActionButton.A : extendedFloatingActionButton.B, z7 ? this.f3871c : this.f3870b);
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
        public boolean l(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, int i8) {
            List<View> listV = coordinatorLayout.v(extendedFloatingActionButton);
            int size = listV.size();
            for (int i9 = 0; i9 < size; i9++) {
                View view = listV.get(i9);
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
            coordinatorLayout.M(extendedFloatingActionButton, i8);
            return true;
        }

        public final boolean J(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            return (this.f3872d || this.f3873e) && ((CoordinatorLayout.f) extendedFloatingActionButton.getLayoutParams()).e() == view.getId();
        }

        public void K(ExtendedFloatingActionButton extendedFloatingActionButton) {
            boolean z7 = this.f3873e;
            extendedFloatingActionButton.u(z7 ? extendedFloatingActionButton.f3868z : extendedFloatingActionButton.C, z7 ? this.f3871c : this.f3870b);
        }

        public final boolean L(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (!J(appBarLayout, extendedFloatingActionButton)) {
                return false;
            }
            if (this.f3869a == null) {
                this.f3869a = new Rect();
            }
            Rect rect = this.f3869a;
            z5.c.a(coordinatorLayout, appBarLayout, rect);
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
            if (fVar.f1193h == 0) {
                fVar.f1193h = 80;
            }
        }

        public ExtendedFloatingActionButtonBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ExtendedFloatingActionButton_Behavior_Layout);
            this.f3872d = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoHide, false);
            this.f3873e = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoShrink, true);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ExtendedFloatingActionButton(Context context, AttributeSet attributeSet, int i8) {
        int i9 = f3863u;
        super(i6.a.c(context, attributeSet, i8, i9), attributeSet, i8);
        this.f3866x = 0;
        y5.a aVar = new y5.a();
        this.f3867y = aVar;
        i iVar = new i(aVar);
        this.B = iVar;
        g gVar = new g(aVar);
        this.C = gVar;
        this.E = true;
        Context context2 = getContext();
        this.D = new ExtendedFloatingActionButtonBehavior(context2, attributeSet);
        TypedArray typedArrayH = k.h(context2, attributeSet, R$styleable.ExtendedFloatingActionButton, i8, i9, new int[0]);
        j5.h hVarC = j5.h.c(context2, typedArrayH, R$styleable.ExtendedFloatingActionButton_showMotionSpec);
        j5.h hVarC2 = j5.h.c(context2, typedArrayH, R$styleable.ExtendedFloatingActionButton_hideMotionSpec);
        j5.h hVarC3 = j5.h.c(context2, typedArrayH, R$styleable.ExtendedFloatingActionButton_extendMotionSpec);
        j5.h hVarC4 = j5.h.c(context2, typedArrayH, R$styleable.ExtendedFloatingActionButton_shrinkMotionSpec);
        y5.a aVar2 = new y5.a();
        f fVar = new f(aVar2, new a(), true);
        this.A = fVar;
        f fVar2 = new f(aVar2, new b(), false);
        this.f3868z = fVar2;
        iVar.c(hVarC);
        gVar.c(hVarC2);
        fVar.c(hVarC3);
        fVar2.c(hVarC4);
        typedArrayH.recycle();
        setShapeAppearanceModel(m.g(context2, attributeSet, i8, i9, m.f4705a).m());
    }
}
