package com.google.firebase.perf.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: classes6.dex */
public interface AndroidApplicationInfoOrBuilder extends MessageLiteOrBuilder {
    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    /* synthetic */ MessageLite getDefaultInstanceForType();

    String getPackageName();

    ByteString getPackageNameBytes();

    String getSdkVersion();

    ByteString getSdkVersionBytes();

    String getVersionName();

    ByteString getVersionNameBytes();

    boolean hasPackageName();

    boolean hasSdkVersion();

    boolean hasVersionName();

    @Override // com.google.protobuf.MessageLiteOrBuilder
    /* synthetic */ boolean isInitialized();
}
