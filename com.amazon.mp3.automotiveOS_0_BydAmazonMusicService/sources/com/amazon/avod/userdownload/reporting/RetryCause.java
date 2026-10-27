package com.amazon.avod.userdownload.reporting;

import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum RetryCause implements Cause {
    USER_RETRY_GENERIC_ERROR("User:GenericError", "UserRetry"),
    USER_RETRY_LICENSE_REPAIR("User:LicenseRepair", "UserRetry"),
    USER_RETRY_SD_CARD_FULL("User:DiskFullSDCard", "UserRetry"),
    USER_RETRY_INTERNAL_STORAGE_FULL("User:DiskFullInternal", "UserRetry"),
    CHARON_SYNC_RETRY_NO_SERVER_ENTITLEMENTS("CharonSync:NoServerEntitlements", "CharonSyncRetry"),
    SDK_RETRY("Other:SDK", "SDKRetry"),
    USER_RETRY_FREED_SPACE("Automatic:DiskFullFreedSpace", "AutomaticRetry"),
    NO_AVAILABLE_DOWNLOAD_RIGHTS_FOLLOWING_LICENSE_RELEASE("Automatic:NoAvailableDownloadRightsFollowingLicenseRelease", "AutomaticRetry"),
    QA_RETRY("Other:QA", "QARetry"),
    PLAYBACK_SDK_RETRY_INTERNAL("PlaybackSDK:Internal", "SDKRetry");

    private static final String OPERATION_NAME = "DownloadRetry";
    private final String mCauseMessage;
    private final String mEventSubType;

    RetryCause(@Nonnull String str, @Nonnull String str2) {
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
