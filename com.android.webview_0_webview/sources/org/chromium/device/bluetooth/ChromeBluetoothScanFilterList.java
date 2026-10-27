package org.chromium.device.bluetooth;

import android.bluetooth.le.ScanFilter;
import java.util.ArrayList;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class ChromeBluetoothScanFilterList {
    public ArrayList a = new ArrayList();

    private void addFilter(ScanFilter scanFilter) {
        this.a.add(scanFilter);
    }

    public static ChromeBluetoothScanFilterList create() {
        return new ChromeBluetoothScanFilterList();
    }

    public ArrayList getList() {
        return this.a;
    }
}
