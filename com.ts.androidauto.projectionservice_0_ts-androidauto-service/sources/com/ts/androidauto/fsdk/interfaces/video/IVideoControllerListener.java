package com.ts.androidauto.fsdk.interfaces.video;

import com.ts.androidauto.fsdk.common.ResourceConst;

/* JADX INFO: loaded from: classes2.dex */
public interface IVideoControllerListener {
    void appStateChangedTo(ResourceConst.AppType appType, ResourceConst.AppState appState);

    void onPhoneCallStateChanged(int i);

    void onRequestClusterVideoFocus(int i);

    void onRequestVideoFocus(int i);

    default void onPowerStatusChange(boolean powerOff) {
    }

    default void onPowerStrChanged(boolean strOff) {
    }
}
