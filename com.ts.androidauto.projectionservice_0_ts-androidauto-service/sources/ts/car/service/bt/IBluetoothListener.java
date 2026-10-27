package ts.car.service.bt;

import ts.car.service.bt.data.DeviceItem;

/* JADX INFO: loaded from: classes2.dex */
public interface IBluetoothListener {
    @Deprecated
    default void onAutoConnectStateChanged(boolean ongoing) {
    }

    default void onBondRequested(String address, String name, int pincode, int bondType) {
    }

    default void onBondStateChanged(DeviceItem device, int newState, int oldState, int reason) {
    }

    default void onBtStateChanged(int state) {
    }

    default void onConnectionStateChanged(String address, int profile, int newState, int oldState) {
    }

    default void onDeviceFound(DeviceItem device) {
    }

    default void onDiscoveryStateChanged(boolean state) {
    }

    default void onNameChanged(String name) {
    }

    default void onOobCompleted(String address, boolean result) {
    }

    default void onRemoteUuidsUpdated(String address, int services) {
    }
}
