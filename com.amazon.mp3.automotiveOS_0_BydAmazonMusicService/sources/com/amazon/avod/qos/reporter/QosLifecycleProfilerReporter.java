package com.amazon.avod.qos.reporter;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.LifecycleProfilerReporter;
import com.amazon.avod.pmet.LifecycleProfilerMetrics;
import com.amazon.avod.qos.metadata.QOSEventName;
import com.google.common.base.Joiner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class QosLifecycleProfilerReporter implements LifecycleProfilerReporter {
    private final com.amazon.avod.playback.PlaybackEventReporter mReporter;

    public QosLifecycleProfilerReporter(@Nonnull com.amazon.avod.playback.PlaybackEventReporter playbackEventReporter) {
        this.mReporter = playbackEventReporter;
    }

    @Override // com.amazon.avod.playback.LifecycleProfilerReporter
    public void reportMetric(@Nonnull Map<LifecycleProfilerMetrics, Long> map) {
        this.mReporter.reportMetric(QOSEventName.Information.toString(), "PlayersTTFFTraces", TimeSpan.ZERO, serializeTracesToNoteField(map), null);
    }

    private String serializeTracesToNoteField(@Nonnull Map<LifecycleProfilerMetrics, Long> map) {
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry<LifecycleProfilerMetrics, Long> entry : map.entrySet()) {
            String reportableString = entry.getKey().toReportableString();
            Long value = entry.getValue();
            arrayList.add(String.format(Locale.US, "%s:%s", reportableString, value == null ? "Invalid" : Long.toString(value.longValue())));
        }
        Collections.sort(arrayList);
        return Joiner.on(",").useForNull("").join(arrayList);
    }
}
