package com.amazon.avod.content.smoothstream.manifest.acquisition;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.event.ManifestFetchedEvent;
import com.amazon.avod.content.smoothstream.downloading.SmoothStreamingDownloadExceptionUtils;
import com.amazon.avod.content.smoothstream.downloading.SmoothStreamingRequestPrioritizationUtils;
import com.amazon.avod.content.smoothstream.manifest.Manifest;
import com.amazon.avod.content.smoothstream.manifest.ManifestParser;
import com.amazon.avod.content.smoothstream.manifest.acquisition.RetryingObjectAcquirer;
import com.amazon.avod.content.smoothstream.manifest.validation.ManifestValidationException;
import com.amazon.avod.content.smoothstream.manifest.validation.ManifestValidator;
import com.amazon.avod.content.smoothstream.manifest.validation.ManifestValidatorFactory;
import com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.content.urlvending.ContentUrlSelector;
import com.amazon.avod.content.urlvending.DownloadType;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.media.service.QATriggerManifestAcquisitionFailure;
import com.amazon.avod.playback.LifecycleProfiler;
import com.amazon.avod.playback.capability.DeviceIdentity;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.pmet.LifecycleProfilerMetrics;
import com.amazon.avod.util.ExponentialBackoff;
import com.amazon.avod.util.URLUtils;
import com.google.common.base.Preconditions;
import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ManifestAcquirer implements ManifestAcquirerInterface {
    private final SmoothStreamingPlaybackConfig mConfig;
    private ContentManagementEventBus mContentEventBus;
    private final SmoothStreamingContentStore mContentStore;
    private final RetryingObjectAcquirer<Manifest> mDelegate;
    private final DeviceIdentity mDeviceIdentity;
    private final SmoothStreamingDownloadExceptionUtils mExceptionUtils;
    private final boolean mIsLiveStream;
    private final ManifestCapturerInterface mManifestCapturer;
    private final ManifestValidator mManifestValidator;
    private final MediaProfiler mMediaProfiler;
    private final ManifestParser mParser;
    private final SmoothStreamingRequestPrioritizationUtils mPrioritizationUtils;
    private final boolean mShouldPersistCdnManifestRedirects;

    public ManifestAcquirer(@Nonnull DownloadService downloadService, @Nonnull ContentManagementEventBus contentManagementEventBus, @Nonnull MediaProfiler mediaProfiler, @Nonnull SmoothStreamingContentStore smoothStreamingContentStore, @Nonnull ManifestParser manifestParser, @Nonnull ManifestCapturerInterface manifestCapturerInterface, boolean z) {
        DeviceIdentity deviceIdentity = MediaSystemSharedDependencies.getInstance().getDeviceIdentity();
        RetryingObjectAcquirer retryingObjectAcquirer = new RetryingObjectAcquirer(downloadService, contentManagementEventBus);
        SmoothStreamingDownloadExceptionUtils smoothStreamingDownloadExceptionUtils = new SmoothStreamingDownloadExceptionUtils();
        SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig = SmoothStreamingPlaybackConfig.INSTANCE;
        this(mediaProfiler, deviceIdentity, retryingObjectAcquirer, smoothStreamingContentStore, smoothStreamingDownloadExceptionUtils, manifestParser, smoothStreamingPlaybackConfig, new SmoothStreamingRequestPrioritizationUtils(smoothStreamingPlaybackConfig), new ManifestValidatorFactory().createManifestValidator(), manifestCapturerInterface, z, smoothStreamingPlaybackConfig.shouldPersistCdnManifestRedirects(), contentManagementEventBus);
    }

    ManifestAcquirer(@Nonnull MediaProfiler mediaProfiler, @Nonnull DeviceIdentity deviceIdentity, @Nonnull RetryingObjectAcquirer<Manifest> retryingObjectAcquirer, @Nonnull SmoothStreamingContentStore smoothStreamingContentStore, @Nonnull SmoothStreamingDownloadExceptionUtils smoothStreamingDownloadExceptionUtils, @Nonnull ManifestParser manifestParser, @Nonnull SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, @Nonnull SmoothStreamingRequestPrioritizationUtils smoothStreamingRequestPrioritizationUtils, @Nonnull ManifestValidator manifestValidator, @Nonnull ManifestCapturerInterface manifestCapturerInterface, boolean z, boolean z2, @Nonnull ContentManagementEventBus contentManagementEventBus) {
        this.mMediaProfiler = (MediaProfiler) Preconditions.checkNotNull(mediaProfiler, "mediaProfiler");
        this.mDeviceIdentity = (DeviceIdentity) Preconditions.checkNotNull(deviceIdentity, "deviceIdentity");
        this.mDelegate = (RetryingObjectAcquirer) Preconditions.checkNotNull(retryingObjectAcquirer, "delegate");
        this.mContentStore = (SmoothStreamingContentStore) Preconditions.checkNotNull(smoothStreamingContentStore, "contentStore");
        this.mExceptionUtils = (SmoothStreamingDownloadExceptionUtils) Preconditions.checkNotNull(smoothStreamingDownloadExceptionUtils, "exceptionUtils");
        this.mParser = (ManifestParser) Preconditions.checkNotNull(manifestParser, "parser");
        this.mConfig = (SmoothStreamingPlaybackConfig) Preconditions.checkNotNull(smoothStreamingPlaybackConfig, "config");
        this.mPrioritizationUtils = (SmoothStreamingRequestPrioritizationUtils) Preconditions.checkNotNull(smoothStreamingRequestPrioritizationUtils, "prioritizationUtils");
        this.mManifestValidator = (ManifestValidator) Preconditions.checkNotNull(manifestValidator, "manifestValidator");
        this.mManifestCapturer = (ManifestCapturerInterface) Preconditions.checkNotNull(manifestCapturerInterface, "manifestCapturer");
        this.mIsLiveStream = z;
        this.mShouldPersistCdnManifestRedirects = z2;
        this.mContentEventBus = (ContentManagementEventBus) Preconditions.checkNotNull(contentManagementEventBus, "contentEventBus");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestAcquirerInterface
    public Manifest getManifestFromUrl(@Nonnull final ContentUrlSelector contentUrlSelector, @Nonnull final String str, @Nonnull final File file, @Nonnull final PlayableContent playableContent, @Nonnull final ContentSessionType contentSessionType, boolean z, @Nonnull final DrmScheme drmScheme, @Nonnull final LifecycleProfiler lifecycleProfiler, @Nullable String str2, @Nonnull ContentType contentType) throws ContentException {
        final boolean zIsManifestDownloadCompressionEnabled = this.mConfig.isManifestDownloadCompressionEnabled();
        final boolean zSupportsCompressedManifests = this.mContentStore.supportsCompressedManifests(contentSessionType);
        RetryingObjectAcquirer.RetryingObjectAcquirerCallbacks<Manifest> retryingObjectAcquirerCallbacks = new RetryingObjectAcquirer.RetryingObjectAcquirerCallbacks<Manifest>() { // from class: com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestAcquirer.1
            @Override // com.amazon.avod.content.smoothstream.manifest.acquisition.RetryingObjectAcquirer.RetryingObjectAcquirerCallbacks
            public /* bridge */ /* synthetic */ Manifest readLocalObject(@Nullable Map map) throws ContentException {
                return readLocalObject((Map<String, List<String>>) map);
            }

            @Override // com.amazon.avod.content.smoothstream.manifest.acquisition.RetryingObjectAcquirer.RetryingObjectAcquirerCallbacks
            public boolean localObjectExists() {
                return ManifestAcquirer.this.mContentStore.isManifestAvailable(contentSessionType, file, playableContent, str);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.avod.content.smoothstream.manifest.acquisition.RetryingObjectAcquirer.RetryingObjectAcquirerCallbacks
            public Manifest readLocalObject(@Nullable Map<String, List<String>> map) throws ContentException {
                ManifestAcquirer.this.mMediaProfiler.start("rtwLoadManifestFromDisk");
                try {
                    ByteBuffer byteBufferLoadManifest = ManifestAcquirer.this.mContentStore.loadManifest(contentSessionType, file, playableContent, str);
                    ManifestAcquirer.this.mMediaProfiler.stop("rtwLoadManifestFromDisk");
                    ManifestAcquirer.this.mMediaProfiler.start("rtwParseManifest");
                    ManifestAcquirer.this.mManifestCapturer.capture(new Date().getTime(), (ByteBuffer) byteBufferLoadManifest.slice().rewind(), contentUrlSelector.getCurrentContentUrl().getSessionId(), ManifestAcquirer.this.mDeviceIdentity.getDeviceId(), getObjectUrl(contentUrlSelector.getCurrentContentUrl()), contentUrlSelector.getCurrentContentUrl().isTnf(), map);
                    LifecycleProfiler lifecycleProfiler2 = lifecycleProfiler;
                    LifecycleProfilerMetrics lifecycleProfilerMetrics = LifecycleProfilerMetrics.PARSE_MANIFEST;
                    lifecycleProfiler2.start(lifecycleProfilerMetrics);
                    try {
                        try {
                            Manifest manifest = ManifestAcquirer.this.mParser.parse(byteBufferLoadManifest, drmScheme);
                            ManifestAcquirer.this.mManifestCapturer.captureParsedData(manifest.hasMultiPeriodSupport());
                            Manifest manifestValidate = ManifestAcquirer.this.mManifestValidator.validate(manifest);
                            ManifestAcquirer.this.mMediaProfiler.stop("rtwParseManifest");
                            lifecycleProfiler.end(lifecycleProfilerMetrics);
                            ManifestAcquirer.this.mContentStore.releaseBuffer(contentSessionType, byteBufferLoadManifest);
                            return manifestValidate;
                        } catch (ManifestValidationException | IllegalArgumentException e) {
                            ManifestAcquirer.this.mManifestCapturer.uploadFromException(e);
                            throw new ContentException(ContentException.ContentError.MALFORMED_MANIFEST, e.getMessage(), null, URLUtils.nullWhenMalformed(getObjectUrl(contentUrlSelector.getCurrentContentUrl())));
                        }
                    } catch (Throwable th) {
                        ManifestAcquirer.this.mMediaProfiler.stop("rtwParseManifest");
                        lifecycleProfiler.end(LifecycleProfilerMetrics.PARSE_MANIFEST);
                        ManifestAcquirer.this.mContentStore.releaseBuffer(contentSessionType, byteBufferLoadManifest);
                        throw th;
                    }
                } catch (Throwable th2) {
                    ManifestAcquirer.this.mMediaProfiler.stop("rtwLoadManifestFromDisk");
                    throw th2;
                }
            }

            @Override // com.amazon.avod.content.smoothstream.manifest.acquisition.RetryingObjectAcquirer.RetryingObjectAcquirerCallbacks
            public void deleteLocalObject() {
                ManifestAcquirer.this.mContentStore.deleteErroredContent(contentSessionType, file, playableContent, str);
            }

            @Override // com.amazon.avod.content.smoothstream.manifest.acquisition.RetryingObjectAcquirer.RetryingObjectAcquirerCallbacks
            public String getObjectUrl(ContentUrl contentUrl) {
                if (ManifestAcquirer.this.mConfig.isManifestUrlOverrideEnabled()) {
                    return ManifestAcquirer.this.mConfig.getManifestUrlOverrideValue();
                }
                return contentUrl.getUrl();
            }

            @Override // com.amazon.avod.content.smoothstream.manifest.acquisition.RetryingObjectAcquirer.RetryingObjectAcquirerCallbacks
            public Map<String, String> getObjectHeaders() {
                return Collections.emptyMap();
            }

            @Override // com.amazon.avod.media.downloadservice.SaveCallback
            public void save(InputStream inputStream, int i) throws ContentException {
                ManifestAcquirer.this.mContentStore.storeManifest(contentSessionType, file, playableContent, str, inputStream, i, zIsManifestDownloadCompressionEnabled && zSupportsCompressedManifests);
                lifecycleProfiler.end(LifecycleProfilerMetrics.ACQUIRE_MANIFEST);
            }

            @Override // com.amazon.avod.content.smoothstream.manifest.acquisition.RetryingObjectAcquirer.RetryingObjectAcquirerCallbacks
            public ContentException translateException(Exception exc, @Nullable DownloadStatistics downloadStatistics) {
                return ManifestAcquirer.this.mExceptionUtils.translateExceptionForManifest(exc, null, downloadStatistics);
            }
        };
        this.mMediaProfiler.start("rtwAcquireManifest");
        lifecycleProfiler.start(LifecycleProfilerMetrics.ACQUIRE_MANIFEST);
        try {
            QATriggerManifestAcquisitionFailure.getInstance().blockManifestAcquisitionIfEnabled();
            Manifest manifestAcquireObject = this.mDelegate.acquireObject(contentUrlSelector, playableContent, contentSessionType, this.mConfig.getNumberOfManifestDownloadTries(this.mIsLiveStream), this.mConfig.getStreamingRetryDelayBackoff(), this.mConfig.isManifestRetryWithExponentialBackoffAllowed() ? new ExponentialBackoff(this.mConfig.getManifestDownloadRetryMinBackoffMillis().getTotalMilliseconds(), this.mConfig.getManifestDownloadRetryMaxBackoffMillis().getTotalMilliseconds(), this.mConfig.getManifestDownloadRetryBackoffGrowthFactor()) : null, retryingObjectAcquirerCallbacks, this.mPrioritizationUtils.computeManifestPriority(contentSessionType), !zIsManifestDownloadCompressionEnabled, !zSupportsCompressedManifests, this.mConfig.requestEncodedDashManifest(), this.mConfig.shouldRequestSegmentListManifest(), this.mIsLiveStream ? this.mConfig.getLiveManifestDownloadTimeout() : this.mConfig.getManifestDownloadTimeout(), z, str2, DownloadType.MANIFEST, contentType, !ContentType.isLive(contentType) && this.mConfig.isManifestResponseCacheEnabled(), true, this.mIsLiveStream && this.mShouldPersistCdnManifestRedirects);
            this.mContentEventBus.postEvent(new ManifestFetchedEvent(manifestAcquireObject));
            return manifestAcquireObject;
        } finally {
            this.mMediaProfiler.stop("rtwAcquireManifest");
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestAcquirerInterface
    public void shutdown() {
        this.mManifestCapturer.shutdown();
    }
}
