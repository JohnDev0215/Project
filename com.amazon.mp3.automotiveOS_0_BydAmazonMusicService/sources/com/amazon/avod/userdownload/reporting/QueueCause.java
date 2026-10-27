package com.amazon.avod.userdownload.reporting;

import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum QueueCause implements Cause {
    SEASON_USER_QUEUE("User:Season", "SeasonUserQueue"),
    USER_QUEUE("User", "UserQueue"),
    SYNC_QUEUE("Sync:AutoDownloads", "SyncQueue"),
    SDK_QUEUE("Other:SDK", "SdkQueue"),
    QA_QUEUE("Other:QA", "QAQueue");

    private static final String OPERATION_NAME = "DownloadQueue";
    private final String mCauseMessage;
    private final String mEventSubType;

    QueueCause(@Nonnull String str, @Nonnull String str2) {
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
