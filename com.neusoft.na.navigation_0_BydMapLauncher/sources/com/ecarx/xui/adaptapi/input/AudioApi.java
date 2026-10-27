package com.ecarx.xui.adaptapi.input;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AudioApi {
    private static AudioVolume _audio;

    public abstract int getActiveAudioFocusType(Context context);

    public static AudioApi create() {
        if (_audio == null) {
            _audio = new AudioVolume();
        }
        return _audio;
    }
}
