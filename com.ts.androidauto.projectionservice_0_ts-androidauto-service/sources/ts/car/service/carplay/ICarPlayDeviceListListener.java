package ts.car.service.carplay;

import ts.car.service.carplay.data.CarPlayDevice;

/* JADX INFO: loaded from: classes2.dex */
public interface ICarPlayDeviceListListener {
    default void onDeviceMerged(CarPlayDevice device) {
    }

    default void onCarPlayDeviceStatusChanged(CarPlayDevice device) {
    }

    default void onCarPlayOobStartEvent(byte[] macAddress) {
    }

    default void onProjectionRequested(CarPlayDevice device, int type) {
    }

    default void oniPhoneAttachedOverUsb(CarPlayDevice device, boolean supported) {
    }

    default void oniPhoneDetachedOverUsb(CarPlayDevice device) {
    }
}
