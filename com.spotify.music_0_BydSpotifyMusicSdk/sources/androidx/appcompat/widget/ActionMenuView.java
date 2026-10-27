package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import androidx.appcompat.view.menu.ActionMenuItemView;
import p.c4;
import p.ev2;
import p.f4;
import p.g4;
import p.h4;
import p.hk0;
import p.i4;
import p.j4;
import p.ja1;
import p.ka1;
import p.nj1;
import p.qi1;
import p.qj1;
import p.ri1;
import p.si1;
import p.u11;
import p.xi1;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuView extends ka1 implements ri1, qj1 {
    public boolean A;
    public g4 B;
    public nj1 C;
    public qi1 D;
    public boolean E;
    public int F;
    public final int G;
    public final int H;
    public j4 I;
    public si1 x;
    public Context y;
    public int z;

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        setBaselineAligned(false);
        float f = context.getResources().getDisplayMetrics().density;
        this.G = (int) (56.0f * f);
        this.H = (int) (f * 4.0f);
        this.y = context;
        this.z = 0;
    }

    public static i4 l() {
        i4 i4Var = new i4(-2, -2);
        i4Var.a = false;
        ((LinearLayout.LayoutParams) i4Var).gravity = 16;
        return i4Var;
    }

    public static i4 m(ViewGroup.LayoutParams layoutParams) {
        i4 i4Var;
        if (layoutParams == null) {
            return l();
        }
        if (layoutParams instanceof i4) {
            i4 i4Var2 = (i4) layoutParams;
            i4Var = new i4(i4Var2);
            i4Var.a = i4Var2.a;
        } else {
            i4Var = new i4(layoutParams);
        }
        if (((LinearLayout.LayoutParams) i4Var).gravity <= 0) {
            ((LinearLayout.LayoutParams) i4Var).gravity = 16;
        }
        return i4Var;
    }

    @Override // p.qj1
    public final void b(si1 si1Var) {
        this.x = si1Var;
    }

    @Override // p.ka1, android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof i4;
    }

    @Override // p.ri1
    public final boolean d(xi1 xi1Var) {
        return this.x.q(xi1Var, null, 0);
    }

    @Override // android.view.View
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    @Override // p.ka1, android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return l();
    }

    @Override // p.ka1, android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return m(layoutParams);
    }

    public Menu getMenu() {
        if (this.x == null) {
            Context context = getContext();
            si1 si1Var = new si1(context);
            this.x = si1Var;
            si1Var.e = new hk0(5, this);
            g4 g4Var = new g4(context);
            this.B = g4Var;
            g4Var.t = true;
            g4Var.u = true;
            nj1 u11Var = this.C;
            if (u11Var == null) {
                u11Var = new u11();
            }
            g4Var.m = u11Var;
            this.x.b(g4Var, this.y);
            g4 g4Var2 = this.B;
            g4Var2.f122p = this;
            this.x = g4Var2.k;
        }
        return this.x;
    }

    public Drawable getOverflowIcon() {
        getMenu();
        g4 g4Var = this.B;
        f4 f4Var = g4Var.q;
        if (f4Var != null) {
            return f4Var.getDrawable();
        }
        if (g4Var.s) {
            return g4Var.r;
        }
        return null;
    }

    public int getPopupTheme() {
        return this.z;
    }

    public int getWindowAnimations() {
        return 0;
    }

    @Override // p.ka1
    /* JADX INFO: renamed from: h */
    public final /* bridge */ /* synthetic */ ja1 generateDefaultLayoutParams() {
        return l();
    }

    @Override // p.ka1
    /* JADX INFO: renamed from: i */
    public final ja1 generateLayoutParams(AttributeSet attributeSet) {
        return new i4(getContext(), attributeSet);
    }

    @Override // p.ka1
    /* JADX INFO: renamed from: j */
    public final /* bridge */ /* synthetic */ ja1 generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return m(layoutParams);
    }

    public final boolean n(int i) {
        boolean zA = false;
        if (i == 0) {
            return false;
        }
        KeyEvent.Callback childAt = getChildAt(i - 1);
        KeyEvent.Callback childAt2 = getChildAt(i);
        if (i < getChildCount() && (childAt instanceof h4)) {
            zA = ((h4) childAt).a();
        }
        return (i <= 0 || !(childAt2 instanceof h4)) ? zA : zA | ((h4) childAt2).b();
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        g4 g4Var = this.B;
        if (g4Var != null) {
            g4Var.d();
            if (this.B.e()) {
                this.B.c();
                this.B.l();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        g4 g4Var = this.B;
        if (g4Var != null) {
            g4Var.c();
            c4 c4Var = g4Var.B;
            if (c4Var == null || !c4Var.b()) {
                return;
            }
            c4Var.j.dismiss();
        }
    }

    @Override // p.ka1, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int width;
        int paddingLeft;
        if (!this.E) {
            super.onLayout(z, i, i2, i3, i4);
            return;
        }
        int childCount = getChildCount();
        int i5 = (i4 - i2) / 2;
        int dividerWidth = getDividerWidth();
        int i6 = i3 - i;
        int paddingRight = (i6 - getPaddingRight()) - getPaddingLeft();
        boolean zA = ev2.a(this);
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                i4 i4Var = (i4) childAt.getLayoutParams();
                if (i4Var.a) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (n(i9)) {
                        measuredWidth += dividerWidth;
                    }
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (zA) {
                        paddingLeft = getPaddingLeft() + ((LinearLayout.LayoutParams) i4Var).leftMargin;
                        width = paddingLeft + measuredWidth;
                    } else {
                        width = (getWidth() - getPaddingRight()) - ((LinearLayout.LayoutParams) i4Var).rightMargin;
                        paddingLeft = width - measuredWidth;
                    }
                    int i10 = i5 - (measuredHeight / 2);
                    childAt.layout(paddingLeft, i10, width, measuredHeight + i10);
                    paddingRight -= measuredWidth;
                    i7 = 1;
                } else {
                    paddingRight -= (childAt.getMeasuredWidth() + ((LinearLayout.LayoutParams) i4Var).leftMargin) + ((LinearLayout.LayoutParams) i4Var).rightMargin;
                    n(i9);
                    i8++;
                }
            }
        }
        if (childCount == 1 && i7 == 0) {
            View childAt2 = getChildAt(0);
            int measuredWidth2 = childAt2.getMeasuredWidth();
            int measuredHeight2 = childAt2.getMeasuredHeight();
            int i11 = (i6 / 2) - (measuredWidth2 / 2);
            int i12 = i5 - (measuredHeight2 / 2);
            childAt2.layout(i11, i12, measuredWidth2 + i11, measuredHeight2 + i12);
            return;
        }
        int i13 = i8 - (i7 ^ 1);
        int iMax = Math.max(0, i13 > 0 ? paddingRight / i13 : 0);
        if (zA) {
            int width2 = getWidth() - getPaddingRight();
            for (int i14 = 0; i14 < childCount; i14++) {
                View childAt3 = getChildAt(i14);
                i4 i4Var2 = (i4) childAt3.getLayoutParams();
                if (childAt3.getVisibility() != 8 && !i4Var2.a) {
                    int i15 = width2 - ((LinearLayout.LayoutParams) i4Var2).rightMargin;
                    int measuredWidth3 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i16 = i5 - (measuredHeight3 / 2);
                    childAt3.layout(i15 - measuredWidth3, i16, i15, measuredHeight3 + i16);
                    width2 = i15 - ((measuredWidth3 + ((LinearLayout.LayoutParams) i4Var2).leftMargin) + iMax);
                }
            }
            return;
        }
        int paddingLeft2 = getPaddingLeft();
        for (int i17 = 0; i17 < childCount; i17++) {
            View childAt4 = getChildAt(i17);
            i4 i4Var3 = (i4) childAt4.getLayoutParams();
            if (childAt4.getVisibility() != 8 && !i4Var3.a) {
                int i18 = paddingLeft2 + ((LinearLayout.LayoutParams) i4Var3).leftMargin;
                int measuredWidth4 = childAt4.getMeasuredWidth();
                int measuredHeight4 = childAt4.getMeasuredHeight();
                int i19 = i5 - (measuredHeight4 / 2);
                childAt4.layout(i18, i19, i18 + measuredWidth4, measuredHeight4 + i19);
                paddingLeft2 = measuredWidth4 + ((LinearLayout.LayoutParams) i4Var3).rightMargin + iMax + i18;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v21, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r1v23 */
    /* JADX WARN: Type inference failed for: r1v26 */
    @Override // p.ka1, android.view.View
    public final void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        boolean z;
        int i6;
        int i7;
        int i8;
        int i9;
        ?? r1;
        int i10;
        int i11;
        int i12;
        si1 si1Var;
        boolean z2 = this.E;
        boolean z3 = View.MeasureSpec.getMode(i) == 1073741824;
        this.E = z3;
        if (z2 != z3) {
            this.F = 0;
        }
        int size = View.MeasureSpec.getSize(i);
        if (this.E && (si1Var = this.x) != null && size != this.F) {
            this.F = size;
            si1Var.p(true);
        }
        int childCount = getChildCount();
        if (!this.E || childCount <= 0) {
            for (int i13 = 0; i13 < childCount; i13++) {
                i4 i4Var = (i4) getChildAt(i13).getLayoutParams();
                ((LinearLayout.LayoutParams) i4Var).rightMargin = 0;
                ((LinearLayout.LayoutParams) i4Var).leftMargin = 0;
            }
            super.onMeasure(i, i2);
            return;
        }
        int mode = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i);
        int size3 = View.MeasureSpec.getSize(i2);
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, paddingBottom, -2);
        int i14 = size2 - paddingRight;
        int i15 = this.G;
        int i16 = i14 / i15;
        int i17 = i14 % i15;
        if (i16 == 0) {
            setMeasuredDimension(i14, 0);
            return;
        }
        int i18 = (i17 / i16) + i15;
        int childCount2 = getChildCount();
        int i19 = 0;
        int iMax = 0;
        int i20 = 0;
        boolean z4 = false;
        int i21 = 0;
        int iMax2 = 0;
        long j = 0;
        while (true) {
            i3 = this.H;
            if (i20 >= childCount2) {
                break;
            }
            View childAt = getChildAt(i20);
            int i22 = size3;
            if (childAt.getVisibility() == 8) {
                i10 = i14;
                i11 = paddingBottom;
            } else {
                boolean z5 = childAt instanceof ActionMenuItemView;
                int i23 = i19 + 1;
                if (z5) {
                    childAt.setPadding(i3, 0, i3, 0);
                }
                i4 i4Var2 = (i4) childAt.getLayoutParams();
                i4Var2.f = false;
                i4Var2.c = 0;
                i4Var2.b = 0;
                i4Var2.d = false;
                ((LinearLayout.LayoutParams) i4Var2).leftMargin = 0;
                ((LinearLayout.LayoutParams) i4Var2).rightMargin = 0;
                i4Var2.e = z5 && (TextUtils.isEmpty(((ActionMenuItemView) childAt).getText()) ^ true);
                int i24 = i4Var2.a ? 1 : i16;
                i4 i4Var3 = (i4) childAt.getLayoutParams();
                i10 = i14;
                i11 = paddingBottom;
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(childMeasureSpec) - paddingBottom, View.MeasureSpec.getMode(childMeasureSpec));
                ActionMenuItemView actionMenuItemView = z5 ? (ActionMenuItemView) childAt : null;
                boolean z6 = actionMenuItemView != null && (TextUtils.isEmpty(actionMenuItemView.getText()) ^ true);
                if (i24 <= 0 || (z6 && i24 < 2)) {
                    i12 = 0;
                } else {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(i24 * i18, Integer.MIN_VALUE), iMakeMeasureSpec);
                    int measuredWidth = childAt.getMeasuredWidth();
                    i12 = measuredWidth / i18;
                    if (measuredWidth % i18 != 0) {
                        i12++;
                    }
                    if (z6 && i12 < 2) {
                        i12 = 2;
                    }
                }
                i4Var3.d = !i4Var3.a && z6;
                i4Var3.b = i12;
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i12 * i18, 1073741824), iMakeMeasureSpec);
                iMax = Math.max(iMax, i12);
                if (i4Var2.d) {
                    i21++;
                }
                if (i4Var2.a) {
                    z4 = true;
                }
                i16 -= i12;
                iMax2 = Math.max(iMax2, childAt.getMeasuredHeight());
                if (i12 == 1) {
                    j |= (long) (1 << i20);
                }
                i19 = i23;
            }
            i20++;
            size3 = i22;
            paddingBottom = i11;
            i14 = i10;
        }
        int i25 = i14;
        int i26 = size3;
        int i27 = iMax2;
        boolean z7 = z4 && i19 == 2;
        boolean z8 = false;
        while (i21 > 0 && i16 > 0) {
            int i28 = Integer.MAX_VALUE;
            int i29 = 0;
            int i30 = 0;
            long j2 = 0;
            while (i30 < childCount2) {
                int i31 = i27;
                i4 i4Var4 = (i4) getChildAt(i30).getLayoutParams();
                boolean z9 = z8;
                if (i4Var4.d) {
                    int i32 = i4Var4.b;
                    if (i32 < i28) {
                        j2 = 1 << i30;
                        i28 = i32;
                        i29 = 1;
                    } else if (i32 == i28) {
                        i29++;
                        j2 |= 1 << i30;
                    }
                }
                i30++;
                z8 = z9;
                i27 = i31;
            }
            i5 = i27;
            z = z8;
            j |= j2;
            if (i29 > i16) {
                i4 = mode;
                break;
            }
            int i33 = i28 + 1;
            int i34 = 0;
            while (i34 < childCount2) {
                View childAt2 = getChildAt(i34);
                i4 i4Var5 = (i4) childAt2.getLayoutParams();
                int i35 = mode;
                int i36 = childMeasureSpec;
                int i37 = childCount2;
                long j3 = 1 << i34;
                if ((j2 & j3) != 0) {
                    if (z7 && i4Var5.e) {
                        r1 = 1;
                        r1 = 1;
                        if (i16 == 1) {
                            childAt2.setPadding(i3 + i18, 0, i3, 0);
                        }
                    } else {
                        r1 = 1;
                    }
                    i4Var5.b += r1;
                    i4Var5.f = r1;
                    i16--;
                } else if (i4Var5.b == i33) {
                    j |= j3;
                }
                i34++;
                childMeasureSpec = i36;
                mode = i35;
                childCount2 = i37;
            }
            i27 = i5;
            z8 = true;
        }
        i4 = mode;
        i5 = i27;
        z = z8;
        int i38 = childMeasureSpec;
        int i39 = childCount2;
        boolean z10 = !z4 && i19 == 1;
        if (i16 <= 0 || j == 0 || (i16 >= i19 - 1 && !z10 && iMax <= 1)) {
            i6 = i39;
        } else {
            float fBitCount = Long.bitCount(j);
            if (!z10) {
                if ((j & 1) != 0 && !((i4) getChildAt(0).getLayoutParams()).e) {
                    fBitCount -= 0.5f;
                }
                int i40 = i39 - 1;
                if ((j & ((long) (1 << i40))) != 0 && !((i4) getChildAt(i40).getLayoutParams()).e) {
                    fBitCount -= 0.5f;
                }
            }
            int i41 = fBitCount > 0.0f ? (int) ((i16 * i18) / fBitCount) : 0;
            i6 = i39;
            for (int i42 = 0; i42 < i6; i42++) {
                if ((j & ((long) (1 << i42))) != 0) {
                    View childAt3 = getChildAt(i42);
                    i4 i4Var6 = (i4) childAt3.getLayoutParams();
                    if (childAt3 instanceof ActionMenuItemView) {
                        i4Var6.c = i41;
                        i4Var6.f = true;
                        if (i42 == 0 && !i4Var6.e) {
                            ((LinearLayout.LayoutParams) i4Var6).leftMargin = (-i41) / 2;
                        }
                        z = true;
                    } else if (i4Var6.a) {
                        i4Var6.c = i41;
                        i4Var6.f = true;
                        ((LinearLayout.LayoutParams) i4Var6).rightMargin = (-i41) / 2;
                        z = true;
                    } else {
                        if (i42 != 0) {
                            ((LinearLayout.LayoutParams) i4Var6).leftMargin = i41 / 2;
                        }
                        if (i42 != i6 - 1) {
                            ((LinearLayout.LayoutParams) i4Var6).rightMargin = i41 / 2;
                        }
                    }
                }
            }
        }
        if (z) {
            int i43 = 0;
            while (i43 < i6) {
                View childAt4 = getChildAt(i43);
                i4 i4Var7 = (i4) childAt4.getLayoutParams();
                if (i4Var7.f) {
                    i9 = i38;
                    childAt4.measure(View.MeasureSpec.makeMeasureSpec((i4Var7.b * i18) + i4Var7.c, 1073741824), i9);
                } else {
                    i9 = i38;
                }
                i43++;
                i38 = i9;
            }
        }
        if (i4 != 1073741824) {
            i8 = i25;
            i7 = i5;
        } else {
            i7 = i26;
            i8 = i25;
        }
        setMeasuredDimension(i8, i7);
    }

    public void setExpandedActionViewsExclusive(boolean z) {
        this.B.y = z;
    }

    public void setOnMenuItemClickListener(j4 j4Var) {
        this.I = j4Var;
    }

    public void setOverflowIcon(Drawable drawable) {
        getMenu();
        g4 g4Var = this.B;
        f4 f4Var = g4Var.q;
        if (f4Var != null) {
            f4Var.setImageDrawable(drawable);
        } else {
            g4Var.s = true;
            g4Var.r = drawable;
        }
    }

    public void setOverflowReserved(boolean z) {
        this.A = z;
    }

    public void setPopupTheme(int i) {
        if (this.z != i) {
            this.z = i;
            if (i == 0) {
                this.y = getContext();
            } else {
                this.y = new ContextThemeWrapper(getContext(), i);
            }
        }
    }

    public void setPresenter(g4 g4Var) {
        this.B = g4Var;
        g4Var.f122p = this;
        this.x = g4Var.k;
    }

    @Override // p.ka1, android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new i4(getContext(), attributeSet);
    }
}
