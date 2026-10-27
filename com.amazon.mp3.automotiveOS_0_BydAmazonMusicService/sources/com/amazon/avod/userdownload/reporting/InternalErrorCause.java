package com.amazon.avod.userdownload.reporting;

import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum InternalErrorCause implements Cause {
    ILLEGAL_STATE_TRANSITION("IllegalStateTransition", "ErrorCondition"),
    DUPLICATE_QUEUE_REQUEST("DuplicateQueueRequest", "ErrorCondition"),
    UNEXPECTED_DELETION_PROGRESS("UnexpectedDeletionProgress", "ErrorCondition"),
    UNEXPECTED_DOWNLOAD_PROGRESS("UnexpectedDownloadProgress", "ErrorCondition"),
    SD_CARD_READ_ONLY("SDCardReadOnly", "ErrorCondition");

    private static final String OPERATION_NAME = "DownloadInternalError";
    private final String mCauseMessage;
    private final String mEventSubType;

    InternalErrorCause(@Nonnull String str, @Nonnull String str2) {
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
