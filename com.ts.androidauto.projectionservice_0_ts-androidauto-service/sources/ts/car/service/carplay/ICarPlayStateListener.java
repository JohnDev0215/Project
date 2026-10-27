package ts.car.service.carplay;

/* JADX INFO: loaded from: classes2.dex */
public interface ICarPlayStateListener {
    default void onSessionStateChanged(int status) {
    }

    default void onSessionConnectStateChanged(int status, boolean isOldConnection, String clientAddr) {
    }

    default void onCarPlaySessionConnectStateChanged(int status, boolean isOldConnection, String clientAddr, boolean isUserConnect) {
    }

    default void onAppStateChanged(int appState, boolean enabled, int speechMode) {
    }

    default void onCarPlayMediaFocusStateChanged(boolean isCarPlayMedia) {
    }
}
