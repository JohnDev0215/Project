package com.amazon.avod.userdownload.reporting;

import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum LicenseOperationCause implements Cause {
    LICENSE_REMOVED_DOWNLOAD_DELETED("AppLicenseRemoved:DownloadDeletion", "AppLicenseRemoved"),
    LICENSE_REMOVED_PRS_DIRECTIVE("AppLicenseRemoved:PrsDirective", "AppLicenseRemoved"),
    LICENSE_REMOVED_NO_SERVER_ENTITLEMENTS("AppLicenseRemoved:NoServerEntitlements", "AppLicenseRemoved"),
    LICENSE_REMOVED_MISSING_DRM_RECORD("AppLicenseRemoved:MissingDrmRecord", "AppLicenseRemoved"),
    LICENSE_REMOVED_AV_MARKETPLACE_CHANGE("AppLicenseRemoved:AvMarketplaceChange", "AppLicenseRemoved"),
    AUTOMATIC_SYNC_REFRESH_EXPIRABLE_LICENSE("AutomaticSync:LicenseAcquired:Refresh", "SyncServiceLicenseRefresh"),
    AUTOMATIC_SYNC_ACQUIRE_MISSING_LICENSE("AutomaticSync:LicenseAcquired:Missing", "SyncServiceAcquiredMissingLicense"),
    AUTOMATIC_LICENSE_SYNC_ACTION("AutomaticSync:LicenseSyncAction", "SyncWithDrmStore"),
    AUTOMATIC_OFFLINE_VALIDATOR("AutomaticSync:OfflineValidator", "SyncWithDrmStore"),
    PLAYER_LICENSE_ERROR("Player:LicenseError", "SyncWithDrmStore"),
    PLAYER_SDK_REFRESH_LICENSE("PlayerSDK:RefreshLicense", "PlayerSDKRefreshLicense"),
    PLAYER_SDK_SYNC_LICENSE("PlayerSDK:RefreshLicense", "PlayerSDKSyncLicense"),
    PLAYER_SDK_RELEASE_LICENSE("PlayerSDK:RefreshLicense", "PlayerSDKReleaseLicense");

    private static final String OPERATION_NAME = "DownloadLicenseOperation";
    private final String mCauseMessage;
    private final String mEventSubType;

    LicenseOperationCause(@Nonnull String str, @Nonnull String str2) {
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
