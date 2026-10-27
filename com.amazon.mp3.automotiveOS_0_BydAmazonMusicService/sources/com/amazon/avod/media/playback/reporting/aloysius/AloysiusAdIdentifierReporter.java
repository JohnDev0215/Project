package com.amazon.avod.media.playback.reporting.aloysius;

import com.amazon.avod.media.events.MediaEventReporter;
import com.amazon.avod.playback.event.playback.AdTrackingAuditPingEvent;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface AloysiusAdIdentifierReporter extends MediaEventReporter {
    @Override // com.amazon.avod.media.events.MediaEventReporter
    void initialize();

    void onAdTrackingAuditPingEvent(@Nonnull AdTrackingAuditPingEvent adTrackingAuditPingEvent);

    @Override // com.amazon.avod.media.events.MediaEventReporter
    void terminate();
}
