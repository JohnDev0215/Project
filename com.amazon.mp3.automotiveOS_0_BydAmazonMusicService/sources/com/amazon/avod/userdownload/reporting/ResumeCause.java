package com.amazon.avod.userdownload.reporting;

import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum ResumeCause implements Cause {
    WAN_CONNECTED_RESUME("Automatic:WANConnected", OPERATION_NAME),
    WIFI_CONNECTED_RESUME("Automatic:WifiConnected", OPERATION_NAME),
    PLAYBACK_FINISHED_RESUME("Automatic:PlaybackFinished", OPERATION_NAME),
    SYNCHRONOUS_DOWNLOAD_DELETION("Automatic:SynchronousDownloadDeletion", OPERATION_NAME),
    SYNCHRONOUS_DOWNLOAD_MIGRATION("Automatic:SynchronousDownloadMigration", OPERATION_NAME),
    EXTERNAL_STORAGE_STATE_UNKNOWN("Automatic:ExternalStorageStateUnknown", OPERATION_NAME),
    MEDIA_SYSTEM_NOT_INITIALIZED("Automatic:MediaSystemNotInitialized", OPERATION_NAME);

    private static final String OPERATION_NAME = "DownloadQueueResume";
    private final String mCauseMessage;
    private final String mEventSubType;

    ResumeCause(@Nonnull String str, @Nonnull String str2) {
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
