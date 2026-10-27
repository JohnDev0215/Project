package com.amazon.avod.media.playback.render;

import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class AudioOutputSettings {
    private volatile boolean mIsOpticalOutputEnabled;

    public static AudioOutputSettings getInstance() {
        return SingletonHolder.mAudioOutputSettings;
    }

    private static class SingletonHolder {
        private static final AudioOutputSettings mAudioOutputSettings = new AudioOutputSettings();

        private SingletonHolder() {
        }
    }

    private AudioOutputSettings() {
    }

    public boolean isOpticalOutputEnabled() {
        return this.mIsOpticalOutputEnabled;
    }
}
