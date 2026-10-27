package com.cerence.cloudservices.api.services.session.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: classes2.dex */
public interface DeleteBySessionIdOrBuilder extends MessageLiteOrBuilder {
    String getDeviceId();

    ByteString getDeviceIdBytes();

    String getSessionIdToDelete();

    ByteString getSessionIdToDeleteBytes();

    String getUserId();

    ByteString getUserIdBytes();
}
