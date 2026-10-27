package com.ecarx.xui.adaptapi.bt.settings;

import com.ecarx.xui.adaptapi.bt.common.BtDevice;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IBtSettings {
    boolean cancelBtDiscovery();

    String getBtLocalAddress();

    String getBtLocalName();

    String getBtRemoteDeviceName(String str);

    int getBtRemoteUuids(String str);

    int getBtRoleMode();

    int getBtState();

    String getServiceVersionName();

    boolean isBluetoothServiceReady();

    boolean isBtAutoConnectEnable();

    boolean isBtDiscoverable();

    boolean isBtDiscovering();

    boolean isBtEnabled();

    boolean registerBtCallback(IBtSettingsCallback iBtSettingsCallback);

    int reqBtConnectHfpA2dp(String str);

    int reqBtDisconnectAll();

    List<BtDevice> reqBtFoundDevices();

    boolean reqBtPair(String str);

    @Deprecated
    List<BtDevice> reqBtPairedDevices();

    boolean reqBtPairedDevicesAsync();

    boolean reqBtUnpair(String str);

    void setBtAutoConnectMode(boolean z);

    boolean setBtDiscoverableTimeout(int i);

    boolean setBtEnable(boolean z);

    boolean setBtLocalName(String str);

    boolean startBtDiscovery();

    boolean switchBtRoleMode(int i);

    boolean unregisterBtCallback(IBtSettingsCallback iBtSettingsCallback);
}
