package androidx.media;

import android.media.AudioAttributes;
import defpackage.kl3;

/* JADX INFO: loaded from: classes.dex */
public class AudioAttributesImplApi21Parcelizer {
    public static AudioAttributesImplApi21 read(kl3 kl3Var) {
        AudioAttributesImplApi21 audioAttributesImplApi21 = new AudioAttributesImplApi21();
        audioAttributesImplApi21.a = (AudioAttributes) kl3Var.l(audioAttributesImplApi21.a, 1);
        audioAttributesImplApi21.b = kl3Var.j(audioAttributesImplApi21.b, 2);
        return audioAttributesImplApi21;
    }

    public static void write(AudioAttributesImplApi21 audioAttributesImplApi21, kl3 kl3Var) {
        kl3Var.getClass();
        AudioAttributes audioAttributes = audioAttributesImplApi21.a;
        kl3Var.o(1);
        kl3Var.u(audioAttributes);
        kl3Var.t(audioAttributesImplApi21.b, 2);
    }
}
