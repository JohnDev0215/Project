package com.android.car.apps.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Size;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;

/* JADX INFO: loaded from: classes5.dex */
public class BackgroundImageView extends ConstraintLayout {
    private float mBitmapBlurPercent;
    private Size mBitmapTargetSize;
    private View mDarkeningScrim;
    private CrossfadeImageView mImageView;

    public BackgroundImageView(Context context) {
        this(context, null);
    }

    public BackgroundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.backgroundImageViewStyle);
    }

    public BackgroundImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.BackgroundImageView, defStyle, 0);
        try {
            float extraScale = a.getFloat(R.styleable.BackgroundImageView_imageAdditionalScale, 1.05f);
            int resId = a.getResourceId(R.styleable.BackgroundImageView_contentLayout, R.layout.background_image);
            this.mBitmapBlurPercent = a.getFloat(R.styleable.BackgroundImageView_bitmap_blur_percent, getResources().getFloat(R.dimen.background_bitmap_blur_percent));
            int size = a.getInteger(R.styleable.BackgroundImageView_bitmap_target_size_px, getResources().getInteger(R.integer.background_bitmap_target_size_px));
            this.mBitmapTargetSize = new Size(size, size);
            a.recycle();
            inflate(getContext(), resId, this);
            this.mImageView = (CrossfadeImageView) findViewById(R.id.background_image_image);
            this.mDarkeningScrim = findViewById(R.id.background_image_darkening_scrim);
            setImageAdditionalScale(extraScale);
        } catch (Throwable th) {
            a.recycle();
            throw th;
        }
    }

    @Deprecated
    public void setBackgroundImage(Bitmap bitmap, boolean showAnimation) {
        Drawable drawable = bitmap != null ? new BitmapDrawable(bitmap) : null;
        updateBlur(drawable, showAnimation);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        setBackgroundDrawable(drawable, true);
    }

    public void setBackgroundDrawable(Drawable drawable, boolean showAnimation) {
        updateBlur(drawable, showAnimation);
    }

    private void updateBlur(Drawable drawable, boolean showAnimation) {
        if (drawable == null) {
            this.mImageView.setImageBitmap(null, false);
            return;
        }
        Bitmap src = BitmapUtils.fromDrawable(drawable, this.mBitmapTargetSize);
        Bitmap blurred = ImageUtils.blur(getContext(), src, this.mBitmapTargetSize, this.mBitmapBlurPercent);
        this.mImageView.setImageBitmap(blurred, showAnimation);
        invalidate();
        requestLayout();
    }

    @Override // android.view.View
    public void setBackgroundColor(int color) {
        this.mImageView.setBackgroundColor(color);
    }

    public void setDimmed(boolean dim) {
        this.mDarkeningScrim.setVisibility(dim ? 0 : 8);
    }

    public void setImageAdditionalScale(float scale) {
        this.mImageView.setImageAdditionalScale(scale);
    }
}
