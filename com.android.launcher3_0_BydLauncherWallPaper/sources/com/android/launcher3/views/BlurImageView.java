package com.android.launcher3.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import com.android.launcher3.graphics.ShaderBitmapCache;

/* JADX INFO: loaded from: classes4.dex */
public class BlurImageView extends AppCompatImageView {
    private final int MAX_TANS_LENGTH;
    private final Rect dst;
    boolean isScrolling;
    private Paint mBitmapPaint;
    private Paint mPaint;
    private final RectF mRectF;
    private Paint mShaderPaintEnd;
    private Paint mShaderPaintStart;
    private int mTansLength;
    private Bitmap origBitmap;
    private final Rect src;

    public BlurImageView(Context context) {
        super(context);
        this.mRectF = new RectF();
        this.MAX_TANS_LENGTH = 300;
        this.src = new Rect();
        this.dst = new Rect();
        initPaint();
    }

    public BlurImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mRectF = new RectF();
        this.MAX_TANS_LENGTH = 300;
        this.src = new Rect();
        this.dst = new Rect();
        initPaint();
    }

    public BlurImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mRectF = new RectF();
        this.MAX_TANS_LENGTH = 300;
        this.src = new Rect();
        this.dst = new Rect();
        initPaint();
    }

    private void initPaint() {
        this.mBitmapPaint = new Paint(1);
        Paint paint = new Paint(1);
        this.mPaint = paint;
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        this.mShaderPaintEnd = new Paint(1);
        this.mShaderPaintStart = new Paint(1);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap = ShaderBitmapCache.getInstance().getBitmap();
        if (bitmap == null) {
            ShaderBitmapCache.getInstance().initCache(getMeasuredWidth(), getMeasuredHeight());
        }
        Bitmap bitmap2 = this.origBitmap;
        if (bitmap2 == null) {
            super.onDraw(canvas);
            return;
        }
        if (bitmap2.isRecycled()) {
            return;
        }
        this.src.set(0, 0, this.origBitmap.getWidth(), this.origBitmap.getHeight());
        this.dst.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
        Canvas canvas2 = ShaderBitmapCache.getInstance().getCanvas();
        if (this.isScrolling) {
            this.mRectF.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
            canvas2.save();
            canvas2.drawBitmap(this.origBitmap, this.src, this.dst, this.mBitmapPaint);
            canvas2.saveLayer(this.mRectF, this.mPaint);
            canvas2.drawPaint(getAlphaPaint(getMeasuredWidth(), getMeasuredHeight()));
            canvas2.restore();
            this.src.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
            this.dst.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
            canvas.drawBitmap(bitmap, this.src, this.dst, this.mBitmapPaint);
            return;
        }
        this.src.set(0, 0, this.origBitmap.getWidth(), this.origBitmap.getHeight());
        this.dst.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
        canvas.drawBitmap(this.origBitmap, this.src, this.dst, this.mBitmapPaint);
    }

    public void setTansLength(int tansLength) {
        this.mTansLength = tansLength;
        invalidate();
    }

    public int getTansLength() {
        return this.mTansLength;
    }

    private Paint getAlphaPaint(int imageW, int imageH) {
        int iMin = this.mTansLength;
        if (iMin > 300) {
            iMin = imageW - iMin <= 300 ? Math.min(imageW - iMin, 300) : 300;
        }
        float f = imageH / 2;
        this.mShaderPaintEnd.setShader(new LinearGradient((imageW - iMin) - getTranslationX(), f, imageW - getTranslationX(), f, -16777216, 0, Shader.TileMode.CLAMP));
        return this.mShaderPaintEnd;
    }

    private Paint getAlphaPaintStart(int imageW, int imageH) {
        float f = imageH / 2;
        this.mShaderPaintStart.setShader(new LinearGradient(-getTranslationX(), f, Math.max(this.mTansLength, 300) - getTranslationX(), f, 0, -16777216, Shader.TileMode.CLAMP));
        return this.mShaderPaintStart;
    }

    @Override // android.view.View
    public void setTranslationX(float translationX) {
        super.setTranslationX(translationX);
        invalidate();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        this.origBitmap = bm;
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        if (drawable instanceof BitmapDrawable) {
            this.origBitmap = ((BitmapDrawable) drawable).getBitmap();
        }
        if (drawable != null || this.origBitmap == null) {
            return;
        }
        this.origBitmap = null;
    }

    public void setScrolling(boolean scrolling) {
        this.isScrolling = scrolling;
        invalidate();
    }
}
