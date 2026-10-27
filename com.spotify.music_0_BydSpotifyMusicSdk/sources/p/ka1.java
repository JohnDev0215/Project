package p;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;

/* JADX INFO: loaded from: classes.dex */
public abstract class ka1 extends ViewGroup {
    public boolean c;
    public int e;
    public int k;
    public int l;
    public int m;
    public int n;
    public float o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f142p;
    public int[] q;
    public int[] r;
    public Drawable s;
    public int t;
    public int u;
    public int v;
    public int w;

    public ka1(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = true;
        this.e = -1;
        this.k = 0;
        this.m = 8388659;
        int[] iArr = yz1.n;
        v4 v4Var = new v4(context, context.obtainStyledAttributes(attributeSet, iArr, i, 0));
        zt2.j(this, context, iArr, attributeSet, (TypedArray) v4Var.k, i);
        int iW = v4Var.w(1, -1);
        if (iW >= 0) {
            setOrientation(iW);
        }
        int iW2 = v4Var.w(0, -1);
        if (iW2 >= 0) {
            setGravity(iW2);
        }
        boolean zP = v4Var.p(2, true);
        if (!zP) {
            setBaselineAligned(zP);
        }
        this.o = ((TypedArray) v4Var.k).getFloat(4, -1.0f);
        this.e = v4Var.w(3, -1);
        this.f142p = v4Var.p(7, false);
        setDividerDrawable(v4Var.t(5));
        this.v = v4Var.w(8, 0);
        this.w = v4Var.s(6, 0);
        v4Var.L();
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ja1;
    }

    public final void f(Canvas canvas, int i) {
        this.s.setBounds(getPaddingLeft() + this.w, i, (getWidth() - getPaddingRight()) - this.w, this.u + i);
        this.s.draw(canvas);
    }

    public final void g(Canvas canvas, int i) {
        this.s.setBounds(i, getPaddingTop() + this.w, this.t + i, (getHeight() - getPaddingBottom()) - this.w);
        this.s.draw(canvas);
    }

    @Override // android.view.View
    public int getBaseline() {
        int i;
        if (this.e < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i2 = this.e;
        if (childCount <= i2) {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
        View childAt = getChildAt(i2);
        int baseline = childAt.getBaseline();
        if (baseline == -1) {
            if (this.e == 0) {
                return -1;
            }
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
        }
        int bottom = this.k;
        if (this.l == 1 && (i = this.m & 112) != 48) {
            if (i == 16) {
                bottom += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.n) / 2;
            } else if (i == 80) {
                bottom = ((getBottom() - getTop()) - getPaddingBottom()) - this.n;
            }
        }
        return bottom + ((LinearLayout.LayoutParams) ((ja1) childAt.getLayoutParams())).topMargin + baseline;
    }

    public int getBaselineAlignedChildIndex() {
        return this.e;
    }

    public Drawable getDividerDrawable() {
        return this.s;
    }

    public int getDividerPadding() {
        return this.w;
    }

    public int getDividerWidth() {
        return this.t;
    }

    public int getGravity() {
        return this.m;
    }

    public int getOrientation() {
        return this.l;
    }

    public int getShowDividers() {
        return this.v;
    }

    public int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.o;
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public ja1 generateDefaultLayoutParams() {
        int i = this.l;
        if (i == 0) {
            return new ja1(-2, -2);
        }
        if (i == 1) {
            return new ja1(-1, -2);
        }
        return null;
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public ja1 generateLayoutParams(AttributeSet attributeSet) {
        return new ja1(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
    public ja1 generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new ja1(layoutParams);
    }

    public final boolean k(int i) {
        if (i == 0) {
            return (this.v & 1) != 0;
        }
        if (i == getChildCount()) {
            return (this.v & 4) != 0;
        }
        if ((this.v & 2) == 0) {
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
    public final void onDraw(Canvas canvas) {
        int right;
        int left;
        int i;
        if (this.s == null) {
            return;
        }
        int i2 = 0;
        if (this.l == 1) {
            int virtualChildCount = getVirtualChildCount();
            while (i2 < virtualChildCount) {
                View childAt = getChildAt(i2);
                if (childAt != null && childAt.getVisibility() != 8 && k(i2)) {
                    f(canvas, (childAt.getTop() - ((LinearLayout.LayoutParams) ((ja1) childAt.getLayoutParams())).topMargin) - this.u);
                }
                i2++;
            }
            if (k(virtualChildCount)) {
                View childAt2 = getChildAt(virtualChildCount - 1);
                f(canvas, childAt2 == null ? (getHeight() - getPaddingBottom()) - this.u : childAt2.getBottom() + ((LinearLayout.LayoutParams) ((ja1) childAt2.getLayoutParams())).bottomMargin);
                return;
            }
            return;
        }
        int virtualChildCount2 = getVirtualChildCount();
        boolean zA = ev2.a(this);
        while (i2 < virtualChildCount2) {
            View childAt3 = getChildAt(i2);
            if (childAt3 != null && childAt3.getVisibility() != 8 && k(i2)) {
                ja1 ja1Var = (ja1) childAt3.getLayoutParams();
                g(canvas, zA ? childAt3.getRight() + ((LinearLayout.LayoutParams) ja1Var).rightMargin : (childAt3.getLeft() - ((LinearLayout.LayoutParams) ja1Var).leftMargin) - this.t);
            }
            i2++;
        }
        if (k(virtualChildCount2)) {
            View childAt4 = getChildAt(virtualChildCount2 - 1);
            if (childAt4 != null) {
                ja1 ja1Var2 = (ja1) childAt4.getLayoutParams();
                if (zA) {
                    left = childAt4.getLeft() - ((LinearLayout.LayoutParams) ja1Var2).leftMargin;
                    i = this.t;
                    right = left - i;
                } else {
                    right = childAt4.getRight() + ((LinearLayout.LayoutParams) ja1Var2).rightMargin;
                }
            } else if (zA) {
                right = getPaddingLeft();
            } else {
                left = getWidth() - getPaddingRight();
                i = this.t;
                right = left - i;
            }
            g(canvas, right);
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0190  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01a4  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onLayout(boolean r24, int r25, int r26, int r27, int r28) {
        /*
            Method dump skipped, instruction units count: 455
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.ka1.onLayout(boolean, int, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:153:0x02f4  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0486  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x048b  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x04b3  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x04b8  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x04c0  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x04cc  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x04de  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x04f2  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x0537  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x0548  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0550  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x055b  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x05e4  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x0691  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x0698  */
    /* JADX WARN: Removed duplicated region for block: B:318:0x06b4  */
    /* JADX WARN: Removed duplicated region for block: B:368:0x07cb  */
    /* JADX WARN: Removed duplicated region for block: B:381:0x0806  */
    /* JADX WARN: Removed duplicated region for block: B:388:0x083d  */
    /* JADX WARN: Removed duplicated region for block: B:391:0x0860  */
    /* JADX WARN: Removed duplicated region for block: B:442:? A[RETURN, SYNTHETIC] */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onMeasure(int r38, int r39) {
        /*
            Method dump skipped, instruction units count: 2208
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.ka1.onMeasure(int, int):void");
    }

    public void setBaselineAligned(boolean z) {
        this.c = z;
    }

    public void setBaselineAlignedChildIndex(int i) {
        if (i >= 0 && i < getChildCount()) {
            this.e = i;
            return;
        }
        throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable == this.s) {
            return;
        }
        this.s = drawable;
        if (drawable != null) {
            this.t = drawable.getIntrinsicWidth();
            this.u = drawable.getIntrinsicHeight();
        } else {
            this.t = 0;
            this.u = 0;
        }
        setWillNotDraw(drawable == null);
        requestLayout();
    }

    public void setDividerPadding(int i) {
        this.w = i;
    }

    public void setGravity(int i) {
        if (this.m != i) {
            if ((8388615 & i) == 0) {
                i |= 8388611;
            }
            if ((i & 112) == 0) {
                i |= 48;
            }
            this.m = i;
            requestLayout();
        }
    }

    public void setHorizontalGravity(int i) {
        int i2 = i & 8388615;
        int i3 = this.m;
        if ((8388615 & i3) != i2) {
            this.m = i2 | ((-8388616) & i3);
            requestLayout();
        }
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.f142p = z;
    }

    public void setOrientation(int i) {
        if (this.l != i) {
            this.l = i;
            requestLayout();
        }
    }

    public void setShowDividers(int i) {
        if (i != this.v) {
            requestLayout();
        }
        this.v = i;
    }

    public void setVerticalGravity(int i) {
        int i2 = i & 112;
        int i3 = this.m;
        if ((i3 & 112) != i2) {
            this.m = i2 | (i3 & (-113));
            requestLayout();
        }
    }

    public void setWeightSum(float f) {
        this.o = Math.max(0.0f, f);
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }
}
