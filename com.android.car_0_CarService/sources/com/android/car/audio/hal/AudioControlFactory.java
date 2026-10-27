package com.android.car.audio.hal;

import android.hardware.automotive.audiocontrol.V2_0.IAudioControl;
import android.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public final class AudioControlFactory {
    private static final String TAG = AudioControlWrapper.class.getSimpleName();

    public static AudioControlWrapper newAudioControl() {
        IAudioControl audioControlV2 = AudioControlWrapperV2.getService();
        if (audioControlV2 != null) {
            return new AudioControlWrapperV2(audioControlV2);
        }
        Log.i(TAG, "IAudioControl@V2.0 not in the manifest");
        android.hardware.automotive.audiocontrol.V1_0.IAudioControl audioControlV1 = AudioControlWrapperV1.getService();
        if (audioControlV1 != null) {
            Log.w(TAG, "IAudioControl V1.0 is deprecated. Consider upgrading to V2.0");
            return new AudioControlWrapperV1(audioControlV1);
        }
        throw new IllegalStateException("No version of AudioControl HAL in the manifest");
    }
}
