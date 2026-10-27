package com.ts.ota.otasdkmgr;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public interface ITestTboxCallBack {
    void onCloudServiceConnectStateChanged(int i);

    void onNetworkOperatorList(ArrayList<String> arrayList);

    void onTboxRestartChanged(int i);

    default void onNetworkOperatorModeChange(int state) {
    }

    default void onOperatorInfoChange(ArrayList<NetworkOperatorAidl> list) {
    }

    default void onManuallyStationedNetworkChange(String data, int curNetType, int state, String lastData, int lastNetType) {
    }

    default void onAutomaticNetworkChange(int state) {
    }

    default void onSimCardStateChange(int status) {
    }
}
