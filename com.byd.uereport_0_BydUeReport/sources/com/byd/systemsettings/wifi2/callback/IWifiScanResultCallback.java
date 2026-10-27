package com.byd.systemsettings.wifi2.callback;

import android.net.wifi.ScanResult;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IWifiScanResultCallback extends WifiCallback {
    default boolean isBackground() {
        return false;
    }

    default void onScanFinished() {
    }

    void onScanResult(List<ScanResult> list);

    default void onScanTimeout() {
    }
}
