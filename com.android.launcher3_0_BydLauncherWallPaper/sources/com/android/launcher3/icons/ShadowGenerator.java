package com.android.launcher3.icons;

import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;

/* JADX INFO: loaded from: classes4.dex */
public class ShadowGenerator {
    private static final int AMBIENT_SHADOW_ALPHA = 30;
    public static final float BLUR_FACTOR = 0.010416667f;
    private static final float HALF_DISTANCE = 0.5f;
    private static final int KEY_SHADOW_ALPHA = 61;
    public static final float KEY_SHADOW_DISTANCE = 0.020833334f;
    private final BlurMaskFilter mDefaultBlurMaskFilter;
    private final int mIconSize;
    private final Paint mBlurPaint = new Paint(3);
    private final Paint mDrawPaint = new Paint(3);

    public ShadowGenerator(int iconSize) {
        this.mIconSize = iconSize;
        this.mDefaultBlurMaskFilter = new BlurMaskFilter(iconSize * 0.010416667f, BlurMaskFilter.Blur.NORMAL);
    }

    public synchronized void recreateIcon(Bitmap icon, Canvas out) {
        recreateIcon(icon, this.mDefaultBlurMaskFilter, 30, 61, out);
    }

    public synchronized void recreateIcon(Bitmap icon, BlurMaskFilter blurMaskFilter, int ambientAlpha, int keyAlpha, Canvas out) {
        this.mBlurPaint.setMaskFilter(blurMaskFilter);
        Bitmap bitmapExtractAlpha = icon.extractAlpha(this.mBlurPaint, new int[2]);
        this.mDrawPaint.setAlpha(ambientAlpha);
        out.drawBitmap(bitmapExtractAlpha, r0[0], r0[1], this.mDrawPaint);
        this.mDrawPaint.setAlpha(keyAlpha);
        out.drawBitmap(bitmapExtractAlpha, r0[0], r0[1] + (this.mIconSize * 0.020833334f), this.mDrawPaint);
        this.mDrawPaint.setAlpha(255);
        out.drawBitmap(icon, 0.0f, 0.0f, this.mDrawPaint);
    }

    public static float getScaleForBounds(RectF bounds) {
        float fMin = Math.min(Math.min(bounds.left, bounds.right), bounds.top);
        float f = fMin < 0.010416667f ? 0.48958334f / (0.5f - fMin) : 1.0f;
        return bounds.bottom < 0.03125f ? Math.min(f, 0.46875f / (0.5f - bounds.bottom)) : f;
    }

    public static class Builder {
        public final int color;
        public float keyShadowDistance;
        public float radius;
        public float shadowBlur;
        public final RectF bounds = new RectF();
        public int ambientShadowAlpha = 30;
        public int keyShadowAlpha = 61;

        public Builder(int color) {
            this.color = color;
        }

        public Builder setupBlurForSize(int height) {
            float f = height * 1.0f;
            this.shadowBlur = f / 24.0f;
            this.keyShadowDistance = f / 16.0f;
            return this;
        }

        public Bitmap createPill(int width, int height) {
            return createPill(width, height, height / 2.0f);
        }

        public Bitmap createPill(int width, int height, float r) {
            this.radius = r;
            float f = width;
            float f2 = f / 2.0f;
            int iMax = Math.max(Math.round(this.shadowBlur + f2), Math.round(this.radius + this.shadowBlur + this.keyShadowDistance));
            float f3 = height;
            this.bounds.set(0.0f, 0.0f, f, f3);
            float f4 = iMax;
            this.bounds.offsetTo(f4 - f2, f4 - (f3 / 2.0f));
            int i = iMax * 2;
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
            drawShadow(new Canvas(bitmapCreateBitmap));
            return bitmapCreateBitmap;
        }

        public void drawShadow(Canvas c) {
            Paint paint = new Paint(3);
            paint.setColor(this.color);
            paint.setShadowLayer(this.shadowBlur, 0.0f, this.keyShadowDistance, GraphicsUtils.setColorAlphaBound(-16777216, this.keyShadowAlpha));
            RectF rectF = this.bounds;
            float f = this.radius;
            c.drawRoundRect(rectF, f, f, paint);
            paint.setShadowLayer(this.shadowBlur, 0.0f, 0.0f, GraphicsUtils.setColorAlphaBound(-16777216, this.ambientShadowAlpha));
            RectF rectF2 = this.bounds;
            float f2 = this.radius;
            c.drawRoundRect(rectF2, f2, f2, paint);
            if (Color.alpha(this.color) < 255) {
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                paint.clearShadowLayer();
                paint.setColor(-16777216);
                RectF rectF3 = this.bounds;
                float f3 = this.radius;
                c.drawRoundRect(rectF3, f3, f3, paint);
                paint.setXfermode(null);
                paint.setColor(this.color);
                RectF rectF4 = this.bounds;
                float f4 = this.radius;
                c.drawRoundRect(rectF4, f4, f4, paint);
            }
        }
    }
}
