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
import b.b.i;
import b.h.m.b0;
import b.h.m.p;
import b.h.m.t;
import c.b.a.a.f;
import c.b.a.a.k;
import c.b.a.a.l;
import com.google.android.material.appbar.AppBarLayout;

/* JADX INFO: loaded from: classes.dex */
public class CollapsingToolbarLayout extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f4476b = k.Widget_Design_CollapsingToolbar;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f4477c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4478d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Toolbar f4479e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public View f4480f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public View f4481g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4482h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4483i;
    public int j;
    public int k;
    public final Rect l;
    public final c.b.a.a.d0.a m;
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

        @Override // b.h.m.p
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
        public void a(AppBarLayout appBarLayout, int i2) {
            CollapsingToolbarLayout collapsingToolbarLayout = CollapsingToolbarLayout.this;
            collapsingToolbarLayout.x = i2;
            b0 b0Var = collapsingToolbarLayout.y;
            int iH = b0Var != null ? b0Var.h() : 0;
            int childCount = CollapsingToolbarLayout.this.getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = CollapsingToolbarLayout.this.getChildAt(i3);
                c cVar = (c) childAt.getLayoutParams();
                c.b.a.a.n.d dVarH = CollapsingToolbarLayout.h(childAt);
                int i4 = cVar.f4486a;
                if (i4 == 1) {
                    dVarH.f(b.h.h.a.b(-i2, 0, CollapsingToolbarLayout.this.g(childAt)));
                } else if (i4 == 2) {
                    dVarH.f(Math.round((-i2) * cVar.f4487b));
                }
            }
            CollapsingToolbarLayout.this.n();
            CollapsingToolbarLayout collapsingToolbarLayout2 = CollapsingToolbarLayout.this;
            if (collapsingToolbarLayout2.q != null && iH > 0) {
                t.a0(collapsingToolbarLayout2);
            }
            CollapsingToolbarLayout.this.m.d0(Math.abs(i2) / ((CollapsingToolbarLayout.this.getHeight() - t.A(CollapsingToolbarLayout.this)) - iH));
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

    public static c.b.a.a.n.d h(View view) {
        int i2 = f.view_offset_helper;
        c.b.a.a.n.d dVar = (c.b.a.a.n.d) view.getTag(i2);
        if (dVar != null) {
            return dVar;
        }
        c.b.a.a.n.d dVar2 = new c.b.a.a.n.d(view);
        view.setTag(i2, dVar2);
        return dVar2;
    }

    public final void a(int i2) {
        b();
        ValueAnimator valueAnimator = this.t;
        if (valueAnimator == null) {
            ValueAnimator valueAnimator2 = new ValueAnimator();
            this.t = valueAnimator2;
            valueAnimator2.setDuration(this.u);
            this.t.setInterpolator(i2 > this.r ? c.b.a.a.m.a.f4010c : c.b.a.a.m.a.f4011d);
            this.t.addUpdateListener(new b());
        } else if (valueAnimator.isRunning()) {
            this.t.cancel();
        }
        this.t.setIntValues(this.r, i2);
        this.t.start();
    }

    public final void b() {
        if (this.f4477c) {
            Toolbar toolbar = null;
            this.f4479e = null;
            this.f4480f = null;
            int i2 = this.f4478d;
            if (i2 != -1) {
                Toolbar toolbar2 = (Toolbar) findViewById(i2);
                this.f4479e = toolbar2;
                if (toolbar2 != null) {
                    this.f4480f = c(toolbar2);
                }
            }
            if (this.f4479e == null) {
                int childCount = getChildCount();
                int i3 = 0;
                while (true) {
                    if (i3 >= childCount) {
                        break;
                    }
                    View childAt = getChildAt(i3);
                    if (childAt instanceof Toolbar) {
                        toolbar = (Toolbar) childAt;
                        break;
                    }
                    i3++;
                }
                this.f4479e = toolbar;
            }
            m();
            this.f4477c = false;
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
        if (this.f4479e == null && (drawable = this.p) != null && this.r > 0) {
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
        c.b.a.a.d0.a aVar = this.m;
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
        return this.f4482h;
    }

    public int getExpandedTitleMarginTop() {
        return this.f4483i;
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
        int i2 = this.v;
        if (i2 >= 0) {
            return i2;
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
        View view2 = this.f4480f;
        if (view2 == null || view2 == this) {
            if (view == this.f4479e) {
                return true;
            }
        } else if (view == view2) {
            return true;
        }
        return false;
    }

    public b0 j(b0 b0Var) {
        b0 b0Var2 = t.w(this) ? b0Var : null;
        if (!b.h.l.b.a(this.y, b0Var2)) {
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
        if (!this.n && (view = this.f4481g) != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.f4481g);
            }
        }
        if (!this.n || this.f4479e == null) {
            return;
        }
        if (this.f4481g == null) {
            this.f4481g = new View(getContext());
        }
        if (this.f4481g.getParent() == null) {
            this.f4479e.addView(this.f4481g, -1, -1);
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
            t.s0(this, t.w((View) parent));
            if (this.w == null) {
                this.w = new d();
            }
            ((AppBarLayout) parent).b(this.w);
            t.h0(this);
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
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        View view;
        super.onLayout(z, i2, i3, i4, i5);
        b0 b0Var = this.y;
        if (b0Var != null) {
            int iH = b0Var.h();
            int childCount = getChildCount();
            for (int i6 = 0; i6 < childCount; i6++) {
                View childAt = getChildAt(i6);
                if (!t.w(childAt) && childAt.getTop() < iH) {
                    t.V(childAt, iH);
                }
            }
        }
        int childCount2 = getChildCount();
        for (int i7 = 0; i7 < childCount2; i7++) {
            h(getChildAt(i7)).d();
        }
        if (this.n && (view = this.f4481g) != null) {
            boolean z2 = t.O(view) && this.f4481g.getVisibility() == 0;
            this.o = z2;
            if (z2) {
                boolean z3 = t.z(this) == 1;
                View view2 = this.f4480f;
                if (view2 == null) {
                    view2 = this.f4479e;
                }
                int iG = g(view2);
                c.b.a.a.d0.c.a(this, this.f4481g, this.l);
                this.m.M(this.l.left + (z3 ? this.f4479e.getTitleMarginEnd() : this.f4479e.getTitleMarginStart()), this.l.top + iG + this.f4479e.getTitleMarginTop(), this.l.right - (z3 ? this.f4479e.getTitleMarginStart() : this.f4479e.getTitleMarginEnd()), (this.l.bottom + iG) - this.f4479e.getTitleMarginBottom());
                this.m.U(z3 ? this.j : this.f4482h, this.l.top + this.f4483i, (i4 - i2) - (z3 ? this.f4482h : this.j), (i5 - i3) - this.k);
                this.m.K();
            }
        }
        if (this.f4479e != null) {
            if (this.n && TextUtils.isEmpty(this.m.B())) {
                setTitle(this.f4479e.getTitle());
            }
            View view3 = this.f4480f;
            if (view3 == null || view3 == this) {
                setMinimumHeight(f(this.f4479e));
            } else {
                setMinimumHeight(f(view3));
            }
        }
        n();
        int childCount3 = getChildCount();
        for (int i8 = 0; i8 < childCount3; i8++) {
            h(getChildAt(i8)).a();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        b();
        super.onMeasure(i2, i3);
        int mode = View.MeasureSpec.getMode(i3);
        b0 b0Var = this.y;
        int iH = b0Var != null ? b0Var.h() : 0;
        if (mode != 0 || iH <= 0) {
            return;
        }
        super.onMeasure(i2, View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() + iH, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
    }

    @Override // android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        Drawable drawable = this.p;
        if (drawable != null) {
            drawable.setBounds(0, 0, i2, i3);
        }
    }

    public void setCollapsedTitleGravity(int i2) {
        this.m.R(i2);
    }

    public void setCollapsedTitleTextAppearance(int i2) {
        this.m.O(i2);
    }

    public void setCollapsedTitleTextColor(int i2) {
        setCollapsedTitleTextColor(ColorStateList.valueOf(i2));
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

    public void setContentScrimColor(int i2) {
        setContentScrim(new ColorDrawable(i2));
    }

    public void setContentScrimResource(int i2) {
        setContentScrim(b.h.e.a.d(getContext(), i2));
    }

    public void setExpandedTitleColor(int i2) {
        setExpandedTitleTextColor(ColorStateList.valueOf(i2));
    }

    public void setExpandedTitleGravity(int i2) {
        this.m.Z(i2);
    }

    public void setExpandedTitleMarginBottom(int i2) {
        this.k = i2;
        requestLayout();
    }

    public void setExpandedTitleMarginEnd(int i2) {
        this.j = i2;
        requestLayout();
    }

    public void setExpandedTitleMarginStart(int i2) {
        this.f4482h = i2;
        requestLayout();
    }

    public void setExpandedTitleMarginTop(int i2) {
        this.f4483i = i2;
        requestLayout();
    }

    public void setExpandedTitleTextAppearance(int i2) {
        this.m.W(i2);
    }

    public void setExpandedTitleTextColor(ColorStateList colorStateList) {
        this.m.Y(colorStateList);
    }

    public void setExpandedTitleTypeface(Typeface typeface) {
        this.m.b0(typeface);
    }

    public void setMaxLines(int i2) {
        this.m.f0(i2);
    }

    public void setScrimAlpha(int i2) {
        Toolbar toolbar;
        if (i2 != this.r) {
            if (this.p != null && (toolbar = this.f4479e) != null) {
                t.a0(toolbar);
            }
            this.r = i2;
            t.a0(this);
        }
    }

    public void setScrimAnimationDuration(long j) {
        this.u = j;
    }

    public void setScrimVisibleHeightTrigger(int i2) {
        if (this.v != i2) {
            this.v = i2;
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
                b.h.f.l.a.m(this.q, t.z(this));
                this.q.setVisible(getVisibility() == 0, false);
                this.q.setCallback(this);
                this.q.setAlpha(this.r);
            }
            t.a0(this);
        }
    }

    public void setStatusBarScrimColor(int i2) {
        setStatusBarScrim(new ColorDrawable(i2));
    }

    public void setStatusBarScrimResource(int i2) {
        setStatusBarScrim(b.h.e.a.d(getContext(), i2));
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
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        boolean z = i2 == 0;
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
    public CollapsingToolbarLayout(Context context, AttributeSet attributeSet, int i2) {
        int i3 = f4476b;
        super(c.b.a.a.m0.a.a.c(context, attributeSet, i2, i3), attributeSet, i2);
        this.f4477c = true;
        this.l = new Rect();
        this.v = -1;
        Context context2 = getContext();
        c.b.a.a.d0.a aVar = new c.b.a.a.d0.a(this);
        this.m = aVar;
        aVar.j0(c.b.a.a.m.a.f4012e);
        TypedArray typedArrayH = c.b.a.a.d0.k.h(context2, attributeSet, l.CollapsingToolbarLayout, i2, i3, new int[0]);
        aVar.Z(typedArrayH.getInt(l.CollapsingToolbarLayout_expandedTitleGravity, 8388691));
        aVar.R(typedArrayH.getInt(l.CollapsingToolbarLayout_collapsedTitleGravity, 8388627));
        int dimensionPixelSize = typedArrayH.getDimensionPixelSize(l.CollapsingToolbarLayout_expandedTitleMargin, 0);
        this.k = dimensionPixelSize;
        this.j = dimensionPixelSize;
        this.f4483i = dimensionPixelSize;
        this.f4482h = dimensionPixelSize;
        int i4 = l.CollapsingToolbarLayout_expandedTitleMarginStart;
        if (typedArrayH.hasValue(i4)) {
            this.f4482h = typedArrayH.getDimensionPixelSize(i4, 0);
        }
        int i5 = l.CollapsingToolbarLayout_expandedTitleMarginEnd;
        if (typedArrayH.hasValue(i5)) {
            this.j = typedArrayH.getDimensionPixelSize(i5, 0);
        }
        int i6 = l.CollapsingToolbarLayout_expandedTitleMarginTop;
        if (typedArrayH.hasValue(i6)) {
            this.f4483i = typedArrayH.getDimensionPixelSize(i6, 0);
        }
        int i7 = l.CollapsingToolbarLayout_expandedTitleMarginBottom;
        if (typedArrayH.hasValue(i7)) {
            this.k = typedArrayH.getDimensionPixelSize(i7, 0);
        }
        this.n = typedArrayH.getBoolean(l.CollapsingToolbarLayout_titleEnabled, true);
        setTitle(typedArrayH.getText(l.CollapsingToolbarLayout_title));
        aVar.W(k.TextAppearance_Design_CollapsingToolbar_Expanded);
        aVar.O(i.TextAppearance_AppCompat_Widget_ActionBar_Title);
        int i8 = l.CollapsingToolbarLayout_expandedTitleTextAppearance;
        if (typedArrayH.hasValue(i8)) {
            aVar.W(typedArrayH.getResourceId(i8, 0));
        }
        int i9 = l.CollapsingToolbarLayout_collapsedTitleTextAppearance;
        if (typedArrayH.hasValue(i9)) {
            aVar.O(typedArrayH.getResourceId(i9, 0));
        }
        this.v = typedArrayH.getDimensionPixelSize(l.CollapsingToolbarLayout_scrimVisibleHeightTrigger, -1);
        int i10 = l.CollapsingToolbarLayout_maxLines;
        if (typedArrayH.hasValue(i10)) {
            aVar.f0(typedArrayH.getInt(i10, 1));
        }
        this.u = typedArrayH.getInt(l.CollapsingToolbarLayout_scrimAnimationDuration, 600);
        setContentScrim(typedArrayH.getDrawable(l.CollapsingToolbarLayout_contentScrim));
        setStatusBarScrim(typedArrayH.getDrawable(l.CollapsingToolbarLayout_statusBarScrim));
        this.f4478d = typedArrayH.getResourceId(l.CollapsingToolbarLayout_toolbarId, -1);
        typedArrayH.recycle();
        setWillNotDraw(false);
        t.x0(this, new a());
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new c(getContext(), attributeSet);
    }

    public static class c extends FrameLayout.LayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f4486a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f4487b;

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f4486a = 0;
            this.f4487b = 0.5f;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, l.CollapsingToolbarLayout_Layout);
            this.f4486a = typedArrayObtainStyledAttributes.getInt(l.CollapsingToolbarLayout_Layout_layout_collapseMode, 0);
            a(typedArrayObtainStyledAttributes.getFloat(l.CollapsingToolbarLayout_Layout_layout_collapseParallaxMultiplier, 0.5f));
            typedArrayObtainStyledAttributes.recycle();
        }

        public void a(float f2) {
            this.f4487b = f2;
        }

        public c(int i2, int i3) {
            super(i2, i3);
            this.f4486a = 0;
            this.f4487b = 0.5f;
        }

        public c(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f4486a = 0;
            this.f4487b = 0.5f;
        }
    }
}
