package org.chromium.device.bluetooth;

import android.bluetooth.le.ScanFilter;
import android.os.ParcelUuid;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public final class ChromeBluetoothScanFilterBuilder {
    public final ScanFilter.Builder a = new ScanFilter.Builder();

    @CalledByNative
    public static ChromeBluetoothScanFilterBuilder create() {
        return new ChromeBluetoothScanFilterBuilder();
    }

    @CalledByNative
    public ScanFilter build() {
        return this.a.build();
    }

    @CalledByNative
    public final void setDeviceName(String str) {
        if (str != null) {
            this.a.setDeviceName(str);
        }
    }

    @CalledByNative
    public final void setServiceUuid(String str) {
        if (str != null) {
            this.a.setServiceUuid(ParcelUuid.fromString(str));
        }
    }
}
