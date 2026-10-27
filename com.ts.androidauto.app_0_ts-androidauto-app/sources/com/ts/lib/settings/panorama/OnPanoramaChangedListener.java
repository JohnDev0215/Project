package com.ts.lib.settings.panorama;

/* JADX INFO: loaded from: classes2.dex */
public interface OnPanoramaChangedListener {
    void onACUStateChanged(int i);

    void onBackLineConfigChanged(int i);

    void onCameraStateChanged(int i, int i2);

    void onCameraUseChange(String str, boolean z);

    void onCarInfoChanged(int i);

    void onDisplayModeChanged(int i);

    void onEmergencyButtonStateChanged(int i);

    void onLVDSStateChanged(int i);

    void onPanOutputSignalChanged(int i);

    void onPanoAPAStateChanged(int i);

    void onPanoOutputStateChanged(int i);

    void onPanoRotationChanged(int i);

    void onPanoWorkStateChanged(int i);

    void onPanoramaOnlineStateChanged(int i);

    void onPanoramaTransparenceChanged(int i);

    void onRFCameraSwitchStateChanged(int i);
}
