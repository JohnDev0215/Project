package com.amazon.CoralAndroidClient.Connector;

import com.amazon.CoralAndroidClient.ClientBase.ClientRequest;
import com.amazon.CoralAndroidClient.ClientBase.ClientResponse;

/* JADX INFO: loaded from: classes2.dex */
public interface TransmissionFilter {
    void afterSend(ClientRequest clientRequest, ClientResponse clientResponse);

    void beforeSend(ClientRequest clientRequest);
}
