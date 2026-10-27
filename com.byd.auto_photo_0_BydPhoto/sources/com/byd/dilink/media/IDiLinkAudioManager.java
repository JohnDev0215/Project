package com.byd.dilink.media;

import android.content.Context;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkAudioManager {
    int changeAudioSlotStateOnAccOffState(int i, boolean z);

    @NonNull
    String getCurrentAudioFocusPackage();

    String getCurrentAudioFocusPackage(int i);

    @NonNull
    int getCurrentAudioFocusStream();

    @NonNull
    int getCurrentAudioFocusStream(int i);

    int getCurrentAudioFocusStream(@NonNull String str);

    int getHeadUnitOutputDevice();

    boolean getMuteState(int i);

    boolean getNaviMuteState();

    int getPlayMode();

    int getPlayProgress();

    int getPlayState();

    int getProjectVideoModeHost(Context context);

    int getSecondaryAudioFocusStream();

    int getStreamVolume(int i, int i2);

    String getTargetDeviceAudioFocusPackage(Context context, int i);

    boolean getTargetDeviceMusicPlayState(Context context, int i);

    int getTargetDeviceUseSpeakerState(Context context, int i);

    int getVehiclePhoneState(Context context);

    boolean getVehicleSpeakerPermitState(Context context);

    boolean isCurrentSystemUseVehicleSpeaker(Context context);

    boolean isProjectVideoMode(Context context);

    boolean isStreamAllowed(int i, @NonNull String str);

    void registerDiLinkAudioStateCallback(Context context, IDiLinkAudioStateCallback iDiLinkAudioStateCallback);

    int releaseDenoiseMode(int i, IDiLinkOnDenoiseFocusChangeListener iDiLinkOnDenoiseFocusChangeListener);

    int requestDenoiseMode(int i, IDiLinkOnDenoiseFocusChangeListener iDiLinkOnDenoiseFocusChangeListener);

    int requestUseVehicleSpeaker(Context context);

    int setAutoVoiceActiveState(boolean z, boolean z2);

    void setCallState(int i, int i2);

    void setHeadUnitOutputDevice(int i);

    void setMediaType(int i);

    void setMuteState(int i, boolean z);

    void setNaviMuteState(boolean z);

    void setPlayMode(int i, int i2);

    void setPlayProgress(int i, int i2);

    void setPlayState(int i, int i2);

    void setProjectVideoMode(Context context, boolean z, int i);

    void setStreamMuteForSystem(int i, boolean z);

    void setStreamVolume(int i, int i2, int i3, int i4, int i5);

    int setTrackVolumeDuck(boolean z);

    void setVehicleSpeakerPermitState(Context context, boolean z);

    void startAudioOutput();

    void startAudioOutput(@NonNull String str);

    void stopAudioOutput();

    void stopAudioOutput(@NonNull String str);

    void unregisterDiLinkAudioStateCallback(Context context, IDiLinkAudioStateCallback iDiLinkAudioStateCallback);
}
