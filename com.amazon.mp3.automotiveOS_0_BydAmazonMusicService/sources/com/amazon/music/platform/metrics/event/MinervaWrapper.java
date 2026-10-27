package com.amazon.music.platform.metrics.event;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MinervaWrapper.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\b\b\u0086\b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\rR#\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R&\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u00158\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u0012\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/platform/metrics/event/MinervaWrapper;", "Lcom/amazon/music/platform/metrics/event/MetricsWrapper;", "", "toString", "", "hashCode", "", "other", "", "equals", "metricsGroupId", "Ljava/lang/String;", "getMetricsGroupId", "()Ljava/lang/String;", "metricSchema", "getMetricSchema", "", "metrics", "Ljava/util/Map;", "getMetrics", "()Ljava/util/Map;", "", "paramList", "Ljava/util/List;", "getParamList", "()Ljava/util/List;", "getParamList$annotations", "()V", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class MinervaWrapper implements MetricsWrapper {
    private final String metricSchema;
    private final Map<String, Object> metrics;
    private final String metricsGroupId;
    private final List<Object> paramList;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MinervaWrapper)) {
            return false;
        }
        MinervaWrapper minervaWrapper = (MinervaWrapper) other;
        return Intrinsics.areEqual(this.metricsGroupId, minervaWrapper.metricsGroupId) && Intrinsics.areEqual(this.metricSchema, minervaWrapper.metricSchema) && Intrinsics.areEqual(this.metrics, minervaWrapper.metrics) && Intrinsics.areEqual(this.paramList, minervaWrapper.paramList);
    }

    public int hashCode() {
        String str = this.metricsGroupId;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.metricSchema;
        return ((((iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + this.metrics.hashCode()) * 31) + this.paramList.hashCode();
    }

    public String toString() {
        return "MinervaWrapper(metricsGroupId=" + this.metricsGroupId + ", metricSchema=" + this.metricSchema + ", metrics=" + this.metrics + ", paramList=" + this.paramList + ')';
    }

    public final String getMetricsGroupId() {
        return this.metricsGroupId;
    }

    public final String getMetricSchema() {
        return this.metricSchema;
    }

    public final List<Object> getParamList() {
        return this.paramList;
    }
}
