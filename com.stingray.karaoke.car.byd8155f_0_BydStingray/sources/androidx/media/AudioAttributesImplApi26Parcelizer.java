package androidx.media;

import android.media.AudioAttributes;
import x3.AbstractC2674b;

/* JADX INFO: loaded from: classes.dex */
public class AudioAttributesImplApi26Parcelizer {
    public static AudioAttributesImplApi26 read(AbstractC2674b abstractC2674b) {
        AudioAttributesImplApi26 audioAttributesImplApi26 = new AudioAttributesImplApi26();
        audioAttributesImplApi26.f12014a = (AudioAttributes) abstractC2674b.g(audioAttributesImplApi26.f12014a, 1);
        audioAttributesImplApi26.f12015b = abstractC2674b.f(audioAttributesImplApi26.f12015b, 2);
        return audioAttributesImplApi26;
    }

    public static void write(AudioAttributesImplApi26 audioAttributesImplApi26, AbstractC2674b abstractC2674b) {
        abstractC2674b.getClass();
        abstractC2674b.k(audioAttributesImplApi26.f12014a, 1);
        abstractC2674b.j(audioAttributesImplApi26.f12015b, 2);
    }
}
