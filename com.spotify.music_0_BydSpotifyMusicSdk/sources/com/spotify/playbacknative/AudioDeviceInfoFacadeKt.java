package com.spotify.playbacknative;

import android.media.AudioDeviceInfo;
import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public final class AudioDeviceInfoFacadeKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String getAddressFrom(AudioDeviceInfo audioDeviceInfo) {
        if (Build.VERSION.SDK_INT >= 28) {
            return audioDeviceInfo.getAddress();
        }
        return null;
    }
}
