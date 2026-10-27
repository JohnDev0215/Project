package androidx.media;

import x3.AbstractC2674b;

/* JADX INFO: loaded from: classes.dex */
public class AudioAttributesImplBaseParcelizer {
    public static AudioAttributesImplBase read(AbstractC2674b abstractC2674b) {
        AudioAttributesImplBase audioAttributesImplBase = new AudioAttributesImplBase();
        audioAttributesImplBase.f12016a = abstractC2674b.f(audioAttributesImplBase.f12016a, 1);
        audioAttributesImplBase.f12017b = abstractC2674b.f(audioAttributesImplBase.f12017b, 2);
        audioAttributesImplBase.c = abstractC2674b.f(audioAttributesImplBase.c, 3);
        audioAttributesImplBase.d = abstractC2674b.f(audioAttributesImplBase.d, 4);
        return audioAttributesImplBase;
    }

    public static void write(AudioAttributesImplBase audioAttributesImplBase, AbstractC2674b abstractC2674b) {
        abstractC2674b.getClass();
        abstractC2674b.j(audioAttributesImplBase.f12016a, 1);
        abstractC2674b.j(audioAttributesImplBase.f12017b, 2);
        abstractC2674b.j(audioAttributesImplBase.c, 3);
        abstractC2674b.j(audioAttributesImplBase.d, 4);
    }
}
