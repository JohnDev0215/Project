package ts.car.service.carplay;

import ts.car.service.carplay.data.CarPlayDevice;

/* JADX INFO: loaded from: classes2.dex */
public interface ICarPlayDeviceListListener {
    default void onCarPlayDeviceStatusChanged(CarPlayDevice carPlayDevice) {
    }

    default void onCarPlayOobStartEvent(byte[] bArr) {
    }

    default void onDeviceMerged(CarPlayDevice carPlayDevice) {
    }

    default void onProjectionRequested(CarPlayDevice carPlayDevice, int i8) {
    }

    default void oniPhoneAttachedOverUsb(CarPlayDevice carPlayDevice, boolean z7) {
    }

    default void oniPhoneDetachedOverUsb(CarPlayDevice carPlayDevice) {
    }
}
