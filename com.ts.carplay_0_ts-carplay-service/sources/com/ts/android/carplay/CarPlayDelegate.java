package com.ts.android.carplay;

import com.ts.android.carplay.CarPlay;
import com.ts.android.carplay.CarPlayInputManager;

/* JADX INFO: loaded from: classes.dex */
public interface CarPlayDelegate {
    void onAppStateChanged(Object obj, int i, int i2, int i3, int i4);

    void onAuxInUpdateState(Object obj, int i);

    void onClientMacAddrRecv(Object obj, byte[] bArr);

    void onClientNotification(Object obj, int i, CarPlayClient carPlayClient, CarPlayInputManager carPlayInputManager, CarPlayResourceManager carPlayResourceManager);

    void onDisableBluetooth(Object obj, byte[] bArr);

    void onDuckAudio(Object obj, int i, int i2);

    void onEnhancedSiriParamsUpdate(Object obj, int i, String str);

    void onIAP2MessageReceived(Object obj, byte[] bArr);

    CarPlay.InfoMsg onInfoRequest(Object obj, InfoMsgId infoMsgId);

    void onLauchNativeUI(Object obj, String str);

    void onNotification(Object obj, int i, int i2, int i3, int i4);

    void onRequestViewArea(Object obj, int i);

    void onResourceChange(Object obj, int i, int i2, int i3, int i4);

    int onResourceRequest(Object obj, int i, int i2, int i3, int i4);

    void onScreenProcessErr(Object obj);

    void onSetInputMode(Object obj, int i, CarPlayInputManager.InputDevice inputDevice);

    void onSuggestUi(Object obj, int i);

    void onSupportFeaturesUpdate(Object obj, int i);

    void onUiContextChanged(Object obj, int i);

    void onUnDuckAudio(Object obj, int i);
}
