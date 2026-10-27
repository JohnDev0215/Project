package com.engine.tbox.data;

import com.engine.tbox.item.MessageUtils;

/* JADX INFO: loaded from: classes.dex */
public abstract class OtaNotificationBase {
    protected byte[] mUuidArray;
    protected String mUuidString;

    public boolean setUuidByteArray(byte[] uuidArray) {
        if (uuidArray == null || uuidArray.length != 16) {
            return false;
        }
        this.mUuidString = MessageUtils.toHexString(uuidArray);
        this.mUuidArray = uuidArray;
        return true;
    }

    public String getUuidString() {
        return this.mUuidString;
    }

    public byte[] getUuidByteArray() {
        return this.mUuidArray;
    }
}
