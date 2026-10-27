package com.google.firebase.perf.v1;

import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: classes6.dex */
public interface AndroidMemoryReadingOrBuilder extends MessageLiteOrBuilder {
    long getClientTimeUs();

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    /* synthetic */ MessageLite getDefaultInstanceForType();

    int getUsedAppJavaHeapMemoryKb();

    boolean hasClientTimeUs();

    boolean hasUsedAppJavaHeapMemoryKb();

    @Override // com.google.protobuf.MessageLiteOrBuilder
    /* synthetic */ boolean isInitialized();
}
