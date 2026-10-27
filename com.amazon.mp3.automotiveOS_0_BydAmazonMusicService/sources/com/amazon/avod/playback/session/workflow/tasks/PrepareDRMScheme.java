package com.amazon.avod.playback.session.workflow.tasks;

import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.playback.session.PlaybackSessionResources;
import com.amazon.avod.userdownload.PVDownloadPlayerShim;
import com.amazon.avod.util.DLog;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.EventBus;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PrepareDRMScheme extends SimpleTask {
    private final PlaybackSessionContext mPlaybackSessionContext;
    private final PlaybackSessionResources mPlaybackSessionResources;

    public PrepareDRMScheme(@Nonnull EventBus eventBus, @Nonnull ExceptionCallback exceptionCallback, @Nonnull PlaybackSessionResources playbackSessionResources, @Nonnull PlaybackSessionContext playbackSessionContext) {
        super(eventBus, exceptionCallback);
        this.mPlaybackSessionResources = (PlaybackSessionResources) Preconditions.checkNotNull(playbackSessionResources, "playbackSessionResources");
        this.mPlaybackSessionContext = (PlaybackSessionContext) Preconditions.checkNotNull(playbackSessionContext, "playbackSessionContext");
    }

    @Override // com.amazon.avod.playback.session.workflow.scheduler.BaseTask
    protected void execute() throws MediaException {
        DrmScheme currentDrmScheme = this.mPlaybackSessionResources.getRendererSchemeController().getRendererScheme(this.mPlaybackSessionContext.getRendererSchemeType(), null).getDrmFramework().getCurrentDrmScheme();
        PVDownloadPlayerShim download = this.mPlaybackSessionContext.getDownload();
        if (download != null) {
            Optional<DrmScheme> drmScheme = download.getDrmScheme();
            if (!drmScheme.isPresent()) {
                return;
            } else {
                currentDrmScheme = drmScheme.get();
            }
        }
        if (this.mPlaybackSessionContext.getAudioVideoUrls().getContentUrls().get(0).isTnf() && (this.mPlaybackSessionResources.getPlaybackConfig().shouldUseWidevineForTnf() || this.mPlaybackSessionResources.getPlaybackConfig().shouldForceMediaCodecMediaDrmForTnf())) {
            DLog.warnf("Falling back to use Widevine DrmScheme for TNF!");
            currentDrmScheme = DrmScheme.WIDEVINE;
        }
        if (currentDrmScheme == DrmScheme.WIDEVINE && !this.mPlaybackSessionContext.isDashSession()) {
            DLog.warnf("Cannot use Widevine DrmScheme without DASH, reverting to PlayReady!");
            currentDrmScheme = DrmScheme.PLAYREADY;
        }
        this.mPlaybackSessionContext.setDrmScheme(currentDrmScheme);
    }
}
