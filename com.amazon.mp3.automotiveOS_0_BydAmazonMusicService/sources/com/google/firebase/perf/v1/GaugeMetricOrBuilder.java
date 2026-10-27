package com.google.firebase.perf.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public interface GaugeMetricOrBuilder extends MessageLiteOrBuilder {
    AndroidMemoryReading getAndroidMemoryReadings(int i);

    int getAndroidMemoryReadingsCount();

    List<AndroidMemoryReading> getAndroidMemoryReadingsList();

    CpuMetricReading getCpuMetricReadings(int i);

    int getCpuMetricReadingsCount();

    List<CpuMetricReading> getCpuMetricReadingsList();

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    /* synthetic */ MessageLite getDefaultInstanceForType();

    GaugeMetadata getGaugeMetadata();

    String getSessionId();

    ByteString getSessionIdBytes();

    boolean hasGaugeMetadata();

    boolean hasSessionId();

    @Override // com.google.protobuf.MessageLiteOrBuilder
    /* synthetic */ boolean isInitialized();
}
