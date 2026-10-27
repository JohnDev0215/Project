package com.amazon.music.voice;

/* JADX INFO: loaded from: classes4.dex */
public interface AlexaVolumeController {
    void fadeVolume(float f);

    float getMaximumVolume();

    float getMinimumVolume();

    float getVolume();

    void setVolume(float f);
}
