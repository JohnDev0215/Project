package org.chromium.device.bluetooth;

import android.bluetooth.le.ScanFilter;
import android.os.ParcelUuid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class ChromeBluetoothScanFilterBuilder {
    public ScanFilter.Builder a = new ScanFilter.Builder();

    public static ChromeBluetoothScanFilterBuilder create() {
        return new ChromeBluetoothScanFilterBuilder();
    }

    private void setDeviceName(String str) {
        if (str != null) {
            this.a.setDeviceName(str);
        }
    }

    private void setServiceUuid(String str) {
        if (str != null) {
            this.a.setServiceUuid(ParcelUuid.fromString(str));
        }
    }

    public ScanFilter build() {
        return this.a.build();
    }
}
