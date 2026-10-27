package ts.car.service.androidauto;

/* JADX INFO: loaded from: classes.dex */
public interface IAndroidAutoServiceListener {
    @Deprecated
    default void onAppStatesChanged(int i, boolean z) {
    }

    default void onClusterVideoUpdateUiConfigReply(boolean z) {
    }

    default void onRequestClusterVideoFocus(int i) {
    }

    default void onRequestVideoFocus(int i) {
    }

    @Deprecated
    default void onVideoFocusStatusChanged(int i) {
    }
}
