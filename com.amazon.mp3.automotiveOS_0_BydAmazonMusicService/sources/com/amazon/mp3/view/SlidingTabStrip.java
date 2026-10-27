package com.amazon.mp3.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.view.SlidingTabLayout;

/* JADX INFO: loaded from: classes4.dex */
class SlidingTabStrip extends LinearLayout {
    private SlidingTabLayout.TabColorizer mCustomTabColorizer;
    private final SimpleTabColorizer mDefaultTabColorizer;
    private int mSelectedIndicatorColor;
    private final Paint mSelectedIndicatorPaint;
    private final int mSelectedIndicatorThickness;
    private float mSelectedIndicatorThicknessDips;
    private int mSelectedPosition;
    private float mSelectionOffset;

    SlidingTabStrip(Context context) {
        this(context, null);
    }

    SlidingTabStrip(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        this.mSelectedIndicatorColor = getResources().getColor(R.color.prime_tabs_active_indicator_color);
        this.mSelectedIndicatorThicknessDips = getResources().getDimension(R.dimen.prime_tab_indicator_thickness);
        setGravity(8388611);
        setPadding((int) getResources().getDimension(R.dimen.tab_strip_padding), 0, 0, 0);
        SimpleTabColorizer simpleTabColorizer = new SimpleTabColorizer();
        this.mDefaultTabColorizer = simpleTabColorizer;
        simpleTabColorizer.setIndicatorColors(this.mSelectedIndicatorColor);
        this.mSelectedIndicatorThickness = (int) this.mSelectedIndicatorThicknessDips;
        this.mSelectedIndicatorPaint = new Paint();
    }

    void setCustomTabColorizer(SlidingTabLayout.TabColorizer customTabColorizer) {
        this.mCustomTabColorizer = customTabColorizer;
        invalidate();
    }

    void setSelectedIndicatorColors(int... colors) {
        this.mCustomTabColorizer = null;
        this.mDefaultTabColorizer.setIndicatorColors(colors);
        invalidate();
    }

    void onViewPagerPageChanged(int position, float positionOffset) {
        this.mSelectedPosition = position;
        this.mSelectionOffset = positionOffset;
        invalidate();
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onDraw(Canvas canvas) {
        int height = getHeight();
        int childCount = getChildCount();
        SlidingTabLayout.TabColorizer tabColorizer = this.mCustomTabColorizer;
        if (tabColorizer == null) {
            tabColorizer = this.mDefaultTabColorizer;
        }
        if (childCount > 0) {
            View childAt = getChildAt(this.mSelectedPosition);
            int left = childAt.getLeft();
            int right = childAt.getRight();
            int indicatorColor = tabColorizer.getIndicatorColor(this.mSelectedPosition);
            if (this.mSelectionOffset > 0.0f && this.mSelectedPosition < getChildCount() - 1) {
                int indicatorColor2 = tabColorizer.getIndicatorColor(this.mSelectedPosition + 1);
                if (indicatorColor != indicatorColor2) {
                    indicatorColor = blendColors(indicatorColor2, indicatorColor, this.mSelectionOffset);
                }
                View childAt2 = getChildAt(this.mSelectedPosition + 1);
                float left2 = this.mSelectionOffset * childAt2.getLeft();
                float f = this.mSelectionOffset;
                left = (int) (left2 + ((1.0f - f) * left));
                right = (int) ((f * childAt2.getRight()) + ((1.0f - this.mSelectionOffset) * right));
            }
            this.mSelectedIndicatorPaint.setColor(indicatorColor);
            canvas.drawRect(left, height - this.mSelectedIndicatorThickness, right, height, this.mSelectedIndicatorPaint);
        }
    }

    private static int blendColors(int color1, int color2, float ratio) {
        float f = 1.0f - ratio;
        return Color.rgb((int) ((Color.red(color1) * ratio) + (Color.red(color2) * f)), (int) ((Color.green(color1) * ratio) + (Color.green(color2) * f)), (int) ((Color.blue(color1) * ratio) + (Color.blue(color2) * f)));
    }

    private static class SimpleTabColorizer implements SlidingTabLayout.TabColorizer {
        private int[] mIndicatorColors;

        private SimpleTabColorizer() {
        }

        @Override // com.amazon.mp3.view.SlidingTabLayout.TabColorizer
        public final int getIndicatorColor(int position) {
            int[] iArr = this.mIndicatorColors;
            return iArr[position % iArr.length];
        }

        void setIndicatorColors(int... colors) {
            this.mIndicatorColors = colors;
        }
    }

    public int getSelectedPosition() {
        return this.mSelectedPosition;
    }
}
