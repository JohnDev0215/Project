package com.ts.carplay.linkadapter.link;

import android.content.Context;
import com.ts.carplay.common.data.CarPlayDevice;
import com.ts.carplay.linkadapter.device.data.LinkDevice;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface ILinkAdapter {
    boolean confirmWirelessCarPlayAvailable(String str);

    void connectCarPlay(int i, String str, boolean z);

    void deleteCarPlayDevice(String str);

    void disconnectCarPlay(String str);

    boolean doUsbRoleSwitch(LinkDevice linkDevice, int i);

    CarPlayDevice getActivedWirelessDevice();

    CarPlayDevice getCarPlayAidlDevice(String str);

    LinkDevice getCarPlayDevice(String str);

    List<CarPlayDevice> getCarPlayDeviceList();

    String getConnectionDeviceId(String str);

    int getDeviceConnectionType(String str);

    List<CarPlayDevice> getUsbCarPlayDeviceList();

    void init(Context context);

    boolean isUserConnected();

    void mergeDevices(String str, int i, String str2);

    boolean onDeviceFriendlyNameUpdate(String str, String str2);

    void onKnownWirelessAttributesChanged(String str, int i);

    void onStatusChanged(LinkDevice linkDevice, int i);

    void onUpdateIapSessionState(String str, int i, int i2);

    boolean onUsbDeviceNameUpdate(String str, String str2);

    boolean reconnectWirelessCarPlay(String str);

    void registerListener(ILinkAdapterListener iLinkAdapterListener);

    void uninit();

    void unregisterListener(ILinkAdapterListener iLinkAdapterListener);

    void wirelessCapabilityUpdate(String str, boolean z, int i);

    default void saveDevice(LinkDevice device) {
    }
}
