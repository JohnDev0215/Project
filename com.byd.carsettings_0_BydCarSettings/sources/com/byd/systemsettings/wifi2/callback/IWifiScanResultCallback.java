package com.byd.systemsettings.wifi2.callback;

import android.net.wifi.ScanResult;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IWifiScanResultCallback extends WifiCallback {
    void onScanFinished();

    void onScanResult(List<ScanResult> list);

    void onScanTimeout();
}
