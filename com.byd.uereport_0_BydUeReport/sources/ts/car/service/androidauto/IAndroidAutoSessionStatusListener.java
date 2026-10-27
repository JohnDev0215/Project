package ts.car.service.androidauto;

/* JADX INFO: loaded from: classes2.dex */
public interface IAndroidAutoSessionStatusListener {
    default void onProjectionAppStatesChanged(int i8, boolean z7) {
    }

    default void onSessionConnectStatusChanged(int i8, int i9, String str, int i10) {
    }

    @Deprecated
    default void onSessionStatusChanged(int i8, int i9) {
    }

    default void onSessionStatusChanged(int i8, int i9, String str) {
    }
}
