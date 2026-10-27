package ts.car.service.carplay;

import ts.car.service.carplay.data.CarPlayDevice;

/* JADX INFO: loaded from: classes5.dex */
public interface ICarPlayDeviceListListener {
    default void onCarPlayDeviceStatusChanged(CarPlayDevice carPlayDevice) {
    }

    default void onDeviceMerged(CarPlayDevice carPlayDevice) {
    }

    default void onProjectionRequested(CarPlayDevice carPlayDevice, int i) {
    }

    default void oniPhoneAttachedOverUsb(CarPlayDevice carPlayDevice, boolean z) {
    }

    default void oniPhoneDetachedOverUsb(CarPlayDevice carPlayDevice) {
    }
}
