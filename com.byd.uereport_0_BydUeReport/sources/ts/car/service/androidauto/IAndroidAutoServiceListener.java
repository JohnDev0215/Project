package ts.car.service.androidauto;

/* JADX INFO: loaded from: classes2.dex */
public interface IAndroidAutoServiceListener {
    @Deprecated
    default void onAppStatesChanged(int i8, boolean z7) {
    }

    default void onClusterVideoUpdateUiConfigReply(boolean z7) {
    }

    default void onRequestClusterVideoFocus(int i8) {
    }

    default void onRequestVideoFocus(int i8) {
    }

    @Deprecated
    default void onVideoFocusStatusChanged(int i8) {
    }
}
