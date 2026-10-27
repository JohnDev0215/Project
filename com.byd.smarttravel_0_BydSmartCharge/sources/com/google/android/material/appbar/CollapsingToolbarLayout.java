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
import b.g.j.b0;
import b.g.j.p;
import b.g.j.t;
import c.b.a.a.q.k;
import com.google.android.material.R$id;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.appbar.AppBarLayout;

/* JADX INFO: loaded from: classes.dex */
public class CollapsingToolbarLayout extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f2037b = R$style.Widget_Design_CollapsingToolbar;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f2038c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2039d;
    public Toolbar e;
    public View f;
    public View g;
    public int h;
    public int i;
    public int j;
    public int k;
    public final Rect l;
    public final c.b.a.a.q.a m;
    public boolean n;
    public boolean o;
    public Drawable p;
    public Drawable q;
    public int r;
    public boolean s;
    public ValueAnimator t;
    public long u;
    public int v;
    public AppBarLayout.e w;
    public int x;
    public b0 y;

    public class a implements p {
        public a() {
        }

        @Override // b.g.j.p
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
        public void a(AppBarLayout appBarLayout, int i) {
            CollapsingToolbarLayout collapsingToolbarLayout = CollapsingToolbarLayout.this;
            collapsingToolbarLayout.x = i;
            b0 b0Var = collapsingToolbarLayout.y;
            int iH = b0Var != null ? b0Var.h() : 0;
            int childCount = CollapsingToolbarLayout.this.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = CollapsingToolbarLayout.this.getChildAt(i2);
                c cVar = (c) childAt.getLayoutParams();
                c.b.a.a.b.d dVarH = CollapsingToolbarLayout.h(childAt);
                int i3 = cVar.f2042a;
                if (i3 == 1) {
                    dVarH.f(b.g.e.a.b(-i, 0, CollapsingToolbarLayout.this.g(childAt)));
                } else if (i3 == 2) {
                    dVarH.f(Math.round((-i) * cVar.f2043b));
                }
            }
            CollapsingToolbarLayout.this.n();
            CollapsingToolbarLayout collapsingToolbarLayout2 = CollapsingToolbarLayout.this;
            if (collapsingToolbarLayout2.q != null && iH > 0) {
                t.a0(collapsingToolbarLayout2);
            }
            CollapsingToolbarLayout.this.m.d0(Math.abs(i) / ((CollapsingToolbarLayout.this.getHeight() - t.A(CollapsingToolbarLayout.this)) - iH));
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

    public static c.b.a.a.b.d h(View view) {
        int i = R$id.view_offset_helper;
        c.b.a.a.b.d dVar = (c.b.a.a.b.d) view.getTag(i);
        if (dVar != null) {
            return dVar;
        }
        c.b.a.a.b.d dVar2 = new c.b.a.a.b.d(view);
        view.setTag(i, dVar2);
        return dVar2;
    }

    public final void a(int i) {
        b();
        ValueAnimator valueAnimator = this.t;
        if (valueAnimator == null) {
            ValueAnimator valueAnimator2 = new ValueAnimator();
            this.t = valueAnimator2;
            valueAnimator2.setDuration(this.u);
            this.t.setInterpolator(i > this.r ? c.b.a.a.a.a.f1650c : c.b.a.a.a.a.f1651d);
            this.t.addUpdateListener(new b());
        } else if (valueAnimator.isRunning()) {
            this.t.cancel();
        }
        this.t.setIntValues(this.r, i);
        this.t.start();
    }

    public final void b() {
        if (this.f2038c) {
            Toolbar toolbar = null;
            this.e = null;
            this.f = null;
            int i = this.f2039d;
            if (i != -1) {
                Toolbar toolbar2 = (Toolbar) findViewById(i);
                this.e = toolbar2;
                if (toolbar2 != null) {
                    this.f = c(toolbar2);
                }
            }
            if (this.e == null) {
                int childCount = getChildCount();
                int i2 = 0;
                while (true) {
                    if (i2 >= childCount) {
                        break;
                    }
                    View childAt = getChildAt(i2);
                    if (childAt instanceof Toolbar) {
                        toolbar = (Toolbar) childAt;
                        break;
                    }
                    i2++;
                }
                this.e = toolbar;
            }
            m();
            this.f2038c = false;
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
        if (this.e == null && (drawable = this.p) != null && this.r > 0) {
            drawable.mutate().setAlpha(this.r);
            this.p.draw(canvas);
        }
        if (this.n && this.o) {
            this.m.j(canvas);
        }
        if (this.q == null || this.r <= 0) {
            return;
        }
        b0 b0Var = this.y;
        int iH = b0Var != null ? b0Var.h() : 0;
        if (iH > 0) {
            this.q.setBounds(0, -this.x, getWidth(), iH - this.x);
            this.q.mutate().setAlpha(this.r);
            this.q.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j) {
        boolean z;
        if (this.p == null || this.r <= 0 || !i(view)) {
            z = false;
        } else {
            this.p.mutate().setAlpha(this.r);
            this.p.draw(canvas);
            z = true;
        }
        return super.drawChild(canvas, view, j) || z;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.q;
        boolean zH0 = false;
        if (drawable != null && drawable.isStateful()) {
            zH0 = false | drawable.setState(drawableState);
        }
        Drawable drawable2 = this.p;
        if (drawable2 != null && drawable2.isStateful()) {
            zH0 |= drawable2.setState(drawableState);
        }
        c.b.a.a.q.a aVar = this.m;
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
        return this.m.o();
    }

    public Typeface getCollapsedTitleTypeface() {
        return this.m.s();
    }

    public Drawable getContentScrim() {
        return this.p;
    }

    public int getExpandedTitleGravity() {
        return this.m.w();
    }

    public int getExpandedTitleMarginBottom() {
        return this.k;
    }

    public int getExpandedTitleMarginEnd() {
        return this.j;
    }

    public int getExpandedTitleMarginStart() {
        return this.h;
    }

    public int getExpandedTitleMarginTop() {
        return this.i;
    }

    public Typeface getExpandedTitleTypeface() {
        return this.m.y();
    }

    public int getMaxLines() {
        return this.m.A();
    }

    public int getScrimAlpha() {
        return this.r;
    }

    public long getScrimAnimationDuration() {
        return this.u;
    }

    public int getScrimVisibleHeightTrigger() {
        int i = this.v;
        if (i >= 0) {
            return i;
        }
        b0 b0Var = this.y;
        int iH = b0Var != null ? b0Var.h() : 0;
        int iA = t.A(this);
        return iA > 0 ? Math.min((iA * 2) + iH, getHeight()) : getHeight() / 3;
    }

    public Drawable getStatusBarScrim() {
        return this.q;
    }

    public CharSequence getTitle() {
        if (this.n) {
            return this.m.B();
        }
        return null;
    }

    public final boolean i(View view) {
        View view2 = this.f;
        if (view2 == null || view2 == this) {
            if (view == this.e) {
                return true;
            }
        } else if (view == view2) {
            return true;
        }
        return false;
    }

    public b0 j(b0 b0Var) {
        b0 b0Var2 = t.w(this) ? b0Var : null;
        if (!b.g.i.c.a(this.y, b0Var2)) {
            this.y = b0Var2;
            requestLayout();
        }
        return b0Var.c();
    }

    public void k(boolean z, boolean z2) {
        if (this.s != z) {
            if (z2) {
                a(z ? 255 : 0);
            } else {
                setScrimAlpha(z ? 255 : 0);
            }
            this.s = z;
        }
    }

    public final void l() {
        setContentDescription(getTitle());
    }

    public final void m() {
        View view;
        if (!this.n && (view = this.g) != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.g);
            }
        }
        if (!this.n || this.e == null) {
            return;
        }
        if (this.g == null) {
            this.g = new View(getContext());
        }
        if (this.g.getParent() == null) {
            this.e.addView(this.g, -1, -1);
        }
    }

    public final void n() {
        if (this.p == null && this.q == null) {
            return;
        }
        setScrimsShown(getHeight() + this.x < getScrimVisibleHeightTrigger());
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Object parent = getParent();
        if (parent instanceof AppBarLayout) {
            t.r0(this, t.w((View) parent));
            if (this.w == null) {
                this.w = new d();
            }
            ((AppBarLayout) parent).b(this.w);
            t.g0(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        ViewParent parent = getParent();
        AppBarLayout.e eVar = this.w;
        if (eVar != null && (parent instanceof AppBarLayout)) {
            ((AppBarLayout) parent).p(eVar);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View view;
        super.onLayout(z, i, i2, i3, i4);
        b0 b0Var = this.y;
        if (b0Var != null) {
            int iH = b0Var.h();
            int childCount = getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                View childAt = getChildAt(i5);
                if (!t.w(childAt) && childAt.getTop() < iH) {
                    t.V(childAt, iH);
                }
            }
        }
        int childCount2 = getChildCount();
        for (int i6 = 0; i6 < childCount2; i6++) {
            h(getChildAt(i6)).d();
        }
        if (this.n && (view = this.g) != null) {
            boolean z2 = t.O(view) && this.g.getVisibility() == 0;
            this.o = z2;
            if (z2) {
                boolean z3 = t.z(this) == 1;
                View view2 = this.f;
                if (view2 == null) {
                    view2 = this.e;
                }
                int iG = g(view2);
                c.b.a.a.q.c.a(this, this.g, this.l);
                this.m.M(this.l.left + (z3 ? this.e.getTitleMarginEnd() : this.e.getTitleMarginStart()), this.l.top + iG + this.e.getTitleMarginTop(), this.l.right - (z3 ? this.e.getTitleMarginStart() : this.e.getTitleMarginEnd()), (this.l.bottom + iG) - this.e.getTitleMarginBottom());
                this.m.U(z3 ? this.j : this.h, this.l.top + this.i, (i3 - i) - (z3 ? this.h : this.j), (i4 - i2) - this.k);
                this.m.K();
            }
        }
        if (this.e != null) {
            if (this.n && TextUtils.isEmpty(this.m.B())) {
                setTitle(this.e.getTitle());
            }
            View view3 = this.f;
            if (view3 == null || view3 == this) {
                setMinimumHeight(f(this.e));
            } else {
                setMinimumHeight(f(view3));
            }
        }
        n();
        int childCount3 = getChildCount();
        for (int i7 = 0; i7 < childCount3; i7++) {
            h(getChildAt(i7)).a();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        b();
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i2);
        b0 b0Var = this.y;
        int iH = b0Var != null ? b0Var.h() : 0;
        if (mode != 0 || iH <= 0) {
            return;
        }
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() + iH, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        Drawable drawable = this.p;
        if (drawable != null) {
            drawable.setBounds(0, 0, i, i2);
        }
    }

    public void setCollapsedTitleGravity(int i) {
        this.m.R(i);
    }

    public void setCollapsedTitleTextAppearance(int i) {
        this.m.O(i);
    }

    public void setCollapsedTitleTextColor(int i) {
        setCollapsedTitleTextColor(ColorStateList.valueOf(i));
    }

    public void setCollapsedTitleTypeface(Typeface typeface) {
        this.m.S(typeface);
    }

    public void setContentScrim(Drawable drawable) {
        Drawable drawable2 = this.p;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.p = drawableMutate;
            if (drawableMutate != null) {
                drawableMutate.setBounds(0, 0, getWidth(), getHeight());
                this.p.setCallback(this);
                this.p.setAlpha(this.r);
            }
            t.a0(this);
        }
    }

    public void setContentScrimColor(int i) {
        setContentScrim(new ColorDrawable(i));
    }

    public void setContentScrimResource(int i) {
        setContentScrim(b.g.b.a.d(getContext(), i));
    }

    public void setExpandedTitleColor(int i) {
        setExpandedTitleTextColor(ColorStateList.valueOf(i));
    }

    public void setExpandedTitleGravity(int i) {
        this.m.Z(i);
    }

    public void setExpandedTitleMarginBottom(int i) {
        this.k = i;
        requestLayout();
    }

    public void setExpandedTitleMarginEnd(int i) {
        this.j = i;
        requestLayout();
    }

    public void setExpandedTitleMarginStart(int i) {
        this.h = i;
        requestLayout();
    }

    public void setExpandedTitleMarginTop(int i) {
        this.i = i;
        requestLayout();
    }

    public void setExpandedTitleTextAppearance(int i) {
        this.m.W(i);
    }

    public void setExpandedTitleTextColor(ColorStateList colorStateList) {
        this.m.Y(colorStateList);
    }

    public void setExpandedTitleTypeface(Typeface typeface) {
        this.m.b0(typeface);
    }

    public void setMaxLines(int i) {
        this.m.f0(i);
    }

    public void setScrimAlpha(int i) {
        Toolbar toolbar;
        if (i != this.r) {
            if (this.p != null && (toolbar = this.e) != null) {
                t.a0(toolbar);
            }
            this.r = i;
            t.a0(this);
        }
    }

    public void setScrimAnimationDuration(long j) {
        this.u = j;
    }

    public void setScrimVisibleHeightTrigger(int i) {
        if (this.v != i) {
            this.v = i;
            n();
        }
    }

    public void setScrimsShown(boolean z) {
        k(z, t.P(this) && !isInEditMode());
    }

    public void setStatusBarScrim(Drawable drawable) {
        Drawable drawable2 = this.q;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.q = drawableMutate;
            if (drawableMutate != null) {
                if (drawableMutate.isStateful()) {
                    this.q.setState(getDrawableState());
                }
                b.g.c.l.a.m(this.q, t.z(this));
                this.q.setVisible(getVisibility() == 0, false);
                this.q.setCallback(this);
                this.q.setAlpha(this.r);
            }
            t.a0(this);
        }
    }

    public void setStatusBarScrimColor(int i) {
        setStatusBarScrim(new ColorDrawable(i));
    }

    public void setStatusBarScrimResource(int i) {
        setStatusBarScrim(b.g.b.a.d(getContext(), i));
    }

    public void setTitle(CharSequence charSequence) {
        this.m.i0(charSequence);
        l();
    }

    public void setTitleEnabled(boolean z) {
        if (z != this.n) {
            this.n = z;
            l();
            m();
            requestLayout();
        }
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        Drawable drawable = this.q;
        if (drawable != null && drawable.isVisible() != z) {
            this.q.setVisible(z, false);
        }
        Drawable drawable2 = this.p;
        if (drawable2 == null || drawable2.isVisible() == z) {
            return;
        }
        this.p.setVisible(z, false);
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.p || drawable == this.q;
    }

    public CollapsingToolbarLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void setCollapsedTitleTextColor(ColorStateList colorStateList) {
        this.m.Q(colorStateList);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public CollapsingToolbarLayout(Context context, AttributeSet attributeSet, int i) {
        int i2 = f2037b;
        super(c.b.a.a.z.a.a.c(context, attributeSet, i, i2), attributeSet, i);
        this.f2038c = true;
        this.l = new Rect();
        this.v = -1;
        Context context2 = getContext();
        c.b.a.a.q.a aVar = new c.b.a.a.q.a(this);
        this.m = aVar;
        aVar.j0(c.b.a.a.a.a.e);
        TypedArray typedArrayH = k.h(context2, attributeSet, R$styleable.CollapsingToolbarLayout, i, i2, new int[0]);
        aVar.Z(typedArrayH.getInt(R$styleable.CollapsingToolbarLayout_expandedTitleGravity, 8388691));
        aVar.R(typedArrayH.getInt(R$styleable.CollapsingToolbarLayout_collapsedTitleGravity, 8388627));
        int dimensionPixelSize = typedArrayH.getDimensionPixelSize(R$styleable.CollapsingToolbarLayout_expandedTitleMargin, 0);
        this.k = dimensionPixelSize;
        this.j = dimensionPixelSize;
        this.i = dimensionPixelSize;
        this.h = dimensionPixelSize;
        int i3 = R$styleable.CollapsingToolbarLayout_expandedTitleMarginStart;
        if (typedArrayH.hasValue(i3)) {
            this.h = typedArrayH.getDimensionPixelSize(i3, 0);
        }
        int i4 = R$styleable.CollapsingToolbarLayout_expandedTitleMarginEnd;
        if (typedArrayH.hasValue(i4)) {
            this.j = typedArrayH.getDimensionPixelSize(i4, 0);
        }
        int i5 = R$styleable.CollapsingToolbarLayout_expandedTitleMarginTop;
        if (typedArrayH.hasValue(i5)) {
            this.i = typedArrayH.getDimensionPixelSize(i5, 0);
        }
        int i6 = R$styleable.CollapsingToolbarLayout_expandedTitleMarginBottom;
        if (typedArrayH.hasValue(i6)) {
            this.k = typedArrayH.getDimensionPixelSize(i6, 0);
        }
        this.n = typedArrayH.getBoolean(R$styleable.CollapsingToolbarLayout_titleEnabled, true);
        setTitle(typedArrayH.getText(R$styleable.CollapsingToolbarLayout_title));
        aVar.W(R$style.TextAppearance_Design_CollapsingToolbar_Expanded);
        aVar.O(androidx.appcompat.R$style.TextAppearance_AppCompat_Widget_ActionBar_Title);
        int i7 = R$styleable.CollapsingToolbarLayout_expandedTitleTextAppearance;
        if (typedArrayH.hasValue(i7)) {
            aVar.W(typedArrayH.getResourceId(i7, 0));
        }
        int i8 = R$styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance;
        if (typedArrayH.hasValue(i8)) {
            aVar.O(typedArrayH.getResourceId(i8, 0));
        }
        this.v = typedArrayH.getDimensionPixelSize(R$styleable.CollapsingToolbarLayout_scrimVisibleHeightTrigger, -1);
        int i9 = R$styleable.CollapsingToolbarLayout_maxLines;
        if (typedArrayH.hasValue(i9)) {
            aVar.f0(typedArrayH.getInt(i9, 1));
        }
        this.u = typedArrayH.getInt(R$styleable.CollapsingToolbarLayout_scrimAnimationDuration, 600);
        setContentScrim(typedArrayH.getDrawable(R$styleable.CollapsingToolbarLayout_contentScrim));
        setStatusBarScrim(typedArrayH.getDrawable(R$styleable.CollapsingToolbarLayout_statusBarScrim));
        this.f2039d = typedArrayH.getResourceId(R$styleable.CollapsingToolbarLayout_toolbarId, -1);
        typedArrayH.recycle();
        setWillNotDraw(false);
        t.v0(this, new a());
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new c(getContext(), attributeSet);
    }

    public static class c extends FrameLayout.LayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2042a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f2043b;

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f2042a = 0;
            this.f2043b = 0.5f;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CollapsingToolbarLayout_Layout);
            this.f2042a = typedArrayObtainStyledAttributes.getInt(R$styleable.CollapsingToolbarLayout_Layout_layout_collapseMode, 0);
            a(typedArrayObtainStyledAttributes.getFloat(R$styleable.CollapsingToolbarLayout_Layout_layout_collapseParallaxMultiplier, 0.5f));
            typedArrayObtainStyledAttributes.recycle();
        }

        public void a(float f) {
            this.f2043b = f;
        }

        public c(int i, int i2) {
            super(i, i2);
            this.f2042a = 0;
            this.f2043b = 0.5f;
        }

        public c(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f2042a = 0;
            this.f2043b = 0.5f;
        }
    }
}
