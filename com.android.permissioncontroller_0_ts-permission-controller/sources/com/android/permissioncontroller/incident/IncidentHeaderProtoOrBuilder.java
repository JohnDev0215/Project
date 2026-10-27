package com.android.permissioncontroller.incident;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: classes8.dex */
public interface IncidentHeaderProtoOrBuilder extends MessageLiteOrBuilder {
    String getReason();

    ByteString getReasonBytes();

    boolean hasReason();
}
