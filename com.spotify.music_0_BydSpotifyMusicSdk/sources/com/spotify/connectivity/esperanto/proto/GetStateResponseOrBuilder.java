package com.spotify.connectivity.esperanto.proto;

import com.google.protobuf.ByteString;
import com.spotify.connectivity.esperanto.proto.GetStateResponse;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public interface GetStateResponseOrBuilder extends yj1 {
    @Override // p.yj1
    /* synthetic */ wj1 getDefaultInstanceForType();

    String getErrorMessage();

    ByteString getErrorMessageBytes();

    String getStatus();

    ByteString getStatusBytes();

    GetStateResponse.StatusCode getStatusCode();

    int getStatusCodeValue();

    boolean hasErrorMessage();

    @Override // p.yj1
    /* synthetic */ boolean isInitialized();
}
