package com.byd.dilink.media;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkAudioStateCallback {
    void onAnyDeviceAudioFocusPackageChanged(int i, String str);

    void onAnyDeviceMusicPlayStateChanged(int i, boolean z);

    void onAnyDeviceUseSpeakerStateChanged(int i, int i2);

    void onIVISwitchStateChanged(boolean z);

    void onPhoneStateChanged(int i);

    void onProjectVideoModeChanged(boolean z, int i);

    void onUseVehicleSpeakerClientChanged(int i);
}
