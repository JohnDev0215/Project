package com.facebook.drawee.generic;

import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import com.facebook.common.internal.Preconditions;
import com.facebook.drawee.drawable.ScalingUtils;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class GenericDraweeHierarchyBuilder {

    @Nullable
    private ColorFilter mActualImageColorFilter;

    @Nullable
    private PointF mActualImageFocusPoint;

    @Nullable
    private Matrix mActualImageMatrix;

    @Nullable
    private ScalingUtils.ScaleType mActualImageScaleType;

    @Nullable
    private Drawable mBackground;
    private float mDesiredAspectRatio;
    private int mFadeDuration;

    @Nullable
    private Drawable mFailureImage;

    @Nullable
    private ScalingUtils.ScaleType mFailureImageScaleType;

    @Nullable
    private List<Drawable> mOverlays;

    @Nullable
    private Drawable mPlaceholderImage;

    @Nullable
    private ScalingUtils.ScaleType mPlaceholderImageScaleType;

    @Nullable
    private Drawable mPressedStateOverlay;

    @Nullable
    private Drawable mProgressBarImage;

    @Nullable
    private ScalingUtils.ScaleType mProgressBarImageScaleType;
    private Resources mResources;

    @Nullable
    private Drawable mRetryImage;

    @Nullable
    private ScalingUtils.ScaleType mRetryImageScaleType;

    @Nullable
    private RoundingParams mRoundingParams;
    public static final ScalingUtils.ScaleType DEFAULT_SCALE_TYPE = ScalingUtils.ScaleType.CENTER_INSIDE;
    public static final ScalingUtils.ScaleType DEFAULT_ACTUAL_IMAGE_SCALE_TYPE = ScalingUtils.ScaleType.CENTER_CROP;

    public GenericDraweeHierarchyBuilder(Resources resources) {
        this.mResources = resources;
        init();
    }

    public static GenericDraweeHierarchyBuilder newInstance(Resources resources) {
        return new GenericDraweeHierarchyBuilder(resources);
    }

    private void init() {
        this.mFadeDuration = 300;
        this.mDesiredAspectRatio = 0.0f;
        this.mPlaceholderImage = null;
        ScalingUtils.ScaleType scaleType = DEFAULT_SCALE_TYPE;
        this.mPlaceholderImageScaleType = scaleType;
        this.mRetryImage = null;
        this.mRetryImageScaleType = scaleType;
        this.mFailureImage = null;
        this.mFailureImageScaleType = scaleType;
        this.mProgressBarImage = null;
        this.mProgressBarImageScaleType = scaleType;
        this.mActualImageScaleType = DEFAULT_ACTUAL_IMAGE_SCALE_TYPE;
        this.mActualImageMatrix = null;
        this.mActualImageFocusPoint = null;
        this.mActualImageColorFilter = null;
        this.mBackground = null;
        this.mOverlays = null;
        this.mPressedStateOverlay = null;
        this.mRoundingParams = null;
    }

    public Resources getResources() {
        return this.mResources;
    }

    public int getFadeDuration() {
        return this.mFadeDuration;
    }

    public float getDesiredAspectRatio() {
        return this.mDesiredAspectRatio;
    }

    @Nullable
    public Drawable getPlaceholderImage() {
        return this.mPlaceholderImage;
    }

    @Nullable
    public ScalingUtils.ScaleType getPlaceholderImageScaleType() {
        return this.mPlaceholderImageScaleType;
    }

    @Nullable
    public Drawable getRetryImage() {
        return this.mRetryImage;
    }

    @Nullable
    public ScalingUtils.ScaleType getRetryImageScaleType() {
        return this.mRetryImageScaleType;
    }

    @Nullable
    public Drawable getFailureImage() {
        return this.mFailureImage;
    }

    @Nullable
    public ScalingUtils.ScaleType getFailureImageScaleType() {
        return this.mFailureImageScaleType;
    }

    @Nullable
    public Drawable getProgressBarImage() {
        return this.mProgressBarImage;
    }

    @Nullable
    public ScalingUtils.ScaleType getProgressBarImageScaleType() {
        return this.mProgressBarImageScaleType;
    }

    @Nullable
    public ScalingUtils.ScaleType getActualImageScaleType() {
        return this.mActualImageScaleType;
    }

    @Nullable
    public PointF getActualImageFocusPoint() {
        return this.mActualImageFocusPoint;
    }

    @Nullable
    public ColorFilter getActualImageColorFilter() {
        return this.mActualImageColorFilter;
    }

    @Nullable
    public Drawable getBackground() {
        return this.mBackground;
    }

    @Nullable
    public List<Drawable> getOverlays() {
        return this.mOverlays;
    }

    @Nullable
    public Drawable getPressedStateOverlay() {
        return this.mPressedStateOverlay;
    }

    public GenericDraweeHierarchyBuilder setRoundingParams(@Nullable RoundingParams roundingParams) {
        this.mRoundingParams = roundingParams;
        return this;
    }

    @Nullable
    public RoundingParams getRoundingParams() {
        return this.mRoundingParams;
    }

    private void validate() {
        List<Drawable> list = this.mOverlays;
        if (list != null) {
            Iterator<Drawable> it = list.iterator();
            while (it.hasNext()) {
                Preconditions.checkNotNull(it.next());
            }
        }
    }

    public GenericDraweeHierarchy build() {
        validate();
        return new GenericDraweeHierarchy(this);
    }
}
