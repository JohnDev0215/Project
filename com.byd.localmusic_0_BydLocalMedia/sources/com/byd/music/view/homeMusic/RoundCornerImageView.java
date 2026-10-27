package com.byd.music.view.homeMusic;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.byd.music.R;

/* JADX INFO: loaded from: classes5.dex */
public class RoundCornerImageView extends ImageView {
    private static final int COLORDRAWABLE_DIMENSION = 4;
    private static final int DEFAULT_BORDER_COLOR = 0;
    private static final int DEFAULT_BORDER_WIDTH = 0;
    public static final int SHAPE_OVAL = 16;
    public static final int SHAPE_RECTANGLE = 1;
    protected int mBitmapHeight;
    private final Paint mBitmapPaint;
    protected int mBitmapWidth;
    protected int mBorderColor;
    private final Paint mBorderPaint;
    protected float mBorderRadius;
    private final RectF mBorderRect;
    protected int mBorderWidth;
    protected float mDrawableRadius;
    private final RectF mDrawableRect;
    protected float mRoundRadius;
    protected int mRoundShape;
    private static final ImageView.ScaleType SCALE_TYPE = ImageView.ScaleType.CENTER_CROP;
    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;

    public RoundCornerImageView(Context context) {
        super(context);
        this.mDrawableRect = new RectF();
        this.mBorderRect = new RectF();
        this.mBitmapPaint = new Paint();
        this.mBorderPaint = new Paint();
        this.mBorderColor = 0;
        this.mBorderWidth = 0;
    }

    public RoundCornerImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundCornerImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDrawableRect = new RectF();
        this.mBorderRect = new RectF();
        this.mBitmapPaint = new Paint();
        this.mBorderPaint = new Paint();
        this.mBorderColor = 0;
        this.mBorderWidth = 0;
        super.setScaleType(SCALE_TYPE);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundImageView, i, 0);
        this.mBorderWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundImageView_cirBorderWidth, 0);
        this.mBorderColor = typedArrayObtainStyledAttributes.getColor(R.styleable.RoundImageView_cirBorderColor, 0);
        this.mRoundShape = typedArrayObtainStyledAttributes.getInteger(R.styleable.RoundImageView_roundShape, 1);
        this.mRoundRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundImageView_roundRadius, 0);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.widget.ImageView
    public ImageView.ScaleType getScaleType() {
        return SCALE_TYPE;
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        if (scaleType != SCALE_TYPE) {
            throw new IllegalArgumentException(String.format("ScaleType %s not supported.", scaleType));
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        setup(canvas);
    }

    private void clipDef(Canvas canvas) {
        int i = this.mRoundShape;
        if (i == 16) {
            if (this.mBorderWidth != 0) {
                canvas.drawCircle(getMeasuredWidth() / 2.0f, getMeasuredHeight() / 2.0f, this.mBorderRadius, this.mBorderPaint);
            }
            canvas.drawCircle(getMeasuredWidth() / 2.0f, getMeasuredHeight() / 2.0f, this.mDrawableRadius, this.mBitmapPaint);
        } else if (i == 1) {
            RectF rectF = new RectF(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
            float f = this.mRoundRadius;
            canvas.drawRoundRect(rectF, f, f, this.mBitmapPaint);
        }
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        Bitmap bitmapCreateBitmap;
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            if (drawable instanceof ColorDrawable) {
                bitmapCreateBitmap = Bitmap.createBitmap(4, 4, BITMAP_CONFIG);
            } else {
                bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), BITMAP_CONFIG);
            }
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmapCreateBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void setup(Canvas canvas) {
        Bitmap bitmapFromDrawable = getBitmapFromDrawable(getDrawable());
        if (bitmapFromDrawable != null) {
            BitmapShader bitmapShader = new BitmapShader(bitmapFromDrawable, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            this.mBitmapPaint.setAntiAlias(true);
            this.mBitmapPaint.setShader(bitmapShader);
            this.mBorderPaint.setStyle(Paint.Style.FILL);
            this.mBorderPaint.setAntiAlias(true);
            this.mBorderPaint.setColor(this.mBorderColor);
            this.mBorderPaint.setStrokeWidth(this.mBorderWidth);
            this.mBitmapHeight = bitmapFromDrawable.getHeight();
            this.mBitmapWidth = bitmapFromDrawable.getWidth();
            this.mBorderRect.set(0.0f, 0.0f, getWidth(), getHeight());
            this.mBorderRadius = Math.min((this.mBorderRect.height() - this.mBorderWidth) / 2.0f, (this.mBorderRect.width() - this.mBorderWidth) / 2.0f);
            RectF rectF = this.mDrawableRect;
            int i = this.mBorderWidth;
            rectF.set(i, i, this.mBorderRect.width() - this.mBorderWidth, this.mBorderRect.height() - this.mBorderWidth);
            this.mDrawableRadius = Math.min(this.mDrawableRect.height() / 2.0f, this.mDrawableRect.width() / 2.0f);
            updateShaderMatrix(bitmapShader);
            clipDef(canvas);
        }
    }

    private void updateShaderMatrix(BitmapShader bitmapShader) {
        float fWidth;
        float fHeight;
        Matrix matrix = new Matrix();
        matrix.set(null);
        float fWidth2 = 0.0f;
        if (this.mBitmapWidth * this.mDrawableRect.height() > this.mDrawableRect.width() * this.mBitmapHeight) {
            fWidth = this.mDrawableRect.height() / this.mBitmapHeight;
            fHeight = 0.0f;
            fWidth2 = (this.mDrawableRect.width() - (this.mBitmapWidth * fWidth)) * 0.5f;
        } else {
            fWidth = this.mDrawableRect.width() / this.mBitmapWidth;
            fHeight = (this.mDrawableRect.height() - (this.mBitmapHeight * fWidth)) * 0.5f;
        }
        matrix.setScale(fWidth, fWidth);
        int i = (int) (fWidth2 + 0.5f);
        int i2 = this.mBorderWidth;
        matrix.postTranslate(i + i2, ((int) (fHeight + 0.5f)) + i2);
        bitmapShader.setLocalMatrix(matrix);
    }

    public int getBorderWidth() {
        return this.mBorderWidth;
    }
}
