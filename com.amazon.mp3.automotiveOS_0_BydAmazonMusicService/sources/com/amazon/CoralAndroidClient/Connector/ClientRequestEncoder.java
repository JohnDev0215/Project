package com.amazon.CoralAndroidClient.Connector;

import com.amazon.CoralAndroidClient.ClientBase.ClientRequest;
import com.amazon.CoralAndroidClient.Exception.NativeException;

/* JADX INFO: loaded from: classes2.dex */
public interface ClientRequestEncoder {
    byte[] encode(ClientRequest clientRequest) throws NativeException;
}
