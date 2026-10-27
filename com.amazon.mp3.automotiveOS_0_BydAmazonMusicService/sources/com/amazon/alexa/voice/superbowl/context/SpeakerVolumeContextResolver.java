package com.amazon.alexa.voice.superbowl.context;

import android.content.Context;
import android.media.AudioManager;
import com.amazon.alexa.voice.core.processor.superbowl.ContextResolver;
import com.amazon.alexa.voice.core.processor.superbowl.context.SpeakerVolumeContext;
import com.visualon.OSMPUtils.voMimeTypes;

/* JADX INFO: loaded from: classes2.dex */
public final class SpeakerVolumeContextResolver implements ContextResolver<SpeakerVolumeContext> {
    private final AudioManager audioManager;

    public SpeakerVolumeContextResolver(Context context) {
        this.audioManager = (AudioManager) context.getSystemService(voMimeTypes.VOBASE_TYPE_AUDIO);
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.ContextResolver
    public SpeakerVolumeContext resolve() {
        return new SpeakerVolumeContext.Builder().muted(isMicrophoneMuted()).volume(getCurrentMusicVolume(0, 100)).build();
    }

    private boolean isMicrophoneMuted() {
        return this.audioManager.isMicrophoneMute();
    }

    private int getCurrentMusicVolume(int i, int i2) {
        return i + ((i2 * this.audioManager.getStreamVolume(3)) / this.audioManager.getStreamMaxVolume(3));
    }
}
