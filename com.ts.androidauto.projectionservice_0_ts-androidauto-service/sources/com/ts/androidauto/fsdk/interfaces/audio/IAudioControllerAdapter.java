package com.ts.androidauto.fsdk.interfaces.audio;

import android.media.AudioAttributes;
import com.ts.androidauto.fsdk.common.ResourceConst;

/* JADX INFO: loaded from: classes2.dex */
public interface IAudioControllerAdapter {
    int duckAudio(int i);

    int duckAudio(int i, int i2, int i3);

    AudioAttributes getAudioAttributes(ResourceConst.AudioId audioId);

    boolean getLastAudioFocusState(int i);

    int getMicSourceId();

    void init();

    void registerAudioListener(IAudioControllerListener iAudioControllerListener);

    int requestAudioFocus(ResourceConst.AudioFocusRequest audioFocusRequest);

    void requestChangeEntertainmentMute(int i);

    int requestMicFocus(ResourceConst.MicFocusRequest micFocusRequest);

    int unDuckAudio(int i);

    int unDuckAudio(int i, int i2);

    void unregisterAudioListener(IAudioControllerListener iAudioControllerListener);
}
