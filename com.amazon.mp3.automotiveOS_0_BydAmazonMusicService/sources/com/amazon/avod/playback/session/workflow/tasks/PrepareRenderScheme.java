package com.amazon.avod.playback.session.workflow.tasks;

import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.playback.session.PlaybackSessionResources;
import com.amazon.avod.userdownload.PVDownloadPlayerShim;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.EventBus;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PrepareRenderScheme extends SimpleTask {
    private final PlaybackSessionContext mPlaybackSessionContext;
    private final PlaybackSessionResources mPlaybackSessionResources;

    public PrepareRenderScheme(@Nonnull EventBus eventBus, @Nonnull ExceptionCallback exceptionCallback, @Nonnull PlaybackSessionResources playbackSessionResources, @Nonnull PlaybackSessionContext playbackSessionContext) {
        super(eventBus, exceptionCallback);
        this.mPlaybackSessionResources = (PlaybackSessionResources) Preconditions.checkNotNull(playbackSessionResources, "playbackSessionResources");
        this.mPlaybackSessionContext = (PlaybackSessionContext) Preconditions.checkNotNull(playbackSessionContext, "playbackSessionContext");
    }

    @Override // com.amazon.avod.playback.session.workflow.scheduler.BaseTask
    protected void execute() throws MediaException {
        RendererSchemeType schemeType;
        PVDownloadPlayerShim download = this.mPlaybackSessionContext.getDownload();
        if (download != null && download.getRendererSchemeType().isPresent()) {
            schemeType = download.getRendererSchemeType().get();
        } else {
            VideoSpecification videoSpec = this.mPlaybackSessionContext.getVideoSpec();
            schemeType = this.mPlaybackSessionResources.getRendererSchemeController().getRendererScheme(videoSpec.getRendererSchemeTypeString() != null ? RendererSchemeType.fromSchemeString(videoSpec.getRendererSchemeTypeString()).get() : null, null).getSchemeType();
        }
        if (this.mPlaybackSessionContext.getAudioVideoUrls().getContentUrls().get(0).isTnf()) {
            if (this.mPlaybackSessionResources.getPlaybackConfig().shouldForceOmxForTnf()) {
                schemeType = RendererSchemeType.OMXIL;
            } else if (this.mPlaybackSessionResources.getPlaybackConfig().shouldForceMediaCodecMediaDrmForTnf()) {
                schemeType = RendererSchemeType.MEDIACODEC_MEDIADRM;
            }
        }
        this.mPlaybackSessionContext.setRendererSchemeType(schemeType);
    }
}
