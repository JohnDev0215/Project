package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

/* JADX INFO: renamed from: q0, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1358q0 extends ViewGroup {
    public int[] A;
    public int[] B;
    public Drawable C;
    public int D;
    public int E;
    public int F;
    public int G;
    public boolean s;
    public int t;
    public int u;
    public int v;
    public int w;
    public int x;
    public float y;
    public boolean z;

    public AbstractC1358q0(Context context, AttributeSet attributeSet, int i) {
        int resourceId;
        super(context, attributeSet, i);
        this.s = true;
        this.t = -1;
        this.u = 0;
        this.w = 8388659;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC0678dn.V, i, 0);
        int i2 = typedArrayObtainStyledAttributes.getInt(5, -1);
        if (i2 >= 0 && this.v != i2) {
            this.v = i2;
            requestLayout();
        }
        int i3 = typedArrayObtainStyledAttributes.getInt(4, -1);
        if (i3 >= 0 && this.w != i3) {
            i3 = (8388615 & i3) == 0 ? i3 | 8388611 : i3;
            this.w = (i3 & 112) == 0 ? i3 | 48 : i3;
            requestLayout();
        }
        boolean z = typedArrayObtainStyledAttributes.getBoolean(6, true);
        if (!z) {
            this.s = z;
        }
        this.y = typedArrayObtainStyledAttributes.getFloat(8, -1.0f);
        this.t = typedArrayObtainStyledAttributes.getInt(7, -1);
        this.z = typedArrayObtainStyledAttributes.getBoolean(2, false);
        Drawable drawable = (!typedArrayObtainStyledAttributes.hasValue(0) || (resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0)) == 0) ? typedArrayObtainStyledAttributes.getDrawable(0) : AbstractC1631v.b(context, resourceId);
        if (drawable != this.C) {
            this.C = drawable;
            if (drawable != null) {
                this.D = drawable.getIntrinsicWidth();
                this.E = drawable.getIntrinsicHeight();
            } else {
                this.D = 0;
                this.E = 0;
            }
            setWillNotDraw(drawable == null);
            requestLayout();
        }
        this.F = typedArrayObtainStyledAttributes.getInt(3, 0);
        this.G = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
        typedArrayObtainStyledAttributes.recycle();
    }

    public void a(Canvas canvas, int i) {
        this.C.setBounds(getPaddingLeft() + this.G, i, (getWidth() - getPaddingRight()) - this.G, this.E + i);
        this.C.draw(canvas);
    }

    public void b(Canvas canvas, int i) {
        this.C.setBounds(i, getPaddingTop() + this.G, this.D + i, (getHeight() - getPaddingBottom()) - this.G);
        this.C.draw(canvas);
    }

    public final void c(int i, int i2) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                C1303p0 c1303p0 = (C1303p0) childAt.getLayoutParams();
                if (((ViewGroup.MarginLayoutParams) c1303p0).height == -1) {
                    int i4 = ((ViewGroup.MarginLayoutParams) c1303p0).width;
                    ((ViewGroup.MarginLayoutParams) c1303p0).width = childAt.getMeasuredWidth();
                    measureChildWithMargins(childAt, i2, 0, iMakeMeasureSpec, 0);
                    ((ViewGroup.MarginLayoutParams) c1303p0).width = i4;
                }
            }
        }
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C1303p0;
    }

    public final void d(int i, int i2) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                C1303p0 c1303p0 = (C1303p0) childAt.getLayoutParams();
                if (((ViewGroup.MarginLayoutParams) c1303p0).width == -1) {
                    int i4 = ((ViewGroup.MarginLayoutParams) c1303p0).height;
                    ((ViewGroup.MarginLayoutParams) c1303p0).height = childAt.getMeasuredHeight();
                    measureChildWithMargins(childAt, iMakeMeasureSpec, 0, i2, 0);
                    ((ViewGroup.MarginLayoutParams) c1303p0).height = i4;
                }
            }
        }
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public C1303p0 generateDefaultLayoutParams() {
        int i = this.v;
        if (i == 0) {
            return new C1303p0(-2, -2);
        }
        if (i == 1) {
            return new C1303p0(-1, -2);
        }
        return null;
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public C1303p0 generateLayoutParams(AttributeSet attributeSet) {
        return new C1303p0(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public C1303p0 generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new C1303p0(layoutParams);
    }

    @Override // android.view.View
    public int getBaseline() {
        int i;
        if (this.t < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i2 = this.t;
        if (childCount <= i2) {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
        View childAt = getChildAt(i2);
        int baseline = childAt.getBaseline();
        if (baseline == -1) {
            if (this.t == 0) {
                return -1;
            }
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
        }
        int bottom = this.u;
        if (this.v == 1 && (i = this.w & 112) != 48) {
            if (i == 16) {
                bottom += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.x) / 2;
            } else if (i == 80) {
                bottom = ((getBottom() - getTop()) - getPaddingBottom()) - this.x;
            }
        }
        return bottom + ((ViewGroup.MarginLayoutParams) ((C1303p0) childAt.getLayoutParams())).topMargin + baseline;
    }

    public int h() {
        return 0;
    }

    public int i() {
        return 0;
    }

    public int j() {
        return 0;
    }

    public boolean k(int i) {
        if (i == 0) {
            return (this.F & 1) != 0;
        }
        if (i == getChildCount()) {
            return (this.F & 4) != 0;
        }
        if ((this.F & 2) == 0) {
            return false;
        }
        for (int i2 = i - 1; i2 >= 0; i2--) {
            if (getChildAt(i2).getVisibility() != 8) {
                return true;
            }
        }
        return false;
    }

    public void l(View view, int i, int i2, int i3, int i4) {
        measureChildWithMargins(view, i, i2, i3, i4);
    }

    public int m() {
        return 0;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        int right;
        int left;
        int i;
        if (this.C == null) {
            return;
        }
        int i2 = 0;
        if (this.v == 1) {
            int childCount = getChildCount();
            while (i2 < childCount) {
                View childAt = getChildAt(i2);
                if (childAt != null && childAt.getVisibility() != 8 && k(i2)) {
                    a(canvas, (childAt.getTop() - ((ViewGroup.MarginLayoutParams) ((C1303p0) childAt.getLayoutParams())).topMargin) - this.E);
                }
                i2++;
            }
            if (k(childCount)) {
                View childAt2 = getChildAt(childCount - 1);
                a(canvas, childAt2 == null ? (getHeight() - getPaddingBottom()) - this.E : childAt2.getBottom() + ((ViewGroup.MarginLayoutParams) ((C1303p0) childAt2.getLayoutParams())).bottomMargin);
                return;
            }
            return;
        }
        int childCount2 = getChildCount();
        boolean zA = X0.a(this);
        while (i2 < childCount2) {
            View childAt3 = getChildAt(i2);
            if (childAt3 != null && childAt3.getVisibility() != 8 && k(i2)) {
                C1303p0 c1303p0 = (C1303p0) childAt3.getLayoutParams();
                b(canvas, zA ? childAt3.getRight() + ((ViewGroup.MarginLayoutParams) c1303p0).rightMargin : (childAt3.getLeft() - ((ViewGroup.MarginLayoutParams) c1303p0).leftMargin) - this.D);
            }
            i2++;
        }
        if (k(childCount2)) {
            View childAt4 = getChildAt(childCount2 - 1);
            if (childAt4 != null) {
                C1303p0 c1303p02 = (C1303p0) childAt4.getLayoutParams();
                if (zA) {
                    left = childAt4.getLeft() - ((ViewGroup.MarginLayoutParams) c1303p02).leftMargin;
                    i = this.D;
                    right = left - i;
                } else {
                    right = childAt4.getRight() + ((ViewGroup.MarginLayoutParams) c1303p02).rightMargin;
                }
            } else if (zA) {
                right = getPaddingLeft();
            } else {
                left = getWidth() - getPaddingRight();
                i = this.D;
                right = left - i;
            }
            b(canvas, right);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(AbstractC1358q0.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(AbstractC1358q0.class.getName());
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01b8  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onLayout(boolean r24, int r25, int r26, int r27, int r28) {
        /*
            Method dump skipped, instruction units count: 490
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC1358q0.onLayout(boolean, int, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:153:0x0315  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0322  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0489  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x048e  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x04b6  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x04bb  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x04c3  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x04d4  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x04eb  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x04fe  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x0541  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0552  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x055a  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0565  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x05f3  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x06b5  */
    /* JADX WARN: Removed duplicated region for block: B:314:0x06bc  */
    /* JADX WARN: Removed duplicated region for block: B:317:0x06d9  */
    /* JADX WARN: Removed duplicated region for block: B:367:0x07eb  */
    /* JADX WARN: Removed duplicated region for block: B:380:0x082c  */
    /* JADX WARN: Removed duplicated region for block: B:386:0x0863  */
    /* JADX WARN: Removed duplicated region for block: B:389:0x0885  */
    /* JADX WARN: Removed duplicated region for block: B:425:? A[RETURN, SYNTHETIC] */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onMeasure(int r38, int r39) {
        /*
            Method dump skipped, instruction units count: 2189
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC1358q0.onMeasure(int, int):void");
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }
}
