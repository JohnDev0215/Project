package com.google.android.material.appbar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.R$id;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.appbar.AppBarLayout;
import o0.b0;
import o0.p;
import o0.t;
import z5.k;

/* JADX INFO: loaded from: classes2.dex */
public class CollapsingToolbarLayout extends FrameLayout {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f3503d = R$style.Widget_Design_CollapsingToolbar;
    public b0 A;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f3504e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3505f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Toolbar f3506g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public View f3507h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public View f3508i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f3509j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f3510k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f3511l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f3512m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Rect f3513n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final z5.a f3514o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f3515p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f3516q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Drawable f3517r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Drawable f3518s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f3519t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f3520u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public ValueAnimator f3521v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public long f3522w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f3523x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public AppBarLayout.e f3524y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f3525z;

    public class a implements p {
        public a() {
        }

        @Override // o0.p
        public b0 a(View view, b0 b0Var) {
            return CollapsingToolbarLayout.this.j(b0Var);
        }
    }

    public class b implements ValueAnimator.AnimatorUpdateListener {
        public b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            CollapsingToolbarLayout.this.setScrimAlpha(((Integer) valueAnimator.getAnimatedValue()).intValue());
        }
    }

    public class d implements AppBarLayout.e {
        public d() {
        }

        @Override // com.google.android.material.appbar.AppBarLayout.c
        public void a(AppBarLayout appBarLayout, int i8) {
            CollapsingToolbarLayout collapsingToolbarLayout = CollapsingToolbarLayout.this;
            collapsingToolbarLayout.f3525z = i8;
            b0 b0Var = collapsingToolbarLayout.A;
            int iH = b0Var != null ? b0Var.h() : 0;
            int childCount = CollapsingToolbarLayout.this.getChildCount();
            for (int i9 = 0; i9 < childCount; i9++) {
                View childAt = CollapsingToolbarLayout.this.getChildAt(i9);
                c cVar = (c) childAt.getLayoutParams();
                k5.d dVarH = CollapsingToolbarLayout.h(childAt);
                int i10 = cVar.f3528a;
                if (i10 == 1) {
                    dVarH.f(j0.a.b(-i8, 0, CollapsingToolbarLayout.this.g(childAt)));
                } else if (i10 == 2) {
                    dVarH.f(Math.round((-i8) * cVar.f3529b));
                }
            }
            CollapsingToolbarLayout.this.n();
            CollapsingToolbarLayout collapsingToolbarLayout2 = CollapsingToolbarLayout.this;
            if (collapsingToolbarLayout2.f3518s != null && iH > 0) {
                t.b0(collapsingToolbarLayout2);
            }
            CollapsingToolbarLayout.this.f3514o.d0(Math.abs(i8) / ((CollapsingToolbarLayout.this.getHeight() - t.B(CollapsingToolbarLayout.this)) - iH));
        }
    }

    public CollapsingToolbarLayout(Context context) {
        this(context, null);
    }

    public static int f(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            return view.getHeight();
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        return view.getHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    public static k5.d h(View view) {
        int i8 = R$id.view_offset_helper;
        k5.d dVar = (k5.d) view.getTag(i8);
        if (dVar != null) {
            return dVar;
        }
        k5.d dVar2 = new k5.d(view);
        view.setTag(i8, dVar2);
        return dVar2;
    }

    public final void a(int i8) {
        b();
        ValueAnimator valueAnimator = this.f3521v;
        if (valueAnimator == null) {
            ValueAnimator valueAnimator2 = new ValueAnimator();
            this.f3521v = valueAnimator2;
            valueAnimator2.setDuration(this.f3522w);
            this.f3521v.setInterpolator(i8 > this.f3519t ? j5.a.f5580c : j5.a.f5581d);
            this.f3521v.addUpdateListener(new b());
        } else if (valueAnimator.isRunning()) {
            this.f3521v.cancel();
        }
        this.f3521v.setIntValues(this.f3519t, i8);
        this.f3521v.start();
    }

    public final void b() {
        if (this.f3504e) {
            Toolbar toolbar = null;
            this.f3506g = null;
            this.f3507h = null;
            int i8 = this.f3505f;
            if (i8 != -1) {
                Toolbar toolbar2 = (Toolbar) findViewById(i8);
                this.f3506g = toolbar2;
                if (toolbar2 != null) {
                    this.f3507h = c(toolbar2);
                }
            }
            if (this.f3506g == null) {
                int childCount = getChildCount();
                int i9 = 0;
                while (true) {
                    if (i9 >= childCount) {
                        break;
                    }
                    View childAt = getChildAt(i9);
                    if (childAt instanceof Toolbar) {
                        toolbar = (Toolbar) childAt;
                        break;
                    }
                    i9++;
                }
                this.f3506g = toolbar;
            }
            m();
            this.f3504e = false;
        }
    }

    public final View c(View view) {
        for (ViewParent parent = view.getParent(); parent != this && parent != null; parent = parent.getParent()) {
            if (parent instanceof View) {
                view = parent;
            }
        }
        return view;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof c;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public c generateDefaultLayoutParams() {
        return new c(-1, -1);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        Drawable drawable;
        super.draw(canvas);
        b();
        if (this.f3506g == null && (drawable = this.f3517r) != null && this.f3519t > 0) {
            drawable.mutate().setAlpha(this.f3519t);
            this.f3517r.draw(canvas);
        }
        if (this.f3515p && this.f3516q) {
            this.f3514o.j(canvas);
        }
        if (this.f3518s == null || this.f3519t <= 0) {
            return;
        }
        b0 b0Var = this.A;
        int iH = b0Var != null ? b0Var.h() : 0;
        if (iH > 0) {
            this.f3518s.setBounds(0, -this.f3525z, getWidth(), iH - this.f3525z);
            this.f3518s.mutate().setAlpha(this.f3519t);
            this.f3518s.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j8) {
        boolean z7;
        if (this.f3517r == null || this.f3519t <= 0 || !i(view)) {
            z7 = false;
        } else {
            this.f3517r.mutate().setAlpha(this.f3519t);
            this.f3517r.draw(canvas);
            z7 = true;
        }
        return super.drawChild(canvas, view, j8) || z7;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.f3518s;
        boolean zH0 = false;
        if (drawable != null && drawable.isStateful()) {
            zH0 = false | drawable.setState(drawableState);
        }
        Drawable drawable2 = this.f3517r;
        if (drawable2 != null && drawable2.isStateful()) {
            zH0 |= drawable2.setState(drawableState);
        }
        z5.a aVar = this.f3514o;
        if (aVar != null) {
            zH0 |= aVar.h0(drawableState);
        }
        if (zH0) {
            invalidate();
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public FrameLayout.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new c(layoutParams);
    }

    public final int g(View view) {
        return ((getHeight() - h(view).b()) - view.getHeight()) - ((FrameLayout.LayoutParams) ((c) view.getLayoutParams())).bottomMargin;
    }

    public int getCollapsedTitleGravity() {
        return this.f3514o.o();
    }

    public Typeface getCollapsedTitleTypeface() {
        return this.f3514o.s();
    }

    public Drawable getContentScrim() {
        return this.f3517r;
    }

    public int getExpandedTitleGravity() {
        return this.f3514o.w();
    }

    public int getExpandedTitleMarginBottom() {
        return this.f3512m;
    }

    public int getExpandedTitleMarginEnd() {
        return this.f3511l;
    }

    public int getExpandedTitleMarginStart() {
        return this.f3509j;
    }

    public int getExpandedTitleMarginTop() {
        return this.f3510k;
    }

    public Typeface getExpandedTitleTypeface() {
        return this.f3514o.y();
    }

    public int getMaxLines() {
        return this.f3514o.A();
    }

    public int getScrimAlpha() {
        return this.f3519t;
    }

    public long getScrimAnimationDuration() {
        return this.f3522w;
    }

    public int getScrimVisibleHeightTrigger() {
        int i8 = this.f3523x;
        if (i8 >= 0) {
            return i8;
        }
        b0 b0Var = this.A;
        int iH = b0Var != null ? b0Var.h() : 0;
        int iB = t.B(this);
        return iB > 0 ? Math.min((iB * 2) + iH, getHeight()) : getHeight() / 3;
    }

    public Drawable getStatusBarScrim() {
        return this.f3518s;
    }

    public CharSequence getTitle() {
        if (this.f3515p) {
            return this.f3514o.B();
        }
        return null;
    }

    public final boolean i(View view) {
        View view2 = this.f3507h;
        if (view2 == null || view2 == this) {
            if (view == this.f3506g) {
                return true;
            }
        } else if (view == view2) {
            return true;
        }
        return false;
    }

    public b0 j(b0 b0Var) {
        b0 b0Var2 = t.x(this) ? b0Var : null;
        if (!n0.c.a(this.A, b0Var2)) {
            this.A = b0Var2;
            requestLayout();
        }
        return b0Var.c();
    }

    public void k(boolean z7, boolean z8) {
        if (this.f3520u != z7) {
            if (z8) {
                a(z7 ? 255 : 0);
            } else {
                setScrimAlpha(z7 ? 255 : 0);
            }
            this.f3520u = z7;
        }
    }

    public final void l() {
        setContentDescription(getTitle());
    }

    public final void m() {
        View view;
        if (!this.f3515p && (view = this.f3508i) != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.f3508i);
            }
        }
        if (!this.f3515p || this.f3506g == null) {
            return;
        }
        if (this.f3508i == null) {
            this.f3508i = new View(getContext());
        }
        if (this.f3508i.getParent() == null) {
            this.f3506g.addView(this.f3508i, -1, -1);
        }
    }

    public final void n() {
        if (this.f3517r == null && this.f3518s == null) {
            return;
        }
        setScrimsShown(getHeight() + this.f3525z < getScrimVisibleHeightTrigger());
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Object parent = getParent();
        if (parent instanceof AppBarLayout) {
            t.t0(this, t.x((View) parent));
            if (this.f3524y == null) {
                this.f3524y = new d();
            }
            ((AppBarLayout) parent).b(this.f3524y);
            t.i0(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        ViewParent parent = getParent();
        AppBarLayout.e eVar = this.f3524y;
        if (eVar != null && (parent instanceof AppBarLayout)) {
            ((AppBarLayout) parent).p(eVar);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        View view;
        super.onLayout(z7, i8, i9, i10, i11);
        b0 b0Var = this.A;
        if (b0Var != null) {
            int iH = b0Var.h();
            int childCount = getChildCount();
            for (int i12 = 0; i12 < childCount; i12++) {
                View childAt = getChildAt(i12);
                if (!t.x(childAt) && childAt.getTop() < iH) {
                    t.W(childAt, iH);
                }
            }
        }
        int childCount2 = getChildCount();
        for (int i13 = 0; i13 < childCount2; i13++) {
            h(getChildAt(i13)).d();
        }
        if (this.f3515p && (view = this.f3508i) != null) {
            boolean z8 = t.P(view) && this.f3508i.getVisibility() == 0;
            this.f3516q = z8;
            if (z8) {
                boolean z9 = t.A(this) == 1;
                View view2 = this.f3507h;
                if (view2 == null) {
                    view2 = this.f3506g;
                }
                int iG = g(view2);
                z5.c.a(this, this.f3508i, this.f3513n);
                this.f3514o.M(this.f3513n.left + (z9 ? this.f3506g.getTitleMarginEnd() : this.f3506g.getTitleMarginStart()), this.f3513n.top + iG + this.f3506g.getTitleMarginTop(), this.f3513n.right - (z9 ? this.f3506g.getTitleMarginStart() : this.f3506g.getTitleMarginEnd()), (this.f3513n.bottom + iG) - this.f3506g.getTitleMarginBottom());
                this.f3514o.U(z9 ? this.f3511l : this.f3509j, this.f3513n.top + this.f3510k, (i10 - i8) - (z9 ? this.f3509j : this.f3511l), (i11 - i9) - this.f3512m);
                this.f3514o.K();
            }
        }
        if (this.f3506g != null) {
            if (this.f3515p && TextUtils.isEmpty(this.f3514o.B())) {
                setTitle(this.f3506g.getTitle());
            }
            View view3 = this.f3507h;
            if (view3 == null || view3 == this) {
                setMinimumHeight(f(this.f3506g));
            } else {
                setMinimumHeight(f(view3));
            }
        }
        n();
        int childCount3 = getChildCount();
        for (int i14 = 0; i14 < childCount3; i14++) {
            h(getChildAt(i14)).a();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i8, int i9) {
        b();
        super.onMeasure(i8, i9);
        int mode = View.MeasureSpec.getMode(i9);
        b0 b0Var = this.A;
        int iH = b0Var != null ? b0Var.h() : 0;
        if (mode != 0 || iH <= 0) {
            return;
        }
        super.onMeasure(i8, View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() + iH, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
    }

    @Override // android.view.View
    public void onSizeChanged(int i8, int i9, int i10, int i11) {
        super.onSizeChanged(i8, i9, i10, i11);
        Drawable drawable = this.f3517r;
        if (drawable != null) {
            drawable.setBounds(0, 0, i8, i9);
        }
    }

    public void setCollapsedTitleGravity(int i8) {
        this.f3514o.R(i8);
    }

    public void setCollapsedTitleTextAppearance(int i8) {
        this.f3514o.O(i8);
    }

    public void setCollapsedTitleTextColor(int i8) {
        setCollapsedTitleTextColor(ColorStateList.valueOf(i8));
    }

    public void setCollapsedTitleTypeface(Typeface typeface) {
        this.f3514o.S(typeface);
    }

    public void setContentScrim(Drawable drawable) {
        Drawable drawable2 = this.f3517r;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.f3517r = drawableMutate;
            if (drawableMutate != null) {
                drawableMutate.setBounds(0, 0, getWidth(), getHeight());
                this.f3517r.setCallback(this);
                this.f3517r.setAlpha(this.f3519t);
            }
            t.b0(this);
        }
    }

    public void setContentScrimColor(int i8) {
        setContentScrim(new ColorDrawable(i8));
    }

    public void setContentScrimResource(int i8) {
        setContentScrim(e0.a.d(getContext(), i8));
    }

    public void setExpandedTitleColor(int i8) {
        setExpandedTitleTextColor(ColorStateList.valueOf(i8));
    }

    public void setExpandedTitleGravity(int i8) {
        this.f3514o.Z(i8);
    }

    public void setExpandedTitleMarginBottom(int i8) {
        this.f3512m = i8;
        requestLayout();
    }

    public void setExpandedTitleMarginEnd(int i8) {
        this.f3511l = i8;
        requestLayout();
    }

    public void setExpandedTitleMarginStart(int i8) {
        this.f3509j = i8;
        requestLayout();
    }

    public void setExpandedTitleMarginTop(int i8) {
        this.f3510k = i8;
        requestLayout();
    }

    public void setExpandedTitleTextAppearance(int i8) {
        this.f3514o.W(i8);
    }

    public void setExpandedTitleTextColor(ColorStateList colorStateList) {
        this.f3514o.Y(colorStateList);
    }

    public void setExpandedTitleTypeface(Typeface typeface) {
        this.f3514o.b0(typeface);
    }

    public void setMaxLines(int i8) {
        this.f3514o.f0(i8);
    }

    public void setScrimAlpha(int i8) {
        Toolbar toolbar;
        if (i8 != this.f3519t) {
            if (this.f3517r != null && (toolbar = this.f3506g) != null) {
                t.b0(toolbar);
            }
            this.f3519t = i8;
            t.b0(this);
        }
    }

    public void setScrimAnimationDuration(long j8) {
        this.f3522w = j8;
    }

    public void setScrimVisibleHeightTrigger(int i8) {
        if (this.f3523x != i8) {
            this.f3523x = i8;
            n();
        }
    }

    public void setScrimsShown(boolean z7) {
        k(z7, t.Q(this) && !isInEditMode());
    }

    public void setStatusBarScrim(Drawable drawable) {
        Drawable drawable2 = this.f3518s;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.f3518s = drawableMutate;
            if (drawableMutate != null) {
                if (drawableMutate.isStateful()) {
                    this.f3518s.setState(getDrawableState());
                }
                h0.a.m(this.f3518s, t.A(this));
                this.f3518s.setVisible(getVisibility() == 0, false);
                this.f3518s.setCallback(this);
                this.f3518s.setAlpha(this.f3519t);
            }
            t.b0(this);
        }
    }

    public void setStatusBarScrimColor(int i8) {
        setStatusBarScrim(new ColorDrawable(i8));
    }

    public void setStatusBarScrimResource(int i8) {
        setStatusBarScrim(e0.a.d(getContext(), i8));
    }

    public void setTitle(CharSequence charSequence) {
        this.f3514o.i0(charSequence);
        l();
    }

    public void setTitleEnabled(boolean z7) {
        if (z7 != this.f3515p) {
            this.f3515p = z7;
            l();
            m();
            requestLayout();
        }
    }

    @Override // android.view.View
    public void setVisibility(int i8) {
        super.setVisibility(i8);
        boolean z7 = i8 == 0;
        Drawable drawable = this.f3518s;
        if (drawable != null && drawable.isVisible() != z7) {
            this.f3518s.setVisible(z7, false);
        }
        Drawable drawable2 = this.f3517r;
        if (drawable2 == null || drawable2.isVisible() == z7) {
            return;
        }
        this.f3517r.setVisible(z7, false);
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f3517r || drawable == this.f3518s;
    }

    public CollapsingToolbarLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void setCollapsedTitleTextColor(ColorStateList colorStateList) {
        this.f3514o.Q(colorStateList);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public CollapsingToolbarLayout(Context context, AttributeSet attributeSet, int i8) {
        int i9 = f3503d;
        super(i6.a.c(context, attributeSet, i8, i9), attributeSet, i8);
        this.f3504e = true;
        this.f3513n = new Rect();
        this.f3523x = -1;
        Context context2 = getContext();
        z5.a aVar = new z5.a(this);
        this.f3514o = aVar;
        aVar.j0(j5.a.f5582e);
        TypedArray typedArrayH = k.h(context2, attributeSet, R$styleable.CollapsingToolbarLayout, i8, i9, new int[0]);
        aVar.Z(typedArrayH.getInt(R$styleable.CollapsingToolbarLayout_expandedTitleGravity, 8388691));
        aVar.R(typedArrayH.getInt(R$styleable.CollapsingToolbarLayout_collapsedTitleGravity, 8388627));
        int dimensionPixelSize = typedArrayH.getDimensionPixelSize(R$styleable.CollapsingToolbarLayout_expandedTitleMargin, 0);
        this.f3512m = dimensionPixelSize;
        this.f3511l = dimensionPixelSize;
        this.f3510k = dimensionPixelSize;
        this.f3509j = dimensionPixelSize;
        int i10 = R$styleable.CollapsingToolbarLayout_expandedTitleMarginStart;
        if (typedArrayH.hasValue(i10)) {
            this.f3509j = typedArrayH.getDimensionPixelSize(i10, 0);
        }
        int i11 = R$styleable.CollapsingToolbarLayout_expandedTitleMarginEnd;
        if (typedArrayH.hasValue(i11)) {
            this.f3511l = typedArrayH.getDimensionPixelSize(i11, 0);
        }
        int i12 = R$styleable.CollapsingToolbarLayout_expandedTitleMarginTop;
        if (typedArrayH.hasValue(i12)) {
            this.f3510k = typedArrayH.getDimensionPixelSize(i12, 0);
        }
        int i13 = R$styleable.CollapsingToolbarLayout_expandedTitleMarginBottom;
        if (typedArrayH.hasValue(i13)) {
            this.f3512m = typedArrayH.getDimensionPixelSize(i13, 0);
        }
        this.f3515p = typedArrayH.getBoolean(R$styleable.CollapsingToolbarLayout_titleEnabled, true);
        setTitle(typedArrayH.getText(R$styleable.CollapsingToolbarLayout_title));
        aVar.W(R$style.TextAppearance_Design_CollapsingToolbar_Expanded);
        aVar.O(androidx.appcompat.R$style.TextAppearance_AppCompat_Widget_ActionBar_Title);
        int i14 = R$styleable.CollapsingToolbarLayout_expandedTitleTextAppearance;
        if (typedArrayH.hasValue(i14)) {
            aVar.W(typedArrayH.getResourceId(i14, 0));
        }
        int i15 = R$styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance;
        if (typedArrayH.hasValue(i15)) {
            aVar.O(typedArrayH.getResourceId(i15, 0));
        }
        this.f3523x = typedArrayH.getDimensionPixelSize(R$styleable.CollapsingToolbarLayout_scrimVisibleHeightTrigger, -1);
        int i16 = R$styleable.CollapsingToolbarLayout_maxLines;
        if (typedArrayH.hasValue(i16)) {
            aVar.f0(typedArrayH.getInt(i16, 1));
        }
        this.f3522w = typedArrayH.getInt(R$styleable.CollapsingToolbarLayout_scrimAnimationDuration, 600);
        setContentScrim(typedArrayH.getDrawable(R$styleable.CollapsingToolbarLayout_contentScrim));
        setStatusBarScrim(typedArrayH.getDrawable(R$styleable.CollapsingToolbarLayout_statusBarScrim));
        this.f3505f = typedArrayH.getResourceId(R$styleable.CollapsingToolbarLayout_toolbarId, -1);
        typedArrayH.recycle();
        setWillNotDraw(false);
        t.y0(this, new a());
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new c(getContext(), attributeSet);
    }

    public static class c extends FrameLayout.LayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f3528a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f3529b;

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f3528a = 0;
            this.f3529b = 0.5f;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CollapsingToolbarLayout_Layout);
            this.f3528a = typedArrayObtainStyledAttributes.getInt(R$styleable.CollapsingToolbarLayout_Layout_layout_collapseMode, 0);
            a(typedArrayObtainStyledAttributes.getFloat(R$styleable.CollapsingToolbarLayout_Layout_layout_collapseParallaxMultiplier, 0.5f));
            typedArrayObtainStyledAttributes.recycle();
        }

        public void a(float f8) {
            this.f3529b = f8;
        }

        public c(int i8, int i9) {
            super(i8, i9);
            this.f3528a = 0;
            this.f3529b = 0.5f;
        }

        public c(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f3528a = 0;
            this.f3529b = 0.5f;
        }
    }
}
