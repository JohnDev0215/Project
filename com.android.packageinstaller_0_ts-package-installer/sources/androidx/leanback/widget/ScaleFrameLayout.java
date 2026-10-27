package androidx.leanback.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public class ScaleFrameLayout extends FrameLayout {
    private static final int DEFAULT_CHILD_GRAVITY = 8388659;
    private float mChildScale;
    private float mLayoutScaleX;
    private float mLayoutScaleY;

    public ScaleFrameLayout(Context context) {
        this(context, null);
    }

    public ScaleFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScaleFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mLayoutScaleX = 1.0f;
        this.mLayoutScaleY = 1.0f;
        this.mChildScale = 1.0f;
    }

    public void setLayoutScaleX(float scaleX) {
        if (scaleX != this.mLayoutScaleX) {
            this.mLayoutScaleX = scaleX;
            requestLayout();
        }
    }

    public void setLayoutScaleY(float scaleY) {
        if (scaleY != this.mLayoutScaleY) {
            this.mLayoutScaleY = scaleY;
            requestLayout();
        }
    }

    public void setChildScale(float scale) {
        if (this.mChildScale != scale) {
            this.mChildScale = scale;
            for (int i = 0; i < getChildCount(); i++) {
                getChildAt(i).setScaleX(scale);
                getChildAt(i).setScaleY(scale);
            }
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        child.setScaleX(this.mChildScale);
        child.setScaleY(this.mChildScale);
    }

    @Override // android.view.ViewGroup
    protected boolean addViewInLayout(View child, int index, ViewGroup.LayoutParams params, boolean preventRequestLayout) {
        boolean ret = super.addViewInLayout(child, index, params, preventRequestLayout);
        if (ret) {
            child.setScaleX(this.mChildScale);
            child.setScaleY(this.mChildScale);
        }
        return ret;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        float pivotX;
        int parentLeft;
        int parentRight;
        int parentTop;
        int parentBottom;
        int count;
        int layoutDirection;
        int childLeft;
        int childTop;
        ScaleFrameLayout scaleFrameLayout = this;
        int count2 = getChildCount();
        int layoutDirection2 = getLayoutDirection();
        if (layoutDirection2 == 1) {
            pivotX = getWidth() - getPivotX();
        } else {
            pivotX = getPivotX();
        }
        if (scaleFrameLayout.mLayoutScaleX != 1.0f) {
            int paddingLeft = getPaddingLeft();
            float f = scaleFrameLayout.mLayoutScaleX;
            parentLeft = paddingLeft + ((int) ((pivotX - (pivotX / f)) + 0.5f));
            parentRight = ((int) (((((right - left) - pivotX) / f) + pivotX) + 0.5f)) - getPaddingRight();
        } else {
            parentLeft = getPaddingLeft();
            parentRight = (right - left) - getPaddingRight();
        }
        float pivotY = getPivotY();
        if (scaleFrameLayout.mLayoutScaleY != 1.0f) {
            int paddingTop = getPaddingTop();
            float f2 = scaleFrameLayout.mLayoutScaleY;
            parentTop = paddingTop + ((int) ((pivotY - (pivotY / f2)) + 0.5f));
            parentBottom = ((int) (((((bottom - top) - pivotY) / f2) + pivotY) + 0.5f)) - getPaddingBottom();
        } else {
            parentTop = getPaddingTop();
            parentBottom = (bottom - top) - getPaddingBottom();
        }
        int i = 0;
        while (i < count2) {
            View child = scaleFrameLayout.getChildAt(i);
            if (child.getVisibility() == 8) {
                count = count2;
                layoutDirection = layoutDirection2;
            } else {
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) child.getLayoutParams();
                int width = child.getMeasuredWidth();
                int height = child.getMeasuredHeight();
                int gravity = lp.gravity;
                if (gravity == -1) {
                    gravity = DEFAULT_CHILD_GRAVITY;
                }
                int absoluteGravity = Gravity.getAbsoluteGravity(gravity, layoutDirection2);
                int verticalGravity = gravity & 112;
                count = count2;
                int count3 = absoluteGravity & 7;
                layoutDirection = layoutDirection2;
                if (count3 == 1) {
                    int childLeft2 = parentRight - parentLeft;
                    childLeft = ((((childLeft2 - width) / 2) + parentLeft) + lp.leftMargin) - lp.rightMargin;
                } else if (count3 == 5) {
                    int childLeft3 = parentRight - width;
                    childLeft = childLeft3 - lp.rightMargin;
                } else {
                    childLeft = lp.leftMargin + parentLeft;
                }
                if (verticalGravity == 16) {
                    childTop = (((((parentBottom - parentTop) - height) / 2) + parentTop) + lp.topMargin) - lp.bottomMargin;
                } else if (verticalGravity == 48) {
                    childTop = parentTop + lp.topMargin;
                } else if (verticalGravity == 80) {
                    int childTop2 = parentBottom - height;
                    childTop = childTop2 - lp.bottomMargin;
                } else {
                    childTop = lp.topMargin + parentTop;
                }
                int absoluteGravity2 = childTop + height;
                child.layout(childLeft, childTop, childLeft + width, absoluteGravity2);
                child.setPivotX(pivotX - childLeft);
                child.setPivotY(pivotY - childTop);
            }
            i++;
            scaleFrameLayout = this;
            count2 = count;
            layoutDirection2 = layoutDirection;
        }
    }

    private static int getScaledMeasureSpec(int measureSpec, float scale) {
        return scale == 1.0f ? measureSpec : View.MeasureSpec.makeMeasureSpec((int) ((View.MeasureSpec.getSize(measureSpec) / scale) + 0.5f), View.MeasureSpec.getMode(measureSpec));
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.mLayoutScaleX != 1.0f || this.mLayoutScaleY != 1.0f) {
            int scaledWidthMeasureSpec = getScaledMeasureSpec(widthMeasureSpec, this.mLayoutScaleX);
            int scaledHeightMeasureSpec = getScaledMeasureSpec(heightMeasureSpec, this.mLayoutScaleY);
            super.onMeasure(scaledWidthMeasureSpec, scaledHeightMeasureSpec);
            setMeasuredDimension((int) ((getMeasuredWidth() * this.mLayoutScaleX) + 0.5f), (int) ((getMeasuredHeight() * this.mLayoutScaleY) + 0.5f));
            return;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override // android.view.View
    public void setForeground(Drawable d) {
        throw new UnsupportedOperationException();
    }
}
