package ts.car.service.carplay;

/* JADX INFO: loaded from: classes2.dex */
public interface ICarPlayStateListener {
    default void onAppStateChanged(int i8, boolean z7, int i9) {
    }

    default void onCarPlayMediaFocusStateChanged(boolean z7) {
    }

    default void onCarPlaySessionConnectStateChanged(int i8, boolean z7, String str, boolean z8) {
    }

    default void onSessionConnectStateChanged(int i8, boolean z7, String str) {
    }

    default void onSessionStateChanged(int i8) {
    }
}
