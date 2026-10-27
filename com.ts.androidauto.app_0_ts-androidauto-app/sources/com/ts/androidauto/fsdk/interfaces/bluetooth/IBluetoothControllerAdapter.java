package com.ts.androidauto.fsdk.interfaces.bluetooth;

import android.content.Context;
import com.ts.androidauto.fsdk.common.BluetoothDeviceInfo;

/* JADX INFO: loaded from: classes2.dex */
public interface IBluetoothControllerAdapter {
    void connectHfp(String str);

    void disconnectHfp(String str);

    void enableBluetooth();

    String getCarBluetoothAddress();

    BluetoothDeviceInfo getConnectedPhoneBluetoothDeviceInfo(String str);

    void init(Context context);

    boolean isA2dpConnected(String str);

    boolean isBluetoothEnable();

    boolean isHfpConnected(String str);

    boolean isOtherHfpDeviceInCall(String str);

    void registBluetoothListener(IBluetoothControllerListener iBluetoothControllerListener);

    void requestDisconnectA2dp(String str);

    void requestSetMdAuthState(boolean z, String str);

    void requestShowAuthFailedPop();

    void setBluetoothAutoConnectEnabled(String str, boolean z);
}
