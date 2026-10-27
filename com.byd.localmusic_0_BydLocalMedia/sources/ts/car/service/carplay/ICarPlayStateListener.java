package ts.car.service.carplay;

/* JADX INFO: loaded from: classes5.dex */
public interface ICarPlayStateListener {
    default void onAppStateChanged(int i, boolean z, int i2) {
    }

    default void onCarPlayMediaFocusStateChanged(boolean z) {
    }

    default void onSessionStateChanged(int i) {
    }
}
