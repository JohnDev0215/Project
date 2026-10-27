package ts.car.service.carplay;

/* JADX INFO: loaded from: classes2.dex */
public interface ICarPlayServiceListener {
    default void onAppStateChanged(int i8, int i9, int i10) {
    }

    @Deprecated
    default void onCarPlayMediaFocusStateChanged(boolean z7) {
    }

    default void onRequestVideoFocusChanged(int i8) {
    }

    default void onSupportFeaturesChanged(int i8) {
    }
}
