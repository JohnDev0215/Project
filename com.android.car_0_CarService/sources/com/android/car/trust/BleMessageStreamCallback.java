package com.android.car.trust;

import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
interface BleMessageStreamCallback {
    void onMessageReceived(byte[] bArr, UUID uuid);

    void onMessageReceivedError(UUID uuid);

    void onWriteMessageError();
}
