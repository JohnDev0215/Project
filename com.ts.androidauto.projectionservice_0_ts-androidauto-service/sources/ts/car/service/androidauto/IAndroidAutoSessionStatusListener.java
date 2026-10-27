package ts.car.service.androidauto;

/* JADX INFO: loaded from: classes2.dex */
public interface IAndroidAutoSessionStatusListener {
    @Deprecated
    default void onSessionStatusChanged(int linkStatus, int errorCode) {
    }

    default void onSessionStatusChanged(int linkStatus, int errorCode, String message) {
    }

    default void onProjectionAppStatesChanged(int appType, boolean enabled) {
    }

    default void onSessionConnectStatusChanged(int linkStatus, int errorCode, String message, int projectionConnectType) {
    }
}
