package com.amazon.mp3.voice;

import com.amazon.music.voice.AlexaVolumeController;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class VoiceVolumeController implements AlexaVolumeController {
    private float mVolume;

    @Override // com.amazon.music.voice.AlexaVolumeController
    public float getMaximumVolume() {
        return 1.0f;
    }

    @Override // com.amazon.music.voice.AlexaVolumeController
    public float getMinimumVolume() {
        return 0.0f;
    }

    @Override // com.amazon.music.voice.AlexaVolumeController
    public void setVolume(final float volume) {
        Validate.inclusiveBetween(0.0d, 1.0d, volume);
        this.mVolume = volume;
    }

    @Override // com.amazon.music.voice.AlexaVolumeController
    public float getVolume() {
        return this.mVolume;
    }

    public void fadeVolume(final float volume, final Runnable completionRunnable) {
        setVolume(volume);
    }

    @Override // com.amazon.music.voice.AlexaVolumeController
    public void fadeVolume(final float volume) {
        fadeVolume(volume, null);
    }
}
