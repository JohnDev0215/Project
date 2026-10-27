package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.appcompat.view.menu.ActionMenuItemView;
import defpackage.AbstractC1358q0;
import defpackage.C1303p0;
import defpackage.T;
import defpackage.U;
import defpackage.X0;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ActionMenuView extends AbstractC1358q0 {
    public boolean H;
    public int I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public int f14J;
    public int K;

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.s = false;
        float f = context.getResources().getDisplayMetrics().density;
        this.f14J = (int) (56.0f * f);
        this.K = (int) (f * 4.0f);
    }

    @Override // defpackage.AbstractC1358q0, android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams != null && (layoutParams instanceof U);
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    @Override // defpackage.AbstractC1358q0
    /* JADX INFO: renamed from: f */
    public C1303p0 generateLayoutParams(AttributeSet attributeSet) {
        return new U(getContext(), attributeSet);
    }

    @Override // defpackage.AbstractC1358q0, android.view.ViewGroup
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public U generateDefaultLayoutParams() {
        U u = new U(-2, -2);
        u.b = 16;
        return u;
    }

    @Override // defpackage.AbstractC1358q0, android.view.ViewGroup
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public U generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams == null) {
            return generateDefaultLayoutParams();
        }
        U u = layoutParams instanceof U ? new U((U) layoutParams) : new U(layoutParams);
        if (u.b <= 0) {
            u.b = 16;
        }
        return u;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // defpackage.AbstractC1358q0, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int width;
        int paddingLeft;
        if (!this.H) {
            super.onLayout(z, i, i2, i3, i4);
            return;
        }
        int childCount = getChildCount();
        int i5 = (i4 - i2) / 2;
        int i6 = this.D;
        int i7 = i3 - i;
        int paddingRight = (i7 - getPaddingRight()) - getPaddingLeft();
        boolean zA = X0.a(this);
        int i8 = 0;
        int i9 = 0;
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = getChildAt(i10);
            if (childAt.getVisibility() != 8) {
                U u = (U) childAt.getLayoutParams();
                if (u.c) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (p(i10)) {
                        measuredWidth += i6;
                    }
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (zA) {
                        paddingLeft = getPaddingLeft() + ((ViewGroup.MarginLayoutParams) u).leftMargin;
                        width = paddingLeft + measuredWidth;
                    } else {
                        width = (getWidth() - getPaddingRight()) - ((ViewGroup.MarginLayoutParams) u).rightMargin;
                        paddingLeft = width - measuredWidth;
                    }
                    int i11 = i5 - (measuredHeight / 2);
                    childAt.layout(paddingLeft, i11, width, measuredHeight + i11);
                    paddingRight -= measuredWidth;
                    i8 = 1;
                } else {
                    paddingRight -= (childAt.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) u).leftMargin) + ((ViewGroup.MarginLayoutParams) u).rightMargin;
                    p(i10);
                    i9++;
                }
            }
        }
        if (childCount == 1 && i8 == 0) {
            View childAt2 = getChildAt(0);
            int measuredWidth2 = childAt2.getMeasuredWidth();
            int measuredHeight2 = childAt2.getMeasuredHeight();
            int i12 = (i7 / 2) - (measuredWidth2 / 2);
            int i13 = i5 - (measuredHeight2 / 2);
            childAt2.layout(i12, i13, measuredWidth2 + i12, measuredHeight2 + i13);
            return;
        }
        int i14 = i9 - (i8 ^ 1);
        int iMax = Math.max(0, i14 > 0 ? paddingRight / i14 : 0);
        if (zA) {
            int width2 = getWidth() - getPaddingRight();
            for (int i15 = 0; i15 < childCount; i15++) {
                View childAt3 = getChildAt(i15);
                U u2 = (U) childAt3.getLayoutParams();
                if (childAt3.getVisibility() != 8 && !u2.c) {
                    int i16 = width2 - ((ViewGroup.MarginLayoutParams) u2).rightMargin;
                    int measuredWidth3 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i17 = i5 - (measuredHeight3 / 2);
                    childAt3.layout(i16 - measuredWidth3, i17, i16, measuredHeight3 + i17);
                    width2 = i16 - ((measuredWidth3 + ((ViewGroup.MarginLayoutParams) u2).leftMargin) + iMax);
                }
            }
            return;
        }
        int paddingLeft2 = getPaddingLeft();
        for (int i18 = 0; i18 < childCount; i18++) {
            View childAt4 = getChildAt(i18);
            U u3 = (U) childAt4.getLayoutParams();
            if (childAt4.getVisibility() != 8 && !u3.c) {
                int i19 = paddingLeft2 + ((ViewGroup.MarginLayoutParams) u3).leftMargin;
                int measuredWidth4 = childAt4.getMeasuredWidth();
                int measuredHeight4 = childAt4.getMeasuredHeight();
                int i20 = i5 - (measuredHeight4 / 2);
                childAt4.layout(i19, i20, i19 + measuredWidth4, measuredHeight4 + i20);
                paddingLeft2 = measuredWidth4 + ((ViewGroup.MarginLayoutParams) u3).rightMargin + iMax + i19;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r12v11 */
    /* JADX WARN: Type inference failed for: r12v12, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r12v31 */
    @Override // defpackage.AbstractC1358q0, android.view.View
    public void onMeasure(int i, int i2) {
        boolean z;
        boolean z2;
        int i3;
        int i4;
        int i5;
        ?? r12;
        int i6;
        int i7;
        int i8;
        int i9;
        boolean z3 = this.H;
        boolean z4 = View.MeasureSpec.getMode(i) == 1073741824;
        this.H = z4;
        if (z3 != z4) {
            this.I = 0;
        }
        View.MeasureSpec.getSize(i);
        boolean z5 = this.H;
        int childCount = getChildCount();
        if (!this.H || childCount <= 0) {
            for (int i10 = 0; i10 < childCount; i10++) {
                U u = (U) getChildAt(i10).getLayoutParams();
                ((ViewGroup.MarginLayoutParams) u).rightMargin = 0;
                ((ViewGroup.MarginLayoutParams) u).leftMargin = 0;
            }
            super.onMeasure(i, i2);
            return;
        }
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, paddingBottom, -2);
        int i11 = size - paddingRight;
        int i12 = this.f14J;
        int i13 = i11 / i12;
        int i14 = i11 % i12;
        if (i13 == 0) {
            setMeasuredDimension(i11, 0);
            return;
        }
        int i15 = (i14 / i13) + i12;
        int childCount2 = getChildCount();
        int iMax = 0;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        boolean z6 = false;
        long j = 0;
        int iMax2 = 0;
        while (i18 < childCount2) {
            View childAt = getChildAt(i18);
            int i19 = size2;
            if (childAt.getVisibility() == 8) {
                i7 = mode;
                i6 = i11;
                i8 = paddingBottom;
            } else {
                boolean z7 = childAt instanceof ActionMenuItemView;
                int i20 = i16 + 1;
                if (z7) {
                    int i21 = this.K;
                    i5 = i20;
                    r12 = 0;
                    childAt.setPadding(i21, 0, i21, 0);
                } else {
                    i5 = i20;
                    r12 = 0;
                }
                U u2 = (U) childAt.getLayoutParams();
                u2.h = r12;
                u2.e = r12;
                u2.d = r12;
                u2.f = r12;
                ((ViewGroup.MarginLayoutParams) u2).leftMargin = r12;
                ((ViewGroup.MarginLayoutParams) u2).rightMargin = r12;
                u2.g = z7 && ((ActionMenuItemView) childAt).b();
                int i22 = u2.c ? 1 : i13;
                i6 = i11;
                U u3 = (U) childAt.getLayoutParams();
                i7 = mode;
                i8 = paddingBottom;
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(childMeasureSpec) - paddingBottom, View.MeasureSpec.getMode(childMeasureSpec));
                ActionMenuItemView actionMenuItemView = z7 ? (ActionMenuItemView) childAt : null;
                boolean z8 = actionMenuItemView != null && actionMenuItemView.b();
                if (i22 <= 0 || (z8 && i22 < 2)) {
                    i9 = 0;
                } else {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(i22 * i15, Integer.MIN_VALUE), iMakeMeasureSpec);
                    int measuredWidth = childAt.getMeasuredWidth();
                    i9 = measuredWidth / i15;
                    if (measuredWidth % i15 != 0) {
                        i9++;
                    }
                    if (z8 && i9 < 2) {
                        i9 = 2;
                    }
                }
                u3.f = !u3.c && z8;
                u3.d = i9;
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i15 * i9, 1073741824), iMakeMeasureSpec);
                iMax = Math.max(iMax, i9);
                if (u2.f) {
                    i17++;
                }
                if (u2.c) {
                    z6 = true;
                }
                i13 -= i9;
                iMax2 = Math.max(iMax2, childAt.getMeasuredHeight());
                if (i9 == 1) {
                    j |= (long) (1 << i18);
                }
                i16 = i5;
            }
            i18++;
            size2 = i19;
            paddingBottom = i8;
            i11 = i6;
            mode = i7;
        }
        int i23 = mode;
        int i24 = i11;
        int i25 = size2;
        int i26 = iMax2;
        boolean z9 = z6 && i16 == 2;
        boolean z10 = false;
        while (i17 > 0 && i13 > 0) {
            int i27 = Integer.MAX_VALUE;
            int i28 = 0;
            int i29 = 0;
            long j2 = 0;
            while (i29 < childCount2) {
                U u4 = (U) getChildAt(i29).getLayoutParams();
                boolean z11 = z10;
                if (u4.f) {
                    int i30 = u4.d;
                    if (i30 < i27) {
                        j2 = 1 << i29;
                        i27 = i30;
                        i28 = 1;
                    } else if (i30 == i27) {
                        j2 |= 1 << i29;
                        i28++;
                    }
                }
                i29++;
                z10 = z11;
            }
            z = z10;
            j |= j2;
            if (i28 > i13) {
                break;
            }
            int i31 = i27 + 1;
            int i32 = 0;
            while (i32 < childCount2) {
                View childAt2 = getChildAt(i32);
                U u5 = (U) childAt2.getLayoutParams();
                int i33 = i26;
                int i34 = i17;
                long j3 = 1 << i32;
                if ((j2 & j3) != 0) {
                    if (z9 && u5.g && i13 == 1) {
                        int i35 = this.K;
                        childAt2.setPadding(i35 + i15, 0, i35, 0);
                    }
                    u5.d++;
                    u5.h = true;
                    i13--;
                } else if (u5.d == i31) {
                    j |= j3;
                }
                i32++;
                i26 = i33;
                i17 = i34;
            }
            z10 = true;
        }
        z = z10;
        int i36 = i26;
        boolean z12 = !z6 && i16 == 1;
        if (i13 <= 0 || j == 0 || (i13 >= i16 - 1 && !z12 && iMax <= 1)) {
            z2 = z;
        } else {
            float fBitCount = Long.bitCount(j);
            if (!z12) {
                if ((j & 1) != 0 && !((U) getChildAt(0).getLayoutParams()).g) {
                    fBitCount -= 0.5f;
                }
                int i37 = childCount2 - 1;
                if ((j & ((long) (1 << i37))) != 0 && !((U) getChildAt(i37).getLayoutParams()).g) {
                    fBitCount -= 0.5f;
                }
            }
            int i38 = fBitCount > 0.0f ? (int) ((i13 * i15) / fBitCount) : 0;
            boolean z13 = z;
            for (int i39 = 0; i39 < childCount2; i39++) {
                if ((j & ((long) (1 << i39))) != 0) {
                    View childAt3 = getChildAt(i39);
                    U u6 = (U) childAt3.getLayoutParams();
                    if (childAt3 instanceof ActionMenuItemView) {
                        u6.e = i38;
                        u6.h = true;
                        if (i39 == 0 && !u6.g) {
                            ((ViewGroup.MarginLayoutParams) u6).leftMargin = (-i38) / 2;
                        }
                        z13 = true;
                    } else if (u6.c) {
                        u6.e = i38;
                        u6.h = true;
                        ((ViewGroup.MarginLayoutParams) u6).rightMargin = (-i38) / 2;
                        z13 = true;
                    } else {
                        if (i39 != 0) {
                            ((ViewGroup.MarginLayoutParams) u6).leftMargin = i38 / 2;
                        }
                        if (i39 != childCount2 - 1) {
                            ((ViewGroup.MarginLayoutParams) u6).rightMargin = i38 / 2;
                        }
                    }
                }
            }
            z2 = z13;
        }
        if (z2) {
            for (int i40 = 0; i40 < childCount2; i40++) {
                View childAt4 = getChildAt(i40);
                U u7 = (U) childAt4.getLayoutParams();
                if (u7.h) {
                    childAt4.measure(View.MeasureSpec.makeMeasureSpec((u7.d * i15) + u7.e, 1073741824), childMeasureSpec);
                }
            }
        }
        if (i23 != 1073741824) {
            i4 = i24;
            i3 = i36;
        } else {
            i3 = i25;
            i4 = i24;
        }
        setMeasuredDimension(i4, i3);
    }

    public boolean p(int i) {
        boolean zB = false;
        if (i == 0) {
            return false;
        }
        KeyEvent.Callback childAt = getChildAt(i - 1);
        KeyEvent.Callback childAt2 = getChildAt(i);
        if (i < getChildCount() && (childAt instanceof T)) {
            zB = false | ((ActionMenuItemView) ((T) childAt)).b();
        }
        if (i <= 0 || !(childAt2 instanceof T)) {
            return zB;
        }
        if (((ActionMenuItemView) ((T) childAt2)).b()) {
            throw null;
        }
        return zB | false;
    }

    @Override // defpackage.AbstractC1358q0, android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new U(getContext(), attributeSet);
    }
}
