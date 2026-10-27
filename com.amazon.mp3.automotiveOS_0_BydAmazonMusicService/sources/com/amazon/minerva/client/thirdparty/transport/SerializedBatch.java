package com.amazon.minerva.client.thirdparty.transport;

import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;

/* JADX INFO: loaded from: classes3.dex */
public class SerializedBatch {
    private byte[] batchContent;
    private String fileName;

    public SerializedBatch(byte[] bArr, String str) {
        this.batchContent = bArr;
        this.fileName = str;
    }

    public byte[] getBatchContent() {
        return this.batchContent;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getRegion() {
        return this.fileName.split(Attributes.PREDEFINED_ATTRIBUTE_PREFIX, 3)[1];
    }
}
