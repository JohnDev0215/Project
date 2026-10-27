package com.cerence.cloudservices.api.services.session.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: classes2.dex */
public interface DiagnosticInformationOrBuilder extends MessageLiteOrBuilder {
    String getFqdn();

    ByteString getFqdnBytes();

    String getSessionId();

    ByteString getSessionIdBytes();
}
