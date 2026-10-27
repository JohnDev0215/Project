package com.google.firebase.perf.v1;

import com.google.firebase.perf.v1.TransportInfo;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: classes6.dex */
public interface TransportInfoOrBuilder extends MessageLiteOrBuilder {
    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    /* synthetic */ MessageLite getDefaultInstanceForType();

    TransportInfo.DispatchDestination getDispatchDestination();

    boolean hasDispatchDestination();

    @Override // com.google.protobuf.MessageLiteOrBuilder
    /* synthetic */ boolean isInitialized();
}
