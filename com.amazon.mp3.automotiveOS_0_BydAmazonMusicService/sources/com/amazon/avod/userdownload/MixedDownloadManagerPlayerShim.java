package com.amazon.avod.userdownload;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.media.framework.error.LicenseQueryException;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.userdownload.internal.PlaybackDownloadManager;
import com.amazon.avod.userdownload.reporting.LicenseOperationCause;
import com.amazon.avod.userdownload.reporting.MakeActiveCause;
import com.amazon.avod.userdownload.reporting.PauseCause;
import com.google.common.base.Optional;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class MixedDownloadManagerPlayerShim implements PVDownloadManagerPlayerShim {
    private final boolean mEnablePlayerSdkDownloads;
    private final PlaybackDownloadManager mPlaybackDownloadManager;
    private final PVDownloadManagerPlayerShim mUserDownloadManager;

    public MixedDownloadManagerPlayerShim(@Nonnull PVDownloadManagerPlayerShim pVDownloadManagerPlayerShim, @Nonnull PlaybackDownloadManager playbackDownloadManager) {
        this(pVDownloadManagerPlayerShim, playbackDownloadManager, PlayerSdkDownloadConfig.getInstance().enablePlayerSdkDownloads());
    }

    public MixedDownloadManagerPlayerShim(@Nonnull PVDownloadManagerPlayerShim pVDownloadManagerPlayerShim, @Nonnull PlaybackDownloadManager playbackDownloadManager, boolean z) {
        this.mUserDownloadManager = pVDownloadManagerPlayerShim;
        this.mPlaybackDownloadManager = playbackDownloadManager;
        this.mEnablePlayerSdkDownloads = z;
    }

    @Override // com.amazon.avod.userdownload.PVDownloadManagerPlayerShim
    public void resume(PauseToken pauseToken) {
        if (this.mEnablePlayerSdkDownloads) {
            this.mPlaybackDownloadManager.resume(pauseToken);
        }
        this.mUserDownloadManager.resume(pauseToken);
    }

    @Override // com.amazon.avod.userdownload.PVDownloadManagerPlayerShim
    @Nonnull
    public PauseToken pause(PauseCause pauseCause) {
        if (this.mEnablePlayerSdkDownloads) {
            this.mPlaybackDownloadManager.pause(pauseCause);
        }
        return this.mUserDownloadManager.pause(pauseCause);
    }

    @Override // com.amazon.avod.userdownload.PVDownloadManagerPlayerShim
    public void makeActive(@Nonnull PVDownloadPlayerShim pVDownloadPlayerShim, @Nonnull MakeActiveCause makeActiveCause) {
        if (pVDownloadPlayerShim instanceof PlaybackDownload) {
            if (this.mEnablePlayerSdkDownloads) {
                UnmodifiableIterator<PlaybackDownload> it = this.mPlaybackDownloadManager.getAllDownloadsForAllUsers().iterator();
                while (it.hasNext()) {
                    if (pVDownloadPlayerShim.equals(it.next())) {
                        this.mPlaybackDownloadManager.makeActive(pVDownloadPlayerShim, makeActiveCause);
                        return;
                    }
                }
                return;
            }
            return;
        }
        this.mUserDownloadManager.makeActive(pVDownloadPlayerShim, makeActiveCause);
    }

    @Override // com.amazon.avod.userdownload.PVDownloadManagerPlayerShim
    public void registerStreamingDownloadEventListener(PVDownloadPlayerShim pVDownloadPlayerShim, ContentSession contentSession) {
        if (pVDownloadPlayerShim instanceof PlaybackDownload) {
            if (this.mEnablePlayerSdkDownloads) {
                UnmodifiableIterator<PlaybackDownload> it = this.mPlaybackDownloadManager.getAllDownloadsForAllUsers().iterator();
                while (it.hasNext()) {
                    if (pVDownloadPlayerShim.equals(it.next())) {
                        this.mPlaybackDownloadManager.registerStreamingDownloadEventListener(pVDownloadPlayerShim, contentSession);
                        return;
                    }
                }
                return;
            }
            return;
        }
        this.mUserDownloadManager.registerStreamingDownloadEventListener(pVDownloadPlayerShim, contentSession);
    }

    @Override // com.amazon.avod.userdownload.PVDownloadManagerPlayerShim
    @Nonnull
    public Optional<PVDownloadPlayerShim> getOfflineDownloadIfPresent(VideoSpecification videoSpecification, Map<String, String> map) {
        if (this.mEnablePlayerSdkDownloads) {
            Optional<PVDownloadPlayerShim> offlineDownloadIfPresent = this.mPlaybackDownloadManager.getOfflineDownloadIfPresent(videoSpecification, map);
            if (offlineDownloadIfPresent.isPresent()) {
                return offlineDownloadIfPresent;
            }
        }
        return this.mUserDownloadManager.getOfflineDownloadIfPresent(videoSpecification, map);
    }

    @Override // com.amazon.avod.userdownload.PVDownloadManagerPlayerShim
    @Nonnull
    public Optional<PVDownloadPlayerShim> getOfflineDownloadIfPresent(String str, Map<String, String> map) {
        if (this.mEnablePlayerSdkDownloads) {
            Optional<PVDownloadPlayerShim> offlineDownloadIfPresent = this.mPlaybackDownloadManager.getOfflineDownloadIfPresent(str, map);
            if (offlineDownloadIfPresent.isPresent()) {
                return offlineDownloadIfPresent;
            }
        }
        return this.mUserDownloadManager.getOfflineDownloadIfPresent(str, map);
    }

    @Override // com.amazon.avod.userdownload.PVDownloadManagerPlayerShim
    public void onPlaybackLicenseError(PVDownloadPlayerShim pVDownloadPlayerShim, LicenseQueryException licenseQueryException, LicenseOperationCause licenseOperationCause) {
        if (pVDownloadPlayerShim instanceof PlaybackDownload) {
            if (this.mEnablePlayerSdkDownloads) {
                this.mPlaybackDownloadManager.onPlaybackLicenseError(pVDownloadPlayerShim, licenseQueryException, licenseOperationCause);
                return;
            }
            return;
        }
        this.mUserDownloadManager.onPlaybackLicenseError(pVDownloadPlayerShim, licenseQueryException, licenseOperationCause);
    }

    @Override // com.amazon.avod.userdownload.PVDownloadManagerPlayerShim
    public void consumeRightIfNeeded(PVDownloadPlayerShim pVDownloadPlayerShim) {
        if (pVDownloadPlayerShim instanceof PlaybackDownload) {
            if (this.mEnablePlayerSdkDownloads) {
                UnmodifiableIterator<PlaybackDownload> it = this.mPlaybackDownloadManager.getAllDownloadsForAllUsers().iterator();
                while (it.hasNext()) {
                    if (pVDownloadPlayerShim.equals(it.next())) {
                        this.mPlaybackDownloadManager.consumeRightIfNeeded(pVDownloadPlayerShim);
                        return;
                    }
                }
                return;
            }
            return;
        }
        this.mUserDownloadManager.consumeRightIfNeeded(pVDownloadPlayerShim);
    }
}
