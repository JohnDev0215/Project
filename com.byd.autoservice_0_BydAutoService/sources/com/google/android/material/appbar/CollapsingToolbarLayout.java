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
import i2.k;
import l0.b0;
import l0.p;
import l0.t;

/* JADX INFO: loaded from: classes.dex */
public class CollapsingToolbarLayout extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f1919b = R$style.Widget_Design_CollapsingToolbar;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1920c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1921d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Toolbar f1922e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public View f1923f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public View f1924g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1925h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1926i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f1927j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f1928k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Rect f1929l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final i2.a f1930m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f1931n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f1932o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Drawable f1933p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Drawable f1934q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f1935r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f1936s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public ValueAnimator f1937t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public long f1938u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f1939v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public AppBarLayout.e f1940w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f1941x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public b0 f1942y;

    public class a implements p {
        public a() {
        }

        @Override // l0.p
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
        public void a(AppBarLayout appBarLayout, int i3) {
            CollapsingToolbarLayout collapsingToolbarLayout = CollapsingToolbarLayout.this;
            collapsingToolbarLayout.f1941x = i3;
            b0 b0Var = collapsingToolbarLayout.f1942y;
            int iH = b0Var != null ? b0Var.h() : 0;
            int childCount = CollapsingToolbarLayout.this.getChildCount();
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = CollapsingToolbarLayout.this.getChildAt(i4);
                c cVar = (c) childAt.getLayoutParams();
                t1.d dVarH = CollapsingToolbarLayout.h(childAt);
                int i5 = cVar.f1945a;
                if (i5 == 1) {
                    dVarH.f(g0.a.b(-i3, 0, CollapsingToolbarLayout.this.g(childAt)));
                } else if (i5 == 2) {
                    dVarH.f(Math.round((-i3) * cVar.f1946b));
                }
            }
            CollapsingToolbarLayout.this.n();
            CollapsingToolbarLayout collapsingToolbarLayout2 = CollapsingToolbarLayout.this;
            if (collapsingToolbarLayout2.f1934q != null && iH > 0) {
                t.a0(collapsingToolbarLayout2);
            }
            CollapsingToolbarLayout.this.f1930m.d0(Math.abs(i3) / ((CollapsingToolbarLayout.this.getHeight() - t.A(CollapsingToolbarLayout.this)) - iH));
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

    public static t1.d h(View view) {
        int i3 = R$id.view_offset_helper;
        t1.d dVar = (t1.d) view.getTag(i3);
        if (dVar != null) {
            return dVar;
        }
        t1.d dVar2 = new t1.d(view);
        view.setTag(i3, dVar2);
        return dVar2;
    }

    public final void a(int i3) {
        b();
        ValueAnimator valueAnimator = this.f1937t;
        if (valueAnimator == null) {
            ValueAnimator valueAnimator2 = new ValueAnimator();
            this.f1937t = valueAnimator2;
            valueAnimator2.setDuration(this.f1938u);
            this.f1937t.setInterpolator(i3 > this.f1935r ? s1.a.f4903c : s1.a.f4904d);
            this.f1937t.addUpdateListener(new b());
        } else if (valueAnimator.isRunning()) {
            this.f1937t.cancel();
        }
        this.f1937t.setIntValues(this.f1935r, i3);
        this.f1937t.start();
    }

    public final void b() {
        if (this.f1920c) {
            Toolbar toolbar = null;
            this.f1922e = null;
            this.f1923f = null;
            int i3 = this.f1921d;
            if (i3 != -1) {
                Toolbar toolbar2 = (Toolbar) findViewById(i3);
                this.f1922e = toolbar2;
                if (toolbar2 != null) {
                    this.f1923f = c(toolbar2);
                }
            }
            if (this.f1922e == null) {
                int childCount = getChildCount();
                int i4 = 0;
                while (true) {
                    if (i4 >= childCount) {
                        break;
                    }
                    View childAt = getChildAt(i4);
                    if (childAt instanceof Toolbar) {
                        toolbar = (Toolbar) childAt;
                        break;
                    }
                    i4++;
                }
                this.f1922e = toolbar;
            }
            m();
            this.f1920c = false;
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
        if (this.f1922e == null && (drawable = this.f1933p) != null && this.f1935r > 0) {
            drawable.mutate().setAlpha(this.f1935r);
            this.f1933p.draw(canvas);
        }
        if (this.f1931n && this.f1932o) {
            this.f1930m.j(canvas);
        }
        if (this.f1934q == null || this.f1935r <= 0) {
            return;
        }
        b0 b0Var = this.f1942y;
        int iH = b0Var != null ? b0Var.h() : 0;
        if (iH > 0) {
            this.f1934q.setBounds(0, -this.f1941x, getWidth(), iH - this.f1941x);
            this.f1934q.mutate().setAlpha(this.f1935r);
            this.f1934q.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j3) {
        boolean z3;
        if (this.f1933p == null || this.f1935r <= 0 || !i(view)) {
            z3 = false;
        } else {
            this.f1933p.mutate().setAlpha(this.f1935r);
            this.f1933p.draw(canvas);
            z3 = true;
        }
        return super.drawChild(canvas, view, j3) || z3;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.f1934q;
        boolean zH0 = false;
        if (drawable != null && drawable.isStateful()) {
            zH0 = false | drawable.setState(drawableState);
        }
        Drawable drawable2 = this.f1933p;
        if (drawable2 != null && drawable2.isStateful()) {
            zH0 |= drawable2.setState(drawableState);
        }
        i2.a aVar = this.f1930m;
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
        return this.f1930m.o();
    }

    public Typeface getCollapsedTitleTypeface() {
        return this.f1930m.s();
    }

    public Drawable getContentScrim() {
        return this.f1933p;
    }

    public int getExpandedTitleGravity() {
        return this.f1930m.w();
    }

    public int getExpandedTitleMarginBottom() {
        return this.f1928k;
    }

    public int getExpandedTitleMarginEnd() {
        return this.f1927j;
    }

    public int getExpandedTitleMarginStart() {
        return this.f1925h;
    }

    public int getExpandedTitleMarginTop() {
        return this.f1926i;
    }

    public Typeface getExpandedTitleTypeface() {
        return this.f1930m.y();
    }

    public int getMaxLines() {
        return this.f1930m.A();
    }

    public int getScrimAlpha() {
        return this.f1935r;
    }

    public long getScrimAnimationDuration() {
        return this.f1938u;
    }

    public int getScrimVisibleHeightTrigger() {
        int i3 = this.f1939v;
        if (i3 >= 0) {
            return i3;
        }
        b0 b0Var = this.f1942y;
        int iH = b0Var != null ? b0Var.h() : 0;
        int iA = t.A(this);
        return iA > 0 ? Math.min((iA * 2) + iH, getHeight()) : getHeight() / 3;
    }

    public Drawable getStatusBarScrim() {
        return this.f1934q;
    }

    public CharSequence getTitle() {
        if (this.f1931n) {
            return this.f1930m.B();
        }
        return null;
    }

    public final boolean i(View view) {
        View view2 = this.f1923f;
        if (view2 == null || view2 == this) {
            if (view == this.f1922e) {
                return true;
            }
        } else if (view == view2) {
            return true;
        }
        return false;
    }

    public b0 j(b0 b0Var) {
        b0 b0Var2 = t.w(this) ? b0Var : null;
        if (!k0.c.a(this.f1942y, b0Var2)) {
            this.f1942y = b0Var2;
            requestLayout();
        }
        return b0Var.c();
    }

    public void k(boolean z3, boolean z4) {
        if (this.f1936s != z3) {
            if (z4) {
                a(z3 ? 255 : 0);
            } else {
                setScrimAlpha(z3 ? 255 : 0);
            }
            this.f1936s = z3;
        }
    }

    public final void l() {
        setContentDescription(getTitle());
    }

    public final void m() {
        View view;
        if (!this.f1931n && (view = this.f1924g) != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.f1924g);
            }
        }
        if (!this.f1931n || this.f1922e == null) {
            return;
        }
        if (this.f1924g == null) {
            this.f1924g = new View(getContext());
        }
        if (this.f1924g.getParent() == null) {
            this.f1922e.addView(this.f1924g, -1, -1);
        }
    }

    public final void n() {
        if (this.f1933p == null && this.f1934q == null) {
            return;
        }
        setScrimsShown(getHeight() + this.f1941x < getScrimVisibleHeightTrigger());
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Object parent = getParent();
        if (parent instanceof AppBarLayout) {
            t.r0(this, t.w((View) parent));
            if (this.f1940w == null) {
                this.f1940w = new d();
            }
            ((AppBarLayout) parent).b(this.f1940w);
            t.g0(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        ViewParent parent = getParent();
        AppBarLayout.e eVar = this.f1940w;
        if (eVar != null && (parent instanceof AppBarLayout)) {
            ((AppBarLayout) parent).p(eVar);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z3, int i3, int i4, int i5, int i6) {
        View view;
        super.onLayout(z3, i3, i4, i5, i6);
        b0 b0Var = this.f1942y;
        if (b0Var != null) {
            int iH = b0Var.h();
            int childCount = getChildCount();
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = getChildAt(i7);
                if (!t.w(childAt) && childAt.getTop() < iH) {
                    t.V(childAt, iH);
                }
            }
        }
        int childCount2 = getChildCount();
        for (int i8 = 0; i8 < childCount2; i8++) {
            h(getChildAt(i8)).d();
        }
        if (this.f1931n && (view = this.f1924g) != null) {
            boolean z4 = t.O(view) && this.f1924g.getVisibility() == 0;
            this.f1932o = z4;
            if (z4) {
                boolean z5 = t.z(this) == 1;
                View view2 = this.f1923f;
                if (view2 == null) {
                    view2 = this.f1922e;
                }
                int iG = g(view2);
                i2.c.a(this, this.f1924g, this.f1929l);
                this.f1930m.M(this.f1929l.left + (z5 ? this.f1922e.getTitleMarginEnd() : this.f1922e.getTitleMarginStart()), this.f1929l.top + iG + this.f1922e.getTitleMarginTop(), this.f1929l.right - (z5 ? this.f1922e.getTitleMarginStart() : this.f1922e.getTitleMarginEnd()), (this.f1929l.bottom + iG) - this.f1922e.getTitleMarginBottom());
                this.f1930m.U(z5 ? this.f1927j : this.f1925h, this.f1929l.top + this.f1926i, (i5 - i3) - (z5 ? this.f1925h : this.f1927j), (i6 - i4) - this.f1928k);
                this.f1930m.K();
            }
        }
        if (this.f1922e != null) {
            if (this.f1931n && TextUtils.isEmpty(this.f1930m.B())) {
                setTitle(this.f1922e.getTitle());
            }
            View view3 = this.f1923f;
            if (view3 == null || view3 == this) {
                setMinimumHeight(f(this.f1922e));
            } else {
                setMinimumHeight(f(view3));
            }
        }
        n();
        int childCount3 = getChildCount();
        for (int i9 = 0; i9 < childCount3; i9++) {
            h(getChildAt(i9)).a();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i3, int i4) {
        b();
        super.onMeasure(i3, i4);
        int mode = View.MeasureSpec.getMode(i4);
        b0 b0Var = this.f1942y;
        int iH = b0Var != null ? b0Var.h() : 0;
        if (mode != 0 || iH <= 0) {
            return;
        }
        super.onMeasure(i3, View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() + iH, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
    }

    @Override // android.view.View
    public void onSizeChanged(int i3, int i4, int i5, int i6) {
        super.onSizeChanged(i3, i4, i5, i6);
        Drawable drawable = this.f1933p;
        if (drawable != null) {
            drawable.setBounds(0, 0, i3, i4);
        }
    }

    public void setCollapsedTitleGravity(int i3) {
        this.f1930m.R(i3);
    }

    public void setCollapsedTitleTextAppearance(int i3) {
        this.f1930m.O(i3);
    }

    public void setCollapsedTitleTextColor(int i3) {
        setCollapsedTitleTextColor(ColorStateList.valueOf(i3));
    }

    public void setCollapsedTitleTypeface(Typeface typeface) {
        this.f1930m.S(typeface);
    }

    public void setContentScrim(Drawable drawable) {
        Drawable drawable2 = this.f1933p;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.f1933p = drawableMutate;
            if (drawableMutate != null) {
                drawableMutate.setBounds(0, 0, getWidth(), getHeight());
                this.f1933p.setCallback(this);
                this.f1933p.setAlpha(this.f1935r);
            }
            t.a0(this);
        }
    }

    public void setContentScrimColor(int i3) {
        setContentScrim(new ColorDrawable(i3));
    }

    public void setContentScrimResource(int i3) {
        setContentScrim(b0.a.d(getContext(), i3));
    }

    public void setExpandedTitleColor(int i3) {
        setExpandedTitleTextColor(ColorStateList.valueOf(i3));
    }

    public void setExpandedTitleGravity(int i3) {
        this.f1930m.Z(i3);
    }

    public void setExpandedTitleMarginBottom(int i3) {
        this.f1928k = i3;
        requestLayout();
    }

    public void setExpandedTitleMarginEnd(int i3) {
        this.f1927j = i3;
        requestLayout();
    }

    public void setExpandedTitleMarginStart(int i3) {
        this.f1925h = i3;
        requestLayout();
    }

    public void setExpandedTitleMarginTop(int i3) {
        this.f1926i = i3;
        requestLayout();
    }

    public void setExpandedTitleTextAppearance(int i3) {
        this.f1930m.W(i3);
    }

    public void setExpandedTitleTextColor(ColorStateList colorStateList) {
        this.f1930m.Y(colorStateList);
    }

    public void setExpandedTitleTypeface(Typeface typeface) {
        this.f1930m.b0(typeface);
    }

    public void setMaxLines(int i3) {
        this.f1930m.f0(i3);
    }

    public void setScrimAlpha(int i3) {
        Toolbar toolbar;
        if (i3 != this.f1935r) {
            if (this.f1933p != null && (toolbar = this.f1922e) != null) {
                t.a0(toolbar);
            }
            this.f1935r = i3;
            t.a0(this);
        }
    }

    public void setScrimAnimationDuration(long j3) {
        this.f1938u = j3;
    }

    public void setScrimVisibleHeightTrigger(int i3) {
        if (this.f1939v != i3) {
            this.f1939v = i3;
            n();
        }
    }

    public void setScrimsShown(boolean z3) {
        k(z3, t.P(this) && !isInEditMode());
    }

    public void setStatusBarScrim(Drawable drawable) {
        Drawable drawable2 = this.f1934q;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.f1934q = drawableMutate;
            if (drawableMutate != null) {
                if (drawableMutate.isStateful()) {
                    this.f1934q.setState(getDrawableState());
                }
                e0.a.m(this.f1934q, t.z(this));
                this.f1934q.setVisible(getVisibility() == 0, false);
                this.f1934q.setCallback(this);
                this.f1934q.setAlpha(this.f1935r);
            }
            t.a0(this);
        }
    }

    public void setStatusBarScrimColor(int i3) {
        setStatusBarScrim(new ColorDrawable(i3));
    }

    public void setStatusBarScrimResource(int i3) {
        setStatusBarScrim(b0.a.d(getContext(), i3));
    }

    public void setTitle(CharSequence charSequence) {
        this.f1930m.i0(charSequence);
        l();
    }

    public void setTitleEnabled(boolean z3) {
        if (z3 != this.f1931n) {
            this.f1931n = z3;
            l();
            m();
            requestLayout();
        }
    }

    @Override // android.view.View
    public void setVisibility(int i3) {
        super.setVisibility(i3);
        boolean z3 = i3 == 0;
        Drawable drawable = this.f1934q;
        if (drawable != null && drawable.isVisible() != z3) {
            this.f1934q.setVisible(z3, false);
        }
        Drawable drawable2 = this.f1933p;
        if (drawable2 == null || drawable2.isVisible() == z3) {
            return;
        }
        this.f1933p.setVisible(z3, false);
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f1933p || drawable == this.f1934q;
    }

    public CollapsingToolbarLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void setCollapsedTitleTextColor(ColorStateList colorStateList) {
        this.f1930m.Q(colorStateList);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public CollapsingToolbarLayout(Context context, AttributeSet attributeSet, int i3) {
        int i4 = f1919b;
        super(r2.a.c(context, attributeSet, i3, i4), attributeSet, i3);
        this.f1920c = true;
        this.f1929l = new Rect();
        this.f1939v = -1;
        Context context2 = getContext();
        i2.a aVar = new i2.a(this);
        this.f1930m = aVar;
        aVar.j0(s1.a.f4905e);
        TypedArray typedArrayH = k.h(context2, attributeSet, R$styleable.CollapsingToolbarLayout, i3, i4, new int[0]);
        aVar.Z(typedArrayH.getInt(R$styleable.CollapsingToolbarLayout_expandedTitleGravity, 8388691));
        aVar.R(typedArrayH.getInt(R$styleable.CollapsingToolbarLayout_collapsedTitleGravity, 8388627));
        int dimensionPixelSize = typedArrayH.getDimensionPixelSize(R$styleable.CollapsingToolbarLayout_expandedTitleMargin, 0);
        this.f1928k = dimensionPixelSize;
        this.f1927j = dimensionPixelSize;
        this.f1926i = dimensionPixelSize;
        this.f1925h = dimensionPixelSize;
        int i5 = R$styleable.CollapsingToolbarLayout_expandedTitleMarginStart;
        if (typedArrayH.hasValue(i5)) {
            this.f1925h = typedArrayH.getDimensionPixelSize(i5, 0);
        }
        int i6 = R$styleable.CollapsingToolbarLayout_expandedTitleMarginEnd;
        if (typedArrayH.hasValue(i6)) {
            this.f1927j = typedArrayH.getDimensionPixelSize(i6, 0);
        }
        int i7 = R$styleable.CollapsingToolbarLayout_expandedTitleMarginTop;
        if (typedArrayH.hasValue(i7)) {
            this.f1926i = typedArrayH.getDimensionPixelSize(i7, 0);
        }
        int i8 = R$styleable.CollapsingToolbarLayout_expandedTitleMarginBottom;
        if (typedArrayH.hasValue(i8)) {
            this.f1928k = typedArrayH.getDimensionPixelSize(i8, 0);
        }
        this.f1931n = typedArrayH.getBoolean(R$styleable.CollapsingToolbarLayout_titleEnabled, true);
        setTitle(typedArrayH.getText(R$styleable.CollapsingToolbarLayout_title));
        aVar.W(R$style.TextAppearance_Design_CollapsingToolbar_Expanded);
        aVar.O(androidx.appcompat.R$style.TextAppearance_AppCompat_Widget_ActionBar_Title);
        int i9 = R$styleable.CollapsingToolbarLayout_expandedTitleTextAppearance;
        if (typedArrayH.hasValue(i9)) {
            aVar.W(typedArrayH.getResourceId(i9, 0));
        }
        int i10 = R$styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance;
        if (typedArrayH.hasValue(i10)) {
            aVar.O(typedArrayH.getResourceId(i10, 0));
        }
        this.f1939v = typedArrayH.getDimensionPixelSize(R$styleable.CollapsingToolbarLayout_scrimVisibleHeightTrigger, -1);
        int i11 = R$styleable.CollapsingToolbarLayout_maxLines;
        if (typedArrayH.hasValue(i11)) {
            aVar.f0(typedArrayH.getInt(i11, 1));
        }
        this.f1938u = typedArrayH.getInt(R$styleable.CollapsingToolbarLayout_scrimAnimationDuration, 600);
        setContentScrim(typedArrayH.getDrawable(R$styleable.CollapsingToolbarLayout_contentScrim));
        setStatusBarScrim(typedArrayH.getDrawable(R$styleable.CollapsingToolbarLayout_statusBarScrim));
        this.f1921d = typedArrayH.getResourceId(R$styleable.CollapsingToolbarLayout_toolbarId, -1);
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
        public int f1945a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f1946b;

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1945a = 0;
            this.f1946b = 0.5f;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CollapsingToolbarLayout_Layout);
            this.f1945a = typedArrayObtainStyledAttributes.getInt(R$styleable.CollapsingToolbarLayout_Layout_layout_collapseMode, 0);
            a(typedArrayObtainStyledAttributes.getFloat(R$styleable.CollapsingToolbarLayout_Layout_layout_collapseParallaxMultiplier, 0.5f));
            typedArrayObtainStyledAttributes.recycle();
        }

        public void a(float f3) {
            this.f1946b = f3;
        }

        public c(int i3, int i4) {
            super(i3, i4);
            this.f1945a = 0;
            this.f1946b = 0.5f;
        }

        public c(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1945a = 0;
            this.f1946b = 0.5f;
        }
    }
}
