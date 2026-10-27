package androidx.media;

import android.media.AudioManager;

/* JADX INFO: loaded from: classes.dex */
public final class AudioManagerCompat {
    public static final int AUDIOFOCUS_GAIN = 1;
    public static final int AUDIOFOCUS_GAIN_TRANSIENT = 2;
    public static final int AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE = 4;
    public static final int AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK = 3;
    private static final String TAG = "AudioManCompat";

    public static int requestAudioFocus(AudioManager audioManager, AudioFocusRequestCompat audioFocusRequestCompat) {
        if (audioManager == null) {
            throw new IllegalArgumentException("AudioManager must not be null");
        }
        if (audioFocusRequestCompat == null) {
            throw new IllegalArgumentException("AudioFocusRequestCompat must not be null");
        }
        return audioManager.requestAudioFocus(audioFocusRequestCompat.getAudioFocusRequest());
    }

    public static int abandonAudioFocusRequest(AudioManager audioManager, AudioFocusRequestCompat audioFocusRequestCompat) {
        if (audioManager == null) {
            throw new IllegalArgumentException("AudioManager must not be null");
        }
        if (audioFocusRequestCompat == null) {
            throw new IllegalArgumentException("AudioFocusRequestCompat must not be null");
        }
        return audioManager.abandonAudioFocusRequest(audioFocusRequestCompat.getAudioFocusRequest());
    }

    private AudioManagerCompat() {
    }
}
