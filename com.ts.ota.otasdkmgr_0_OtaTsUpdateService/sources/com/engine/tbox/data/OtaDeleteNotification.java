package com.engine.tbox.data;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class OtaDeleteNotification extends OtaNotificationBase {
    public static final int DELETE_ALL_PACKAGE = 3;
    public static final int DELETE_MEDIA_PACKAGE = 1;
    public static final int DELETE_WHOLE_CAR_PACKAGE = 2;
    private int delete;

    public static boolean isValidDeleteOperation(int delete) {
        return delete >= 1 && delete <= 3;
    }

    @Override // com.engine.tbox.data.OtaNotificationBase
    public byte[] getUuidByteArray() {
        return this.mUuidArray;
    }

    public int getDelete() {
        return this.delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }

    public String toString() {
        return "OtaDeleteNotification [mUuidString=" + this.mUuidString + ", delete=" + this.delete + ", mUuidArray=" + Arrays.toString(this.mUuidArray) + "]";
    }
}
