package androidx.appcompat.widget;

import F1.E;
import F1.H;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.stingray.drivemodedetector.R;
import n.AbstractC1850a;

/* JADX INFO: loaded from: classes.dex */
public class ButtonBarLayout extends LinearLayout {
    public boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f11341m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f11342n;

    public ButtonBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f11342n = -1;
        int[] iArr = AbstractC1850a.f19358i;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr);
        int[] iArr2 = H.f2303a;
        E.b(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, 0, 0);
        this.l = typedArrayObtainStyledAttributes.getBoolean(0, true);
        typedArrayObtainStyledAttributes.recycle();
        if (getOrientation() == 1) {
            setStacked(this.l);
        }
    }

    private void setStacked(boolean z6) {
        if (this.f11341m != z6) {
            if (!z6 || this.l) {
                this.f11341m = z6;
                setOrientation(z6 ? 1 : 0);
                setGravity(z6 ? 8388613 : 80);
                View viewFindViewById = findViewById(R.id.spacer);
                if (viewFindViewById != null) {
                    viewFindViewById.setVisibility(z6 ? 8 : 4);
                }
                for (int childCount = getChildCount() - 2; childCount >= 0; childCount--) {
                    bringChildToFront(getChildAt(childCount));
                }
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i7, int i10) {
        int iMakeMeasureSpec;
        boolean z6;
        int i11;
        int size = View.MeasureSpec.getSize(i7);
        int paddingBottom = 0;
        if (this.l) {
            if (size > this.f11342n && this.f11341m) {
                setStacked(false);
            }
            this.f11342n = size;
        }
        if (this.f11341m || View.MeasureSpec.getMode(i7) != 1073741824) {
            iMakeMeasureSpec = i7;
            z6 = false;
        } else {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE);
            z6 = true;
        }
        super.onMeasure(iMakeMeasureSpec, i10);
        if (this.l && !this.f11341m && (getMeasuredWidthAndState() & (-16777216)) == 16777216) {
            setStacked(true);
            z6 = true;
        }
        if (z6) {
            super.onMeasure(i7, i10);
        }
        int childCount = getChildCount();
        int i12 = 0;
        while (true) {
            i11 = -1;
            if (i12 >= childCount) {
                i12 = -1;
                break;
            } else if (getChildAt(i12).getVisibility() == 0) {
                break;
            } else {
                i12++;
            }
        }
        if (i12 >= 0) {
            View childAt = getChildAt(i12);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight() + getPaddingTop() + layoutParams.topMargin + layoutParams.bottomMargin;
            if (this.f11341m) {
                int i13 = i12 + 1;
                int childCount2 = getChildCount();
                while (true) {
                    if (i13 >= childCount2) {
                        break;
                    }
                    if (getChildAt(i13).getVisibility() == 0) {
                        i11 = i13;
                        break;
                    }
                    i13++;
                }
                paddingBottom = i11 >= 0 ? getChildAt(i11).getPaddingTop() + ((int) (getResources().getDisplayMetrics().density * 16.0f)) + measuredHeight : measuredHeight;
            } else {
                paddingBottom = getPaddingBottom() + measuredHeight;
            }
        }
        int[] iArr = H.f2303a;
        if (getMinimumHeight() != paddingBottom) {
            setMinimumHeight(paddingBottom);
            if (i10 == 0) {
                super.onMeasure(i7, i10);
            }
        }
    }

    public void setAllowStacking(boolean z6) {
        if (this.l != z6) {
            this.l = z6;
            if (!z6 && this.f11341m) {
                setStacked(false);
            }
            requestLayout();
        }
    }
}
