package com.amazon.client.metrics.thirdparty.transport;

/* JADX INFO: loaded from: classes2.dex */
public interface TransportStateNotifier {

    public interface TransportWarmedListener {
    }

    void listenForTransportWarmed(TransportWarmedListener transportWarmedListener);
}
