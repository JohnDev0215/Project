package com.ts.androidauto.app.manager;

/* JADX INFO: loaded from: classes2.dex */
public interface IAndroidAutoRemoteUiManagerListener {
    default void onServiceStateChanged(boolean connected) {
    }

    default void onStatusChanged(int status) {
    }

    default void onNavigationFocusChanged(boolean useNativeNavigation) {
    }

    default void onVoiceSessionStatusChanged(boolean isVrFocus) {
    }

    default void onErrorCodeNotification(int errorCode, String message) {
    }

    default void onBatteryStatusNotification(int batteryLevel, int timeRemainingS, boolean batteryCritical) {
    }

    default void onLastAvailableDeviceChanged(String deviceId, int capability) {
    }

    default void onRequestClusterVideoFocus(int requestType) {
    }

    default void onClusterVideoUpdateUiConfigReply(boolean replyOk) {
    }

    default void onEcallStatusChanged(int status) {
    }
}
