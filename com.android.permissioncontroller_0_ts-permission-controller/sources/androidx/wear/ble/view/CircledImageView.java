package androidx.wear.ble.view;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import androidx.core.view.ViewCompat;
import com.android.permissioncontroller.R;
import java.util.Objects;

/* JADX INFO: loaded from: classes7.dex */
public class CircledImageView extends View {
    private static final ArgbEvaluator ARGB_EVALUATOR = new ArgbEvaluator();
    private final ValueAnimator.AnimatorUpdateListener mAnimationListener;
    private int mCircleBorderColor;
    private float mCircleBorderWidth;
    private ColorStateList mCircleColor;
    private boolean mCircleHidden;
    private float mCircleRadius;
    private float mCircleRadiusPercent;
    private float mCircleRadiusPressed;
    private float mCircleRadiusPressedPercent;
    private ValueAnimator mColorAnimator;
    private long mColorChangeAnimationDurationMs;
    private int mCurrentColor;
    private Drawable mDrawable;
    private final Drawable.Callback mDrawableCallback;
    private float mImageCirclePercentage;
    private float mImageHorizontalOffcenterPercentage;
    private Integer mImageTint;
    private Rect mIndeterminateBounds;
    private ProgressDrawable mIndeterminateDrawable;
    private float mInitialCircleRadius;
    private final RectF mOval;
    private final Paint mPaint;
    private boolean mPressed;
    private float mProgress;
    private boolean mProgressIndeterminate;
    private float mRadiusInset;
    private float mShadowVisibility;
    private final float mShadowWidth;

    public CircledImageView(Context context) {
        this(context, null);
    }

    public CircledImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircledImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mProgress = 1.0f;
        this.mCircleHidden = false;
        this.mPressed = false;
        this.mIndeterminateBounds = new Rect();
        this.mColorChangeAnimationDurationMs = 0L;
        this.mImageCirclePercentage = 1.0f;
        this.mImageHorizontalOffcenterPercentage = 0.0f;
        this.mDrawableCallback = new Drawable.Callback() { // from class: androidx.wear.ble.view.CircledImageView.1
            @Override // android.graphics.drawable.Drawable.Callback
            public void invalidateDrawable(Drawable drawable) {
                CircledImageView.this.invalidate();
            }

            @Override // android.graphics.drawable.Drawable.Callback
            public void scheduleDrawable(Drawable drawable, Runnable runnable, long l) {
            }

            @Override // android.graphics.drawable.Drawable.Callback
            public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            }
        };
        this.mAnimationListener = new ValueAnimator.AnimatorUpdateListener() { // from class: androidx.wear.ble.view.CircledImageView.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                int color = ((Integer) animation.getAnimatedValue()).intValue();
                if (color != CircledImageView.this.mCurrentColor) {
                    CircledImageView.this.mCurrentColor = color;
                    CircledImageView.this.invalidate();
                }
            }
        };
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CircledImageView);
        this.mDrawable = a.getDrawable(0);
        ColorStateList colorStateList = a.getColorStateList(3);
        this.mCircleColor = colorStateList;
        if (colorStateList == null) {
            this.mCircleColor = ColorStateList.valueOf(android.R.color.darker_gray);
        }
        float dimension = a.getDimension(5, 0.0f);
        this.mCircleRadius = dimension;
        this.mInitialCircleRadius = dimension;
        this.mCircleRadiusPressed = a.getDimension(7, dimension);
        this.mCircleBorderColor = a.getColor(1, ViewCompat.MEASURED_STATE_MASK);
        float dimension2 = a.getDimension(2, 0.0f);
        this.mCircleBorderWidth = dimension2;
        if (dimension2 > 0.0f) {
            this.mRadiusInset += dimension2;
        }
        float circlePadding = a.getDimension(4, 0.0f);
        if (circlePadding > 0.0f) {
            this.mRadiusInset += circlePadding;
        }
        this.mShadowWidth = a.getDimension(12, 0.0f);
        this.mImageCirclePercentage = a.getFloat(9, 0.0f);
        this.mImageHorizontalOffcenterPercentage = a.getFloat(10, 0.0f);
        if (a.hasValue(11)) {
            this.mImageTint = Integer.valueOf(a.getColor(11, 0));
        }
        float fraction = a.getFraction(6, 1, 1, 0.0f);
        this.mCircleRadiusPercent = fraction;
        this.mCircleRadiusPressedPercent = a.getFraction(8, 1, 1, fraction);
        a.recycle();
        this.mOval = new RectF();
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        ProgressDrawable progressDrawable = new ProgressDrawable();
        this.mIndeterminateDrawable = progressDrawable;
        progressDrawable.setCallback(this.mDrawableCallback);
        setWillNotDraw(false);
        setColorForCurrentState();
    }

    public void setCircleHidden(boolean circleHidden) {
        if (circleHidden != this.mCircleHidden) {
            this.mCircleHidden = circleHidden;
            invalidate();
        }
    }

    @Override // android.view.View
    protected boolean onSetAlpha(int alpha) {
        return true;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        float circleRadius = this.mPressed ? getCircleRadiusPressed() : getCircleRadius();
        if (this.mShadowWidth > 0.0f && this.mShadowVisibility > 0.0f) {
            this.mOval.set(paddingLeft, paddingTop, getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
            float radius = this.mCircleBorderWidth + circleRadius + (this.mShadowWidth * this.mShadowVisibility);
            this.mPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.mPaint.setAlpha(Math.round(r5.getAlpha() * getAlpha()));
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setShader(new RadialGradient(this.mOval.centerX(), this.mOval.centerY(), radius, new int[]{ViewCompat.MEASURED_STATE_MASK, 0}, new float[]{0.6f, 1.0f}, Shader.TileMode.MIRROR));
            canvas.drawCircle(this.mOval.centerX(), this.mOval.centerY(), radius, this.mPaint);
            this.mPaint.setShader(null);
        }
        if (this.mCircleBorderWidth > 0.0f) {
            this.mOval.set(paddingLeft, paddingTop, getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
            RectF rectF = this.mOval;
            rectF.set(rectF.centerX() - circleRadius, this.mOval.centerY() - circleRadius, this.mOval.centerX() + circleRadius, this.mOval.centerY() + circleRadius);
            this.mPaint.setColor(this.mCircleBorderColor);
            this.mPaint.setAlpha(Math.round(r3.getAlpha() * getAlpha()));
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPaint.setStrokeWidth(this.mCircleBorderWidth);
            if (this.mProgressIndeterminate) {
                this.mOval.roundOut(this.mIndeterminateBounds);
                this.mIndeterminateDrawable.setBounds(this.mIndeterminateBounds);
                this.mIndeterminateDrawable.setRingColor(this.mCircleBorderColor);
                this.mIndeterminateDrawable.setRingWidth(this.mCircleBorderWidth);
                this.mIndeterminateDrawable.draw(canvas);
            } else {
                canvas.drawArc(this.mOval, -90.0f, this.mProgress * 360.0f, false, this.mPaint);
            }
        }
        if (!this.mCircleHidden) {
            this.mOval.set(paddingLeft, paddingTop, getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
            this.mPaint.setColor(this.mCurrentColor);
            this.mPaint.setAlpha(Math.round(r3.getAlpha() * getAlpha()));
            this.mPaint.setStyle(Paint.Style.FILL);
            float centerX = this.mOval.centerX();
            float centerY = this.mOval.centerY();
            canvas.drawCircle(centerX, centerY, circleRadius, this.mPaint);
        }
        Drawable drawable = this.mDrawable;
        if (drawable != null) {
            drawable.setAlpha(Math.round(getAlpha() * 255.0f));
            Integer num = this.mImageTint;
            if (num != null) {
                this.mDrawable.setTint(num.intValue());
            }
            this.mDrawable.draw(canvas);
        }
        super.onDraw(canvas);
    }

    private void setColorForCurrentState() {
        int newColor = this.mCircleColor.getColorForState(getDrawableState(), this.mCircleColor.getDefaultColor());
        if (this.mColorChangeAnimationDurationMs > 0) {
            ValueAnimator valueAnimator = this.mColorAnimator;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            } else {
                this.mColorAnimator = new ValueAnimator();
            }
            this.mColorAnimator.setIntValues(this.mCurrentColor, newColor);
            this.mColorAnimator.setEvaluator(ARGB_EVALUATOR);
            this.mColorAnimator.setDuration(this.mColorChangeAnimationDurationMs);
            this.mColorAnimator.addUpdateListener(this.mAnimationListener);
            this.mColorAnimator.start();
            return;
        }
        if (newColor != this.mCurrentColor) {
            this.mCurrentColor = newColor;
            invalidate();
        }
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width;
        int height;
        float radius = getCircleRadius() + this.mCircleBorderWidth + (this.mShadowWidth * this.mShadowVisibility);
        float desiredWidth = radius * 2.0f;
        float desiredHeight = 2.0f * radius;
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == 1073741824) {
            width = widthSize;
        } else if (widthMode == Integer.MIN_VALUE) {
            width = (int) Math.min(desiredWidth, widthSize);
        } else {
            width = (int) desiredWidth;
        }
        if (heightMode == 1073741824) {
            height = heightSize;
        } else if (heightMode == Integer.MIN_VALUE) {
            height = (int) Math.min(desiredHeight, heightSize);
        } else {
            height = (int) desiredHeight;
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(width, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(height, BasicMeasure.EXACTLY));
    }

    @Override // android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Drawable drawable = this.mDrawable;
        if (drawable != null) {
            int nativeDrawableWidth = drawable.getIntrinsicWidth();
            int nativeDrawableHeight = this.mDrawable.getIntrinsicHeight();
            int viewWidth = getMeasuredWidth();
            int viewHeight = getMeasuredHeight();
            float imageCirclePercentage = this.mImageCirclePercentage;
            if (imageCirclePercentage <= 0.0f) {
                imageCirclePercentage = 1.0f;
            }
            float scaleFactor = Math.min(1.0f, Math.min(((float) nativeDrawableWidth) != 0.0f ? (viewWidth * imageCirclePercentage) / nativeDrawableWidth : 1.0f, ((float) nativeDrawableHeight) != 0.0f ? (viewHeight * imageCirclePercentage) / nativeDrawableHeight : 1.0f));
            int drawableWidth = Math.round(nativeDrawableWidth * scaleFactor);
            int drawableHeight = Math.round(nativeDrawableHeight * scaleFactor);
            int drawableLeft = ((viewWidth - drawableWidth) / 2) + Math.round(this.mImageHorizontalOffcenterPercentage * drawableWidth);
            int drawableTop = (viewHeight - drawableHeight) / 2;
            this.mDrawable.setBounds(drawableLeft, drawableTop, drawableLeft + drawableWidth, drawableTop + drawableHeight);
        }
        super.onLayout(changed, left, top, right, bottom);
    }

    public void setImageDrawable(Drawable drawable) {
        if (drawable != this.mDrawable) {
            Drawable existingDrawable = this.mDrawable;
            this.mDrawable = drawable;
            boolean skipLayout = drawable != null && existingDrawable != null && existingDrawable.getIntrinsicHeight() == drawable.getIntrinsicHeight() && existingDrawable.getIntrinsicWidth() == drawable.getIntrinsicWidth();
            if (skipLayout) {
                this.mDrawable.setBounds(existingDrawable.getBounds());
            } else {
                requestLayout();
            }
            invalidate();
        }
    }

    public void setImageResource(int resId) {
        setImageDrawable(resId == 0 ? null : getContext().getDrawable(resId));
    }

    public void setImageCirclePercentage(float percentage) {
        float clamped = Math.max(0.0f, Math.min(1.0f, percentage));
        if (clamped != this.mImageCirclePercentage) {
            this.mImageCirclePercentage = clamped;
            invalidate();
        }
    }

    public void setImageHorizontalOffcenterPercentage(float percentage) {
        if (percentage != this.mImageHorizontalOffcenterPercentage) {
            this.mImageHorizontalOffcenterPercentage = percentage;
            invalidate();
        }
    }

    public void setImageTint(int tint) {
        if (tint != this.mImageTint.intValue()) {
            this.mImageTint = Integer.valueOf(tint);
            invalidate();
        }
    }

    public float getCircleRadius() {
        float radius = this.mCircleRadius;
        if (this.mCircleRadius <= 0.0f && this.mCircleRadiusPercent > 0.0f) {
            radius = Math.max(getMeasuredHeight(), getMeasuredWidth()) * this.mCircleRadiusPercent;
        }
        return radius - this.mRadiusInset;
    }

    public float getCircleRadiusPercent() {
        return this.mCircleRadiusPercent;
    }

    public float getCircleRadiusPressed() {
        float radius = this.mCircleRadiusPressed;
        if (this.mCircleRadiusPressed <= 0.0f && this.mCircleRadiusPressedPercent > 0.0f) {
            radius = Math.max(getMeasuredHeight(), getMeasuredWidth()) * this.mCircleRadiusPressedPercent;
        }
        return radius - this.mRadiusInset;
    }

    public float getCircleRadiusPressedPercent() {
        return this.mCircleRadiusPressedPercent;
    }

    public void setCircleRadius(float circleRadius) {
        if (circleRadius != this.mCircleRadius) {
            this.mCircleRadius = circleRadius;
            invalidate();
        }
    }

    public void setCircleRadiusPercent(float circleRadiusPercent) {
        if (circleRadiusPercent != this.mCircleRadiusPercent) {
            this.mCircleRadiusPercent = circleRadiusPercent;
            invalidate();
        }
    }

    public void setCircleRadiusPressed(float circleRadiusPressed) {
        if (circleRadiusPressed != this.mCircleRadiusPressed) {
            this.mCircleRadiusPressed = circleRadiusPressed;
            invalidate();
        }
    }

    public void setCircleRadiusPressedPercent(float circleRadiusPressedPercent) {
        if (circleRadiusPressedPercent != this.mCircleRadiusPressedPercent) {
            this.mCircleRadiusPressedPercent = circleRadiusPressedPercent;
            invalidate();
        }
    }

    @Override // android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        setColorForCurrentState();
    }

    public void setCircleColor(int circleColor) {
        setCircleColorStateList(ColorStateList.valueOf(circleColor));
    }

    public void setCircleColorStateList(ColorStateList circleColor) {
        if (!Objects.equals(circleColor, this.mCircleColor)) {
            this.mCircleColor = circleColor;
            setColorForCurrentState();
            invalidate();
        }
    }

    public ColorStateList getCircleColorStateList() {
        return this.mCircleColor;
    }

    public int getDefaultCircleColor() {
        return this.mCircleColor.getDefaultColor();
    }

    public void showIndeterminateProgress(boolean show) {
        this.mProgressIndeterminate = show;
        if (show) {
            this.mIndeterminateDrawable.startAnimation();
        } else {
            this.mIndeterminateDrawable.stopAnimation();
        }
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility != 0) {
            showIndeterminateProgress(false);
        } else if (this.mProgressIndeterminate) {
            showIndeterminateProgress(true);
        }
    }

    public void setProgress(float progress) {
        if (progress != this.mProgress) {
            this.mProgress = progress;
            invalidate();
        }
    }

    public void setShadowVisibility(float shadowVisibility) {
        if (shadowVisibility != this.mShadowVisibility) {
            this.mShadowVisibility = shadowVisibility;
            invalidate();
        }
    }

    public float getInitialCircleRadius() {
        return this.mInitialCircleRadius;
    }

    public void setCircleBorderColor(int circleBorderColor) {
        this.mCircleBorderColor = circleBorderColor;
    }

    public void setCircleBorderWidth(float circleBorderWidth) {
        if (circleBorderWidth != this.mCircleBorderWidth) {
            this.mCircleBorderWidth = circleBorderWidth;
            invalidate();
        }
    }

    @Override // android.view.View
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        if (pressed != this.mPressed) {
            this.mPressed = pressed;
            invalidate();
        }
    }

    public Drawable getImageDrawable() {
        return this.mDrawable;
    }

    public long getColorChangeAnimationDuration() {
        return this.mColorChangeAnimationDurationMs;
    }

    public void setColorChangeAnimationDuration(long mColorChangeAnimationDurationMs) {
        this.mColorChangeAnimationDurationMs = mColorChangeAnimationDurationMs;
    }
}
