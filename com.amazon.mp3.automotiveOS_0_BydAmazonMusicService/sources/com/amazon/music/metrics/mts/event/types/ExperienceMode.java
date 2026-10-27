package com.amazon.music.metrics.mts.event.types;

import com.amazon.music.metrics.mts.event.types.core.MetricValueType;
import kotlin.Metadata;

/* JADX INFO: compiled from: ExperienceMode.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0004H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/amazon/music/metrics/mts/event/types/ExperienceMode;", "", "Lcom/amazon/music/metrics/mts/event/types/core/MetricValueType;", "metricsValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getMetricValue", "MOBILE", "ANDROID_AUTO", "CAR_MODE", "DMMMetricsMTSEvents_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum ExperienceMode implements MetricValueType {
    MOBILE("MOBILE"),
    ANDROID_AUTO("ANDROID_AUTO"),
    CAR_MODE("CAR_MODE");

    private final String metricsValue;

    ExperienceMode(String str) {
        this.metricsValue = str;
    }

    @Override // com.amazon.music.metrics.mts.event.types.core.MetricValueType
    /* JADX INFO: renamed from: getMetricValue, reason: from getter */
    public String getMetricsValue() {
        return this.metricsValue;
    }
}
