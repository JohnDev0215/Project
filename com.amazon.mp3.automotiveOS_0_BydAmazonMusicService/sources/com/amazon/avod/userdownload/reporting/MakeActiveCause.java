package com.amazon.avod.userdownload.reporting;

import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum MakeActiveCause implements Cause {
    EPISODE_DOWNLOAD_OPTIONS_LINK_MAKE_ACTIVE("User:EpisodeDownloadOptionsLink:MakeActive", "UserMoveToFront"),
    EPISODE_DOWNLOAD_OPTIONS_LINK_RESUME("User:EpisodeDownloadOptionsLink:Resume", "UserMoveToFront"),
    EPISODE_DETAIL_PAGE_MAKE_ACTIVE("User:EpisodeDetailPage", "UserMoveToFront"),
    DOWNLOAD_LANDING_PAGE_MAKE_ACTIVE("User:DownloadLandingPage", "UserMoveToFront"),
    DOWNLOADS_BASE_MAKE_ACTIVE("User:DownloadsBase", "UserMoveToFront"),
    DETAIL_PAGE_MOVIE_MAKE_ACTIVE("User:MovieDetailPage", "UserMoveToFront"),
    DOWNLOAD_NOTIFICATION_MAKE_ACTIVE("User:DownloadNotification", "UserMoveToFront"),
    LONG_PRESS_MAKE_ACTIVE("User:LongPress:MakeActive", "UserMoveToFront"),
    LONG_PRESS_RESUME("User:LongPress:Resume", "UserMoveToFront"),
    PROGRESSIVE_PLAYBACK("User:ProgressivePlayback", "UserMoveToFront"),
    QA_MAKE_ACTIVE("Other:QA", "QAMoveToFront"),
    PLAYER_SDK_MAKE_ACTIVE("Other:PlayerSDKMakeActive", "PlayerSDKMoveToFront");

    private static final String OPERATION_NAME = "DownloadMakeActive";
    private final String mCauseMessage;
    private final String mEventSubType;

    MakeActiveCause(@Nonnull String str, @Nonnull String str2) {
        Preconditions.checkNotNull(str, "causeMessage");
        this.mCauseMessage = String.format(Locale.US, "%s:%s", OPERATION_NAME, str);
        this.mEventSubType = (String) Preconditions.checkNotNull(str2, "downloadSubtype");
    }

    @Override // com.amazon.avod.userdownload.reporting.Cause
    public String getCauseMessage() {
        return this.mCauseMessage;
    }

    @Override // com.amazon.avod.userdownload.reporting.Cause
    public String getEventSubType() {
        return this.mEventSubType;
    }

    @Override // com.amazon.avod.metrics.pmet.MetricParameter
    @Nonnull
    public String toReportableString() {
        return getCauseMessage();
    }
}
