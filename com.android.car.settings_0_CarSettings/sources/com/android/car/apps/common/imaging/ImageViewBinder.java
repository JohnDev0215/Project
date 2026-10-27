package com.android.car.apps.common.imaging;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Size;
import android.widget.ImageView;
import com.android.car.apps.common.CommonFlags;
import com.android.car.apps.common.R;
import com.android.car.apps.common.imaging.ImageBinder;
import com.android.car.apps.common.imaging.ImageBinder.ImageRef;

/* JADX INFO: loaded from: classes5.dex */
public class ImageViewBinder<T extends ImageBinder.ImageRef> extends ImageBinder<T> {
    private boolean mCancelled;
    private final boolean mFlagBitmaps;
    private final ImageView mImageView;
    private T mSavedRef;

    public ImageViewBinder(Size maxImageSize, ImageView imageView) {
        this(ImageBinder.PlaceholderType.FOREGROUND, maxImageSize, imageView, false);
    }

    public ImageViewBinder(ImageBinder.PlaceholderType type, Size maxImageSize, ImageView imageView, boolean flagBitmaps) {
        super(type, maxImageSize);
        this.mImageView = imageView;
        this.mFlagBitmaps = flagBitmaps;
    }

    @Override // com.android.car.apps.common.imaging.ImageBinder
    protected void setDrawable(Drawable drawable) {
        ImageView imageView = this.mImageView;
        if (imageView != null) {
            imageView.setImageDrawable(drawable);
            if (this.mFlagBitmaps) {
                CommonFlags flags = CommonFlags.getInstance(this.mImageView.getContext());
                if (flags.shouldFlagImproperImageRefs()) {
                    if (drawable instanceof BitmapDrawable) {
                        int tint = this.mImageView.getContext().getColor(R.color.improper_image_refs_tint_color);
                        this.mImageView.setColorFilter(tint);
                    } else {
                        this.mImageView.clearColorFilter();
                    }
                }
            }
        }
    }

    @Override // com.android.car.apps.common.imaging.ImageBinder
    public void setImage(Context context, T newRef) {
        this.mSavedRef = newRef;
        this.mCancelled = false;
        if (this.mImageView != null) {
            super.setImage(context, newRef);
        }
    }

    public void maybeRestartLoading(Context context) {
        if (this.mCancelled) {
            setImage(context, this.mSavedRef);
        }
    }

    public void maybeCancelLoading(Context context) {
        this.mCancelled = true;
        if (this.mImageView != null) {
            super.setImage(context, null);
        }
    }

    @Override // com.android.car.apps.common.imaging.ImageBinder
    protected void prepareForNewBinding(Context context) {
        this.mImageView.setImageBitmap(null);
        this.mImageView.setImageDrawable(null);
        this.mImageView.clearColorFilter();
        super.prepareForNewBinding(context);
    }
}
