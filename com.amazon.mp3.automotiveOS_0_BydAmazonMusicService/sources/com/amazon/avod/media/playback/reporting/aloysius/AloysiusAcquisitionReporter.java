package com.amazon.avod.media.playback.reporting.aloysius;

import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat;
import androidx.core.util.Pair;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.event.ContentEventErrorBase;
import com.amazon.avod.content.event.ContentEventFragmentDownloaded;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.event.DrmLicenseDownloadEvent;
import com.amazon.avod.content.event.FatalContentEventError;
import com.amazon.avod.content.event.FragmentInformationHolder;
import com.amazon.avod.content.event.RetriableContentEventError;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel;
import com.amazon.avod.content.smoothstream.manifest.acquisition.events.NewContentDownloadedEvent;
import com.amazon.avod.event.AdEventTransport;
import com.amazon.avod.http.HttpStatusCodeException;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.media.downloadservice.exceptions.InvalidCDNResponseException;
import com.amazon.avod.media.downloadservice.exceptions.NetworkIOException;
import com.amazon.avod.media.downloadservice.exceptions.NetworkResponseException;
import com.amazon.avod.media.downloadservice.exceptions.NoDataConnectionException;
import com.amazon.avod.media.events.AloysiusConfig;
import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.events.MediaEventReporter;
import com.amazon.avod.media.events.model.MediaEvent;
import com.amazon.avod.playback.event.playback.AdAcquisitionDownloadedEvent;
import com.amazon.avod.playback.event.playback.AdAcquisitionErroredEvent;
import com.amazon.avod.playback.event.playback.AdVastManifestDownloadedEvent;
import com.amazon.avod.playback.session.ConsumptionIdProvider;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.JSONUtils;
import com.amazon.bolthttp.BoltException;
import com.amazon.bolthttp.policy.RetryLimitException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.eventbus.Subscribe;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AloysiusAcquisitionReporter implements MediaEventReporter {
    private static final int ACQUISITION_RESPONSE_HEADER_KEY_STRING_LENGTH = 50;
    private static final int ACQUISITION_RESPONSE_HEADER_VALUE_STRING_LENGTH = 200;
    private final AdEventTransport mAdEventTransport;
    private final ConsumptionIdProvider mConsumptionIdProvider;
    private final ContentManagementEventBus mEventBus;
    private final MediaEventQueue mMediaEventQueue;
    private final boolean mShouldReportFragmentInformation;
    private final boolean mShouldReportQualityScore;
    private final TimeUnit nanoConverter;

    private long getBytesProcessed(long j) {
        if (j > 0) {
            return j;
        }
        return 0L;
    }

    public AloysiusAcquisitionReporter(@Nonnull MediaEventQueue mediaEventQueue, @Nullable ContentManagementEventBus contentManagementEventBus, @Nullable AdEventTransport adEventTransport, @Nonnull ConsumptionIdProvider consumptionIdProvider) {
        this(mediaEventQueue, contentManagementEventBus, adEventTransport, AloysiusConfig.getInstance().shouldReportFragmentInforamtion(), AloysiusConfig.getInstance().shouldReportQualityScore(), consumptionIdProvider);
    }

    @VisibleForTesting
    AloysiusAcquisitionReporter(@Nonnull MediaEventQueue mediaEventQueue, @Nullable ContentManagementEventBus contentManagementEventBus, @Nullable AdEventTransport adEventTransport, boolean z, boolean z2, @Nonnull ConsumptionIdProvider consumptionIdProvider) {
        this.nanoConverter = TimeUnit.NANOSECONDS;
        this.mMediaEventQueue = mediaEventQueue;
        this.mEventBus = contentManagementEventBus;
        this.mAdEventTransport = adEventTransport;
        this.mShouldReportFragmentInformation = z;
        this.mShouldReportQualityScore = z2;
        this.mConsumptionIdProvider = (ConsumptionIdProvider) Preconditions.checkNotNull(consumptionIdProvider, "consumptionIdProvider");
    }

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void initialize() {
        ContentManagementEventBus contentManagementEventBus = this.mEventBus;
        if (contentManagementEventBus != null) {
            contentManagementEventBus.registerEventBusHandler(this);
        }
        AdEventTransport adEventTransport = this.mAdEventTransport;
        if (adEventTransport != null) {
            adEventTransport.registerEventBusHandler(this);
        }
    }

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void terminate() {
        ContentManagementEventBus contentManagementEventBus = this.mEventBus;
        if (contentManagementEventBus != null) {
            contentManagementEventBus.unregisterEventBusHandler(this);
        }
        AdEventTransport adEventTransport = this.mAdEventTransport;
        if (adEventTransport != null) {
            adEventTransport.unregisterEventBusHandler(this);
        }
    }

    private MediaEvent buildDrmAcquisitionMediaEvent(AcquisitionMediaEvent.Response.Type type, DownloadStatistics downloadStatistics, @Nonnull String str, @Nullable String str2, boolean z, int i) throws MalformedURLException {
        AnonymousClass1 anonymousClass1 = null;
        AcquisitionMediaEvent.Builder builder = new AcquisitionMediaEvent.Builder(anonymousClass1);
        AcquisitionMediaEvent.Request.Builder builder2 = new AcquisitionMediaEvent.Request.Builder(anonymousClass1);
        builder.asset(AcquisitionMediaEvent.Asset.Type.DRMLicense, str2, z);
        builder2.server(getHostname(str));
        builder2.url(str);
        builder.request(builder2.build());
        builder.response(type, Integer.valueOf(i), Long.valueOf(getBytesProcessed(downloadStatistics.getBytesProcessed())), getFormattedHeadersFromMapList(downloadStatistics.getUnformattedHeaders()));
        builder.performance(this.nanoConverter.toMillis(downloadStatistics.getLatencyInNanos()), this.nanoConverter.toMillis(downloadStatistics.getTotalTimeInNanos()));
        return builder.build();
    }

    @Subscribe
    public void handleContentEventDrmLicenseDownload(@Nonnull DrmLicenseDownloadEvent drmLicenseDownloadEvent) {
        Preconditions.checkNotNull(drmLicenseDownloadEvent, NotificationCompat.CATEGORY_EVENT);
        try {
            AcquisitionMediaEvent.Response.Type type = drmLicenseDownloadEvent.isSuccess() ? AcquisitionMediaEvent.Response.Type.Success : AcquisitionMediaEvent.Response.Type.UnknownError;
            String consumptionId = this.mConsumptionIdProvider.getConsumptionId() != null ? this.mConsumptionIdProvider.getConsumptionId() : "";
            if (drmLicenseDownloadEvent.getConsumptionId() != null) {
                consumptionId = drmLicenseDownloadEvent.getConsumptionId();
            }
            this.mMediaEventQueue.add(buildDrmAcquisitionMediaEvent(type, drmLicenseDownloadEvent.getStatistics(), drmLicenseDownloadEvent.getUrl(), consumptionId, drmLicenseDownloadEvent.isCaching(), drmLicenseDownloadEvent.getResponseCode()));
        } catch (MalformedURLException e) {
            DLog.exceptionf(e, "bad url syntax on DRM getContentUrl.getUrl() method", new Object[0]);
        }
    }

    @Subscribe
    public void handleFragmentDownloadedEvent(ContentEventFragmentDownloaded contentEventFragmentDownloaded) {
        FragmentInformationHolder fragmentInformationHolder = contentEventFragmentDownloaded.getFragmentInformationHolder();
        if (fragmentInformationHolder.getContentUrl() != null) {
            try {
                AcquisitionMediaEvent.Builder builder = new AcquisitionMediaEvent.Builder(null);
                AcquisitionMediaEvent.Asset.Type assetType = getAssetType(fragmentInformationHolder.getStreamType());
                if (assetType == null) {
                    DLog.errorf("discarding fragment downloaded event. Reason: stream type is UNSUPPORTED");
                    return;
                }
                builder.asset(assetType, fragmentInformationHolder.getContentUrl().getSessionId(), false);
                builder.request(createRequest(fragmentInformationHolder));
                builder.response(AcquisitionMediaEvent.Response.Type.Success, 200, Long.valueOf(getBytesProcessed(contentEventFragmentDownloaded.getFragmentSizeBytes())), getFormattedHeadersFromMapList(fragmentInformationHolder.getUnformattedDownloadHeaders()));
                builder.performance(this.nanoConverter.toMillis(contentEventFragmentDownloaded.getTimeToTheFirstByteInNanos()), this.nanoConverter.toMillis(contentEventFragmentDownloaded.getDownloadTimeInNanos()));
                this.mMediaEventQueue.add(builder.build());
            } catch (MalformedURLException e) {
                DLog.exceptionf(e, "bad url syntax on fragmentInfo.getContentUrl.getUrl() method, url: %s", fragmentInformationHolder.getAbsoluteUrl());
            }
        }
    }

    private AcquisitionMediaEvent.Asset.Type getAssetType(StreamType streamType) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$content$smoothstream$manifest$StreamType[streamType.ordinal()];
        if (i == 1) {
            return AcquisitionMediaEvent.Asset.Type.Audio;
        }
        if (i == 2) {
            return AcquisitionMediaEvent.Asset.Type.Video;
        }
        if (i != 3) {
            return null;
        }
        return AcquisitionMediaEvent.Asset.Type.TimedText;
    }

    @Subscribe
    public void handleNewContentDownloadedEvent(NewContentDownloadedEvent newContentDownloadedEvent) {
        if (isManifestUrl(newContentDownloadedEvent.getContentUrl().getUrl())) {
            try {
                DownloadStatistics downloadStatistics = newContentDownloadedEvent.getDownloadStatistics();
                AnonymousClass1 anonymousClass1 = null;
                AcquisitionMediaEvent.Builder builder = new AcquisitionMediaEvent.Builder(anonymousClass1);
                builder.asset(AcquisitionMediaEvent.Asset.Type.Manifest, newContentDownloadedEvent.getContentUrl().getSessionId(), false);
                builder.request(new AcquisitionMediaEvent.Request.Builder(anonymousClass1).server(getHostname(newContentDownloadedEvent.getContentUrl().getUrl())).url(newContentDownloadedEvent.getContentUrl().getUrl()).build());
                builder.response(AcquisitionMediaEvent.Response.Type.Success, 200, Long.valueOf(getBytesProcessed(downloadStatistics.getBytesProcessed())), getFormattedHeadersFromMapList(downloadStatistics.getUnformattedHeaders()));
                builder.performance(this.nanoConverter.toMillis(downloadStatistics.getLatencyInNanos()), this.nanoConverter.toMillis(downloadStatistics.getTotalTimeInNanos()));
                this.mMediaEventQueue.add(builder.build());
            } catch (MalformedURLException e) {
                DLog.exceptionf(e, "bad url syntax on fragmentInfo.getContentUrl.getUrl() method", new Object[0]);
            }
        }
    }

    @Subscribe
    public void handleAdPlaybackLoadedEvent(AdAcquisitionDownloadedEvent adAcquisitionDownloadedEvent) {
        AcquisitionMediaEvent.Builder builder = new AcquisitionMediaEvent.Builder(0 == true ? 1 : 0);
        builder.asset(AcquisitionMediaEvent.Asset.Type.Video, adAcquisitionDownloadedEvent.getConsumptionId(), false);
        builder.request(createAdMP4Request(adAcquisitionDownloadedEvent.getRequestUrl()));
        DownloadStatistics statistics = adAcquisitionDownloadedEvent.getStatistics();
        builder.response(AcquisitionMediaEvent.Response.Type.Success, 200, statistics != null ? Long.valueOf(statistics.getBytesProcessed()) : null, getFormattedHeadersFromMapList(statistics != null ? statistics.getUnformattedHeaders() : null));
        builder.performance(this.nanoConverter.toMillis(statistics != null ? statistics.getLatencyInNanos() : 0L), this.nanoConverter.toMillis(statistics != null ? statistics.getTotalTimeInNanos() : 0L));
        this.mMediaEventQueue.add(builder.build());
    }

    @Subscribe
    public void handleAdPlaybackErroredEvent(AdAcquisitionErroredEvent adAcquisitionErroredEvent) {
        AcquisitionMediaEvent.Builder builder = new AcquisitionMediaEvent.Builder(0 == true ? 1 : 0);
        builder.asset(AcquisitionMediaEvent.Asset.Type.Video, adAcquisitionErroredEvent.getConsumptionId(), false);
        builder.request(createAdMP4Request(adAcquisitionErroredEvent.getRequestUrl()));
        builder.response(convertDownloadServiceExceptionToResponseErrorType(adAcquisitionErroredEvent.getFailureCause()), Integer.valueOf(convertDownloadServiceExceptionToResponseErrorCode(adAcquisitionErroredEvent.getFailureCause())), 0L, getFormattedHeadersFromMapList(adAcquisitionErroredEvent.getStatistics() != null ? adAcquisitionErroredEvent.getStatistics().getUnformattedHeaders() : null));
        this.mMediaEventQueue.add(builder.build());
    }

    @Subscribe
    public void handleAdManifestDownloadedEvent(@Nonnull AdVastManifestDownloadedEvent adVastManifestDownloadedEvent) {
        Preconditions.checkNotNull(adVastManifestDownloadedEvent, NotificationCompat.CATEGORY_EVENT);
        DownloadStatistics downloadStatistics = adVastManifestDownloadedEvent.getDownloadStatistics();
        AnonymousClass1 anonymousClass1 = null;
        AcquisitionMediaEvent.Builder builder = new AcquisitionMediaEvent.Builder(anonymousClass1);
        String consumptionId = this.mConsumptionIdProvider.getConsumptionId() != null ? this.mConsumptionIdProvider.getConsumptionId() : "";
        if (adVastManifestDownloadedEvent.getConsumptionId() != null) {
            consumptionId = adVastManifestDownloadedEvent.getConsumptionId();
        }
        builder.asset(AcquisitionMediaEvent.Asset.Type.Manifest, consumptionId, false);
        builder.performance(this.nanoConverter.toMillis(downloadStatistics.getLatencyInNanos()), this.nanoConverter.toMillis(downloadStatistics.getTotalTimeInNanos()));
        builder.request(new AcquisitionMediaEvent.Request.Builder(anonymousClass1).server(getHostnameSafe(adVastManifestDownloadedEvent.getUrl())).url(adVastManifestDownloadedEvent.getUrl()).build());
        if (adVastManifestDownloadedEvent.getException() == null) {
            builder.response(AcquisitionMediaEvent.Response.Type.Success, 200, Long.valueOf(getBytesProcessed(downloadStatistics.getBytesProcessed())), getFormattedHeadersFromMapList(downloadStatistics.getUnformattedHeaders()));
        } else {
            builder.response(convertDownloadServiceExceptionToResponseErrorType(adVastManifestDownloadedEvent.getException()), Integer.valueOf(convertDownloadServiceExceptionToResponseErrorCode(adVastManifestDownloadedEvent.getException())), Long.valueOf(getBytesProcessed(downloadStatistics.getBytesProcessed())), getFormattedHeadersFromMapList(downloadStatistics.getUnformattedHeaders()));
        }
        this.mMediaEventQueue.add(builder.build());
    }

    @Nonnull
    private ImmutableMultimap<String, String> getFormattedHeadersFromMapList(@Nullable Map<String, List<String>> map) {
        return JSONUtils.truncateMultimap(JSONUtils.mapListToMultimap(map), 50, 200);
    }

    private boolean isManifestUrl(String str) {
        return str.endsWith(".mpd") || str.contains(".mpd?") || str.endsWith(".ism") || str.endsWith(".ism?");
    }

    @Subscribe
    public void handleFatalContentEventError(FatalContentEventError fatalContentEventError) {
        handleContentEventErrorBase(fatalContentEventError);
    }

    @Subscribe
    public void handleRetriableContentEventError(RetriableContentEventError retriableContentEventError) {
        handleContentEventErrorBase(retriableContentEventError);
    }

    private void handleContentEventErrorBase(ContentEventErrorBase contentEventErrorBase) {
        if (contentEventErrorBase.getUrl() == null) {
            return;
        }
        if (contentEventErrorBase.getSmoothStreamingUri() != null) {
            handleFragmentContentErrorEvent(contentEventErrorBase);
        } else if (isManifestUrl(contentEventErrorBase.getUrl().getUrl())) {
            handleManifestContentErrorEvent(contentEventErrorBase);
        }
    }

    private void handleFragmentContentErrorEvent(@Nonnull ContentEventErrorBase contentEventErrorBase) {
        try {
            AnonymousClass1 anonymousClass1 = null;
            AcquisitionMediaEvent.Builder builder = new AcquisitionMediaEvent.Builder(anonymousClass1);
            AcquisitionMediaEvent.Asset.Type assetType = getAssetType(contentEventErrorBase);
            builder.asset(assetType, contentEventErrorBase.getUrl().getSessionId(), false);
            SmoothStreamingURI smoothStreamingUri = contentEventErrorBase.getSmoothStreamingUri();
            AcquisitionMediaEvent.Request.Builder builderServer = new AcquisitionMediaEvent.Request.Builder(anonymousClass1).bitrate(smoothStreamingUri.getQualityLevel().getBitrate()).server(getHostname(contentEventErrorBase.getUrl().getUrl()));
            if (!smoothStreamingUri.isInitFragment()) {
                builderServer.fragmentIndex(smoothStreamingUri.getChunkIndex());
                if (this.mShouldReportFragmentInformation && smoothStreamingUri.isVideo()) {
                    VideoQualityLevel videoQualityLevel = (VideoQualityLevel) smoothStreamingUri.getQualityLevel();
                    builderServer.fragmentResolution(videoQualityLevel.getMaxWidth(), videoQualityLevel.getMaxHeight());
                    builderServer.fragmentDuration((int) TimeUnit.NANOSECONDS.toMillis(smoothStreamingUri.getDurationInNanos()));
                }
            }
            if (contentEventErrorBase.getUrl() != null && !Strings.isNullOrEmpty(contentEventErrorBase.getUrl().getUrl())) {
                if (assetType.equals(AcquisitionMediaEvent.Asset.Type.Audio) || assetType.equals(AcquisitionMediaEvent.Asset.Type.Video) || assetType.equals(AcquisitionMediaEvent.Asset.Type.TimedText)) {
                    builderServer.url(String.valueOf(smoothStreamingUri.getAbsoluteUrl(contentEventErrorBase.getUrl().getUrl())));
                } else {
                    builderServer.url(contentEventErrorBase.getUrl().getUrl());
                }
            }
            if (smoothStreamingUri.getDownloadChunkSize() >= 0) {
                builderServer.bytesExpected(smoothStreamingUri.getDownloadChunkSize());
            }
            builder.request(builderServer.build());
            setErrorResponseData(contentEventErrorBase, builder);
            DownloadStatistics downloadStatistics = contentEventErrorBase.getException().getDownloadStatistics();
            if (downloadStatistics != null) {
                builder.performance(this.nanoConverter.toMillis(downloadStatistics.getLatencyInNanos()), this.nanoConverter.toMillis(downloadStatistics.getTotalTimeInNanos()));
            }
            this.mMediaEventQueue.add(builder.build());
        } catch (MalformedURLException e) {
            DLog.exceptionf(e, "bad url syntax on fragmentInfo.getContentUrl.getUrl() method", new Object[0]);
        }
    }

    private void handleManifestContentErrorEvent(@Nonnull ContentEventErrorBase contentEventErrorBase) {
        AnonymousClass1 anonymousClass1 = null;
        AcquisitionMediaEvent.Builder builder = new AcquisitionMediaEvent.Builder(anonymousClass1);
        builder.asset(AcquisitionMediaEvent.Asset.Type.Manifest, contentEventErrorBase.getUrl().getSessionId(), false);
        builder.request(new AcquisitionMediaEvent.Request.Builder(anonymousClass1).server(getHostnameSafe(contentEventErrorBase.getUrl().getUrl())).url(contentEventErrorBase.getUrl().getUrl()).build());
        DownloadStatistics downloadStatistics = contentEventErrorBase.getException().getDownloadStatistics();
        if (downloadStatistics != null) {
            builder.performance(this.nanoConverter.toMillis(downloadStatistics.getLatencyInNanos()), this.nanoConverter.toMillis(downloadStatistics.getTotalTimeInNanos()));
        }
        setErrorResponseData(contentEventErrorBase, builder);
        this.mMediaEventQueue.add(builder.build());
    }

    private AcquisitionMediaEvent.Asset.Type getAssetType(ContentEventErrorBase contentEventErrorBase) {
        return getAssetType(contentEventErrorBase.getSmoothStreamingUri().getStream().getType());
    }

    private void setErrorResponseData(@Nonnull ContentEventErrorBase contentEventErrorBase, @Nonnull AcquisitionMediaEvent.Builder builder) {
        int statusCode = contentEventErrorBase.getException().getStatusCode();
        if (statusCode > 0) {
            builder.response(getResponseType(statusCode, contentEventErrorBase.getErrorCode()), Integer.valueOf(statusCode), 0L, getFormattedHeadersFromMapList(contentEventErrorBase.getUnformattedHeaders()));
        } else {
            Pair<AcquisitionMediaEvent.Response.Type, Integer> responseTypeAndCode = getResponseTypeAndCode(contentEventErrorBase.getErrorCode());
            builder.response((AcquisitionMediaEvent.Response.Type) Preconditions.checkNotNull(responseTypeAndCode.first), (Integer) Preconditions.checkNotNull(responseTypeAndCode.second), 0L, getFormattedHeadersFromMapList(contentEventErrorBase.getUnformattedHeaders()));
        }
    }

    private AcquisitionMediaEvent.Response.Type getResponseType(int i, @Nonnull ContentException.ContentError contentError) {
        if (i == 404) {
            return AcquisitionMediaEvent.Response.Type.FileNotFound;
        }
        if (i == 504) {
            return AcquisitionMediaEvent.Response.Type.NetworkTimeout;
        }
        return getResponseTypeAndCode(contentError).first;
    }

    /* JADX INFO: renamed from: com.amazon.avod.media.playback.reporting.aloysius.AloysiusAcquisitionReporter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$content$ContentException$ContentError;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$content$smoothstream$manifest$StreamType;

        static {
            int[] iArr = new int[ContentException.ContentError.values().length];
            $SwitchMap$com$amazon$avod$content$ContentException$ContentError = iArr;
            try {
                iArr[ContentException.ContentError.NETWORK_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentException$ContentError[ContentException.ContentError.CDN_ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentException$ContentError[ContentException.ContentError.FILE_MISSING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[StreamType.values().length];
            $SwitchMap$com$amazon$avod$content$smoothstream$manifest$StreamType = iArr2;
            try {
                iArr2[StreamType.AUDIO.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$smoothstream$manifest$StreamType[StreamType.VIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$smoothstream$manifest$StreamType[StreamType.SUBTITLES.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private Pair<AcquisitionMediaEvent.Response.Type, Integer> getResponseTypeAndCode(@Nonnull ContentException.ContentError contentError) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$content$ContentException$ContentError[contentError.ordinal()];
        if (i == 1 || i == 2) {
            return Pair.create(AcquisitionMediaEvent.Response.Type.NetworkTimeout, 504);
        }
        if (i == 3) {
            return Pair.create(AcquisitionMediaEvent.Response.Type.FileNotFound, 404);
        }
        return Pair.create(AcquisitionMediaEvent.Response.Type.UnknownError, 500);
    }

    private AcquisitionMediaEvent.Request createRequest(FragmentInformationHolder fragmentInformationHolder) throws MalformedURLException {
        AcquisitionMediaEvent.Request.Builder builderServer = new AcquisitionMediaEvent.Request.Builder(null).bitrate(fragmentInformationHolder.getBitrate()).server(getHostname(fragmentInformationHolder.getAbsoluteUrl()));
        if (!fragmentInformationHolder.isInitFragment() && !fragmentInformationHolder.isLastInStream()) {
            builderServer.fragmentIndex(fragmentInformationHolder.getChunkIndex());
            if (this.mShouldReportFragmentInformation && fragmentInformationHolder.isVideo()) {
                builderServer.fragmentResolution(fragmentInformationHolder.getWidth(), fragmentInformationHolder.getHeight());
                builderServer.fragmentDuration((int) TimeUnit.NANOSECONDS.toMillis(fragmentInformationHolder.getDurationInNanos()));
            }
        }
        String absoluteUrl = fragmentInformationHolder.getAbsoluteUrl();
        if (!Strings.isNullOrEmpty(absoluteUrl)) {
            builderServer.url(absoluteUrl);
        }
        if (fragmentInformationHolder.getDownloadChunkSize() >= 0) {
            builderServer.bytesExpected(fragmentInformationHolder.getDownloadChunkSize());
        }
        if (this.mShouldReportQualityScore) {
            builderServer.qualityScore(fragmentInformationHolder.getQualityScore());
        }
        return builderServer.build();
    }

    private AcquisitionMediaEvent.Request createAdMP4Request(@Nullable String str) {
        AcquisitionMediaEvent.Request.Builder builder = new AcquisitionMediaEvent.Request.Builder(null);
        try {
            if (!Strings.isNullOrEmpty(str)) {
                builder.server(getHostname(str));
                builder.url(str);
            }
        } catch (MalformedURLException e) {
            DLog.exceptionf(e, "bad url syntax in createAdMP4Request", new Object[0]);
        }
        return builder.build();
    }

    private static AcquisitionMediaEvent.Response.Type convertDownloadServiceExceptionToResponseErrorType(Exception exc) {
        if (((exc instanceof NetworkResponseException) || (exc instanceof BoltException)) && 404 == convertDownloadServiceExceptionToResponseErrorCode(exc)) {
            return AcquisitionMediaEvent.Response.Type.FileNotFound;
        }
        if ((exc instanceof NetworkIOException) || (exc instanceof NoDataConnectionException) || (exc instanceof InvalidCDNResponseException)) {
            return AcquisitionMediaEvent.Response.Type.NetworkTimeout;
        }
        return AcquisitionMediaEvent.Response.Type.UnknownError;
    }

    private static int convertDownloadServiceExceptionToResponseErrorCode(Exception exc) {
        if (exc instanceof NetworkResponseException) {
            return ((NetworkResponseException) exc).getResponseCode();
        }
        if (exc instanceof BoltException) {
            return findStatusCode((BoltException) exc);
        }
        return 0;
    }

    private static int findStatusCode(@Nonnull BoltException boltException) {
        Throwable cause = boltException.getCause();
        if (cause instanceof RetryLimitException) {
            cause = ((RetryLimitException) cause).getCause();
        }
        if (cause instanceof HttpStatusCodeException) {
            return ((HttpStatusCodeException) cause).getStatusCode();
        }
        return 0;
    }

    private String getHostname(String str) throws MalformedURLException {
        return new URL(str).getHost();
    }

    private String getHostnameSafe(@Nonnull String str) {
        try {
            return getHostname(str);
        } catch (MalformedURLException e) {
            DLog.exceptionf(e, "bad url syntax on fragmentInfo.getContentUrl.getUrl() method for %s", str);
            return "";
        }
    }

    @VisibleForTesting
    static class AcquisitionMediaEvent implements MediaEvent {
        private final Asset mAsset;
        private final Performance mPerformance;
        private final Request mRequest;
        private final Response mResponse;

        /* synthetic */ AcquisitionMediaEvent(Builder builder, AnonymousClass1 anonymousClass1) {
            this(builder);
        }

        private AcquisitionMediaEvent(@Nonnull Builder builder) {
            this.mAsset = ((Builder) Preconditions.checkNotNull(builder, "builder")).mAsset;
            this.mRequest = builder.mRequest;
            this.mResponse = builder.mResponse;
            this.mPerformance = builder.mPerformance;
        }

        @Nonnull
        public Asset getAsset() {
            return this.mAsset;
        }

        @Nonnull
        public Request getRequest() {
            return this.mRequest;
        }

        @Nonnull
        public Response getResponse() {
            return this.mResponse;
        }

        @Nullable
        public Performance getPerformance() {
            return this.mPerformance;
        }

        @Override // com.amazon.avod.media.events.model.MediaEvent
        public MediaEvent.MediaEventType getMediaEventType() {
            return MediaEvent.MediaEventType.Acquisition;
        }

        @VisibleForTesting
        static class Asset {
            private final boolean mCaching;

            @Nullable
            private final String mConsumptionId;
            private final Type mType;

            enum Type {
                Manifest,
                Video,
                Audio,
                TimedText,
                DRMLicense
            }

            /* synthetic */ Asset(Type type, String str, boolean z, AnonymousClass1 anonymousClass1) {
                this(type, str, z);
            }

            private Asset(@Nonnull Type type, @Nullable String str, boolean z) {
                this.mType = (Type) Preconditions.checkNotNull(type, "asset type");
                this.mConsumptionId = str;
                this.mCaching = z;
            }

            @Nonnull
            public Type getType() {
                return this.mType;
            }

            @Nullable
            public String getConsumptionId() {
                return this.mConsumptionId;
            }

            public boolean isCaching() {
                return this.mCaching;
            }
        }

        @VisibleForTesting
        static class Request {
            private final Integer mBitrate;
            private final Long mBytesExpected;
            private final boolean mFragment;
            private final Integer mFragmentDuration;
            private final Integer mFragmentIndex;
            private final FragmentResolution mFragmentResolution;
            private final double mQualityScore;
            private final Server mServer;
            private final String mUrl;

            /* synthetic */ Request(Builder builder, AnonymousClass1 anonymousClass1) {
                this(builder);
            }

            private Request(@Nonnull Builder builder) {
                Preconditions.checkNotNull(builder, "AcquisitionMediaEvent.Request.Builder");
                this.mBitrate = builder.mBitrate;
                this.mFragment = builder.mFragment;
                this.mFragmentIndex = builder.mFragmentIndex;
                this.mBytesExpected = builder.mBytesExpected;
                this.mUrl = Strings.nullToEmpty(builder.mUrl);
                this.mServer = builder.mServer;
                this.mFragmentResolution = builder.mFragmentResolution;
                this.mFragmentDuration = builder.mFragmentDuration;
                this.mQualityScore = builder.mQualityScore;
            }

            @Nonnegative
            @Nullable
            public Integer getBitrate() {
                return this.mBitrate;
            }

            public boolean isFragment() {
                return this.mFragment;
            }

            @Nonnegative
            @Nullable
            public Integer getFragmentIndex() {
                return this.mFragmentIndex;
            }

            @Nonnegative
            @Nullable
            public Long getBytesExpected() {
                return this.mBytesExpected;
            }

            @Nonnull
            public Server getServer() {
                return this.mServer;
            }

            @Nonnull
            public String getUrl() {
                return this.mUrl;
            }

            @Nullable
            public FragmentResolution getFragmentResolution() {
                return this.mFragmentResolution;
            }

            @Nullable
            public Integer getFragmentDuration() {
                return this.mFragmentDuration;
            }

            public double getQualityScore() {
                return this.mQualityScore;
            }

            @JsonInclude(JsonInclude.Include.NON_NULL)
            @VisibleForTesting
            static class Server {
                private final String mHostname;
                private final String mIpv4;
                private final String mIpv6;

                /* synthetic */ Server(String str, String str2, String str3, AnonymousClass1 anonymousClass1) {
                    this(str, str2, str3);
                }

                private Server(@Nonnull String str, @Nullable String str2, @Nullable String str3) {
                    this.mHostname = (String) Preconditions.checkNotNull(str, "hostname");
                    this.mIpv4 = str2;
                    this.mIpv6 = str3;
                }

                @Nonnull
                public String getHostname() {
                    return this.mHostname;
                }

                @Nullable
                public String getIpv4() {
                    return this.mIpv4;
                }

                @Nullable
                public String getIpv6() {
                    return this.mIpv6;
                }
            }

            static class FragmentResolution {
                private final Integer mHeight;
                private final Integer mWidth;

                /* synthetic */ FragmentResolution(int i, int i2, AnonymousClass1 anonymousClass1) {
                    this(i, i2);
                }

                private FragmentResolution(int i, int i2) {
                    this.mWidth = Integer.valueOf(i);
                    this.mHeight = Integer.valueOf(i2);
                }

                public Integer getWidth() {
                    return this.mWidth;
                }

                public Integer getHeight() {
                    return this.mHeight;
                }
            }

            private static class Builder {
                private Integer mBitrate;
                private Long mBytesExpected;
                private boolean mFragment;
                private Integer mFragmentDuration;
                private Integer mFragmentIndex;
                private FragmentResolution mFragmentResolution;
                private double mQualityScore;
                private Server mServer;
                private String mUrl;

                private Builder() {
                }

                /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                    this();
                }

                Builder bitrate(@Nonnegative int i) {
                    Preconditions.checkArgument(i >= 0, "bitrate must be non-negative");
                    this.mBitrate = Integer.valueOf(i);
                    return this;
                }

                Builder fragmentIndex(@Nonnegative int i) {
                    Preconditions.checkArgument(i >= 0, "fragmentIndex must be non-negative");
                    this.mFragment = true;
                    this.mFragmentIndex = Integer.valueOf(i);
                    return this;
                }

                Builder url(@Nonnull String str) {
                    this.mUrl = (String) Preconditions.checkNotNull(str, ImagesContract.URL);
                    return this;
                }

                Builder bytesExpected(@Nonnegative long j) {
                    Preconditions.checkArgument(j >= 0, "bytesExpected must be non-negative");
                    this.mBytesExpected = Long.valueOf(j);
                    return this;
                }

                Builder server(@Nonnull String str) {
                    try {
                        InetAddress ipAddress = getIpAddress(str);
                        this.mServer = new Server(str, ipAddress instanceof Inet4Address ? ipAddress.getHostAddress() : null, ipAddress instanceof Inet6Address ? ipAddress.getHostAddress() : null, null);
                    } catch (UnknownHostException e) {
                        DLog.exceptionf(e, "unable to convert a hostname into a IP address, ignoring server block", new Object[0]);
                    }
                    return this;
                }

                Builder fragmentResolution(int i, int i2) {
                    this.mFragmentResolution = new FragmentResolution(i, i2, null);
                    return this;
                }

                Builder fragmentDuration(int i) {
                    this.mFragmentDuration = Integer.valueOf(i);
                    return this;
                }

                Builder qualityScore(double d) {
                    this.mQualityScore = d;
                    return this;
                }

                Request build() {
                    return new Request(this, null);
                }

                private InetAddress getIpAddress(String str) throws UnknownHostException {
                    return InetAddress.getByName(str);
                }
            }
        }

        @VisibleForTesting
        static class Response {
            private final Long mBytesAcquired;

            @Nonnull
            private final ImmutableMultimap<String, String> mHeadersFormatted;
            private final Type mResponse;
            private final Integer mResponseCode;

            enum Type {
                Success,
                FileNotFound,
                NetworkTimeout,
                HostNameNotFound,
                HostLookupTimeout,
                UnknownError
            }

            /* synthetic */ Response(Type type, Integer num, Long l, ImmutableMultimap immutableMultimap, AnonymousClass1 anonymousClass1) {
                this(type, num, l, immutableMultimap);
            }

            private Response(@Nonnull Type type, @Nullable Integer num, @Nullable Long l, @Nonnull ImmutableMultimap<String, String> immutableMultimap) {
                this.mResponse = (Type) Preconditions.checkNotNull(type);
                this.mResponseCode = num;
                this.mBytesAcquired = l;
                this.mHeadersFormatted = (ImmutableMultimap) Preconditions.checkNotNull(immutableMultimap, "headersFormatted");
            }

            @Nonnull
            public Type getResponse() {
                return this.mResponse;
            }

            @Nullable
            public Integer getResponseCode() {
                return this.mResponseCode;
            }

            @Nullable
            public Long getBytesAcquired() {
                return this.mBytesAcquired;
            }

            @Nonnull
            public Map<String, Collection<String>> getHeaders() {
                return this.mHeadersFormatted.asMap();
            }
        }

        @VisibleForTesting
        static class Performance {
            private final long mTimeToFirstByte;
            private final long mTimeToLastByte;

            /* synthetic */ Performance(long j, long j2, AnonymousClass1 anonymousClass1) {
                this(j, j2);
            }

            private Performance(long j, long j2) {
                Preconditions.checkArgument(j >= 0, "timeToFirstByte must be non negative");
                Preconditions.checkArgument(j2 >= 0, "timeToLastByte must be non negative");
                this.mTimeToFirstByte = j;
                this.mTimeToLastByte = j2;
            }

            @Nonnegative
            public long getTimeToFirstByte() {
                return this.mTimeToFirstByte;
            }

            @Nonnegative
            public long getTimeToLastByte() {
                return this.mTimeToLastByte;
            }
        }

        private static class Builder {
            private Asset mAsset;
            private Performance mPerformance;
            private Request mRequest;
            private Response mResponse;

            private Builder() {
            }

            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            Builder asset(@Nonnull Asset.Type type, @Nullable String str, boolean z) {
                this.mAsset = new Asset(type, str, z, null);
                return this;
            }

            Builder request(@Nonnull Request request) {
                this.mRequest = (Request) Preconditions.checkNotNull(request, "request");
                return this;
            }

            Builder response(@Nonnull Response.Type type, @Nonnull Integer num, @Nullable Long l, @Nonnull ImmutableMultimap<String, String> immutableMultimap) {
                this.mResponse = new Response(type, num, l, immutableMultimap, null);
                return this;
            }

            Builder performance(long j, long j2) {
                this.mPerformance = new Performance(j, j2, null);
                return this;
            }

            public MediaEvent build() {
                return new AcquisitionMediaEvent(this, null);
            }
        }
    }
}
