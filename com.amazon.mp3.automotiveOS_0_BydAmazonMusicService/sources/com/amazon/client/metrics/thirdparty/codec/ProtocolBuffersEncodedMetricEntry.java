package com.amazon.client.metrics.thirdparty.codec;

import com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage;

/* JADX INFO: loaded from: classes2.dex */
public class ProtocolBuffersEncodedMetricEntry implements EncodedMetricEntry {
    private DeviceMetricsMessage.MetricEntryMessage mMetricEntryMessage;

    public ProtocolBuffersEncodedMetricEntry(DeviceMetricsMessage.MetricEntryMessage metricEntryMessage) {
        if (metricEntryMessage == null) {
            throw new IllegalArgumentException("MetricEntryMessage is null");
        }
        this.mMetricEntryMessage = metricEntryMessage;
    }

    @Override // com.amazon.client.metrics.thirdparty.codec.EncodedMetricEntry
    public DeviceMetricsMessage.MetricEntryMessage getEncodedMetricEntry() {
        return this.mMetricEntryMessage;
    }

    @Override // com.amazon.client.metrics.thirdparty.codec.EncodedMetricEntry
    public int getEncodedSize() {
        return this.mMetricEntryMessage.getSerializedSize();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return this.mMetricEntryMessage.equals(((ProtocolBuffersEncodedMetricEntry) obj).getEncodedMetricEntry());
        }
        return false;
    }

    public int hashCode() {
        return this.mMetricEntryMessage.hashCode();
    }

    public String toString() {
        return this.mMetricEntryMessage.toString();
    }
}
