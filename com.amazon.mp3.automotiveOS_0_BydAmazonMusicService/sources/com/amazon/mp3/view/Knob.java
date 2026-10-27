package com.amazon.mp3.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.ScreenUtil;

/* JADX INFO: loaded from: classes4.dex */
public class Knob extends FrameLayout {
    private final int CIRCLE_0_DEGREES;
    private final int CIRCLE_360_DEGREES;
    private final int CIRCLE_45_DEGREES;
    private final int CIRCLE_90_DEGREES;
    private final float GRADIENT_END_RATIO;
    private final int PROGRESS_STROKE_TIP;
    private float mDownDeg;
    private boolean mEnabled;
    private int mKnobDisabledImageDrawable;
    private int mKnobEnabledImageDrawable;
    private float mKnobMidX;
    private float mKnobMidY;
    private float mKnobProgress;
    private Matrix mKnobRotationMatrix;
    private float mKnobScaleRatio;
    private ImageView mKnobView;
    private int mMaxKnobValue;
    private KnobProgressChangeListener mProgressChangeListener;
    private int mProgressEndColor;
    private Paint mProgressPaint;
    private RectF mProgressRect;
    private int mProgressStartColor;
    private int mProgressStrokeWidthPx;
    private int mProgressWidthDp;
    private int mProgressWidthPx;
    private int mStartOffset;
    private int mSweepAngle;

    public interface KnobProgressChangeListener {
        void onPressWhileDisabled();

        void onProgressChanged(Knob knob, final int level);
    }

    public void setOnKnobProgressChangeListener(final KnobProgressChangeListener listener) {
        this.mProgressChangeListener = listener;
    }

    public Knob(final Context context, final AttributeSet attrs) {
        super(context, attrs, 0);
        this.CIRCLE_360_DEGREES = 360;
        this.CIRCLE_90_DEGREES = 90;
        this.CIRCLE_45_DEGREES = 45;
        this.CIRCLE_0_DEGREES = 0;
        this.GRADIENT_END_RATIO = 0.5f;
        this.PROGRESS_STROKE_TIP = 5;
        this.mKnobEnabledImageDrawable = R.drawable.knob_enable;
        this.mKnobDisabledImageDrawable = R.drawable.knob_disable;
        this.mEnabled = true;
        this.mProgressStartColor = getContext().getResources().getColor(R.color.equalizer_knob_progress_start_color);
        this.mProgressEndColor = getContext().getResources().getColor(R.color.equalizer_knob_progress_end_color);
        this.mProgressWidthDp = getContext().getResources().getInteger(R.integer.equalizer_knob_progress_width_dp);
        this.mKnobProgress = 0.0f;
        this.mDownDeg = 0.0f;
        this.mStartOffset = 45;
        this.mMaxKnobValue = 1000;
        this.mKnobScaleRatio = 1.0f;
        init();
    }

    private void init() {
        setWillNotDraw(false);
        this.mProgressWidthPx = ScreenUtil.convertDpToPx(this.mProgressWidthDp);
        this.mSweepAngle = 360 - (this.mStartOffset * 2);
        initKnob();
        initProgress();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mKnobMidX = canvas.getWidth() / 2.0f;
        this.mKnobMidY = canvas.getHeight() / 2.0f;
        float f = this.mKnobProgress * (this.mSweepAngle / this.mMaxKnobValue);
        drawProgress(canvas, f);
        drawKnob(f);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() == 0) {
            if (!this.mEnabled) {
                this.mProgressChangeListener.onPressWhileDisabled();
                return true;
            }
            this.mDownDeg = getDegreesFromBottom(e.getX() - this.mKnobMidX, (e.getY() - this.mKnobMidY) * (-1.0f));
            return true;
        }
        if (e.getAction() == 2) {
            if (!this.mEnabled) {
                return true;
            }
            float degreesFromBottom = getDegreesFromBottom(e.getX() - this.mKnobMidX, (e.getY() - this.mKnobMidY) * (-1.0f));
            float f = this.mDownDeg;
            int i = this.mStartOffset;
            if (f < i && degreesFromBottom > 360 - i) {
                float f2 = this.mKnobProgress - ((((f + 360.0f) - degreesFromBottom) * this.mMaxKnobValue) / this.mSweepAngle);
                this.mKnobProgress = f2;
                if (f2 < 0.0f) {
                    this.mKnobProgress = 0.0f;
                }
            } else if (degreesFromBottom < i && f > 360 - i) {
                float f3 = this.mKnobProgress;
                float f4 = (360.0f + degreesFromBottom) - f;
                int i2 = this.mMaxKnobValue;
                float f5 = f3 + ((f4 * i2) / this.mSweepAngle);
                this.mKnobProgress = f5;
                if (f5 > i2) {
                    this.mKnobProgress = i2;
                }
            } else {
                float f6 = this.mKnobProgress;
                int i3 = this.mMaxKnobValue;
                float f7 = f6 + (((degreesFromBottom - f) * i3) / this.mSweepAngle);
                this.mKnobProgress = f7;
                if (f7 > i3) {
                    this.mKnobProgress = i3;
                }
                if (this.mKnobProgress < 0.0f) {
                    this.mKnobProgress = 0.0f;
                }
            }
            this.mDownDeg = degreesFromBottom;
            KnobProgressChangeListener knobProgressChangeListener = this.mProgressChangeListener;
            if (knobProgressChangeListener != null) {
                knobProgressChangeListener.onProgressChanged(this, (int) Math.ceil(this.mKnobProgress));
            }
            invalidate();
            return true;
        }
        if (e.getAction() == 1) {
            return true;
        }
        return super.onTouchEvent(e);
    }

    private float getDegreesFromBottom(final float x, final float y) {
        float fAtan2 = ((float) ((Math.atan2(y, x) * 180.0d) / 3.141592653589793d)) + 90.0f;
        return fAtan2 < 0.0f ? fAtan2 * (-1.0f) : 360.0f - fAtan2;
    }

    private void initKnob() {
        ImageView imageView = new ImageView(getContext());
        this.mKnobView = imageView;
        imageView.setImageResource(this.mKnobEnabledImageDrawable);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        int i = this.mProgressWidthPx;
        layoutParams.setMargins(i, i, i, i);
        addView(this.mKnobView, layoutParams);
        this.mKnobRotationMatrix = new Matrix();
    }

    private void initProgress() {
        Paint paint = new Paint();
        this.mProgressPaint = paint;
        paint.setAntiAlias(true);
        int i = this.mProgressWidthPx * 2;
        this.mProgressStrokeWidthPx = i;
        this.mProgressPaint.setStrokeWidth(i);
        this.mProgressPaint.setStyle(Paint.Style.STROKE);
        this.mProgressPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mProgressRect = new RectF();
    }

    private void drawProgress(final Canvas canvas, final float currKnobDegrees) {
        if (this.mEnabled) {
            int iMin = (int) Math.min(this.mKnobMidX, this.mKnobMidY);
            int i = this.mProgressWidthPx;
            float f = i - iMin;
            float f2 = iMin - i;
            RectF rectF = this.mProgressRect;
            float f3 = this.mKnobMidX;
            float f4 = this.mKnobMidY;
            rectF.set(f3 + f, f + f4, f3 + f2, f4 + f2);
            int i2 = this.mProgressStartColor;
            SweepGradient sweepGradient = new SweepGradient(this.mProgressRect.centerX(), this.mProgressRect.centerY(), new int[]{i2, i2, this.mProgressEndColor}, new float[]{0.0f, this.mStartOffset / 360.0f, 0.5f});
            this.mKnobRotationMatrix.reset();
            this.mKnobRotationMatrix.setRotate(90.0f, this.mProgressRect.centerX(), this.mProgressRect.centerY());
            sweepGradient.setLocalMatrix(this.mKnobRotationMatrix);
            this.mProgressPaint.setShader(sweepGradient);
            canvas.drawArc(this.mProgressRect, (this.mStartOffset + 90.0f) - 5.0f, currKnobDegrees, false, this.mProgressPaint);
        }
    }

    private void drawKnob(final float currKnobDegrees) {
        this.mKnobRotationMatrix.reset();
        if (this.mEnabled) {
            this.mKnobView.setScaleType(ImageView.ScaleType.MATRIX);
            this.mKnobRotationMatrix.setRotate(currKnobDegrees, this.mKnobView.getWidth() / 2.0f, this.mKnobView.getHeight() / 2.0f);
        }
        this.mKnobView.setImageMatrix(this.mKnobRotationMatrix);
    }

    public void setMax(final int max) {
        this.mMaxKnobValue = max;
    }

    public int getProgress() {
        return (int) Math.ceil(this.mKnobProgress);
    }

    public void setProgress(final int progress) {
        this.mKnobProgress = progress;
        invalidate();
    }

    public void setProgressStartColor(final int progressStartColor) {
        this.mProgressStartColor = progressStartColor;
        invalidate();
    }

    public void setProgressEndColor(final int progressEndColor) {
        this.mProgressEndColor = progressEndColor;
        invalidate();
    }

    public void setProgressWidthDp(final int progressWidthDp) {
        this.mProgressWidthDp = progressWidthDp;
        init();
    }

    public void setKnobEnabledImageDrawable(final int imageDrawableId) {
        this.mKnobEnabledImageDrawable = imageDrawableId;
        if (this.mEnabled) {
            this.mKnobView.setImageResource(imageDrawableId);
            invalidate();
        }
    }

    public void setKnobDisabledImageDrawable(final int imageDrawableId) {
        this.mKnobDisabledImageDrawable = imageDrawableId;
        if (this.mEnabled) {
            return;
        }
        this.mKnobView.setImageResource(imageDrawableId);
        invalidate();
    }

    public void setStartOffset(final int startOffset) {
        this.mStartOffset = startOffset;
        this.mSweepAngle = 360 - (startOffset * 2);
        invalidate();
    }

    public int getKnobWidth() {
        return (int) (getResources().getDrawable(this.mKnobEnabledImageDrawable).getIntrinsicWidth() * this.mKnobScaleRatio);
    }

    public void setKnobEnabled(boolean enable) {
        this.mEnabled = enable;
        this.mKnobView.setImageResource(enable ? this.mKnobEnabledImageDrawable : this.mKnobDisabledImageDrawable);
        invalidate();
    }
}
