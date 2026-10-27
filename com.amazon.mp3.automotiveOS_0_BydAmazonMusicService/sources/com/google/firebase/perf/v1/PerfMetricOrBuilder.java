package com.google.firebase.perf.v1;

import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: classes6.dex */
public interface PerfMetricOrBuilder extends MessageLiteOrBuilder {
    ApplicationInfo getApplicationInfo();

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    /* synthetic */ MessageLite getDefaultInstanceForType();

    GaugeMetric getGaugeMetric();

    NetworkRequestMetric getNetworkRequestMetric();

    TraceMetric getTraceMetric();

    TransportInfo getTransportInfo();

    boolean hasApplicationInfo();

    boolean hasGaugeMetric();

    boolean hasNetworkRequestMetric();

    boolean hasTraceMetric();

    boolean hasTransportInfo();

    @Override // com.google.protobuf.MessageLiteOrBuilder
    /* synthetic */ boolean isInitialized();
}
