package com.android.car.trust;

import com.android.car.BLEStreamProtos.BLEOperationProto;

/* JADX INFO: loaded from: classes3.dex */
interface BleMessageStream {
    int getMaxWriteSize();

    void registerCallback(BleMessageStreamCallback bleMessageStreamCallback);

    void setMaxWriteSize(int i);

    void unregisterCallback(BleMessageStreamCallback bleMessageStreamCallback);

    void writeMessage(byte[] bArr, BLEOperationProto.OperationType operationType, boolean z);
}
