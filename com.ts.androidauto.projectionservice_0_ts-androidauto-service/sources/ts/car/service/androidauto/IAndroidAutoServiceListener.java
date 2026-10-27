package ts.car.service.androidauto;

/* JADX INFO: loaded from: classes2.dex */
public interface IAndroidAutoServiceListener {
    @Deprecated
    default void onVideoFocusStatusChanged(int requestType) {
    }

    @Deprecated
    default void onAppStatesChanged(int appState, boolean enabled) {
    }

    default void onRequestVideoFocus(int requestType) {
    }

    default void onRequestClusterVideoFocus(int requestType) {
    }

    default void onClusterVideoUpdateUiConfigReply(boolean replyOk) {
    }
}
