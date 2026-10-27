package com.amazon.avod.content;

import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.readytowatch.ReadyToWatch;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturerInterface;
import com.amazon.avod.content.urlvending.AudioTrackMetadata;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.content.urlvending.ContentUrlSelector;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.playback.PlaybackPerformanceReport;
import com.amazon.avod.playback.PlaybackSessionProtocol;
import com.google.common.collect.ImmutableList;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface ContentSession {
    void begin() throws MediaException;

    void end(boolean z);

    @Nullable
    byte[] getAudioDefaultKeyId();

    @Nonnull
    ImmutableList<AudioTrackMetadata> getAudioTrackMetadataList();

    @Nullable
    AudioVideoUrls getAudioVideoUrls();

    @Nullable
    String getAuditPingUrl(@Nonnull String str);

    @Nonnull
    Set<String> getAvailableAudioLanguages();

    @Nullable
    String getConsumptionId();

    @Nullable
    ContentUrlSelector getContentUrlSelector();

    ContentSessionContext getContext();

    @Nullable
    String getCurrentAudioLanguage();

    long getDownloadedTimeAfterPositionInNanos(long j);

    @Nullable
    String getEncodedEncryptionHeader() throws ContentException;

    @Nonnull
    ContentManagementEventBus getEventDispatcher();

    TimeSpan getLastAvailableTime();

    @Nullable
    ContentException getLastFatalError();

    @Nullable
    ManifestCapturerInterface getManifestCapturer();

    PlaybackSessionProtocol getProtocol();

    ReadyToWatch getReadyToWatch();

    long getRemainingNeededSizeInBytes();

    long getTimeWindowEndMillis() throws ContentException;

    long getTimeWindowStartMillis() throws ContentException;

    long getTotalNeededSizeInBytes();

    @Nullable
    byte[] getVideoDefaultKeyId();

    boolean isActive();

    boolean isEncryptedContent();

    boolean isFullyDownloadedFromNanos(long j);

    boolean isRestarting();

    void notifyPlayerBindStateChange(@Nonnull PlayerBindState playerBindState);

    void refreshManifest() throws MediaException;

    void setListener(ContentSessionEventListener contentSessionEventListener);

    void setPlaybackPerformanceReport(PlaybackPerformanceReport playbackPerformanceReport);

    void setPlaybackRestrictedToBufferedContent(boolean z);

    void setVideoQualityOverride(@Nullable QualityLevel qualityLevel);

    boolean shouldSwitchAudioAdaptationSet();

    boolean verifyAudioCachedContent(long j) throws ContentException;
}
