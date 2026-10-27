package com.amazon.avod.userdownload.reporting;

import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum PauseCause implements Cause {
    WAN_CONNECTED_PAUSE("Automatic:WANConnected", OPERATION_NAME, ResumeCause.WAN_CONNECTED_RESUME),
    WIFI_DISCONNECTED_PAUSE("Automatic:WifiDisconnected", OPERATION_NAME, ResumeCause.WIFI_CONNECTED_RESUME),
    PLAYBACK_STARTED_PAUSE("Automatic:PlaybackStarted", OPERATION_NAME, ResumeCause.PLAYBACK_FINISHED_RESUME),
    SYNCHRONOUS_DOWNLOAD_DELETION("Automatic:SynchronousDownloadDeletion", OPERATION_NAME, ResumeCause.SYNCHRONOUS_DOWNLOAD_DELETION),
    SYNCHRONOUS_DOWNLOAD_MIGRATION("Automatic:SynchronousDownloadMigration", OPERATION_NAME, ResumeCause.SYNCHRONOUS_DOWNLOAD_MIGRATION),
    EXTERNAL_STORAGE_STATE_UNKNOWN("Automatic:ExternalStorageStateUnknown", OPERATION_NAME, ResumeCause.EXTERNAL_STORAGE_STATE_UNKNOWN),
    MEDIA_SYSTEM_NOT_INITIALIZED("Automatic:MediaSystemNotInitialized", OPERATION_NAME, ResumeCause.MEDIA_SYSTEM_NOT_INITIALIZED);

    private static final String OPERATION_NAME = "DownloadQueuePause";
    private final String mCauseMessage;
    private final String mEventSubType;
    private final ResumeCause mResumeCause;

    PauseCause(@Nonnull String str, @Nonnull String str2, @Nonnull ResumeCause resumeCause) {
        Preconditions.checkNotNull(str, "causeMessage");
        this.mCauseMessage = String.format(Locale.US, "%s:%s", OPERATION_NAME, str);
        this.mEventSubType = (String) Preconditions.checkNotNull(str2, "downloadSubtype");
        this.mResumeCause = (ResumeCause) Preconditions.checkNotNull(resumeCause, "resumeCause");
    }

    @Override // com.amazon.avod.userdownload.reporting.Cause
    public String getCauseMessage() {
        return this.mCauseMessage;
    }

    @Override // com.amazon.avod.userdownload.reporting.Cause
    public String getEventSubType() {
        return this.mEventSubType;
    }

    public Cause getResumeCause() {
        return this.mResumeCause;
    }

    @Override // com.amazon.avod.metrics.pmet.MetricParameter
    @Nonnull
    public String toReportableString() {
        return getCauseMessage();
    }
}
