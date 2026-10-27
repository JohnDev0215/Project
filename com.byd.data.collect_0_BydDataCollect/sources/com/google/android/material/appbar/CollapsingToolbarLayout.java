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
import b.a.i;
import b.g.k.b0;
import b.g.k.p;
import b.g.k.t;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.internal.l;
import d.b.a.a.f;
import d.b.a.a.k;

/* JADX INFO: loaded from: classes.dex */
public class CollapsingToolbarLayout extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int f3661b = k.Widget_Design_CollapsingToolbar;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f3662c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3663d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Toolbar f3664e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private View f3665f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private View f3666g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f3667h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f3668i;
    private int j;
    private int k;
    private final Rect l;
    final com.google.android.material.internal.a m;
    private boolean n;
    private boolean o;
    private Drawable p;
    Drawable q;
    private int r;
    private boolean s;
    private ValueAnimator t;
    private long u;
    private int v;
    private AppBarLayout.e w;
    int x;
    b0 y;

    class a implements p {
        a() {
        }

        @Override // b.g.k.p
        public b0 a(View view, b0 b0Var) {
            return CollapsingToolbarLayout.this.j(b0Var);
        }
    }

    class b implements ValueAnimator.AnimatorUpdateListener {
        b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            CollapsingToolbarLayout.this.setScrimAlpha(((Integer) valueAnimator.getAnimatedValue()).intValue());
        }
    }

    private class d implements AppBarLayout.e {
        d() {
        }

        @Override // com.google.android.material.appbar.AppBarLayout.c
        public void a(AppBarLayout appBarLayout, int i2) {
            CollapsingToolbarLayout collapsingToolbarLayout = CollapsingToolbarLayout.this;
            collapsingToolbarLayout.x = i2;
            b0 b0Var = collapsingToolbarLayout.y;
            int i3 = b0Var != null ? b0Var.i() : 0;
            int childCount = CollapsingToolbarLayout.this.getChildCount();
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = CollapsingToolbarLayout.this.getChildAt(i4);
                c cVar = (c) childAt.getLayoutParams();
                com.google.android.material.appbar.d dVarH = CollapsingToolbarLayout.h(childAt);
                int i5 = cVar.f3671a;
                if (i5 == 1) {
                    dVarH.f(b.g.f.a.b(-i2, 0, CollapsingToolbarLayout.this.g(childAt)));
                } else if (i5 == 2) {
                    dVarH.f(Math.round((-i2) * cVar.f3672b));
                }
            }
            CollapsingToolbarLayout.this.n();
            CollapsingToolbarLayout collapsingToolbarLayout2 = CollapsingToolbarLayout.this;
            if (collapsingToolbarLayout2.q != null && i3 > 0) {
                t.a0(collapsingToolbarLayout2);
            }
            CollapsingToolbarLayout.this.m.d0(Math.abs(i2) / ((CollapsingToolbarLayout.this.getHeight() - t.A(CollapsingToolbarLayout.this)) - i3));
        }
    }

    public CollapsingToolbarLayout(Context context) {
        this(context, null);
    }

    private void a(int i2) {
        b();
        ValueAnimator valueAnimator = this.t;
        if (valueAnimator == null) {
            ValueAnimator valueAnimator2 = new ValueAnimator();
            this.t = valueAnimator2;
            valueAnimator2.setDuration(this.u);
            this.t.setInterpolator(i2 > this.r ? d.b.a.a.m.a.f4676c : d.b.a.a.m.a.f4677d);
            this.t.addUpdateListener(new b());
        } else if (valueAnimator.isRunning()) {
            this.t.cancel();
        }
        this.t.setIntValues(this.r, i2);
        this.t.start();
    }

    private void b() {
        if (this.f3662c) {
            Toolbar toolbar = null;
            this.f3664e = null;
            this.f3665f = null;
            int i2 = this.f3663d;
            if (i2 != -1) {
                Toolbar toolbar2 = (Toolbar) findViewById(i2);
                this.f3664e = toolbar2;
                if (toolbar2 != null) {
                    this.f3665f = c(toolbar2);
                }
            }
            if (this.f3664e == null) {
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
                this.f3664e = toolbar;
            }
            m();
            this.f3662c = false;
        }
    }

    private View c(View view) {
        for (ViewParent parent = view.getParent(); parent != this && parent != null; parent = parent.getParent()) {
            if (parent instanceof View) {
                view = parent;
            }
        }
        return view;
    }

    private static int f(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            return view.getHeight();
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        return view.getHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    static com.google.android.material.appbar.d h(View view) {
        int i2 = f.view_offset_helper;
        com.google.android.material.appbar.d dVar = (com.google.android.material.appbar.d) view.getTag(i2);
        if (dVar != null) {
            return dVar;
        }
        com.google.android.material.appbar.d dVar2 = new com.google.android.material.appbar.d(view);
        view.setTag(i2, dVar2);
        return dVar2;
    }

    private boolean i(View view) {
        View view2 = this.f3665f;
        if (view2 == null || view2 == this) {
            if (view == this.f3664e) {
                return true;
            }
        } else if (view == view2) {
            return true;
        }
        return false;
    }

    private void l() {
        setContentDescription(getTitle());
    }

    private void m() {
        View view;
        if (!this.n && (view = this.f3666g) != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.f3666g);
            }
        }
        if (!this.n || this.f3664e == null) {
            return;
        }
        if (this.f3666g == null) {
            this.f3666g = new View(getContext());
        }
        if (this.f3666g.getParent() == null) {
            this.f3664e.addView(this.f3666g, -1, -1);
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof c;
    }

    /* JADX INFO: Access modifiers changed from: protected */
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
        if (this.f3664e == null && (drawable = this.p) != null && this.r > 0) {
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
        int i2 = b0Var != null ? b0Var.i() : 0;
        if (i2 > 0) {
            this.q.setBounds(0, -this.x, getWidth(), i2 - this.x);
            this.q.mutate().setAlpha(this.r);
            this.q.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View view, long j) {
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
    protected void drawableStateChanged() {
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
        com.google.android.material.internal.a aVar = this.m;
        if (aVar != null) {
            zH0 |= aVar.h0(drawableState);
        }
        if (zH0) {
            invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.FrameLayout, android.view.ViewGroup
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public FrameLayout.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new c(layoutParams);
    }

    final int g(View view) {
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
        return this.f3667h;
    }

    public int getExpandedTitleMarginTop() {
        return this.f3668i;
    }

    public Typeface getExpandedTitleTypeface() {
        return this.m.y();
    }

    public int getMaxLines() {
        return this.m.A();
    }

    int getScrimAlpha() {
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
        int i3 = b0Var != null ? b0Var.i() : 0;
        int iA = t.A(this);
        return iA > 0 ? Math.min((iA * 2) + i3, getHeight()) : getHeight() / 3;
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

    b0 j(b0 b0Var) {
        b0 b0Var2 = t.w(this) ? b0Var : null;
        if (!b.g.j.c.a(this.y, b0Var2)) {
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

    final void n() {
        if (this.p == null && this.q == null) {
            return;
        }
        setScrimsShown(getHeight() + this.x < getScrimVisibleHeightTrigger());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
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
    protected void onDetachedFromWindow() {
        ViewParent parent = getParent();
        AppBarLayout.e eVar = this.w;
        if (eVar != null && (parent instanceof AppBarLayout)) {
            ((AppBarLayout) parent).p(eVar);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        View view;
        super.onLayout(z, i2, i3, i4, i5);
        b0 b0Var = this.y;
        if (b0Var != null) {
            int i6 = b0Var.i();
            int childCount = getChildCount();
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = getChildAt(i7);
                if (!t.w(childAt) && childAt.getTop() < i6) {
                    t.V(childAt, i6);
                }
            }
        }
        int childCount2 = getChildCount();
        for (int i8 = 0; i8 < childCount2; i8++) {
            h(getChildAt(i8)).d();
        }
        if (this.n && (view = this.f3666g) != null) {
            boolean z2 = t.O(view) && this.f3666g.getVisibility() == 0;
            this.o = z2;
            if (z2) {
                boolean z3 = t.z(this) == 1;
                View view2 = this.f3665f;
                if (view2 == null) {
                    view2 = this.f3664e;
                }
                int iG = g(view2);
                com.google.android.material.internal.c.a(this, this.f3666g, this.l);
                this.m.M(this.l.left + (z3 ? this.f3664e.getTitleMarginEnd() : this.f3664e.getTitleMarginStart()), this.l.top + iG + this.f3664e.getTitleMarginTop(), this.l.right - (z3 ? this.f3664e.getTitleMarginStart() : this.f3664e.getTitleMarginEnd()), (this.l.bottom + iG) - this.f3664e.getTitleMarginBottom());
                this.m.U(z3 ? this.j : this.f3667h, this.l.top + this.f3668i, (i4 - i2) - (z3 ? this.f3667h : this.j), (i5 - i3) - this.k);
                this.m.K();
            }
        }
        if (this.f3664e != null) {
            if (this.n && TextUtils.isEmpty(this.m.B())) {
                setTitle(this.f3664e.getTitle());
            }
            View view3 = this.f3665f;
            if (view3 == null || view3 == this) {
                setMinimumHeight(f(this.f3664e));
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
    protected void onMeasure(int i2, int i3) {
        b();
        super.onMeasure(i2, i3);
        int mode = View.MeasureSpec.getMode(i3);
        b0 b0Var = this.y;
        int i4 = b0Var != null ? b0Var.i() : 0;
        if (mode != 0 || i4 <= 0) {
            return;
        }
        super.onMeasure(i2, View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() + i4, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
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
        setContentScrim(androidx.core.content.a.d(getContext(), i2));
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
        this.f3667h = i2;
        requestLayout();
    }

    public void setExpandedTitleMarginTop(int i2) {
        this.f3668i = i2;
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

    void setScrimAlpha(int i2) {
        Toolbar toolbar;
        if (i2 != this.r) {
            if (this.p != null && (toolbar = this.f3664e) != null) {
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
                androidx.core.graphics.drawable.a.m(this.q, t.z(this));
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
        setStatusBarScrim(androidx.core.content.a.d(getContext(), i2));
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
    protected boolean verifyDrawable(Drawable drawable) {
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
        int i3 = f3661b;
        super(com.google.android.material.theme.a.a.c(context, attributeSet, i2, i3), attributeSet, i2);
        this.f3662c = true;
        this.l = new Rect();
        this.v = -1;
        Context context2 = getContext();
        com.google.android.material.internal.a aVar = new com.google.android.material.internal.a(this);
        this.m = aVar;
        aVar.j0(d.b.a.a.m.a.f4678e);
        TypedArray typedArrayH = l.h(context2, attributeSet, d.b.a.a.l.CollapsingToolbarLayout, i2, i3, new int[0]);
        aVar.Z(typedArrayH.getInt(d.b.a.a.l.CollapsingToolbarLayout_expandedTitleGravity, 8388691));
        aVar.R(typedArrayH.getInt(d.b.a.a.l.CollapsingToolbarLayout_collapsedTitleGravity, 8388627));
        int dimensionPixelSize = typedArrayH.getDimensionPixelSize(d.b.a.a.l.CollapsingToolbarLayout_expandedTitleMargin, 0);
        this.k = dimensionPixelSize;
        this.j = dimensionPixelSize;
        this.f3668i = dimensionPixelSize;
        this.f3667h = dimensionPixelSize;
        int i4 = d.b.a.a.l.CollapsingToolbarLayout_expandedTitleMarginStart;
        if (typedArrayH.hasValue(i4)) {
            this.f3667h = typedArrayH.getDimensionPixelSize(i4, 0);
        }
        int i5 = d.b.a.a.l.CollapsingToolbarLayout_expandedTitleMarginEnd;
        if (typedArrayH.hasValue(i5)) {
            this.j = typedArrayH.getDimensionPixelSize(i5, 0);
        }
        int i6 = d.b.a.a.l.CollapsingToolbarLayout_expandedTitleMarginTop;
        if (typedArrayH.hasValue(i6)) {
            this.f3668i = typedArrayH.getDimensionPixelSize(i6, 0);
        }
        int i7 = d.b.a.a.l.CollapsingToolbarLayout_expandedTitleMarginBottom;
        if (typedArrayH.hasValue(i7)) {
            this.k = typedArrayH.getDimensionPixelSize(i7, 0);
        }
        this.n = typedArrayH.getBoolean(d.b.a.a.l.CollapsingToolbarLayout_titleEnabled, true);
        setTitle(typedArrayH.getText(d.b.a.a.l.CollapsingToolbarLayout_title));
        aVar.W(k.TextAppearance_Design_CollapsingToolbar_Expanded);
        aVar.O(i.TextAppearance_AppCompat_Widget_ActionBar_Title);
        int i8 = d.b.a.a.l.CollapsingToolbarLayout_expandedTitleTextAppearance;
        if (typedArrayH.hasValue(i8)) {
            aVar.W(typedArrayH.getResourceId(i8, 0));
        }
        int i9 = d.b.a.a.l.CollapsingToolbarLayout_collapsedTitleTextAppearance;
        if (typedArrayH.hasValue(i9)) {
            aVar.O(typedArrayH.getResourceId(i9, 0));
        }
        this.v = typedArrayH.getDimensionPixelSize(d.b.a.a.l.CollapsingToolbarLayout_scrimVisibleHeightTrigger, -1);
        int i10 = d.b.a.a.l.CollapsingToolbarLayout_maxLines;
        if (typedArrayH.hasValue(i10)) {
            aVar.f0(typedArrayH.getInt(i10, 1));
        }
        this.u = typedArrayH.getInt(d.b.a.a.l.CollapsingToolbarLayout_scrimAnimationDuration, 600);
        setContentScrim(typedArrayH.getDrawable(d.b.a.a.l.CollapsingToolbarLayout_contentScrim));
        setStatusBarScrim(typedArrayH.getDrawable(d.b.a.a.l.CollapsingToolbarLayout_statusBarScrim));
        this.f3663d = typedArrayH.getResourceId(d.b.a.a.l.CollapsingToolbarLayout_toolbarId, -1);
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
        int f3671a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f3672b;

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f3671a = 0;
            this.f3672b = 0.5f;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, d.b.a.a.l.CollapsingToolbarLayout_Layout);
            this.f3671a = typedArrayObtainStyledAttributes.getInt(d.b.a.a.l.CollapsingToolbarLayout_Layout_layout_collapseMode, 0);
            a(typedArrayObtainStyledAttributes.getFloat(d.b.a.a.l.CollapsingToolbarLayout_Layout_layout_collapseParallaxMultiplier, 0.5f));
            typedArrayObtainStyledAttributes.recycle();
        }

        public void a(float f2) {
            this.f3672b = f2;
        }

        public c(int i2, int i3) {
            super(i2, i3);
            this.f3671a = 0;
            this.f3672b = 0.5f;
        }

        public c(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f3671a = 0;
            this.f3672b = 0.5f;
        }
    }
}
