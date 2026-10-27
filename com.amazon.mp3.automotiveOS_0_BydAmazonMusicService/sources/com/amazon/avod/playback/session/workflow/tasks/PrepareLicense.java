package com.amazon.avod.playback.session.workflow.tasks;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.media.contentcache.CacheRecord;
import com.amazon.avod.media.drm.DrmLicenseAcquirer;
import com.amazon.avod.media.framework.error.DrmLicensingException;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.playback.LifecycleProfiler;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.playback.session.PlaybackSessionResources;
import com.amazon.avod.pmet.LifecycleProfilerMetrics;
import com.amazon.avod.userdownload.PVDownloadPlayerShim;
import com.amazon.avod.userdownload.reporting.LicenseOperationCause;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.EventBus;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PrepareLicense extends SimpleTask {
    private final PlaybackSessionContext mPlaybackSessionContext;
    private final PlaybackSessionResources mPlaybackSessionResources;

    public PrepareLicense(@Nonnull EventBus eventBus, @Nonnull ExceptionCallback exceptionCallback, @Nonnull PlaybackSessionResources playbackSessionResources, @Nonnull PlaybackSessionContext playbackSessionContext) {
        super(eventBus, exceptionCallback);
        this.mPlaybackSessionResources = (PlaybackSessionResources) Preconditions.checkNotNull(playbackSessionResources, "playbackSessionResources");
        this.mPlaybackSessionContext = (PlaybackSessionContext) Preconditions.checkNotNull(playbackSessionContext, "playbackSessionContext");
    }

    @Override // com.amazon.avod.playback.session.workflow.scheduler.BaseTask
    protected void execute() throws MediaException {
        CacheRecord cacheRecordQueryFor;
        DrmLicenseAcquirer licenseAcquirer = this.mPlaybackSessionResources.getLicenseAcquirer();
        ContentSession contentSession = (ContentSession) Preconditions.checkNotNull(this.mPlaybackSessionContext.getContentSession(), "contentSession");
        try {
            LifecycleProfiler lifecycleProfiler = this.mPlaybackSessionResources.getLifecycleProfiler();
            LifecycleProfilerMetrics lifecycleProfilerMetrics = LifecycleProfilerMetrics.LICENSING;
            lifecycleProfiler.start(lifecycleProfilerMetrics);
            String offlineKeyId = this.mPlaybackSessionContext.getOfflineKeyId();
            if (offlineKeyId == null && this.mPlaybackSessionContext.getDownload() == null && (cacheRecordQueryFor = this.mPlaybackSessionResources.getCacheManager().queryFor(this.mPlaybackSessionContext.getVideoSpec())) != null) {
                offlineKeyId = cacheRecordQueryFor.getOfflineKeyId();
            }
            String strDoLicensing = licenseAcquirer.doLicensing(contentSession, this.mPlaybackSessionContext.getVideoOptions().getSessionContext(), this.mPlaybackSessionContext.getDrmScheme(), this.mPlaybackSessionContext.getRendererSchemeType(), offlineKeyId, this.mPlaybackSessionContext.getVideoSpec().getPlaybackEnvelope());
            lifecycleProfiler.end(lifecycleProfilerMetrics);
            this.mPlaybackSessionContext.setOfflineKeyId(strDoLicensing);
        } catch (MediaException e) {
            PVDownloadPlayerShim download = this.mPlaybackSessionContext.getDownload();
            if (download != null && (e instanceof DrmLicensingException)) {
                this.mPlaybackSessionResources.getDownloadManager().onPlaybackLicenseError(download, ((DrmLicensingException) e).toLicenseQueryException(null), LicenseOperationCause.PLAYER_LICENSE_ERROR);
            }
            throw e;
        }
    }
}
