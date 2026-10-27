package ts.car.service.androidauto;

/* JADX INFO: loaded from: classes.dex */
public interface IAndroidAutoAppListener {
    default void onLastAvailableDeviceChanged(String deviceId, int capability) {
    }

    default void onVideoFocusStatusChanged(int videoFocusStatus) {
    }

    default void onClusterVideoFocusStatusChanged(int videoFocusStatus) {
    }

    default void onProjectionBluetoothAddressChanged(String address) {
    }
}
