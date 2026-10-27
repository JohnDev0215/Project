package com.facebook.drawee.backends.pipeline.info;

import com.facebook.fresco.ui.common.ControllerListener2;
import com.facebook.fresco.ui.common.DimensionsInfo;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class ImagePerfData {

    @Nullable
    private final Object mCallerContext;

    @Nullable
    private final String mComponentTag;
    private final long mControllerCancelTimeMs;
    private final long mControllerFailureTimeMs;
    private final long mControllerFinalImageSetTimeMs;

    @Nullable
    private final ImageRequest[] mControllerFirstAvailableImageRequests;

    @Nullable
    private final String mControllerId;

    @Nullable
    private final ImageRequest mControllerImageRequest;
    private final long mControllerIntermediateImageSetTimeMs;

    @Nullable
    private final ImageRequest mControllerLowResImageRequest;
    private final long mControllerSubmitTimeMs;

    @Nullable
    private final DimensionsInfo mDimensionsInfo;

    @Nullable
    private final Throwable mErrorThrowable;

    @Nullable
    private ControllerListener2.Extras mExtraData;
    private final long mImageDrawTimeMs;

    @Nullable
    private final ImageInfo mImageInfo;
    private final int mImageOrigin;

    @Nullable
    private final ImageRequest mImageRequest;
    private final long mImageRequestEndTimeMs;
    private final long mImageRequestStartTimeMs;
    private final long mInvisibilityEventTimeMs;
    private final boolean mIsPrefetch;
    private final int mOnScreenHeightPx;
    private final int mOnScreenWidthPx;

    @Nullable
    private final String mRequestId;

    @Nullable
    private final String mUltimateProducerName;
    private final long mVisibilityEventTimeMs;
    private final int mVisibilityState;

    public ImagePerfData(@Nullable String controllerId, @Nullable String requestId, @Nullable ImageRequest imageRequest, @Nullable Object callerContext, @Nullable ImageInfo imageInfo, @Nullable ImageRequest controllerImageRequest, @Nullable ImageRequest controllerLowResImageRequest, @Nullable ImageRequest[] controllerFirstAvailableImageRequests, long controllerSubmitTimeMs, long controllerIntermediateImageSetTimeMs, long controllerFinalImageSetTimeMs, long controllerFailureTimeMs, long controllerCancelTimeMs, long imageRequestStartTimeMs, long imageRequestEndTimeMs, int imageOrigin, @Nullable String ultimateProducerName, boolean isPrefetch, int onScreenWidthPx, int onScreenHeightPx, @Nullable Throwable errorThrowable, int visibilityState, long visibilityEventTimeMs, long invisibilityEventTime, @Nullable String componentTag, long imageDrawTimeMs, @Nullable DimensionsInfo dimensionsInfo, @Nullable ControllerListener2.Extras extraData) {
        this.mControllerId = controllerId;
        this.mRequestId = requestId;
        this.mImageRequest = imageRequest;
        this.mCallerContext = callerContext;
        this.mImageInfo = imageInfo;
        this.mControllerImageRequest = controllerImageRequest;
        this.mControllerLowResImageRequest = controllerLowResImageRequest;
        this.mControllerFirstAvailableImageRequests = controllerFirstAvailableImageRequests;
        this.mControllerSubmitTimeMs = controllerSubmitTimeMs;
        this.mControllerIntermediateImageSetTimeMs = controllerIntermediateImageSetTimeMs;
        this.mControllerFinalImageSetTimeMs = controllerFinalImageSetTimeMs;
        this.mControllerFailureTimeMs = controllerFailureTimeMs;
        this.mControllerCancelTimeMs = controllerCancelTimeMs;
        this.mImageRequestStartTimeMs = imageRequestStartTimeMs;
        this.mImageRequestEndTimeMs = imageRequestEndTimeMs;
        this.mImageOrigin = imageOrigin;
        this.mUltimateProducerName = ultimateProducerName;
        this.mIsPrefetch = isPrefetch;
        this.mOnScreenWidthPx = onScreenWidthPx;
        this.mOnScreenHeightPx = onScreenHeightPx;
        this.mErrorThrowable = errorThrowable;
        this.mVisibilityState = visibilityState;
        this.mVisibilityEventTimeMs = visibilityEventTimeMs;
        this.mInvisibilityEventTimeMs = invisibilityEventTime;
        this.mComponentTag = componentTag;
        this.mImageDrawTimeMs = imageDrawTimeMs;
        this.mDimensionsInfo = dimensionsInfo;
        this.mExtraData = extraData;
    }

    public int getImageOrigin() {
        return this.mImageOrigin;
    }

    public int getOnScreenWidthPx() {
        return this.mOnScreenWidthPx;
    }

    public int getOnScreenHeightPx() {
        return this.mOnScreenHeightPx;
    }

    public int getVisibilityState() {
        return this.mVisibilityState;
    }
}
