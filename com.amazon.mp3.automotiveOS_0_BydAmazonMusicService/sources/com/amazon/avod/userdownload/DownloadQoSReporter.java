package com.amazon.avod.userdownload;

import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.qos.metadata.QOSEventName;
import com.amazon.avod.util.Preconditions2;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadQoSReporter {
    public static final ImmutableSet<String> ERROR_SUBTYPES = ImmutableSet.of("Errored", "Broken");
    private static final String INFO_EVENT_TYPE = QOSEventName.Information.name();
    private final PlaybackEventReporter mEventReporter;
    private final String mEventType;

    public enum DownloadQoSEventType {
        PLAYER_SDK_DOWNLOAD_EVENT(QOSEventName.PlayerSdkDownloadEvent);

        private QOSEventName mQOSEventName;

        DownloadQoSEventType(QOSEventName qOSEventName) {
            this.mQOSEventName = qOSEventName;
        }
    }

    public DownloadQoSReporter(@Nonnull PlaybackEventReporter playbackEventReporter, @Nonnull DownloadQoSEventType downloadQoSEventType) {
        this.mEventReporter = (PlaybackEventReporter) Preconditions.checkNotNull(playbackEventReporter, "eventReporter");
        this.mEventType = (String) Preconditions.checkNotNull(downloadQoSEventType.mQOSEventName.name(), "eventType");
    }

    public void reportMetric(@Nonnull String str, @Nonnull String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, int i) {
        Preconditions.checkNotNull(str, "eventSubtype");
        Preconditions.checkNotNull(str2, "titleId");
        if (ERROR_SUBTYPES.contains(str)) {
            Preconditions.checkArgument(str7 != null, "errorMessage required for subtype %s", str);
            Preconditions2.checkPositive(i, String.format(Locale.US, "errorSeverity required for subtype %s", str));
        }
        this.mEventReporter.reportMetric(this.mEventReporter.createMetricsBuilder().titleId(str2).eventType(this.mEventType).eventSubtype(str).deliveryType(str5).urlSetId(str3).url(str4).note(str6).errorMessage(str7).errorSeverity(Integer.valueOf(i)));
    }

    public void reportInformationalMetric(@Nullable String str, @Nullable String str2) {
        this.mEventReporter.reportMetric(this.mEventReporter.createMetricsBuilder().eventType(INFO_EVENT_TYPE).eventSubtype(str).note(str2));
    }
}
