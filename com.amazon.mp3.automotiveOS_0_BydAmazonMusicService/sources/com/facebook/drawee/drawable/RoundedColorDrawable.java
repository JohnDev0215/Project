package com.facebook.drawee.drawable;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.internal.Preconditions;
import java.util.Arrays;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class RoundedColorDrawable extends Drawable implements Rounded {

    @Nullable
    @VisibleForTesting
    float[] mInsideBorderRadii;
    private final float[] mRadii = new float[8];

    @VisibleForTesting
    final float[] mBorderRadii = new float[8];

    @VisibleForTesting
    final Paint mPaint = new Paint(1);
    private boolean mIsCircle = false;
    private float mBorderWidth = 0.0f;
    private float mPadding = 0.0f;
    private int mBorderColor = 0;
    private boolean mScaleDownInsideBorders = false;
    private boolean mPaintFilterBitmap = false;

    @VisibleForTesting
    final Path mPath = new Path();

    @VisibleForTesting
    final Path mBorderPath = new Path();
    private int mColor = 0;
    private final RectF mTempRect = new RectF();
    private int mAlpha = 255;

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public RoundedColorDrawable(int color) {
        setColor(color);
    }

    @TargetApi(11)
    public static RoundedColorDrawable fromColorDrawable(ColorDrawable colorDrawable) {
        return new RoundedColorDrawable(colorDrawable.getColor());
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        updatePath();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.mPaint.setColor(DrawableUtils.multiplyColorAlpha(this.mColor, this.mAlpha));
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setFilterBitmap(getPaintFilterBitmap());
        canvas.drawPath(this.mPath, this.mPaint);
        if (this.mBorderWidth != 0.0f) {
            this.mPaint.setColor(DrawableUtils.multiplyColorAlpha(this.mBorderColor, this.mAlpha));
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPaint.setStrokeWidth(this.mBorderWidth);
            canvas.drawPath(this.mBorderPath, this.mPaint);
        }
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public void setCircle(boolean isCircle) {
        this.mIsCircle = isCircle;
        updatePath();
        invalidateSelf();
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public void setRadii(float[] radii) {
        if (radii == null) {
            Arrays.fill(this.mRadii, 0.0f);
        } else {
            Preconditions.checkArgument(radii.length == 8, "radii should have exactly 8 values");
            System.arraycopy(radii, 0, this.mRadii, 0, 8);
        }
        updatePath();
        invalidateSelf();
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public void setRadius(float radius) {
        Preconditions.checkArgument(radius >= 0.0f, "radius should be non negative");
        Arrays.fill(this.mRadii, radius);
        updatePath();
        invalidateSelf();
    }

    public void setColor(int color) {
        if (this.mColor != color) {
            this.mColor = color;
            invalidateSelf();
        }
    }

    public int getColor() {
        return this.mColor;
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public void setBorder(int color, float width) {
        if (this.mBorderColor != color) {
            this.mBorderColor = color;
            invalidateSelf();
        }
        if (this.mBorderWidth != width) {
            this.mBorderWidth = width;
            updatePath();
            invalidateSelf();
        }
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public int getBorderColor() {
        return this.mBorderColor;
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public float getBorderWidth() {
        return this.mBorderWidth;
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public void setPadding(float padding) {
        if (this.mPadding != padding) {
            this.mPadding = padding;
            updatePath();
            invalidateSelf();
        }
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public float getPadding() {
        return this.mPadding;
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public void setScaleDownInsideBorders(boolean scaleDownInsideBorders) {
        if (this.mScaleDownInsideBorders != scaleDownInsideBorders) {
            this.mScaleDownInsideBorders = scaleDownInsideBorders;
            updatePath();
            invalidateSelf();
        }
    }

    @Override // com.facebook.drawee.drawable.Rounded
    public void setPaintFilterBitmap(boolean paintFilterBitmap) {
        if (this.mPaintFilterBitmap != paintFilterBitmap) {
            this.mPaintFilterBitmap = paintFilterBitmap;
            invalidateSelf();
        }
    }

    public boolean getPaintFilterBitmap() {
        return this.mPaintFilterBitmap;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int alpha) {
        if (alpha != this.mAlpha) {
            this.mAlpha = alpha;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mAlpha;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return DrawableUtils.getOpacityFromColor(DrawableUtils.multiplyColorAlpha(this.mColor, this.mAlpha));
    }

    private void updatePath() {
        float[] fArr;
        float[] fArr2;
        this.mPath.reset();
        this.mBorderPath.reset();
        this.mTempRect.set(getBounds());
        RectF rectF = this.mTempRect;
        float f = this.mBorderWidth;
        rectF.inset(f / 2.0f, f / 2.0f);
        int i = 0;
        if (this.mIsCircle) {
            this.mBorderPath.addCircle(this.mTempRect.centerX(), this.mTempRect.centerY(), Math.min(this.mTempRect.width(), this.mTempRect.height()) / 2.0f, Path.Direction.CW);
        } else {
            int i2 = 0;
            while (true) {
                fArr = this.mBorderRadii;
                if (i2 >= fArr.length) {
                    break;
                }
                fArr[i2] = (this.mRadii[i2] + this.mPadding) - (this.mBorderWidth / 2.0f);
                i2++;
            }
            this.mBorderPath.addRoundRect(this.mTempRect, fArr, Path.Direction.CW);
        }
        RectF rectF2 = this.mTempRect;
        float f2 = this.mBorderWidth;
        rectF2.inset((-f2) / 2.0f, (-f2) / 2.0f);
        float f3 = this.mPadding + (this.mScaleDownInsideBorders ? this.mBorderWidth : 0.0f);
        this.mTempRect.inset(f3, f3);
        if (this.mIsCircle) {
            this.mPath.addCircle(this.mTempRect.centerX(), this.mTempRect.centerY(), Math.min(this.mTempRect.width(), this.mTempRect.height()) / 2.0f, Path.Direction.CW);
        } else if (this.mScaleDownInsideBorders) {
            if (this.mInsideBorderRadii == null) {
                this.mInsideBorderRadii = new float[8];
            }
            while (true) {
                fArr2 = this.mInsideBorderRadii;
                if (i >= fArr2.length) {
                    break;
                }
                fArr2[i] = this.mRadii[i] - this.mBorderWidth;
                i++;
            }
            this.mPath.addRoundRect(this.mTempRect, fArr2, Path.Direction.CW);
        } else {
            this.mPath.addRoundRect(this.mTempRect, this.mRadii, Path.Direction.CW);
        }
        float f4 = -f3;
        this.mTempRect.inset(f4, f4);
    }
}
