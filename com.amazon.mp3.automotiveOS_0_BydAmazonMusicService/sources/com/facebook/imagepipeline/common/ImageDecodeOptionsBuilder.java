package com.facebook.imagepipeline.common;

import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import com.facebook.imagepipeline.common.ImageDecodeOptionsBuilder;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.transformation.BitmapTransformation;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes3.dex */
public class ImageDecodeOptionsBuilder<T extends ImageDecodeOptionsBuilder> {
    private Bitmap.Config mAnimatedBitmapConfig;
    private Bitmap.Config mBitmapConfig;

    @Nullable
    private BitmapTransformation mBitmapTransformation;

    @Nullable
    private ColorSpace mColorSpace;

    @Nullable
    private ImageDecoder mCustomImageDecoder;
    private boolean mDecodeAllFrames;
    private boolean mDecodePreviewFrame;
    private boolean mExcludeBitmapConfigFromComparison;
    private boolean mForceStaticImage;
    private boolean mUseLastFrameForPreview;
    private int mMinDecodeIntervalMs = 100;
    private int mMaxDimensionPx = Integer.MAX_VALUE;

    public ImageDecodeOptionsBuilder() {
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        this.mBitmapConfig = config;
        this.mAnimatedBitmapConfig = config;
    }

    public int getMinDecodeIntervalMs() {
        return this.mMinDecodeIntervalMs;
    }

    public int getMaxDimensionPx() {
        return this.mMaxDimensionPx;
    }

    public boolean getDecodePreviewFrame() {
        return this.mDecodePreviewFrame;
    }

    public boolean getUseLastFrameForPreview() {
        return this.mUseLastFrameForPreview;
    }

    public boolean getDecodeAllFrames() {
        return this.mDecodeAllFrames;
    }

    @Nullable
    public ImageDecoder getCustomImageDecoder() {
        return this.mCustomImageDecoder;
    }

    public boolean getForceStaticImage() {
        return this.mForceStaticImage;
    }

    public Bitmap.Config getBitmapConfig() {
        return this.mBitmapConfig;
    }

    public Bitmap.Config getAnimatedBitmapConfig() {
        return this.mAnimatedBitmapConfig;
    }

    @Nullable
    public BitmapTransformation getBitmapTransformation() {
        return this.mBitmapTransformation;
    }

    @Nullable
    public ColorSpace getColorSpace() {
        return this.mColorSpace;
    }

    public boolean getExcludeBitmapConfigFromComparison() {
        return this.mExcludeBitmapConfigFromComparison;
    }

    public ImageDecodeOptions build() {
        return new ImageDecodeOptions(this);
    }
}
