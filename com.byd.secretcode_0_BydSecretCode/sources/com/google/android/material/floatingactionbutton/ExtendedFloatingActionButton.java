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
import defpackage.bj;
import defpackage.fl;
import defpackage.ih;
import defpackage.jj;
import defpackage.mk;
import defpackage.t9;
import defpackage.ti;
import defpackage.ui;
import defpackage.yi;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ExtendedFloatingActionButton extends MaterialButton implements CoordinatorLayout.b {
    public static final int s = R$style.Widget_MaterialComponents_ExtendedFloatingActionButton_Icon;
    public static final Property<View, Float> t = new d(Float.class, "width");
    public static final Property<View, Float> u = new e(Float.class, "height");
    public final yi A;
    public final CoordinatorLayout.c<ExtendedFloatingActionButton> B;
    public boolean C;
    public int v;
    public final ti w;
    public final yi x;
    public final yi y;
    public final yi z;

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
        public boolean a;
        public final /* synthetic */ yi b;
        public final /* synthetic */ h c;

        public c(yi yiVar, h hVar) {
            this.b = yiVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.a = true;
            this.b.d();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.b.c();
            if (this.a) {
                return;
            }
            this.b.f(this.c);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.b.onAnimationStart(animator);
            this.a = false;
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

    public class f extends ui {
        public final j g;
        public final boolean h;

        public f(ti tiVar, j jVar, boolean z) {
            super(ExtendedFloatingActionButton.this, tiVar);
            this.g = jVar;
            this.h = z;
        }

        @Override // defpackage.ui, defpackage.yi
        public AnimatorSet b() {
            ih ihVarL = l();
            if (ihVarL.j("width")) {
                PropertyValuesHolder[] propertyValuesHolderArrG = ihVarL.g("width");
                propertyValuesHolderArrG[0].setFloatValues(ExtendedFloatingActionButton.this.getWidth(), this.g.a());
                ihVarL.l("width", propertyValuesHolderArrG);
            }
            if (ihVarL.j("height")) {
                PropertyValuesHolder[] propertyValuesHolderArrG2 = ihVarL.g("height");
                propertyValuesHolderArrG2[0].setFloatValues(ExtendedFloatingActionButton.this.getHeight(), this.g.b());
                ihVarL.l("height", propertyValuesHolderArrG2);
            }
            return super.k(ihVarL);
        }

        @Override // defpackage.ui, defpackage.yi
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

        @Override // defpackage.yi
        public void f(h hVar) {
            if (hVar == null) {
                return;
            }
            if (!this.h) {
                throw null;
            }
            throw null;
        }

        @Override // defpackage.yi
        public boolean g() {
            return this.h == ExtendedFloatingActionButton.this.C || ExtendedFloatingActionButton.this.getIcon() == null || TextUtils.isEmpty(ExtendedFloatingActionButton.this.getText());
        }

        @Override // defpackage.yi
        public int i() {
            return R$animator.mtrl_extended_fab_change_size_motion_spec;
        }

        @Override // defpackage.yi
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

        @Override // defpackage.ui, defpackage.yi
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            ExtendedFloatingActionButton.this.C = this.h;
            ExtendedFloatingActionButton.this.setHorizontallyScrolling(true);
        }
    }

    public class g extends ui {
        public boolean g;

        public g(ti tiVar) {
            super(ExtendedFloatingActionButton.this, tiVar);
        }

        @Override // defpackage.ui, defpackage.yi
        public void c() {
            super.c();
            ExtendedFloatingActionButton.this.v = 0;
            if (this.g) {
                return;
            }
            ExtendedFloatingActionButton.this.setVisibility(8);
        }

        @Override // defpackage.ui, defpackage.yi
        public void d() {
            super.d();
            this.g = true;
        }

        @Override // defpackage.yi
        public void f(h hVar) {
            if (hVar != null) {
                throw null;
            }
        }

        @Override // defpackage.yi
        public boolean g() {
            return ExtendedFloatingActionButton.this.s();
        }

        @Override // defpackage.yi
        public int i() {
            return R$animator.mtrl_extended_fab_hide_motion_spec;
        }

        @Override // defpackage.yi
        public void j() {
            ExtendedFloatingActionButton.this.setVisibility(8);
        }

        @Override // defpackage.ui, defpackage.yi
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            this.g = false;
            ExtendedFloatingActionButton.this.setVisibility(0);
            ExtendedFloatingActionButton.this.v = 1;
        }
    }

    public static abstract class h {
    }

    public class i extends ui {
        public i(ti tiVar) {
            super(ExtendedFloatingActionButton.this, tiVar);
        }

        @Override // defpackage.ui, defpackage.yi
        public void c() {
            super.c();
            ExtendedFloatingActionButton.this.v = 0;
        }

        @Override // defpackage.yi
        public void f(h hVar) {
            if (hVar != null) {
                throw null;
            }
        }

        @Override // defpackage.yi
        public boolean g() {
            return ExtendedFloatingActionButton.this.t();
        }

        @Override // defpackage.yi
        public int i() {
            return R$animator.mtrl_extended_fab_show_motion_spec;
        }

        @Override // defpackage.yi
        public void j() {
            ExtendedFloatingActionButton.this.setVisibility(0);
            ExtendedFloatingActionButton.this.setAlpha(1.0f);
            ExtendedFloatingActionButton.this.setScaleY(1.0f);
            ExtendedFloatingActionButton.this.setScaleX(1.0f);
        }

        @Override // defpackage.ui, defpackage.yi
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
        return (Math.min(t9.E(this), t9.D(this)) * 2) + getIconSize();
    }

    public ih getExtendMotionSpec() {
        return this.y.a();
    }

    public ih getHideMotionSpec() {
        return this.A.a();
    }

    public ih getShowMotionSpec() {
        return this.z.a();
    }

    public ih getShrinkMotionSpec() {
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

    public void setExtendMotionSpec(ih ihVar) {
        this.y.h(ihVar);
    }

    public void setExtendMotionSpecResource(int i2) {
        setExtendMotionSpec(ih.d(getContext(), i2));
    }

    public void setExtended(boolean z) {
        if (this.C == z) {
            return;
        }
        yi yiVar = z ? this.y : this.x;
        if (yiVar.g()) {
            return;
        }
        yiVar.j();
    }

    public void setHideMotionSpec(ih ihVar) {
        this.A.h(ihVar);
    }

    public void setHideMotionSpecResource(int i2) {
        setHideMotionSpec(ih.d(getContext(), i2));
    }

    public void setShowMotionSpec(ih ihVar) {
        this.z.h(ihVar);
    }

    public void setShowMotionSpecResource(int i2) {
        setShowMotionSpec(ih.d(getContext(), i2));
    }

    public void setShrinkMotionSpec(ih ihVar) {
        this.x.h(ihVar);
    }

    public void setShrinkMotionSpecResource(int i2) {
        setShrinkMotionSpec(ih.d(getContext(), i2));
    }

    public final boolean t() {
        return getVisibility() != 0 ? this.v == 2 : this.v != 1;
    }

    public final void u(yi yiVar, h hVar) {
        if (yiVar.g()) {
            return;
        }
        if (!v()) {
            yiVar.j();
            yiVar.f(hVar);
            return;
        }
        measure(0, 0);
        AnimatorSet animatorSetB = yiVar.b();
        animatorSetB.addListener(new c(yiVar, hVar));
        Iterator<Animator.AnimatorListener> it = yiVar.e().iterator();
        while (it.hasNext()) {
            animatorSetB.addListener(it.next());
        }
        animatorSetB.start();
    }

    public final boolean v() {
        return t9.P(this) && !isInEditMode();
    }

    public ExtendedFloatingActionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.extendedFloatingActionButtonStyle);
    }

    public static class ExtendedFloatingActionButtonBehavior<T extends ExtendedFloatingActionButton> extends CoordinatorLayout.c<T> {
        public Rect a;
        public h b;
        public h c;
        public boolean d;
        public boolean e;

        public ExtendedFloatingActionButtonBehavior() {
            this.d = false;
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
            extendedFloatingActionButton.u(z ? extendedFloatingActionButton.y : extendedFloatingActionButton.z, z ? this.c : this.b);
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
            return (this.d || this.e) && ((CoordinatorLayout.f) extendedFloatingActionButton.getLayoutParams()).e() == view.getId();
        }

        public void K(ExtendedFloatingActionButton extendedFloatingActionButton) {
            boolean z = this.e;
            extendedFloatingActionButton.u(z ? extendedFloatingActionButton.x : extendedFloatingActionButton.A, z ? this.c : this.b);
        }

        public final boolean L(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (!J(appBarLayout, extendedFloatingActionButton)) {
                return false;
            }
            if (this.a == null) {
                this.a = new Rect();
            }
            Rect rect = this.a;
            bj.a(coordinatorLayout, appBarLayout, rect);
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
            this.d = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoHide, false);
            this.e = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoShrink, true);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ExtendedFloatingActionButton(Context context, AttributeSet attributeSet, int i2) {
        int i3 = s;
        super(fl.c(context, attributeSet, i2, i3), attributeSet, i2);
        this.v = 0;
        ti tiVar = new ti();
        this.w = tiVar;
        i iVar = new i(tiVar);
        this.z = iVar;
        g gVar = new g(tiVar);
        this.A = gVar;
        this.C = true;
        Context context2 = getContext();
        this.B = new ExtendedFloatingActionButtonBehavior(context2, attributeSet);
        TypedArray typedArrayH = jj.h(context2, attributeSet, R$styleable.ExtendedFloatingActionButton, i2, i3, new int[0]);
        ih ihVarC = ih.c(context2, typedArrayH, R$styleable.ExtendedFloatingActionButton_showMotionSpec);
        ih ihVarC2 = ih.c(context2, typedArrayH, R$styleable.ExtendedFloatingActionButton_hideMotionSpec);
        ih ihVarC3 = ih.c(context2, typedArrayH, R$styleable.ExtendedFloatingActionButton_extendMotionSpec);
        ih ihVarC4 = ih.c(context2, typedArrayH, R$styleable.ExtendedFloatingActionButton_shrinkMotionSpec);
        ti tiVar2 = new ti();
        f fVar = new f(tiVar2, new a(), true);
        this.y = fVar;
        f fVar2 = new f(tiVar2, new b(), false);
        this.x = fVar2;
        iVar.h(ihVarC);
        gVar.h(ihVarC2);
        fVar.h(ihVarC3);
        fVar2.h(ihVarC4);
        typedArrayH.recycle();
        setShapeAppearanceModel(mk.g(context2, attributeSet, i2, i3, mk.a).m());
    }
}
