package com.ts.carplay.lib.interfaces;

import com.ts.carplay.linkadapter.device.data.LinkDevice;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public interface ReconnectionDependence {
    boolean closeRfCommConnection(String str);

    boolean confirmWirelessCarPlayAvailable(String str);

    boolean connectBtClassicProfile(String str);

    boolean connectRfcommSocket(String str);

    boolean enableBluetooth();

    Set<String> getBondedDevices();

    LinkDevice getCarPlayDevice(String str);

    boolean isBluetoothEnabled();

    boolean isConnected(String str);

    boolean isWifiActived();

    boolean isWifiApEnabled();

    boolean reconnectWirelessCarPlay(String str);

    boolean setBluetoothAutoConnectEnabled(boolean z, String str, boolean z2);

    boolean startSoftAp();
}
