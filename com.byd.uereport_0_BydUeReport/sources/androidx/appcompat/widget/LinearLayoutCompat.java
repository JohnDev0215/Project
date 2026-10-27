package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.appcompat.R$styleable;
import o0.t;
import r.e0;
import r.k0;

/* JADX INFO: loaded from: classes.dex */
public class LinearLayoutCompat extends ViewGroup {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f612d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f613e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f614f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f615g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f616h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f617i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f618j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f619k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int[] f620l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int[] f621m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Drawable f622n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f623o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f624p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f625q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f626r;

    public LinearLayoutCompat(Context context) {
        this(context, null);
    }

    public final void A(View view, int i8, int i9, int i10, int i11) {
        view.layout(i8, i9, i10 + i8, i11 + i9);
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof a;
    }

    public void g(Canvas canvas) {
        int right;
        int left;
        int i8;
        int virtualChildCount = getVirtualChildCount();
        boolean zB = k0.b(this);
        for (int i9 = 0; i9 < virtualChildCount; i9++) {
            View viewS = s(i9);
            if (viewS != null && viewS.getVisibility() != 8 && t(i9)) {
                a aVar = (a) viewS.getLayoutParams();
                j(canvas, zB ? viewS.getRight() + ((ViewGroup.MarginLayoutParams) aVar).rightMargin : (viewS.getLeft() - ((ViewGroup.MarginLayoutParams) aVar).leftMargin) - this.f623o);
            }
        }
        if (t(virtualChildCount)) {
            View viewS2 = s(virtualChildCount - 1);
            if (viewS2 != null) {
                a aVar2 = (a) viewS2.getLayoutParams();
                if (zB) {
                    left = viewS2.getLeft() - ((ViewGroup.MarginLayoutParams) aVar2).leftMargin;
                    i8 = this.f623o;
                    right = left - i8;
                } else {
                    right = viewS2.getRight() + ((ViewGroup.MarginLayoutParams) aVar2).rightMargin;
                }
            } else if (zB) {
                right = getPaddingLeft();
            } else {
                left = getWidth() - getPaddingRight();
                i8 = this.f623o;
                right = left - i8;
            }
            j(canvas, right);
        }
    }

    @Override // android.view.View
    public int getBaseline() {
        int i8;
        if (this.f613e < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i9 = this.f613e;
        if (childCount <= i9) {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
        View childAt = getChildAt(i9);
        int baseline = childAt.getBaseline();
        if (baseline == -1) {
            if (this.f613e == 0) {
                return -1;
            }
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
        }
        int bottom = this.f614f;
        if (this.f615g == 1 && (i8 = this.f616h & 112) != 48) {
            if (i8 == 16) {
                bottom += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.f617i) / 2;
            } else if (i8 == 80) {
                bottom = ((getBottom() - getTop()) - getPaddingBottom()) - this.f617i;
            }
        }
        return bottom + ((ViewGroup.MarginLayoutParams) ((a) childAt.getLayoutParams())).topMargin + baseline;
    }

    public int getBaselineAlignedChildIndex() {
        return this.f613e;
    }

    public Drawable getDividerDrawable() {
        return this.f622n;
    }

    public int getDividerPadding() {
        return this.f626r;
    }

    public int getDividerWidth() {
        return this.f623o;
    }

    public int getGravity() {
        return this.f616h;
    }

    public int getOrientation() {
        return this.f615g;
    }

    public int getShowDividers() {
        return this.f625q;
    }

    public int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.f618j;
    }

    public void h(Canvas canvas) {
        int virtualChildCount = getVirtualChildCount();
        for (int i8 = 0; i8 < virtualChildCount; i8++) {
            View viewS = s(i8);
            if (viewS != null && viewS.getVisibility() != 8 && t(i8)) {
                i(canvas, (viewS.getTop() - ((ViewGroup.MarginLayoutParams) ((a) viewS.getLayoutParams())).topMargin) - this.f624p);
            }
        }
        if (t(virtualChildCount)) {
            View viewS2 = s(virtualChildCount - 1);
            i(canvas, viewS2 == null ? (getHeight() - getPaddingBottom()) - this.f624p : viewS2.getBottom() + ((ViewGroup.MarginLayoutParams) ((a) viewS2.getLayoutParams())).bottomMargin);
        }
    }

    public void i(Canvas canvas, int i8) {
        this.f622n.setBounds(getPaddingLeft() + this.f626r, i8, (getWidth() - getPaddingRight()) - this.f626r, this.f624p + i8);
        this.f622n.draw(canvas);
    }

    public void j(Canvas canvas, int i8) {
        this.f622n.setBounds(i8, getPaddingTop() + this.f626r, this.f623o + i8, (getHeight() - getPaddingBottom()) - this.f626r);
        this.f622n.draw(canvas);
    }

    public final void k(int i8, int i9) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        for (int i10 = 0; i10 < i8; i10++) {
            View viewS = s(i10);
            if (viewS.getVisibility() != 8) {
                a aVar = (a) viewS.getLayoutParams();
                if (((ViewGroup.MarginLayoutParams) aVar).height == -1) {
                    int i11 = ((ViewGroup.MarginLayoutParams) aVar).width;
                    ((ViewGroup.MarginLayoutParams) aVar).width = viewS.getMeasuredWidth();
                    measureChildWithMargins(viewS, i9, 0, iMakeMeasureSpec, 0);
                    ((ViewGroup.MarginLayoutParams) aVar).width = i11;
                }
            }
        }
    }

    public final void l(int i8, int i9) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        for (int i10 = 0; i10 < i8; i10++) {
            View viewS = s(i10);
            if (viewS.getVisibility() != 8) {
                a aVar = (a) viewS.getLayoutParams();
                if (((ViewGroup.MarginLayoutParams) aVar).width == -1) {
                    int i11 = ((ViewGroup.MarginLayoutParams) aVar).height;
                    ((ViewGroup.MarginLayoutParams) aVar).height = viewS.getMeasuredHeight();
                    measureChildWithMargins(viewS, iMakeMeasureSpec, 0, i9, 0);
                    ((ViewGroup.MarginLayoutParams) aVar).height = i11;
                }
            }
        }
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public a generateDefaultLayoutParams() {
        int i8 = this.f615g;
        if (i8 == 0) {
            return new a(-2, -2);
        }
        if (i8 == 1) {
            return new a(-1, -2);
        }
        return null;
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public a generateLayoutParams(AttributeSet attributeSet) {
        return new a(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public a generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new a(layoutParams);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (this.f622n == null) {
            return;
        }
        if (this.f615g == 1) {
            h(canvas);
        } else {
            g(canvas);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        if (this.f615g == 1) {
            v(i8, i9, i10, i11);
        } else {
            u(i8, i9, i10, i11);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i8, int i9) {
        if (this.f615g == 1) {
            z(i8, i9);
        } else {
            x(i8, i9);
        }
    }

    public int p(View view, int i8) {
        return 0;
    }

    public int q(View view) {
        return 0;
    }

    public int r(View view) {
        return 0;
    }

    public View s(int i8) {
        return getChildAt(i8);
    }

    public void setBaselineAligned(boolean z7) {
        this.f612d = z7;
    }

    public void setBaselineAlignedChildIndex(int i8) {
        if (i8 >= 0 && i8 < getChildCount()) {
            this.f613e = i8;
            return;
        }
        throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable == this.f622n) {
            return;
        }
        this.f622n = drawable;
        if (drawable != null) {
            this.f623o = drawable.getIntrinsicWidth();
            this.f624p = drawable.getIntrinsicHeight();
        } else {
            this.f623o = 0;
            this.f624p = 0;
        }
        setWillNotDraw(drawable == null);
        requestLayout();
    }

    public void setDividerPadding(int i8) {
        this.f626r = i8;
    }

    public void setGravity(int i8) {
        if (this.f616h != i8) {
            if ((8388615 & i8) == 0) {
                i8 |= 8388611;
            }
            if ((i8 & 112) == 0) {
                i8 |= 48;
            }
            this.f616h = i8;
            requestLayout();
        }
    }

    public void setHorizontalGravity(int i8) {
        int i9 = i8 & 8388615;
        int i10 = this.f616h;
        if ((8388615 & i10) != i9) {
            this.f616h = i9 | ((-8388616) & i10);
            requestLayout();
        }
    }

    public void setMeasureWithLargestChildEnabled(boolean z7) {
        this.f619k = z7;
    }

    public void setOrientation(int i8) {
        if (this.f615g != i8) {
            this.f615g = i8;
            requestLayout();
        }
    }

    public void setShowDividers(int i8) {
        if (i8 != this.f625q) {
            requestLayout();
        }
        this.f625q = i8;
    }

    public void setVerticalGravity(int i8) {
        int i9 = i8 & 112;
        int i10 = this.f616h;
        if ((i10 & 112) != i9) {
            this.f616h = i9 | (i10 & (-113));
            requestLayout();
        }
    }

    public void setWeightSum(float f8) {
        this.f618j = Math.max(0.0f, f8);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public boolean t(int i8) {
        if (i8 == 0) {
            return (this.f625q & 1) != 0;
        }
        if (i8 == getChildCount()) {
            return (this.f625q & 4) != 0;
        }
        if ((this.f625q & 2) == 0) {
            return false;
        }
        for (int i9 = i8 - 1; i9 >= 0; i9--) {
            if (getChildAt(i9).getVisibility() != 8) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00ff  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void u(int r25, int r26, int r27, int r28) {
        /*
            Method dump skipped, instruction units count: 329
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.LinearLayoutCompat.u(int, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x009f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void v(int r18, int r19, int r20, int r21) {
        /*
            Method dump skipped, instruction units count: 204
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.LinearLayoutCompat.v(int, int, int, int):void");
    }

    public void w(View view, int i8, int i9, int i10, int i11, int i12) {
        measureChildWithMargins(view, i9, i10, i11, i12);
    }

    /* JADX WARN: Removed duplicated region for block: B:197:0x0453  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01d8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void x(int r39, int r40) {
        /*
            Method dump skipped, instruction units count: 1294
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.LinearLayoutCompat.x(int, int):void");
    }

    public int y(int i8) {
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:150:0x0325  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0333  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void z(int r34, int r35) {
        /*
            Method dump skipped, instruction units count: 914
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.LinearLayoutCompat.z(int, int):void");
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f612d = true;
        this.f613e = -1;
        this.f614f = 0;
        this.f616h = 8388659;
        int[] iArr = R$styleable.LinearLayoutCompat;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i8, 0);
        t.j0(this, context, iArr, attributeSet, e0VarV.r(), i8, 0);
        int iK = e0VarV.k(R$styleable.LinearLayoutCompat_android_orientation, -1);
        if (iK >= 0) {
            setOrientation(iK);
        }
        int iK2 = e0VarV.k(R$styleable.LinearLayoutCompat_android_gravity, -1);
        if (iK2 >= 0) {
            setGravity(iK2);
        }
        boolean zA = e0VarV.a(R$styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (!zA) {
            setBaselineAligned(zA);
        }
        this.f618j = e0VarV.i(R$styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.f613e = e0VarV.k(R$styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.f619k = e0VarV.a(R$styleable.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(e0VarV.g(R$styleable.LinearLayoutCompat_divider));
        this.f625q = e0VarV.k(R$styleable.LinearLayoutCompat_showDividers, 0);
        this.f626r = e0VarV.f(R$styleable.LinearLayoutCompat_dividerPadding, 0);
        e0VarV.w();
    }

    public static class a extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f627a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f628b;

        public a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f628b = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.LinearLayoutCompat_Layout);
            this.f627a = typedArrayObtainStyledAttributes.getFloat(R$styleable.LinearLayoutCompat_Layout_android_layout_weight, 0.0f);
            this.f628b = typedArrayObtainStyledAttributes.getInt(R$styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            typedArrayObtainStyledAttributes.recycle();
        }

        public a(int i8, int i9) {
            super(i8, i9);
            this.f628b = -1;
            this.f627a = 0.0f;
        }

        public a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f628b = -1;
        }
    }
}
