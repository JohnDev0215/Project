package com.cerence.cloudservices.api.services.session.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: classes2.dex */
public interface VersionInformationOrBuilder extends MessageLiteOrBuilder {
    String getCerenceCloudVersion();

    ByteString getCerenceCloudVersionBytes();
}
