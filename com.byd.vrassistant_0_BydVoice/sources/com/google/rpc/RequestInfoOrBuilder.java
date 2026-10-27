package com.google.rpc;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* JADX INFO: loaded from: classes3.dex */
public interface RequestInfoOrBuilder extends MessageOrBuilder {
    String getRequestId();

    ByteString getRequestIdBytes();

    String getServingData();

    ByteString getServingDataBytes();
}
