package com.ts.ota.otasdkmgr;

/* JADX INFO: loaded from: classes.dex */
public interface ITboxCallBack {
    void onUpdateTrafficData();

    default void onTboxCmd2Ivi(int cmd) {
    }

    default void onSyncApnParamResult(int resultCode) {
    }

    default void onGetApnParamFromTbox(String apn, String username, String pwd, String apnType, String apnProtocol, String proxy, String port) {
    }

    default void onGetTrafficFromTbox(int apnType, String iccid, String time, long dayUpTraffic, long dayDownTraffic, long monthUpTraffic, long monthDownTraffic) {
    }

    default void onGetTboxNetworkState(int apn, int state) {
    }
}
