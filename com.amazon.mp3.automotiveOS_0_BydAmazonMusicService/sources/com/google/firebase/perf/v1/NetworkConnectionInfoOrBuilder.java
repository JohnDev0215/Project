package com.google.firebase.perf.v1;

import com.google.firebase.perf.v1.NetworkConnectionInfo;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: classes6.dex */
public interface NetworkConnectionInfoOrBuilder extends MessageLiteOrBuilder {
    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    /* synthetic */ MessageLite getDefaultInstanceForType();

    NetworkConnectionInfo.MobileSubtype getMobileSubtype();

    NetworkConnectionInfo.NetworkType getNetworkType();

    boolean hasMobileSubtype();

    boolean hasNetworkType();

    @Override // com.google.protobuf.MessageLiteOrBuilder
    /* synthetic */ boolean isInitialized();
}
