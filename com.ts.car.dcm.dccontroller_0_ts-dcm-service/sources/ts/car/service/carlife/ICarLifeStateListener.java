package ts.car.service.carlife;

/* JADX INFO: loaded from: classes.dex */
public interface ICarLifeStateListener {
    default void onSessionStateChanged(int status) {
    }

    default void onAppStateChanged(int appState, boolean enabled) {
    }
}
