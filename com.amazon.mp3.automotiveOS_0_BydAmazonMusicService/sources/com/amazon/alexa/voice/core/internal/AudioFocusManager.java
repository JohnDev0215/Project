package com.amazon.alexa.voice.core.internal;

import android.content.Context;
import android.media.AudioManager;
import com.visualon.OSMPUtils.voMimeTypes;

/* JADX INFO: loaded from: classes2.dex */
public final class AudioFocusManager {
    private final AudioManager audioManager;
    private final int durationHint;

    public AudioFocusManager(Context context, int i) {
        this.durationHint = i;
        this.audioManager = (AudioManager) context.getSystemService(voMimeTypes.VOBASE_TYPE_AUDIO);
    }

    public Object acquire() {
        DefaultAudioFocus defaultAudioFocus = new DefaultAudioFocus();
        this.audioManager.requestAudioFocus(defaultAudioFocus, 3, this.durationHint);
        return defaultAudioFocus;
    }

    public void release(Object obj) {
        if (obj instanceof AudioManager.OnAudioFocusChangeListener) {
            this.audioManager.abandonAudioFocus((AudioManager.OnAudioFocusChangeListener) obj);
        }
    }

    static class DefaultAudioFocus implements AudioManager.OnAudioFocusChangeListener {
        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
        }

        DefaultAudioFocus() {
        }
    }
}
