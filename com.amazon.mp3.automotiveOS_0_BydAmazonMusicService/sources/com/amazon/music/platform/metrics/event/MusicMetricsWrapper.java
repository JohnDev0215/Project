package com.amazon.music.platform.metrics.event;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MusicMetricsWrapper.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\n\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000f\u001a\u00020\u000e8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0014\u0010\u0012R#\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00158\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R#\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00158\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u0017\u001a\u0004\b\u001b\u0010\u0019R)\u0010\u001d\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00150\u001c8\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 ¨\u0006!"}, d2 = {"Lcom/amazon/music/platform/metrics/event/MusicMetricsWrapper;", "Lcom/amazon/music/platform/metrics/event/MetricsWrapper;", "", "toString", "", "hashCode", "", "other", "", "equals", "metricsEventName", "Ljava/lang/String;", "getMetricsEventName", "()Ljava/lang/String;", "", "timeStamp", "J", "getTimeStamp", "()J", "eventVersion", "getEventVersion", "", "attributes", "Ljava/util/Map;", "getAttributes", "()Ljava/util/Map;", "metricsContext", "getMetricsContext", "", "contentInfoAttributes", "Ljava/util/List;", "getContentInfoAttributes", "()Ljava/util/List;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class MusicMetricsWrapper implements MetricsWrapper {
    private final Map<String, String> attributes;
    private final List<Map<String, String>> contentInfoAttributes;
    private final long eventVersion;
    private final Map<String, String> metricsContext;
    private final String metricsEventName;
    private final long timeStamp;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MusicMetricsWrapper)) {
            return false;
        }
        MusicMetricsWrapper musicMetricsWrapper = (MusicMetricsWrapper) other;
        return Intrinsics.areEqual(getMetricsEventName(), musicMetricsWrapper.getMetricsEventName()) && getTimeStamp() == musicMetricsWrapper.getTimeStamp() && this.eventVersion == musicMetricsWrapper.eventVersion && Intrinsics.areEqual(this.attributes, musicMetricsWrapper.attributes) && Intrinsics.areEqual(this.metricsContext, musicMetricsWrapper.metricsContext) && Intrinsics.areEqual(this.contentInfoAttributes, musicMetricsWrapper.contentInfoAttributes);
    }

    public int hashCode() {
        return (((((((((getMetricsEventName().hashCode() * 31) + Long.hashCode(getTimeStamp())) * 31) + Long.hashCode(this.eventVersion)) * 31) + this.attributes.hashCode()) * 31) + this.metricsContext.hashCode()) * 31) + this.contentInfoAttributes.hashCode();
    }

    public String toString() {
        return "MusicMetricsWrapper(metricsEventName=" + getMetricsEventName() + ", timeStamp=" + getTimeStamp() + ", eventVersion=" + this.eventVersion + ", attributes=" + this.attributes + ", metricsContext=" + this.metricsContext + ", contentInfoAttributes=" + this.contentInfoAttributes + ')';
    }

    public String getMetricsEventName() {
        return this.metricsEventName;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public final long getEventVersion() {
        return this.eventVersion;
    }

    public final Map<String, String> getAttributes() {
        return this.attributes;
    }

    public final Map<String, String> getMetricsContext() {
        return this.metricsContext;
    }

    public final List<Map<String, String>> getContentInfoAttributes() {
        return this.contentInfoAttributes;
    }
}
