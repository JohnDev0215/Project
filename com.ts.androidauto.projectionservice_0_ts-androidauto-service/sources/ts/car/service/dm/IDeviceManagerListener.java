package ts.car.service.dm;

import ts.car.service.dm.data.ConnectRequest;
import ts.car.service.dm.data.UnifiedDevice;

/* JADX INFO: loaded from: classes2.dex */
public interface IDeviceManagerListener {
    default void onDeviceAttached(UnifiedDevice device) {
    }

    default void onDeviceChanged(UnifiedDevice device) {
    }

    default void onDeviceDetached(UnifiedDevice device) {
    }

    default void onDeviceProjectionRequested(boolean supported, ConnectRequest connectRequest) {
    }

    default void onDeviceDetachedOverUsb(UnifiedDevice device) {
    }
}
