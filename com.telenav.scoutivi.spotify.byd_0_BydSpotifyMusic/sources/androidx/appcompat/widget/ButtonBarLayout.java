package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/* JADX INFO: loaded from: classes.dex */
public class ButtonBarLayout extends LinearLayout {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f867e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f868g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f869h;

    public ButtonBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f868g = -1;
        this.f869h = 0;
        int[] iArr = e.j.ButtonBarLayout;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr);
        i0.u.i0(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, 0, 0);
        this.f867e = typedArrayObtainStyledAttributes.getBoolean(e.j.ButtonBarLayout_allowStacking, true);
        typedArrayObtainStyledAttributes.recycle();
    }

    private int a(int i7) {
        int childCount = getChildCount();
        while (i7 < childCount) {
            if (getChildAt(i7).getVisibility() == 0) {
                return i7;
            }
            i7++;
        }
        return -1;
    }

    private boolean b() {
        return getOrientation() == 1;
    }

    private void setStacked(boolean z6) {
        setOrientation(z6 ? 1 : 0);
        setGravity(z6 ? 8388613 : 80);
        View viewFindViewById = findViewById(e.f.spacer);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(z6 ? 8 : 4);
        }
        for (int childCount = getChildCount() - 2; childCount >= 0; childCount--) {
            bringChildToFront(getChildAt(childCount));
        }
    }

    @Override // android.view.View
    public int getMinimumHeight() {
        return Math.max(this.f869h, super.getMinimumHeight());
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i7, int i8) {
        int iMakeMeasureSpec;
        boolean z6;
        int size = View.MeasureSpec.getSize(i7);
        int paddingBottom = 0;
        if (this.f867e) {
            if (size > this.f868g && b()) {
                setStacked(false);
            }
            this.f868g = size;
        }
        if (b() || View.MeasureSpec.getMode(i7) != 1073741824) {
            iMakeMeasureSpec = i7;
            z6 = false;
        } else {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE);
            z6 = true;
        }
        super.onMeasure(iMakeMeasureSpec, i8);
        if (this.f867e && !b()) {
            if ((getMeasuredWidthAndState() & (-16777216)) == 16777216) {
                setStacked(true);
                z6 = true;
            }
        }
        if (z6) {
            super.onMeasure(i7, i8);
        }
        int iA = a(0);
        if (iA >= 0) {
            View childAt = getChildAt(iA);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            int paddingTop = getPaddingTop() + childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin + 0;
            if (b()) {
                int iA2 = a(iA + 1);
                if (iA2 >= 0) {
                    paddingTop += getChildAt(iA2).getPaddingTop() + ((int) (getResources().getDisplayMetrics().density * 16.0f));
                }
                paddingBottom = paddingTop;
            } else {
                paddingBottom = paddingTop + getPaddingBottom();
            }
        }
        if (i0.u.z(this) != paddingBottom) {
            setMinimumHeight(paddingBottom);
        }
    }

    public void setAllowStacking(boolean z6) {
        if (this.f867e != z6) {
            this.f867e = z6;
            if (!z6 && getOrientation() == 1) {
                setStacked(false);
            }
            requestLayout();
        }
    }
}
