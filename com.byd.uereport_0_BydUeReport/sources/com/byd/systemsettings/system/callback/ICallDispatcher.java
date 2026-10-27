package com.byd.systemsettings.system.callback;

import com.byd.systemsettings.ISystemSettingsCallback;

/* JADX INFO: loaded from: classes2.dex */
public interface ICallDispatcher {
    default void dismissAllDialog() {
    }

    default void dismissBtSettings() {
    }

    default void dismissCpaaSettings() {
    }

    default void dismissHotspotSettings() {
    }

    default void dismissWifiSettings() {
    }

    default void doAction(String str, String str2) {
    }

    default void finishScreensaverOrScreenOff(boolean z7) {
    }

    default boolean hasDialogShowing() {
        return false;
    }

    void registerSystemSettingsCallback(ISystemSettingsCallback iSystemSettingsCallback);

    default void showBtSettings() {
    }

    default void showCpaaSettings() {
    }

    default void showHotspotSettings() {
    }

    default void showScreensaverOrScreenOff(boolean z7) {
    }

    default void showWifiSettings() {
    }

    void unregisterSystemSettingsCallback(ISystemSettingsCallback iSystemSettingsCallback);
}
