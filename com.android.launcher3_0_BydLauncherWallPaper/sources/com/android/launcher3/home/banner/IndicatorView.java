package com.android.launcher3.home.banner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.security.KeyStore;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.RelativeLayout;
import com.android.launcher3.home.Constants;
import com.android.launcher3.home.pageutil.BannerUtils;
import com.android.launcher3.home.pageutil.PreferencesUtils;
import com.android.launcher3.util.Logger;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes4.dex */
public class IndicatorView extends View implements Indicator {
    private Interpolator accelerateInterpolator;
    private final Paint indicatorPaint;
    private float indicatorRadius;
    private float indicatorRatio;
    private float indicatorSelectedRadius;
    private float indicatorSelectedRatio;
    private float indicatorSpacing;
    private int indicatorStyle;
    private final Interpolator interpolator;
    private Context mContext;
    private float offset;
    private int pagerCount;
    private RelativeLayout.LayoutParams params;
    private Path path;
    private final RectF rectF;
    private int selectedColor;
    private int selectedPage;
    private int unColor;

    @Retention(RetentionPolicy.SOURCE)
    public @interface IndicatorStyle {
        public static final int INDICATOR_BEZIER = 2;
        public static final int INDICATOR_BIG_CIRCLE = 4;
        public static final int INDICATOR_CIRCLE = 0;
        public static final int INDICATOR_CIRCLE_RECT = 1;
        public static final int INDICATOR_DASH = 3;
    }

    @Override // com.android.launcher3.home.banner.Indicator
    public View getView() {
        return this;
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int state) {
    }

    public IndicatorView(Context context) {
        this(context, null);
    }

    public IndicatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.interpolator = new DecelerateInterpolator();
        this.unColor = Color.GRAY;
        this.selectedColor = -1;
        this.indicatorRadius = dip2px(2.6f);
        this.indicatorRatio = 4.0f;
        this.indicatorSelectedRadius = dip2px(2.6f);
        this.indicatorSelectedRatio = 4.0f;
        this.indicatorSpacing = dip2px(5.0f);
        this.mContext = context;
        this.rectF = new RectF();
        this.indicatorPaint = new Paint(1);
    }

    @Override // com.android.launcher3.home.banner.Indicator
    public void initIndicatorCount(int pagerCount) {
        this.pagerCount = pagerCount;
        setVisibility(pagerCount > 1 ? 0 : 8);
        requestLayout();
    }

    @Override // com.android.launcher3.home.banner.Indicator
    public RelativeLayout.LayoutParams getParams() {
        if (this.params == null) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            this.params = layoutParams;
            layoutParams.addRule(12);
            this.params.addRule(14);
            if (BannerUtils.isLand(KeyStore.getApplicationContext())) {
                this.params.bottomMargin = dip2px(180.0f);
            } else {
                this.params.bottomMargin = dip2px(225.0f);
            }
        }
        return this.params;
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        this.selectedPage = position;
        this.offset = positionOffset;
        invalidate();
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int position) {
        PreferencesUtils.putInt(this.mContext.getApplicationContext(), Constants.LAST_SELECTED_PAGE, position, 3);
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int widthMeasureSpec) {
        int mode = View.MeasureSpec.getMode(widthMeasureSpec);
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        if (mode != Integer.MIN_VALUE && mode != 0) {
            if (mode != 1073741824) {
                return 0;
            }
            return size;
        }
        float ratioSelectedRadius = getRatioSelectedRadius();
        float ratioRadius = getRatioRadius();
        return (int) ((Math.max(ratioSelectedRadius, ratioRadius) * 2.0f * this.pagerCount) + ((r2 - 1) * this.indicatorSpacing) + (ratioSelectedRadius - ratioRadius) + getPaddingLeft() + getPaddingRight());
    }

    private int measureHeight(int heightMeasureSpec) {
        int mode = View.MeasureSpec.getMode(heightMeasureSpec);
        int size = View.MeasureSpec.getSize(heightMeasureSpec);
        if (mode == Integer.MIN_VALUE || mode == 0) {
            return (int) ((Math.max(getRatioSelectedRadius(), getRatioRadius()) * 2.0f) + getPaddingTop() + getPaddingBottom());
        }
        if (mode != 1073741824) {
            return 0;
        }
        return size;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.pagerCount == 0) {
            return;
        }
        drawDash(canvas, getHeight() / 3.0f);
    }

    private void drawCircleRect(Canvas canvas, float midY) {
        float fMax;
        float fMin;
        Logger.e("pengbin", "drawCircleRect == " + midY);
        drawPagerCountCircle(canvas, midY);
        float fIndicatorStartX = indicatorStartX(this.selectedPage);
        float ratioSelectedRadius = getRatioSelectedRadius();
        float f = fIndicatorStartX - ratioSelectedRadius;
        float f2 = fIndicatorStartX + ratioSelectedRadius;
        float fInterpolatedOffset = interpolatedOffset();
        float fMax2 = this.indicatorSpacing + (Math.max(getRatioRadius(), ratioSelectedRadius) * 2.0f);
        if ((this.selectedPage + 1) % this.pagerCount == 0) {
            float f3 = fMax2 * (-r1);
            fMax = f + Math.max(f3 * fInterpolatedOffset * 2.0f, f3);
            fMin = Math.min(f3 * (fInterpolatedOffset - 0.5f) * 2.0f, 0.0f);
        } else {
            fMax = f + Math.max((fInterpolatedOffset - 0.5f) * fMax2 * 2.0f, 0.0f);
            fMin = Math.min(fInterpolatedOffset * fMax2 * 2.0f, fMax2);
        }
        float f4 = f2 + fMin;
        RectF rectF = this.rectF;
        float f5 = this.indicatorSelectedRadius;
        rectF.set(fMax, midY - f5, f4, midY + f5);
        this.indicatorPaint.setColor(this.selectedColor);
        RectF rectF2 = this.rectF;
        float f6 = this.indicatorSelectedRadius;
        canvas.drawRoundRect(rectF2, f6, f6, this.indicatorPaint);
    }

    private void drawBezier(Canvas canvas, float midY) {
        Logger.e("pengbin", "drawBezier == " + midY);
        drawPagerCountCircle(canvas, midY);
        if (this.path == null) {
            this.path = new Path();
        }
        if (this.accelerateInterpolator == null) {
            this.accelerateInterpolator = new AccelerateInterpolator();
        }
        float fIndicatorStartX = indicatorStartX(this.selectedPage);
        float fIndicatorStartX2 = indicatorStartX((this.selectedPage + 1) % this.pagerCount) - fIndicatorStartX;
        float interpolation = (this.accelerateInterpolator.getInterpolation(this.offset) * fIndicatorStartX2) + fIndicatorStartX;
        float fInterpolatedOffset = fIndicatorStartX + (fIndicatorStartX2 * interpolatedOffset());
        float ratioSelectedRadius = getRatioSelectedRadius();
        float f = this.indicatorSelectedRadius * 0.57f;
        float f2 = this.indicatorSelectedRatio * f;
        float fInterpolatedOffset2 = ((f2 - ratioSelectedRadius) * interpolatedOffset()) + ratioSelectedRadius;
        float interpolation2 = f2 + ((ratioSelectedRadius - f2) * this.accelerateInterpolator.getInterpolation(this.offset));
        float fInterpolatedOffset3 = (this.indicatorSelectedRadius - f) * interpolatedOffset();
        float interpolation3 = (this.indicatorSelectedRadius - f) * this.accelerateInterpolator.getInterpolation(this.offset);
        this.indicatorPaint.setColor(this.selectedColor);
        float f3 = this.indicatorSelectedRadius;
        this.rectF.set(interpolation - fInterpolatedOffset2, (midY - f3) + fInterpolatedOffset3, interpolation + fInterpolatedOffset2, (f3 + midY) - fInterpolatedOffset3);
        canvas.drawRoundRect(this.rectF, fInterpolatedOffset2, fInterpolatedOffset2, this.indicatorPaint);
        float f4 = (midY - f) - interpolation3;
        float f5 = f + midY + interpolation3;
        this.rectF.set(fInterpolatedOffset - interpolation2, f4, fInterpolatedOffset + interpolation2, f5);
        canvas.drawRoundRect(this.rectF, interpolation2, interpolation2, this.indicatorPaint);
        this.path.reset();
        this.path.moveTo(fInterpolatedOffset, midY);
        this.path.lineTo(fInterpolatedOffset, f4);
        float f6 = ((interpolation - fInterpolatedOffset) / 2.0f) + fInterpolatedOffset;
        this.path.quadTo(f6, midY, interpolation, (midY - this.indicatorSelectedRadius) + fInterpolatedOffset3);
        this.path.lineTo(interpolation, (this.indicatorSelectedRadius + midY) - fInterpolatedOffset3);
        this.path.quadTo(f6, midY, fInterpolatedOffset, f5);
        this.path.close();
        canvas.drawPath(this.path, this.indicatorPaint);
    }

    private void drawCircle(Canvas canvas, float midY) {
        drawPagerCountCircle(canvas, midY);
        Logger.e("pengbin", "drawCircle == " + this.selectedPage + " == " + interpolatedOffset());
        float fIndicatorStartX = indicatorStartX(this.selectedPage);
        float fIndicatorStartX2 = indicatorStartX((this.selectedPage + 1) % this.pagerCount);
        float ratioSelectedRadius = getRatioSelectedRadius();
        float f = fIndicatorStartX - ratioSelectedRadius;
        float f2 = fIndicatorStartX + ratioSelectedRadius;
        float fInterpolatedOffset = f + (((fIndicatorStartX2 - ratioSelectedRadius) - f) * interpolatedOffset());
        float fInterpolatedOffset2 = f2 + (((fIndicatorStartX2 + ratioSelectedRadius) - f2) * interpolatedOffset());
        RectF rectF = this.rectF;
        float f3 = this.indicatorSelectedRadius;
        rectF.set(fInterpolatedOffset, midY - f3, fInterpolatedOffset2, midY + f3);
        this.indicatorPaint.setColor(this.selectedColor);
        RectF rectF2 = this.rectF;
        float f4 = this.indicatorSelectedRadius;
        canvas.drawRoundRect(rectF2, f4, f4, this.indicatorPaint);
    }

    private void drawDash(Canvas canvas, float midY) {
        float fInterpolatedOffset = interpolatedOffset();
        float ratioSelectedRadius = getRatioSelectedRadius();
        float ratioRadius = getRatioRadius();
        float f = ratioSelectedRadius - ratioRadius;
        float f2 = f * fInterpolatedOffset;
        int i = (this.selectedPage + 1) % this.pagerCount;
        boolean z = i == 0;
        this.indicatorPaint.setColor(this.unColor);
        for (int i2 = 0; i2 < this.pagerCount; i2++) {
            float fIndicatorStartX = indicatorStartX(i2);
            if (z) {
                fIndicatorStartX += f2;
            }
            float f3 = fIndicatorStartX - ratioRadius;
            float f4 = this.indicatorRadius;
            float f5 = midY - f4;
            float f6 = fIndicatorStartX + ratioRadius;
            float f7 = midY + f4;
            if (this.selectedPage + 1 <= i2) {
                this.rectF.set(f3 + f, f5, f6 + f, f7);
            } else {
                this.rectF.set(f3, f5, f6, f7);
            }
            RectF rectF = this.rectF;
            float f8 = this.indicatorRadius;
            canvas.drawRoundRect(rectF, f8, f8, this.indicatorPaint);
        }
        this.indicatorPaint.setColor(this.selectedColor);
        if (fInterpolatedOffset < 0.99f) {
            float fIndicatorStartX2 = indicatorStartX(this.selectedPage) - ratioSelectedRadius;
            if (z) {
                fIndicatorStartX2 += f2;
            }
            RectF rectF2 = this.rectF;
            float f9 = this.indicatorSelectedRadius;
            rectF2.set(fIndicatorStartX2, midY - f9, (((ratioSelectedRadius * 2.0f) + fIndicatorStartX2) + f) - f2, midY + f9);
            RectF rectF3 = this.rectF;
            float f10 = this.indicatorSelectedRadius;
            canvas.drawRoundRect(rectF3, f10, f10, this.indicatorPaint);
        }
        if (fInterpolatedOffset > 0.99f) {
            float fIndicatorStartX3 = indicatorStartX(i) + ratioSelectedRadius;
            if (z) {
                f = f2;
            }
            float f11 = fIndicatorStartX3 + f;
            RectF rectF4 = this.rectF;
            float f12 = this.indicatorSelectedRadius;
            rectF4.set((f11 - (ratioSelectedRadius * 2.0f)) - f2, midY - f12, f11, midY + f12);
            RectF rectF5 = this.rectF;
            float f13 = this.indicatorSelectedRadius;
            canvas.drawRoundRect(rectF5, f13, f13, this.indicatorPaint);
        }
    }

    private void drawBigCircle(Canvas canvas, float midY) {
        drawPagerCountCircle(canvas, midY);
        float fInterpolatedOffset = interpolatedOffset();
        float fIndicatorStartX = indicatorStartX(this.selectedPage);
        float fIndicatorStartX2 = indicatorStartX((this.selectedPage + 1) % this.pagerCount);
        float ratioRadius = getRatioRadius();
        float f = this.indicatorSelectedRadius;
        float f2 = this.indicatorSelectedRatio * f;
        float f3 = (f2 - ratioRadius) * fInterpolatedOffset;
        float f4 = f2 - f3;
        float f5 = ratioRadius + f3;
        float f6 = (f - this.indicatorRadius) * fInterpolatedOffset;
        this.indicatorPaint.setColor(this.selectedColor);
        if (fInterpolatedOffset < 0.99f) {
            RectF rectF = this.rectF;
            rectF.set(fIndicatorStartX - f4, (midY - f) + f6, fIndicatorStartX + f4, (f + midY) - f6);
            canvas.drawRoundRect(this.rectF, f4, f4, this.indicatorPaint);
        }
        if (fInterpolatedOffset > 0.1f) {
            float f7 = this.indicatorRadius;
            float f8 = midY + f7 + f6;
            RectF rectF2 = this.rectF;
            rectF2.set(fIndicatorStartX2 - f5, (midY - f7) - f6, fIndicatorStartX2 + f5, f8);
            canvas.drawRoundRect(this.rectF, f5, f5, this.indicatorPaint);
        }
    }

    private void drawPagerCountCircle(Canvas canvas, float midY) {
        this.indicatorPaint.setColor(this.unColor);
        for (int i = 0; i < this.pagerCount; i++) {
            float fIndicatorStartX = indicatorStartX(i);
            float ratioRadius = getRatioRadius();
            float f = this.indicatorRadius;
            this.rectF.set(fIndicatorStartX - ratioRadius, midY - f, fIndicatorStartX + ratioRadius, f + midY);
            RectF rectF = this.rectF;
            float f2 = this.indicatorRadius;
            canvas.drawRoundRect(rectF, f2, f2, this.indicatorPaint);
        }
    }

    private float indicatorStartX(int index) {
        float ratioRadius = getRatioRadius();
        float fMax = Math.max(ratioRadius, getRatioSelectedRadius());
        return getPaddingLeft() + fMax + (((fMax * 2.0f) + this.indicatorSpacing) * index) + (this.indicatorStyle == 3 ? 0.0f : (fMax - ratioRadius) / 2.0f);
    }

    private float getRatioRadius() {
        return this.indicatorRadius * this.indicatorRatio;
    }

    private float getRatioSelectedRadius() {
        return this.indicatorSelectedRadius * this.indicatorSelectedRatio;
    }

    private float interpolatedOffset() {
        return this.interpolator.getInterpolation(this.offset);
    }

    private int dip2px(float dp) {
        return (int) (dp * getContext().getResources().getDisplayMetrics().density);
    }

    public IndicatorView setIndicatorRadius(float indicatorRadius) {
        int iDip2px = dip2px(indicatorRadius);
        if (this.indicatorRadius == this.indicatorSelectedRadius) {
            this.indicatorSelectedRadius = iDip2px;
        }
        this.indicatorRadius = iDip2px;
        return this;
    }

    public IndicatorView setIndicatorRatio(float indicatorRatio) {
        if (this.indicatorRatio == this.indicatorSelectedRatio) {
            this.indicatorSelectedRatio = indicatorRatio;
        }
        this.indicatorRatio = indicatorRatio;
        return this;
    }

    public IndicatorView setIndicatorSelectedRadius(float indicatorSelectedRadius) {
        this.indicatorSelectedRadius = dip2px(indicatorSelectedRadius);
        return this;
    }

    public IndicatorView setIndicatorSelectedRatio(float indicatorSelectedRatio) {
        this.indicatorSelectedRatio = indicatorSelectedRatio;
        return this;
    }

    public IndicatorView setIndicatorSpacing(float indicatorSpacing) {
        this.indicatorSpacing = dip2px(indicatorSpacing);
        return this;
    }

    public IndicatorView setIndicatorStyle(int indicatorStyle) {
        this.indicatorStyle = indicatorStyle;
        return this;
    }

    public IndicatorView setIndicatorColor(int indicatorColor) {
        this.unColor = indicatorColor;
        return this;
    }

    public IndicatorView setIndicatorSelectorColor(int indicatorSelectorColor) {
        this.selectedColor = indicatorSelectorColor;
        return this;
    }

    public IndicatorView setParams(RelativeLayout.LayoutParams params) {
        this.params = params;
        return this;
    }
}
