package a.b.p;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

/* JADX INFO: loaded from: classes.dex */
public class i0 extends ViewGroup {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f282a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f283b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f284c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f285d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f286e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f287f;
    public float g;
    public boolean h;
    public int[] i;
    public int[] j;
    public Drawable k;
    public int l;
    public int m;
    public int n;
    public int o;

    public static class a extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f288a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f289b;

        public a(int i, int i2) {
            super(i, i2);
            this.f289b = -1;
            this.f288a = 0.0f;
        }

        public a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f289b = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.b.j.LinearLayoutCompat_Layout);
            this.f288a = typedArrayObtainStyledAttributes.getFloat(a.b.j.LinearLayoutCompat_Layout_android_layout_weight, 0.0f);
            this.f289b = typedArrayObtainStyledAttributes.getInt(a.b.j.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            typedArrayObtainStyledAttributes.recycle();
        }

        public a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f289b = -1;
        }
    }

    public i0(Context context) {
        this(context, null);
    }

    public i0(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public i0(Context context, AttributeSet attributeSet, int i) {
        int resourceId;
        super(context, attributeSet, i);
        this.f282a = true;
        this.f283b = -1;
        this.f284c = 0;
        this.f286e = 8388659;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.b.j.LinearLayoutCompat, i, 0);
        a.h.k.q.T(this, context, a.b.j.LinearLayoutCompat, attributeSet, typedArrayObtainStyledAttributes, i, 0);
        int i2 = typedArrayObtainStyledAttributes.getInt(a.b.j.LinearLayoutCompat_android_orientation, -1);
        if (i2 >= 0) {
            setOrientation(i2);
        }
        int i3 = typedArrayObtainStyledAttributes.getInt(a.b.j.LinearLayoutCompat_android_gravity, -1);
        if (i3 >= 0) {
            setGravity(i3);
        }
        boolean z = typedArrayObtainStyledAttributes.getBoolean(a.b.j.LinearLayoutCompat_android_baselineAligned, true);
        if (!z) {
            setBaselineAligned(z);
        }
        this.g = typedArrayObtainStyledAttributes.getFloat(a.b.j.LinearLayoutCompat_android_weightSum, -1.0f);
        this.f283b = typedArrayObtainStyledAttributes.getInt(a.b.j.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.h = typedArrayObtainStyledAttributes.getBoolean(a.b.j.LinearLayoutCompat_measureWithLargestChild, false);
        int i4 = a.b.j.LinearLayoutCompat_divider;
        setDividerDrawable((!typedArrayObtainStyledAttributes.hasValue(i4) || (resourceId = typedArrayObtainStyledAttributes.getResourceId(i4, 0)) == 0) ? typedArrayObtainStyledAttributes.getDrawable(i4) : a.b.l.a.a.b(context, resourceId));
        this.n = typedArrayObtainStyledAttributes.getInt(a.b.j.LinearLayoutCompat_showDividers, 0);
        this.o = typedArrayObtainStyledAttributes.getDimensionPixelSize(a.b.j.LinearLayoutCompat_dividerPadding, 0);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof a;
    }

    public void g(Canvas canvas, int i) {
        this.k.setBounds(getPaddingLeft() + this.o, i, (getWidth() - getPaddingRight()) - this.o, this.m + i);
        this.k.draw(canvas);
    }

    @Override // android.view.View
    public int getBaseline() {
        int i;
        if (this.f283b < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i2 = this.f283b;
        if (childCount <= i2) {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
        View childAt = getChildAt(i2);
        int baseline = childAt.getBaseline();
        if (baseline == -1) {
            if (this.f283b == 0) {
                return -1;
            }
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
        }
        int bottom = this.f284c;
        if (this.f285d == 1 && (i = this.f286e & 112) != 48) {
            if (i == 16) {
                bottom += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.f287f) / 2;
            } else if (i == 80) {
                bottom = ((getBottom() - getTop()) - getPaddingBottom()) - this.f287f;
            }
        }
        return bottom + ((ViewGroup.MarginLayoutParams) ((a) childAt.getLayoutParams())).topMargin + baseline;
    }

    public int getBaselineAlignedChildIndex() {
        return this.f283b;
    }

    public Drawable getDividerDrawable() {
        return this.k;
    }

    public int getDividerPadding() {
        return this.o;
    }

    public int getDividerWidth() {
        return this.l;
    }

    public int getGravity() {
        return this.f286e;
    }

    public int getOrientation() {
        return this.f285d;
    }

    public int getShowDividers() {
        return this.n;
    }

    public int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.g;
    }

    public void h(Canvas canvas, int i) {
        this.k.setBounds(i, getPaddingTop() + this.o, this.l + i, (getHeight() - getPaddingBottom()) - this.o);
        this.k.draw(canvas);
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public a generateDefaultLayoutParams() {
        int i = this.f285d;
        if (i == 0) {
            return new a(-2, -2);
        }
        if (i == 1) {
            return new a(-1, -2);
        }
        return null;
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
    public a generateLayoutParams(AttributeSet attributeSet) {
        return new a(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public a generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new a(layoutParams);
    }

    public int l() {
        return 0;
    }

    public int m() {
        return 0;
    }

    public int n() {
        return 0;
    }

    public boolean o(int i) {
        if (i == 0) {
            return (this.n & 1) != 0;
        }
        if (i == getChildCount()) {
            return (this.n & 4) != 0;
        }
        if ((this.n & 2) == 0) {
            return false;
        }
        for (int i2 = i - 1; i2 >= 0; i2--) {
            if (getChildAt(i2).getVisibility() != 8) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        int right;
        int left;
        int paddingRight;
        if (this.k == null) {
            return;
        }
        int i = 0;
        if (this.f285d == 1) {
            int virtualChildCount = getVirtualChildCount();
            while (i < virtualChildCount) {
                View childAt = getChildAt(i);
                if (childAt != null && childAt.getVisibility() != 8 && o(i)) {
                    g(canvas, (childAt.getTop() - ((ViewGroup.MarginLayoutParams) ((a) childAt.getLayoutParams())).topMargin) - this.m);
                }
                i++;
            }
            if (o(virtualChildCount)) {
                View childAt2 = getChildAt(virtualChildCount - 1);
                g(canvas, childAt2 == null ? (getHeight() - getPaddingBottom()) - this.m : childAt2.getBottom() + ((ViewGroup.MarginLayoutParams) ((a) childAt2.getLayoutParams())).bottomMargin);
                return;
            }
            return;
        }
        int virtualChildCount2 = getVirtualChildCount();
        boolean zB = b1.b(this);
        while (i < virtualChildCount2) {
            View childAt3 = getChildAt(i);
            if (childAt3 != null && childAt3.getVisibility() != 8 && o(i)) {
                a aVar = (a) childAt3.getLayoutParams();
                h(canvas, zB ? childAt3.getRight() + ((ViewGroup.MarginLayoutParams) aVar).rightMargin : (childAt3.getLeft() - ((ViewGroup.MarginLayoutParams) aVar).leftMargin) - this.l);
            }
            i++;
        }
        if (o(virtualChildCount2)) {
            View childAt4 = getChildAt(virtualChildCount2 - 1);
            if (childAt4 != null) {
                a aVar2 = (a) childAt4.getLayoutParams();
                if (zB) {
                    left = childAt4.getLeft();
                    paddingRight = ((ViewGroup.MarginLayoutParams) aVar2).leftMargin;
                    right = (left - paddingRight) - this.l;
                } else {
                    right = childAt4.getRight() + ((ViewGroup.MarginLayoutParams) aVar2).rightMargin;
                }
            } else if (zB) {
                right = getPaddingLeft();
            } else {
                left = getWidth();
                paddingRight = getPaddingRight();
                right = (left - paddingRight) - this.l;
            }
            h(canvas, right);
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

    /* JADX WARN: Removed duplicated region for block: B:29:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01b4  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onLayout(boolean r24, int r25, int r26, int r27, int r28) {
        /*
            Method dump skipped, instruction units count: 486
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.b.p.i0.onLayout(boolean, int, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:136:0x02cd  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0308  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0316  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x04b6  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x04bb  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x04e3  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x04e8  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x04f2  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0501  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x0516  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x052a  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x056d  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x057d  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0585  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0590  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x062b  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x06ea  */
    /* JADX WARN: Removed duplicated region for block: B:317:0x06f1  */
    /* JADX WARN: Removed duplicated region for block: B:320:0x070e  */
    /* JADX WARN: Removed duplicated region for block: B:336:0x0766  */
    /* JADX WARN: Removed duplicated region for block: B:366:0x0818  */
    /* JADX WARN: Removed duplicated region for block: B:380:0x0860  */
    /* JADX WARN: Removed duplicated region for block: B:383:0x0892  */
    /* JADX WARN: Removed duplicated region for block: B:386:0x0897  */
    /* JADX WARN: Removed duplicated region for block: B:389:0x08b9  */
    /* JADX WARN: Removed duplicated region for block: B:439:? A[RETURN, SYNTHETIC] */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onMeasure(int r39, int r40) {
        /*
            Method dump skipped, instruction units count: 2298
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.b.p.i0.onMeasure(int, int):void");
    }

    public void p(View view, int i, int i2, int i3, int i4) {
        measureChildWithMargins(view, i, i2, i3, i4);
    }

    public int q() {
        return 0;
    }

    public void setBaselineAligned(boolean z) {
        this.f282a = z;
    }

    public void setBaselineAlignedChildIndex(int i) {
        if (i >= 0 && i < getChildCount()) {
            this.f283b = i;
            return;
        }
        StringBuilder sbD = b.a.a.a.a.d("base aligned child index out of range (0, ");
        sbD.append(getChildCount());
        sbD.append(")");
        throw new IllegalArgumentException(sbD.toString());
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable == this.k) {
            return;
        }
        this.k = drawable;
        if (drawable != null) {
            this.l = drawable.getIntrinsicWidth();
            this.m = drawable.getIntrinsicHeight();
        } else {
            this.l = 0;
            this.m = 0;
        }
        setWillNotDraw(drawable == null);
        requestLayout();
    }

    public void setDividerPadding(int i) {
        this.o = i;
    }

    public void setGravity(int i) {
        if (this.f286e != i) {
            if ((8388615 & i) == 0) {
                i |= 8388611;
            }
            if ((i & 112) == 0) {
                i |= 48;
            }
            this.f286e = i;
            requestLayout();
        }
    }

    public void setHorizontalGravity(int i) {
        int i2 = i & 8388615;
        int i3 = this.f286e;
        if ((8388615 & i3) != i2) {
            this.f286e = i2 | ((-8388616) & i3);
            requestLayout();
        }
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.h = z;
    }

    public void setOrientation(int i) {
        if (this.f285d != i) {
            this.f285d = i;
            requestLayout();
        }
    }

    public void setShowDividers(int i) {
        if (i != this.n) {
            requestLayout();
        }
        this.n = i;
    }

    public void setVerticalGravity(int i) {
        int i2 = i & 112;
        int i3 = this.f286e;
        if ((i3 & 112) != i2) {
            this.f286e = i2 | (i3 & (-113));
            requestLayout();
        }
    }

    public void setWeightSum(float f2) {
        this.g = Math.max(0.0f, f2);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }
}
