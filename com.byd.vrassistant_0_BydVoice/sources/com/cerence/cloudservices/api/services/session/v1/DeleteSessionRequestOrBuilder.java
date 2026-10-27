package com.cerence.cloudservices.api.services.session.v1;

import com.cerence.cloudservices.api.services.session.v1.DeleteSessionRequest;
import com.google.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: classes2.dex */
public interface DeleteSessionRequestOrBuilder extends MessageLiteOrBuilder {
    DeleteAll getAll();

    DeleteBySessionId getBySessionId();

    DeleteSessionRequest.PayloadCase getPayloadCase();

    boolean hasAll();

    boolean hasBySessionId();
}
