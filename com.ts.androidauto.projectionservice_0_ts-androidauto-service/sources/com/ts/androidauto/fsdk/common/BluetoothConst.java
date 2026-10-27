package com.ts.androidauto.fsdk.common;

/* JADX INFO: loaded from: classes2.dex */
public class BluetoothConst {
    public static String BT_HFP_PROFILE_STR = "hfp";
    public static String BT_A2DP_PROFILE_STR = "a2dp";

    public enum BtPhoneStatus {
        PHONE_STATUS_ACTIVE,
        PHONE_STATUS_IDLE
    }

    public enum OtherPhoneStatus {
        OTHER_PHONE_STATUS_ACTIVE,
        OTHER_PHONE_STATUS_IDLE
    }

    public enum ProfileStatus {
        PROFILE_STATUS_CONNECTED,
        PROFILE_STATUS_DISCONNECTED
    }
}
