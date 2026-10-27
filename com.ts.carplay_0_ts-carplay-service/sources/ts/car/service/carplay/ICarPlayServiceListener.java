package ts.car.service.carplay;

/* JADX INFO: loaded from: classes.dex */
public interface ICarPlayServiceListener {
    default void onRequestVideoFocusChanged(int requestType) {
    }

    @Deprecated
    default void onCarPlayMediaFocusStateChanged(boolean isCarPlayMedia) {
    }

    default void onSupportFeaturesChanged(int supportsFeatures) {
    }

    default void onAppStateChanged(int appState, int entity, int speechMode) {
    }
}
