package com.amazon.device.crashmanager.source;

import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.device.crashmanager.Artifact;

/* JADX INFO: loaded from: classes2.dex */
public interface ArtifactSource {
    Artifact getNextArtifact(MetricEvent metricEvent, String str);

    void saveCurrentIndex();
}
