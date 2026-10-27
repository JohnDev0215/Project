package com.amazon.avod.userdownload.reporting;

import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum RedownloadCause implements Cause {
    DOWNLOAD_TO_DIFFERENT_SD_CARD("User:DifferentSDCard", "UserRedownload"),
    DOWNLOAD_TO_INTERNAL_STORAGE("User:InternalStorage", "UserRedownload"),
    UNAVAILABLE_DOWNLOAD_QUEUED_TO_NEW_STORAGE_MEDIUM("User:QueueToNewStorageMedium", "UserRedownload"),
    USER_INITIATED_INCOMPLETE_SDK_DOWNLOAD("User:InitiatedIncompleteDownload", "UserRedownload"),
    PLAYER_SDK_MISSING_DOWNLOAD("User:PlayerSdkMissingDownload", "PlayerSdkRedownload");

    private static final String OPERATION_NAME = "DownloadRedownload";
    private final String mCauseMessage;
    private final String mEventSubType;

    RedownloadCause(@Nonnull String str, @Nonnull String str2) {
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
