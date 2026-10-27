package com.ts.car.dcm.dccontroller.connectionmanagerservice;

import com.ts.car.dcm.dccontroller.storemanager.Device;
import ts.car.dcm.common.data.AbnormalDevice;
import ts.car.dcm.common.data.ProjectionLinkParameter;
import ts.car.dcm.common.data.WifiConfigInfo;

/* JADX INFO: loaded from: classes.dex */
public interface ICmsCallback {
    boolean isAvailablePmService(int i);

    void notifyBtOobPairComplete(String str, int i);

    void onAbnormalDeviceStatusChanged(AbnormalDevice abnormalDevice, int i);

    void onBtConnected(String str, int i);

    void onBtDisconnected(String str, int i);

    void onBtProfileChanged(String str, int i, int i2);

    void onBtStateChanged(int i);

    boolean onCheckUsbDeviceName(Device device, int i);

    boolean onConnectProjection(Device device, ProjectionLinkParameter projectionLinkParameter);

    boolean onDeleteProjectionData(Device device, int i);

    void onDeviceAccept(Device device);

    void onDeviceAttach(Device device, int i);

    void onDeviceCapabilityChecked(String str, int i, boolean z, int i2);

    void onDeviceConnectionUpdated(Device device, int i, int i2, int i3);

    void onDeviceDetach(Device device, int i);

    void onDeviceStatusChanged(Device device);

    void onDevicesMerged(Device device);

    boolean onDisconnectIapSession(String str, int i);

    boolean onDisconnectProjection(Device device, int i, int i2);

    void onDisconnectedOtherBtDevices(String str, int i);

    void onEnableFunction(int i, int i2, boolean z);

    void onEnableUsbAoAFinished(Device device, int i, boolean z);

    void onEnableUsbAoAStarting(Device device, int i);

    boolean onIap2BtDeviceConnected(Device device, int i);

    void onProjectionConnectionChanged(Device device, int i, int i2, int i3);

    void onProjectionFunctionChanged(Device device, int i, int i2);

    void onProjectionServiceReady();

    void onProjectionServiceStatus(int i, boolean z);

    void onRequestProjection(Device device, int i);

    void onResetUsbBusStarting(Device device);

    void onResetUsbFinished(Device device, boolean z);

    void onRoleSwitchFinished(Device device, int i, int i2);

    void onRoleSwitchStarting(Device device, int i);

    void onShowProjection(int i, boolean z);

    boolean onSwitchAaConnection(Device device, Device device2);

    boolean onSwitchProjectionConnection(Device device, int i);

    void onWifiApInfo(WifiConfigInfo wifiConfigInfo, int i);

    void onWifiConnectStatus(Device device, int i);

    void onWirelessCarplayDetected(String str, int i);

    void onWirelessProjectionFound(Device device);
}
