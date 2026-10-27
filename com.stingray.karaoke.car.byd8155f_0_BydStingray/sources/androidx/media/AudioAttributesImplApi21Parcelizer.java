package androidx.media;

import android.media.AudioAttributes;
import x3.AbstractC2674b;

/* JADX INFO: loaded from: classes.dex */
public class AudioAttributesImplApi21Parcelizer {
    public static AudioAttributesImplApi21 read(AbstractC2674b abstractC2674b) {
        AudioAttributesImplApi21 audioAttributesImplApi21 = new AudioAttributesImplApi21();
        audioAttributesImplApi21.f12014a = (AudioAttributes) abstractC2674b.g(audioAttributesImplApi21.f12014a, 1);
        audioAttributesImplApi21.f12015b = abstractC2674b.f(audioAttributesImplApi21.f12015b, 2);
        return audioAttributesImplApi21;
    }

    public static void write(AudioAttributesImplApi21 audioAttributesImplApi21, AbstractC2674b abstractC2674b) {
        abstractC2674b.getClass();
        abstractC2674b.k(audioAttributesImplApi21.f12014a, 1);
        abstractC2674b.j(audioAttributesImplApi21.f12015b, 2);
    }
}
