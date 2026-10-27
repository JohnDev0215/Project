package com.amazon.avod.media.playback.reporting.aloysius;

import com.amazon.avod.metrics.aloysius.AloysiusDownloadEventReporter;
import com.amazon.avod.util.DLog;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class NoopDownloadReporter extends AloysiusDownloadEventReporter {
    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void initialize() {
    }

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void terminate() {
    }

    @Override // com.amazon.avod.metrics.aloysius.AloysiusDownloadEventReporter
    public void reportDownloadEvent(@Nonnull AloysiusDownloadEventReporter.Action action, int i, @Nonnull String str) {
        DLog.devf("Reported download event:%s for no op", action.name());
    }
}
