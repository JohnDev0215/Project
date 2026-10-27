package com.amazon.avod.userdownload.reporting;

import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum DeletionCause implements Cause {
    USER_INITIATED_RESTART_WITH_DIFFERENT_QUALITY("User:ChangeQuality", "UserDelete"),
    USER_INITIATED_CANCEL_FROM_NOTIFICATION_TRAY("User:NotificationTrayCancel", "UserDelete"),
    USER_INITIATED_LONG_PRESS_DELETE("User:LongPressDelete", "UserDelete"),
    USER_INITIATED_LONG_PRESS_CANCEL("User:LongPressCancel", "UserDelete"),
    USER_INITIATED_DETAIL_PAGE_DELETE("User:DetailPageDelete", "UserDelete"),
    USER_INITIATED_DETAIL_PAGE_SEASON_DOWNLOAD_CANCEL("User:DetailPageSeasonDownloadCancel", "SeasonUserCancel"),
    USER_INITIATED_DETAIL_PAGE_SEASON_DOWNLOAD_DELETE("User:DetailPageSeasonDownloadDelete", "SeasonUserDelete"),
    USER_INITIATED_DETAIL_PAGE_CANCEL("User:DetailPageCancel", "UserDelete"),
    USER_INITIATED_DOWNLOAD_LANDING_PAGE_DELETE("User:DownloadLandingPageDelete", "UserDelete"),
    USER_INITIATED_DOWNLOADS_LANDING_DELETE("User:DownloadsLandingDelete", "UserDelete"),
    USER_INITIATED_ALL_EXTERNAL("User:AllExternal", "UserDelete"),
    USER_INITIATED_CANCEL_GEO_IP_RESTRICTION("User:GeoIPRestrictionError", "UserDelete"),
    USER_INITIATED_CANCEL_GENERIC_ERROR("User:GenericError", "UserDelete"),
    CMS_INITIATED("Other:CMS", "UserDelete"),
    LAUNCHER_INITIATED("User:Launcher", "UserDelete"),
    QA_DELETE_BY_INTENT("Other:QAIntent", "QADelete"),
    SDK_DELETE("Other:SDKDelete", "SdkDelete"),
    SDK_CANCEL("Other:SDKCancel", "SdkDelete"),
    PLAYER_SDK_DELETE("Other:PlayerSDKDelete", "PlayerSDKDelete"),
    PLAYER_SDK_CANCEL("Other:PlayerSDKCancel", "PlayerSDKDelete"),
    AUTOMATIC_MARKETPLACE_CHANGE("Automatic:MarketplaceChange", "AppDelete"),
    AUTOMATIC_ORPHANED_USER("Automatic:OrphanedUser", "AppDelete"),
    AUTOMATIC_ORPHANED_FILE("Automatic:OrphanedFile", "AppDelete"),
    AUTOMATIC_INSUFFICIENT_SPACE("Automatic:InsufficientSpace", "AppDelete"),
    AUTOMATIC_FILE_DELETED("Automatic:FileDeleted", "AppDelete"),
    AUTOMATIC_RELEASE_LICENSE("Automatic:ReleaseLicense", "AppDelete"),
    PRS_DELETE("Automatic:DeletedByPRS", "AppDelete"),
    PLAYER_SDK_DIAL_DOWN("Automatic:PlayerSdkDialDown", "PlayerSDKDelete"),
    SYNC_FALLBACK_DELETE("CharonSync:Fallback", "SyncDelete"),
    AUTO_DOWNLOADS_DELETION("CharonSync:AutoDownloads", "SyncDelete"),
    ACEMS_DATA_DELETION("CharonSync:ACEMSData", "SyncDelete");

    private static final String OPERATION_NAME = "DownloadDeletion";
    private final String mCauseMessage;
    private final String mEventSubType;

    DeletionCause(@Nonnull String str, @Nonnull String str2) {
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
