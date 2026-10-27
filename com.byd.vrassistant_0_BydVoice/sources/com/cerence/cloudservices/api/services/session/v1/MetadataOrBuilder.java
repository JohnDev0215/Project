package com.cerence.cloudservices.api.services.session.v1;

import com.google.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: classes2.dex */
public interface MetadataOrBuilder extends MessageLiteOrBuilder {
    DiagnosticInformation getDiagnosticInfo();

    VersionInformation getVersionInfo();

    boolean hasDiagnosticInfo();

    boolean hasVersionInfo();
}
