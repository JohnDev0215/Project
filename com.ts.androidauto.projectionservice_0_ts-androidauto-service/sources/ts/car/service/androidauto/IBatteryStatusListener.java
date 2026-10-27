package ts.car.service.androidauto;

/* JADX INFO: loaded from: classes2.dex */
public interface IBatteryStatusListener {
    default void onBatteryStatusChanged(int batteryLevel, int timeRemainingS, boolean batteryCritical) {
    }
}
