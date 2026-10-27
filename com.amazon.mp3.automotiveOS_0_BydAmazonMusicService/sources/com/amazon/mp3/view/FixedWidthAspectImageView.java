package com.amazon.mp3.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.amazon.mp3.R$styleable;

/* JADX INFO: loaded from: classes4.dex */
public class FixedWidthAspectImageView extends ImageView {
    private float mSquareHeightScalingFactor;

    public FixedWidthAspectImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mSquareHeightScalingFactor = 1.0f;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray typedArrayObtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attrs, R$styleable.FixedWidthAspectImageView, 0, 0);
        try {
            this.mSquareHeightScalingFactor = typedArrayObtainStyledAttributes.getFloat(0, 1.0f);
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        setMeasuredDimension(measuredWidth, (int) (measuredWidth * this.mSquareHeightScalingFactor));
    }

    public void setSquareHeightScalingFactor(float squareHeightScalingFactor) {
        this.mSquareHeightScalingFactor = squareHeightScalingFactor;
    }
}
