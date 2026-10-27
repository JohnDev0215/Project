package com.amazon.client.metrics.thirdparty.codec;

import com.amazon.client.metrics.thirdparty.CodecException;
import com.amazon.client.metrics.thirdparty.MetricEntry;

/* JADX INFO: loaded from: classes2.dex */
public class MetricEntryToStringCodec implements MetricEntryCodec {
    @Override // com.amazon.client.metrics.thirdparty.codec.MetricEntryCodec
    public EncodedMetricEntry encode(MetricEntry metricEntry) throws CodecException {
        return new StringEncodedMetricEntry(metricEntry.toString());
    }
}
