package ts.car.service.carplay;

/* JADX INFO: loaded from: classes5.dex */
public interface ICarPlayServiceListener {
    default void onAppStateChanged(int i, int i2, int i3) {
    }

    @Deprecated
    default void onCarPlayMediaFocusStateChanged(boolean z) {
    }

    default void onRequestVideoFocusChanged(int i) {
    }

    default void onSupportFeaturesChanged(int i) {
    }
}
