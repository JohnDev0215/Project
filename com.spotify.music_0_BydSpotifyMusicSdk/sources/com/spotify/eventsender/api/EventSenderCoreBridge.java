package com.spotify.eventsender.api;

/* JADX INFO: loaded from: classes.dex */
public interface EventSenderCoreBridge {
    void queueMetricsDataSnapshotForSending(byte[] bArr, byte[] bArr2);

    int send(byte[] bArr, byte[] bArr2, byte[] bArr3);
}
