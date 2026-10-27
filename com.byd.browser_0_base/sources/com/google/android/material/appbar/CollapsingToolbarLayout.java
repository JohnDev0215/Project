package com.google.android.material.appbar;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import androidx.appcompat.R;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$id;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.elevation.ElevationOverlayProvider;
import defpackage.C0173a;
import defpackage.bz;
import defpackage.cn3;
import defpackage.in3;
import defpackage.n3;
import defpackage.om3;
import defpackage.ou;
import defpackage.p80;
import defpackage.pt3;
import defpackage.pu;
import defpackage.r83;
import defpackage.rc1;
import defpackage.u5;
import defpackage.uc1;
import defpackage.wr1;
import java.util.Objects;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class CollapsingToolbarLayout extends FrameLayout {
    public static final int H = R$style.Widget_Design_CollapsingToolbar;
    public int A;
    public int B;
    public pt3 C;
    public int D;
    public boolean E;
    public int F;
    public boolean G;
    public boolean e;
    public final int f;
    public ViewGroup g;
    public View h;
    public View i;
    public int j;
    public int k;
    public int l;
    public int m;
    public final Rect n;
    public final ou o;
    public final ElevationOverlayProvider p;
    public boolean q;
    public boolean r;
    public Drawable s;
    public Drawable t;
    public int u;
    public boolean v;
    public ValueAnimator w;
    public long x;
    public int y;
    public b z;

    public class a implements wr1 {
        public a() {
        }

        @Override // defpackage.wr1
        public final pt3 k(View view, pt3 pt3Var) {
            CollapsingToolbarLayout collapsingToolbarLayout = CollapsingToolbarLayout.this;
            collapsingToolbarLayout.getClass();
            WeakHashMap<View, in3> weakHashMap = om3.a;
            pt3 pt3Var2 = om3.d.a(collapsingToolbarLayout) ? pt3Var : null;
            if (!Objects.equals(collapsingToolbarLayout.C, pt3Var2)) {
                collapsingToolbarLayout.C = pt3Var2;
                collapsingToolbarLayout.requestLayout();
            }
            return pt3Var.a.c();
        }
    }

    public class b implements AppBarLayout.f {
        public b() {
        }

        @Override // com.google.android.material.appbar.AppBarLayout.b
        public final void a(int i) {
            CollapsingToolbarLayout collapsingToolbarLayout = CollapsingToolbarLayout.this;
            collapsingToolbarLayout.A = i;
            pt3 pt3Var = collapsingToolbarLayout.C;
            int iE = pt3Var != null ? pt3Var.e() : 0;
            int childCount = collapsingToolbarLayout.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = collapsingToolbarLayout.getChildAt(i2);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                cn3 cn3VarB = CollapsingToolbarLayout.b(childAt);
                int i3 = layoutParams.a;
                if (i3 == 1) {
                    cn3VarB.b(n3.w(-i, 0, ((collapsingToolbarLayout.getHeight() - CollapsingToolbarLayout.b(childAt).b) - childAt.getHeight()) - ((FrameLayout.LayoutParams) ((LayoutParams) childAt.getLayoutParams())).bottomMargin));
                } else if (i3 == 2) {
                    cn3VarB.b(Math.round((-i) * layoutParams.b));
                }
            }
            collapsingToolbarLayout.d();
            if (collapsingToolbarLayout.t != null && iE > 0) {
                WeakHashMap<View, in3> weakHashMap = om3.a;
                om3.d.j(collapsingToolbarLayout);
            }
            int height = collapsingToolbarLayout.getHeight();
            WeakHashMap<View, in3> weakHashMap2 = om3.a;
            int iC = (height - om3.d.c(collapsingToolbarLayout)) - iE;
            float scrimVisibleHeightTrigger = height - collapsingToolbarLayout.getScrimVisibleHeightTrigger();
            float f = iC;
            float fMin = Math.min(1.0f, scrimVisibleHeightTrigger / f);
            ou ouVar = collapsingToolbarLayout.o;
            ouVar.d = fMin;
            ouVar.e = C0173a.f(1.0f, fMin, 0.5f, fMin);
            ouVar.f = collapsingToolbarLayout.A + iC;
            ouVar.p(Math.abs(i) / f);
        }
    }

    public CollapsingToolbarLayout(Context context) {
        this(context, null);
    }

    public static cn3 b(View view) {
        int i = R$id.view_offset_helper;
        cn3 cn3Var = (cn3) view.getTag(i);
        if (cn3Var != null) {
            return cn3Var;
        }
        cn3 cn3Var2 = new cn3(view);
        view.setTag(i, cn3Var2);
        return cn3Var2;
    }

    public final void a() {
        if (this.e) {
            ViewGroup viewGroup = null;
            this.g = null;
            this.h = null;
            int i = this.f;
            if (i != -1) {
                ViewGroup viewGroup2 = (ViewGroup) findViewById(i);
                this.g = viewGroup2;
                if (viewGroup2 != null) {
                    ViewParent parent = viewGroup2.getParent();
                    View view = viewGroup2;
                    while (parent != this && parent != null) {
                        if (parent instanceof View) {
                            view = (View) parent;
                        }
                        parent = parent.getParent();
                        view = view;
                    }
                    this.h = view;
                }
            }
            if (this.g == null) {
                int childCount = getChildCount();
                int i2 = 0;
                while (true) {
                    if (i2 >= childCount) {
                        break;
                    }
                    View childAt = getChildAt(i2);
                    if ((childAt instanceof Toolbar) || (childAt instanceof android.widget.Toolbar)) {
                        viewGroup = (ViewGroup) childAt;
                        break;
                    }
                    i2++;
                }
                this.g = viewGroup;
            }
            c();
            this.e = false;
        }
    }

    public final void c() {
        View view;
        if (!this.q && (view = this.i) != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.i);
            }
        }
        if (!this.q || this.g == null) {
            return;
        }
        if (this.i == null) {
            this.i = new View(getContext());
        }
        if (this.i.getParent() == null) {
            this.g.addView(this.i, -1, -1);
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public final void d() {
        if (this.s == null && this.t == null) {
            return;
        }
        setScrimsShown(getHeight() + this.A < getScrimVisibleHeightTrigger());
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x005e  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void draw(android.graphics.Canvas r7) {
        /*
            r6 = this;
            super.draw(r7)
            r6.a()
            android.view.ViewGroup r0 = r6.g
            if (r0 != 0) goto L20
            android.graphics.drawable.Drawable r0 = r6.s
            if (r0 == 0) goto L20
            int r1 = r6.u
            if (r1 <= 0) goto L20
            android.graphics.drawable.Drawable r0 = r0.mutate()
            int r1 = r6.u
            r0.setAlpha(r1)
            android.graphics.drawable.Drawable r0 = r6.s
            r0.draw(r7)
        L20:
            boolean r0 = r6.q
            r1 = 0
            if (r0 == 0) goto L61
            boolean r0 = r6.r
            if (r0 == 0) goto L61
            android.view.ViewGroup r0 = r6.g
            ou r2 = r6.o
            if (r0 == 0) goto L5e
            android.graphics.drawable.Drawable r0 = r6.s
            if (r0 == 0) goto L5e
            int r0 = r6.u
            if (r0 <= 0) goto L5e
            int r0 = r6.B
            r3 = 1
            if (r0 != r3) goto L3d
            goto L3e
        L3d:
            r3 = r1
        L3e:
            if (r3 == 0) goto L5e
            float r0 = r2.b
            float r3 = r2.e
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 >= 0) goto L5e
            int r0 = r7.save()
            android.graphics.drawable.Drawable r3 = r6.s
            android.graphics.Rect r3 = r3.getBounds()
            android.graphics.Region$Op r4 = android.graphics.Region.Op.DIFFERENCE
            r7.clipRect(r3, r4)
            r2.d(r7)
            r7.restoreToCount(r0)
            goto L61
        L5e:
            r2.d(r7)
        L61:
            android.graphics.drawable.Drawable r0 = r6.t
            if (r0 == 0) goto L94
            int r0 = r6.u
            if (r0 <= 0) goto L94
            pt3 r0 = r6.C
            if (r0 == 0) goto L72
            int r0 = r0.e()
            goto L73
        L72:
            r0 = r1
        L73:
            if (r0 <= 0) goto L94
            android.graphics.drawable.Drawable r2 = r6.t
            int r3 = r6.A
            int r3 = -r3
            int r4 = r6.getWidth()
            int r5 = r6.A
            int r0 = r0 - r5
            r2.setBounds(r1, r3, r4, r0)
            android.graphics.drawable.Drawable r0 = r6.t
            android.graphics.drawable.Drawable r0 = r0.mutate()
            int r1 = r6.u
            r0.setAlpha(r1)
            android.graphics.drawable.Drawable r6 = r6.t
            r6.draw(r7)
        L94:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.CollapsingToolbarLayout.draw(android.graphics.Canvas):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0018, code lost:
    
        r3 = true;
     */
    /* JADX WARN: Removed duplicated region for block: B:17:0x001d  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004d  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean drawChild(android.graphics.Canvas r7, android.view.View r8, long r9) {
        /*
            r6 = this;
            android.graphics.drawable.Drawable r0 = r6.s
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L4d
            int r3 = r6.u
            if (r3 <= 0) goto L4d
            android.view.View r3 = r6.h
            if (r3 == 0) goto L14
            if (r3 != r6) goto L11
            goto L14
        L11:
            if (r8 != r3) goto L1a
            goto L18
        L14:
            android.view.ViewGroup r3 = r6.g
            if (r8 != r3) goto L1a
        L18:
            r3 = r1
            goto L1b
        L1a:
            r3 = r2
        L1b:
            if (r3 == 0) goto L4d
            int r3 = r6.getWidth()
            int r4 = r6.getHeight()
            int r5 = r6.B
            if (r5 != r1) goto L2b
            r5 = r1
            goto L2c
        L2b:
            r5 = r2
        L2c:
            if (r5 == 0) goto L38
            if (r8 == 0) goto L38
            boolean r5 = r6.q
            if (r5 == 0) goto L38
            int r4 = r8.getBottom()
        L38:
            r0.setBounds(r2, r2, r3, r4)
            android.graphics.drawable.Drawable r0 = r6.s
            android.graphics.drawable.Drawable r0 = r0.mutate()
            int r3 = r6.u
            r0.setAlpha(r3)
            android.graphics.drawable.Drawable r0 = r6.s
            r0.draw(r7)
            r0 = r1
            goto L4e
        L4d:
            r0 = r2
        L4e:
            boolean r6 = super.drawChild(r7, r8, r9)
            if (r6 != 0) goto L58
            if (r0 == 0) goto L57
            goto L58
        L57:
            r1 = r2
        L58:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.CollapsingToolbarLayout.drawChild(android.graphics.Canvas, android.view.View, long):boolean");
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.t;
        boolean zR = false;
        if (drawable != null && drawable.isStateful()) {
            zR = false | drawable.setState(drawableState);
        }
        Drawable drawable2 = this.s;
        if (drawable2 != null && drawable2.isStateful()) {
            zR |= drawable2.setState(drawableState);
        }
        ou ouVar = this.o;
        if (ouVar != null) {
            zR |= ouVar.r(drawableState);
        }
        if (zR) {
            invalidate();
        }
    }

    public final void f(boolean z, int i, int i2, int i3, int i4) {
        View view;
        int titleMarginBottom;
        int titleMarginStart;
        int titleMarginEnd;
        int titleMarginTop;
        if (!this.q || (view = this.i) == null) {
            return;
        }
        WeakHashMap<View, in3> weakHashMap = om3.a;
        boolean z2 = false;
        boolean z3 = om3.g.b(view) && this.i.getVisibility() == 0;
        this.r = z3;
        if (z3 || z) {
            boolean z4 = om3.e.c(this) == 1;
            View view2 = this.h;
            if (view2 == null) {
                view2 = this.g;
            }
            int height = ((getHeight() - b(view2).b) - view2.getHeight()) - ((FrameLayout.LayoutParams) ((LayoutParams) view2.getLayoutParams())).bottomMargin;
            View view3 = this.i;
            Rect rect = this.n;
            p80.a(this, view3, rect);
            ViewGroup viewGroup = this.g;
            if (viewGroup instanceof Toolbar) {
                Toolbar toolbar = (Toolbar) viewGroup;
                titleMarginStart = toolbar.getTitleMarginStart();
                titleMarginEnd = toolbar.getTitleMarginEnd();
                titleMarginTop = toolbar.getTitleMarginTop();
                titleMarginBottom = toolbar.getTitleMarginBottom();
            } else if (viewGroup instanceof android.widget.Toolbar) {
                android.widget.Toolbar toolbar2 = (android.widget.Toolbar) viewGroup;
                titleMarginStart = toolbar2.getTitleMarginStart();
                titleMarginEnd = toolbar2.getTitleMarginEnd();
                titleMarginTop = toolbar2.getTitleMarginTop();
                titleMarginBottom = toolbar2.getTitleMarginBottom();
            } else {
                titleMarginBottom = 0;
                titleMarginStart = 0;
                titleMarginEnd = 0;
                titleMarginTop = 0;
            }
            int i5 = rect.left + (z4 ? titleMarginEnd : titleMarginStart);
            int i6 = rect.top + height + titleMarginTop;
            int i7 = rect.right;
            if (!z4) {
                titleMarginStart = titleMarginEnd;
            }
            int i8 = i7 - titleMarginStart;
            int i9 = (rect.bottom + height) - titleMarginBottom;
            ou ouVar = this.o;
            Rect rect2 = ouVar.h;
            if (!(rect2.left == i5 && rect2.top == i6 && rect2.right == i8 && rect2.bottom == i9)) {
                rect2.set(i5, i6, i8, i9);
                ouVar.S = true;
            }
            int i10 = z4 ? this.l : this.j;
            int i11 = rect.top + this.k;
            int i12 = (i3 - i) - (z4 ? this.j : this.l);
            int i13 = (i4 - i2) - this.m;
            Rect rect3 = ouVar.g;
            if (rect3.left == i10 && rect3.top == i11 && rect3.right == i12 && rect3.bottom == i13) {
                z2 = true;
            }
            if (!z2) {
                rect3.set(i10, i11, i12, i13);
                ouVar.S = true;
            }
            ouVar.i(z);
        }
    }

    public final void g() {
        if (this.g != null && this.q && TextUtils.isEmpty(this.o.G)) {
            ViewGroup viewGroup = this.g;
            setTitle(viewGroup instanceof Toolbar ? ((Toolbar) viewGroup).getTitle() : viewGroup instanceof android.widget.Toolbar ? ((android.widget.Toolbar) viewGroup).getTitle() : null);
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    public int getCollapsedTitleGravity() {
        return this.o.k;
    }

    public Typeface getCollapsedTitleTypeface() {
        Typeface typeface = this.o.w;
        return typeface != null ? typeface : Typeface.DEFAULT;
    }

    public Drawable getContentScrim() {
        return this.s;
    }

    public int getExpandedTitleGravity() {
        return this.o.j;
    }

    public int getExpandedTitleMarginBottom() {
        return this.m;
    }

    public int getExpandedTitleMarginEnd() {
        return this.l;
    }

    public int getExpandedTitleMarginStart() {
        return this.j;
    }

    public int getExpandedTitleMarginTop() {
        return this.k;
    }

    public Typeface getExpandedTitleTypeface() {
        Typeface typeface = this.o.z;
        return typeface != null ? typeface : Typeface.DEFAULT;
    }

    public int getHyphenationFrequency() {
        return this.o.q0;
    }

    public int getLineCount() {
        StaticLayout staticLayout = this.o.i0;
        if (staticLayout != null) {
            return staticLayout.getLineCount();
        }
        return 0;
    }

    public float getLineSpacingAdd() {
        return this.o.i0.getSpacingAdd();
    }

    public float getLineSpacingMultiplier() {
        return this.o.i0.getSpacingMultiplier();
    }

    public int getMaxLines() {
        return this.o.n0;
    }

    public int getScrimAlpha() {
        return this.u;
    }

    public long getScrimAnimationDuration() {
        return this.x;
    }

    public int getScrimVisibleHeightTrigger() {
        int i = this.y;
        if (i >= 0) {
            return i + this.D + this.F;
        }
        pt3 pt3Var = this.C;
        int iE = pt3Var != null ? pt3Var.e() : 0;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        int iC = om3.d.c(this);
        return iC > 0 ? Math.min((iC * 2) + iE, getHeight()) : getHeight() / 3;
    }

    public Drawable getStatusBarScrim() {
        return this.t;
    }

    public CharSequence getTitle() {
        if (this.q) {
            return this.o.G;
        }
        return null;
    }

    public int getTitleCollapseMode() {
        return this.B;
    }

    public TimeInterpolator getTitlePositionInterpolator() {
        return this.o.V;
    }

    public TextUtils.TruncateAt getTitleTextEllipsize() {
        return this.o.F;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (parent instanceof AppBarLayout) {
            AppBarLayout appBarLayout = (AppBarLayout) parent;
            if (this.B == 1) {
                appBarLayout.setLiftOnScroll(false);
            }
            WeakHashMap<View, in3> weakHashMap = om3.a;
            setFitsSystemWindows(om3.d.a(appBarLayout));
            if (this.z == null) {
                this.z = new b();
            }
            appBarLayout.addOnOffsetChangedListener((AppBarLayout.f) this.z);
            om3.h.c(this);
        }
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.o.h(configuration);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        ViewParent parent = getParent();
        b bVar = this.z;
        if (bVar != null && (parent instanceof AppBarLayout)) {
            ((AppBarLayout) parent).removeOnOffsetChangedListener((AppBarLayout.f) bVar);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        pt3 pt3Var = this.C;
        if (pt3Var != null) {
            int iE = pt3Var.e();
            int childCount = getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                View childAt = getChildAt(i5);
                WeakHashMap<View, in3> weakHashMap = om3.a;
                if (!om3.d.a(childAt) && childAt.getTop() < iE) {
                    childAt.offsetTopAndBottom(iE);
                }
            }
        }
        int childCount2 = getChildCount();
        for (int i6 = 0; i6 < childCount2; i6++) {
            cn3 cn3VarB = b(getChildAt(i6));
            View view = cn3VarB.a;
            cn3VarB.b = view.getTop();
            cn3VarB.c = view.getLeft();
        }
        f(false, i, i2, i3, i4);
        g();
        d();
        int childCount3 = getChildCount();
        for (int i7 = 0; i7 < childCount3; i7++) {
            b(getChildAt(i7)).a();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int measuredHeight;
        int measuredHeight2;
        a();
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i2);
        pt3 pt3Var = this.C;
        int iE = pt3Var != null ? pt3Var.e() : 0;
        if ((mode == 0 || this.E) && iE > 0) {
            this.D = iE;
            super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() + iE, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
        }
        if (this.G) {
            ou ouVar = this.o;
            if (ouVar.n0 > 1) {
                g();
                f(true, 0, 0, getMeasuredWidth(), getMeasuredHeight());
                int i3 = ouVar.p;
                if (i3 > 1) {
                    TextPaint textPaint = ouVar.U;
                    textPaint.setTextSize(ouVar.l);
                    textPaint.setTypeface(ouVar.z);
                    textPaint.setLetterSpacing(ouVar.g0);
                    this.F = (i3 - 1) * Math.round(textPaint.descent() + (-textPaint.ascent()));
                    super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() + this.F, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
                }
            }
        }
        ViewGroup viewGroup = this.g;
        if (viewGroup != null) {
            View view = this.h;
            if (view == null || view == this) {
                ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    measuredHeight = viewGroup.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
                } else {
                    measuredHeight = viewGroup.getMeasuredHeight();
                }
                setMinimumHeight(measuredHeight);
                return;
            }
            ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
            if (layoutParams2 instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) layoutParams2;
                measuredHeight2 = view.getMeasuredHeight() + marginLayoutParams2.topMargin + marginLayoutParams2.bottomMargin;
            } else {
                measuredHeight2 = view.getMeasuredHeight();
            }
            setMinimumHeight(measuredHeight2);
        }
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        Drawable drawable = this.s;
        if (drawable != null) {
            ViewGroup viewGroup = this.g;
            if ((this.B == 1) && viewGroup != null && this.q) {
                i2 = viewGroup.getBottom();
            }
            drawable.setBounds(0, 0, i, i2);
        }
    }

    public void setCollapsedTitleGravity(int i) {
        this.o.l(i);
    }

    public void setCollapsedTitleTextAppearance(int i) {
        this.o.j(i);
    }

    public void setCollapsedTitleTextColor(int i) {
        setCollapsedTitleTextColor(ColorStateList.valueOf(i));
    }

    public void setCollapsedTitleTypeface(Typeface typeface) {
        ou ouVar = this.o;
        if (ouVar.m(typeface)) {
            ouVar.i(false);
        }
    }

    public void setContentScrim(Drawable drawable) {
        Drawable drawable2 = this.s;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.s = drawableMutate;
            if (drawableMutate != null) {
                int width = getWidth();
                int height = getHeight();
                ViewGroup viewGroup = this.g;
                if ((this.B == 1) && viewGroup != null && this.q) {
                    height = viewGroup.getBottom();
                }
                drawableMutate.setBounds(0, 0, width, height);
                this.s.setCallback(this);
                this.s.setAlpha(this.u);
            }
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.d.j(this);
        }
    }

    public void setContentScrimColor(int i) {
        setContentScrim(new ColorDrawable(i));
    }

    public void setContentScrimResource(int i) {
        Context context = getContext();
        Object obj = bz.a;
        setContentScrim(context.getDrawable(i));
    }

    public void setExpandedTitleColor(int i) {
        setExpandedTitleTextColor(ColorStateList.valueOf(i));
    }

    public void setExpandedTitleGravity(int i) {
        ou ouVar = this.o;
        if (ouVar.j != i) {
            ouVar.j = i;
            ouVar.i(false);
        }
    }

    public void setExpandedTitleMarginBottom(int i) {
        this.m = i;
        requestLayout();
    }

    public void setExpandedTitleMarginEnd(int i) {
        this.l = i;
        requestLayout();
    }

    public void setExpandedTitleMarginStart(int i) {
        this.j = i;
        requestLayout();
    }

    public void setExpandedTitleMarginTop(int i) {
        this.k = i;
        requestLayout();
    }

    public void setExpandedTitleTextAppearance(int i) {
        this.o.n(i);
    }

    public void setExpandedTitleTextColor(ColorStateList colorStateList) {
        ou ouVar = this.o;
        if (ouVar.n != colorStateList) {
            ouVar.n = colorStateList;
            ouVar.i(false);
        }
    }

    public void setExpandedTitleTypeface(Typeface typeface) {
        ou ouVar = this.o;
        if (ouVar.o(typeface)) {
            ouVar.i(false);
        }
    }

    public void setExtraMultilineHeightEnabled(boolean z) {
        this.G = z;
    }

    public void setForceApplySystemWindowInsetTop(boolean z) {
        this.E = z;
    }

    public void setHyphenationFrequency(int i) {
        this.o.q0 = i;
    }

    public void setLineSpacingAdd(float f) {
        this.o.o0 = f;
    }

    public void setLineSpacingMultiplier(float f) {
        this.o.p0 = f;
    }

    public void setMaxLines(int i) {
        ou ouVar = this.o;
        if (i != ouVar.n0) {
            ouVar.n0 = i;
            Bitmap bitmap = ouVar.K;
            if (bitmap != null) {
                bitmap.recycle();
                ouVar.K = null;
            }
            ouVar.i(false);
        }
    }

    public void setRtlTextDirectionHeuristicsEnabled(boolean z) {
        this.o.f91J = z;
    }

    public void setScrimAlpha(int i) {
        ViewGroup viewGroup;
        if (i != this.u) {
            if (this.s != null && (viewGroup = this.g) != null) {
                WeakHashMap<View, in3> weakHashMap = om3.a;
                om3.d.j(viewGroup);
            }
            this.u = i;
            WeakHashMap<View, in3> weakHashMap2 = om3.a;
            om3.d.j(this);
        }
    }

    public void setScrimAnimationDuration(long j) {
        this.x = j;
    }

    public void setScrimVisibleHeightTrigger(int i) {
        if (this.y != i) {
            this.y = i;
            d();
        }
    }

    public void setScrimsShown(boolean z) {
        WeakHashMap<View, in3> weakHashMap = om3.a;
        boolean z2 = om3.g.c(this) && !isInEditMode();
        if (this.v != z) {
            if (z2) {
                int i = z ? 255 : 0;
                a();
                ValueAnimator valueAnimator = this.w;
                if (valueAnimator == null) {
                    ValueAnimator valueAnimator2 = new ValueAnimator();
                    this.w = valueAnimator2;
                    valueAnimator2.setInterpolator(i > this.u ? u5.c : u5.d);
                    this.w.addUpdateListener(new pu(this));
                } else if (valueAnimator.isRunning()) {
                    this.w.cancel();
                }
                this.w.setDuration(this.x);
                this.w.setIntValues(this.u, i);
                this.w.start();
            } else {
                setScrimAlpha(z ? 255 : 0);
            }
            this.v = z;
        }
    }

    public void setStatusBarScrim(Drawable drawable) {
        Drawable drawable2 = this.t;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.t = drawableMutate;
            if (drawableMutate != null) {
                if (drawableMutate.isStateful()) {
                    this.t.setState(getDrawableState());
                }
                Drawable drawable3 = this.t;
                WeakHashMap<View, in3> weakHashMap = om3.a;
                drawable3.setLayoutDirection(om3.e.c(this));
                this.t.setVisible(getVisibility() == 0, false);
                this.t.setCallback(this);
                this.t.setAlpha(this.u);
            }
            WeakHashMap<View, in3> weakHashMap2 = om3.a;
            om3.d.j(this);
        }
    }

    public void setStatusBarScrimColor(int i) {
        setStatusBarScrim(new ColorDrawable(i));
    }

    public void setStatusBarScrimResource(int i) {
        Context context = getContext();
        Object obj = bz.a;
        setStatusBarScrim(context.getDrawable(i));
    }

    public void setTitle(CharSequence charSequence) {
        ou ouVar = this.o;
        if (charSequence == null || !TextUtils.equals(ouVar.G, charSequence)) {
            ouVar.G = charSequence;
            ouVar.H = null;
            Bitmap bitmap = ouVar.K;
            if (bitmap != null) {
                bitmap.recycle();
                ouVar.K = null;
            }
            ouVar.i(false);
        }
        setContentDescription(getTitle());
    }

    public void setTitleCollapseMode(int i) {
        this.B = i;
        boolean z = i == 1;
        this.o.c = z;
        ViewParent parent = getParent();
        if (parent instanceof AppBarLayout) {
            AppBarLayout appBarLayout = (AppBarLayout) parent;
            if (this.B == 1) {
                appBarLayout.setLiftOnScroll(false);
            }
        }
        if (z && this.s == null) {
            float dimension = getResources().getDimension(R$dimen.design_appbar_elevation);
            ElevationOverlayProvider elevationOverlayProvider = this.p;
            setContentScrimColor(elevationOverlayProvider.b(dimension, elevationOverlayProvider.d));
        }
    }

    public void setTitleEllipsize(TextUtils.TruncateAt truncateAt) {
        ou ouVar = this.o;
        ouVar.F = truncateAt;
        ouVar.i(false);
    }

    public void setTitleEnabled(boolean z) {
        if (z != this.q) {
            this.q = z;
            setContentDescription(getTitle());
            c();
            requestLayout();
        }
    }

    public void setTitlePositionInterpolator(TimeInterpolator timeInterpolator) {
        ou ouVar = this.o;
        ouVar.V = timeInterpolator;
        ouVar.i(false);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        Drawable drawable = this.t;
        if (drawable != null && drawable.isVisible() != z) {
            this.t.setVisible(z, false);
        }
        Drawable drawable2 = this.s;
        if (drawable2 == null || drawable2.isVisible() == z) {
            return;
        }
        this.s.setVisible(z, false);
    }

    @Override // android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.s || drawable == this.t;
    }

    public CollapsingToolbarLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.collapsingToolbarLayoutStyle);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final FrameLayout.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public void setCollapsedTitleTextColor(ColorStateList colorStateList) {
        this.o.k(colorStateList);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public CollapsingToolbarLayout(Context context, AttributeSet attributeSet, int i) {
        int i2;
        ColorStateList colorStateListA;
        TextUtils.TruncateAt truncateAt;
        int i3 = H;
        super(uc1.a(context, attributeSet, i, i3), attributeSet, i);
        this.e = true;
        this.n = new Rect();
        this.y = -1;
        this.D = 0;
        this.F = 0;
        Context context2 = getContext();
        ou ouVar = new ou(this);
        this.o = ouVar;
        ouVar.W = u5.e;
        ouVar.i(false);
        ouVar.f91J = false;
        this.p = new ElevationOverlayProvider(context2);
        TypedArray typedArrayD = r83.d(context2, attributeSet, R$styleable.CollapsingToolbarLayout, i, i3, new int[0]);
        int i4 = typedArrayD.getInt(R$styleable.CollapsingToolbarLayout_expandedTitleGravity, 8388691);
        if (ouVar.j != i4) {
            ouVar.j = i4;
            ouVar.i(false);
        }
        ouVar.l(typedArrayD.getInt(R$styleable.CollapsingToolbarLayout_collapsedTitleGravity, 8388627));
        int dimensionPixelSize = typedArrayD.getDimensionPixelSize(R$styleable.CollapsingToolbarLayout_expandedTitleMargin, 0);
        this.m = dimensionPixelSize;
        this.l = dimensionPixelSize;
        this.k = dimensionPixelSize;
        this.j = dimensionPixelSize;
        int i5 = R$styleable.CollapsingToolbarLayout_expandedTitleMarginStart;
        if (typedArrayD.hasValue(i5)) {
            this.j = typedArrayD.getDimensionPixelSize(i5, 0);
        }
        int i6 = R$styleable.CollapsingToolbarLayout_expandedTitleMarginEnd;
        if (typedArrayD.hasValue(i6)) {
            this.l = typedArrayD.getDimensionPixelSize(i6, 0);
        }
        int i7 = R$styleable.CollapsingToolbarLayout_expandedTitleMarginTop;
        if (typedArrayD.hasValue(i7)) {
            this.k = typedArrayD.getDimensionPixelSize(i7, 0);
        }
        int i8 = R$styleable.CollapsingToolbarLayout_expandedTitleMarginBottom;
        if (typedArrayD.hasValue(i8)) {
            this.m = typedArrayD.getDimensionPixelSize(i8, 0);
        }
        this.q = typedArrayD.getBoolean(R$styleable.CollapsingToolbarLayout_titleEnabled, true);
        setTitle(typedArrayD.getText(R$styleable.CollapsingToolbarLayout_title));
        ouVar.n(R$style.TextAppearance_Design_CollapsingToolbar_Expanded);
        ouVar.j(R.style.TextAppearance_AppCompat_Widget_ActionBar_Title);
        int i9 = R$styleable.CollapsingToolbarLayout_expandedTitleTextAppearance;
        if (typedArrayD.hasValue(i9)) {
            ouVar.n(typedArrayD.getResourceId(i9, 0));
        }
        int i10 = R$styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance;
        if (typedArrayD.hasValue(i10)) {
            ouVar.j(typedArrayD.getResourceId(i10, 0));
        }
        int i11 = R$styleable.CollapsingToolbarLayout_titleTextEllipsize;
        if (typedArrayD.hasValue(i11)) {
            int i12 = typedArrayD.getInt(i11, -1);
            if (i12 == 0) {
                truncateAt = TextUtils.TruncateAt.START;
            } else if (i12 == 1) {
                truncateAt = TextUtils.TruncateAt.MIDDLE;
            } else if (i12 != 3) {
                truncateAt = TextUtils.TruncateAt.END;
            } else {
                truncateAt = TextUtils.TruncateAt.MARQUEE;
            }
            setTitleEllipsize(truncateAt);
        }
        int i13 = R$styleable.CollapsingToolbarLayout_expandedTitleTextColor;
        if (typedArrayD.hasValue(i13) && ouVar.n != (colorStateListA = rc1.a(context2, typedArrayD, i13))) {
            ouVar.n = colorStateListA;
            ouVar.i(false);
        }
        int i14 = R$styleable.CollapsingToolbarLayout_collapsedTitleTextColor;
        if (typedArrayD.hasValue(i14)) {
            ouVar.k(rc1.a(context2, typedArrayD, i14));
        }
        this.y = typedArrayD.getDimensionPixelSize(R$styleable.CollapsingToolbarLayout_scrimVisibleHeightTrigger, -1);
        int i15 = R$styleable.CollapsingToolbarLayout_maxLines;
        if (typedArrayD.hasValue(i15) && (i2 = typedArrayD.getInt(i15, 1)) != ouVar.n0) {
            ouVar.n0 = i2;
            Bitmap bitmap = ouVar.K;
            if (bitmap != null) {
                bitmap.recycle();
                ouVar.K = null;
            }
            ouVar.i(false);
        }
        int i16 = R$styleable.CollapsingToolbarLayout_titlePositionInterpolator;
        if (typedArrayD.hasValue(i16)) {
            ouVar.V = AnimationUtils.loadInterpolator(context2, typedArrayD.getResourceId(i16, 0));
            ouVar.i(false);
        }
        this.x = typedArrayD.getInt(R$styleable.CollapsingToolbarLayout_scrimAnimationDuration, 600);
        setContentScrim(typedArrayD.getDrawable(R$styleable.CollapsingToolbarLayout_contentScrim));
        setStatusBarScrim(typedArrayD.getDrawable(R$styleable.CollapsingToolbarLayout_statusBarScrim));
        setTitleCollapseMode(typedArrayD.getInt(R$styleable.CollapsingToolbarLayout_titleCollapseMode, 0));
        this.f = typedArrayD.getResourceId(R$styleable.CollapsingToolbarLayout_toolbarId, -1);
        this.E = typedArrayD.getBoolean(R$styleable.CollapsingToolbarLayout_forceApplySystemWindowInsetTop, false);
        this.G = typedArrayD.getBoolean(R$styleable.CollapsingToolbarLayout_extraMultilineHeightEnabled, false);
        typedArrayD.recycle();
        setWillNotDraw(false);
        a aVar = new a();
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.i.m(this, aVar);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public static class LayoutParams extends FrameLayout.LayoutParams {
        public final int a;
        public float b;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.a = 0;
            this.b = 0.5f;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CollapsingToolbarLayout_Layout);
            this.a = typedArrayObtainStyledAttributes.getInt(R$styleable.CollapsingToolbarLayout_Layout_layout_collapseMode, 0);
            this.b = typedArrayObtainStyledAttributes.getFloat(R$styleable.CollapsingToolbarLayout_Layout_layout_collapseParallaxMultiplier, 0.5f);
            typedArrayObtainStyledAttributes.recycle();
        }

        public LayoutParams() {
            super(-1, -1);
            this.a = 0;
            this.b = 0.5f;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.a = 0;
            this.b = 0.5f;
        }
    }
}
