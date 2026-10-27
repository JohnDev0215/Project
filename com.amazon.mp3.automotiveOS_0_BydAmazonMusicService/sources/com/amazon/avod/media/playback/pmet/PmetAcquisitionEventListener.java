package com.amazon.avod.media.playback.pmet;

import com.amazon.avod.content.event.ContentEventFragmentDownloaded;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.event.FragmentInformationHolder;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.content.smoothstream.manifest.acquisition.events.NewContentDownloadedEvent;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.media.events.MediaEventReporter;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.pmet.ContentTypePivot;
import com.amazon.avod.pmet.PlaybackPmetMetric;
import com.amazon.avod.pmet.PlaybackPmetMetricReporter;
import com.amazon.avod.pmet.PlaybackPmetReportingConfig;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.Subscribe;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PmetAcquisitionEventListener implements MediaEventReporter {
    private final ContentManagementEventBus mEventBus;
    private final boolean mIsFragmentDownloadPmetReportingEnabled;
    private final boolean mIsFragmentQualityPmetReportingEnabled;
    private final boolean mIsManifestDownloadPmetReportingEnabled;
    private final TimeUnit mNanoConverter;
    private final PlaybackPmetMetricReporter mPlaybackPmetMetricReporter;

    public PmetAcquisitionEventListener(@Nonnull ContentManagementEventBus contentManagementEventBus) {
        this(contentManagementEventBus, PlaybackPmetMetricReporter.getInstance(), PlaybackPmetReportingConfig.getInstance());
    }

    PmetAcquisitionEventListener(@Nonnull ContentManagementEventBus contentManagementEventBus, @Nonnull PlaybackPmetMetricReporter playbackPmetMetricReporter, @Nonnull PlaybackPmetReportingConfig playbackPmetReportingConfig) {
        this.mNanoConverter = TimeUnit.NANOSECONDS;
        this.mEventBus = (ContentManagementEventBus) Preconditions.checkNotNull(contentManagementEventBus, "eventBus");
        this.mPlaybackPmetMetricReporter = (PlaybackPmetMetricReporter) Preconditions.checkNotNull(playbackPmetMetricReporter, "playbackPmetMetricReporter");
        Preconditions.checkNotNull(playbackPmetReportingConfig, "playbackPmetReportingConfig");
        this.mIsFragmentDownloadPmetReportingEnabled = playbackPmetReportingConfig.isFragmentDownloadPmetReportingEnabled();
        this.mIsFragmentQualityPmetReportingEnabled = playbackPmetReportingConfig.isFragmentQualityPmetReportingEnabled();
        this.mIsManifestDownloadPmetReportingEnabled = playbackPmetReportingConfig.isManifestDownloadPmetReportingEnabled();
    }

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void initialize() {
        this.mEventBus.registerEventBusHandler(this);
    }

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void terminate() {
        this.mEventBus.unregisterEventBusHandler(this);
    }

    @Subscribe
    public void handleFragmentDownloadedEvent(ContentEventFragmentDownloaded contentEventFragmentDownloaded) {
        FragmentInformationHolder fragmentInformationHolder = contentEventFragmentDownloaded.getFragmentInformationHolder();
        if (fragmentInformationHolder.getContentUrl() == null || !ContentType.isLive(contentEventFragmentDownloaded.getContentType())) {
            return;
        }
        if (fragmentInformationHolder.isAudio() || fragmentInformationHolder.isVideo()) {
            String cdnName = fragmentInformationHolder.getContentUrl().getCdnName();
            String origin = fragmentInformationHolder.getContentUrl().getOrigin();
            if (this.mIsFragmentDownloadPmetReportingEnabled) {
                StreamType streamType = fragmentInformationHolder.getStreamType();
                this.mPlaybackPmetMetricReporter.reportTimerMetric(PlaybackPmetMetric.FRAGMENT_DOWNLOAD, streamType.name(), this.mNanoConverter.toMillis(contentEventFragmentDownloaded.getTimeToTheFirstByteInNanos()), ContentTypePivot.LIVE, cdnName, origin, contentEventFragmentDownloaded.getTitleId());
            }
            if (this.mIsFragmentQualityPmetReportingEnabled && fragmentInformationHolder.isVideo()) {
                this.mPlaybackPmetMetricReporter.reportLivePlaybackFragmentQuality(VideoResolution.getResolutionBand(fragmentInformationHolder.getWidth(), fragmentInformationHolder.getHeight(), fragmentInformationHolder.getDisplayAspectRatio()), cdnName, origin, contentEventFragmentDownloaded.getTitleId());
            }
        }
    }

    @Subscribe
    public void handleNewContentDownloadedEvent(NewContentDownloadedEvent newContentDownloadedEvent) {
        if (this.mIsManifestDownloadPmetReportingEnabled && isManifestUrl(newContentDownloadedEvent.getContentUrl().getUrl()) && ContentType.isLive(newContentDownloadedEvent.getContentType())) {
            DownloadStatistics downloadStatistics = newContentDownloadedEvent.getDownloadStatistics();
            String cdnName = newContentDownloadedEvent.getContentUrl().getCdnName();
            String origin = newContentDownloadedEvent.getContentUrl().getOrigin();
            long millis = this.mNanoConverter.toMillis(downloadStatistics.getLatencyInNanos());
            long millis2 = this.mNanoConverter.toMillis(downloadStatistics.getTotalTimeInNanos());
            PlaybackPmetMetricReporter playbackPmetMetricReporter = this.mPlaybackPmetMetricReporter;
            PlaybackPmetMetric playbackPmetMetric = PlaybackPmetMetric.MANIFEST_DOWNLOAD;
            ContentTypePivot contentTypePivot = ContentTypePivot.LIVE;
            playbackPmetMetricReporter.reportTimerMetric(playbackPmetMetric, null, millis, contentTypePivot, cdnName, origin, newContentDownloadedEvent.getTitleId());
            this.mPlaybackPmetMetricReporter.reportTimerMetric(playbackPmetMetric, "Total", millis2, contentTypePivot, cdnName, origin, newContentDownloadedEvent.getTitleId());
        }
    }

    private boolean isManifestUrl(String str) {
        return str.endsWith(".mpd") || str.contains(".mpd?") || str.endsWith(".ism") || str.endsWith(".ism?");
    }
}
