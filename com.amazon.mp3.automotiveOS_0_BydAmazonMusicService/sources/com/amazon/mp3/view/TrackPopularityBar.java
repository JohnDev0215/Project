package com.amazon.mp3.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.amazon.mp3.R$styleable;
import com.amazon.mp3.automotiveOS.R;

/* JADX INFO: loaded from: classes4.dex */
public class TrackPopularityBar extends View {
    private int mBarActiveColor;
    private float mBarHeight;
    private int mBarInactiveColor;
    private int mBarTransparentColor;
    private float mBarWidth;
    private Context mContext;
    private Paint mPaint;
    private double mPopularity;
    private int mTotalBarCount;

    public TrackPopularityBar(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        this.mPopularity = -1.0d;
        init(context, attrs);
    }

    public void init(final Context context, @Nullable final AttributeSet attrs) {
        this.mContext = context;
        initAttrs(attrs);
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        this.mPaint.setStrokeWidth(0.0f);
    }

    private void initAttrs(@Nullable final AttributeSet attrs) {
        TypedArray typedArrayObtainStyledAttributes = this.mContext.getTheme().obtainStyledAttributes(attrs, R$styleable.TrackPopularityBar, 0, 0);
        this.mBarWidth = typedArrayObtainStyledAttributes.getDimension(2, getResources().getDimension(R.dimen.spacing_2dp));
        this.mBarHeight = typedArrayObtainStyledAttributes.getDimension(1, getResources().getDimension(R.dimen.spacing_8dp));
        this.mBarActiveColor = typedArrayObtainStyledAttributes.getColor(0, getResources().getColor(R.color.vivace_blue));
        this.mBarInactiveColor = typedArrayObtainStyledAttributes.getColor(3, getResources().getColor(R.color.dark_grey));
        this.mBarTransparentColor = getResources().getColor(R.color.transparent);
        this.mTotalBarCount = typedArrayObtainStyledAttributes.getInteger(4, 15);
    }

    @Override // android.view.View
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        float f = 0.0f;
        for (int i = 0; i < this.mTotalBarCount; i++) {
            this.mPaint.setColor(((double) i) < this.mPopularity ? this.mBarActiveColor : this.mBarInactiveColor);
            canvas.drawRect(f, 0.0f, f + this.mBarWidth, this.mBarHeight, this.mPaint);
            f += this.mBarWidth;
            if (i < this.mTotalBarCount - 1) {
                this.mPaint.setColor(getResources().getColor(R.color.transparent));
                canvas.drawRect(f, 0.0f, f + this.mBarWidth, this.mBarHeight, this.mPaint);
                f += this.mBarWidth;
            }
        }
    }

    public TrackPopularityBar setPopularity(final double popularity) {
        this.mPopularity = (int) Math.ceil(popularity * ((double) this.mTotalBarCount));
        setContentDescription("" + this.mPopularity);
        return this;
    }

    public boolean show() {
        if (this.mPopularity <= -1.0d) {
            return false;
        }
        if (getVisibility() != 0) {
            setVisibility(0);
        }
        invalidate();
        return true;
    }

    public void setTotalBarCount(final int totalBarCount) {
        this.mTotalBarCount = totalBarCount;
    }

    public void setBarWidth(final int barWidth) {
        this.mBarWidth = getResources().getDimension(barWidth);
    }

    public void setBarHeight(final int barHeight) {
        this.mBarHeight = getResources().getDimension(barHeight);
    }

    public void setBarActiveColor(final int barActiveColor) {
        this.mBarActiveColor = getResources().getColor(barActiveColor);
    }

    public void setBarInactiveColor(final int barInactiveColor) {
        this.mBarInactiveColor = getResources().getColor(barInactiveColor);
    }
}
