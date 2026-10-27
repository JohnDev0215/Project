package com.ts.androidauto.aap.common;

/* JADX INFO: loaded from: classes.dex */
public class StatusConst {

    public enum AoaStatus {
        AOA_STATUS_INIT,
        AOA_STATUS_SWITCHING,
        AOA_STATUS_SEND_REQUEST_SUCCESS,
        AOA_STATUS_SUCCESS,
        AOA_STATUS_FAIL,
        AOA_STATUS_TIMEOUT,
        AOA_STATUS_NOT_SUPPORT,
        AOA_STATUS_ALREADY_AOA,
        AOA_STATUS_OTHER_ERROR
    }

    public enum ConnectErrorCode {
        INVALID_CODE,
        AOA_TIMEOUT_WITH_USB,
        AOA_TIMEOUT_WITHOUT_USB,
        WIRE_CONNECT_TIMEOUT,
        USB_DETACH,
        RELEASE_APP,
        SYSTEM_STATE_CHANGE_SLEEP,
        CMS_CALL_DISCONNECT
    }
}
