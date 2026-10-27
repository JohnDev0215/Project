package com.google.firebase.perf.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: classes6.dex */
public interface GaugeMetadataOrBuilder extends MessageLiteOrBuilder {
    int getCpuClockRateKhz();

    int getCpuProcessorCount();

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    /* synthetic */ MessageLite getDefaultInstanceForType();

    int getDeviceRamSizeKb();

    int getMaxAppJavaHeapMemoryKb();

    int getMaxEncouragedAppJavaHeapMemoryKb();

    @Deprecated
    String getProcessName();

    @Deprecated
    ByteString getProcessNameBytes();

    boolean hasCpuClockRateKhz();

    boolean hasCpuProcessorCount();

    boolean hasDeviceRamSizeKb();

    boolean hasMaxAppJavaHeapMemoryKb();

    boolean hasMaxEncouragedAppJavaHeapMemoryKb();

    @Deprecated
    boolean hasProcessName();

    @Override // com.google.protobuf.MessageLiteOrBuilder
    /* synthetic */ boolean isInitialized();
}
