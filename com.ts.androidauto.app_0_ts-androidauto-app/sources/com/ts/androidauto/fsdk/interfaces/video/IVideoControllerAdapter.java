package com.ts.androidauto.fsdk.interfaces.video;

import com.ts.androidauto.fsdk.common.ResourceConst;
import com.ts.androidauto.fsdk.data.AndroidAutoDevice;

/* JADX INFO: loaded from: classes2.dex */
public interface IVideoControllerAdapter {
    void appStateChangedTo(ResourceConst.AppType appType, ResourceConst.AppState appState);

    void deinit();

    ResourceConst.LastVideoMode getLastVideoMode();

    boolean getShowingFlag();

    void init();

    boolean isInPowerOff();

    boolean isInScreenOff();

    void registVideoListener(IVideoControllerListener iVideoControllerListener);

    ResourceConst.VideoFocusReqResult requestClusterVideoFocus(ResourceConst.VideoFocusRequest videoFocusRequest);

    ResourceConst.VideoFocusReqResult requestVideoFocus(ResourceConst.VideoFocusRequest videoFocusRequest);

    void setDisplayActivityInfo(String str, String str2);

    void setLastVideoMode();

    void setLinkDevice(AndroidAutoDevice androidAutoDevice);

    void setScreenOn(boolean z);

    void setShowingFlag(boolean z);

    void showProjectionScreen(boolean z, int i, int i2);

    default void setCameraRearState(boolean cameraRearState) {
    }
}
