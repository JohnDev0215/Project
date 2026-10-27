package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
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
import com.google.android.material.R$animator;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import defpackage.fj1;
import defpackage.in3;
import defpackage.om3;
import defpackage.p80;
import defpackage.ph1;
import defpackage.qd;
import defpackage.r83;
import defpackage.uc1;
import defpackage.uj2;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ExtendedFloatingActionButton extends MaterialButton implements CoordinatorLayout.b {
    public static final int K = R$style.Widget_MaterialComponents_ExtendedFloatingActionButton_Icon;
    public static final c L = new c();
    public static final d M = new d();
    public static final e N = new e();
    public static final f O = new f();
    public final i A;
    public final h B;
    public final int C;
    public int D;
    public int E;
    public final ExtendedFloatingActionButtonBehavior F;
    public boolean G;
    public boolean H;
    public boolean I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public ColorStateList f30J;
    public int x;
    public final g y;
    public final g z;

    public class a implements j {
        public a() {
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.j
        public final ViewGroup.LayoutParams a() {
            return new ViewGroup.LayoutParams(-2, -2);
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.j
        public final int getHeight() {
            return ExtendedFloatingActionButton.this.getMeasuredHeight();
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.j
        public final int getPaddingEnd() {
            return ExtendedFloatingActionButton.this.E;
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.j
        public final int getPaddingStart() {
            return ExtendedFloatingActionButton.this.D;
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.j
        public final int getWidth() {
            ExtendedFloatingActionButton extendedFloatingActionButton = ExtendedFloatingActionButton.this;
            return (extendedFloatingActionButton.getMeasuredWidth() - (extendedFloatingActionButton.getCollapsedPadding() * 2)) + extendedFloatingActionButton.D + extendedFloatingActionButton.E;
        }
    }

    public class b implements j {
        public b() {
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.j
        public final ViewGroup.LayoutParams a() {
            return new ViewGroup.LayoutParams(getWidth(), getHeight());
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.j
        public final int getHeight() {
            return ExtendedFloatingActionButton.this.getCollapsedSize();
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.j
        public final int getPaddingEnd() {
            return ExtendedFloatingActionButton.this.getCollapsedPadding();
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.j
        public final int getPaddingStart() {
            return ExtendedFloatingActionButton.this.getCollapsedPadding();
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.j
        public final int getWidth() {
            return ExtendedFloatingActionButton.this.getCollapsedSize();
        }
    }

    public class c extends Property<View, Float> {
        public c() {
            super(Float.class, "width");
        }

        @Override // android.util.Property
        public final Float get(View view) {
            return Float.valueOf(view.getLayoutParams().width);
        }

        @Override // android.util.Property
        public final void set(View view, Float f) {
            View view2 = view;
            view2.getLayoutParams().width = f.intValue();
            view2.requestLayout();
        }
    }

    public class d extends Property<View, Float> {
        public d() {
            super(Float.class, "height");
        }

        @Override // android.util.Property
        public final Float get(View view) {
            return Float.valueOf(view.getLayoutParams().height);
        }

        @Override // android.util.Property
        public final void set(View view, Float f) {
            View view2 = view;
            view2.getLayoutParams().height = f.intValue();
            view2.requestLayout();
        }
    }

    public class e extends Property<View, Float> {
        public e() {
            super(Float.class, "paddingStart");
        }

        @Override // android.util.Property
        public final Float get(View view) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            return Float.valueOf(om3.e.e(view));
        }

        @Override // android.util.Property
        public final void set(View view, Float f) {
            View view2 = view;
            int iIntValue = f.intValue();
            int paddingTop = view2.getPaddingTop();
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.e.i(view2, iIntValue, paddingTop, om3.e.d(view2), view2.getPaddingBottom());
        }
    }

    public class f extends Property<View, Float> {
        public f() {
            super(Float.class, "paddingEnd");
        }

        @Override // android.util.Property
        public final Float get(View view) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            return Float.valueOf(om3.e.d(view));
        }

        @Override // android.util.Property
        public final void set(View view, Float f) {
            View view2 = view;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.e.i(view2, om3.e.e(view2), view2.getPaddingTop(), f.intValue(), view2.getPaddingBottom());
        }
    }

    public interface j {
        ViewGroup.LayoutParams a();

        int getHeight();

        int getPaddingEnd();

        int getPaddingStart();

        int getWidth();
    }

    public ExtendedFloatingActionButton(Context context) {
        this(context, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x001c, code lost:
    
        r0 = true;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void f(com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton r4, defpackage.qd r5) {
        /*
            boolean r0 = r5.f()
            if (r0 == 0) goto L7
            goto L64
        L7:
            java.util.WeakHashMap<android.view.View, in3> r0 = defpackage.om3.a
            boolean r0 = om3.g.c(r4)
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L2b
            int r0 = r4.getVisibility()
            if (r0 == 0) goto L20
            int r0 = r4.x
            r3 = 2
            if (r0 != r3) goto L1e
        L1c:
            r0 = r1
            goto L25
        L1e:
            r0 = r2
            goto L25
        L20:
            int r0 = r4.x
            if (r0 == r1) goto L1e
            goto L1c
        L25:
            if (r0 != 0) goto L32
            boolean r0 = r4.I
            if (r0 == 0) goto L32
        L2b:
            boolean r0 = r4.isInEditMode()
            if (r0 != 0) goto L32
            goto L33
        L32:
            r1 = r2
        L33:
            if (r1 != 0) goto L3c
            r5.e()
            r5.b()
            goto L64
        L3c:
            r4.measure(r2, r2)
            android.animation.AnimatorSet r4 = r5.g()
            wi0 r0 = new wi0
            r0.<init>(r5)
            r4.addListener(r0)
            java.util.ArrayList<android.animation.Animator$AnimatorListener> r5 = r5.c
            java.util.Iterator r5 = r5.iterator()
        L51:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L61
            java.lang.Object r0 = r5.next()
            android.animation.Animator$AnimatorListener r0 = (android.animation.Animator.AnimatorListener) r0
            r4.addListener(r0)
            goto L51
        L61:
            r4.start()
        L64:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.f(com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton, qd):void");
    }

    public final void g(ColorStateList colorStateList) {
        super.setTextColor(colorStateList);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.b
    public CoordinatorLayout.Behavior<ExtendedFloatingActionButton> getBehavior() {
        return this.F;
    }

    public int getCollapsedPadding() {
        return (getCollapsedSize() - getIconSize()) / 2;
    }

    public int getCollapsedSize() {
        int i2 = this.C;
        if (i2 >= 0) {
            return i2;
        }
        WeakHashMap<View, in3> weakHashMap = om3.a;
        return (Math.min(om3.e.e(this), om3.e.d(this)) * 2) + getIconSize();
    }

    public fj1 getExtendMotionSpec() {
        return this.z.f;
    }

    public fj1 getHideMotionSpec() {
        return this.B.f;
    }

    public fj1 getShowMotionSpec() {
        return this.A.f;
    }

    public fj1 getShrinkMotionSpec() {
        return this.y.f;
    }

    @Override // com.google.android.material.button.MaterialButton, android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.G && TextUtils.isEmpty(getText()) && getIcon() != null) {
            this.G = false;
            this.y.e();
        }
    }

    public void setAnimateShowBeforeLayout(boolean z) {
        this.I = z;
    }

    public void setExtendMotionSpec(fj1 fj1Var) {
        this.z.f = fj1Var;
    }

    public void setExtendMotionSpecResource(int i2) {
        setExtendMotionSpec(fj1.b(getContext(), i2));
    }

    public void setExtended(boolean z) {
        if (this.G == z) {
            return;
        }
        g gVar = z ? this.z : this.y;
        if (gVar.f()) {
            return;
        }
        gVar.e();
    }

    public void setHideMotionSpec(fj1 fj1Var) {
        this.B.f = fj1Var;
    }

    public void setHideMotionSpecResource(int i2) {
        setHideMotionSpec(fj1.b(getContext(), i2));
    }

    @Override // android.widget.TextView, android.view.View
    public final void setPadding(int i2, int i3, int i4, int i5) {
        super.setPadding(i2, i3, i4, i5);
        if (!this.G || this.H) {
            return;
        }
        WeakHashMap<View, in3> weakHashMap = om3.a;
        this.D = om3.e.e(this);
        this.E = om3.e.d(this);
    }

    @Override // android.widget.TextView, android.view.View
    public final void setPaddingRelative(int i2, int i3, int i4, int i5) {
        super.setPaddingRelative(i2, i3, i4, i5);
        if (!this.G || this.H) {
            return;
        }
        this.D = i2;
        this.E = i4;
    }

    public void setShowMotionSpec(fj1 fj1Var) {
        this.A.f = fj1Var;
    }

    public void setShowMotionSpecResource(int i2) {
        setShowMotionSpec(fj1.b(getContext(), i2));
    }

    public void setShrinkMotionSpec(fj1 fj1Var) {
        this.y.f = fj1Var;
    }

    public void setShrinkMotionSpecResource(int i2) {
        setShrinkMotionSpec(fj1.b(getContext(), i2));
    }

    @Override // android.widget.TextView
    public void setTextColor(int i2) {
        super.setTextColor(i2);
        this.f30J = getTextColors();
    }

    public ExtendedFloatingActionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.extendedFloatingActionButtonStyle);
    }

    public static class ExtendedFloatingActionButtonBehavior<T extends ExtendedFloatingActionButton> extends CoordinatorLayout.Behavior<T> {
        public Rect a;
        public final boolean b;
        public final boolean c;

        public ExtendedFloatingActionButtonBehavior() {
            this.b = false;
            this.c = true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final /* bridge */ /* synthetic */ boolean a(View view, Rect rect) {
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final void c(CoordinatorLayout.e eVar) {
            if (eVar.h == 0) {
                eVar.h = 80;
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean d(CoordinatorLayout coordinatorLayout, View view, View view2) {
            ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) view;
            if (view2 instanceof AppBarLayout) {
                s(coordinatorLayout, (AppBarLayout) view2, extendedFloatingActionButton);
            } else {
                ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                if (layoutParams instanceof CoordinatorLayout.e ? ((CoordinatorLayout.e) layoutParams).a instanceof BottomSheetBehavior : false) {
                    t(view2, extendedFloatingActionButton);
                }
            }
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean h(CoordinatorLayout coordinatorLayout, View view, int i) {
            ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) view;
            List<View> listP = coordinatorLayout.p(extendedFloatingActionButton);
            int size = listP.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view2 = listP.get(i2);
                if (!(view2 instanceof AppBarLayout)) {
                    ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                    if ((layoutParams instanceof CoordinatorLayout.e ? ((CoordinatorLayout.e) layoutParams).a instanceof BottomSheetBehavior : false) && t(view2, extendedFloatingActionButton)) {
                        break;
                    }
                } else {
                    if (s(coordinatorLayout, (AppBarLayout) view2, extendedFloatingActionButton)) {
                        break;
                    }
                }
            }
            coordinatorLayout.w(extendedFloatingActionButton, i);
            return true;
        }

        public final boolean s(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, ExtendedFloatingActionButton extendedFloatingActionButton) {
            CoordinatorLayout.e eVar = (CoordinatorLayout.e) extendedFloatingActionButton.getLayoutParams();
            boolean z = this.b;
            boolean z2 = this.c;
            if (!((z || z2) && eVar.f == appBarLayout.getId())) {
                return false;
            }
            if (this.a == null) {
                this.a = new Rect();
            }
            Rect rect = this.a;
            p80.a(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                ExtendedFloatingActionButton.f(extendedFloatingActionButton, z2 ? extendedFloatingActionButton.y : extendedFloatingActionButton.B);
            } else {
                ExtendedFloatingActionButton.f(extendedFloatingActionButton, z2 ? extendedFloatingActionButton.z : extendedFloatingActionButton.A);
            }
            return true;
        }

        public final boolean t(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            CoordinatorLayout.e eVar = (CoordinatorLayout.e) extendedFloatingActionButton.getLayoutParams();
            boolean z = this.b;
            boolean z2 = this.c;
            if (!((z || z2) && eVar.f == view.getId())) {
                return false;
            }
            if (view.getTop() < (extendedFloatingActionButton.getHeight() / 2) + ((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.e) extendedFloatingActionButton.getLayoutParams())).topMargin) {
                ExtendedFloatingActionButton.f(extendedFloatingActionButton, z2 ? extendedFloatingActionButton.y : extendedFloatingActionButton.B);
            } else {
                ExtendedFloatingActionButton.f(extendedFloatingActionButton, z2 ? extendedFloatingActionButton.z : extendedFloatingActionButton.A);
            }
            return true;
        }

        public ExtendedFloatingActionButtonBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ExtendedFloatingActionButton_Behavior_Layout);
            this.b = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoHide, false);
            this.c = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoShrink, true);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ExtendedFloatingActionButton(Context context, AttributeSet attributeSet, int i2) {
        int i3 = K;
        super(uc1.a(context, attributeSet, i2, i3), attributeSet, i2);
        this.x = 0;
        ph1 ph1Var = new ph1(2);
        i iVar = new i(ph1Var);
        this.A = iVar;
        h hVar = new h(ph1Var);
        this.B = hVar;
        this.G = true;
        this.H = false;
        this.I = false;
        Context context2 = getContext();
        this.F = new ExtendedFloatingActionButtonBehavior(context2, attributeSet);
        TypedArray typedArrayD = r83.d(context2, attributeSet, R$styleable.ExtendedFloatingActionButton, i2, i3, new int[0]);
        fj1 fj1VarA = fj1.a(context2, typedArrayD, R$styleable.ExtendedFloatingActionButton_showMotionSpec);
        fj1 fj1VarA2 = fj1.a(context2, typedArrayD, R$styleable.ExtendedFloatingActionButton_hideMotionSpec);
        fj1 fj1VarA3 = fj1.a(context2, typedArrayD, R$styleable.ExtendedFloatingActionButton_extendMotionSpec);
        fj1 fj1VarA4 = fj1.a(context2, typedArrayD, R$styleable.ExtendedFloatingActionButton_shrinkMotionSpec);
        this.C = typedArrayD.getDimensionPixelSize(R$styleable.ExtendedFloatingActionButton_collapsedSize, -1);
        WeakHashMap<View, in3> weakHashMap = om3.a;
        this.D = om3.e.e(this);
        this.E = om3.e.d(this);
        ph1 ph1Var2 = new ph1(2);
        g gVar = new g(ph1Var2, new a(), true);
        this.z = gVar;
        g gVar2 = new g(ph1Var2, new b(), false);
        this.y = gVar2;
        iVar.f = fj1VarA;
        hVar.f = fj1VarA2;
        gVar.f = fj1VarA3;
        gVar2.f = fj1VarA4;
        typedArrayD.recycle();
        setShapeAppearanceModel(new uj2(uj2.c(context2, attributeSet, i2, i3, uj2.m)));
        this.f30J = getTextColors();
    }

    @Override // android.widget.TextView
    public void setTextColor(ColorStateList colorStateList) {
        super.setTextColor(colorStateList);
        this.f30J = getTextColors();
    }

    public class g extends qd {
        public final j g;
        public final boolean h;

        public g(ph1 ph1Var, j jVar, boolean z) {
            super(ExtendedFloatingActionButton.this, ph1Var);
            this.g = jVar;
            this.h = z;
        }

        @Override // defpackage.gj1
        public final void a() {
            this.d.e = null;
            ExtendedFloatingActionButton extendedFloatingActionButton = ExtendedFloatingActionButton.this;
            extendedFloatingActionButton.H = false;
            extendedFloatingActionButton.setHorizontallyScrolling(false);
            ViewGroup.LayoutParams layoutParams = extendedFloatingActionButton.getLayoutParams();
            if (layoutParams == null) {
                return;
            }
            j jVar = this.g;
            layoutParams.width = jVar.a().width;
            layoutParams.height = jVar.a().height;
        }

        @Override // defpackage.gj1
        public final int d() {
            return this.h ? R$animator.mtrl_extended_fab_change_size_expand_motion_spec : R$animator.mtrl_extended_fab_change_size_collapse_motion_spec;
        }

        @Override // defpackage.gj1
        public final void e() {
            ExtendedFloatingActionButton extendedFloatingActionButton = ExtendedFloatingActionButton.this;
            extendedFloatingActionButton.G = this.h;
            ViewGroup.LayoutParams layoutParams = extendedFloatingActionButton.getLayoutParams();
            if (layoutParams == null) {
                return;
            }
            j jVar = this.g;
            layoutParams.width = jVar.a().width;
            layoutParams.height = jVar.a().height;
            int paddingStart = jVar.getPaddingStart();
            int paddingTop = extendedFloatingActionButton.getPaddingTop();
            int paddingEnd = jVar.getPaddingEnd();
            int paddingBottom = extendedFloatingActionButton.getPaddingBottom();
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.e.i(extendedFloatingActionButton, paddingStart, paddingTop, paddingEnd, paddingBottom);
            extendedFloatingActionButton.requestLayout();
        }

        @Override // defpackage.gj1
        public final boolean f() {
            ExtendedFloatingActionButton extendedFloatingActionButton = ExtendedFloatingActionButton.this;
            return this.h == extendedFloatingActionButton.G || extendedFloatingActionButton.getIcon() == null || TextUtils.isEmpty(extendedFloatingActionButton.getText());
        }

        @Override // defpackage.qd, defpackage.gj1
        public final AnimatorSet g() {
            fj1 fj1Var = this.f;
            if (fj1Var == null) {
                if (this.e == null) {
                    this.e = fj1.b(this.a, d());
                }
                fj1Var = this.e;
                fj1Var.getClass();
            }
            boolean zG = fj1Var.g("width");
            j jVar = this.g;
            ExtendedFloatingActionButton extendedFloatingActionButton = ExtendedFloatingActionButton.this;
            if (zG) {
                PropertyValuesHolder[] propertyValuesHolderArrE = fj1Var.e("width");
                propertyValuesHolderArrE[0].setFloatValues(extendedFloatingActionButton.getWidth(), jVar.getWidth());
                fj1Var.h("width", propertyValuesHolderArrE);
            }
            if (fj1Var.g("height")) {
                PropertyValuesHolder[] propertyValuesHolderArrE2 = fj1Var.e("height");
                propertyValuesHolderArrE2[0].setFloatValues(extendedFloatingActionButton.getHeight(), jVar.getHeight());
                fj1Var.h("height", propertyValuesHolderArrE2);
            }
            if (fj1Var.g("paddingStart")) {
                PropertyValuesHolder[] propertyValuesHolderArrE3 = fj1Var.e("paddingStart");
                PropertyValuesHolder propertyValuesHolder = propertyValuesHolderArrE3[0];
                WeakHashMap<View, in3> weakHashMap = om3.a;
                propertyValuesHolder.setFloatValues(om3.e.e(extendedFloatingActionButton), jVar.getPaddingStart());
                fj1Var.h("paddingStart", propertyValuesHolderArrE3);
            }
            if (fj1Var.g("paddingEnd")) {
                PropertyValuesHolder[] propertyValuesHolderArrE4 = fj1Var.e("paddingEnd");
                PropertyValuesHolder propertyValuesHolder2 = propertyValuesHolderArrE4[0];
                WeakHashMap<View, in3> weakHashMap2 = om3.a;
                propertyValuesHolder2.setFloatValues(om3.e.d(extendedFloatingActionButton), jVar.getPaddingEnd());
                fj1Var.h("paddingEnd", propertyValuesHolderArrE4);
            }
            if (fj1Var.g("labelOpacity")) {
                PropertyValuesHolder[] propertyValuesHolderArrE5 = fj1Var.e("labelOpacity");
                boolean z = this.h;
                propertyValuesHolderArrE5[0].setFloatValues(z ? 0.0f : 1.0f, z ? 1.0f : 0.0f);
                fj1Var.h("labelOpacity", propertyValuesHolderArrE5);
            }
            return h(fj1Var);
        }

        @Override // defpackage.gj1
        public final void onAnimationStart(Animator animator) {
            ph1 ph1Var = this.d;
            Animator animator2 = (Animator) ph1Var.e;
            if (animator2 != null) {
                animator2.cancel();
            }
            ph1Var.e = animator;
            boolean z = this.h;
            ExtendedFloatingActionButton extendedFloatingActionButton = ExtendedFloatingActionButton.this;
            extendedFloatingActionButton.G = z;
            extendedFloatingActionButton.H = true;
            extendedFloatingActionButton.setHorizontallyScrolling(true);
        }

        @Override // defpackage.gj1
        public final void b() {
        }
    }

    public class h extends qd {
        public boolean g;

        public h(ph1 ph1Var) {
            super(ExtendedFloatingActionButton.this, ph1Var);
        }

        @Override // defpackage.gj1
        public final void a() {
            this.d.e = null;
            ExtendedFloatingActionButton extendedFloatingActionButton = ExtendedFloatingActionButton.this;
            extendedFloatingActionButton.x = 0;
            if (this.g) {
                return;
            }
            extendedFloatingActionButton.setVisibility(8);
        }

        @Override // defpackage.qd, defpackage.gj1
        public final void c() {
            super.c();
            this.g = true;
        }

        @Override // defpackage.gj1
        public final int d() {
            return R$animator.mtrl_extended_fab_hide_motion_spec;
        }

        @Override // defpackage.gj1
        public final void e() {
            ExtendedFloatingActionButton.this.setVisibility(8);
        }

        @Override // defpackage.gj1
        public final boolean f() {
            int i = ExtendedFloatingActionButton.K;
            ExtendedFloatingActionButton extendedFloatingActionButton = ExtendedFloatingActionButton.this;
            if (extendedFloatingActionButton.getVisibility() == 0) {
                if (extendedFloatingActionButton.x == 1) {
                    return true;
                }
            } else if (extendedFloatingActionButton.x != 2) {
                return true;
            }
            return false;
        }

        @Override // defpackage.gj1
        public final void onAnimationStart(Animator animator) {
            ph1 ph1Var = this.d;
            Animator animator2 = (Animator) ph1Var.e;
            if (animator2 != null) {
                animator2.cancel();
            }
            ph1Var.e = animator;
            this.g = false;
            ExtendedFloatingActionButton extendedFloatingActionButton = ExtendedFloatingActionButton.this;
            extendedFloatingActionButton.setVisibility(0);
            extendedFloatingActionButton.x = 1;
        }

        @Override // defpackage.gj1
        public final void b() {
        }
    }

    public class i extends qd {
        public i(ph1 ph1Var) {
            super(ExtendedFloatingActionButton.this, ph1Var);
        }

        @Override // defpackage.gj1
        public final void a() {
            this.d.e = null;
            ExtendedFloatingActionButton.this.x = 0;
        }

        @Override // defpackage.gj1
        public final int d() {
            return R$animator.mtrl_extended_fab_show_motion_spec;
        }

        @Override // defpackage.gj1
        public final void e() {
            ExtendedFloatingActionButton extendedFloatingActionButton = ExtendedFloatingActionButton.this;
            extendedFloatingActionButton.setVisibility(0);
            extendedFloatingActionButton.setAlpha(1.0f);
            extendedFloatingActionButton.setScaleY(1.0f);
            extendedFloatingActionButton.setScaleX(1.0f);
        }

        @Override // defpackage.gj1
        public final boolean f() {
            int i = ExtendedFloatingActionButton.K;
            ExtendedFloatingActionButton extendedFloatingActionButton = ExtendedFloatingActionButton.this;
            if (extendedFloatingActionButton.getVisibility() != 0) {
                if (extendedFloatingActionButton.x != 2) {
                    return false;
                }
            } else if (extendedFloatingActionButton.x == 1) {
                return false;
            }
            return true;
        }

        @Override // defpackage.gj1
        public final void onAnimationStart(Animator animator) {
            ph1 ph1Var = this.d;
            Animator animator2 = (Animator) ph1Var.e;
            if (animator2 != null) {
                animator2.cancel();
            }
            ph1Var.e = animator;
            ExtendedFloatingActionButton extendedFloatingActionButton = ExtendedFloatingActionButton.this;
            extendedFloatingActionButton.setVisibility(0);
            extendedFloatingActionButton.x = 2;
        }

        @Override // defpackage.gj1
        public final void b() {
        }
    }
}
