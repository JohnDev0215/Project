package com.amazon.avod.userdownload;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.media.framework.error.LicenseQueryException;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.userdownload.reporting.LicenseOperationCause;
import com.amazon.avod.userdownload.reporting.MakeActiveCause;
import com.amazon.avod.userdownload.reporting.PauseCause;
import com.google.common.base.Optional;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface PVDownloadManagerPlayerShim {
    void consumeRightIfNeeded(PVDownloadPlayerShim pVDownloadPlayerShim);

    @Nonnull
    Optional<PVDownloadPlayerShim> getOfflineDownloadIfPresent(VideoSpecification videoSpecification, Map<String, String> map);

    @Nonnull
    Optional<PVDownloadPlayerShim> getOfflineDownloadIfPresent(String str, Map<String, String> map);

    void makeActive(PVDownloadPlayerShim pVDownloadPlayerShim, MakeActiveCause makeActiveCause);

    @Deprecated
    void onPlaybackLicenseError(PVDownloadPlayerShim pVDownloadPlayerShim, LicenseQueryException licenseQueryException, LicenseOperationCause licenseOperationCause);

    @Nonnull
    PauseToken pause(PauseCause pauseCause);

    void registerStreamingDownloadEventListener(PVDownloadPlayerShim pVDownloadPlayerShim, ContentSession contentSession);

    void resume(PauseToken pauseToken);
}
