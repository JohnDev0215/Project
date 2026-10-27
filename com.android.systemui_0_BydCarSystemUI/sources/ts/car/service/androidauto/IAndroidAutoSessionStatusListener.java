package ts.car.service.androidauto;

/* JADX INFO: loaded from: classes2.dex */
public interface IAndroidAutoSessionStatusListener {
    default void onProjectionAppStatesChanged(int i, boolean z) {
    }

    @Deprecated
    default void onSessionStatusChanged(int i, int i2) {
    }

    default void onSessionStatusChanged(int i, int i2, String str) {
    }
}
