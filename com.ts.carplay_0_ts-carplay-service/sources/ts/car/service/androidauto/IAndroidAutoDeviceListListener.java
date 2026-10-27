package ts.car.service.androidauto;

import ts.car.service.androidauto.data.AndroidAutoDevice;

/* JADX INFO: loaded from: classes.dex */
public interface IAndroidAutoDeviceListListener {
    default void onDeviceMerged(AndroidAutoDevice device) {
    }

    default void onAndroidAutoDeviceStatusChanged(AndroidAutoDevice device) {
    }

    default void onProjectionRequested(AndroidAutoDevice device, int type) {
    }

    default void onAndroidDetachedOverUsb(AndroidAutoDevice device) {
    }
}
