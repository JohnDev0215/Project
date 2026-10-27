package com.amazon.avod.playback.session.workflow.tasks;

import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.urlvending.AudioTrackMetadata;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.content.urlvending.AudioVideoUrlsBuilder;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.PlaybackDataSource;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.playback.session.PlaybackSessionResources;
import com.amazon.avod.qos.SmoothStreamingVideoPresentationEventReporter;
import com.amazon.avod.qos.metadata.DeliveryType;
import com.amazon.avod.userdownload.PVDownloadPlayerShim;
import com.amazon.avod.util.DLog;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.eventbus.EventBus;
import java.util.Collections;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PrepareDownloadResources extends SimpleTask {
    private final PlaybackSessionContext mPlaybackSessionContext;
    private final PlaybackSessionResources mPlaybackSessionResources;

    public PrepareDownloadResources(@Nonnull EventBus eventBus, @Nonnull ExceptionCallback exceptionCallback, @Nonnull PlaybackSessionResources playbackSessionResources, @Nonnull PlaybackSessionContext playbackSessionContext) {
        super(eventBus, exceptionCallback);
        this.mPlaybackSessionResources = (PlaybackSessionResources) Preconditions.checkNotNull(playbackSessionResources, "playbackSessionResources");
        this.mPlaybackSessionContext = (PlaybackSessionContext) Preconditions.checkNotNull(playbackSessionContext, "playbackSessionContext");
    }

    @Override // com.amazon.avod.playback.session.workflow.scheduler.BaseTask
    protected void execute() throws MediaException {
        AudioVideoUrls audioVideoUrls;
        PVDownloadPlayerShim download = this.mPlaybackSessionContext.getDownload();
        if (download == null) {
            return;
        }
        this.mPlaybackSessionContext.setDataSource(PlaybackDataSource.DOWNLOADED);
        this.mPlaybackSessionContext.setContentSessionType(ContentSessionType.PROGRESSIVE_PLAYBACK);
        PlaybackSessionContext playbackSessionContext = this.mPlaybackSessionContext;
        DeliveryType deliveryType = DeliveryType.DOWNLOAD;
        playbackSessionContext.setDeliveryType(deliveryType);
        VideoSpecification videoSpecificationBuild = VideoSpecification.newBuilder((VideoSpecification) Preconditions.checkNotNull(this.mPlaybackSessionContext.getVideoSpec(), "contextVideoSpec")).setAudioFormat(download.getAudioFormat()).setMediaQuality(download.getDownloadQuality()).setMimeType("video/aiv-asin").setAudioTrackIds(download.getAudioTrackIds()).build();
        this.mPlaybackSessionContext.setVideoSpec(videoSpecificationBuild);
        Optional<String> url = download.getUrl();
        Optional<String> sessionId = download.getSessionId();
        ImmutableList<AudioTrackMetadata> audioTrackMetadataList = download.getAudioTrackMetadataList();
        if (url.isPresent() && sessionId.isPresent()) {
            audioVideoUrls = new AudioVideoUrlsBuilder().setContentUrls(ImmutableList.of(new ContentUrl(url.get(), sessionId.get()))).setAudioTrackMetadataList(audioTrackMetadataList).setPlaybackSettingsOverride(Collections.emptyMap()).build();
        } else {
            DLog.logf("PrepareDownloadResources - requesting fresh audioVideoUrls as they are not currently available.");
            SmoothStreamingVideoPresentationEventReporter eventReporter = this.mPlaybackSessionResources.getEventReporter();
            RendererSchemeType rendererSchemeTypeOrNull = download.getRendererSchemeType().orNull();
            audioVideoUrls = this.mPlaybackSessionResources.getContentUrlPolicyManager().getAudioVideoUrls(videoSpecificationBuild, this.mPlaybackSessionContext.getVideoOptions().getSessionContext(), this.mPlaybackSessionContext.getContentSessionType(), eventReporter.getUserWatchSessionId(), eventReporter.getPlaybackReporter(), rendererSchemeTypeOrNull, this.mPlaybackSessionResources.getRendererSchemeController().getRendererScheme(rendererSchemeTypeOrNull, this.mPlaybackSessionContext.getDrmScheme()), false);
        }
        this.mPlaybackSessionContext.setAudioVideoUrls(audioVideoUrls);
        this.mPlaybackSessionResources.getEventReporter().setDeliveryType(deliveryType);
        Optional<String> offlineKeyId = download.getOfflineKeyId();
        if (offlineKeyId.isPresent()) {
            this.mPlaybackSessionContext.setOfflineKeyId(offlineKeyId.get());
        }
    }
}
