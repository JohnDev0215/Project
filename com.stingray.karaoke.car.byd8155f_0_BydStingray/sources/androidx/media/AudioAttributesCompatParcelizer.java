package androidx.media;

import x3.AbstractC2674b;
import x3.InterfaceC2676d;

/* JADX INFO: loaded from: classes.dex */
public class AudioAttributesCompatParcelizer {
    public static AudioAttributesCompat read(AbstractC2674b abstractC2674b) {
        AudioAttributesCompat audioAttributesCompat = new AudioAttributesCompat();
        InterfaceC2676d interfaceC2676dH = audioAttributesCompat.f12013a;
        if (abstractC2674b.e(1)) {
            interfaceC2676dH = abstractC2674b.h();
        }
        audioAttributesCompat.f12013a = (AudioAttributesImpl) interfaceC2676dH;
        return audioAttributesCompat;
    }

    public static void write(AudioAttributesCompat audioAttributesCompat, AbstractC2674b abstractC2674b) {
        abstractC2674b.getClass();
        AudioAttributesImpl audioAttributesImpl = audioAttributesCompat.f12013a;
        abstractC2674b.i(1);
        abstractC2674b.l(audioAttributesImpl);
    }
}
