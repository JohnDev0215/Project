package com.amazon.client.metrics.thirdparty.codec;

import com.amazon.client.metrics.thirdparty.CodecException;
import com.amazon.client.metrics.thirdparty.MetricEntry;

/* JADX INFO: loaded from: classes2.dex */
public interface MetricEntryCodec {
    EncodedMetricEntry encode(MetricEntry metricEntry) throws CodecException;
}
