package com.amazon.avod.content.smoothstream.downloading;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.downloading.FragmentValidator;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.quality.FragmentDownloadParameters;
import com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.media.AudioStreamType;
import com.amazon.avod.media.ExternalFourCCMapper;
import com.amazon.avod.media.VideoStreamType;
import com.amazon.avod.media.downloadservice.DownloadCallback;
import com.amazon.avod.media.downloadservice.DownloadListener;
import com.amazon.avod.media.downloadservice.DownloadRequest;
import com.amazon.avod.media.downloadservice.DownloadRequestPriority;
import com.amazon.avod.media.framework.memory.GrowableBuffer;
import com.amazon.avod.media.framework.resources.SurgingResourcePool;
import com.amazon.avod.util.URLUtils;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.io.Closeables;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
class SmoothStreamingLazyFragmentRequest implements DownloadRequest {
    private final SurgingResourcePool<GrowableBuffer> mBufferPool;
    private final SmoothStreamingContentStore mContentStore;
    private ContentSessionContext mContext;
    private FragmentDownloadController mController;
    private FragmentDownloadParameters mDownloadParams;
    private DownloadRequestPriority mEnqueuePriority;
    private DownloadListener mListener;
    private ContentUrl mManifestUrl;
    private final SmoothStreamingRequestPrioritizationUtils mPrioritizationUtils;
    private final boolean mShouldAllowHttpRedirects;
    private final long mTimeOutInNanos;
    private int mTryCount;
    private SmoothStreamingURI mUri;
    private final FragmentValidator mValidator;

    @Override // com.amazon.avod.media.downloadservice.DownloadRequest
    public boolean shouldDisableCompression() {
        return true;
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadRequest
    public boolean shouldPersistHttpRedirects() {
        return false;
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadRequest
    public boolean shouldUncompressResponse() {
        return true;
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadRequest
    public boolean shouldUseHttpResponseCache() {
        return false;
    }

    SmoothStreamingLazyFragmentRequest(FragmentValidator fragmentValidator, SmoothStreamingContentStore smoothStreamingContentStore, SmoothStreamingRequestPrioritizationUtils smoothStreamingRequestPrioritizationUtils, long j, SurgingResourcePool<GrowableBuffer> surgingResourcePool, boolean z) {
        this.mValidator = fragmentValidator;
        this.mContentStore = smoothStreamingContentStore;
        this.mPrioritizationUtils = smoothStreamingRequestPrioritizationUtils;
        this.mTimeOutInNanos = j;
        this.mBufferPool = surgingResourcePool;
        this.mShouldAllowHttpRedirects = z;
    }

    public void initialize(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI, int i, FragmentDownloadController fragmentDownloadController, DownloadListener downloadListener) {
        this.mController = fragmentDownloadController;
        this.mListener = downloadListener;
        this.mContext = contentSessionContext;
        this.mTryCount = i;
        this.mUri = smoothStreamingURI;
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadRequest
    @Nonnull
    public String getUrl() {
        initDownloadParams();
        return getAdjustedUri().getAbsoluteUrl(getManifestUrl().getUrl());
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadRequest
    @Nonnull
    public Map<String, String> getHeaders() {
        initDownloadParams();
        return getAdjustedUri().getDownloadHeaders();
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadRequest
    public long getExpectedDownloadSize() {
        return getAdjustedUri().getDownloadChunkSize();
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadRequest
    public long getTimeoutInNanos() {
        initDownloadParams();
        return this.mDownloadParams.getSlowFragmentTimeoutInNanos();
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadRequest
    @Nonnull
    public DownloadCallback getDownloadCallback() {
        return new DownloadCallback() { // from class: com.amazon.avod.content.smoothstream.downloading.SmoothStreamingLazyFragmentRequest$$ExternalSyntheticLambda0
            @Override // com.amazon.avod.media.downloadservice.SaveCallback
            public final void save(InputStream inputStream, int i) throws Throwable {
                this.f$0.lambda$getDownloadCallback$0(inputStream, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getDownloadCallback$0(InputStream inputStream, int i) throws Throwable {
        InputStream inputStreamOpenValidatedStream;
        SmoothStreamingURI adjustedUri = getAdjustedUri();
        URL urlNullWhenMalformed = URLUtils.nullWhenMalformed(adjustedUri.getAbsoluteUrl(getManifestUrl().getUrl()));
        if (ContentUrl.isDashUrl(this.mManifestUrl)) {
            this.mContentStore.validateAndStoreFragment(this.mContext, adjustedUri, inputStream, i, this.mValidator, this.mManifestUrl.getUrl());
            return;
        }
        GrowableBuffer growableBufferRequestResource = this.mBufferPool.requestResource();
        InputStream inputStream2 = null;
        try {
            inputStreamOpenValidatedStream = this.mValidator.openValidatedStream(inputStream, i, this.mUri.getStream().isAudio(), growableBufferRequestResource, urlNullWhenMalformed);
        } catch (Throwable th) {
            th = th;
        }
        try {
            this.mContentStore.storeFragment(this.mContext, adjustedUri, inputStreamOpenValidatedStream, i, this.mManifestUrl.getUrl());
            this.mBufferPool.releaseResource(growableBufferRequestResource);
            Closeables.closeQuietly(inputStreamOpenValidatedStream);
        } catch (Throwable th2) {
            th = th2;
            inputStream2 = inputStreamOpenValidatedStream;
            this.mBufferPool.releaseResource(growableBufferRequestResource);
            Closeables.closeQuietly(inputStream2);
            throw th;
        }
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadRequest
    @Nonnull
    public DownloadListener getListener() {
        return this.mListener;
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadRequest
    @Nonnull
    public DownloadRequestPriority getPriority() {
        return this.mPrioritizationUtils.computeFragmentPriority(this.mContext.getEffectiveSessionType(), getAdjustedUri(), this.mContext.getState().getPlayPositionInNanos());
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadRequest
    @Nonnull
    public DownloadRequestPriority getEnqueuePriority() {
        DownloadRequestPriority downloadRequestPriority = this.mEnqueuePriority;
        return downloadRequestPriority != null ? downloadRequestPriority : getPriority();
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadRequest
    public void setEnqueuePriority(@Nonnull DownloadRequestPriority downloadRequestPriority) {
        this.mEnqueuePriority = (DownloadRequestPriority) Preconditions.checkNotNull(downloadRequestPriority, "enqueuePriority");
    }

    public ContentUrl getManifestUrl() {
        initDownloadParams();
        return this.mManifestUrl;
    }

    public SmoothStreamingURI getAdjustedUri() {
        initDownloadParams();
        return this.mDownloadParams.getSmoothStreamingURI();
    }

    protected synchronized void initDownloadParams() {
        if (this.mDownloadParams == null) {
            this.mDownloadParams = this.mController.getDownloadParameters(this.mUri, this.mTryCount, this.mTimeOutInNanos);
            this.mManifestUrl = this.mController.getManifestUrl();
        }
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadRequest
    public int getStatsTag() {
        return getStatsTagFromFourCC(getAdjustedUri().getQualityLevel().getFourCC());
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadRequest
    public boolean shouldAllowHttpRedirects() {
        return this.mShouldAllowHttpRedirects;
    }

    static int getStatsTagFromFourCC(String str) {
        if (!Strings.isNullOrEmpty(str)) {
            AudioStreamType audioStreamTypeFromFourCC = AudioStreamType.INSTANCE.fromFourCC(str);
            VideoStreamType videoStreamTypeMapVideoCodecToStreamType = ExternalFourCCMapper.INSTANCE.mapVideoCodecToStreamType(str);
            if (audioStreamTypeFromFourCC == AudioStreamType.DDP) {
                return 268439235;
            }
            if (audioStreamTypeFromFourCC == AudioStreamType.AACH || audioStreamTypeFromFourCC == AudioStreamType.AACHV2 || audioStreamTypeFromFourCC == AudioStreamType.AACL) {
                return 268438188;
            }
            if (videoStreamTypeMapVideoCodecToStreamType == VideoStreamType.H264) {
                return 268436068;
            }
            if (videoStreamTypeMapVideoCodecToStreamType == VideoStreamType.H265) {
                return 268436069;
            }
            if (videoStreamTypeMapVideoCodecToStreamType == VideoStreamType.AV01) {
                return 268436070;
            }
        }
        return 268435456;
    }
}
