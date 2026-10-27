package com.ts.carplay.linkadapter.link;

import com.ts.carplay.common.data.CarPlayDevice;
import com.ts.carplay.linkadapter.device.data.LinkDevice;

/* JADX INFO: loaded from: classes.dex */
public interface ILinkAdapterListener {
    default int connecting(LinkDevice device, boolean first, boolean fromUser) {
        return 0;
    }

    default int disconnecting(LinkDevice device, int reason) {
        return 0;
    }

    default void onUsbRoleSwitchFinished(LinkDevice device, boolean success) {
    }

    default void onSameDeviceConnect(LinkDevice device, int reason) {
    }

    default void onIap2BtDeviceConnected(LinkDevice device, int reason) {
    }

    default void notifyBtOobPairComplete(String devId, int status, int srvType) {
    }

    default void checkUsbDeviceName(LinkDevice device) {
    }

    default void disconnectIapSession(String devId) {
    }

    default void onProjectionConnectionStateChanged(String devId, int projectionType, int status, int reason) {
    }

    default void onWirelessCarPlayUpdate(String devId, boolean available) {
    }

    default void onServiceStateChanged(boolean connected) {
    }

    default void onRequestProjection(CarPlayDevice device, int type) {
    }

    default void onDeviceStatusChanged(CarPlayDevice device) {
    }

    default void oniPhoneAttachedOverUsb(CarPlayDevice device, boolean supported) {
    }

    default void oniPhoneDetachedOverUsb(CarPlayDevice device) {
    }
}
