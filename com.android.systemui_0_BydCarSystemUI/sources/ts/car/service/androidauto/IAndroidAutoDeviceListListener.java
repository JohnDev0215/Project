package ts.car.service.androidauto;

import ts.car.service.androidauto.data.AndroidAutoDevice;

/* JADX INFO: loaded from: classes2.dex */
public interface IAndroidAutoDeviceListListener {
    default void onAndroidAutoDeviceStatusChanged(AndroidAutoDevice androidAutoDevice) {
    }

    default void onAndroidDetachedOverUsb(AndroidAutoDevice androidAutoDevice) {
    }

    default void onDeviceMerged(AndroidAutoDevice androidAutoDevice) {
    }

    default void onProjectionRequested(AndroidAutoDevice androidAutoDevice, int i) {
    }
}
