package r;

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
import n.AbstractC1850a;

/* JADX INFO: renamed from: r.X, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2235X extends ViewGroup {
    public boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f21457m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f21458n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f21459p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f21460q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f21461r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f21462s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int[] f21463t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int[] f21464u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Drawable f21465v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f21466w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f21467x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f21468y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f21469z;

    public AbstractC2235X(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.l = true;
        this.f21457m = -1;
        this.f21458n = 0;
        this.f21459p = 8388659;
        int[] iArr = AbstractC1850a.f19360k;
        h3.y yVarF = h3.y.F(context, attributeSet, iArr, 0);
        int[] iArr2 = F1.H.f2303a;
        F1.E.b(this, context, iArr, attributeSet, (TypedArray) yVarF.f15500n, 0, 0);
        TypedArray typedArray = (TypedArray) yVarF.f15500n;
        int i7 = typedArray.getInt(1, -1);
        if (i7 >= 0) {
            setOrientation(i7);
        }
        int i10 = typedArray.getInt(0, -1);
        if (i10 >= 0) {
            setGravity(i10);
        }
        boolean z6 = typedArray.getBoolean(2, true);
        if (!z6) {
            setBaselineAligned(z6);
        }
        this.f21461r = typedArray.getFloat(4, -1.0f);
        this.f21457m = typedArray.getInt(3, -1);
        this.f21462s = typedArray.getBoolean(7, false);
        setDividerDrawable(yVarF.s(5));
        this.f21468y = typedArray.getInt(8, 0);
        this.f21469z = typedArray.getDimensionPixelSize(6, 0);
        yVarF.G();
    }

    public final void c(Canvas canvas, int i7) {
        this.f21465v.setBounds(getPaddingLeft() + this.f21469z, i7, (getWidth() - getPaddingRight()) - this.f21469z, this.f21467x + i7);
        this.f21465v.draw(canvas);
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C2234W;
    }

    public final void d(Canvas canvas, int i7) {
        this.f21465v.setBounds(i7, getPaddingTop() + this.f21469z, this.f21466w + i7, (getHeight() - getPaddingBottom()) - this.f21469z);
        this.f21465v.draw(canvas);
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public C2234W generateDefaultLayoutParams() {
        int i7 = this.o;
        if (i7 == 0) {
            return new C2234W(-2, -2);
        }
        if (i7 == 1) {
            return new C2234W(-1, -2);
        }
        return null;
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public C2234W generateLayoutParams(AttributeSet attributeSet) {
        return new C2234W(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public C2234W generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C2234W ? new C2234W((C2234W) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new C2234W((ViewGroup.MarginLayoutParams) layoutParams) : new C2234W(layoutParams);
    }

    @Override // android.view.View
    public int getBaseline() {
        int i7;
        if (this.f21457m < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i10 = this.f21457m;
        if (childCount <= i10) {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
        View childAt = getChildAt(i10);
        int baseline = childAt.getBaseline();
        if (baseline == -1) {
            if (this.f21457m == 0) {
                return -1;
            }
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
        }
        int bottom = this.f21458n;
        if (this.o == 1 && (i7 = this.f21459p & 112) != 48) {
            if (i7 == 16) {
                bottom += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.f21460q) / 2;
            } else if (i7 == 80) {
                bottom = ((getBottom() - getTop()) - getPaddingBottom()) - this.f21460q;
            }
        }
        return bottom + ((LinearLayout.LayoutParams) ((C2234W) childAt.getLayoutParams())).topMargin + baseline;
    }

    public int getBaselineAlignedChildIndex() {
        return this.f21457m;
    }

    public Drawable getDividerDrawable() {
        return this.f21465v;
    }

    public int getDividerPadding() {
        return this.f21469z;
    }

    public int getDividerWidth() {
        return this.f21466w;
    }

    public int getGravity() {
        return this.f21459p;
    }

    public int getOrientation() {
        return this.o;
    }

    public int getShowDividers() {
        return this.f21468y;
    }

    public int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.f21461r;
    }

    public final boolean h(int i7) {
        if (i7 == 0) {
            return (this.f21468y & 1) != 0;
        }
        if (i7 == getChildCount()) {
            return (this.f21468y & 4) != 0;
        }
        if ((this.f21468y & 2) == 0) {
            return false;
        }
        for (int i10 = i7 - 1; i10 >= 0; i10--) {
            if (getChildAt(i10).getVisibility() != 8) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int right;
        int left;
        int i7;
        if (this.f21465v == null) {
            return;
        }
        int i10 = 0;
        if (this.o == 1) {
            int virtualChildCount = getVirtualChildCount();
            while (i10 < virtualChildCount) {
                View childAt = getChildAt(i10);
                if (childAt != null && childAt.getVisibility() != 8 && h(i10)) {
                    c(canvas, (childAt.getTop() - ((LinearLayout.LayoutParams) ((C2234W) childAt.getLayoutParams())).topMargin) - this.f21467x);
                }
                i10++;
            }
            if (h(virtualChildCount)) {
                View childAt2 = getChildAt(virtualChildCount - 1);
                c(canvas, childAt2 == null ? (getHeight() - getPaddingBottom()) - this.f21467x : childAt2.getBottom() + ((LinearLayout.LayoutParams) ((C2234W) childAt2.getLayoutParams())).bottomMargin);
                return;
            }
            return;
        }
        int virtualChildCount2 = getVirtualChildCount();
        boolean z6 = getLayoutDirection() == 1;
        while (i10 < virtualChildCount2) {
            View childAt3 = getChildAt(i10);
            if (childAt3 != null && childAt3.getVisibility() != 8 && h(i10)) {
                C2234W c2234w = (C2234W) childAt3.getLayoutParams();
                d(canvas, z6 ? childAt3.getRight() + ((LinearLayout.LayoutParams) c2234w).rightMargin : (childAt3.getLeft() - ((LinearLayout.LayoutParams) c2234w).leftMargin) - this.f21466w);
            }
            i10++;
        }
        if (h(virtualChildCount2)) {
            View childAt4 = getChildAt(virtualChildCount2 - 1);
            if (childAt4 != null) {
                C2234W c2234w2 = (C2234W) childAt4.getLayoutParams();
                if (z6) {
                    left = childAt4.getLeft() - ((LinearLayout.LayoutParams) c2234w2).leftMargin;
                    i7 = this.f21466w;
                    right = left - i7;
                } else {
                    right = childAt4.getRight() + ((LinearLayout.LayoutParams) c2234w2).rightMargin;
                }
            } else if (z6) {
                right = getPaddingLeft();
            } else {
                left = getWidth() - getPaddingRight();
                i7 = this.f21466w;
                right = left - i7;
            }
            d(canvas, right);
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

    /* JADX WARN: Removed duplicated region for block: B:29:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01a5  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onLayout(boolean r23, int r24, int r25, int r26, int r27) {
        /*
            Method dump skipped, instruction units count: 456
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: r.AbstractC2235X.onLayout(boolean, int, int, int, int):void");
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
        throw new UnsupportedOperationException("Method not decompiled: r.AbstractC2235X.onMeasure(int, int):void");
    }

    public void setBaselineAligned(boolean z6) {
        this.l = z6;
    }

    public void setBaselineAlignedChildIndex(int i7) {
        if (i7 >= 0 && i7 < getChildCount()) {
            this.f21457m = i7;
            return;
        }
        throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable == this.f21465v) {
            return;
        }
        this.f21465v = drawable;
        if (drawable != null) {
            this.f21466w = drawable.getIntrinsicWidth();
            this.f21467x = drawable.getIntrinsicHeight();
        } else {
            this.f21466w = 0;
            this.f21467x = 0;
        }
        setWillNotDraw(drawable == null);
        requestLayout();
    }

    public void setDividerPadding(int i7) {
        this.f21469z = i7;
    }

    public void setGravity(int i7) {
        if (this.f21459p != i7) {
            if ((8388615 & i7) == 0) {
                i7 |= 8388611;
            }
            if ((i7 & 112) == 0) {
                i7 |= 48;
            }
            this.f21459p = i7;
            requestLayout();
        }
    }

    public void setHorizontalGravity(int i7) {
        int i10 = i7 & 8388615;
        int i11 = this.f21459p;
        if ((8388615 & i11) != i10) {
            this.f21459p = i10 | ((-8388616) & i11);
            requestLayout();
        }
    }

    public void setMeasureWithLargestChildEnabled(boolean z6) {
        this.f21462s = z6;
    }

    public void setOrientation(int i7) {
        if (this.o != i7) {
            this.o = i7;
            requestLayout();
        }
    }

    public void setShowDividers(int i7) {
        if (i7 != this.f21468y) {
            requestLayout();
        }
        this.f21468y = i7;
    }

    public void setVerticalGravity(int i7) {
        int i10 = i7 & 112;
        int i11 = this.f21459p;
        if ((i11 & 112) != i10) {
            this.f21459p = i10 | (i11 & (-113));
            requestLayout();
        }
    }

    public void setWeightSum(float f10) {
        this.f21461r = Math.max(0.0f, f10);
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }
}
