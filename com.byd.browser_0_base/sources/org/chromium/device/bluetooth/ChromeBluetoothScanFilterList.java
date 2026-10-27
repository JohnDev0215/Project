package org.chromium.device.bluetooth;

import android.bluetooth.le.ScanFilter;
import java.util.ArrayList;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public final class ChromeBluetoothScanFilterList {
    public final ArrayList<ScanFilter> a = new ArrayList<>();

    @CalledByNative
    public static ChromeBluetoothScanFilterList create() {
        return new ChromeBluetoothScanFilterList();
    }

    @CalledByNative
    public final void addFilter(ScanFilter scanFilter) {
        this.a.add(scanFilter);
    }

    @CalledByNative
    public ArrayList<ScanFilter> getList() {
        return this.a;
    }
}
