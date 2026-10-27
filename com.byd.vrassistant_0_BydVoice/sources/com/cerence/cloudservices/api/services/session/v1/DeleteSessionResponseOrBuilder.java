package com.cerence.cloudservices.api.services.session.v1;

import com.cerence.cloudservices.api.services.session.v1.DeleteSessionResponse;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.rpc.Status;

/* JADX INFO: loaded from: classes2.dex */
public interface DeleteSessionResponseOrBuilder extends MessageLiteOrBuilder {
    DeleteSessionResponse.DeleteResultCase getDeleteResultCase();

    Metadata getMetadata();

    Status getStatus();

    boolean hasMetadata();

    boolean hasStatus();
}
