package com.android.systemui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.android.systemui.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.text.DecimalFormat;

/* JADX INFO: loaded from: classes.dex */
public class SocSeekbar extends View {
    public static final int VIEW_TYPE_1 = 1;
    public static final int VIEW_TYPE_2 = 2;
    public static final int VIEW_TYPE_DEFAULT = 0;
    private Paint circlePaint;
    private DecimalFormat df;
    private Paint fillPaint;
    private int height;
    private LinearGradient linearGradient;
    private LinearGradient linearGradient2;
    private LinearGradient linearGradient3;
    private int mBarHeight;
    private int mBarWidth;
    private boolean mIsDragging;
    private float mLimit;
    private OnSeekBarChangeListener mListener;
    private Bitmap mRecycleBitmap;
    private float mScaledTouchSlop;
    private float mTouchDownX;
    private int marginLeft;
    private int marginRight;
    private int marginTop;
    private float progressOne;
    private float progressThree;
    private float progressTwo;
    private float radius;
    private int scaleCount;
    private float thumbRadius;
    private int viewType;
    private int width;

    public interface OnSeekBarChangeListener {
        void onProgressChanged(float progress);

        void onProgressLimit();

        void onStartTrackingTouch(float progress);

        void onStopTrackingTouch(float progress);
    }

    public SocSeekbar(Context context) {
        super(context);
        this.viewType = 0;
        this.scaleCount = 45;
        this.mIsDragging = false;
        this.mTouchDownX = 0.0f;
        this.mScaledTouchSlop = 8.0f;
        initData();
    }

    public SocSeekbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.viewType = 0;
        this.scaleCount = 45;
        this.mIsDragging = false;
        this.mTouchDownX = 0.0f;
        this.mScaledTouchSlop = 8.0f;
        initData();
    }

    public SocSeekbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.viewType = 0;
        this.scaleCount = 45;
        this.mIsDragging = false;
        this.mTouchDownX = 0.0f;
        this.mScaledTouchSlop = 8.0f;
        initData();
    }

    private void initData() {
        this.df = new DecimalFormat("#.#");
        Paint paint = new Paint();
        this.fillPaint = paint;
        paint.setAntiAlias(true);
        this.fillPaint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint();
        this.circlePaint = paint2;
        paint2.setAntiAlias(true);
        this.circlePaint.setStyle(Paint.Style.FILL);
        this.circlePaint.setColor(ContextCompat.getColor(getContext(), R.color.sublevelseekbar_circle));
        this.linearGradient = new LinearGradient(0.0f, this.marginTop, 0.0f, this.mBarHeight, new int[]{ContextCompat.getColor(getContext(), R.color.carlights_seekbar_secondprogress), ContextCompat.getColor(getContext(), R.color.carlights_seekbar_secondprogress)}, (float[]) null, Shader.TileMode.CLAMP);
        this.linearGradient2 = new LinearGradient(0.0f, this.marginTop, 0.0f, this.mBarHeight, new int[]{ContextCompat.getColor(getContext(), R.color.byd_vehicle_seekbar_progress_end_color), ContextCompat.getColor(getContext(), R.color.byd_vehicle_seekbar_progress_end_color)}, (float[]) null, Shader.TileMode.CLAMP);
        this.linearGradient3 = new LinearGradient(0.0f, this.marginTop, 0.0f, this.mBarHeight, new int[]{ContextCompat.getColor(getContext(), R.color.thumb_socseekbar_bg_color), ContextCompat.getColor(getContext(), R.color.thumb_socseekbar_bg_color)}, (float[]) null, Shader.TileMode.CLAMP);
        this.marginRight = getResources().getDimensionPixelSize(R.dimen.dp_27);
        this.marginLeft = getResources().getDimensionPixelSize(R.dimen.dp_27);
        this.marginTop = getResources().getDimensionPixelSize(R.dimen.dp_14);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.dp_12);
        this.mBarHeight = dimensionPixelSize;
        this.radius = dimensionPixelSize / 2.0f;
        this.thumbRadius = getResources().getDimensionPixelSize(R.dimen.dp_10);
        initRecycleBitmap();
    }

    private void initRecycleBitmap() {
        BydLogHelper.logE("[initProgress]----initRecycleBitmap");
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.mipmap.soc_seekbar_thumb);
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        this.mRecycleBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(this.mRecycleBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.width = View.MeasureSpec.getSize(widthMeasureSpec);
        this.height = View.MeasureSpec.getSize(heightMeasureSpec);
        this.mBarWidth = (this.width - this.marginRight) - this.marginLeft;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        try {
            int i = this.viewType;
            if (i == 1) {
                this.fillPaint.setShader(this.linearGradient3);
                int i2 = this.marginLeft;
                float f = this.radius;
                canvas.drawRoundRect(i2 - f, this.marginTop, i2 + this.mBarWidth + f, r1 + this.mBarHeight, f, f, this.fillPaint);
                if (this.mRecycleBitmap == null) {
                    initRecycleBitmap();
                }
                canvas.drawBitmap(this.mRecycleBitmap, this.marginLeft - (r0.getWidth() / 2.0f), (this.height / 2.0f) - (this.mRecycleBitmap.getHeight() / 2.0f), (Paint) null);
                return;
            }
            if (i == 2) {
                this.fillPaint.setShader(this.progressOne + this.progressTwo == 0.0f ? this.linearGradient3 : this.linearGradient);
                int i3 = this.marginLeft;
                float f2 = this.radius;
                canvas.drawRoundRect(i3 - f2, this.marginTop, i3 + ((this.progressOne + this.progressTwo) * this.mBarWidth) + f2, r1 + this.mBarHeight, f2, f2, this.fillPaint);
                this.fillPaint.setShader(this.progressThree == 0.0f ? this.linearGradient : this.linearGradient3);
                int i4 = this.marginLeft;
                float f3 = this.progressOne + this.progressTwo;
                int i5 = this.mBarWidth;
                float f4 = this.radius;
                canvas.drawRoundRect((i4 + (f3 * i5)) - f4, this.marginTop, i4 + i5 + f4, r1 + this.mBarHeight, f4, f4, this.fillPaint);
                if (this.mRecycleBitmap == null) {
                    initRecycleBitmap();
                }
                canvas.drawBitmap(this.mRecycleBitmap, (this.marginLeft + (this.mBarWidth * (this.progressOne + this.progressTwo))) - (r0.getWidth() / 2.0f), (this.height / 2.0f) - (this.mRecycleBitmap.getHeight() / 2.0f), (Paint) null);
                return;
            }
            this.fillPaint.setShader(this.progressOne == 0.0f ? this.linearGradient2 : this.linearGradient);
            int i6 = this.marginLeft;
            float f5 = this.radius;
            canvas.drawRoundRect(i6 - f5, this.marginTop, i6 + (this.progressOne * this.mBarWidth) + f5, r1 + this.mBarHeight, f5, f5, this.fillPaint);
            this.fillPaint.setShader(this.progressThree == 0.0f ? this.linearGradient2 : this.linearGradient3);
            int i7 = this.marginLeft;
            float f6 = this.progressOne + this.progressTwo;
            int i8 = this.mBarWidth;
            float f7 = this.radius;
            canvas.drawRoundRect((i7 + (f6 * i8)) - f7, this.marginTop, i7 + i8 + f7, r1 + this.mBarHeight, f7, f7, this.fillPaint);
            this.fillPaint.setShader(this.linearGradient2);
            int i9 = this.marginLeft;
            float f8 = this.progressOne;
            int i10 = this.mBarWidth;
            canvas.drawRect(i9 + (i10 * f8), this.marginTop, i9 + ((f8 + this.progressTwo) * i10), r1 + this.mBarHeight, this.fillPaint);
            if (this.mRecycleBitmap == null) {
                initRecycleBitmap();
            }
            canvas.drawBitmap(this.mRecycleBitmap, (this.marginLeft + (this.mBarWidth * (this.progressOne + this.progressTwo))) - (r0.getWidth() / 2.0f), (this.height / 2.0f) - (this.mRecycleBitmap.getHeight() / 2.0f), (Paint) null);
        } catch (Exception e) {
            BydLogHelper.logE("[initProgress]----" + e.getMessage());
        }
    }

    public void setProgress(int viewType, float progressOne, float progressTwo, float progressThree, int scaleCount) {
        float f = progressOne + progressTwo;
        if (f + progressThree != 1.0f) {
            return;
        }
        this.viewType = viewType;
        this.progressOne = progressOne;
        this.progressTwo = progressTwo;
        this.progressThree = progressThree;
        this.mLimit = (this.marginLeft + (f * this.mBarWidth)) - this.radius;
        this.scaleCount = scaleCount;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
        postInvalidate();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == 0) {
            this.viewType = 0;
            this.mIsDragging = false;
            this.mTouchDownX = event.getX();
            trackTouchEvent(event);
            OnSeekBarChangeListener onSeekBarChangeListener = this.mListener;
            if (onSeekBarChangeListener != null) {
                onSeekBarChangeListener.onStartTrackingTouch(this.progressOne + this.progressTwo);
            }
            invalidate();
        } else if (action == 1) {
            if (this.mIsDragging) {
                trackTouchEvent(event);
                setPressed(false);
            } else {
                trackTouchEvent(event);
            }
            BydLogHelper.logD("onTouchEvent mLimit = " + this.mLimit + "  ----  mTouchUpX = " + event.getX());
            OnSeekBarChangeListener onSeekBarChangeListener2 = this.mListener;
            if (onSeekBarChangeListener2 != null) {
                onSeekBarChangeListener2.onStopTrackingTouch(this.progressOne + this.progressTwo);
            }
            invalidate();
        } else if (action == 2) {
            if (this.mIsDragging) {
                trackTouchEvent(event);
                invalidate();
            } else if (Math.abs(event.getX() - this.mTouchDownX) > this.mScaledTouchSlop) {
                this.mIsDragging = true;
            }
            OnSeekBarChangeListener onSeekBarChangeListener3 = this.mListener;
            if (onSeekBarChangeListener3 != null) {
                onSeekBarChangeListener3.onProgressChanged(this.progressOne + this.progressTwo);
            }
        } else if (action == 3) {
            if (this.mIsDragging) {
                setPressed(false);
            }
            OnSeekBarChangeListener onSeekBarChangeListener4 = this.mListener;
            if (onSeekBarChangeListener4 != null) {
                onSeekBarChangeListener4.onStopTrackingTouch(this.progressOne + this.progressTwo);
            }
            invalidate();
        }
        return true;
    }

    private void trackTouchEvent(MotionEvent event) {
        OnSeekBarChangeListener onSeekBarChangeListener;
        try {
            float x = event.getX();
            float f = this.progressTwo;
            float f2 = this.progressThree;
            float f3 = f + f2;
            int i = this.marginLeft;
            float f4 = this.progressOne;
            int i2 = this.mBarWidth;
            float f5 = 0.0f;
            if (x >= i + (i2 * f4)) {
                f5 = x > ((float) i) + (((f + f4) + f2) * ((float) i2)) ? 1.0f : (((x - i) - (f4 * i2)) * 1.0f) / (i2 * f3);
            } else if (event.getAction() == 1 && (onSeekBarChangeListener = this.mListener) != null && this.progressOne != 0.0f) {
                onSeekBarChangeListener.onProgressLimit();
            }
            float fRound = Math.round(f5 * f3 * this.scaleCount);
            int i3 = this.scaleCount;
            float f6 = fRound / (i3 * 1.0f);
            this.progressTwo = f6;
            float f7 = f3 - f6;
            this.progressThree = f7;
            setProgress(0, this.progressOne, f6, f7, i3);
        } catch (Exception e) {
            BydLogHelper.logE(e.getMessage());
        }
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent event) {
        try {
            getParent().getParent().getParent().getParent().requestDisallowInterceptTouchEvent(this.mIsDragging);
        } catch (Exception e) {
            BydLogHelper.logE(e.getMessage());
        }
        return super.dispatchTouchEvent(event);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        try {
            BydLogHelper.logE("[initProgress]----onDetachedFromWindow  mRecycleBitmap.recycle");
            this.mRecycleBitmap.recycle();
            this.mRecycleBitmap = null;
        } catch (Exception e) {
            BydLogHelper.logE(e.getMessage());
        }
    }

    public void setOnSeekBarChangeListener(OnSeekBarChangeListener listener) {
        this.mListener = listener;
    }
}
