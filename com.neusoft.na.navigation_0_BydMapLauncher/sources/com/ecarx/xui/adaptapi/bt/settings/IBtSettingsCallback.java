package com.ecarx.xui.adaptapi.bt.settings;

import com.ecarx.xui.adaptapi.bt.common.BtDevice;
import com.ecarx.xui.adaptapi.bt.common.IPairResponse;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IBtSettingsCallback {
    void onAdapterDiscoverableModeChanged(int i, int i2);

    void onAdapterStateChanged(int i, int i2);

    void onBluetoothServiceReady();

    void onBtRoleModeChanged(int i);

    void onDeviceBondStateChanged(BtDevice btDevice, int i, int i2);

    void onDeviceFoundChanged(int i, BtDevice btDevice);

    void onDeviceOutOfRange(String str);

    void onDeviceUuidsUpdated(BtDevice btDevice);

    void onLocalAdapterNameChanged(String str);

    void onPairedDevicesChanged(List<BtDevice> list);

    void onPairingRequest(String str, IPairResponse iPairResponse);
}
